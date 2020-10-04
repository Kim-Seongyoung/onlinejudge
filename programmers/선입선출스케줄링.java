package programmers;

public class 선입선출스케줄링 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n =6;
		int[] cores = {1,2,3};
		System.out.println(solution(n, cores));
	}
	static public int solution(int n, int[] cores) {
		int answer = 0;
        if(cores.length>=n) {
        	answer=n;
        }else {
        	int start = 1;
        	int end = 500000000;
        	while(start<=end) {
        		int mid = (start+end)/2;
        		long sum=cores.length;
        		int count = 0;
        		for (int i = 0; i < cores.length; i++) {
					sum +=mid/cores[i];
					if(mid%cores[i]==0) {
						count++;
					}
				}
        		if(sum<n) {
        			start = mid+1;
        		}else if(n<=sum-count) {
        			end = mid-1;
        		}else {
        			n-=sum-count;
        			for (int i = 0; i < cores.length; i++) {
						if(mid%cores[i]==0) {
							n--;
							if(n==0) {
								return i+1;
							}
						}
					}
        		}
        	}
        	
        }
        return answer;
    }
}
