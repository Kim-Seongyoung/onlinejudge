package programmers;

import java.util.Arrays;

public class 거스름돈 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] money = { 2, 3 };
		System.out.println(solution(5, money));
	}

	static public int solution(int n, int[] money) {
		long[] v = new long[n + 1];
		Arrays.sort(money);
		v[0] = 1;
		for (int i = 0; i < money.length; i++) {
			// 동전의 종류 i 개로 j를 만들 수 있는 경우
			for (int j = money[i]; j <= n; j++) {
				v[j] += v[j - money[i]];
			}
		}
		return (int) (v[n] % 1000000007);
	}
}
