<?php
	
	//deconnexion au site
	$base::logout($identifiant);
	header("location:index.php?message=deconnexion_reussie");
	
?>