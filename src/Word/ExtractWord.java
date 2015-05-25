package Word;

import java.util.ArrayList;

public class ExtractWord {
	private ArrayList<String> word_list = new ArrayList<String>();
	
	public void execute(String sentence){
		String word = "";
		
		for(int i = 0; i < sentence.length(); i++){
			if((i != 0) && ((sentence.charAt(i) == ' ') || (i == sentence.length()-1))){
				if(i == sentence.length()-1)
					word += sentence.charAt(i);
				word_list.add(word);
				word = "";
			}
			else{
				word += sentence.charAt(i);
			}
		}
	}
	
	public ArrayList<String> getWordList(){	return word_list; }
}
