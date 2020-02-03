<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>予約状況詳細</title>
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
			<div id="view-title">予約状況詳細</div>

			<!--
				<ul class="msgs">
			    	<li>エラーメッセージ表示エリア</li>
			    </ul>
			-->

			<p id="contents-title">${statusForm.facilityForm.name}</p>
			<div id="calendar-menu">
				${statusForm.calendarForm.year}年${statusForm.calendarForm.month + 1}月
				<button id="next-month" value="${statusForm.calendarForm.month + 2}">次月</button>
			</div>

			<table id="calendar">
				<tr>
					<c:forEach var="weekName"
						items="${statusForm.calendarForm.weekName}" step="1">
						<th class="calendar-th"><c:out value="${weekName}" /></th>
					</c:forEach>
				</tr>
				<tr>
					<c:forEach var="day" items="${statusForm.calendarForm.dayFormList}"
						varStatus="status">
						<c:if test="${(status.count - 1) % 7 == 0}">
							<tr>
						</c:if>
						<td>
							<ul class="calendar-cell-ui">
								<li class="calendar-cell-li">${day.date}</li>
								<c:forEach var="reservation" items="${day.reservationFormList}">
									<li class="calendar-cell-li"><a
										href="/facilityreservation/${statusForm.facilityForm.id}/${reservation.id}">${reservation.startTime}～${reservation.endTime}<br>(${reservation.userId})
									</a></li>
								</c:forEach>
								<c:if test="${day.date != '-'}">
									<li class="calendar-cell-li"><input type="image"
										src="/img/add.png" alt="新規予約" title="新規予約"
										onclick="location.href='${statusForm.facilityForm.id}/add/?date=${day.date}&month=${statusForm.calendarForm.month + 1}&year=${statusForm.calendarForm.year}'"></li>
								</c:if>
							</ul>
						</td>
					</c:forEach>
				</tr>
			</table>

			<div id="return-display">
				<a href="/facilityreservation/list">前のページに戻る</a>
			</div>
			<jsp:include page="/WEB-INF/views/footer/footer.jsp" flush="true" />
		</div>
	</div>

	<script type="text/javascript">
		$("#next-month").click(function() {
			var month = $(this).val();
			console.log(month);
			$(function() {
				$.ajax({
					url : "/facilityreservation",
					type : "POST",
					data : JSON.stringify({"month" : month}),
					dataType : "json",
					contentType : "application/json ; charset=utf-8"
				})
				.done(function(result, status, jqxhr) {
					console.log("☆☆☆☆☆");

					var nextMonth;
					for ( var i in result) {
						console.log(result[i]);
						nextMonth = 12345
					}

				})
			})
		})
	</script>

</body>
</html>