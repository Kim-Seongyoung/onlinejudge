package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14719 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int sum = 0;
		st.nextToken();
		int W = Integer.parseInt(st.nextToken());
		int[] arr = new int[W];
		st = new StringTokenizer(br.readLine());

		arr[0] = Integer.parseInt(st.nextToken());
		int max = arr[0];
		int pivot = 0;
		boolean flag = false;
		for (int i = 1; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			if (max <= arr[i]) {
				for (int j = pivot; j < i; j++) {
					sum += max - arr[j];
				}
				max = arr[i];
				pivot = i;
				flag = true;
			} else {
				flag = false;
			}
		}
		if (!flag) {
			max = arr[arr.length - 1];
			int end = pivot;
			pivot = arr.length - 1;
			for (int i = arr.length - 2; i >= end; i--) {
				if (max <= arr[i]) {
					for (int j = pivot; j > i; j--) {
						sum += (max - arr[j]);
					}
					max = arr[i];
					pivot = i;
				}
			}
		}
		System.out.println(sum);
	}

}
