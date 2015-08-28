// YOU MUST IMPORT JQUERY LIB AT FIRST
$(document).ready(function(){
	$(".rtable2 tr").mouseover(function(){  
		$(this).addClass("over");
	}).mouseout(function(){
		$(this).removeClass("over");
	})
	$(".rtable2 tr:even").addClass("alt");
})