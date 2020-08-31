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
		// 바위들의 위치 정렬(오름차순)
		Arrays.sort(rocks);
		int[] arr = new int[rocks.length + 2];
		// 시작 바위
		arr[0] = 0;
		for (int i = 0; i < rocks.length; i++) {
			arr[i + 1] = rocks[i];
		}
		// 끝 바위
		arr[rocks.length + 1] = distance;
		int start = 1;
		int end = distance;
		int mid;
		// 바위 사이의 거리의 최소값 중 가장 큰 값 찾기
		// 이진 탐색으로 검색
		while (start < end) {
			// mid 값으로 가능한 거리인지 판단
			mid = (start + end) / 2+1;
			int prev = 0;
			// 바위 제거 횟수
			int count = 0;
			boolean check = true;
			for (int i = 1; i < arr.length; i++) {
				// 현재 바위와 전 바위의 거리
				int temp = arr[i] - prev;
				// mid 값보다 작으면 mid 값이 최소가 아니므로 제거
				if (mid > temp) {
					count++;
					// 제거된 바위가 제거 할 수 있는 수보다 크면 종료
					if (count > n) {
						check = false;
						break;
					}
				} else {
					// 전 바위 변경
					prev = arr[i];
				}
			}
			if (check) {
				start = mid;
			} else {
				end = mid - 1;
			}
		}
		return start;
	}
}
