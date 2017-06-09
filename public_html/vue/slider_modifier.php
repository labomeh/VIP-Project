<?php
	require_once(PATH_VUE.HEADER);
	include(PATH_VUE.ALERT);
?>

	<div class="table-responsive">
		<table class="table">
			<tr>
				<th>Miniature</th>
				<th>Ordre</th>
				<th>Nom du fichier</th>
				<th>Titre de l'image</th>
				<th>Description</th>
				<th></th>
				<th></th>
			</tr>			
			
			<?php
			foreach($images as $image) {
				echo '
				<form action="?page=slider_updater" method="post">
					<input type="hidden" name="number" value="'.$image[0].'">
					<tr>
						<td><img src="'.PATH_IMAGE_SLIDER.$image[2].'" style="width:100px"></td>
						<td><input name="order" value="'.$image[1].'" class="form-control"></td>
						<td>'.$image[2].'</td>
						<td><textarea name="title" class="form-control">'.$image[3].'</textarea></td>
						<td><textarea name="description" class="form-control">'.$image[4].'</textarea></td>
						<td><button type="submit" name="modify" value="modify" class="btn btn-default">Modifier</button></td>
						<td><button type="submit" name="delete" value="delete" class="btn btn-default">Supprimer</button></td>
					</tr>
				</form>';
			}
			?>
			
		</table>
	</div>
	
	<form action="?page=slider_image_uploader" method="post">
		<div class="form-group" id="upload_image_div">
			<label>Ajouter une image:</label>
			<input name="file_name" type="file" class="form-control">
			<label>Titre de l'image : <input name="image_title" class="form-control"></label>
			<label>Description de l'image : <textarea name="image_description" class="form-control">Ajouter une description</textarea></label>
			<button type="submit" class="btn btn-default">Envoyer</button>
		</div>
	</form>	

<?php
	require_once(PATH_VUE.FOOTER);
?>