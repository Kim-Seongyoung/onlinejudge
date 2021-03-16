package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_16767 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		PriorityQueue<Point> arr = new PriorityQueue<>(new Comparator<Point>() {
			@Override
			public int compare(Point o1, Point o2) {
				return o1.start - o2.start;
			}
		});
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			arr.add(new Point(i, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		PriorityQueue<Point> pq = new PriorityQueue<>(new Comparator<Point>() {
			@Override
			public int compare(Point o1, Point o2) {
				return o1.idx - o2.idx;
			}
		});
		Point p = arr.poll();
		int max = 0;
		int time = p.start + p.eat;
		while (!arr.isEmpty() || !pq.isEmpty()) {
			while (!arr.isEmpty()) {
				if (time >= arr.peek().start) {
					pq.add(arr.poll());
				} else {
					break;
				}
			}
			if (pq.isEmpty()) {
				p = arr.poll();
				time = p.start+p.eat;
			} else {
				p = pq.poll();
				max = Math.max(max, time - p.start);
				time += p.eat;
			}
		}
		System.out.println(max);
	}

	static class Point {
		int idx;
		int start;
		int eat;

		Point(int idx, int start, int eat) {
			this.idx = idx;
			this.start = start;
			this.eat = eat;
		}
	}
}
