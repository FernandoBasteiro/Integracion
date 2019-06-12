<%@ page import="dto.EmpleadoDTO"%>
<%@ page import="enumeraciones.EstadoEmpleado"%>
<%@ page import="enumeraciones.Puesto"%>
<%@ page import="java.util.ArrayList"%>
<% EmpleadoDTO empleado = (EmpleadoDTO) session.getAttribute("loggedUsr");
if (empleado == null) response.sendRedirect("/Web/index.jsp");
else {
ArrayList<EmpleadoDTO> empleados = (ArrayList<EmpleadoDTO>) request.getAttribute("empleados");
%>
<jsp:include page="../includes/header.jsp"/>
<main role="main">
	<div class="container">
		<div class="row">
			<div class="col col-xs-12">
				<h2 class="d-inline"><i class="fas fa-users mr-3 text-primary"></i>Administrar Empleados</h2>
				<a href="/Web/empleados/crearEmpleado.jsp" class="btn btn-primary float-right"><i class="fas fa-plus mr-2"></i>Nuevo empleado</a>
				<hr/>
			</div>
		</div>
		<div class="row">
			<div class="col col-xs-12 menu-filtro">
				<form method="post" action="?" class="filter-tools">
					<div class="form-row pb-2">
						<div class="col-sm-6">
							<div class="input-group">
								<div class="input-group-prepend">
						          <div class="input-group-text">Buscar</div>
						      </div>
							  <input name="buscarEmpleadoLegajo" type="search" class="form-control" placeholder="Ingrese legajo..." aria-label="Ingrese legajo" aria-describedby="buscarEmpleadoLegajo">
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
							  <input name="buscarEmpleadoDni" type="search" class="form-control" placeholder="Ingrese dni..." aria-label="Ingrese dni" aria-describedby="buscarEmpleadoDni">
							   <div class="input-group-append">
							    <button class="btn btn-secondary" type="button"><i class="fas fa-search"></i></button>
							  </div>
							</div>
						</div>	
					</div>
					<div class="form-row">
						
							<div class="form-group col-sm-5">
							    <select class="form-control" id="estadoEmpleado" name="estadoEmpleado">
							      <option selected>- Estado -</option>
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
							      <option selected>- Puesto -</option>
							      <%
									for (Puesto puesto : Puesto.values()) {
									%>
										<option value=<%=puesto.getId() %>><%=puesto.getNombre() %></option>
									<%
									}
									%>
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
				      <th scope="col">Estado</th>
				      <th scope="col" class="text-center">Acciones</th>
				    </tr>
				  </thead>
				  <tbody>
				  <%
				  	int fila = 1;
				  	for (EmpleadoDTO e : empleados) {
				  %>
				    <tr>
				      <th scope="row"><%=fila++ %></th>
				      <td><%=e.getLegajo() %></td>
				      <td><%=e.getApellido() %></td>
				      <td><%=e.getNombre() %></td>
				      <td><%=e.getDni() %></td>
				      <td><%=e.getPuesto().getNombre() %></td>
				      <td><span class="badge badge-pill badge-success"><%=e.getEstadoEmpleado().getNombre() %></span></td>
				      <td class="actions text-center">
				      	<a href="/Web/Private?action=verEmpleado&legajo=<%=e.getLegajo() %>" class="view mx-1" title="Ver empleado"><i class="fas fa-eye text-success"></i></a>
				      	<a href="/Web/Private?action=verEmpleado&modificar=true&legajo=<%=e.getLegajo() %>" class="edit mx-1" title="Editar empleado"><i class="fas fa-pencil-alt text-primary"></i></a>
				      	<a href="/Web/empleados/index.jsp?action=eliminarEmpleado&legajo=<%=e.getLegajo() %>" class="delete mx-1" title="Eliminar empleado" data-toggle="modal" data-target="#modal"><i class="fas fa-trash text-danger"></i></a>
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