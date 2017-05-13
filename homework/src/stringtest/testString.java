package stringtest;

public class testString {
	public static void main(String[] args){
		String s="camera1234567890";
		System.out.println(s.substring(2,s.length()-1));
		System.out.println(s.split("b")[0]);
		
		String a="%abc%def";
		System.out.println(a.replace("%", "A"));
		System.out.println(a);
	}
}
