package programmers;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class 이중우선순위큐 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] operations = {"I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"};
		System.out.println(solution(operations));
	}
	static public int[] solution(String[] operations) {
        int[] answer = new int[2];
        LinkedList<Point> arr = new LinkedList<>();
        PriorityQueue<Point> min = new PriorityQueue<>(new Comparator<Point>() {

			@Override
			public int compare(Point o1, Point o2) {
				// TODO Auto-generated method stub
				return o1.num-o2.num;
			}
		});
        PriorityQueue<Point> max = new PriorityQueue<>(new Comparator<Point>() {

			@Override
			public int compare(Point o1, Point o2) {
				// TODO Auto-generated method stub
				return o2.num-o1.num;
			}
		});
        for (int i = 0; i < operations.length; i++) {
			if(operations[i].charAt(0)=='I') {
				arr.add(new Point(Integer.parseInt(operations[i].substring(2))));
				min.add(arr.peekLast());
				max.add(arr.peekLast());
			}else {
				if(operations[i].charAt(2)=='-') {
					while(!min.isEmpty()) {
						Point p = min.poll();
						if(p.flag) {
							p.flag=false;
							break;
						}
					}
				}else {
					while(!max.isEmpty()) {
						Point p = max.poll();
						if(p.flag) {
							p.flag=false;
							break;
						}
					}
				}
			}
		}
        while(!max.isEmpty()) {
        	Point p =max.poll();
        	if(p.flag) {
        		answer[0] = p.num;
        		break;
        	}
        }
        while(!min.isEmpty()) {
        	Point p =min.poll();
        	if(p.flag) {
        		answer[1] = p.num;
        		break;
        	}
        }
        return answer;
    }
	static class Point{
		int num;
		boolean flag;
		Point(int num){
			this.num= num;
			this.flag=true;
		}
	}
}
