package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_6087 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		char[][] map = new char[N][M];
		Point[] point = new Point[2];
		int temp = 0;
		int[][] v = new int[N][M];
		String s;
		for (int i = 0; i < N; i++) {
			s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);
				v[i][j] = Integer.MAX_VALUE;
				if (map[i][j] == 'C') {
					point[temp] = new Point(i, j, 0,0);
					temp++;
				}
			}
		}
		int[] dy = { 0, 0, 1, -1 };
		int[] dx = { 1, -1, 0, 0 };
		LinkedList<Point> q = new LinkedList<>();
		int tempy,tempx;
		v[point[0].y][point[0].x] = 0;

		boolean[][][] d = new boolean[N][M][2];
		for (int i = 0; i < 4; i++) {
			tempy= point[0].y+dy[i];
			tempx = point[0].x+dx[i];
			if(tempy>=0&& tempy<N && tempx>=0 && tempx<M && map[tempy][tempx]!='*') {
				v[tempy][tempx]=0;
				q.add(new Point(tempy,tempx, i, 0));
			}
		}
		Point p;
		while(!q.isEmpty()) {
			p = q.poll();
			for (int i = 0; i < 4; i++) {
				tempy= p.y+dy[i];
				tempx = p.x+dx[i];
				if(tempy>=0&& tempy<N && tempx>=0 && tempx<M && map[tempy][tempx]!='*') {
					if(p.d>1) {
						if(i>1) {
							if(v[tempy][tempx]>p.count) {
								d[tempy][tempx][1] = true;
								v[tempy][tempx] = p.count;
								q.add(new Point(tempy,tempx,i,p.count));
							}else if(v[tempy][tempx]== p.count && !d[tempy][tempx][1]) {
								d[tempy][tempx][1] = true;
								q.add(new Point(tempy,tempx,i,p.count));
							}
						}else {
							if(v[tempy][tempx]>p.count+1) {
								d[tempy][tempx][0] = true;
								v[tempy][tempx] = p.count+1;
								q.add(new Point(tempy,tempx,i,p.count+1));
							}else if(v[tempy][tempx]== p.count+1 && !d[tempy][tempx][0]) {
								d[tempy][tempx][0] = true;
								q.add(new Point(tempy,tempx,i,p.count+1));
							}
						}
					}else {
						if(i>1) {
							if(v[tempy][tempx]>p.count+1) {
								v[tempy][tempx] = p.count+1;
								d[tempy][tempx][1] = true;
								q.add(new Point(tempy,tempx,i,p.count+1));
							}else if(v[tempy][tempx]== p.count+1 && !d[tempy][tempx][1]) {
								d[tempy][tempx][1] = true;
								q.add(new Point(tempy,tempx,i,p.count+1));
							}
						}else {
							if(v[tempy][tempx]>p.count) {
								d[tempy][tempx][0] = true;
								v[tempy][tempx] = p.count;
								q.add(new Point(tempy,tempx,i,p.count));
							}else if(v[tempy][tempx]== p.count && !d[tempy][tempx][0]) {
								d[tempy][tempx][0] = true;
								q.add(new Point(tempy,tempx,i,p.count));
							}
						}
					}
				}
			}
		}
		System.out.println(v[point[1].y][point[1].x]);
	}

	static class Point {
		int y, x, d, count;

		Point(int y, int x, int d, int count) {
			this.y = y;
			this.x = x;
			this.d = d;
			this.count = count;
		}
	}
}
