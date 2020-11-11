package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1103 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		v = new boolean[N][M];
		flag = false;
		arrMax = new int[N][M];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				if (s.charAt(j) == 'H') {
					map[i][j] = 0;
				} else {
					map[i][j] = s.charAt(j) - '0';
				}
			}
		}
		v[0][0] = true;
		int result = DFS(0, 0, map);
		if (flag) {
			System.out.println(-1);
		} else {
			System.out.println(result);
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(arrMax[i][j]+" ");
			}
			System.out.println();
		}
	}

	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	static boolean[][] v;
	static int[][] arrMax;
	static boolean flag;

	static int DFS(int y, int x, int[][] map) {

		for (int i = 0; i < 4; i++) {
			int tempy = y + dy[i] * map[y][x];
			int tempx = x + dx[i] * map[y][x];
			if (tempy >= 0 && tempy < map.length && tempx >= 0 && tempx < map[0].length && map[tempy][tempx] != 0) {
				if (v[tempy][tempx]) {
					flag = true;
				} else {
					if (arrMax[tempy][tempx] == 0) {
						v[tempy][tempx] = true;
						int temp = DFS(tempy, tempx, map);
						arrMax[y][x] = Math.max(temp + 1, arrMax[y][x]);
						if (flag) {
							return temp;
						}
						v[tempy][tempx] = false;
					} else {
						arrMax[y][x] = Math.max(arrMax[y][x], arrMax[tempy][tempx] + 1);
					}
				}
			} else {
				arrMax[y][x] = Math.max(1, arrMax[y][x]);
			}
		}
		return arrMax[y][x];
	}

}
