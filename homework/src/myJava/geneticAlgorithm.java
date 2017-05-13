package myJava;

import java.util.Random;
import java.util.Scanner;

public class geneticAlgorithm {

	/**
	 * @param args
测试用例:
35
1981440 1696768 2514944 3563520 1326080 2675712 2722816 1843200 1269760 3243008 1122304 2787328 2188288 1091584 2243584 1929216 2001920 3135488 2984960 3218432 2497536 1995776 3871744 3980288 842752 2403328 3410944 2215936 3165184 3249152 3644416 3939328 925696 3391488 610304

对应输出应该为:

42842112
	 */
	static int N=20;				//初始种群数
	static int m=2;				//机器个数
	static int n;				//任务总数
	static int totalTask=0;		//任务总和
	static int[][] population;
//	static int[] task = {3072,3072,7168,3072,1024};
//	static int[] task = {3,3,7,3,1,2,2};
	static int[] task;
	static double[] F = new double[N];	//保存适应度评分
	static double T = 0.2;
	static double U = 0.2;
	static double sum;		//适应值总和
	static double[] chooseProb = new double[N];	//归一化的适应度评分
	static double[] s = new double[N];	//总概率
	static double[] randomNumber = new double[N];		//随机数
	static int[][] parents;		//下一个父代
	static double MaxRandom=0.5;	//设置变异概率
	static int GenerationMax=1000;		//最大繁殖代数
	static int MIN=5000;			//最小差值
	static int[] OPTIMAL;			//最优解
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		getTask();	//输入任务
//		System.out.println("task:");
//		print(task,n);
		//1、选择初始种群
//		n=task.length;		//编码长度
		
		//随机产生N 个位数为n,用自然数编码的染色体串作为初始种群population
		population = new int[N][n];
		parents = new int[N][n];
		OPTIMAL  = new int[n];
		int i,j;
		for(i=0;i<n;i++){
			totalTask+=task[i];
		}
		
		
		Random random = new Random();
		for(i=0;i<N;i++){
			for(j=0;j<n;j++){
				population[i][j]=random.nextInt(m)+1;
				parents[i][j]=population[i][j];		//第一代父代
			}
		}
//		System.out.println("population:");
//		printArray(population);
		
		while(GenerationMax-->0){		//循环迭代
//			System.out.println("第"+GenerationMax+"代------------------------------");
//			System.out.println("parents:");
//			printArray(parents);
			
			
			//2、适应度计算
			getFitValue(parents);		//得到适应值
//			System.out.println("F:");
//			print(F,N);
			getChooseProbValue();		//归一化chooseProb，选择最大值的序号
			
//			System.out.println("chooseProb:");
//			print(chooseProb,N);
			
			//3、选择父代，复制
			setS();				//设置概率集合
	//		System.out.println("s:");
	//		print(s,N);

			for(i=0;i<N;i++){
				randomNumber[i]=random.nextDouble();	//生成0-1之间的随机数
			}
//			System.out.println("randomNumber:");
//			print(randomNumber,N);
			
			for(i=0;i<N;i++){
				for(j=0;j<N;j++){
					if(randomNumber[i]<=s[j]){		//若随机概率randomNumber[i]落在s[j]区间上，则选择i作为下一个父代
						setParent(i,j);
						break;
					}
				}
			}
			
//			System.out.println("parents:");
//			printArray(parents);

			//4、交叉
			for(i=0;i<=N/2;i+=2){
				exchange(parents[i],parents[i+1]);		//两两交换等位基因
			}
//			System.out.println("after change:");
//			printArray(parents);
			
			//5、变异
			int temp;
			for(i=0;i<N;i++){
				if(randomNumber[i]<MaxRandom){
					
					temp = random.nextInt(n);
//					System.out.println("变异第:"+i+"行，第"+temp+"个数");
					setVariation(i,temp);	//设置第i行的变异位
					
				}
			}
			
//			System.out.println("after variation:");
//			printArray(parents);
			
			//每一代中选出最优染色体
			getOptimalIndividual(parents);
		}
		
		//停止迭代，选出最优个体
//		System.out.println("finally parents:");
//		printArray(parents);
//		System.out.println("OPTIMAL:");
//		print(OPTIMAL,n);
//		System.out.println("MIN:"+MIN);
//		System.out.println("最少需要:"+(MIN+totalTask)/2+"时间");
		System.out.println((MIN+totalTask)/2*1024);
	}




	private static void getTask() {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		n=sc.nextInt();
		String temp=sc.nextLine();
		task = new int[n];
		for(int i=0;i<n;i++){
			task[i]=sc.nextInt()/1024;
		}
	}




	private static void getOptimalIndividual(int[][] array) {
		// TODO Auto-generated method stub
		int temp,index=0;
		boolean flag=false;
		int i,j;
		for(i=0;i<N;i++){
			temp=0;
			for(j=0;j<n;j++){
				if(array[i][j]==1){
					temp+=task[j];
				}
			}
//			System.out.println("temp:"+temp);
			if(Math.abs(totalTask-2*temp)<MIN){
				flag=true;						//修改标志
				MIN=Math.abs(totalTask-2*temp);
				index=i;		//保存最小值索引
//				System.out.println("minimum:"+minimum+" &index:"+index);
			}
		}
		if(flag){	//MIN已更新
//			System.out.println("MIN:"+MIN+" &index:"+index);
			for(j=0;j<n;j++){				//保存最优值
				OPTIMAL[j]=array[index][j];
			}
//			return index;
		}
//		return -1;
	}


	private static void setVariation(int i, int index) {	//设置变异位
		// TODO Auto-generated method stub
//		System.out.println("in variation:i="+i+" & index="+index+" & parents[i][index]="+parents[i][index]);
		parents[i][index] = parents[i][index]==1?2:1;	//1->2，2->1
//		printArray(parents);
	}


	private static void exchange(int[] fa, int[] ma) {
		// TODO Auto-generated method stub
		Random random = new Random();
		int index,temp;
		int count=2;		//交换次数
		for(int i=0;i<count;i++){
			index=random.nextInt(n);
//			System.out.println("交换第:"+index+"位基因");
			temp = fa[index];			//交换等位基因
			fa[index] = ma[index];
			ma[index] = temp;
		}
	}


	private static void setParent(int i, int j) {
		// TODO Auto-generated method stub
		for(int k=0;k<n;k++){
			parents[i][k] = population[j][k];
		}
	}


	private static void setS() {
		// TODO Auto-generated method stub
		s[0]=chooseProb[0];
		for(int i=1;i<N;i++){
			s[i] = s[i-1]+chooseProb[i];
		}
	}


//	private static void selectMaxElement() {
//		// TODO Auto-generated method stub
//		double max=0;
//		for(int i=0;i<N;i++){
//			if(chooseProb[i]>max){
//				max = chooseProb[i];
//			}
//		}
//	}


	private static void getChooseProbValue() {
		// TODO Auto-generated method stub
		for(int i=0;i<N;i++){
			chooseProb[i]=F[i]/sum;
		}
	}


	//适应度汁算
	private static void getFitValue(int[][] array){
		int i,j,temp;
		sum=0;
		for(i=0;i<N;i++){
			F[i]=0;
			temp=0;
			for(j=0;j<n;j++){
				if(array[i][j]==1){
//					F[i]+=T*Math.exp(-U*task[j]);
					temp+=task[j];
//					F[i]+=Math.exp(-task[j]);
				}
			}
//			F[i] = 2*temp-totalTask>0?temp:totalTask-temp;
			F[i] = Math.exp( -(2*temp-totalTask>0?temp:totalTask-temp) );
//			System.out.println("F["+i+"]:"+(2*temp-totalTask>0?temp:totalTask-temp));
			sum+=F[i];
		}
	}
	

	private static void printResult(int index) {
		// TODO Auto-generated method stub
		for(int i=0;i<n;i++){
			System.out.print(parents[index][i]+" ");
		}
		System.out.println();
	}
	
	private static void printArray(int[][] array) {
		// TODO Auto-generated method stub
		for(int i=0;i<N;i++){
			for(int j=0;j<n;j++){
				System.out.print(array[i][j]+" ");
			}
			System.out.println();
		}
	}

	
	private static void print(double[] array, int length) {
		// TODO Auto-generated method stub
		for(int i=0;i<length;i++){
			System.out.print(array[i]+" ");
		}
		System.out.println();
	}
	
	private static void print(int[] array, int length) {
		// TODO Auto-generated method stub
		for(int i=0;i<length;i++){
			System.out.print(array[i]+" ");
		}
		System.out.println();
	}
}
