package myJava;

import java.util.Scanner;

public class adjustTeam {

	/**
	 * @param args
	 * ��������:
		BGBGBGBGGGBBGBGBGG
		
		��Ӧ���Ӧ��Ϊ:
		
		33
		
		��������:
		BBGGGBGGBGBBGBBBBBBBGGGBBBBGBBBBBGBBBGBGBBGBG
		
		��Ӧ���Ӧ��Ϊ:
		
		208
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String team = sc.nextLine();
		int size = team.length();
//		int[] flag = new int[size];		//��¼ת���Ķ�������
		int i,j,k,countLeft=0,countRight=0;
//		int sum=0,countB=0;
		char [] teamArr = team.toCharArray();

		//���ҵ���
		for(j=0,k=size-1;j<=k;){		//j,kͷβָ��
//			if(flag[j]==1){
			if(teamArr[j]=='B'){
//				if(flag[k]==0){
				if(teamArr[k]=='G'){
					countRight += k-j;	//�����ɹ�
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

		//�������
		for(j=0,k=size-1;j<=k;){		//j,kͷβָ��
//			if(flag[k]==1){
			if(teamArr[k]=='B'){
//				if(flag[j]==0){
				if(teamArr[j]=='G'){
					countLeft += k-j;	//�����ɹ�
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
