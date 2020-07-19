package programmers;

public class 소수만들기 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = {1,2,3,4};
		solution(nums);
	}
	static public int solution(int[] nums) {
        int answer = 0;
        boolean[] primeArr = new boolean[3000];
        primeArr[0] = true;
        primeArr[1] = true;
        for (int i = 2; i < primeArr.length; i++) {
			if(!primeArr[i]) {
				for (int j = i+i; j < primeArr.length; j=j+i) {
					primeArr[j] = true;
				}
			}
		}
        int sum = 0;
        for (int i = 0; i < nums.length-2; i++) {
			sum = nums[i];
        	for (int j = i+1; j < nums.length-1; j++) {
				sum +=nums[j];
        		for (int k = 0; k < nums.length; k++) {
					sum+=nums[k];
					if(!primeArr[sum]) {
						answer++;
					}
					sum-=nums[k];
				}
        		sum-=nums[j];
			}
		}
        return answer;
    }
}
