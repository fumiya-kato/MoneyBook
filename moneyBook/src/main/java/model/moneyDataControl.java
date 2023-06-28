package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class moneyDataControl {
	// CSVファイルに書き込み
	public void saveData(moneyData md) throws IOException {
		String fileName = "moneyData" + md.getYear() + md.getMonth() + ".csv";
		File file = new File(fileName);
		
		String dataCSV = String.format("%d,%s,%d,%d", md.getDay(), md.getContent(), md.getIncome(), md.getOutgo());
		
		FileOutputStream fos;
		
		if(file.exists()) {
			// 追記モード
			fos = new FileOutputStream(file, true);
		}
		else {
			// 上書きモード
			file.createNewFile();
			fos = new FileOutputStream(file);
		}
		OutputStreamWriter osw = new OutputStreamWriter(fos);
		BufferedWriter bw = new BufferedWriter(osw);
		
		// ファイルに書き込む
		bw.write(dataCSV);
		bw.newLine();
		
		// ファイルを閉じる
		bw.close();
		
	}
	
	public List<moneyData> loadData(int year, int month) throws IOException{
		// リストの生成
		List<moneyData> moneyDataList = new ArrayList<moneyData>();
		String fileName = "moneyData" + year + month + ".csv";
		File file= new File(fileName);
		
		if(file.exists()) {
			// ファイル読み込みの準備
			FileInputStream fis = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			
			String line;
			while((line = br.readLine()) != null){
				// CSVデータの読み込み
				String[] values = line.split(",");
				int day = Integer.parseInt(values[0]);
				String content = values[1];
				int income = Integer.parseInt(values[2]);
				int outgo = Integer.parseInt(values[3]);
			
				// インスタンス生成
				moneyData md = new moneyData(year, month, day, content, income, outgo);
				// リストに追加
				moneyDataList.add(md);
			}
			// ファイルを閉じる
			br.close();
		}
		else {
			// ファイルが存在しない場合の処理
		}
		
		return moneyDataList;
	}
}
