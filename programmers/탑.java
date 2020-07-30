package programmers;

import java.util.LinkedList;

public class 탑 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] heights = {6,9,5,7,4};
		int[] answer = new int[heights.length];
		LinkedList<Point> q = new LinkedList<>();
		for (int i = 0; i < heights.length; i++) {
			while (!q.isEmpty()) {
				Point p = q.pollLast();
				if (p.h > heights[i]) {
					q.add(p);
					q.add(new Point(heights[i], i));
					answer[i] = p.idx + 1;
					break;
				}
			}
			if (q.isEmpty()) {
				q.add(new Point(heights[i], i));
				answer[i] = 0;
			}
		}
	}

	public int[] solution(int[] heights) {
		int[] answer = new int[heights.length - 1];
		LinkedList<Point> q = new LinkedList<>();
		for (int i = 0; i < heights.length; i++) {
			while (!q.isEmpty()) {
				Point p = q.pollLast();
				if (p.h > heights[i]) {
					q.add(new Point(heights[i], i));
					answer[i] = p.idx + 1;
					break;
				}
			}
			if (q.isEmpty()) {
				q.add(new Point(heights[i], i));
				answer[i] = 0;
			}
		}
		return answer;
	}

	static class Point {
		int h;
		int idx;

		Point(int h, int idx) {
			this.h = h;
			this.idx = idx;
		}
	}
}
