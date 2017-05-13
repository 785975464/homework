package wordfrequency;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
	private static int Articlesize,K=3;
	private static ArrayList<String> ArticleList;
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException{
		long startTime=System.currentTimeMillis();									//��ȡ��ʼʱ��
		String filename="C:/My Documents/NYSK_data10420.txt";							//��������Դ·��
		ArticleList=ArticleTable.getArticles(filename);								//��������б�
		Articlesize=ArticleList.size();												//��������б��С
		ArrayList<String> StopTable = StopwordsTable.getStopWords();				//��ʼ��ͣ�ôʱ�
		List<Map.Entry<String, Double>>[] WordTable = new ArrayList[Articlesize];	//��ʼ�����ʱ��ά����
		System.out.println("ArticlesizeΪ��"+Articlesize);
		for( int i=0 ; i<Articlesize ; i++){
			WordTable[i] = new ArrayList<Map.Entry<String, Double>>();		//��ʼ�����ʱ��ά���飬��ÿƪ���·���һ��WordTable[i]��
			WordTable[i] = WordsTable.getWords(ArticleList.get(i));			//��ÿƪ���½��зִ�ͳ��
			WordTable[i] = CheckText.textChecker(WordTable[i], StopTable);	//��ÿƪ���½���ͣ�ôʼ��
		}
		//Kmeans.K_meansFun(K, Articlesize, WordTable);						//ʵ��K-means�㷨
		HierarchicalCluster.HierarchicalFun(K, WordTable);					//ʵ�ֲ�ξ����㷨
		long endTime=System.currentTimeMillis();							//��ȡ����ʱ��
		System.out.println("��������ʱ�䣺 "+(endTime-startTime)+"ms");
	}
	
	public static void show(ArrayList<String> list){
		for(int i=0;i<list.size();i++){
			//System.out.println("["+i+"]"+list.get(i));
			System.out.println(list.get(i));
		}
		System.out.println("size��СΪ��"+list.size());
	}
	public static void show(Set<Integer> list){	
		/**
		Iterator<Integer> it = list.iterator();
		while(it.hasNext()){
			//System.out.println("["+i+"]"+list.get(i));
			System.out.println(it.next());
		}
		**/
		System.out.println("size��СΪ��"+list.size());
	}
	public static void showint(ArrayList<Integer> list){
		/**
		for(int i=0;i<list.size();i++){
			//System.out.println("["+i+"]"+list.get(i));
			System.out.println(list.get(i));
		}
		**/
		System.out.println("size��СΪ��"+list.size());
	}

	public static void showdoub(ArrayList<Double> list){
		for(int i=0;i<list.size();i++){
			//System.out.println("["+i+"]"+list.get(i));
			System.out.println(list.get(i));
		}
		System.out.println("size��СΪ��"+list.size());
	}
	public static void show(List<Map.Entry<String, Double>> list){
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i).getKey()+'\t'+list.get(i).getValue());
		}
		System.out.println("size��СΪ��"+list.size());
	}
	@SuppressWarnings("unchecked")
	public static void show(Map<String, Object> list){
		ArrayList<String> listA_Key = (ArrayList<String>)list.get("meansKey");
		ArrayList<Double> listA_Value = (ArrayList<Double>)list.get("meansValue");
		for(int i=0;i<listA_Key.size();i++){
			System.out.println(listA_Key.get(i)+"="+listA_Value.get(i));
			//System.out.println(list.get(i));
		}
		System.out.println("size��СΪ��"+listA_Key.size());
	}
}
