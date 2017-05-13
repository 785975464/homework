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
		System.out.println("成功加载MySQL驱动！");
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
		
		
		//--------------第二个连接
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
				System.out.println("更新成功！");
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
		    // 生成一个MD5加密计算摘要
		    md = MessageDigest.getInstance("MD5");
		    // 计算md5函数
		    md.update(password.getBytes());
		    // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
		    // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
		    String pwd = new BigInteger(1, md.digest()).toString(16);
//		    System.err.println(pwd);
		    return pwd;
		   } catch (Exception e) {
		    e.printStackTrace();
		   }
		   return password;
		}
}
