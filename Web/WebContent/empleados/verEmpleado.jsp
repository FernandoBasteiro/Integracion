<%@ page import="dto.EmpleadoDTO"%>
<%
EmpleadoDTO empleado = (EmpleadoDTO) request.getAttribute("fichaEmpleado");
%>
<jsp:include page="../includes/header.jsp"/>
<main role="main">
	<div class="container">
		<div class="row">
			<div class="col col-xs-12 text-right">
				<h2 class="d-inline float-left">Ver empleado</h2>
				<a href="/Web/empleados/index.jsp" class="btn btn-secondary"><i class="fas fa-chevron-left mr-2"></i>Volver al listado</a>
				<a href="/Web/Private?action=crearEmpleado&legajo=<%=empleado.getLegajo()%>" class="btn btn-primary"><i class="fas fa-edit mr-2"></i>Editar</a>
				<hr/>
			</div>
		</div>
		<div class="row">
			<div class="col col-xs-6">
				<p><strong class="mr-2">Legajo:</strong><span class="badge badge-pill badge-info"><%=empleado.getLegajo()%></span></p>
			</div>
			<div class="col col-xs-6">
				<p><strong class="mr-2">Estado:</strong><span class="badge badge-pill badge-success"><%=empleado.getEstadoEmpleado()%></span></p>
			</div>
		</div>
		<h3 class="mb-3 mt-4">Datos Personales</h3>
		<div class="row">
			<div class="col col-xs-6">
				<p><strong class="mr-2">Nombre:</strong><%=empleado.getNombre()%></p>
			</div>
			<div class="col col-xs-6">
				<p><strong class="mr-2">Apellido:</strong><%=empleado.getApellido()%></p>
			</div>
		</div>
		<div class="row">
			<div class="col col-xs-6">
				<p><strong class="mr-2">DNI:</strong><%=empleado.getDni()%></p>
			</div>
			<div class="col col-xs-6">
				<p><strong class="mr-2">Nacionalidad:</strong><%=empleado.getNacionalidad()%></p>
			</div>
		</div>
		<div class="row">
			<div class="col col-xs-12">
				<p><strong class="mr-2">Domicilio:</strong><%=empleado.getDomicilio()%></p>
			</div>	
		</div>		
		<div class="row">
			<div class="col col-md-12 col-lg-6">
				<p><strong class="mr-2">Correo electrónico:</strong><a href="mailto:mail@example.com"><%=empleado.getEmail()%></a></p>
			</div>
			<div class="col col-md-12 col-lg-6">
				<p><strong class="mr-2">Teléfono:</strong><%=empleado.getTelefono()%></p>
			</div>
		</div>
		<div class="row">
			<div class="col col-xs-6">
				<p><strong class="mr-2">Estado civil:</strong><%=empleado.getEstadoCivil()%></p>
			</div>
			<div class="col col-xs-6">
				<p><strong class="mr-2">Género:</strong><%=empleado.getGenero()%></p>
			</div>				
		</div>
		<div class="row">
			<div class="col col-xs-12">
				<p><strong class="mr-2">Fecha de nacimiento:</strong><%=empleado.getFechaNacimiento()%></p>
			</div>	
		</div>
		<h3 class="mb-3 mt-4">Datos Laborales</h3>
		<div class="row">
			<div class="col col-xs-12">
				<p><strong class="mr-2">Puesto:</strong><span class="badge badge-pill badge-dark"><%=empleado.getPuesto()%></span></p>
			</div>
		</div>
		<div class="row">
			<div class="col col-xs-6">
				<p><strong class="mr-2">Fecha de ingreso:</strong><%=empleado.getFechaIngreso()%></p>
			</div>
			<div class="col col-xs-6">
				<p><strong class="mr-2">Fecha de egreso:</strong><%=empleado.getFechaEgreso() %></p>
			</div>
		</div>
		<div class="row">
			<div class="col col-xs-6">
				<p><strong class="mr-2">Horas asignadas:</strong><%=empleado.getHorasAsignadas()%> hs</p>
			</div>
			<div class="col col-xs-6">
				<p><strong class="mr-2">Sueldo base:</strong>$<%=empleado.getSueldoBase()%></p>
			</div>
		</div>
		<div class="row">
			<div class="col col-xs-12">
				<p><strong class="mr-2">CBU:</strong><%=empleado.getCbu()%></p>
			</div>
		</div>
		<hr/>
	</div><!-- container -->
</main>
<jsp:include page="../includes/footer.jsp"/>