<?php
	require_once(PATH_BDD.BDD);
	$bdd = new bdd($MYSQL_host, $MYSQL_dbname, $MYSQL_user, $MYSQL_password);
	
	// récupération du numéro de l'image à supprimer
	$image_number=$_POST['number'];
	
	// création et exécution de la requête
	$slider_elements_deleting_query =
	'DELETE FROM IMAGE WHERE numImage='.$image_number.';
	DELETE FROM IMAGE_DESCRIPTION WHERE numImage='.$image_number.';';
	$bdd->queryArray($slider_elements_deleting_query);
?>