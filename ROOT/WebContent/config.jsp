<%@ page import="dto.EmpleadoDTO"%>
<%@ page import="dtoParamGralesDTO"%>
<%
EmpleadoDTO empleado = (EmpleadoDTO) session.getAttribute("loggedUsr");
if (empleado == null) response.sendRedirect("/index.jsp");
ArrayList<ParamGralesDTO> params = (ParamGralesDTO) session.getAttribute("params");
boolean isParams = (params != null) ? true : false;
%>
<jsp:include page="includes/header.jsp"/>
<main role="main">
	<div class="container">
		<form action="/Private" method="post">
			<div class="form-row">
				<div class="form-group col-sm-12 text-right">
					<h2 class="d-inline float-left"><i class="fas fa-tools mr-3 text-danger"></i>Configuración General</h2>
					<button type="submit" class="btn btn-primary"><i class="fas fa-save mr-2"></i>Guardar</button>
					<hr/>
				</div>
				<% if(isParams){ 

				%>
					
				<% } %>
			</div>
		</form>
		<hr/>
	</div><!-- container -->
</main>
<jsp:include page="includes/footer.jsp"/>