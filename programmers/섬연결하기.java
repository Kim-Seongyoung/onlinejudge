package programmers;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
public class 섬연결하기 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public int solution(int n, int[][] costs) {
        int answer = 0;
		Island[] island = new Island[n];
		boolean[] v = new boolean[n];
		for (int i = 0; i < island.length; i++) {
			island[i] = new Island();
		}
		for (int i = 0; i < costs.length; i++) {
			island[costs[i][0]].bridge.add(new Bridge(costs[i][1], costs[i][2]));
			island[costs[i][1]].bridge.add(new Bridge(costs[i][0], costs[i][2]));
		}
		PriorityQueue<Bridge> pq = new PriorityQueue<>(new Comparator<Bridge>() {

			@Override
			public int compare(Bridge o1, Bridge o2) {
				return o1.len-o2.len;
			}
		});
		v[0] = true;
		for (int i = 0; i < island[0].bridge.size(); i++) {
			pq.add(island[0].bridge.get(i));
		}
		int count = 0;
		while(count!=n-1) {
			Bridge b =pq.poll();
			if(!v[b.idx]) {
				v[b.idx] = true;
				for (int i = 0; i < island[b.idx].bridge.size(); i++) {
					if(!v[island[b.idx].bridge.get(i).idx]) {
						pq.add(island[b.idx].bridge.get(i));
					}
				}
				answer+=b.len;
				count++;
			}
		}
		return answer;
	}

	class Island{
		ArrayList<Bridge> bridge;
		Island(){
			this.bridge= new ArrayList<>();
		}
	}

	class Bridge {
		int idx;
		int len;

		Bridge(int idx, int len) {
			this.idx = idx;
			this.len = len;
		}
	}
}
