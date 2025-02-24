def solve(s, queries):
    import sys
    input = sys.stdin.readline
    
    # 예시이므로 전역 변수 대신 내부에서 관리
    n = len(s)
    
    # 각 인덱스에 대한 알파벳
    charOf = [''] * (n+1)
    for i, ch in enumerate(s, start=1):
        charOf[i] = ch
    
    class StringSet:
        __slots__ = ('creationId', 'indices', 'alive')
        def __init__(self, cid):
            self.creationId = cid
            # 파이썬에서는 sorted list로 관리. 대량 삽입/삭제 시 비효율 가능성 있음.
            self.indices = []
            self.alive = True
    
    # 문자열 객체들을 모아둘 리스트
    allStrings = []
    global_cid = 0
    
    # 초기 문자열 하나 생성
    S0 = StringSet(global_cid)
    S0.indices = list(range(1, n+1))  # [1,2,...,n]
    allStrings.append(S0)
    
    # 각 문자가 어떤 StringSet에 속하는지 관리
    posToString = [None] * (n+1)
    for i in range(1, n+1):
        posToString[i] = S0
    
    result = []  # 답안을 모을 리스트
    
    def create_new_string():
        nonlocal global_cid
        global_cid += 1
        ss = StringSet(global_cid)
        allStrings.append(ss)
        return ss
    
    for q in queries:
        parts = q.split()
        t = int(parts[0])
        
        if t == 1:
            # 1 x y
            x, y = int(parts[1]), int(parts[2])
            # 같은 문자열인지 확인
            if posToString[x] == posToString[y]:
                result.append("YES")
            else:
                result.append("NO")
        
        elif t == 2:
            # 2 x word
            x = int(parts[1])
            word = parts[2]
            wordSet = set(word)
            oldString = posToString[x]
            if not oldString.alive:
                # 혹은 그냥 무시
                continue
            
            newString = create_new_string()
            moved_indices = []
            
            keep = []
            for idx in oldString.indices:
                if charOf[idx] in wordSet:
                    moved_indices.append(idx)
                else:
                    keep.append(idx)
            
            # 갱신
            oldString.indices = keep
            if len(oldString.indices) == 0:
                oldString.alive = False
            
            newString.indices = moved_indices
            if len(moved_indices) == 0:
                newString.alive = False  # 비어 있으면 바로 소멸 처리 가능
            else:
                for idx in moved_indices:
                    posToString[idx] = newString
        
        elif t == 3:
            # 3 x y word
            x_, y_ = int(parts[1]), int(parts[2])
            word = parts[3]
            wordSet = set(word)
            
            newString = create_new_string()
            
            moved_indices = []
            # x~y 범위 순회
            for i in range(x_, y_+1):
                if charOf[i] in wordSet:
                    # i가 속한 oldString에서 제거 후 newString으로 옮김
                    oldString = posToString[i]
                    if oldString.alive:
                        # oldString.indices에서 i 제거
                        # (리스트라면 remove(i)에 O(m) 시간)
                        oldString.indices.remove(i)
                        # posToString 갱신
                        posToString[i] = newString
                        moved_indices.append(i)
                        if len(oldString.indices) == 0:
                            oldString.alive = False
            newString.indices = sorted(moved_indices)
            if len(moved_indices) == 0:
                newString.alive = False
        
        elif t == 4:
            # 4 x y
            x_, y_ = int(parts[1]), int(parts[2])
            A = posToString[x_]
            B = posToString[y_]
            if A == B:
                # 이미 같은 문자열이면 패스
                continue
            # 둘 중 누가 먼저 생성되었나?
            if A.creationId < B.creationId:
                # A가 먼저
                # B의 문자들 A에 추가
                for idx in B.indices:
                    posToString[idx] = A
                A.indices.extend(B.indices)
                A.indices.sort()
                B.alive = False
                B.indices = []
            else:
                # B가 먼저
                for idx in A.indices:
                    posToString[idx] = B
                B.indices.extend(A.indices)
                B.indices.sort()
                A.alive = False
                A.indices = []
        
        elif t == 5:
            # 존재하는 모든 문자열에 대해 알파벳 빈도 출력
            # 생성순 오름차순으로 확인
            aliveSets = [ss for ss in allStrings if ss.alive]
            aliveSets.sort(key=lambda ss: ss.creationId)
            
            for ss in aliveSets:
                freq = {}
                for idx in ss.indices:
                    ch = charOf[idx]
                    freq[ch] = freq.get(ch, 0) + 1
                # freq를 a~z 순으로 출력하되, 개수가 0인 것은 안 넣음
                # 문제 설명에는 "포함된 알파벳만" 출력 예시가 있으므로, freq>0 인 것만
                comp = []
                for c in sorted(freq.keys()):
                    comp.append(f"{c} {freq[c]}")
                result.append(" ".join(comp))
    
    return result
