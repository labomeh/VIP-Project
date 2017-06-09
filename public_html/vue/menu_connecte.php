			
			<ul class="nav navbar-nav navbar-right">
				<form class="navbar-form navbar-left" name="formulaireLogout" id="formulaireLogout" method="post" action="index.php?page=logout">
					<!--<input type="hidden" class="form-control" name="url" value="<?php echo $page; ?>">-->
					<label type="text" class="form-control" name="identifiant">Bonjour <?php echo $_SESSION['user']; ?></label>
					<button type="submit" class="btn btn-default"><?php echo BOUTON_DECONNEXION; ?></button>
				</form>
			</ul>
		</div>
	</div>
</nav>