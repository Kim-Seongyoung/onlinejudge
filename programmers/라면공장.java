package programmers;

import java.util.Comparator;
import java.util.PriorityQueue;

public class 라면공장 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int stock =4;
		int[] dates = {4,10,15};
		int[] supplies = {20,5,10};
		int k = 30;
		System.out.println(solution(stock, dates, supplies, k));
	}
	static public int solution(int stock, int[] dates, int[] supplies, int k) {
        int answer = 0;
        PriorityQueue<Integer> q = new PriorityQueue<Integer>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o2.intValue()-o1.intValue();
			}
		});
        
        int pivot_dates = 0;
        // 며칠까지 버틸 수 있는지
        int sum = stock;
        while(true) {
        	// 다시 밀가루가 공급되는 날보다 커지면 종료
        	if(sum>=k) {
        		break;
        	}
        	while(pivot_dates<dates.length) {
        		// 최대 버틸 수 있는 날 전까지 공급 받을 수 있는 날 검사
        		if(dates[pivot_dates]<=sum) {
        			// 공급 받을 수 있는 양 저장
        			q.add(supplies[pivot_dates]);
        			pivot_dates++;
        		}else {
        			break;
        		}
        	}
        	// 최대 버틸 수 있는 날까지 최대의 공급량을 더한다.
        	sum += q.poll();
        	answer++;
        }
        return answer;
    }
}
