	<div class="slideshow-container">
		
		<?php
			foreach($photos as $photo) {
				echo '
				<div class="mySlides">
					<img src="'.PATH_IMAGE.$photo[0].'" style="width:100%">
				</div>';
			}
		?>

	<a class="prev" onclick="plusSlides(-1)">&#10094;</a>
	<a class="next" onclick="plusSlides(1)">&#10095;</a>
	</div>
	<script src="script/slider.js"></script>
	
<?php
	require_once(PATH_VUE.FOOTER);
?>