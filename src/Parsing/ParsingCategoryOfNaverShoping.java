package Parsing;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class ParsingCategoryOfNaverShoping {
	private String catagory;
	
	public void connection(String address) {
		Document doc;
		Elements elems;
		catagory = "";
		
		try {
			doc = Jsoup.connect(address).get();
			elems = doc.select(".finder_col");
			if(elems.isEmpty() || elems.equals("") || elems.get(0).text().equals("")) return;
			catagory = elems.get(0).text();
		} catch (IOException e) {
			connection(address);
		}
	}
	
	public String getCatagory() { return catagory; }
}
