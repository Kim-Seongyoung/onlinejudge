package programmers;

import java.util.LinkedList;

public class 가장먼노드 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 6;
		int[][] edge ={{1, 2}, {2, 3}, {3, 4}, {4, 5}, {5, 6}};
		System.out.println(solution(n, edge));
	}
	static public int solution(int n, int[][] edge) {
        int answer = 0;
        boolean[] v= new boolean[n+1];
        Node[] arr = new Node[n+1];
        for (int i = 0; i <= n; i++) {
			arr[i] = new Node();
		}
        for (int i = 0; i < edge.length; i++) {
			arr[edge[i][0]].q.add(edge[i][1]);
			arr[edge[i][1]].q.add(edge[i][0]);
		}
        LinkedList<Integer> q = new LinkedList<Integer>();
        q.add(1);
        v[1] = true;
        while(!q.isEmpty()) {
        	int t = q.poll();
        	int size = arr[t].q.size();
        	for (int i = 0; i < size; i++) {
				int p = arr[t].q.poll();
				if(!v[p]) {
					v[p] = true;
					arr[p].len = arr[t].len+1;
					q.add(p);
				}
			}
        }
        int max =0;
        for (int i = 2; i < arr.length; i++) {
			if(max<arr[i].len) {
				max = arr[i].len;
				answer=1;
			}else if(max==arr[i].len) {
				answer++;
			}
		}
        return answer;
    }
	static class Node{
		int len;
		LinkedList<Integer> q;
		Node(){
			this.len=0;
			this.q=new LinkedList<Integer>();
		}
	}
}
