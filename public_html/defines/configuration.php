<?php
	define('NOM_SITE','Mon premier site php');
	define('INDEX', 'index.php');
	define('LOGO', 'logo.jpg');
	define('HEADER', 'header.php');
	define('FOOTER', 'footer.php');
	define('MENU', 'menu.php');
	define('MENU_CONNECTE', 'menu_connecte.php');
	define('ALERT', 'alert.php');
	define('SLIDER', 'slider.php');
	define('BDD', 'bdd.php');
	define('SLIDER_MODIFIER', 'slider_modifier.php');
	define('SLIDER_LOADER', 'slider_loader.php');
	define('SLIDER_UPDATER', 'slider_updater.php');
	define('SLIDER_IMAGE_SUPPRESSOR', 'slider_image_suppressor.php');
	define('SLIDER_IMAGE_UPLOADER', 'slider_image_uploader.php');
	$user = "p1523150";
	$salt_key = "coucou";
	$crypt_password = crypt("Subverse2015", $salt_key);
	
	// Acces base de données
	$MYSQL_host="localhost";
	$MYSQL_dbname="p1523150";
	$MYSQL_user="p1523150";
	$MYSQL_password="260673";
	
?>