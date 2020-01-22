<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>ユーザ一覧</title>
<meta charset="UTF-8">

<link rel="stylesheet" type="text/css" href="/css/style.css" />
</head>
<body>
	<div id="base">
		<jsp:include page="/WEB-INF/views/header/header.jsp" flush="true" />
		<div id="contents">
			<div id="view-title">ユーザー情報管理</div>


			<!--
            <ul class="msgs">
                <li>エラーメッセージ表示エリア</li>
            </ul>
             -->
			<p id="contents-title">ユーザ一覧</p>
			<input type="button" value="新規登録"
				onClick="location.href='http://localhost:8080/user/add'">
			<table id="user-info">
				<tr>
					<th class="user-info-th user-info-index">NO</th>
					<th class="user-info-th">ユーザー名</th>
					<th class="user-info-th">パスワード</th>
					<th class="user-info-th">権限</th>
					<th class="user-info-th">備考</th>
				</tr>
				<c:forEach var="user" items="${userListForm.userList}"
					varStatus="loop">
					<tr>
						<td>${loop.count}</td>
						<td><a href="/user/detail/${user.loginName}">${user.loginName}</a></td>
						<td><c:out value="${user.password1}" /></td>

						<td>
							<c:choose>
								<c:when test="${user.parmissionLevel == 1}">管理者</c:when>
								<c:when test="${user.parmissionLevel == 2}">ユーザー</c:when>
							</c:choose>
						</td>
						<td><c:out value="${user.note}" /></td>
					</tr>
				</c:forEach>
			</table>
			
			<div id="return-display">
			<a style="font-size:18px;" href="/menu">メニューに戻る</a>
			</div>
		</div>
		<div id="footer">
			<p class="copyright">(C) 2003 Ginga Software, All Rights
				Reserved..</p>
		</div>
	</div>
</body>
</html>