package programmers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class 메뉴리뉴얼 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] orders = { "ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH" };
		int[] course = { 2, 3, 4 };
		solution(orders, course);
	}

	static public String[] solution(String[] orders, int[] course) {
		String[] answer;
		HashMap<Integer, Node> hm = new HashMap<>();
		HashMap<String, Integer> comb = new HashMap<>();
		for (int i = 0; i < course.length; i++) {
			hm.put(course[i], new Node());
		}
		char[] s;
		for (int i = 0; i < orders.length; i++) {
			s = orders[i].toCharArray();
			Arrays.sort(s);
			search(s, comb, new StringBuilder(), 0, course[course.length-1], 0);

		}
		Iterator<String> iter = comb.keySet().iterator();
		while(iter.hasNext()) {
			String key = iter.next();
			if(hm.containsKey(key.length())) {
				if(hm.get(key.length()).num<=comb.get(key)) {
					if(hm.get(key.length()).num!=comb.get(key)) {
						hm.get(key.length()).arr.clear();
						hm.get(key.length()).num = comb.get(key);
					}
					hm.get(key.length()).arr.add(key);
				}
			}
		}
		
		int count = 0;
		for (int i = 0; i < course.length; i++) {
			count += hm.get(course[i]).arr.size();
		}
		answer = new String[count];
		int idx = 0;
		for (int i = 0; i < course.length; i++) {
			while (!hm.get(course[i]).arr.isEmpty()) {
				answer[idx] = hm.get(course[i]).arr.poll();
				idx++;
			}
		}
		Arrays.sort(answer);
		return answer;
	}

	static class Node {
		int num;
		LinkedList<String> arr;

		Node() {
			this.num = 2;
			this.arr = new LinkedList<String>();
		}
	}

	static void search(char[] s, HashMap<String, Integer> comb, StringBuilder sub_s, int start, int end, int idx) {
		if (start == end) {
			return;
		} else {
			StringBuilder sb = new StringBuilder().append(sub_s);
			for (int i = idx; i < s.length; i++) {
				sb.append(s[i]);
				if (comb.containsKey(sb.toString())) {
					comb.replace(sb.toString(), comb.get(sb.toString()) + 1);
				} else {
					comb.put(sb.toString(), 1);
				}
				search(s, comb, sb, start + 1, end, i + 1);
				sb.deleteCharAt(sb.length() - 1);
			}
		}
	}
}
