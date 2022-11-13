$(document).ready(function() {
	$("#account").addClass("active")
	$("input").val('')
	$.validator.addMethod('checkRecharge', function (value) { 
		return /^[0-9]{1,15}([.][0-9]{1,2})?$/.test(value)
	})
	$("#recharge_form").validate({
		rules : {
			recharge : {
				checkRecharge : true,
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
							$(form).empty()
		            		$(form).removeClass("bg-white border border-dark")
		            		$('<div/>',{
        						id: 'message',
        						class:"d-flex flex-column justify-content-center align-items-center bg-white border border-darkorange p-5 fs-3"
    						}).appendTo(form);
		            		$('#message').html("<span>Ricarica effettuata!</span>")
		            		setTimeout(function(){window.location.href = $("#home").prop("href")}, 2000)
						}
						else if (res_data == -1)
	            			$('#message').html("Ricarica fallita. Errore del server.")	
	            		else
	            			window.location.href = res_data		            				
		            },
		            error: function(){
	            		$('#message').html("Ricarica fallita. Problema di comunicazione con il server.")
					}
		    });
		}
	});
});