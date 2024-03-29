<%@ page import="dto.EmpleadoDTO"%>
<%@ page import="dto.VentaDTO"%>
<%@ page import="dto.ItemVentaDTO"%>
<%@ page import="enumeraciones.EstadoVenta"%>
<%@ page import="enumeraciones.MedioDePago"%>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="java.time.format.DateTimeFormatter"%>
<% EmpleadoDTO empleado = (EmpleadoDTO) session.getAttribute("loggedUsr");
if (empleado == null) response.sendRedirect("/index.jsp");
VentaDTO factura = (VentaDTO) request.getAttribute("factura");
DecimalFormat priceFormatter = new DecimalFormat("$#0.00");
%>
<jsp:include page="../includes/header.jsp"/>
<main role="main">
	<div class="container">
		<div class="row">
			<div class="col col-xs-12 text-right">
				<h2 class="d-inline float-left"><i class="fas fa-receipt mr-3 text-info"></i>Ver factura</h2>
				<a href="/Private?action=listarVentas" class="btn btn-secondary"><i class="fas fa-chevron-left mr-2"></i>Volver al listado</a>
				<%if(factura.getEstado() != EstadoVenta.COBRADA && factura.getEstado() != EstadoVenta.ANULADA){%>
					<a href="/Private?action=marcarCobrado" data-factura="<%=factura.getId()%>" data-action="marcarCobrado" data-toggle="modal" data-target="#modal" class="btn btn-success"><i class="fas fa-hand-holding-usd mr-2"></i>Cobrar</a>
				<%} %>
				<%if(factura.getEstado() != EstadoVenta.ANULADA){%>
				<a href="/Private/anularFactura" data-factura="<%=factura.getId()%>" data-action="anularFactura" data-toggle="modal" data-target="#modal" class="btn btn-danger"><i class="fas fa-times mr-2"></i>Anular</a>
				<% } %>
				<hr/>
			</div>
		</div>
		<div class="row">
			<div class="col col-xs-6">
				<p><strong class="mr-2">Fecha de facturaci�n:</strong><%=factura.getFechaVenta().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))%></p>
			</div>
			<div class="col col-xs-6">
				<p><strong class="mr-2">N�mero:</strong><span class="badge badge-pill badge-info"><%=factura.getId()%></span></p>
			</div>
		</div>
		<div class="row">
		<% if(factura.getFechaCobro()!=null ){   %>
			<div class="col col-xs-6">
				<p><strong class="mr-2">Fecha de cobro:</strong><%=factura.getFechaCobro().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))%></p>
			</div>
			<% }else{   %>
			<div class="col col-xs-6">
				<p><strong class="mr-2">Fecha de cobro:</strong> - </p>
			</div> <%}
		
		String statusBadge = "";
		switch(factura.getEstado()){
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
			
			
			<div class="col col-xs-6">
				<p><strong class="mr-2">Estado:</strong><span class="badge badge-pill <%=statusBadge%>"><%=factura.getEstado().getNombre() %></span></p>
			</div>
		</div>
		<div class="row">
			<div class="col col-xs-6">
				<p><strong class="mr-2">Tipo de factura:</strong><span class="badge badge-pill badge-dark"><%=factura.getTipoFact()%></span></p>
			</div>
			<div class="col col-xs-6">
				<p><strong class="mr-2">CUIT:</strong><%=factura.getCuit()%></p>
			</div>
		</div>
		<div class="row">
			<div class="col col-xs-6">
				<p><strong class="mr-2">Medio de pago:</strong><%=factura.getMedioDePago().getNombre()  %></p>
			</div>
			<% if(factura.getMedioDePago().getNombre() == MedioDePago.TARJETA_DEBITO.getNombre()){ %>
			<div class="col col-xs-6">
				<p><strong class="mr-2">Tarjeta:</strong>xxxx xxxx xxxx <%=factura.getNumeroTarjeta()%></p>
			</div>
			<% } else if(factura.getMedioDePago().getNombre() == MedioDePago.TARJETA_CREDITO.getNombre()){ %>
			<div class="col col-xs-6">
				<p><strong class="mr-2">Tarjeta:</strong>xxxx xxxx xxxx <%=factura.getNumeroTarjeta()%></p>
			</div>
			<% } %>
		</div>
		<% if(factura.getMedioDePago().getNombre() == MedioDePago.TARJETA_CREDITO.getNombre()){ %>
		<div class="row">
			<div class="col col-xs-6">
				<p><strong class="mr-2">Cuotas:</strong><%=factura.getCantCuotas() %></p>
			</div>
		</div>
		<% } %>
		<div class="row">
			<div class="col col-xs-6">
				<p><strong class="mr-2">Cajero:</strong><%=factura.getEmpleado().getNombre()+" "+factura.getEmpleado().getApellido()   %></p>
			</div>
		</div>
		<div class="row">
			<div class="col col-xs-12">
				<table class="table">
				  <thead>
				    <tr>
				      <th scope="col">#</th>
				      <th scope="col">C�digo</th>
				      <th scope="col">Nombre</th>
				      <th scope="col">Presentaci�n</th>
				      <th scope="col">Precio</th>
				      <th scope="col">Cantidad</th>
				      <th scope="col">Subtotal</th>
				    </tr>
				  </thead>
				  <tbody>
				  <% 
				  int item_num = 1;
				  for (ItemVentaDTO item : factura.getItems()){ 
				  String subtotal = priceFormatter.format(item.getPrecio()*item.getCantidad());
				  %>
				    <tr>
				      <th scope="row"><%=item_num%></th>
				      <td><%=item.getProducto().getCodigo()%></td>
				      <td><%=item.getProducto().getNombre()%></td>
				      <td><%=item.getProducto().getPresentacion()%></td>
				      <td><%=priceFormatter.format(item.getPrecio())%></td>
				      <td><%=item.getCantidad()%></td>
				      <td><%=subtotal%></td>
				    </tr>
				    <%
				  item_num++;  
				  } %>
				  </tbody>
				  <tfoot>
				    <tr class="table-active">
				      <th colspan="6" class="text-right ">Total</th>
				      <th><%=priceFormatter.format(factura.getTotal())%></th>
				    </tr>
				  </tfoot>
				</table>
			</div>
		</div>
		<hr/>
	</div><!-- container -->
</main>
<jsp:include page="../includes/footer.jsp"/>