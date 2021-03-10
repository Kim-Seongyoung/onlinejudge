package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14717 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int num1 = Integer.parseInt(st.nextToken());
		int num2 = Integer.parseInt(st.nextToken());
		if (num1 == num2) {
			System.out.println(String.format("%.3f", 1 - (10 - num1) / 153.0));
		} else {
			int pivot = 0;
			int[] arr = new int[18];
			for (int i = 1; i <= 10; i++) {
				if (i != num1) {
					arr[pivot] = i;
					pivot++;
				}
			}
			for (int i = 1; i <= 10; i++) {
				if (i != num2) {
					arr[pivot] = i;
					pivot++;
				}
			}
			pivot = (num1+num2)%10;
			int count=0;
			for (int i = 0; i < arr.length; i++) {
				for (int j = i+1; j < arr.length; j++) {
					if(arr[i]==arr[j] || (arr[i]+arr[j])%10>=pivot) {
						count++;
					}
				}
			}
			System.out.println(String.format("%.3f", 1 - count / 153.0));
			
		}
	}

}
