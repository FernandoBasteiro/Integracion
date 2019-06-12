<%@ page import="dto.EmpleadoDTO"%>
<%@ page import="enumeraciones.Puesto"%>
<jsp:include page="includes/header.jsp" />
<main role="main"> <%
 	EmpleadoDTO empleado = (EmpleadoDTO) session.getAttribute("loggedUsr");
 	if (empleado == null) {
 %>
<div class="container">
	<form method="post" class="form-signin text-center"
		action="/Web/Private">
		<input type="hidden" name="action" value="login" /> <img class="mb-4"
			src="/Web/img/logo_transparent.png" alt="Logo Sarasa IAPP"
			width="150" height="114">
		<h1 class="h3 mb-3 font-weight-normal">Iniciar sesi�n</h1>
		<div class="form-group">
			<label for="legajo" class="sr-only">Legajo</label> <input type=text
				name="legajo" id="legajo" class="form-control" placeholder="Legajo"
				required autofocus>
		</div>
		<div class="form-group">
			<label for="password" class="sr-only">Contrase�a</label> <input
				type="password" name="password" id="password" class="form-control"
				placeholder="Contrase�a" required>
		</div>
		<button class="btn btn-lg btn-primary btn-block" type="submit">Ingresar</button>
	</form>
	<hr />
</div>
<!-- container --> <%
 	} else {
 %>
<div class="jumbotron">
	<div class="container">
		<h1 class="display-3">
			Bienvenid@
			<%=empleado.getNombre()%>!
		</h1>
		<p>Es un gran honor tenerte en nuestro equipo �Que tengas un excelente d�a!</p>
	</div>
</div>
<div class="container">
	<div class="row">
		<div class="col-md-3">
			<h2><i class="fas fa-cash-register mr-3 text-success"></i>Vender</h2>
			<p>Un m�dulo para dar soporte a nuestros cajer@s en sus trabajos diarios. Permite buscar productos existentes en stock, abonar en efectivo y calcular el vuelto para el cliente, o con tarjeta de cr�dito o d�bito.</p>
			<% if (empleado.getPuesto().getId() >= Puesto.CAJERO.getId()) { %>
			<p>
				<a class="btn btn-success" href="/Web/facturacion/vender.jsp" role="button">Comenzar
					&raquo;</a>
			</p>
			<%} %>
		</div>
		<div class="col-md-3">
			<h2><i class="fas fa-users mr-3 text-primary"></i>Personas</h2>
			<p>Dise�ado para nuestros Gerentes. Permite administrar el personal de la empresa y visualizar la informaci�n de los empleados, facilitando el proceso de alta, modificaci�n y/o baja de los mismos.</p>
			<% if (empleado.getPuesto().getId() >= Puesto.GERENTE.getId()) {%>
			<p>
				<a class="btn btn-primary" href="/Web/Private?action=listarEmpleados" role="button">Administrar
					&raquo;</a>
			</p>
			<%}%>
		</div>
		<div class="col-md-3">
			<h2><i class="fas fa-boxes mr-3 text-warning"></i>Productos</h2>
			<p>Si sos Supervisor o Gerente pod�s administrar todos nuestros productos desde este m�dulo. Permite visualizar y gestionar el stock, indicando la cantidad m�nima, total y disponible de cada producto.</p>
			<% 
	    	if (empleado.getPuesto().getId() >= Puesto.SUPERVISOR.getId()) {
	    	%>
			<p>
				<a class="btn btn-warning" href="/Web/Private?action=listarProductos" role="button">Administrar
					&raquo;</a>
			</p>
			<%}%>
		</div>
		<div class="col-md-3">
			<h2><i class="fas fa-file-invoice-dollar mr-3 text-info"></i>Facturaci�n</h2>
			<p>Acced� a toda la informaci�n de ventas y facturaci�n de nuestra empresa a trav�s de este m�dulo para Gerentes que te permitir� consultar las facturas emitidas, verificar sus estados e imputar sus cobros.</p>
			<% 
	    	if (empleado.getPuesto().getId() >= Puesto.GERENTE.getId()) {
	    	%>
			<p>
				<a class="btn btn-info" href="/Web/facturacion/index.jsp" role="button">Ingresar
					&raquo;</a>
			</p>
			<%}%>
		</div>
	</div>
	<hr>
</div>
<!-- /container --> <%
 	}
 %> </main>
<jsp:include page="includes/footer.jsp" />