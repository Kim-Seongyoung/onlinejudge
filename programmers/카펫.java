package programmers;

public class 카펫 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		solution(18,6);
	}
	static public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        int sum = brown+yellow;
        for (int i =1; i <=(int) Math.sqrt(sum); i++) {
			if(sum%i==0) {
				int j = sum/i;
				if(brown >= j*2+i*2-4) {
					answer[0] = j;
					answer[1] = i;
					break;
				}
			}
		}
        
        return answer;
    }
}
