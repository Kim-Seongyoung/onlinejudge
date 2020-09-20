package programmers;

public class 사칙연산 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] arr = { "1", "-", "1", "-", "1", "+", "1", "-", "1", "+", "1" };
		solution(arr);
	}

	static public int solution(String arr[]) {
		int[][][] map = new int[arr.length / 2 + 1][arr.length / 2 + 1][2];
		boolean[] op = new boolean[arr.length / 2];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				map[i][j][0] = Integer.MIN_VALUE;
				map[i][j][1] = Integer.MAX_VALUE;
			}
		}
		for (int i = 0; i < op.length; i++) {
			map[0][i][0] = Integer.parseInt(arr[i * 2]);
			map[0][i][1] = Integer.parseInt(arr[i * 2]);
			if (arr[i * 2 + 1].charAt(0) == '+') {
				op[i] = true;
			}
		}

		map[0][op.length][0] = Integer.parseInt(arr[arr.length - 1]);
		map[0][op.length][1] = Integer.parseInt(arr[arr.length - 1]);
		for (int i = 1; i < map.length; i++) {
			for (int j = 0; j < map.length - i; j++) {
				for (int k = 0; k < i; k++) {
					if (op[i + j - k - 1]) {
						map[i][j][0] = Math.max(map[i][j][0], map[i - 1 - k][j][0] + map[k][i + j - k][0]);
						map[i][j][1] = Math.min(map[i][j][1], map[i - 1 - k][j][1] + map[k][i + j - k][1]);
					} else {
						map[i][j][0] = Math.max(map[i][j][0], map[i - 1 - k][j][0] - map[k][i + j - k][1]);
						map[i][j][1] = Math.min(map[i][j][1], map[i - 1 - k][j][1] - map[k][i + j - k][0]);
					}
				}
			}
		}
		return map[arr.length / 2][0][0];
	}
}
