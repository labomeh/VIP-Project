<!DOCTYPE html>
<html lang="en">
<head>
	<!-- Theme Made By www.w3schools.com - No Copyright -->
	<title>Simon Pasquereau</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
	<link rel="stylesheet" type="text/css" href="<?php echo PATH_CSS; ?>css.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="<?php echo PATH_SCRIPT; ?>vip_script.js"></script>
</head>

	<body>
	<!--En-tête -->
		<header class="header">
			<section class="container">
				<div class="row">
					<div class="col-md-2 col-sm-2 col-xs-12">
						<img src="<?php echo PATH_IMAGE.LOGO; ?>" alt="<?php echo LOGO ?>" class="img-circle" style="width:100%">
					</div>
					<div class="col-md-10 col-sm_10 col-xs-12">
						<h1><?php echo NOM_SITE; ?></h1>
					</div>
				</div>
			</section>
		</header>
		
	<!--Menu-->
		<nav class="navbar navbar-default">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>                        
					</button>
				</div>
				<div class="collapse navbar-collapse" id="myNavbar">
					<ul class="nav navbar-nav navbar-left">
						<li><a href="index.php?page=vip">VIP</a></li>
						<li><a href="index.php?page=movies">MOVIES</a></li>
					</ul>
	
	<!--Vue-->
		<section class="container">
			<div class="row">