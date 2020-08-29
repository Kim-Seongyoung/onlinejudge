package programmers;

public class 올바른괄호의갯수 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int solution(int n) {
		return search(n*2,0,0,0);
	}

	int search(int n, int cnt, int sum, int count) {
		if (n == cnt) {
			return 1;
		} else {
			int result=0;
			if (count < n / 2) {
				result = search(n, cnt + 1, sum + 1, count + 1);
			}
			if (sum >= 1) {
				result +=search(n, cnt + 1, sum - 1, count);
			}
			return result;
		}
	}
}
