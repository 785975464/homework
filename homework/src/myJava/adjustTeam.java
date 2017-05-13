package myJava;

import java.util.Scanner;

public class adjustTeam {

	/**
	 * @param args
	 * 测试用例:
		BGBGBGBGGGBBGBGBGG
		
		对应输出应该为:
		
		33
		
		测试用例:
		BBGGGBGGBGBBGBBBBBBBGGGBBBBGBBBBBGBBBGBGBBGBG
		
		对应输出应该为:
		
		208
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String team = sc.nextLine();
		int size = team.length();
//		int[] flag = new int[size];		//记录转换的二进制数
		int i,j,k,countLeft=0,countRight=0;
//		int sum=0,countB=0;
		char [] teamArr = team.toCharArray();

		//向右调整
		for(j=0,k=size-1;j<=k;){		//j,k头尾指针
//			if(flag[j]==1){
			if(teamArr[j]=='B'){
//				if(flag[k]==0){
				if(teamArr[k]=='G'){
					countRight += k-j;	//交换成功
					j++;
					k--;
				}
				else{
					k--;
				}
			}
			else{
				j++;
			}
		}

		//向左调整
		for(j=0,k=size-1;j<=k;){		//j,k头尾指针
//			if(flag[k]==1){
			if(teamArr[k]=='B'){
//				if(flag[j]==0){
				if(teamArr[j]=='G'){
					countLeft += k-j;	//交换成功
					j++;
					k--;
				}
				else{
					j++;
				}
			}
			else{
				k--;
			}
		}
		System.out.println(countLeft<countRight?countLeft:countRight);
//		System.out.println("countLeft is: "+countLeft+" countRight is: "+countRight+" size is: "+size);
	}

}
