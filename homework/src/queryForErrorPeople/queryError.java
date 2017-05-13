package queryForErrorPeople;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class queryError {




	private static double LeftEyeValue, RightEyeValue, LeftEyeFunction,RightEyeFunction;// ,VisualFunction;LeftEyeVisualFunction, RightEyeVisualFunction;
	private static int tableId=0;
	//private static String checkresult;
	//private int con_normal, con_warn, con_abnor, age;			//正常、预警、异常
	private static int con_normal=0, con_warn=0, con_abnor=0;//, age=10;
	private static int wrongrs=0;
	private static ResultSet rs;
	public static void main(String args[])throws Exception{
		int state=-2,state_Left=-2,state_Right=-2,age=0;
		double temp;
		Connection conn=null;
		String sql,sql2;//,name,add;

		//String url="jdbc:mysql://localhost:3306/skydb?user=root&password=&useUnicode=true&characterEncoding=utf-8";
		String url="jdbc:mysql://115.159.0.84:3306/skydb?user=root&password=&useUnicode=true&characterEncoding=utf-8";
		//url=jdbc\:mysql\://202.119.84.51\:4433/skydb?useUnicode\=true&characterEncoding\=utf-8
		//try{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("成功加载MySQL驱动！");
			conn=DriverManager.getConnection(url);
			Statement stmt=conn.createStatement();
//			Statement stmt2=conn.createStatement();
			
			sql=" select userinfo.username,realname,age,tableId,LeftEyeLittleValue,LeftEyeVisualFunction,RightEyeLittleValue,RightEyeVisualFunction " +
					" from visioncheck,userinfo " +
					" where visioncheck.username=userinfo.username and visioncheck.tableId in "+
					"(" +
						" select MAX(visioncheck.tableId) from userinfo, visioncheck " +
						" where visioncheck.username=userinfo.username " +
						" group by userinfo.username" +
					")";

			rs=stmt.executeQuery(sql);
			String[] level = new String[]{"未知","异常","预警","正常","错误"};		//1表示正常，0表示预警，-1表示异常，-2表示未知，2表示错误

			while(rs.next()){
				
				try{
					try{
						LeftEyeValue=Double.valueOf(rs.getString("LeftEyeLittleValue"));
						//System.out.println(LeftEyeValue);
						//System.out.println(rs.getString("tableId"));
						RightEyeValue=Double.valueOf(rs.getString("RightEyeLittleValue"));
						LeftEyeFunction=Double.valueOf(rs.getString("LeftEyeVisualFunction"));
						RightEyeFunction=Double.valueOf(rs.getString("RightEyeVisualFunction"));
						tableId=Integer.valueOf(rs.getString("tableId"));
					}catch(Exception e){
						//e.printStackTrace();
						state = 2;
//						System.out.println(rs.getString(1)+"视力信息错误！");
//						wrongrs++;
//						sql2=" update visioncheck " +
//								" set visioncheck.visionlevel ='错误' " +
//								" where tableId = "+tableId;
//						stmt2.executeUpdate(sql2);
						continue;
					}
					try{
						//age=Integer.valueOf(rs.getString("age"));
						age=2016-Integer.valueOf(rs.getString("age").substring(0, 4));
						//checkresult=rs.getString("checkresult");
					}catch(Exception e){
						//e.printStackTrace();
						state = 2;
//						System.out.println(rs.getString(1)+"年龄信息错误！age为"+age);
						wrongrs++;
//						sql2=" update visioncheck " +
//								" set visioncheck.visionlevel ='错误'" +
//								" where tableId = "+tableId;
//						stmt2.executeUpdate(sql2);
						continue;
					}
					temp=Math.abs(Math.log10(1/LeftEyeValue)-Math.log10(1/RightEyeValue));
				
				
					if( Double.isNaN(temp) || Double.isInfinite(temp) ){		//如果temp计算结果异常，则置为错误，状态2
						state = 2;
					}
					else if(temp>=0.2){
						state = -1;				//1表示正常，0表示预警，-1表示异常
						//System.out.println("序号为"+tableId+"的人员大于0.2");
					}
					else{		//绝对值的差小于0.2
						state_Left=IsVisualFunctionNormal(LeftEyeValue,LeftEyeFunction,age);
						state_Right=IsVisualFunctionNormal(RightEyeValue,RightEyeFunction,age);
						//System.out.println("序号为"+tableId+"的人员右眼结果为\t"+level[state_Right+2]+"\t左眼结果为\t"+level[state_Left+2]);
						if(state_Left==-1 || state_Right==-1){
							state=-1;
						}
						else if(state_Left==0 || state_Right==0){
							state=0;
						}
						else{
							state=1;
						}
					
					}
				}catch(Exception e){
					//e.printStackTrace();
					state = 2;
//					System.out.println(rs.getString(1)+"视力、年龄信息错误！age为："+age);
					wrongrs++;
//					sql2=" update visioncheck " +
//							" set visioncheck.visionlevel ='错误'" +
//							" where tableId = "+tableId;
//					stmt2.executeUpdate(sql2);
					continue;
				}
				count(state);
//				sql2=" update visioncheck " +
//						" set visioncheck.visionlevel ='"+level[state+2]+"' " +
//						" where tableId = "+tableId;
//				stmt2.executeUpdate(sql2);			//更新
				/**/
				
			}

		System.out.println("视力正常的人数是："+con_normal+"\t"+"视力预警的人数是："+con_warn+"\t"+"视力异常的人数是："+con_abnor+"\t视力错误的人数是："+wrongrs);
		int sum=con_normal+con_warn+con_abnor+wrongrs;
		System.out.println("总人数是："+sum);
	}
	
	public static void count(int state){
		if(state==1){con_normal++;}
		else if(state==0){con_warn++;}
		else{con_abnor++;}
	}
	

	public int getcon_Nor(){
		return con_normal;
	}
	public int getcon_Warn(){
		return con_warn;
	}
	public int getcon_Abnor(){
		return con_abnor;
	}
	public int getWrong_rs(){
		return wrongrs;
	}
	
	private static int IsVisualFunctionNormal(double EyeValue,double VisualFunction,int age){
			
		if(!IsAgeLegal(EyeValue,age)){
			return -1;
			//return con_abnor;
		}
		else if(VisualFunction<=0.35){
			return 1;
			//System.out.println("视力正常的人数是："+con_normal);
			//return con_normal;
		}
		else if(VisualFunction>0.35 && VisualFunction<=0.45){
			try {
				//System.out.println("视力异常");
				//System.out.println("视力异常的人员是：\t"+rs.getString(1)+"\t"+rs.getString("realname")+"\t"+rs.getString("age")+"\t"+rs.getString("tableId"));
				age=2016-Integer.valueOf(rs.getString("age").substring(0, 4));
				System.out.println("视力预警的人员是：\t"+rs.getString(1)+"\t"+rs.getString(2)+"\t"+age+"\t"+
						rs.getString(5)+"\t"+rs.getString(6)+"\t"+"\t"+rs.getString(7)+"\t"+"\t"+rs.getString(8));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
			}
			
			return 0;
			//return con_warn;
		}
		else //if(VisualFunction>0.45)
		{
//			try {
//				//System.out.println("视力异常");
//				age=2016-Integer.valueOf(rs.getString("age").substring(0, 4));
//				System.out.println("视力异常的人员是：\t"+rs.getString(1)+"\t"+rs.getString(2)+"\t"+age+"\t"+
//						rs.getString(5)+"\t"+rs.getString(6)+"\t"+"\t"+rs.getString(7)+"\t"+"\t"+rs.getString(8));
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
////				e.printStackTrace();
//			}
			return -1;		//异常人群，>0.45
			//return con_abnor;
		}
	}
	
	
	private static boolean IsAgeLegal(double EyeValue,int age){
		if(age>=0 && age<5){
			if(EyeValue>=0.45)	//EyeValue>=0.5，2016-6-20修改阈值
				return true;
			else
				return false;
		}
		else if(age>=5 && age<8){
			if(EyeValue>=0.55)	//EyeValue>=0.5
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


