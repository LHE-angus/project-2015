<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>请选择产品</title>
<base target="_self" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<meta http-equiv="Cache-Control" content="no-cache" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<style type="text/css">
select,input{font-family:Microsoft Yahei;color:#74685F}
input[type='submit'],input[type='button'] { border-radius:3px; border:1px solid #CCC; padding:3px 5px;background-color:#eee; color:#555; cursor: pointer; margin-right: 1px;}
input[type='submit']:hover,input[type='button']:hover { background-color:#ccc; border:1px solid #74685F;}
input[type='submit']:active,input[type='button']:active { color:#F00; }
</style>
</head>
<body style="font-family:Microsoft Yahei;">
<div id="navTab" class="tabsPage" style="text-align:left;">
	<div class="navTab-panel tabsPageContent">
		<div class="pageContent">
			<html-el:form action="/manager/SelectPePdModel.do">
				<html-el:hidden property="queryString" styleId="queryString" />
				<html-el:hidden property="method" styleId="method" value="save" />
				<html-el:hidden property="id" styleId="id" />
				<div style="height:5px;"></div>
				<table width="100%" border="0" align="left" class="list">
					<tr>
						<td>
							<html-el:select property="cls_id" styleId="cls_id" onchange="getQueryNames($('#key_word').val());">
								<option value="">-请选择品类-</option>
								<html-el:optionsCollection label="tree_name" value="cls_id"  name="clsList" />
							</html-el:select>
							&nbsp;
              				搜索关键字 ：
							<html-el:text property="key_word" styleId="key_word" size="24" maxlength="100" style="width:210px;" title="可以输入中文、全拼、简拼进行关键字即时搜索" />
						</td>
					</tr>
          			<tr>
            			<td>
            				<table width="100%" border="0" cellspacing="0" cellpadding="0">
                				<tr>
                  					<td>
                  						<div style="height:28px;font-size:14px;">待选择区</div>
                    					<html-el:select property="select1" multiple="true" style="width:290px;height:258px;" styleId="select1" onchange="moveSelected(this.form.select1, this.form.select2);">
                      						<c:forEach var="cur" items="${entityList}" varStatus="vs">
                        						<option value="${cur.pd_id}">${cur.md_name}</option>
                      						</c:forEach>
                    					</html-el:select>
                    				</td>
                  					<td>
                  						<div style="height:28px;font-size:14px;">已选择区</div>
                    					<html-el:select property="select2" multiple="true" style="width:290px;height:258px;" styleId="select2" onchange="moveSelected(this.form.select2, this.form.select1);">
                    					</html-el:select>
                    				</td>
                				</tr>
              				</table>
              			</td>
          			</tr>
          			<tr>
            			<td style="text-align:right;">
            				<html-el:button property="" value=" 提 交 " styleClass="websub" styleId="btn_submit" />
              				<html-el:button property="" value=" 返 回 " styleClass="websub" styleId="btn_back" onclick="window.close();" />
              			</td>
          			</tr>
        		</table>
      		</html-el:form>
    	</div>
  	</div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	var f = document.forms[0];
	$("#key_word").attr("autocomplete", "off");
	$("#key_word").attr("disableautocomplete", "true");
	
	$("#btn_submit").click(function(){
		<c:if test="${af.map.selectype eq 'signal'}">if ($("#select2 option").length > 1) {
			alert("单选，只能选择一个！");
		} else {
		</c:if>
		var ids = "";
		var names = "";
		for(var i=0;i<f.select2.length;i++){
			if ( i == 0 ) {
				ids += f.select2.options[i].value;
				names += f.select2.options[i].text;
			} else {
				ids += "," + f.select2.options[i].value;
				names += "," + f.select2.options[i].text;
			}
		}
		if ($("#select2 option").length > 50){
			alert("不能选择超过50个型号");
			return false;
		}
		window.returnValue = {
				ids : ids,
				names : names
			};
		window.close();
		<c:if test="${af.map.selectype eq 'signal'}">}</c:if>
	});
	
    var bind_name = 'input';
    if (navigator.userAgent.indexOf("MSIE") != -1){
        bind_name = 'propertychange';
    }
    $('#key_word').bind(bind_name, function(){
    	getQueryNames($('#key_word').val());
    });
    
    $('#key_word').keypress(function (e) {
        var k = e.keyCode || e.which;
        if (k == 13) {
            return false;
        }
    });
});

function getQueryNames(key_word) {
   	$("#select1").empty();
   	$.ajax({
		type: "POST",
		cache: false,
		url: "${ctx}/jxcnokey/SelectPePdModel.do",
		data: "method=" + "getQueryNames" +"&key_word=" + $("#key_word").val(),
		dataType: "json",
		error: function(request, settings){},
		success: function(data) {
			if (data.length >= 1) {
				for(var i = 0; i < data.length - 1; i++) {
					var opt = new Option( data[i].name,data[i].id); 
					$("#select1").get(0).options.add(opt);
				}
			}
		}
	});
}

function moveSelected(sourceSelect, targetSelect, isDelete){
	var cachOptionsArray = new Array();
	var index = 0;
	for (var i = sourceSelect.options.length - 1; i >= 0; i--){
		if (sourceSelect.options[i].selected){
			cachOptionsArray[index] = new Option(sourceSelect.options[i].text, sourceSelect.options[i].value);
			if(isDelete==undefined || isDelete==true){
				sourceSelect.options[i] = null;
			}
			index++;
		}
	}
	var exist = false;
	for (var i = cachOptionsArray.length - 1; i >= 0; i--){
		exist = false;
		for (var j = 0; j < targetSelect.options.length; j++){
			if (targetSelect.options[j].value.toString() == cachOptionsArray[i].value.toString()){
				exist = true; 
				break;
			}
		}
		if (!exist){
			targetSelect.options[targetSelect.options.length] = cachOptionsArray[i];
		}
	}
	<c:if test="${af.map.selectype eq 'signal'}">if ($("#select2 option").length == 1) {
		$("#btn_submit").click();
	}</c:if>
}
//]]></script> 
</body>
</html>
