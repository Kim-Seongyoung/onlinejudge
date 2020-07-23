package programmers;

public class 입국심사 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n =3;
		int[] times = {1000000000,1000000000,1000000000};
		solution(n, times);
	}
	static public long solution(int n, int[] times) {
        int min =Integer.MAX_VALUE;
        int max = 0;
        for (int i = 0; i < times.length; i++) {
			min = Math.min(min, times[i]);
		}
        long end = (long)min*(long)n;	// 조심 int형 곱하기를 long 안하면 중간에 int형 넘어가면 음수
        long start = 1;

        while(start<end) {
        	long mid = (start+end)/2;
        	long sum = 0;
        	for (int i = 0; i < times.length; i++) {
				sum+=(mid/times[i]);
			}
        	if(sum>=n) {
        		end = mid;
        	}else {
        		start = mid+1;
        	}
        }
        
        return start;
    }
}
