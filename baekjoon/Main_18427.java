package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_18427 {
	public static void main(String[] args) throws IOException {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		int[][] sum = new int[N][H+1];
		ArrayList<Integer>[] arr =new ArrayList[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i] = new ArrayList<Integer>();
			arr[i].add(0);
			while(st.hasMoreTokens()) {
				arr[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		for (int i = 0; i < arr[0].size(); i++) {
			sum[0][arr[0].get(i)]++;
		}
		int temp= 0;
		for (int i = 1; i < N; i++) {
			for (int j = 0; j <=H; j++) {
				if(sum[i-1][j]!=0) {
					for (int k = 0; k < arr[i].size(); k++) {
						temp = j+arr[i].get(k);
						if(temp<=H) {
							sum[i][temp] += sum[i-1][j];
							sum[i][temp]%=10007;
						}
					}
				}
			}
		}
		System.out.println(sum[N-1][H]);
	}
	

}
