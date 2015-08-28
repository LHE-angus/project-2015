<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>
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
<body><div align="left"><br /></div><div align="left"> 
</div><div class="oarcont">
  <div class="oartop">
    <table width="500" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" alt="" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
	<div class="rtabcont2">
		<html-el:form action="/paragon/KonkaParagonShowt" enctype="multipart/form-data">
			<html-el:hidden property="showt_id" styleId="showt_id" />
			<html-el:hidden property="mod_id" styleId="mod_id" />
			<html-el:hidden property="method" styleId="method" value="save" />
			<html-el:hidden property="queryString" styleId="queryString" />
			<table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable3">
				<tr>
					<td nowrap="nowrap" class="title_item" align="right" width="147">门店代码：<span style="color:red">*</span></td>
					<td width="85%" >
						<html-el:text property="show_shop_code" size="40" maxlength="30" styleId="show_shop_code" disabled="true"/>&nbsp;
						 <input id="gsBTN" type='button' value='选择' onclick="getShowInfo(this);" class="but2" />
	               		 <html-el:hidden property="show_shop_code" styleId="show_shop_code"/>
	               		 <html-el:hidden property="show_shop_ids" styleId="show_shop_ids" value="${af.map.show_shop_id}"/>
	               	 </td>
				</tr>
				<tr>
		          <td nowrap="nowrap" class="title_item" align="right" width="147">门店名称：<span style="color:red">*</span></td>
		          <td><html-el:text property="show_shop_name" styleId="show_shop_name"  value="${af.map.show_shop_name}" style="width:150px;" styleClass="webinput" maxlength="10" disabled="true"/></td>
		        </tr>
				<tr>
					<td nowrap="nowrap" class="title_item" align="right" width="147">形象版本：<span style="color:red">*</span></td>
					<td width="85%" >
						<html-el:select property="version_id" styleId="version_id">
				        	<html-el:option value="">请选择...</html-el:option>
				        	<html-el:optionsCollection name="versionList" label="version_name" value="version_id" />
			        	</html-el:select>
	               	</td>
				</tr>
				<tr>
					<td nowrap="nowrap" class="title_item" align="right" width="147">展位面积：<span style="color:red">*</span></td>
					<td width="85%" ><html-el:text property="showt_area" styleId="showt_area" />㎡</td>
				</tr>
				<tr>
					<td nowrap="nowrap" class="title_item" align="right" width="147">展位类型：<span style="color:red">*</span></td>
					<td width="85%" >
						  <html-el:select property="showt_type" styleId="showt_type">
						  		<html-el:option value="">-请选择-</html-el:option>
				                <html-el:option value="--">--</html-el:option>
				                <html-el:option value="L">L</html-el:option>
				                <html-el:option value="U">U</html-el:option>
				                <html-el:option value="通到">通道</html-el:option>
				                <html-el:option value="其他">其他</html-el:option>
			              </html-el:select>
					</td>
				</tr>
				<tr>
					<td nowrap="nowrap" class="title_item" align="right" width="147">展位延米：<span style="color:red">*</span></td>
					<td width="85%" ><html-el:text property="showt_mile" styleId="showt_mile" />m</td>
				</tr>
				<tr>
					<td nowrap="nowrap" class="title_item" align="right" width="147">制作费用：<span style="color:red">*</span></td>
					<td width="85%" ><html-el:text property="showt_cash" styleId="showt_cash" />元</td>
				</tr>
				<tr>
					<td nowrap="nowrap" class="title_item" align="right" width="147">制作时间：<span style="color:red">*</span></td>
					<td width="85%" >
						<fmt:formatDate value="${af.map.showt_time}" pattern="yyyy-MM-dd" var="_showt_time"/>
						<html-el:text property="showt_time" styleId="showt_time" value="${_showt_time}" size="9" maxlength="10" readonly="true" style="cursor:pointer;" onclick="new Calendar(2005, 2030, 0).show(this);" />
					</td>
				</tr>
				 <tr>
		          	<td nowrap="nowrap" class="title_item" align="right" width="147">数据期号：<span style="color:red">*</span></td>
			        <td>
			        	 <html-el:text property="fixdate" styleId="fixdate" size="32" maxlength="20" readonly="true" onclick="WdatePicker({dateFmt:'yyyyMM'})" style="cursor:pointer;" value="${af.map.fixdate}" />
			        </td>
		        </tr>
				<tr>
					<td>&nbsp;</td>
			        <td><label>
			            <input class="but4" type="button" name="btn_submit" id="btn_submit" value="提交" />
			            <input class="but5" type="button" name=btn_back value="返回" onclick="history.back();return false;" />
			          </label></td>
				</tr>
			</table>
		</html-el:form>
	</div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#show_shop_code").attr("dataType", "Require").attr("msg", "请选择");
	$("#version_id").attr("dataType", "Require").attr("msg", "请选择");
	$("#showt_area").attr("dataType", "Require").attr("msg", "请填写");
	$("#showt_type").attr("dataType", "Require").attr("msg", "请选择");
	$("#showt_mile").attr("dataType", "Require").attr("msg", "请填写");
	$("#showt_cash").attr("dataType", "Require").attr("msg", "请填写");
	$("#showt_time").attr("dataType", "Require").attr("msg", "请填写");
	$("#fixdate").attr("dataType", "Require").attr("msg", "请填写");
	
	$("#btn_submit").click(function(){
		if(Validator.Validate(this.form, 3)){
            $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
            $("#btn_back").attr("disabled", "true");
			this.form.submit();
		}
	});
});
function getShowInfo(obj) {
	var $p = $(obj).parent();
	var returnValue = window.showModalDialog("SelectShowInfoForManinfo.do?selects=" + $("#show_shop_ids").val() + "&selectype=signal&azaz=" + Math.random(), window, "dialogWidth:600px;status:no;dialogHeight:450px");
	if (returnValue != null) {
		$("#show_shop_code", $p).val(returnValue.shop_code);
		$("#show_shop_name").val(returnValue.shop_name);
	}
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
