package programmers;

import java.util.Arrays;
import java.util.Comparator;

public class 캠핑 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 4;
		int[][] data = { { 0, 0 }, { 1, 1 }, { 0, 2 }, { 2, 0 } };
		System.out.println(solution(n, data));
	}

	static public int solution(int n, int[][] data) {
		int answer = 0;
		Point[] arr = new Point[data.length];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = new Point(data[i][1], data[i][0]);
		}
		Arrays.sort(arr, new Comparator<Point>() {

			@Override
			public int compare(Point o1, Point o2) {
				if (o1.y > o2.y) {
					return 2;
				} else if (o1.y == o2.y) {
					if (o1.x > o2.x) {
						return 1;
					} else {
						return -1;
					}
				} else {
					return -2;
				}
			}

		});
		int left_x = 0;
		int right_x = Integer.MAX_VALUE;
		int right_y =0 ;
		for (int i = 0; i < arr.length; i++) {
			left_x = 0;
			right_x = Integer.MAX_VALUE;
			right_y = arr[i].y;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[i].y < arr[j].y) {
					if (left_x <= arr[j].x && arr[j].x < arr[i].x) {
						left_x = arr[j].x;
						answer++;
					} else if (arr[j].x <= right_x && arr[i].x < arr[j].x) {
						int temp = j;
						for (int k = j; k<arr.length && arr[j].y ==arr[k].y&& arr[k].x<=right_x; k++) {
							answer++;
							temp++;
						}
						right_x = arr[j].x;
						j=temp-1;
					}
				}
			}
		}
		return answer;
	}

	static class Point {
		int y;
		int x;

		Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
