package programmers;

import java.util.HashMap;
import java.util.Iterator;

public class 위장 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	int answer = 0;
	public int solution(String[][] clothes) {
        HashMap<String, Integer> hm = new HashMap<String, Integer>();
        for (int i = 0; i < clothes.length; i++) {
			if(!hm.containsKey(clothes[i][1])) {
				hm.put(clothes[i][1], 1);
			}else {
				hm.replace(clothes[i][1], hm.get(clothes[i][1])+1);
			}
		}
        int[] arr = new int[hm.size()];
        Iterator<String> iter = hm.keySet().iterator();
        for (int i = 0; i < arr.length; i++) {
			arr[i] = hm.get(iter.next());
		}
        combi(arr, 0, 1);
        return answer;
    }
	void combi(int[] arr,int pivot, int mul) {
		if(arr.length==pivot) {
			return;
		}else {
			for (int i = pivot; i < arr.length; i++) {
				int temp = (mul*arr[i]);
				answer += temp;
				combi(arr, i+1, temp);
			}
		}
	}
}
