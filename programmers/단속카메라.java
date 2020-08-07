package programmers;
import java.util.Comparator;
import java.util.PriorityQueue;
public class 단속카메라 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public int solution(int[][] routes) {
        int answer = 1;
        PriorityQueue<Car> pq = new PriorityQueue<>(new Comparator<Car>() {

			@Override
			public int compare(Car o1, Car o2) {
				if(o1.end<o2.end) {
					return o1.end-o2.end;
				}else if(o1.end==o2.end) {
					return o1.start-o2.start;
				}else {
					return o1.end-o2.end;
				}
			}
		});
        for (int i = 0; i < routes.length; i++) {
			pq.add(new Car(routes[i][0],routes[i][1]));
		}
        int pivot = pq.poll().end;
        while(!pq.isEmpty()) {
        	Car car = pq.poll();
        	if(car.start>pivot) {
        		pivot= car.end;
        		answer++;
        	}
        }
        return answer;
    }
	class Car{
		int start;
		int end;
		Car(int start,int end){
			this.start= start;
			this.end=end;
		}
	}
}
