package programmers;

public class 보행자천국 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	int MOD = 20170805;
    public int solution(int m, int n, int[][] cityMap) {
                int[][][] map = new int[m][n][2];
        for (int i = 0; i < m; i++) {
			if(cityMap[i][0]!=1) {
				map[i][0][0] = 1;
			}else {
				break;
			}
		}
        for (int i = 0; i < n; i++) {
			if(cityMap[0][i]!=1) {
				map[0][i][1]=1;
			}else {
				break;
			}
		}
        for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				if(cityMap[i][j]!=1) {
					if(cityMap[i-1][j]==0) {
						map[i][j][0]=(map[i-1][j][0]+map[i-1][j][1])%MOD;
					}else if(cityMap[i-1][j]==2){
						map[i][j][0]=map[i-1][j][0]%MOD;
					}
					if(cityMap[i][j-1]==0) {
						map[i][j][1] = (map[i][j-1][0]+map[i][j-1][1])%MOD;
					}else if(cityMap[i][j-1]==2){
						map[i][j][1] = map[i][j-1][1]%MOD;
					}
				}
			}
		}
        return (map[m-1][n-1][0]+map[m-1][n-1][1])%MOD;
    }
}
