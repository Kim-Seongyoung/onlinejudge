package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_19237 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		// map 배열
		Point[][] map = new Point[N][N];
		// 상어 리스트
		ArrayList<Shark> sharks = new ArrayList<>();
		for (int i = 0; i <= M; i++) {
			sharks.add(new Shark(i));
		}
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				// map 정보
				map[i][j] = new Point(0, 0);
				// 0으로 초기화하면 나중에 상어가 갈 수 있는지 확인하는 조건을 추가해야 하기 때문
				map[i][j].smell = K * -1;
				int idx = Integer.parseInt(st.nextToken());
				// 상어의 위치 및 map 정보
				if (idx != 0) {
					sharks.get(idx).y = i;
					sharks.get(idx).x = j;
					map[i][j].idx = idx;
					map[i][j].smell = 0;
					map[i][j].sharks.add(idx);
				}
			}
		}
		// 방향에 대한 정보 저장
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= M; i++) {
			sharks.get(i).d = Integer.parseInt(st.nextToken()) - 1;
		}
		int[] dy = { -1, 1, 0, 0 };
		int[] dx = { 0, 0, -1, 1 };
		for (int i = 1; i <= M; i++) {
			for (int j = 0; j < 4; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < 4; k++) {
					sharks.get(i).pivot_arr[j][k] = Integer.parseInt(st.nextToken()) - 1;
				}
			}
		}
		boolean flag = true;
		// 상어 이동
		for (int i = 1; i <= 1000; i++) {
			for (int j = 1; j < sharks.size(); j++) {
				// null이면 죽은 상어
				if (sharks.get(j) != null) {
					int y = -1;
					int x = -1;
					int d = 0;
					// 상어가 갈 수 있는 곳, 우선순위 순으로
					for (int k = 0; k < 4; k++) {
						int tempy = sharks.get(j).y + dy[sharks.get(j).pivot_arr[sharks.get(j).d][k]];
						int tempx = sharks.get(j).x + dx[sharks.get(j).pivot_arr[sharks.get(j).d][k]];
						if (tempy >= 0 && tempy < N && tempx >= 0 && tempx < N) {
							// 냄새가 없어서 바로 갈 수 있는 곳, 이 조건이 가장 우선 순위가 높음
							if (map[tempy][tempx].smell + K < i) {
								y = tempy;
								x = tempx;
								d = sharks.get(j).pivot_arr[sharks.get(j).d][k];
								break;
							}
							// 자신의 냄새가 있는 곳 중 우선순위가 높은 곳
							else if (map[tempy][tempx].idx == j && y == -1) {
								y = tempy;
								x = tempx;
								d = sharks.get(j).pivot_arr[sharks.get(j).d][k];
							}
						}
					}
					// 전 위치에서 상어를 제거
					map[sharks.get(j).y][sharks.get(j).x].sharks.poll();
					// 상어 정보 업데이트
					sharks.get(j).y = y;
					sharks.get(j).x = x;
					sharks.get(j).d = d;
					// 현재 위치에 상어 저장
					map[y][x].sharks.add(j);
				}
			}
			// 같은 곳으로 온 상어 제거
			for (int j = 1; j < sharks.size(); j++) {
				if (sharks.get(j) != null) {
					// 번호가 작은 상어가 살아 남기 때문에 그 외의 상어는 제거
					// 맨 앞 말고는 제거
					while (map[sharks.get(j).y][sharks.get(j).x].sharks.size() != 1) {
						sharks.set(map[sharks.get(j).y][sharks.get(j).x].sharks.removeLast(), null);
						M--;
					}
					// map에 상어 냄새 정보 저장
					map[sharks.get(j).y][sharks.get(j).x].smell = i;
					map[sharks.get(j).y][sharks.get(j).x].idx = j;

				}
			}
			// 1번만 남았을 때 종료, 번호가 작은 상어가 살아 남기 때문에 1마리 남았을 때가 번호가 1이다
			if (M == 1) {
				flag = false;
				System.out.println(i);
				break;
			}
		}
		// 1000초를 넘었을 때
		if (flag) {
			System.out.println(-1);
		}
	}
	// Map을 위한 클래스
	static class Point {
		// 냄새를 뿌린 시간
		int smell;
		// 냄새를 뿌린 상어
		int idx;
		// 상어들의 번호
		LinkedList<Integer> sharks;

		Point(int smell, int idx) {
			this.smell = smell;
			this.idx = idx;
			this.sharks = new LinkedList<>();
		}
	}

	static class Shark {
		// (y,x) 위치와 d 방향, 상어의 번호 idx, pivot_arr 상어의 움직임 우선순위
		int y;
		int x;
		int d;
		int idx;
		int[][] pivot_arr;

		Shark(int idx) {
			this.y = 0;
			this.x = 0;
			this.d = 0;
			this.idx = idx;
			pivot_arr = new int[4][4];
		}
	}
}
