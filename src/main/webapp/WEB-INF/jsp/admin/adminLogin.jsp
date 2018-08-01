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

<link href="<%=staticPath%>/css/bootstrap/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="<%=staticPath%>/css/adminStyles.css" />

</head>

<body>
	<jsp:include page="top.jsp"/>
	<div class="container" style="margin-top: 100px;">
		<div class="row">
			<div class="col-lg-4 col-lg-offset-4">
				<form class="form-signin" method="post" action="<%=path%>/admin/login">
        <h2 class="form-signin-heading">管理员登陆</h2>
        <label for="inputEmail" class="sr-only">邮箱</label>
        <input type="email" id="inputEmail" class="form-control" name="email" placeholder="邮箱地址" required autofocus>
        <label for="inputPassword" class="sr-only">密码</label>
        <input type="password" id="inputPassword" class="form-control" name="pass" placeholder="密码" required>
        <div class="checkbox">
          <label>
            <input type="checkbox" name="remember" value="remember-me"> 记住我
          </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">登陆</button>
      </form>
			</div>
		</div>
	</div>
		
    <script src="<%=staticPath%>/js/jquery/jquery-3.3.1.min.js" ></script>
    <script src="<%=staticPath%>/js/bootstrap/bootstrap.min.js" ></script>
    <script type="text/javascript">
    	window.onload = function(){
    		var msg = '${msg}';
    		if (msg != null && msg != "" && msg != undefined) {
				alert(msg);
			}
    	}
    </script>
</body>

</html>
