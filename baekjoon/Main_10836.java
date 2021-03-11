package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_10836 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int[][] map = new int[M][M];
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] =1;
			}
		}
		int[] op = new int[2*M-1];
		for (int i = 0; i < op.length; i++) {
			op[i] = 1;
		}
		int temp;
		int pivot = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			temp= Integer.parseInt(st.nextToken());
			pivot = temp;
			temp = Integer.parseInt(st.nextToken());
			for (int j = 0; j < temp; j++) {
				op[pivot]++;
				pivot++;
			}
			temp = Integer.parseInt(st.nextToken());
			for (int j = 0; j < temp; j++) {
				op[pivot] +=2;
				pivot++;
			}
			
		}
		for (int i = M-1; i < op.length; i++) {
			bw.append(op[i]+" ");
		}
		bw.newLine();
		for (int i = M-2; i >=0; i--) {
			bw.append(op[i]+" ");
			for (int j = M; j < op.length; j++) {
				bw.append(op[j]+" ");
			}
			bw.newLine();
		}
		bw.flush();
		bw.close();
	}

}
