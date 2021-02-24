package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_2638 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		int count = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					count++;
				}
			}
		}
		int[] dy = { 0, 0, 1, -1 };
		int[] dx = { -1, 1, 0, 0 };
		Point p;
		int tempy, tempx, time;
		time = 0;
		LinkedList<Point> q = new LinkedList<>();
		int[][] v = new int[N][M];
		while (count > 0) {
			q.add(new Point(0, 0));
			time++;
			v[0][0] = 1;
			while (!q.isEmpty()) {
				p = q.poll();
				for (int i = 0; i < dx.length; i++) {
					tempy = p.y + dy[i];
					tempx = p.x + dx[i];
					if (tempy >= 0 && tempy < N && tempx >= 0 && tempx < M) {
						if (map[tempy][tempx] == 1) {
							v[tempy][tempx]++;
						} else if (v[tempy][tempx] == 0) {
							v[tempy][tempx] =1;
							q.add(new Point(tempy, tempx));
						}
					}
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(v[i][j]>=2) {
						count--;
						map[i][j]=0;
					}
					v[i][j]=0;
				}
			}

		}
		System.out.println(time);

	}
	static class Point {
		int y;
		int x;

		Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

}
