package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_1976 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		boolean[][] map = new boolean[N][N];
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st=  new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				if(st.nextToken().charAt(0)=='1') {
					map[i][j] = true;
				}
			}
		}
		st = new StringTokenizer(br.readLine());
		LinkedList<Integer> q = new LinkedList<>();
		q.add(Integer.parseInt(st.nextToken())-1);
		boolean[] v =new boolean[N];
		v[q.peek()] = true;
		int next;
		while(!q.isEmpty()) {
			next = q.poll();
			for (int i = 0; i < N; i++) {
				if(map[next][i] && !v[i]) {
					v[i] = true;
					q.add(i);
				}
			}
		}
		boolean check = true;
		while(st.hasMoreTokens()) {
			if(!v[Integer.parseInt(st.nextToken())-1]) {
				check = false;
				break;
			}
		}
		if(check) {
			System.out.println("YES");
		}else {
			System.out.println("NO");
		}
	}

}
