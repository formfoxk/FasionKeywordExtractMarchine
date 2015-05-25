package Tf_Idf;

import java.util.ArrayList;

import DATA.Word;

public class Tf_idf {
	ArrayList<Word> tf_idf_word_list;
	
	public void execute(ArrayList<Word> word_list, int document_set) {
		tf_idf_word_list = new ArrayList<Word>();
		double tf_idf, tf, idf;
		
		for(Word w : word_list){
			tf = logB((1 + w.getCount()), 2.0);
			double divided_num;
			if(w.getCount() == 0)
				divided_num =  document_set / 0.9;
			else
				divided_num =  document_set / w.getCount();
			
			idf = logB((document_set / divided_num), 2.0);
			tf_idf = tf * idf;
			tf_idf_word_list.add(new Word(w.getWord(), (int)tf_idf));
		}
	}

	public double logB(double x, double base) {
		return Math.log(x) / Math.log(base);
	}
	public ArrayList<Word> getWordList() { return tf_idf_word_list; }
}
