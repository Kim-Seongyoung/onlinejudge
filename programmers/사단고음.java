package programmers;

import java.util.LinkedList;

public class 사단고음 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 2147483647;
		int[] arr = new int[500];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = solution(i);
		}
		System.out.println(solution(n));
	}
	static public int solution(int n) {
        int answer = 0;
        if(n%2==0) {
        	return answer;
        }
        int muxCount =-1;
        int temp = n;
        while(temp!=0) {
        	muxCount++;
        	temp/=3;
        }
        int plusCount = muxCount*2;
        LinkedList<Node> q = new LinkedList<>();
        q.add(new Node(0,2,n-2));
        while(!q.isEmpty()) {
        	Node node = q.poll();
        	if(node.muxCount==muxCount|| n==1) {
        		if(muxCount==node.muxCount &&  plusCount == node.plusCount) {
        			answer++;
        		}
        	}else {
        		if((node.muxCount+1)*2 <= node.plusCount && node.n%3==0) {
        			q.add(new Node(node.muxCount+1, node.plusCount,node.n/3));
        		}
        		if(node.plusCount<plusCount) {
        			q.add(new Node(node.muxCount,node.plusCount+1,node.n-1));
        		}
        	}
        }
        return answer;
    }
	static class Node{
		int muxCount;
		int plusCount;
		int n;
		Node(int muxCount, int plusCount, int n){
			this.muxCount= muxCount;
			this.plusCount = plusCount;
			this.n=n;
		}
	}
}
