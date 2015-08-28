<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>选择部门</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
</head>
<body style="background-color:#fff;">
<div class="oarcont" id="body_oarcont">
  <div class="rtabcont1">
    <html-el:form action="/chengduoa/Dialog">
      <html-el:hidden property="queryString" styleId="queryString" />
      <html-el:hidden property="method" styleId="method" value="save" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="id" styleId="id" />
      <div style="height:5px;"></div>
      <table width="100%" border="0" align="left" class="list">
        <tr>
          <td>&nbsp;&nbsp;搜索：
            <html-el:text property="key_word" styleId="key_word" size="24" maxlength="100" style="width:140px;"/></td>
        </tr>
        <tr>
          <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td>&nbsp;&nbsp;<html-el:select property="select1" multiple="true" style="width:200px;height:235px;" styleId="select1" onchange="moveSelected(this.form.select1, this.form.select2);">
                    <c:if test="${not empty konkadeptList}">
                      <c:forEach var="cur" items="${konkadeptList}">
                        <html-el:option value="${cur.dept_id}">${fn:escapeXml(cur.map.tree_name)}</html-el:option>
                      </c:forEach>
                    </c:if>
                  </html-el:select></td>
                <td><html-el:select property="select2" multiple="true" style="width:200px;height:235px;align:right;" styleId="select2" onchange="moveSelected(this.form.select2, this.form.select1);">
                    <c:if test="${not empty selectList}">
                      <html-el:optionsCollection label="dept_name" value="dept_id"  name="selectList" />
                    </c:if>
                  </html-el:select></td>
              </tr>
            </table></td>
        </tr>
        <tr>
          <td style="text-align:right;padding:22px; 0 0;"><html-el:button property="" value="提 交" styleClass="but4" styleId="btn_submit" />
            &nbsp;&nbsp;<html-el:button property="" value="返 回" styleClass="but5" styleId="btn_back" onclick="window.close();" />&nbsp;&nbsp;</td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	var f = document.forms[0];
	$("#key_word").attr("autocomplete", "off");
	$("#key_word").attr("disableautocomplete", "true");
	
	$("#btn_submit").click(function(){
		<c:if test="${af.map.selectype eq 'signal'}">
		if ($("#select2 option").length > 1) {
			alert("只能选择一个部门！");
		} else {
		</c:if>
		var selectedUsersId = "";
		var selectedUsersName = "";
		for(var i=0;i<f.select2.length;i++){
			selectedUsersId+=f.select2.options[i].value+",";
			selectedUsersName+=f.select2.options[i].text+",";
		}
		window.returnValue = {
				user_link_ids : selectedUsersId,
				user_link_names : selectedUsersName
			};
		window.close();
		<c:if test="${af.map.selectype eq 'signal'}">}</c:if>
	});
	
    var bind_name = 'input';
    if (navigator.userAgent.indexOf("MSIE") != -1){
        bind_name = 'propertychange';
    }
    $('#key_word').bind(bind_name, function(){
    	getQueryDeptNames($('#key_word').val());
    });
    
    $('#key_word').keypress(function (e) {
        var k = e.keyCode || e.which;
        if (k == 13) {
            return false;
        }
    });
});

function getQueryDeptNames(key_word) {
   	$("#select1").empty();
   	$.ajax({
		type: "POST",
		cache: false,
		url: "${ctx}/manager/chengduoa/DiaLog.do",
		data: "method=getQueryDeptNames&key_word=" + $("#key_word").val() + "&selectedDeptsID=" + getSelect2Value(),
		dataType: "json",
		error: function(request, settings){},
		success: function(data) {
			if (data.length > 1) {
				for(var i = 0; i < data.length - 1; i++) {
					var opt = new Option( data[i].dept_name,data[i].dept_id); 
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
}

function getSelect2Value(){
	var str = "";
	var obj = document.getElementById("select2");
	
    for(var i = 0; i < obj.options.length; i++) {
    	str += obj.options[i].value + ","
    }
	
	if(str != "") {
		return str.substr(0, str.length - 1);
	} else {
		return "";
	}
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
