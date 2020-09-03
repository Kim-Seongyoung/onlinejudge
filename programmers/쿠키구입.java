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
		for (int i = 1; i < len; i++) {
			sum[i] = sum[i - 1] + cookie[i];
		}
		int mid = sum[len - 1] / 2;
		for (int i = 0; i < sum.length; i++) {
			for (int j = i; j >= 0; j--) {
				int pivot = sum[i] - sum[j] + cookie[j];
				if(answer>= pivot) {
					continue;
				}
				if (mid < pivot) {
					break;
				}
				for (int k = i + 1; k < len; k++) {
					int temp = sum[k] - sum[i];
					if (pivot <= temp) {
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
