package Word;

import java.util.ArrayList;

import DATA.Word;

public class DeleteOverlappingWord2 {
	private ArrayList<Word> word_list;
	
	public void execute(ArrayList<Word> noun_list, ArrayList<Word> check_noun_list){
		word_list = new ArrayList<Word>();
		
		for (int i = 0; i < noun_list.size(); i++) {
			boolean is_exist_word = false;
			for (int j = 0; j < check_noun_list.size(); j++) {
				if (noun_list.get(i).getWord().indexOf(check_noun_list.get(j).getWord()) > -1) {
					is_exist_word = true;
					break;
				}
			}
			if (!is_exist_word)
				word_list.add(new Word(noun_list.get(i).getWord(), noun_list.get(i).getCount()));
			else
				is_exist_word = false;
			
		}
	}
	
	public ArrayList<Word> getWordList() { return word_list; }
}