package programmers;

public class 신규아이디추천 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(solution("z-+.^."));
	}
	public static String solution(String new_id) {
		String answer = "";
        StringBuilder sb = new StringBuilder();
        new_id = new_id.toLowerCase();
        for (int i = 0; i < new_id.length(); i++) {
			if(('a'<=new_id.charAt(i)&& new_id.charAt(i)<='z')||new_id.charAt(i)=='_' || new_id.charAt(i)=='-'||('0'<= new_id.charAt(i)&& new_id.charAt(i)<='9')) {
				sb.append(new_id.charAt(i));
			}else if(new_id.charAt(i)=='.') {
				if(sb.length()!=0 && sb.charAt(sb.length()-1)!='.') {
					sb.append(new_id.charAt(i));
				}
			}
		}

        if(sb.length()!=0) {
        	if(sb.charAt(sb.length()-1)=='.') {
            	sb.deleteCharAt(sb.length()-1);
            }	
        }else {
        	sb.append('a');
        }
        if(sb.length()>=16) {
        	if(sb.charAt(14)=='.') {
            	answer= sb.substring(0,14);
        	}else {
            	answer= sb.substring(0,15);
        	}
        }else if(sb.length()<=2) {
        	answer = sb.toString();
        	while(answer.length()<3) {
        		answer+=sb.charAt(sb.length()-1);
        	}
        }else {
        	answer = sb.toString();
        }
        return answer;
    }
}
