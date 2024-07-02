package a0702.kruskal;
import java.io.*;
import java.util.*;
//방향 그래프에서 적용 가능
public class TopologicalSortBfsMain {
	static int N, M;
	static List<Integer>[] g;	
	static int[] indegree; //정점의 진입차수 저장하는 배열
	static void bfs() {
		ArrayDeque <Integer> q = new ArrayDeque<>();
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		//comparator reverseorder 오름차순 
		for (int i=1;i<N+1;i++) {
			
			
			if(indegree[i]==0)
				q.offer(i);
		}
		while(!q.isEmpty())
		{
			int i=q.poll();
			System.out.println(i+ " ");
			//모든 정점을 방문하지 않으면
			
			//visited배열 추가를 하면 할 
			for(int j:g[i]) {
				indegree[j]--;
				if(indegree[j]==0)
					q.offer(j);
			}
		}
	
	}
	/*
	 * static void dfs(int i) { v[i]=true; System.out.print((char)(i+'A')+" ");
	 * for(int j:g[i]) { if(!v[j]) { dfs(j); } } }
	 */
//	static void bfs(int i) {
//		ArrayDeque<Integer> q=new ArrayDeque<>();
//		indegree[i]=true;
//		q.offer(i);
//		while(!q.isEmpty()) {
//			i=q.poll(); 진입차수가 0인 정점의 번호\ 

	
//			System.out.print((char)(i+'A')+" ");
//			for(int j:g[i]) { 	
//				if(!v[j]) {
//					v[j]=true;
//					q.offer(j);
//				}
//			}
//			
//		}
//	}
		public static void main(String[] args) throws Exception {
			System.setIn(new FileInputStream("src/a0702/kruskal/input_topologicalSort.txt"));
			Scanner sc=new Scanner(System.in);
			N=sc.nextInt();	//7
			M=sc.nextInt();	//간선 수
			g=new List[N+1]; 
			for(int i=0;i<N;i++) g[i]=new ArrayList<>();
			indegree=new int[N+1];
			
			for(int i=0;i<M;i++) {
				int a =sc.nextInt();
				int b =sc.nextInt();
				g[a].add(b);
				indegree[b]++;
				
			}
			System.out.println(Arrays.toString(indegree));
			for(List<Integer> a: g)
				System.out.println(a);
				System.out.println();
		}
		static void khan(int i) {
			
			
			
		}
}

