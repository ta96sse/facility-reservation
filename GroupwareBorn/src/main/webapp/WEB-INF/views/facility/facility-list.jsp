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

			<!-- ページネーション -->
			<div id="pagination">
				<c:url value="./list" var="prev">
					<c:param name="page" value="${page-1}" />
				</c:url>
				<c:if test="${page > 1}">
					<a href="<c:out value="${prev}" />">Prev</a>
				</c:if>
				<c:forEach begin="1" end="${maxPages}" step="1" varStatus="i">
					<c:choose>
						<c:when test="${page == i.index}">
							<span>${i.index}</span>
						</c:when>
						<c:otherwise>
							<c:url value="./list" var="url">
								<c:param name="page" value="${i.index}" />
							</c:url>
							<a href='<c:out value="${url}" />'>${i.index}</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:url value="./list" var="next">
					<c:param name="page" value="${page + 1}" />
				</c:url>
				<c:if test="${page + 1 <= maxPages}">
					<a href='<c:out value="${next}" />'>Next</a>
				</c:if>
			</div>
			<!-- ページネーション -->

			<div id="return-display">
				<a style="font-size: 18px;" href="/menu">メニューに戻る</a>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/footer/footer.jsp" flush="true" />

	</div>
</body>
</html>