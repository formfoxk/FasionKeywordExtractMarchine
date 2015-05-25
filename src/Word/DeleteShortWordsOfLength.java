package Word;

import java.util.ArrayList;

public class DeleteShortWordsOfLength {
	private ArrayList<String> word_list;
	
	public void execute(ArrayList<String> check_word_list){
		word_list = new ArrayList<String>();
		
		for(String s : check_word_list){
			if(s.length() > 2)
				word_list.add(s);
		}
	}
	
	public ArrayList<String> getWordList() { return word_list; }
}
