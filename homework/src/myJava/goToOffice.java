package myJava;

import java.util.Scanner;

public class goToOffice {
	private static int X=0,Y=0;
	private static int size;
	private static String temp;
	private static int[][] positionCar;
	private static int officeX,officeY;
	private static int walkTime,taxiTime;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		init();
		int i;
		int nearestP=20000;
		int walkDis=0,taxiDis=0,tatalTime=0;
		int minTime = (Math.abs(officeX)+Math.abs(officeY))*walkTime;
		for(i=0;i<size;i++){
			walkDis = Math.abs(positionCar[0][i])+Math.abs(positionCar[1][i]);
			taxiDis = Math.abs(positionCar[0][i]-officeX)+Math.abs(positionCar[1][i]-officeY);
			tatalTime = walkDis*walkTime+taxiDis*taxiTime;
			if(tatalTime<minTime){
				minTime=tatalTime;
			}
		}
		System.out.println(minTime);
	}
	
	public static void init(){
		Scanner sc = new Scanner(System.in);
		size = sc.nextInt();
		temp = sc.nextLine();//存放换行
		positionCar= new int[2][size];
		int i;
		for(i=0 ; i<size ; i++){
			positionCar[0][i] = sc.nextInt();
		}
		temp = sc.nextLine();//存放换行
		for(i=0 ; i<size ; i++){
			positionCar[1][i] = sc.nextInt();
		}
		temp = sc.nextLine();//存放换行
		officeX=sc.nextInt();
		officeY=sc.nextInt();
		temp = sc.nextLine();//存放换行
		walkTime=sc.nextInt();
		taxiTime=sc.nextInt();
	}

}
