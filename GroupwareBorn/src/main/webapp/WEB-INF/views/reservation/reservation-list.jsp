<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>施設一覧</title>
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
			<div id="view-title">施設一覧</div>

			<!--
			            <ul class="msgs">
			                <li>エラーメッセージ表示エリア</li>
			            </ul>
			 -->

			<p id="contents-title">施設を選択してください。</p>
			<form>

				<select class="type-list">
					<option selected="selected" value=0>すべて</option>

					<c:forEach var="type" items="${facilityListForm.facilityTypeList}">

						<option value="${type.id}">${type.name}</option>
					</c:forEach>

				</select>
				<ul id="facility">
					<c:forEach var="facility" items="${facilityListForm.facilityList}">
						<li class="facility-list"><a class="function-button"
							href="/facility-reservation/${facility.id}">${facility.name}：定員${facility.capacity}人</a></li>
					</c:forEach>
				</ul>
			</form>
		</div>
		<div id="return-display">
			<a href="/menu">前のページに戻る</a>
		</div>
		<jsp:include page="/WEB-INF/views/footer/footer.jsp" flush="true" />
	</div>

	<script type="text/javascript">
		$(".type-list").change(function() {
			var typeId = $(this).val();
			console.log(typeId);
			$(function() {
				$.ajax({
					url : "/facility-reservation-list",
					type : "POST",
					data : JSON.stringify({"facilityTypeForm" : {"id" : typeId}}),
					dataType : "json",
					contentType : "application/json ; charset=utf-8"
				})
					.done(function(result, status, jqxhr) {
						$("#facility").empty();
						var typeResult;
						for ( var i in result) {
							typeResult = '<li class="facility-list"><a class="function-button" href="/facility-reservation/'+result[i].id+'">'+ result[i].name+ '：定員'+ result[i].capacity+ '人</a></li>';
							$('#facility').append(typeResult);
						}
					})
			})
		})
	</script>

</body>
</html>


<!--
<body>
	<div id="base">

		<jsp:include page="/WEB-INF/views/header/header.jsp" flush="true" />

		<div id="contents">
			<div id="view-title">施設一覧</div>

			<!--
            <ul class="msgs">
                <li>エラーメッセージ表示エリア</li>
            </ul>


			<p id="contents-title">施設を選択してください。</p>
			<div id="return-display">
				<a style="font-size: 18px;" href="/menu">メニューに戻る</a>
			</div>

			<select class="type-list">
				<option selected="selected">すべて</option>
				<c:forEach var="facilityType"
					items="${facilityListForm.facilityTypeList}">
					<option id="typeID" value="${facilityType.id}">${facilityType.name}</option>
				</c:forEach>
			</select> <br>

			<ul id="facility">
				<c:forEach var="facility" items="${facilityListForm.facilityList}"
					varStatus="loop">
					<li class="facility-list"><a class="function-button"
						href="/facilityreservation/${facility.id}">${facility.name}：定員${facility.capacity}人</a></li>
				</c:forEach>
			</ul>

			<div id="return-display">
				<a style="font-size: 18px;" href="/menu">メニューに戻る</a>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/footer/footer.jsp" flush="true" />

	</div>

	<!-- javascriptの記述
	<script type="text/javascript">
		$(function() {
			$('.type-list').change(function() {

				$.ajax({
					url : './facilityreservation-list',
					type : 'POST',
					data : {'typeId' : $('#typeID').val(),},
					dataType: "json"
				})
					.done(function (result,status,jqxhr) {
						  // 通信成功時のコールバック処理
						  $("#facility").empty();
						  var typeResult;
						  for(var i in result){
							  typeResult = '<li class="facility-list"><a class="function-button"
								href="/facilityreservation/${facility.id}" value="'+result[i].facilityName+" "+"定員"+result[i].capacity+'名" />${facility.name}：定員${facility.capacity}人</a></li>'
								$('#facility').append(typeResult);

						}).fail(function (data) {
						  // 通信失敗時のコールバック処理
						}).always(function (data) {
						  // 常に実行する処理
						});


<!-- ページネーション
			<div id="pagination">
				<c:url value="./list" var="prev">
					<c:param name="page" value="${page-1}" />
				</c:url>
				<c:if test="${page > 1}">
					<a href="<c:out value="${prev}" />">Prev</a>
				</c:if>
				<c:forEach begin="1" end="${maxPages}" step="1" varStatus="i">
					<c:choose>
						<c:when test="${page == i.index}">
							<span>${i.index}</span>
						</c:when>
						<c:otherwise>
							<c:url value="./list" var="url">
								<c:param name="page" value="${i.index}" />
							</c:url>
							<a href='<c:out value="${url}" />'>${i.index}</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:url value="./list" var="next">
					<c:param name="page" value="${page + 1}" />
				</c:url>
				<c:if test="${page + 1 <= maxPages}">
					<a href='<c:out value="${next}" />'>Next</a>
				</c:if>
			</div>
<!-- ページネーション -->