package programmers;

import java.util.Comparator;
import java.util.PriorityQueue;

public class 가장큰수 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] numbers= {0};
		System.out.println(solution(numbers));
	}
	static public String solution(int[] numbers) {
        PriorityQueue<String> q= new PriorityQueue<>(new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return Integer.parseInt(o2+o1)-Integer.parseInt(o1+o2);
			}

			
		});
        for (int i = 0; i < numbers.length; i++) {
			q.add(numbers[i]+"");
		}
        StringBuilder sb = new StringBuilder();
        while(!q.isEmpty()) {
        	sb.append(q.poll());
        }
        if(sb.charAt(0)=='0') {
        	return 0+"";
        }
        return sb.toString();
    }
}
