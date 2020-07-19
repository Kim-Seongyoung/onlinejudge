package programmers;

import java.util.HashSet;

public class 영어끝말잇기 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 2;
		String[] words = { "hello", "one", "even", "never", "now", "world", "draw" };
		solution(n, words);
	}

	static public int[] solution(int n, String[] words) {
		int[] answer = new int[2];
		boolean flag = true;
		HashSet<String> hs = new HashSet<String>();
		for (int i = 0; i < words.length / n && flag; i++) {
			for (int j = 0; j < n; j++) {
				if (i * n + j > 0 && words[i * n + j - 1]
						.charAt(words[i * n + j - 1].length() - 1) != words[i * n + j].charAt(0)) {
					answer[0] = j + 1;
					answer[1] = i + 1;
					flag = false;
					break;
				}
				if (!hs.add(words[i * n + j])) {
					answer[0] = j + 1;
					answer[1] = i + 1;
					flag = false;
					break;
				}
			}
		}
		return answer;
	}
}
