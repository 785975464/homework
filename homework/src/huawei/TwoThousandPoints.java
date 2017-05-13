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
				temp = r.nextInt(NODESIZE);						//生成0~1999之间的随机数
				
				if(temp!=i){									//起点、终点相同
					cost = r.nextInt(COSTSIZE)+1;
					edgeSet[i*EDGESIZE+j][0] = i*EDGESIZE+j;	//第0个参数，设置边的下标index
					edgeSet[i*EDGESIZE+j][1] = i;				//第1个参数，设置边的起点 i
					edgeSet[i*EDGESIZE+j][2] = temp;			//第2个参数，设置边的终点，随机生成
					edgeSet[i*EDGESIZE+j][3] = cost;			//第3个参数，设置边的权重，随机生成
				}
				else{
//					System.out.println("第"+i*EDGESIZE+j+"条边重复，值为："+i);
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
//		System.out.println("输出成功！");
//		System.out.println(result);
		
		
		BufferedOutputStream Buff=null;
		FileOutputStream outSTr = null;
		try {
			outSTr = new FileOutputStream(new File(path ));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println("创建输出文件错误！");
		}
		Buff=new BufferedOutputStream(outSTr);
		try {
			Buff.write(result.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println("文件写入错误！");
		}
		try {
			Buff.flush();
			Buff.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println("输出流关闭错误！");
		}
		System.out.println("打印成功！");
}

	private static void print() {
//		System.out.println("size为："+ALLEDGESIZE);
		for(int i=0;i<ALLEDGESIZE;i++){
			
			for(int j=0;j<4;j++){
				System.out.print(edgeSet[i][j]+" ");
			}
			System.out.println();
		}
	}

}
