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
		// ����ѧ��  
        ExportExcel<Student> ex = new ExportExcel<Student>();  
        String[] headers =  
        { "ѧ��", "����", "����", "�Ա�", "��������" };  
        List<Student> dataset = new ArrayList<Student>();
        Date temp = new Date();
        dataset.add(new Student(10000001, "����", 20, true, temp));  
        dataset.add(new Student(20000002, "����", 24, false, temp));  
        dataset.add(new Student(30000003, "����", 22, true, temp));  
        
        
        // ����ͼ��  
//        ExportExcel<Book> ex2 = new ExportExcel<Book>();  
//        String[] headers2 =  
//        { "ͼ����", "ͼ������", "ͼ������", "ͼ��۸�", "ͼ��ISBN", "ͼ�������", "����ͼƬ" };  
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
//                    "�廪������", buf));  
//            dataset2.add(new Book(2, "java���˼��", "brucl", 300.33f, "1234567",  
//                    "���������", buf));  
//            dataset2.add(new Book(3, "DOM����", "lenotang", 300.33f, "1234567",  
//                    "�廪������", buf));  
//            dataset2.add(new Book(4, "c++����", "leno", 400.33f, "1234567",  
//                    "�廪������", buf));  
//            dataset2.add(new Book(5, "c#����", "leno", 300.33f, "1234567",  
//                    "�����������", buf));  
  
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
        	String tempvalue = sdf.format(temp); 
//            OutputStream out = new FileOutputStream("E://"+tempvalue+".xls");
        	OutputStream out = new FileOutputStream("D://sky_tempdata//a.xls"); 
            
            ex.exportExcel(headers, dataset, out);  
            out.close();  
//            JOptionPane.showMessageDialog(null, "�����ɹ�!");  
            System.out.println("excel�����ɹ���");  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
	}
	
	
	public void test2(String imagesPath, String docsPath) {
		String FILE_SEPARATOR = System.getProperties()
				.getProperty("file.separator");
		// ����ѧ��
		ExportExcel<Student> ex = new ExportExcel<Student>();
		String[] headers = { "ѧ��", "����", "����", "�Ա�", "��������" };
		List<Student> dataset = new ArrayList<Student>();
		dataset.add(new Student(10000001, "����", 20, true, new Date()));
		dataset.add(new Student(20000002, "����", 24, false, new Date()));
		dataset.add(new Student(30000003, "����", 22, true, new Date()));
		// ����ͼ��
//		ExportExcel<Book> ex2 = new ExportExcel<Book>();
//		String[] headers2 = { "ͼ����", "ͼ������", "ͼ������", "ͼ��۸�", "ͼ��ISBN",
//				"ͼ�������", "����ͼƬ" };
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
//					"�廪������", buf));
//			dataset2.add(new Book(2, "java���˼��", "brucl", 300.33f, "1234567",
//					"���������", buf));
//			dataset2.add(new Book(3, "DOM����", "lenotang", 300.33f, "1234567",
//					"�廪������", buf));
//			dataset2.add(new Book(4, "c++����", "leno", 400.33f, "1234567",
//					"�廪������", buf));
//			dataset2.add(new Book(5, "c#����", "leno", 300.33f, "1234567",
//					"�����������", buf));
			OutputStream out = new FileOutputStream(docsPath + FILE_SEPARATOR
					+ "export2003_a.xls");
//			OutputStream out2 = new FileOutputStream(docsPath + FILE_SEPARATOR
//					+ "export2003_b.xls");
			ex.exportExcel(headers, dataset, out);
//			ex2.exportExcel(headers2, dataset2, out2);
			out.close();
//			out2.close();
			JOptionPane.showMessageDialog(null, "�����ɹ�!");
			System.out.println("excel�����ɹ���");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
