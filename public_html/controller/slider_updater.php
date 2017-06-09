<?php
	if(isset($_POST['modify']) && ($_POST['modify']=='modify')) {
		// Vérification de la validité des données
		if(!$base->isAlpha($_POST['title']) || !$base->isAlpha($_POST['description'])) {
			header("location:index.php?page=slider_modifier&message=format_donnees_image_invalides");
		} else {
			require_once(PATH_MODEL.SLIDER_UPDATER);
		}
	}
	if(isset($_POST['delete']) && ($_POST['delete']=='delete')) {
		require_once(PATH_MODEL.SLIDER_IMAGE_SUPPRESSOR);
	}
	
	// on charge à nouveau la page de modification
	require_once(PATH_CONTROLLER.SLIDER_MODIFIER);
?>