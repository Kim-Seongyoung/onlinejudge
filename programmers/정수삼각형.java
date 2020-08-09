package programmers;

public class 정수삼각형 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] triangle = {{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}};
		solution(triangle);
	}
	static public int solution(int[][] triangle) {
        int answer = 0;
        int size = triangle.length-1;
        int[][] arr = new int[triangle.length][triangle[size].length];
        for (int i = 0; i < size; i++) {
			for (int j = 0; j < triangle[i].length; j++) {
				arr[i+1][j] = Math.max(arr[i][j]+triangle[i][j],arr[i+1][j]);
				arr[i+1][j+1] = Math.max(arr[i][j]+triangle[i][j],arr[i+1][j+1]);
			}
		}
        for (int i = 0; i < arr[size].length; i++) {
			answer = Math.max(answer, arr[size][i]+triangle[size][i]);
		}
        return answer;
    }
}
