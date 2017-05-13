package didi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class setPredict {
	static String sql,time;
	static Connection conn=null;
	static int day=0;						//�ݴ�������Ϣ
	static int dis_id=1;					//�ݴ�������Ϣ
	static int timeperiod =0;				//�ݴ�ʱ��Ƭ��Ϣ
	static int[][] day_array = new int[][]{	//����������Ϣ
		{1,8,15},
		{3,10,17},
		{5,12,19},
		{7,14,21},
		{2,9,16}};
	static int[] day_testing = new int[]{22,24,26,28,30};
//	static int[] day_testing = new int[]{22};
	static int[] timeperiod_array = new int[]{46,58,70,82,94,106,118,130,142};					//����ʱ��Ƭ��Ϣ
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		//���ݿ��ʼ��------------------------------
		String url="jdbc:mysql://localhost:3306/didi_data_submit?user=root&password=&useUnicode=true&characterEncoding=utf-8";
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("�ɹ�����MySQL������");
		conn=DriverManager.getConnection(url);
		Statement stmt=conn.createStatement();
		//--------------------------------------
		for(int d=0;d<day_testing.length;d++){
			day = day_testing[d];	//���Լ���day��
			dis_id=1;			//���������1��ʼ
			
			do{
				for(int t=0;t<timeperiod_array.length;t++){		//���µ�dis_id�������Ԥ��ֵ
					timeperiod = timeperiod_array[t];
					//day_array[d][i]������Ԥ�����һ������ǰ���ܵĶ�Ӧ���ںϳɶ���
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
					System.out.println("�ɹ����µ�"+day+"�죬��"+dis_id+"�����򣬵�"+t+"��ʱ��Ƭ��");
				}
				dis_id++;
			}while(dis_id<=66);	//66
		}
		System.out.println("ȫ����ɣ�");
	}

}
