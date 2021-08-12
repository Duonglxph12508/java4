
	<div class="row centered-form">
		<div
			class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
			
				<div class="panel-heading">
					<h3 class="panel-title text-center">Share video</h3>
				</div>
				<div class="panel-body">
					<form role="form" method="POST" action="/asg//user/share?videoId=${video.id }">
						<div class="form-group">
							<input type="text" name="email" id="email"
								class="form-control input-sm" placeholder="Enter email">
						</div>
						<input type="submit" value="Send"
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