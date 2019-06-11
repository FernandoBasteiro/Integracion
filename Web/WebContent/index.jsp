<jsp:include page="includes/header.jsp"/>
<main role="main">
	<div class="container">
		<form class="form-signin text-center" action="administrar.jsp">
		  <img class="mb-4" src="/Web/img/logo_transparent.png" alt="Logo Sarasa IAPP" width="150" height="114">
		  <h1 class="h3 mb-3 font-weight-normal">Iniciar sesión</h1>
		  <div class="form-group">
		  	<label for="legajo" class="sr-only">Legajo</label>
		  	<input type=text id="legajo" class="form-control" placeholder="Legajo" required autofocus>
		  </div>
		  <div class="form-group">
		  	<label for="password" class="sr-only">Contraseña</label>
		  	<input type="password" id="password" class="form-control" placeholder="Contraseña" required>
		  </div>
		  <button class="btn btn-lg btn-primary btn-block" type="submit">Ingresar</button>
		</form>
		<hr/>
	</div><!-- container -->
</main>
<jsp:include page="includes/footer.jsp"/>