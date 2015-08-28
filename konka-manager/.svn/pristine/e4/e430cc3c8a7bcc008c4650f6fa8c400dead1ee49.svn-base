<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<script type="text/javascript">//<![CDATA[
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

/* 
 * 注：通过AJAX进行网点开拓，被开拓网点变成待开拓网点。
 * 支持单个网点和批量操作,单个网点 ：如,('10001',0) 批量：如,('',1),复选框name="pks"
 * 同时开拓网点复选框变成不可以状态，开拓操作部可用
 */
function ajaxShopDevelop(shop_id,flg){
	var dls_shop_id='${af.map.shop_id}';
	var dls_r3_id='${af.map.r3_id}';
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
		url: "AgentsList.do",
		data: "method=ajaxShopDevelop&pks_shop_id=" + pks_shop_id+"&dls_shop_id="+dls_shop_id+"&dls_r3_id="+dls_r3_id,
		dataType: "json",
		error: function(request, settings) {tag = false;},
		success: function(jsonData) {
			if(jsonData && jsonData.ajax_status == "0"){
               alert("请选择开拓的网点！");
			}else if(jsonData && jsonData.ajax_status == "1"){
				alert("选择的网点已成为当前代理商网点！");
			    for(var i=0; i< jsonData.list.length; i++){
			        var shopDevelop = jsonData.list[i];
			        var span = document.getElementById("span_"+shopDevelop.shop_id);
			        span.innerHTML = "已开拓";
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