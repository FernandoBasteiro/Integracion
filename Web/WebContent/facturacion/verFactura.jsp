<jsp:include page="../includes/header.jsp"/>
<main role="main">
	<div class="container">
		<div class="row">
			<div class="col col-xs-12 text-right">
				<h2 class="d-inline float-left">Ver factura</h2>
				<a href="/Web/facturacion/index.jsp" class="btn btn-secondary"><i class="fas fa-chevron-left mr-2"></i>Volver al listado</a>
				<a href="/Web/facturacion/index.jsp" class="btn btn-success"><i class="fas fa-hand-holding-usd mr-2"></i>Cobrar</a>
				<a href="/Web/facturacion/index.jsp" class="btn btn-danger"><i class="fas fa-times mr-2"></i>Anular</a>
				<hr/>
			</div>
		</div>
		<div class="row">
			<div class="col col-xs-6">
				<p><strong class="mr-2">Fecha de facturación:</strong>11/06/2019</p>
			</div>
			<div class="col col-xs-6">
				<p><strong class="mr-2">Número:</strong><span class="badge badge-pill badge-info">0001231239</span></p>
			</div>
		</div>
		<div class="row">
			<div class="col col-xs-6">
				<p><strong class="mr-2">Fecha de cobro:</strong>11/06/2019</p>
			</div>
			<div class="col col-xs-6">
				<p><strong class="mr-2">Estado:</strong><span class="badge badge-pill badge-success">Cobrada</span></p>
			</div>
		</div>
		<div class="row">
			<div class="col col-xs-6">
				<p><strong class="mr-2">Tipo de factura:</strong><span class="badge badge-pill badge-dark">A</span></p>
			</div>
			<div class="col col-xs-6">
				<p><strong class="mr-2">CUIT:</strong>27-32546345-2</p>
			</div>
		</div>
		<div class="row">
			<div class="col col-xs-6">
				<p><strong class="mr-2">Medio de pago:</strong>Efectivo</p>
			</div>
			<div class="col col-xs-6">
				<p><strong class="mr-2">Cajero:</strong>Erica Nuñez</p>
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
				      <th scope="col">Cantidad</th>
				      <th scope="col">Precio</th>
				      <th scope="col">Subtotal</th>
				    </tr>
				  </thead>
				  <tbody>
				    <tr>
				      <th scope="row">1</th>
				      <td>12345322</td>
				      <td>Coca-Cola</td>
				      <td>Botella 500ml</td>
				      <td>2</td>
				      <td>$40.00</td>
				      <td>$80.00</td>
				    </tr>
				    <tr>
				      <th scope="row">2</th>
				      <td>23423424</td>
				      <td>Gatorade</td>
				      <td>Botella 1lt</td>
				      <td>1</td>
				      <td>$80.00</td>
				      <td>$80.00</td>
				    </tr>
				    <tr>
				      <th scope="row">3</th>
				      <td>55454434</td>
				      <td>Tomate</td>
				      <td>Bolsa 5kg</td>
				      <td>1</td>
				      <td>$100.00</td>
				      <td>$100.00</td>
				    </tr>
				  </tbody>
				  <tfoot>
				    <tr class="table-active">
				      <th colspan="6" class="text-right ">Total</th>
				      <th>$260.00</th>
				    </tr>
				  </tfoot>
				</table>
			</div>
		</div>
		<hr/>
	</div><!-- container -->
</main>
<jsp:include page="../includes/footer.jsp"/>