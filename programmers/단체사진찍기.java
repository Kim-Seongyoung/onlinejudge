package programmers;

import java.util.HashMap;

public class 단체사진찍기 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	HashMap<Character,Integer> hm;
	boolean[]v;
	int answer;
	public int solution(int n, String[] data) {
        answer = 0;
        char[] arr = {'A','C','F','J','M','N','R','T'};
        char[][] order = new char[n][4];
         hm = new HashMap<>();
        v= new boolean[8];
        for (int i = 0; i < arr.length; i++) {
			hm.put(arr[i], 0);
		}
        for (int i = 0; i < order.length; i++) {
			order[i][0] = data[i].charAt(0);
			order[i][1] = data[i].charAt(2);
			order[i][2] = data[i].charAt(3);
			order[i][3] = data[i].charAt(4);
		}
        search(arr, 0, order);
        return answer;
    }
	void search(char[] arr, int curr,char[][] order) {
		if(curr ==8) {
			for (int i = 0; i < order.length; i++) {
				int temp = Math.abs(hm.get(order[i][0]) - hm.get(order[i][1]))-1;
				if(order[i][2]=='=') {
					if(temp!=(order[i][3]-'0')) {
						return;
					}
				}else if(order[i][2]=='<') {
					if(temp >= (order[i][3]-'0')) {
						return;
					}
				}else {
					if(temp <= (order[i][3]-'0')) {
						return;
					}
				}
			}
			answer++;
		}else {
			for (int i = 0; i < arr.length; i++) {
				if(!v[i]) {
					v[i] = true;
					hm.put(arr[i], curr);
					search(arr, curr+1,order);
					v[i] = false;
				}
			}
		}
	}
}
