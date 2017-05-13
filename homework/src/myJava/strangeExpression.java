package myJava;

import java.util.Scanner;

public class strangeExpression {

	/**
	 * @param args
	 * 测试用例:
	4-8*9*1
	
	对应输出应该为:
	
	-36
	 */
	static int flag=0;	//0代表+,1代表-,2代表*
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String expression = sc.nextLine();
		char[] c = expression.toCharArray();
		int i,sum=0;
		for(i=0;i<c.length;i++){
			if(c[i]=='+'){
				flag=0;
			}
			else if(c[i]=='-'){
				flag=1;
			}
			else if(c[i]=='*'){
				flag=2;
			}
			else{	//数字0-9
				if(flag==0){
					sum+=c[i]-'0';
				}
				else if(flag==1){
					sum-=c[i]-'0';
				}
				else //if(flag==2){
					sum*=c[i]-'0';
//				}
			}
		}
		System.out.println(sum);
	}

}
