package a0703.prim;

import java.io.*;
import java.util.*;
// 정점의 개수가 1만개인데 간선의 개수가 2만개다-> 인접리스트 ArrayList로 사용
// 넘으면 행렬 사용 (최대 5000x9999까지 가능) 
// 정점의 개수가 많지 않은 경우 (200개 이하)-> 행렬 사용
public class PrimMain {
	 	public static void main(String[] args) throws Exception{
			System.setIn(new FileInputStream("src/a0703/prim/input_prim.txt"));
			Scanner sc=new Scanner(System.in);
			int N=sc.nextInt();	//7
		List<int[]> [] g = new List[N]; for(int i=0;i<N;i++) g[i]=new ArrayList<>();
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				int c = sc.nextInt();
				if(c!=0) g[i].add(new int[] {j,c});
			}
		}
		boolean[] v = new boolean[N];
		int[] weight = new int[N];//프림 w[] this is for memoization
		for(int i=0;i<N;i++) weight[i]=Integer.MAX_VALUE;
	
		int sum = 0 , cnt =0;
		weight[0]=0; //첫 스텝) 0번 vertex부터 시작 지정.
		for (int i=0;i<N;i++) {
			int min = Integer.MAX_VALUE;
			int minVertex = -1;
			for(int j=0;j<N;j++) {
				//minEdge 배열 갱신
					if(!v[j]&&min>weight[j]) {
							min = weight[j];
							minVertex = j;
					}
			}
			
			v[minVertex]=true;
			sum+=min;
			System.out.println(Arrays.toString(v));
			System.out.println("minVertex="+minVertex);
			System.out.println("min="+min+"sum="+sum );
			
			if(cnt++ ==N-1) break; //MST가 완성이 되었는가? 
			
			for(int[] j:g[minVertex]) {
				//완성 안 된 경우 갱신
				if(!v[j[0]]&&weight[j[0]]>j[1]) {
					weight[j[0]]=j[1];
				}
			}
			System.out.println(Arrays.toString(weight));
		}
		//	for(List<Integer> a:g)System.out.println(a);System.out.println();
//		for(int i=0;i<N;i++)System.out.println(""+(char)(i+'A')+i+": "+g[i]);System.out.println();
		System.out.println(sum);
		sc.close();
	}

}

/*

=BFS===============
A B C D E F G :0->N
A C B E D F G :N->0
=DFS===============
A B D F E C G :0->N
A C E F G D B :N->0


=단방향======
x ->
0 ->1 2
1 ->3 4
2 ->4
3 ->5
4 ->5
5 ->6
6 ->

=양뱡향======
x <> 
A0 ->1 2
B1 ->0 3 4
C2 ->0 4
D3 ->1 5
E4 ->1 2 5
F5 ->3 4 6
G6 ->5

 */

