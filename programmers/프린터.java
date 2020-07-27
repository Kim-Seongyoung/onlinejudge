package programmers;

import java.util.Arrays;
import java.util.LinkedList;

public class 프린터 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public int solution(int[] priorities, int location) {
        int answer = 0;
        LinkedList<Point> q = new LinkedList<>();
        for (int i = 0; i < priorities.length; i++) {
        	q.add(new Point(i,priorities[i]));
        }
        Arrays.sort(priorities);
        int pivot = priorities.length-1;
        while(!q.isEmpty()) {
        	Point p = q.poll();
        	if(priorities[pivot] == p.prior) {
        		answer++;
        		if(p.idx==location) {
        			break;
        		}
        		pivot--;
        	}else {
        		q.add(p);
        	}
        }
        return answer;
    }
	class Point{
		int idx;
		int prior;
		Point(int idx, int prior){
			this.idx=idx;
			this.prior=prior;
		}
	}
}
