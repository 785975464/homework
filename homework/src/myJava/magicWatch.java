package myJava;

import java.util.Scanner;

public class magicWatch {

	/**
	 * @param args
	 * 
	 * long最大值：9223372036854775807
		int最大值：2147483647

	 * 50 k
	 * 1 3 5 7 9 2 4 6 8 10 1 3 5 7 9 2 4 6 8 10 1 3 5 7 9 2 4 6 8 10 1 3 5 7 9 2 4 6 8 10 1 3 5 7 9 2 4 6 8 10
	 * 输出
	 * 39 91 68 95 47 89 41 68 45 97 39 91 68 95 47 89 41 68 45 97 39 91 68 95 47 89 41 68 45 97 39 91 68 95 47 89 41 68 45 97 39 91 68 95 47 89 41 68 45 97
	 * 
	 * 
	 * 测试用例:
		7 192347
		3 15 7 1 16 1 72
		
		对应输出应该为:
		
		88 72 62 55 11 11 21
		
		由于本方法复杂度过大，可以使用MatrixPow文件中的矩阵快速幂方法进行计算，其复杂度为logN
	 */
	public static void main(String[] args) {
		long MAX=400000000000L;
//		long MAX=Long.MAX_VALUE;
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int size = sc.nextInt();
		int k = sc.nextInt();
		String temp = sc.nextLine();//存放换行
		long[] sequence= new long[size];
		int i,j;
		for(i=0 ; i<size ; i++){
			sequence[i] = sc.nextInt();
		}
		long startTime = System.currentTimeMillis();    //获取开始时间
		
		//方法一，循环计算
		long tempfirst=sequence[0];
		for(i=0 ; i<k ; i++){	//循环k遍
			for(j=0 ; j<size-1 ; j++){
//				print(sequence,size);
//				if(j+1>=size){
//					sequence[j]+=tempfirst;
//				}
//				else{
					sequence[j]+=sequence[j+1];
//				}
//				if(sequence[j]>=100)
//					sequence[j]%=100;
//				System.out.println("sequence[j]: "+sequence[j]+" sequence[(j+1)%size]: "+sequence[(j+1)%size]);
					
				if(sequence[j]>MAX){
					sequence[j]%=100;
				}
			}
			sequence[j]+=tempfirst;
//			sequence[size-1]%=100;
			tempfirst=sequence[0];	//重置首位
		}
//		System.out.println("long最大值：" +Long.MAX_VALUE);    //输出最大值
//		System.out.println("int最大值：" +Integer.MAX_VALUE);    //输出最大值
		long endTime = System.currentTimeMillis();    //获取结束时间
		System.out.println("程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间
		
		//方法二，代数计算
		if(k==1){
			print(sequence,size);
			return;
		}
		int an,type,Max=0,Min=1;
		if(k%2==0){
			//若k为偶数
			an=(int) ((Math.pow(2, k)+2)/3);
			type = Max;
		}
		else{
			an=(int) ((Math.pow(2, k)-2)/3);
			type = Min;
		}
		for(i=0;i<size;i++){
			
		}
		print(sequence,size);
	}
	
	static void print(long[] sequence,int size){
		System.out.print(sequence[0]%100);
		for(int i=1 ; i<size ; i++){
			System.out.print(" "+sequence[i]%100);
		}
//		System.out.println();
//		
//		System.out.print(sequence[0]);
//		for(int i=1 ; i<size ; i++){
//			System.out.print(" "+sequence[i]);
//		}
	}

}
