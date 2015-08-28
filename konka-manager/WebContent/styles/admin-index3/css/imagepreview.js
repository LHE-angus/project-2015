/*
 * Url preview script 
 * powered by jQuery (http://www.jquery.com)
 * 
 * written by Alen Grakalic (http://cssglobe.com)
 * 
 * for more info visit http://cssglobe.com/post/1695/easiest-tooltip-and-image-preview-using-jquery
 *
 */
 
this.screenshotPreview = function(){	
	$("a.screenshot").hover(function(e){
		var c = (this.t != "") ? "<br/>" + this.t : "";
		$("#theme_list").append("<p id='screenshot'><img src='"+ $(this).attr('data-rel') +"' alt='' /></p>");								 
		$("#screenshot").fadeIn("fast");						
    },
	function(){
		$("#screenshot").remove();
    });			
};


// starting the script on page load
$(document).ready(function(){
	screenshotPreview();
});