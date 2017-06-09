<?php
	include(PATH_VUE.HEADER);
	include(PATH_VUE.ALERT);
?>

	<!-- Container (Grid) -->
	<div class="container-fluid bg-3 text-center">

		<h3 class="margin">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</h3>
		
		<div class="row">
			<div class="col-sm-4">
				<p>Lorem ipsum dolor sit amet</p>
				<img src="<?php echo PATH_IMAGE.'loremipsum.jpg'; ?>" alt="Lorem ipsum dolor sit amet" class="img-responsive margin" style="width:100%" alt="Image">
			</div>
			<div class="col-sm-4"> 
				<p>Lorem ipsum dolor sit amet</p>
				<img src="<?php echo PATH_IMAGE.'loremipsum.jpg'; ?>" alt="Lorem ipsum dolor sit amet" class="img-responsive margin" style="width:100%" alt="Image">
			</div>
			<div class="col-sm-4"> 
				<p>Lorem ipsum dolor sit amet</p>
				<img src="<?php echo PATH_IMAGE.'loremipsum.jpg'; ?>" alt="Lorem ipsum dolor sit amet" class="img-responsive margin" style="width:100%" alt="Image">
			</div>
		</div>
	</div>

<?php	
	include(PATH_VUE.FOOTER);
?>