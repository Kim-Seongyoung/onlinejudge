package programmers;

public class 숫자야구 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] baseball = {{123, 1, 1}, {356, 1, 0}, {327, 2, 0}, {489, 0, 1}};
		System.out.println(solution(baseball));
	}

	static public int solution(int[][] baseball) {
		int answer = 0;
		// 3자리라서 3중 for문을 이용해서 완전 탐색을 진행 
		for (int i = 1; i <= 9; i++) {
			for (int j = 1; j <= 9; j++) {
				// 중복이 안되기 때문
				if (j != i) {
					for (int k = 1; k <= 9; k++) {
						// 중복이 안되기 때문
						if(k!=j && k!=i) {
							boolean flag = true;
							// 입력을 비교하여 조건에 부합하는지 찾는다
							for (int l = 0; l < baseball.length; l++) {
								int strike = 0;
								int ball = 0;
								int temp = baseball[l][0];
								int[] num = new int[3];
								num[0] = temp/100;
								temp =temp%100;
								num[1] = temp/10;
								temp = temp% 10;
								num[2] = temp;
								// 첫번째 자리
								if(num[0] ==i) {
									strike++;
								}else if(i==num[1] || i==num[2]) {
									ball++;
								}
								// 두번째 자리
								if(num[1] ==j) {
									strike++;
								}else if(j==num[0] || j==num[2]) {
									ball++;
								}
								// 세번째 자리
								if(num[2] ==k) {
									strike++;
								}else if(k==num[1] || k==num[0]) {
									ball++;
								}
								// 입력이랑 하나라도 틀리면 불가능 하기 때문
								if(strike != baseball[l][1] || ball != baseball[l][2]) {
									flag = false;
									break;
								}
							}
							if(flag) {
								answer++;
							}
						}
					}
				}
			}
		}
		return answer;
	}

}
