package didi;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class readCSV {
//	private static final int MAX_NUM = 100;
//	private static final int size = 310;
//	private static String fileName = "C:\\Users\\Jay\\Desktop\\滴滴算法\\训练集\\training_result\\1_result.csv";
//	private static String[] temparray = new String[4];
	private static String result="";
//	private static int[][] resultarray = new int[310][310];
//	private static int start=0,end=0,value=0;
	private static int line = 1;	//全局变量，读到第几行
	private static int district_index = 1;	//全局变量，读到第几个区域
//	private static int day = 3;		//全局变量，第几天，控制读取的文件名称
	
	
	public static void main(String args[]){
		int[] days = new int[]{1,8,15,3,10,17,5,12,19,7,14,21,2,9,16} ;
		for( int i=0; i<days.length;i++){
			makefiles(days[i]);
		}
	}
	
	public static void makefiles(int day){
		
//		初始化
		line = 1;
		district_index = 1;
		result="";
		
		File file = new File("C:\\Users\\Jay\\Desktop\\滴滴算法\\训练集\\training_result\\"+day+"_result.csv");
	    BufferedReader reader = null;
	    try {
	        System.out.println("以行为单位读取文件内容，一次读一整行：");
	        reader = new BufferedReader(new FileReader(file));
	        String tempString = null;
	        
	        System.out.println("开始读第"+district_index+"个区域");
	        // 一次读入一行，直到读入null为文件结束  
	        while ((tempString = reader.readLine()) != null) {// && line<10
	            // 读入数值
	        	
	        	
	        	if(tempString.startsWith(district_index+"")){
	        		result += tempString + "\r\n";
	        	}
	        	else{
	        		print(day);
	        		district_index++;
	        		result = "";	//清空result
	        		System.out.println("开始读第"+district_index+"个区域");
	        	}
	        	
	        	System.out.println("第"+line+"行为："+tempString);
	        	
	            line++;
//	            System.out.println();
	        }
	        
	        System.out.println("读入成功！");
	        print(day);
	        
	        reader.close();
	        
	        System.out.println("读入流关闭成功！");
	    } catch (IOException e) {
	        System.out.println("读入流错误！");
	    } finally {
	        if (reader != null) {
	            try {
	                reader.close();
	            } catch (IOException e1) {
	            	System.out.println("读入流关闭错误！");
	            }
	        }
	        
//	        
	    }
	}

	private static void print(int day) {
		// TODO Auto-generated method stub
		System.out.println("print！");
		
		//测试数据
		
		BufferedOutputStream Buff=null;
		FileOutputStream outSTr = null;
		try {
			File file = new File("G:/didi_data_csv/"+day);
			file.mkdir();
			outSTr = new FileOutputStream("G:/didi_data_csv/"+day+"/"+district_index+"_district.csv");
		} catch (FileNotFoundException e) {
//			 TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("创建输出文件错误！");
		}
		Buff=new BufferedOutputStream(outSTr);
		try {
			Buff.write(result.trim().getBytes());
		} catch (IOException e) {
//			 TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("文件写入错误！");
		}
		try {
			Buff.flush();
			Buff.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("输出流关闭错误！");
		}
		System.out.println("打印成功！");
		
		
	}

	private static void show() {}

	private static void init() {}
}
