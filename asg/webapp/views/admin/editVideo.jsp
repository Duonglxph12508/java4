
	<div class="row centered-form">
		<div
			class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
			
				<div class="panel-heading">
					<h3 class="panel-title text-center">Video</h3>
				</div>
				<div class="panel-body">
					<form role="form" method="POST" action="/asg/admin/video/edit?id=${ video.id }">
						<div class="form-group">
							<input type="text" name="titile" id="titile"
								class="form-control input-sm" placeholder="titile" value="${video.titile}" required>
						</div>

						<div class="form-group">
							<input type="text" name="poster" id="poster"
								class="form-control input-sm" placeholder="poster" value="${video.poster}" required>
						</div>
						
						<div class="form-group">
							<input type="text" name="views" id="views"
								class="form-control input-sm" placeholder="views" value="${video.views}" required>
						</div>
						
						<div class="form-group">
							<input type="text" name="description" id="description"
								class="form-control input-sm" placeholder="description"value="${video.description}" required>
						</div>

						<div class="form-group">
							<select class="form-control input-sm" id="active" name="active" >
								<option ${ video.active == 0 ? "selected" : "" } value="0">Active</option>
								<option ${ video.active == 1 ? "selected" : "" } value="1">In active</option>

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