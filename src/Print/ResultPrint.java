package Print;

import DATA.WordDB;

public class ResultPrint {
	public static void execute(String db_name){
		WordDB rwld = new WordDB(db_name);
		for(int i = 0; i < rwld.getWordList().size(); i++)
			System.out.println((i+1) + ". " + rwld.getWordList().get(i).getWord());
	}
}
