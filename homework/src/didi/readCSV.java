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
//	private static String fileName = "C:\\Users\\Jay\\Desktop\\�ε��㷨\\ѵ����\\training_result\\1_result.csv";
//	private static String[] temparray = new String[4];
	private static String result="";
//	private static int[][] resultarray = new int[310][310];
//	private static int start=0,end=0,value=0;
	private static int line = 1;	//ȫ�ֱ����������ڼ���
	private static int district_index = 1;	//ȫ�ֱ����������ڼ�������
//	private static int day = 3;		//ȫ�ֱ������ڼ��죬���ƶ�ȡ���ļ�����
	
	
	public static void main(String args[]){
		int[] days = new int[]{1,8,15,3,10,17,5,12,19,7,14,21,2,9,16} ;
		for( int i=0; i<days.length;i++){
			makefiles(days[i]);
		}
	}
	
	public static void makefiles(int day){
		
//		��ʼ��
		line = 1;
		district_index = 1;
		result="";
		
		File file = new File("C:\\Users\\Jay\\Desktop\\�ε��㷨\\ѵ����\\training_result\\"+day+"_result.csv");
	    BufferedReader reader = null;
	    try {
	        System.out.println("����Ϊ��λ��ȡ�ļ����ݣ�һ�ζ�һ���У�");
	        reader = new BufferedReader(new FileReader(file));
	        String tempString = null;
	        
	        System.out.println("��ʼ����"+district_index+"������");
	        // һ�ζ���һ�У�ֱ������nullΪ�ļ�����  
	        while ((tempString = reader.readLine()) != null) {// && line<10
	            // ������ֵ
	        	
	        	
	        	if(tempString.startsWith(district_index+"")){
	        		result += tempString + "\r\n";
	        	}
	        	else{
	        		print(day);
	        		district_index++;
	        		result = "";	//���result
	        		System.out.println("��ʼ����"+district_index+"������");
	        	}
	        	
	        	System.out.println("��"+line+"��Ϊ��"+tempString);
	        	
	            line++;
//	            System.out.println();
	        }
	        
	        System.out.println("����ɹ���");
	        print(day);
	        
	        reader.close();
	        
	        System.out.println("�������رճɹ���");
	    } catch (IOException e) {
	        System.out.println("����������");
	    } finally {
	        if (reader != null) {
	            try {
	                reader.close();
	            } catch (IOException e1) {
	            	System.out.println("�������رմ���");
	            }
	        }
	        
//	        
	    }
	}

	private static void print(int day) {
		// TODO Auto-generated method stub
		System.out.println("print��");
		
		//��������
		
		BufferedOutputStream Buff=null;
		FileOutputStream outSTr = null;
		try {
			File file = new File("G:/didi_data_csv/"+day);
			file.mkdir();
			outSTr = new FileOutputStream("G:/didi_data_csv/"+day+"/"+district_index+"_district.csv");
		} catch (FileNotFoundException e) {
//			 TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("��������ļ�����");
		}
		Buff=new BufferedOutputStream(outSTr);
		try {
			Buff.write(result.trim().getBytes());
		} catch (IOException e) {
//			 TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("�ļ�д�����");
		}
		try {
			Buff.flush();
			Buff.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("������رմ���");
		}
		System.out.println("��ӡ�ɹ���");
		
		
	}

	private static void show() {}

	private static void init() {}
}
