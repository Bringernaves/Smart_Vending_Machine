$(document).ready(function() {
    $("a.nav-link, a.dropdown-item").hover(
        function() {
			if (!($(this).hasClass("disabled"))){
				$(this).css("text-decoration", "underline");
				$(this).addClass("bg-gold");
			}
        },
        function(){
            if (!($(this).hasClass("disabled"))){
				$(this).css("text-decoration", "none");
				$(this).removeClass("bg-gold");
			}
        }
    );
});
