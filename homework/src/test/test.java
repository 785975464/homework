package test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class test {
	public static void main(String[] args) throws InterruptedException{
		System.out.println("我爱小凤!");
		int x=0;
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
		Date dt;
		String val="2015/1.23";
		val=val.replace("/", "-");
		val=val.replace(".", "-");
		System.out.println("val为："+val);
		try {
			dt = df.parse(val);
			System.out.println(df.format(dt));
		}catch (ParseException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("日期转换错误！");
			Date nowtime = new Date();
			System.out.println(df.format(nowtime));
		}
		/**
		while( ( x=getTime() )>30){
			System.out.println("当前时间为"+x);
			Thread.sleep(1000);
		}
		**/
	}
	public static Integer getTime(){
		Date date = new Date();
		//System.out.println("当前时间为："+date.toString());
		DateFormat df1 = DateFormat.getInstance(); 
		DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss EE"); 
		//System.out.println(df1.format(date));
		//System.out.println(df2.format(date));
		String time = df2.format(date);
		String[] t = time.split(":");
		String[] s = t[2].split(" ");
		return Integer.parseInt(s[0]);
	}
}
