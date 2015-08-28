<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>选择网点</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="oarcont">
  <div class="rtabcont1">
	<html-el:form action="/admin/SelectEntyShopByArea" onsubmit="return false;">
    <html-el:hidden property="queryString" styleId="queryString" />
    <html-el:hidden property="areas_ids" styleId="areas_ids" />
    <html-el:hidden property="selects" styleId="selects" />
    <html-el:hidden property="receive_user_type" styleId="receive_user_type" />
    <html-el:hidden property="method" styleId="method" value="getEntpShopForMsgAndArticle" />
	  <table width="100%" border="0" align="left" class="list">
	         <tr>
	           <td>搜索网点名：
	             <html-el:text property="mmt_shop_name" styleId="mmt_shop_name" size="24" maxlength="100" style="width:100px;" />
	             &nbsp;&nbsp;
	             <input type="button"  class="but1" value="搜 索" id="search" /></td>
	         </tr>
	   </table>
	</html-el:form>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/admin/SelectEntyShopByArea">
        <table width="100%" border="0" align="left" class="list">
          <tr>
            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td>待选择区
                      <html-el:select property="select1" multiple="true" style="width:290px;height:358px;" styleId="select1" onchange="moveSelected(this.form.select1, this.form.select2);">
                        <c:forEach var="cur" items="${entityList}" varStatus="vs">
                          <option value="${cur.map.mmt_shop_id}">${cur.map.mmt_shop_name}</option>
                        </c:forEach>
                      </html-el:select>
                  </td>
                  <td>已选择区
                    <html-el:select property="select2" multiple="true" style="width:290px;height:358px;" styleId="select2" onchange="moveSelected(this.form.select2, this.form.select1);">
                      <c:if test="${not empty selectList}">
                        <html-el:optionsCollection label="map.mmt_shop_name" value="map.mmt_shop_id"  name="selectList" />
                      </c:if>
                    </html-el:select></td>
                </tr>
              </table></td>
          </tr>
          <tr>
            <td style="text-align:right;"><html-el:button property="" styleClass="but4" value="提 交" styleId="btn_submit" />&nbsp;&nbsp;
              <html-el:button property="" value="返 回" styleClass="but5" styleId="btn_back" onclick="window.parent.close();" /></td>
          </tr>
        </table>
      </html-el:form>
  </div>      
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	var f = document.forms[1];
	$("#mmt_shop_name").attr("autocomplete", "off");
	$("#mmt_shop_name").attr("disableautocomplete", "true");
	
	$("#search").click(function(){
		var ids_search = "";
		for(var i=0;i<f.select2.length;i++){
			if ( i == 0 ) {
				ids_search += f.select2.options[i].value;
			} else {
				ids_search += "," + f.select2.options[i].value;
			}
		}		
		this.form.selects.value = ids_search;
		this.form.submit();
	});
	
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
		window.parent.returnValue = {
				ids : ids,
				names : names
			};
		window.parent.close();
		<c:if test="${af.map.selectype eq 'signal'}">}</c:if>
	});
	
//     var bind_name = 'input';
//     if (navigator.userAgent.indexOf("MSIE") != -1){
//         bind_name = 'propertychange';
//     }
});

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
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>