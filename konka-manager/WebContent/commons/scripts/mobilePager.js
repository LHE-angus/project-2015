function Pager(form, recordCount, pageSize, currentPage, lang) {
	this.form = form;
	this.recordCount = recordCount;
	this.pageSize = pageSize;
	this.currentPage = currentPage;
	this.lang = lang || "cn";
	this._hiddenInputNameAndValues = [];
}

Pager.prototype.addHiddenInputs = function(name, value) {
	this._hiddenInputNameAndValues[this._hiddenInputNameAndValues.length] = new Array(
			name, value);
};

Pager.prototype.gotoPage = function(page) {
	this.form.requestPage.value = page;
	this.form.submit();
};

Pager.prototype.toString = function() {
	var pager = this;
	var pagerStrings = [];
	var pageCount = Math.ceil(this.recordCount / this.pageSize);

	var previousPage = this.currentPage - 1;
	var firstPage = 1;
	var nextPage = this.currentPage + 1;
	var lastPage = pageCount;

	switch (this.lang) {
	case "cn":
		pagerStrings[pagerStrings.length] = '<input type="hidden" id="requestPage" name="requestPage"/>';
		if (this.currentPage == 1 || pageCount <= 1) {
			pagerStrings[pagerStrings.length] = '<span data-role="button" data-icon="arrow-l" data-theme="a" data-inline="true">\u4e0a\u9875</span>';
		} else {
			pagerStrings[pagerStrings.length] = '<a id="goback" name="goback" data-role="button" data-icon="arrow-l" data-theme="c" data-inline="true">\u4e0a\u9875</a> ';
		}
		if (this.currentPage == pageCount || pageCount <= 1) {
			pagerStrings[pagerStrings.length] = '<span data-role="button" data-icon="arrow-r" data-theme="a" data-inline="true">\u4e0b\u9875</span>';
		} else {
			pagerStrings[pagerStrings.length] = '<a id="goforward" name="goforward" onclick="pager.gotoPage('
				+ nextPage
				+ ');" data-role="button" data-icon="arrow-r" data-theme="c" data-inline="true">\u4e0b\u9875</a>';
		}
		break;
	case "msg":
		pagerStrings[pagerStrings.length] = '\u5171<span style="color:#FF0000"> ' + this.recordCount + ' </span>\u6761\u6d88\u606f ';// \u8bb0\u5f55
		if (this.currentPage == 1 || pageCount <= 1) {
			pagerStrings[pagerStrings.length] = '<span style="color:#CCCCCC;"> \u9996\u9875 \u4e0a\u4e00\u9875 </span>';
		} else {
			pagerStrings[pagerStrings.length] = '<span style="cursor:pointer;text-decoration: underline;" onclick="pager.gotoPage(1);">\u9996\u9875</span> <span style="cursor:pointer;text-decoration: underline;" onclick="pager.gotoPage(' + previousPage + ');">\u4e0a\u4e00\u9875</span> ';
		}
		if (this.currentPage == pageCount || pageCount <= 1) {
			pagerStrings[pagerStrings.length] = '<span style="color:#CCCCCC;"> \u4e0b\u4e00\u9875 \u5c3e\u9875</span>';
		} else {
			pagerStrings[pagerStrings.length] = '<span style="cursor:pointer;text-decoration: underline;" onclick="pager.gotoPage('
					+ nextPage
					+ ');">\u4e0b\u4e00\u9875</span> <span style="cursor:pointer;text-decoration: underline;" onclick="pager.gotoPage('
					+ lastPage + ');">\u5c3e\u9875</span> ';
		}
		pagerStrings[pagerStrings.length] = ' \u7b2c <input style="text-align:center;border: 1px solid #CCCCCC;" name="pager.requestPage" type="text" id="requestPage" size="3" maxlength="6" value="' + this.currentPage + '" onchange="this.value = isInteger(this.value) ? this.value : 1" /> \u9875 ';
		pagerStrings[pagerStrings.length] = '<input class="bgButton" name="buttonsubmitPage" type="submit" id="buttonsubmitPage" value="\u8f6c\u5230" onclick="this.form.requestPage.value = isInteger(this.form.requestPage.value) ? this.form.requestPage.value : 1" />'
		break;
	default:
		pagerStrings[pagerStrings.length] = 'Total <span style="color:#FF0000"> ' + this.recordCount + ' </span> ';
		pagerStrings[pagerStrings.length] = 'Page <span style="color:#FF0000"> ' + pageCount + ' </span> of ';
		pagerStrings[pagerStrings.length] = '<span style="color:#FF0000"> ' + this.pageSize + ' </span> ';
		if (this.currentPage == 1 || pageCount <= 1) {
			pagerStrings[pagerStrings.length] = '<span style="color:#CCCCCC;"> [First Page] [Previous Page] </span>';
		} else {
			pagerStrings[pagerStrings.length] = '<span style="cursor:pointer;text-decoration: underline;" onclick="pager.gotoPage(1);"> [First Page] </span> <span style="cursor:pointer;text-decoration: underline;" onclick="pager.gotoPage(' + previousPage + ');"> [Previous Page] </span> ';
		}
		if (this.currentPage == pageCount || pageCount <= 1) {
			pagerStrings[pagerStrings.length] = '<span style="color:#CCCCCC;"> [Next Page] [Last Page] </span>';
		} else {
			pagerStrings[pagerStrings.length] = '<span style="cursor:pointer;text-decoration: underline;" onclick="pager.gotoPage('
					+ nextPage
					+ ');"> [Next Page] </span> <span style="cursor:pointer;text-decoration: underline;" onclick="pager.gotoPage('
					+ lastPage + ');"> [Last Page] </span> ';
		}
		pagerStrings[pagerStrings.length] = ' <input style="text-align:center;border: 1px solid #CCCCCC;" name="pager.requestPage" type="text" id="requestPage" size="4" maxlength="6" value="' + this.currentPage + '" onchange="this.value = isInteger(this.value) ? this.value : 1" /> ';
		pagerStrings[pagerStrings.length] = '<input name="buttonsubmitPage" type="submit" id="buttonsubmitPage" value="go" onclick="this.form.requestPage.value = isInteger(this.form.requestPage.value) ? this.form.requestPage.value : 1" />';
		break;
	}
	var _hiddenInputStrings = [];
	for ( var i = 0; i < this._hiddenInputNameAndValues.length; i++) {
		_hiddenInputStrings[_hiddenInputStrings.length] = '<input name="'
				+ this._hiddenInputNameAndValues[i][0] + '" type="hidden" id="'
				+ this._hiddenInputNameAndValues[i][0] + '" value="'
				+ this._hiddenInputNameAndValues[i][1] + '" />';
	}
	pagerStrings[pagerStrings.length] = _hiddenInputStrings.join("").toString();
	return pagerStrings.join("").toString();
};

function isInteger(value) {
	return /^[-\+]?\d+$/.test(value)
}
