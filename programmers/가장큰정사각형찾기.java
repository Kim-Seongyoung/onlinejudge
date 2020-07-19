package programmers;

public class 가장큰정사각형찾기 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int solution(int[][] board) {
		int N = board.length;
		int M = board[0].length;
		int temp_sum=0;
		for (int i = 0; i < N; i++) {
			temp_sum+=board[i][0];
		}
		for (int i = 0; i < M; i++) {
			temp_sum+=board[0][i];
		}
		int max = temp_sum>0 ? 1 : 0;
		for (int i = 1; i < N; i++) {
			for (int j = 1; j < M; j++) {
				if (board[i][j] == 1) {
					board[i][j] = Math.min(Math.min(board[i - 1][j], board[i][j - 1]), board[i - 1][j - 1])+1;
					max = Math.max(max, board[i][j]);
				}
			}
		}

		return max*max;
	}
}
