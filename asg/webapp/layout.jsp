<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Home</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style>
/* Remove the navbar's default margin-bottom and rounded borders */
.navbar {
	margin-bottom: 0;
	border-radius: 0;
}

/* Add a gray background color and some padding to the footer */
footer {
	background-color: #f2f2f2;
	padding: 25px;
}
</style>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Portfolio</a>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
					<li class="active"><a href="/asg/Home">Home</a></li>
					<li><a href="/asg/user/favorite">My Favorite</a></li>
					<li><a href="/asg/user/showShare">My Share</a></li>
					<c:if test="${auth.role==1 }">
						<li><a href="/asg/admin/video">Update video</a></li>
						<li><a href="/asg/admin/user">Update User</a></li>
					</c:if>
					
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<c:if test="${auth==null }">
						<li><a href="/asg/Login"><span
								class="glyphicon glyphicon-log-in"></span> Login</a></li>
					</c:if>
					<c:if test="${auth!=null }">
						<li><a>Hello, ${auth.fullName}</a></li>
						<li><a href="/asg/Logout"><span
								class="glyphicon glyphicon-log-in"></span> Logout</a></li>
					</c:if>

				</ul>
			</div>
		</div>
	</nav>



	<jsp:include page="${ view }" />


	<footer class="container-fluid text-center">
		<p>Duonglxph12508</p>
	</footer>
</body>
</html>