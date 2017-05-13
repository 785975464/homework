package myJava;

import java.util.Random;

public class multiMachine {

	/**
	 * @param args
	 */
//	static int[] task = {3072,3072,7168,3072,1024};
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] task = {3072,3072,7168,3072,1024};
		int m=2;
		int L=task.length/m+1;
		
		int[][] chromosome = new int[m][L];
		
		quickSort(task);
		
		//1、选择初始种群，生成m个长度为L的染色体
		int max=3;
		Random random = new Random();
		int s;
		int i;
		for(i=0;i<L;i++){
//			s= random.nextInt(max)+1;	//random.nextInt(max)表示生成[0,max)之间的随机数
			chromosome[0][i]=random.nextInt(max)+1;
			chromosome[1][i]=random.nextInt(max)+1;
		}
//		for(i=0;i<task.length;i++){
//			System.out.println(task[i]);
//		}
		for(i=0;i<L;i++){
			System.out.println(chromosome[0][i]+" "+chromosome[1][i]);
		}
	}
	
	private static void quickSort(int array[]) {
		//快排
		sort(array,0,array.length-1);
	}

	private static void sort(int[] array, int low, int high) {
		// TODO Auto-generated method stub
		int i,j,index;
		if(low>=high) return;
		i=low;
		j=high;
		index=array[i];
		while(i<j){
			while(i<j && array[j]>=index){
				j--;
			}
			if(i<j){
				array[i++]=array[j];
			}
			while(i<j && array[i]<index){
				i++;
			}
			if(i<j){
				array[j--]=array[i];
			}
			array[i]=index;
			sort(array,low,i-1);
			sort(array,i+1,high);
		}
	}

//	void Tms(){
//		int t1=0,t2=0;
//		for(int i=0;i<)
//	}

}
