/**
 * Class Pager
 * 
 * @author kim
 * @version build 2006-11-01
 * @version build 2007-06-25 add en version
 * @version build 2008-03-10 add reg pattern to void input not integer value
 * @useage 1.import this file 2.coding as following... var pager = new
 *         Pager(document.forms[0], 692, 20, 3); pager.addHiddenInputs("id",
 *         "45"); document.write(pager.toString());
 */
 
 /**
	 * constructor
	 * 
	 * @param form
	 *            a form object
	 * @param recordCount
	 *            record count
	 * @param pageSize
	 *            page size
	 * @param currentPage
	 *            current page
	 * @param lang
	 *            default is zh_CN
	 */
function Pager(form, recordCount, pageSize, currentPage, lang) {
    this.form = form;
    this.recordCount = recordCount;
    this.pageSize = pageSize;
    this.currentPage = currentPage;
    this.lang = lang || "cn";
    this._hiddenInputNameAndValues = [];
}
/**
 * to buile a html hidden input element
 * 
 * @param name
 *            element name and id
 * @param value
 *            element value
 */
Pager.prototype.addHiddenInputs = function (name, value) {
	this._hiddenInputNameAndValues[this._hiddenInputNameAndValues.length] = new Array(name, value);
};

/**
 * go to a specific page
 * 
 * @param the
 *            page
 */
Pager.prototype.gotoPage = function(page) {
	this.form.requestPage.value = page;
	this.form.submit();
};

/**
 * pager string
 */
Pager.prototype.toString = function (){
	var pager = this;
	var pagerStrings = [];

	// BUG FIX: kim at 2006/11/02 12:50
	// IMPORTANT: Math.ceil return double in Java but return integer in
	// Javascript
	// OLD CODE: var pageCount = parseInt(Math.ceil((this.recordCount +
	// this.pageSize - 1) / this.pageSize));
	var pageCount = Math.ceil(this.recordCount / this.pageSize);

	var previousPage = this.currentPage - 1;
	var firstPage = 1;
	var nextPage = this.currentPage + 1;
	var lastPage = pageCount;
	
	switch (this.lang) {
		case "cn":
			pagerStrings[pagerStrings.length] = '\u5171<span style="color:#FF0000"> ' + this.recordCount + ' </span>\u6761 ';
		//	pagerStrings[pagerStrings.length] = '\u6bcf\u9875\u663e\u793a<span style="color:#FF0000"> ' + this.pageSize + ' </span>\u6761 ';
		//	pagerStrings[pagerStrings.length] = '\u5206<span style="color:#FF0000"> ' + pageCount + ' </span>\u9875 ';
			if (this.currentPage == 1 || pageCount <= 1) {
				pagerStrings[pagerStrings.length] = '<span style="color:#CCCCCC;"> \u9996\u9875 \u4e0a\u4e00\u9875 </span>';
			} else {
				pagerStrings[pagerStrings.length] = '<span style="cursor:pointer;text-decoration: underline;" onclick="pager.gotoPage(1);">\u9996\u9875</span> <span style="cursor:pointer;text-decoration: underline;" onclick="pager.gotoPage(' + previousPage + ');">\u4e0a\u4e00\u9875</span> ';
			}
			if (this.currentPage == pageCount || pageCount <= 1) {
				pagerStrings[pagerStrings.length] = '<span style="color:#CCCCCC;"> \u4e0b\u4e00\u9875 \u5c3e\u9875</span>';
			} else {
				pagerStrings[pagerStrings.length] = '<span style="cursor:pointer;text-decoration: underline;" onclick="pager.gotoPage(' + nextPage + ');">\u4e0b\u4e00\u9875</span> <span style="cursor:pointer;text-decoration: underline;" onclick="pager.gotoPage(' + lastPage + ');">\u5c3e\u9875</span> ';
			}
			pagerStrings[pagerStrings.length] = ' \u7b2c <input style="text-align:center;border: 1px solid #CCCCCC;" name="pager.requestPage" type="text" id="requestPage" size="4" maxlength="6" value="' + this.currentPage + '" onchange="this.value = isInteger(this.value) ? this.value : 1" /> \u9875 ';
			pagerStrings[pagerStrings.length] = '<input class="bgButton" name="buttonsubmitPage" type="submit" id="buttonsubmitPage" value="\u8f6c\u5230" onclick="this.form.requestPage.value = isInteger(this.form.requestPage.value) ? this.form.requestPage.value : 1" />';
			break;
		case "msg":
			//pagerStrings[pagerStrings.length] = '\u5171<span style="color:#FF0000"> ' + this.recordCount + ' </span>\u6761\u6d88\u606f ';//\u8bb0\u5f55 ';
			//pagerStrings[pagerStrings.length] = '\u6bcf\u9875\u663e\u793a<span style="color:#FF0000"> ' + this.pageSize + ' </span>\u6761 ';
			//pagerStrings[pagerStrings.length] = '\u5206<span style="color:#FF0000"> ' + pageCount + ' </span>\u9875 ';
			if (this.currentPage == 1 || pageCount <= 1) {
				pagerStrings[pagerStrings.length] = '<span style="color:#CCCCCC;"> \u9996\u9875 \u4e0a\u4e00\u9875 </span>';
			} else {
				pagerStrings[pagerStrings.length] = '<span style="cursor:pointer;text-decoration: underline;" onclick="pager.gotoPage(1);">\u9996\u9875</span> <span style="cursor:pointer;text-decoration: underline;" onclick="pager.gotoPage(' + previousPage + ');">\u4e0a\u4e00\u9875</span> ';
			}
			if (this.currentPage == pageCount || pageCount <= 1) {
				pagerStrings[pagerStrings.length] = '<span style="color:#CCCCCC;"> \u4e0b\u4e00\u9875 \u5c3e\u9875</span>';
			} else {
				pagerStrings[pagerStrings.length] = '<span style="cursor:pointer;text-decoration: underline;" onclick="pager.gotoPage(' + nextPage + ');">\u4e0b\u4e00\u9875</span> <span style="cursor:pointer;text-decoration: underline;" onclick="pager.gotoPage(' + lastPage + ');">\u5c3e\u9875</span> ';
			}
			pagerStrings[pagerStrings.length] = ' \u7b2c <input style="text-align:center;border: 1px solid #CCCCCC;" name="pager.requestPage" type="text" id="requestPage" size="4" maxlength="6" value="' + this.currentPage + '" onchange="this.value = isInteger(this.value) ? this.value : 1" /> \u9875 ';
			pagerStrings[pagerStrings.length] = '<input class="bgButton" name="buttonsubmitPage" type="submit" id="buttonsubmitPage" value="\u8f6c\u5230" onclick="this.form.requestPage.value = isInteger(this.form.requestPage.value) ? this.form.requestPage.value : 1" />';
			break;
		default:
			//pagerStrings[pagerStrings.length] = 'Total <span style="color:#FF0000"> ' + this.recordCount + ' </span> ';
			//pagerStrings[pagerStrings.length] = 'Page <span style="color:#FF0000"> ' + pageCount + ' </span> of ';
			//pagerStrings[pagerStrings.length] = '<span style="color:#FF0000"> ' + this.pageSize + ' </span> ';
			if (this.currentPage == 1 || pageCount <= 1) {
				pagerStrings[pagerStrings.length] = '<span style="color:#CCCCCC;"> \u9996\u9875 \u4e0a\u4e00\u9875 </span>';
			} else {
				pagerStrings[pagerStrings.length] = '<span style="cursor:pointer;text-decoration: underline;" onclick="pager.gotoPage(1);">\u9996\u9875</span> <span style="cursor:pointer;text-decoration: underline;" onclick="pager.gotoPage(' + previousPage + ');">\u4e0a\u4e00\u9875</span> ';
			}
			if (this.currentPage == pageCount || pageCount <= 1) {
				pagerStrings[pagerStrings.length] = '<span style="color:#CCCCCC;"> \u4e0b\u4e00\u9875 \u5c3e\u9875</span>';
			} else {
				pagerStrings[pagerStrings.length] = '<span style="cursor:pointer;text-decoration: underline;" onclick="pager.gotoPage(' + nextPage + ');">\u4e0b\u4e00\u9875</span> <span style="cursor:pointer;text-decoration: underline;" onclick="pager.gotoPage(' + lastPage + ');">\u5c3e\u9875</span> ';
			}
			pagerStrings[pagerStrings.length] = ' \u7b2c <input style="text-align:center;border: 1px solid #CCCCCC;" name="pager.requestPage" type="text" id="requestPage" size="4" maxlength="6" value="' + this.currentPage + '" onchange="this.value = isInteger(this.value) ? this.value : 1" /> \u9875 ';
			pagerStrings[pagerStrings.length] = '<input class="bgButton" name="buttonsubmitPage" type="submit" id="buttonsubmitPage" value="\u8f6c\u5230" onclick="this.form.requestPage.value = isInteger(this.form.requestPage.value) ? this.form.requestPage.value : 1" />';
			break;
	}
	var _hiddenInputStrings = [];
	for (var i = 0; i < this._hiddenInputNameAndValues.length; i++){
		_hiddenInputStrings[_hiddenInputStrings.length] = '<input name="' + this._hiddenInputNameAndValues[i][0] + '" type="hidden" id="' + this._hiddenInputNameAndValues[i][0] + '" value="' + this._hiddenInputNameAndValues[i][1] + '" />';
	}
	pagerStrings[pagerStrings.length] = _hiddenInputStrings.join("").toString();
	return pagerStrings.join("").toString();
};

function isInteger(value) {
	return /^[-\+]?\d+$/.test(value)
}

function checkAll(e) {
	for (var i = e.form.elements.length - 1; i > 0; i--) {
		if (e.form.elements[i].type == "checkbox" && e.form.elements[i].disabled == false) {
			e.form.elements[i].checked = e.checked;
		}
	}
}

function confirmDeleteAll(form) {
	var checkedCount = 0;
	if (!form.pks) {
		return;
	}
	if(!form.pks.length) {
		if (form.pks.checked == true) {
			checkedCount = 1;
		}
	}
	for (var i = 0; i < form.pks.length; i++) {
		if (form.pks[i].checked == true) {
			checkedCount++;
		}
	}
	if (checkedCount == 0) {
		alert("\u8bf7\u81f3\u5c11\u9009\u62e9\u4e00\u4e2a\u5220\u9664\u9879\uff01");
	} else {
		if(confirm("\u786e\u5b9a\u8981\u5220\u9664\u6240\u6709\u9009\u4e2d\u7684\u9879\u5417\uff1f")) {
			form.method.value = "delete";
			form.submit();
		}
	}
}

function confirmSubmitAll(msg, methord, form) {
	if(msg != null)
	{
		if(!confirm(msg))
			return false;
	}
	var checkedCount = 0;
	if (!form.pks) {
		return;
	}
	if(!form.pks.length) {
		if (form.pks.checked == true) {
			checkedCount = 1;
		}
	}
	for (var i = 0; i < form.pks.length; i++) {
		if (form.pks[i].checked == true) {
			checkedCount++;
		}
	}
	if (checkedCount == 0) {
		alert("\u8bf7\u81f3\u5c11\u9009\u62e9\u4e00\u4e2a\u5220\u9664\u9879\uff01");
	} else {
		form.method.value = methord;
		form.submit();
	}
}

function confirmOperateAll(msg, methord, form) {
	if(msg != null)
	{
		if(!confirm(msg))
			return false;
	}
	var checkedCount = 0;
	if (!form.pks) {
		return;
	}
	if(!form.pks.length) {
		if (form.pks.checked == true) {
			checkedCount = 1;
		}
	}
	for (var i = 0; i < form.pks.length; i++) {
		if (form.pks[i].checked == true) {
			checkedCount++;
		}
	}
	if (checkedCount == 0) {
		alert("\u8bf7\u81f3\u5c11\u9009\u62e9\u4e00\u4e2a\u64CD\u4F5C\u9879\uFF01");
	} else {
		form.method.value = methord;
		form.submit();
	}
}



function confirmSubmitAllAjax(msg, methord, form, refresh, url) {
	if(msg != null)
	{
		if(!confirm(msg))
			return false;
	}
	var checkedCount = 0;
	if (!form.pks) {
		return;
	}
	if(!form.pks.length) {
		if (form.pks.checked == true) {
			checkedCount = 1;
		}
	}
	for (var i = 0; i < form.pks.length; i++) {
		if (form.pks[i].checked == true) {
			checkedCount++;
		}
	}
	if (checkedCount == 0) {
		alert("\u8bf7\u81f3\u5c11\u9009\u62e9\u4e00\u4e2a\u5220\u9664\u9879\uff01");
	} else {
		form.method.value = methord;
		form.submit();
	}
}

function confirmCheckAll(form) {
	var checkedCount = 0;
	if (!form.pks) {
		return;
	}
	if(!form.pks.length) {
		if (form.pks.checked == true) {
			checkedCount = 1;
		}
	}
	for (var i = 0; i < form.pks.length; i++) {
		if (form.pks[i].checked == true) {
			checkedCount++;
		}
	}
	if (checkedCount == 0) {
		alert("\u8bf7\u81f3\u5c11\u9009\u62e9\u4e00\u4e2a\u9009\u4e2d\u9879\uff01");
	} else {
		if(confirm("\u786e\u5b9a\u8981\u64CD\u4F5C\u6240\u6709\u9009\u4e2d\u7684\u9879\u5417\uff1f")) {
			form.method.value = "delete";
			form.submit();
		}
	}
}

function confirmDelete(msg, page, queryString, method) {
	msg  = msg  || "\u786e\u5b9a\u5220\u9664\u8fd9\u6761\u4fe1\u606f\u5417\uff1f";
	page = page || "?";
	page = page.indexOf("?") != -1 ? page : (page + "?");
	method = method || "delete";
	if(confirm(msg)){
		location.href = page + "method=" + method + "&" + encodeURI(queryString);
	}
}

function confirmUpdate(msg, page, queryString, method) {
	// msg = msg ||
	// "\u786e\u5b9a\u4fee\u6539\u8fd9\u6761\u4fe1\u606f\u5417\uff1f";
	page = page || "?";
	page = page.indexOf("?") != -1 ? page : (page + "?");
	method = method || "edit";
	location.href = page  + "method=" + method + "&" + encodeURI(queryString);
}

function doNeedMethod(msg, page, method, queryString) {
	// msg = msg ||
	// "\u786e\u5b9a\u4fee\u6539\u8fd9\u6761\u4fe1\u606f\u5417\uff1f";
	if(msg != null) {
		if(!confirm(msg))
			return false;
	}
	page = page || "?";
	page = page.indexOf("?") != -1 ? page : (page + "?");
	var keySeq = "";
	location.href = page  + "method=" + method + "&" + encodeURI(queryString);
}

function doAjaxMethod(msg, page, method, queryString, refresh, url){
	if(msg != null)
	{
		if(!confirm(msg))
			return false;
	}
	page = page || "?";
	page = page.indexOf("?") != -1 ? page : (page + "?");
    $.ajax({
        type: "POST",
        url: page  + "method=" + method + "&" + encodeURI(queryString),
        success: function(data) {
    		if(refresh == true){
    			location.replace(url);
    		}
        }
    });
}
