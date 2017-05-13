package wordfrequency;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Kmeans {
	public static void K_meansFun(int k,int Articlesize, List<Map.Entry<String, Double>>[] WordTable ){
		ArrayList<String> meansKey = new ArrayList<String>();
		ArrayList<Double> meansValue = new ArrayList<Double>();
		Map<String, Object> meansA = new HashMap<String, Object>();	//meansA中存储集合A中的元素及均值
		Map<String, Object> meansB = new HashMap<String, Object>();
		Map<String, Object> meansC = new HashMap<String, Object>();
		meansA.put("meansKey", meansKey);
		meansA.put("meansValue", meansValue);
		meansB.put("meansKey", meansKey);
		meansB.put("meansValue", meansValue);
		meansC.put("meansKey", meansKey);
		meansC.put("meansValue", meansValue);
		int[] kset = new int[k];									//选择K个随机数
		for(int i=0;i<k;i++){
			Random rand = new Random();
			kset[i] = rand.nextInt(Articlesize);
			for(int j=0;j<i;j++){
				if(kset[j]==kset[i]){								//检查是否有重复的随机数
					i--;											//若有重复，则重新产生
					break;
				}
			}
		}
		
		for(int i=0;i<k;i++){
			//kset[i]=RandNum[i];
			System.out.println("第"+i+"个随机数为："+kset[i]);
		}
	
		ArrayList<Integer> Aset = new ArrayList<Integer>();
		ArrayList<Integer> Bset = new ArrayList<Integer>();
		ArrayList<Integer> Cset = new ArrayList<Integer>();
		Aset.add(kset[0]);
		Bset.add(kset[1]);
		Cset.add(kset[2]);

		meansA = meansInit(WordTable[kset[0]]);						//设定集合的初始中心，并初始化均值
		//System.out.println("初始化集合A的元素有：");
		//Main.show(meansA);
		meansB = meansInit(WordTable[kset[1]]);
		//System.out.println("初始化集合B的元素有：");
		//Main.show(meansB);
		meansC = meansInit(WordTable[kset[2]]);
		//System.out.println("初始化集合C的元素有：");
		//Main.show(meansC);
		
		double criteA = 0,criteB = 0,criteC = 0;					//初始化均值集合的评价函数值
		for(int i=0;i<Articlesize;i++){								//第一次遍历，得到初步划分
			criteA = criteFun(WordTable[i] , meansA );				//调用评价函数，更新评价函数值
			criteB = criteFun(WordTable[i] , meansB );
			criteC = criteFun(WordTable[i] , meansC );
			if( Aset.contains( i ) || Bset.contains( i ) || Cset.contains( i ) ){
				//System.out.println("集合中已包含元素"+i);					//若集合中含有初始元素，则不进行处理
			}
			else if( criteB >= criteA && criteC >= criteA ){
				Aset.add(i);										//将元素加入到评价函数值较小的集合中	
			}
			else if( criteA >= criteB && criteC >= criteB ){
				Bset.add(i);
			}
			else{
				Cset.add(i);
			}
		}
		meansA = getmeans(Aset, WordTable);							//初始化完成后，更新集合均值
		meansB = getmeans(Bset, WordTable);
		meansC = getmeans(Cset, WordTable);
	
		//System.out.println("初始集合A中的元素有：");
		//Main.showint(Aset);
		//System.out.println("初始集合B中的元素有：");
		//Main.showint(Bset);
		//System.out.println("初始集合C中的元素有：");
		//Main.showint(Cset);
		//**************************完成集合的初始划分************************************	
		boolean state=true;											//设置循环状态为true，当集合不再变化时，置为false，结束循环
		while( state ){
			for(int i=0;i<Articlesize;i++){							//重复计算评价函数值，迭代划分
				criteA = criteFun(WordTable[i] , meansA );
				criteB = criteFun(WordTable[i] , meansB );
				criteC = criteFun(WordTable[i] , meansC );
				if( criteB >= criteA && criteC >= criteA && !Aset.contains(i) ){		//元素距离集合A的距离最近，且该元素不在集合A中
					int index;
					if(Bset.contains(i)){							//如果在集合B中，则删除该元素；否则在集合C中删除
						index = Bset.indexOf(i);
						Bset.remove(index);
					}
					else{
						index = Cset.indexOf(i);
						Cset.remove(index);
					}
					Aset.add(i);									//将该元素加入到集合A中
					state=true;
				}
				else if( criteA >= criteB && criteC >= criteB && !Bset.contains(i) ){	//元素距离集合B的距离最近，且该元素不在集合B中
					int index;
					if(Aset.contains(i)){							//如果在集合A中，则删除该元素；否则在集合C中删除
						index = Aset.indexOf(i);
						Aset.remove(index);
					}
					else{
						index = Cset.indexOf(i);
						Cset.remove(index);
					}
					Bset.add(i);									//将该元素加入到集合B中
					state=true;
				}
				else if( criteA >= criteC && criteB >= criteC && !Cset.contains(i) ){	//元素距离集合C的距离最近，且该元素不在集合C中
					int index;
					if(Aset.contains(i)){							//如果在集合A中，则删除该元素；否则在集合C中删除
						index = Aset.indexOf(i);
						Aset.remove(index);
					}
					else{
						index = Bset.indexOf(i);
						Bset.remove(index);
					}
					Cset.add(i);									//将该元素加入到集合C中
					state=true;
				}
				else{
					state=false;									//当所有集合中的所有元素均不再发生变化，将state置为false，循环结束
				}
			}
		}
		System.out.println("集合A中的元素有：");
		Main.showint(Aset);
		System.out.println("集合B中的元素有：");
		Main.showint(Bset);
		System.out.println("集合C中的元素有：");
		Main.showint(Cset);	
	}
	
	public static Map<String,Object> meansInit( List<Map.Entry<String, Double>> TheWordTable ){		//初始化均值表
		ArrayList<String> meansKey = new ArrayList<String>();
		ArrayList<Double> meansValue = new ArrayList<Double>();
		for(int j=0;j<TheWordTable.size();j++){							//将给定文章中的单词存入均值集合中
			String tempKey= TheWordTable.get(j).getKey();
			double tempValue= TheWordTable.get(j).getValue();
			meansKey.add( tempKey );
			meansValue.add( tempValue );
		}
		Map<String, Object> means = new HashMap<String, Object>();
		means.put("meansKey", meansKey);
		means.put("meansValue", meansValue);
		//System.out.println("初始化结果！");
		//Main.showdoub(meansValue);
		return means;													//返回均值集合
	}
	
	
	public static Map<String, Object> getmeans( ArrayList<Integer> DataSet ,List<Map.Entry<String, Double>>[] WordTable ){		//计算组内均值
		int size=DataSet.size();
		if(size==0){
			return null;
		}
		ArrayList<String> meansKey = new ArrayList<String>();
		ArrayList<Double> meansValue = new ArrayList<Double>();
		for(int i=0;i<size;i++){											//集合中的文章数
			for(int j=0;j<WordTable[DataSet.get(i)].size();j++){			//统计每篇文章中的单词数
				String tempKey= WordTable[DataSet.get(i)].get(j).getKey();
				double tempValue= WordTable[DataSet.get(i)].get(j).getValue();
				if( !meansKey.contains( tempKey ) ){						//未找到元素，则加入meansKey		
					meansKey.add( tempKey );
					meansValue.add( tempValue );
				}
				else{
					int index=meansKey.indexOf( tempKey );
					meansValue.set(index, (meansValue.get(index)+tempValue));		//若找到元素，则将meansValue相加
				}
			}
		}
		for(int x=0;x<meansKey.size();x++){
			meansValue.set(x, meansValue.get(x)/size);
		}
		Map<String, Object> means = new HashMap<String, Object>();
		means.put("meansKey", meansKey);
		means.put("meansValue", meansValue);
		return means;
	}
	
	
	@SuppressWarnings("unchecked")
	public static double criteFun(List<Map.Entry<String, Double>> word , Map<String, Object> means ){		//计算准则函数值，将某篇文章与给定的均值集合进行求解，返回到均值的距离平方
		ArrayList<String> meansKey = new ArrayList<String>();
		ArrayList<Double> meansValue = new ArrayList<Double>();
		meansKey.addAll((Collection<? extends String>) means.get("meansKey"));		//将均值集合暂存到ArrayList中
		meansValue.addAll((Collection<? extends Double>) means.get("meansValue"));
		ArrayList<String> wordKey = new ArrayList<String>();
		ArrayList<Double> wordValue = new ArrayList<Double>();
		double crite = 0;
		for(int i=0;i<word.size();i++){												//将文章单词列表与给定集合进行对比
			wordKey.add(word.get(i).getKey());
			wordValue.add(word.get(i).getValue());
			if(meansKey.contains(wordKey.get(i))){									//若集合中包含此单词
				int index = meansKey.indexOf(wordKey.get(i));						//则获取下标
				crite+=Math.pow( meansValue.get(index)-wordValue.get(i) , 2 );		//并计算距离的平方
				meansKey.remove(index);												//同时在均值集合中删除该元素
				meansValue.remove(index);			
			}
			else{
				crite+=Math.pow(wordValue.get(i),2);								//若集合中不包含此单词，则重新计算距离的平方值
			}
		}
		for(int i=0;i<meansValue.size();i++){										//将均值集合中的剩余元素计算距离的平方值
			crite+=Math.pow(meansValue.get(i),2);
		}
		return crite;																//返回评价函数值
	}

}
