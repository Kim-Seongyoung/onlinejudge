package programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class 자물쇠와열쇠 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public boolean solution(int[][] key, int[][] lock) {
        int m = key.length;
		int n = lock.length;
		boolean[][] map = new boolean[m+2* n - 2][m+2* n - 2];

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < m; j++) {
				if (key[i][j] == 1) {
					map[i + n - 1][j + n - 1] = true;
				}
			}
		}
		ArrayList<Point>[] lockPoint = new ArrayList[4];
		for (int i = 0; i < lockPoint.length; i++) {
			lockPoint[i] = new ArrayList<>();
		}
		for (int i = 0; i < lock.length; i++) {
			for (int j = 0; j < lock.length; j++) {
				if (lock[i][j] == 0) {
					int tempi = i;
					int tempj = j;
					int temp = 0;
					for (int k = 0; k < 4; k++) {
						lockPoint[k].add(new Point(tempi, tempj));
						temp = tempi;
						tempi = tempj;
						tempj = lock.length - temp - 1;
					}
				}
			}
		}
		if (lockPoint[0].size() == 0) {
			return true;
		}
		for (int i = 0; i < 4; i++) {
			Collections.sort(lockPoint[i], new Comparator<Point>() {
				@Override
				public int compare(Point o1, Point o2) {
					if (o1.y < o2.y) {
						return -2;
					} else if (o1.y == o2.y) {
						if (o1.x < o2.x) {
							return -1;
						} else {
							return 1;
						}
					} else {
						return 2;
					}
				}
			});
		}
		ArrayList<Point> keyPoint = new ArrayList<>();
		for (int i = 0; i <= map.length - n; i++) {
			for (int j = 0; j <= map.length - n; j++) {
				keyPoint.clear();
				boolean flag = true;
				for (int k = i; k < i + n && flag; k++) {
					for (int l = j; l < j + n; l++) {
						if (map[k][l]) {
							keyPoint.add(new Point(k-i, l-j));
							if (keyPoint.size() > lockPoint[0].size()) {
								flag = false;
								break;
							}
						}
					}
				}
				if (flag && lockPoint[0].size() == keyPoint.size()) {
					for (int k = 0; k < 4; k++) {
						boolean check = true;
						for (int l = 0; l < keyPoint.size(); l++) {
							if (lockPoint[k].get(l).y != keyPoint.get(l).y
									|| lockPoint[k].get(l).x != keyPoint.get(l).x) {
								check = false;
								break;
							}
						}
						if (check) {
							return true;
						}
					}
				}

			}
		}
		return false;
    }
    class Point {
		int y;
		int x;

		Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
