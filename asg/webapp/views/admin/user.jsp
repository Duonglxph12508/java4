<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<div class="container">
		<div class="page-header">
			<h1 class="text-danger" align="center">User List</h1>
		</div>
		<a href="/asg/admin/user/edit?id=0"><button
				class="btn btn-success">create user</button></a>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>id</th>
					<th>Email</th>
					<th>Password</th>
					<th>Full name</th>
					<th>Role</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="user" items="${ list }">
					<tr>
						<td>${user.id }</td>
						<td>${user.email }</td>
						<td>${user.password }</td>
						<td>${user.fullName }</td>
						<td>${user.role==1 ? "Admin" : "User" }</td>
						<td><a href="/asg/admin/user/edit?id=${user.id }"
							class="btn btn-primary">Update</a></td>
						<td>
							<form action="/asg//admin/user/delete?id=${user.id }"
								method="POST">
								<button class="btn btn-danger">Delete</button>
							</form>

						</td>

					</tr>
				</c:forEach>
			</tbody>
		</table>
		
	      
	    </div>
		<ul class="pager">
			<li><a href="/asg/admin/user?page=${page - 1}">Previous</a></li>
			<li><a href="/asg/admin/user?page=${page + 1}">Next</a></li>
		</ul>
		
	</div>