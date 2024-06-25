//bfs는 주로 queue 자로구조 이용
static boolean[][] v;        
static ArrayDeque<int[]> q = new ArrayDeque<>();
2가지 버전 depth 인자로 가지는 것과 안 가지는 것
/* ArrayDeque 메서드 정리,  
static void bfs(int i, int j) {
        static ArrayDeque<int[]> q = new ArrayDeque<>();
        v[i][j] = true;

        v[i][j] = true;
        q.poll()
        
  

}

static void bfs(int i , int j, int depth) {



}
