package visionlevel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class setSingleVisionLevel {




	private static double LeftEyeValue, RightEyeValue, LeftEyeFunction,RightEyeFunction;// ,VisualFunction;LeftEyeVisualFunction, RightEyeVisualFunction;
	private static String username;
//	private static int tableId=0;
	//private static String checkresult;
	//private int con_normal, con_warn, con_abnor, age;			//������Ԥ�����쳣
//	private static int con_normal=0, con_warn=0, con_abnor=0;//, age=10;
//	private static int wrongrs=0;

	//�����Ӿ��ȼ�
	
	public static void main(String args[]) throws SQLException{
		int state=-2,state_Left=-2,state_Right=-2,age=0;
		double temp;
		Connection conn=null;
		String sql,sql2;//,name,add;

//		String url="jdbc:mysql://localhost:3306/skydb?user=root&password=&useUnicode=true&characterEncoding=utf-8";
//		String url="jdbc:mysql://115.159.0.84:3306/skydb?user=root&password=&useUnicode=true&characterEncoding=utf-8";
		String url="jdbc:mysql://120.25.209.155:3306/skydb?user=root&password=zcy2815&useUnicode=true&characterEncoding=utf-8";
		//try{
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
//				e1.printStackTrace();
				System.out.println("MySQL��������ʧ�ܣ�");
			}
			System.out.println("�ɹ�����MySQL������");
			conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			Statement stmt2 = conn.createStatement();
			
			sql=" select username,realname,age,LeftEyeLittleValue,LeftEyeVisualFunction,RightEyeLittleValue,RightEyeVisualFunction " +
					" from all_stu_info ";// +
//					" where visioncheck.username=userinfo.username and visioncheck.tableId in "+
//					"(" +
//						" select MAX(visioncheck.tableId) from userinfo, visioncheck " +
//						" where visioncheck.username=userinfo.username " +
//						" group by userinfo.username" +
//					")";

			ResultSet rs=stmt.executeQuery(sql);
			String[] level = new String[]{"δ֪","�쳣","Ԥ��","����","����","�ǽ���"};		//1��ʾ������0��ʾԤ����-1��ʾ�쳣��-2��ʾδ֪��2��ʾ����3��ʾ�ǽ���   2016-07-08
			int count = 0;
			while(rs.next()){
//				state=-2;
				state_Left=-2;
				state_Right=-2;
				System.out.println("�������õ�"+(count++)+"����Ϣ��");
				try{
					username=rs.getString("username");
					try{
						LeftEyeValue=Double.valueOf(rs.getString("LeftEyeLittleValue"));
						LeftEyeFunction=Double.valueOf(rs.getString("LeftEyeVisualFunction"));
						RightEyeValue=Double.valueOf(rs.getString("RightEyeLittleValue"));
						RightEyeFunction=Double.valueOf(rs.getString("RightEyeVisualFunction"));
					}
					catch(Exception e){
						System.out.println(rs.getString(1)+"���ۻ�����������Ϣ����");
//						wrongrs++;
						sql2=" update all_stu_info " +
								" set LeftEyeVisionLevel ='����' ," +
								" RightEyeVisionLevel ='����'" +
								" where username='" + username +"'";
						stmt2.executeUpdate(sql2);
						continue;
					}
					
					
				
					try{
						age=2016-Integer.valueOf(rs.getString("age").substring(0, 4));
					}catch(Exception e){
						//e.printStackTrace();
//						state = 2;
						System.out.println(rs.getString(1)+"������Ϣ����ageΪ"+age);
//						wrongrs++;
						sql2=" update all_stu_info " +
								" set LeftEyeVisionLevel ='����' ," +
								" RightEyeVisionLevel ='����'" +
								" where username='" + username +"'";
						stmt2.executeUpdate(sql2);
						continue;
					}
					temp=Math.abs(Math.log10(1/LeftEyeValue)-Math.log10(1/RightEyeValue));
				
				
					if( Double.isNaN(temp) || Double.isInfinite(temp) ){		//���temp�������쳣������Ϊ����״̬2
//						state = 2;
						sql2=" update all_stu_info " +
								" set LeftEyeVisionLevel ='����' ," +
								" RightEyeVisionLevel ='����'" +
								" where username='" + username +"'";
						stmt2.executeUpdate(sql2);
						//continue;
					}
					else if(temp>=0.2){
//						state = -1;				//1��ʾ������0��ʾԤ����-1��ʾ�쳣
						//System.out.println("���Ϊ"+tableId+"����Ա����0.2");
						sql2=" update all_stu_info " +
								" set LeftEyeVisionLevel ='�쳣' ," +
								" RightEyeVisionLevel ='�쳣'" +
								" where username='" + username +"'";
						stmt2.executeUpdate(sql2);
						//continue;
					}
					else{
						state_Left=IsVisualFunctionNormal(LeftEyeValue,LeftEyeFunction,age);
						state_Right=IsVisualFunctionNormal(RightEyeValue,RightEyeFunction,age);
						//System.out.println(rs.getString("realname")+"�����۽��Ϊ\t"+level[state_Right+2]+"\t���۽��Ϊ\t"+level[state_Left+2]);
						sql2=" update all_stu_info " +
								" set LeftEyeVisionLevel ='"+level[state_Left+2]+"' ," +
								" RightEyeVisionLevel ='"+level[state_Right+2]+"'" +
								" where username='" + username +"'";
						try{
							stmt2.executeUpdate(sql2);			//����
						}
						catch(Exception e){
							System.out.println("���´���");
						}
//						System.out.println("���³ɹ���");
						//����<Ԥ��<�ǽ���<�쳣,2016-07-13
//						if(state_Left==-1 || state_Right==-1){
//							state=-1;
//						}
//						else if(state_Left==3 || state_Right==3){
//							state=3;
//						}
//						else if(state_Left==0 || state_Right==0){
//							state=0;
//						}
//						else{
//							state=1;
//						}
					
					}
				}catch(Exception e){
					//e.printStackTrace();
//					state = 2;
					System.out.println(rs.getString(1)+"������������Ϣ����ageΪ��"+age);
//					wrongrs++;
					sql2=" update all_stu_info " +
							" set LeftEyeVisionLevel ='����' ," +
							" RightEyeVisionLevel ='����'";
					stmt2.executeUpdate(sql2);
					continue;
				}
//				count(state);
				
			}

//		System.out.println("���������������ǣ�"+con_normal+"\t"+"����Ԥ���������ǣ�"+con_warn+"\t"+"�����쳣�������ǣ�"+con_abnor+"\t��������������ǣ�"+wrongrs);
//		int sum=con_normal+con_warn+con_abnor+wrongrs;
//		System.out.println("�������ǣ�"+sum);
	}
	
//	public static void count(int state){
//		if(state==1){con_normal++;}
//		else if(state==0){con_warn++;}
//		else{con_abnor++;}
//	}
	

//	public int getcon_Nor(){
//		return con_normal;
//	}
//	public int getcon_Warn(){
//		return con_warn;
//	}
//	public int getcon_Abnor(){
//		return con_abnor;
//	}
//	public int getWrong_rs(){
//		return wrongrs;
//	}
	
	private static int IsVisualFunctionNormal(double EyeValue,double VisualFunction,int age){
			
		if(!IsAgeLegal(EyeValue,age)){
			return -1;
			//return con_abnor;
		}
		else if(VisualFunction<=0.36){	//0.35������0.36�����������룬2016-07-08
			return 1;
			//System.out.println("���������������ǣ�"+con_normal);
			//return con_normal;
		}
		else if(VisualFunction>0.36 && VisualFunction<=0.46){	//С��0.46�������Ӿ��ȼ��ǽ���=�쳣��2016-07-08
			return 0;
			//return con_warn;
		}
		else //if(VisualFunction>0.45)
			{
			return 3;
			//return con_abnor;
			}
	}
	
	
	private static boolean IsAgeLegal(double EyeValue,int age){
		if(age>=0 && age<5){
			if(EyeValue>=0.45)	//EyeValue>=0.5��2016-6-20�޸���ֵ
				return true;
			else
				return false;
		}
		else if(age>=5 && age<8){
			if(EyeValue>=0.55)	//EyeValue>=0.6
				return true;
			else
				return false;
		}
		else if(age>=8 && age<12){
			if(EyeValue>=0.7)	//EyeValue>=0.8
				return true;
			else
				return false;
		}
		else if(age>=12 && age<150){
			if(EyeValue>=0.9)	//EyeValue>=1.0
				return true;
			else
				return false;
		}
		else
			return false;
	}


}


