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
  <div class="rtabcont2">
    <html-el:form action="/zmd/KonkaXxZmdAccouts">
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="bill_id" value="${af.map.bill_id}" />
      <html-el:hidden property="rec_money_s" value="${af.map.rec_money_s}" />
      <html-el:hidden property="queryString" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable4">
      <tr>
         <td colspan="4" class="title_item">账单基础信息</td>
      </tr>
        <tr>
          <td width="15%" nowrap="nowrap" align="right">账单标题：</td>
          <td colspan="3">${fn:escapeXml(af.map.title)}</td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" align="right">分公司：</td>
          <td width="35%">${dept_name}</td>
          <td width="15%" nowrap="nowrap" align="right">专卖店：</td>
          <td width="35%">${zmd_sn}</td>
        </tr>
        <tr>
          <td nowrap="nowrap" align="right">账单起始时间：</td>
          <td><fmt:formatDate value="${af.map.start_time}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
          <td nowrap="nowrap" align="right">账单结束时间：</td>
          <td><fmt:formatDate value="${af.map.end_time}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
        </tr>
        <tr>
          <td colspan="4" class="title_item">账单金额信息</td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" align="right">账单总金额[期初]：</td>
          <td><fmt:formatNumber value="${af.map.bill_money_s}" pattern="#0.00"/>元</td>
          <td width="15%" nowrap="nowrap"  align="right">账单总金额[期末]：</td>
          <td><fmt:formatNumber value="${af.map.bill_money_e}" pattern="#0.00"/>元</td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" align="right">账单总金额[当期]：</td>
          <td colspan="3"><fmt:formatNumber value="${af.map.bill_money}" pattern="#0.00"/>元</td>
        </tr>
        <tr>
          <td colspan="4" class="title_item">结账信息</td>
        </tr>                          
        <tr>
          <td width="15%" nowrap="nowrap" align="right">实收款[期初]：</td>
          <td><fmt:formatNumber value="${af.map.rec_money_s}" pattern="#0.00"/>元</td>
          <td width="15%" nowrap="nowrap"  align="right">实收款[期末]：</td>
          <td><fmt:formatNumber value="${af.map.rec_money_e}" pattern="#0.00"/>元</td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" align="right"><span style="color:#F00;">[必填]</span>实收款[当期]：</td>
          <td colspan="3"><html-el:text property="rec_money" styleId="rec_money" maxlength="10" size="12" />元</td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" align="right"><span style="color:#F00;">[必填]</span>结账人签名：</td>
          <td colspan="3"><html-el:text property="jz_user" styleId="jz_user" maxlength="10" size="12" /></td>
        </tr>
        <tr>
          <td>&nbsp;&nbsp;</td>
          <td colspan="2"><input class="but4" type="button" name="Submit4" value="保存" id="send" />
            <input class="but3" type="reset"  value="重填 " />
            <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back()" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="clear"></div>
</div>
<!-- ****** Main Frame End ****** -->
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#rec_money").attr("focus",setOnlyNum);
	$("#rec_money").attr("dataType", "Require").attr("msg", "请填写实收款！");
	$("#jz_user").attr("dataType", "Require").attr("msg", "请填写结账人姓名！");
	$("#send").click(function(){
			var isSubmit = Validator.Validate(this.form, 3);

			if (isSubmit) {
				$(":button").attr("disabled", "true");
				$(":reset").attr("disabled", "true");
				this.form.submit();
			}
	 });
});

function setOnlyNum() {
	$(this).css("ime-mode", "disabled").attr("t_value", "").attr("o_value", "");
	$(this).bind("dragenter",function(){
		return false;
	});
	$(this).keypress(function (){
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).keyup(function (){
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).blur(function (){
		if(!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value;}
		if(this.value.length == 0) this.value = "0";
	});
	//this.text.selected;
}
//]]></script>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>
