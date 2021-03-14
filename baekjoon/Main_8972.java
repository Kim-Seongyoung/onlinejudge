package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_8972 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		LinkedList<Point> mad = new LinkedList<>();
		Point me = new Point(0, 0);
		String s;
		for (int i = 0; i < N; i++) {
			s = br.readLine();
			for (int j = 0; j < M; j++) {
				if (s.charAt(j) == '.') {
					continue;
				} else if (s.charAt(j) == 'R') {
					mad.add(new Point(i, j));
					map[i][j]++;
				} else {
					me.y = i;
					me.x = j;
				}
			}
		}
		s = br.readLine();
		int[] dy = { 0, 1, 1, 1, 0, 0, 0, -1, -1, -1 };
		int[] dx = { 0, -1, 0, 1, -1, 0, 1, -1, 0, 1 };
		int d = 0, len, suby, subx;
		Point p;
		int X = 0;
		for (int i = 0; i < s.length(); i++) {
			d = s.charAt(i) - '0';
			map[me.y][me.x] = 0;
			me.y += dy[d];
			me.x += dx[d];
			if (map[me.y][me.x] == 1) {
				X = i + 1;
				break;
			}
			map[me.y][me.x]=-1;
			len = mad.size();
			for (int j = 0; j < len; j++) {
				p = mad.poll();
				if (map[p.y][p.x] != 1) {
					map[p.y][p.x] = 0;
				} else {
					map[p.y][p.x] = 0;
					suby = me.y - p.y;
					subx = me.x - p.x;
					if (suby == 0) {
						if (subx > 0) {
							d = 6;
						} else {
							d = 4;
						}
					} else if (subx == 0) {
						if (suby > 0) {
							d = 2;
						} else {
							d = 8;
						}
					} else {
						if (suby > 0 && subx > 0) {
							d = 3;
						} else if (suby > 0 && subx < 0) {
							d = 1;
						} else if (suby < 0 && subx > 0) {
							d = 9;
						} else {
							d = 7;
						}
					}
					p.y += dy[d];
					p.x += dx[d];
					mad.add(p);
				}
			}
			len = mad.size();
			for (int j = 0; j < len; j++) {
				p = mad.poll();
				map[p.y][p.x]++;
				mad.add(p);
			}
			if (map[me.y][me.x] > -1) {
				X = i+1;
				break;
			}
		}
		
		if(X!=0) {
			bw.append("kraj "+X);
		}else {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(map[i][j]==1) {
						bw.append('R');
					}else if(map[i][j]==-1) {
						bw.append('I');
					}else {
						bw.append('.');
					}
				}
				bw.newLine();
			}
		}
		bw.flush();
		bw.close();
	}

	static class Point {
		int y, x;

		Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

}
