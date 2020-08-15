package programmers;

public class 멀리뛰기 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public long solution(int n) {
		long[] arr = new long[n + 1];
		arr[1] = 1;
		if (n >= 2) {
			arr[2] = 2;
			for (int i = 3; i < arr.length; i++) {
				arr[i] = arr[i-1]+arr[i-2]%1234567;
			}
		}
		return arr[n];
	}
}
