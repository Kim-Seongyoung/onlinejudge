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
		// 각 섬에서 갈 수 있는 다리와 도달하는 섬을 저장한다.
		for (int i = 0; i < costs.length; i++) {
			island[costs[i][0]].bridge.add(new Bridge(costs[i][1], costs[i][2]));
			island[costs[i][1]].bridge.add(new Bridge(costs[i][0], costs[i][2]));
		}
		//현재 우선순위 큐에서 건설 비용이 가장 적은 다리부터 poll
		PriorityQueue<Bridge> pq = new PriorityQueue<>(new Comparator<Bridge>() {

			@Override
			public int compare(Bridge o1, Bridge o2) {
				return o1.cost-o2.cost;
			}
		});
		// Prime 알고리즘 사용
		// 첫번째 섬 기준으로 시작
		v[0] = true;
		// 첫번째 섬에 연결된 다리 저장
		for (int i = 0; i < island[0].bridge.size(); i++) {
			pq.add(island[0].bridge.get(i));
		}
		int count = 0;
		// 모든 섬을 최소 비용으로 방문하려면 n-1개의 다리가 필요
		while(count!=n-1) {
			// 건설 비용이 가장 적은 다리 선택
			Bridge b =pq.poll();
			// 방문여부 확인
			if(!v[b.idx]) {
				// 미 방문 시 방문 처리 후 그 섬에 연결된 다리 저장
				v[b.idx] = true;
				for (int i = 0; i < island[b.idx].bridge.size(); i++) {
					// 저장할 다리가 미 방문한 섬과 연결될 경우만 저장
					if(!v[island[b.idx].bridge.get(i).idx]) {
						pq.add(island[b.idx].bridge.get(i));
					}
				}
				// 다리 건설 비용 저장
				answer+=b.cost;
				count++;
			}
		}
		return answer;
	}
	// 섬에 연결된 다리만 저장
	class Island{
		ArrayList<Bridge> bridge;
		Island(){
			this.bridge= new ArrayList<>();
		}
	}
	// 다리의 건설 비용과 연결된 섬 저장
	class Bridge {
		int idx;
		int cost;

		Bridge(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}
	}
}
