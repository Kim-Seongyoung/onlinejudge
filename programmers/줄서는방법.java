package programmers;

public class 줄서는방법 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for (int j = 1; j <= 2; j++) {
			int[] a = solution(2, j);
			for (int i = 0; i < a.length; i++) {
				System.out.print(a[i] + " ");
			}
			System.out.println();
		}

	}

	static public int[] solution(int n, long k) {
		int[] answer = new int[n];
		long[] arr = new long[n + 1];
		boolean[] v = new boolean[n];
		arr[0] = 1;
		for (int i = 1; i <= n; i++) {
			arr[i] = arr[i - 1] * i;
		}
		k--;
		int pivot = 0;
		while (k != 0) {
			long temp = arr[n] / n;
			int count = 0;
			int i = 0;
			while (true) {
				if (!v[i]) {
					if (count == k / temp) {
						answer[pivot] = i + 1;
						v[i] = true;
						break;
					}
					count++;
				}
				i++;
				i=i%v.length;
			}
			k -= k / temp*temp ;
			n--;
			pivot++;
		}
		for (int i = pivot; i < answer.length; i++) {
			for (int j = 0; j < v.length; j++) {
				if (!v[j]) {
					v[j] = true;
					answer[i] = j + 1;
					break;
				}
			}
		}
		return answer;
	}
}
