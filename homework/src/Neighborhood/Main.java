package Neighborhood;

public class Main {
	public static void main(String agrs[]){
		neighborhood nb = new neighborhood();		//����==����ֵ=��ʵ����
		dayuan dy = new dayuan();
		String c = dy.getCarNum();
		String cNum = nb.getCarNum();
		//String carNum = "С����";
		
		System.out.println( c );
		System.out.println( cNum );
		System.out.println( dy );
		//System.out.println(  nb.getCarNum()  );
	}
}
