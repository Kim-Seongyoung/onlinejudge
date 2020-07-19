package programmers;

import java.util.LinkedList;

public class 짝지어제거하기 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		solution("baabaa");
	}

	static public int solution(String s) {
		LinkedList<Character> q = new LinkedList<Character>();
		for (int i = 0; i < s.length(); i++) {
			if (q.isEmpty()) {
				q.add(s.charAt(i));
			} else {
				char c = q.peekLast();
				if (c == s.charAt(i)) {
					q.pollLast();
				}else {
					q.add(s.charAt(i));
				}
			}
		}
		if(q.isEmpty()) {
			return 1;
		}else {
			return 0;
		}
	}
}
