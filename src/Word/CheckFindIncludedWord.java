package Word;

public class CheckFindIncludedWord {
	public static boolean execute(String compared_word, String compare_word){
		if(compared_word.indexOf(compare_word) > -1) return true;
		else return false;
	}
}
