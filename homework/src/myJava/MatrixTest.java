package myJava;

public class MatrixTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a=7,b=30;
//		System.out.println(qmul(4,b));
//		System.out.println(qpow(a,b));
//		System.out.println(simplepow(a,b));
		int[][] ma={{1,0,1},{1,1,0},{0,1,1}};
		int[][] mb={{1,0,1},{1,1,0},{0,1,1}};
		int[][] res;
		int size=3;
		res = MulMatrix(ma,mb,size);
		print(res,size);
	}
	private static void print(int[][] res, int size) {
		// TODO Auto-generated method stub
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				System.out.print(res[i][j]+" ");
			}
			System.out.println();
		}
			
	}
	private static int[][] MulMatrix(int[][] ma, int[][] mb, int size) {
		int[][]res = new int[size][size];
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
//				System.out.println("i j: "+i+" "+j);
				res[i][j]=getMul(ma,mb,i,j,size);
			}
		}
		return res;
		// TODO Auto-generated method stub
	}
	
	private static int getMul(int[][] ma, int[][] mb, int row, int col, int size) {
		// TODO Auto-generated method stub
		int sum=0;
		for(int i=0;i<size;i++){
			
//			System.out.println("ma[row][i]: "+ma[row][i]+" mb[i][col]: "+mb[i][col]);
			sum+=ma[row][i]*mb[i][col];
		}
		return sum;
	}
	
	//���ٳ˷� 
	static int qmul(int a,int b){// �������ݷ�Χ��ѡ��long long 
	    int ans=0;
	    while(b>0){
	        if( (b&1)==1){
	        	System.out.println("b&1 "+(b&1));
	        	ans+=a;//��λ�����λ��Ϊ1���ж�
	        }
	        b>>=1;a<<=1;//λ�������/2��*2
	        System.out.println("b "+b+" a "+a);
	    }
	    return ans;
	}
	
	//������ a^b 
	static long qpow(long a,int b){
		long startTime = System.currentTimeMillis();    //��ȡ��ʼʱ��
	    if(a==0)return 0;
	    long ans=1;
	    while(b>0){
	        if((b&1)==1)
	        	ans*=a;//�Ϳ��ٳ˷�������
	        b>>=1;a*=a;//����ͬ��
	    }
	    long endTime = System.currentTimeMillis();    //��ȡ����ʱ��
		System.out.println("����1����ʱ�䣺" + (endTime - startTime) + "ms");    //�����������ʱ��
	    return ans;
	}
	
	static long simplepow(int a,int b){
		long startTime = System.currentTimeMillis();    //��ȡ��ʼʱ��
		long ans=1;
	    while(b-->0){
	        ans*=a;
	    }
	    long endTime = System.currentTimeMillis();    //��ȡ����ʱ��
		System.out.println("����2����ʱ�䣺" + (endTime - startTime) + "ms");    //�����������ʱ��
		return ans;
	}

}
