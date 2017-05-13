package huawei;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class TwoThousandPoints {

	/**
	 * @param args
	 */
	
	private static int NODESIZE = 2000;
	private static int EDGESIZE = 20;
	private static int COSTSIZE = 100;
	private static int ALLEDGESIZE = NODESIZE * EDGESIZE;
	private static int[][] edgeSet = new int[ALLEDGESIZE][4];
	private static String path = "G:/40000edges.csv";
//	private static int temp;
	
	
	public static void main(String[] args) {
		int temp,cost;
		Random r=new java.util.Random();
//		    System.out.println(r.nextInt()%100);
		for(int i=0;i<NODESIZE;i++){
			
			for(int j=0;j<EDGESIZE;j++){
				temp = r.nextInt(NODESIZE);						//����0~1999֮��������
				
				if(temp!=i){									//��㡢�յ���ͬ
					cost = r.nextInt(COSTSIZE)+1;
					edgeSet[i*EDGESIZE+j][0] = i*EDGESIZE+j;	//��0�����������ñߵ��±�index
					edgeSet[i*EDGESIZE+j][1] = i;				//��1�����������ñߵ���� i
					edgeSet[i*EDGESIZE+j][2] = temp;			//��2�����������ñߵ��յ㣬�������
					edgeSet[i*EDGESIZE+j][3] = cost;			//��3�����������ñߵ�Ȩ�أ��������
				}
				else{
//					System.out.println("��"+i*EDGESIZE+j+"�����ظ���ֵΪ��"+i);
					j--;
				}
			}
		}
//		print();
		write();
	}
	
	private static void write() {
	// TODO Auto-generated method stub
		String result="";
		for(int i=0;i<ALLEDGESIZE;i++){
			
			for(int j=0;j<3;j++){
				result += edgeSet[i][j]+",";
//				System.out.print(edgeSet[i][j]+" ");
			}
			result += edgeSet[i][3]+"\r\n";
//			System.out.println();
		}
//		System.out.println("����ɹ���");
//		System.out.println(result);
		
		
		BufferedOutputStream Buff=null;
		FileOutputStream outSTr = null;
		try {
			outSTr = new FileOutputStream(new File(path ));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println("��������ļ�����");
		}
		Buff=new BufferedOutputStream(outSTr);
		try {
			Buff.write(result.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println("�ļ�д�����");
		}
		try {
			Buff.flush();
			Buff.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println("������رմ���");
		}
		System.out.println("��ӡ�ɹ���");
}

	private static void print() {
//		System.out.println("sizeΪ��"+ALLEDGESIZE);
		for(int i=0;i<ALLEDGESIZE;i++){
			
			for(int j=0;j<4;j++){
				System.out.print(edgeSet[i][j]+" ");
			}
			System.out.println();
		}
	}

}
