<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/css/style.css"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<title>error</title>
</head>
<body>



<div id="base" style="margin:auto;">
<jsp:include page="/WEB-INF/views/header/header.jsp" flush="true" />

<h1 style="color:red;">例外が発生しました。</h1>
<a style="font-size:25px;">詳細</a> : <a class="title" style="font-size:20px;" href="#">${message}</a>


<div id="detail" style="margin-top:50px; margin-bottom:100px; width:100%;">
	<ul class="list" style="text-align:center; list-style:none;">
	</ul>
</div>
<a href="/menu">メニューに戻る</a>
<div id="footer">
			<p class="copyright">(C) 2003 Ginga Software, All Rights
				Reserved..</p>
</div>
</div>

<input type="hidden" id="detail-message" value="${detail}"/>
<script>
	$(function(){
		$('.title').click(function(){
			$('ul.list').empty();
			var message =$('#detail-message').val();
			var h = '<li style="line-height:2.5em;">' +message+ '</li>';
			$('ul.list').append(h).hide().fadeIn(2000);
			return false;
		});
	});
</script>
</body>
</html>