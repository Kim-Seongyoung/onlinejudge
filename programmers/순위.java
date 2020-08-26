package programmers;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
public class 순위 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public int solution(int n, int[][] results) {
        int answer = 0;
        n++;
        Player[] players = new Player[n];
        for (int i = 1; i <n; i++) {
			players[i] = new Player(i);
		}
        for (int i = 0; i < results.length; i++) {
			players[results[i][0]].win.add(results[i][1]);
			players[results[i][1]].lose.add(results[i][0]);
		}
        LinkedList<Player> win = new LinkedList<>();
        LinkedList<Player> lose = new LinkedList<>();
        for (int i = 1; i < players.length; i++) {
        	players[i].countLose = players[i].lose.size();
        	players[i].countWin = players[i].win.size();
			if(players[i].win.size()==0) {
				win.add(players[i]);
			}
			if(players[i].lose.size()==0) {
				lose.add(players[i]);
			}
		}
        while(!win.isEmpty()) {
        	Player player = win.poll();
        	Iterator<Integer> iter = player.lose.iterator();
        	while(iter.hasNext()) {
        		int temp = iter.next();
        		players[temp].win.addAll(player.win);
        		players[temp].countWin--;
        		if(players[temp].countWin==0) {
        			win.add(players[temp]);
        		}
        	}
        }
        while(!lose.isEmpty()) {
        	Player player = lose.poll();
        	Iterator<Integer> iter = player.win.iterator();
        	while(iter.hasNext()) {
        		int temp = iter.next();
        		players[temp].lose.addAll(player.lose);
        		players[temp].countLose--;
        		if(players[temp].countLose==0) {
        			lose.add(players[temp]);
        		}
        	}
        }
        n=n-2;
        for (int i = 1; i <= n+1; i++) {
			if(players[i].win.size()+players[i].lose.size()==n) {
				answer++;
			}
		}
        return answer;
    }
	class Player{
		int index;
		int countWin;
		int countLose;
		HashSet<Integer> win;
		HashSet<Integer> lose;
		
		Player(int index){
			this.index=index;
			win = new HashSet<>();
			lose = new HashSet<>();
			countLose = 0;
			countWin =0 ;
		}
	}
}
