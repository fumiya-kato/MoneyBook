package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.LoadListLogic;
import model.PostLogic;
import model.moneyData;

@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		// リクエストパラメータの取得
		String yearString = request.getParameter("year");
		String monthString = request.getParameter("month");
		
		List<moneyData> dataList = new ArrayList<moneyData>();
		LoadListLogic llLogic = new LoadListLogic();
		
		if(yearString != null && monthString != null) {
			int year = Integer.parseInt(yearString);
			int month = Integer.parseInt(monthString);
			// リストを取得
			dataList = llLogic.execute(year, month);
		}
		else {
			// 実行日を種痘
			Date date = new Date();
			SimpleDateFormat sdfMonth = new SimpleDateFormat("MM");
			SimpleDateFormat sdfYear = new SimpleDateFormat("yy");
			String thisMonth = sdfMonth.format(date);
			String thisYear = sdfYear.format(date);
			int year = Integer.parseInt(thisYear);
			int month = Integer.parseInt(thisMonth);
			
			dataList = llLogic.execute(year, month);
		}
		// スコープに保存
		request.setAttribute("moneyDataList", dataList);
		
		// フォワード
		RequestDispatcher d = request.getRequestDispatcher("/index.jsp");
		d.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String yearString = request.getParameter("year");
		String monthString = request.getParameter("month");
		String dayString = request.getParameter("day");
		String content = request.getParameter("content");
		String incomeString = request.getParameter("income");
		String outgoString = request.getParameter("outgo");
		
		PostLogic pLogic = new PostLogic();
		String errorMsg = "";
		
		if(incomeString ==null && outgoString == null) {
			errorMsg += "「収入」と「支出」のどちらかに入力してください。";
		}
		else {
			int year = 0;
			int month = 0;
			int day = 0;
			int income = 0;
			int outgo = 0;
			
			// 文字列が整数か確認し、数字に変換
			if(pLogic.isNum(yearString)) {
				year = Integer.parseInt(yearString);
			}
			else {
				errorMsg += "年は整数で入力してください。";
			}
			if(pLogic.isNum(monthString)) {
				month = Integer.parseInt(monthString);
			}
			else {
				errorMsg += "月は整数で入力してください。";
			}
			if(pLogic.isNum(dayString)) {
				day = Integer.parseInt(dayString);
			}
			else {
				errorMsg += "日は整数で入力してください。";
			}
			
			if(incomeString.equals("")) {
				income = 0;
			}
			else {
				if(pLogic.isNum(incomeString)) {
					income = Integer.parseInt(incomeString);
				}
				else {
					errorMsg += "収入は整数で入力してください。";
				}
			}
			if(outgoString.equals("")) {
				outgo = 0;
			}
			else {
				if(pLogic.isNum(outgoString)) {
					outgo = Integer.parseInt(outgoString);
				}
				else {
					errorMsg += "支出は整数で入力してください。";
				}
			}
			
			if(errorMsg.length() != 0) {
				moneyData md = new moneyData(year, month, day, content, income, outgo);
				boolean result = pLogic.execute(md);
				if(!(result)) {
					errorMsg += "保存に失敗しました。";
				}
			}
		}
		
		request.setAttribute("errorMsg", errorMsg);
		
		// フォワード
		RequestDispatcher d = request.getRequestDispatcher("/index.jsp");
		d.forward(request, response);
	}

}
