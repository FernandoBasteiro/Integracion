<%@ page import="dto.EmpleadoDTO"%>
<%@ page import="enumeraciones.EstadoEmpleado"%>
<%@ page import="enumeraciones.Puesto"%>
<%@ page import="java.util.ArrayList"%>
<% EmpleadoDTO empleado = (EmpleadoDTO) session.getAttribute("loggedUsr");
if (empleado == null) response.sendRedirect("/index.jsp");
else {
ArrayList<EmpleadoDTO> empleados = (ArrayList<EmpleadoDTO>) request.getAttribute("empleados");
%>
<jsp:include page="../includes/header.jsp"/>
<main role="main">
	<div class="container">
		<div class="row">
			<div class="col col-xs-12">
				<h2 class="d-inline"><i class="fas fa-users mr-3 text-primary"></i>Administrar Empleados</h2>
				<a href="/empleados/crearEmpleado.jsp" class="btn btn-primary float-right"><i class="fas fa-plus mr-2"></i>Nuevo empleado</a>
				<hr/>
			</div>
		</div>
		<div class="row">
			<div class="col col-xs-12 menu-filtro">
				
					<div class="form-row pb-2">
						<div class="col-sm-6">
							<form method="post" action="/Private?action=listarEmpleados">
								<div class="input-group">
									<div class="input-group-prepend">
							          <div class="input-group-text">Buscar</div>
							      </div>
								  <input name="buscarEmpleadoLegajo" type="search" required class="form-control" placeholder="Ingrese legajo..." aria-label="Ingrese legajo" aria-describedby="buscarEmpleadoLegajo">
								   <div class="input-group-append">
								    <button class="btn btn-secondary" type="submit"><i class="fas fa-search"></i></button>
								  </div>
								</div>
							</form>
						</div>	
						<div class="col-sm-6">
							<form method="post" action="/Private?action=listarEmpleados">
								<div class="input-group">
									<div class="input-group-prepend">
								          <div class="input-group-text">Buscar</div>
								      </div>
									  <input name="buscarEmpleadoDni" type="search" class="form-control" pattern="[\d]{11}" title="El CUIL debe contener exactamente 11 números, sin espacios ni guiones." required placeholder="Ingrese CUIL..." aria-label="Ingrese CUIL" aria-describedby="buscarEmpleadoDni">
									   <div class="input-group-append">
									    <button class="btn btn-secondary" type="submit"><i class="fas fa-search"></i></button>
									  </div>
								</div>
							</form>
						</div>	
					</div>
					
						<form method="post" action="/Private?action=listarEmpleados">
						<div class="form-row">
							<div class="form-group col-sm-5">
							    <select class="form-control" id="estadoEmpleado" name="estadoEmpleado">
							      <option disabled selected>- Estado -</option>
							      <%
									for (EstadoEmpleado estadoEmp : EstadoEmpleado.values()) {
									%>
										<option value=<%=estadoEmp.getId() %>><%=estadoEmp.getNombre() %></option>
									<%
									}
									%>
							    </select>
							 </div>			
							<div class="form-group col-sm-5">
							    <select class="form-control" id="puestoEmpleado" name="puestoEmpleado">
							      <option disabled selected>- Puesto -</option>
							      <%
									for (Puesto puesto : Puesto.values()) {
									%>
										<option value=<%=puesto.getId() %>><%=puesto.getNombre() %></option>
									<%
									}
									%>
							    </select>
							  </div>
							  <div class="form-group col-sm-1">
						    		<button class="btn btn-secondary btn-block" type="submit">Filtrar</button>
							  </div>
							  <div class="form-group col-sm-1">
						    		<a href="/Private?action=listarEmpleados" title="Limpiar Filtros" class="btn btn-outline-secondary btn-block" role="button"><i class="fas fa-eraser"></i></a>
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
				      <th scope="col">CUIL</th>
				      <th scope="col">Puesto</th>
				      <th scope="col">Estado</th>
				      <th scope="col" class="text-center">Acciones</th>
				    </tr>
				  </thead>
				  <tbody>
				  <%
				  	int fila = 1;
				  	for (EmpleadoDTO e : empleados) {
				  	
				  		String statusBadge = "";
						switch(e.getEstadoEmpleado()){
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
				    <tr>
				      <th scope="row"><%=fila++ %></th>
				      <td><%=e.getLegajo() %></td>
				      <td><%=e.getApellido() %></td>
				      <td><%=e.getNombre() %></td>
				      <td><%=e.getDni() %></td>
				      <td><%=e.getPuesto().getNombre() %></td>
				      <td><span class="badge badge-pill <%=statusBadge%>"><%=e.getEstadoEmpleado().getNombre() %></span></td>
				      <td class="actions text-center">
				      	<a href="/Private?action=verEmpleado&legajo=<%=e.getLegajo() %>" class="view mx-1" title="Ver empleado"><i class="fas fa-eye text-success"></i></a>
				      	<a href="/Private?action=verEmpleado&modificar=true&legajo=<%=e.getLegajo() %>" class="edit mx-1" title="Editar empleado"><i class="fas fa-pencil-alt text-primary"></i></a>
				      	<a href="#" data-legajo="<%=e.getLegajo()%>" data-nombre="<%=e.getNombre()%>" data-apellido="<%=e.getApellido()%>" data-action="eliminarEmpleado" class="delete mx-1 <%=(e.getEstadoEmpleado() == EstadoEmpleado.ANULADO) ? "disabled" : ""%>" title="Eliminar empleado" <%=(e.getEstadoEmpleado() == EstadoEmpleado.ANULADO) ? "aria-disabled='true' tabindex='-1'" : "data-toggle='modal' data-target='#modal'"%>><i class="fas fa-trash <%=(e.getEstadoEmpleado() == EstadoEmpleado.ANULADO) ? "text-muted" : "text-danger"%>"></i></a>
				      	<a href="#" data-legajo="<%=e.getLegajo()%>" data-nombre="<%=e.getNombre()%>" data-apellido="<%=e.getApellido()%>" data-action="cargarNovedad"  class="news mx-1 <%=(e.getEstadoEmpleado() == EstadoEmpleado.ANULADO || e.getEstadoEmpleado() == EstadoEmpleado.DESVINCULADO) ? "disabled" : ""%>" title="Cargar novedad" <%=(e.getEstadoEmpleado() == EstadoEmpleado.ANULADO || e.getEstadoEmpleado() == EstadoEmpleado.DESVINCULADO) ? "aria-disabled='true' tabindex='-1'" : "data-toggle='modal' data-target='#modal'"%>><i class="fas fa-umbrella-beach <%=(e.getEstadoEmpleado() == EstadoEmpleado.ANULADO || e.getEstadoEmpleado() == EstadoEmpleado.DESVINCULADO) ? "text-muted" : "text-info"%>"></i></a>
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