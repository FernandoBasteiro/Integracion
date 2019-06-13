<%@ page import="dto.EmpleadoDTO"%>
<%@ page import="dto.ProductoDTO"%>
<%@ page import="dto.VentaDTO"%>
<%@ page import="enumeraciones.EstadoEmpleado"%>
<%@ page import="enumeraciones.EstadoVenta"%>
<%@ page import="enumeraciones.MedioDePago"%>
<%@ page import="enumeraciones.Puesto"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.text.DecimalFormat" %>
<% EmpleadoDTO empleado = (EmpleadoDTO) session.getAttribute("loggedUsr");
if (empleado == null) response.sendRedirect("/Web/index.jsp");
else {
ArrayList<VentaDTO> ventas = (ArrayList<VentaDTO>) request.getAttribute("facturas");
%>
<jsp:include page="../includes/header.jsp"/>
<main role="main">
	<div class="container">
		<div class="row">
			<div class="col col-xs-12">
				<h2 class="d-inline"><i class="fas fa-file-invoice-dollar mr-3 text-info"></i>Facturación</h2>
				<a href="#" data-action="imputarCobros" data-toggle="modal" data-target="#modal" class="btn btn-primary float-right"><i class="fas fa-hand-holding-usd mr-2"></i>Imputar Cobros</a>
				<hr/>
			</div>
		</div>
		<div class="row">
			<div class="col col-xs-12 menu-filtro">
					<div class="form-row pb-2">
						<div class="col-sm-6">
						
						
						<form action="/Web/Private?action=listarVentas" method="post">
							<div class="input-group">
								<div class="input-group-prepend">
						          <div class="input-group-text">Buscar</div>
						      </div>
							  <input name="buscarFacturaNumero" type="search" class="form-control" placeholder="Ingrese número de factura..." aria-label="Ingrese número de factura" aria-describedby="buscarFacturaNumero">
							   <div class="input-group-append">
							    <button class="btn btn-secondary" type="submit"><i class="fas fa-search"></i></button>
							  </div>
							</div>
							</form>						
						
					</div>
						<div class="col-sm-6">
						<form action="/Web/Private?action=listarVentas" method="post">
							<div class="input-group">
								<div class="input-group-prepend">
						          <div class="input-group-text">Buscar</div>
						      </div>
							  <input name="buscarFacturaOperacion" type="search" class="form-control" placeholder="Ingrese número de operación..." aria-label="Ingrese número de operación" aria-describedby="buscarFacturaOperacion">
							   <div class="input-group-append">
							    <button class="btn btn-secondary" type="button"><i class="fas fa-search"></i></button>
							  </div>
							</div>
							</form>
						</div>
					</div>
				<form action="/Web/Private?action=listarVentas" method="post">
					
					<div class="form-row align-items-right">
						<div class="form-group col-sm-3">
							<div class="input-group">
								<div class="input-group-prepend">
					          		<div class="input-group-text">Fecha</div>
					        	</div>
						 		 <input type="date" name="fechaFactura" class="form-control" placeholder="dd/mm/aaaa" value="">
							</div>	
						 </div>			
						<div class="form-group col-sm-3">
						    <select class="form-control" id="estadoFactura" name="estadoFactura">
						      <option selected disabled>- Estado -</option>
						      <%
						for (EstadoVenta estadoVta : EstadoVenta.values()) {
						%>
							<option value="<%=estadoVta.getId() %>"><%=estadoVta.getNombre() %></option>
						<%
						}
						%>
						    </select>
						  </div>
						<div class="form-group col-sm-3">
						    <select class="form-control" id="medioPagoFactura" name="medioPagoFactura">
						      <option selected disabled>- Medio de pago -</option>
						      <%
						      for (MedioDePago mdp : MedioDePago.values()) {
								%>
									<option value="<%=mdp.getId() %>"><%=mdp.getNombre() %></option>
								<%
								}
								%>
						    </select>
						  </div>
						  <div class="form-group col-sm-3">
					    		<button class="btn btn-secondary btn-block" type="submit">Filtrar</button>
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
				      <th scope="col">Nro. Factura</th>
				      <th scope="col">Tipo</th>
				      <th scope="col">CUIT</th>
				      <th scope="col">Estado</th>
				      <th scope="col">Medio de Pago</th>
				      <th scope="col">Nro. Operación</th>
				      <th scope="col">Total</th>
				      <th scope="col" class="text-center">Acciones</th>
				    </tr>
				  </thead>
				  <tbody>
				  <%
				  	int fila = 1;
				  	DecimalFormat priceFormatter = new DecimalFormat("$#0.00");
				  	for (VentaDTO v : ventas) {
				  	
				  		String statusBadge = "";
						switch(v.getEstado()){
							case COBRADA:
								statusBadge = "badge-success";
								break;
							case FACTURADA:
								statusBadge = "badge-warning";
								break;
							case ABIERTA:
								statusBadge = "badge-info";
								break;
							case ANULADA:
								statusBadge = "badge-danger";
								break;
							default:
								statusBadge = "badge-info";
								break;
						
						}
						
				  %>

				    <tr>
				      <th scope="row"><%=fila++ %></th>
				       <td><%=v.getId() %></td>
				       <td><%=v.getTipoFact() %></td>
				       <td><%=v.getCuit() %></td>
				       <td><span class="badge badge-pill <%=statusBadge%>"><%=v.getEstado().getNombre() %></span></td>
				       <td><%=v.getMedioDePago().getNombre() %></td>
				       <td><%=(v.getNroOperacion()==null) ? "-" : v.getNroOperacion() %></td>
				       <td><%=priceFormatter.format(v.getTotal())%></td>
				      
				      <td class="actions text-center">
				      	<a href="/Web/Private?action=verFactura&factura=<%=v.getId() %>" class="view mx-1" title="Ver factura"><i class="fas fa-eye text-success"></i></a>
				      	<a href="/Web/Private?action=marcarCobrado" data-factura="<%=v.getId()%>" data-action="marcarCobrado" <%=(v.getEstado() == EstadoVenta.COBRADA || v.getEstado() == EstadoVenta.ANULADA) ? "aria-disabled='true' tabindex='-1'" : "data-toggle='modal' data-target='#modal'" %> class="edit mx-1 <%=(v.getEstado() == EstadoVenta.COBRADA || v.getEstado() == EstadoVenta.ANULADA) ? "disabled" : "" %>" title="Ingresar cobranza"><i class="fas fa-hand-holding-usd <%=(v.getEstado() == EstadoVenta.COBRADA || v.getEstado() == EstadoVenta.ANULADA) ? "text-muted" : "text-primary" %>"></i></a>
				      	<a href="/Web/Private/anularFactura" data-factura="<%=v.getId()%>" data-action="anularFactura" <%=(v.getEstado() == EstadoVenta.ANULADA) ? "aria-disabled='true' tabindex='-1'" : "data-toggle='modal' data-target='#modal'" %> class="delete mx-1 <%=(v.getEstado() == EstadoVenta.ANULADA) ? "disabled" : "" %>" title="Anular factura"><i class="fas fa-times text-danger <%=(v.getEstado() == EstadoVenta.ANULADA) ? "text-muted" : "text-primary" %>"></i></a>
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