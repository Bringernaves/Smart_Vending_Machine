$(document).ready(function() {
    $("#account").addClass("active")
    $("input[name='psw']").val('')
    $("input[name='new_email']").val('')
    $("input[name='old_psw']").val('')
    $("input[name='new_psw']").val('')
    $("input[name='renew_psw']").val('')
    $("input[name='renew_psw']").prop("disabled", true)
    
    $.validator.addMethod('checkEmail', function (value) { 
		return /^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$/.test(value)
	})

    $.validator.addMethod('checkPsw', function (value) { 
		return /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$/.test(value); 	
	})
	
	$.validator.addMethod('notEqualToEmail', function (value) { 
		if ($("input[name='email']").prop("placeholder")!=value)
			return true;
		else
			return false;	
	})
	
	$.validator.addMethod('notEqualToOldPsw', function (value) { 
		if ($("input[name='new_psw']").val()!=$("input[name='old_psw']").val())
			return true;
		else
			return false;	
	})
	
    $("#changeEmail_form").validate({
		rules : {
			psw : {
				checkPsw : true,
				required : true
			},
			new_email : {
				checkEmail : true,
				required : true,
				notEqualToEmail : true
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
					}
					else {
						$(element).removeClass("is-valid").addClass("is-invalid")
					}	
		},
		onfocusout: function(element) {
  					if ($(element).valid()){
						$(element).removeClass("is-invalid").addClass("is-valid")
					}
					else {
						$(element).removeClass("is-valid").addClass("is-invalid")
					}	
		},
		submitHandler : function(form){
			var url = $(form).attr("action")
			var req_data = "&"
			req_data += $(form[0]).serialize() + "&"
			req_data += $(form[1]).serialize()
			$("input[name='psw']").removeClass("is-valid")
			$("input[name='new_email']").removeClass("is-valid")
			$("#message1").empty()
		    $.ajax({
		            type: 'POST',
		            url: url,
		            data: req_data,
		            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		            success: function (res_data) {
		            	if (res_data == 1){
		            		$("input[name='email']").prop("placeholder", $(form[1]).val())
		            		$("input[name='psw']").val('')
    						$("input[name='new_email']").val('')
    						$('#message1').html("Email modificata!")
    						setTimeout(function(){$('#message1').empty()},2000)
		            	}
		            	else if (res_data == 0){
							$("input[name='new_email']").val('')
							$("input[name='new_email']").addClass("is-invalid")
		            		$('#message1').html("Modifica fallita. Email gia' esistente.") 
						}
						else if (res_data == 2){
							$("input[name='email']").val('')
							$("input[name='email']").addClass("is-invalid")
		            		$('#message1').html("Modifica fallita. Password errata.") 
						}
						else
		            		$('#message1').html("Modifica fallita. Errore del server.") 
		            },
		            error: function(){
	            		$('#message1').html("Modifica fallita. Problema di comunicazione con il server.") 
					}
		    });
		}
	});
    $("#changePassword_form").validate({
		rules : {
			old_psw : {
				checkPsw : true,
				required : true
			},
			new_psw : {
				checkPsw : true,
				required : true,
				notEqualToOldPsw : true
			},
			renew_psw : {
				checkPsw : true,
				required : true,
				equalTo : "[name='new_psw']"
			}
		},
		errorPlacement: function(error, element){
			if (element.attr("name") == "old_psw" || element.attr("name") == "new_psw"){
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
			old_psw : {
				checkPsw : "Deve contenere almeno una lettera maiuscola, una minuscola e un numero. Minimo 8 caratteri.",
				required : "",
				psw : ""
			},
			new_psw : {
				checkPsw : "Deve contenere almeno una lettera maiuscola, una minuscola e un numero. Minimo 8 caratteri.",
				notEqualToOldPsw : "",
				required : "",
				psw : ""
			}
		},
		onkeyup: function(element) {
					if ($(element).valid()){
						$(element).removeClass("is-invalid").addClass("is-valid")
						if ($(element).is(element.form[1]))
							$(element.form[2]).prop("disabled", false)
					}
					else {
						$(element).removeClass("is-valid").addClass("is-invalid")
						if ($(element).is(element.form[1]))
							$(element.form[2]).prop("disabled", true)
					}	
					
					if ($(element).is(element.form[1]) && $(element.form[2]).val()!=''){
						$(element.form[2]).val('')
						
						if ($(element.form[2]).hasClass("is-valid"))
							$(element.form[2]).removeClass("is-valid")
							
						if ($(element.form[2]).hasClass("is-invalid"))
							$(element.form[2]).removeClass("is-invalid")
					}
		},
		onfocusout: function(element) {
					if ($(element).valid()){
						$(element).removeClass("is-invalid").addClass("is-valid")
						if ($(element).is(element.form[1]))
							$(element.form[2]).prop("disabled", false)
					}
					else {
						$(element).removeClass("is-valid").addClass("is-invalid")
						if ($(element).is(element.form[1]))
							$(element.form[2]).prop("disabled", true)
					}	
					
					if ($(element).is(element.form[1]) && $(element.form[2]).val()!=''){
						$(element.form[2]).val('')
						
						if ($(element.form[2]).hasClass("is-valid"))
							$(element.form[2]).removeClass("is-valid")
							
						if ($(element.form[2]).hasClass("is-invalid"))
							$(element.form[2]).removeClass("is-invalid")
					}
		},
		submitHandler : function(form){
			var url = $(form).attr("action")
			var req_data = "&"
			req_data += $(form[0]).serialize() + "&"
			req_data += $(form[1]).serialize()
			$("input[name='old_psw']").removeClass("is-valid")
			$("input[name='new_psw']").removeClass("is-valid")
			$("input[name='renew_psw']").removeClass("is-valid")
			$("#message2").empty()
		    $.ajax({
		            type: 'POST',
		            url: url,
		            data: req_data,
		            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		            success: function (res_data) {
		            	if (res_data == 1){
		            		$("input[name='old_psw']").val('')
							$("input[name='new_psw']").val('')
							$("input[name='renew_psw']").val('')
							$('#message2').html("Password modificata!")
							setTimeout(function(){$('#message2').empty()},2000)
		            	}
		            	else if (res_data == 0){
							$("input[name='email']").val('')
							$("input[name='email']").addClass("is-invalid")
		            		$('#message2').html("Modifica fallita. Password errata.") 
						}
						else
		            		$('#message2').html("Modifica fallita. Errore del server.") 
		            },
		            error: function(){
	            		$('#message2').html("Modifica fallita. Problema di comunicazione con il server.") 
					}
		    });
		}
	});
});