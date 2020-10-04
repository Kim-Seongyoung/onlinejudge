package expert;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_9942 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		// 메모이제이션을 위한 배열
		ArrayList<Integer>[] arr = new ArrayList[11];
		int[] mux = new int[11];
		int[] sum = new int[11];
		mux[0]=1;
		sum[0]=0;
		// 1부터 N까지의 합과 곱
		for (int i = 1; i < mux.length; i++) {
			mux[i] =mux[i-1]*i;
			sum[i]=sum[i-1]+i;
		}
		int iter = Integer.parseInt(br.readLine());
		for (int i = 1; i <= iter; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			long result = 0;
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			// 테스트케이스 중 N이 처음일 때
			if(arr[N] == null) {
				//N일 때 만들수 있는 경우의 수
				//배열이 1부터 N까지 1씩 증가하는 수열이기 때문에
				//N으로 만들 수 있는 최대값-최소값+1이 만들 수 있는 경우의 수
				int size = 0;
				if(N%2==0) {
					size = (sum[N]-sum[N/2])*2-sum[N]+1;
				}else {
					size = (sum[N]-sum[N/2+1])*2+N/2+1-sum[N]+1;
				}
				arr[N] = new ArrayList<Integer>();
				for (int j = 0; j < size; j++) {
					arr[N].add(0);
				}
				//순열 만들기 배열 두 개(A,B)일 떄 A를 {1,2,...N}으로 고정하고 B만 순열 만들기 
				combi(0,new boolean[N],-1*sum[N],arr[N]);
			}
			// K가 최소값보다 작으면 0부터, 크면 K-sum[N]부터
			int start  = K-sum[N] <0 ? 0: K-sum[N];
			for (int j =start; j < arr[N].size(); j++) {
				result += arr[N].get(j);
			}
			// A의 순열의 개수를 곱해주면 총 개수
			bw.write("#"+i+" "+result*mux[N]);
			bw.newLine();
		}
		bw.flush();
		bw.close();
	}
	//배열의 인덱스 참조를 위해서 sum은 N으로 만들 수 있는 최소값*-1로 계산
	static void combi(int cnt, boolean[] v, int sum, ArrayList<Integer> hm) {
		if (v.length == cnt) {
			hm.set(sum, hm.get(sum)+1);
		} else {
			for (int i = 0; i < v.length; i++) {
				if (!v[i]) {
					v[i] = true;
					//B[i]와 A[cnt] 중 큰 값을 골라 더해준다.
					combi(cnt + 1, v, sum +Math.max(i+1,cnt+1), hm);
					v[i] = false;
				}
			}
		}
	}
}
