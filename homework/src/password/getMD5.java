package password;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class getMD5 {
	private static String password;
	private static String name;
	private static String id;
	private static String md5;
	public static void main(String args[]){
		Connection conn=null,conn2=null;
		String sql,sql2;
		String url="jdbc:mysql://localhost:3306/test?user=root&password=&useUnicode=true&characterEncoding=utf-8";
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		System.out.println("�ɹ�����MySQL������");
		try {
			conn=DriverManager.getConnection(url);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		
		//--------------�ڶ�������
		try {
			conn2=DriverManager.getConnection(url);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		Statement stmt2 = null;
		try {
			stmt2 = conn2.createStatement();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		

		
		
		sql="select id,name,password " +
				" from user ";
//				" where visioncheck.username=userinfo.username and visioncheck.tableId in "+
//				"(" +
//					" select MAX(visioncheck.tableId) from userinfo, visioncheck " +
//					" where visioncheck.username=userinfo.username " +
//					" group by userinfo.username" +
//				")";
		
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			while(rs.next()){
				id = rs.getString(1);
				name = rs.getString(2);
				password = rs.getString(3);
				System.out.println(id+"\t"+name+"\t"+password);
				md5 = makeMD5(password);
				System.out.println(md5);
				
				
				
//				ResultSet rs2 = null;
				sql2="update user set md5Compute='"+md5+"' where id='"+id+"'";
				try {
					stmt2.executeUpdate(sql2);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				System.out.println("���³ɹ���");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
//		String password="123456";
//		System.out.print(makeMD5(password));
	}
	public static String makeMD5(String password) {
		MessageDigest md;
		   try {
		    // ����һ��MD5���ܼ���ժҪ
		    md = MessageDigest.getInstance("MD5");
		    // ����md5����
		    md.update(password.getBytes());
		    // digest()���ȷ������md5 hashֵ������ֵΪ8Ϊ�ַ�������Ϊmd5 hashֵ��16λ��hexֵ��ʵ���Ͼ���8λ���ַ�
		    // BigInteger������8λ���ַ���ת����16λhexֵ�����ַ�������ʾ���õ��ַ�����ʽ��hashֵ
		    String pwd = new BigInteger(1, md.digest()).toString(16);
//		    System.err.println(pwd);
		    return pwd;
		   } catch (Exception e) {
		    e.printStackTrace();
		   }
		   return password;
		}
}
