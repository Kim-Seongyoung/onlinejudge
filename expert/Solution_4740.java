package expert;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution_4740 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int iter = Integer.parseInt(br.readLine());
		int[] dy = { 0, 0, -1, 1 };
		int[] dx = { -1, 1, 0, 0 };
		LinkedList<Point> q = new LinkedList<>();
		LinkedList<Point> store = new LinkedList<>();
		LinkedList<Point> tempStore = new LinkedList<>();
		for (int i = 1; i <= iter; i++) {
			// 입력 부분
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int Q = Integer.parseInt(st.nextToken());
			char[][] map = new char[N][M];
			for (int j = 0; j < N; j++) {
				map[j] = br.readLine().toCharArray();
			}
			// 각 버튼 클릭시
			for (int j = 0; j < Q; j++) {
				String s = br.readLine();
				if (s.charAt(0) == 'U') {
					boolean flag = true;
					// 한 줄을 추가 할 수 있는지 판단
					for (int k = 0; k < M; k++) {
						// 제일 위에 블록이 있는지 판단
						if (map[0][k] != '#') {
							flag = false;
							break;
						}
					}
					// 한 줄이 추가 가능할때
					if (flag) {
						// 한줄씩 복사
						for (int k = 0; k < N - 1; k++) {
							map[k] = map[k + 1].clone();
						}
						// 맨 아래는 새롭게 들어오는 블록 추가
						map[N - 1] = s.substring(2).toCharArray();
						// 빈 공간 삭제
						for (int k = 0; k < M; k++) {
							if(map[N-1][k]=='#') {
								for (int l = N-1; l >0; l--) {
									map[l][k] = map[l-1][k];
									map[l-1][k] = '#';
								}
							}
						}
					}
				} else if (s.charAt(0) == 'L') {
					// 왼쪽으로 밀기
					for (int k = 0; k < N; k++) {
						// 현재 행 중에서 빈 곳 중 가장 왼쪽 위치
						int pivot = 0;
						for (int l = 0; l < M; l++) {
							if (map[k][l] != '#') {
								char c = map[k][l];
								map[k][l] = '#';
								map[k][pivot] = c;
								pivot++;
							}
						}
					}
				} else if (s.charAt(0) == 'R') {
					// 오른쪽으로 밀기
					for (int k = 0; k < N; k++) {
						// 현재 행 중에서 빈 곳 중 가장 오른쪽 위치
						int pivot = M - 1;
						for (int l = M - 1; l >= 0; l--) {
							if (map[k][l] != '#') {
								char c = map[k][l];
								map[k][l] = '#';
								map[k][pivot] = c;
								pivot--;
							}
						}
					}
				} else {
					// 방문처리를 위한 배열
					boolean[][] v = new boolean[N][M];
					// 블록 집합 중 최대 크기 
					int max = 0;
					// 최대 크기 블록 집합들의 위치 저장
					store.clear();
					for (int k = 0; k < N; k++) {
						for (int l = 0; l < M; l++) {
							// 블록이여서 방문하지 않은 블록 탐색
							if (map[k][l] != '#' && !v[k][l]) {
								// 시작하는 블록의 색깔
								char c = map[k][l];
								// 인접한 같은 색깔의 블록 리스트
								q.clear();
								q.add(new Point(k, l));
								v[k][l] = true;
								tempStore.clear();
								while (!q.isEmpty()) {
									Point p = q.poll();
									// 현재 사용한 블록들 저장
									tempStore.add(p);
									// 인접한 블록 검색
									for (int o = 0; o < 4; o++) {
										int tempy = p.y + dy[o];
										int tempx = p.x + dx[o];
										// 방문하지 않았고 같은 색 블록 탐색
										if (tempy >= 0 && tempy < N && tempx >= 0 && tempx < M && !v[tempy][tempx]
												&& map[tempy][tempx] == c) {
											v[tempy][tempx] = true;
											q.add(new Point(tempy, tempx));
										}
									}
								}
								// 최대 블록 크기와 현재 진행한 블록 집합의 크기가 같거나 클 때
								if (max <= tempStore.size()) {
									// 클 때
									if (max < tempStore.size()) {
										// 최대 값 변경
										max = tempStore.size();
										// 기존에 저장한 블록 초기화
										store.clear();
									}
									// 현재 블록들 저장
									store.addAll(tempStore);
								}
							}
						}
					}
					// 저장된 블록들 삭제
					while(!store.isEmpty()) {
						Point p = store.poll();
						map[p.y][p.x] = '#';
					}
					// 빈 공간 삭제
					for (int k = 0; k < map[0].length; k++) {
						// 현재 행 중에서 빈 곳 중 가장 밑에 있는 위치
						int pivot = map.length-1;
						for (int l = map.length-1; l >=0; l--) {
							if(map[l][k]!='#') {
								char c = map[l][k];
								map[l][k] = '#';
								map[pivot][k] = c;
								pivot--;
							}
						}
					}
				}
			}
			// 출력 저장
			bw.write("#"+i);
			bw.newLine();
			for (int j = 0; j < map.length; j++) {
				bw.write(map[j]);
				bw.newLine();
			}
			bw.newLine();
		}
		bw.flush();
		bw.close();
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
