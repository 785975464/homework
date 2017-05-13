package password;

public class testMD5 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String password="SGAdmin";
		String p="8136e8d09db5b11558f0c0b3ace3f941";
		String md5 = getMD5.makeMD5(password);
		System.out.println(md5);
		System.out.println(getMD5.makeMD5(p));
	}

}
