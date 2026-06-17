from langchain_openai import ChatOpenAI
from langchain.agents import create_agent
from langchain.tools import tool
import os

@tool
def read_file(path: str):
    """读取文件"""
    with open(path, "r", encoding="utf8") as f:
        return f.read()

@tool
def write_file(path: str, content: str):
    """写文件"""
    with open(path, "w", encoding="utf8") as f:
        f.write(content)
    return "success"

@tool
def list_directory(path="."):
    """列出目录"""
    import os

    result = []

    for root, dirs, files in os.walk(path):
        result.append(root)

        for f in files:
            result.append(
                os.path.join(root, f)
            )

    return "\n".join(result)

@tool
def find_file(filename: str):
    """根据文件名查找文件"""

    result = []

    for root, dirs, files in os.walk("."):
        if filename in files:
            result.append(
                os.path.join(root, filename)
            )

    return "\n".join(result)

model = ChatOpenAI(
    model="gemma-4-e2b",
    api_key="lm-studio",
    base_url="http://localhost:1234/v1"
)

agent = create_agent(
    model=model,
    tools=[
        read_file,
        write_file,
        list_directory,
        find_file
    ]
)

# result1 = agent.invoke(
#     {
#         "messages":[
#             (
#                 "system",
#                 """
#                 修改文件前必须：
#                 1. 如果知道文件名，则调用find_file。否则，调用list_directory
#                 2. 再调用read_file
#                 3. 不允许猜测路径
#                 4. 找不到文件必须询问用户
#                 """
#             ),
#             (
#                 "user",
#                 "找到你好世界的Java代码，在main方法增加Arrays.sort测试"
#             )
#         ]
#     }
# )
#
# print(result1.get("messages")[-1].content)


result2 = agent.invoke(
    {
        "messages":[
            (
                "system",
                """
                修改文件前必须：
                1. 如果知道文件名，则调用find_file。否则，调用list_directory
                2. 再调用read_file
                3. 不允许猜测路径
                4. 找不到文件必须询问用户
                """
            ),
            (
                "user",
                "读取HelloWorld.java，在main方法增加Arrays.sort测试"
            )
        ]
    }
)

print(result2.get("messages")[-1].content)