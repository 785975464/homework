package wordfrequency;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CheckText {
	public static List<Map.Entry<String, Double>> textChecker( List<Map.Entry<String, Double>> WTable , ArrayList<String> STable ){
		for(int i=0;i<WTable.size();i++){
			if( STable.contains( WTable.get(i).getKey().toLowerCase() ) ){		//将单词改成小写，并与停用词表对照检查
				WTable.remove(i--);												//若包含停用词，则删除
			}
		}
		return WTable;	
	}

}
