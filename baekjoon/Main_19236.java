package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_19236 {
	static int[] dy = { 0, -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dx = { 0, 0, -1, -1, -1, 0, 1, 1, 1 };
	static int max;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 물고기의 위치와 방향을 3차원 배열을 통해 하나로 통합
		int[][][] map = new int[4][4][2];
		max = 0;
		for (int i = 0; i < 4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				map[i][j][0] = Integer.parseInt(st.nextToken());
				map[i][j][1] = Integer.parseInt(st.nextToken());
			}
		}
		// DFS
		search(map, 0, 0, 0);
		System.out.println(max);
	}

	static void search(int[][][] map, int y, int x, int sum) {
		// 상어가 범위 밖이나 물고기가 없는 곳으로 이동할 때 종료
		// 합이 가장 클때를 찾는다.
		if (0 > y || y > 3 || 0 > x || x > 3 || map[y][x][0] == 0) {
			max = Math.max(max, sum);
			return;
		} else {
			// map을 직접 사용하면 다시 원래의 값으로 돌리기 힘들기 때문에
			// 따로 사용한 배열을 만든다.
			int[][][] temp = new int[4][4][2];
			// 물고기가 순서대로 움직이기 때문에
			// y값이 -1이면 사라진 물고기
			int[][] arr = new int[17][2];
			for (int i = 0; i < arr.length; i++) {
				arr[i][0] = -1;
			}
			// 배열은 얕은 복사이기 때문에 일일이 복사
			// 복사할 때 물고기들의 위치 저장
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					temp[i][j][0] = map[i][j][0];
					temp[i][j][1] = map[i][j][1];
					arr[temp[i][j][0]][0] = i;
					arr[temp[i][j][0]][1] = j;
				}
			}
			// 상어의 현재 위치에 처리
			arr[map[y][x][0]][0] = -1;
			arr[map[y][x][0]][1] = -1;
			temp[y][x][0] = 0;
			// 상어가 먹은 뒤 물고기들의 움직임
			moveFish(temp, arr, y, x);
			// 현재 상어가 먹은 물고기 번호 합
			int tempSum = sum + map[y][x][0];
			int tempy = y;
			int tempx = x;
			// 4x4로 고정이기 때문에 최대 3번 갈 수 있다
			for (int i = 0; i < 3; i++) {
				tempy += dy[map[y][x][1]];
				tempx += dx[map[y][x][1]];
				search(temp, tempy, tempx, tempSum);
			}
		}
	}

	static void moveFish(int[][][] map, int[][] arr, int y, int x) {
		// 번호 순으로 움직이기 때문
		for (int i = 1; i < 17; i++) {
			// y 좌표가 -1이면 죽은 물고기
			if (arr[i][0] != -1) {
				int d = map[arr[i][0]][arr[i][1]][1];
				while (true) {
					// 현재 물고기가 가는 좌표
					int tempy = arr[i][0] + dy[d];
					int tempx = arr[i][1] + dx[d];
					if (tempy >= 0 && tempy < 4 && tempx >= 0 && tempx < 4) {
						// 갈 수 있고 다른 물고기가 없을때
						if (map[tempy][tempx][0] == 0) {
							// 상어가 있을 때는 방향만 전환
							if (tempy == y && tempx == x) {
								d++;
								if (d == 9) {
									d = 1;
								}
							// 상어가 없으면 이동
							} else {
								// 기존 위치는 0으로 초기화
								map[arr[i][0]][arr[i][1]][0] = 0;
								map[arr[i][0]][arr[i][1]][1] = 0;
								map[tempy][tempx][0] = i;
								map[tempy][tempx][1] = d;
								break;
							}
						// 다른 물고기가 있을 때
						} else {
							// 서로 위치를 바꾼다
							arr[map[tempy][tempx][0]][0] = arr[i][0];
							arr[map[tempy][tempx][0]][1] = arr[i][1];
							map[arr[i][0]][arr[i][1]][0] = map[tempy][tempx][0];
							map[arr[i][0]][arr[i][1]][1] = map[tempy][tempx][1];
							map[tempy][tempx][0] = i;
							map[tempy][tempx][1] = d;
							break;
						}
					// 갈 수 없는 좌표일 때 방향만 전환
					} else {
						d++;
						if (d == 9) {
							d = 1;
						}
					}
				}
			}
		}
	}
}
