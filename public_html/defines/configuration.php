<?php
	define('NOM_SITE','VIP World');
	define('INDEX', 'index.php');
	define('LOGO', 'logo.jpg');
	define('HEADER', 'header.php');
	define('FOOTER', 'footer.php');
	define('MENU', 'menu.php');
	define('MENU_CONNECTE', 'menu_connecte.php');
	define('ALERT', 'alert.php');
	define('SLIDER', 'slider.php');
	define('BDD', 'bdd.php');
	define('VIP_LOADER', 'vip_loader.php');
	$user = "p1523150";
	$salt_key = "coucou";
	$crypt_password = crypt("Subverse2015", $salt_key);
	
	// Acces base de données
	$MYSQL_host="localhost";
	$MYSQL_dbname="p1523150";
	$MYSQL_user="p1523150";
	$MYSQL_password="260673";
	
?>