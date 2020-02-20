<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>予約情報更新・削除</title>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/css/style-new.css" />
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
			<div id="view-title">予約情報管理</div>

			<div id="update">
				<table style="margin: auto;">
					<tr>
						<th>予約ID：</th>
						<td><font size="5">${reservationForm.id}</font></td>
					</tr>
				</table>
			</div>
			<div id="delete">
				<table style="margin: auto;">
					<tr>
						<th>予約ID：</th>
						<td><font size="5">${reservationForm.id}</font></td>
					</tr>
				</table>
			</div>

			<!--
            <ul class="msgs">
                <li>エラーメッセージ表示エリア</li>
            </ul>
             -->

			<p id="contents-title">予約情報の更新、削除を行えます</p>
			<form:form modelAttribute="reservationForm"
				action="/facility-reservation/complete" method="post">
				<p>${reservationForm.facilityForm.name}</p>
				<p>${reservationForm.year}年${reservationForm.month}月${reservationForm.date}日</p>
				<p class="timeCheck1" style="color: red"></p>
				<p class="timeCheck2" style="color: red"></p>
				<table id="reservation-info">
					<tr>
						<th class="reservation-info-th">最終更新者<br> <font size="1px"
							style="color: red;">※ユーザー名は変更できません</font></th>
					</tr>
					<tr>
						<td><form:input class="userId" path="userForm.loginName" readonly="true" /></td>
					</tr>
					<tr>
						<th class="reservation-info-th">予約開始時間</th>
					</tr>
					<tr>
						<td>
							<form:select class="selectObj" path="startHour"
								value="${reservationForm.startHour}" varStatus="status">
								<c:forEach var="startHour" begin="09" end="21">
									<c:if test="${reservationForm.startHour == startHour}">
										<option selected="selected">${startHour}</option>
									</c:if>
									<c:if test="${reservationForm.startHour != startHour}">
										<option>${startHour}</option>
									</c:if>
								</c:forEach>
							</form:select>
							時
							<form:select class="selectObj" path="startMinute"
								value="${reservationForm.startMinute}">
								<c:forEach var="startMinute" begin="00" end="45" step="15">
									<c:if test="${reservationForm.startMinute == startMinute}">
										<option selected="selected"><fmt:formatNumber value="${startMinute}" pattern="00" /></option>
									</c:if>
									<c:if test="${reservationForm.startMinute != startMinute}">
										<option><fmt:formatNumber value="${startMinute}" pattern="00" /></option>
									</c:if>
								</c:forEach>
							</form:select>
							分
						</td>
					</tr>
					<tr>
						<th class="reservation-info-th">予約終了時間</th>
					</tr>
					<tr>
						<td>
							<form:select class="selectObj" path="endHour"
								value="${reservationForm.endHour}">
								<c:forEach var="endHour" begin="09" end="21">
									<c:if test="${reservationForm.endHour == endHour}">
										<option selected="selected">${endHour}</option>
									</c:if>
									<c:if test="${reservationForm.endHour != endHour}">
										<option>${endHour}</option>
									</c:if>
								</c:forEach>
							</form:select>
							時
							<form:select class="selectObj" path="endMinute"
								value="${reservationForm.endMinute}">
								<c:forEach var="endMinute" begin="00" end="45" step="15">
									<c:if test="${reservationForm.endMinute == endMinute}">
										<option selected="selected"><fmt:formatNumber value="${endMinute}" pattern="00" /></option>
									</c:if>
									<c:if test="${reservationForm.endMinute != endMinute}">
										<option><fmt:formatNumber value="${endMinute}" pattern="00" /></option>
									</c:if>
								</c:forEach>
							</form:select>
							分
						</td>
					</tr>
				</table>

				<form:input type="hidden" path="id" value="${reservationForm.id}" />
				<form:input type="hidden" path="year" value="${reservationForm.year}" />
				<form:input type="hidden" path="month" value="${reservationForm.month}" />
				<form:input type="hidden" path="date" value="${reservationForm.date}" />
				<form:input type="hidden" path="facilityForm.id"
					value="${reservationForm.facilityForm.id}" />

				<input type="button" class="update" name="update" value="更新">
				<input type="button" class="delete" name="delete" value="削除">
				<input type="button" value="戻る"
					onClick="location.href='/facility-reservation/${reservationForm.facilityForm.id}'" />
			</form:form>
		</div>
		<jsp:include page="/WEB-INF/views/footer/footer.jsp" flush="true" />

	</div>
	<!-- javascriptの記述 -->
	<script type="text/javascript">
		$(function() {
			$('.update').click(function() {
				var startTime = parseInt($('#startHour').val() + $('#startMinute').val());
				var endTime = parseInt($('#endHour').val() + $('#endMinute').val());

				$('.timeCheck1').text('');
				$('.timeCheck2').text('');

				if (startTime >= endTime) {
					$('.timeCheck1').text('終了時間は開始時間より遅く設定してください');
				} else {

					$(function(){
						$.ajax({
							url : "/facility-reservation/check",
							type : "POST",
							data : JSON.stringify({
								"id" : $('#id').val(),
								"year" : $('#year').val(),
								"month" : $('#month').val(),
								"date" : $('#date').val(),
								"startHour" : $('#startHour').val(),
								"startMinute" : $('#startMinute').val(),
								"endHour" : $('#endHour').val(),
								"endMinute" : $('#endMinute').val(),
								"facilityForm" : {"id" : $('#facilityForm\\.id').val()}
							}),
							dataType : "json",
							contentType : "application/json ; charset=utf-8"
						})
						.done(function(result, status, jqxhr) {
							console.log(result);
							if (result == 1) {
								$('.timeCheck2').text('この時間はすでに予約されています');
							} else {
								$('#update').dialog('open');
							}
						})

					});
				}

			});

			$('#update').dialog({
				autoOpen : false,
				modal : true,
				title : '予約を更新します',
				buttons : {
					'OK' : function() {
						$(this).dialog('close');
						$('.update').attr('type', 'submit');
						$('.update').click();
					},
					'cancel' : function() {
						$(this).dialog('close');
					}
				}
			});

			$('.delete').click(function() {
				$('#delete').dialog('open');
			});

			$('#delete').dialog({
				autoOpen : false,
				modal : true,
				title : '予約を削除します',
				buttons : {
					'OK' : function() {
						$(this).dialog('close');
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
</body>
</html>