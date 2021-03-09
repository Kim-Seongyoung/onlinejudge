package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Main_2632 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int size = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		int[] arrM = new int[m];
		int[] arrN = new int[n];
		HashMap<Integer, Integer> hmM = new HashMap<Integer, Integer>();
		hmM.put(0, 1);
		HashMap<Integer, Integer> hmN = new HashMap<Integer, Integer>();
		hmN.put(0, 1);
		int sum = 0;
		for (int i = 0; i < m; i++) {
			arrM[i] = Integer.parseInt(br.readLine());
			sum += arrM[i];
		}
		hmM.put(sum, 1);
		sum = 0;
		for (int i = 0; i < n; i++) {
			arrN[i] = Integer.parseInt(br.readLine());
			sum += arrN[i];
		}
		hmN.put(sum, 1);
		search(arrM, hmM,size);
		search(arrN, hmN,size);
		int count = 0;
		Iterator<Integer> iter ;
		if(hmM.size()<hmN.size()) {
			iter = hmM.keySet().iterator();
			count = cal(iter, hmM, hmN, size);
		}else {
			iter = hmN.keySet().iterator();
			count = cal(iter, hmN, hmM, size);
		}
		System.out.println(count);
		
	}
	static int cal(Iterator<Integer> iter, HashMap<Integer, Integer> hm1,HashMap<Integer, Integer> hm2,int size) {
		int pivot1, pivot2;
		int count = 0;
		while (iter.hasNext()) {
			pivot1 = iter.next();
			pivot2 = size - pivot1;
			if (hm2.containsKey(pivot2)) {
				count += (hm1.get(pivot1) * hm2.get(pivot2));
			}
		}
		return count;
	}
	static void search(int[] arr, HashMap<Integer, Integer> hm, int size) {
		int sum = 0;
		int idx = 0;
		int count = 0;
		for (int i = 0; i < arr.length; i++) {
			sum = 0;
			for (int j = 0; j < arr.length - 1; j++) {
				idx = (i + j) % arr.length;
				sum += arr[idx];
				if (sum <= size) {
					count = 1;
					if (hm.containsKey(sum)) {
						count += hm.get(sum);
					}
					hm.put(sum, count);
				}else {
					break;
				}
			}
		}
	}

}
