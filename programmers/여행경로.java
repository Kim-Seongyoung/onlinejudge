package programmers;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class 여행경로 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		String[][] tickets = {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL", "SFO"}};
//		String[][] tickets = {{"ICN","BBB"},{"BBB","CCC"},{"CCC","ICN"},{"ICN","DDD"},{"ICN","EEE"},{"EEE","FFF"}};
//		String[] result = solution(tickets);
		System.out.println();
	}
	static boolean flag;
	static String[] answer;
	static public String[] solution(String[][] tickets) {
        answer = new String[tickets.length+1];
        flag= false;
        HashMap<String, Point> hm = new HashMap<String, Point>();
        for (int i = 0; i < tickets.length; i++) {
			if(!hm.containsKey(tickets[i][0])) {
				hm.put(tickets[i][0], new Point());
			}
			if(!hm.containsKey(tickets[i][1])) {
				hm.put(tickets[i][1], new Point());
			}
			hm.get(tickets[i][0]).q.add(tickets[i][1]);
		}
        Iterator<String> iter = hm.keySet().iterator();
        while(iter.hasNext()) {
        	hm.get(iter.next()).Sort();
        }
        answer[0] = "ICN";
        search(hm, "ICN", 1, answer.length);
        return answer;
    }
	static void search(HashMap<String, Point> hm, String start, int cnt, int size) {
		if(cnt == size) {
			flag= true;
			return;
		}else {
			if(!flag) {
				Point p = hm.get(start);
				int q_size= p.q.size();
				while(q_size>0) {
					String s= p.q.poll();
					answer[cnt] = s; 
					search(hm, s, cnt+1, size);
					if(flag) {
						return;
					}
					p.q.add(s);
					q_size--;
				}
			}
		}
	}
	static class Point{
		LinkedList<String> q;
		Point(){
			this.q=new LinkedList<String>();
		}
		void Sort() {
			Collections.sort(this.q);
		}
	}
}
