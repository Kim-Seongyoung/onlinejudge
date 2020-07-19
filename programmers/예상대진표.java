package programmers;

public class 예상대진표 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public int solution(int n, int a, int b)
    {
        int answer = 1;
        int min = Math.min(a, b);
        int max = Math.max(a, b);
        while(true) {
        	if(min%2==1) {
        		min = (min+1)/2;
        	}else {
        		min = min/2;
        	}
        	if(max%2==1) {
        		max = (max+1)/2;
        	}else {
        		max = max/2;
        	}
        	if(max==min) {
        		break;
        	}else {
        		answer++;
        	}
        }
        return answer;
    }
}
