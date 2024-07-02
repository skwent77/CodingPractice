//줄 세우기
package a0702.kruskal;

import java.io.*;
import java.util.*;
//큐를 이용한 khan's algorithm

public class BJ_2252 {
	static int N, M;
	static StringTokenizer st;
	// graph d.s.
	static List<Integer>[] g;
	static int[] indegree;
	static BufferedWriter bw;
	static void bfs() {

	}

	public static void main(String[] args) throws Exception {
		// TODO 그래프에서 선후관계 조건이 있을 떄 노드 순서 정렬
		System.setIn(new FileInputStream("src/a0702/kruskal/input_bj_2252.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		g = new List[N + 1];
		indegree = new int[N + 1]; // 진입 차수: 학생 수+1

		for (int i = 1; i < g.length; i++)
			g[i] = new ArrayList<>();
		// 정점 i의 인접 리스트 저장
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			g[from].add(to);
			indegree[to]++;
		}

		for (List<Integer> tar : g)
			System.out.println(tar);
	}

}
