
/**
 * jQuery inputable plugin : simple inputable implements with ajax request & response
 * http://blog.csdn.net/xxd851116
 *
 * Create a inputable element with input, type of text. 
 *
 * @option inputName: Text input element name attribute; 
 * @option inputStyle: Text input element style, contains this input attribute size;
 * @option inputMaxLength|inputSize: Text input element maxLength|size attribute;
 * @option url: When the text input element blur, client request url;
 * @option param: request data, pattern: 'key/value';
 * @option paramProps: param property, pattern: '['prop1'(, 'prop2')*]';
 * @option loadingPath: loading image path on request doing;
 * @option onSuccess: executing function if request is success(status = 200);
 * @option onError: executing function if request is error(status = 404, 500..);
 *
 * @return jQuery
 *
 * Copyright (c) 2009 XingXiuDong
 *
 * Date: 2009-09-16
 * Version: 1.0
 * Version: 1.1, add loading image during requesting
 * Version: 1.2, add paramProps options to append request param with curent element propertys
 */
;(function($){
	$.fn.extend({
		inputable : function(settings) {
			var $this = this;
			
			var options = $.extend({
				inputName		:	0,		// input element name
				inputStyle		:	{},		// input element style
				inputSize		:	10,		// input element size
				inputMaxLength	:	10,		// input element maxlength
				url				:	"",		// request uri
				param			:	{},		// request params，pattern： key/value
				paramProps		: 	[],		// inputable's element property, to add param by property's value
				loadingPath		:	"",		// loading image path
				onSuccess		:	function(){},	// executed after success
				onError			:	function(){}	// executed after error
			}, settings);
			
			$this.each(function(){
				var $_cur = $(this);
				
				var $span = $("<span></span>");
				$span.text($.trim($_cur.text()));
				
				// no.1 element: span -> text
				$_cur.empty().append($span);
		
				// no.2 element: span -> input
				var $input = $("<input type=\"text\" name=\"" + options.inputName + "\" />")
					.attr("maxlength", options.inputMaxLength).attr("size", options.inputSize);
				$input.css(options.inputStyle).val($.trim($_cur.text()));
				$_cur.append($span.clone().empty().append($input).hide());
		
				// no.3 last element: img 
				var $loading;
				if (options.showLoading = options.loadingPath.length == 0 ? false : true) {
					$loading = $("<img src=\"" + options.loadingPath + "\" />").hide().appendTo($_cur);
				}
				
				$_cur.click(function(){
					$(this).find("span:first-child").hide().next().show().find("input").focus().select();
				});
		
				$_cur.find("span input[type='text']").blur(function() {
					$(this).parent().hide().prev().show();
				}).click(function(){
					return false;
				}).change(function() {
					if (options.url.length == 0) return false;
					
					if (options.showLoading) $loading.show();
					
					var $_this = $(this);
					options.param[options.inputName] = $_this.val();
					$.each(options.paramProps, function(index, paramProp){
						options.param[paramProp] = $_cur.attr(paramProp);
					});
					
					$.getJSON(options.url, options.param, function(json) {
						if (json.status == "200") {
							$_this.parent().prev().text($.trim(options.param[options.inputName]));
							if (options.showLoading) $loading.hide();
							options.onSuccess();
						} else {
							if (options.showLoading) $loading.hide();
							options.onError();
						}
					});
				});
			});
			
			return $this;
		}
	});
})(jQuery);