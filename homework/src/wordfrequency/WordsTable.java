package wordfrequency;

import java.util.*;
import java.util.Map.Entry;
import java.io.*;

public class WordsTable {
	public static List<Map.Entry<String, Double>> getWords( String file ) throws IOException{
		int index=2;																	//设定单词出现的频数阈值
		Map<String, Double> hashMap = new HashMap<String, Double>();					//哈希表用于存储文章单词及其频率
		StringTokenizer st = new StringTokenizer(file, " ,.?!:;&“”‘’()/\"\'\t\r\n");	//利用StringTokenizer函数对文章进行分词
		while (st.hasMoreTokens()) {													//while有下一个元素，则继续读取
			String key = st.nextToken();
			if (hashMap.get(key) != null) {												//若哈希表中含有此元素，则value++
				int value = ((Double) hashMap.get(key)).intValue();
				value++;
				hashMap.put(key, new Double(value));

			} else {
				hashMap.put(key, new Double(1));										//否则，将该元素加入哈希表中，并将value值置为1
			}
		}
		Map<String, Double> treeMap = new TreeMap<String, Double>(hashMap);				//将哈希表转为哈希树，用于遍历
		List<Entry<String, Double>> list = new ArrayList<Map.Entry<String, Double>>(treeMap.entrySet());
		int count=list.size();															//将list的大小存在count中，用于计算单词出现的频率
		for(int i=0;i<list.size();i++){													//遍历list，将每个元素的频数转为频率
			if (list.get(i).getValue()>index){
				list.get(i).setValue( list.get(i).getValue()/count );
			}
			else if(list.get(i).getValue()<=index){
				list.remove(i--);							//list.remove(i--)，由于删除元素，list的大小会减小，并且被删除元素的后面元素序号会依次往前移一个
			}
		}
		return list;										//返回存有单词及其频率的列表
	}
}
