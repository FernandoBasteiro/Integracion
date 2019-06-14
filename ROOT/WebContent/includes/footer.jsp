
<footer class="container">
	<p>&copy; Supermercado Sarasa IAPP 2019</p>
</footer>
<!-- Modal -->
<div class="modal fade" id="modal" tabindex="-1" role="dialog"
	aria-labelledby="modalTitle" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="modalTitle"></h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
				<button type="button" class="btn btn-primary">Confirmar</button>
			</div>
		</div>
	</div>
</div>
<script src="/js/vendor/modernizr-3.7.1.min.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"
	integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
	crossorigin="anonymous"></script>
<script>
	window.jQuery
			|| document
					.write('<script src="/js/vendor/jquery-3.4.1.min.js"><\/script>')
</script>
<script src="/js/vendor/jquery-ui-1.12.1.custom/jquery-ui.min.js"></script>
<script src="/js/vendor/popper.min.js"></script>
<script src="/js/vendor/bootstrap.min.js"></script>
<script src="/js/vendor/dependsOn/dependsOn.min.js"></script>
<script src="/js/plugins.js"></script>
<script src="/js/main.js"></script>
<%
	if (request.getAttribute("script") != null) {
		if (request.getAttribute("script") == "true") {
%>
<script type="text/javascript">
	$(function() {
		localStorage.removeItem("venta");
	})
</script>
<%
	} else {
%>
<script type="text/javascript">
	$(function() {

		if (localStorage.getItem('venta') == null)
			return;

		var oldVta = JSON.parse(localStorage.getItem('venta'));
		var index = 1;
		var totalVenta = oldVta.total;

		$("select[name=tipoFactura] option[value=" + oldVta.tipoFactura + "]")
				.attr('selected', 'selected');
		$("select[name=medioPago] option[value=" + oldVta.medioPago + "]")
				.attr('selected', 'selected');
		$('input[name=cuitFactura]').val(oldVta.cuitFactura);
		$('input[name=montoPago]').val(oldVta.montoPago);
		$('input[name=debitoTarjeta]').val(oldVta.debitoTarjeta);
		$('input[name=debitoCodigoSeguridad]')
				.val(oldVta.debitoCodigoSeguridad);
		$('input[name=debitoTitular]').val(oldVta.debitoTitular);
		$('input[name=debitoDni]').val(oldVta.debitoDni);
		$('input[name=debitoVencimiento]').val(oldVta.debitoVencimiento);
		$('input[name=debitoTipoCuenta]').val(oldVta.debitoTipoCuenta);
		$('input[name=debitoPin]').val(oldVta.debitoPin);
		$('input[name=creditoTarjeta]').val(oldVta.creditoTarjeta);
		$('input[name=creditoCodigoSeguridad]').val(
				oldVta.creditoCodigoSeguridad);
		$('input[name=creditoTitular]').val(oldVta.creditoTitular);
		$('input[name=creditoDni]').val(oldVta.creditoDni);
		$('input[name=creditoVencimiento]').val(oldVta.creditoVencimiento);
		$('input[name=creditoCuotas]').val(oldVta.creditoCuotas);

		$
				.each(
						oldVta.items,
						function(i, v) {
							totalVenta += v.precio*v.cantidad;
							var itemVta = $('<tr><th scope="row">'
									+ index
									+ '</th><td>'
									+ v.codigo
									+ '</td><td>'
									+ v.nombre
									+ '</td><td>'
									+ v.presentacion
									+ '</td><td>'
									+ v.cantidad
									+ '</td><td>$'
									+ v.precio.toFixed(2)
									+ '</td><td>$'
									+ (v.precio.toFixed(2) * v.cantidad)
											.toFixed(2)
									+ '</td><td><a href="#" class="delete"><i class="fas fa-trash text-danger"></i></a></td><input type="hidden" name="items" value="'+v.codigo+','+v.cantidad+'" /></tr>')
							itemVta.data('codigo',v.codigo);
							itemVta.data('nombre',v.nombre);
							itemVta.data('presentacion',v.presentacion);
							itemVta.data('cantidad',v.cantidad);
							itemVta.data('precio',v.precio);
							$('#formVenta tbody').append(itemVta)
							index++;
							$('#totalVenta').text(parseFloat(totalVenta).toFixed(2));
							itemVta.find('.delete').on(
									'click',
									function(e) {
										e.preventDefault();
										$('#listadoItemVenta tbody tr').get(
												$(this).closest('tr').index())
												.remove();
										index--;
										totalVenta -= prod.precio * cantProd;
										$('#totalVenta').text(totalVenta.toFixed(2));
									});
						});
	})
</script>
<%
	}
	}
%>
</body>
</html>