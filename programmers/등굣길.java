package programmers;

public class 등굣길 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int m = 7;
		int n = 4;
		int[][] puddles = {{2,1},{2,2},{2,3},{4,2},{4,3},{4,4},{6,2},{6,3}};
		System.out.println(solution(m, n, puddles));
	}

	static public int solution(int m, int n, int[][] puddles) {
		boolean[][] v = new boolean[n][m];
		int[][] map = new int[n][m];
		for (int i = 0; i < puddles.length; i++) {
			v[puddles[i][1] - 1][puddles[i][0] - 1] = true;
		}
		for (int i = 0; i < n; i++) {
			if (!v[i][0]) {
				map[i][0] = 1;
			}else {
				break;
			}
		}
		for (int i = 0; i < m; i++) {
			if (!v[0][i]) {
				map[0][i] = 1;
			}else {
				break;
			}
		}
		for (int i = 1; i < n; i++) {
			for (int j = 1; j < m; j++) {
				if (!v[i][j]) {
					map[i][j] = (map[i - 1][j] + map[i][j - 1]) % 1000000007;
				}
			}
		}
		
		return map[n - 1][m - 1];
	}
}
