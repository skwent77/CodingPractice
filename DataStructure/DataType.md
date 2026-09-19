```python
# 정수
n = 10

# 문자열
word = "python"

# 리스트
numbers = [1, 2, 3]

# 튜플
position = (2, 3)

# 집합
visited = {1, 2, 3}

# 딕셔너리
score = {"Shin": 100, "Jae": 80}

# 불리언
is_valid = True

# 없음
NoneType


## 응용 자료형
스택, 큐
class Stack:
    def __init__(self):
        self.items = []

    def push(self, value):
        self.items.append(value)

    def pop(self):
        return self.items.pop()
```


스택, 큐, 힙은 기본 자료형이 아니라 자료구조입니다. 파이썬에서는 각각 list, collections.deque, heapq 등을 이용해 구현
