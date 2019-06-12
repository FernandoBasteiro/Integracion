<jsp:include page="../includes/header.jsp"/>
<%@ page import="enumeraciones.MedioDePago"%>
<main role="main">
	<div class="container">
		<div class="row">
			<div class="col col-xs-12">
				<h2 class="d-inline">Vender</h2>
				<hr/>
			</div>
		</div>
		<div class="row">
			<div class="col col-xs-12 menu-filtro">
				<form method="post" action="?" id="formVenta">
					<div class="form-row">		
						<div class="form-group col-sm-6">
						    <select name="tipoFactura" class="form-control" id="estadoFactura">
						      <option selected>Tipo de Factura</option>
						      <option>A</option>
						      <option>B</option>
						      <option>C</option>
						    </select>
						  </div>
						<div class="form-group col-sm-6">
						    <input placeholder="Ingrese CUIT..." type="text" name="cuitFactura" class="form-control"/>
						</div>			
					</div>
					<div class="form-row pb-2">
						<div class="form-group col-sm-6">
							<div class="input-group">
								<div class="input-group-prepend">
							    	<label class="input-group-text" for="buscarProducto">Buscar producto por código</label>
							  	</div>
							 	<select id="productoAutocomplete" name="buscarProducto" class="form-control">
							  		<option value="1">Producto 1</option>
							  		<option value="2">Producto 2</option>
							  		<option value="3">Producto 3</option>
							  		<option value="4">Producto 4</option>
							  		<option value="5">Producto 5</option>
							  	</select>
							</div>
						</div>
						<div class="form-group col-sm-4">
							<div class="input-group">
								<div class="input-group-prepend">
							    	<label class="input-group-text" for="buscarProducto">Cantidad</label>
							  	</div>
							 	<input type="number" min="0" name="cantidadProducto" value="1" class="form-control"/>
							</div>
						</div>
						<div class="form-group col-sm-2">
					    	<button class="btn btn-success btn-block" type="button"><i class="fas fa-plus mr-2"></i>Agregar producto</button>
						</div>
					</div>
					<div class="form-row">
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
					<div class="form-row">
						<div class="form-group col-sm-12">
							<h3 class="h4 mb-3 mt-4">Datos del pago</h3>
						</div>
						<div class="form-group col-sm-6">
							<div class="input-group">
								<div class="input-group-prepend">
							    	<label class="input-group-text" for="buscarProducto">Seleccionar medio de pago</label>
							  	</div>
							 	<select id="medioPago" name="medioPago" class="form-control">
									<%
									for (MedioDePago mdp : MedioDePago.values()) {
									%>
										<option value=<%=mdp.getId() %>><%=mdp.getNombre() %></option>
									<%
									}
									%>
								</select>
							</div>						
						</div>
						<div class="form-group col-sm-6" id="pagoEfectivo">
							<div class="input-group">
								<div class="input-group-prepend">
							    	<label class="input-group-text" for="montoPago"><i class="fas fa-dollar-sign"></i></label>
							  	</div>
								<input type="number" min="0" step="0,01" name="montoPago" id="montoPago" class="form-control" placeholder="Ingrese monto con el que abona"/>
							</div>
						</div>
					</div>
					<div class="form-row" id="pagoDebito">
						<div class="form-group col-sm-8">
							<div class="input-group">
								<div class="input-group-prepend">
							    	<label class="input-group-text" for="debitoTarjeta">Número tarjeta</label>
							  	</div>
								<input type="text" name="debitoTarjeta" id="debitoTarjeta" class="form-control" placeholder="0000 0000 0000 0000"/>
							</div>
						</div>
						<div class="form-group col-sm-4">
							<div class="input-group">
								<div class="input-group-prepend">
							    	<label class="input-group-text" for="debitoCodigoSeguridad">Código de Seguridad</label>
							  	</div>
								<input type="number" min="0" name="debitoCodigoSeguridad" id="debitoCodigoSeguridad" class="form-control" placeholder="000"/>
							</div>
						</div>
						<div class="form-group col-sm-8">
							<div class="input-group">
								<div class="input-group-prepend">
							    	<label class="input-group-text" for="debitoTitular">Titular</label>
							  	</div>
								<input type="text" name="debitoTitular" id="debitoTitular" class="form-control" placeholder="Juan Perez"/>
							</div>
						</div>
						<div class="form-group col-sm-4">
							<div class="input-group">
								<div class="input-group-prepend">
							    	<label class="input-group-text" for="debitoDni">DNI</label>
							  	</div>
								<input type="text" name="debitoDni" id="debitoDni" class="form-control" placeholder="12345678"/>
							</div>
						</div>
						<div class="form-group col-sm-4">
							<div class="input-group">
								<div class="input-group-prepend">
							    	<label class="input-group-text" for="debitoVencimiento">Fecha de vencimiento</label>
							  	</div>
								<input type="text" name="debitoVencimiento" id="debitoVencimiento" class="form-control" placeholder="MMAA"/>
							</div>
						</div>
						<div class="form-group col-sm-4">
							<div class="input-group">
								<div class="input-group-prepend">
							    	<label class="input-group-text" for="debitoTipoCuenta">Tipo de Cuenta</label>
							  	</div>
							 	<select id="debitoTipoCuenta" name="debitoTipoCuenta" class="form-control">
									<option value="1" selected>Caja de Ahorro<option>
									<option value="2">Cuenta Corriente<option>
								</select>
							</div>						
						</div>	
						<div class="form-group col-sm-4">
							<div class="input-group">
								<div class="input-group-prepend">
							    	<label class="input-group-text" for="debitoPin">PIN</label>
							  	</div>
								<input type="number" min="0" name="debitoPin" id="debitoPin" class="form-control" placeholder="1234"/>
							</div>
						</div>		
					</div>
					<div class="form-row" id="pagoCredito">
						<div class="form-group col-sm-8">
							<div class="input-group">
								<div class="input-group-prepend">
							    	<label class="input-group-text" for="creditoTarjeta">Número tarjeta</label>
							  	</div>
								<input type="text" name="creditoTarjeta" id="creditoTarjeta" class="form-control" placeholder="0000 0000 0000 0000"/>
							</div>
						</div>
						<div class="form-group col-sm-4">
							<div class="input-group">
								<div class="input-group-prepend">
							    	<label class="input-group-text" for="creditoCodigoSeguridad">Código de Seguridad</label>
							  	</div>
								<input type="number" min="0" name="creditoCodigoSeguridad" id="creditoCodigoSeguridad" class="form-control" placeholder="000"/>
							</div>
						</div>
						<div class="form-group col-sm-8">
							<div class="input-group">
								<div class="input-group-prepend">
							    	<label class="input-group-text" for="creditoTitular">Titular</label>
							  	</div>
								<input type="text" name="creditoTitular" id="creditoTitular" class="form-control" placeholder="Juan Perez"/>
							</div>
						</div>
						<div class="form-group col-sm-4">
							<div class="input-group">
								<div class="input-group-prepend">
							    	<label class="input-group-text" for="creditoDni">DNI</label>
							  	</div>
								<input type="text" name="creditoDni" id="creditoDni" class="form-control" placeholder="12345678"/>
							</div>
						</div>
						<div class="form-group col-sm-6">
							<div class="input-group">
								<div class="input-group-prepend">
							    	<label class="input-group-text" for="creditoVencimiento">Fecha de vencimiento</label>
							  	</div>
								<input type="text" name="creditoVencimiento" id="creditoVencimiento" class="form-control" placeholder="MMAA"/>
							</div>
						</div>
						<div class="form-group col-sm-6">
							<div class="input-group">
								<div class="input-group-prepend">
							    	<label class="input-group-text" for="creditoCuotas">Cuotas</label>
							  	</div>
								<input type="number" min="0" max="12" name="creditoCuotas" id="creditoCodigoSeguridad" class="form-control"/>
							</div>
						</div>
					</div>
					<div class="form-row">
						<div class="form-group col-sm-12 text-right">
							<button class="btn btn-success"><i class="fas fa-file-invoice-dollar mr-2"></i>Generar Factura</button>
						</div>
					</div>		
				</form>
				<hr/>
			</div>
		</div>
		
		<hr/>
	</div><!-- container -->
</main>
<jsp:include page="../includes/footer.jsp"/>