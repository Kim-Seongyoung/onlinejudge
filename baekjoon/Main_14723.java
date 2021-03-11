package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_14723 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int sum = 1;
		int pivot = 1;
		while (true) {
			if (sum >= N) {
				int sub = sum-N;
				if(sub==0) {
					System.out.println(pivot+" 1");
				}else {
					System.out.println(sub+" "+(pivot-sub));
				}
				break;
			}
			sum += pivot;
			pivot++;
		}
	}

}
