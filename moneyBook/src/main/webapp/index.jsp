<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.moneyData, java.util.List" %>
<%
// スコープに保存されたリストを取得
List<moneyData> mdl = (List<moneyData>) request.getAttribute("moneyDataList");
// スコープに保存されたエラーメッセージを取得
String errorMsg = (String) request.getAttribute("errorMsg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>小遣い管理</title>
</head>
<body>
<!-- ヘッダー -->
<div id=header><h1>小遣い管理</h1></div>

<!-- 投稿フォーム -->

<div id=form>
<p>
<form action="/moneyBook/Main" method="post">
20<input type="text" name="year" size="2" required>年
<input type="text" name="month" size="2" required>月
<input type="text" name="day" size="2" required>日<br>
ことがら：<input type="text" name="content" plaseholder="ことがら" required>
収入：<input type="text" name="income" plaseholder="収入" size="8">円
支出：<input tyoe="text" name="outgo" plaseholder="支出" size="8" >円<br>
<input type="submit" values="登録">
</form>
</P>
</div>

<!-- エラーメッセージ -->
<div id=errorMsg>
<% if(errorMsg != null){ %>
<p><%= errorMsg %></p>
<% } %>
</div>

<!-- ソート -->
<div id=sort>
<p>
<form action="/moneyBook/Main" method="get">
20<select name="year">
<% for(int i = 20;i<50;i++){ %>
<option value="<%= i %>"><%= i %></option>
<% } %>
</select>年
<select name="month">
<option value="0" selected>全</option>
<% for(int j = 1; j<=12; j++){ %>
<option value="<%= j %>"><%= j %></option>
<% } %>
</select>月
<input type="submit" value="表示">
</form>
</P>
</div>

<!-- 過去情報取得 -->
<% if(mdl != null){ %>
<% if(mdl.size() != 0){ %>
<table>
<tr>
<th>日付</th><th>ことがら</th><th>収入</th><th>支出</th>
</tr>
<% for(moneyData m : mdl){ %>
<tr>
<td><%= m.getMonth() %>月<%= m.getDay() %>日</td>
<td><%= m.getContent() %></td>
<td><%= m.getIncome() %></td>
<td><%= m.getOutgo() %></td>
</tr>
<% } %>
</table>
<% } %>
<% } %>
</body>
</html>