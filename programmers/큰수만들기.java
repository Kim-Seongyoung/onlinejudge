package programmers;

public class 큰수만들기 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String number ="1231234";
		int k =3;
		System.out.println(solution(number, k));
	}
	static public String solution(String number, int k) {
        StringBuilder sb = new StringBuilder();
		int max = -1;
		int idx = 0;
		while(k!=0) {
			if(k==number.length()-idx) {
				return sb.toString();
			}
			max = -1;
			int temp_idx  =0;
        	for (int i = idx; i <= idx+k; i++) {
				if(max<number.charAt(i)-'0') {
					max= number.charAt(i)-'0';
					temp_idx = i;
				}
			}
        	sb.append(number.charAt(temp_idx));
        	k =k-(temp_idx-idx);
        	idx = temp_idx+1;
        }
		if(number.length()!=idx) {
			for (int i = idx; i < number.length(); i++) {
				sb.append(number.charAt(i));
			}
		}
		return sb.toString();
    }
}
