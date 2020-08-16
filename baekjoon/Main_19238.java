package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_19238 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int fuel = Integer.parseInt(st.nextToken());
		int answer = 0;
		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				if (st.nextToken().charAt(0) == '1') {
					map[i][j] = -1;
				}
			}
		}
		st = new StringTokenizer(br.readLine());
		Point taxi = new Point(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, 0);
		int[] dy = { -1, 0, 0, 1 };
		int[] dx = { 0, -1, 1, 0 };
		Passenger[] passengers = new Passenger[M + 1];
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			Point p = new Point(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, 0);
			map[p.y][p.x] = i;
			passengers[i] = new Passenger(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
			LinkedList<Point> q = new LinkedList<>();
			q.add(p);
			boolean[][] v = new boolean[N][N];
			v[p.y][p.x] = true;
			while (!q.isEmpty()) {
				Point point = q.poll();
				if (point.y == passengers[i].y && point.x == passengers[i].x) {
					passengers[i].len = point.cnt;
					break;
				}
				for (int j = 0; j < 4; j++) {
					int tempy = point.y + dy[j];
					int tempx = point.x + dx[j];
					if (tempy >= 0 && tempy < N && tempx >= 0 && tempx < N && map[tempy][tempx] != -1
							&& !v[tempy][tempx]) {
						v[tempy][tempx] = true;
						q.add(new Point(tempy, tempx, point.cnt + 1));

					}
				}
			}
			if(passengers[i].len==0) {
				System.out.println(-1);
				return;
			}
		}
		for (int k = 1; k < passengers.length; k++) {
			PriorityQueue<Point> q = new PriorityQueue<>(new Comparator<Point>() {
				@Override
				public int compare(Point o1, Point o2) {
					if(o1.cnt>o2.cnt) {
						return 3;
					}else if(o1.cnt==o2.cnt){
						if(o1.y>o2.y) {
							return 2;
						}else if(o1.y==o2.y) {
							if(o1.x>o2.x) {
								return 1;
							}else {
								return -1;
							}
						}else {
							return -2;
						}
					}else {
						return -3;
					}
				}
			});
			boolean[][] v = new boolean[N][N];
			q.add(new Point(taxi.y,taxi.x,0));//조심
			v[taxi.y][taxi.x] = true;
			int move = 0;
			while (!q.isEmpty()) {
				Point p = q.poll();
				if (map[p.y][p.x] > 0) {
					fuel -= (p.cnt+passengers[map[p.y][p.x]].len);
					move = passengers[map[p.y][p.x]].len*2;
					taxi.y = passengers[map[p.y][p.x]].y;
					taxi.x = passengers[map[p.y][p.x]].x;
					map[p.y][p.x] = 0;
					break;
				}
				for (int i = 0; i < 4; i++) {
					int tempy = p.y + dy[i];
					int tempx = p.x + dx[i];
					if (tempy >= 0 && tempy < N && tempx >= 0 && tempx < N && map[tempy][tempx] != -1
							&& !v[tempy][tempx]) {
						v[tempy][tempx] = true;
						q.add(new Point(tempy, tempx, p.cnt + 1));

					}
				}
			}
			if (fuel < 0 || move==0) {
				answer= -1;
				break;
			}else {
				fuel +=move;
			}
		}
		if(answer!=-1) {
			answer = fuel;
		}
		System.out.println(answer);
	}

	static class Passenger {
		int y;
		int x;
		int len;

		Passenger(int y, int x) {
			this.y=y;
			this.x=x;
			this.len = 0;
		}
	}

	static class Point {
		int y;
		int x;
		int cnt;

		Point(int y, int x, int cnt) {
			this.y = y;
			this.x = x;
			this.cnt = cnt;
		}
	}
}
