package programmers;

public class 기둥과보설치 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n =5;
		int[][] build_frame = {{1,0,0,1},{1,1,1,1},{2,1,0,1},{2,2,1,1},{5,0,0,1},{5,1,0,1},{4,2,1,1},{3,2,1,1}};
		solution(n, build_frame);
	}

	static public int[][] solution(int n, int[][] build_frame) {
		int[][] answer = {};
		boolean[][][] map = new boolean[n + 2][n + 2][4];
		int count = 0;
		for (int i = 0; i < build_frame.length; i++) {
			int x = build_frame[i][0]+1;
			int y = build_frame[i][1];
			int a = build_frame[i][2];
			int b = build_frame[i][3];
			if (b == 0) {
				boolean check = true;
				if (a == 0) {
					int tempy = y+1;
					if(map[tempy][x][0]) {
						if(!map[tempy][x][2] && !map[tempy][x][3]) {
							check = false;
						}
					}
					if(check && map[tempy][x][2]) {
						if(!(map[tempy][x-1][1] || (map[tempy][x-1][2] && map[tempy][x][3]))) {
							check= false;
						}
					}
					if(check && map[tempy][x][3]) {
						if(!(map[tempy][x+1][1] || (map[tempy][x+1][3] && map[tempy][x][2]))) {
							check= false;
						}
					}
					if(check) {
						map[y][x][0] = false;
						map[tempy][x][1] = false;
						count--;
					}
				} else {
					if(map[y][x][0]) {
						if(!(map[y][x][1] || map[y][x][2])) {
							check = false;
						}
					}
					if(check&& map[y][x][2]) {
						if(!(map[y][x][1]|| map[y][x-1][1])) {
							check = false;
						}
					}
					if(check &&map[y][x+1][0]) {
						if(!(map[y][x+1][1] || map[y][x+1][3])) {
							check = false;
						}
					}
					if(check && map[y][x+1][3]) {
						if(!(map[y][x+1][1] || map[y][x+2][1])) {
							check = false;
						}
					}
					if(check) {
						map[y][x][3] = false;
						map[y][x+1][2] = false;
						count--;
					}
				}
			} else {
				if (a == 0) {
					if (y == 0) {
						map[y][x][0] = true;
						map[y + 1][x][1] = true;
						count++;
					} else {
						if (map[y][x][1] || map[y][x][2] || map[y][x][3]) {
							map[y][x][0] = true;
							map[y + 1][x][1] = true;
							count++;
						}
					}
				} else {
					if (map[y][x][1] || map[y][x + 1][1] || (map[y][x][2] && map[y][x + 1][3])) {
						map[y][x][3] = true;
						map[y][x + 1][2] = true;
						count++;
					}
				}
			}
		}
		answer = new int[count][3];
		int pivot = 0;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				if(map[j][i][0]) {
					answer[pivot][0] = i-1;
					answer[pivot][1] = j;
					answer[pivot][2] = 0;
					pivot++;
				}
				if(map[j][i][3]) {
					answer[pivot][0] = i-1;
					answer[pivot][1] = j;
					answer[pivot][2] = 1;
					pivot++;
				}
			}
		}
		return answer;
	}
}
