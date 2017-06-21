<?php
	require_once(PATH_BDD.BDD);
	$bdd = new bdd($MYSQL_host, $MYSQL_dbname, $MYSQL_user, $MYSQL_password);
	$idVIP=$_POST['idVIP'];
	$slider_elements_loading_query =
	"SELECT fileName, date, place
	FROM photo, shows
	WHERE photo.idPhoto=shows.idPhoto and shows.idVIP=".$idVIP.";";
	$bdd->queryArray($slider_elements_loading_query);
	$photos = $bdd->_data;
	
	$info_loading_query =
	"SELECT idVIP, surname1VIP, surname2VIP, nameVIP, birthdateVIP, birthplaceVIP, roleCodeVIP
	FROM VIP
	WHERE idVIP=".$idVIP.";";
	$bdd->queryArray($info_loading_query);
	$vip = $bdd->_data;
?>