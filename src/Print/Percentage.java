package Print;

public class Percentage {
	private static int is_check_percentage = -1;
	
	public static void execute(int count, int size){
	
		int percentage = (((count) * 100) / size);
		if((percentage % 5 == 0) && (percentage != is_check_percentage))
			System.out.print(percentage + "% ");
		is_check_percentage = percentage;
	}
}
