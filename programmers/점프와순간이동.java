package programmers;

public class 점프와순간이동 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	static public int solution(int n) {
        int answer = 0;
        while(n!=0) {
        	if(n%2!=0) {
        		answer++;
        	}
        	n/=2;
        }
        return answer;
    }
}
