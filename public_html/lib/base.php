<?php

	class base {
		
		function __construct()
		{
			session_name('global');
			session_start();
		}
		
		function __destruct()
		{
		}
		
		public static function isMessage($string) {
			if(isset($string) && $string!='' && preg_match('/[a-z_]$/', $string)) {
				return htmlspecialchars($string);
			} else {
				return false;
			}
		}
		
		// fonctions de vérification des POST et GET
	
		public static function isAlpha($string) {
			if(isset($string) && $string!='' && !preg_match('/[!\'(){}^$?.+*\#]/', $string)) {
				return htmlspecialchars($string);
			} else {
				return false;
			}
		}
		
// 		// fonctions connexion et deconnexion
// 		public static function login($user) {
// 			$_SESSION['user'] = $user;
// 			$_SESSION['logged']=true;
// 		}
		
// 		public static function logout() {
// 			$_SESSION['user'] = null;
// 			$_SESSION['logged']=false;
// 		}
		
// 		public static function isConnected() {
// 			if(isset($_SESSION['logged']) && $_SESSION['logged']==true) {
// 				return true;
// 			}
// 			else {
// 				return false;
// 			}
// 		}
		
	}
?>