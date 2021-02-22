package programmers;

import java.util.StringTokenizer;

public class 광고삽입 {
//5459 - 645
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String play_time = "02:00:00";
		String adv_time = "01:30:00";
		String[] logs = { "01:00:00-02:00:00" };
		System.out.println(solution(play_time, adv_time, logs));
	}

	static public String solution(String play_time, String adv_time, String[] logs) {
		String answer = "";
		int playTime = timeToNum(play_time);
		long[] arr = new long[playTime+2];
		int advTime = timeToNum(adv_time);
		for (int i = 0; i < logs.length; i++) {
			StringTokenizer st = new StringTokenizer(logs[i], "-");
			String s1 = st.nextToken();
			String s2 = st.nextToken();
			arr[timeToNum(s1)+1]++;
			arr[timeToNum(s2)+1]--;
		}
		long pivot =arr[0];
		for (int i = 1; i < arr.length; i++) {
			if(arr[i]!=0) {
				pivot += arr[i];
			}
			arr[i] =arr[i-1]+pivot;
		}
		int minAdsTime = playTime-advTime;
		long max=0;
		int time = 0;
		long temp = 0;
		for (int i = 0; i <=minAdsTime; i++) {
			temp = arr[i+advTime]-arr[i];
			if(temp>max) {
				max = temp;
				time = i;
			}
		}
		int t = time/3600;
		if(t<10) {
			answer="0";
		}
		answer += t+":";
		time%=3600;
		t= time/60;
		if(t<10) {
			answer+="0";
		}
		answer+=t+":";
		time%=60;
		if(time<10) {
			answer+="0";
		}
		answer+=time;
		return answer;
	}

	static int timeToNum(String time) {
		int result = 0;
		StringTokenizer st = new StringTokenizer(time, ":");
		result += Integer.parseInt(st.nextToken()) * 3600;
		result += Integer.parseInt(st.nextToken()) * 60;
		result += Integer.parseInt(st.nextToken());
		return result;
	}

}
