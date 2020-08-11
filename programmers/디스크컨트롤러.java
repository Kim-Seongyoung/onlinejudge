package programmers;
import java.util.Comparator;
import java.util.PriorityQueue;
public class 디스크컨트롤러 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public int solution(int[][] jobs) {
		int answer = 0;
		PriorityQueue<Point> q = new PriorityQueue<>(new Comparator<Point>() {
			@Override
			public int compare(Point o1, Point o2) {
				if (o1.start == o2.start) {
					return o1.len - o2.len;
				}
				return o1.start - o2.start;
			}
		});
		for (int i = 0; i < jobs.length; i++) {
			q.add(new Point(jobs[i][0], jobs[i][0] + jobs[i][1], jobs[i][1]));
		}
		int time = 0;
		PriorityQueue<Point> temp = new PriorityQueue<>(new Comparator<Point>() {
			@Override
			public int compare(Point o1, Point o2) {
				return o1.len - o2.len;
			}
		});
		while (!q.isEmpty()) {
			temp.add(q.poll());
			while (!temp.isEmpty()) {
				Point p = temp.poll();
				int pivot = Math.max(time, p.start);
				time = pivot + p.len;
				while (!q.isEmpty()) {
					Point t = q.poll();
					if (t.start > time) {
						q.add(t);
						break;
					}
					temp.add(t);
				}
				answer += (pivot - p.start + p.len);
			}
		}
		return answer / jobs.length;
	}

	class Point {
		int start;
		int end;
		int len;
		Point(int start, int end,int len) {
			this.start = start;
			this.end = end;
			this.len= len;
		}
	}
}
