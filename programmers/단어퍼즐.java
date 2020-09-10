package programmers;

import java.util.HashSet;

public class 단어퍼즐 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public int solution(String[] strs, String t) {
	       int answer = -1;
	        HashSet<String> hs = new HashSet<>();
	        for (int i = 0; i < strs.length; i++) {
				hs.add(strs[i]);
			}
	        StringBuilder sb = new StringBuilder(t);
	        int[] arr = new int[t.length()+1];
	        if(hs.contains(sb.charAt(0)+"")) {
	        	arr[1] = 1;
	        }else {
	        	arr[1]=Integer.MAX_VALUE;
	        }
	        for (int i = 2; i < arr.length; i++) {
	        	int min = Integer.MAX_VALUE;
				for (int j = i-1; j >=i-5 && j>=0; j--) {
					if(hs.contains(sb.substring(j, i))&& arr[j] !=Integer.MAX_VALUE){
						min = Math.min(min, arr[j]+1);
					}
				}
				arr[i] = min;
			}
	        if(arr[arr.length-1] !=Integer.MAX_VALUE) {
	        	answer=arr[arr.length-1];
	        }
	        return answer;
	    }
}
