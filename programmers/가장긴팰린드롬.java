package programmers;

public class 가장긴팰린드롬 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public int solution(String s)
    {
        for (int i = 0; i < s.length(); i++) {
        	int len = s.length()-i;
			for (int j = 0; j <= i; j++) {
				int temp = j+len-1;
				int k = j;
				boolean flag = true;
				while(j<temp) {
					if(s.charAt(k)!=s.charAt(temp)) {
						flag = false;
						break;
					}
					k++;
					temp--;
				}
				if(flag) {
					return len;
				}
			}
		}
        return 0;
    }
}
