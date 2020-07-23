package programmers;

public class 기지국설치 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public int solution(int n, int[] stations, int w) {
		//40분
	       int answer = 0;
			int l = 2*w+1;
			int curr = 1;
			int pivot = 0;
			while (curr <= n) {
				if (pivot < stations.length) {
					if (curr < stations[pivot] - w) {
						answer++;
						curr += l;
					} else {
						curr = stations[pivot]+w + 1;
						pivot++;
					}
				}else {
					int temp = (n-curr)+1;
					int result = temp/l;
					if(temp%l!=0) {
						result++;
					}
					answer += result;
					break;
				}
			}
	        return answer;
	    }
}
