package model;

public class PostLogic {
	// 整数判定
	public boolean isNum(String num) {
		try {
			if(num != null) {
				Integer.parseInt(num);
				return true;
			}
		}
		catch(NumberFormatException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean execute(moneyData md) {
		try {
			moneyDataControl mdc = new moneyDataControl();
			mdc.saveData(md);
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
