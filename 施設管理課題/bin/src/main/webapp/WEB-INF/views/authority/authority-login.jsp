<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>ログイン</title>
<link rel="stylesheet" type="text/css" href="/css/style.css" />
</head>
<body>
    <div id="base">
        <div id="header">
            <h1>
                銀河ソフトウェア&nbsp;&nbsp;施設予約システム
            </h1>
        </div>
        <div id="contents">
	        <div id="view-title">ログイン</div>


			<form:form modelAttribute="authorityLoginForm" action="/login" method="post">
				<p id="login-title">ユーザ名とパスワードを入力してください</p>
				<a style="color:red"><form:errors path="errorMsg" /></a>
				
	            <table id="login">
	                <tbody>
	                    <tr>
	                        <th>ユーザ名</th>
	                        <td>
	                        <a style="color:red"><form:errors path="loginId"/></a>
	                        <form:input path="loginId"/></td>
	                    </tr>
	                    <tr>
	                        <th>パスワード</th>
	                        <td>
	                        <a style="color:red"><form:errors path="password"/></a>
	                        <form:input path="password"/></td>
	                    </tr>
	                </tbody>
	            </table>
	            <p id="login-button">
	                <input type="submit" value="ログイン" />
	                <input type="reset" value="クリア" />
	            </p>
            </form:form>
        </div>
		<jsp:include page="/WEB-INF/views/footer/footer.jsp" flush="true" />
    </div>
</body>
</html>