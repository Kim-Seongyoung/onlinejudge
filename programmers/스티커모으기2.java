package programmers;

public class 스티커모으기2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int solution(int sticker[]) {
		int answer = 0;
		int[][] arr = new int[sticker.length][2];
		arr[0][0] = sticker[0];
		arr[0][1] = sticker[0];
		if (sticker.length > 2) {//sticker 길이가 1일때 조심 33번 테케
			arr[1][0] = sticker[0];
			arr[1][1] = sticker[0];

			for (int i = 2; i < arr.length - 1; i++) {
				arr[i][0] = arr[i - 1][1] + sticker[i];
				arr[i][1] = Math.max(arr[i - 1][0], arr[i - 1][1]);
			}
			answer = Math.max(arr[arr.length - 2][0], arr[arr.length - 2][1]);

			arr[0][0] = 0;
			arr[0][1] = 0;
			arr[1][0] = sticker[1];
			arr[1][1] = 0;
			for (int i = 2; i < arr.length; i++) {
				arr[i][0] = arr[i - 1][1] + sticker[i];
				arr[i][1] = Math.max(arr[i - 1][0], arr[i - 1][1]);
			}
			answer = Math.max(answer, Math.max(arr[arr.length - 1][0], arr[arr.length - 1][1]));
		}else {
			answer = sticker[0];
		}
		return answer;
	}
}
