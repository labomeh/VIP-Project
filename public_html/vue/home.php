<?php
include (PATH_VUE . HEADER);
include (PATH_VUE . ALERT);
?>

<!-- Container (Grid) -->
<div class="container-fluid bg-3">

	<div class="row">
		<div class="col-sm-6 text-center">
			<p class="text-center">
				<strong>View all the VIP</strong>
			</p>
			<br> <a href="index.php?page=vip"> <img src="<?php echo PATH_IMAGE.'vip_link.jpg'?>"
				class="img-rounded" alt="View all the VIP" width="415" height="415">
			</a>
		</div>
		<div class="col-sm-6 text-center">
			<p class="text-center">
				<strong>View all the movies</strong>
			</p>
			<br> <a href="index.php?page=movies"><img src="<?php echo PATH_IMAGE.'movie_link.jpg'?>"
				class="img-rounded" alt="Random Name" width="415" height="415"> </a>
		</div>
	</div>


</div>

<?php
include (PATH_VUE . FOOTER);
?>