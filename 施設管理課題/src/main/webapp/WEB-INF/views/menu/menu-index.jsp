<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>メニュー</title>
<link rel="stylesheet" type="text/css" href="/css/style.css" />
<link rel="stylesheet" type="text/css" href="/css/a.css" />
</head>
<body>
	<div id="base">

		<jsp:include page="/WEB-INF/views/header/header.jsp" flush="true" />

		<div id="contents">
			<div id="view-title">メニュー</div>

			<!--
            <ul class="msgs">
                <li>エラーメッセージ表示エリア</li>
            </ul>
             -->
			<p id="contents-title">機能を選択してください。</p>
			<form:form action="/menu/select" method="post">
				<spring:eval var="accountSessionForm"
					expression="@accountSessionForm" />
				<ul id="menu">
					<li class="menu-list"><a class="function-button"
						href="/facility-reservation/list">施設予約</a>
						<c:if test="${accountSessionForm.permissionLevel == 1}">
							<li class="menu-list"><a class="function-button"
								href="/facility/list">施設情報管理</a></li>
							<li class="menu-list"><a class="function-button"
								href="/user/list">ユーザー情報管理</a></li>
							<li class="menu-list"><a class="function-button">データ初期化</a>
							</li>
						</c:if>
				</ul>
			</form:form>
		</div>
		<jsp:include page="/WEB-INF/views/footer/footer.jsp" flush="true" />

	</div>
</body>
</html>