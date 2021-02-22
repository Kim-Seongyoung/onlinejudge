package programmers;

import java.util.ArrayList;

public class 매출하락최소화 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] sales = {10, 10, 1, 1};
		int[][] links= {{3,2},{4,3},{1,4}};
		System.out.println(solution(sales, links));
	}
	static public int solution(int[] sales, int[][] links) {
		Node[] nodes = new Node[sales.length];
		for (int i = 0; i < sales.length; i++) {
			nodes[i] = new Node(sales[i]);
		}
		for (int i = 0; i < links.length; i++) {
			links[i][0]--;
			links[i][1]--;
			nodes[links[i][0]].link.add(nodes[links[i][1]]);
		}
		cost_cal(nodes[0]);
		return Math.min(nodes[0].cost[0], nodes[0].cost[1]);
	}

	static void cost_cal(Node node) {
		if(node.link.size()!=0) {
			int sum = 0;
			for (int i = 0; i < node.link.size(); i++) {
				cost_cal(node.link.get(i));
				sum+=Math.min(node.link.get(i).cost[0],node.link.get(i).cost[1]);
			}
			node.cost[1] = sum+node.sale;
			int min =Integer.MAX_VALUE;
			for (int i = 0; i < node.link.size(); i++) {
				min = Math.min(min, sum-Math.min(node.link.get(i).cost[1],node.link.get(i).cost[0])+node.link.get(i).cost[1]);
			}
			node.cost[0] = min;
		}
	}

	static class Node {
		int sale;
		int[] cost;
		ArrayList<Node> link;

		Node(int sale) {
			this.sale = sale;
			this.cost = new int[2];
			this.cost[0] = 0;
			this.cost[1] = sale;
			this.link = new ArrayList<Node>();
		}
	}
}
