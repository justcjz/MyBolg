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
<link href="<%=staticPath%>/css/bootstrap/bootstrap-table.css" rel="stylesheet">
<link rel="stylesheet" href="<%=staticPath%>/css/adminStyles.css" />

</head>

<body>
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#sidebar-collapse">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
					<a class="navbar-brand" href="#"><span>Blog</span>&nbsp;Admin</a>
					<ul class="user-menu">
						<li class="dropdown pull-right">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-user"></span> ADMIN <span class="caret"></span></a>
							<ul class="dropdown-menu" role="menu">
								<li>
									<a href="<%=path%>/admin/logout>"><span class="glyphicon glyphicon-log-out"></span>退出系统</a>
								</li>
							</ul>
						</li>
					</ul>
				</div>
			</div>
			
		</nav>
	
		
		
	</div><!--/.main-->
    <script src="../js/jquery/jquery-3.3.1.min.js" ></script>
    <script src="../js/bootstrap/bootstrap.min.js" ></script>
	<script src="../js/bootstrap/bootstrap-table.js"></script>
	<script>
		!function ($) {
			$(document).on("click","ul.nav li.parent > a > span.icon", function(){		  
				$(this).find('em:first').toggleClass("glyphicon-minus");	  
			}); 
			$(".sidebar span.icon").find('em:first').addClass("glyphicon-plus");
		}(window.jQuery);

		$(window).on('resize', function () {
		  if ($(window).width() > 768) $('#sidebar-collapse').collapse('show')
		})
		$(window).on('resize', function () {
		  if ($(window).width() <= 767) $('#sidebar-collapse').collapse('hide')
		})
	</script>	
</body>

</html>
