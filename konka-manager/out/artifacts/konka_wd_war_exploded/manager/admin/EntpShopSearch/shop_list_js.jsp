<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<script type="text/javascript">//<![CDATA[
function openChild(num){
	var now= new Date().getTime();
	if(num == 1){
		var returnValue = window.showModalDialog("EntpShopSearch.do?method=listBaseBrandInfo&time="+now, window, "dialogWidth:800px;status:no;dialogHeight:680px");
	    if(returnValue != null){
			var value = returnValue.split(",");
			document.getElementById("brand_name").value = value[0];
			document.getElementById("brand_id").value = value[1];
	    } 
	}else if(num == 2){
		var returnValue = window.showModalDialog("EntpShopSearch.do?method=listJdxxRootEntp&time="+now, window, "dialogWidth:800px;status:no;dialogHeight:680px");
	    if(returnValue != null){
			var value = returnValue.split(",");
			document.getElementById("root_entp_name_jdxx").value = value[0];
			document.getElementById("root_entp_id_jdxx").value = value[1];
	    } 
	}else if(num == 3){
		var returnValue = window.showModalDialog("EntpShopSearch.do?method=listYjhxRootEntp&time="+now, window, "dialogWidth:800px;status:no;dialogHeight:680px");
	    if(returnValue != null){
			var value = returnValue.split(",");
			document.getElementById("root_entp_name_yjhx").value = value[0];
			document.getElementById("root_entp_id_yjhx").value = value[1];
	    } 
	}	
}

function setMonthByYear(){    //根据年份设置月份
	if($.trim($("#year").val()).length > 0){
		var year = parseInt($("#year").val(),10);
		var now_year = parseInt("${now_year}",10);
		var now_month = parseInt("${now_month}",10);
		var month = document.getElementById("month");  //month为下拉列表Month对象 
		month.length = 1; //保留第一个选择项
		if(year == 2010){
		    for(var i = 8; i <= 12; i++){ 
			    if(i < 10){
				    i = "0" + i;
				}
			    var option = new Option(); 
		        option.text = i + ' 月'; 
		        option.value = i; 
		        month.options.add(option);  //往下拉列表Month添加数据 
		    } 
		}
		if(year == now_year){
		    for(var i = 1; i < now_month; i++){ 
			    if(i < 10){
				    i = "0" + i;
				}
			    var option = new Option(); 
		        option.text = i + ' 月'; 
		        option.value = i; 
		        month.options.add(option);  //往下拉列表Month添加数据 
		    } 
		}
		if(year > 2010 && year < now_year){
		    for(var i = 1; i <= 12; i++){ 
			    if(i < 10){
				    i = "0" + i;
				}
			    var option = new Option(); 
		        option.text = i + ' 月'; 
		        option.value = i; 
		        month.options.add(option);  //往下拉列表Month添加数据 
		    } 
		}
	}
}

function setOnlyNum() {
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
		//if(this.value.length == 0) this.value = "0";
	});
	//this.text.selected;
}

function sellAnalysisSubmit(form,key_values){
	var action = 'EntpShopSellAnalysis.do';
	var method = 'list';
	/*
	var brand_name_zh = document.getElementById("brand_name").value;
	if(brand_name_zh != ""){
		 key_values = key_values +'&brand_name_zh='+ encodeURI(brand_name_zh);
	}
	*/
	key_values = key_values +'&'+ $('#'+form).serialize();
	doNeedMethod(null, action, method, key_values);	
}

/* 
 * 注：自动添加“经营类别”后，系统将自动带出可供选择的“经营品牌”。
 * 通过cls_id查询brand_name
 */
function getBrandNameByClsIds(cls_ids){
	$("#busi_brand_ul").hide();
	$("#busi_brand_default").hide();
	$("#cls_id_loading").show();
	$.ajax({
		type: "POST",
		url: "EntpShopSearch.do",
		data: "method=getBrandIdsByClsId&cls_ids=" + cls_ids.substring(0 , cls_ids.length - 1),
		dataType: "json",
		error: function(request, settings) {tag = false;},
		success: function(oper) {
			if ("" != oper.result){
				var strs = oper.result.split(";");
				var brand_names = "";
				var html = "";
				for(i = 0; i < strs.length - 1; i++){
					var temps = strs[i].split("-");
					html = html + "<li style='width:120px;float:left;'><input type='checkbox' name='brand_ids' id='brand_ids_" + temps[1] + "' value='" +  temps[1] + "' /><label for='brand_ids"+ emps[1] +"'>" + temps[0] + "</label></li>";
				}
				if(strs.length > 80){
					$("#busi_brand_ul").css({"height":"200px","overflow-y":"auto"});
				}else{
					$("#busi_brand_ul").css("height","");
				}
				$("#cls_id_loading").hide();
				$("#busi_brand_ul").html(html);
				$("#busi_brand_ul").show();
			} else {
				$("#cls_id_loading").hide();
			}
		}
    });
}

/* 
 * 注：通过AJAX进行网点开拓，被开拓网点变成待开拓网点。
 * 支持单个网点和批量操作,单个网点 ：如,('10001',0) 批量：如,('',1),复选框name="pks"
 * 同时开拓网点复选框变成不可以状态，开拓操作部可用
 */
function ajaxShopDevelop(shop_id,flg){
    var pks_shop_id = shop_id;
    if(flg == 1){
    	 var pks = document.getElementsByName("pks");
         for(var i=0; i< pks.length; i++){      
              if(pks[i].checked == true){
            	  pks_shop_id = pks_shop_id + pks[i].value + "_";
              }
         }
         pks_shop_id = pks_shop_id.substring(0,pks_shop_id.length - 1);      
    }
    if(pks_shop_id == ""){
    	alert("您没有选择开拓的网点！");
        return;
    }
    
	$.ajax({
		type: "POST",
		url: "EntpShopSearch5W.do",
		data: "method=ajaxShopDevelop&pks_shop_id=" + pks_shop_id,
		dataType: "json",
		error: function(request, settings) {tag = false;},
		success: function(jsonData) {
			if(jsonData && jsonData.ajax_status == "0"){
               alert("未知错误，开拓失败，请稍后重试！");
			}else if(jsonData && jsonData.ajax_status == "1"){
			   alert("请选择未开拓的网点！");

			}else if(jsonData && jsonData.ajax_status == "2"){
				alert("选择的网点已成为待开拓网点！");
			    for(var i=0; i< jsonData.list.length; i++){
			        var shopDevelop = jsonData.list[i];
			        var span = document.getElementById("span_"+shopDevelop.shop_id);
			        span.innerHTML = "待开拓";
			        span.style.color = "#CCCCCC";

			        var input = document.getElementById("pks_"+shopDevelop.shop_id);
			        input.checked = false;
			        input.setAttribute("disabled","disabled");
			    }			
			}
		}
    });
}

//]]></script>