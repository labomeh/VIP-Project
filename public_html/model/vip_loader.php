<?php
	require_once(PATH_BDD.BDD);
	$bdd = new bdd($MYSQL_host, $MYSQL_dbname, $MYSQL_user, $MYSQL_password);
    if(!isset($_GET['search'])){
	   $movie_loading_query =
	   "SELECT idVIP, surname1VIP, nameVIP, birthdateVIP, birthplaceVIP, roleCodeVIP, idPartner
	   FROM VIP
	   ORDER BY nameVIP ASC ";
    }
    else{
        $movie_loading_query =
	   "SELECT idVIP, surname1VIP, nameVIP, birthdateVIP, birthplaceVIP, roleCodeVIP, idPartner
	   FROM VIP
       WHERE surname1VIP LIKE '%".$_GET['search']."%' OR surname2VIP LIKE '%".$_GET['search']."%' OR nameVIP LIKE '%".$_GET['search']."%'
	   ORDER BY nameVIP ASC ";
    }
	$bdd->queryArray($movie_loading_query);
	$vip = $bdd->_data;
?>