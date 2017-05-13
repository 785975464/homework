package myJava;

import java.util.Scanner;

public class workArrange {

	/**
	 * @param args
	 * ��������:
		2
		20
		502431
		
		��Ӧ���Ӧ��Ϊ: 10
		
		��������:
		2
		243015
		312540
		
		��Ӧ���Ӧ��Ϊ: 30
		
		��������:
		4
		45230
		140
		2
		130
		
		��Ӧ���Ӧ��Ϊ: 18

	 */
	static int[][] flag;					//��¼����ķ������������գ�
	static int[] flag_start;			//��¼ÿһ�еķ����±�ֵ��������գ�
	static int[][]taskMatrix;
	static int taskNumber = 6;
	static int size,count=0;								//��¼Ա�������ܵķ�����
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		size = sc.nextInt();
		String temp = sc.nextLine();//��Ż���
		char[][] taskInput = new char[size][];			//�洢����
		
		taskMatrix = new int[size][taskNumber];			//�������
		flag = new int[size][taskNumber];						//��¼ÿһ�еķ��ʱ�־��0��ʾδ�����ʹ�
		flag_start = new int[size];						//��¼ÿһ�еķ����±�ֵ��0Ϊ��ʼֵ
		int i,j;
		for(i=0 ; i<size ; i++){		//��ʼ��
			for(j=0 ; j<taskNumber ; j++){
				taskMatrix[i][j]=0;
				flag[i][j]=0;
			}
			flag_start[i]=0;
		}
		//�����������
		for(i=0 ; i<size ; i++){
			taskInput[i] = sc.nextLine().toCharArray();
//			System.out.println("taskInput[i]: "+String.valueOf(taskInput[i]));
			for(j=0 ; j<taskInput[i].length ; j++){
				taskMatrix[i][ taskInput[i][j]-'0' ]=1;
			}
		}

//		print(taskMatrix, size, taskNumber);
		deepsearch(0);
		System.out.println(count);
	}
	
	static void deepsearch(int row){
		
//		System.out.println("in deepsearch row:"+row);
		int i;
		if(row==size-1){				//�������²�ڵ�
			for(i=0;i<taskNumber;i++){
				if(flag[row][i]==0 && taskMatrix[row][i]==1){	//�ýڵ�δ�����ʣ��ҿ��Է���
					flag_start[row]=i;
//					System.out.println("last line, flag_start["+row+"]:"+flag_start[row]+", flag["+i+"]:"+flag[i]);
					count++;
//					printFlag_start();
				}
			}
			flag_start[size-1]=0;
		}
		else{
			for(i=flag_start[row];i<taskNumber;i++){
				
				if(flag[row][i]==0 && taskMatrix[row][i]==1){	//�ýڵ�δ�����ʣ��ҿ��Է���
					set_flag(row,i);
					flag_start[row]=i+1;		//�ڽ�����һ��ڵ�֮ǰ������뿪λ��+1�����´�ѭ���Ŀ�ʼλ��
//					System.out.println("in deepsearch i:"+i+" & flag_start["+row+"]:"+flag_start[row]+" & flag["+row+"]"+"["+i+"]:"+flag[row][i]);
					update_flag_start(row);
					deepsearch(row+1);
					clear_flag_row(row,flag_start[row]-1);		//flag_start[row]-1��¼���η��ʵ��±�
				}
			}
		}
		
	}
	
	private static void set_flag(int row, int j) {
		// TODO Auto-generated method stub
		for(int i=row;i<size;i++){
			flag[i][j]=1;
		}
	}

	private static void clear_flag_row(int row, int j) {
		// TODO Auto-generated method stub
		for(int i=row;i<size;i++){
			flag[i][j]=0;		//�����һ�γ���1���±꣨�У�
		}
	}

	private static void printFlag_start() {
		// TODO Auto-generated method stub
		System.out.print("flag_start is :");
		for(int i=0;i<size-1;i++){
			System.out.print((flag_start[i]-1)+" ");
		}
		System.out.println(flag_start[size-1]);
	}

	private static void update_flag_start(int row) {
		// TODO Auto-generated method stub
//		System.out.println("update flag_start is :"+row+"��");
		for(int i=row+1;i<size;i++){
			flag_start[i]=0;
		}
	}

	private static void print(int[][] matrix, int row, int col) {
		// TODO Auto-generated method stub
		for(int i=0;i<row;i++){
			for(int j=0;j<col;j++){
				System.out.print(matrix[i][j]+" ");
			}
			System.out.println();
		}
	}

}
