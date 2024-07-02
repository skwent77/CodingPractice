package a0702.kruskal;

import java.io.*;
import java.util.*;

public class KruskalMain {
	static int N;
//	static List<Edge> g = new int[N][3]; 
	//static Edge[] g = new Edge[E];
	//Edge클래스 comparable 인터페이스 cost정렬 구현해보기 
	static int[][] g; // 간선 입력 저장용

	static int[] p;

	static void make() {
		// 정점 번호는 0,1,2,3,4
		p = new int[N];
		for (int i = 0; i < N; i++) {
			p[i] = i;
		}
	}

	/* 1,2,3예제로 설명 */
	static int find(int a) {
		if (p[a] == a)
			return a;
		return p[a] = find(p[a]);
	}

	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b); // This should be find(b) instead of find(a)
		if (aRoot == bRoot) return false;
		p[bRoot] = aRoot;
		return true;
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/a0702/kruskal/input_kruskal.txt"));
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		int E = sc.nextInt();
		System.out.println(N + " " + E);

		g = new int[E][3];
//start

		for (int i = 0; i < E; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			int cost = sc.nextInt();
//            g[i]= {from.to.cost}
			g[i] = new int[] { from, to, cost };
			for (int[] a : g) {
				System.out.println(Arrays.toString(a));
				System.out.println();
			}
		}
		for (int[] a : g) {
			System.out.println(Arrays.toString(a));
		}
		//int[]은 comparable인터페이스 내장 x 
		Arrays.sort(g,(o1,o2)->Integer.compare(o1[2],o2[2]));
		make();
		int sum = 0, cnt = 0;
		//sum은 cost비용의 합, cnt는 n-1
		/*
		 * g[i]= new String[]
		Arrays.sort(g,(o1,o2)->Integer.compare(o1[2].compareTo(o2[2])));
		*/
		for (int[] e : g) {
			if(union(e[0],e[1]))
					{
				
						sum += e[2];
						if(++cnt == N-1) break;
					
					}
//			System.out.println(Arrays.toString(sum));
		}
		System.out.println(sum);
		sc.close();
	}

}
