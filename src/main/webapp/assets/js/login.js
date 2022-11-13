$(document).ready(function() {
	$("#login").addClass("active")
	$("input").not("[type='radio']").val('')
	$.validator.addMethod('checkEmailId', function (value, element) { 
		if ($(element).attr('type') == "email")
			return /^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$/.test(value)
		else
			return /^[1-9][0-9]*$/.test(value)
	})
	$.validator.addMethod('checkPsw', function (value) { 
    	return /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$/.test(value); 
	})
	$("input[name='loginType']").change(function(){
		if($("input[name='loginType']:first").prop("checked") == true){
			$("input[name='emailId']").attr("type", "email")
			$("input[name='emailId']").attr("placeholder", "Email")
			$(".fa-hashtag").removeClass("fa-hashtag").addClass("fa-envelope")
			
		}
		else {
			$("input[name='emailId']").attr("type", "text")
			$("input[name='emailId']").attr("placeholder", "ID")
			$(".fa-envelope").removeClass("fa-envelope").addClass("fa-hashtag")
		}
		$("input").not("[type='radio']").val('')
		$("input").valid()
		$("input").removeClass("is-invalid")
	})
	$("#login_form").validate({
		rules : {
			emailId : {
				checkEmailId : true,
				required : true
			},
			psw : {
				checkPsw : true,
				required : true
			},
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
			},
		},
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
			var url = $(form).attr("action")
			var req_data = "&"
			for (var i=0; i<=3; i++){
				if (i==0 || i==1){
					if ($(form[i]).prop("checked") == true)
						req_data += $(form[i]).serialize() + "&"
				}
				else if (i==2)
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
						if (res_data == 0)
		            		$('#message').html("Login fallito. Credenziali non valide.")
						else if (res_data == -1)
							$('#message').html("Login fallito. Errore del server.")
		            	else {
							window.location.href = res_data
		            	}
		            },
		            error: function(){
	            		$('#message').html("Login fallito. Problema di comunicazione con il server.")
					}
		    });
		}
	});
});