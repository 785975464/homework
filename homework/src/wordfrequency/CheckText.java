package wordfrequency;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CheckText {
	public static List<Map.Entry<String, Double>> textChecker( List<Map.Entry<String, Double>> WTable , ArrayList<String> STable ){
		for(int i=0;i<WTable.size();i++){
			if( STable.contains( WTable.get(i).getKey().toLowerCase() ) ){		//�����ʸĳ�Сд������ͣ�ôʱ���ռ��
				WTable.remove(i--);												//������ͣ�ôʣ���ɾ��
			}
		}
		return WTable;	
	}

}
