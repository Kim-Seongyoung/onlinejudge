package programmers;

import java.util.Arrays;

public class 징검다리 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int distance = 25;
		int[] rocks = { 2, 14, 11, 21, 17 };
		int n = 2;
		System.out.println(solution(distance, rocks, n));
	}

	static public int solution(int distance, int[] rocks, int n) {
		Arrays.sort(rocks);
		int[] arr = new int[rocks.length + 2];
		arr[0] = 0;
		for (int i = 0; i < rocks.length; i++) {
			arr[i + 1] = rocks[i];
		}
		arr[rocks.length + 1] = distance;
		int start = 1;
		int end = distance;
		int mid;
		while (start < end) {
			mid = (start + end) / 2+1;
			int prev = 0;
			int count = 0;
			int min = Integer.MAX_VALUE;
			boolean check = true;
			for (int i = 1; i < arr.length; i++) {
				int temp = arr[i] - prev;
				if (mid > temp) {
					count++;
					if (count > n) {
						check = false;
						break;
					}
				} else {
					min = Math.min(temp, min);
					prev = arr[i];
				}
			}
			if (check) {
				start = min;
			} else {
				end = mid - 1;
			}
		}
		return start;
	}
}
