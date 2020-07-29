package programmers;

public class 야근지수 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n =4;
		int[] works= {4,3,3};
		long answer = 0;
		int[] arr = new int[50001];
		for (int i = 0; i < works.length; i++) {
			arr[works[i]]++;
		}
		int pivot = n;
		for (int i = 10; i > 0; i--) {
			if (pivot != 0) {
				if (arr[i] <= pivot) {
					arr[i - 1] += arr[i];
					pivot -= arr[i];
				}else {
					arr[i-1]+=pivot;
					arr[i] -=pivot;
					pivot=0;
					answer =(long) arr[i]*i*i;
				}
			}else {
				answer +=(long) arr[i]*i*i;
			}
		}
		System.out.println(answer);
	}

	public long solution(int n, int[] works) {
		long answer = 0;
		int[] arr = new int[50001];
		for (int i = 0; i < works.length; i++) {
			arr[works[i]]++;
		}
		int pivot = n;
		for (int i = 50000; i > 0; i--) {
			if (pivot != 0) {
				if (arr[i] <= pivot) {
					arr[i - 1] += arr[i];
					pivot -= arr[i];
				}else {
					arr[i-1]+=pivot;
					arr[i] -=pivot;
					pivot=0;
					answer =(long) arr[i]*(long)arr[i];
				}
			}else {
				answer +=(long) arr[i]*(long)arr[i];
			}
		}
		return answer;
	}
}
