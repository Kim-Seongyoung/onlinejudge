package programmers;

public class 최고의집합 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		solution(2, 9);
	}
	static public int[] solution(int n, int s) {
        int[] answer = {};
        int pivot= s/n;
        int remainder = s%n;
        if(pivot==0){
            answer = new int[1];
            answer[0] = -1;
        }else{
            answer = new int[n];
            for (int i = 0; i < n-remainder; i++) {
				answer[i] = pivot;
			}
            for (int i = n-remainder; i < n; i++) {
				answer[i] = pivot+1;
			}
        }
        return answer;
    }
}
