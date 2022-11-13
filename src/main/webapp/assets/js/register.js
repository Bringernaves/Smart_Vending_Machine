$(document).ready(function() {
    $("#register").addClass("active")
    $("input").val('')
    $("input[name='repsw']").prop("disabled", true)
    
    $.validator.addMethod('checkEmail', function (value) { 
		return /^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$/.test(value)
	})
	
    $.validator.addMethod('checkPsw', function (value) { 
		return /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$/.test(value); 	
	})
	
    $("#register_form").validate({
		rules : {
			firstname : {
				required : true
			},
			lastname : {
				required : true
			},
			email : {
				checkEmail : true,
				required : true
			},
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
						if ($(element).is(element.form[3]))
							$(element.form[4]).prop("disabled", false)
					}
					else {
						$(element).removeClass("is-valid").addClass("is-invalid")
						if ($(element).is(element.form[3]))
							$(element.form[4]).prop("disabled", true)
					}	
					
					if ($(element).is(element.form[3]) && $(element.form[4]).val()!=''){
						$(element.form[4]).val('')
						
						if ($(element.form[4]).hasClass("is-valid"))
							$(element.form[4]).removeClass("is-valid")
							
						if ($(element.form[4]).hasClass("is-invalid"))
							$(element.form[4]).removeClass("is-invalid")
					}
		},
		onfocusout: function(element) {
  					if ($(element).valid()){
						$(element).removeClass("is-invalid").addClass("is-valid")
						if ($(element).is(element.form[3]))
							$(element.form[4]).prop("disabled", false)
					}
					else {
						$(element).removeClass("is-valid").addClass("is-invalid")
						if ($(element).is(element.form[3]))
							$(element.form[4]).prop("disabled", true)
					}
					
					if ($(element).is(element.form[3]) && $(element.form[4]).val()!=''){
						$(element.form[4]).val('')
						
						if ($(element.form[4]).hasClass("is-valid"))
							$(element.form[4]).removeClass("is-valid")
							
						if ($(element.form[4]).hasClass("is-invalid"))
							$(element.form[4]).removeClass("is-invalid")
					}
		},
		submitHandler : function(form){
			var url = $(form).attr("action")
			var req_data = "&"
			for (var i=0; i<=3; i++){
				if (i!=3)
					req_data += $(form[i]).serialize() + "&"
				else
					req_data += $(form[i]).serialize()
			}
			$("#message").empty()
		    $.ajax({
		            type: 'POST',
		            url: url,
		            data: req_data,
		            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		            success: function (res_data) {
		            	if (res_data == 1){
		            		$(form).empty()
		            		$(form).removeClass("bg-white border-start border-end border-dark")
		            		$('<div/>',{
        						id: 'message',
        						class:"d-flex justify-content-center bg-white border border-darkorange p-5 fs-3"
    						}).appendTo(form);
		            		$('#message').html("Registrazione effettuata!") 
		            	}
		            	else if (res_data == 0){
							$("input[name='email']").val('')
							$("input[name='email']").addClass("is-invalid")
		            		$('#message').html("Registrazione fallita. Email gia' esistente.") 
						}
						else
		            		$('#message').html("Registrazione fallita. Errore del server.") 
		            },
		            error: function(){
	            		$('#message').html("Registrazione fallita. Problema di comunicazione con il server.") 
					}
		    });
		}
	});
});
