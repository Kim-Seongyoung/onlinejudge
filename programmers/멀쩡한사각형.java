package programmers;

public class 멀쩡한사각형 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(solution(8,10));
	}
	static public long solution(int w, int h) {
        int max = Math.max(w, h);
        int min = Math.min(w, h);
        int tempa = max;
        int tempb = min;
        while(min!=0) {
        	int temp = max%min;
        	max = min;
        	min = temp;
        }        
        return (long)w*(long)h-(w+h-max);
    }
}
