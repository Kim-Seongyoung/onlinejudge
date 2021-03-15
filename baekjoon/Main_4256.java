package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_4256 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		int N;
		int[] arr1, arr2;
		StringTokenizer st;
		for (int i = 0; i < T; i++) {
			N = Integer.parseInt(br.readLine());
			arr1 = new int[N];
			arr2 = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr1[j] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr2[j] = Integer.parseInt(st.nextToken());
			}
			search(arr1, arr2, 0, N-1);
			for (int j = 0; j < N; j++) {
				bw.append(arr1[j]+" ");
			}
			bw.newLine();
		}
		bw.flush();
		bw.close();
	}

	static void search(int[] arr1, int[] arr2, int start, int end) {
		if (start <= end) {
			
			int pivot = arr1[start];
			for (int i = start; i < end; i++) {
				arr1[i] = arr1[i+1];
			}
			arr1[end]=pivot;
			for (int i = start; i <= end; i++) {
				if (pivot == arr2[i]) {
					for (int j = i; j < end; j++) {
						arr2[j] = arr2[j+1];
					}
					arr2[end] = pivot;
					pivot=i;
					break;
				}
			}
			search(arr1, arr2,start,pivot-1);
			search(arr1, arr2,pivot,end-1);
		}
	}
}
