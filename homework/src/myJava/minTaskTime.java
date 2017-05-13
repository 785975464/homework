package myJava;

import java.util.Scanner;

public class minTaskTime {

	/**
	 * @param args
	 * 测试用例:
		10
		1411072 2110464 1388544 2362368 1103872 59392 133120 1184768 1500160 1332224
		
		对应输出应该为:
		
		6295552
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int size = sc.nextInt();
		String temp = sc.nextLine();//存放换行
		int[] array= new int[size];
		int[] flag=new int[size];
		//输入
		int sum=0,diff=0;	//总和、差
		int i;
		for(i=0 ; i<size ; i++){
			array[i] = sc.nextInt();
			sum+=array[i];
			flag[i]=0;
		}
		
		long startTime = System.currentTimeMillis();    //获取开始时间
		
		int diff_min=4194304,sum_min=0;
		int sumA=0;
		
		for(i=0 ; i<Math.pow(2,size-1) ; i++){
			sumA = getSum(array,flag,size);
			//计算差值
			diff = 2*sumA>=sum ? 2*sumA-sum : sum-2*sumA;
			if(diff<diff_min){
				diff_min = diff;
				sum_min = sumA;
//				System.out.println("diff_min is: "+diff_min+" & sum_min is: "+sum_min);
			}
			update(flag,size);
//			getArray(flag,size);
		}
		
		System.out.println(2*sum_min>=sum ? sum_min : sum-sum_min);
		long endTime = System.currentTimeMillis();    //获取结束时间
		System.out.println("程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间
	}

	private static void update(int[] flag, int size) {
		// TODO Auto-generated method stub
		updateFlag(flag,size,0);

	}

	private static void updateFlag(int[] flag, int size, int i) {
		
		// TODO Auto-generated method stub
		if(i==size) return;
		else{
//			array[i]++;
			if(flag[i]>0){
				flag[i]=0;
				updateFlag(flag,size,i+1);
			}
			else{
				flag[i]=1;
				return;
			}
		}
		
	}

	private static void getArray(int[] array, int size) {
		// TODO Auto-generated method stub
		for(int i=0;i<size;i++){
			System.out.print(array[i]+" ");
		}
		System.out.println();
	}

	private static int getSum(int[] array, int[] flag, int size) {
		// TODO Auto-generated method stub
		int sum=0;
		for(int i=0; i<size ;i++){
//			System.out.println("in getSum: "+i+" flag[i] is: "+flag[i]);
			sum += array[i]*flag[i];
		}
		return sum;
	}

}
