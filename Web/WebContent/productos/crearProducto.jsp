<%@ page import="dto.EmpleadoDTO"%>
<%@ page import="dto.EmpleadoDTO"%>
<%@ page import="dto.ProductoDTO"%>
<%@ page import="enumeraciones.EstadoEmpleado"%>
<%@ page import="enumeraciones.Puesto"%>
<%@ page import="java.util.ArrayList"%>
<%
ProductoDTO prod = (ProductoDTO) request.getAttribute("producto"); 
EmpleadoDTO empleado = (EmpleadoDTO) session.getAttribute("loggedUsr");
if (empleado == null) response.sendRedirect("/Web/index.jsp");
%>
<jsp:include page="../includes/header.jsp"/>
<main role="main">
	<div class="container">
		<form action="/Web/Private" method="post">
			<input type="hidden" name="action" value="crearProducto"/>
			<div class="form-row">
				<div class="form-group col-sm-12 text-right">
					<% if(request.getParameter("codigo") == null){ %>
					<h2 class="d-inline float-left"><i class="fas fa-gift mr-3 text-warning"></i>Crear Producto</h2>
					<% }else{ %>
					<h2 class="d-inline float-left"><i class="fas fa-box-open mr-3 text-warning"></i>Editar Producto</h2>
					<% } %>
					<a href="/Web/Private?action=listarProductos" class="btn btn-secondary"><i class="fas fa-chevron-left mr-2"></i>Volver al listado</a>
					<button type="submit" class="btn btn-primary"><i class="fas fa-save mr-2"></i>Guardar</button>
					<hr/>
				</div>
			<div class="form-group col-sm-6">
				<label for="nombreProducto">Nombre</label>
				<input type="text" required name="nombreProducto" class="form-control" value="<%=(prod != null)?prod.getNombre():""%>"/>
			</div>
			<div class="form-group col-sm-6">
				<label for="codigoProducto">Código</label>
				<input type="number" required name="codigoProducto" class="form-control" value="<%=(prod != null)?prod.getCodigo().toString():""%>"/>
			</div>
			<div class="form-group col-sm-12">
				<label for="descripcionProducto">Descripción</label>
				<input type="text" required name="descripcionProducto" class="form-control" value="<%=(prod != null)?prod.getDescripcion():""%>"/>
			</div>
			<div class="form-group col-sm-6">
				<label for="precioProducto">Precio</label>
				<input type="number" step="0.01" required name="precioProducto" class="form-control" value="<%=(prod != null)?prod.getPrecio():""%>"/>
			</div>
			<div class="form-group col-sm-6">
				<label for="presentacionProducto">Presentación</label>
				<input type="text" required name="presentacionProducto" class="form-control" value="<%=(prod != null)?prod.getPresentacion():""%>"/>
			</div>
			<div class="form-group col-sm-12">
					<h3 class="mb-3 mt-4">Stock</h3>
			</div>	
			<div class="form-group col-sm-12">
				<ul class="list-group list-group-horizontal">
				  <li class="list-group-item flex-fill">
				  	<label for="stockDisponible">Disponible</label>
					<input type="number" required name="stockDisponible" class="form-control" value="<%=(prod != null)?prod.getStock().getCantidadDisponible():""%>"/>
				  </li>
				  <li class="list-group-item flex-fill">
				  	<label for="stockMinimo">Mínimo</label>
					<input type="number" required name="stockMinimo" class="form-control" value="<%=(prod != null)?prod.getStock().getCantidadMinimo():""%>"/>
				  </li>
				  <li class="list-group-item flex-fill">
				  	<label for="stockTotal">Total</label>
					<input type="number" required name="stockTotal" class="form-control" value="<%=(prod != null)?prod.getStock().getCantidadTotal():""%>"/>
				  </li>
				</ul>
			</div>
			<div class="form-group col-sm-12 text-right">
					<button type="submit" class="btn btn-primary"><i class="fas fa-save mr-2"></i>Guardar</button>
				</div>
			<hr/>
			</div>
		</form>
		<hr/>
	</div><!-- container -->
</main>
<jsp:include page="../includes/footer.jsp"/>