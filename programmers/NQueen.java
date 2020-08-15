package programmers;

public class NQueen {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
	public int solution(int n) {
        boolean[][] v = new boolean[n][n];
        return search(0, n, v);
    }
	int search(int y, int n, boolean[][] v) {
		if(y==n) {
			return 1;
		}else {
			int result = 0;
			boolean[][] temp = new boolean[n][n];
			for (int i = 0; i < n; i++) {
				if(!v[y][i]) {
					for (int k = 0; k < n; k++) {
						for (int j = 0; j < n; j++) {
							temp[k][j] = v[k][j];
						}
					}
					int left = i-1;
					int rigth = i+1;
					for (int k = y+1; k < n; k++) {
						temp[k][i] = true;
						if(left>=0) {
							temp[k][left] =true;
							left--;
						}
						if(rigth<n) {
							temp[k][rigth] = true;
							rigth++;
						}
					}
					result+= search(y+1, n, temp);
				}
			}
			return result;
		}
	}
}
