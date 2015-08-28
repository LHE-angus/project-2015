<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/javascripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>  
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：选择上报员</td>
      </tr>
    </table>
  </div>
   <div class="rtabcont1">
        <html-el:form action="/paragon/SelectSalesMan" onsubmit="return false;">
         <html-el:hidden property="queryString" styleId="queryString" />
           <html-el:hidden property="selects" styleId="selects" />
          <html-el:hidden property="method" styleId="method" value="list" />
          <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
            <div style="height:5px;"></div>
            <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
	         <tr>
	          <td width="15"></td>
	           <td><strong class="fb">搜索业务员名称：</strong>
	             <html-el:text property="sales_name_like" styleId="sales_name_like" size="24" maxlength="100" style="width:100px;" />
	             &nbsp;&nbsp;
	             <input type="button"  class="but1" value="搜 索" id="search" /></td>
	         </tr>
	   </table>
	</html-el:form>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/paragon/SelectSalesMan">
        <table width="100%" border="0" align="left" class="list">
          <tr>
            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td>待选择区<html-el:select property="select1" multiple="true" style="width:290px;height:358px;" styleId="select1" onchange="moveSelected(this.form.select1, this.form.select2);">
                      <c:forEach var="cur" items="${entityList}" varStatus="vs">
                        <option value="${cur.id}">${cur.user_name}</option>
                      </c:forEach>
                    </html-el:select></td>
                  <td>已选择区<html-el:select property="select2" multiple="true" style="width:290px;height:358px;" styleId="select2" onchange="moveSelected(this.form.select2, this.form.select1);">
                      <c:if test="${not empty selectList}">
                        <html-el:optionsCollection label="user_name" value="id"  name="selectList" />
                      </c:if>
                    </html-el:select></td>
                </tr>
              </table></td>
          </tr>
           <tr>
            <td style="text-align:right;"><html-el:button property="" value="提 交" styleClass="websub" styleId="btn_submit" />
              <html-el:button property="" value="返 回" styleClass="websub" styleId="btn_back" onclick="window.close();" /></td>
          </tr>
        </table>
     </html-el:form>
    </div>
    <div class="clear"></div>
  </div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	var f = document.forms[1];
	$("#sales_name_like").attr("autocomplete", "off");
	$("#sales_name_like").attr("disableautocomplete", "true");
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
		window.returnValue = {
				ids : ids,
				names : names
			};
		window.close();
		<c:if test="${af.map.selectype eq 'signal'}">}</c:if>
	});
	
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
	//<c:if test="${af.map.selectype eq 'signal'}">if ($("#select2 option").length == 1) {
	//	$("#btn_submit").click();
	//}</c:if>
}

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
