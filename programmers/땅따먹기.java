package programmers;

public class 땅따먹기 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] land = {{1,2,3,5},{5,6,7,8},{4,3,2,1}};
		solution(land);
	}
	static int solution(int[][] land) {
        int answer = 0;
        int[][] map = new int[land.length][4];
        for (int i = 0; i < 4; i++) {
			map[0][i] = land[0][i];
		}
        for (int i = 1; i < land.length; i++) {
			for (int j = 0; j < 4; j++) {
				int max = 0;
				for (int k = 0; k < 4; k++) {
					if(j!=k) {
						max = Math.max(max, land[i][j]+map[i-1][k]);
					}
				}
				map[i][j] = max;
			}
		}
        for (int i = 0; i < 4; i++) {
			answer = Math.max(answer, map[land.length-1][i]);
		}
        return answer;
    }
}
