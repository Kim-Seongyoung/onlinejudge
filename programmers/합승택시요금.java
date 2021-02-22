package programmers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class 합승택시요금 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] fares = {{4, 1, 10}, {3, 5, 24}, {3, 1, 41},{5, 6, 2}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}};
		solution(6, 4, 6, 2, fares);
	}

	static public int solution(int n, int s, int a, int b, int[][] fares) {
		int answer = Integer.MAX_VALUE;
		HashMap<Integer, ArrayList<Edge>> hm = new HashMap<Integer, ArrayList<Edge>>();
		int[][] cost = new int[3][n+1];
		for (int i = 1; i <= n; i++) {
			hm.put(i, new ArrayList<Edge>());
			cost[0][i] = Integer.MAX_VALUE;
			cost[1][i] = Integer.MAX_VALUE;
			cost[2][i] = Integer.MAX_VALUE;
		}
		for (int i = 0; i < fares.length; i++) {
			hm.get(fares[i][0]).add(new Edge(fares[i][1], fares[i][2]));
			hm.get(fares[i][1]).add(new Edge(fares[i][0], fares[i][2]));
		}
		cost_cal(s, hm, cost[0]);
		cost_cal(a, hm, cost[1]);
		cost_cal(b, hm, cost[2]);
		for (int i = 1; i <=n; i++) {
			answer = Math.min(answer, cost[0][i]+cost[1][i]+cost[2][i]);
		}
		return answer;
	}

	static class Edge {
		int num;
		int cost;

		Edge(int num, int cost) {
			this.num = num;
			this.cost = cost;
		}
	}

	static void cost_cal(int start, HashMap<Integer, ArrayList<Edge>> hm, int[] cost) {
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>(new Comparator<Edge>() {
			@Override
			public int compare(Edge o1, Edge o2) {
				return o1.cost - o2.cost;
			}
		});
		for (int i = 0; i < hm.get(start).size(); i++) {
			pq.add(hm.get(start).get(i));
		}
		cost[start]=0;
		while (!pq.isEmpty()) {
			Edge e = pq.poll();
			if (cost[e.num] > e.cost) {
				cost[e.num] = e.cost;
				for (int i = 0; i < hm.get(e.num).size(); i++) {
					pq.add(new Edge(hm.get(e.num).get(i).num, hm.get(e.num).get(i).cost + cost[e.num]));
				}
			}
		}
	}
}
