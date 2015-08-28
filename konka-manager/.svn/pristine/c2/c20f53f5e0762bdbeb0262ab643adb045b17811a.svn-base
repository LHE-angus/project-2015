<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${naviString}</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div style="width:100%">
  <div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;"/> 当前位置：${naviString}</div>
  <div class="rtabcont1">
    <html-el:form action="/KonkaJxcFhBillRegister">
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="queryString" />
      <html-el:hidden property="fh_bill_id" value="${af.map.fh_bill_id}" />
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
        <tr>
          <th colspan="9">发货登记信息修改</th>
        </tr>
        <tr>
          <th width="5%" height="30" align="center" nowrap="nowrap" bgcolor="#fff2dc"><font class="bigall">序号</font></th>
          <th width="10%" height="30" align="center" nowrap="nowrap" bgcolor="#fff2dc"><font class="bigall">
            <c:if test="${not empty is_shiyebu}">分公司</c:if>
            <c:if test="${not empty is_fenjingban}">网点</c:if>
            </font></th>
          <th width="10%" height="30" align="center" nowrap="nowrap" bgcolor="#fff2dc"><font class="bigall">发货单号</font></th>
          <th width="15%" height="30" align="center" nowrap="nowrap" bgcolor="#fff2dc"><font class="bigall">发货时间</font></th>
          <th width="10%" height="30" align="center" nowrap="nowrap" bgcolor="#fff2dc"><font class="bigall">出货仓库</font></th>
          <th width="10%" height="30" align="center" nowrap="nowrap" bgcolor="#fff2dc"><font class="bigall">产品类型</font></th>
          <th width="10%" height="30" align="center" nowrap="nowrap" bgcolor="#fff2dc"><font class="bigall">产品型号</font></th>
          <th width="15%" height="30" align="center" nowrap="nowrap" bgcolor="#fff2dc"><font class="bigall">数量</font></th>
          <th width="15%" height="30" align="center"  bgcolor="#fff2dc"><font class="bigall">备注</font></th>
        </tr>
        <tbody id="tbody_jhdj">
          <c:forEach items="${konkaJxcFhBillDetailsList}" var="cur" varStatus="vs">
            <tr>
              <html-el:hidden property="fh_bill_details_id" value="${cur.id}" />
              <!--  上次发货数量         -->
              <html-el:hidden property="last_fh_count" styleId="last_fh_count" value="${cur.count}" />
              <!--   当前库存数量         -->
              <html-el:hidden property="maxPdCount" styleId="maxPdCount" value="${cur.map.current_store_pd_num}" />
                <td width="5%" align="center" nowrap="nowrap" width="10%" align="center" nowrap="nowrap">
              ${vs.count}
                </td>
              <td width="10%" align="center" nowrap="nowrap">${fn:escapeXml(cur.map.branch_name)}</td>
              <td width="10%" align="center" nowrap="nowrap">${fn:escapeXml(cur.fh_bill_id)}</td>
              <td width="15%" align="center" nowrap="nowrap"><fmt:formatDate value="${cur.fh_date}" pattern="yyyy-MM-dd" /></td>
              <td width="10%" align="center" nowrap="nowrap">${fn:escapeXml(cur.map.store_name)}</td>
              <td width="10%" align="center" nowrap="nowrap">${fn:escapeXml(cur.pd_type_name)}</td>
              <td width="10%" align="center" nowrap="nowrap">${fn:escapeXml(cur.pd_name)}</td>
              <html-el:hidden property="selectPdName" styleId="selectPdName" value="${cur.pd_name}" />
              <td width="15%" align="center" nowrap="nowrap"><html-el:text property="count"  styleId="pdCount" styleClass="webinput" maxlength="4" value="${cur.count}"></html-el:text></td>
              <td width="15%" align="center">${fn:escapeXml(cur.remark)}</td>
            </tr>
          </c:forEach>
        </tbody>
        <tr>
          <td colspan="9" align="left"><span id="store_tip" style=""></span>&nbsp;</td>
        </tr>
        <tr>
          <td colspan="9" align="center"><html-el:button property="" value="确认" styleClass="bgButtonSave" styleId="btn_submit" />
            &nbsp;
            <html-el:button property="" value="重 填" styleClass="bgButtonReset" styleId="btn_reset" onclick="this.form.reset();" />
            &nbsp;
            <html-el:button property="" value="返 回" styleClass="bgButtonBack" styleId="btn_back" onclick="history.back();" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/commons.plugin.jxc.js"></script> 
<script type="text/javascript"><!--//<![CDATA[
$(document).ready(function(){
	$("#btn_submit").click(function(){
		if (Validator.Validate(this.form, 1)) {
			$("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
			$("#btn_reset").attr("disabled", "true");
			$("#btn_back").attr("disabled", "true");
			this.form.submit();
		}
	});
	//在修改数量时，验证库存---
	//填写的数量范围  (  0  至   last_fh_count + maxPdCount)或(  last_fh_count - maxPdCount 至   last_fh_count + maxPdCount)

		
		$("input[type='text'][name='count']").focus(function(){
			var lastTR = $(this).parent().parent();
			var JQ_pd_count = $("#pdCount", lastTR);//发货数量
			var JQ_last_fh_count = $("#last_fh_count", lastTR);//修改前的发货数量
			var JQ_maxPdCount = $("#maxPdCount", lastTR);//当前库存数量
			var maxInputCount = parseFloat(JQ_maxPdCount.val()) + parseFloat(JQ_last_fh_count.val());//允许输入的最大数量
			
			JQ_pd_count.attr("dataType", "Integer").attr("msg", "请填写数量,且必须是整数");
			var selectPdName =$("#selectPdName", lastTR).val();
			$("#store_tip").empty().append('<span id="tip" style="color:#060;">产品：[' + selectPdName + ']当前库存数量为:[ ' + JQ_maxPdCount.val()+
			' ] ,您输入的数量必须小于:</span>'+'<span id="tip" style="color:#f00;">[ '+ maxInputCount +' ]</span>');
		}).keyup(function(){
			var lastTR = $(this).parent().parent();
			var JQ_pd_count = $("#pdCount", lastTR);//发货数量
			var thisCount = parseFloat(JQ_pd_count.val());
			var JQ_last_fh_count = $("#last_fh_count", lastTR);//修改前的发货数量
			var JQ_maxPdCount = $("#maxPdCount", lastTR);//当前库存数量
			var maxInputCount = parseFloat(JQ_maxPdCount.val()) + parseFloat(JQ_last_fh_count.val());//允许输入的最大数量
			
			if (isNaN(thisCount)) thisCount = 0;
			if(thisCount > parseFloat(maxInputCount)){
				alert("对不起，您输入的数量不符合要求，请重新输入！");
				thisCount = JQ_last_fh_count.val();
			}
			$(this).focus().val(thisCount);
		});


});
//]]>--></script>
<jsp:include page="../public_page.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>
