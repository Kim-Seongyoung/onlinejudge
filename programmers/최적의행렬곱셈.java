package programmers;

public class 최적의행렬곱셈 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] matrix_sizes = { { 5, 3 }, { 3, 10 }, { 10, 6 } };
		solution(matrix_sizes);
	}

	static public int solution(int[][] matrix_sizes) {
		int[][] map = new int[matrix_sizes.length][matrix_sizes.length];
		for (int i = 2; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				map[i][j] = Integer.MAX_VALUE;
			}
		}
		for (int i = 0; i < map.length-1; i++) {
			map[1][i] = matrix_sizes[i][0]*matrix_sizes[i][1]*matrix_sizes[i+1][1];
		}
		for (int i = 2; i < map.length; i++) {
			for (int j = 0; j < map.length - i; j++) {
				for (int k = 0; k < i; k++) {
					int a = map[i-k-1][j]+map[k][i+j-k]+matrix_sizes[j][0]*matrix_sizes[i+j-k][0]*matrix_sizes[i+j+k+i][1];
					map[i][j] = Math.min(map[i][j],a);
				}
			}
		}
		return map[map.length-1][0];
	}
}
