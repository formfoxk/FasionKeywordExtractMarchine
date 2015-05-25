package Sort;

import DATA.Word;

import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;

public class SortWord {
	ArrayList<Word> word_list;
	
	public void compare(ArrayList<Word> no_rank_word_list){
		word_list = no_rank_word_list;
		
		Comparator<Word> comparator = new Comparator<Word>()
			       {
			             public int compare(Word a1, Word a2)
			             {
			                  if ( a1.getCount() > a2.getCount()) return -1;
			                  else if (a1.getCount() < a2.getCount()) return 1;
			                  else return 0;
			             }
			       };
			       Collections.sort(word_list,comparator);
	}
	public void setWordList(ArrayList<Word> word_list) { this.word_list = word_list; }
	public ArrayList<Word> getWordList() { return word_list; }
}
