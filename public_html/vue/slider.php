	<div class="container">
	<?php if($photos==null){echo '<div class="container"><p>No photos to show for '.$vip[0][1]." ".$vip[0][3].'</p></div>';} else {
		echo '<div class="slideshow-container">';		
			foreach($photos as $photo) {
				echo '
				<div class="mySlides text-center">
					<img src="'.PATH_IMAGE.$photo[0].'" style="max-height:800px;">
				</div>';
			}
			echo '<a class="prev" onclick="plusSlides(-1)" style="background:darkgray;">&#10094;</a>
			<a class="next" onclick="plusSlides(1)" style="background:darkgray;">&#10095;</a>
			</div>';
		}
		?>

	</div>
	<script src="script/slider.js"></script>
	
<?php
	require_once(PATH_VUE.FOOTER);
?>