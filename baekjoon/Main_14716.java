package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_14716 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		boolean[][] map = new boolean[M][N];
		boolean[][] v = new boolean[M][N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				if (st.nextToken().charAt(0) == '1') {
					map[i][j] = true;
				}
			}
		}
		int[] dy = { -1, -1, -1, 0, 0, 1, 1, 1 };
		int[] dx = { -1, 0, 1, -1, 1, -1, 0, 1 };
		int tempy,tempx,nexty,nextx;
		LinkedList<Integer> q = new LinkedList<Integer>();
		int count =0;
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] && !v[i][j]) {
					q.add(i);
					q.add(j);
					v[i][j] = true;
					count++;
					while(!q.isEmpty()) {
						tempy = q.poll();
						tempx = q.poll();
						for (int k = 0; k < 8; k++) {
							nexty = tempy+dy[k];
							nextx = tempx+dx[k];
							if(nexty>=0&& nexty<M && nextx>=0 && nextx<N&& map[nexty][nextx] && !v[nexty][nextx]) {
								v[nexty][nextx] = true;
								q.add(nexty);
								q.add(nextx);
							}
						}
					}
				}
			}
		}
		System.out.println(count);
	}

}
