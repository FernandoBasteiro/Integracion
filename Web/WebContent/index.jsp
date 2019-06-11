<jsp:include page="includes/header.jsp"/>
<main role="main">
<% boolean logged_in = false;
	if(logged_in){
%>
	<div class="container">
		<form class="form-signin text-center" action="?">
		  <img class="mb-4" src="/Web/img/logo_transparent.png" alt="Logo Sarasa IAPP" width="150" height="114">
		  <h1 class="h3 mb-3 font-weight-normal">Iniciar sesión</h1>
		  <div class="form-group">
		  	<label for="legajo" class="sr-only">Legajo</label>
		  	<input type=text name="legajo" id="legajo" class="form-control" placeholder="Legajo" required autofocus>
		  </div>
		  <div class="form-group">
		  	<label for="password" class="sr-only">Contraseña</label>
		  	<input type="password" name="password" id="password" class="form-control" placeholder="Contraseña" required>
		  </div>
		  <button class="btn btn-lg btn-primary btn-block" type="submit">Ingresar</button>
		</form>
		<hr/>
	</div><!-- container -->
	<% } else { %>
	<div class="jumbotron">
    <div class="container">
      <h1 class="display-3">Bienvenido [nombre_empleado]!</h1>
      <p>This is a template for a simple marketing or informational website. It includes a large callout called a jumbotron and three supporting pieces of content. Use it as a starting point to create something more unique.</p>
      <p><a class="btn btn-primary btn-lg" href="#" role="button">Learn more &raquo;</a></p>
    </div>
  </div>
  <div class="container">
    <div class="row">
      <div class="col-md-4">
        <h2>Heading</h2>
        <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
        <p><a class="btn btn-secondary" href="#" role="button">View details &raquo;</a></p>
      </div>
      <div class="col-md-4">
        <h2>Heading</h2>
        <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
        <p><a class="btn btn-secondary" href="#" role="button">View details &raquo;</a></p>
      </div>
      <div class="col-md-4">
        <h2>Heading</h2>
        <p>Donec sed odio dui. Cras justo odio, dapibus ac facilisis in, egestas eget quam. Vestibulum id ligula porta felis euismod semper. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus.</p>
        <p><a class="btn btn-secondary" href="#" role="button">View details &raquo;</a></p>
      </div>
    </div>
    <hr>
  </div> <!-- /container -->
  <% } %>
</main>
<jsp:include page="includes/footer.jsp"/>