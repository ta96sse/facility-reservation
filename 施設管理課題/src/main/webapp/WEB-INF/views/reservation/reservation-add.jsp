<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>施設予約</title>
<meta charset="UTF-8">

<link rel="stylesheet" type="text/css" href="/css/style.css" />
<link rel="stylesheet" type="text/css" href="/css/a.css" />
<!-- jQueryの読み込み -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">

</script>
</head>

<body>
	<div id="base">
		<div id="header">
			<jsp:include page="/WEB-INF/views/header/header.jsp" flush="true" />
		</div>
		<div id="contents">
			<div id="view-title">施設予約</div>
			<form:form modelAttribute="reservationForm" id="reservationForm"
				action="/facility-reservation/confirm" method="post">

				<p id="contents-title">予約時間を選択してください。</p>
				<p>${reservationForm.facilityForm.name}</p>
				<p>${reservationForm.year}年${reservationForm.month}月${reservationForm.date}日</p>
				<p class="timeCheck1" style="color: red"></p>
				<p class="timeCheck2" style="color: red"></p>
				<p>
					予約開始時間
					<form:select path="startHour">
						<c:forEach var="startHour" begin="9" end="21">
							<c:if test="${reservationForm.startHour == startHour}">
										<option selected="selected">${startHour}</option>
									</c:if>
									<c:if test="${reservationForm.startHour != startHour}">
										<option>${startHour}</option>
									</c:if>
						</c:forEach>
					</form:select>
					時
					<form:select path="startMinute">
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
				</p>
				<p>
					予約終了時間
					<form:select path="endHour">
						<c:forEach var="endHour" begin="9" end="21">
							<c:if test="${reservationForm.endHour == endHour}">
										<option selected="selected">${endHour}</option>
									</c:if>
									<c:if test="${reservationForm.endHour != endHour}">
										<option>${endHour}</option>
									</c:if>
						</c:forEach>
					</form:select>
					時
					<form:select path="endMinute">
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
				</p>

				<form:input type="hidden" path="year" value="${reservationForm.year}" />
				<form:input type="hidden" path="month" value="${reservationForm.month}" />
				<form:input type="hidden" path="date" value="${reservationForm.date}" />
				<form:input type="hidden" path="facilityForm.id"
					value="${reservationForm.facilityForm.id}" />
				<form:input type="hidden" path="facilityForm.name"
					value="${reservationForm.facilityForm.name}" />

				<input type="button" class="add" value="予約" />
			</form:form>
			<div id="return-display">
				<a href="/facility-reservation/${reservationForm.facilityForm.id}">前のページに戻る</a>
			</div>
			<jsp:include page="/WEB-INF/views/footer/footer.jsp" flush="true" />
		</div>
	</div>
	<script type="text/javascript">
		$('.add').click(function() {
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
							$('#reservationForm').submit();
						}
					})
				})

			}
		});

	</script>
</body>
</html>