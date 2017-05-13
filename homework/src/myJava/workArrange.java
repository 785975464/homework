package myJava;

import java.util.Scanner;

public class workArrange {

	/**
	 * @param args
	 * 测试用例:
		2
		20
		502431
		
		对应输出应该为: 10
		
		测试用例:
		2
		243015
		312540
		
		对应输出应该为: 30
		
		测试用例:
		4
		45230
		140
		2
		130
		
		对应输出应该为: 18

	 */
	static int[][] flag;					//记录总体的访问情况（可清空）
	static int[] flag_start;			//记录每一行的访问下标值（不可清空）
	static int[][]taskMatrix;
	static int taskNumber = 6;
	static int size,count=0;								//记录员工数，总的分配数
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		size = sc.nextInt();
		String temp = sc.nextLine();//存放换行
		char[][] taskInput = new char[size][];			//存储输入
		
		taskMatrix = new int[size][taskNumber];			//任务矩阵
		flag = new int[size][taskNumber];						//记录每一行的访问标志，0表示未被访问过
		flag_start = new int[size];						//记录每一行的访问下标值，0为初始值
		int i,j;
		for(i=0 ; i<size ; i++){		//初始化
			for(j=0 ; j<taskNumber ; j++){
				taskMatrix[i][j]=0;
				flag[i][j]=0;
			}
			flag_start[i]=0;
		}
		//构建任务矩阵
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
		if(row==size-1){				//遍历最下层节点
			for(i=0;i<taskNumber;i++){
				if(flag[row][i]==0 && taskMatrix[row][i]==1){	//该节点未被访问，且可以访问
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
				
				if(flag[row][i]==0 && taskMatrix[row][i]==1){	//该节点未被访问，且可以访问
					set_flag(row,i);
					flag_start[row]=i+1;		//在进入下一层节点之前，标记离开位置+1，即下次循环的开始位置
//					System.out.println("in deepsearch i:"+i+" & flag_start["+row+"]:"+flag_start[row]+" & flag["+row+"]"+"["+i+"]:"+flag[row][i]);
					update_flag_start(row);
					deepsearch(row+1);
					clear_flag_row(row,flag_start[row]-1);		//flag_start[row]-1记录本次访问的下标
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
			flag[i][j]=0;		//清除上一次出现1的下标（列）
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
//		System.out.println("update flag_start is :"+row+"行");
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
