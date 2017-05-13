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
		Map<String, Object> meansA = new HashMap<String, Object>();	//meansA�д洢����A�е�Ԫ�ؼ���ֵ
		Map<String, Object> meansB = new HashMap<String, Object>();
		Map<String, Object> meansC = new HashMap<String, Object>();
		meansA.put("meansKey", meansKey);
		meansA.put("meansValue", meansValue);
		meansB.put("meansKey", meansKey);
		meansB.put("meansValue", meansValue);
		meansC.put("meansKey", meansKey);
		meansC.put("meansValue", meansValue);
		int[] kset = new int[k];									//ѡ��K�������
		for(int i=0;i<k;i++){
			Random rand = new Random();
			kset[i] = rand.nextInt(Articlesize);
			for(int j=0;j<i;j++){
				if(kset[j]==kset[i]){								//����Ƿ����ظ��������
					i--;											//�����ظ��������²���
					break;
				}
			}
		}
		
		for(int i=0;i<k;i++){
			//kset[i]=RandNum[i];
			System.out.println("��"+i+"�������Ϊ��"+kset[i]);
		}
	
		ArrayList<Integer> Aset = new ArrayList<Integer>();
		ArrayList<Integer> Bset = new ArrayList<Integer>();
		ArrayList<Integer> Cset = new ArrayList<Integer>();
		Aset.add(kset[0]);
		Bset.add(kset[1]);
		Cset.add(kset[2]);

		meansA = meansInit(WordTable[kset[0]]);						//�趨���ϵĳ�ʼ���ģ�����ʼ����ֵ
		//System.out.println("��ʼ������A��Ԫ���У�");
		//Main.show(meansA);
		meansB = meansInit(WordTable[kset[1]]);
		//System.out.println("��ʼ������B��Ԫ���У�");
		//Main.show(meansB);
		meansC = meansInit(WordTable[kset[2]]);
		//System.out.println("��ʼ������C��Ԫ���У�");
		//Main.show(meansC);
		
		double criteA = 0,criteB = 0,criteC = 0;					//��ʼ����ֵ���ϵ����ۺ���ֵ
		for(int i=0;i<Articlesize;i++){								//��һ�α������õ���������
			criteA = criteFun(WordTable[i] , meansA );				//�������ۺ������������ۺ���ֵ
			criteB = criteFun(WordTable[i] , meansB );
			criteC = criteFun(WordTable[i] , meansC );
			if( Aset.contains( i ) || Bset.contains( i ) || Cset.contains( i ) ){
				//System.out.println("�������Ѱ���Ԫ��"+i);					//�������к��г�ʼԪ�أ��򲻽��д���
			}
			else if( criteB >= criteA && criteC >= criteA ){
				Aset.add(i);										//��Ԫ�ؼ��뵽���ۺ���ֵ��С�ļ�����	
			}
			else if( criteA >= criteB && criteC >= criteB ){
				Bset.add(i);
			}
			else{
				Cset.add(i);
			}
		}
		meansA = getmeans(Aset, WordTable);							//��ʼ����ɺ󣬸��¼��Ͼ�ֵ
		meansB = getmeans(Bset, WordTable);
		meansC = getmeans(Cset, WordTable);
	
		//System.out.println("��ʼ����A�е�Ԫ���У�");
		//Main.showint(Aset);
		//System.out.println("��ʼ����B�е�Ԫ���У�");
		//Main.showint(Bset);
		//System.out.println("��ʼ����C�е�Ԫ���У�");
		//Main.showint(Cset);
		//**************************��ɼ��ϵĳ�ʼ����************************************	
		boolean state=true;											//����ѭ��״̬Ϊtrue�������ϲ��ٱ仯ʱ����Ϊfalse������ѭ��
		while( state ){
			for(int i=0;i<Articlesize;i++){							//�ظ��������ۺ���ֵ����������
				criteA = criteFun(WordTable[i] , meansA );
				criteB = criteFun(WordTable[i] , meansB );
				criteC = criteFun(WordTable[i] , meansC );
				if( criteB >= criteA && criteC >= criteA && !Aset.contains(i) ){		//Ԫ�ؾ��뼯��A�ľ���������Ҹ�Ԫ�ز��ڼ���A��
					int index;
					if(Bset.contains(i)){							//����ڼ���B�У���ɾ����Ԫ�أ������ڼ���C��ɾ��
						index = Bset.indexOf(i);
						Bset.remove(index);
					}
					else{
						index = Cset.indexOf(i);
						Cset.remove(index);
					}
					Aset.add(i);									//����Ԫ�ؼ��뵽����A��
					state=true;
				}
				else if( criteA >= criteB && criteC >= criteB && !Bset.contains(i) ){	//Ԫ�ؾ��뼯��B�ľ���������Ҹ�Ԫ�ز��ڼ���B��
					int index;
					if(Aset.contains(i)){							//����ڼ���A�У���ɾ����Ԫ�أ������ڼ���C��ɾ��
						index = Aset.indexOf(i);
						Aset.remove(index);
					}
					else{
						index = Cset.indexOf(i);
						Cset.remove(index);
					}
					Bset.add(i);									//����Ԫ�ؼ��뵽����B��
					state=true;
				}
				else if( criteA >= criteC && criteB >= criteC && !Cset.contains(i) ){	//Ԫ�ؾ��뼯��C�ľ���������Ҹ�Ԫ�ز��ڼ���C��
					int index;
					if(Aset.contains(i)){							//����ڼ���A�У���ɾ����Ԫ�أ������ڼ���C��ɾ��
						index = Aset.indexOf(i);
						Aset.remove(index);
					}
					else{
						index = Bset.indexOf(i);
						Bset.remove(index);
					}
					Cset.add(i);									//����Ԫ�ؼ��뵽����C��
					state=true;
				}
				else{
					state=false;									//�����м����е�����Ԫ�ؾ����ٷ����仯����state��Ϊfalse��ѭ������
				}
			}
		}
		System.out.println("����A�е�Ԫ���У�");
		Main.showint(Aset);
		System.out.println("����B�е�Ԫ���У�");
		Main.showint(Bset);
		System.out.println("����C�е�Ԫ���У�");
		Main.showint(Cset);	
	}
	
	public static Map<String,Object> meansInit( List<Map.Entry<String, Double>> TheWordTable ){		//��ʼ����ֵ��
		ArrayList<String> meansKey = new ArrayList<String>();
		ArrayList<Double> meansValue = new ArrayList<Double>();
		for(int j=0;j<TheWordTable.size();j++){							//�����������еĵ��ʴ����ֵ������
			String tempKey= TheWordTable.get(j).getKey();
			double tempValue= TheWordTable.get(j).getValue();
			meansKey.add( tempKey );
			meansValue.add( tempValue );
		}
		Map<String, Object> means = new HashMap<String, Object>();
		means.put("meansKey", meansKey);
		means.put("meansValue", meansValue);
		//System.out.println("��ʼ�������");
		//Main.showdoub(meansValue);
		return means;													//���ؾ�ֵ����
	}
	
	
	public static Map<String, Object> getmeans( ArrayList<Integer> DataSet ,List<Map.Entry<String, Double>>[] WordTable ){		//�������ھ�ֵ
		int size=DataSet.size();
		if(size==0){
			return null;
		}
		ArrayList<String> meansKey = new ArrayList<String>();
		ArrayList<Double> meansValue = new ArrayList<Double>();
		for(int i=0;i<size;i++){											//�����е�������
			for(int j=0;j<WordTable[DataSet.get(i)].size();j++){			//ͳ��ÿƪ�����еĵ�����
				String tempKey= WordTable[DataSet.get(i)].get(j).getKey();
				double tempValue= WordTable[DataSet.get(i)].get(j).getValue();
				if( !meansKey.contains( tempKey ) ){						//δ�ҵ�Ԫ�أ������meansKey		
					meansKey.add( tempKey );
					meansValue.add( tempValue );
				}
				else{
					int index=meansKey.indexOf( tempKey );
					meansValue.set(index, (meansValue.get(index)+tempValue));		//���ҵ�Ԫ�أ���meansValue���
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
	public static double criteFun(List<Map.Entry<String, Double>> word , Map<String, Object> means ){		//����׼����ֵ����ĳƪ����������ľ�ֵ���Ͻ�����⣬���ص���ֵ�ľ���ƽ��
		ArrayList<String> meansKey = new ArrayList<String>();
		ArrayList<Double> meansValue = new ArrayList<Double>();
		meansKey.addAll((Collection<? extends String>) means.get("meansKey"));		//����ֵ�����ݴ浽ArrayList��
		meansValue.addAll((Collection<? extends Double>) means.get("meansValue"));
		ArrayList<String> wordKey = new ArrayList<String>();
		ArrayList<Double> wordValue = new ArrayList<Double>();
		double crite = 0;
		for(int i=0;i<word.size();i++){												//�����µ����б���������Ͻ��жԱ�
			wordKey.add(word.get(i).getKey());
			wordValue.add(word.get(i).getValue());
			if(meansKey.contains(wordKey.get(i))){									//�������а����˵���
				int index = meansKey.indexOf(wordKey.get(i));						//���ȡ�±�
				crite+=Math.pow( meansValue.get(index)-wordValue.get(i) , 2 );		//����������ƽ��
				meansKey.remove(index);												//ͬʱ�ھ�ֵ������ɾ����Ԫ��
				meansValue.remove(index);			
			}
			else{
				crite+=Math.pow(wordValue.get(i),2);								//�������в������˵��ʣ������¼�������ƽ��ֵ
			}
		}
		for(int i=0;i<meansValue.size();i++){										//����ֵ�����е�ʣ��Ԫ�ؼ�������ƽ��ֵ
			crite+=Math.pow(meansValue.get(i),2);
		}
		return crite;																//�������ۺ���ֵ
	}

}
