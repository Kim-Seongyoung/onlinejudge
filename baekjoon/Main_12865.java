package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main_12865 {

	public static void main(String[] args) throws IOException {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st =new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		Point[] points = new Point[N];
		int K = Integer.parseInt(st.nextToken());
		int[][] arr = new int[N][K+1];
		for (int i = 0; i < N; i++) {
			st=  new StringTokenizer(br.readLine());
			points[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(points, new Comparator<Point>() {

			@Override
			public int compare(Point arg0, Point arg1) {
				return arg0.weight-arg1.weight;
			}
		});
		points[0].weight =points[0].weight>K ? K+1 : points[0].weight; 
		for (int i = 1; i < points[0].weight; i++) {
			arr[0][i]=0;
		}
		for (int i =  points[0].weight; i <= K; i++) {
			arr[0][i]=points[0].value;
		}
		for (int i = 1; i < N; i++) {
			points[i].weight =points[i].weight>K ? K+1 : points[i].weight; 
			for (int j = 1; j < points[i].weight; j++) {
				arr[i][j] = arr[i-1][j];
			}
			for (int j =points[i].weight; j <=K; j++) {
				arr[i][j] = Math.max(arr[i-1][j-points[i].weight]+points[i].value, arr[i-1][j]);
			}
		}
		System.out.println(arr[N-1][K]);
		
	}
	static class Point{
		int weight;
		int value;
		Point(int weight, int value){
			this.weight =weight;
			this.value = value;
		}
	}
}
