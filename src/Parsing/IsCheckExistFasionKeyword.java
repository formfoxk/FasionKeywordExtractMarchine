package Parsing;

import java.util.ArrayList;

import DATA.Word;
import Print.Percentage;
import Word.ExtractWord;

public class IsCheckExistFasionKeyword {
	private ArrayList<Word> word_list = new ArrayList<Word>();
	
	public void execute(ArrayList<Word> check_word_list) {
		System.out.print("IsCheckExistFasionKeyword Start -> ");
		
		ArrayList<String> catagory_list = new ArrayList<String>();
		ParsingCategoryOfNaverShoping pcons = new ParsingCategoryOfNaverShoping();
		
		int count = 1;
		for (Word w : check_word_list) {
			String url = MakeNaverShoppingUrl.execute(w.getWord());
			
			pcons.connection(url);
			catagory_list.add(pcons.getCatagory());
			
			if (!pcons.getCatagory().equals("")) { 
				ExtractWord ew = new ExtractWord();
				ew.execute(pcons.getCatagory());
				if((ew.getWordList().get(1).indexOf("패션") > -1) 
						&& ((ew.getWordList().size() > 2)
						&& ((ew.getWordList().get(2).indexOf("패션") > -1)
								|| (ew.getWordList().get(2).indexOf("여성") > -1)
								|| (ew.getWordList().get(2).indexOf("남성") > -1)))) // 카테코리가 패션이라면 단어 저장
						word_list.add(new Word(w.getWord(), w.getCount()));
			}
			Percentage.execute(count++, check_word_list.size());
		}
		
		System.out.println("Finish");
	}
	public ArrayList<Word> getWordList() { return word_list; }
}
