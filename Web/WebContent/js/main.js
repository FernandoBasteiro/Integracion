$(function(){
	$('a.disabled').on('click',function(e){e.preventDefault()})
	$('#estadoEmpleadoCombo').on('change', function(e){
		var $this = $(this), $egreso = $('#fechaEgresoEmpleado');
		if ($this.val() == "2"){
			$egreso.attr("required", "required");
		}else{
			$egreso.removeAttr("required");
		}
	});
	
	$('#modal').on('show.bs.modal', function (event) {		
		var $modal = $(this), $title = $modal.find(".modal-title"), $body = $modal.find(".modal-body"), $footer = $modal.find(".modal-footer");
		var $trigger = $(event.relatedTarget);
		var $action = $trigger.data('action');
		//eliminarEmpleado
		if($action == "eliminarEmpleado"){
			$title.text("Eliminar Empleado");
			$body.html("<p>El empleado <strong>"+$trigger.data('nombre')+" "+$trigger.data('apellido')+"</strong>, con legajo <strong>#"+$trigger.data('legajo')+"</strong> ser&aacute; eliminado.<br/>Desea continuar?</p>")
			$footer.find(".btn-primary").text("Eliminar Empleado").on('click', function(e){
				$btn = $(this);
				$btn.attr("disabled","disabled");
				
				$.ajax({
					url: '/Web/Private/eliminarEmpleado',
					data:{legajo: $trigger.data('legajo')},
					dataType: 'json',
					success: function(data){
						showAlert("warning", "Alerta!", data.warning);
					},
					error: function(jqXHR,textStatus,errorThrown ){
						if(typeof jqXHR.responseJSON != "undefined"){
							showAlert("danger", "Error!", jqXHR.responseJSON.error);
						}else{							
							showAlert("danger", "Error!", textStatus + " (status: "+jqXHR.status+")");
						}
					},
					complete: function(){
						$btn.removeAttr("disabled");
						$modal.modal('hide');
					}
				})
			});
		}else if($action == "cargarNovedad"){
			$title.text("Cargar Novedad");
			$body.html('<p><strong class="mr-2">Nombre:</strong>'+$trigger.data('nombre')+' '+$trigger.data('apellido')+'</p><p><strong class="mr-2">Legajo:</strong>'+$trigger.data('legajo')+'</p><hr/><form id="cargarNovedad" method="post" action="?"><div class="form-row pb-3"><div class="col-sm-6"><label for="novedadMes">Mes</label><input class="form-control" type="number" min="1" max="12" name="novedadMes" value="1"/></div><div class="col-sm-6"><label for="novedadAnio">A&ntilde;o</label><input class="form-control" type="number" min="2019" max="2030" name="novedadAnio" value="2019"/></div></div><div class="form-row"><div class="col-sm-6"><fieldset class="form-group "><legend class="col-form-label pt-0">Es una licencia paga?</legend><div class="form-check form-check-inline"><input class="form-check-input" type="radio" name="esPago"value="true" checked> <label class="form-check-label"for="gridRadios1">Si</label></div><div class="form-check form-check-inline"><input class="form-check-input" type="radio" name="esPago"value="false"> <label class="form-check-label"for="gridRadios2">No</label></div></fieldset></div><div class="col-sm-6"><label>Cantidad de d&iacute;as</label> <input type="number" min="1" name="cantDias" value="1" class="form-control" /></div></div></form>')
			$footer.find(".btn-primary").text("Guardar Novedad").on('click', function(e){
				e.preventDefault();
				$btn = $(this);

				$.ajax({
					url: '/Web/Private/generarNovedad',
					data:$('#cargarNovedad').serialize()+ "&legajo="+$trigger.data('legajo'),
					dataType: 'json',
					success: function(data){
						showAlert("success", "Exito!", data.success);
					},
					error: function(jqXHR,textStatus,errorThrown ){
						if(typeof jqXHR.responseJSON != "undefined"){
							showAlert("danger", "Error!", jqXHR.responseJSON.error);
						}else{							
							showAlert("danger", "Error!", textStatus + " (status: "+jqXHR.status+")");
						}
					},
					complete: function(){
						$btn.removeAttr("disabled");
						$modal.modal('hide');
					}
				})
			})
		}else if( $action=="eliminarProducto" ){
			$title.text("Eliminar Producto");
			$body.html("<p>El producto <strong>"+$trigger.data('nombre')+"</strong> con c&oacute;digo <strong>#"+$trigger.data('codigo')+"</strong> ser&aacute; eliminado.<br/>Desea continuar?</p>");
			$footer.find(".btn-primary").text("Eliminar Producto").on('click', function(e){
				$btn = $(this);
				$btn.attr("disabled","disabled");
				
				$.ajax({
					url: '/Web/Private/eliminarProducto',
					data:{codigo: $trigger.data('codigo')},
					dataType: 'json',
					success: function(data){
						showAlert("success", "Exito!", data.success);
					},
					error: function(jqXHR,textStatus,errorThrown ){
						if(typeof jqXHR.responseJSON != "undefined"){
							showAlert("danger", "Error!", jqXHR.responseJSON.error);
						}else{							
							showAlert("danger", "Error!", textStatus + " (status: "+jqXHR.status+")");
						}
					},
					complete: function(){
						$btn.removeAttr("disabled");
						$modal.modal('hide');
					}
				})
			})
		} else if( $action == "imputarCobros"){
			$title.text("Imputar Cobros");
			$body.html('<form id="imputarCobrosTc" method="post" action="?"><div class="form-row pb-3"><div class="col-sm-6"><label for="periodoMes">Mes</label><input class="form-control" type="number" min="1" max="12" name="periodoMes" value="1"/></div><div class="col-sm-6"><label for="periodoAnio">A&ntilde;o</label><input class="form-control" type="number" min="2019" max="2030" name="periodoAnio" value="2019"/></div></div></form>')
			$footer.find(".btn-primary").text("Imputar Cobros").on('click', function(e){
				e.preventDefault();
				$btn = $(this);

				$.ajax({
					url: '/Web/Private?action=imputarCobros',
					data:$('#imputarCobrosTc').serialize(),
					dataType: 'json',
					success: function(data){
						showAlert("success", "Exito!", data.success);
					},
					error: function(jqXHR,textStatus,errorThrown){
						if(typeof jqXHR.responseJSON != "undefined"){
							showAlert("danger", "Error!", jqXHR.responseJSON.error);
						}else{							
							showAlert("danger", "Error!", textStatus + " (status: "+jqXHR.status+")");
						}
					},
					complete: function(){
						$btn.removeAttr("disabled");
						$modal.modal('hide');
					}
				})
			})
			
		} else if($action=="marcarCobrado"){
			$title.text("Imputar Cobro en Factura");
			$body.html("<p>La factura n&uacute;mero <strong>"+$trigger.data('factura')+"</strong> ser&aacute; marcada como cobrada.<br/>Desea continuar?</p>");
			$footer.find(".btn-primary").text("Imputar Cobro").on('click', function(e){
				e.preventDefault();
				$btn = $(this);
				$.ajax({
					url: '/Web/Private/marcarCobrado',
					data: {factura: $trigger.data('factura')},
					dataType: 'json',
					success: function(data){
						showAlert("success", "Exito!", data.success);
					},
					error: function(jqXHR,textStatus,errorThrown){
						if(typeof jqXHR.responseJSON != "undefined"){
							showAlert("danger", "Error!", jqXHR.responseJSON.error);
						}else{							
							showAlert("danger", "Error!", textStatus + " (status: "+jqXHR.status+")");
						}
					},
					complete: function(){
						$btn.removeAttr("disabled");
						$modal.modal('hide');
					}
				})
			})
		}else if($action=="anularFactura"){
			$title.text("Anular Factura");
			$body.html("<p>La factura n&uacute;mero <strong>"+$trigger.data('factura')+"</strong> ser&aacute; anulada.<br/>Desea continuar?</p>");
			$footer.find(".btn-primary").text("Anular").on('click', function(e){
				e.preventDefault();
				$btn = $(this);
				$.ajax({
					url: '/Web/Private/anularFactura',
					data: {factura: $trigger.data('factura')},
					dataType: 'json',
					success: function(data){
						showAlert("success", "Exito!", data.success);
					},
					error: function(jqXHR,textStatus,errorThrown){
						if(typeof jqXHR.responseJSON != "undefined"){
							showAlert("danger", "Error!", jqXHR.responseJSON.error);
						}else{							
							showAlert("danger", "Error!", textStatus + " (status: "+jqXHR.status+")");
						}
					},
					complete: function(){
						$btn.removeAttr("disabled");
						$modal.modal('hide');
					}
				})
			})
		}
	})
	
});

function showAlert(type, title, body){
	var alert = null;
	var refresh = $('<a href="#" class="btn btn-warning btn-sm ml-3"><i class="fas fa-sync mr-2"></i>Actualizar vista</a>')
	
	refresh.on('click', function(e){e.preventDefault;location.reload()})
	
	if($('.alert').length == 0){
		alert = $('<div class="alert alert-'+type+' alert-dismissible fade show" role="alert"><strong class="mr-2">'+title+'</strong><span class="alert-title">'+body+'</span><button type="button" class="close" data-dismiss="alert"aria-label="Cerrar"><span aria-hidden="true">&times;</span></button></div>');
		alert.find('.alert-title').append(refresh);
		$('#notificationArea').append(alert);
		
	}else{
		$('.alert').addClass("show alert-"+type).find('strong').text(title);
		$('.alert').find('.alert-title').text(body).append(refresh);
	}
	
	$("#notificationArea").show();
	
	
}