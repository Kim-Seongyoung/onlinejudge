package programmers;

import java.util.HashMap;
import java.util.LinkedList;

public class 압축 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String msg = "A";
		solution(msg);
	}
	static public int[] solution(String msg) {
        int[] answer;
        // 출력값 리스트
        LinkedList<Integer> arr = new LinkedList<Integer>();
        // 사전
        HashMap<String, Integer> hm = new HashMap<String, Integer>();
        char c = 'A';
        // A ~ Z까지 기본 사전 만들기
        for (int i = 1; i <=26; i++) {        							
			hm.put(c+"", i);
			c++;
		}
        
        int num = 27;
        for (int i = 0; i < msg.length(); i++) {
			for (int j = 1; ; j++) {					
				// 문자열 끝까지 갔을 때
				if(i+j>msg.length()) {
					// 마지막 출력 값 저장
					arr.add(hm.get(msg.substring(i, i+j-1)));	
					i=i+j-2;									
					break;
				}
				// 현재 입력 + 다음 글자
				String temp = msg.substring(i,i+j);			
				if(!hm.containsKey(temp)) {					
					// 출력 값 저장
					arr.add(hm.get(msg.substring(i, i+j-1)));
					// 현재 입력 + 다음 글자 사전에 저장
					hm.put(temp, num);		
					num++;
					i=i+j-2;
					break;
				}
			}
		}
        // return 형식 int[]
        answer = new int[arr.size()];
    	// 출력 값을 배열에 저장
        for (int i = 0; i < answer.length; i++) {	
			answer[i] = arr.poll();
		}
        return answer;
    }
}
