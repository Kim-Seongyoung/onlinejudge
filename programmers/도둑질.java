package programmers;

public class 도둑질 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public int solution(int[] money) {
        int answer = 0;
        int[][] arr = new int[money.length][2];
        arr[0][0] = money[0];
        arr[0][1] = money[0];
        arr[1][0] =	money[0];
        arr[1][1] = money[0];
        for (int i = 2; i < money.length-1; i++) {
			arr[i][0] = arr[i-1][1]+money[i];
			arr[i][1] = Math.max(arr[i-1][0],arr[i-1][1]);
		}
        answer = Math.max(arr[money.length-2][0], arr[money.length-2][1]);
        arr[0][0]=0;
        arr[0][1] =0;
        arr[1][0] = money[1];
        arr[1][1] = 0;
        for (int i = 2; i < money.length; i++) {
			arr[i][0] = arr[i-1][1]+money[i];
			arr[i][1] = Math.max(arr[i-1][0],arr[i-1][1]);
		}
        answer = Math.max(answer, Math.max(arr[money.length-1][0], arr[money.length-1][1]));
        return answer;
    }
}
