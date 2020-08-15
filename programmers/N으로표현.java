package programmers;

import java.util.ArrayList;
import java.util.HashSet;

public class N으로표현 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int solution(int N, int number) {
		if (N == number) {
			return 1;
		}
		HashSet<Long> hs = new HashSet<Long>();
		ArrayList<Long>[] arr = new ArrayList[9];
		arr[1] = new ArrayList<Long>();
		arr[1].add((long) N);
		for (int i = 2; i < arr.length; i++) {
			arr[i] = new ArrayList<Long>();
			long temp = arr[1].get(0) * 10 + N;
			if (hs.add(temp)) {
				arr[i].add(arr[1].get(0) * 10 + N);
				if (arr[i].get(0) == number) {
					return i;
				}
			}
			for (int j = 1; j < i; j++) {
				for (int k = 0; k < arr[j].size(); k++) {
					for (int l = 0; l < arr[i - j].size(); l++) {
						long t = arr[j].get(k) * arr[i - j].get(l);
						if (hs.add(t)) {
							if (t == number) {
								return i;
							}
							arr[i].add(t);
						}
						t = arr[j].get(k) + arr[i - j].get(l);
						if (hs.add(t)) {
							if (t == number) {
								return i;
							}
							arr[i].add(t);
						}
						t = arr[j].get(k) - arr[i - j].get(l);
						if (hs.add(t)) {
							if (t == number) {
								return i;
							}
							arr[i].add(t);
						}
						if (arr[j].get(k) != 0 && arr[i - j].get(l) != 0) {
							t = arr[j].get(k) / arr[i - j].get(l);
							if (hs.add(t)) {
								if (t == number) {
									return i;
								}
								arr[i].add(t);
							}
						}
					}
				}
			}
		}
		return -1;
	}
}
