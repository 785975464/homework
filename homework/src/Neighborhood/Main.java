package Neighborhood;

public class Main {
	public static void main(String agrs[]){
		neighborhood nb = new neighborhood();		//等于==，赋值=，实例化
		dayuan dy = new dayuan();
		String c = dy.getCarNum();
		String cNum = nb.getCarNum();
		//String carNum = "小汽车";
		
		System.out.println( c );
		System.out.println( cNum );
		System.out.println( dy );
		//System.out.println(  nb.getCarNum()  );
	}
}
