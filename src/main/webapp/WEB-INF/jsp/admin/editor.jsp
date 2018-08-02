<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String staticPath = path+"/static";
%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Tables</title>
		<link href="../css/bootstrap/bootstrap.min.css" rel="stylesheet">
		<link rel="stylesheet" href="../css/adminStyles.css" />
		<style>
			#editor {
				overflow: scroll;
				max-height: 500px;
			}
		</style>

	</head>

	<body>
		<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
			<div class="row">
				<ol class="breadcrumb">
					<li>
						<a href="#"><span class="glyphicon glyphicon-home"></span></a>
					</li>
					<li class="active">添加博文</li>
				</ol>
			</div>

			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">添加博文</h1>
				</div>
			</div>

			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">博文信息</div>
						<div class="panel-body">
							<div id="editor"></div>
							<textarea id="text1" style="width:100%; height:200px;"></textarea>
							<button id="getContent">获取内容</button>
						</div>
					</div>
				</div>
			</div>
		</div>

		</div>
		<!--/.main-->
		<script src="../js/jquery/jquery-3.3.1.min.js"></script>
		<script src="../js/bootstrap/bootstrap.min.js"></script>
		<script type="text/javascript" src="../js/wangEditor.min.js"></script>
		<script>
		var E = window.wangEditor;
		var editor = new E('#editor');
		editor.customConfig.uploadImgServer = '/upload';
		editor.create();
					
		</script>
	</body>

</html>