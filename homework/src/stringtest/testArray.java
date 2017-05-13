package stringtest;

public class testArray {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] values = {"123","abc","Ò»¶þÈý"};
		String loadvalues="";
		for(int i=0;i<values.length;i++){
			loadvalues+=values[i]+"|";
		}
		System.out.println(loadvalues);
	}

}
