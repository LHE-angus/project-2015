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
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
  </div>
  <div class="rtabcont2">
     <html-el:form action="/admin/KonkaShopVisitRecord">
      <html-el:hidden property="method" styleId="method" value="save" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="developType" value="1" />
      <html-el:hidden property="develop_id" value="${af.map.develop_id}" />
      <html-el:hidden property="shop_name_like" value="${af.map.shop_name_like}" />
      <html-el:hidden property="shop_develop_status" value="${af.map.shop_develop_status}" />      
      <html-el:hidden property="shop_visit_status" value="${af.map.shop_visit_status}" />
      <html-el:hidden property="r3_id" styleId="r3_id" value="${af.map.id}" />
      <html-el:hidden property="shop_id" value="${af.map.shop_id}" />
      <html-el:hidden property="shop_name" value="${af.map.shop_name}" />
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
        <tr>
          <td width="240" valign="top" style="border-right:1px solid #ccc;">
            <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
              <tr>
                <td colspan="2" nowrap="nowrap"><strong>网点信息</strong></td>
              </tr>
              <tr>
                 <td width="12%" nowrap="nowrap" class="title_item" align="right"></td>
                  <td width="88%" align="left">${af.map.shop_name}</td>
              </tr>
            </table>
          </td>
          <td valign="top">
              <c:if test="${af.map.operator_step eq '1'}">
               <%@ include file="form_step1.jsp" %>
              </c:if>
              <c:if test="${af.map.operator_step eq '2'}">
                <%@ include file="form_step2.jsp" %>
              </c:if>
          </td>
        </tr>
      </table>
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
        <tr>
          <td width="173"></td>
          <td width="134"><html-el:button property="" value="保 存" styleClass="but4" styleId="btn_submit1" />
            <html-el:button property="" value="返 回" styleClass="but5" styleId="btn_back" onclick="history.back();" /></td>
          <td></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript">//<![CDATA[


$(document).ready(function(){
//////// form_step1.jsp  start
<c:if test="${af.map.operator_step eq '1'}"> 
function checkValues(keys){
    var flg = true;

    for(var i = 0;i< keys.length;i++){
    	
      var v = $("#"+keys[i]).val();
      if(v == null || v == ""){
	        // $("#"+keys[i]).attr("msg", "请填写");
	        $("#"+keys[i]).css("background-color", "#ffaad5");
	        flg = false;
      }else{
	        $("#"+keys[i]).css("background-color", "");
      }
   }
   return flg;
}
    var checks = ["area_name","branch_area_name","customer_type","r3_code","handle_name","customer_name"];
	$("#btn_submit2").click(function(){    
		if(checkValues(checks)){
            $("#btn_submit2").attr("value", "正在提交...").attr("disabled", "true");
			this.form.submit();
		}
	});
</c:if>
//////// form_step1.jsp  end
//////// form_step2.jsp  start
<c:if test="${af.map.operator_step eq '2'}">
    var convertShopDirection = '${af.map.convertShopDirection}';
    $("#convertShopDirection").val(convertShopDirection);
    $("#convertShopDirection").change();
    $("#method").val("save");
</c:if>
////////form_step2.jsp  end

	$("#btn_submit1").click(function(){

	    if($("#convertShopDirection").val() == "0"){
		    if($("#konka_r3_name").val().length == 0){
		       alert("请选择代理商");
		       return;
		    }
	    }else{
		    if($("#r3_id").val().length == 0){
			   alert("请创建R3用户");
			   return;
			}
	    }
        $("#btn_submit1").attr("value", "正在提交...").attr("disabled", "true");
        $("#btn_back").attr("disabled", "true");
	    this.form.submit();
	});
	
	String.prototype.trim = function(){
        return this.replace(/(^\s*)|(\s*$)/g, "");
    };
});

function getAgentsList() { 
	var returnValue = window.showModalDialog("SelectAgentsList.do?area_limit=1&selectype=signal&azaz=" + Math.random(),window,"dialogWidth:930px;status:no;dialogHeight:500px");
	if (returnValue != null) {
		$("#konka_r3_id").val(returnValue.id);
		$("#konka_r3_name").val(returnValue.name);
	};
};

var dls_tr = document.getElementById("dls_tr");
var r3_tr = document.getElementById("r3_tr");
function changeDirection(obj){
	if(obj.value == "0"){
		dls_tr.style.display = "";
		r3_tr.style.display = "none";
		$("#method").val("save");
	}else{
		r3_tr.style.display = "";
		dls_tr.style.display = "none";
		$("#method").val("shopDirectDevelop");
	}
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
