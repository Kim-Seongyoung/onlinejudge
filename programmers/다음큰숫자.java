package programmers;

public class 다음큰숫자 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public int solution(int n) {
        int answer = 0;
        StringBuilder sb = new StringBuilder();
        while(n>0) {
        	sb.append(n%2);
        	n/=2;
        }
        boolean flag = false;
        int count = 0;
        for (int i = 0; i < sb.length(); i++) {
			if(flag) {
				if(sb.charAt(i)=='1') {
					count++;
					sb.setCharAt(i, '0');
				}else {
					sb.setCharAt(i, '1');
					count--;
					flag = false;
					break;
				}
			}else {
				if(sb.charAt(i)=='1') {
					count++;
					flag= true;
					sb.setCharAt(i, '0');
				}
			}
		}
        if (flag) {
			sb.append('1');
			count--;
		}
		for (int i = 0; i < count; i++) {
			sb.setCharAt(i, '1');
		}
        int pivot =1;
        for (int i = 0; i < sb.length(); i++) {
			answer += pivot*(sb.charAt(i)-'0');
			pivot*=2;
		}
        return answer;
    }
}
