import requests
import pandas as pd
import numpy as np
from bs4 import BeautifulSoup
# url넣기
r = requests.get('')
# url 읽기

html = r.text
#text 로 변환

soup = BeautifulSoup(html, 'html.parser')
#Beautifulsoup 객체 생성

titles = soup.select('.size_content')
# html 중 class 가 size_content 요소에 접근 titles 에 저장
list = []
i=0
#
for title in titles:
    text = title.text   #텍스트로 변환
    content = text[text.find("(")+1:text.find(")")] #필요한 문자열만 가져오기 위해서 find 메소드 사용
    div = content.split(" ")    #가져온 문자열 공백으로 슬라이싱
    # print("성별:",div[0])       #성별 가져오기
    num = div[1].split('/')     #키, 몸무게
    # print("키:",num[0])         #키 가져오기
    # print("몸무게:",num[1])     #몸무게 가져오기
    results = {"no": i, "height": num[0], "weight" : num[1], "sex" : div[0]}
    list.append(results)
    # print(list[i])
    i = i + 1
df = pd.DataFrame(list)
end = df.set_index("no")
end.to_csv("C:\maching_learning\excel",encoding="cp949",mode="w",index=True)
print(end)
