package programmers;

public class 종이접기 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		solution(3);
	}

	static public int[] solution(int n) {
		int size = (int) Math.pow(2, n);
		int[] answer = new int[size - 1];
		for (int i = 0; i < answer.length; i++) {
			answer[i] = -1;
		}
		int pivot = 2;
		for (int i = 0; i < size / 2; i++) {
			if (answer[i] == -1) {
				int num =0;
				for (int j = i; j < answer.length; j=j+pivot) {
					answer[j] = num;
					num = Math.abs(num-1);
				}
				pivot *=2;
			}
		}

		return answer;
	}
}
