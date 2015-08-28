<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>退换货管理&gt; 换货审批</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
-->
</style>
</head>
<body>
<div style="width:100%">
  <div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;"/> 当前位置：${naviString}</div>
  <div class="rtabcont1">
    <html-el:form action="/KonkaJxcHhAudit.do" enctype="multipart/form-data">
      <html-el:hidden property="id" />
      <html-el:hidden property="audit_user_id" />
      <html-el:hidden property="audit_dept_id" />
      <html-el:hidden property="audit_user_type" />
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" />
      <html-el:hidden property="queryString" />
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
        <tr>
          <th colspan="2">换货申请信息</th>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item">产品类别：</td>
          <td>${fn:escapeXml(af.map.pd_type_name)}</td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item">产品型号：</td>
          <td>${fn:escapeXml(af.map.pd_name)} </td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item">换货产品类型：</td>
          <td><c:if test="${af.map.hh_type eq 0}">残次品</c:if>
            <c:if test="${af.map.hh_type eq 1}">正品</c:if></td>
        </tr>
        <tr>
          <td class="title_item">产品单价：</td>
          <td>${fn:escapeXml(af.map.price)}</td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item">换货数量：</td>
          <td>${af.map.hh_count}</td>
        </tr>
        <tr>
          <td class="title_item">换货日期：</td>
          <td><fmt:formatDate value="${af.map.in_date}" pattern="yyyy-MM-dd" /></td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item">换货原因：</td>
          <td>${fn:escapeXml(af.map.hh_reason)}</td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item"><font color="red">*</font>审批：</td>
          <td><html-el:select property="is_audit" styleId="is_audit">
              <html-el:option value="1">通过</html-el:option>
              <html-el:option value="-1">不通过</html-el:option>
            </html-el:select></td>
        </tr>
        <tr style="display:none" id="hh_store_par">
          <td width="15%" nowrap="nowrap" class="title_item"><font color="red">*</font>换货仓库：</td>
          <td><html-el:select property="hh_store_id_par" styleId="hh_store_id_par">
              <html-el:option value="">请选择...</html-el:option>
              <c:forEach var="cur" items="${storeList}">
                <html-el:option value="${cur.id}">${fn:escapeXml(cur.store_name)}</html-el:option>
              </c:forEach>
            </html-el:select></td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item"><font color="red">*</font>审批原因：</td>
          <td><html-el:text property="audit_reason" style="width:60%" styleId="audit_reason" styleClass="webinput" maxlength="100"></html-el:text></td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item">备注：</td>
          <td>${fn:escapeXml(af.map.remark)} </td>
        </tr>
        <tr>
          <td colspan="2" align="center"><html-el:button property="save" styleClass="bgButtonSave" styleId="btn_submit" value="保 存"/>
            <html-el:button property="back" value=" 返 回 " onclick="history.back();" styleClass="bgButtonBack"/></td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript">//<![CDATA[
	$(document).ready(function(){
			$("#audit_reason").attr("datatype", "Require").attr("msg", "请输写审批原因！");
		if($("#is_audit").val() == '-1'){
			$("#hh_store_par").hide();
			$("#hh_store_id_par").attr("datatype", "");
		}else if($("#is_audit").val() == '1'){
			$("#hh_store_par").show();
			$("#hh_store_id_par").attr("datatype", "Require").attr("msg", "请选择仓库！");
		}
		$("#is_audit").change(function(){
			if($(this).val() == '-1'){
				$("#hh_store_par").hide();
				$("#hh_store_id_par").attr("datatype", "");
			}else if($(this).val() == '1'){
				$("#hh_store_par").show();
				$("#hh_store_id_par").attr("datatype", "Require").attr("msg", "请选择仓库！");
			}
		});

		var f = document.forms[0];
		$("#btn_submit").click(function(){
			var isSubmit = Validator.Validate(f, 3);
			if (isSubmit){
				$(":submit").attr("value", "正在提交...").attr("disabled", "true");
				$(":button").attr("disabled", "true");
				$(":reset").attr("disabled", "true");
				f.submit();
			}
		});	
});
	
function setOnlyNum() {
		$(this).css("ime-mode", "disabled");
		$(this).attr("t_value", "");
		$(this).attr("o_value", "");
		$(this).bind("dragenter",function(){
			return false;
		});
		$(this).keypress(function (){
			if(!this.value.match(/^\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
		}).keyup(function (){
			if(!this.value.match(/^\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
		}).blur(function (){
			if(!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value;}
			if(this.value.length == 0) this.value = "";
		});
		//this.text.selected;
	}
//]]></script>
<jsp:include page="../public_page.jsp" flush="true"/>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
