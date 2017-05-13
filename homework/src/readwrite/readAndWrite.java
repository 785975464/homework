package readwrite;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class readAndWrite {
	private static final int MAX_NUM = 100;
//	private static final int size = 310;
	private static String fileName = "G:\\case300\\topo.csv";
	private static String[] temparray = new String[4];
	private static String result=null;
	private static int[][] resultarray = new int[310][310];
	private static int start=0,end=0,value=0;
	
	public static void main(String args[]){
		
		init();

		File file = new File(fileName);
	    BufferedReader reader = null;
	    try {
	        System.out.println("以行为单位读取文件内容，一次读一整行：");
	        reader = new BufferedReader(new FileReader(file));
	        String tempString = null;
	        int line = 1;
	        // 一次读入一行，直到读入null为文件结束  
	        while ((tempString = reader.readLine()) != null) {// && line<10
	            // 读入数值
	        	start = Integer.parseInt(tempString.split(",")[1]);
	        	end = Integer.parseInt(tempString.split(",")[2]);
	        	value = Integer.parseInt(tempString.split(",")[3]);
	        	
	        	resultarray[start][end] = value;
	        	
//	            System.out.println("line " + line + ": " + tempString);
//	            for(int i=0;i<resultarray[0].length;i++){
//	            	System.out.print(resultarray[start][i]+" ");
//	            }
	            line++;
//	            System.out.println();
	        }
	        
	        System.out.println("读入成功！");
	        
	        reader.close();
	        
	        System.out.println("读入流关闭成功！");
	    } catch (IOException e) {
//	        e.printStackTrace();
	        System.out.println("读入流错误！");
	    } finally {
	        if (reader != null) {
	            try {
	                reader.close();
	            } catch (IOException e1) {
	            	System.out.println("读入流关闭错误！");
	            }
	        }
	    }
		print();
	}

	private static void print() {
		// TODO Auto-generated method stub
		System.out.println("print！");
		
		//测试数据
//		int count=1;
//		int [][]resultarray = new int[5][5];
//		for(int i=0;i<resultarray.length;i++){
//			for(int j=0;j<resultarray[0].length;j++){
//				resultarray[i][j] = count++;
//			}
//		}
		result="";
		for(int i=0;i<resultarray.length;i++){
			for(int j=0;j<resultarray[0].length-1;j++){
//				System.out.print(resultarray[i][j]+" ");
				result+=resultarray[i][j]+" ";
			}
			
			result+=resultarray[i][resultarray[0].length-1]+"\r\n";
			
//			System.out.print(resultarray[i][resultarray[0].length-1]);
//			System.out.println();
		}
		System.out.println(result);
		
		BufferedOutputStream Buff=null;
		FileOutputStream outSTr = null;
		try {
			outSTr = new FileOutputStream(new File("G:/case300/1.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println("创建输出文件错误！");
		}
		Buff=new BufferedOutputStream(outSTr);
		try {
			Buff.write(result.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println("文件写入错误！");
		}
		try {
			Buff.flush();
			Buff.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println("输出流关闭错误！");
		}
		System.out.println("打印成功！");
		
		
	}

	private static void show() {
		// TODO Auto-generated method stub
		for(int i=0;i<resultarray.length;i++){
			for(int j=0;j<resultarray[0].length;j++){
				System.out.print(resultarray[i][j]+" ");
			}
			System.out.println();
		}
	}

	private static void init() {
		// TODO Auto-generated method stub
//		System.out.println("resultarray.length"+resultarray.length);
//		System.out.println("resultarray[0].length"+resultarray[0].length);
		
		for(int i=0;i<resultarray.length;i++){
			for(int j=0;j<resultarray[0].length;j++){
				resultarray[i][j]=MAX_NUM;
			}
		}
		System.out.println("初始化成功！");
	}
}
