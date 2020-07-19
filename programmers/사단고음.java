package programmers;

public class 사단고음 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = new int[500];
		for (int i = 5; i < arr.length; i++) {
			arr[i] = solution(i);
		}
		System.out.println(solution(2147483647));
	}

	static int answer;

	static public int solution(int n) {
		answer = 0;
		long temp = 3;
		int i = 1;
		long min = 1;
		long max = 1;
		while (true) {
			if (n < temp) {
				break;
			}
			min = temp + i * 2;
			max = max * 3 + 2;
			temp *= 3;
			i++;
		}
		if (n % 2 == 1 && min <= n && n <= max) {
			i = i - 2;
			combi(n - 2, i, 0, 3, 0, i * 2);
			return answer;
		} else {
			return 0;
		}
		
	}

//	static void combi(int n, int cnt, int size, int sum, int count) {
//		if (sum + count > n) {
//			return;
//		}
//		if (n == sum + count) {
//			answer++;
//		} else {
//			combi(n, cnt + 1, size, sum * 3, count + 2);
//			combi(n, cnt + 1, size, (sum + 1)*3, count + 1);
//			combi(n, cnt + 1, size, (sum + 2)*3, count);
//		}
//	}
	static void combi(int n, int size, int cnt, long sum, int count, int plus) {
		if (sum + plus > n || plus < 0) {
			return;
		}
		if (size == cnt) {
			if (sum + plus == n) {
				answer++;
			}
		} else {
			if (count == 2) {
				combi(n, size, cnt + 1, sum * 3, 0, plus);
			} else {
				combi(n, size, cnt + 1, sum * 3, 0, plus);
				combi(n, size, cnt, sum + 1, 1, plus - 1);
			}
		}
	}
}
