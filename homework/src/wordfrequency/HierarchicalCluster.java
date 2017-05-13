package wordfrequency;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class HierarchicalCluster {
	//private static double dist,threshold1=0.0115,threshold2=0.0135;
	private static double dist,threshold1=0.003,threshold2=0.005;
	private static int center;
	public static void HierarchicalFun(int K, List<Map.Entry<String, Double>>[] WordTable){		//层次聚类法
		Set<Integer> hs1=new HashSet<Integer>();
		Set<Integer> hs2=new HashSet<Integer>();
		Set<Integer> hs3=new HashSet<Integer>();						//用Set集合存储文章序号，避免重复
		
		Random rand = new Random();
		center = rand.nextInt(WordTable.length);
		hs1.add(center);														//随机设定初始聚类元素center，设定聚类圆心
		System.out.println("WordTable的length为："+WordTable.length+"\t\tcenter为："+center);
		for(int i=0;i<WordTable.length;i++){
			dist=getDistance(WordTable[center],WordTable[i]);
			if(dist<=threshold1){										//若距离小于给定半径1（阈值1），则将该元素加入第一个集合中
				hs1.add(i);
			}
			else if(dist<=threshold2){									//若距离小于给定半径2（阈值2），则将该元素加入第二个集合中
				hs2.add(i);
			}
			else{
				hs3.add(i);												//否则，则将该元素加入第三个集合中
			}
		}
		Main.show(hs1);
		Main.show(hs2);
		Main.show(hs3);
	}

	public static double getDistance(List<Map.Entry<String, Double>> WordA,List<Map.Entry<String, Double>> WordB){		//计算两篇文章的距离并返回
		double distance=0;												//存放两篇文章的距离
		int sizeA=WordA.size();
		int sizeB=WordB.size();
		ArrayList<String> WordAKey = new ArrayList<String>();
		ArrayList<Double> WordAValue = new ArrayList<Double>();
		
		for(int i=0;i<sizeA;i++){
			WordAKey.add(WordA.get(i).getKey());						//存储WordA中Key值和Value值
			WordAValue.add(WordA.get(i).getValue());
		}
		
		ArrayList<String> WordBKey = new ArrayList<String>();
		ArrayList<Double> WordBValue = new ArrayList<Double>();
		for(int i=0;i<sizeB;i++){
			WordBKey.add(WordB.get(i).getKey());
			WordBValue.add(WordB.get(i).getValue());
		}
		//Main.show(WordB);
		//System.out.println("sizeA是："+sizeA);
		for(int i=0;i<sizeA;i++){										//计算文章A与文章B的单词距离
			//WordAKey.add(WordA.get(i).getKey());
			//WordAValue.add(WordA.get(i).getValue());
			//System.out.println(list1Key.get(i));
			
			if(WordBKey.contains(WordAKey.get(i))){
				int index = WordBKey.indexOf(WordAKey.get(i));			//若文章B中包含文章A的单词，则获取下标，计算距离
				distance+=Math.pow( WordBValue.get(index)-WordAValue.get(i) , 2 );
				
				//System.out.println("重复的单词："+WordAKey.get(i)+"\tWordBValue.get(index)值为："+WordBValue.get(index)+"\tWordAValue.get(i)值为："+WordAValue.get(i)+"\t差平方为："+Math.pow( WordBValue.get(index)-WordAValue.get(i) , 2 ));
				//WordAKey.remove(i);
				//System.out.println("i是："+i);
				//WordAValue.remove(i--);		//A中元素予以保留
				WordBKey.remove(index);									//并将文章B中的单词删除
				WordBValue.remove(index);
			}
			else{
				distance+=Math.pow(WordAValue.get(i),2);				//若不包含A中单词，则重新计算距离
				//System.out.println("未重复的单词："+WordAKey.get(i)+"\t差平方为："+Math.pow(WordAValue.get(i),2));
			}
		}
		for(int i=0;i<WordBKey.size();i++){								//将B中剩余单词进行距离计算
			distance+=Math.pow(WordBValue.get(i),2);
		}
		//Main.show(WordA);
		//Main.show(WordB);
		return distance;												//返回两篇文章的距离
	}

}
