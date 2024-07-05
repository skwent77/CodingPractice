package a0705.dynamicprogramming;

import java.util.Arrays;

public class CoinDPMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int N =8;
		int[] dp = new int[N+1];
		for(int i=1;i<=N;i++) {
			int min = Integer.MAX_VALUE;
//			앞 조건은 Arr idx out exception방지 
			if(i-1>=0 &&min>dp[i-1]+1)
				min = dp[i-1]+1;
			if(i-4>=0 && min>dp[i-4]+1) //dp[0]도 쓰는지는 몰랐네 
				min = dp[i-4]+1;
			if(i-6>=0 && min>dp[i-6]+1)
				min = dp[i-6]+1;
			dp[i] = min;
		}
		int[] coin = {6,4,1};
		/*Arrays.fill(dp, Integer.MAX_VALUE); do[0]=0;*/
		//다익스트라 prim에서도 다 \
		
		
		for (int i = 1; i <=N ; i++)
			
			
			dp[i] = Integer.MAX_VALUE;
		System.out.println(dp[N]);
	}

}
