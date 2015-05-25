package Parsing;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ParsingContentOfBlog{
	private static int search_count = 0;
	private Document doc;
	private Elements elems, html_content;
	private Element elem;
	private String src, content;
	
	public void connection(String address) {
		try {
			// Address Parsing
			doc = Jsoup.connect(address).get();
			if(doc.select("frame[src]").toString().equals("")) return; // src가 없는 경우
			elems = doc.select("frame[src]"); // CSS 셀렉터
			elem = elems.get(0); // id,
			src = elem.attr("abs:src"); // 절대경로로 변경하여 리턴
			
			// Address(id,post number가 포함된 address) Parsing
			doc = Jsoup.connect(src).get(); // src(소스가 있는 네이버 블로그)의 document 초기화
			html_content = doc.select(".post-view.pcol2");
			if(html_content.isEmpty()) return; // 블로그 내용이 없는 경우
			content = html_content.get(0).text();
		} catch (IOException e) {
			if(search_count++ < 5) // 최대 5번의 링크 파싱 시도를 실패하면 파싱 종료
				connection(address);
		}
	}
	
	public String getContent() { return content; }
}
