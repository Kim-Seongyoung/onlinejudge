package programmers;

public class 하노이의탑 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public int[][] solution(int n) {
        int[][][] map = new int[n+1][(int) (Math.pow(2, n)-1)][2];
        map[1][0][0] = 1;
        map[1][0][1] =3;
        for (int i = 2; i <=n; i++) {
			int start = 1;
			int end = 2;
			int remainder = 3;
        	if(i%2!=0) {
				end =3;
				remainder=2;
			}
        	int pivot = 0;
        	for (int j = 0; j < Math.pow(2, i)-1; j++) {
				if(j%2==0) {
					map[i][j][0] = start;
					map[i][j][1] = end;
					start = end;
					end = remainder;
					remainder = map[i][j][0];
				}else {
					map[i][j][0] = map[i-1][pivot][0];
					map[i][j][1] = map[i-1][pivot][1];
					pivot++;
				}
			}
		}
        return map[n];
    }
}
