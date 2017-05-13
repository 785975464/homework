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
	public static void HierarchicalFun(int K, List<Map.Entry<String, Double>>[] WordTable){		//��ξ��෨
		Set<Integer> hs1=new HashSet<Integer>();
		Set<Integer> hs2=new HashSet<Integer>();
		Set<Integer> hs3=new HashSet<Integer>();						//��Set���ϴ洢������ţ������ظ�
		
		Random rand = new Random();
		center = rand.nextInt(WordTable.length);
		hs1.add(center);														//����趨��ʼ����Ԫ��center���趨����Բ��
		System.out.println("WordTable��lengthΪ��"+WordTable.length+"\t\tcenterΪ��"+center);
		for(int i=0;i<WordTable.length;i++){
			dist=getDistance(WordTable[center],WordTable[i]);
			if(dist<=threshold1){										//������С�ڸ����뾶1����ֵ1�����򽫸�Ԫ�ؼ����һ��������
				hs1.add(i);
			}
			else if(dist<=threshold2){									//������С�ڸ����뾶2����ֵ2�����򽫸�Ԫ�ؼ���ڶ���������
				hs2.add(i);
			}
			else{
				hs3.add(i);												//�����򽫸�Ԫ�ؼ��������������
			}
		}
		Main.show(hs1);
		Main.show(hs2);
		Main.show(hs3);
	}

	public static double getDistance(List<Map.Entry<String, Double>> WordA,List<Map.Entry<String, Double>> WordB){		//������ƪ���µľ��벢����
		double distance=0;												//�����ƪ���µľ���
		int sizeA=WordA.size();
		int sizeB=WordB.size();
		ArrayList<String> WordAKey = new ArrayList<String>();
		ArrayList<Double> WordAValue = new ArrayList<Double>();
		
		for(int i=0;i<sizeA;i++){
			WordAKey.add(WordA.get(i).getKey());						//�洢WordA��Keyֵ��Valueֵ
			WordAValue.add(WordA.get(i).getValue());
		}
		
		ArrayList<String> WordBKey = new ArrayList<String>();
		ArrayList<Double> WordBValue = new ArrayList<Double>();
		for(int i=0;i<sizeB;i++){
			WordBKey.add(WordB.get(i).getKey());
			WordBValue.add(WordB.get(i).getValue());
		}
		//Main.show(WordB);
		//System.out.println("sizeA�ǣ�"+sizeA);
		for(int i=0;i<sizeA;i++){										//��������A������B�ĵ��ʾ���
			//WordAKey.add(WordA.get(i).getKey());
			//WordAValue.add(WordA.get(i).getValue());
			//System.out.println(list1Key.get(i));
			
			if(WordBKey.contains(WordAKey.get(i))){
				int index = WordBKey.indexOf(WordAKey.get(i));			//������B�а�������A�ĵ��ʣ����ȡ�±꣬�������
				distance+=Math.pow( WordBValue.get(index)-WordAValue.get(i) , 2 );
				
				//System.out.println("�ظ��ĵ��ʣ�"+WordAKey.get(i)+"\tWordBValue.get(index)ֵΪ��"+WordBValue.get(index)+"\tWordAValue.get(i)ֵΪ��"+WordAValue.get(i)+"\t��ƽ��Ϊ��"+Math.pow( WordBValue.get(index)-WordAValue.get(i) , 2 ));
				//WordAKey.remove(i);
				//System.out.println("i�ǣ�"+i);
				//WordAValue.remove(i--);		//A��Ԫ�����Ա���
				WordBKey.remove(index);									//��������B�еĵ���ɾ��
				WordBValue.remove(index);
			}
			else{
				distance+=Math.pow(WordAValue.get(i),2);				//��������A�е��ʣ������¼������
				//System.out.println("δ�ظ��ĵ��ʣ�"+WordAKey.get(i)+"\t��ƽ��Ϊ��"+Math.pow(WordAValue.get(i),2));
			}
		}
		for(int i=0;i<WordBKey.size();i++){								//��B��ʣ�൥�ʽ��о������
			distance+=Math.pow(WordBValue.get(i),2);
		}
		//Main.show(WordA);
		//Main.show(WordB);
		return distance;												//������ƪ���µľ���
	}

}
