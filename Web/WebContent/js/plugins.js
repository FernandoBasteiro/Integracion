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
			ul.attr("class", "nav flex-column nav-pills bs-autocomplete-menu");
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
		const productos = [ {
			"id" : 1,
			"nombreProducto" : "Amsterdam"
		}, {
			"id" : 2,
			"nombreProducto" : "Athens"
		}, {
			"id" : 3,
			"nombreProducto" : "Baghdad"
		}, {
			"id" : 4,
			"nombreProducto" : "Bangkok"
		}, {
			"id" : 5,
			"nombreProducto" : "Barcelona"
		}, {
			"id" : 6,
			"nombreProducto" : "Beijing"
		}, {
			"id" : 7,
			"nombreProducto" : "Belgrade"
		}, {
			"id" : 8,
			"nombreProducto" : "Berlin"
		}, {
			"id" : 9,
			"nombreProducto" : "Bogota"
		}, {
			"id" : 10,
			"nombreProducto" : "Bratislava"
		}, {
			"id" : 11,
			"nombreProducto" : "Brussels"
		}, {
			"id" : 12,
			"nombreProducto" : "Bucharest"
		}, {
			"id" : 13,
			"nombreProducto" : "Budapest"
		}, {
			"id" : 14,
			"nombreProducto" : "Buenos Aires"
		}, {
			"id" : 15,
			"nombreProducto" : "Cairo"
		}, {
			"id" : 16,
			"nombreProducto" : "CapeTown"
		}, {
			"id" : 17,
			"nombreProducto" : "Caracas"
		}, {
			"id" : 18,
			"nombreProducto" : "Chicago"
		}, {
			"id" : 19,
			"nombreProducto" : "Copenhagen"
		}, {
			"id" : 20,
			"nombreProducto" : "Dhaka"
		}, {
			"id" : 21,
			"nombreProducto" : "Dubai"
		}, {
			"id" : 22,
			"nombreProducto" : "Dublin"
		}, {
			"id" : 23,
			"nombreProducto" : "Frankfurt"
		}, {
			"id" : 24,
			"nombreProducto" : "Geneva"
		}, {
			"id" : 25,
			"nombreProducto" : "Hanoi"
		}, {
			"id" : 26,
			"nombreProducto" : "Helsinki"
		}, {
			"id" : 27,
			"nombreProducto" : "Hong Kong"
		}, {
			"id" : 28,
			"nombreProducto" : "Istanbul"
		}, {
			"id" : 29,
			"nombreProducto" : "Jakarta"
		}, {
			"id" : 30,
			"nombreProducto" : "Jerusalem"
		}, {
			"id" : 31,
			"nombreProducto" : "Johannesburg"
		}, {
			"id" : 32,
			"nombreProducto" : "Kabul"
		}, {
			"id" : 33,
			"nombreProducto" : "Karachi"
		}, {
			"id" : 34,
			"nombreProducto" : "Kiev"
		}, {
			"id" : 35,
			"nombreProducto" : "Kuala Lumpur"
		}, {
			"id" : 36,
			"nombreProducto" : "Lagos"
		}, {
			"id" : 37,
			"nombreProducto" : "Lahore"
		}, {
			"id" : 38,
			"nombreProducto" : "Lima"
		}, {
			"id" : 39,
			"nombreProducto" : "Lisbon"
		}, {
			"id" : 40,
			"nombreProducto" : "Ljubljana"
		}, {
			"id" : 41,
			"nombreProducto" : "London"
		}, {
			"id" : 42,
			"nombreProducto" : "Los Angeles"
		}, {
			"id" : 43,
			"nombreProducto" : "Luxembourg"
		}, {
			"id" : 44,
			"nombreProducto" : "Madrid"
		}, {
			"id" : 45,
			"nombreProducto" : "Manila"
		}, {
			"id" : 46,
			"nombreProducto" : "Marrakesh"
		}, {
			"id" : 47,
			"nombreProducto" : "Melbourne"
		}, {
			"id" : 48,
			"nombreProducto" : "Mexico City"
		}, {
			"id" : 49,
			"nombreProducto" : "Montreal"
		}, {
			"id" : 50,
			"nombreProducto" : "Moscow"
		}, {
			"id" : 51,
			"nombreProducto" : "Mumbai"
		}, {
			"id" : 52,
			"nombreProducto" : "Nairobi"
		}, {
			"id" : 53,
			"nombreProducto" : "New Delhi"
		}, {
			"id" : 54,
			"nombreProducto" : "NewYork"
		}, {
			"id" : 55,
			"nombreProducto" : "Nicosia"
		}, {
			"id" : 56,
			"nombreProducto" : "Oslo"
		}, {
			"id" : 57,
			"nombreProducto" : "Ottawa"
		}, {
			"id" : 58,
			"nombreProducto" : "Paris"
		}, {
			"id" : 59,
			"nombreProducto" : "Prague"
		}, {
			"id" : 60,
			"nombreProducto" : "Reykjavik"
		}, {
			"id" : 61,
			"nombreProducto" : "Riga"
		}, {
			"id" : 62,
			"nombreProducto" : "Rio de Janeiro"
		}, {
			"id" : 63,
			"nombreProducto" : "Rome"
		}, {
			"id" : 64,
			"nombreProducto" : "Saint Petersburg"
		}, {
			"id" : 65,
			"nombreProducto" : "San Francisco"
		}, {
			"id" : 66,
			"nombreProducto" : "Santiago de Chile"
		}, {
			"id" : 67,
			"nombreProducto" : "São Paulo"
		}, {
			"id" : 68,
			"nombreProducto" : "Seoul"
		}, {
			"id" : 69,
			"nombreProducto" : "Shanghai"
		}, {
			"id" : 70,
			"nombreProducto" : "Singapore"
		}, {
			"id" : 71,
			"nombreProducto" : "Sofia"
		}, {
			"id" : 72,
			"nombreProducto" : "Stockholm"
		}, {
			"id" : 73,
			"nombreProducto" : "Sydney"
		}, {
			"id" : 74,
			"nombreProducto" : "Tallinn"
		}, {
			"id" : 75,
			"nombreProducto" : "Tehran"
		}, {
			"id" : 76,
			"nombreProducto" : "The Hague"
		}, {
			"id" : 77,
			"nombreProducto" : "Tokyo"
		}, {
			"id" : 78,
			"nombreProducto" : "Toronto"
		}, {
			"id" : 79,
			"nombreProducto" : "Venice"
		}, {
			"id" : 80,
			"nombreProducto" : "Vienna"
		}, {
			"id" : 81,
			"nombreProducto" : "Vilnius"
		}, {
			"id" : 82,
			"nombreProducto" : "Warsaw"
		}, {
			"id" : 83,
			"nombreProducto" : "Washington D.C."
		}, {
			"id" : 84,
			"nombreProducto" : "Wellington"
		}, {
			"id" : 85,
			"nombreProducto" : "Zagreb"
		} ];

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
																return item.nombreProducto
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
								return $('<li class="nav-item"></li>').data(
										"item.autocomplete", item).append(
										'<a class="nav-link">'
												+ item[_data.item_label]
												+ '</a>').appendTo(ul);
							};
							// end autocomplete
						});
		var listadoVenta = $('#listadoItemVenta');
		var index = 1;

		$('#agregarProducto').on('click', function(e){
			e.preventDefault();
			var codProd = parseInt($('#codigo-producto').val());
			var cantProd = parseInt($('#cantidad-producto').val());
			if (isNaN(codProd) || isNaN(cantProd)) return;
			var prod = productos.find(producto => producto.id === codProd);
			var itemVta = $('<tr><th scope="row">'+index+'</th><td>'+prod.id+'</td><td>'+prod.id+'</td><td>Botella 500ml</td><td>'+cantProd+'</td><td>$40.00</td><td>$'+cantProd+'</td><input type="hidden" name="items[]" value="['+prod.id+','+cantProd+']" /></tr>')
			listadoVenta.find('tbody').append(itemVta);
			index++;
			$('#codigo-producto, #buscarProducto').val('');
			$('#cantidad-producto').val('1');
			
		})
	})();
}
