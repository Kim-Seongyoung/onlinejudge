package programmers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class 블록게임 {
	static HashMap<Integer, Block> hm;
	static int answer;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		int[][] board = { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
//				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 4, 0, 0, 0 },
//				{ 0, 0, 0, 0, 0, 4, 4, 0, 0, 0 }, { 0, 0, 0, 0, 3, 0, 4, 0, 0, 0 }, { 0, 0, 0, 2, 3, 0, 0, 0, 5, 5 },
//				{ 1, 2, 2, 2, 3, 3, 0, 0, 0, 5 }, { 1, 1, 1, 0, 0, 0, 0, 0, 0, 5 } };
//		int[][] board = {  { 0, 4, 0, 0 }, { 0, 4, 4, 4 },{ 2, 3, 3, 3 }, { 2, 2, 2, 3 } };
//		int[][] board =
//			{{1,0,0,0,0,0,0,0,0,0}
//			,{1,1,1,2,0,0,0,0,0,0}
//			,{3,2,2,2,0,0,0,0,0,0}
//			,{3,3,3,4,0,0,0,0,0,0}
//			,{5,4,4,4,0,0,0,0,0,0}
//			,{5,5,5,6,0,0,0,0,0,0}
//			,{7,6,6,6,0,0,0,0,0,0}
//			,{7,7,7,8,0,0,0,0,0,0}
//			,{9,8,8,8,0,0,0,0,0,0}
//			,{9,9,9,0,0,0,0,0,0,0}};

		int[][] board = { { 1, 0, 4, 0, 0, 0 },
						  { 1, 0, 4, 4, 4, 3 },
						  { 1, 1, 2, 3, 3, 3 },
						  { 5, 2, 2, 2, 0, 0 },
						  { 5, 5, 5, 6, 6, 6 },
						  { 0, 0, 0, 0, 6, 0 } };
		System.out.println(solution(board));
	}

	static public int solution(int[][] board) {
		answer = 0;
		// 각 block를 저장, 번호를 Key값으로 사용
		hm = new HashMap<Integer, Block>();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (board[i][j] != 0) {
					// 각 block의 좌표를 저장
					if (!hm.containsKey(board[i][j])) {
						hm.put(board[i][j], new Block());
					}
					hm.get(board[i][j]).y.add(i);
					hm.get(board[i][j]).x.add(j);
				}
			}
		}
		Iterator<Integer> iter = hm.keySet().iterator();
		// 각 block의 type 저장
		while (iter.hasNext()) {
			makeType(hm.get(iter.next()));
		}
		iter = hm.keySet().iterator();
		// 각 block이 직사각형이되는지 체크
		while (iter.hasNext()) {
			Block b = hm.get(iter.next());
			// type이 0이 아니고 이미 check가 되었느지 
			if (b.type != 0 && !b.check) {
				search(b, board);
			}
		}
		return answer;
	}
	static boolean check(int[][] board, int y, int x) {
		for (int i = 0; i <= y; i++) {
			// 만약 검은 색 돌이 떨어지는 곳에 다른 블록이 있을 때
			if (board[i][x] != 0) {
				if (hm.get(board[i][x]).type == 0) {
					// type이 0이면 직사각형을 못 만들기 때문에 
					return false;
				} else {
					// 위에 있는 block이 직사각형이 되는지 판단
					if (!search(hm.get(board[i][x]), board)) {
						return false;
					}
				}
			}
		}
		return true;
	}
	static boolean search(Block b, int[][] board) {
		int y = b.y.get(0);
		int x = b.x.get(0);
		// 타입별로 가능한지 체크
		if (b.type == 1) {
			if(!check(board, y, x+1)) {
				return false; 
			}
			if(!check(board, y, x+2)) {
				return false; 
			}
		} else if (b.type == 2) {
			if(!check(board, y+1, x-1)) {
				return false; 
			}
		} else if (b.type == 3) {
			if(!check(board, y+1, x+1)) {
				return false; 
			}
		} else if (b.type == 4) {
			if(!check(board, y, x-2)) {
				return false; 
			}
			if(!check(board, y, x-1)) {
				return false; 
			}
		} else {
			if(!check(board, y, x+1)) {
				return false; 
			}
			if(!check(board, y, x-1)) {
				return false; 
			}
		}
		answer++;
		b.check = true;
		// 만들 수 있으면 block 삭제
		for (int i = 0; i < 4; i++) {
			board[b.y.get(i)][b.x.get(i)] = 0;
		}
		return true;
	}

	static void makeType(Block b) {
		// 직사각형을 못만드는 경우
		if (b.y.get(0) == b.y.get(1)) {
			b.type = 0;
		} else {
			if (b.y.get(1) == b.y.get(2) && b.y.get(2) == b.y.get(3)) {
				if (b.x.get(0) == b.x.get(1)) {
					// 가로로 긴 ㄴ 모양
					b.type = 1;
				} else if (b.x.get(0) == b.x.get(1) + 1) {
					// ㅗ 모양
					b.type = 5;
				} else {
					// 가로로 긴 ㄴ 모양 반대
					b.type = 4;
				}
			} else if (b.y.get(2) == b.y.get(3)) {
				if (b.x.get(0) == b.x.get(2)) {
					// 세로로 긴 ㄴ 모양
					b.type = 3;
				} else {
					// 세로로 긴 ㄴ 모양 반대
					b.type = 2;
				}
			}
		}
	}

	static class Block {
		int type;
		boolean check;
		ArrayList<Integer> y;
		ArrayList<Integer> x;

		Block() {
			this.type = 0;
			this.check = false;
			this.y = new ArrayList<Integer>();
			this.x = new ArrayList<Integer>();
		}
	}
}
