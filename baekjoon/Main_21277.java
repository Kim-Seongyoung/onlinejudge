package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_21277 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N1 = Integer.parseInt(st.nextToken());
		int M1 = Integer.parseInt(st.nextToken());
		boolean[][] puzzle = new boolean[N1][M1];
		String s;
		for (int i = 0; i < N1; i++) {
			s = br.readLine();
			for (int j = 0; j < M1; j++) {
				if (s.charAt(j) == '1') {
					puzzle[i][j] = true;
				}
			}
		}
		st = new StringTokenizer(br.readLine());
		int N2 = Integer.parseInt(st.nextToken());
		int M2 = Integer.parseInt(st.nextToken());
		ArrayList<Point>[] loc = new ArrayList[4];
		loc[0] = new ArrayList<>();
		loc[1] = new ArrayList<>();
		loc[2] = new ArrayList<>();
		loc[3] = new ArrayList<>();
		for (int i = 0; i < N2; i++) {
			s = br.readLine();
			for (int j = 0; j < M2; j++) {
				if (s.charAt(j) == '1') {
					loc[0].add(new Point(i, j));
					loc[1].add(new Point(j, N2 - i - 1));
					loc[2].add(new Point(N2 - i - 1, M2 - j - 1));
					loc[3].add(new Point(M2 - j - 1, i));
				}
			}
		}
		int min = (N1 + N2) * Math.max(M1, M2);
		for (int k = 0; k < 4; k++) {
			int index_H = -1 * (N2);
			int index_W = -1 * (M2);
			for (int i = index_H; i < N1; i++) {
				int H = 0;
				if (i >= 0) {
					H = Math.max(N1, N2 + i);
				} else {
					H = Math.max(N2, N1 - i);
				}
				for (int j = index_W; j < M1; j++) {
					int W = 0;
					if (j >= 0) {
						W = Math.max(M1, M2 + j);
					} else {
						W = Math.max(M2, M1 - j);
					}
					if (H * W < min) {
						boolean check = true;
						for (int l = 0; l < loc[k].size(); l++) {
							int tempy = loc[k].get(l).y + i;
							int tempx = loc[k].get(l).x + j;
							if (tempy >= 0 && tempy < N1 && tempx >= 0 && tempx < M1 && puzzle[tempy][tempx]) {
								check = false;
								break;
							}
						}
						if (check) {
							min = H * W;
						}
					}
				}
			}
			int temp = N2;
			N2 = M2;
			M2 = temp;
		}
		System.out.println(min);
	}

	static class Point {
		int y, x;

		Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
