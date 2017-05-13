package downloadExcel;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

import javax.swing.JOptionPane;

public class Main {

	/**
	 * @param args
	 */
	public void test(){
//	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 测试学生  
        ExportExcel<Student> ex = new ExportExcel<Student>();  
        String[] headers =  
        { "学号", "姓名", "年龄", "性别", "出生日期" };  
        List<Student> dataset = new ArrayList<Student>();
        Date temp = new Date();
        dataset.add(new Student(10000001, "张三", 20, true, temp));  
        dataset.add(new Student(20000002, "李四", 24, false, temp));  
        dataset.add(new Student(30000003, "王五", 22, true, temp));  
        
        
        // 测试图书  
//        ExportExcel<Book> ex2 = new ExportExcel<Book>();  
//        String[] headers2 =  
//        { "图书编号", "图书名称", "图书作者", "图书价格", "图书ISBN", "图书出版社", "封面图片" };  
//        List<Book> dataset2 = new ArrayList<Book>();  
        try  
        {  
//            BufferedInputStream bis = new BufferedInputStream(  
//                    new FileInputStream("V://book.bmp"));  
//            byte[] buf = new byte[bis.available()];  
//            while ((bis.read(buf)) != -1)  
//            {  
//                //  
//            }  
//            dataset2.add(new Book(1, "jsp", "leno", 300.33f, "1234567",  
//                    "清华出版社", buf));  
//            dataset2.add(new Book(2, "java编程思想", "brucl", 300.33f, "1234567",  
//                    "阳光出版社", buf));  
//            dataset2.add(new Book(3, "DOM艺术", "lenotang", 300.33f, "1234567",  
//                    "清华出版社", buf));  
//            dataset2.add(new Book(4, "c++经典", "leno", 400.33f, "1234567",  
//                    "清华出版社", buf));  
//            dataset2.add(new Book(5, "c#入门", "leno", 300.33f, "1234567",  
//                    "汤春秀出版社", buf));  
  
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
        	String tempvalue = sdf.format(temp); 
//            OutputStream out = new FileOutputStream("E://"+tempvalue+".xls");
        	OutputStream out = new FileOutputStream("D://sky_tempdata//a.xls"); 
            
            ex.exportExcel(headers, dataset, out);  
            out.close();  
//            JOptionPane.showMessageDialog(null, "导出成功!");  
            System.out.println("excel导出成功！");  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
	}
	
	
	public void test2(String imagesPath, String docsPath) {
		String FILE_SEPARATOR = System.getProperties()
				.getProperty("file.separator");
		// 测试学生
		ExportExcel<Student> ex = new ExportExcel<Student>();
		String[] headers = { "学号", "姓名", "年龄", "性别", "出生日期" };
		List<Student> dataset = new ArrayList<Student>();
		dataset.add(new Student(10000001, "张三", 20, true, new Date()));
		dataset.add(new Student(20000002, "李四", 24, false, new Date()));
		dataset.add(new Student(30000003, "王五", 22, true, new Date()));
		// 测试图书
//		ExportExcel<Book> ex2 = new ExportExcel<Book>();
//		String[] headers2 = { "图书编号", "图书名称", "图书作者", "图书价格", "图书ISBN",
//				"图书出版社", "封面图片" };
//		List<Book> dataset2 = new ArrayList<Book>();
		try {
//			BufferedInputStream bis = new BufferedInputStream(
//					new FileInputStream(imagesPath + FILE_SEPARATOR
//							+ "book.png"));
//			byte[] buf = new byte[bis.available()];
//			while ((bis.read(buf)) != -1) {
//				//
//			}
//			dataset2.add(new Book(1, "jsp", "leno", 300.33f, "1234567",
//					"清华出版社", buf));
//			dataset2.add(new Book(2, "java编程思想", "brucl", 300.33f, "1234567",
//					"阳光出版社", buf));
//			dataset2.add(new Book(3, "DOM艺术", "lenotang", 300.33f, "1234567",
//					"清华出版社", buf));
//			dataset2.add(new Book(4, "c++经典", "leno", 400.33f, "1234567",
//					"清华出版社", buf));
//			dataset2.add(new Book(5, "c#入门", "leno", 300.33f, "1234567",
//					"汤春秀出版社", buf));
			OutputStream out = new FileOutputStream(docsPath + FILE_SEPARATOR
					+ "export2003_a.xls");
//			OutputStream out2 = new FileOutputStream(docsPath + FILE_SEPARATOR
//					+ "export2003_b.xls");
			ex.exportExcel(headers, dataset, out);
//			ex2.exportExcel(headers2, dataset2, out2);
			out.close();
//			out2.close();
			JOptionPane.showMessageDialog(null, "导出成功!");
			System.out.println("excel导出成功！");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
