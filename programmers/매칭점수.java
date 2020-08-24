package programmers;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
public class 매칭점수 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public int solution(String word, String[] pages) {
        int answer = 0;
		HashMap<String, HTML> hm = new HashMap<>();
		String url = "<meta property=\"og:url\" content=\"https://";
		String dim = "<body>";
		String dim2 = "<a href=\"https://";
		word = word.toLowerCase();
		for (int i = 0; i < pages.length; i++) {
			String[] arr = pages[i].split(dim);
			String s = arr[0];
			String body = arr[1].toLowerCase();
			String tempURL = s.split(url)[1];
			HTML html = new HTML(i);
			for (int j = 0; j < tempURL.length(); j++) {
				if (tempURL.charAt(j) == '\"') {
					hm.put(tempURL.substring(0, j), html);
					break;
				}
			}
			String[] temp = body.split(dim2);
			for (int j = 1; j < temp.length; j++) {
				for (int k = 0; k < temp[j].length(); k++) {
					if (temp[j].charAt(k) == '\"') {
						html.URL.add(temp[j].substring(0, k));
						break;
					}
				}
			}
			temp = body.split(word);
			for (int j = 1; j < temp.length; j++) {
				if (temp[j].length() == 0) {
					j++;
					while(j<temp.length) {
						if(temp[j].length()==0) {
							j++;
						}else {
							break;
						}
					}
				} else if (temp[j].charAt(0) < 'a' || temp[j].charAt(0) > 'z') {
					html.basicPoint++;
				}
			}
			html.matchingPoint = html.basicPoint;
			html.linkPoint = 1.0 * html.basicPoint / html.URL.size();
		}
		Iterator<String> iter = hm.keySet().iterator();
		while (iter.hasNext()) {
			HTML html = hm.get(iter.next());
			while (!html.URL.isEmpty()) {
				String s = html.URL.poll();
				if (hm.containsKey(s)) {
					hm.get(s).matchingPoint += html.linkPoint;
				}
			}
		}
		iter = hm.keySet().iterator();
		double max = 0;
		while (iter.hasNext()) {
			HTML html = hm.get(iter.next());
			if (max < html.matchingPoint) {
				max = html.matchingPoint;
				answer = html.index;
			} else if (max == html.matchingPoint) {
				if (answer > html.index) {
					answer = html.index;
				}
			}
		}
		return answer;
    }
    class HTML{
		int index;
		int basicPoint;
		double linkPoint;
		double matchingPoint;
		LinkedList<String> URL;
		HTML(int index){
			this.index=index;
			this.basicPoint = 0;
			this.linkPoint = 0;
			this.matchingPoint =0 ;
			URL = new LinkedList<>();
		}
	}
}
