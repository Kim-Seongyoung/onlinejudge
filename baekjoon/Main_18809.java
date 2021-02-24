package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_18809 {

	static ArrayList<Point> arr = new ArrayList<>();
	static int N, M, max;
	static int[] dy = { 0, 0, -1, 1 };
	static int[] dx = { -1, 1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		max = 0;
		int G = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		int t = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				t = st.nextToken().charAt(0) - '0';
				if (t == 0) {
					map[i][j] = Integer.MAX_VALUE;
				} else {
					map[i][j] = 0;
				}
				if (t == 2) {
					arr.add(new Point(i, j, 0));
				}
			}
		}
		combi(map, 0, R, G);
		System.out.println(max);
	}

	static void combi(int[][] map, int cnt, int R, int G) {
		if (cnt == arr.size()) {
			int[][] v = new int[N][M];
			LinkedList<Point> q = new LinkedList<>();
			int tempy, tempx;
			int count = 0;
			for (int i = 0; i < arr.size(); i++) {
				if (arr.get(i).flag != 0) {
					v[arr.get(i).y][arr.get(i).x] = Integer.MAX_VALUE;
					for (int j = 0; j < 4; j++) {
						tempy = arr.get(i).y + dy[j];
						tempx = arr.get(i).x + dx[j];
						if (tempy >= 0 && tempy < N && tempx >= 0 && tempx < M && map[tempy][tempx] == 0
								&& v[tempy][tempx] != Integer.MAX_VALUE) {
							if (v[tempy][tempx] == 0) {
								v[tempy][tempx] = arr.get(i).flag;
								if (arr.get(i).flag > 0) {
									q.add(new Point(tempy, tempx, arr.get(i).flag+1));
								} else {
									q.add(new Point(tempy, tempx, arr.get(i).flag-1));
								}

							} else if (v[tempy][tempx] + arr.get(i).flag == 0) {
								count++;
								v[tempy][tempx] = Integer.MAX_VALUE;
							}
						}
					}
				}
			}
			while (!q.isEmpty()) {
				Point p = q.poll();
				if (v[p.y][p.x] != Integer.MAX_VALUE) {
					for (int i = 0; i < 4; i++) {
						tempy = p.y + dy[i];
						tempx = p.x + dx[i];
						if (tempy >= 0 && tempy < N && tempx >= 0 && tempx < M && map[tempy][tempx] == 0
								&& v[tempy][tempx] != Integer.MAX_VALUE) {
							if (v[tempy][tempx] == 0) {
								v[tempy][tempx] = p.flag;
								if (p.flag > 0) {
									q.add(new Point(tempy, tempx, p.flag + 1));
								} else {
									q.add(new Point(tempy, tempx, p.flag - 1));
								}
							} else {
								if (v[tempy][tempx] + p.flag == 0) {
									count++;
									v[tempy][tempx] = Integer.MAX_VALUE;
								}
							}

						}
					}
				}
			}
			max = Math.max(max, count);
		} else {
			if (arr.size() > cnt + R + G) {
				arr.get(cnt).flag = 0;
				combi(map, cnt + 1, R, G);
			}
			if (R > 0) {
				arr.get(cnt).flag = 1;
				combi(map, cnt + 1, R - 1, G);
			}
			if (G > 0) {
				arr.get(cnt).flag = -1;
				combi(map, cnt + 1, R, G - 1);
			}
		}
	}

	static class Point {
		int y;
		int x;
		int flag;

		Point(int y, int x, int flag) {
			this.y = y;
			this.x = x;
			this.flag = flag;
		}
	}
}
