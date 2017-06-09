<?php
	
	// Initialisation des paramètres du site
	require_once('defines/structure.php');
	require_once(PATH_LANGUES.PATH_FR.'textes.php');
	require_once(PATH_DEFINES.'configuration.php');
	
	//démarrage de la classe base
	require_once(PATH_LIB.'base.php');
	$base = new base();
	
	if(isset($_GET['page']) && $base->isAlpha($_GET['page'])!=false && is_file(PATH_CONTROLLER.$_GET['page'].'.php'))	{
		$page = $_GET['page'];
	} else if(!isset($_GET['page'])) {
		$page = 'index';
	} else {
		$page = 'erreur';
	}
	
	// Appel du controller
	require_once(PATH_CONTROLLER.$page.'.php');
	
?>