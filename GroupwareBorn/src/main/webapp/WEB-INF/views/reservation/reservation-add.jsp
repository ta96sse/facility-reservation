<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
			<form:form modelAttribute="session"
				action="/facility-reservation/confirm" method="post">

				<p id="contents-title">予約時間を選択してください。</p>
				<p>${session.facilityForm.name}</p>
				<p>${session.year}年${session.month}月${session.date}日</p>
				<p>
					予約開始時間
					<form:select class="selectObj" path="startHour"
						value="${session.startHour}">
						<c:forEach var="startHour" begin="9" end="21">
							<option>${startHour}</option>
						</c:forEach>
					</form:select>
					時
					<form:select class="selectObj" path="startMinute"
						value="${session.startMinute}">
						<option>00</option>
						<option>15</option>
						<option>30</option>
						<option>45</option>
					</form:select>
					分
				</p>
				<p>
					予約終了時間
					<form:select class="selectObj" path="endHour"
						value="${session.endHour}">
						<c:forEach var="endtHour" begin="9" end="21">
							<option>${endtHour}</option>
						</c:forEach>
					</form:select>
					時
					<form:select class="selectObj" path="endMinute"
						value="${session.endMinute}">
						<option>00</option>
						<option>15</option>
						<option>30</option>
						<option>45</option>
					</form:select>
					分
				</p>
				<input type="submit" value="予約" />

				<form:input type="hidden" path="facilityForm.id"
					value="${session.facilityForm.id}" />
				<form:input type="hidden" path="facilityForm.name"
					value="${session.facilityForm.name}" />
				<form:input type="hidden" path="year" value="${session.year}" />
				<form:input type="hidden" path="month" value="${session.month}" />
				<form:input type="hidden" path="date" value="${session.date}" />
			</form:form>
			<div id="return-display">
				<a href="/facility-reservation/${session.facilityForm.id}">前のページに戻る</a>
			</div>
			<jsp:include page="/WEB-INF/views/footer/footer.jsp" flush="true" />
		</div>
	</div>
	<script type="text/javascript">

	</script>
</body>
</html>