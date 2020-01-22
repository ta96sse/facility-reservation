<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="header">
	<h1>銀河ソフトウェア&nbsp;&nbsp;施設予約システム</h1>
	<form action="/logout" method="post">
	<ul id="header-bolock">

		<li><spring:eval var="accountSessionForm" expression="@accountSessionForm"/>
			<c:out value="${accountSessionForm.accountName}" escapeXml="false">
				<font color="red">値が存在しません。</font>
			</c:out>さん&nbsp;こんにちは</li>
		<li><input type="submit" value="ログアウト"/></li>
		
	</ul>
	</form>
</div>
