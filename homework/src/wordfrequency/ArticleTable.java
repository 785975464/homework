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
		infile = new BufferedReader(new FileReader(filename));				//��һƪ���£������ǲ���filename
		while ( ( str = infile.readLine() ) != null ) {
			if( str.startsWith("<docid>") && str.endsWith("</docid>") ){	//�ҵ���<docid>��ͷ��</docid>��β���ַ���
				String file = "";
				while( !infile.readLine().equals("<text>") ){				//�������¶�ȡ��ֱ������<text>��ǩ
					//System.out.println("һ�п����ݣ�");						//whileδ�������ģ�������file�У�������ȡ
				}
				while( !(str = infile.readLine()).equals("</text>") ){		//while����<text>��ǩ����ʼ��ȡ�ı�����
					file += str;											//������ƪ���£�����file��
				}
				//System.out.println("file��Ϊ��"+file);
				Article.add(file);											//��file�ַ�������ArticleList��
			}
		}
		//System.out.println("WHILEѭ��������");
		return Article;														//ѭ�����������������б�
	}
}

	/**
	public static void checkindex(ArrayList<Integer> list){			//����������У��Ƿ������ȱʧ
		System.out.println("��ʼ����������У�");
		int temp,next;
		for(int i=0;i<list.size()-1;i++){
			temp=list.get(i);
			next=list.get(i+1);
			if(next-temp!=1){
				System.out.println("��ǰΪ��"+temp+"\t��һ��Ϊ��"+next);
			}
		}
		System.out.println("���м�����������Ϊ��"+list.size());
	}
	**/
