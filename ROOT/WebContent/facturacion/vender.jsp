<%@ page import="enumeraciones.TipoFactura"%>
<%@ page import="enumeraciones.TipoCuenta"%>
<%@ page import="dto.EmpleadoDTO"%>
<%
	EmpleadoDTO empleado = (EmpleadoDTO) session.getAttribute("loggedUsr");
	if (empleado == null)
		response.sendRedirect("/index.jsp");
	//boolean autocomplete = (session.getAttribute("error") != null) ? true: false;
	//VentaDTO venta = (session.getAttribute("venta") != null) ? (VentaDTO) session.getAttribute("venta") : null;
%>
<jsp:include page="../includes/header.jsp" />
<%@ page import="enumeraciones.MedioDePago"%>
<main role="main">
<div class="container">
	<div class="row">
		<div class="col col-xs-12">
			<h2 class="d-inline">
				<i class="fas fa-cash-register mr-3 text-success"></i>Vender
			</h2>
			<hr />
		</div>
	</div>
	<div class="row">
		<div class="col col-xs-12 menu-filtro">
			<form method="post" action="/Private" id="formVenta">
				<input type="hidden" name="action" value="facturar" />
				<div class="form-row">
					<div class="form-group col-sm-6">
						<div class="input-group">
							<div class="input-group-prepend">
								<label class="input-group-text" for="tipoFactura">Tipo
									de Factura</label>
							</div>
							<select id="tipoFactura" name="tipoFactura" class="form-control"
								id="estadoFactura">
								<%
									for (TipoFactura tipoFactura : TipoFactura.values()) {
								%>
								<option value=<%=tipoFactura.getId()%>><%=tipoFactura.getNombre()%></option>
								<%
									}
								%>
							</select>
						</div>
					</div>
					<div class="form-group col-sm-6" id="cuitFactura">
						<div class="input-group">
							<div class="input-group-prepend">
								<label class="input-group-text" for="cuitFactura">CUIT</label>
							</div>
							<input placeholder="Ingrese CUIT..." type="number"
								name="cuitFactura" id="cuitFactura" class="form-control" value="" min="0" required/>
						</div>
					</div>
				</div>
				<div class="form-row pb-2">
					<div class="form-group col-sm-6" id="productoAutocomplete">
						<div class="input-group">
							<div class="input-group-prepend">
								<label class="input-group-text control-label"
									for="buscarProducto">Buscar producto por código</label>
							</div>
							<input class="form-control bs-autocomplete" id="buscarProducto"
								value="" placeholder="Ingresar código producto..." type="text"
								data-source="/Private/listarProductos"
								data-hidden_field_id="codigo-producto" data-item_id="codigo"
								data-item_label="nombre" data-item_sublabel="presentacion"
								autocomplete="off">
						</div>
						<input type="hidden" class="form-control" id="codigo-producto"
							name="codigoProductoAutocomplete" value="" type="number" readonly>
					</div>
					<div class="form-group col-sm-4">
						<div class="input-group">
							<div class="input-group-prepend">
								<label class="input-group-text" for="cantidadProducto">Cantidad</label>
							</div>
							<input type="number" min="1" id="cantidad-producto"
								name="cantidadProducto" value="1" class="form-control" />
						</div>
					</div>
					<div class="form-group col-sm-2">
						<button id="agregarProducto" class="btn btn-success btn-block"
							type="button">
							<i class="fas fa-plus mr-2"></i>Agregar producto
						</button>
					</div>
				</div>
				<div class="form-row">
					<div class="col col-xs-12">
						<table class="table" id="listadoItemVenta">
							<thead>
								<tr>
									<th scope="col">#</th>
									<th scope="col">Código</th>
									<th scope="col">Nombre</th>
									<th scope="col">Presentación</th>
									<th scope="col">Cantidad</th>
									<th scope="col">Precio</th>
									<th scope="col">Subtotal</th>
									<th scope="col">Eliminar</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
							<tfoot>
								<tr class="table-active">
									<th colspan="7" class="text-right ">Total</th>
									<th>$<span id="totalVenta"></span></th>
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
								<label class="input-group-text" for="medioPago">Seleccionar
									medio de pago</label>
							</div>
							<select id="medioPago" name="medioPago" class="form-control">
								<%
									for (MedioDePago mdp : MedioDePago.values()) {
								%>
								<option value=<%=mdp.getId()%>><%=mdp.getNombre()%></option>
								<%
									}
								%>
							</select>
						</div>
					</div>
					<div class="form-group col-sm-6" id="pagoEfectivo">
						<div class="input-group">
							<div class="input-group-prepend">
								<label class="input-group-text" for="montoPago"><i
									class="fas fa-dollar-sign"></i></label>
							</div>
							<input type="number" min="0" step="0,01" name="montoPago"
								id="montoPago" class="form-control"
								placeholder="Ingrese monto con el que abona" required/>
						</div>
					</div>
				</div>
				<div class="form-row" id="pagoDebito">
					<div class="form-group col-sm-8">
						<div class="input-group">
							<div class="input-group-prepend">
								<label class="input-group-text" for="debitoTarjeta">Número
									tarjeta</label>
							</div>
							<input type="text" name="debitoTarjeta" id="debitoTarjeta"
								class="form-control" placeholder="0000 0000 0000 0000" />
						</div>
					</div>
					<div class="form-group col-sm-4">
						<div class="input-group">
							<div class="input-group-prepend">
								<label class="input-group-text" for="debitoCodigoSeguridad">Código
									de Seguridad</label>
							</div>
							<input type="number" min="0" name="debitoCodigoSeguridad"
								id="debitoCodigoSeguridad" class="form-control"
								placeholder="000" />
						</div>
					</div>
					<div class="form-group col-sm-8">
						<div class="input-group">
							<div class="input-group-prepend">
								<label class="input-group-text" for="debitoTitular">Titular</label>
							</div>
							<input type="text" name="debitoTitular" id="debitoTitular"
								class="form-control" placeholder="Juan Perez" />
						</div>
					</div>
					<div class="form-group col-sm-4">
						<div class="input-group">
							<div class="input-group-prepend">
								<label class="input-group-text" for="debitoDni">DNI</label>
							</div>
							<input type="text" name="debitoDni" id="debitoDni"
								class="form-control" placeholder="12345678" />
						</div>
					</div>
					<div class="form-group col-sm-4">
						<div class="input-group">
							<div class="input-group-prepend">
								<label class="input-group-text" for="debitoVencimiento">Fecha
									de vencimiento</label>
							</div>
							<input type="text" name="debitoVencimiento"
								id="debitoVencimiento" class="form-control" placeholder="MMAA" />
						</div>
					</div>
					<div class="form-group col-sm-4">
						<div class="input-group">
							<div class="input-group-prepend">
								<label class="input-group-text" for="debitoTipoCuenta">Tipo
									de Cuenta</label>
							</div>
							<select id="debitoTipoCuenta" name="debitoTipoCuenta"
								class="form-control">
								<%
									for (TipoCuenta tipoCuenta : TipoCuenta.values()) {
								%>
								<option value=<%=tipoCuenta.getId()%>><%=tipoCuenta.getNombre()%></option>
								<%
									}
								%>
							</select>
						</div>
					</div>
					<div class="form-group col-sm-4">
						<div class="input-group">
							<div class="input-group-prepend">
								<label class="input-group-text" for="debitoPin">PIN</label>
							</div>
							<input type="number" min="0" name="debitoPin" id="debitoPin"
								class="form-control" placeholder="1234" />
						</div>
					</div>
				</div>
				<div class="form-row" id="pagoCredito">
					<div class="form-group col-sm-8">
						<div class="input-group">
							<div class="input-group-prepend">
								<label class="input-group-text" for="creditoTarjeta">Número
									tarjeta</label>
							</div>
							<input type="text" name="creditoTarjeta" id="creditoTarjeta"
								class="form-control" placeholder="0000 0000 0000 0000" />
						</div>
					</div>
					<div class="form-group col-sm-4">
						<div class="input-group">
							<div class="input-group-prepend">
								<label class="input-group-text" for="creditoCodigoSeguridad">Código
									de Seguridad</label>
							</div>
							<input type="number" min="0" name="creditoCodigoSeguridad"
								id="creditoCodigoSeguridad" class="form-control"
								placeholder="000" autocomplete="off" />
						</div>
					</div>
					<div class="form-group col-sm-8">
						<div class="input-group">
							<div class="input-group-prepend">
								<label class="input-group-text" for="creditoTitular">Titular</label>
							</div>
							<input type="text" name="creditoTitular" id="creditoTitular"
								class="form-control" placeholder="Juan Perez" />
						</div>
					</div>
					<div class="form-group col-sm-4">
						<div class="input-group">
							<div class="input-group-prepend">
								<label class="input-group-text" for="creditoDni">DNI</label>
							</div>
							<input type="text" name="creditoDni" id="creditoDni"
								class="form-control" placeholder="12345678" />
						</div>
					</div>
					<div class="form-group col-sm-6">
						<div class="input-group">
							<div class="input-group-prepend">
								<label class="input-group-text" for="creditoVencimiento">Fecha
									de vencimiento</label>
							</div>
							<input type="text" name="creditoVencimiento"
								id="creditoVencimiento" class="form-control" placeholder="MMAA" />
						</div>
					</div>
					<div class="form-group col-sm-6">
						<div class="input-group">
							<div class="input-group-prepend">
								<label class="input-group-text" for="creditoCuotas">Cuotas</label>
							</div>
							<input type="number" value="1" min="1" max="12" name="creditoCuotas"
								id="creditoCuotas" class="form-control" />
						</div>
					</div>
				</div>
				<div class="form-row">
					<div class="form-group col-sm-12 text-right">
						<button class="btn btn-success" type="submit">
							<i class="fas fa-file-invoice-dollar mr-2"></i>Generar Factura
						</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<hr />
</div>
<!-- container --> </main>
<jsp:include page="../includes/footer.jsp" />