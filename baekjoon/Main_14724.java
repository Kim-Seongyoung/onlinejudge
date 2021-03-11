package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14724 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br.readLine();
		StringTokenizer st;
		int max = 0;
		int temp;
		int idx =0;
		String[] s = {"PROBRAIN", "GROW", "ARGOS", "ADMIN", "ANT", "MOTION", "SPG", "COMON", "ALMIGHTY"};
		for (int i = 0; i < 9; i++) {
			st = new StringTokenizer(br.readLine());
			while(st.hasMoreTokens()) {
				temp = Integer.parseInt(st.nextToken());
				if(max<temp) {
					max =temp;
					idx = i;
				}
			}
		}
		System.out.println(s[idx]);
	}

}
