package programmers;

import java.util.HashMap;
import java.util.Iterator;

public class 자동완성 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String[] words = {"word", "war", "warrior", "world"};
		System.out.println(solution(words));
	}

	static public int solution(String[] words) {
		int answer = 0;
		// Trie 자료구조 root
		Node root = new Node('0');
		for (int i = 0; i < words.length; i++) {
			// 각 단어 시작 시 root에서 시작
			Node curr = root;
			for (int j = 0; j < words[i].length(); j++) {
				// 각 node에 현재 알파벳가 있는 검사
				if (!curr.hm.containsKey(words[i].charAt(j))) {
					// 없으면 hashMap에 넣어 만들어 준다.
					curr.hm.put(words[i].charAt(j), new Node(words[i].charAt(j)));
				}
				// 지나갈때마다 증가
				curr.num++;
				// 다음 알파벳 node로 이동
				curr = curr.hm.get(words[i].charAt(j));
			}
			// 마지막 단어가 끝나면 그곳도 방문하므로
			curr.num++;
			// 끝에는 '0'으 넣어서 단어 끝이라고 표시
			curr.hm.put('0', null);
		}
		// Trie 구조를 돌면서 자동 완성에 필요한 단어 길이를 검색
		answer = search(0,root);
		return answer;
	}
	static int search(int deep, Node node) {
		
		if(node == null) {
			// node가 없으면 단어가 끝까지 왔다는 소리
			// 접두어 없이 단어 자체를 다 써야 자동 완성이 된다는 것이다
			// 단어 끝에 '0'을 넣었기 때문에 deep에서 -1
			return deep-1;
		}else if(node.num==1) {
			// 이 다음부터는 한번만 왔기 때문에
			// 이때부터 자동완성이 가능한 접두어 가능
			return deep;
		}else {
			int sum = 0;
			// node.num이 1이 아니기 때문에
			// node의 하위를 검색하면서 하위에서 가능한 자동완성 길이를 더해준다.
			Iterator<Character> iter = node.hm.keySet().iterator();
			while(iter.hasNext()) {
				sum +=search(deep+1,node.hm.get(iter.next()));
			}
			return sum;
		}
	}
	static class Node {
		// 알파벳
		char c;
		// 이 node를 지나치는 횟수
		int num;
		// 하위 node
		HashMap<Character, Node> hm;

		Node(char c) {
			this.c = c;
			this.num = 0;
			this.hm = new HashMap<Character, Node>();
		}
	}

}
