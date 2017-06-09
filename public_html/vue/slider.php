<?php
	require_once(PATH_VUE.HEADER);
	include(PATH_VUE.ALERT);
?>
	<div class="slideshow-container">
		
		<?php
			foreach($images as $image) {
				echo '
				<div class="mySlides fade">
					<div class="numbertext">1 / 3</div>
					<img src="'.PATH_IMAGE_SLIDER.$image[2].'" style="width:100%">
					<div class="text">Caption Text</div>
				</div>';
			}
		?>

	<a class="prev" onclick="plusSlides(-1)">&#10094;</a>
	<a class="next" onclick="plusSlides(1)">&#10095;</a>
	</div>

	<div style="text-align:center">
		<span class="dot" onclick="currentSlide(1)"></span>
		<span class="dot" onclick="currentSlide(2)"></span>
		<span class="dot" onclick="currentSlide(3)"></span>
	</div>
<?php
	require_once(PATH_VUE.FOOTER);
?>
<script src="script/slider.js"></script>