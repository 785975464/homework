package didi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class setMean {
	static String sql;
	static Connection conn=null;
	static int day=0;				//�ݴ�������Ϣ
	static int dis_id=1;			//�ݴ�������Ϣ
	static int timeperiod =0;		//�ݴ�ʱ��Ƭ��Ϣ
	static int[] day_array = new int[]{1,8,15,	3,10,17,	5,12,19,	7,14,21,	2,9,16};	//����������Ϣ
	static int[] timeperiod_array = new int[]{46,58,70,82,94,106,118,130,142};					//����ʱ��Ƭ��Ϣ
//	static int[] timeperiod_array = new int[]{45,57,69,81,93,105,117,129,141};					//���¸���ʱ��Ƭ��Ϣ
	
	public static void main(String args[])throws Exception{
		//���ݿ��ʼ��------------------------------
		String url="jdbc:mysql://localhost:3306/didi_data_solve?user=root&password=&useUnicode=true&characterEncoding=utf-8";
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("�ɹ�����MySQL������");
		conn=DriverManager.getConnection(url);
		Statement stmt=conn.createStatement();
		//--------------------------------------
		long startTime=System.currentTimeMillis();	//�����������ʱ��
		
		for(int d=0;d<day_array.length;d++){
			day = day_array[d];	//���µ�day��
			dis_id=1;			//���������1��ʼ
			do{
				for(int t=0;t<timeperiod_array.length;t++){		//���µ�dis_id������ľ�ֵ��
					timeperiod = timeperiod_array[t];			//-1�����¸���ʱ��Ƭ��Ϣ
					
					//����ָ�����ڡ�ָ��ʱ��Ƭ������ǰ��5��ʱ��Ƭ����ƽ��ֵ
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
					System.out.println("�ɹ����µ�"+day+"�죬��"+dis_id+"�����򣬵�"+t+"��ʱ��Ƭ��");
				}
				dis_id++;
			}while(dis_id<=66);
		}
		long endTime=System.currentTimeMillis();
		long Time=(endTime-startTime)/1000;
//		System.out.println(Time);
		System.out.println("ȫ����ɣ�����ʱ��"+Time+"��");
	}
}
