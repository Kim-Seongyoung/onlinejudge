package programmers;

public class 종이접기 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		solution(3);
	}

	static public int[] solution(int n) {
		// 한번 접을 때마다 두배씩 증가
		int size = (int) Math.pow(2, n);
		int[] answer = new int[size - 1];
		// 0과 1을 넣어야 하므로 초기값은 -1로 세팅
		for (int i = 0; i < answer.length; i++) {
			answer[i] = -1;
		}
		// 처음에는 두칸씩 증가하면서 0과 1을 넣어준다
		int pivot = 2;
		for (int i = 0; i < size / 2; i++) {
			if (answer[i] == -1) {
				int num =0;
				for (int j = i; j < answer.length; j=j+pivot) {
					answer[j] = num;
					num = Math.abs(num-1);
				}
				// 간격을 점차 늘린다.
				pivot *=2;
			}
		}
		return answer;
	}
}
