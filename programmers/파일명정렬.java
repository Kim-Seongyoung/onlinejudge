package programmers;

public class 파일명정렬 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] files = {"F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat" };
		solution(files);
		System.out.println();
	}

	static public String[] solution(String[] files) {
		merge( files, 0, files.length - 1);
		return files;
	}

	static public void merge(String[] files, int start, int end) {
		if (start >= end) {
			return;
		} else {
			String[] answer = new String[end-start+1];
			int mid = (start + end) / 2;
			// 왼쪽 부분
			merge(files, start, mid);
			// 오른쪽 부분
			merge(files, mid + 1, end);
			int left_pivot = start;
			int right_pivot = mid + 1;
			int pivot = 0;
			while (left_pivot != mid + 1 && right_pivot != end + 1) {
				// 각 파일명 비교,	true면 왼쪽, false면 오른쪽
				if (comp(files[left_pivot], files[right_pivot])) {
					answer[pivot] = files[left_pivot];
					left_pivot++;
				} else {
					answer[pivot] = files[right_pivot];
					right_pivot++;
				}
				pivot++;
			}
			// 나머지 파일명들 이동
			if (left_pivot == mid + 1) {
				for (int i = right_pivot; i <= end; i++) {
					answer[pivot] = files[i];
					pivot++;
				}
			} else if (right_pivot == end + 1) {
				for (int i = left_pivot; i <= mid; i++) {
					answer[pivot] = files[i];
					pivot++;
				}
			}
			int j = start;
			// 파일명 배열에 옮김
			for (int i = 0; i < answer.length; i++) {
				files[j] = answer[i];
				j++;
			}
		}
	}

	static boolean comp(String left, String right) {
		String[] left_arr = conv(left);
		String[] right_arr = conv(right);
		// 대소문자 구별하지 않으므로 compareToIgnoreCase 사용
		int head = left_arr[0].compareToIgnoreCase(right_arr[0]);
		if (head <0) {
			return true;
		} else if (head == 0) {
			int number_l = Integer.parseInt(left_arr[1]);
			int number_r = Integer.parseInt(right_arr[1]);
			// 같을 경우 들어온 순서 있므로 왼쪽이 먼저
			if (number_l <= number_r) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	// head와 number로 분리
	static String[] conv(String s) {
		// 인덱스 0이 head 부분, 1이 number,  tail은 정렬에 필요 없으므로 버림
		String[] result = new String[2];
		for (int i = 0; i < s.length(); i++) {
			// 숫자 찾기
			if ('0' <= s.charAt(i) && s.charAt(i) <= '9') {
				// 찾으면 그 전까지는 head 부분
				result[0] = s.substring(0, i);
				boolean flag = true;
				// 숫자가 아닐 때까지 찾기
				for (int j = i + 1; j < s.length(); j++) {
					if (!('0' <= s.charAt(j) && s.charAt(j) <= '9')) {
						// 찾으면 head 부분 이후 부터 현재 인덱스까지는 number 부분
						result[1] = s.substring(i, j);
						flag = false;
						break;
					}
				}
				// 파일명 끝까지 숫자 일때
				if (flag) {
					result[1] = s.substring(i, s.length());
				}
				break;
			}
		}
		return result;
	}
}
