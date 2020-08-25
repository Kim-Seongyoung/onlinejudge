package programmers;

import java.util.LinkedList;

public class 리틀프렌즈사천성 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int m =3;
		int n = 3;
		String[] board = {"DBF", "G*F", "GDB"};
		System.out.println(solution(m, n, board));
	}
	static public String solution(int m, int n, String[] board) {
		Block[] block = new Block[27];
		int[][] map = new int[m][n];
		char c = 'A';
		for (int i = 1; i < block.length; i++) {
			block[i] = new Block(c);
			c++;
		}
		LinkedList<Block> q = new LinkedList<>();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (board[i].charAt(j) == '.') {
					continue;
				} else if (board[i].charAt(j) == '*') {
					map[i][j] = -1;
				} else {
					int idx = board[i].charAt(j) - 'A' + 1;
					map[i][j] = idx;
					if (block[idx].check) {
						if (j > block[idx].x1) {
							block[idx].y2 = i;
							block[idx].x2 = j;
						} else {
							block[idx].y2 = block[idx].y1;
							block[idx].y1 = i;
							block[idx].x2 = block[idx].x1;
							block[idx].x1 = j;
						}
					} else {
						block[idx].y1 = i;
						block[idx].x1 = j;
						block[idx].check = true;
					}
				}
			}
		}
		for (int i = 1; i < block.length; i++) {
			if (block[i].check) {
				q.add(block[i]);
			}
		}
		StringBuilder sb = new StringBuilder();
		LinkedList<Block> temp = new LinkedList<>();
		while (!q.isEmpty()) {
			int size = q.size();
			temp.clear();
			while(!q.isEmpty()) {
				Block b = q.poll();
				boolean flag = true;
				int idx = b.c - 'A' + 1;
				for (int j = b.x1; j <= b.x2; j++) {
					if (map[b.y1][j] == 0 || map[b.y1][j] == idx) {
						continue;
					} else {
						flag = false;
						break;
					}
				}
				if (flag) {
					int maxY = Math.max(b.y1, b.y2);
					int minY = Math.min(b.y1, b.y2);
					for (int j = minY; j <= maxY; j++) {
						if (map[j][b.x2] == 0 || map[j][b.x2] == idx) {
							continue;
						} else {
							flag = false;
							break;
						}
					}
					if (flag) {
						map[b.y1][b.x1] = 0;
						map[b.y2][b.x2] = 0;
						sb.append(b.c);
						while(!q.isEmpty()) {
							temp.add(q.poll());
						}
						break;
					}
				}
				if (!flag) {
					flag = true;
					int maxY = Math.max(b.y1, b.y2);
					int minY = Math.min(b.y1, b.y2);
					for (int j = minY; j <= maxY; j++) {
						if (map[j][b.x1] == 0 || map[j][b.x1] == idx) {
							continue;
						} else {
							flag = false;
							temp.add(b);
							break;
						}
					}
					if (flag) {
						for (int j = b.x1; j <= b.x2; j++) {
							if (map[b.y2][j] == 0 || map[b.y2][j] == idx) {
								continue;
							} else {
								flag = false;
								temp.add(b);
								break;
							}
						}
						if (flag) {
							map[b.y1][b.x1] = 0;
							map[b.y2][b.x2] = 0;
							sb.append(b.c);
							while(!q.isEmpty()) {
								temp.add(q.poll());
							}
							break;
						}
					}
				}
			}
			if (size == temp.size()) {
				return "IMPOSSIBLE";
			}
			q.addAll(temp);
		}
		return sb.toString();
	}
	static class Block {
		char c;
		int y1;
		int x1;
		int y2;
		int x2;
		boolean check;

		Block(char c) {
			this.check = false;
			this.c = c;
		}
	}
}
