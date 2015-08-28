/**
 * please include jquery.js first
 * @author Jin,QingHua
 * @version 20081230
 */
var _effect = "slide"; // slide | show | fade =>['slide', 'show', 'fade'][Math.floor(Math.random() * 3)];
$(document).ready(function(){
	var reqURI = location.href || "", hrfURI = "";
	var reqPosition = 0, hrfPosition = 0;
//	var hasUlShow = false;
	$("a", "#column").each(function() {
		if (this.id == 'home') {
			return true;
		}
		reqPosition = reqURI.indexOf("?");
		if (reqPosition != -1) {
			reqURI = reqURI.substring(0, reqPosition);
		}
		
		hrfURI = this.href || "";
		hrfPosition = hrfURI.indexOf("?");
		if (hrfPosition != -1) {
			hrfURI = hrfURI.substring(0, hrfPosition);
		}
		//alert("1 => " + reqURI + "\n2 => " + hrfURI + "\n3 => " + reqURI.indexOf(hrfURI));
		if (reqURI.indexOf(hrfURI) != -1) {
			switch (_effect) {
				//case 'slide' : $(this).parent().parent().slideDown(); break;
				case 'slide'  : $(this).parent().parent().show(); break;
				case 'fade'  : $(this).parent().parent().fadeIn(); break;
			}
//			hasUlShow = true;
			return true;
		}
	});
//	if (!hasUlShow) {
//		$('ul:last', '#column').slideDown(); 
//	}
});

function slideUl($ul) {
	var $uls = $('ul:visible', '#column');
	if ($uls.size() == 0) {
		switch (_effect) {
			case 'slide' : $ul.slideDown(); break;
			case 'show'  : $ul.show(); break;
			case 'fade'  : $ul.fadeIn(); break;
		}
		return;
	}
	$uls.each(function(){
		if ($ul.attr("style") == $(this).attr("style")) {
			return true;
		}
		switch (_effect) {
			case 'slide' : 
				$(this).slideUp();
				$ul.slideDown();
				break;
			case 'show' : 
				$(this).hide();
				$ul.show();
				break;
			case 'fade' : 
				$(this).fadeOut();
				$ul.fadeIn();
				break;
		}
	});
}