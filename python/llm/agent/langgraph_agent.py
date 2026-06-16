from typing import Annotated, Literal
from typing_extensions import TypedDict

from langchain_core.messages import BaseMessage, HumanMessage, SystemMessage
from langchain_core.tools import tool
from langchain_openai import ChatOpenAI
from langgraph.graph import END, StateGraph, START
from langgraph.graph.message import add_messages
from langgraph.prebuilt import ToolNode

# ==========================================
# 1. 初始化 LM Studio 中的 Gemma 模型
# ==========================================
# 请确保你的 LM Studio 已经开启了 Local Server
llm = ChatOpenAI(
    base_url="http://localhost:1234/v1",  # 加上了 /v1 后缀
    api_key="lm-studio",
    model="google/gemma-4-e2b-qat",  # 如果报错，请替换为 LM Studio 顶部的完整模型 ID
    temperature=0.1
)

# ==========================================
# 2. 定义工具 (Tools)
# ==========================================
@tool
def get_weather(city: str) -> str:
    """获取指定城市的实时天气情况。"""
    print(f"\n[工具执行] 正在查询 {city} 的天气...")
    # 这里做个简单的模拟，实际可以接 API
    if "北京" in city:
        return "晴天，28度，微风。"
    elif "上海" in city:
        return "阴天有小雨，22度。"
    else:
        return "晴间多云，25度。"

tools = [get_weather]
# 将工具绑定到模型，让模型知道自己有哪些武器可用
llm_with_tools = llm.bind_tools(tools)


# ==========================================
# 3. 定义 LangGraph 的状态 (State)
# ==========================================
# State 决定了节点之间传递什么数据。这里我们用 add_messages 让新消息自动追加到对话历史中
class AgentState(TypedDict):
    messages: Annotated[list[BaseMessage], add_messages]


# ==========================================
# 4. 定义图的节点 (Nodes)
# ==========================================

# 节点 1：模型思考节点
def call_model(state: AgentState):
    messages = state["messages"]
    # 如果还没有系统提示词，可以在这里加一个（可选）
    if not any(isinstance(m, SystemMessage) for m in messages):
        messages = [SystemMessage(content="你是一个智能助理，请使用工具来回答天气相关的问题。")] + messages

    response = llm_with_tools.invoke(messages)
    # 返回更新后的消息列表
    return {"messages": [response]}

# 节点 2：工具执行节点
# LangGraph 提供了一个预制的 ToolNode，能自动帮你根据模型的指令去执行对应的 Python 函数
tool_node = ToolNode(tools)


# ==========================================
# 5. 定义条件边 (Conditional Edges)
# ==========================================
# 这个函数用来判断：模型思考完后，下一步该去哪？
def should_continue(state: AgentState) -> Literal["tools", "__end__"]:
    last_message = state["messages"][-1]

    # 如果模型返回了 tool_calls，说明它想调用工具，走 "tools" 节点
    if last_message.tool_calls:
        return "tools"

    # 如果没有 tool_calls，说明回答完毕，走结束流程
    return "__end__"


# ==========================================
# 6. 构建图 (Construct Graph)
# ==========================================
workflow = StateGraph(AgentState)

# 添加节点
workflow.add_node("agent", call_model)
workflow.add_node("tools", tool_node)

# 设置起点：从 "agent" 开始
workflow.add_edge(START, "agent")

# 设置条件边：agent 执行完后，调用 should_continue 决定去 "tools" 还是 结束
workflow.add_conditional_edges(
    "agent",
    should_continue,
)

# 设置普通边：工具执行完后，必须重新回到 "agent" 让模型看一眼工具结果，继续思考
workflow.add_edge("tools", "agent")

# 编译图
app = workflow.compile()


# ==========================================
# 7. 测试运行
# ==========================================
if __name__ == "__main__":
    inputs = {"messages": [HumanMessage(content="请问现在北京的天气怎么样？")]}

    print("--- 开始执行 LangGraph Agent ---")
    # 使用 stream 模式可以清晰看到图在节点之间流转的过程
    for output in app.stream(inputs, stream_mode="updates"):
        for node_name, node_output in output.items():
            print(f"\n[进入节点]: {node_name}")
            # 打印当前节点产生的最新消息
            if "messages" in node_output:
                last_msg = node_output["messages"][-1]
                print(f"内容: {last_msg.content}")
                if hasattr(last_msg, 'tool_calls') and last_msg.tool_calls:
                    print(f"尝试调用工具: {last_msg.tool_calls}")

    print("\n--- 执行结束 ---")