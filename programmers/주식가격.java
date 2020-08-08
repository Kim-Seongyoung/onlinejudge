package programmers;

public class 주식가격 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        for (int i = 0; i < prices.length; i++) {
        	answer[i] = 0;
			for (int j = i+1; j < answer.length; j++) {
				answer[i]++;
				if(prices[i] >prices[j]) {
					break;
				}
			}
		}
        return answer;
    }
}
