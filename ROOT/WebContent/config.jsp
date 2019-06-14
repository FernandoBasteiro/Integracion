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
		<form method="post" action="/Private?action=editParams" id="editParam-<%=p.getId()%>">
			<div class="form-row" class="col-sm-12">
				<div class="col-sm-6">
					<input type="hidden" name="params" value="<%=p.getId()%>" /> <label>Clave</label>
					<input class="form-control" name="params" value="<%=p.getClave()%>" />
				</div>
				<div class="col-sm-6">
					<label>Valor</label> 
					<input class="form-control" name="params" value="<%=p.getValor()%>" />
					<button class="btn btn-primary" type="submit"><i class="fas fa-save"></i></button>
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