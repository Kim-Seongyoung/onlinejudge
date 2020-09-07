package programmers;
import java.util.HashMap;
public class 가사검색 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	 public int[] solution(String[] words, String[] queries) {
			int[] answer = new int[queries.length];
			Node prefix = new Node('0',0);
			Node suffix = new Node('0',0);
			for (int i = 0; i < words.length; i++) {
				StringBuilder sb = new StringBuilder(words[i]);
				wordAdd(sb.toString(), 0, prefix);
				wordAdd(sb.reverse().toString(), 0, suffix);
			}
			for (int i = 0; i < queries.length; i++) {
				if(queries[i].charAt(0)=='?') {
					StringBuilder sb = new StringBuilder(queries[i]);
					answer[i] = search(suffix, sb.reverse().toString(), 0);
				}else {
					answer[i] = search(prefix, queries[i], 0);
				}
			}
			return answer;
		}
		int search(Node node, String s, int cnt) {
			if(s.length()==cnt) {
				return 0;
			}else {
				int count=  0;
				if(s.charAt(cnt)=='?') {
					if(node.hm_count.containsKey(s.length())) {
						count = node.hm_count.get(s.length());
					}
				}else {
					if(node.hm.containsKey(s.charAt(cnt))) {
						count= search(node.hm.get(s.charAt(cnt)), s, cnt+1);
					}
				}
				return count;
			}
		}
		void wordAdd(String s, int cnt, Node node) {
			if (cnt == s.length()) {
				return;
			} else {
				
				if (!node.hm.containsKey(s.charAt(cnt))) {
					node.hm.put(s.charAt(cnt), new Node(s.charAt(cnt),node.depth+1));
				}
				if(!node.hm_count.containsKey(s.length())) {
					node.hm_count.put(s.length(),0);
				}
				node.hm_count.put(s.length(), node.hm_count.get(s.length())+1);
				wordAdd(s, cnt+1, node.hm.get(s.charAt(cnt)));
			}
		}
		class Node {
			char c;
			int depth;
			HashMap<Character, Node> hm;
			HashMap<Integer,Integer> hm_count;

			Node(char c,int depth) {
				this.c = c;
				this.depth = depth;
				hm = new HashMap<>();
				hm_count = new HashMap<>();
			}
		}
}
