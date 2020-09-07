package programmers;

import java.util.LinkedList;

public class 튜브의소개팅 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int m = 5;
		int n = 5;
		int s = 12;
		int[][] time_map = { { 0, 1, 1, 1, 1 }, { 9, 9, 9, 1, 9 }, { 1, 1, 1, 1, 9 }, { 1, 1, 5, 9, 9 },
				{ 1, 1, 1, 1, 0 } };
		int[] answer = solution(m, n, s, time_map);
		System.out.println(answer[0] + " " + answer[1]);
	}

	static public int[] solution(int m, int n, int s, int[][] time_map) {
		int[] answer = new int[2];
		int[] dy = { -1, 1, 0, 0 };
		int[] dx = { 0, 0, -1, 1 };
		int[][] v = new int[m][n];
		long[][] vtime = new long[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				v[i][j] = Integer.MAX_VALUE;
				vtime[i][j] = Long.MAX_VALUE;
			}
		}
		LinkedList<Point> q = new LinkedList<>();
		q.add(new Point(0, 0, 0, time_map[0][0]));
		v[0][0] = 0;
		vtime[0][0] = time_map[0][0];
		answer[0] = Integer.MAX_VALUE;
		answer[1] = Integer.MAX_VALUE;
		while (!q.isEmpty()) {
			Point p = q.poll();
			if (p.cnt <= answer[0]) {
				if (p.y == m - 1 && p.x == n - 1) {
					if (answer[0] == p.cnt) {
						answer[1] = (int) Math.min(answer[1], p.time);
					} else if (answer[0] > p.cnt) {
						answer[0] = p.cnt;
						answer[1] = p.time;
					}
				}
				int tempCnt = p.cnt + 1;
				for (int i = 0; i < 4; i++) {
					int tempy = p.y + dy[i];
					int tempx = p.x + dx[i];
					if (tempy >= 0 && tempy < m && tempx >= 0 && tempx < n && time_map[tempy][tempx] != -1) {
						long temp = (long) p.time + (long) time_map[tempy][tempx];
						if (temp <= s && vtime[tempy][tempx] > temp) {
							vtime[tempy][tempx] = temp;
							q.add(new Point(tempy, tempx, tempCnt, (int) temp));
						}
					}
				}
			}
		}
		return answer;
	}

	static class Point {
		int y;
		int x;
		int cnt;
		int time;

		Point(int y, int x, int cnt, int time) {
			this.y = y;
			this.x = x;
			this.cnt = cnt;
			this.time = time;

		}
	}
}
