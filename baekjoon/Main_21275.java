package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_21275 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String s = st.nextToken();
		int[] arrA = new int[s.length()];
		int a = 0, b = 0;
		a = stringToNum(arrA, s)+1;
		s = st.nextToken();
		int[] arrB = new int[s.length()];
		b = stringToNum(arrB, s)+1;
		long[][] result = new long[2][37];
		cal(arrA, a, result[0]);
		cal(arrB, b, result[1]);
		long X = 0, A = 0, B = 0;
		boolean check = false, flag = true;
		for (int i = a; i < result[0].length && flag; i++) {
			if (result[0][i] >= 0) {
				for (int j = b; j < result[0].length; j++) {
					if (i == j) {
						continue;
					} else if (result[0][i] == result[1][j]) {
						if (!check) {
							X = result[0][i];
							A = i;
							B = j;
							check = true;
						} else {
							flag = false;
							break;
						}
					} else if (result[0][i] < result[1][j]) {
						break;
					}
				}
			}
		}
		if (flag) {
			if (check) {
				System.out.println(X + " " + A + " " + B);
			} else {
				System.out.println("Impossible");
			}
		} else {
			System.out.println("Multiple");
		}
	}

	static int stringToNum(int[] arr, String s) {
		int max = 0;
		char c;
		for (int i = 0; i < arr.length; i++) {
			c = s.charAt(s.length() - 1 - i);
			if ('0' <= c && c <= '9') {
				arr[i] = c - '0';
			} else {
				arr[i] = c - 'a' + 10;
			}
			max = Math.max(max, arr[i]);
		}
		return max++;
	}

	static void cal(int[] arr, int pivot, long[] result) {
		long temp = 0;
		long index = 1;
		for (int i = pivot; i < result.length; i++) {
			temp = 0;
			index = 1;
			for (int j = 0; j < arr.length; j++) {
				temp += index * arr[j];
				if (temp < 0) {
					break;
				}
				index *= i;
			}
			if (temp < 0) {
				result[i] = -1;
			} else {
				result[i] = temp;
			}
		}
	}

}
