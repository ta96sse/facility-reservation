<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${title}</title>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/css/style-new.css" />
</head>
<body>
	<div id="base">
		<jsp:include page="/WEB-INF/views/header/header.jsp" flush="true" />
		<div id="contents">
			<div id="view-title">予約内容確認</div>

			<p id="contents-title">以下の内容で予約します。よろしいですか？</p>
			<form:form modelAttribute="reservationForm"
				action="/facility-reservation/complete" method="post">
				<spring:eval var="accountSessionForm"
					expression="@accountSessionForm" />
				<table id="reservation-info">
					<tr>
						<th class="reservation-info-th">予約施設</th>
						<td><c:out value="${reservationForm.facilityForm.name}" /></td>
					</tr>
					<tr>
						<th class="reservation-info-th">予約日</th>

						<td><c:out
								value="${reservationForm.year}年${reservationForm.month}月${reservationForm.date}日" /></td>
					</tr>
					<tr>
						<th class="reservation-info-th">予約時間</th>
						<td><c:out
								value="${reservationForm.startHour}:${reservationForm.startMinute}～${reservationForm.endHour}:${reservationForm.endMinute}" /></td>
					</tr>
				</table>
				<form:input type="hidden" path="facilityForm.id"
					value="${reservationForm.facilityForm.id}" />
				<form:input type="hidden" path="year" value="${reservationForm.year}" />
				<form:input type="hidden" path="month" value="${reservationForm.month}" />
				<form:input type="hidden" path="date" value="${reservationForm.date}" />
				<form:input type="hidden" path="startHour"
					value="${reservationForm.startHour}" />
				<form:input type="hidden" path="startMinute"
					value="${reservationForm.startMinute}" />
				<form:input type="hidden" path="endHour" value="${reservationForm.endHour}" />
				<form:input type="hidden" path="endMinute"
					value="${reservationForm.endMinute}" />

				<input type="button" class="back" value="戻る"
					onClick="location.href='/facility-reservation/${reservationForm.facilityForm.id}/add/?year=${reservationForm.year}&month=${reservationForm.month}&date=${reservationForm.date}'" />
				<input type="submit" name="add" value="確定" />
			</form:form>
		</div>
		<jsp:include page="/WEB-INF/views/footer/footer.jsp" flush="true" />

	</div>
</body>
</html>