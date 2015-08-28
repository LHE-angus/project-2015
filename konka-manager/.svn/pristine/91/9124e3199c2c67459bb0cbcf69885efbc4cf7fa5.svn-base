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
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/customer/fancybox/fancybox.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/commons/scripts/calendar/WdatePicker.js"></script>
<style type="text/css">
input,textarea,select,file,button{font-family:Microsoft Yahei;font-size:12px;}
.webinput {background:#f5f4f4;padding-left: 5px;height: 19px;line-height: 19px;/*font-family: Arial, Helvetica, sans-serif;*/border: #ccc solid 1px;}

.ck-li{position:relative;height:22px;padding:1px auto;}
.ck-li .hidden-accessible{position:absolute !important;clip:rect(1px 1px 1px 1px);}
.ck-li .ck-default{font-family:Microsoft yahei,verdana,Geneva,Tahoma,Georgia;font-size:12px;display:inline-block;padding:1px 8px;height:16px;line-height:16px;border:1px solid #CCC;background: #F6F6F6;font-weight: bold;color:#C4C4C4;cursor:pointer;}
.ck-li .ck-hover{font-family:Microsoft yahei,verdana,Geneva,Tahoma,Georgia;font-size:12px;display:inline-block;padding:1px 8px;height:16px;line-height:16px;border:1px solid #FBCB09; background:#FDF5CE;font-weight: bold;color:#C77405;cursor:pointer;}
.ck-li .ck-visited{font-family:Microsoft yahei,verdana,Geneva,Tahoma,Georgia;font-size:12px;display:inline-block;padding:1px 8px;height:16px;line-height:16px;border:2px solid #EF0F28/*#FF4800/*FBD850*/; background:white url("${ctx}/styles/customer/images/ck-visited.gif") right bottom no-repeat;font-weight:bold;color:#EF0F28/*#FF4800/*#EB8F00*/;cursor:pointer;}

.navClass {background-color:#CCC;border-collapse:collapse;}
.navClass th {background:#F6F6F6;color:#C4C4C4;font-size:12px;font-weight:bold;height:20px;line-height:20px;text-align:center;padding:2px;border:1px solid #CCC;}
.navClass td {padding:3px;height:18px;background-color:#fff;border:1px solid #CCC;}

.xubox_close{position:absolute;}
.xubox_close1_0{ right:-25px; top:-16px; width:33px; height:31px; background:url("${ctx}/styles/customer/images/xubox_ico0_1.png") -6px -182px no-repeat; cursor:pointer; overflow:hidden;}
.xubox_close1_0:hover{background:url("${ctx}/styles/customer/images/xubox_ico0_1.png") -6px -215px no-repeat;}
.xubox_border{border-radius:5px;position:absolute;}

.main_box{position:relative;width:260px;z-index:0;border:1px solid rgba(0,0,0,0);-webkit-border-radius:1px;-moz-border-radius:1px;border-radius:1px;-webkit-box-shadow:0 0 5px rgba(0,0,0,0.4);-moz-box-shadow:0 0 3px rgba(0,0,0,0.4);box-shadow:0 0 5px rgba(0,0,0,0.4);border:1px solid #CCC;}
.box{position:relative;width:130px;z-index:0;border:1px solid rgba(0,0,0,0);-webkit-border-radius:1px;-moz-border-radius:1px;border-radius:1px;-webkit-box-shadow:0 0 5px rgba(0,0,0,0.4);-moz-box-shadow:0 0 3px rgba(0,0,0,0.4);box-shadow:0 0 5px rgba(0,0,0,0.4);border:1px solid #CCC;}
.pic_box{text-align:center;;z-index:1;background:#FFFFFF;margin:5px;}
</style>
</head>
<body style="font-family:Microsoft Yahei;">
<div class="oarcont"> 
  <div class="rtabcont2">
    <html-el:form action="/spgl/PdShowNew" enctype="multipart/form-data" method="post">
      <html-el:hidden property="method" value="moidfySave" />
      <html-el:hidden property="mod_id" styleId="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="queryString" styleId="queryString" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
        <tr><td colspan="2" align="left"  class="title_item">商品列表</td></tr>
        <tr>
          <td align="left" colspan="2">      
          <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
          <tr>
          <td nowrap="nowrap" align="left">商品编码</td>
          <td nowrap="nowrap" align="left">商品所属</td>
          <td nowrap="nowrap" align="left">商品主图</td>
          </tr>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
           <td nowrap="nowrap" align="left" >${cur.pd_sn}</td>
           <html-el:hidden property="ids" value="${cur.id}" styleId="ids"/>
           <td nowrap="nowrap" align="left">
           <c:if test="${cur.dept_sn eq 0}">总部产品</c:if>
           <c:if test="${cur.dept_sn ne 0}">分公司产品</c:if>  
           </td>
           <td nowrap="nowrap" align="left"><img width="60px" height="40px" src="${ctx}/${fn:substringBefore(cur.main_pic, '.')}_240.jpg" /></td>
          </tr>
          </c:forEach>
          </table>
          </td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap"><font color="red">* </font>上架日期：</td>
          <td>
          <fmt:formatDate value="${af.map.up_time}" pattern="yyyy-MM-dd HH:mm:ss" var="_up_time" />
             <html-el:text styleId="up_time" property="up_time" size="20" maxlength="15" readonly="true" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" style="cursor:pointer;text-align:center;" value="${_up_time}" />
          </td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap"><font color="red">* </font>下架日期：</td>
          <td><fmt:formatDate value="${af.map.down_time}" pattern="yyyy-MM-dd HH:mm:ss" var="_down_time" />
             <html-el:text styleId="down_time" property="down_time" size="20" maxlength="15" readonly="true" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" style="cursor:pointer;text-align:center;" value="${_down_time}" />
          </td>
        </tr>
        <tr>
          <td align="center" colspan="2"><input class="but4" type="button" name="Submit4" value="提交" id="btn_submit" />
            <input class="but5" type="button" name="Submit5" value="返回" id="btn_back" onclick="history.back()" />
          </td>
        </tr>
      </table>
    </html-el:form>  
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/scripts/commons.plugin.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/fancybox/jquery.fancybox-1.3.1.pack.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

	$("#up_time").attr("datatype", "Require").attr("msg", "请选择上架日期！");
	$("#down_time").attr("datatype", "Require").attr("msg", "请选择下架日期！");


	$("#btn_submit").click(function(){

		var up_date = $("#up_time").val();
		var down_date = $("#down_time").val();
		
		var _up_date = up_date.replace(/-/g,"").replace(/:/g,"").replace(/ /g,"");
		var _down_date = down_date.replace(/-/g,"").replace(/:/g,"").replace(/ /g,"");

		if(_down_date < _up_date){
			alert("下架时间不能小于上架时间");
			return false; 
		}
		
		if(Validator.Validate(this.form, 3)){
			if(confirm("确定提交表单？")){
	            $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
	            $("#btn_back").attr("disabled", "true");
				this.form.submit();
			} else {
				return false;
			}
		}
	});
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
