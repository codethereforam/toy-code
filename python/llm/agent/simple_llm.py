from openai import OpenAI

client = OpenAI(
    api_key="lm-studio",
    base_url="http://localhost:1234/v1"
)

response = client.chat.completions.create(
    model="gemma-4-e2b",
    messages=[
        {
            "role": "user",
            "content": "写一个快速排序"
        }
    ]
)

print(response.choices[0].message.content)