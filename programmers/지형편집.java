package programmers;

import java.util.Arrays;

public class 지형편집 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		int[][] land ={{4, 4, 3}, {3, 2, 2}, { 2, 1, 0  }};
		int[][] land={{1,1},{100,1}};
		int P = 5;
		int Q = 3;
		System.out.println(solution(land, P, Q));
	}
	static public long solution(int[][] land, int P, int Q) {
        long answer = Long.MAX_VALUE;
        long pre=0,pos=0;
        long preblock, postblock;
        long[] arr = new long[land.length*land.length];
        int pivot = 0;
        for (int i = 0; i < land.length; i++) {
			for (int j = 0; j < land.length; j++) {
				arr[pivot] = land[i][j];
				pos += land[i][j];
				pivot++;
			}
		}
        Arrays.sort(arr);
        if(arr.length==1 || arr[arr.length-1]==arr[0]) {
        	return 0;
        }
        pos-= arr[0];
        postblock = pos-(arr.length-1)*arr[0];
        answer = postblock*Q;
        for (int i = 1; i < arr.length; i++) {
			pre+=arr[i-1];
			pos -= arr[i];
 			preblock = i*arr[i]-pre;
			postblock = pos -(arr.length-1-i)*arr[i];
			answer = Math.min(answer, preblock*P+postblock*Q);
		}
        return answer;
	}

}
