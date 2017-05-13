package myJava;

import java.util.Scanner;

public class deliverBiscuits {

	/**
	 * @param args
9999999999999X
3

测试用例:
9XXXXXXXXXXXXXXXXX
1

对应输出应该为:

100000000000000000
	 */
	static int result=0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String k = sc.nextLine();
		int n = sc.nextInt();
		
		long start = Long.valueOf(k.replaceAll("X", "0"));
		long end = Long.valueOf(k.replaceAll("X", "9"));
//		System.out.println("k:"+k+" & n:"+n);
//		System.out.println("start:"+start+" & end:"+end);
//		int i;
		int length = k.length();
		long temp;
//		for(i=0;i<10;i++){
////			System.out.println(k.replace('X', (char) i));
//			temp = Long.valueOf(k.replaceAll("X", String.valueOf(i)));
////			System.out.println("temp:"+temp);
//			if(temp%n==0){
//				result++;
//			}
//		}
		
		long i;
		for(i=start;i<=end;i++){
			if(i%n==0){
				result++;
			}
		}
		System.out.println(result);
	}

}
