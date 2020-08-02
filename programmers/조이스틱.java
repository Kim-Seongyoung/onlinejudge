package programmers;

public class 조이스틱 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

	public int solution(String name) {
		int temp = name.charAt(0) - 'A';
		int answer = 0;
		if (temp < 13) {
			answer += temp;
		} else {
			answer += (26 - temp);
		}
		boolean flag = false;
		int min = name.length()-1;
		int start = 0;
		for (int i = 1; i < name.length(); i++) {
			if (name.charAt(i) == 'A') {
				if (!flag) {
					flag = true;
					start = i-1;
				}
				continue;
			}
			if(flag) {
				min = Math.min(min, Math.min(start*2+name.length()-i,(name.length()-i)*2+start));
			}
			flag = false;
			int c = name.charAt(i) - 'A';
			if (c < 13) {
				answer += c;
			} else {
				answer += (26 - c);
			}
		}
		answer+=min;
		return answer;
	}
}
