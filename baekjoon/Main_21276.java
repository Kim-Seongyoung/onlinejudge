package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class Main_21276 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new  BufferedWriter(new OutputStreamWriter(System.out));
		br.readLine();
		StringTokenizer st = new StringTokenizer(br.readLine());
		TreeMap<String, Person> tm = new TreeMap<>();
		String s;
		while(st.hasMoreTokens()) {
			s = st.nextToken();
			tm.put(s, new Person(s));
		}
		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i <M; i++) {
			st = new StringTokenizer(br.readLine());
			String a,b;
			a= st.nextToken();
			b= st.nextToken();
			tm.get(a).parent_num++;
			tm.get(b).child.add(a);
		}
		LinkedList<Person> q = new LinkedList<>();
		Iterator<String> iter = tm.keySet().iterator();
		while(iter.hasNext()) {
			s = iter.next();
			if(tm.get(s).parent_num==0) {
				q.add(tm.get(s));
			}
		}
		Collections.sort(q, new Comparator<Person>() {
			@Override
			public int compare(Person p1, Person p2) {
				return p1.name.compareTo(p2.name);
			}
		});
		bw.append(q.size()+"\n");
		while(!q.isEmpty()) {
			Person p = q.poll();
			bw.append(p.name+" ");
			LinkedList<Person> node = new LinkedList<>();
			node.add(p);
			while(!node.isEmpty()) {
				Person cnt = node.poll();
				iter = cnt.child.iterator();
				while(iter.hasNext()) {
					s=iter.next();
					if(tm.get(s).parent_num>=2) {
						iter.remove();
						tm.get(s).parent_num--;
					}else {
						node.add(tm.get(s));
					}
				}
				
			}
		}
		bw.newLine();
		iter = tm.keySet().iterator();
		while(iter.hasNext()) {
			s = iter.next();
			bw.append(s+" "+tm.get(s).child.size());
			Iterator<String> child_iter = tm.get(s).child.iterator();
			while(child_iter.hasNext()) {
				bw.append(" "+child_iter.next());
			}
			bw.newLine();
		}
		bw.flush();
		bw.close();
	}
	static class Person{
		String name;
		int parent_num;
		TreeSet<String> child;
		Person(String name){
			this.name=name;
			this.parent_num=0;
			this.child=new TreeSet<String>();
		}
	}
}
