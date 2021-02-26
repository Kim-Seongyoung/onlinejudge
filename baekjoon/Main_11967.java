package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_11967 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		Point[][] map = new Point[N + 1][N + 1];
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= N; j++) {
				map[i][j] = new Point(i, j);
			}
		}
		LinkedList<Point> q = new LinkedList<>();
		q.add(map[1][1]);
		map[1][1].light = true;
		map[1][1].v = true;
		int count = 1;
		int x, y, a, b, tempy, tempx;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			map[x][y].arr.add(map[a][b]);
		}
		int[] dy = { 0, 0, -1, 1 };
		int[] dx = { -1, 1, 0, 0 };
		LinkedList<Point> q2 = new LinkedList<>();
		int size;
		Point p;
		boolean check;
		while (!q.isEmpty()) {
			while (!q.isEmpty()) {
				p = q.poll();
				while (!p.arr.isEmpty()) {
					Point temp = p.arr.poll();
					if (!temp.light) {
						temp.light = true;
						count++;
					}
				}
				for (int i = 0; i < 4; i++) {
					tempx = p.x + dx[i];
					tempy = p.y + dy[i];
					if (tempx >= 1 && tempx <= N && tempy >= 1 && tempy <= N && !map[tempx][tempy].v) {
						map[tempx][tempy].v = true;
						if (map[tempx][tempy].light) {
							q.add(map[tempx][tempy]);
						} else {
							q2.add(map[tempx][tempy]);
						}
					}
				}
			}
			size = q2.size();
			check = true;
			for (int i = 0; i < size; i++) {
				p = q2.poll();
				if (p.light) {
					check = false;
					q.add(p);
				}else {
					q2.add(p);
				}
			}
			if(check) {
				break;
			}
		}
		System.out.println(count);
	}

	static class Point {
		boolean light;
		boolean v;
		int y, x;
		LinkedList<Point> arr;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
			this.light = false;
			this.v = false;
			this.arr = new LinkedList<>();
		}
	}

}
