package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoadListLogic {
	// リスト取得
	public List<moneyData> execute(int year, int month) throws IOException{
		List<moneyData> dataList = new ArrayList<moneyData>();
		moneyDataControl mdc = new moneyDataControl();

		if(month == 0) {
			for(int i=1; i<13; i++) {
				List<moneyData> list = new ArrayList<moneyData>();
				list = mdc.loadData(year, i);
				dataList.addAll(list);
			}
		}
		else {
			dataList = mdc.loadData(year, month);
		}
		
		return dataList;
	}
}
