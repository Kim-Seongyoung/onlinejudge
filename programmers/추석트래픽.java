package programmers;

public class 추석트래픽 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] lines = {"2016-09-15 01:00:04.001 2.0s", "2016-09-15 01:00:07.000 2s"};
		System.out.println(solution(lines));
	}
	static public int solution(String[] lines) {
        int answer = 0;
        Line[] line = new Line[lines.length];
        // 시간을 초단위 변환 및 요청 시간 및 응답 시간을 저장
        for (int i = 0; i < line.length; i++) {
        	// 시 단위
			double conv = Integer.parseInt(lines[i].substring(11,13))*3600;
			// 분 단위
			conv+=Integer.parseInt(lines[i].substring(14,16))*60;
			conv+=Double.parseDouble(lines[i].substring(17,23));
			double time = Double.parseDouble(lines[i].substring(24,lines[i].length()-1));
			line[i] = new Line(conv-time+0.001,conv);
		}
        for (int i = 0; i < line.length; i++) {
        	int count = 1;
			for (int j = i+1; j < line.length; j++) {
				// 기준 응답 시간 + 1초에 요청 시간이나 응답 시간이 작으면 기준 응답 시간 ~ +1초 사이에 존재한다.
				if(line[j].end<line[i].end+1 || line[j].start < line[i].end+1) {
					count++;
				}
				// 기준이 되는 응답 시간에 1초 + 3초(최대 처리 시간)보다 큰 응답 시간을 가지면 요청 시간도 포함되지 않기 때문에
				// 그 이후는 안해도 된다(응답 시간으로 정렬 되어 있기 때문)
				else if(line[i].end+4<line[j].end) {
					break;
				}
			}
			answer = Math.max(count, answer);
		}
        return answer;
    }
	
	static class Line{
		// 요청 시간 및 응답 시간
		double start;
		double end;
		Line(double start, double end){
			this.start=start;
			this.end=end;
		}
	}
}
