<?php
	include(PATH_VUE.HEADER);
	include(PATH_VUE.ALERT);
?>

<!-- Container (Grid) -->
<div class="container-fluid bg-3">

	<h1 class="margin">MOVIES List</h1>
	<form method="get" action="index.php" class="form-inline">
		<input type="hidden" name="page" value="movies">
		<div class="form-group">
      		<label for="search">Search a visa or a title</label>
      		<input id="search" class="form-control" name="search" type="text">
    	</div>
    	<button type="submit" class="btn btn-default">Submit</button>
	</form>
	<br>
	<div class="row">
		<div class="col-sm-12">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>Title</th>
						<th>Release Year</th>
						<th>Visa</th>
					</tr>
				</thead>
				<tbody>
				<?php
				foreach ($movies as $movie) {
					echo '
					<tr id="'.$movie[2].'">
						<td>'.$movie[0].'</td>
						<td>'.$movie[1].'</td>
						<td>'.$movie[2].'</td>
					</tr>';
				}
				?>
				</tbody>
			</table>
		</div>
	</div>

</div>

<?php	
	include(PATH_VUE.FOOTER);
?>