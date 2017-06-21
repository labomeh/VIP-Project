<?php
	include(PATH_VUE.HEADER);
	include(PATH_VUE.ALERT);
?>

<!-- Container (Grid) -->
<div class="container-fluid bg-3">

	<h1 class="margin">VIP List</h1>
    <form class="form-inline" action="index.php" method="get">
        <input type="hidden" name="page" value="vip">
        <div class="form-group">
            <label for="search">Search for a vip :</label>
            <input class="form-control" type="text" name="search" id="search">
        </div>
    </form>
	<div class="row">
		<div class="col-sm-12">
            
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
					echo '<td><form method="post" action="index.php?page=vip_info">
								<input type="hidden" name="idVIP" value="'.$v_vip[0].'">
								<input type="hidden" name="idPartner" value="'.$v_vip[6].'">
								<input type="hidden" name="roleCode" value="'.$v_vip[5].'">
								<button type="submit" class="btn-info">Voir plus</button></form></td></tr>';
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