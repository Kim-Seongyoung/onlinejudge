package programmers;

public class 숫자의표현 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int solution(int n) {
		int answer = 1;
		int pivot = n / 2 + 1;

		for (int i = pivot; i > 0; i--) {
			int sum = 0;
			for (int j = i; j > 0; j--) {
				sum += j;
				if (sum >= n) {
					if (sum == n) {
						answer++;
					}
					break;
				}
			}
		}
		return answer;
	}
}
