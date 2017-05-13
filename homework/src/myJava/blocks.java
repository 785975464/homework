package myJava;

import java.util.Random;
import java.util.Scanner;

public class blocks {

	/**
	 * @param args
��������:
19
88242 313 1991 4207 2483 1763 224 16 582 22943 28632 47682 378 90 88 43 117 19 8

��Ӧ���Ӧ��Ϊ:

99901
	 */
	static int N=100;				//��ʼ��Ⱥ��
	static int m=2;				//��������
	static int n;				//��������
	static int totalTask=0;		//�����ܺ�
	static int[][] population;
//	static int[] task = {3072,3072,7168,3072,1024};
//	static int[] task = {3,3,7,3,1,2,2};
	static int[] task;
	static double[] F = new double[N];	//������Ӧ������
	static double T = 0.2;
	static double U = 0.2;
	static double sum;		//��Ӧֵ�ܺ�
	static double[] chooseProb = new double[N];	//��һ������Ӧ������
	static double[] s = new double[N];	//�ܸ���
	static double[] randomNumber = new double[N];		//�����
	static int[][] parents;		//��һ������
	static double MaxRandom=1;	//���ñ������
	static int GenerationMax=1000;		//���ֳ����
	static int MIN=2;			//��С��ֵ
	static int[] OPTIMAL;			//���Ž�
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		getTask();	//��������
//		System.out.println("task:");
//		print(task,n);
		//1��ѡ���ʼ��Ⱥ
//		n=task.length;		//���볤��
		
		//�������N ��λ��Ϊn,����Ȼ�������Ⱦɫ�崮��Ϊ��ʼ��Ⱥpopulation
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
				parents[i][j]=population[i][j];		//��һ������
			}
		}
//		System.out.println("population:");
//		printArray(population);
		
		while(GenerationMax-->0){		//ѭ������
//			System.out.println("��"+GenerationMax+"��------------------------------");
//			System.out.println("parents:");
//			printArray(parents);
			
			
			//2����Ӧ�ȼ���
			getFitValue(parents);		//�õ���Ӧֵ
//			System.out.println("F:");
//			print(F,N);
			getChooseProbValue();		//��һ��chooseProb��ѡ�����ֵ�����
			
//			System.out.println("chooseProb:");
//			print(chooseProb,N);
			
			//3��ѡ�񸸴�������
			setS();				//���ø��ʼ���
	//		System.out.println("s:");
	//		print(s,N);

			for(i=0;i<N;i++){
				randomNumber[i]=random.nextDouble();	//����0-1֮��������
			}
//			System.out.println("randomNumber:");
//			print(randomNumber,N);
			
			for(i=0;i<N;i++){
				for(j=0;j<N;j++){
					if(randomNumber[i]<=s[j]){		//���������randomNumber[i]����s[j]�����ϣ���ѡ��i��Ϊ��һ������
						setParent(i,j);
						break;
					}
				}
			}
			
//			System.out.println("parents:");
//			printArray(parents);

			//4������
			for(i=0;i<=N/2;i+=2){
				exchange(parents[i],parents[i+1]);		//����������λ����
			}
//			System.out.println("after change:");
//			printArray(parents);
			
			//5������
			int temp;
			for(i=0;i<N;i++){
				if(randomNumber[i]<MaxRandom){
					
					temp = random.nextInt(n);
//					System.out.println("�����:"+i+"�У���"+temp+"����");
					setVariation(i,temp);	//���õ�i�еı���λ
					
				}
			}
			
//			System.out.println("after variation:");
//			printArray(parents);
			
			//ÿһ����ѡ������Ⱦɫ��
			getOptimalIndividual(parents);
		}
		
		//ֹͣ������ѡ�����Ÿ���
//		System.out.println("finally parents:");
//		printArray(parents);
		System.out.println("OPTIMAL:");
		print(OPTIMAL,n);
		System.out.println("MIN:"+MIN);
//		System.out.println("������Ҫ:"+(MIN+totalTask)/2+"ʱ��");
		if(MIN==0){
			System.out.println(totalTask/2);
		}
		else{
			System.out.println("�߶�Ϊ:"+(MIN+totalTask)/2);
			System.out.println(-1);
		}
	}




	private static void getTask() {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		n=sc.nextInt();
		String temp=sc.nextLine();
		task = new int[n];
		for(int i=0;i<n;i++){
			task[i]=sc.nextInt();
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
				flag=true;						//�޸ı�־
				MIN=Math.abs(totalTask-2*temp);
				index=i;		//������Сֵ����
//				System.out.println("minimum:"+minimum+" &index:"+index);
			}
		}
		if(flag){	//MIN�Ѹ���
//			System.out.println("MIN:"+MIN+" &index:"+index);
			for(j=0;j<n;j++){				//��������ֵ
				OPTIMAL[j]=array[index][j];
			}
//			return index;
		}
//		return -1;
	}


	private static void setVariation(int i, int index) {	//���ñ���λ
		// TODO Auto-generated method stub
//		System.out.println("in variation:i="+i+" & index="+index+" & parents[i][index]="+parents[i][index]);
		parents[i][index] = parents[i][index]==1?2:1;	//1->2��2->1
//		printArray(parents);
	}


	private static void exchange(int[] fa, int[] ma) {
		// TODO Auto-generated method stub
		Random random = new Random();
		int index,temp;
		int count=2;		//��������
		for(int i=0;i<count;i++){
			index=random.nextInt(n);
//			System.out.println("������:"+index+"λ����");
			temp = fa[index];			//������λ����
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


	//��Ӧ��֭��
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
