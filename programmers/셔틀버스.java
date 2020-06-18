package programmers;

import java.util.Arrays;

public class 셔틀버스 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 1;
		int t = 1;
		int m = 1;
		String[] timetable = {"23:59"};
		System.out.println(solution(n, t, m, timetable));
	}

	static public String solution(int n, int t, int m, String[] timetable) {
		String answer = "";
		Bus[] bus = new Bus[n];
		int[] arr = new int[timetable.length];
		// 09:00를 정수로 표현
		int time = 540;
		bus[0] = new Bus(time);
		// 버스 운행 시간
		for (int i = 1; i < n; i++) {
			time += t;
			bus[i] = new Bus(time);
		}
		// 사람들이 정류장에 오는 시간을 정수로 표현
		for (int i = 0; i < timetable.length; i++) {
			arr[i] = Integer.parseInt(timetable[i].substring(0, 2)) * 60
					+ Integer.parseInt(timetable[i].substring(3, 5));
		}
		// 먼저 오는 사람 순으로 정렬
		Arrays.sort(arr);
		int pivot = 0;
		// 각 사람이 탈 수 있는 버스에 타는 사람 수를 늘려준다.
		for (int i = 0; i < arr.length && pivot<n; i++) {
			// 현재 사람이 탈 수 있는 버스 찾기
			if (bus[pivot].t >= arr[i]) {
				bus[pivot].m++;
				// 시간이 같은 사람이 오면 번호가 낮은 사람으로 기준
				if(bus[pivot].last==-1 || arr[i] > arr[bus[pivot].last]) {
					bus[pivot].last = i;
				}
				// 버스에 사람이 꽉 찰 경우
				if (bus[pivot].m == m) {
					pivot++;
				}
			}else {
				i--;
				pivot++;
			}
		}
		int temp = 0;
		// 맨 마지막 버스에 사람이 탈 수 있는 찾기
		if (bus[n - 1].m != m) {
			// 탈 수 있으면 그 버스가 오는 시간
			temp = bus[n - 1].t;

		} else {
			// 못 타면 마지막 사람의 시간에서 1초 빠른 시간
			temp = arr[bus[n - 1].last] - 1;
		}
		// 정수형 시간을 hh:mm으로 변환
		int hour = temp / 60;
		int minute = temp % 60;
		if (hour < 10) {
			answer = "0";
		}
		answer += hour + ":";
		if (minute < 10) {
			answer += "0";
		}
		answer += minute;
		return answer;
	}

	static class Bus {
		// 시간, 탄 사람 수, 마지막에 타는 사람 번호
		int t;
		int m;
		int last;

		Bus(int t) {
			this.t = t;
			this.m = 0;
			this.last = -1;
		}
	}
}
