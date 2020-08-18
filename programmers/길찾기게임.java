package programmers;

import java.util.PriorityQueue;
import java.util.Comparator;
public class 길찾기게임 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	int cnt;
	public int[][] solution(int[][] nodeinfo) {
		int[][] answer = new int[2][nodeinfo.length];
		PriorityQueue<Node> q = new PriorityQueue<>(new Comparator<Node>() {

			@Override
			public int compare(Node o1, Node o2) {
				return o2.y-o1.y;
			}
		});

		for (int i = 0; i < nodeinfo.length; i++) {
			q.add(new Node(i + 1, nodeinfo[i][1], nodeinfo[i][0]));
		}
		Node root = q.poll();
		while (!q.isEmpty()) {
			Node cnt= root;
			Node node =q.poll();
			while(true) {
				if(cnt.x<node.x) {
					if(cnt.right==null) {
						cnt.right = node;
						node.p=cnt;
						break;
					}
					cnt = cnt.right;
				}else {
					if(cnt.left==null) {
						cnt.left = node;
						node.p=cnt;
						break;
					}
					cnt = cnt.left;
				}
			}
		}
		cnt= 0;
		prefix(root,answer[0]);
		cnt=0;
		postfix(root, answer[1]);
		return answer;
	}
	void prefix(Node node,int[] answer) {
		answer[cnt] = node.index;
		cnt++;
		if(node.left!=null) {
			prefix(node.left, answer);
		}
		if(node.right!=null) {
			prefix(node.right, answer);
		}
		
	}
	void postfix(Node node, int[] answer) {
		if(node.left!=null) {
			postfix(node.left, answer);
		}
		if(node.right!=null) {
			postfix(node.right, answer);
		}
		answer[cnt] = node.index;
		cnt++;
		
	}
	class Node {
		Node p, left, right;
		int y;
		int x;

		int index;

		Node(int index, int y, int x) {
			this.index = index;
			this.y = y;
			this.x = x;
		}
	}
}
