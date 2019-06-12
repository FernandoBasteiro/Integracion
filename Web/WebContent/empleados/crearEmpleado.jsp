<%@ page import="enumeraciones.EstadoEmpleado"%>
<%@ page import="enumeraciones.EstadoCivil"%>
<%@ page import="enumeraciones.Genero"%>
<%@ page import="enumeraciones.Puesto"%>
<%@ page import="dto.EmpleadoDTO"%>
<% EmpleadoDTO empleado = (EmpleadoDTO) session.getAttribute("loggedUsr");
if (empleado == null) response.sendRedirect("/Web/index.jsp");
EmpleadoDTO emp = (EmpleadoDTO) request.getAttribute("fichaEmpleado");
%>
<jsp:include page="../includes/header.jsp"/>
<main role="main">
	<div class="container">
		<form action="/Web/Private" method="post">
			<input id="action" name="action" type="hidden" value="crearEmpleado"/>
			<div class="form-row">
				<div class="form-group col-sm-12 text-right">
					<% if(request.getParameter("id") == null){ %>
					<h2 class="d-inline float-left">Crear empleado</h2>
					<% }else{ %>
					<h2 class="d-inline float-left">Editar empleado</h2>
					<% } %>
					<a href="/Web/empleados/index.jsp" class="btn btn-secondary"><i class="fas fa-chevron-left mr-2"></i>Volver al listado</a>
					<button type="submit" class="btn btn-primary"><i class="fas fa-save mr-2"></i>Guardar</button>
					<hr/>
				</div>
			</div>
			<div class="form-row">
				<div class="form-group col-sm-6">
					<label for="legajo">Legajo</label>
					<input class="form-control" name="legajo" type="text" value="<%=(emp != null) ? emp.getLegajo() : "" %>" disabled/>
				</div>
				<div class="form-group col-sm-6">
					<label for="estadoEmpleado">Estado</label>
					<select name="estadoEmpleado" class="form-control">
						<%
						for (EstadoEmpleado estadoEmp : EstadoEmpleado.values()) {
						%>
							<option value=<%=estadoEmp.getId() %>><%=estadoEmp.getNombre() %></option>
						<%
						}
						%>
					</select>
				</div>
			</div>
			<div class="form-row">
				<div class="form-group col-sm-12">
					<h3 class="mb-3 mt-4">Datos Personales</h3>
				</div>
			</div>
			<div class="form-row">
				<div class="form-group col-sm-6">
					<label for="nombreEmpleado">Nombre</label>
					<input type="text" required name="nombreEmpleado" class="form-control" value=""/>
				</div>
				<div class="form-group col-sm-6">
					<label for="apellidoEmpleado">Apellido</label>
					<input type="text" required name="apellidoEmpleado" class="form-control" value=""/>
				</div>
			</div>
			<div class="form-row">
				<div class="form-group col-sm-6">
					<label for="dniEmpleado">DNI</label>
					<input type="text" required name="dniEmpleado" class="form-control" value=""/>
				</div>
				<div class="form-group col-sm-6">
					<label for="nacionalidadEmpleado">Nacionalidad</label>
					<input type="text" required name="nacionalidadEmpleado" class="form-control" value=""/>
				</div>
			</div>
			<div class="form-row">
				<div class="form-group col-sm-6">
					<label for="domicilioEmpleado">Domicilio</label>
					<input type="text" required name="domicilioEmpleado" class="form-control" value=""/>
				</div>	
				<div class="form-group col-sm-6">
					<label for="emailEmpleado">Correo electrónico</label>
					<input type="email" required name="emailEmpleado" class="form-control" value=""/>
				</div>
			</div>
			<div class="form-row">
				<div class="form-group col-sm-6">
					<label for="telefonoEmpleado">Teléfono</label>
					<input type="text" required name="telefonoEmpleado" class="form-control" value=""/>
				</div>
				<div class="form-group col-sm-6">
					<label for="estadoCivilEmpleado">Estado Civil</label>
					<select name="estadoCivilEmpleado" class="form-control">
						<%
						for (EstadoCivil estadoCivil : EstadoCivil.values()) {
						%>
							<option value=<%=estadoCivil.getId() %>><%=estadoCivil.getNombre() %></option>
						<%
						}
						%>
					</select>
				</div>
			</div>
			<div class="form-row">
				<div class="form-group col-sm-6">
					<label for="generoEmpleado">Género</label>
					<select name="generoEmpleado" class="form-control">
					<%
						for (Genero genero : Genero.values()) {
						%>
							<option value=<%=genero.getId() %>><%=genero.getNombre() %></option>
						<%
						}
						%>
					</select>
				</div>				
				<div class="form-group col-sm-6">
					<label for="fechaNacimientoEmpleado">Fecha de nacimiento</label>
					<input type="date" required name="fechaNacimientoEmpleado" class="form-control" value=""/>
				</div>	
			</div>
			<div class="form-row">
				<div class="form-group col-sm-12">
					<h3 class="mb-3 mt-4">Datos Laborales</h3>
				</div>
			</div>
			<div class="form-row">
				<div class="form-group col-sm-6">
					<label for="puestoEmpleado">Puesto</label>
					<select name="puestoEmpleado" class="form-control">
						<%
						for (Puesto puesto : Puesto.values()) {
						%>
							<option value=<%=puesto.getId() %>><%=puesto.getNombre() %></option>
						<%
						}
						%>
					</select>				
				</div>
				<div class="form-group col-sm-6">
					<label for="horasEmpleado">Horas asignadas</label>
					<input type="number" required name="horasEmpleado" min="0" class="form-control" value=""/>
				</div>
			</div>
			<div class="form-row">
				<div class="form-group col-sm-6">
					<label for="fechaIngresoEmpleado">Fecha de ingreso</label>
					<input type="date" required name="fechaIngresoEmpleado" class="form-control" value=""/>
				</div>
				<div class="form-group col-sm-6">
					<label for="fechaEgresoEmpleado">Fecha de egreso</label>
					<input type="date" name="fechaEgresoEmpleado" class="form-control" value=""/>
				</div>
			</div>
			<div class="form-row">
				<div class="form-group col-sm-6">
					<label for="sueldoEmpleado">Sueldo base</label>
					<input type="number" required name="sueldoEmpleado" step="0.01" min="0" class="form-control" value=""/>
				</div>
				<div class="form-group col-sm-6">
					<label for="cbuEmpleado">C.B.U.</label>
					<input type="text" required name="cbuEmpleado" class="form-control" value=""/>
				</div>
			</div>
			<div class="form-row">
				<div class="form-group col-sm-6">
					<label for="passwordEmpleado">Contraseña</label>
					<input type="password" required placeholder="Ingrese una contraseña para el empleado..." name="passwordEmpleado" class="form-control" value=""/>
				</div>
				<div class="form-group col-sm-6">
					<label for="passwordEmpleado2">Confirmar contraseña</label>
					<input type="password" required placeholder="Repita la contraseña..." name="passwordEmpleado2" class="form-control" value=""/>
				</div>
			</div>
			<div class="form-row">
				<div class="form-group col-sm-12 text-right">
					<button class="btn btn-primary" type="submit"><i class="fas fa-save mr-2"></i>Guardar</button>
				</div>
			</div>
		</form>
		<hr/>
	</div><!-- container -->
</main>
<jsp:include page="../includes/footer.jsp"/>