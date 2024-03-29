<%@ page import="dto.EmpleadoDTO"%>
<%@ page import="dto.ProductoDTO"%>
<%
ProductoDTO prod = (ProductoDTO) request.getAttribute("producto"); 
EmpleadoDTO empleado = (EmpleadoDTO) session.getAttribute("loggedUsr");
if (empleado == null) response.sendRedirect("/index.jsp");
%>
<jsp:include page="../includes/header.jsp"/>
<main role="main">
	<div class="container">
		<div class="row">
			<div class="col col-xs-12 text-right">
				<h2 class="d-inline float-left"><i class="fas fa-box mr-3 text-warning"></i>Ver producto</h2>
				<a href="/Private?action=listarProductos" class="btn btn-secondary"><i class="fas fa-chevron-left mr-2"></i>Volver al listado</a>
				<a href="/Private?action=verProducto&modificar=true&codigo=<%=prod.getCodigoStr()%>" class="btn btn-primary"><i class="fas fa-edit mr-2"></i>Editar</a>
				<hr/>
			</div>
		</div>
		<div class="row">
			<div class="col col-xs-6">
				<p><strong class="mr-2">Nombre:</strong><%=prod.getNombre()%></p>
			</div>
			<div class="col col-xs-6">
				<p><strong class="mr-2">C�digo:</strong><span class="badge badge-pill badge-info"><%=prod.getCodigoStr()%></span></p>
			</div>
		</div>
		<div class="row">
			<div class="col col-xs-6">
				<p><strong class="mr-2">Descripci�n:</strong><%=prod.getDescripcion()%></p>
			</div>
			<div class="col col-xs-6">
				<p><strong class="mr-2">Precio:</strong>$<%=prod.getPrecio()%></p>
			</div>
		</div>
		<div class="row">
			<div class="col col-xs-12">
				<p><strong class="mr-2">Presentaci�n:</strong><%=prod.getPresentacion()%></p>
			</div>	
		</div>
		<h3 class="mb-3 mt-4">Stock</h3>		
		<div class="row">
			<div class="col col-xs-12">
				<ul class="list-group list-group-horizontal">
				<% String badgeDisponible = "";
					if(prod.getStock().getCantidadDisponible() < prod.getStock().getCantidadMinimo() || prod.getStock().getCantidadDisponible() == 0){
						badgeDisponible = "danger";
					}else if(prod.getStock().getCantidadDisponible() > prod.getStock().getCantidadTotal()){
						badgeDisponible ="warning";
					}else{
						badgeDisponible = "success";
					}%>
				  <li class="list-group-item flex-fill"><strong class="mr-2">Disponible:</strong><span class="badge badge-pill badge-<%=badgeDisponible%>"><%=prod.getStock().getCantidadDisponible()%></span></li>
				  <li class="list-group-item flex-fill"><strong class="mr-2">M�nimo</strong><span class="badge badge-pill badge-info"><%=prod.getStock().getCantidadMinimo()%></span></li>
				  <li class="list-group-item flex-fill"><strong class="mr-2">Total:</strong><span class="badge badge-pill badge-info"><%=prod.getStock().getCantidadTotal()%></span></li>
				</ul>
			</div>
		</div>
		<hr/>
	</div><!-- container -->
</main>
<jsp:include page="../includes/footer.jsp"/>