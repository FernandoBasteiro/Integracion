<%@ page import="dto.EmpleadoDTO"%>
<%@ page import="dto.NovedadDTO"%>
<%@ page import="enumeraciones.EstadoEmpleado"%>
<%@ page import="java.time.format.DateTimeFormatter"%>
<% 
EmpleadoDTO empleado = (EmpleadoDTO) session.getAttribute("loggedUsr");
if (empleado == null) response.sendRedirect("/index.jsp");
EmpleadoDTO emp = (EmpleadoDTO) request.getAttribute("fichaEmpleado");
%>
<jsp:include page="../includes/header.jsp"/>
<main role="main">
	<div class="container">
		<div class="row">
			<div class="col col-xs-12 text-right">
				<h2 class="d-inline float-left"><i class="fas fa-user mr-3 text-primary"></i>Ver Empleado</h2>
				<a href="/Private?action=listarEmpleados" class="btn btn-secondary"><i class="fas fa-chevron-left mr-2"></i>Volver al listado</a>				
				<% if(emp.getEstadoEmpleado() == EstadoEmpleado.ACTIVO){%>
					<a href="#" data-legajo="<%=emp.getLegajo()%>" data-nombre="<%=emp.getNombre()%>" data-apellido="<%=emp.getApellido()%>" data-action="cargarNovedad"  class="btn btn-info" data-toggle="modal" data-target="#modal"><i class="fas fa-umbrella-beach mr-2"></i>Cargar Novedad</a>
				<% }%>
				<a href="/Private?action=verEmpleado&modificar=true&legajo=<%=emp.getLegajo()%>" class="btn btn-primary"><i class="fas fa-edit mr-2"></i>Editar</a>
				<hr/>
			</div>
		</div>
		<div class="row">
			<div class="col col-xs-6">
				<p><strong class="mr-2">Legajo:</strong><span class="badge badge-pill badge-info"><%=emp.getLegajo()%></span></p>
			</div>
			<div class="col col-xs-6">
			<% 
			String statusBadge = "";
			switch(emp.getEstadoEmpleado()){
				case ACTIVO:
					statusBadge = "badge-success";
					break;
				case DESVINCULADO:
					statusBadge = "badge-danger";
					break;
				case ANULADO:
					statusBadge = "badge-danger";
					break;
				default:
					statusBadge = "badge-info";
					break;
			}
			%>
				<p><strong class="mr-2">Estado:</strong><span class="badge badge-pill <%=statusBadge%>"><%=emp.getEstadoEmpleado().getNombre()%></span></p>
			</div>
		</div>
		<h3 class="mb-3 mt-4">Datos Personales</h3>
		<div class="row">
			<div class="col col-xs-6">
				<p><strong class="mr-2">Nombre:</strong><%=emp.getNombre()%></p>
			</div>
			<div class="col col-xs-6">
				<p><strong class="mr-2">Apellido:</strong><%=emp.getApellido()%></p>
			</div>
		</div>
		<div class="row">
			<div class="col col-xs-6">
				<p><strong class="mr-2">CUIL:</strong><%=emp.getDni()%></p>
			</div>
			<div class="col col-xs-6">
				<p><strong class="mr-2">Nacionalidad:</strong><%=emp.getNacionalidad()%></p>
			</div>
		</div>
		<div class="row">
			<div class="col col-xs-12">
				<p><strong class="mr-2">Domicilio:</strong><%=emp.getDomicilio()%></p>
			</div>	
		</div>		
		<div class="row">
			<div class="col col-md-12 col-lg-6">
				<p><strong class="mr-2">Correo electrónico:</strong><a href="mailto:mail@example.com"><%=emp.getEmail()%></a></p>
			</div>
			<div class="col col-md-12 col-lg-6">
				<p><strong class="mr-2">Teléfono:</strong><%=emp.getTelefono()%></p>
			</div>
		</div>
		<div class="row">
			<div class="col col-xs-6">
				<p><strong class="mr-2">Estado civil:</strong><%=emp.getEstadoCivil().getNombre()%></p>
			</div>
			<div class="col col-xs-6">
				<p><strong class="mr-2">Género:</strong><%=emp.getGenero().getNombre()%></p>
			</div>				
		</div>
		<div class="row">
			<div class="col col-xs-12">
				<p><strong class="mr-2">Fecha de nacimiento:</strong><%=emp.getFechaNacimiento().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))%></p>
			</div>	
		</div>
		<h3 class="mb-3 mt-4">Datos Laborales</h3>
		<div class="row">
			<div class="col col-xs-12">
				<p><strong class="mr-2">Puesto:</strong><span class="badge badge-pill badge-dark"><%=emp.getPuesto().getNombre()%></span></p>
			</div>
		</div>
		<div class="row">
			<div class="col col-xs-6">
				<p><strong class="mr-2">Fecha de ingreso:</strong><%=emp.getFechaIngreso().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))%></p>
			</div>
			<div class="col col-xs-6">
				<p><strong class="mr-2">Fecha de egreso:</strong><%=(emp.getFechaEgreso()==null) ? "-" : emp.getFechaEgreso().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))%></p>
			</div>
		</div>
		<div class="row">
			<div class="col col-xs-6">
				<p><strong class="mr-2">Horas asignadas:</strong><%=emp.getHorasAsignadas()%> hs</p>
			</div>
			<div class="col col-xs-6">
				<p><strong class="mr-2">Sueldo base:</strong>$<%=emp.getSueldoBase()%></p>
			</div>
		</div>
		<div class="row">
			<div class="col col-xs-12">
				<p><strong class="mr-2">CBU:</strong><%=emp.getCbu()%></p>
			</div>
		</div>
		<% if(emp.getNovedades().size() > 0){ 
		int listaNov = 1;
		%>
		<h3 class="mb-3 mt-4">Novedades</h3>
		<div class="row">
			<div class="col col-xs-12">
				<table class="table">
				  <thead>
				    <tr>
				      <th scope="col">#</th>
				      <th scope="col">ID</th>
				      <th scope="col">Fecha</th>
				      <th scope="col">Paga</th>
				      <th scope="col">Cant. Días</th>
				      <th scope="col">Mes</th>
				      <th scope="col">Año</th>
				    </tr>
				  </thead>
				  <tbody>
				  <% for(NovedadDTO novedad : emp.getNovedades()){ %>
				    <tr>
				      <th scope="row"><%=listaNov%></th>
				      <td><%=novedad.getId()%></td>
				      <td><%=novedad.getFechaCreacion()%></td>
				      <td><%=(novedad.getEsPaga())?"Si":"No"%></td>
				      <td><%=novedad.getCantDias() %></td>
				      <td><%=novedad.getMes()%></td>
				      <td><%=novedad.getAnio()%></td>
				    </tr>
				    <% 
				  listaNov++;  
				  } %>
				  </tbody>
				</table>
			</div>
		</div>
		<%} %>
		<hr/>
	</div><!-- container -->
</main>
<jsp:include page="../includes/footer.jsp"/>