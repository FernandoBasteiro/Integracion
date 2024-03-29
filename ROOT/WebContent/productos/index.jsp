<%@ page import="dto.EmpleadoDTO"%>
<%@ page import="dto.ProductoDTO"%>
<%@ page import="enumeraciones.EstadoEmpleado"%>
<%@ page import="enumeraciones.Puesto"%>
<%@page import="java.text.DecimalFormat" %>
<%@ page import="java.util.ArrayList"%>
<% EmpleadoDTO empleado = (EmpleadoDTO) session.getAttribute("loggedUsr");
if (empleado == null) response.sendRedirect("/index.jsp");
else {
ArrayList<ProductoDTO> productos = (ArrayList<ProductoDTO>) request.getAttribute("productos");
%>
<jsp:include page="../includes/header.jsp"/>
<main role="main">
	<div class="container">
		<div class="row">
			<div class="col col-xs-12">
				<h2 class="d-inline"><i class="fas fa-boxes mr-3 text-warning"></i>Administrar Productos</h2>
				<a href="/productos/crearProducto.jsp" class="btn btn-primary float-right"><i class="fas fa-plus mr-2"></i>Nuevo producto</a>
				<hr/>
			</div>
		</div>
		<div class="row">
			<div class="col col-xs-12 menu-filtro">
					<div class="form-row pb-2">
						<div class="col-sm-5">
						<form action="/Private?action=listarProductos" method="post">
							<div class="input-group">
								<div class="input-group-prepend">
						          <div class="input-group-text">Buscar</div>
						      </div>
							  <input name="buscarProductoNombre" type="search" class="form-control" required placeholder="Ingrese nombre del producto..." aria-label="Ingrese nombre del producto" aria-describedby="buscarProductoNombre">
							   <div class="input-group-append">
							    <button class="btn btn-secondary" type="submit"><i class="fas fa-search"></i></button>
							  </div>
							</div>
							</form>
						</div>	
						<div class="col-sm-5">
							<form action="/Private?action=listarProductos" method="post">
							<div class="input-group">
							<div class="input-group-prepend">
						          <div class="input-group-text">Buscar</div>
						      </div>
							  <input name="buscarProductoCodigo" type="search" required class="form-control" placeholder="Ingrese c�digo..." aria-label="Ingrese c�digo" aria-describedby="buscarProductoCodigo">
							   <div class="input-group-append">
							    <button class="btn btn-secondary" type="submit"><i class="fas fa-search"></i></button>
							  </div>
							</div>
							</form>
						</div>	
						<div class="form-group col-sm-2">
							<a href="/Private?action=listarProductos" title="Limpiar Filtros" class="btn btn-outline-secondary btn-block" role="button"><i class="fas fa-eraser mr-2"></i>Limpiar</a>
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
				      <th scope="col">C�digo</th>
				      <th scope="col">Nombre</th>
				      <th scope="col">Descripci�n</th>
				      <th scope="col">Presentaci�n</th>
				      <th scope="col">Precio</th>
				      <th scope="col" class="text-center">Stock Disponible</th>
				      <th scope="col"  class="text-center" style="min-width:110px;">Acciones</th>
				    </tr>
				  </thead>
				  <tbody>
				  <%
				  	int fila = 1;
				  	DecimalFormat priceFormatter = new DecimalFormat("$#0.00");

				  	for (ProductoDTO p : productos) {
				  		
				  		String badgeDisponible = "";
						if(p.getStock().getCantidadDisponible() < p.getStock().getCantidadMinimo() || p.getStock().getCantidadDisponible() == 0){
							badgeDisponible = "danger";
						}else if(p.getStock().getCantidadDisponible() > p.getStock().getCantidadTotal()){
							badgeDisponible ="warning";
						}else{
							badgeDisponible = "success";
						}
				  	%>
				    <tr class="<%=(p.getStock().getCantidadDisponible() == 0) ? "text-muted" : "" %>">
				      <th scope="row"><%=fila++ %></th>
				       <td><%=p.getCodigoStr() %></td>
				       <td><%=p.getNombre() %></td>
				       <td><%=p.getDescripcion() %></td>
				       <td><%=p.getPresentacion() %></td>
				       <td><%=priceFormatter.format(p.getPrecio())%></td>
				       <td class="text-center"><span class="badge badge-pill badge-<%=badgeDisponible%>"><%=p.getStock().getCantidadDisponible()+"u" %></span></td>
				     
				      <td class="actions text-center">
				      	<a href="/Private?action=verProducto&codigo=<%=p.getCodigo() %>" class="view mx-1" title="Ver producto"><i class="fas fa-eye text-success"></i></a>
				      	<a href="/Private?action=verProducto&modificar=true&codigo=<%=p.getCodigo() %>" class="edit mx-1" title="Editar producto"><i class="fas fa-pencil-alt text-primary"></i></a>
				      	<a href="#" data-action="eliminarProducto" data-codigo="<%=p.getCodigo()%>" data-nombre="<%=p.getNombre()%>" class="delete mx-1 <%=(p.getStock().getCantidadDisponible() == 0) ? "disabled" : ""%>" title="Eliminar producto" <%=(p.getStock().getCantidadDisponible() == 0) ? "aria-disabled='true' tabindex='-1'" : "data-toggle='modal' data-target='#modal'"%>><i class="fas fa-trash <%=(p.getStock().getCantidadDisponible() == 0) ? "text-muted" : "text-danger"%>"></i></a>
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