package LOGIC;

import DATA.WordDB;
import MorphemeAnalysor.ExtractNounFromContent;
import Parsing.MakeBlogUrl;
import Parsing.ParsingContentOfBlog;
import Parsing.ParsingAddressOfBlog;
import Print.Percentage;
import Sort.SortWord;
import Word.CheckFindIncludedWord;
import Word.CheckOverlappingWord;
import Word.FrequentItemsetSearch;

public class Parsing {
	
	public void execute(String query1, String query2){
		System.out.println("--------------------Parsing Start--------------------");
				
		String api1, api2;
		api1= MakeBlogUrl.execute(query1); // query1의 api 생성
		api2= MakeBlogUrl.execute(query2); // query2의 api 생성
		
		System.out.print("ParsingAddressOfBlog Start -> ");
		ParsingAddressOfBlog plob = new ParsingAddressOfBlog();
		plob.connection(api1); // api1의 100개 블로그 주소 파싱
		plob.connection(api2); // api2의 100개 블로그 주소 파싱
		
		CheckOverlappingWord chw_of_blog_address = new CheckOverlappingWord();
		chw_of_blog_address.execute(plob.getAddressList()); // 중복되는 블로그 주소
		System.out.println("Finish");
		
		System.out.print("Extract Noun Start -> ");
		ParsingContentOfBlog pcob = new ParsingContentOfBlog();
		ExtractNounFromContent enfc = new ExtractNounFromContent();
		FrequentItemsetSearch fis = new FrequentItemsetSearch();
		CheckOverlappingWord cow = new CheckOverlappingWord();
		
		for (int i = 0; i < chw_of_blog_address.getStringWordList().size(); i++)
		{
			pcob.connection(chw_of_blog_address.getStringWordList().get(i).toString()); // 블로그 내용을 파싱
			
			if(!CheckFindIncludedWord.execute(pcob.getContent(), "배송")) // 블로그 내용 중 배송이 있는지
			{
				enfc.execute(pcob.getContent()); // 블로그내용에서 명사추출
				fis.execute(enfc.getWordList(), pcob.getContent()); // 복합어 생성
				cow.execute(fis.getWordList());
			}
			
			Percentage.execute(i+1, chw_of_blog_address.getStringWordList().size());
		}
		System.out.println("Finish");
		
		System.out.print("SortWord Start -> ");
		SortWord sw = new SortWord();
		sw.compare(cow.getObjectWordList());
		System.out.println("Finish");
		
		
		System.out.print("Save DB Start -> ");
		WordDB awldb = new WordDB("compound_all_word_list");
		awldb.allDelete(); // DB에 저장된 단어 모두 삭제
		awldb.insert(sw.getWordList());

		System.out.println("Finish");
		
		System.out.println("--------------------Parsing Finish--------------------");
	}
}
