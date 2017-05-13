package myJava;

import java.util.ArrayList;
import java.util.Scanner;

public class removeDupElement {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int size = sc.nextInt();
		String temp = sc.nextLine();//´æ·Å»»ÐÐ
		int[] sequence= new int[size];
		int i;
		for(i=0 ; i<size ; i++){
			sequence[i] = sc.nextInt();
		}
		ArrayList<Integer> arr = new ArrayList<Integer>();
		for(i=size-1 ; i>=0 ; i--){
			if(!arr.contains(sequence[i])){
				arr.add(sequence[i]);
			}
		}
		int arrSize = arr.size();
		System.out.print(arr.get(arrSize-1));
		if(arrSize>1){
			for(i=arrSize-2 ; i>=0 ; i--){
				System.out.print(" "+arr.get(i));
			}
		}
	}
}
