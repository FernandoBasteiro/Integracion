<jsp:include page="../includes/header.jsp"/>
<main role="main">
	<div class="container">
		<div class="row">
			<div class="col col-xs-12 text-right">
				<% if(request.getParameter("id") == null){ %>
				<h2 class="d-inline float-left">Crear empleado</h2>
				<% }else{ %>
				<h2 class="d-inline float-left">Editar empleado</h2>
				<% } %>
				<a href="/Web/empleados/index.jsp" class="btn btn-secondary"><i class="fas fa-chevron-left mr-2"></i>Volver al listado</a>
				<a href="/Web/empleados/index.jsp" class="btn btn-primary"><i class="fas fa-save mr-2"></i>Guardar</a>
				<hr/>
			</div>
		</div>
		<form action="?" method="post" enctype="multipart/form-data">
			<div class="form-row">
				<div class="form-group col-sm-6">
					<label for="legajo">Legajo</label>
					<input class="form-control" name="legajo" type="text" value="607104" disabled/>
				</div>
				<div class="form-group col-sm-6">
					<label for="estadoEmpleado">Estado</label>
					<select name="estadoEmpleado" class="form-control">
						<option value="activo">Activo</option>
						<option value="licencia_paga">Licencia Paga</option>
						<option value="licencia_no_paga">Licencia No Paga</option>
						<option value="desvinculado">Desvinculado</option>
					</select>
				</div>
				<div class="form-group col-sm-12">
					<h3 class="mb-3 mt-4">Datos Personales</h3>
				</div>
				<div class="form-group col-sm-6">
					<label for="nombreEmpleado">Nombre</label>
					<input type="text" name="nombreEmpleado" class="form-control" value=""/>
				</div>
				<div class="form-group col-sm-6">
					<label for="apellidoEmpleado">Apellido</label>
					<input type="text" name="apellidoEmpleado" class="form-control" value=""/>
				</div>
				<div class="form-group col-sm-6">
					<label for="dniEmpleado">DNI</label>
					<input type="text" name="dniEmpleado" class="form-control" value=""/>
				</div>
				<div class="form-group col-sm-6">
					<label for="nacionalidadEmpleado">Nacionalidad</label>
					<input type="text" name="nacionalidadEmpleado" class="form-control" value=""/>
				</div>
				<div class="form-group col-sm-6">
					<label for="domicilioEmpleado">Domiciliio</label>
					<input type="text" name="domicilioEmpleado" class="form-control" value=""/>
				</div>	
				<div class="form-group col-sm-6">
					<label for="emailEmpleado">Correo electrónico</label>
					<input type="email" name="emailEmpleado" class="form-control" value=""/>
				</div>
				<div class="form-group col-sm-6">
					<label for="telefonoEmpleado">Teléfono</label>
					<input type="text" name="telefonoEmpleado" class="form-control" value=""/>
				</div>
				<div class="form-group col-sm-6">
					<label for="estadoCivilEmpleado">Estado Civil</label>
					<select name="estadoCivilEmpleado" class="form-control">
						<option value="soltero">Soltero</option>
						<option value="en_concubinato">En concubinato</option>
						<option value="casado">Casado</option>
						<option value="divorciado">Divorciado</option>
						<option value="viudo">Viudo</option>
					</select>
				</div>
				<div class="form-group col-sm-6">
					<label for="generoEmpleado">Género</label>
					<select name="generoEmpleado" class="form-control">
						<option value="femenino">Femenino</option>
						<option value="masculino">Masculino</option>
						<option value="no_binario">No binario</option>
					</select>
				</div>				
				<div class="form-group col-sm-6">
					<label for="fechaNacimientoEmpleado">Fecha de nacimiento</label>
					<input type="date" name="fechaNacimientoEmpleado" class="form-control" value=""/>
				</div>	
				<div class="form-group col-sm-12">
					<h3 class="mb-3 mt-4">Datos Laborales</h3>
				</div>
				<div class="form-group col-sm-12">
					<p><strong class="mr-2">Puesto:</strong><span class="badge badge-pill badge-dark">Gerente</span></p>
				</div>
				<div class="form-group col-sm-6">
					<label for="fechaIngresoEmpleado">Fecha de ingreso</label>
					<input type="date" name="fechaIngresoEmpleado" class="form-control" value=""/>
				</div>
				<div class="form-group col-sm-6">
					<label for="fechaEgresoEmpleado">Fecha de egreso</label>
					<input type="date" name="fechaEgresoEmpleado" class="form-control" value=""/>
				</div>
				<div class="form-group col-sm-6">
					<label for="horasEmpleado">Horas asignadas</label>
					<input type="number" name="horasEmpleado" min="0" class="form-control" value=""/>
				</div>
				<div class="form-group col-sm-6">
					<label for="sueldoEmpleado">Sueldo base</label>
					<input type="number" name="sueldoEmpleado" min="0" class="form-control" value=""/>
				</div>
				<div class="form-group col-sm-6">
					<label for="cbuEmpleado">C.B.U.</label>
					<input type="number" name="cbuEmpleado" min="0" class="form-control" value=""/>
				</div>
				
			</div>
		</form>
		</div>
		<hr/>
	</div><!-- container -->
</main>
<jsp:include page="../includes/footer.jsp"/>