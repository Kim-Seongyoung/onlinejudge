package programmers;

import java.util.Arrays;

public class 숫자게임 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	static public int solution(int[] A, int[] B) {
        int answer = 0;
        // 먼저 A와 B 배열을 정렬한다
        Arrays.sort(A);
        Arrays.sort(B);
        int j = B.length-1;
        for(int i =A.length-1; i>=0;i--){
        	// B가 클 경우
            if(A[i]<B[j]){
            	// 승점을 증가 시키고 다음 인덱스로 이동
                answer++;
                j--;
            }
        }
        return answer;
    }
}
