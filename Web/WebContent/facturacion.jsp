<jsp:include page="includes/header.jsp"/>
<main role="main">
	<div class="container">
		<div class="row">
			<div class="col col-xs-12">
				<h2 class="d-inline">Administrar Productos</h2>
				<a href="#" class="btn btn-primary float-right">Nuevo producto</a>
				<hr/>
			</div>
		</div>
		<div class="row">
			<div class="col col-xs-12 menu-filtro">
				<form method="get" class="filter-tools">
					<div class="form-row">
						<div class="col-sm-12">
							<div class="input-group">
								<div class="input-group-prepend">
						          <div class="input-group-text">Buscar</div>
						        </div>
							  <input type="search" class="form-control" placeholder="Ingrese nombre o código de producto" aria-label="Ingrese nombre o código de producto" aria-describedby="buscarProducto">
							   <div class="input-group-append">
							    <button class="btn btn-secondary" type="button"><i class="fas fa-search"></i></button>
							  </div>
							</div>
						</div>				
					</div>
				</form>
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
				      <th scope="col">Acciones</th>
				    </tr>
				  </thead>
				  <tbody>
				    <tr>
				      <th scope="row">1</th>
				      <td>ABC123</td>
				      <td>Coca-Cola</td>
				      <td>Gaseosa de Cola</td>
				      <td>Botella de 2.25lt</td>
				      <td>$90.00</td>
				      <td>100u</td>
				      <td class="actions">
				      	<a href="productos.jsp?view=id" class="view mx-1" title="Ver empleado"><i class="fas fa-eye text-success"></i></a>
				      	<a href="productos.jsp?edit=id" class="edit mx-1" title="Editar empleado"><i class="fas fa-pencil-alt text-primary"></i></a>
				      	<a href="productos.jsp?delete=id" class="delete mx-1" title="Eliminar empleado" data-toggle="modal" data-target="#modal"><i class="fas fa-trash text-danger"></i></a>
				      </td>
				    </tr>
				  </tbody>
				</table>
			</div>
		</div>
		<hr/>
	</div><!-- container -->
</main>
<jsp:include page="includes/footer.jsp"/>