package programmers;

public class 스킬트리 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        int[] arr = new int[26];
        int pivot =1;
        for (int i = 0; i < skill.length(); i++) {
			arr[skill.charAt(i)-'A'] = pivot;
			pivot++;
		}
        for (int i = 0; i < skill_trees.length; i++) {
        	pivot =1;
        	boolean flag=  true;
        	for (int j = 0; j < skill_trees[i].length(); j++) {
        		int idx = skill_trees[i].charAt(j)-'A';
        		if(arr[idx]!=0) {
        			if(pivot== arr[idx]){
        				pivot++;
        			}else {
        				flag = false;
        				break;
        			}
        		}
			}
        	if(flag) {
        		answer++;
        	}
		}
        return answer;
    }
}
