package programmers;

public class 삼xn타일링 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		long[] arr = new long[5001];
//		for (int i = 0; i <=5000; i++) {
//			arr[i] = solution(i);
//		}
		System.out.println(solution(500));
	}
	static public int solution(int n) {
        long[] arr = new long[5001];
        arr[2] = 3L;
        arr[4] = 11L;
        long temp;
        for (int i = 6; i <= n; i=i+2) {
        	temp = arr[i-2]*4 - arr[i-4];
        	arr[i] = temp>0 ? temp %1000000007 : temp +1000000007;
		}
        return (int) arr[n];
    }
}
