package programmers;

import java.util.Arrays;
import java.util.HashSet;

public class 소수찾기 {

	public static void main(String[] args) {
		String numbers = "9275";
		System.out.println(solution(numbers));
	}
	// 조합에 필요한 visit 배열
	static boolean[] v;
	// 찾으면 소수 넣은 HashSet
	static HashSet<Integer> hs;
	// 소수 배열
	static boolean[] prime_number;
	static public int solution(String numbers) {
        int answer = 0;
        hs = new HashSet<Integer>();
        v = new boolean[numbers.length()];
        // 입력 배열의 조합의 최대값
        // 소수 배열 크기 정하기 위해서
        char[] temp = numbers.toCharArray();
        Arrays.sort(temp);
        int sum = 0;
        for (int i = temp.length-1; i >=0; i--) {
			sum = sum*10+(temp[i]-'0');
		}
        prime_number = new boolean[sum+1];
        prime_number[0] = true;
        prime_number[1] = true;
        // 에라토스테네스의 체를 이용하여 소수 배열 
        for (int i = 2; i < prime_number.length; i++) {
			if(!prime_number[i]) {
				for (int j = i+i; j < prime_number.length; j=j+i) {
					prime_number[j] = true;
				}
			}
		}
        // 조합을 통해 모든 경우의 수 찾기
        combi(numbers,0);
        // 구한 소수의 갯수
        answer = hs.size();
        return answer;
    }
	static void combi(String numbers,int cnt) {
		// 소수 판별
		if(!prime_number[cnt]) {
			hs.add(cnt);
		}
		// 조합 구하기
		for (int i = 0; i < numbers.length(); i++) {
			if(!v[i]) {
				v[i] = true;
				combi(numbers, cnt*10+( numbers.charAt(i)-'0'));
				v[i] = false;
			}
		}
	}
}
