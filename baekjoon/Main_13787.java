package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_13787 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int H, W,   tempy, tempx, tempd;
		long L,idx;
		StringTokenizer st;
		int[] dy = { -1, 0, 1, 0 };
		int[] dx = { 0, 1, 0, -1 };
		char[] NESW = { 'N', 'E', 'S', 'W' };
		char[][] map;
		int[][][] v;
		boolean check;
		Point robot;
		while (true) {
			st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			L = Long.parseLong(st.nextToken());
			if (H == 0 && W == 0 && L == 0) {
				break;
			}
			map = new char[H][W];
			v = new int[H][W][4];
			check = true;
			ArrayList<Point> q = new ArrayList<>();
			for (int i = 0; i < H; i++) {
				map[i] = br.readLine().toCharArray();
				if (check) {
					for (int j = 0; j < W; j++) {
						if (map[i][j] != '.' && map[i][j] != '#') {
							if (map[i][j] == 'N') {
								q.add(new Point(i, j, 0));
							} else if (map[i][j] == 'E') {
								q.add(new Point(i, j, 1));
							} else if (map[i][j] == 'S') {
								q.add(new Point(i, j, 2));
							} else {
								q.add(new Point(i, j, 3));
							}
							map[i][j] = '.';
							check = false;
							break;
						}
					}
				}
			}
			idx = 0;
			check = true;
			while (idx < L) {
				robot = q.get((int)idx);
				if (v[robot.y][robot.x][robot.d] != 0) {
					long temp = L - idx;
					temp %= (idx - v[robot.y][robot.x][robot.d]);
					robot = q.get((int)(v[robot.y][robot.x][robot.d] + temp));
					bw.append((robot.y + 1) + " " + (robot.x + 1) + " " + NESW[robot.d]);
					bw.newLine();
					check = false;
					break;
				} else {
					v[robot.y][robot.x][robot.d] = (int)idx;
				}
				tempd = robot.d;
				while (true) {
					tempy = robot.y + dy[tempd];
					tempx = robot.x + dx[tempd];
					if (tempy >= 0 && tempy < H && tempx >= 0 && tempx < W && map[tempy][tempx] == '.') {
						break;
					}
					tempd++;
					tempd %= 4;
				}
				q.add(new Point(tempy, tempx, tempd));
				idx++;
			}
			if (check) {
				robot = q.get((int)idx);
				bw.append((robot.y + 1) + " " + (robot.x + 1) + " " + NESW[robot.d]);
				bw.newLine();
			}
		}
		bw.flush();
		bw.close();
	}

	static class Point {
		int y, x, d;

		Point(int y, int x, int d) {
			this.y = y;
			this.x = x;
			this.d = d;
		}
	}
}
