package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main_1553 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		boolean[] domino = new boolean[49];
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int[][] arr = new int[8][7];
		boolean[][] v = new boolean[8][7];
		for (int i = 0; i < arr.length; i++) {
			String s = br.readLine();
			for (int j = 0; j < 7; j++) {
				arr[i][j] = s.charAt(j) - '0';
			}
		}
		bw.write(""+search(arr, domino, v, 0, 0, 0));
		bw.flush();
		bw.close();

	}

	static int[] dy = { 0, 0, 1, -1 };
	static int[] dx = { 1, -1, 0, 0 };

	static int search(int[][] arr, boolean[] domino, boolean[][] v, int y, int x, int count) {
		if (y > 7 || x > 6) {
			if (count == 56) {
				return 1;
			} else {
				return 0;
			}
		}
		int sum = 0;
		int index;
		int tempy = y + 1;
		int tempx = x + 1;
		if (v[y][x]) {
			if (x > 5) {
				sum += search(arr, domino, v, tempy, 0,count);
			} else {
				sum += search(arr, domino, v, y, tempx,count);
			}
		} else {
			v[y][x] = true;
			if (y < 7) {
				index = Math.min(arr[y][x], arr[tempy][x]) * 6 + Math.max(arr[y][x], arr[tempy][x]);
				if (!domino[index]) {
					v[tempy][x] = true;
					domino[index] = true;
					if (x < 6) {
						sum += search(arr, domino, v, y, tempx,count+2);
					} else {
						sum += search(arr, domino, v, tempy, 0,count+2);
					}
					domino[index] = false;
					v[tempy][x] = false;
				}
			}
			if (x < 6) {
				if (!v[y][tempx]) {
					index = Math.min(arr[y][x], arr[y][tempx]) * 6 + Math.max(arr[y][x], arr[y][tempx]);
					if (!domino[index]) {
						domino[index] = true;
						v[y][tempx] = true;
						if (tempx < 6) {
							sum += search(arr, domino, v, y, tempx + 1,count+2);
						} else {
							sum += search(arr, domino, v, tempy, 0,count+2);
						}

						domino[index] = false;
						v[y][tempx] = false;
					}
				}
			}

			v[y][x] = false;
		}
		return sum;
	}

}
