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
    <html-el:form action="/spgl/EcPdEavl" method="post">
      <html-el:hidden property="id" styleId="id" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="method" styleId="method" value="save" />
      <html-el:hidden property="queryString" styleId="queryString" />
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">商品名称：</td>
          <td width="88%" align="left">${af.map.map.pd_name}</td>
        </tr>
        <tr>
          <td  nowrap="nowrap" class="title_item" align="right">评论标题：</td>
          <td  align="left">${af.map.eval_title}</td>
        </tr>
        <tr>
          <td  nowrap="nowrap" class="title_item" align="right">评论优点：</td>
          <td  align="left">${af.map.eval_con_merit}</td>
        </tr>
        <tr>
          <td  nowrap="nowrap" class="title_item" align="right">评论缺点：</td>
          <td  align="left">${af.map.eval_con_defect}</td>
        </tr>
        <tr>
          <td  nowrap="nowrap" class="title_item" align="right">评论总结：</td>
          <td  align="left">${af.map.eval_con_sumary}
          <c:if test="${not empty af.map.konkaPeAttachmentsList}"><div>			    
			      <c:forEach var="imgs" items="${af.map.konkaPeAttachmentsList}" varStatus="vsi"> 
			       <a href="${ctx}/${imgs.save_path}" target="_blank"><img src="${ctx}/${imgs.save_path}" width="60"></img></a> 
			      </c:forEach> </div>
			  </c:if>
          </td>
        </tr> 
        <tr>
          <td  nowrap="nowrap" class="title_item" align="right">评论时间：</td>
          <td  align="left"><fmt:formatDate value="${af.map.eval_date}"  pattern="yyyy-MM-dd"></fmt:formatDate></td>
        </tr>
        <tr>
          <td  nowrap="nowrap" class="title_item" align="right">评分：最高5分：</td>
          <td  align="left">${af.map.eval_score}</td>
        </tr>
        <tr>
          <td  nowrap="nowrap" class="title_item" align="right">评论有用：</td>
          <td  align="left">${af.map.eval_useful}</td>
        </tr>
        <tr>
          <td  nowrap="nowrap" class="title_item" align="right">评论没用：</td>
          <td  align="left">${af.map.eval_useless}</td>
        </tr>
        <tr>
          <td  nowrap="nowrap" class="title_item" align="right">回复内容：</td>
          <td  align="left"><html-el:textarea property="re_content" styleId="re_content" style="width:80%;resize: none;" rows="2"></html-el:textarea>
            <div id="remark_note"  style="color:#0066FF;font-size:12px;display:none"><img src="${ctx}/styles/images/tishi.gif" style="vertical-align:middle;" /></div></td>
        </tr>
        <tr>
          <td  nowrap="nowrap" class="title_item" align="right">回复时间：</td>
          <td  align="left"> <fmt:formatDate value="${af.map.re_date}" pattern="yyyy-MM-dd" var="_re_date"/>
            <html-el:text property="re_date" value="${_re_date}" size="10" maxlength="20" readonly="true" onclick="new Calendar(2000, 2030, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" /></td>
        </tr>
        <tr>
          <td  nowrap="nowrap" class="title_item" align="right">回复人：</td>
          <td  align="left"><html-el:text property="re_real_name" maxlength="30"></html-el:text></td>
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
	$("textarea[name='re_content']").attr("datatype","LimitB").attr("datatype","Require").attr("max","200").attr("msg","填写备注信息在200字以内！");

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
