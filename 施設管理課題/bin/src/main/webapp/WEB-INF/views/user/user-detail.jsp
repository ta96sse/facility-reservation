<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>ユーザー情報更新・削除</title>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/css/style.css" />
<!-- jQueryの読み込み -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!-- jQueryUiの読み込み -->
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
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
			<div id="view-title">ユーザー情報管理</div>

		<div id="dialog">
			<table style="margin:auto;">
				<tr>
					<th>user :   </th>
					<td><font size="5">${userFormSession.loginName}</font></td>
				</tr>
			</table>
		</div>
			

			<!--
            <ul class="msgs">
                <li>エラーメッセージ表示エリア</li>
            </ul>
             -->
			<p id="contents-title">ユーザー情報の更新、削除を行えます</p>
			<form:form modelAttribute="userFormSession" action="/user/confilm" method="post">
				<table id="facility-info">
					<tr>
						<th class="facility-info-th">ユーザー名<br><font size="1px" style="color:red;">※ユーザー名は変更できません</font></th>
						<td>
						<a class="userCheck" style="color:red"></a>
						<form:input class="userId" path="loginName" readonly="true"/></td>
					</tr>
					<tr>
						<th class="facility-info-th">Password</th>
						<td>
						<a class="passCheck" style="color:red"></a>
						<a style="color:red"><form:errors path="password1"/></a>
						<form:input class="password1" path="password1" /></td>
					</tr>
					<tr>
						<th class="facility-info-th">Password<br><font size="1px">※もう一度入力してください。</font></th>
						<td>
						<a style="color:red"><form:errors path="password2"/></a>
						<form:input class="password2" path="password2" /></td>
					</tr>
					<tr>
						<th class="facility-info-th">権限</th>
						<td>
							<form:radiobutton path="parmissionLevel" value="1" label="管理者" checked="checked" />
							<form:radiobutton path="parmissionLevel" value="2" label="ユーザー" />
						</td>
					<tr>
						<th class="facility-info-th">備考</th>
						<td><form:textarea id="facility-info-remarks" rows="10"
								maxlength="200" path="note" /></td>
					</tr>
				</table>
				<input type="button" class="update"  name="update" value="更新">
				<input type="button" class="delete" name="delete" value="削除">
				<input type="button"  value="戻る" onClick="location.href='/user/list'"/>
			</form:form>
		</div>
		<div id="footer">
			<p class="copyright">(C) 2003 Ginga Software, All Rights
				Reserved..</p>
		</div>
	</div>
	<!-- javascriptの記述 -->
	<script type="text/javascript">
		$(function(){
			$('.update').click(function(){
				var pass1 = $('.password1').val();
				var pass2 = $('.password2').val();
				$('.passCheck').text('');
				if(pass1 != pass2){
					$('.passCheck').text('パスワード不一致');
				}else{
					$('.update').attr('type','submit');
				}
			});
			
			
			
			$('.delete').click(function(){
				$('#dialog').dialog('open');
			});
			
			$('#dialog').dialog({
				autoOpen :false,
				modal : true,
				title : 'DeleteUser',
				buttons :{
					'OK' : function(){
						$(this).dialog('close');
						$('form').attr('action', '/user/complete');
						$('.delete').attr('type','submit');
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