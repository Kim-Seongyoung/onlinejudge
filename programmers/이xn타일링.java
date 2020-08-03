package programmers;

public class 이xn타일링 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	 public int solution(int n) {
	        int[] arr = new int[n+1];
	        arr[1]=1;
	        arr[2]=2;
	        for (int i = 3; i < arr.length; i++) {
				arr[i] = (arr[i-1]+arr[i-2])%1000000007;
			}
	        return arr[n];
	    }
}
