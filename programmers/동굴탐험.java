package programmers;

import java.util.HashSet;
import java.util.LinkedList;

public class 동굴탐험 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public boolean solution(int n, int[][] path, int[][] order) {
		boolean answer = true;
		Node[] nodes = new Node[n];
		int[] v = new int[n];
		for (int i = 0; i < nodes.length; i++) {
			nodes[i] = new Node(i);
		}
		for (int i = 0; i < path.length; i++) {
			nodes[path[i][0]].nodes.add(path[i][1]);
			nodes[path[i][1]].nodes.add(path[i][0]);
		}
		for (int i = 0; i < order.length; i++) {
			v[order[i][1]] = -1;
			nodes[order[i][0]].order= order[i][1];
		}
		LinkedList<Node> q = new LinkedList<>();
		HashSet<Integer> hs = new HashSet<>();
		LinkedList<Integer> wait = new LinkedList<>();
		q.add(nodes[0]);
		if (v[0] == -1) {
			return false;
		}
		v[0] = 1;

		while (!q.isEmpty()) {
			while (!q.isEmpty()) {
				Node node = q.poll();
				if(node.order!=-1) {
					wait.add(node.order);
					v[node.order] = 0;
				}
				while (!node.nodes.isEmpty()) {
					int temp = node.nodes.poll();
					if (v[temp] == 0) {
						v[temp] = 1;
						q.add(nodes[temp]);
					} else if (v[temp] == -1 && !hs.contains(temp)) {
						hs.add(temp);
					}
				}
			}
			if (hs.isEmpty()) {
				return true;
			}
			int size= hs.size();
			while(!wait.isEmpty()) {
				int temp = wait.poll();
				if(hs.contains(temp)) {
					hs.remove(temp);
					q.add(nodes[temp]);
				}
			}
			if(hs.size()==size) {
				return false;
			}
		}
		return answer;
	}

	class Node {
		int index;
		LinkedList<Integer> nodes;
		int order;

		Node(int index) {
			this.index = index;
			this.nodes = new LinkedList<>();
			this.order = -1;
		}
	}
}
