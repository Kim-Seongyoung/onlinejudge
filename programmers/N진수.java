package programmers;

public class N진수 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n=16;
		int t=16;
		int m=2;
		int p=1;
		System.out.println(solution(n,t,m,p));
	}
	static public String solution(int n, int t, int m, int p) {
        String answer = "";
        // N진수로 변환된 숫자들의 최대 길이
        int size = t*m;
        // N진수로 변환된 숫자들
        String temp = "0";
        // 숫자가 어디까지 필요한지 구한다.
        int num =1;
        while(temp.length()<size) {
            // N진수 변환 되었을 때 숫자들을 붙여서 저장
        	temp +=conv(num,n);
        	num++;
        }
        // 처음 p번째 부터 사람 수만큼 건너뛰면서 말해야하는 숫자 저장
        for (int i = p-1; answer.length()!=t; i=i+m) {
			answer +=temp.charAt(i);
		}
        return answer;
    }
	static String conv(int num, int n) {
		String result = "";
		// 나머지 연산을 통해 각 자리 수에 맞는 값 저장
		while(num>0) {
			int remainder = num%n;
			// 10진법 이상일 때, 나머지가 10이상이면 A,B 순으로 저장
			if(10<=remainder) {
				result =  (char) ('A'+(remainder-10))+result;
			}else {
				result =remainder+result;
			}
			num /=n;
		}
		return result;
	}
}
