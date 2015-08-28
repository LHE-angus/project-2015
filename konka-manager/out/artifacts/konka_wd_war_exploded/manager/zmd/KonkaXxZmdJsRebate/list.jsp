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
<link href="${ctx}/styles/jGrowl.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table id="tab_1" width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" alt="" style="vertical-align:middle;" /></td>
        <td id="ceshi">当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
  	<html-el:form action="/zmd/KonkaXxZmdJsRebate">
      <html-el:hidden property="method" value="stepOne" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="zmd_id" styleId="zmd_id" />
      <table id="tab_2" width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td nowrap="nowrap" style="padding:2px;">
          	<table width="80%" border="0" cellpadding="0" cellspacing="0">
          		 <tr>
          			<td width="35%" align="right" nowrap="nowrap" style="padding:20px 2px;">&nbsp;&nbsp;<strong class="fb">专卖店编号：</strong></td>
          			<td align="left" nowrap="nowrap" style="padding:2px;">
          				<html-el:select property="zmd" styleId="zmd" multiple="multiple">
		            		<c:forEach items="${zmdList}" var="cur">
		            			<html-el:option value="${cur.zmd_id}">${cur.zmd_sn}</html-el:option>
		            		</c:forEach>
		            	</html-el:select>
          			</td>
          		</tr>
          		<tr>
          			<td></td>
          			<td align="left" nowrap="nowrap" style="padding:2px 2px 20px 2px;">
          				<input class="but3" type="button" id="btn_submit" name="Submit" value="下一步" />
          				&nbsp;&nbsp;<input class="but8" style="width:120px;" type="button" onclick="getJsList()" value="查询结算记录" />	
          			</td>
          		</tr>
          	</table>
          </td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery1.5.1.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.jgrowl.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#zmd").multiselect({
		noneSelectedText: '=请选择=',
		selectedList: 1,
		multiple: false,
		minWidth:200,
		click: function(event, ui){
		if (ui.value != "") {
			$("#zmd_id").val(ui.value);
		}
	}
	}).multiselectfilter();

	$("#zmd_id").attr("dataType", "Require").attr("msg", "请选择专卖店！");

	$("#btn_submit").click(function(){
		if(Validator.Validate(this.form, 1)){
			if(confirm("确定要提交表单？")){
				//$("#message_tip").show();
	            $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
	            $("#reset").attr("disabled", "true");
	            $("#btn_back").attr("disabled", "true");
				this.form.submit();
			} else {
				return false;
			}
		}
	});
});

function getJsList(){
	if ("" == $("#zmd_id").val()) {
		alert("请选择专卖店！");
		return false
	}

	location.href = "KonkaXxZmdJsRebate.do?method=view&mod_id=${af.map.mod_id}&zmd_id=" + $("#zmd_id").val();
}
//]]></script>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>