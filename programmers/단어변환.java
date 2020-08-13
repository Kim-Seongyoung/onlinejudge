package programmers;

import java.util.ArrayList;
import java.util.LinkedList;

public class 단어변환 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String begin = "hit";
		String target = "cog";
		String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};
		solution(begin, target, words);
	}
	static public int solution(String begin, String target, String[] words) {
        int answer = 0;
        boolean[] v = new boolean[words.length+1];
        Word[] arr = new Word[words.length+1];
        arr[words.length] = new Word(begin,words.length);
        v[words.length] = true;
        boolean flag= true;
        int target_idx = 0;
        for (int i = 0; i < words.length; i++) {
			arr[i]= new Word(words[i],i);
			if(words[i].equals(target)) {
				target_idx=i;
				flag = false;
			}
		}
        if(flag) {
        	return 0;
        }
        connectWord(arr);
        LinkedList<Word> q = new LinkedList<>();
        q.add(arr[words.length]);
        while(!q.isEmpty()) {
        	Word w = q.poll();
        	for (int i = 0; i < w.arr.size(); i++) {
				if(!v[w.arr.get(i).idx]) {
					if(w.arr.get(i).idx == target_idx) {
						return w.cnt+1;
					}
					v[w.arr.get(i).idx]=true;
					w.arr.get(i).cnt=w.cnt+1;
					q.add(w.arr.get(i));
				}
			}
        }
        return answer;
    }
	static void connectWord(Word[] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = i+1; j < arr.length; j++) {
				int count = 0;
				for (int k = 0; k < arr[i].word.length(); k++) {
					if(arr[i].word.charAt(k)!=arr[j].word.charAt(k)) {
						count++;
						if(count>1) {
							break;
						}
					}
				}
				if(count==1) {
					arr[i].arr.add(arr[j]);
					arr[j].arr.add(arr[i]);
				}
			}
		}
	}
	static class Word{
		int idx;
		int cnt;
		String word;
		ArrayList<Word> arr;
		Word(String word, int idx){
			this.word=word;
			this.idx= idx;
			this.cnt= 0;
			this.arr = new ArrayList<>();
		}
	}
}
