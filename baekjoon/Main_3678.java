package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Main_3678 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int[][] map = new int[117][117];
		int[] arr = new int[10001];
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
		PriorityQueue<Point> pq = new PriorityQueue<>(new Comparator<Point>() {
			@Override
			public int compare(Point o1, Point o2) {
				if (o1.count > o2.count) {
					return 2;
				} else if (o1.count == o2.count) {
					if (o1.idx > o2.idx) {
						return 1;
					} else {
						return -1;
					}
				} else {
					return -2;
				}
			}
		});
		pq.add(new Point(1, 1));
		pq.add(new Point(2, 1));
		pq.add(new Point(3, 1));
		pq.add(new Point(4, 1));
		pq.add(new Point(5, 0));
		hm.put(1, 1);
		hm.put(2, 2);
		hm.put(3, 3);
		hm.put(4, 4);
		map[58][58] = 1;
		map[58][59] = 2;
		map[57][59] = 3;
		map[57][58] = 4;

		int y = 57;
		int x = 58;
		int i = 4;
		int pivot = 1;
		while (i < arr.length) {
			for (int j = 0; j < pivot; j++) {
				y++;
				x--;
				i++;
				map[y][x] = search(map, y, x, pq);
				hm.put(i, map[y][x]);
			}
			for (int j = 0; j < pivot; j++) {
				y++;
				i++;
				map[y][x] = search(map, y, x, pq);
				hm.put(i, map[y][x]);
			}
			pivot++;
			for (int j = 0; j < pivot; j++) {
				x++;
				i++;
				map[y][x] = search(map, y, x, pq);
				hm.put(i, map[y][x]);
			}
			for (int j = 0; j < pivot - 1; j++) {
				y--;
				x++;
				i++;
				map[y][x] = search(map, y, x, pq);
				hm.put(i, map[y][x]);
			}
			for (int j = 0; j < pivot; j++) {
				y--;
				i++;
				map[y][x] = search(map, y, x, pq);
				hm.put(i, map[y][x]);
			}
			for (int j = 0; j < pivot; j++) {
				x--;
				i++;
				map[y][x] = search(map, y, x, pq);
				hm.put(i, map[y][x]);
			}
		}
		int c = Integer.parseInt(br.readLine());
		for (int j = 0; j < c; j++) {
			bw.write(hm.get(Integer.parseInt(br.readLine()))+"");
			bw.newLine();
		}
		bw.flush();
		bw.close();
	}

	static int search(int[][] map, int y, int x, PriorityQueue<Point> pq) {
		HashSet<Integer> hs = new HashSet<Integer>();
		int[] dy = { -1, -1, 0, 0, 1, 1 };
		int[] dx = { 0, 1, -1, 1, -1, 0 };
		for (int i = 0; i < 6; i++) {
			int tempy = y+dy[i];
			int tempx = x+dx[i];
			if(tempy>=0&& tempy<map.length && tempx>=0 &&  tempx<map.length) {
				hs.add(map[y+dy[i]][x+dx[i]]);
			}
		}
		LinkedList<Point> q = new LinkedList<>();
		while(!pq.isEmpty()) {
			Point p = pq.poll();
			q.add(p);
			if(!hs.contains(p.idx)) {
				p.count++;
				while(!q.isEmpty()) {
					pq.add(q.poll());
				}
				return p.idx;
			}
		}
		return 0;
	}

	static class Point {
		int idx;
		int count;

		Point(int idx, int count) {
			this.idx = idx;
			this.count = count;
		}
	}

}
