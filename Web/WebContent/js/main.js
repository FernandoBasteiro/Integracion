$(function(){
	
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
						showAlert("success", "Exito!", data.success);
					},
					error: function(data){
						showAlert("danger", "Error!", data.error);
					},
					complete: function(){
						$btn.removeAttr("disabled");
						$modal.modal('hide');
					}
				})
			});
		}else if($action == "cargarNovedad"){
			$title.text("Cargar Novedad");
			//$body.html('')
		}
		//cargarNovedad
		
	})
	
});

function showAlert(type, title, body){
	var alert = null;
	
	if($('.alert').length == 0){
		alert = $('<div class="alert alert-'+type+' alert-dismissible fade show" role="alert"><strong class="mr-2">'+title+'</strong><span class="alert-title">'+body+'</span><button type="button" class="close" data-dismiss="alert"aria-label="Cerrar"><span aria-hidden="true">&times;</span></button></div>');
		$('#notificationArea').append(alert);
		
	}else{
		$('.alert').addClass("show alert-"+type).find('strong').text(title);
		$('.alert').find('.alert-title').text(body);
	}
	$("#notificationArea").show();
	
	
}