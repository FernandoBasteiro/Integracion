<jsp:include page="../includes/header.jsp"/>
<main role="main">
	<div class="container">
		<div class="row">
			<div class="col col-xs-12 text-right">
				<h2 class="d-inline float-left">Ver producto</h2>
				<a href="/Web/productos/index.jsp" class="btn btn-secondary"><i class="fas fa-chevron-left mr-2"></i>Volver al listado</a>
				<a href="/Web/productos/crearProducto.jsp?id=0" class="btn btn-primary"><i class="fas fa-edit mr-2"></i>Editar</a>
				<hr/>
			</div>
		</div>
		<div class="row">
			<div class="col col-xs-6">
				<p><strong class="mr-2">Nombre:</strong>Coca-Cola</p>
			</div>
			<div class="col col-xs-6">
				<p><strong class="mr-2">C�digo:</strong><span class="badge badge-pill badge-info">123ABC456</span></p>
			</div>
		</div>
		<div class="row">
			<div class="col col-xs-6">
				<p><strong class="mr-2">Descripci�n:</strong>Gaseosa de Cola</p>
			</div>
			<div class="col col-xs-6">
				<p><strong class="mr-2">Precio:</strong>$90.00</p>
			</div>
		</div>
		<div class="row">
			<div class="col col-xs-12">
				<p><strong class="mr-2">Presentaci�n:</strong>Botella de 2.25lt</p>
			</div>	
		</div>
		<h3 class="mb-3 mt-4">Stock</h3>		
		<div class="row">
			<div class="col col-xs-12">
				<ul class="list-group list-group-horizontal">
				  <li class="list-group-item flex-fill"><strong class="mr-2">Disponible:</strong><span class="badge badge-pill badge-success">100</span></li>
				  <li class="list-group-item flex-fill"><strong class="mr-2">M�nimo</strong><span class="badge badge-pill badge-info">50</span></li>
				  <li class="list-group-item flex-fill"><strong class="mr-2">Total:</strong><span class="badge badge-pill badge-info">50</span></li>
				</ul>
			</div>
		</div>
		<hr/>
	</div><!-- container -->
</main>
<jsp:include page="../includes/footer.jsp"/>