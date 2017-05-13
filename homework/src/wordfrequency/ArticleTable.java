package wordfrequency;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ArticleTable {
	public static ArrayList<String>  getArticles(String filename) throws IOException{
		ArrayList<String> Article = new ArrayList<String>();
		BufferedReader infile = null;
		String str;
		infile = new BufferedReader(new FileReader(filename));				//打开一篇文章，名字是参数filename
		while ( ( str = infile.readLine() ) != null ) {
			if( str.startsWith("<docid>") && str.endsWith("</docid>") ){	//找到以<docid>开头、</docid>结尾的字符串
				String file = "";
				while( !infile.readLine().equals("<text>") ){				//继续向下读取，直至遇到<text>标签
					//System.out.println("一行空数据！");						//while未读到正文，不存入file中，继续读取
				}
				while( !(str = infile.readLine()).equals("</text>") ){		//while读到<text>标签，开始读取文本内容
					file += str;											//读出整篇文章，存入file中
				}
				//System.out.println("file串为："+file);
				Article.add(file);											//将file字符串加入ArticleList中
			}
		}
		//System.out.println("WHILE循环结束！");
		return Article;														//循环结束，返回文章列表
	}
}

	/**
	public static void checkindex(ArrayList<Integer> list){			//检查文章序列，是否有序号缺失
		System.out.println("开始检查文章序列！");
		int temp,next;
		for(int i=0;i<list.size()-1;i++){
			temp=list.get(i);
			next=list.get(i+1);
			if(next-temp!=1){
				System.out.println("当前为："+temp+"\t下一个为："+next);
			}
		}
		System.out.println("序列检查结束！长度为："+list.size());
	}
	**/
