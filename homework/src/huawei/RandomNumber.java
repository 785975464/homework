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
		HashSet h=new HashSet();	//HashSetȷ����Ȩ�ز��ظ�
//		for(int i=0;i<99;i++){
		int count=0;
		h.add(0);		//�����㡢�յ㣬���ɾ���������ظ�
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
		System.out.println("a.size()Ϊ"+a.size()+"\tiΪ"+count);
		System.out.println(temp);
	}

}
