package judgelegalnumber;

import java.io.IOException;  
import java.util.regex.Matcher;  
import java.util.regex.Pattern;

import org.springframework.core.io.ClassPathResource;

public class judgeNumber {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(isMobileLegal("17092521795"));
		//电子邮件    
		System.out.println(isEmailLegal("dffdfdf@qq.cm"));
//		System.out.println();  
		
	}
	
	//判断手机号是否合法 
	public static boolean isMobileLegal(String mobiles){  
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9])|(17[0,1]))\\d{8}$");  
		Matcher m = p.matcher(mobiles);  
//		System.out.println(m.matches()+"---");  
		return m.matches();  
	}
	
	//判断电子邮件是否合法 
	public static boolean isEmailLegal(String emails){
		String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";    
		Pattern p = Pattern.compile(check);
		
		Matcher m = p.matcher(emails);  
//		System.out.println(m.matches()+"---");  
		return m.matches();  
	}
}
