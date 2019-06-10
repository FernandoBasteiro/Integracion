<jsp:include page="includes/header.jsp"/>
<main role="main">
	<div class="container">
		<form class="form-signin" action="administrar.jsp">
		  <img class="mb-4" src="/docs/4.3/assets/brand/bootstrap-solid.svg" alt="" width="72" height="72">
		  <h1 class="h3 mb-3 font-weight-normal">Iniciar sesión</h1>
		  <label for="legajo" class="sr-only">Legajo</label>
		  <input type=text id="legajo" class="form-control" placeholder="Legajo" required autofocus>
		  <label for="password" class="sr-only">Contraseña</label>
		  <input type="password" id="password" class="form-control" placeholder="Contraseña" required>
		  <button class="btn btn-lg btn-primary btn-block" type="submit">Ingresar</button>
		</form>
		<hr/>
	</div><!-- container -->
</main>
<jsp:include page="includes/footer.jsp"/>