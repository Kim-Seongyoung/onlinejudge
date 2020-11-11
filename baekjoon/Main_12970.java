package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_12970 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int A_count = N/2;
		int temp = A_count * (A_count+N%2);
		if(temp>=K) {
			StringBuilder sb = new StringBuilder();
			int B_count = N-A_count;
			while(K>0) {
				temp = K/B_count;
				for (int i = 0; i < temp; i++) {
					sb.append('A');
				}
				sb.append('B');
				K-=temp*B_count;
				B_count--;
				A_count -= temp;
			}
			while(B_count>0) {
				sb.append('B');
				B_count--;
			}
			while(A_count>0) {
				sb.append('A');
				A_count--;
			}
			System.out.println(sb.toString());
		}else {
			System.out.println(-1);
		}
	}

}
