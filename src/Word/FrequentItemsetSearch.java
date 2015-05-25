package Word;

import java.util.ArrayList;

public class FrequentItemsetSearch {
	private ArrayList<String> compound_word_list;
	private ArrayList<String> one_height_word_list;
	private ArrayList<String> two_height_word_list;
	
	public void execute(ArrayList<String> word_list, String content) {
		compound_word_list = new ArrayList<String>();
		one_height_word_list = new ArrayList<String>();
		two_height_word_list = new ArrayList<String>();
		
		// height 1
		for (int i = 0; i < word_list.size(); i++) {
			for (int j = i; j < word_list.size(); j++) {
				if (i != j) {
					String compound = null;
					compound = (word_list.get(i).toString() + " " + word_list.get(j).toString()); // 복합어
					
					boolean is_check_exist_word; // 복합어가 내용에 존재 하는지 확인
					is_check_exist_word = CheckFindIncludedWord.execute(content, compound);
					if(is_check_exist_word) one_height_word_list.add(compound); // 복합어가 내용에 존재 한다면 저장
				}
			}
		}
		// height 2
		for (int i = 0; i < one_height_word_list.size(); i++) {
			for (int j = i; j < one_height_word_list.size(); j++) {
				if (i != j) {
					String word1, word2;
					word1 = one_height_word_list.get(i);
					word2 = one_height_word_list.get(j);
					
					ExtractWord ew = new ExtractWord();
					ew.execute(word2);
					
					String separated_word1, separated_word2;
					separated_word1 = ew.getWordList().get(0);
					separated_word2 = ew.getWordList().get(1);
					
					boolean is_check_exist_word1, is_check_exist_word2; // 복합어가 내용에 존재 하는지 확인
					is_check_exist_word1 = CheckFindIncludedWord.execute(word1, separated_word1);
					is_check_exist_word2 = CheckFindIncludedWord.execute(word1, separated_word2);
					
					String compound = null;
					if(!(is_check_exist_word1||is_check_exist_word2))
						compound = (word1 + " " + word2); // 복합어
					else if(!is_check_exist_word1)
						compound = (word1 + " " + separated_word1); // 복합어
					else if(!is_check_exist_word2)
						compound = (word1 + " " + separated_word2); // 복합어

					if (compound != null) {
						boolean is_check_exist_word3; // 복합어가 내용에 존재 하는지 확인
						is_check_exist_word3 = CheckFindIncludedWord.execute(content, compound);
						if (is_check_exist_word3)
							two_height_word_list.add(compound); // 복합어가 내용에 존재 한다면 저장
					}
				}
			}
		}
		for(String s : one_height_word_list)
			compound_word_list.add(s);
		for(String s : two_height_word_list)
			compound_word_list.add(s);
	}
	
	public ArrayList<String> getWordList() { return compound_word_list; }
}
