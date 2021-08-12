<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<div class="container">
		<div class="page-header">
			<h1 class="text-danger" align="center">Video List</h1>
		</div>
		<a href="/asg/admin/video/edit?id=0"><button
				class="btn btn-success">create video</button></a>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>id</th>
					<th>Titile</th>
					<th>Poster</th>
					<th>Description</th>
					<th>Views</th>
					<th>Active</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="video" items="${ list }">
					<tr>
						<td>${video.id }</td>
						<td>${video.titile}</td>
						<td>
							<img alt="" src="${video.poster }" width="40px" height="50px">
						</td>
						<td>${video.description}</td>
						<td>${video.views}</td>
						<td>${video.active==1 ? "In Active" : "Active" }</td>
						<td><a href="/asg/admin/video/edit?id=${video.id }"
							class="btn btn-primary">Update</a></td>
						<td>
							<form action="/asg/admin/video/delete?id=${video.id }"
								method="POST">
								<button class="btn btn-danger">Delete</button>
							</form>

						</td>

					</tr>
				</c:forEach>
			</tbody>
		</table>

		<ul class="pager">
			<li><a href="/asg/admin/video?page=${page - 1}">Previous</a></li>
			<li><a href="/asg/admin/video?page=${page + 1}">Next</a></li>
		</ul>
	</div>