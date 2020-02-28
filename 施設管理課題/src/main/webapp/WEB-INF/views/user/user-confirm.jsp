<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>ユーザー情報登録</title>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/css/style.css" />
</head>
<body>
	<div id="base">
		<jsp:include page="/WEB-INF/views/header/header.jsp" flush="true" />
		<div id="contents">
			<div id="view-title">ユーザー情報 確認画面</div>

			<p id="contents-title">ユーザー情報を確認してください。</p>
			<form:form modelAttribute="userFormSession" action="/user/complete"
				method="post">
				<table id="facility-info">
					<tr>
						<th class="facility-info-th">ユーザー名</th>
						<td><c:out value="${userFormSession.loginName}" /></td>
					</tr>
					<tr>
						<th class="facility-info-th">パスワード</th>
						<td><c:out value="${userFormSession.password1}" /></td>
					</tr>
					<tr>
						<th class="facility-info-th">権限</th>
						<td>
							<c:choose>
								<c:when test="${userFormSession.parmissionLevel == 1}">管理者</c:when>
								<c:when test="${userFormSession.parmissionLevel == 2}">ユーザー</c:when>
							</c:choose>
						</td>
					</tr>
					<tr>
						<th class="facility-info-th">備考</th>
						<td><c:out value="${userFormSession.note}" /></td>
					</tr>
				</table>
				<input type="hidden" id="loginName" name="loginName"
					value="${userFormSession.loginName}" />
				<input type="hidden" id="password1" name="password1"
					value="${userFormSession.password1}" />
				<input type="hidden" id="parmissionLevel" name="parmissionLevel"
					value="${userFormSession.parmissionLevel}" />
				<input type="hidden" id="note" name="note"
					value="${userFormSession.note}" />
					
				<input type="submit" name="${btnAction}" value="${btnName}" />
				<input type="button" class="back"value="戻る" onClick="location.href='/user/back/${btnAction}'"/>
			</form:form>
		</div>
		<div id="footer">
			<p class="copyright">(C) 2003 Ginga Software, All Rights
				Reserved..</p>
		</div>
	</div>
</body>
</html>