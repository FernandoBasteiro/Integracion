<%@ page import="dto.EmpleadoDTO"%>
<% EmpleadoDTO empleado = (EmpleadoDTO) session.getAttribute("loggedUsr");
if (empleado == null) response.sendRedirect("/Web/index.jsp");
%>
<jsp:include page="../includes/header.jsp"/>
<main role="main">
	<div class="container">
		<div class="row">
			<div class="col col-xs-12">
				<h2 class="d-inline">Facturación</h2>
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
							  <input name="buscarFacturaNumero" type="search" class="form-control" placeholder="Ingrese número de factura..." aria-label="Ingrese número de factura" aria-describedby="buscarFacturaNumero">
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
							  <input name="buscarFacturaOperacion" type="search" class="form-control" placeholder="Ingrese número de operación..." aria-label="Ingrese número de operación" aria-describedby="buscarFacturaOperacion">
							   <div class="input-group-append">
							    <button class="btn btn-secondary" type="button"><i class="fas fa-search"></i></button>
							  </div>
							</div>
						</div>
					</div>
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
						      <option selected>- Estado -</option>
						      <option>Pendiente</option>
						      <option>Cobrada</option>
						      <option>Anulada</option>
						    </select>
						  </div>
						<div class="form-group col-sm-3">
						    <select class="form-control" id="medioPagoFactura" name="medioPagoFactura">
						      <option selected>- Medio de pago -</option>
						      <option>Efectivo</option>
						      <option>Tarjeta de débito</option>
						      <option>Tarjeta de crédito</option>
						    </select>
						  </div>
						  <div class="form-group col-sm-3">
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
				    <tr>
				      <th scope="row">1</th>
				      <td>0001120002</td>
				      <td>A</td>
				      <td>20-16894056-5</td>
				      <td><span class="badge badge-pill badge-warning">Pendiente</span></td>
				      <td>EFVO</td>
				      <td>-</td>
				      <td>$1200.02</td>
				      <td class="actions text-center">
				      	<a href="/Web/facturacion/verFactura.jsp?view=id" class="view mx-1" title="Ver factura"><i class="fas fa-eye text-success"></i></a>
				      	<a href="/Web/facturacion/index.jsp?action=cobrar&factura=nro" class="edit mx-1" title="Ingresar cobranza"><i class="fas fa-hand-holding-usd text-primary"></i></a>
				      	<a href="/Web/facturacion/index.jsp?action=anularFactura&factura=nro" class="delete mx-1" title="Anular factura"><i class="fas fa-times text-danger"></i></a>
				      </td>
				    </tr>
				    <tr>
				      <th scope="row">2</th>
				      <td>0001120005</td>
				      <td>C</td>
				      <td>Consumidor Final</td>
				      <td><span class="badge badge-pill badge-danger">Anulada</span></td>
				      <td>TD</td>
				      <td>3355</td>
				      <td>$1570.02</td>
				      <td class="actions text-center">
				      	<a href="/Web/facturacion/verFactura.jsp?view=id" class="view mx-1" title="Ver factura"><i class="fas fa-eye text-success"></i></a>
				      	<a href="/Web/facturacion/index.jsp?action=cobrar&factura=nro" class="edit mx-1" title="Ingresar cobranza"><i class="fas fa-hand-holding-usd text-primary"></i></a>
				      	<a href="/Web/facturacion/index.jsp?action=anularFactura&factura=nro" class="delete mx-1" title="Anular factura"><i class="fas fa-times text-danger"></i></a>
				      </td>
				    </tr>
				    <tr>
				      <th scope="row">3</th>
				      <td>0001120008</td>
				      <td>B</td>
				      <td>20-16000056-5</td>
				      <td><span class="badge badge-pill badge-success">Cobrada</span></td>
				      <td>TC</td>
				      <td>7655</td>
				      <td>$170.20</td>
				      <td class="actions text-center">
				      	<a href="/Web/facturacion/verFactura.jsp?view=id" class="view mx-1" title="Ver factura"><i class="fas fa-eye text-success"></i></a>
				      	<a href="/Web/facturacion/index.jsp?action=cobrar&factura=nro" class="edit mx-1" title="Ingresar cobranza"><i class="fas fa-hand-holding-usd text-primary"></i></a>
				      	<a href="/Web/facturacion/index.jsp?action=anularFactura&factura=nro" class="delete mx-1" title="Anular factura"><i class="fas fa-times text-danger"></i></a>
				      </td>
				    </tr>
				  </tbody>
				</table>
			</div>
		</div>
		<hr/>
	</div><!-- container -->
</main>
<jsp:include page="../includes/footer.jsp"/>