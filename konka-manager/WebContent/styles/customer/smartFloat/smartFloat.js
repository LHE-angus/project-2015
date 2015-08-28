$.fn.smartFloat = function(marginTop) {
	//this.marginTop = marginTop;
	var position = function(element) {
		var top = element.position().top, pos = element.css("position");
		$(window).scroll(function() {
			var scrolls = $(this).scrollTop();
			//alert(scrolls + "___" + top);
			if (scrolls > top) { //如果滚动到页面超出了当前元素element的相对页面顶部的高度
				if (window.XMLHttpRequest) { //如果不是ie6
					element.css({
						position: "fixed",
						top: 0,
						marginTop: 0
					}).addClass("shadow");	
				} else { //如果是ie6
					element.css({
						position: "absolute",
						top: scrolls,
						marginTop: 0
					});	
				}
			}else {
				element.css({
					position: pos,//"absolute",//
					top: top,
					marginTop: marginTop 
				}).removeClass("shadow");	
			}
		});
	};
	return $(this).each(function() {
		position($(this));						 
	});
};