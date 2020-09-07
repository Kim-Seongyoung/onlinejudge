package programmers;

public class 쿠키구입 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public int solution(int[] cookie) {
        int answer = 0;
		int len = cookie.length;
		int[] sum = new int[len];
		sum[0] = cookie[0];
		// 각 위치까지의 누적 쿠키 개수
		for (int i = 1; i < len; i++) {
			sum[i] = sum[i - 1] + cookie[i];
		}
		// 한명이 가질 수 있는 최대 쿠키 수
		int mid = sum[len - 1] / 2;
		for (int i = 0; i < sum.length; i++) {
			for (int j = i; j >= 0; j--) {
				//첫째 아들이 받는 쿠키
				int pivot = sum[i] - sum[j] + cookie[j];
				// 최대 값보다 작을 때
				if(answer>= pivot) {
					continue;
				}
				// 한명이 가질 수 있는 최대 개수를 넘긴 경우
				if (mid < pivot) {
					break;
				}
				// 두번째 아들이 받는 쿠키
				for (int k = i + 1; k < len; k++) {
					int temp = sum[k] - sum[i];
					// 첫째보다 많거나 같을 경우
					if (pivot <= temp) {
						// 같으면 현재 최대값과 비교
						if (pivot == temp) {
							answer = Math.max(answer, pivot);
						}
						break;
					}
				}

			}
		}
		return answer;
    }
}
