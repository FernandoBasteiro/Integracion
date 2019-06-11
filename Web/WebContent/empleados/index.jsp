<jsp:include page="../includes/header.jsp"/>
<main role="main">
	<div class="container">
		<div class="row">
			<div class="col col-xs-12">
				<h2 class="d-inline">Administrar Empleados</h2>
				<a href="/Web/empleados/crearEmpleado.jsp" class="btn btn-primary float-right">Nuevo empleado</a>
				<hr/>
			</div>
		</div>
		<div class="row">
			<div class="col col-xs-12 menu-filtro">
				<form method="get" class="filter-tools">
					<div class="form-row pb-2">
						<div class="col-sm-6">
							<div class="input-group">
								<div class="input-group-prepend">
						          <div class="input-group-text">Buscar</div>
						      </div>
							  <input type="search" class="form-control" placeholder="Ingrese legajo..." aria-label="Ingrese legajo" aria-describedby="buscarEmpleadoLegajo">
							   <div class="input-group-append">
							    <button class="btn btn-secondary" type="button"><i class="fas fa-search"></i></button>
							  </div>
							</div>
						</div>	
						<div class="col-sm-6">
							<div class="input-group">
							<div class="input-group-prepend">
						          <div class="input-group-text">Buscar</div>
						      </div>
							  <input type="search" class="form-control" placeholder="Ingrese dni..." aria-label="Ingrese dni" aria-describedby="buscarEmpleadoDni">
							   <div class="input-group-append">
							    <button class="btn btn-secondary" type="button"><i class="fas fa-search"></i></button>
							  </div>
							</div>
						</div>	
					</div>
					<div class="form-row">
						
							<div class="form-group col-sm-5">
							    <select class="form-control" id="estadoEmpleado">
							      <option selected>- Estado -</option>
							      <option>Activo</option>
							      <option>Licencia Paga</option>
							      <option>Licencia No Paga</option>
							      <option>Desvinculado</option>
							      <option>Baja</option>
							    </select>
							 </div>			
							<div class="form-group col-sm-5">
							    <select class="form-control" id="puestoEmpleado">
							      <option selected>- Puesto -</option>
							      <option>Cajero</option>
							      <option>Repositor</option>
							      <option>Seguridad</option>
							      <option>Supervisor</option>
							      <option>Gerente</option>
							    </select>
							  </div>
							  <div class="form-group col-sm-2">
						    		<button class="btn btn-secondary btn-block" type="button">Filtrar</button>
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
				      <th scope="col">Legajo</th>
				      <th scope="col">Apellido</th>
				      <th scope="col">Nombre</th>
				      <th scope="col">DNI</th>
				      <th scope="col">Puesto</th>
				      <th scope="col" class="text-center">Acciones</th>
				    </tr>
				  </thead>
				  <tbody>
				    <tr>
				      <th scope="row">1</th>
				      <td>607104</td>
				      <td>Nuñez</td>
				      <td>Erica Natalia</td>
				      <td>32961625</td>
				      <td>Cajera</td>
				      <td class="actions text-center">
				      	<a href="/Web/empleados/verEmpleado.jsp?id=0" class="view mx-1" title="Ver empleado"><i class="fas fa-eye text-success"></i></a>
				      	<a href="/Web/empleados/crearEmpleado.jsp?id=0" class="edit mx-1" title="Editar empleado"><i class="fas fa-pencil-alt text-primary"></i></a>
				      	<a href="/Web/empleados/verEmpleado.jsp?id=0" class="delete mx-1" title="Eliminar empleado" data-toggle="modal" data-target="#modal"><i class="fas fa-trash text-danger"></i></a>
				      </td>
				    </tr>
				    <tr>
				      <th scope="row">2</th>
				      <td>607105</td>
				      <td>Basteiro</td>
				      <td>Fernando</td>
				      <td>33004993</td>
				      <td>Repositor</td>
				      <td class="actions text-center">
				      	<a href="/Web/empleados/verEmpleado.jsp?id=0" class="view mx-1" title="Ver empleado"><i class="fas fa-eye text-success"></i></a>
				      	<a href="/Web/empleados/crearEmpleado.jsp?id=0" class="edit mx-1" title="Editar empleado"><i class="fas fa-pencil-alt text-primary"></i></a>
				      	<a href="/Web/empleados/verEmpleado.jsp?id=0" class="delete mx-1" title="Eliminar empleado" data-toggle="modal" data-target="#modal"><i class="fas fa-trash text-danger"></i></a>
				      </td>
				    </tr>
				    <tr>
				      <th scope="row">3</th>
				      <td>608105</td>
				      <td>Fossati</td>
				      <td>Federico</td>
				      <td>30004693</td>
				      <td>Supervisor</td>
				      <td class="actions text-center">
				      	<a href="/Web/empleados/verEmpleado.jsp?id=0" class="view mx-1" title="Ver empleado"><i class="fas fa-eye text-success"></i></a>
				      	<a href="/Web/empleados/crearEmpleado.jsp?id=0" class="edit mx-1" title="Editar empleado"><i class="fas fa-pencil-alt text-primary"></i></a>
				      	<a href="/Web/empleados/verEmpleado.jsp?id=0" class="delete mx-1" title="Eliminar empleado" data-toggle="modal" data-target="#modal"><i class="fas fa-trash text-danger"></i></a>
				      </td>
				    </tr>
				  </tbody>
				</table>
			</div>
		</div>
		<hr/>
	</div><!-- container -->
</main>
<jsp:include page="../includes/footer.jsp"/>