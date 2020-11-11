package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_2931 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		char[][] map = new char[R][C];
		boolean[][] v = new boolean[R][C];
		Point start = new Point(0, 0);
		Point end = new Point(0, 0);
		// map 그리기
		for (int i = 0; i < R; i++) {
			String s = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = s.charAt(j);
				if (map[i][j] == 'M') {
					start.y = i;
					start.x = j;
				} else if (map[i][j] == 'Z') {
					end.y = i;
					end.x = j;
				}
			}
		}
		int[] dy = { -1, 1, 0, 0 };
		int[] dx = { 0, 0, -1, 1 };
		boolean flag = false;
		// M에서 출발이 가능한지 확인, ex) M과 연결된 부분이 없을때
		for (int i = 0; i < 4; i++) {
			int tempy = start.y + dy[i];
			int tempx = start.x + dx[i];
			if (tempy >= 0 && tempy < R && tempx >= 0 && tempx < C && map[tempy][tempx] != '.' && map[tempy][tempx] !='Z') {
				v[start.y][start.x] = true;
				flag = true;
				start.y = tempy;
				start.x = tempx;
				start.c = map[start.y][start.x];
				break;
			}
		}
		// M에서 출발이 가능하지 않으면 Z에서 가능한지 확인
		if (!flag) {
			for (int i = 0; i < 4; i++) {
				int tempy = end.y + dy[i];
				int tempx = end.x + dx[i];
				if (tempy >= 0 && tempy < R && tempx >= 0 && tempx < C && map[tempy][tempx] != '.' && map[tempy][tempx]!='M') {
					flag = true;
					start.y = tempy;
					start.x = tempx;
					start.c = map[start.y][start.x];
					v[end.y][end.x] = true;
					break;
				}
			}
		}
		Point result = new Point(0, 0, '.');
		if (flag) {
			// 갈 수 있는 경로를 탐색
			LinkedList<Point> q = new LinkedList<>();
			q.add(start);
			v[start.y][start.x] = true;
			while (!q.isEmpty()) {
				Point p = q.poll();
				if (p.c == '|') {
					if (!v[p.y + 1][p.x]) {
						q.add(new Point(p.y + 1, p.x, map[p.y + 1][p.x]));
						v[p.y + 1][p.x] = true;
					} else {
						q.add(new Point(p.y - 1, p.x, map[p.y - 1][p.x]));
						v[p.y - 1][p.x] = true;
					}
				} else if (p.c == '-') {
					if (!v[p.y][p.x + 1]) {
						q.add(new Point(p.y, p.x + 1, map[p.y][p.x + 1]));
						v[p.y][p.x + 1] = true;
					} else {
						q.add(new Point(p.y, p.x - 1, map[p.y][p.x - 1]));
						v[p.y][p.x - 1] = true;
					}
				} else if (p.c == '+') {
					for (int i = 0; i < 4; i++) {
						int tempy = p.y + dy[i];
						int tempx = p.x + dx[i];
						if (tempy >= 0 && tempy < R && tempx >= 0 && tempx < C && !v[tempy][tempx]) {
							v[tempy][tempx] = true;
							q.add(new Point(tempy, tempx, map[tempy][tempx]));
						}
					}
				} else if (p.c == '1') {
					if (!v[p.y + 1][p.x]) {
						q.add(new Point(p.y + 1, p.x, map[p.y + 1][p.x]));
						v[p.y + 1][p.x] = true;
					} else {
						q.add(new Point(p.y, p.x + 1, map[p.y][p.x + 1]));
						v[p.y][p.x + 1] = true;
					}
				} else if (p.c == '2') {
					if (!v[p.y - 1][p.x]) {
						q.add(new Point(p.y - 1, p.x, map[p.y - 1][p.x]));
						v[p.y - 1][p.x] = true;
					} else {
						q.add(new Point(p.y, p.x + 1, map[p.y][p.x + 1]));
						v[p.y][p.x + 1] = true;
					}
				} else if (p.c == '3') {
					if (!v[p.y - 1][p.x]) {
						q.add(new Point(p.y - 1, p.x, map[p.y - 1][p.x]));
						v[p.y - 1][p.x] = true;
					} else {
						q.add(new Point(p.y, p.x - 1, map[p.y][p.x - 1]));
						v[p.y][p.x - 1] = true;
					}
				} else if (p.c == '4') {
					if (!v[p.y + 1][p.x]) {
						q.add(new Point(p.y + 1, p.x, map[p.y + 1][p.x]));
						v[p.y + 1][p.x] = true;
					} else {
						q.add(new Point(p.y, p.x - 1, map[p.y][p.x - 1]));
						v[p.y][p.x - 1] = true;
					}
				} else if (p.c == '.') {
					// 갈 수 있지만 배관이 없는 경우
					// 배관을 넣어야하는 위치
					result = p;
					break;
				}
			}

		} else {
			// M.Z의 경우 같이 M과 Z사이가 비였을 때
			result.y = (start.y + end.y) / 2;
			result.x = (start.x + end.x) / 2;
		}

		boolean[] loc = new boolean[4];
		int count = 0;
		// 배관을 넣어야 하는 위치 4방향 중 모두 필요한 곳 찾기
		for (int i = 0; i < 4; i++) {
			int tempy = result.y + dy[i];
			int tempx = result.x + dx[i];
			if (tempy >= 0 && tempy < R && tempx >= 0 && tempx < C && map[tempy][tempx] != '.') {
				if (i == 0 && (map[tempy][tempx] == '|' || map[tempy][tempx] == '1' || map[tempy][tempx] == '4'
						|| map[tempy][tempx] == '+' || (map[tempy][tempx] == 'Z' && !v[tempy][tempx])
						|| (map[tempy][tempx] == 'M' && !v[tempy][tempx]))) {
					count++;
					loc[i] = true;
				} else if (i == 1 && (map[tempy][tempx] == '|' || map[tempy][tempx] == '2' || map[tempy][tempx] == '3'
						|| map[tempy][tempx] == '+' || (map[tempy][tempx] == 'Z' && !v[tempy][tempx])
						|| (map[tempy][tempx] == 'M' && !v[tempy][tempx]))) {
					count++;
					loc[i] = true;
				} else if (i == 2 && (map[tempy][tempx] == '-' || map[tempy][tempx] == '1' || map[tempy][tempx] == '2'
						|| map[tempy][tempx] == '+' || (map[tempy][tempx] == 'Z' && !v[tempy][tempx])
						|| (map[tempy][tempx] == 'M' && !v[tempy][tempx]))) {
					count++;
					loc[i] = true;
				} else if (i == 3 && (map[tempy][tempx] == '-' || map[tempy][tempx] == '3' || map[tempy][tempx] == '4'
						|| map[tempy][tempx] == '+' || (map[tempy][tempx] == 'Z' && !v[tempy][tempx])
						|| (map[tempy][tempx] == 'M' && !v[tempy][tempx]))) {
					count++;
					loc[i] = true;
				}
			}
		}
		char c = '0';
		// 각 조건에 맞는 배관
		if (count == 4) {
			c = '+';
		} else if (loc[0] && loc[1]) {
			c = '|';
		} else if (loc[0] && loc[2]) {
			c = '3';
		} else if (loc[0] && loc[3]) {
			c = '2';
		} else if (loc[1] && loc[2]) {
			c = '4';
		} else if (loc[1] && loc[3]) {
			c = '1';
		} else if (loc[2] && loc[3]) {
			c = '-';
		}

		System.out.println((result.y + 1) + " " + (result.x + 1) + " " + c);
	}

	static class Point {
		char c;
		int y;
		int x;

		Point(int y, int x) {
			this.y = y;
			this.x = x;
			this.c = '0';
		}

		Point(int y, int x, char c) {
			this.y = y;
			this.x = x;
			this.c = c;
		}
	}
}
