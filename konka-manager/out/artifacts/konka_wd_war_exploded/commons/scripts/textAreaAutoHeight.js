// TextArea自动变高度（兼容IE，Firefox） 
// 调用：textAreaAutoHeight(document.getElementById("pay_comment"));
function textAreaAutoHeight(obj) {
	// IE浏览器
	if($.browser.msie) {
		$(obj).each(function(){  
	        this.onpropertychange = textAreaPropertyChange;
	    });  
	} else {
		obj.addEventListener("input",textAreaPropertyChange,false);
		// 非IE浏览器时，画面load不会自动调用textAreaPropertyChange方法，手动强制调用
		textAreaPropertyChange(obj);
	}
}

function textAreaPropertyChange(obj) {
    // IE浏览器
	if($.browser.msie) {
        if (this.scrollHeight > 16) {
    		this.style.posHeight = this.scrollHeight;
        }
	} else {

        	// 非IE浏览器时，画面load不会自动调用textAreaPropertyChange方法，手动强制调用
    		if (obj) {
        		try {
            		$(obj).html();

        			if (obj.scrollHeight > 16) {
	        			obj.style.height = obj.scrollHeight + "px";
        			}
            	}
        		catch(e) {
            		// Firefox通过addEventListener调用的时候，obj也有值，
            		// 通过异常来捕获Firefox默认传递的值还是用户传递进来的Html元素
            		// 暂时想不出更加好的方法了。。。
        			if (this.scrollHeight > 16) {
	        			this.style.height = this.scrollHeight + "px";
        			}
            	}
    		}
    		else
    		{
    			if (this.scrollHeight > 16) {
        			this.style.height = this.scrollHeight + "px";
    			}
    		}
	}
}