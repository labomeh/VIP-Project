<?php
require_once (PATH_VUE . HEADER);
include (PATH_VUE . ALERT);
?>

<div class="container">
	<div class="container">
	<h1><?php echo $vip[0][1]." ".$vip[0][3]?></h1>
	<p>Born the <?php echo $vip[0][4].", ".$vip[0][5]?></p>
	<p>Nationalities: <?php foreach($nationalities as $nationality){
		echo $nationality[0].', ';
	}
	echo '.';
	?>
	</p>
	<?php if(isset($partner)) {
		echo '<p>Wed with: '.$partner[0][0].' '.$partner[0][1].'</p>';
	}?>
	</div>
	<br>
	
	<div class="container">
	<?php if(isset($played_movies)) {
		echo
		'<h1>Movies played:</h1>
		<div class="row">
			<div class="col-sm-8">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Title</th>
							<th>Release Year</th>
							<th>Visa</th>
						</tr>
					</thead>
					<tbody>';
				foreach($played_movies as $played_movie) {
					echo '
					<tr>
						<td>'.$played_movie[0].'</td>
						<td>'.$played_movie[1].'</td>
						<td>'.$played_movie[2].'</td>
					</tr>';
				}
				echo '</tbody></table></div></div>';
	} else {echo '<p>No played movies for '.$vip[0][1]." ".$vip[0][3].'</p>';}?>
	</div>
	<br>
	
	<div class="container">
	<?php if(isset($directed_movies)) {
		echo
		'<h1>Movies directed</h1>
		<div class="row">
			<div class="col-sm-8">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Title</th>
							<th>Release Year</th>
							<th>Visa</th>
						</tr>
					</thead>
					<tbody>';
				foreach($directed_movies as $directed_movie) {
					echo '
					<tr>
						<td>'.$directed_movie[0].'</td>
						<td>'.$directed_movie[1].'</td>
						<td>'.$directed_movie[2].'</td>
					</tr>';
				}
				
				echo '</tbody></table></div></div>';
	} else {echo '<p>No directed movies for '.$vip[0][1]." ".$vip[0][3].'</p>';}?>
	</div>
	<br>
</div>