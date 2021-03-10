package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main_14725 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		Tunnel tunnel = new Tunnel("");
		Tunnel pivot = tunnel;
		StringTokenizer st;
		String food;
		String[] op = new String[16];
		String s = "";
		for (int i = 0; i < op.length; i++) {
			op[i]=s;
			s+="--";
		}
		int idxS=0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			st.nextToken();
			pivot = tunnel;
			idxS=0;
			while(st.hasMoreTokens()) {
				food= op[idxS]+st.nextToken();
				if(!pivot.tm.containsKey(food)) {
					pivot.tm.put(food, new Tunnel(food));
				}
				pivot= pivot.tm.get(food);
				idxS++;
			}
		}
		LinkedList<Tunnel> q = new LinkedList<>();
		LinkedList<Tunnel> q2 = new LinkedList<>();
		Iterator<String> iter = tunnel.tm.keySet().iterator();
		while(iter.hasNext()) {
			s=iter.next();
			q.add(tunnel.tm.get(s));
		}
		while(!q.isEmpty()) {
			pivot = q.poll();
			bw.append(pivot.food);
			bw.newLine();
			iter = pivot.tm.keySet().iterator();
			while(iter.hasNext()) {
				s=iter.next();
				q2.addFirst(pivot.tm.get(s));
			}
			while(!q2.isEmpty()) {
				q.addFirst(q2.poll());
			}
		}
		bw.flush();
		bw.close();
	}
	static class Tunnel{
		String food;
		TreeMap<String,Tunnel> tm;
		public Tunnel(String food) {
			this.food=food;
			this.tm = new TreeMap<>();
		}
	}
}
