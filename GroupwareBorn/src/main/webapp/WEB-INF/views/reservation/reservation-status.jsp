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

<link rel="stylesheet" type="text/css" href="/css/style-new.css" />
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
			<input type="hidden" id="facilityId" name="facilityId"
				value="${statusForm.facilityForm.id}">
			<div id="calendar-menu">
				<input type="button" id="last-month" value="前月"> <span>${statusForm.calendarForm.year}年${statusForm.calendarForm.month}月
				</span> <input type="button" id="next-month" value="次月"> <input
					type="hidden" id="year" name="year"
					value="${statusForm.calendarForm.year}"> <input
					type="hidden" id="month" name="month"
					value="${statusForm.calendarForm.month}">
			</div>

			<table id="calendar">
				<thead>
					<tr>
						<c:forEach var="weekName"
							items="${statusForm.calendarForm.weekName}" varStatus="loop">
							<c:choose>
								<c:when test="${loop.count == 6}">
									<th class="saturday-th"><c:out value="${weekName}" /></th>
								</c:when>
								<c:when test="${loop.count == 7}">
									<th class="sunday-th"><c:out value="${weekName}" /></th>
								</c:when>
								<c:otherwise>
									<th class="weekday-th"><c:out value="${weekName}" /></th>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</tr>
				</thead>
				<tbody>
					<tr>
						<c:forEach var="day"
							items="${statusForm.calendarForm.dayFormList}" varStatus="status">
							<c:if test="${(status.count - 1) % 7 == 0}">
								<tr>
							</c:if>
							<c:if test="${day.date == '-'}">
								<td class="blank-td">
									<ul class="calendar-cell-ul">
										<li class="calendar-cell-li">${day.date}</li>
									</ul>
								</td>
							</c:if>
							<c:if test="${day.date != '-'}">
								<td class="calendar-td">
									<ul class="calendar-cell-ul">
										<li class="calendar-cell-li">${day.date}</li>
										<c:forEach var="reservation"
											items="${day.reservationFormList}">
											<li class="calendar-cell-li"><a
												href="/facility-reservation/${statusForm.facilityForm.id}/detail/${reservation.id}">${reservation.startTime}～${reservation.endTime}<br>(${reservation.userId})
											</a></li>
										</c:forEach>
										<c:if
											test="${day.yearMonthDate >= statusForm.calendarForm.today}">

											<li class="calendar-cell-li"><input type="image"
												src="/img/add.png" alt="新規予約" title="新規予約"
												onclick="location.href='${statusForm.facilityForm.id}/add/?year=${statusForm.calendarForm.year}&month=${statusForm.calendarForm.month}&date=${day.date}'"></li>

										</c:if>
									</ul>
								</td>
							</c:if>
						</c:forEach>
					</tr>
				</tbody>
			</table>

			<div id="return-display">
				<a href="/facility-reservation/list">前のページに戻る</a>
			</div>
			<jsp:include page="/WEB-INF/views/footer/footer.jsp" flush="true" />
		</div>
	</div>

	<script type="text/javascript">
		$("#next-month").click(function() {
			var facilityId = $("#facilityId").val();
			var year = $("#year").val();
			var month = $("#month").val();
			var intYear = parseInt(year);
			var nextMonth = parseInt(month) + 1;
			if (nextMonth == 13) {
				intYear++;
				nextMonth = 1;
			}
			var changeCalFlag = true;
			var data = {
				"facilityForm" : {
					"id" : facilityId
				},
				"calendarForm" : {
					"year" : year,
					"month" : month,
					"changeCalFlag" : changeCalFlag
				},
			};
			calendar(data);
		});

		$("#last-month").click(function() {
			var facilityId = $("#facilityId").val();
			var year = $("#year").val();
			var month = $("#month").val();
			var intYear = parseInt(year);
			var lastMonth = parseInt(month) - 1;
			if (lastMonth == 0) {
				intYear--;
				lastMonth = 12;
			}
			var changeCalFlag = false;
			var data = {
				"facilityForm" : {
					"id" : facilityId
				},
				"calendarForm" : {
					"year" : year,
					"month" : month,
					"changeCalFlag" : changeCalFlag
				},
			};
			calendar(data);
		});

		function calendar(data) {
			$
					.ajax({
						url : "/facility-reservation/change-calendar",
						type : "POST",
						data : JSON.stringify(data),
						dataType : "json",
						contentType : "application/json ; charset=utf-8"
					})
					.done(
							function(result, status, jqxhr) {
								console.log("☆☆☆☆☆");
								console.log(data);
								console.log(result);
								$("span")
										.text(
												result.calendarForm.year
														+ "年"
														+ Number(result.calendarForm.month)
														+ "月");
								$("#year").val(result.calendarForm.year);
								$("#month").val(result.calendarForm.month);
								$("table#calendar tbody").empty();
								var htmlData = "<tr>";
								var dayForm = result.calendarForm.dayFormList;
								for ( var i in dayForm) {
									if (i % 7 == 0) {
										htmlData += "<tr>"
									}
									if (dayForm[i].date == "-") {
										htmlData += '<td class="blank-td"><ul class="calendar-cell-ul"><li class="calendar-cell-li">'
												+ dayForm[i].date
												+ "</li></ul></td>"
									}
									if (dayForm[i].date != "-") {
										htmlData += '<td class="calendar-td"><ul class="calendar-cell-ul"><li class="calendar-cell-li">'
												+ dayForm[i].date + "</li>"
										var reservationForm = dayForm[i].reservationFormList;
										for ( var j in reservationForm) {
											htmlData += '<li class="calendar-cell-li"><a href="/facility-reservation/' + result.facilityForm.id + "/detail/" + reservationForm[j].id + '">'
													+ reservationForm[j].startTime
													+ "～"
													+ reservationForm[j].endTime
													+ "<br>"
													+ "("
													+ reservationForm[j].userId
													+ ")</a></li>";
										}

										if (dayForm[i].yearMonthDate >= result.calendarForm.today) {
											htmlData += '<li class="calendar-cell-li"><input type="image" src="/img/add.png" alt="新規予約" title="新規予約" onclick=location.href='
													+ '"/facility-reservation/'
													+ result.facilityForm.id
													+ "/add/?year="
													+ result.calendarForm.year
													+ "&month="
													+ result.calendarForm.month
													+ "&date="
													+ dayForm[i].date
													+ '"></li>';
										}
										htmlData += "</ul></td>"
									}
								}
								htmlData += "</tr>";
								$('table#calendar tbody').append(htmlData);
							}).fail(function(jqXHR, textStatus, errorThrown) {
						console.log('エラー');
					})
		}
	</script>

</body>
</html>