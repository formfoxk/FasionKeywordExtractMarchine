package Parsing;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class MakeBlogUrl {
	public static String execute(String query){
		String api_key = "f000143146fd32e50acdee870d0e98fd";
		//String api_key = "4f4c10ff76acfd0795f62f177d1ec777";
		//String api_key = "7e0b13bade20453aab5ce1cb975a67f6";
		//String api_key = "71c0949c380babbd98114ea37fde86fe";
		String url = null;
		
		try {
			url = "http://openapi.naver.com/search?key=" + api_key
					+ "&query=" + URLEncoder.encode(query, "UTF-8")
					+ "&display=100&start=1&target=blog&sort=sim";
		} catch (UnsupportedEncodingException e) {
			e.getStackTrace();
		}
		
		return url;
	}
	
	public static String execute(String query, int start){
		//String api_key = "f000143146fd32e50acdee870d0e98fd";
		String api_key = "4f4c10ff76acfd0795f62f177d1ec777";
		String url = null;
		
		try {
			url = "http://openapi.naver.com/search?key=" + api_key
					+ "&query=" + URLEncoder.encode(query, "UTF-8")
					+ "&display=100&start=" + start + "&target=blog&sort=sim";
		} catch (UnsupportedEncodingException e) {
			e.getStackTrace();
		}
		
		return url;
	}
}
