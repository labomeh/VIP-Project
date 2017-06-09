<?php
	require_once(PATH_BDD.BDD);
	$bdd = new bdd($MYSQL_host, $MYSQL_dbname, $MYSQL_user, $MYSQL_password);
	$slider_elements_loading_query =
	"SELECT I.numImage, I.ordre, I.nomFichier, D.titreImage, D.description
	FROM IMAGE I, IMAGE_DESCRIPTION D
	WHERE I.numImage=D.numImage
	ORDER BY I.ordre ASC ";
	$bdd->queryArray($slider_elements_loading_query);
	$images = $bdd->_data;
?>