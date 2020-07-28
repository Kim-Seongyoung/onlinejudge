package programmers;

public class 방문길이 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String dirs = "LULLLLLLU";
	}
	public int solution(String dirs) {
		int answer = 0;
		boolean[][][] map = new boolean[11][11][4];
		int y=5;
        int x=5;
        for (int i = 0; i < dirs.length(); i++) {
			char c= dirs.charAt(i);
			if(c=='U') {
			
				y++;
				if(y==11) {
					y=10;
				}else {
					if(!map[y-1][x][0]) {
						answer++;
						map[y-1][x][0] = true;
						map[y][x][1] = true;
					}
				}
			}else if(c=='D') {
				y--;
				if(y==-1) {
					y=0;
				}else {
					if(!map[y+1][x][1]) {
						answer++;
						map[y+1][x][1] = true;
						map[y][x][0] = true;
					}
				}
			}else if(c=='R') {
				x++;
				if(x==11) {
					x=10;
				}else {
					if(!map[y][x-1][2]) {
						answer++;
						map[y][x-1][2] = true;
						map[y][x][3] = true;
					}
				}
			}else {
				x--;
				if(x==-1) {
					x=0;
				}else {
					if(!map[y][x+1][3]) {
						answer++;
						map[y][x+1][3] = true;
						map[y][x][2] = true;
					}
				}
			}
			
		}
        return answer;
    }
}
