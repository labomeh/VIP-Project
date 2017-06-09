<?php

	class bdd {
		
		public $_data;
		
		function __construct($MYSQL_host, $MYSQL_dbname, $MYSQL_user, $MYSQL_password)
		{
			try 
			{
				$this->bdd = new PDO('mysql:host='.$MYSQL_host .'; dbname='.$MYSQL_dbname , $MYSQL_user, $MYSQL_password);
			}
			catch(PDOException $ex)
			{
				die('Erreur : '.$ex->getMessage());
			}
		}
		
		function queryArray($my_query, $my_data=array())
		{
			try
			{
				$request = $this->bdd -> prepare($my_query);
				$request -> execute($my_data);
				$this -> _data = $request->fetchAll();
			}
			catch (Exception $ex)
			{
				die('Erreur : '.$ex->getMessage());
			}
		}
		
		function __destruct()
		{
			$this->bdd =null;
		}
		
	}
?>