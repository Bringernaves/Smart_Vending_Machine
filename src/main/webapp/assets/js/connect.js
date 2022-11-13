$(document).ready(function() {
	$("#connect").addClass("active")
	$("input").val('')
	$.validator.addMethod('checkId', function (value) { 
		return /^[1-9][0-9]*$/.test(value)
	})
	$("#connect_form").validate({
		rules : {
			id : {
				checkId : true,
				required : true
			}
		},
		errorPlacement: function(){},
		onkeyup: function(element) {
  					if ($(element).valid()){
						$(element).removeClass("is-invalid")
					}
					else {
						$(element).addClass("is-invalid")
					}
		},
		onfocusout: function(element) {
  					if ($(element).valid()){
						$(element).removeClass("is-invalid")
					}
					else {
						$(element).addClass("is-invalid")
					}
		},
		submitHandler : function(form){
			var url = $(form).attr("action");
			var req_data = "&" + $(form[0]).serialize()
			$("#message").empty()
		    $.ajax({
		            type: 'POST',
		            url: url,
		            data: req_data,
		            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		            success: function (res_data) {
						if (res_data == 1){
							var idMachine = $(form[0]).val()
							$(form).empty()
		            		$(form).removeClass("bg-white border border-dark")
		            		$('<div/>',{
        						id: 'message',
        						class:"d-flex flex-column justify-content-center align-items-center bg-white border border-darkorange p-5 fs-3"
    						}).appendTo(form);
		            		$('#message').html("<span>Connesso al distributore #" + idMachine + "</span>" + 
											"<a class='fs-5 mt-2' href=''>Disconnetti</a>")
						}
						else if (res_data == 0)
	            			$('#message').html("Connessione fallita. Distributore connesso ad un altro utente.")	
        				else if (res_data == 2)
	            			$('#message').html("Connessione fallita. Distributore inesistente.")
	            		else if (res_data == -1)
	            			$('#message').html("Connessione fallita. Errore del server.")	
	            		else
	            			window.location.href = res_data		            				
		            },
		            error: function(){
	            		$('#message').html("Connessione fallita. Problema di comunicazione con il server.")
					}
		    });
		}
	});
});