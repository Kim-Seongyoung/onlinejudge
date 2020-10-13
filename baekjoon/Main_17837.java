package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17837 {
	static int[] dy = { 0, 0, -1, 1 };
	static int[] dx = { 1, -1, 0, 0 };
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		// 체스 말 배열
		Node[] nodes = new Node[K + 1];
		// map 배열 map[y][x][0]은 블록의 종류, map[y][x][1]은 맨 아래에 깔려있는 체스 말의 번호
		// 입력
		int[][][] map = new int[N][N][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j][0] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 1; i <= K; i++) {
			st = new StringTokenizer(br.readLine());
			nodes[i] = new Node(i, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1,
					Integer.parseInt(st.nextToken()) - 1);
			map[nodes[i].y][nodes[i].x][1] = i;
		}
		boolean flag = true;
		// 1000초를 넘어가면 -1 종료
		for (int i = 1; i <= 1000; i++) {
			for (int j = 1; j <= K; j++) {
				// 다음 이동 위치
				int tempy = nodes[j].y + dy[nodes[j].d];
				int tempx = nodes[j].x + dx[nodes[j].d];
				// 범위 안일 때
				if (tempy >= 0 && tempy < N && tempx >= 0 && tempx < N) {
					// 하얀, 빨간, 파란 색일 경우
					if (map[tempy][tempx][0] == 0) {
						White(map, j, nodes, tempy, tempx);
					} else if (map[tempy][tempx][0] == 1) {
						Red(map, j, nodes, tempy, tempx);
					} else {
						Blue(map, j, nodes, tempy, tempx);
					}
				} else {
					// 범위 밖일 때는 파란색과 동일하게 작동
					Blue(map, j, nodes, tempy, tempx);
				}
				// 현재 이동한 위치의 체스 말 개수
				int count = 0;
				// 현재 가장 밑에 깔려 있는 체스 말에서 시작
				Node node = nodes[map[nodes[j].y][nodes[j].x][1]];
				while (node != null) {
					count++;
					node = node.next;
					if (count == 4) {
						flag = false;
						System.out.println(i);
						break;
					}
				}
				if (!flag) {
					break;
				}
			}
			if (!flag) {
				break;
			}
		}
		if (flag) {
			System.out.println(-1);
		}
	}
	// 파란색일 때
	static void Blue(int[][][] map, int j, Node[] nodes, int tempy, int tempx) {
		// 방향 바꾸기
		if (nodes[j].d == 0) {
			nodes[j].d = 1;
		} else if (nodes[j].d == 1) {
			nodes[j].d = 0;
		} else if (nodes[j].d == 2) {
			nodes[j].d = 3;
		} else {
			nodes[j].d = 2;
		}
		// 바꾼 방향의 위치
		tempy = nodes[j].y + dy[nodes[j].d];
		tempx = nodes[j].x + dx[nodes[j].d];
		if (tempy >= 0 && tempy < N && tempx >= 0 && tempx < N) {
			if (map[tempy][tempx][0] == 0) {
				// 하얀색일 때
				White(map, j, nodes, tempy, tempx);
			} else if (map[tempy][tempx][0] == 1) {
				// 빨간색일 때
				Red(map, j, nodes, tempy, tempx);
			}
		}
	}

	// 하얀색 블록일 때
	static void White(int[][][] map, int j, Node[] nodes, int tempy, int tempx) {
		Node node = nodes[j];
		if (node.prev != null) {
			// 이동하는 체스 말이 중간일때
			// 밑에 있는 체스말과 prev와 연결 끊기
			node.prev.next = null;
		} else {
			// 맨 밑에 있을 때는 모든 체스 말과 함께 이동하기 때문에 map에 아무것도 없다고 표시
			map[nodes[j].y][nodes[j].x][1] = 0;
		}
		node.prev = null;

		if (map[tempy][tempx][1] != 0) {
			// 이동 위치에 체스 말이 있을 때
			// 이동 위치의 체스 말의 제일 위 블록 찾기
			node = nodes[map[tempy][tempx][1]];
			while (node.next != null) {
				node = node.next;
			}
			// 제일 위에 있는 체스 말과 연결
			node.next = nodes[j];
			nodes[j].prev = node;
			node = node.next;
		} else {
			// 아무것도 없을 때
			map[tempy][tempx][1] = j;
		}
		// 이동시킨 블록들의 좌표 수정
		while (node != null) {
			node.y = tempy;
			node.x = tempx;
			node = node.next;
		}
	}

	// 빨간 색일 경우
	static void Red(int[][][] map, int j, Node[] nodes, int tempy, int tempx) {
		Node node = nodes[j];
		if (node.prev != null) {
			// 이동하는 체스 말이 중간일때
			// 밑에 있는 체스말과 prev와 연결 끊기
			node.prev.next = null;
		} else {
			// 맨 밑에 있을 때는 모든 체스 말과 함께 이동하기 때문에 map에 아무것도 없다고 표시
			map[nodes[j].y][nodes[j].x][1] = 0;
		}
		// 이동하는 체스 말 뒤집기
		node.prev = null;
		while (node.next != null) {
			// 이동하는 체스 말의 위치를 이동하는 곳으로 바꾸기
			node.y = tempy;
			node.x = tempx;
			// 연결을 뒤집어서 체스 말 뒤집기
			Node temp = node.next;
			node.next = node.prev;
			node.prev = temp;
			node = temp;
		}
		node.y = tempy;
		node.x = tempx;
		node.next = node.prev;
		node.prev = null;
		if (map[tempy][tempx][1] != 0) {
			// 이동 위치에 체스 말이 있을 때
			// 이동 위치의 체스 말의 제일 위 블록 찾기
			Node temp = nodes[map[tempy][tempx][1]];
			while (temp.next != null) {
				temp = temp.next;
			}
			// 제일 위에 있는 체스 말과 연결
			temp.next = node;
			node.prev = temp;
		} else {
			// 아무것도 없을 때
			map[tempy][tempx][1] = node.idx;
		}
	}

	// 체스 말의 정보와 next, prev를 만들어 양방향 링크드리스트를 구현
	static class Node {
		int idx;
		int y;
		int x;
		int d;
		Node prev;
		Node next;

		Node(int idx, int y, int x, int d) {
			this.idx = idx;
			this.y = y;
			this.x = x;
			this.d = d;
			this.prev = null;
			this.next = null;
		}
	}
}
