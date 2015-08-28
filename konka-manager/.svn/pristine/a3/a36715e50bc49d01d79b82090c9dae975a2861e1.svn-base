<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/commons/styles/message/message.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/customer/multiselect/jquery.multiselect2side.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
       <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td nowrap="nowrap">当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
    <%@ include file="/commons/pages/messages.jsp" %>
  </div>
  <div class="rtabcont2">
    <html-el:form action="admin/KonkaSapInterfaceSetting" method="post">
      <html-el:hidden property="method" value="save" styleId="method"  />
      <html-el:hidden property="id" styleId="id" />
          <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
      	<tr>
          <td nowrap="nowrap" height="28" class="title_item" align="right" width="15%"><span style="color: red;">*</span>接口：</td>
          <td ><html-el:text property="service" styleId="service"  readonly="${af.map.id gt 0 ?true:false }" /></td>
        </tr>
        <tr>
           <td nowrap="nowrap" height="28" class="title_item" align="right" width="15%"><span style="color: red;">*</span>接口说明：</td>
           <td ><html-el:text property="serviceDesc" styleId="serviceDesc" readonly="${af.map.id gt 0 ?true:false }" /></td>
        </tr>
         <tr>
           <td nowrap="nowrap" height="28" class="title_item" align="right" width="15%"><span style="color: red;">*</span>启动：</td>
           <td width="85%">
	           <select name="status" style="width:154px">
	           	<option value="0">启用</option>
	           	<option value="-1">挂起</option>
	           </select>
           </td>
         </tr>
         
         <tr>
       		<td nowrap="nowrap" class="title_item" colspan="2">
	       	<input class="but4" type="button" value="保存" id="btn_save" />
	       	&nbsp;<input class="but5" type="button" value="返回" onclick="javascript:history.go(-1)" />
       		</td> 
       	 </tr>
       	
      </table>
      
    </html-el:form>
  </div>
  <div class="clear"></div>
</div>
<!-- ****** Main Frame End ****** -->
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/multiselect/jquery.multiselect2sideYwy.js"></script>
<script type="text/javascript">//<![CDATA[
    $(document).ready(function(){
    	/**较验规则**/
    	$("#service").attr("dataType", "Require").attr("msg", "接口(方法)不能为空！");
    	$("#serviceDesc").attr("dataType", "Require").attr("msg", "接口名称不能为空！");
        $("#btn_save").click(function(){
        	if(Validator.Validate(this.form, 3)){
    			 $("#btn_submit").attr("disabled", "true");
    			 this.form.submit();
    		}
        });

	});

//]]></script>
</body>
</html>
