package programmers;

public class 최적의행렬곱셈 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] matrix_sizes = { { 5, 3 }, { 3, 10 }, { 10, 6 } };
		solution(matrix_sizes);
	}

	static public int solution(int[][] matrix_sizes) {
		int[][] map = new int[matrix_sizes.length][matrix_sizes.length];
		int[][][] sizeArr = new int[matrix_sizes.length][matrix_sizes.length][2];
		
		for (int i = 0; i < sizeArr.length; i++) {
			sizeArr[0][i][0] = matrix_sizes[i][0];
			sizeArr[0][i][1] = matrix_sizes[i][1];
		}
		for (int i = 1; i < sizeArr.length; i++) {
			for (int j = 0; j < sizeArr.length-i; j++) {
				sizeArr[i][j][0] = sizeArr[i-1][j][0];
				sizeArr[i][j][1] = sizeArr[i-1][j+1][1];
			}
		}
		for (int i = 1; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				map[i][j] = Integer.MAX_VALUE;
			}
		}
		for (int i = 1; i < map.length; i++) {
			for (int j = 0; j < map.length - i; j++) {
				for (int k = 0; k < i; k++) {
					int a = map[i-k-1][j]+map[k][i+j-k]+sizeArr[i-k-1][j][0]*sizeArr[k][i+j-k][0]*sizeArr[k][i+j-k][1];
					map[i][j] = Math.min(map[i][j],a);
				}
			}
		}
		return map[map.length-1][0];
	}
}
