<?php

	// Transformation des $_POST en variables
	foreach($_POST as $cle=>$val) {
		$$cle=$val;
	}

	echo $url;	
	
	// Vérification des variables reçues
	if(!isset($url) || !$base->isAlpha($url) || !is_file(PATH_CONTROLLER.$url.".php")) {
		//url de la page invalide
		echo $url;
		header("location:index.php?page=".$url."&message=url_invalide");
		exit();
	}
	
	//elseif (!isset($secure_key) || !$base->validate()) {
	//	// cle de securite invalide
	//	header("location:index.php?page=".$url."&message=cle_securite_invalide");
	//	exit();
	//}

	 elseif ($identifiant!=$user) {
		// identifiant invalide
		header("location:index.php?page=".$url."&message=identifiant_invalide");
		exit();
	} elseif (!isset($identifiant) || !$base->isAlpha($identifiant)) {
		// format identifiant invalide
		header("location:index.php?page=".$url."&message=format_identifiant_invalide");
		exit();
	} elseif (!isset($password) || !$base->isAlpha($password)) {
		// format password invalide
		header("location:index.php?page=".$url."&message=format_password_invalide");
		exit();
	} elseif ($crypt_password!=crypt($password, $salt_key)) {
		// password invalide
		header("location:index.php?page=".$url."&message=password_invalide");
		exit();
	} else {
		//connexion au site
		$base::login($identifiant);
		header("location:index.php?page=".$url."&message=connexion_reussie");
	}

?>