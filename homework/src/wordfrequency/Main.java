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
		long startTime=System.currentTimeMillis();									//获取开始时间
		String filename="C:/My Documents/NYSK_data10420.txt";							//设置数据源路径
		ArticleList=ArticleTable.getArticles(filename);								//获得文章列表
		Articlesize=ArticleList.size();												//获得文章列表大小
		ArrayList<String> StopTable = StopwordsTable.getStopWords();				//初始化停用词表
		List<Map.Entry<String, Double>>[] WordTable = new ArrayList[Articlesize];	//初始化单词表二维数组
		System.out.println("Articlesize为："+Articlesize);
		for( int i=0 ; i<Articlesize ; i++){
			WordTable[i] = new ArrayList<Map.Entry<String, Double>>();		//初始化单词表二维数组，将每篇文章放在一个WordTable[i]中
			WordTable[i] = WordsTable.getWords(ArticleList.get(i));			//对每篇文章进行分词统计
			WordTable[i] = CheckText.textChecker(WordTable[i], StopTable);	//对每篇文章进行停用词检查
		}
		//Kmeans.K_meansFun(K, Articlesize, WordTable);						//实现K-means算法
		HierarchicalCluster.HierarchicalFun(K, WordTable);					//实现层次聚类算法
		long endTime=System.currentTimeMillis();							//获取结束时间
		System.out.println("程序运行时间： "+(endTime-startTime)+"ms");
	}
	
	public static void show(ArrayList<String> list){
		for(int i=0;i<list.size();i++){
			//System.out.println("["+i+"]"+list.get(i));
			System.out.println(list.get(i));
		}
		System.out.println("size大小为："+list.size());
	}
	public static void show(Set<Integer> list){	
		/**
		Iterator<Integer> it = list.iterator();
		while(it.hasNext()){
			//System.out.println("["+i+"]"+list.get(i));
			System.out.println(it.next());
		}
		**/
		System.out.println("size大小为："+list.size());
	}
	public static void showint(ArrayList<Integer> list){
		/**
		for(int i=0;i<list.size();i++){
			//System.out.println("["+i+"]"+list.get(i));
			System.out.println(list.get(i));
		}
		**/
		System.out.println("size大小为："+list.size());
	}

	public static void showdoub(ArrayList<Double> list){
		for(int i=0;i<list.size();i++){
			//System.out.println("["+i+"]"+list.get(i));
			System.out.println(list.get(i));
		}
		System.out.println("size大小为："+list.size());
	}
	public static void show(List<Map.Entry<String, Double>> list){
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i).getKey()+'\t'+list.get(i).getValue());
		}
		System.out.println("size大小为："+list.size());
	}
	@SuppressWarnings("unchecked")
	public static void show(Map<String, Object> list){
		ArrayList<String> listA_Key = (ArrayList<String>)list.get("meansKey");
		ArrayList<Double> listA_Value = (ArrayList<Double>)list.get("meansValue");
		for(int i=0;i<listA_Key.size();i++){
			System.out.println(listA_Key.get(i)+"="+listA_Value.get(i));
			//System.out.println(list.get(i));
		}
		System.out.println("size大小为："+listA_Key.size());
	}
}
