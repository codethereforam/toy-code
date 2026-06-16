from langchain_core.messages import HumanMessage, SystemMessage
from langchain_core.tools import tool
from langchain_openai import ChatOpenAI

# 1. 初始化本地 Gemma 模型
# lm-studio 启动后默认支持 OpenAI 格式的 API 接口
llm = ChatOpenAI(
    base_url="http://localhost:1234/v1",
    api_key="xxx",  # 本地运行不需要真正的 key，填任意字符串即可
    model="gemma4",    # 替换为你本地下载的具体模型名称
    temperature=0.1     # 降低随机性，让 Agent 推理更稳定
)

# 2. 定义 Agent 可以使用的工具 (Tool)
@tool
def calculate_word_length(word: str) -> int:
    """计算一个单词或字符串的字母长度。"""
    print(f"\n[工具调用] 正在计算 '{word}' 的长度...")
    return len(word)

# 将工具绑定到模型
tools = [calculate_word_length]
llm_with_tools = llm.bind_tools(tools)

# 3. 模拟 Agent 的决策循环 (ReAct 核心逻辑)
def run_agent(user_query: str):
    print(f"\n用户问题: {user_query}")
    
    # 构建初始消息，引导 Agent 的身份
    messages = [
        SystemMessage(content="你是一个乐于助人的 AI 助理。如果需要计算单词长度，请务必使用配套的工具。"),
        HumanMessage(content=user_query)
    ]
    
    # 第一次思考：模型决定是直接回答还是用工具
    response = llm_with_tools.invoke(messages)
    
    # 检查模型是否决定调用工具
    if response.tool_calls:
        for tool_call in response.tool_calls:
            if tool_call["name"] == "calculate_word_length":
                # 执行工具并获取结果
                args = tool_call["args"]
                tool_result = calculate_word_length.invoke(args)
                
                # 将工具执行结果追加到对话历史中，让模型重新整合回答
                messages.append(response)
                messages.append({
                    "role": "tool",
                    "content": str(tool_result),
                    "tool_call_id": tool_call["id"]
                })
                
                # 第二次思考：结合工具结果给出最终人类可读的回答
                final_response = llm_with_tools.invoke(messages)
                print(f"Agent 最终回答: {final_response.content}")
    else:
        # 如果不需要工具，直接输出回答
        print(f"Agent 直接回答: {response.content}")

# --- 测试 Agent ---
if __name__ == "__main__":
    # 测试场景 1：不需要工具
    run_agent("你好，请问你是谁？")
    
    print("-" * 30)
    
    # 测试场景 2：触发工具调用
    run_agent("请帮我算一下 'Supercalifragilisticexpialidocious' 这个单词有多少个字母？")