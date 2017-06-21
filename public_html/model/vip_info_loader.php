<?php
	require_once(PATH_BDD.BDD);
	$bdd = new bdd($MYSQL_host, $MYSQL_dbname, $MYSQL_user, $MYSQL_password);
	$idVIP=$_POST['idVIP'];
	$idPartner=$_POST['idPartner'];
	$roleCode=$_POST['roleCode'];
	
	$info_loading_query =
	"SELECT idVIP, surname1VIP, surname2VIP, nameVIP, birthdateVIP, birthplaceVIP, roleCodeVIP
	FROM VIP
	WHERE idVIP=".$idVIP.";";
	$bdd->queryArray($info_loading_query);
	$vip = $bdd->_data;
	
	$nationalities_loading_query =
	"SELECT country
	FROM nationality
	WHERE idVIP=".$idVIP.";";
	$bdd->queryArray($nationalities_loading_query);
	$nationalities = $bdd->_data;
	
	if($roleCode=='A' || $roleCode=='B') {
		$movies_played_query =
		"SELECT movieTitle, releaseYear, movie.movieVisa
		FROM movie, casting
		WHERE casting.idVIP=".$idVIP." AND casting.movieVisa=movie.movieVisa;";
		$bdd->queryArray($movies_played_query);
		$played_movies = $bdd->_data;
	}
	
	if($roleCode=='D' || $roleCode=='B') {
		$movies_directed_query =
		"SELECT movieTitle, releaseYear, movie.movieVisa
		FROM movie, directing
		WHERE directing.idVIP=".$idVIP." AND directing.movieVisa=movie.movieVisa;";
		$bdd->queryArray($movies_directed_query);
		$directed_movies = $bdd->_data;
	}	
	
	if($idPartner!=-1) {
		$partner_loading_query =
		"SELECT surname1VIP, nameVIP
		FROM VIP
		WHERE idVIP=".$idPartner.";";
		$bdd->queryArray($partner_loading_query);
		$partner = $bdd->_data;
	}
?>