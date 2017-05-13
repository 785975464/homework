package scanner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Scanner取得的输入以space, tab, enter 键为结束符，
		  //要想取得包含space在内的输入，可以用java.io.BufferedReader类来实现
		  //使用BufferedReader的readLine( )方法
		  //必须要处理java.io.IOException异常
		  BufferedReader br = new BufferedReader(new InputStreamReader(System.in ));
		  //java.io.InputStreamReader继承了Reader类
		  String read = null;
		  System.out.print("输入数据：");
		  try {
		   read = br.readLine();
		  } catch (IOException e) {
		   e.printStackTrace();
		  }
		  System.out.println("输入的数据为："+read); 
	}

}
