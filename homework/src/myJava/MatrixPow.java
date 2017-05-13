package myJava;

import java.util.Scanner;

public class MatrixPow {

	/**
	 * @param args
	 * 50 k
	 * 1 3 5 7 9 2 4 6 8 10 1 3 5 7 9 2 4 6 8 10 1 3 5 7 9 2 4 6 8 10 1 3 5 7 9 2 4 6 8 10 1 3 5 7 9 2 4 6 8 10
	 * ���
	 * 39 91 68 95 47 89 41 68 45 97 39 91 68 95 47 89 41 68 45 97 39 91 68 95 47 89 41 68 45 97 39 91 68 95 47 89 41 68 45 97 39 91 68 95 47 89 41 68 45 97
	 */
	static long[][] res;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int size = sc.nextInt();
		int k = sc.nextInt();
		String temp = sc.nextLine();//��Ż���
		long[] sequence= new long[size];
		
		int i,j;
		for(i=0 ; i<size ; i++){
			sequence[i] = sc.nextInt();
		}

		
		res  = new long[size][size];
		for(i=0 ; i<size ; i++){		//��ʼ��Ϊ��λ��
			for(j=0 ; j<size ; j++){
				if(i==j)
					res[i][j]=1;
				else
					res[i][j]=0;
			}
		}
//		print(res,size);
		
		long[][] matrix = new long[size][size];
		for(i=0 ; i<size ; i++){		//��ʼ���任����matrix
			for(j=0 ; j<size ; j++){
				if(i==j || j==i-1)
					matrix[i][j]=1;
				else if(i==0 && j==size-1)
					matrix[i][j]=1;
				else
					matrix[i][j]=0;
			}
		}
//		print(matrix,size);
		
		qpow(matrix,k,size);
//		print(res,size);
		getResult(res,sequence,size);
	}
	
	private static void getResult(long[][] matrix, long[] sequence, int size) {
		// TODO Auto-generated method stub
		long[][]sequence_num=new long[1][size];	//��չΪ��ά���飬����
		sequence_num[0]=sequence;
//		for(int i=0;i<size;i++){
//			sequence_num[i][0]=sequence[i];
//		}
		for(int i=0;i<size-1;i++){
			
			System.out.print(getMul(sequence_num,matrix,0,i,size)+" ");
//			System.out.println();
		}
		System.out.print(getMul(sequence_num,matrix,0,size-1,size));	//An*Tn^n
	}


	private static void print(long[][] matrix, int size) {
		// TODO Auto-generated method stub
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				System.out.print(matrix[i][j]+" ");
			}
			System.out.println();
		}
			
	}

	//������ a^b 
	static void qpow(long[][] a,int k, int size){
//		long startTime = System.currentTimeMillis();    //��ȡ��ʼʱ��
//	    if(a==0)return 0;
	    
	    while(k>0){
	        if((k&1)==1)
	        	res=MulMatrix(res,a,size);//�Ϳ��ٳ˷�������
	        k>>=1;
	        a=MulMatrix(a,a,size);//����ͬ��
	    }
//	    long endTime = System.currentTimeMillis();    //��ȡ����ʱ��
//		System.out.println("����1����ʱ�䣺" + (endTime - startTime) + "ms");    //�����������ʱ��
//	    return res;
	}

	private static long[][] MulMatrix(long[][] res, long[][] a, int size) {
		long[][]temp = new long[size][size];
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				temp[i][j]=getMul(res,a,i,j,size);
			}
		}
		return temp;
		// TODO Auto-generated method stub
	}

	private static long getMul(long[][] res, long[][] a, int row, int col, int size) {
		// TODO Auto-generated method stub
		int sum=0;
		for(int i=0;i<size;i++){
			sum+=res[row][i]*a[i][col];
		}
		return sum%100;
	}

}
