
			<ul class="nav navbar-nav navbar-right">
				<form class="navbar-form navbar-left" name="formulaireLogin" id="formulaireLogin" method="post" action="index.php?page=login">
					<input type="hidden" class="form-control" name="url" value="<?php echo $page; ?>">
					<div class="form-group<?php echo (isset($_GET['message']) && $_GET['message'] == "identifiant_invalide" ? "has-error has-feedback" : ""); ?>">
						<input type="text" class="form-control" name="identifiant" placeholder="<?php echo LABEL_IDENTIFIANT; ?>">
					</div>
					<div class="form-group<?php echo (isset($_GET['message']) && $_GET['message'] == "identifiant_invalide" ? "has-error has-feedback" : ""); ?>">
						<input type="password" class="form-control" name="password" placeholder="<?php echo LABEL_PASSWORD; ?>">
					</div>
					<button type="submit" class="btn btn-default"><?php echo BOUTON_CONNEXION;?></button>
				</form>
			</ul>
		
		</div>
	</div>
</nav>