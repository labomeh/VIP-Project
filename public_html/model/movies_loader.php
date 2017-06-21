<?php
	require_once(PATH_BDD.BDD);
	$bdd = new bdd($MYSQL_host, $MYSQL_dbname, $MYSQL_user, $MYSQL_password);
	$movie_loading_query =
	"SELECT movieTitle, releaseYear, movieVisa
	FROM movie
	ORDER BY movieTitle ASC ";
	$bdd->queryArray($movie_loading_query);
	$movies = $bdd->_data;
?>