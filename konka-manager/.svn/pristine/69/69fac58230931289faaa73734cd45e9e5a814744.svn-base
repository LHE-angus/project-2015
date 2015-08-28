/**
 * @author Wu,Yang
 * @version 2010-10-12
 * @param method 公用的跳转方法名
 * @desc 公用的跳转用的方法
 */
function go(isLocal, url){
	var keySeq = "";
	if (null != isLocal) {
		if (isLocal) {
			keySeq = "41251925222518263625";
		} else {
			keySeq = external.GetPrecis('UsbKey');
		}
	}
	if (url.indexOf('?') > -1) {
		url = url + "&keySeq=" + keySeq;
	} else {
		url = url + "?keySeq=" + keySeq;
	}
	location.href = url;
}

/**
 * @author Wu,Yang
 * @version 2010-08-30
 * @param para 需要合并的td
 * @param parentObj td所属的父元素
 * @desc 相同元素的行合并
 */
function trMerge(para, parentObj) {
	var that;
	$("." + para, parentObj).each(function(){
		if ((that != undefined) && ($(this).html() == $(that).html())) {
			rowspan = $(that).attr("rowSpan");
			if (rowspan == undefined) {
				$(that).attr("rowSpan", 1);   
				rowspan = $(that).attr("rowSpan");   
			}
			rowspan = Number(rowspan) + 1;
			$(that).attr("rowSpan", rowspan); // do your action for the colspan cell here
			$(this).remove(); // .remove(); // do your action for the old cell here
	    } else {
			that = this;
	    }
	});
}

/**
 * @author Wu,Yang
 * @version 2010-08-30
 * @desc select的ajax联动
 */
function doSelectAjax(url, parentElement, parentValue, sonElement, sonValue, methodValue){
	parentElement.change(function(){
		var TrimParentElementValue = $.trim(parentElement.val());
		if("" != TrimParentElementValue){
			$.ajax({
					type: "POST",
					url: url,
					data: "method=" + methodValue + "&parentElementValue=" + TrimParentElementValue,
					dataType: "json",
					error: function(request, settings) {alert("数据加载请求失败！"); },
					success: function(Dates) {
						sonElement.empty().show();
						sonElement.get(0).options.add(new Option("请选择...", ""));
						if (null!= Dates && Dates.length > 1) {
							for(var i = 0; i < Dates.length - 1; i++) {
								var opt = new Option(Dates[i].date_desc, Dates[i].date_id); 
								opt.title = Dates[i].date_desc;
								sonElement.get(0).options.add(opt);
							}
							sonElement.attr("value", sonValue);
						} 
					}
			});
		}
	});
	if ("" != parentValue) {
		parentElement.change();
	}
}

function doSelectAjaxForTwoPars(url, parentElement1, parentElement2, parentValue, sonElement, sonValue, methodValue){
	parentElement2.change(function(){
		var TrimParentElementValue1 = $.trim(parentElement1.val());
		var TrimParentElementValue2 = $.trim(parentElement2.val());
		if("" != TrimParentElementValue1 && "" != TrimParentElementValue2){
			$.ajax({
					type: "POST",
					url: url,
					data: "method=" + methodValue + "&parentElementValue1=" + TrimParentElementValue1 + "&parentElementValue2=" + TrimParentElementValue2,
					dataType: "json",
					error: function(request, settings) {alert("数据加载请求失败！"); },
					success: function(Dates) {
						sonElement.empty().show();
						sonElement.get(0).options.add(new Option("请选择...", ""));
						if (Dates.length > 1) {
							for(var i = 0; i < Dates.length - 1; i++) {
								var opt = new Option(Dates[i].date_desc, Dates[i].date_id); 
								opt.title = Dates[i].date_desc;
								sonElement.get(0).options.add(opt);
							}
							sonElement.attr("value", sonValue);
						} 
					}
			});
		}
	});
	if ("" != parentValue) {
		parentElement2.change();
	}
}

function doSelectAjaxForThreePars(url, parentElement1, parentElement2, parentElement3, parentValue, sonElement, sonValue, methodValue){
	parentElement2.change(function(){
		var TrimParentElementValue1 = $.trim(parentElement1.val());
		var TrimParentElementValue2 = $.trim(parentElement2.val());
		var TrimParentElementValue3 = $.trim(parentElement3.val());
		if("" != TrimParentElementValue1 && "" != TrimParentElementValue2 && "" != TrimParentElementValue3){
			$.ajax({
				type: "POST",
				url: url,
				data: "method=" + methodValue + "&parentElementValue1=" + TrimParentElementValue1 + "&parentElementValue2=" + TrimParentElementValue2+ "&parentElementValue3=" + TrimParentElementValue3,
				dataType: "json",
				error: function(request, settings) {alert("数据加载请求失败！"); },
				success: function(Dates) {
					sonElement.empty().show();
					sonElement.get(0).options.add(new Option("请选择...", ""));
					if (Dates.length > 1) {
						for(var i = 0; i < Dates.length - 1; i++) {
							var opt = new Option(Dates[i].date_desc, Dates[i].date_id); 
							opt.title = Dates[i].date_desc;
							sonElement.get(0).options.add(opt);
						}
						sonElement.attr("value", sonValue);
					} 
				}
			});
		}
	});
	if ("" != parentValue) {
		parentElement2.change();
	}
}

/**
 * @author Wu,Yang
 * @version 2010-08-30
 * @desc select的ajax联动
 */
function doSelectAjaxWithCheckbox(url, parentElement, parentValue, sonElement, sonValue, methodValue){
	parentElement.change(function(){
		var TrimParentElementValue = $.trim(parentElement.val());
		if("" != TrimParentElementValue){
			$.ajax({
				type: "POST",
				url: url,
				data: "method=" + methodValue + "&parentElementValue=" + TrimParentElementValue,
				dataType: "json",
				error: function(request, settings) {alert("数据加载请求失败！"); },
				success: function(Dates) {
					sonElement.empty();
					if (Dates.length > 1) {
						for(var i = 0; i < Dates.length - 1; i++) {
						var checkboxHtml = '<input type="checkbox" name="pd_types" id="pd_type_id_' + Dates[i].date_id + '" value="' + Dates[i].date_id + '" />&nbsp;<label for="pd_type_id_' + Dates[i].date_id + '">' + Dates[i].date_desc + '</label>&nbsp;';					
							sonElement.append(checkboxHtml);
						}
					} else {
						sonElement.append("<span style='color:#F00;'>没有数据</span>");
					}
				}
			});
		}
	});
	if ("" != parentValue) {
		parentElement.change();
	}
}

function setOnlyInteger() {
	$(this).css("ime-mode", "disabled").attr("t_value", "").attr("o_value", "").bind("dragenter",function(){
		return false;
	});
	$(this).keypress(function (){
		if(!this.value.match(/^[\+\-]?\d*?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\d+)?)?$/))this.o_value=this.value;
	}).keyup(function (){
		if(!this.value.match(/^[\+\-]?\d*?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\d+)?)?$/))this.o_value=this.value;
	}).blur(function (){
		if(!this.value.match(/^(?:[\+\-]?\d+(?:\d+)?|\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value;}
		if(this.value.length == 0) this.value = 0;
	});
}

function setOnlyInteger() {
	$(this).css("ime-mode", "disabled");
	$(this).attr("t_value", "");
	$(this).attr("o_value", "");
	$(this).bind("dragenter",function(){
		return false;
	});

	var regInteger = /^[-\+]?\d+$/;
	$(this).keypress(function (){
		if(!this.value.match(regInteger))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(regInteger))this.o_value=this.value;
	}).keyup(function (){
		if(!this.value.match(regInteger))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(regInteger))this.o_value=this.value;
	}).blur(function (){
		if(!this.value.match(regInteger))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value;}
		if(this.value.length == 0) this.value = "0";
	});
}
function setOnlyCurrency() {
	$(this).css("ime-mode", "disabled");
	$(this).attr("t_value", "");
	$(this).attr("o_value", "");
	$(this).bind("dragenter",function(){
		return false;
	});
	
	var regkey = /^[\+]?\d*?\.?\d*?$/;
	var regblur = /^(?:[\+]?\d+(?:\.\d+)?|\.\d*?)?$/;
	$(this).keypress(function (){
		if(!this.value.match(regkey))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(regkey))this.o_value=this.value;
	}).keyup(function (){
		if(!this.value.match(regkey))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(regkey))this.o_value=this.value;
	}).blur(function (){
		if(!this.value.match(regblur))this.value=this.o_value;else{if(this.value.match(regblur))this.value=0+this.value;if(this.value.match(regblur))this.value=0;this.o_value=this.value;}
		if(this.value.length == 0) this.value = "0";
	});
}

function setOnlyDouble() {
	$(this).css("ime-mode", "disabled");
	$(this).attr("t_value", "");
	$(this).attr("o_value", "");
	$(this).bind("dragenter",function(){
		return false;
	});

	$(this).keypress(function (){
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).keyup(function (){
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).blur(function (){
		if(!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value;}
		if(this.value.length == 0) this.value = "0";
	});

	//this.text.selected;

}


function initStyle(){
	  $("input[type='text'][readonly],input[type='password'][readonly],textarea[readonly]").css({color:"#999"});
}

function judgeSelectedValueIsRepeat(objId, parObj){
	var isRepeat = false;
	var ary = new Array();
	$("#" + objId, parObj).each(function() {
		if ("" != $(this).val()) {
			ary.push($(this).val());
		}
	});
	var nary = ary.sort();  
	for(var i = 0; i < nary.length - 1; i++) {
		if (nary[i] == nary[i + 1]) {
			isRepeat = true;
			break;
		}  
    }  
	return isRepeat;
}
