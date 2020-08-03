package programmers;

public class 조이스틱 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

	public int solution(String name) {
		int temp = name.charAt(0) - 'A';
		int answer = 0;
		//첫 번째 자리는 위아래만 이동
		if (temp < 13) { 
			answer += temp;
		} else {
			// 13 이상이면 뒤부터 하는 것이 더 빠르다
			answer += (26 - temp);
		}
		boolean flag = false;
		// 기본적으로 오른쪽으로 이동
		// 오른쪽으로만 이동 시 문자열 길이 -1 만큼 이동
		int min = name.length()-1;
		int start = 0;
		for (int i = 1; i < name.length(); i++) {
			// A는 갈 필요가 없기 때문에
			// 그 위치를 기준으로 나눈기 위함
			if (name.charAt(i) == 'A') {
				if (!flag) {
					flag = true;
					start = i-1;
				}
				continue;
			}
			// 연속적인 A가 끝났을 때
			if(flag) {
				// 오른쪽을 갔다가 왼쪽으로 가는 것이 빠른지, 왼쪽으로 갔다가 오른쪽으로 가는 것이 빠른지 아니면 그냥 오른쪽으로 가는게 빠른지 판단
				min = Math.min(min, Math.min(start*2+name.length()-i,(name.length()-i)*2+start));
			}
			flag = false;
			// 1) 판단 문자 기준으로 아래로 이동이 빠른지, 위로 이동이 빠른지
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
