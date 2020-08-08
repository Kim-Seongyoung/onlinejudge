package programmers;

import java.util.LinkedList;

public class 다리를지나는트럭 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int bridge_length = 5;
		int weight = 5;
		int[] truck_weights = {2,2,2,2,1,1,1,1,1};
		System.out.println(solution(bridge_length, weight, truck_weights));
	}
	static public int solution(int bridge_length, int weight, int[] truck_weights) {
        int time = 1;
        int sum = 0;
        LinkedList<Car> cars = new LinkedList<>();
        int i = 0;
        while(i<truck_weights.length) {
        	if(sum+truck_weights[i] <=weight) {
				cars.add(new Car(truck_weights[i],time));
				sum += truck_weights[i];
				time++;
				i++;
			}else {
				Car car = cars.poll();
				sum -= car.w;
				time = Math.max(car.start+bridge_length,time);
			}
			
		}
        Car car = cars.pollLast();
        return car.start+bridge_length;
    }
	static class Car{
		int w;
		int start;
		Car(int w , int start){
			this.w=w;
			this.start=start;
		}
	}
}
