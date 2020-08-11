package programmers;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class 베스트앨범 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] genres = {"classic", "pop", "classic", "classic", "pop"};
		int[] plays = {500, 600, 150, 800, 2500};
		solution(genres, plays);
	}
	static public int[] solution(String[] genres, int[] plays) {
        int[] answer = {};
        HashMap<String, Album> hm = new HashMap<>();
        for (int i = 0; i < genres.length; i++) {
			if(!hm.containsKey(genres[i])) {
				hm.put(genres[i], new Album());
			}
			hm.get(genres[i]).getMusic(i, plays[i]);
		}
        Iterator<String> iter = hm.keySet().iterator();
        PriorityQueue<Album> q = new PriorityQueue<>(new Comparator<Album>() {

			@Override
			public int compare(Album o1, Album o2) {
				return o2.playSum-o1.playSum;
			}
		});
        while(iter.hasNext()) {
        	String k = iter.next();
        	q.add(hm.get(k));
        }
        LinkedList<Integer> arr = new LinkedList<Integer>();
        while(!q.isEmpty()) {
        	Album album = q.poll();
        	arr.add(album.index[1]);
        	if(album.play[0]!=0) {
            	arr.add(album.index[0]);
        	}
        }
        answer = new int[arr.size()];
        for (int i = 0; i < answer.length; i++) {
			answer[i] = arr.poll();
		}
        
        return answer;
    }
	static class Album{
		int[] index;
		int[] play;
		int playSum;
		Album() {
			this.index= new int[2];
			this.play = new int[2];
			this.playSum=0;
		}
		void getMusic(int index, int play) {
			this.playSum+=play;
			if(this.play[0]<play) {
				if(this.play[1]<play) {
					this.index[0] = this.index[1];
					this.index[1] = index;
					this.play[0] = this.play[1];
					this.play[1] = play;
				}else {
					this.index[0] = index;
					this.play[0] = play;
				}
			}
		}
	}
}
