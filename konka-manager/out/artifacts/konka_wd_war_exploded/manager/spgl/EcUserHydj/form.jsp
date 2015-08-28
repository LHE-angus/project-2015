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
<link href="${ctx}/styles/customer/multiselect/jquery.multiselect2side.css" rel="stylesheet" type="text/css" />
<style type="text/css">
#sel{margin-top:10px}
</style>
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
    <html-el:form action="/spgl/EcUserHydj.do" enctype="multipart/form-data">
      <html-el:hidden property="id" value="${af.map.id}" />
      <html-el:hidden property="method" value="save" /> 
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="queryString" />
      <html-el:hidden property="returnUrl" />
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
      	 <tr>
             <td width="20%" class="title_item" align="center">会员信息</td>
             <td width="80%" align="left"  colspan="3"> </td>
         </tr>
      	 <tr>
             <td width="12%" class="title_item" align="right">会员卡号：</td>
             <td width="20%" align="left" ><c:out value="${af.map.card_no}" /></td>        
             <td width="12%" align="right">姓名：</td>
             <td align="left" ><c:out value="${af.map.real_name}" /></td>
         </tr>
          <tr>
             <td width="12%" class="title_item" align="right">会员等级：</td>
             <td width="20%" align="left"  ><c:out value="${af.map.ecBaseCardLevel.card_level_name}" /></td>
         
             <td width="12%" class="title_item" align="right">会员类型：</td>
             <td align="left" ><c:if test="${af.map.user_type eq 1}">工卡会员</c:if><c:if test="${af.map.user_type eq 2}">触网会员</c:if><c:if test="${af.map.user_type eq 3}">其他</c:if> </td>
         </tr> 
          <tr>
             <td width="12%" class="title_item" align="right">部门：</td>
             <td  width="20%" align="left" ><c:out value="${af.map.department}"/> </td>
             <td width="12%" align="right">联系电话：</td>
             <td ><c:out value="${not empty af.map.link_phone ? cur.link_phone : (not empty af.map.link_tel ? af.map.link_tel : '')}" /> </td>
         </tr> 
         <tr>
             <td width="12%" class="title_item" align="right">邮箱：</td>
             <td colspan="3"><c:out value="${af.map.email}"/>  </td>
         </tr>         
         <tr>
             <td width="20%" class="title_item" align="center">会员升级</td>
             <td width="80%" align="left"  colspan="3"> </td>
         </tr>
         <tr>
             <td width="12%" class="title_item" align="right">当前付款积分：</td>
             <td colspan="3"><font color="red"><fmt:formatNumber value="${integral }" pattern="#,##0" /> </font></td>
         </tr> 
         <tr>
             <td width="12%" class="title_item" align="right">升级记录：</td>
             <td colspan="3">
              <table width="100%" border="0" cellspacing="1" cellpadding="0">
              <tr>
	              <td align="center" width="5%">序号</td>
	              <td align="center" width="15%">时间</td>
	              <td align="center" width="10%">新等级</td>
	              <td align="center" width="10%">原等级</td>
	              <td align="center" width="10%">操作人</td>
	              <td width="15%">备注</td>
              </tr>
             <c:forEach items="${auditInfoList}" var="cur" varStatus="vs">
               <tr>
	              <td align="center" >${vs.count }</td>
	              <td align="center"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
	              <td align="center">${cur.level_name }</td>
	              <td align="center">${cur.old_level_name }</td>
	              <td align="center">${cur.opr_user_name }</td>
	              <td title="${cur.memo }" align="left">
	              <c:choose>
                 <c:when test="${fn:length(cur.memo ) > 30}">
                 <c:out value="${fn:substring(cur.memo , 0, 30)}...." />
                 </c:when>
                 <c:otherwise>
                 <c:out value="${cur.memo }" />
                 </c:otherwise>
                 </c:choose>
	          </td>
              </tr> 
             </c:forEach>
             </table>
              </td>
         </tr> 
         <tr>
             <td width="12%" class="title_item" align="right"><font color="red">*</font>会员等级：</td>
             <td colspan="3">
             <c:forEach items="${eEcBaseCardLevelList}" var="cur" varStatus="vs">
               <input type="radio" name="cardLevel"  value="${cur.card_level }" ${af.map.ecBaseCardLevel.card_level eq cur.card_level ? "checked":"" }>${cur.card_level_name }, 奖励积分折扣 <font color="red">${cur.card_type_discount}%</font></input><br/>
             </c:forEach>
              </td>
         </tr> 
         <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">备注：</td>
          <td width="88%" align="left"  colspan="3">
          	<html-el:textarea property="memo" styleId="memo" cols="5" style="width:450px;height:70px;" />
          	<div id="info_tip" style="color:#0066FF;font-size:12px;display:none"><img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" /></div>
          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td  colspan="3"><html-el:button property="" value="升级" styleClass="but4" styleId="btn_submit" />
          &nbsp;&nbsp;  <html-el:button property="" value="返 回" styleClass="but5" styleId="btn_back" onclick="history.back();return false;" /></td>
        </tr>
        </table> 
    </html-el:form>
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs_ext.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){ 
	$("#memo").textbox({
		maxLength: 450,
		onInput: function(event, status) {
			var img = '<img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" />';
			$("#info_tip").slideDown("fast").html(img + " 请将字数限制在：" + status.maxLength + " 个字内，您还可以输入：<b style='color:red;'>" + status.leftLength + "</b> 个字。");
		}
	}).blur(function() {
		$("#info_tip").slideUp("normal");
	});
	
	
	$("#btn_submit").click(function(){
		var isSubmit = Validator.Validate(this.form, 3);
		if (isSubmit) {
			$(":button").attr("disabled", "true");
			$(":reset").attr("disabled", "true");
			this.form.submit();
		}
	}); 
}); 
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
