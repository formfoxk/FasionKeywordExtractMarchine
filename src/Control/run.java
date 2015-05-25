package Control;

import LOGIC.Parsing;
import LOGIC.Refinement;
import Print.ResultPrint;

public class run {
	public static void extractFasionKeyword(){
		Parsing parsing = new Parsing();
		Refinement refinement = new Refinement();
		String query1, query2, db_name;

		query1 = "2014년 봄 남자 패션";
		query2 = "2014년 봄 남성 패션";
		db_name = "spring_man_fasion_keyword";
		parsing.execute(query1, query2);
		refinement.execute(db_name);
		
		query1 = "2014년 봄 여자 패션";
		query2 = "2014년 봄 여성 패션";
		db_name = "spring_woman_fasion_keyword";
		parsing.execute(query1, query2);
		refinement.execute(db_name);
		
		query1 = "2014년 여름 남자 패션";
		query2 = "2014년 여름 남성 패션";
		db_name = "summer_man_fasion_keyword";
		parsing.execute(query1, query2); 
		refinement.execute(db_name);
		
		query1 = "2014년 여름 여자 패션";
		query2 = "2014년 여름 여성 패션";
		db_name = "summer_woman_fasion_keyword";
		parsing.execute(query1, query2);
		refinement.execute(db_name);
	}
	public static void print(){
		System.out.println("spring_man_fasion_keyword");
		ResultPrint.execute("spring_man_fasion_keyword");
		
		System.out.println("spring_woman_fasion_keyword");
		ResultPrint.execute("spring_woman_fasion_keyword");
		
		System.out.println("summer_man_fasion_keyword");
		ResultPrint.execute("summer_man_fasion_keyword");
		
		System.out.println("summer_woman_fasion_keyword");
		ResultPrint.execute("summer_woman_fasion_keyword");
	}
	public static void main(String[] args){
		extractFasionKeyword();
		print();
	}
}
