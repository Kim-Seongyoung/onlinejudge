package programmers;

public class H_Index {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] citations= {5,5,5,5};
		solution(citations);
	}
	static public int solution(int[] citations) {
        int answer = 0;
        int[] arr = new int[10001];
        int[] sum = new int[10001];
        for (int i = 0; i < citations.length; i++) {
			arr[citations[i]]++;
		}
        sum[10000]=arr[10000];
        for (int i = 9999; i >=0; i--) {
			sum[i] =arr[i]+ sum[i+1];
		}
        for (int i = 0; i <=10000; i++) {
			if(i>sum[i]) {
				return i-1;
			}
		}
        return answer;
    }
}
