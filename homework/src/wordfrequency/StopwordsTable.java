package wordfrequency;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class StopwordsTable {
	public static ArrayList<String> getStopWords() throws IOException{
		FileReader fr=new FileReader("G:/NYSKDATA/en_stopword.txt");		//����ͣ�ô��ļ�
    	BufferedReader br=new BufferedReader(fr);							//��ȡ�ļ���������
    	String line="";
    	ArrayList<String> list=new ArrayList<String>();
    	while ((line=br.readLine())!=null) {		//while��һ�в�Ϊ�գ�������ȡͣ�ôʣ�������������
    		list.add(line);
    	}
    br.close();										//�ر�������
	return list;									//����ͣ�ôʱ�
	}
}
