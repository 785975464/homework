package myJava;

import java.util.Scanner;

public class magicWatch {

	/**
	 * @param args
	 * 
	 * long���ֵ��9223372036854775807
		int���ֵ��2147483647

	 * 50 k
	 * 1 3 5 7 9 2 4 6 8 10 1 3 5 7 9 2 4 6 8 10 1 3 5 7 9 2 4 6 8 10 1 3 5 7 9 2 4 6 8 10 1 3 5 7 9 2 4 6 8 10
	 * ���
	 * 39 91 68 95 47 89 41 68 45 97 39 91 68 95 47 89 41 68 45 97 39 91 68 95 47 89 41 68 45 97 39 91 68 95 47 89 41 68 45 97 39 91 68 95 47 89 41 68 45 97
	 * 
	 * 
	 * ��������:
		7 192347
		3 15 7 1 16 1 72
		
		��Ӧ���Ӧ��Ϊ:
		
		88 72 62 55 11 11 21
		
		���ڱ��������Ӷȹ��󣬿���ʹ��MatrixPow�ļ��еľ�������ݷ������м��㣬�临�Ӷ�ΪlogN
	 */
	public static void main(String[] args) {
		long MAX=400000000000L;
//		long MAX=Long.MAX_VALUE;
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
		long startTime = System.currentTimeMillis();    //��ȡ��ʼʱ��
		
		//����һ��ѭ������
		long tempfirst=sequence[0];
		for(i=0 ; i<k ; i++){	//ѭ��k��
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
			tempfirst=sequence[0];	//������λ
		}
//		System.out.println("long���ֵ��" +Long.MAX_VALUE);    //������ֵ
//		System.out.println("int���ֵ��" +Integer.MAX_VALUE);    //������ֵ
		long endTime = System.currentTimeMillis();    //��ȡ����ʱ��
		System.out.println("��������ʱ�䣺" + (endTime - startTime) + "ms");    //�����������ʱ��
		
		//����������������
		if(k==1){
			print(sequence,size);
			return;
		}
		int an,type,Max=0,Min=1;
		if(k%2==0){
			//��kΪż��
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
