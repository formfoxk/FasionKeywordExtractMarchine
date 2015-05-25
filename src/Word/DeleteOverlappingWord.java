package Word;

import java.util.ArrayList;

public class DeleteOverlappingWord {
	private ArrayList<String> word_list;
	
	public void execute(ArrayList<String> noun_list, ArrayList<String> check_noun_list){
		word_list = new ArrayList<String>();
		
		for (int i = 0; i < noun_list.size(); i++) {
			boolean is_exist_word = false;
			for (int j = 0; j < check_noun_list.size(); j++) {
				if (noun_list.get(i).toString().indexOf(check_noun_list.get(j).toString()) > -1) {
					is_exist_word = true;
					break;
				}
			}
			if (!is_exist_word)
				word_list.add(noun_list.get(i).toString());
			else
				is_exist_word = false;
			
		}
	}
	
	public ArrayList<String> getWordList() { return word_list; }
}
