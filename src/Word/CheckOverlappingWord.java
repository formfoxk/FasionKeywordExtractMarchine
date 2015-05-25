package Word;

import java.util.ArrayList;

import DATA.Word;

public class CheckOverlappingWord {
	private ArrayList<Word> object_word_list = new ArrayList<Word>();
	private ArrayList<String> string_word_list = new ArrayList<String>();
	
	public void execute(ArrayList<String> content_word_list){
		for(int i = content_word_list.size() - 1; i >= 0; i--){
			boolean is_check_overlap = false;
			for(int j = 0; j < object_word_list.size(); j++){
				if(content_word_list.get(i).toString().equals(object_word_list.get(j).getWord())){
					is_check_overlap = true;
					object_word_list.get(j).setCount(object_word_list.get(j).getCount() + 1);
					break;
				}
			}
			if(!is_check_overlap){
				object_word_list.add(new Word(content_word_list.get(i).toString(), 0));
				string_word_list.add(content_word_list.get(i).toString());
			}
		}
	}
	
	public ArrayList<String> getStringWordList() { return string_word_list; }
	public ArrayList<Word> getObjectWordList() { return object_word_list; }
}
