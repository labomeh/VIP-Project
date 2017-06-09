<?php
	require_once(PATH_BDD.BDD);
	$bdd = new bdd($MYSQL_host, $MYSQL_dbname, $MYSQL_user, $MYSQL_password);
	$image_number=$_POST['number'];
	$image_ordre=$_POST['order'];
	$image_title=$_POST['title'];
	$image_description=$_POST['description'];
	$slider_elements_updating_query =
	'UPDATE IMAGE SET ordre = ? WHERE numImage = ?;
	UPDATE IMAGE_DESCRIPTION SET titreImage = ? WHERE numImage = ?;
	UPDATE IMAGE_DESCRIPTION SET description = ? WHERE numImage = ?;';
	$query_parameters=[$image_ordre, $image_number, $image_title, $image_number, $image_description, $image_number];
	$bdd->queryArray($slider_elements_updating_query, $query_parameters);
?>