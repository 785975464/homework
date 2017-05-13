package test;

public class t {

	/**
	 * @param args
	 * 第二次发布
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array={1,4,2,5};
		sort(array);
		print(array);
	}

	private static void print(int[] array) {
		// TODO Auto-generated method stub
		for(int i=0;i<array.length;i++){
			System.out.print(array[i]+" ");
		}
	}

	private static void sort(int[] array) {
		// TODO Auto-generated method stub
		for(int i=0;i<array.length;i++){
			array[i]++;
		}
	}

}
