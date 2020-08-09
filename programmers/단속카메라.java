package programmers;
import java.util.Comparator;
import java.util.PriorityQueue;
public class 단속카메라 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public int solution(int[][] routes) {
        // 가장 왼쪽에 있는 나간 지점 순으로 정렬 후 같으면 가장 왼쪽에 있는 들어온 지점순으로 정렬
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
        // 가장 왼쪽에 있는 나간 지점에 카메라 설치
        int pivot = pq.poll().end;
        int answer = 1;
        while(!pq.isEmpty()) {
        	Car car = pq.poll();
        	// 현재 설치된 카메라 중 가장 오른쪽에 있는 지점보다 더 오른쪽 지점에서 들어 차량 검색
        	if(car.start>pivot) {
        		// 그 차량의 나간 지점에 카메라 설치
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
