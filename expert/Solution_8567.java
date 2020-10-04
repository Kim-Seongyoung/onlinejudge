package expert;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Solution_8567 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int iter = Integer.parseInt(br.readLine());
		//미리 최대값까지 약수의 개수가 많은 값을 구한다.
		int[] arr = new int[100001];
		//약수의 개수
		int max = 0;
		for (int i = 1; i < 100001; i++) {
			int count = 0;
			//i의 약수의 개수 구하기
			for (int j = 1; j < Math.sqrt(i); j++) {
				if (i % j == 0) {
					count += 2;
				}
			}
			if (Math.sqrt(i) % 1 == 0) {
				count++;
			}
			if (max <= count) {
				//max 보다 크면 변경
				if (max < count) {
					max = count;
				}
				//크거나 같을 경우 순차적으로 진행하기 때문에  arr[i]=i
				arr[i] = i;
			} else {
				arr[i]= arr[i-1];
			}
		}
		for (int i = 1; i <= iter; i++) {
			bw.write("#"+i+" "+arr[Integer.parseInt(br.readLine())]);
			bw.newLine();
		}
		bw.flush();
		bw.close();
	}

}
