<%@ page import="dto.EmpleadoDTO"%>
<%@ page import="dto.ProductoDTO"%>
<%@ page import="enumeraciones.EstadoEmpleado"%>
<%@ page import="enumeraciones.Puesto"%>
<%@ page import="java.util.ArrayList"%>
<% EmpleadoDTO empleado = (EmpleadoDTO) session.getAttribute("loggedUsr");
if (empleado == null) response.sendRedirect("/Web/index.jsp");
else {
ArrayList<ProductoDTO> productos = (ArrayList<ProductoDTO>) request.getAttribute("productos");
%>
<jsp:include page="../includes/header.jsp"/>
<main role="main">
	<div class="container">
		<div class="row">
			<div class="col col-xs-12">
				<h2 class="d-inline"><i class="fas fa-boxes mr-3 text-warning"></i>Administrar Productos</h2>
				<a href="/Web/productos/crearProducto.jsp" class="btn btn-primary float-right"><i class="fas fa-plus mr-2"></i>Nuevo producto</a>
				<hr/>
			</div>
		</div>
		<div class="row">
			<div class="col col-xs-12 menu-filtro">
					<div class="form-row pb-2">
						<div class="col-sm-6">
						<form action="/Web/Private?action=listarProductos" method="post">
							<div class="input-group">
								<div class="input-group-prepend">
						          <div class="input-group-text">Buscar</div>
						      </div>
							  <input name="buscarProductoNombre" type="search" class="form-control" placeholder="Ingrese nombre del producto..." aria-label="Ingrese nombre del producto" aria-describedby="buscarProductoNombre">
							   <div class="input-group-append">
							    <button class="btn btn-secondary" type="submit"><i class="fas fa-search"></i></button>
							  </div>
							</div>
							</form>
						</div>	
						<div class="col-sm-6">
							<form action="/Web/Private?action=listarProductos" method="post">
							<div class="input-group">
							<div class="input-group-prepend">
						          <div class="input-group-text">Buscar</div>
						      </div>
							  <input name="buscarProductoCodigo" type="search" class="form-control" placeholder="Ingrese código..." aria-label="Ingrese código" aria-describedby="buscarProductoCodigo">
							   <div class="input-group-append">
							    <button class="btn btn-secondary" type="submit"><i class="fas fa-search"></i></button>
							  </div>
							</div>
							</form>
						</div>	
					</div>
				<hr/>
			</div>
		</div>
		<div class="row">
			<div class="col col-xs-12">
				<table class="table table-striped">
				  <thead>
				    <tr>
				      <th scope="col">#</th>
				      <th scope="col">Código</th>
				      <th scope="col">Nombre</th>
				      <th scope="col">Descripción</th>
				      <th scope="col">Presentación</th>
				      <th scope="col">Precio</th>
				      <th scope="col">Stock</th>
				      <th scope="col" class="text-center">Acciones</th>
				    </tr>
				  </thead>
				  <tbody>
				  <%
				  	int fila = 1;
				  	for (ProductoDTO p : productos) {
				  	%>
				    <tr>
				      <th scope="row"><%=fila++ %></th>
				       <td><%=p.getCodigo() %></td>
				       <td><%=p.getNombre() %></td>
				       <td><%=p.getDescripcion() %></td>
				       <td><%=p.getPresentacion() %></td>
				       <td><%=p.getPrecio() %></td>
				       <td><%=p.getStock().getCantidadDisponible()+"u" %></td>
				     
				      <td class="actions text-center">
				      	<a href="/Web/Private?action=verProducto&codigo=<%=p.getCodigo() %>" class="view mx-1" title="Ver producto"><i class="fas fa-eye text-success"></i></a>
				      	<a href="/Web/Private?action=verProducto&modificar=true&codigo=<%=p.getCodigo() %>" class="edit mx-1" title="Editar producto"><i class="fas fa-pencil-alt text-primary"></i></a>
				      	<a href="/Web/productos/index.jsp?action=eliminarProducto&codigo=<%=p.getCodigo() %>" class="delete mx-1" title="Eliminar producto" data-toggle="modal" data-target="#modal"><i class="fas fa-trash text-danger"></i></a>
				      </td>
				    </tr>      
				      <% } %>
				  </tbody>
				</table>
			</div>
		</div>
		<hr/>
	</div><!-- container -->
</main>
<jsp:include page="../includes/footer.jsp"/>
     <% } %>