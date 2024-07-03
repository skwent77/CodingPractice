## 진도표
줄 세우기
문제집 1766 

Kruskal 0702
최소 스패팅 트리 1197

Prim - 0703
## Prim 알고리즘 동작 원리
필요한 변수: 방문배열, 비용배열
int[] weight = new int[N];  
 각 정점에 대해 다른 정점을 가지고 
방문 안 했으면 최소 비용, 최소 정점 갱신 
방문처리, 최소 비용 더하기 
int[] minEdge = new int[N] 
(1차원 배열을 사용하여 memoization)
prioritiyQueue
add() :  　우선순위 큐에 원소를 추가. 

큐가 꽉 찬 경우 에러 발생
</br>
offer :    우선순위 큐에 원소를 추가. 

값 추가 실패 시 false를 반환
