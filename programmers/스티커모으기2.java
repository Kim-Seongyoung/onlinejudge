package programmers;

public class 스티커모으기2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int solution(int sticker[]) {
		int answer = 0;
		// 각 스티커를 사용할 지, 사용했을 때 값은 0번, 사용하지 않았을 때 1번에 인덱스에 저장
		int[][] arr = new int[sticker.length][2];
		if (sticker.length > 2) {//sticker 길이가 1일때 조심 33번 테케
			// 무조건 첫번째를 사용할 때
			arr[1][0] = sticker[0];// 첫번째를 사용하면 두번째를 사용하지 못하기 때문에
			arr[1][1] = sticker[0];
			for (int i = 2; i < arr.length - 1; i++) {
				// 현재 스티커를 사용하면 이전 스티커가 사용하지 않아야기 때문에
				// 이전 스티커를 사용하지 않는 비용인 arr[i-1][1]와 현재 스티커 비용을 더함
				arr[i][0] = arr[i - 1][1] + sticker[i];
				// 사용하지 않을 경우 이전 스티커에서 최대 값을 저장
				arr[i][1] = Math.max(arr[i - 1][0], arr[i - 1][1]);
			}
			//첫번째를 무조건 사용하였기 때문에 마지막 스티커도 사용 불가능
			//그래서 마지막 스티커 전 스티커에서 최대값을 뽑음
			answer = Math.max(arr[arr.length - 2][0], arr[arr.length - 2][1]);
			//첫 번째를 사용 안 할 때
			arr[1][0] = sticker[1];
			arr[1][1] = 0;
			for (int i = 2; i < arr.length; i++) {
				arr[i][0] = arr[i - 1][1] + sticker[i];
				arr[i][1] = Math.max(arr[i - 1][0], arr[i - 1][1]);
			}
			// 첫번째를 사용 안하기 때문에 마지막 스티커에서 최대값 선택
			answer = Math.max(answer, Math.max(arr[arr.length - 1][0], arr[arr.length - 1][1]));
		}else {
			answer = sticker[0];
		}
		return answer;
	}
}
