import os
from dotenv import load_dotenv
from langchain.agents import create_agent
from langchain_core.tools import tool

load_dotenv()

@tool
def get_weather(city: str) -> str:
    """주어진 도시의 날씨 정보를 반환한다."""
    # 실제로는 여기서 외부 API를 호출하면 됨
    fake_weather_db = {
        "서울": "맑음, 22도",
        "부산": "흐림, 25도",
    }
    return fake_weather_db.get(city, f"{city}의 날씨 정보를 찾을 수 없습니다.")

@tool
def add(a: int, b: int) -> int:
    """두 정수를 더한다."""
    return a + b

agent = create_agent(
    model="openai:gpt-5.4-mini",
    tools=[get_weather, add],
    system_prompt="너는 도구를 활용해 사용자 질문에 답하는 비서다. "
           "필요하면 get_weather, add 도구를 써라.",
)

result = agent.invoke({
    "messages": [{
        "role": "user",
        "content": "서울 날씨 알려주고, 3이랑 7 더한 값도 알려줘",
    }]
})
print(result["messages"][-1].content)
