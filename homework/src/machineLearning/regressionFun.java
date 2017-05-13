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
		ArrayList<Double> x = new ArrayList<Double>();						//存放x
		ArrayList<Double> y = new ArrayList<Double>();						//存放y
		String filename = "G:/DATA/regressionData.txt";						//数据格式为：x	(tab)	y
		BufferedReader file = new BufferedReader(new FileReader(filename));
		String str;
		double tempx,tempy;
		double meansx=0,meansy=0;
		while ( ( str = file.readLine() ) != null ) {						//读取x、y并存入ArrayList中
			tempx=Double.parseDouble( str.substring(0, 4) );
			tempy=Double.parseDouble( str.substring(4) );
			x.add(tempx);
			y.add(tempy);
			meansx += tempx;
			meansy += tempy;
		}
		int size = x.size();
		meansx = meansx/size;												//得到x的均值
		meansy = meansy/size;

		beta1 = getBeta(x,y,size,meansx);
		beta0 = meansy - beta1*meansx;
		
		/**
		for(int i=0;i<x.size();i++){
			System.out.println(x.get(i)+"\t"+y.get(i));
		}
		System.out.println("x的均值是："+meansx+"\ty的均值是："+meansy);
		**/
		System.out.println("回归方程为：y0 = "+beta0+"+"+beta1+"x");
		System.out.println("2014年的房价预测为："+setX(2014));
	}
	public static double getBeta(ArrayList<Double> x, ArrayList<Double> y , int size , double means){		//计算beta1
		double beta=0,Lxy=0,Lxx=0;
		for(int i=0;i<size;i++){
			Lxy += (x.get(i)-means) * y.get(i);								//计算Lxy、Lxx
			Lxx += (x.get(i)-means) * (x.get(i)-means);
			//System.out.println("Lxy是："+ (x.get(i)-means) * y.get(i)+"\tLxx是："+ (x.get(i)-means) * (x.get(i)-means));
		}
		//System.out.println("∑Lxy是："+ Lxy +"\t∑Lxx是："+ Lxx );
		beta = Lxy/Lxx;
		return beta;
	}
	public static double setX(double x){
		return beta0+beta1*x;
	}
}
