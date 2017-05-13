package didi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class setMean {
	static String sql;
	static Connection conn=null;
	static int day=0;				//暂存日期信息
	static int dis_id=1;			//暂存区域信息
	static int timeperiod =0;		//暂存时间片信息
	static int[] day_array = new int[]{1,8,15,	3,10,17,	5,12,19,	7,14,21,	2,9,16};	//保存日期信息
	static int[] timeperiod_array = new int[]{46,58,70,82,94,106,118,130,142};					//保存时间片信息
//	static int[] timeperiod_array = new int[]{45,57,69,81,93,105,117,129,141};					//更新附近时间片信息
	
	public static void main(String args[])throws Exception{
		//数据库初始化------------------------------
		String url="jdbc:mysql://localhost:3306/didi_data_solve?user=root&password=&useUnicode=true&characterEncoding=utf-8";
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("成功加载MySQL驱动！");
		conn=DriverManager.getConnection(url);
		Statement stmt=conn.createStatement();
		//--------------------------------------
		long startTime=System.currentTimeMillis();	//计算程序运行时间
		
		for(int d=0;d<day_array.length;d++){
			day = day_array[d];	//更新第day天
			dis_id=1;			//重置区域从1开始
			do{
				for(int t=0;t<timeperiod_array.length;t++){		//更新第dis_id个区域的均值表
					timeperiod = timeperiod_array[t];			//-1，更新附近时间片信息
					
					//根据指定日期、指定时间片，由其前后共5个时间片计算平均值
					sql=
						"UPDATE "+day+"_result_solve "+
						"SET mean = "+
						"( "+
							"SELECT AVG(order_total_number-order_response) "+
							"FROM "+day+"_result "+
							"WHERE  district_id = "+dis_id+" AND time_period>="+timeperiod+"-2 AND time_period<="+timeperiod+"+2 "+
						") "+
						"WHERE district_id = "+dis_id+" AND time_period="+timeperiod;//+"-2 AND time_period<="+timeperiod+"+2 ";
					
					stmt.executeUpdate(sql);
					System.out.println("成功更新第"+day+"天，第"+dis_id+"个区域，第"+t+"个时间片！");
				}
				dis_id++;
			}while(dis_id<=66);
		}
		long endTime=System.currentTimeMillis();
		long Time=(endTime-startTime)/1000;
//		System.out.println(Time);
		System.out.println("全部完成！共耗时："+Time+"秒");
	}
}
