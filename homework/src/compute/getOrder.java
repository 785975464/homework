package compute;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class getOrder {
	private static ArrayList<Double> list = new ArrayList<Double>();
	public static void main(String[] args) throws IOException{			//求数据的秩
		String filename = "G:/DATA/order.txt";
		BufferedReader file = new BufferedReader(new FileReader(filename));
		
		String str;
		double temp;
		while ( ( str = file.readLine() ) != null ) {
			temp=Double.parseDouble( str );
			list.add(temp);
		}
		int[] order = new int[list.size()];
		int sum=0;
		for(int i=0;i<list.size();i++){
			order[i]=getorder(i);
			sum+=order[i];
		}
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i)+"的秩为："+order[i]);
		}
		System.out.println("秩和为："+sum);
	}
	public static int getorder(int index){
		int count=0;
		for(int i=0;i<list.size();i++){
			if(list.get(i)<=list.get(index))
				count++;
		}
		return count;
	}
}
