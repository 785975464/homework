package huawei;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class RandomNumber {

	private static String temp="";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Random r=new java.util.Random();
		HashSet h=new HashSet();	//HashSet确保边权重不重复
//		for(int i=0;i<99;i++){
		int count=0;
		h.add(0);		//添加起点、终点，最后删除，避免重复
		h.add(1999);
		while(h.size()<102){
			count++;
			h.add(r.nextInt(2000));
		}
		h.remove(0);
		h.remove(1999);
		
		ArrayList<Integer> a = new ArrayList<Integer>(h);
		
		for(int i=0;i<99;i++){
			temp += a.get(i)+"|";
		}
		temp += a.get(99);
		
//		temp += r.nextInt(2000)+1+"|";
//		temp += r.nextInt(2000)+1;
		System.out.println("a.size()为"+a.size()+"\ti为"+count);
		System.out.println(temp);
	}

}
