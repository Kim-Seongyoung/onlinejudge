package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_9328 {
	static LinkedList<Point> q, door;
	static boolean[][] v;
	static char[][] map;
	static boolean[] check;
	static boolean newKey;
	static int N, M, count;
	static int[] dy = { 1, 0, -1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int iter = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int i = 0; i < iter; i++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new char[N][M];
			v = new boolean[N][M];
			for (int j = 0; j < N; j++) {
				String s = br.readLine();
				for (int k = 0; k < M; k++) {
					map[j][k] = s.charAt(k);
					if (s.charAt(k) == '*') {
						v[j][k] = true;
					}
				}
			}
			q = new LinkedList<>();
			door = new LinkedList<>();
			check = new boolean[26];
			newKey = false;
			count = 0;
			N--;
			M--;
			for (int j = 0; j <= M; j++) {
				findPivot(map[0][j], 0, j);
				findPivot(map[N][j], N, j);
			}
			for (int j = 1; j < N; j++) {
				findPivot(map[j][0], j, 0);
				findPivot(map[j][M], j, M);
			}

			String s = br.readLine();
			if (s.charAt(0) != '0') {
				newKey = true;
				for (int j = 0; j < s.length(); j++) {
					check[s.charAt(j) - 'a'] = true;
				}
			}

			while (!q.isEmpty()) {
				Point p = q.poll();
				search(p, q);
			}
			LinkedList<Point> temp = new LinkedList<>();
			while (newKey) {
				newKey = false;
				int size = door.size();
				for (int j = 0; j < size; j++) {
					Point d = door.poll();
					if (!v[d.y][d.x]) {
						if (check[d.idx]) {
							temp.add(d);
							v[d.y][d.x] = true;
							while (!temp.isEmpty()) {
								Point p = temp.poll();
								search(p, temp);
							}
						} else {
							door.add(d);
						}
					}
				}
			}
			bw.write(count + "\n");
		}
		bw.flush();
		bw.close();
	}

	static void findPivot(char pivot, int y, int x) {
		if (pivot != '*') {
			if ('A' <= pivot && pivot <= 'Z') {
				door.add(new Point(y, x, pivot - 'A'));
			} else {
				v[y][x] = true;
				if ('a' <= pivot && pivot <= 'z') {
					check[pivot - 'a'] = true;
					newKey = true;
				} else if (pivot == '$') {
					count++;
				}
				q.add(new Point(y, x, 0));
			}
		}
	}

	static void search(Point p, LinkedList<Point> q) {
		for (int j = 0; j < 4; j++) {
			int tempy = p.y + dy[j];
			int tempx = p.x + dx[j];
			if (0 <= tempy && tempy <= N && 0 <= tempx && tempx <= M && !v[tempy][tempx]) {
				if ('A' <= map[tempy][tempx] && map[tempy][tempx] <= 'Z' && !check[map[tempy][tempx] - 'A']) {
					door.add(new Point(tempy, tempx, map[tempy][tempx] - 'A'));
				} else {
					v[tempy][tempx] = true;
					if ('a' <= map[tempy][tempx] && map[tempy][tempx] <= 'z') {
						if (!check[map[tempy][tempx] - 'a']) {
							newKey = true;
							check[map[tempy][tempx] - 'a'] = true;
						}
					} else if (map[tempy][tempx] == '$') {
						count++;
					}
					q.add(new Point(tempy, tempx, 0));
				}
			}
		}
	}

	static class Point {
		int y;
		int x;
		int idx;

		Point(int y, int x, int idx) {
			this.y = y;
			this.x = x;
			this.idx = idx;
		}
	}

}
