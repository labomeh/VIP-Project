<?php

// fonction d'affichage des alertes
function alert($classeAlert, $messageAlert) {
	echo '<div class="alert alert-'.$classeAlert.'"><strong>'.$messageAlert.'</strong></div>';
}

// vérification et traitement du message reçu
if(isset($_GET['message'])) {
	
	// on récupère le message nettoyé
	$message = $base->isMessage($_GET['message']);
	
	// on définit la classe et le message à afficher et on l'affiche
	switch($message) {
		// génériques
			case "cle_securité_invalide":
				alert("danger", MESSAGE_CLE_SECURITE_INVALIDE);
				break;
			case "url_invalide":
				alert("danger", MESSAGE_URL_INVALIDE);
				break;	
		
		// connexion et déconnexion
			case "connexion_reussie":
				if(isset($_SESSION['logged']) && $_SESSION['logged'] == true) alert("success", MESSAGE_CONNECTE);
				break;
			case "deconnexion_reussie":
				if(isset($_SESSION['logged']) && $_SESSION['logged'] == false) alert("success", MESSAGE_DECONNECTE);
				break;
			case "format_identifiant_invalide":
				alert("danger", MESSAGE_FORMAT_IDENTIFIANT_INVALIDE);
				break;
			case "identifiant_invalide":
				alert("danger", MESSAGE_IDENTIFIANT_INCORRECT);
				break;
			case "format_password_invalide":
				alert("danger", MESSAGE_FORMAT_PASSWORD_INVALIDE);
				break;
			case "password_invalide":
				alert("danger", MESSAGE_PASSWORD_INCORRECT);
				break;
				
		// pour la modification du slider		
			case "format_image_invalide":
				alert("danger", MESSAGE_FORMAT_IMAGE);
				break;
			
			case "selection_image":
				alert("danger", MESSAGE_SELECTION_IMAGE);
				break;
			
			case "format_donnees_image_invalides":
				alert("danger", MESSAGE_DONNEES_IMAGE);
				break;
				
			default:
				echo 'aucune alerte détectée';
	}
}

?>