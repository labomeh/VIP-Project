<?php
	require_once(PATH_BDD.BDD);
	$bdd = new bdd($MYSQL_host, $MYSQL_dbname, $MYSQL_user, $MYSQL_password);
	
	// récupération des info POST
	$file_name=$_POST['file_name'];
	$image_title=$_POST['image_title'];
	$image_description=$_POST['image_description'];
	
	// création et exécution de la requête d'insertion
	$slider_elements_uploading_query =
	'SELECT @nextOrder := (SELECT MAX(ordre) FROM IMAGE)+1;
	INSERT INTO IMAGE VALUES(numImage, @nextOrder, ?, 0, 0, ?);
	SELECT @actualNumImage := (SELECT MAX(numImage) FROM IMAGE);
	INSERT INTO IMAGE_DESCRIPTION VALUES(@actualNumImage, ?, ?, 0, 0, ?);';
	$query_parameters=[$file_name, $user, $image_title, $image_description, $user];
	$bdd->queryArray($slider_elements_uploading_query, $query_parameters);
?>