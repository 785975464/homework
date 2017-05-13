/*
 * My Machinelearning Homework
 * By ZCY
 * 2015.11.27
 */
package machineLearning;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class regressionFun {
	private static double beta0,beta1;
	public static void main(String[] args) throws IOException{
		ArrayList<Double> x = new ArrayList<Double>();						//���x
		ArrayList<Double> y = new ArrayList<Double>();						//���y
		String filename = "G:/DATA/regressionData.txt";						//���ݸ�ʽΪ��x	(tab)	y
		BufferedReader file = new BufferedReader(new FileReader(filename));
		String str;
		double tempx,tempy;
		double meansx=0,meansy=0;
		while ( ( str = file.readLine() ) != null ) {						//��ȡx��y������ArrayList��
			tempx=Double.parseDouble( str.substring(0, 4) );
			tempy=Double.parseDouble( str.substring(4) );
			x.add(tempx);
			y.add(tempy);
			meansx += tempx;
			meansy += tempy;
		}
		int size = x.size();
		meansx = meansx/size;												//�õ�x�ľ�ֵ
		meansy = meansy/size;

		beta1 = getBeta(x,y,size,meansx);
		beta0 = meansy - beta1*meansx;
		
		/**
		for(int i=0;i<x.size();i++){
			System.out.println(x.get(i)+"\t"+y.get(i));
		}
		System.out.println("x�ľ�ֵ�ǣ�"+meansx+"\ty�ľ�ֵ�ǣ�"+meansy);
		**/
		System.out.println("�ع鷽��Ϊ��y0 = "+beta0+"+"+beta1+"x");
		System.out.println("2014��ķ���Ԥ��Ϊ��"+setX(2014));
	}
	public static double getBeta(ArrayList<Double> x, ArrayList<Double> y , int size , double means){		//����beta1
		double beta=0,Lxy=0,Lxx=0;
		for(int i=0;i<size;i++){
			Lxy += (x.get(i)-means) * y.get(i);								//����Lxy��Lxx
			Lxx += (x.get(i)-means) * (x.get(i)-means);
			//System.out.println("Lxy�ǣ�"+ (x.get(i)-means) * y.get(i)+"\tLxx�ǣ�"+ (x.get(i)-means) * (x.get(i)-means));
		}
		//System.out.println("��Lxy�ǣ�"+ Lxy +"\t��Lxx�ǣ�"+ Lxx );
		beta = Lxy/Lxx;
		return beta;
	}
	public static double setX(double x){
		return beta0+beta1*x;
	}
}
