package programmers;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;
public class 순위검색 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] info = { "java backend junior pizza 150", "python frontend senior chicken 210",
				"python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80",
				"python backend senior chicken 50" };
		String[] query = { 	"- and backend and senior and - 150", "- and - and - and chicken 100", "- and - and - and - 150" };
		solution(info, query);
	}
	static public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
		Node root = new Node(null, -1);
		LinkedList<Node> t = new LinkedList<>();
		for (int i = 0; i < info.length; i++) {
			StringTokenizer st = new StringTokenizer(info[i]);
			Node node = root;
			String s = st.nextToken();
			if (!node.hm.containsKey(s)) {
				node.hm.put(s, new Node(s, 0));
			}
			node = node.hm.get(s);
			s = st.nextToken();
			if (!node.hm.containsKey(s)) {
				node.hm.put(s, new Node(s, 1));
			}
			node = node.hm.get(s);
			s = st.nextToken();
			if (!node.hm.containsKey(s)) {
				node.hm.put(s, new Node(s, 2));
			}
			node = node.hm.get(s);
			s = st.nextToken();
			if (!node.hm.containsKey(s)) {
				node.hm.put(s, new Node(s, 3));
				node.hm.get(s).num = new int[100001];
				t.add(node.hm.get(s));
			}
			node = node.hm.get(s);
			int num = Integer.parseInt(st.nextToken());
			node.num[num]++;
		}
		while(!t.isEmpty()) {
			Node n = t.poll();
			for (int i = 100000; i >0; i--) {
				n.num[i-1] = n.num[i]+n.num[i-1];
			}
		}
		for (int i = 0; i < query.length; i++) {
			String[] temp = query[i].split(" and ");
			StringTokenizer st = new StringTokenizer(temp[3]);
			temp[3] = st.nextToken();
			String num = st.nextToken();
			answer[i] =search(temp,0,Integer.parseInt(num),root);
		}
		return answer;
    }
	static int search(String[] temp , int cnt, int num, Node node) {
		if(cnt ==4) {
			return node.num[num];
		}else {
			int sum=0;
			if(temp[cnt].charAt(0)=='-') {
				Iterator<String> iter = node.hm.keySet().iterator();
				while(iter.hasNext()) {
					sum+=search(temp, cnt+1, num, node.hm.get(iter.next()));
				}
			}else if(node.hm.containsKey(temp[cnt])){
				sum+=search(temp, cnt+1, num, node.hm.get(temp[cnt]));
			}
			return sum;
		}
	}
	static class Node {
		String s;
		int cnt;
		HashMap<String, Node> hm;
		int[] num;

		Node(String s, int cnt) {
			this.s = s;
			this.cnt = cnt;
			hm = new HashMap<String, Node>();
		}
	}
}
