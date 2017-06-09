<?php
	if(isset($_POST['file_name']) && ($_POST['file_name']!='')) {
		// Verification de l'extension du fichier
		if(!fnmatch("*.jpg", $_POST['file_name']) && !fnmatch("*.png", $_POST['file_name'])) {
			header("location:index.php?page=slider_modifier&message=format_image_invalide");
		}
		
		// Vérification de la validité du titre et de la description
		if(!$base->isAlpha($_POST['image_title']) || !$base->isAlpha($_POST['image_description'])) {
			header("location:index.php?page=slider_modifier&message=format_donnees_image_invalides");
		}
		else {
			require_once(PATH_MODEL.SLIDER_IMAGE_UPLOADER);
		}
	} else {
		// avertissement si aucune image sélectionnée
		header("location:index.php?page=slider_modifier&message=selection_image");
	}
	// on charge à nouveau la page de modification
	require_once(PATH_CONTROLLER.SLIDER_MODIFIER);
?>