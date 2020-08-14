package programmers;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;

public class 후보키 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	LinkedList<Integer> result;
    public int solution(String[][] relation) {
        int answer = 0;
		result = new LinkedList<>();
		String[] s = new String[relation.length];
		for (int i = 0; i < s.length; i++) {
			s[i] = "";
		}
		search(relation, s, 0, 0);
		String[] arr = new String[result.size()];
		Collections.sort(result);
		for (int i = 0; i < arr.length; i++) {
			arr[i] = "" + result.poll();
		}
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != null) {
				answer++;
				for (int j = i + 1; j < arr.length; j++) {
					if (arr[j] != null) {
						int pivot = 0;
						for (int k = 0; k < arr[j].length(); k++) {
							if (arr[i].charAt(pivot) < arr[j].charAt(k)) {
								break;
							} else if (arr[i].charAt(pivot) == arr[j].charAt(k)) {
								pivot++;
							}
							if (pivot == arr[i].length()) {
								arr[j] = null;
								break;
							}
						}
					}
				}
			}
		}

		return answer;
    }
    void search(String[][] relation, String[] s, int cnt, int sum) {
		String[] temp = new String[s.length];
		HashSet<String> hs = new HashSet<>();
		for (int i = cnt; i < relation[0].length; i++) {
			hs.clear();
			boolean flag = false;
			for (int j = 0; j < relation.length; j++) {
				temp[j] = s[j] + "_" + relation[j][i];
				if (!hs.add(temp[j])) {
					flag = true;
				}
			}
			if (flag) {
				search(relation, temp, i + 1, sum * 10 + i+1);
			} else {
				result.add(sum * 10 + i+1);
			}
		}
	}
}
