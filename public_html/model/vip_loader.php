<?php
	require_once(PATH_BDD.BDD);
	$bdd = new bdd($MYSQL_host, $MYSQL_dbname, $MYSQL_user, $MYSQL_password);
	$movie_loading_query =
	"SELECT idVIP, surname1VIP, nameVIP, birthdateVIP, birthplaceVIP, roleCodeVIP, idPartner
	FROM VIP
	ORDER BY nameVIP ASC ";
	$bdd->queryArray($movie_loading_query);
	$vip = $bdd->_data;
?>