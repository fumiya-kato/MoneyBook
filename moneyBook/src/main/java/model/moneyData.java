package model;

import java.io.Serializable;

public class moneyData implements Serializable {
	// フィールド
	private int year;
	private int month;
	private int day;
	private String content;
	private int income;
	private int outgo;
	
	// コンストラクタ
	public moneyData() { }
	public moneyData(int year, int month, int day, String content, int income, int outgo) {
		this.year = year;
		this.month = month;
		this.day = day;
		this.content = content;
		this.income = income;
		this.outgo = outgo;
	}
	
	// getter
	public int getYear() { return year; }
	public int getMonth() { return month; }
	public int getDay() { return day; }
	public String getContent() { return content; }
	public int getIncome() { return income; }
	public int getOutgo() { return outgo; }
	
}
