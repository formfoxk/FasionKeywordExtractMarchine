package MorphemeAnalysor;

import java.util.ArrayList;

import org.snu.ids.ha.index.Keyword;
import org.snu.ids.ha.index.KeywordExtractor;
import org.snu.ids.ha.index.KeywordList;

public class ExtractNounFromContent {
	private KeywordExtractor ke = new KeywordExtractor();
	private KeywordList keyList;
	private ArrayList<String> word_list;
	
	public void execute(String content){
		word_list = new ArrayList<String>();
		keyList = ke.extractKeyword(content, false);
		
		for (Keyword keyword : keyList){
			if((keyword.getTag().equals("NNG"))
					&& (keyword.getString().length() > 1)){ //명사인지 확인 및 한글자인 단어 제외
				word_list.add(keyword.getString());
			}
		}
	}
	public void setWordList(ArrayList<String> word_list) { this.word_list = word_list;}
	public ArrayList<String> getWordList(){	return word_list; }
}
