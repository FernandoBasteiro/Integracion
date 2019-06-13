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
						alert(data.success);
					},
					error: function(data){
						alert(data.error);
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