<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>施設情報更新・削除</title>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/css/style.css" />
<!-- jQueryの読み込み -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!-- jQueryUiの読み込み -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
<link rel="stylesheet"
	href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
</head>
<body>
	<div id="base">
		<jsp:include page="/WEB-INF/views/header/header.jsp" flush="true" />
		<div id="contents">
			<div id="view-title">施設情報管理</div>

			<div id="dialog">
				<table style="margin: auto;">
					<tr>
						<th>facility :</th>
						<td><font size="5">${facilitySessionForm.facilityForm.name}</font></td>
					</tr>
				</table>
			</div>

			<!--
            <ul class="msgs">
                <li>エラーメッセージ表示エリア</li>
            </ul>
             -->

			<p id="contents-title">施設情報の更新、削除を行えます</p>
			<form:form modelAttribute="facilitySessionForm"
				action="/facility/confirm" method="post">
				<table id="facility-info">
					<tr>
						<th class="facility-info-th">施設名</th>
						<td><a class="facilityCheck" style="color: red"></a> <a
							style="color: red"><form:errors path="facilityForm.name" /></a>
							<form:input class="facilityForm.name" path="facilityForm.name" /></td>
					</tr>
					<tr>
						<th class="facility-info-th">種別</th>

						<td><c:forEach var="facilityType"
								items="${facilitySessionForm.facilityTypeList}">

								<c:if
									test="${facilitySessionForm.facilityForm.facilityTypeForm.id == facilityType.id}">
									<form:radiobutton path="facilityForm.facilityTypeForm.id"
										value="${facilityType.id}" label="${facilityType.name}"
										checked="checked" />
								</c:if>
								<c:if
									test="${facilitySessionForm.facilityForm.facilityTypeForm.id != facilityType.id}">
									<form:radiobutton path="facilityForm.facilityTypeForm.id"
										value="${facilityType.id}" label="${facilityType.name}" />
								</c:if>
							</c:forEach></td>
					<tr>
						<th class="facility-info-th">定員</th>
						<td><a class="capacityCheck" style="color: red"></a> <a
							style="color: red"><form:errors path="facilityForm.capacity" /></a>
							<form:input class="facilityForm.capacity"
								path="facilityForm.capacity" /></td>
					</tr>
				</table>
				<input type="hidden" id="facilityForm.id" name="facilityForm.id"
					value="${facilitySessionForm.facilityForm.id}" />
				<input type="submit" class="update" name="update" value="更新">
				<input type="button" class="delete" name="delete" value="削除">
				<input type="button" value="戻る"
					onClick="location.href='/facility/list'" />
			</form:form>
		</div>
		<jsp:include page="/WEB-INF/views/footer/footer.jsp" flush="true" />

	</div>
	<!-- javascriptの記述 -->
	<script type="text/javascript">
		$(function() {
			$('.delete').click(function() {
				$('#dialog').dialog('open');
			});

			$('#dialog').dialog({
				autoOpen : false,
				modal : true,
				title : 'DeleteFacility',
				buttons : {
					'OK' : function() {
						$(this).dialog('close');
						$('form').attr('action', '/facility/complete');
						$('.delete').attr('type', 'submit');
						$('.delete').click();
					},
					'cancel' : function() {
						$(this).dialog('close');

					}
				}

			});

		});
	</script>
	</ bo dy>
</html>