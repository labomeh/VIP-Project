<?php
	include(PATH_VUE.HEADER);
	include(PATH_VUE.ALERT);
?>

<!-- Container (Grid) -->
<div class="container-fluid bg-3">

	<h1 class="margin">MOVIES List</h1>
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
				<tbody>
				<?php
				foreach ($movies as $movie) {
					echo '
					<tr id="'.$movie[2].'" onclick="myFunction(this)">
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
	
	<script type="text/javascript">
	function myFunction(Obj) {
	    document.getElementById("selectedMovie").innerHTML = "Selected movie's Visa: "+Obj.id;
	}
	</script>


</div>

<?php	
	include(PATH_VUE.FOOTER);
?>