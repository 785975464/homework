package readwrite;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class readFile {
//	private static final int MAX_NUM = 100;
//	private static final int size = 310;
	private static String fileName = "C:\\Users\\Jay\\Desktop\\�ε��㷨\\ѵ����2016-01-01\\ԭʼ���� - ����\\poi_data";
//	private static int start=0,end=0,value=0;
	
	public static void main(String args[]) throws ClassNotFoundException, SQLException{
		
//		init();

		File file = new File(fileName);
	    BufferedReader reader = null;
	    
	    


		Connection conn=null;
		String sql = null;//,name,add;

		String url="jdbc:mysql://localhost:3306/didi?user=root&password=&useUnicode=true&characterEncoding=utf-8";
		//String url="jdbc:mysql://115.159.0.84:3306/skydb?user=root&password=&useUnicode=true&characterEncoding=utf-8";
		//url=jdbc\:mysql\://202.119.84.51\:4433/skydb?useUnicode\=true&characterEncoding\=utf-8
		
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("�ɹ�����MySQL������");
		conn=DriverManager.getConnection(url);
		Statement stmt=conn.createStatement();
		
//		sql=" select * from periodicdata " +
//
//		stmt.executeQuery(sql);
//		try{
//			ResultSet rs=stmt.executeQuery(sql);
//			while(rs.next()){
//				String channel = rs.getString(5);
//				String name = rs.getString(4);
//				Float posx = rs.getFloat(6);
//				Float posy = rs.getFloat(7);
//				String iccid = rs.getString(3);
//				int port = rs.getInt(9);
//			}
//		}catch(Exception e){
//			
//		}
	
	    
	    
	    
//	    int max=0;	//�����󳤶�
	    
	    try {
	        System.out.println("����Ϊ��λ��ȡ�ļ����ݣ�һ�ζ�һ���У�");
	        reader = new BufferedReader(new FileReader(file));
	        String tempString = null;
	        int line = 1;
	        // һ�ζ���һ�У�ֱ������nullΪ�ļ�����  
	        while ((tempString = reader.readLine()) != null ) {// && line<=100	ʵ��lineֻ��66��
	            // ������ֵ
	        	
	        	String[] temp = tempString.split("\t",2);
	        	
	            System.out.println("line " + line + "(size:" + temp[1].length() +"): " + temp[0]+"\t\t"+temp[1]);
	            sql = "INSERT INTO poi(district_hash,poi_class) VALUES ('"+temp[0]+"','"+temp[1]+"')";  
	            stmt.executeUpdate(sql);
	            
//	            if(temp[1].length()>max){
//	            	max = temp[1].length();
//	            }
	            
	            line++;
	            System.out.println("�����"+line+"�����ݳɹ���");
	        }
	        System.out.println("ȫ������ɹ���");
//	        System.out.println("ȫ������ɹ���maxΪ:"+max);
	        
	        reader.close();
	        
	        System.out.println("�������رճɹ���");
	    } catch (IOException e) {
//	        e.printStackTrace();
	        System.out.println("����������");
	    } finally {
	        if (reader != null) {
	            try {
	                reader.close();
	            } catch (IOException e1) {
	            	System.out.println("�������رմ���");
	            }
	        }
	    }
		print();
	}

	private static void print() {}

	private static void show() {}

	private static void init() {}
}
