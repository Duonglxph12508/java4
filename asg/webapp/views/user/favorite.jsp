
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<div class="container-fluid bg-3 text-center">
	<h3>Video like</h3>
	<br>
	
	<div class="row">
		<c:if test="${auth!=null }">
		<c:forEach var="video" items="${ videoFr }">
			
				<div class="col-sm-4">
					<div class="panel panel-danger"">
						<div class="panel-heading" name="titile">${ video.titile } </div>
						<div class="panel-body">  
							<img src="${video.poster }" class="img-responsive"
								style="width: 100%" height="auto" alt="id">
						</div>
						<div class="panel-footer">
							<form action="/asg/user/favorite/delete?idVideoUL=${video.id }" method="post">
								<button class="btn btn-danger">Unlike</button>
								
							</form>
						</div>
					</div>
				</div>
			
		</c:forEach>
		</c:if>
	</div>

	<div>
		<ul class="pager">
			<li><a href="/asg//user/favorite?page=${page - 1}">Previous</a></li>
			<li><a href="/asg//user/favorite?page=${page + 1}">Next</a></li>
		</ul>
	</div>
</div>
