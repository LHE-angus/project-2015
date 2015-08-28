function Pager(data, url, pageSize) {
    this.data = data;
    this.url = url;
    this.pageSize = pageSize;
    this._hiddenInputNameAndValues = [];
    this._hiddenInputNameAndValuesPage = [];
}

Pager.prototype.addHiddenInputs = function (name, value) {
	this._hiddenInputNameAndValues[this._hiddenInputNameAndValues.length] = new Array(name, value);
};

Pager.prototype.gotoPage = function(page) {
	this.form.requestPage.value = page;
	this.form.submit();
};

function ruleCase(caseValue){
	//创建表单
	var ruledCase = "";
	for (var i = 0; i < caseValue.length; i++){
		ruledCase += caseValue[i][0] + "=" + caseValue[i][1] + "&";
	}
	 return ruledCase;
}

function isInteger(value) {
	return /^[-\+]?\d+$/.test(value)
}

function goPage(value) {
	pager.getListData(value);
}