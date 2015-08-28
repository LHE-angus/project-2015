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
</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="400" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
    <html-el:form action="/admin/KonkaR3MmtMatch.do" enctype="multipart/form-data">
      <html-el:hidden property="method" value="save"/>
      <html-el:hidden property="id" styleId="id"/>
      <html-el:hidden property="mod_id" styleId="mod_id"/>
      <html-el:hidden property="queryString" styleId="queryString" />
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
       <tr>
          <td nowrap="nowrap" class="title_item" align="right" width="20%">R3客户名称：</td>
          <td><c:out value="${af.map.customer_name}"/></td>
        </tr>
		<tr>
          <td nowrap="nowrap" class="title_item" align="right">所在区域名称：</td>
          <td><c:out value="${af.map.area_name}"/></td>
        </tr>	
		<tr>
          <td nowrap="nowrap" class="title_item" align="right">分公司所在地名称：</td>
          <td><c:out value="${af.map.branch_area_name}"/></td>
        </tr>	
		<tr>
          <td nowrap="nowrap" class="title_item" align="right">客户类型：</td>
          <td>${fn:escapeXml(cur.customer_type)}</td>
        </tr>	
		<tr>
          <td nowrap="nowrap" class="title_item" align="right">交易状态：</td>
          <td> <c:if test="${af.map.status eq 1}">
                 <c:out value="有交易"/>
              </c:if>
              <c:if test="${af.map.status eq 2}">
                 <c:out value="无交易"/>
              </c:if>
           </td>
        </tr>	
		<tr>
          <td nowrap="nowrap" class="title_item" align="right">R3编码：</td>
          <td><c:out value="${af.map.r3_code}"/></td>
        </tr>	
		<tr>
          <td nowrap="nowrap" class="title_item" align="right">经办名称：</td>
          <td><c:out value="${af.map.handle_name}"/></td>
        </tr>	
		<tr>
          <td nowrap="nowrap" class="title_item" align="right">合并客户编码：</td>
          <td><c:out value="${af.map.customer_code}"/></td>
        </tr>	
		<tr>
          <td nowrap="nowrap" class="title_item" align="right">备注：</td>
          <td><c:out value="${af.map.r3_desc}"/></td>
        </tr>	
		<tr>
          <td nowrap="nowrap" class="title_item" align="right">2010合并编码：</td>
          <td><c:out value="${af.map.merge_code_2010}"/></td>
        </tr>	
		<tr>
          <td nowrap="nowrap" class="title_item" align="right">导入日期：</td>
          <td><fmt:formatDate value="${af.map.import_date}" pattern="yyyy-mm-dd" /></td>
        </tr>	
		<tr>
          <td nowrap="nowrap" class="title_item" align="right">导入人姓名：</td>
          <td><c:out value="${af.map.map.import_user_name}"/></td>
        </tr>		
		<tr>
          <td nowrap="nowrap" class="title_item" align="right">康佳网点名称：</td>
          <td><html-el:text property="mmt_shop_name" styleId="mmt_shop_name" style="width:250px;" readonly="true" maxlength="100" value="${af.map.map.mmt_shop_name}"/>
              &nbsp;
                <input id="gsBTN" type='button' value='选择' onclick="getShopInfo();"/>
              <html-el:hidden property="mmt_shop_id" styleId="mmt_shop_id" value="${af.map.map.mmt_shop_id}"/>
          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td><html-el:button property="" value="匹 配" styleClass="but4" styleId="btn_submit" />
          <html-el:button property="" value="清 空" styleClass="but3" styleId="btn_reset" />
            <html-el:button property="" value="返 回" styleClass="but5" styleId="btn_back" onclick="history.back();" /></td>
        </tr>	
      </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

	$("#btn_reset").click(function(){
		$("#mmt_shop_name").val("");
		$("#mmt_shop_id").val("");
	});
	$("#btn_submit").click(function(){
		if(Validator.Validate(this.form, 3)){
	    $("#btn_submit").attr("disabled","disabled");
		$("#btn_back").attr("disabled","disabled");
		this.form.submit();
		}
	});
});
var request = false; 
var btype=getInternet();  
function getInternet()    
{    
	if(navigator.userAgent.indexOf("MSIE")>0) {    
              return "MSIE";       //IE浏览器  
}  
	if(isFirefox=navigator.userAgent.indexOf("Firefox")>0){    
              return "Firefox";     //Firefox浏览器  
}  
	if(isSafari=navigator.userAgent.indexOf("Safari")>0) {
		if(navigator.userAgent.indexOf("360EE")>0){
              return "360EE";      //360Chromium浏览器  
		}else{
              return "Safari";      //Safan (goole chrome)浏览器  
		}
}  
	if(isCamino=navigator.userAgent.indexOf("Camino")>0){    
              return "Camino";   //Camino浏览器  
}  
	if(isMozilla=navigator.userAgent.indexOf("Gecko/")>0){    
              return "Gecko";    //Gecko浏览器  
}    

}   

function getShopInfo() { 
	if(btype=='MSIE'){
		var returnValue = window.showModalDialog("SelectKonkaEntpShop.do?selectype=signal&azaz=" + Math.random(),window,"dialogWidth:930px;status:no;dialogHeight:500px");
		if (returnValue != null) {
			$("#mmt_shop_id").val(returnValue.shop_id);
			$("#mmt_shop_name").val(returnValue.shop_name);
		};
	}else{
		 window.open("SelectKonkaEntpShop.do?selectype=signal&azaz=" + Math.random(),'window','height=450,width=930,top=150,left=300,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
	}
};

function set_value(mmt_shop_id,mmt_shop_name){
	$("#mmt_shop_id").val(mmt_shop_id);
	$("#mmt_shop_name").val(mmt_shop_name);
};	
	//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
