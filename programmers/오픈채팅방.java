package programmers;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class 오픈채팅방 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};
		solution(record);
	}

	static public String[] solution(String[] record) {
		String[] answer;
		// 아이디를 key, 닉네임을 value로 저장
		HashMap<String, String> hm = new HashMap<String, String>();
		// Enter, Leave만 저장 Change는 출력하지 않기 때문
		LinkedList<String> order = new LinkedList<String>();
		// Enter, Leave에 맞춰서 그때 아이디를 저장
		LinkedList<String> id = new LinkedList<String>();
		for (int i = 0; i < record.length; i++) {
			StringTokenizer st = new StringTokenizer(record[i]);
			String temp_order = st.nextToken();
			String temp_id =  st.nextToken();
			// 앞자리가 다 다르기 때문에 그 부분만으로 분기 처리
			if(temp_order.charAt(0)=='E') {
				// Enter일 때
				order.add(temp_order);
				id.add(temp_id);
				// 아이디와 닉네임 저장
				hm.put(temp_id, st.nextToken());
			}else if(temp_order.charAt(0)=='L') {
				// Leave일 때
				order.add(temp_order);
				id.add(temp_id);
			}else {
				// Change일 때 닉네임만 바뀐다
				hm.put(temp_id, st.nextToken());
			}
		}
		int i = 0;
		answer = new String[order.size()];
		// 출력 형식에 맞춰서 저장
		while(!order.isEmpty()) {
			String temp_order = order.poll();
			String name = hm.get(id.poll());
			if(temp_order.charAt(0)=='E') {
				answer[i] = name+"님이 들어왔습니다.";
			}else {
				answer[i] = name+"님이 나갔습니다.";
			}
			i++;
		}
		return answer;
	}
}
