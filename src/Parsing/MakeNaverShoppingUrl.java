package Parsing;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class MakeNaverShoppingUrl {
	public static String execute(String query)
	{
		String url = null;
		try{
			
			url = "http://shopping.naver.com/search/all_search.nhn?query=" + URLEncoder.encode(query, "UTF-8") + "=&frm=NVSHSRC&=&=&=&=";
		
		}catch (UnsupportedEncodingException e) {
			e.getStackTrace();
		}
		
		return url;
	}
}
