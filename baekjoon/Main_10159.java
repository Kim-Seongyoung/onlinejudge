package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_10159 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine()) + 1;
		int M = Integer.parseInt(br.readLine());
		Point[] heavy = new Point[N];
		Point[] light = new Point[N];
		for (int i = 1; i < N; i++) {
			heavy[i] = new Point(N, i);
			light[i] = new Point(N, i);
		}
		StringTokenizer st;
		int a, b;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			heavy[a].count++;
			heavy[b].next.add(a);
			light[b].count++;
			light[a].next.add(b);
		}
		Point p;
		int next;
		LinkedList<Point> q = new LinkedList<>();
		for (int i = 1; i < N; i++) {
			if (heavy[i].count == 0) {
				q.add(heavy[i]);
			}
		}
		while (!q.isEmpty()) {
			p = q.poll();
			if (p.count == 0) {
				while (!p.next.isEmpty()) {
					next = p.next.poll();
					heavy[next].count--;
					for (int i = 1; i < N; i++) {
						if (p.v[i]) {
							heavy[next].v[i] = true;
						}
					}
					q.add(heavy[next]);
				}
			} else {
				q.add(p);
			}
		}

		for (int i = 1; i < N; i++) {
			if (light[i].count == 0) {
				q.add(light[i]);
			}
		}
		while (!q.isEmpty()) {
			p = q.poll();
			if (p.count == 0) {
				while (!p.next.isEmpty()) {
					next = p.next.poll();
					light[next].count--;
					for (int i = 1; i < N; i++) {
						if (p.v[i]) {
							light[next].v[i] = true;
						}
					}
					q.add(light[next]);
				}
			} else {
				q.add(p);
			}
		}
		int count = 0;
		for (int i = 1; i < N; i++) {
			count = 0;
			for (int j = 1; j < N; j++) {
				if (!heavy[i].v[j] && !light[i].v[j]) {
					count++;
				}
			}
			bw.append(count + "\n");
		}
		bw.flush();
		bw.close();
	}

	static class Point {
		int count;
		boolean[] v;
		LinkedList<Integer> next;

		Point(int N, int i) {
			this.count = 0;
			this.v = new boolean[N];
			this.v[i] = true;
			this.next = new LinkedList<Integer>();
		}
	}
}
