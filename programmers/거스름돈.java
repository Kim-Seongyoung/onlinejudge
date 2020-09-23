package programmers;

public class 거스름돈 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public int solution(int n, int[] money) {
        long[] v = new long[n + 1];
		Arrays.sort(money);
		v[0]=1;
		for (int i = money[0]; i <=n; i+=money[0]) {
			v[i] = 1;
		}
		for (int i = 1; i < money.length; i++) {
			for (int j = 0; j <=n; j++) {
				if(j>=money[i]) {
					v[j]+=v[j-money[i]];
				}
			}
		}
		return (int) (v[n]%1000000007);
    }
}
