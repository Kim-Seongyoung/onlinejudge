package programmers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class 배달 {
	//30분
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int N = 5;
		int[][] road = {{1,2,1},{2,3,3},{5,2,2},{1,4,2},{5,3,1},{5,4,2}};
		int K = 3;
		solution(N, road, K);
	}
	static public int solution(int N, int[][] road, int K) {
        int answer = 0;
        City[] arr = new City[N+1];
        for (int i = 1; i < arr.length; i++) {
			arr[i] = new City();
		}
        for (int i = 0; i < road.length; i++) {
			int a = road[i][0];
			int b = road[i][1];
			int c = road[i][2];
			arr[a].road.add(new Road(a,b,c));
			arr[b].road.add(new Road(b,a, c));
		}
        arr[1].len=0;
        PriorityQueue<Road> q = new PriorityQueue<>(new Comparator<Road>() {

			@Override
			public int compare(Road o1, Road o2) {
				return o1.len-o2.len;
			}
		});
        for (int i = 0; i < arr[1].road.size(); i++) {
			q.add(arr[1].road.get(i));
		}
        while(!q.isEmpty()) {
        	Road r = q.poll();
        	int temp = arr[r.a].len+r.len;
        	if(temp<arr[r.b].len) {
        		arr[r.b].len=temp;
        		for (int i = 0; i < arr[r.b].road.size(); i++) {
					q.add(arr[r.b].road.get(i));
				}
        	}
        }
        for (int i = 1; i < arr.length; i++) {
			if(arr[i].len<=K) {
				answer++;
			}
		}
        return answer;
    }
	static class City{
		int len;
		ArrayList<Road> road;
		City() {
			len = Integer.MAX_VALUE;
			road = new ArrayList<Road>();
		}
	}
	static class Road{
		int a;
		int b;
		int len;
		Road(int a,int b, int len) {
			this.a = a;
			this.b=b;
			this.len=len;
		}
	}
}
