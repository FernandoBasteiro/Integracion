<%@ page import="dto.EmpleadoDTO"%>
<%@ page import="dto.ParamGralesDTO"%>
<%@ page import="java.util.ArrayList"%>
<%
	EmpleadoDTO empleado = (EmpleadoDTO) session.getAttribute("loggedUsr");
	if (empleado == null)
		response.sendRedirect("/index.jsp");
	ArrayList<ParamGralesDTO> params = (ArrayList<ParamGralesDTO>) request.getAttribute("params");
	boolean isParams = (params != null) ? true : false;
%>
<jsp:include page="includes/header.jsp" />
<main role="main">
<div class="container">
	<div class="row">
		<div class="col-sm-12">
			<h2 class="d-inline">
				<i class="fas fa-tools mr-3 text-danger"></i>Configuración General
			</h2>
			<hr />
		</div>
	</div>
	<%
		if (isParams) {
			for (ParamGralesDTO p : params) {
	%>
	<form method="post" action="/Private?action=editParams" class="mb-2" id="editParam-<%=p.getId()%>">
		<input type="hidden" name="params" value="<%=p.getId()%>" /> 
		<div class="row">
			<div class="col col-5">
				<div class="form-group row">
					<label class="col-sm-2 col-form-label">Clave</label>
					<input class="col-sm-10 form-control" name="params" value="<%=p.getClave()%>" />
				</div>
			</div>
			<div class="col col-5">
				<div class="form-group row">
					<label class="col-sm-2 col-form-label">Valor</label> 
					<input class="col-sm-10 form-control" name="params" value="<%=p.getValor()%>" />
				</div>
			</div>
			<div class="col col-2">
				<button class="btn btn-primary btn-block" type="submit">
					<i class="fas fa-save mr-2"></i>Grabar
				</button>
			</div>
		</div>
	</form>
	<%
		}
		}
	%>
	<hr />
</div>
<!-- container --> </main>
<jsp:include page="includes/footer.jsp" />