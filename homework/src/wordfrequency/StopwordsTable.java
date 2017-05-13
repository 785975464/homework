package wordfrequency;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class StopwordsTable {
	public static ArrayList<String> getStopWords() throws IOException{
		FileReader fr=new FileReader("G:/NYSKDATA/en_stopword.txt");		//载入停用词文件
    	BufferedReader br=new BufferedReader(fr);							//读取文件至缓冲区
    	String line="";
    	ArrayList<String> list=new ArrayList<String>();
    	while ((line=br.readLine())!=null) {		//while下一行不为空，继续读取停用词，并存入数组中
    		list.add(line);
    	}
    br.close();										//关闭数据流
	return list;									//返回停用词表
	}
}
