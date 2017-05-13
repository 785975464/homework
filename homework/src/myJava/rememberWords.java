package myJava;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class rememberWords {

	/**
	 * @param args
3 4
apple orange strawberry
strawberry orange grapefruit watermelon
Êä³öÎª£º 136
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		String temp = sc.nextLine();
		String remstr = sc.nextLine();
		String sysstr = sc.nextLine();
		
		String[] remarray = remstr.split(" ");
		String[] sysarray = sysstr.split(" ");
		
		Set<String> remset = new HashSet<String>();
		int i,j;
		for(i=0;i<n;i++){
			remset.add(remarray[i]);
		}
		
		int sum=0;
		String t;
		Iterator<String> it = remset.iterator();
		while(it.hasNext()){
			t = it.next();
			for(j=0;j<m;j++){
				if(t.equals(sysarray[j])){
					sum+=t.length()*t.length();
					break;
				}
			}
		}
//		for(i=0;i<remset.size();i++){
//			for(j=0;j<m;j++){
//				if(.equals(sysarray[j])){
//					sum+=remarray[i].length()*remarray[i].length();
//				}
//			}
//		}
		System.out.println(sum);
//		System.out.println("1:"+n+" 2:"+m+" 3:"+remstr+" 4:"+sysstr);
	}

}
