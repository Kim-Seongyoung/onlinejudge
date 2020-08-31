package programmers;

import java.util.LinkedList;

public class 리틀프렌즈사천성 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int m =3;
		int n = 3;
		String[] board = {"DBF", "G*F", "GDB"};//예외 사항
		System.out.println(solution(m, n, board));
	}
	static public String solution(int m, int n, String[] board) {
		// 알파벳 블록 저장
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
					// 처음 만나는 블록인지 확인
					if (block[idx].check) {
						// 두 번째 일 경우 좌표 값 중 왼쪽에 있는 것을 (y1,x1)으로 변경
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
		// 실제로 board에 있는 블록들 저장
		// 알파벳 순서로 저장
		for (int i = 1; i < block.length; i++) {
			if (block[i].check) {
				q.add(block[i]);
			}
		}
		// 결과값
		StringBuilder sb = new StringBuilder();
		LinkedList<Block> temp = new LinkedList<>();
		while (!q.isEmpty()) {
			int size = q.size();
			temp.clear();
			while(!q.isEmpty()) {
				Block b = q.poll();
				boolean flag = true;
				int idx = b.c - 'A' + 1;
				// (y1, x1) 기준으로 (y1, x2)로 갈 수 있는지 체크
				for (int j = b.x1; j <= b.x2; j++) {
					if (map[b.y1][j] == 0 || map[b.y1][j] == idx) {
						continue;
					} else {
						flag = false;
						break;
					}
				}
				// 가능하면
				if (flag) {
					int maxY = Math.max(b.y1, b.y2);
					int minY = Math.min(b.y1, b.y2);
					//  (minY, x2) 기준으로 (maxY, x2) 가능한지 판단
					for (int j = minY; j <= maxY; j++) {
						if (map[j][b.x2] == 0 || map[j][b.x2] == idx) {
							continue;
						} else {
							flag = false;
							break;
						}
					}
					// 가능하면 저장
					if (flag) {
						map[b.y1][b.x1] = 0;
						map[b.y2][b.x2] = 0;
						sb.append(b.c);
						// 나머지는 다시 temp로 이동
						while(!q.isEmpty()) {
							temp.add(q.poll());
						}
						break;
					}
				}
				// 위 조건이 안될 경우
				if (!flag) {
					flag = true;
					int maxY = Math.max(b.y1, b.y2);
					int minY = Math.min(b.y1, b.y2);
					// (minY, x1) 기준으로 (maxY, x1)으로 갈 수 있는지 체크
					for (int j = minY; j <= maxY; j++) {
						if (map[j][b.x1] == 0 || map[j][b.x1] == idx) {
							continue;
						} else {
							flag = false;
							temp.add(b);
							break;
						}
					}
					// 가능한 경우
					if (flag) {
						// (y2, x1) 기준으로 (y2,x2) 갈 수 있는지 체크
						for (int j = b.x1; j <= b.x2; j++) {
							if (map[b.y2][j] == 0 || map[b.y2][j] == idx) {
								continue;
							} else {
								flag = false;
								temp.add(b);
								break;
							}
						}
						// 가능하면 q에 있는 값은 temp 이동
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
			// 아까 q의 사이즈와 temp의 사이즈가 같으면 제거한 블록이 없음
			if (size == temp.size()) {
				return "IMPOSSIBLE";
			}
			// 그러치 않으면 temp를 q에 복사
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
