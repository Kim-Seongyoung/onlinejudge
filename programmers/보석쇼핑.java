package programmers;

import java.util.HashMap;

public class 보석쇼핑 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public int[] solution(String[] gems) {
        int[] answer = new int[2];
		HashMap<String, Integer> hm = new HashMap<>();
		for (int i = 0; i < gems.length; i++) {
			if (!hm.containsKey(gems[i])) {
				hm.put(gems[i], 0);
			}
		}
		int start = 0;
		int count = 0;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < gems.length; i++) {
			if (hm.get(gems[i]) == 0) {
				count++;
			}
			
			hm.put(gems[i], hm.get(gems[i]) + 1);
			while (hm.get(gems[start]) - 1 != 0) {
				hm.put(gems[start], hm.get(gems[start]) - 1);
				start++;
			}
			if (count == hm.size()) {
				if (min > i - start) {
					answer[0] = start + 1;
					answer[1] = i + 1;
					min = i-start;
				}
			}
		}
		
		return answer;
    }
}
