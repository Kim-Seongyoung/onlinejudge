package programmers;

import java.util.HashSet;

public class 풍선터트리기 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	 public int solution(int[] a) {
	        HashSet<Integer> hs = new HashSet<>();
	        int min = a[0];
	        for(int i = 1 ; i< a.length; i++){
	            hs.add(min);
	            min = Math.min(a[i],min);
	        }
	        min = a[a.length-1];
	        for(int i = a.length-2 ; i>=0; i--){
	            hs.add(min);
	            min = Math.min(a[i],min);
	        }
	        return hs.size();
	    }
}
