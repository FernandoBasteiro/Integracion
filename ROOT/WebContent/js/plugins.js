// Avoid `console` errors in browsers that lack a console.
(function() {
	var method;
	var noop = function() {
	};
	var methods = [ 'assert', 'clear', 'count', 'debug', 'dir', 'dirxml',
			'error', 'exception', 'group', 'groupCollapsed', 'groupEnd',
			'info', 'log', 'markTimeline', 'profile', 'profileEnd', 'table',
			'time', 'timeEnd', 'timeline', 'timelineEnd', 'timeStamp', 'trace',
			'warn' ];
	var length = methods.length;
	var console = (window.console = window.console || {});

	while (length--) {
		method = methods[length];

		// Only stub undefined methods.
		if (!console[method]) {
			console[method] = noop;
		}
	}
}());

// Place any jQuery/helper plugins in here.
if ($('#formVenta').length) {
	$('#formVenta #pagoEfectivo').dependsOn({
		'#formVenta #medioPago' : {
			values : [ '1' ]
		}
	}, {
		hide : true
	});
	$('#formVenta #pagoDebito').dependsOn({
		'#formVenta #medioPago' : {
			values : [ '3' ]
		}
	}, {
		hide : true
	});
	$('#formVenta #pagoCredito').dependsOn({
		'#formVenta #medioPago' : {
			values : [ '2' ]
		}
	}, {
		hide : true
	});
	$('#formVenta #cuitFactura').dependsOn({
		'#formVenta #tipoFactura' : {
			values : [ '1', '2' ]
		}
	}, {
		hide : true
	});

}

/** * autocomplete ** */
if ($('#productoAutocomplete').length) {
	$.widget("ui.autocomplete", $.ui.autocomplete, {
		_renderMenu : function(ul, items) {
			var that = this;
			ul.attr("class", "nav nav-pills bs-autocomplete-menu");
			$.each(items, function(index, item) {
				that._renderItemData(ul, item);
			});
		},
		_resizeMenu : function() {
			var ul = this.menu.element;
			ul.outerWidth(Math.min(
			// Firefox wraps long text (possibly a rounding bug)
			// so we add 1px to avoid the wrapping (#7513)
			ul.width("").outerWidth() + 1, this.element.outerWidth()));
		}

	});

	(function() {
		"use strict";
		var venta = {
				tipoFactura: null,
				cuitFactura: null,
				items:[],
				medioPago: null,
				montoPago: null,
				debitoTarjeta: null,
				debitoCodigoSeguridad: null,
				debitoTitular: null,
				debitoDni: null,
				debitoVencimiento: null,
				debitoTipoCuenta: null,
				debitoPin: null,
				creditoTarjeta: null,
				creditoCodigoSeguridad: null,
				creditoTitular: null,
				creditoDni: null,
				creditoVencimiento: null,
				creditoCuotas: null,
				total: null
		}
		var productos = [];

		$.ajax({
			url: '/Private/listarProductos',
			dataType: 'json',
			success: function(data){
				productos = [data];
				productos = productos[0].productos;
			},
			error: function(jqXHR,textStatus,errorThrown){
				console.log(jqXHR,textStatus,errorThrown);
			}
			
		})
		
		$('.bs-autocomplete')
				.each(
						function() {
							var _this = $(this), _data = _this.data(), _hidden_field = $('#'
									+ _data.hidden_field_id);

							_this
									.after(
											'<div class="bs-autocomplete-feedback form-control-feedback"><div class="loader">Cargando...</div></div>')
									.parent('.form-group').addClass(
											'has-feedback');

							var feedback_icon = _this
									.next('.bs-autocomplete-feedback');
							feedback_icon.hide();

							_this
									.autocomplete(
											{
												minLength : 2,
												autoFocus : true,
												source : function(request,
														response) {
													var _regexp = new RegExp(
															request.term, 'i');
													var data = productos
															.filter(function(
																	item) {
																return item.codigo
																		.match(_regexp);
															});
													response(data);
												},

												search : function() {
													feedback_icon.show();
													_hidden_field.val('');
												},

												response : function() {
													feedback_icon.hide();
												},

												focus : function(event, ui) {
													_this
															.val(ui.item[_data.item_label]);
													event.preventDefault();
												},

												select : function(event, ui) {
													_this
															.val(ui.item[_data.item_label]);
													_hidden_field
															.val(ui.item[_data.item_id]);
													event.preventDefault();
												}
											}).data('ui-autocomplete')._renderItem = function(
									ul, item) {
								return $('<li class="nav-item flex-fill"></li>').data(
										"item.autocomplete", item).append(
										'<a class="nav-link">'
												+ item[_data.item_label]
												+ ' (' + item[_data.item_sublabel] + ')'
												+ '</a>').appendTo(ul);
							};
							// end autocomplete
						});
		var listadoVenta = $('#listadoItemVenta');
		var index = 1;
		var totalVenta = 0;

		$('#agregarProducto').on('click', function(e){
			e.preventDefault();
			var codProd = $('#codigo-producto').val();
			var cantProd = parseInt($('#cantidad-producto').val());
			if (isNaN(codProd) || isNaN(cantProd) || codProd == "") return;
			
			var prod = productos.find(producto => producto.codigo === codProd);
			totalVenta += prod.precio*cantProd;
			var itemVta = $('<tr><th scope="row">'+index+'</th><td>'+prod.codigo+'</td><td>'+prod.nombre+'</td><td>'+prod.presentacion+'</td><td>'+cantProd+'</td><td>$'+prod.precio.toFixed(2)+'</td><td>$'+(prod.precio.toFixed(2)*cantProd).toFixed(2)+'</td><td><a href="#" class="delete"><i class="fas fa-trash text-danger"></i></a></td><input type="hidden" name="items" value="'+prod.codigo+','+cantProd+'" /></tr>')
			itemVta.data('codigo',prod.codigo);
			itemVta.data('nombre',prod.nombre);
			itemVta.data('presentacion',prod.presentacion);
			itemVta.data('cantidad',cantProd);
			itemVta.data('precio',prod.precio);

			listadoVenta.find('tbody').append(itemVta);
			index++;
			$('#codigo-producto, #buscarProducto').val('');
			$('#cantidad-producto').val('1');
			$('#totalVenta').text(totalVenta.toFixed(2));
			itemVta.find('.delete').on('click', function(e){e.preventDefault(); $('#listadoItemVenta tbody tr').get($(this).closest('tr').index()).remove(); index--;totalVenta -= prod.precio*cantProd;$('#totalVenta').text(totalVenta.toFixed(2));});
			
		})
		
		$('#formVenta').on('submit', function(e){
			
			if($('#formVenta tbody tr').length == 0) e.preventDefault();
			if(localStorage.getItem('venta')!=null)localStorage.clear();
			
			var venta = {
					tipoFactura: $('select[name=tipoFactura]').val(),
					cuitFactura: $('input[name=cuitFactura]').val(),
					items:[],
					medioPago: $('select[name=medioPago]').val(),
					montoPago: $('input[name=montoPago]').val(),
					debitoTarjeta: $('input[name=debitoTarjeta]').val(),
					debitoCodigoSeguridad: $('input[name=debitoCodigoSeguridad]').val(),
					debitoTitular: $('input[name=debitoTitular]').val(),
					debitoDni: $('input[name=debitoDni]').val(),
					debitoVencimiento: $('input[name=debitoVencimiento]').val(),
					debitoTipoCuenta: $('input[name=debitoTipoCuenta]').val(),
					debitoPin: $('input[name=debitoPin]').val(),
					creditoTarjeta: $('input[name=creditoTarjeta]').val(),
					creditoCodigoSeguridad: $('input[name=creditoCodigoSeguridad]').val(),
					creditoTitular: $('input[name=creditoTitular]').val(),
					creditoDni: $('input[name=creditoDni]').val(),
					creditoVencimiento: $('input[name=creditoVencimiento]').val(),
					creditoCuotas: $('input[name=creditoCuotas]').val(),
					total : $('#totalVenta').text()
			}
			//#	Código	Nombre	Presentación	Cantidad	Precio	Subtotal
			$.each($('input[name=items]'), function(){
				var itemData = {
						codigo: $(this).closest('tr').data('codigo'),
						nombre: $(this).closest('tr').data('nombre'),
						presentacion: $(this).closest('tr').data('presentacion'),
						cantidad: $(this).closest('tr').data('cantidad'),
						precio: $(this).closest('tr').data('precio')
					};
				venta.items.push(itemData);
			})
			localStorage.setItem('venta', JSON.stringify(venta));

		});

	})();
}
