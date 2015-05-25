package Tf_Idf;

import java.util.ArrayList;

import DATA.WordDB;
import MorphemeAnalysor.ExtractNounFromContent;
import Parsing.MakeBlogUrl;
import Parsing.ParsingAddressOfBlog;
import Parsing.ParsingContentOfBlog;
import Print.Percentage;
import Sort.SortWord;
import Word.CheckOverlappingWord;

public class GetWordList {
	private CheckOverlappingWord cow_link = new CheckOverlappingWord();

	public void getLink(String query){
		System.out.print("GetLink Start ");
		
		String url100, url200, url300, url400, url500, url600, url700, url800, url900, url1000;
		url100 = MakeBlogUrl.execute(query,1);
		url200 = MakeBlogUrl.execute(query, 101);
		url300 = MakeBlogUrl.execute(query, 201);
		url400 = MakeBlogUrl.execute(query, 301);
		url500 = MakeBlogUrl.execute(query, 401);
		url600 = MakeBlogUrl.execute(query, 501);
		url700 = MakeBlogUrl.execute(query, 601);
		url800 = MakeBlogUrl.execute(query, 701);
		url900 = MakeBlogUrl.execute(query, 801);
		url1000 = MakeBlogUrl.execute(query, 901);
		
		ArrayList<String> url_list = new ArrayList<String>();
		url_list.add(url100);
		url_list.add(url200);
		url_list.add(url300);
		url_list.add(url400);
		url_list.add(url500);
		url_list.add(url600);
		url_list.add(url700);
		url_list.add(url800);
		url_list.add(url900);
		url_list.add(url1000);
		
		ParsingAddressOfBlog paob = new ParsingAddressOfBlog();
		for(int i = 0; i < url_list.size(); i++)
			paob.connection(url_list.get(i).toString()); // 링크 저장
	
		cow_link.execute(paob.getAddressList()); // 중복된 링크 제거 및 저장
		
		System.out.println("GetLink Finish");
	}
	public void extractWord(){
		System.out.println("ExtractWord Start");
		
		ParsingContentOfBlog pcob = new ParsingContentOfBlog();
		ExtractNounFromContent enfc = new ExtractNounFromContent();
		CheckOverlappingWord cow_word = new CheckOverlappingWord();
		
		for(int i = 0; i < cow_link.getStringWordList().size(); i++){
			pcob.connection(cow_link.getStringWordList().get(i).toString());
			enfc.execute(pcob.getContent().toString()); // 명사추출
			cow_word.execute(enfc.getWordList()); // 중복 단어 제거 및 저장
			
			System.out.println(i);
			Percentage.execute(i+1, cow_link.getStringWordList().size());
		}
		
		SortWord sw = new SortWord();
		sw.compare(cow_word.getObjectWordList());
		
		Tf_idf tf_idf = new Tf_idf();
		tf_idf.execute(sw.getWordList(), cow_link.getStringWordList().size());
		
		WordDB awldb = new WordDB("tf_idf_word_list");
		awldb.allDelete();
		awldb.insert(tf_idf.getWordList());	
		
		System.out.println("ExtractWord Finish");
	}
	
	public void execute(){
		getLink("2014년 남자 봄 패션");
		getLink("2014년 남성 봄 패션");
		getLink("2014년 남자 봄 패션");
		getLink("2014년 남성 봄 패션");
		getLink("2014년 여자 여름 패션");
		getLink("2014년 여성 여름 패션");
		getLink("2014년 여자 여름 패션");
		getLink("2014년 여성 여름 패션");
		
		extractWord();
	}
}
