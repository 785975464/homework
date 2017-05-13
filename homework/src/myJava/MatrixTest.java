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
	
	//快速乘法 
	static int qmul(int a,int b){// 根据数据范围可选择long long 
	    int ans=0;
	    while(b>0){
	        if( (b&1)==1){
	        	System.out.println("b&1 "+(b&1));
	        	ans+=a;//按位与完成位数为1的判断
	        }
	        b>>=1;a<<=1;//位运算代替/2和*2
	        System.out.println("b "+b+" a "+a);
	    }
	    return ans;
	}
	
	//快速幂 a^b 
	static long qpow(long a,int b){
		long startTime = System.currentTimeMillis();    //获取开始时间
	    if(a==0)return 0;
	    long ans=1;
	    while(b>0){
	        if((b&1)==1)
	        	ans*=a;//和快速乘法的区别
	        b>>=1;a*=a;//区别，同上
	    }
	    long endTime = System.currentTimeMillis();    //获取结束时间
		System.out.println("程序1运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间
	    return ans;
	}
	
	static long simplepow(int a,int b){
		long startTime = System.currentTimeMillis();    //获取开始时间
		long ans=1;
	    while(b-->0){
	        ans*=a;
	    }
	    long endTime = System.currentTimeMillis();    //获取结束时间
		System.out.println("程序2运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间
		return ans;
	}

}
