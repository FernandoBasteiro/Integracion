<%@ page import="dto.VentaDTO"%>
<%@ page import="dto.ItemVentaDTO"%>
<%
VentaDTO factura = (VentaDTO) request.getAttribute("factura");
%>
<jsp:include page="../includes/header.jsp"/>
<main role="main">
	<div class="container">
		<div class="row">
			<div class="col col-xs-12 text-right">
				<h2 class="d-inline float-left">Ver factura</h2>
				<a href="/Web/facturacion/index.jsp" class="btn btn-secondary"><i class="fas fa-chevron-left mr-2"></i>Volver al listado</a>
				<a href="/Web/facturacion/index.jsp?action=cobrar&factura=nro" class="btn btn-success"><i class="fas fa-hand-holding-usd mr-2"></i>Cobrar</a>
				<a href="/Web/facturacion/index.jsp?action=anularFactura&factura=nro" class="btn btn-danger"><i class="fas fa-times mr-2"></i>Anular</a>
				<hr/>
			</div>
		</div>
		<div class="row">
			<div class="col col-xs-6">
				<p><strong class="mr-2">Fecha de facturación:</strong><%=factura.getFechaVenta()%></p>
			</div>
			<div class="col col-xs-6">
				<p><strong class="mr-2">Número:</strong><span class="badge badge-pill badge-info"><%=factura.getId()%></span></p>
			</div>
		</div>
		<div class="row">
			<div class="col col-xs-6">
				<p><strong class="mr-2">Fecha de cobro:</strong><%=factura.getFechaCobro()%></p>
			</div>
			<div class="col col-xs-6">
				<p><strong class="mr-2">Estado:</strong><span class="badge badge-pill badge-success"><%=factura.getEstado()%></span></p>
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
				<p><strong class="mr-2">Medio de pago:</strong><%=factura.getMedioDePago()%></p>
			</div>
			<div class="col col-xs-6">
				<p><strong class="mr-2">Cajero:</strong><%=factura.getEmpleado()%></p>
			</div>
		</div>
		<div class="row">
			<div class="col col-xs-12">
				<table class="table">
				  <thead>
				    <tr>
				      <th scope="col">#</th>
				      <th scope="col">Código</th>
				      <th scope="col">Nombre</th>
				      <th scope="col">Presentación</th>
				      <th scope="col">Precio</th>
				      <th scope="col">Cantidad</th>
				      <th scope="col">Subtotal</th>
				    </tr>
				  </thead>
				  <tbody>
				  <% 
				  int item_num = 1;
				  for (ItemVentaDTO item : factura.getItems()){ 
				  Float subtotal = item.getPrecio()*item.getCantidad();
				  %>
				    <tr>
				      <th scope="row"><%=item_num%></th>
				      <td><%=item.getProducto().getCodigo()%></td>
				      <td><%=item.getProducto().getNombre()%></td>
				      <td><%=item.getProducto().getPresentacion()%></td>
				      <td>$<%=item.getPrecio()%></td>
				      <td><%=item.getCantidad()%></td>
				      <td>$<%=subtotal%></td>
				    </tr>
				    <%
				  item_num++;  
				  } %>
				  </tbody>
				  <tfoot>
				    <tr class="table-active">
				      <th colspan="6" class="text-right ">Total</th>
				      <th><%=factura.getTotal()%></th>
				    </tr>
				  </tfoot>
				</table>
			</div>
		</div>
		<hr/>
	</div><!-- container -->
</main>
<jsp:include page="../includes/footer.jsp"/>