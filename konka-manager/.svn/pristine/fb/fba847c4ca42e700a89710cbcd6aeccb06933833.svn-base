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
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
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
    <html-el:form action="/spgl/EcQaInfo" method="post">
      <html-el:hidden property="qa_id" styleId="qa_id" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="method" styleId="method" value="save" />
      <html-el:hidden property="queryString" styleId="queryString" />
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">标题：</td>
          <td width="88%" align="left">${af.map.q_title}</td>
        </tr>
        <tr>
          <td  nowrap="nowrap" class="title_item" align="right">问题类型：</td>
          <td  align="left"><c:choose>
              <c:when test="${af.map.qa_type_code eq 0}">咨询</c:when>
              <c:when test="${af.map.qa_type_code eq 1}">投诉</c:when>
              <c:when test="${af.map.qa_type_code eq 2}">建议</c:when>
            </c:choose></td>
        </tr>
        <tr>
          <td  nowrap="nowrap" class="title_item" align="right">提问内容：</td>
          <td  align="left">${af.map.q_content}</td>
        </tr>
        <tr>
          <td  nowrap="nowrap" class="title_item" align="right">提问人姓名：</td>
          <td  align="left">${af.map.q_name}</td>
        </tr>
        <tr>
          <td  nowrap="nowrap" class="title_item" align="right">提问人固定电话：</td>
          <td  align="left">${af.map.q_tel}</td>
        </tr>
        <tr>
          <td  nowrap="nowrap" class="title_item" align="right">提问人手机：</td>
          <td  align="left">${af.map.q_phone}</td>
        </tr>
        <tr>
          <td  nowrap="nowrap" class="title_item" align="right">提问人邮箱：</td>
          <td  align="left">${af.map.q_email}</td>
        </tr>
        <tr>
          <td  nowrap="nowrap" class="title_item" align="right">提问人联系地址：</td>
          <td  align="left">${af.map.q_addr}</td>
        </tr>
        <tr>
          <td  nowrap="nowrap" class="title_item" align="right">提问时间：</td>
          <td  align="left"><fmt:formatDate value="${af.map.q_date}"  pattern="yyyy-MM-dd"></fmt:formatDate></td>
        </tr>
        <tr>
          <td  nowrap="nowrap" class="title_item" align="right">提问人IP：</td>
          <td  align="left">${af.map.q_ip}</td>
        </tr>
        <tr>
          <td  nowrap="nowrap" class="title_item" align="right">回答人：</td>
          <td  align="left"><html-el:text property="a_name" styleId="a_name" styleClass="webinput"  size="40" maxlength="32" /></td> 
        </tr>
        <tr>
          <td  nowrap="nowrap" class="title_item" align="right">回答内容：</td>
          <td  align="left"><html-el:textarea property="a_content" styleId="a_content" style="width:80%;resize: none;" rows="2"></html-el:textarea>
            <div id="remark_note"  style="color:#0066FF;font-size:12px;display:none"><img src="${ctx}/styles/images/tishi.gif" style="vertical-align:middle;" /></div></td>
        </tr>
        <tr>
          <td  nowrap="nowrap" class="title_item" align="right">回复：</td>
          <td  align="left"><html-el:select  property="info_state" styleId="info_state">
              <html-el:option value="">请选择...</html-el:option>
              <html-el:option value="-1">已删除</html-el:option>
              <html-el:option value="0">未回复</html-el:option>
              <html-el:option value="1">已回复</html-el:option>
            </html-el:select></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td><html-el:button property="" value="提交" styleClass="but4" styleId="btn_submit" />
            <html-el:button property="" value="返 回" styleClass="but5" styleId="btn_back" onclick="history.back();return false;" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs_ext.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

	$("#info_state").attr("datatype", "Require").attr("msg", "请选择回复结果！");
	$("textarea[name='a_content']").attr("datatype","LimitB").attr("max","2000").attr("msg","填写备注信息在2000字以内！");
	
	
	$("#btn_submit").click(function(){
		var isSubmit = Validator.Validate(this.form, 3);
		if (isSubmit) {
			$(":button").attr("disabled", "true");
			this.form.submit();
		}
	});
});


//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
