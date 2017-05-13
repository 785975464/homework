package timecomputing;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class getLaterTime {

	/**
	 * @param args
	 * @return 
	 */
	public static void getRecheckTime(String date,String level) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//			Date dt;
//			long day = 0;
		try {
//				dt = df.parse(date);
		
//				Date now = new Date();
			//System.out.println("This is timeComputing()! and the day is:"+day);
//				Date dNow = new Date();   //当前时间
//				Date  = new Date();
			Date dnow = df.parse(date);
			Calendar calendar = Calendar.getInstance(); //得到日历
			calendar.setTime(dnow);//把当前时间赋给日历
			
			calendar.add(calendar.MONTH, -3);  //设置为前3月
			Date dBefore = calendar.getTime();   //得到前3月的时间

//				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //设置时间格式
			String defaultStartDate = df.format(dBefore);    //格式化前3月的时间
//				String defaultEndDate = df.format(dNow); //格式化当前时间

			System.out.println("前3个月的时间是：" + defaultStartDate);
//				System.out.println("生成的时间是：" + defaultEndDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("日期出错！");
//			return ;
		}
//		return ;
	}

}
