<?php
	include(PATH_VUE.HEADER);
	include(PATH_VUE.ALERT);
?>

<!-- Container (Grid) -->
<div class="container-fluid bg-3">

	<h1 class="margin">VIP Details</h1>
	<div class="row">
		<div class="col-sm-8">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>Firstname</th>
						<th>Lastname</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
				<?php
				foreach ($vip as $v_vip) {
					echo '
					<tr id="'.$v_vip[0].'" onclick="myFunction(this)">
						<td>'.$v_vip[1].'</td>
						<td>'.$v_vip[2].'</td>';
					if($v_vip[5]=="A") {
						echo '<td>Actor</td>';
					} elseif($v_vip[5]=="D") {
						echo '<td>Director</td>';
					} elseif($v_vip[5]=="B") {
						echo '<td>Actor/Director</td>';
					} else {
						echo '<td></td>';
					}
					echo '
					</tr>';
				}
				?>
				</tbody>
			</table>
		</div>
		<div class="col-sm-4">
			<div class="well">
				<p>VIP</p>
			</div>
			<div class="well">
				<p>VIP info to display</p>
				<p id="selectedVIP"></p>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
	function myFunction(Obj) {
	    document.getElementById("selectedVIP").innerHTML = "Selected VIP : "+Obj.id;
	}
	</script>


</div>

<?php
	include(PATH_VUE.FOOTER);
?>