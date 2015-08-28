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
<!--[if IE]>
<link href="${ctx}/styles/manager/ieHack.css" rel="stylesheet" type="text/css" />
<![endif]-->
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
		    <html-el:form action="/admin/KonkaSpActivityManager.do">
		      <html-el:hidden property="method" value="spActivityManagerForOtherR3List" />
		      <html-el:hidden property="id" />
		      <div style="height:5px;"></div>
				<table width="100%" border="0" align="left" class="list">
					<tr>
						<td>
					        <strong class="fb">R3分类：</strong>
				            <html-el:select property="is_sdf" styleId="is_sdf" style="width:70px;">
				              <html-el:option value="0">售达方</html-el:option>
				              <html-el:option value="1">送达方</html-el:option>
				            </html-el:select>
				          	<strong class="fb">客户名称：</strong>
				          	<html-el:text property="customer_name_like" size="40" maxlength="40" styleId="customer_name_like" />
							 &nbsp;<html-el:submit styleClass="but1" value="搜索" />
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
                        						<%-- <option value="${cur.map.l4_dept_id}_${cur.map.l4_dept_name}_${cur.map.ywy_user_name}_${cur.map.r3_code}_${cur.map.customer_type}_${cur.map.customer_type_name}_${cur.map.job_id}">${cur.customer_name}</option> --%>
                      							<option value="${cur.map.l4_dept_id}_${cur.map.l4_dept_name}_${cur.map.ywy_user_name}_${cur.map.r3_code}_${cur.map.customer_type}_${cur.map.customer_type_name}_${cur.map.job_id}">${cur.customer_name}</option>
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
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript">//<![CDATA[
function selectCust(l4_dept_id, l4_dept_name, ywy_user_name, r3_code, customer_type, customer_type_name, job_id,user_id,customer_name){
	window.returnValue = {'l4_dept_id' : l4_dept_id,'l4_dept_name' : l4_dept_name, 'ywy_user_name' : ywy_user_name, 'r3_code' : r3_code, 'customer_type' : customer_type,'customer_type_name':customer_type_name, 'job_id' : job_id, 'user_id' : user_id, 'customer_name' : customer_name};
	if (window.opener && window.opener != null) window.opener.returnValue = { 'l4_dept_id' : l4_dept_id,'l4_dept_name' : l4_dept_name, 'ywy_user_name' : ywy_user_name, 'r3_code' : r3_code, 'customer_type' : customer_type,'customer_type_name':customer_type_name, 'job_id' : job_id, 'user_id' : user_id, 'customer_name' : customer_name};
	window.close();
}
$(document).ready(function(){
	var f = document.forms[0];
	$("#btn_submit").click(function(){
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
		/* if ($("#select2 option").length > 1){
			alert("只能选择1个型号");
			return false;
		} */
		window.returnValue = {
				ids : ids,
				names : names
			};
		window.close();
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
	<c:if test="${af.map.selectype eq 'signal'}">if ($("#select2 option").length == 1) {
		$("#btn_submit").click();
	}</c:if>
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>



