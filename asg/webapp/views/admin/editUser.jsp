
	<div class="row centered-form">
		<div
			class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
			
				<div class="panel-heading">
					<h3 class="panel-title text-center">Register</h3>
				</div>
				<div class="panel-body">
					<form role="form" method="POST" action="/asg/admin/user/edit?id=${ user.id }">
						<div class="form-group">
							<input type="text" name="email" id="email"
								class="form-control input-sm" placeholder="Email" value="${ user.email }">
						</div>

						<div class="form-group">
							<input type="text" name="fullName" id="fullName"
								class="form-control input-sm" placeholder="Full Name" value="${ user.fullName }">
						</div>

						<div class="form-group">
							<input type="password" name="password" id="password"
								class="form-control input-sm" placeholder="Password"value="${ user.password }">
						</div>

						<div class="form-group">
							<select class="form-control input-sm" id="role" name="role" >
								<option ${ user.role == 0 ? "selected" : "" } value="0">User</option>
								<option ${ user.role == 1 ? "selected" : "" } value="1">Admin</option>

							</select>
						</div>


						<input type="submit" value="Register"
							class="btn btn-info btn-block">
					</form>
				</div>
			</div>
		</div>
	


<style>
#container1 {
	background-color: #e2dada;
}

.centered-form {
	margin-top: 120px;
	margin-bottom: 120px;
}

.centered-form .panel {
	background: rgba(255, 255, 255, 0.8);
	box-shadow: rgba(0, 0, 0, 0.3) 20px 20px 20px;
}
</style>