from datetime import datetime

from langchain.tools import tool
from langchain_openai import ChatOpenAI
from langchain.agents import create_agent

@tool
def get_time() -> str:
    """获取当前时间"""
    return str(datetime.now())


model = ChatOpenAI(
    model="gemma-4-e2b",
    api_key="lm-studio",
    base_url="http://localhost:1234/v1"
)

agent = create_agent(
    model=model,
    tools=[get_time]
)

result = agent.invoke(
    {
        "messages": [
            (
                "user",
                "现在几点了"
            )
        ]
    }
)
print(result.get("messages")[-1].content)