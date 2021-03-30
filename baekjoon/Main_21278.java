package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_21278 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		boolean[][] arr = new boolean[N + 1][N + 1];
		int[][] sum = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				sum[i][j] = Integer.MAX_VALUE;
			}
		}
		for (int i = 1; i <= N; i++) {
			sum[i][i] = 0;
		}
		int a, b;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			arr[a][b] = true;
			arr[b][a] = true;
		}
		for (int i = 1; i <= N; i++) {
			LinkedList<Integer> q = new LinkedList<Integer>();
			q.add(i);
			while (!q.isEmpty()) {
				int pivot = q.poll();
				int cost = sum[i][pivot]+1;
				for (int j = 1; j <= N; j++) {
					if (arr[pivot][j] && sum[i][j] >cost ) {
						q.add(j);
						sum[i][j] = cost;
					}
				}
			}
		}
		int min = Integer.MAX_VALUE;
		a=0;
		b=0;
		for (int i = 1; i <=N; i++) {
			for (int j = i+1; j <=N; j++) {
				int temp = 0;
				for (int k = 1; k <=N; k++) {
					temp+=Math.min(sum[i][k], sum[j][k]);
				}
				if(min>temp) {
					min = temp;
					a=i;
					b=j;
				}
			}
		}
		System.out.println(a+" "+b+" "+min*2);
	}

}
