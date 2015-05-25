package LOGIC;

import Parsing.IsCheckExistFasionKeyword;
import Word.DeleteOverlappingWord2;
import Word.FindLargeWordFromSmallWord;
import DATA.Word;
import DATA.WordDB;

public class Refinement {
	private WordDB compound_all_word_list = new WordDB("compound_all_word_list");
	private WordDB tf_idf_word_list = new WordDB("tf_idf_word_list");
	
	void tf_idf(){
		System.out.println("--------------------TF-IDF Start--------------------");
		
		System.out.print("Delete word from tf/idf word -> ");
		DeleteOverlappingWord2 dow = new DeleteOverlappingWord2();
		dow.execute(compound_all_word_list.getWordList(), tf_idf_word_list.getWordList()); // tf/idf에 포함되어있는 단어 삭제
		System.out.println("Finish");
		
		System.out.print("Save DB Start -> ");
		compound_all_word_list.allDelete();
		compound_all_word_list.insert(dow.getWordList());
		System.out.println("Finish");
		
		System.out.println("--------------------TF-IDF Finish--------------------");
	}
	
	void refinement(String db_name){
		System.out.println("--------------------ReFinement Start--------------------");
		
		IsCheckExistFasionKeyword icefk = new IsCheckExistFasionKeyword(); // 네이버 쇼핑 카테고리에서 패션 키워드인거만 추출
		icefk.execute(compound_all_word_list.getWordList());
		
		//WordDB test = new WordDB(db_name);
		FindLargeWordFromSmallWord flwfsw = new FindLargeWordFromSmallWord(); 
		flwfsw.execute(icefk.getWordList(), icefk.getWordList()); // 큰 단어에 작은 단어가 포함되어 있는 경우 작은 단어 제거

			
		System.out.print("Save DB Start -> "); 
		WordDB save_db = new WordDB(db_name);
		save_db.allDelete();
		save_db.insert(flwfsw.getWordList());
		System.out.println("Finish");
		
		System.out.println("--------------------ReFinement Finish--------------------");
	}
	
	public void execute(String db_name){
		tf_idf();
		refinement(db_name);	
	
	}
}
