package expert;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution_4740 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int iter = Integer.parseInt(br.readLine());
		int[] dy = { 0, 0, -1, 1 };
		int[] dx = { -1, 1, 0, 0 };
		LinkedList<Point> q = new LinkedList<>();
		LinkedList<Point> store = new LinkedList<>();
		LinkedList<Point> tempStore = new LinkedList<>();
		for (int i = 1; i <= iter; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int Q = Integer.parseInt(st.nextToken());
			char[][] map = new char[N][M];
			for (int j = 0; j < N; j++) {
				map[j] = br.readLine().toCharArray();
			}
			for (int j = 0; j < Q; j++) {
				String s = br.readLine();
				if (s.charAt(0) == 'U') {
					boolean flag = true;
					for (int k = 0; k < M; k++) {
						if (map[0][k] != '#') {
							flag = false;
							break;
						}
					}
					if (flag) {
						for (int k = 0; k < N - 1; k++) {
							map[k] = map[k + 1].clone();
						}
						map[N - 1] = s.substring(2).toCharArray();
					}
					moveDown(map);
				} else if (s.charAt(0) == 'L') {
					for (int k = 0; k < N; k++) {
						int pivot = 0;
						for (int l = 0; l < M; l++) {
							if (map[k][l] != '#') {
								char c = map[k][l];
								map[k][l] = '#';
								map[k][pivot] = c;
								pivot++;
							}
						}
					}
				} else if (s.charAt(0) == 'R') {
					for (int k = 0; k < N; k++) {
						int pivot = M - 1;
						for (int l = M - 1; l >= 0; l--) {
							if (map[k][l] != '#') {
								char c = map[k][l];
								map[k][l] = '#';
								map[k][pivot] = c;
								pivot--;
							}
						}
					}
				} else {
					boolean[][] v = new boolean[N][M];
					int max = 0;
					store.clear();
					for (int k = 0; k < N; k++) {
						for (int l = 0; l < M; l++) {
							if (map[k][l] != '#' && !v[k][l]) {
								char c = map[k][l];
								q.clear();
								q.add(new Point(k, l));
								v[k][l] = true;
								tempStore.clear();
								while (!q.isEmpty()) {
									Point p = q.poll();
									tempStore.add(p);
									for (int o = 0; o < 4; o++) {
										int tempy = p.y + dy[o];
										int tempx = p.x + dx[o];
										if (tempy >= 0 && tempy < N && tempx >= 0 && tempx < M && !v[tempy][tempx]
												&& map[tempy][tempx] == c) {
											v[tempy][tempx] = true;
											q.add(new Point(tempy, tempx));
										}
									}
								}
								if (max <= tempStore.size()) {
									if (max < tempStore.size()) {
										max = tempStore.size();
										store.clear();
									}
									store.addAll(tempStore);
								}
							}
						}
					}
					while(!store.isEmpty()) {
						Point p = store.poll();
						map[p.y][p.x] = '#';
					}
					moveDown(map);
				}
			}
			bw.write("#"+i);
			bw.newLine();
			for (int j = 0; j < map.length; j++) {
				bw.write(map[j]);
				bw.newLine();
			}
			bw.newLine();
		}
		bw.flush();
		bw.close();
	}
	static void moveDown(char[][] map) {
		for (int i = 0; i < map[0].length; i++) {
			int pivot = map.length-1;
			for (int j = map.length-1; j >=0; j--) {
				if(map[j][i]!='#') {
					char c = map[j][i];
					map[j][i] = '#';
					map[pivot][i] = c;
					pivot--;
				}
			}
		}
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
