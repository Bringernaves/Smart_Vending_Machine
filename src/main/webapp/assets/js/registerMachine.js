$(document).ready(function() {
    $("#register").addClass("active")
    $("input").val('')
    $("input[name='repsw']").prop("disabled", true)
    $.validator.addMethod('checkPsw', function (value) { 
		return /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$/.test(value); 	
	})
    $("#registration_form").validate({
		rules : {
			psw : {
				checkPsw : true,
				required : true
			},
			repsw : {
				checkPsw : true,
				required : true,
				equalTo : "[name='psw']"
			}
		},
		errorPlacement: function(error, element){
			if (element.attr("name") == "psw"){
				if (error.html()!=""){
					error.css("font-size", "small")
					error.appendTo(element.next())
				}
				else {
					element.next().empty()
				}
			}
		},
		messages : {
			psw : {
				checkPsw : "Deve contenere almeno una lettera maiuscola, una minuscola e un numero. Minimo 8 caratteri.",
				required : "",
				psw : ""
			}
		},
		onkeyup: function(element) {
  					if ($(element).valid()){
						$(element).removeClass("is-invalid").addClass("is-valid")
						if ($(element).is(element.form[0]))
							$(element.form[1]).prop("disabled", false)
					}
					else {
						$(element).removeClass("is-valid").addClass("is-invalid")
						if ($(element).is(element.form[0]))
							$(element.form[1]).prop("disabled", true)
					}
					
					if ($(element).is(element.form[0]) && $(element.form[1]).val()!=''){
						$(element.form[1]).val('')
						
						if ($(element.form[1]).hasClass("is-valid"))
							$(element.form[1]).removeClass("is-valid")
							
						if ($(element.form[1]).hasClass("is-invalid"))
							$(element.form[1]).removeClass("is-invalid")
					}
		},
		onfocusout: function(element) {
  					if ($(element).valid()){
						$(element).removeClass("is-invalid")
						$(element).addClass("is-valid")
						if ($(element).is(element.form[0]))
							$(element.form[1]).prop("disabled", false)
					}
					else {
						$(element).removeClass("is-valid")
						$(element).addClass("is-invalid")
						if ($(element).is(element.form[0]))
							$(element.form[1]).prop("disabled", true)
					}
					
					if ($(element).is(element.form[0]) && $(element.form[1]).val()!=''){
						$(element.form[1]).val('')
						
						if ($(element.form[1]).hasClass("is-valid"))
							$(element.form[1]).removeClass("is-valid")
							
						if ($(element.form[1]).hasClass("is-invalid"))
							$(element.form[1]).removeClass("is-invalid")
					}
		},
		submitHandler : function(form){
			var url = $(form).attr("action")
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
        						class:"d-flex justify-content-center bg-white border border-darkorange p-5 fs-3"
    						}).appendTo(form);
		            		$('#message').html("Registrazione effettuata!") 
		            	}
		            	else if (res_data == -1)
		            		$('#message').html("Registrazione fallita. Errore del server.") 
						else
							window.location.href = res_data		
		            },
		            error: function(){
	            		$('#message').html("Registrazione fallita. Problema di comunicazione con il server.") 
					}
		    });
		}
	});
});
