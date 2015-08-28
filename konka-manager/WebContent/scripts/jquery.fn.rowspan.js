(function($){
	$.fn.extend({
		rowspan : function() {
			var that;
			$(this).each(function(){
				if ($(this).text() == $(that).text()) {
					rowspan = $(that).attr("rowspan");
					if (rowspan == undefined) {
						$(that).attr("rowspan", 1);   
						rowspan = $(that).attr("rowspan");   
					}
					rowspan = Number(rowspan) + 1;
					$(that).attr("rowspan", rowspan); // do your action for the colspan cell here
					$(this).hide(); // .remove(); // do your action for the old cell here
				} else {
					that = this;
				}
			})
		}
	});
})(jQuery);