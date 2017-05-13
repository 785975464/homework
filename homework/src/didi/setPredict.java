package didi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class setPredict {
	static String sql,time;
	static Connection conn=null;
	static int day=0;						//暂存日期信息
	static int dis_id=1;					//暂存区域信息
	static int timeperiod =0;				//暂存时间片信息
	static int[][] day_array = new int[][]{	//保存日期信息
		{1,8,15},
		{3,10,17},
		{5,12,19},
		{7,14,21},
		{2,9,16}};
	static int[] day_testing = new int[]{22,24,26,28,30};
//	static int[] day_testing = new int[]{22};
	static int[] timeperiod_array = new int[]{46,58,70,82,94,106,118,130,142};					//保存时间片信息
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		//数据库初始化------------------------------
		String url="jdbc:mysql://localhost:3306/didi_data_submit?user=root&password=&useUnicode=true&characterEncoding=utf-8";
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("成功加载MySQL驱动！");
		conn=DriverManager.getConnection(url);
		Statement stmt=conn.createStatement();
		//--------------------------------------
		for(int d=0;d<day_testing.length;d++){
			day = day_testing[d];	//测试集第day天
			dis_id=1;			//重置区域从1开始
			
			do{
				for(int t=0;t<timeperiod_array.length;t++){		//更新第dis_id个区域的预测值
					timeperiod = timeperiod_array[t];
					//day_array[d][i]代表所预测的这一天是由前三周的对应日期合成而成
					time = "2016-01-"+day;
					sql=
							"UPDATE data_sub "+
							"SET predict =  "+
							"0.2* "+
							"( "+
									"SELECT mean "+
									"FROM "+day_array[d][0]+"_result_solve AS t1 "+
									"WHERE t1.district_id = "+dis_id+" AND t1.time_period= "+timeperiod+
							") "+
							"+ 0.3* "+
							"( "+
									"SELECT mean "+
									"FROM "+day_array[d][1]+"_result_solve AS t1 "+
									"WHERE t1.district_id= "+dis_id+" AND t1.time_period= "+timeperiod+
							") "+
							"+ 0.5* "+
							"( "+
									"SELECT mean "+
									"FROM "+day_array[d][2]+"_result_solve AS t1 "+
									"WHERE t1.district_id= "+dis_id+" AND t1.time_period= "+timeperiod+
							") "+
							"WHERE date= '"+time+"' AND "+
										"data_sub.district_id= "+dis_id+" AND "+
										"data_sub.time_period= "+timeperiod;
					
					stmt.executeUpdate(sql);
					//System.out.println(sql);
					System.out.println("成功更新第"+day+"天，第"+dis_id+"个区域，第"+t+"个时间片！");
				}
				dis_id++;
			}while(dis_id<=66);	//66
		}
		System.out.println("全部完成！");
	}

}
