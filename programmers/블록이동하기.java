package programmers;

import java.util.LinkedList;

public class 블록이동하기 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public int solution(int[][] board) {
        int answer = 0;
		boolean[][][] v = new boolean[board.length][board.length][2];
		LinkedList<Drone> q = new LinkedList<>();
		q.add(new Drone(0, 0, 0, 1, 0, 0));
		int[] dy = { 0, 0, -1, 1 };
		int[] dx = { -1, 1, 0, 0 };
		v[0][0][0] = true;
		int N = board.length - 1;
		while (!q.isEmpty()) {
			Drone p = q.poll();
			if ((p.y1 == N && p.x1 == N) || (p.y2 == N && p.x2 == N)) {
				return p.cnt;
			}
			int tempCnt = p.cnt + 1;
			for (int i = 0; i < 4; i++) {
				int tempy1 = p.y1 + dy[i];
				int tempx1 = p.x1 + dx[i];
				int tempy2 = p.y2 + dy[i];
				int tempx2 = p.x2 + dx[i];
				if (tempy1 >= 0 && tempy1 <= N && tempx1 >= 0 && tempx1 <= N && tempy2 >= 0 && tempy2 <= N
						&& tempx2 >= 0 && tempx2 <= N && board[tempy1][tempx1] == 0 && board[tempy2][tempx2] == 0
						&& !v[tempy1][tempx1][p.d]) {
					v[tempy1][tempx1][p.d] = true;
					q.add(new Drone(tempy1, tempx1, tempy2, tempx2, tempCnt, p.d));
				}
			}
			if (p.d == 0) {
				int tempy1 = p.y1 + 1;
				if (tempy1 <= N && board[tempy1][p.x1] == 0 && board[tempy1][p.x2] == 0) {
					if (!v[p.y2][p.x2][1]) {
						v[p.y2][p.x2][1] = true;
						q.add(new Drone(p.y2, p.x2, tempy1, p.x2, tempCnt, 1));
					}
					if (!v[p.y1][p.x1][1]) {
						v[p.y1][p.x1][1] = true;
						q.add(new Drone(p.y1, p.x1, tempy1, p.x1, tempCnt, 1));
					}
				}
				tempy1 = p.y1 - 1;
				if (tempy1 >= 0 && board[tempy1][p.x1] == 0 && board[tempy1][p.x2] == 0) {
					if (!v[tempy1][p.x1][1]) {
						v[tempy1][p.x1][1] = true;
						q.add(new Drone(tempy1, p.x1, p.y1, p.x1, tempCnt, 1));
					}
					if (!v[tempy1][p.x2][1]) {
						v[tempy1][p.x2][1] = true;
						q.add(new Drone(tempy1, p.x2, p.y2, p.x2, tempCnt, 1));
					}
				}
			} else {
				int tempx1 = p.x1 + 1;
				if (tempx1 <= N && board[p.y1][tempx1] == 0 && board[p.y2][tempx1] == 0) {
					if (!v[p.y1][p.x1][0]) {
						v[p.y1][p.x1][0] = true;
						q.add(new Drone(p.y1, p.x1, p.y1, tempx1, tempCnt, 0));
					}
					if (!v[p.y2][p.x2][0]) {
						v[p.y2][p.x2][0] = true;
						q.add(new Drone(p.y2, p.x2, p.y2, tempx1, tempCnt, 0));
					}
				}
				tempx1 = p.x1 - 1;
				if (tempx1 >= 0 && board[p.y1][tempx1] == 0 && board[p.y2][tempx1] == 0) {
					if (!v[p.y1][tempx1][0]) {
						v[p.y1][tempx1][0] = true;
						q.add(new Drone(p.y1, tempx1, p.y1, p.x1, tempCnt, 0));
					}
					if (!v[p.y2][tempx1][0]) {
						v[p.y2][tempx1][0] = true;
						q.add(new Drone(p.y2, tempx1, p.y2, p.x2, tempCnt, 0));
					}
				}
			}
		}
		return answer;
    }
    class Drone{
		int y1;
		int x1;
		int y2;
		int x2;
		int cnt;
		int d;
		Drone(int y1,int x1, int y2,int x2, int cnt, int d){
			this.y1=y1;
			this.x1=x1;
			this.y2=y2;
			this.x2=x2;
			this.cnt=cnt;
			this.d=d;
		}
	}
}
