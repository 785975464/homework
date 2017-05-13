package periodictable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class periodicTable {
	//private static String checkresult;
	//private int con_normal, con_warn, con_abnor, age;			//正常、预警、异常
	private static int con_normal=0, con_warn=0, con_abnor=0;//, age=10;
	private static int wrongrs=0;
	
	public static void main(String args[])throws Exception{

		Connection conn=null;
		String sql = null;//,name,add;

		String url="jdbc:mysql://localhost:3306/periodictable?user=root&password=&useUnicode=true&characterEncoding=utf-8";
		//String url="jdbc:mysql://115.159.0.84:3306/skydb?user=root&password=&useUnicode=true&characterEncoding=utf-8";
		//url=jdbc\:mysql\://202.119.84.51\:4433/skydb?useUnicode\=true&characterEncoding\=utf-8
		
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("成功加载MySQL驱动！");
		conn=DriverManager.getConnection(url);
		Statement stmt=conn.createStatement();
		
		sql=" select * from periodicdata " +

		stmt.executeQuery(sql);
		try{
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()){
				String channel = rs.getString(5);
				String name = rs.getString(4);
				Float posx = rs.getFloat(6);
				Float posy = rs.getFloat(7);
				String iccid = rs.getString(3);
				int port = rs.getInt(9);
			}
		}catch(Exception e){
			
		}
	}
}
