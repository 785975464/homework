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
//				Date dNow = new Date();   //��ǰʱ��
//				Date  = new Date();
			Date dnow = df.parse(date);
			Calendar calendar = Calendar.getInstance(); //�õ�����
			calendar.setTime(dnow);//�ѵ�ǰʱ�丳������
			
			calendar.add(calendar.MONTH, -3);  //����Ϊǰ3��
			Date dBefore = calendar.getTime();   //�õ�ǰ3�µ�ʱ��

//				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //����ʱ���ʽ
			String defaultStartDate = df.format(dBefore);    //��ʽ��ǰ3�µ�ʱ��
//				String defaultEndDate = df.format(dNow); //��ʽ����ǰʱ��

			System.out.println("ǰ3���µ�ʱ���ǣ�" + defaultStartDate);
//				System.out.println("���ɵ�ʱ���ǣ�" + defaultEndDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("���ڳ���");
//			return ;
		}
//		return ;
	}

}
