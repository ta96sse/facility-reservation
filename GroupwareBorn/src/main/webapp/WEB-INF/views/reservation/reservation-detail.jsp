<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>予約情報更新・削除</title>
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
			<div id="view-title">予約情報管理</div>

			<div id="update">
				<table style="margin: auto;">
					<tr>
						<th>予約ID：</th>
						<td><font size="5">${session.id}</font></td>
					</tr>
				</table>
			</div>
			<div id="delete">
				<table style="margin: auto;">
					<tr>
						<th>予約ID：</th>
						<td><font size="5">${session.id}</font></td>
					</tr>
				</table>
			</div>

			<!--
            <ul class="msgs">
                <li>エラーメッセージ表示エリア</li>
            </ul>
             -->

			<p id="contents-title">予約情報の更新、削除を行えます</p>
			<form:form modelAttribute="session"
				action="/facility-reservation/confirm" method="post">
				<p>${session.facilityForm.name}</p>
				<p>${session.year}年${session.month}月${session.date}日</p>
				<p class="timeCheck" style="color: red"></p>
				<table id="facility-info">
					<tr>
						<th class="facility-info-th">最終更新者<br> <font size="1px"
							style="color: red;">※ユーザー名は変更できません</font></th>
					</tr>
					<tr>
						<td><a class="facilityCheck" style="color: red"></a> <a
							style="color: red"><form:errors path="userId" /></a> <form:input
								class="userId" path="userId" readonly="true" /></td>
					</tr>
					<tr>
						<th class="facility-info-th">予約開始時間</th>
					</tr>
					<tr>
						<td><form:select class="selectObj" path="startHour"
								value="${session.startHour}">
								<c:forEach var="startHour" begin="9" end="21">
									<option>${startHour}</option>
								</c:forEach>
							</form:select> 時 <form:select class="selectObj" path="startMinute"
								value="${session.startMinute}">
								<option>00</option>
								<option>15</option>
								<option>30</option>
								<option>45</option>
							</form:select> 分</td>
					</tr>
					<tr>
						<th class="facility-info-th">予約終了時間</th>
					</tr>
					<tr>
						<td><form:select class="selectObj" path="endHour"
								value="${session.endHour}">
								<c:forEach var="endtHour" begin="9" end="21">
									<option>${endtHour}</option>
								</c:forEach>
							</form:select> 時 <form:select class="selectObj" path="endMinute"
								value="${session.endMinute}">
								<option>00</option>
								<option>15</option>
								<option>30</option>
								<option>45</option>
							</form:select> 分</td>
					</tr>
				</table>

				<form:input type="hidden" path="id" value="${session.id}" />
				<form:input type="hidden" path="year" value="${session.year}" />
				<form:input type="hidden" path="month" value="${session.month}" />
				<form:input type="hidden" path="date" value="${session.date}" />
				<input type="button" class="update" name="update" value="更新">
				<input type="button" class="delete" name="delete" value="削除">
				<input type="button" value="戻る"
					onClick="location.href='/facility-reservation/${session.facilityForm.id}'" />
			</form:form>
		</div>
		<jsp:include page="/WEB-INF/views/footer/footer.jsp" flush="true" />

	</div>
	<!-- javascriptの記述 -->
	<script type="text/javascript">
		$(function() {
			$('.update').click(function() {
				var startTime = parseInt($('#startHour').val()
						+ $('#startMinute').val());
				var endTime = parseInt($('#endHour').val()
						+ $('#endMinute').val());
				console.log(startTime);
				console.log(endTime);
				if (startTime >= endTime) {
					$('.timeCheck').text('終了時間は開始時間より遅く設定してください');
				} else {
					$('#update').dialog('open');
				}
			});

			$('#update').dialog({
				autoOpen : false,
				modal : true,
				title : '予約を更新します',
				buttons : {
					'OK' : function() {
						$(this).dialog('close');
						$('form').attr('action',
								'/facility-reservation/complete');
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
						$('form').attr('action',
								'/facility-reservation/complete');
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