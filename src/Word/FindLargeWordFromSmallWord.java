package Word;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import DATA.Word;

public class FindLargeWordFromSmallWord {
	private ArrayList<Word> word_list;
	
	public void execute(ArrayList<Word> compared_word_list, ArrayList<Word> compare_word_list){
		word_list = new ArrayList<Word>();
		HashSet<Word> contained_word_list = new HashSet<Word>();
		
		for(int i = 0; i < compared_word_list.size(); i++){
			for(int j = 0; j < compare_word_list.size(); j++)
				 if ((compared_word_list.get(i).getWord().indexOf(compare_word_list.get(j).getWord()) > -1)
						 && (!compared_word_list.get(i).getWord().equals(compare_word_list.get(j).getWord()))) { 
					 contained_word_list.add(new Word(compare_word_list.get(j).getWord(), compare_word_list.get(j).getCount())); 
				 }
		}
		
		//중복 단어 제거
		ArrayList<Word> delete_word_list = new ArrayList<Word>();
		Iterator it = contained_word_list.iterator(); 
		while(it.hasNext()){
			delete_word_list.add((Word)it.next());
		}
		
		// 단어 삭제
		int i = 0, j = 0;
		OUTER : while(i < compared_word_list.size()){
			j = 0;
			while(j < delete_word_list.size()){
				if(compared_word_list.get(i).getWord().equals(delete_word_list.get(j).getWord())){
					i++;
					continue OUTER;
				}
				j++;
			}
			word_list.add(compared_word_list.get(i));
			i++;
		}
	}
	
	public ArrayList<Word> getWordList() { return word_list; }
}
