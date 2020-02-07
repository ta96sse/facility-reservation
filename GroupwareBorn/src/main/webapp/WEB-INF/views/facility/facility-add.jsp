<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>施設情報登録</title>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/css/style.css" />
<!-- jQueryの読み込み -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
<!--
	window.document.onkeypress = lineCheck;
	function lineCheck(e) {
		var ta = document.getElementById("facility-info-remarks");
		var row = ta.getAttribute("rows");
		var r = (ta.value.split("\n")).length;
		if (document.all) {
			if (r >= row && window.event.keyCode == 13) { //keyCode for IE
				return false; //入力キーを無視
			}
		} else {
			if (r >= row && e.which == 13) { //which for NN
				return false;
			}
		}
	}
//-->
</script>
</head>
<body>
	<div id="base">
		<jsp:include page="/WEB-INF/views/header/header.jsp" flush="true" />
		<div id="contents">
			<div id="view-title">施設情報管理</div>

			<form:form modelAttribute="facilitySessionForm"
				action="/facility/confirm" method="post">

				<form:errors path="systemMsg" />

				<p id="contents-title">施設情報を入力してください</p>
				<table id="facility-info">
					<tr>
						<th class="facility-info-th">施設名</th>
						<td><a style="color: red"><form:errors
									path="facilityForm.name" /></a> <form:input
								path="facilityForm.name" /></td>
					</tr>
					<tr>
						<th class="facility-info-th">種別</th>

						<td><c:forEach var="facilityType"
								items="${facilitySessionForm.facilityTypeList}"
								varStatus="status">

								<c:if test="${1 == status.count}">
									<form:radiobutton path="facilityForm.facilityTypeForm.id"
										value="${facilityType.id}" label="${facilityType.name}"
										checked="checked" />
								</c:if>
								<c:if test="${1 != status.count}">
									<form:radiobutton path="facilityForm.facilityTypeForm.id"
										value="${facilityType.id}" label="${facilityType.name}" />
								</c:if>

							</c:forEach></td>
					</tr>
					<tr>
						<th class="facility-info-th">定員</th>
						<td><a style="color: red"><form:errors path="facilityForm.capacity" /></a>
							<form:input path="facilityForm.capacity" /></td>
					</tr>
				</table>
				<input type="submit" class="addButton" name="add" value="確認" />
				<input type="button" value="戻る"
					onClick="location.href='/facility/list'" />
			</form:form>
		</div>
		<jsp:include page="/WEB-INF/views/footer/footer.jsp" flush="true" />
	</div>

</body>
</html>