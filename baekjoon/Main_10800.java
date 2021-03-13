package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main_10800 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		Player[] players = new Player[N];
		int[] count = new int[N];
		StringTokenizer st;
		for (int i = 0; i < players.length; i++) {
			st = new StringTokenizer(br.readLine());
			players[i] = new Player(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), i);
		}
		Arrays.sort(players, new Comparator<Player>() {
			@Override
			public int compare(Player o1, Player o2) {
				if (o1.size > o2.size) {
					return 2;
				} else if (o1.size == o2.size) {
					if (o1.color > o2.color) {
						return 1;
					}else if(o1.color==o2.color) {
						return 0;
					} else {
						return -1;
					}
				} else {
					return -2;
				}
			}
		});
		int[] color = new int[N + 1];
		int idx = 0;
		int idx2 = 0;
		int pivot, temp=0,temp2=0;
		while (idx < N) {
			idx2 = idx;
			pivot = 0;
			temp=0;
			while (idx2 < N && players[idx].size == players[idx2].size) {
				if (pivot == players[idx2].color) {
					temp += players[idx2].size;
					count[players[idx2].index] = temp2;
				} else {
					color[pivot] += temp;
					pivot = players[idx2].color;
					temp = players[idx2].size;
					temp2=color[0] - color[pivot];
					count[players[idx2].index] = temp2;
				}
				idx2++;
			}
			color[pivot] += temp;
			color[0] += (idx2 - idx) * players[idx].size;
			idx = idx2;
		}
		for (int i = 0; i < N; i++) {
			bw.append(count[i] + "\n");
		}
		bw.flush();
		bw.close();
	}

	static class Player {
		int color, size, index;

		Player(int color, int size, int index) {
			this.color = color;
			this.size = size;
			this.index = index;
		}
	}
}
