package myJava;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class myCollection {

	/**
	 * @param args
	 * 测试用例:
		1 100 1 100
		
		对应输出应该为:
		
		6087
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int w = sc.nextInt();
		int x = sc.nextInt();
		int y = sc.nextInt();
		int z = sc.nextInt();
		
		Set set = new HashSet();
		int i,j;
		for(i=w;i<=x;i++){
			for(j=y;j<=z;j++){
				set.add(i*1.0/j);
			}
		}
//		Iterator it = set.iterator();
//		while(it.hasNext()){
//			System.out.println(it.next());
//		}
		System.out.println("size:"+set.size());
	}

}
