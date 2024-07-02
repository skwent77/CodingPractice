package a0702.kruskal;

import java.io.*;
import java.util.*;

public class DisjointSetMain {
	static int N;
	// 1차원 배열 사용
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
		if(p[a]==a)
			return a;
		
		return p[a]=find(p[a]);
		
	}
	static boolean union(int a, int b) {
	    int aRoot = find(a);
	    int bRoot = find(b); // This should be find(b) instead of find(a)
	    if (aRoot == bRoot)
	        return false;
	    p[bRoot] = aRoot; // ㄱㅎ마다  달라 
	    return true;
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		N = 5;
		make();
		System.out.println(union(2,1));
		System.out.println("{0,1,2,3,4}");
		
		System.out.println(Arrays.toString(p));
//		System.out.println(union(0,1));

		System.out.println(union(3,2));
		System.out.println("{0,1,2,3,4}");
		
		System.out.println(Arrays.toString(p));
		
		System.out.println(union(4,3));
		
		System.out.println("{0,1,2,3,4}");
		
		System.out.println(Arrays.toString(p));
		
		System.out.println("==find==");
		System.out.println(find(4));
		System.out.println(find(3));
		System.out.println(Arrays.toString(p));
	}

}
