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
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[][][] map = new int[N][N][2];
		Node[] nodes = new Node[K + 1];
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
		for (int i = 1; i <= 1000; i++) {
			for (int j = 1; j <= K; j++) {
				int tempy = nodes[j].y + dy[nodes[j].d];
				int tempx = nodes[j].x + dx[nodes[j].d];
				if (tempy >= 0 && tempy < N && tempx >= 0 && tempx < N) {
					if (map[tempy][tempx][0] == 0) {
						White(map, j, nodes, tempy, tempx);
					} else if (map[tempy][tempx][0] == 1) {
						Red(map, j, nodes, tempy, tempx);
					} else {
						Blue(map, j, nodes, tempy, tempx);
					}
				} else {
					Blue(map, j, nodes, tempy, tempx);
				}
				int count =0;
				Node node = nodes[map[nodes[j].y][nodes[j].x][1]];
				while(node!=null) {
					count++;
					node = node.next;
					if(count==4) {
						flag = false;
						System.out.println(i);
						break;
					}
				}
				if(!flag) {
					break;
				}
			}
			if(!flag) {
				break;
			}
		}
		if (flag) {
			System.out.println(-1);
		}
	}

	static void Blue(int[][][] map, int j, Node[] nodes, int tempy, int tempx) {
		if (nodes[j].d == 0) {
			nodes[j].d = 1;
		} else if (nodes[j].d == 1) {
			nodes[j].d = 0;
		} else if (nodes[j].d == 2) {
			nodes[j].d = 3;
		} else {
			nodes[j].d = 2;
		}
		tempy = nodes[j].y + dy[nodes[j].d];
		tempx = nodes[j].x + dx[nodes[j].d];
		if (tempy >= 0 && tempy < N && tempx >= 0 && tempx < N) {
			if (map[tempy][tempx][0] == 0) {
				White(map, j, nodes, tempy, tempx);
			} else if (map[tempy][tempx][0] == 1) {
				Red(map, j, nodes, tempy, tempx);
			}
		}
	}

	static void White(int[][][] map, int j, Node[] nodes, int tempy, int tempx) {
		Node node = nodes[j];
		if(node.prev!=null) {
			node.prev.next = null;
		}else {
			map[nodes[j].y][nodes[j].x][1] = 0;
		}
		node.prev=null;
		if (map[tempy][tempx][1] != 0) {
			node = nodes[map[tempy][tempx][1]];
			while (node.next != null) {
				node = node.next;
			}
			node.next = nodes[j];
			nodes[j].prev = node;
			node = node.next;
		}else {
			map[tempy][tempx][1] = j;
		}
		while (node != null) {
			node.y = tempy;
			node.x = tempx;
			node = node.next;
		}
	}

	static void Red(int[][][] map, int j, Node[] nodes, int tempy, int tempx) {
		Node node = nodes[j];
		if(node.prev!=null) {
			node.prev.next = null;
		}else {
			map[nodes[j].y][nodes[j].x][1] = 0;
		}
		node.prev=null;
		while (node.next != null) {
			node.y = tempy;
			node.x = tempx;
			Node temp = node.next;
			node.next = node.prev;
			node.prev = temp;
			node = temp;
		}
		node.y=tempy;
		node.x=tempx;
		node.next = node.prev;
		node.prev = null;
		if (map[tempy][tempx][1] != 0) {
			Node temp = nodes[map[tempy][tempx][1]];
			while (temp.next != null) {
				temp = temp.next;
			}
			temp.next = node;
			node.prev = temp;
		}else {
			map[tempy][tempx][1] = node.idx;
		}
	}

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
