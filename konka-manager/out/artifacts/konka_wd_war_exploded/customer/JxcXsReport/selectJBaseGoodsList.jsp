<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>请选择商品</title>
<base target="_self" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<meta http-equiv="Cache-Control" content="no-cache" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<!--[if IE]>
<link href="${ctx}/styles/manager/ieHack.css" rel="stylesheet" type="text/css" />
<![endif]-->
</head>
<body>
<div id="navTab" class="tabsPage" style="text-align:left;">
  <div class="navTab-panel tabsPageContent">
    <div class="pageContent">
      <html-el:form action="/manager/JxcXsReport.do">
        <html-el:hidden property="queryString" styleId="queryString" />
        <html-el:hidden property="id" styleId="id" />
        <div style="height:5px;"></div>
        <table width="100%" border="0" align="left" class="list">
          <tr>
            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td>待选择区
                    <html-el:select property="select1" multiple="true" style="width:290px;height:258px;" styleId="select1" onchange="moveSelected(this.form.select1, this.form.select2);">
                      <c:forEach var="cur" items="${noselectList}" varStatus="vs">
                        <option value="${cur.goods_id}">${cur.goods_name}</option>
                      </c:forEach>
                    </html-el:select></td>
                  <td>已选择区
                    <html-el:select property="select2" multiple="true" style="width:290px;height:258px;" styleId="select2" onchange="moveSelected(this.form.select2, this.form.select1);">
                      <c:forEach var="cur" items="${selectList}" varStatus="vs">
                        <option value="${cur.goods_id}">${cur.goods_name}</option>
                      </c:forEach>
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
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	var f = document.forms[0];
	
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
	
    var bind_name = 'input';
    if (navigator.userAgent.indexOf("MSIE") != -1){
        bind_name = 'propertychange';
    }
   
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
	<c:if test="${af.map.selectype eq 'signal'}">if ($("#select2 option").length == 1) {
		$("#btn_submit").click();
	}</c:if>
}
//]]></script>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>
