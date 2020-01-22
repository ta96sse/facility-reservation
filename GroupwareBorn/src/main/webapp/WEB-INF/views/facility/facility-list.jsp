<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>施設一覧</title>
<meta charset="UTF-8">

<link rel="stylesheet" type="text/css" href="/css/style.css" />
</head>
<body>
	<div id="base">
		<jsp:include page="/WEB-INF/views/header/header.jsp" flush="true" />
		<div id="contents">
			<div id="view-title">施設情報管理</div>


			<!--
            <ul class="msgs">
                <li>エラーメッセージ表示エリア</li>
            </ul>
             -->
			<p id="contents-title">施設一覧</p>
			<div id="return-display">
				<a style="font-size: 18px;" href="/menu">メニューに戻る</a>
			</div>
			<input type="button" value="新規登録"
				onClick="location.href='http://localhost:8080/facility/add'">
			<table id="facility-info">
				<tr>
					<th class="facility-info-th facility-info-index">NO</th>
					<th class="facility-info-th">施設名</th>
					<th class="facility-info-th">種別</th>
					<th class="facility-info-th">定員</th>
				</tr>
				<c:forEach var="facility" items="${facilityListForm}"
					varStatus="loop">
					<tr>
						<td><c:out value="${facility.id}" /></td>
						<td><a href="/facility/detail/${facility.id}">${facility.name}</a></td>
						<td><c:out value="${facility.facilityTypeForm.name}" /></td>
						<td><c:out value="${facility.capacity}" /></td>
					</tr>
				</c:forEach>
			</table>

			<div id="return-display">
				<a style="font-size: 18px;" href="/menu">メニューに戻る</a>
			</div>
		</div>
		<div id="footer">
			<p class="copyright">(C) 2003 Ginga Software, All Rights
				Reserved..</p>
		</div>
	</div>
</body>
</html>