package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_17182 {

	static int min;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		Node[][] node = new Node[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				node[j][i] = new Node();
			}
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int temp = Integer.parseInt(st.nextToken());
				if(i!=j) {
					for (int k = 0; k < N; k++) {
						node[k][i].edge.add(new Edge(i,j, temp));
					}
				}
			}
		}
		min = Integer.MAX_VALUE;
		PriorityQueue<Edge> pq = new  PriorityQueue<Edge>(new Comparator<Edge>() {

			@Override
			public int compare(Edge o1, Edge o2) {
				return o1.cost-o2.cost;
			}
		});
		Edge e;
		boolean[] v = new boolean[N];
		for (int i = 0; i < N; i++) {
			node[i][i].cost=0;
			for (int j = 0; j < node[i][i].edge.size(); j++) {
				pq.add(node[i][i].edge.get(j));
			}
			while(!pq.isEmpty()) {
				e = pq.poll();
				int c = node[i][e.start].cost+e.cost;
				if(node[i][e.end].cost>c) {
					node[i][e.end].cost =c; 
					for (int j = 0; j < node[i][e.end].edge.size(); j++) {
						pq.add(node[i][e.end].edge.get(j));
					}
				}
			}
		}
		v[K]= true;
		DFS(node, K,v, 0, 1);
		System.out.println(min);
	}
	static void DFS(Node[][] node, int pivot,boolean[] v,int cost,int cnt) {
		if(cnt==v.length) {
			min = Math.min(min, cost);
			return;
		}
		for (int i = 0; i < v.length; i++) {
			if(!v[i]) {
				v[i]= true;
				DFS(node, i, v, cost+node[pivot][i].cost,cnt+1);
				v[i] =false;
			}
		}
	}
	static class Node{
		int cost;
		ArrayList<Edge> edge;
		Node(){
			this.cost= Integer.MAX_VALUE;
			this.edge = new ArrayList<>();
		}
	}
	static class Edge{
		int start;
		int end;
		int cost;
		Edge(int start, int end,int cost){
			this.start=start;
			this.end=end;
			this.cost=cost;
		}
	}
}
