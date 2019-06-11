<%@ page import="dto.ProductoDTO"%>
<%
ProductoDTO prod = (ProductoDTO) request.getAttribute("producto");
%>
<jsp:include page="../includes/header.jsp"/>
<main role="main">
	<div class="container">
		<div class="row">
			<div class="col col-xs-12 text-right">
				<h2 class="d-inline float-left">Ver producto</h2>
				<a href="/Web/productos/index.jsp" class="btn btn-secondary"><i class="fas fa-chevron-left mr-2"></i>Volver al listado</a>
				<a href="/Web/Private?action=crearProducto&codigo=<%=prod.getCodigo()%>" class="btn btn-primary"><i class="fas fa-edit mr-2"></i>Editar</a>
				<hr/>
			</div>
		</div>
		<div class="row">
			<div class="col col-xs-6">
				<p><strong class="mr-2">Nombre:</strong><%=prod.getNombre()%></p>
			</div>
			<div class="col col-xs-6">
				<p><strong class="mr-2">Código:</strong><span class="badge badge-pill badge-info"><%=prod.getCodigo()%></span></p>
			</div>
		</div>
		<div class="row">
			<div class="col col-xs-6">
				<p><strong class="mr-2">Descripción:</strong><%=prod.getDescripcion()%></p>
			</div>
			<div class="col col-xs-6">
				<p><strong class="mr-2">Precio:</strong>$<%=prod.getPrecio()%></p>
			</div>
		</div>
		<div class="row">
			<div class="col col-xs-12">
				<p><strong class="mr-2">Presentación:</strong><%=prod.getPresentacion()%></p>
			</div>	
		</div>
		<h3 class="mb-3 mt-4">Stock</h3>		
		<div class="row">
			<div class="col col-xs-12">
				<ul class="list-group list-group-horizontal">
				  <li class="list-group-item flex-fill"><strong class="mr-2">Disponible:</strong><span class="badge badge-pill badge-success"><%=prod.getStock().getCantidadDisponible()%></span></li>
				  <li class="list-group-item flex-fill"><strong class="mr-2">Mínimo</strong><span class="badge badge-pill badge-info"><%=prod.getStock().getCantidadMinimo()%></span></li>
				  <li class="list-group-item flex-fill"><strong class="mr-2">Total:</strong><span class="badge badge-pill badge-info"><%=prod.getStock().getCantidadTotal()%></span></li>
				</ul>
			</div>
		</div>
		<hr/>
	</div><!-- container -->
</main>
<jsp:include page="../includes/footer.jsp"/>