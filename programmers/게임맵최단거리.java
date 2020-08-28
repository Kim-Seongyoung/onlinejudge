package programmers;

import java.util.LinkedList;

public class 게임맵최단거리 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public int solution(int[][] maps) {
		int[] dy = { 0, 0, -1, 1 };
		int[] dx = { -1, 1, 0, 0 };
		int N = maps.length;
		int M = maps[0].length;
		LinkedList<Point> q = new LinkedList<>();
		q.add(new Point(0, 0));
		maps[0][0] = 1;
		while (!q.isEmpty()) {
			Point p = q.poll();
			for (int i = 0; i < 4; i++) {
				int tempy = p.y + dy[i];
				int tempx = p.x + dx[i];
				;
				if (tempy >= 0 && tempy < N && tempx >= 0 && tempx < M && maps[tempy][tempx] == 1) {
					maps[tempy][tempx] = maps[p.y][p.x] + 1;
					q.add(new Point(tempy, tempx));
				}
			}
		}
		if (maps[N - 1][M - 1] == 1) {
			return -1;
		}
		return maps[N - 1][M - 1];
	}
	class Point {
		int y;
		int x;
		Point(int y, int x){
			this.y=y;
			this.x=x;
		}
	}
}
