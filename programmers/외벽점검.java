package programmers;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Comparator;
public class 외벽점검 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public int solution(int n, int[] weak, int[] dist) {
        int answer = -1;
		Arrays.sort(dist);
		for (int i = 1; i <= dist.length; i++) {
			if (search(0, i, n, new boolean[weak.length], weak, dist)) {
				return i;
			}
		}
		return answer;
    }
    boolean search(int cnt, int size, int n, boolean[] v, int[] weak, int[] dist) {
		if (cnt == size) {
			LinkedList<Integer> q = new LinkedList<>();
			int start = weak[0];
			for (int i = 0; i < weak.length; i++) {
				if (v[i]) {
					q.add(weak[i] - start);
					if (i + 1 != weak.length) {
						start = weak[i + 1];
					}
				}
			}
			if (!v[weak.length - 1]) {
				q.add(weak[0] + n - start + q.remove(0));
			}
			Collections.sort(q, new Comparator<Integer>() {

				@Override
				public int compare(Integer o1, Integer o2) {
					return o2.intValue() - o1.intValue();
				}
			});
			if (q.size() <= dist.length) {
				for (int i = dist.length - 1; i >= 0; i--) {
					if (dist[i] < q.poll()) {
						return false;
					}
					if (q.isEmpty()) {
						return true;
					}
				}
				return true;
			} else {
				return false;
			}
		} else {
			for (int i = cnt; i < weak.length; i++) {
				
					v[i] = true;
					if (search(cnt + 1, size, n, v, weak, dist)) {
						return true;
					}
					v[i] = false;
				
			}
			return false;
		}
	}
}
