package baekjoon;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_18859 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		boolean check = true;
		int pivot = arr[1] - arr[0];
		if (pivot == 0) {
			check = false;
		} else {
			int cnt = arr[1] + pivot;
			int lastNum = 0;
			LinkedList<Integer> q = new LinkedList<>();
			for (int i = 2; i < arr.length; i++) {
				if (cnt == arr[i]) {
					cnt += pivot;
					lastNum = arr[i];
				} else if (cnt < arr[i]) {
					for (int j = i; j < arr.length; j++) {
						q.add(arr[j]);
					}
					break;
				} else {
					q.add(arr[i]);
				}
			}
			if (!q.isEmpty()) {
				LinkedList<Integer> temp = new LinkedList<>();
				temp.addAll(q);
				temp.add(lastNum);
				if(!search(q, arr[0])) {
					Collections.sort(temp);
					check= search(temp,arr[0]);
				}
				
			}

		}
		if (check) {
			System.out.println("Yes");
		} else {
			System.out.println("No");
		}
	}
	static boolean search(LinkedList<Integer> q, int arr0) {
		int p = q.poll();
		int pivot = p - arr0;
		boolean check = true;
		if (pivot == 0) {
			check = false;
		} else {
			int cnt = p + pivot;
			while (!q.isEmpty()) {
				if (cnt == q.poll()) {
					cnt += pivot;
				} else {
					check = false;
					break;
				}
			}
		}
		return check;
	}
}
