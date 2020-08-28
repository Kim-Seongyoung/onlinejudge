package programmers;

public class 숫자블록 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public int[] solution(long begin, long end) {
        int[] answer = new int[(int) (end-begin)+1];
        int idx =0;
        for (long i = begin; i <= end; i++) {
        	answer[idx]=1;
			for (int j = 2; j <=Math.sqrt(i); j++) {
				if(i%j==0 && i/j <=10000000) {
					answer[idx]=(int) (i/j);
                    break;
				}
			}
			idx++;
		}
        if(begin==1) {
        	answer[0]=0;
        }
        return answer;
    }
}
