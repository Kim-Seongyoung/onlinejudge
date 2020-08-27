package programmers;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
public class 지형이동 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public int solution(int[][] land, int height) {
		int answer = 0;
		int N = land.length;
		int[][] map = new int[N][N];
		boolean[][] v = new boolean[N][N];
		int[] dy = { 0, 0, -1, 1 };
		int[] dx = { -1, 1, 0, 0 };
		int color = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!v[i][j]) {
					LinkedList<Point> q = new LinkedList<>();
					q.add(new Point(i, j));
					v[i][j] = true;
					map[i][j] = color;
					while (!q.isEmpty()) {
						Point p = q.poll();
						for (int k = 0; k < 4; k++) {
							int tempy = p.y + dy[k];
							int tempx = p.x + dx[k];
							if (tempy >= 0 && tempy < N && tempx >= 0 && tempx < N && !v[tempy][tempx]
									&& Math.abs(land[p.y][p.x] - land[tempy][tempx]) <= height) {
								v[tempy][tempx] = true;
								q.add(new Point(tempy, tempx));
								map[tempy][tempx] = color;
							}
						}
					}
					color++;
				}
			}
		}
		PriorityQueue<Ladder> ladder = new PriorityQueue<>(new Comparator<Ladder>() {

			@Override
			public int compare(Ladder o1, Ladder o2) {
				return o1.cost - o2.cost;
			}
		});
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				v[i][j] = false;
				for (int k = 0; k < 4; k++) {
					int tempy = i + dy[k];
					int tempx = j + dx[k];
					if (tempy >= 0 && tempy < N && tempx >= 0 && tempx < N && v[tempy][tempx]
							&& map[tempy][tempx] != map[i][j]) {
						ladder.add(new Ladder(map[i][j], map[tempy][tempx], Math.abs(land[i][j] - land[tempy][tempx])));
					}
				}
			}
		}
		int[] colors = new int[color];
		for (int i = 0; i < colors.length; i++) {
			colors[i] = i;
		}
		for (int i = 1; i < color; i++) {
			while (true) {
				Ladder l = ladder.poll();
				if(Find(colors,l.color1)!=Find(colors,l.color2)) {
					Union(l.color1, l.color2, colors);
					answer+=l.cost;
					break;
				}
			}
		}
		return answer;
	}

	int Find(int[] colors, int pivot) {
		if (colors[pivot] != pivot) {
			colors[pivot] = Find(colors, colors[pivot]);
		}
		return colors[pivot];
	}

	void Union(int a, int b, int[] colors) {
		int tempA = Find(colors, a);
		int tempB = Find(colors, b);
		colors[tempA] = tempB;
	}

	class Ladder {
		int color1;
		int color2;
		int cost;

		Ladder(int color1, int color2, int cost) {
			this.color1 = color1;
			this.color2 = color2;
			this.cost = cost;
		}
	}

	class Point {
		int y;
		int x;

		Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
