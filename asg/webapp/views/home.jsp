
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<div class="container-fluid bg-3 text-center">
	<h3>Hot list video</h3>
	<br>
	
		<div class="row">
			<c:forEach var="video" items="${ list }">
				<div class="col-sm-4">
					<div class="panel panel-danger">
						<div class="panel-heading" name="titile">${ video.titile }</div>
						<div class="panel-body">
							<img src="${video.poster }" class="img-responsive"
								style="width: 100%" height="auto" alt="id">
						</div>
						<div class="panel-footer">

						<form action="/asg/Home?videoId=${video.id }" method="Post">
							<button class="glyphicon glyphicon-thumbs-up btn-lg btn-info" type="submit"  ></button>
							<a class="glyphicon glyphicon-share btn-lg btn-info" href="/asg/user/share?videoId=${video.id }" onclick="btnAlert()" ></a>
						</form>
						
						</div>
					</div>
				</div>

			</c:forEach>
		</div>
	
	<div>
		<ul class="pager">
			<li><a href="/asg/Home?page=${page - 1}">Previous</a></li>
			<li><a href="/asg/Home?page=${page + 1}">Next</a></li>
		</ul>
	</div>
</div>
<br>


		
	<script type="text/javascript">
		if(${auth==null }){
			function btnAlert() {
				alert("please login");
			}
		}

	</script>