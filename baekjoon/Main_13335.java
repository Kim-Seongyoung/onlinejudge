package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_13335 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int sum = 0;
		LinkedList<Integer> q = new LinkedList<Integer>();
		for (int i = 0; i < w; i++) {
			q.add(0);
		}
		st = new StringTokenizer(br.readLine());
		int time = 1;
		int pivot = Integer.parseInt(st.nextToken());
		while (true) {
			sum -= q.poll();
			if (sum + pivot <= L) {
				sum += pivot;
				q.add(pivot);
				if (!st.hasMoreTokens()) {
					System.out.println(time + w);
					break;
				}
				pivot = Integer.parseInt(st.nextToken());
			} else {
				q.add(0);
			}
			time++;
		}
	}

}
