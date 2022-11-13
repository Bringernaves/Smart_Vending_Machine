$(document).ready(function(){
    var sell_form = $("#sell_form").clone()
    var url = $("#sell_form").attr("action")
	
   	function sellProduct(){
		$("#sell_form").submit(function(e){
	        e.preventDefault()
	        var form = $(this)
	        $.ajax({
	            type: 'POST',
	            url: url,
	            data: form.serialize(),
	            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	            success: function (res_data) {
	                if (res_data == 1){
	                    $('#message').html("Acquisto effettuato!")
	                    var pair = form.serialize().split("=")
	                    var id = pair[1]
	                    var input = $("input[value="+id+"]")
	                    var qty = parseInt(input.parent().prev().html())
	                    qty-=1
	                    if (qty > 0) {
		                    input.parent().prev().html(qty + " pz.")
		                    input.prop("checked", false)
	                    }
	                    else
	                    	input.parent().parent().remove()
	                }
	                else if (res_data == 0)
	                    $('#message').html("Acquisto fallito. Credito insufficiente.")
	                else if (res_data == -1)
	                    $('#message').html("Acquisto fallito. Errore del server.")
	                else {
	                    window.location.href = res_data
	                }
	            },
	            error: function(){
	                $('#message').html("Acquisto fallito. Problema di comunicazione con il server.")
	            }
	        })
    	})
	}
	function checkStatus(){
	 	$("input").change(function(){
			$("#message").html("")
		})
		
        $.ajax({
            type: 'POST',
            url: url,
            success: function (res_data) {
                if (typeof res_data == 'object'){
                    $("#content").empty()
                    tmp = sell_form.clone()
                    tmp.removeClass("d-none")
                    tmp.appendTo("#content");
                    if (res_data.length!=0) {
                        for (var i=0; i<res_data.length; i++){
                            $("tbody").append("<tr>" + 
                                                "<td>" + 
                                                    res_data[i].name + 
                                                "</td>" +
                                                "<td>" + 
                                                    res_data[i].price + 
                                                "&euro;</td>" +
                                              	"<td>" + 
                                                    res_data[i].qty + 
                                                " pz.</td>" +
                                                "<td>" + 
                                                    "<input class='form-check-input' type='radio' name='id' value='" + res_data[i].id + "'>" + 
                                                "</td>" + 
                                            "</tr>")
                        }
                    }
                    else {
                        $("#content").empty()
                        $('<div/>',{
                            id: 'message',
                            class:"d-flex flex-column justify-content-center align-items-center m-auto bg-white border border-darkorange p-5 fs-3"
                        }).appendTo("#content");
                        $('#message').html("Non ci sono prodotti disponibili.")
                        
                    }
                    sellProduct()
                 	setTimeout(checkStatus, 3000)
                }
                else if (res_data == 2){ 
                    var idMachine = $("#id").html()
                    $("#content").empty()
                    $('<div/>',{
                        id: 'message',
                        class:"d-flex flex-column justify-content-center align-items-center m-auto bg-white border border-darkorange p-5 fs-3"
                    }).appendTo("#content");
                    $('#message').html("<span>Disconnesso</span><span class='fs-6'>Connettiti dal tuo dispositivo inserendo " + idMachine + ".")
                   	sellProduct()
                 	setTimeout(checkStatus, 3000)
                }
                else if (res_data == 0)
                    setTimeout(checkStatus, 3000);
                else if (res_data == -1) {
                    $("#content").empty()
                    $('<div/>',{
                        id: 'message',
                        class:"d-flex flex-column justify-content-center align-items-center m-auto bg-white border border-darkorange p-5 fs-3"
                    }).appendTo("#content");
                    $('#message').html("<span>Disconnesso</span><span class='fs-6'>Errore del server.")	
                    sellProduct()
                 	setTimeout(checkStatus, 3000)
                }	
                else
                    window.location.href = res_data		            				
            },
            error: function(){
                $("#content").empty()
                $('<div/>',{
                    id: 'message',
                    class:"d-flex flex-column justify-content-center align-items-center m-auto bg-white border border-darkorange p-5 fs-3"
                }).appendTo("#content");
                $('#message').html("<span>Disconnesso</span><span class='fs-6'>Problema di comunicazione con il server.")
            }
        })
    }
  	checkStatus()
})
