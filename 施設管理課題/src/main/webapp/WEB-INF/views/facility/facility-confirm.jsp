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
<link rel="stylesheet" type="text/css" href="/css/style.css" />
</head>
<body>
	<div id="base">
		<jsp:include page="/WEB-INF/views/header/header.jsp" flush="true" />
		<div id="contents">
			<div id="view-title">施設情報 確認画面</div>

			<p id="contents-title">施設情報を確認してください。</p>
			<form:form modelAttribute="facilitySessionForm"
				action="/facility/complete" method="post">
				<spring:eval var="accountSessionForm"
					expression="@accountSessionForm" />
				<table id="facility-info">
					<tr>
						<th class="facility-info-th">施設名</th>
						<td><c:out value="${facilitySessionForm.facilityForm.name}" /></td>
					</tr>
					<tr>
						<th class="facility-info-th">種別</th>

						<td><c:out
								value="${facilitySessionForm.facilityForm.facilityTypeForm.name}" /></td>

						<!--
						<td><c:forEach var="facilityType" items="${typeListForm}"
								varStatus="status">

								<c:if
									test="${facilityFormSession.facilityTypeForm.id== facilityType.id}">
									${facilityType.name}
								</c:if>

							</c:forEach></td>
						-->


					</tr>
					<tr>
						<th class="facility-info-th">定員</th>
						<td><c:out
								value="${facilitySessionForm.facilityForm.capacity}" /></td>
					</tr>
				</table>
				<form:input type="hidden" path="facilityForm.id"
					value="${facilitySessionForm.facilityForm.id}" />
				<form:input type="hidden" path="facilityForm.name"
					value="${facilitySessionForm.facilityForm.name}" />
				<form:input type="hidden" path="facilityForm.facilityTypeForm.id"
					value="${facilitySessionForm.facilityForm.facilityTypeForm.id}" />
				<form:input type="hidden" path="facilityForm.capacity"
					value="${facilitySessionForm.facilityForm.capacity}" />

				<input type="submit" name="${btnAction}" value="${btnName}" />
				<input type="button" class="back" value="戻る"
					onClick="location.href='/facility/back/${btnAction}'" />
			</form:form>
		</div>
		<jsp:include page="/WEB-INF/views/footer/footer.jsp" flush="true" />

	</div>
</body>
</html>