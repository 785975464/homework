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
		//Scannerȡ�õ�������space, tab, enter ��Ϊ��������
		  //Ҫ��ȡ�ð���space���ڵ����룬������java.io.BufferedReader����ʵ��
		  //ʹ��BufferedReader��readLine( )����
		  //����Ҫ����java.io.IOException�쳣
		  BufferedReader br = new BufferedReader(new InputStreamReader(System.in ));
		  //java.io.InputStreamReader�̳���Reader��
		  String read = null;
		  System.out.print("�������ݣ�");
		  try {
		   read = br.readLine();
		  } catch (IOException e) {
		   e.printStackTrace();
		  }
		  System.out.println("���������Ϊ��"+read); 
	}

}
