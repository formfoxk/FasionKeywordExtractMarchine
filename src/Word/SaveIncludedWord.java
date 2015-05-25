package Word;

import java.util.ArrayList;

import DATA.Word;

public class SaveIncludedWord {
	CheckOverlappingWord cow = new CheckOverlappingWord();
	
	public void execute(ArrayList<String> compared_word_list, ArrayList<Word> compare_word_list){
		ArrayList<String> word_list = new ArrayList<String>();
		
		for(int i = 0; i < compared_word_list.size(); i++)
			for(int j = 0; j < compare_word_list.size(); j++){
				if(CheckFindIncludedWord.execute(compared_word_list.get(i), compare_word_list.get(j).getWord())){
					word_list.add(compare_word_list.get(j).getWord());
					break;
				}
			}
		
		cow.execute(word_list);
	}
	public ArrayList<Word> getWordList() { return cow.getObjectWordList(); }
}
