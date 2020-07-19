package programmers;

import java.util.Collections;
import java.util.LinkedList;

public class 구명보트 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] people = {70,50,80,50};
		int limit = 100;
		solution(people, limit);
	}
	static public int solution(int[] people, int limit) {
        int answer = 0;
        LinkedList<Integer> q = new LinkedList<Integer>();
        for (int i = 0; i < people.length; i++) {
			q.add(people[i]);
		}
        Collections.sort(q);
        while(!q.isEmpty()) {
        	int temp = q.pollLast();
        	if(!q.isEmpty()) {
        		if(temp+q.peek()<=limit) {
        			q.poll();
        		}
        	}
        	answer++;
        }
        return answer;
    }
}
