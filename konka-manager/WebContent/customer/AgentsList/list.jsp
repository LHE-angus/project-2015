<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer/jquery-ui.custom.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/tip/jquery.qtip.css" rel="stylesheet" type="text/css" />
<style type="text/css">
select {
	font-family:Microsoft YAHEI;
	font-size:12px;
}
input {
	font-family:Microsoft YAHEI;
	font-size:12px;
}
</style>
<script type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script>
</head>
<body style="font-family:Microsoft Yahei;">
<div class="oartop">
  <table width="400" border="0" cellpadding="0" cellspacing="0">
    <tr>
      <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
      <td>当前位置：${naviString}</td>
    </tr>
  </table>
</div>
<html-el:form action="/manager/AgentsList">
  <html-el:hidden property="method" value="list" />
  <html-el:hidden property="mod_id" styleId="mod_id" />
  <div class="rtabcont1">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableTop">
      <tr>
        <td nowrap="nowrap" align="right">
        	<strong class="fb">网点名称：</strong>
        </td>
        <td>
          	<html-el:text property="partner_name_like" styleClass="webinput" styleId="partner_name_like" maxlength="40" size="18"/>
        </td>
        <td nowrap="nowrap" align="right">
        	<strong class="fb">网点编码：</strong>
        </td>
        <td>
          	<html-el:text property="partner_sn_like" styleClass="webinput" styleId="partner_sn_like" maxlength="40" size="18"/>
        </td>
        <td nowrap="nowrap" align="right">
        	<strong class="fb">网点级别：</strong>
        </td>
        <td>
          	<html-el:select property="p_level" styleId="p_level" style="width:110px">
	        </html-el:select>
        </td>
        <td nowrap="nowrap" align="right">
        	<strong class="fb">是否停用：</strong>
        </td>
        <td>
          	<html-el:select property="is_del" styleId="is_del" style="width:70px;" onchange="this.form.submit();">
	        	<html-el:option value="0">未停用</html-el:option>
	            <html-el:option value="1">已停用</html-el:option>
	        </html-el:select>
        </td>
     </tr>
     <tr>
        <td nowrap="nowrap" align="right">
        	<strong class="fb">上级网点：</strong>
        </td>
        <td>
        	<html-el:text property="par_name_like" styleClass="webinput" styleId="par_name_like" maxlength="40" size="18"/>
        </td>
        <td nowrap="nowrap" align="right">
        	<strong class="fb">联系人姓名：</strong>
        </td>
        <td>
        	<html-el:text property="link_name_like" styleClass="webinput" styleId="link_name_like" maxlength="40" size="18"/>
        </td>
        <td nowrap="nowrap" align="right">
        	<strong class="fb">联系人电话：</strong>
        </td>
        <td>
        	<html-el:text property="link_mobile_like" styleClass="webinput" styleId="link_mobile_like" maxlength="40" size="18"/>
        </td>
        <td nowrap="nowrap" align="right">
        </td>
        <td>
          <input name="search" type="submit" class="bgSearch" id="search" value="搜 索" />
        </td>
      </tr>
    </table>
  </div>
</html-el:form>
<div class="rtabcont1">
  <%@ include file="/commons/pages/messages.jsp" %>
  <input name="input" type="button" id="gotoAdd" class="bgButtonAdd" value="新 增" onclick="location.href='AgentsList.do?method=add&r3_id=${af.map.r3_id}&mod_id=${af.map.mod_id}'" />
</div>
<div class="rtabcont1">
  <div style="overflow-x:auto;">
  <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
    <tr class="tabtt1">
      <td width="3%" align="center" nowrap="nowrap">序号</td>
      <td width="10%" align="center" nowrap="nowrap">网点名称</td>
      <td width="15%" align="center" nowrap="nowrap">网点编码</td>
      <td width="6%" align="center" nowrap="nowrap">网点级别</td>
      <td width="10%" align="center" nowrap="nowrap">上级网点</td>
      <td width="10%" align="center" nowrap="nowrap">上级网点编码</td>
      <td width="8%" align="center" nowrap="nowrap">联系人姓名</td>
      <td width="8%" align="center" nowrap="nowrap">联系电话</td>
      <td width="10%" align="center" nowrap="nowrap">省</td>
      <td width="10%" align="center" nowrap="nowrap">市</td>
      <td width="10%" align="center" nowrap="nowrap">县</td>
      <td width="10%" align="center" nowrap="nowrap">镇</td>
      <td width="15%" align="center" nowrap="nowrap">地址</td>
      <td width="5%" align="center" nowrap="nowrap">状态</td>
      <td width="5%" align="center" nowrap="nowrap">是否确认</td>
      <td width="5%" align="center" nowrap="nowrap">有无账户</td>
      <td width="8%" align="center" nowrap="nowrap">创建时间</td>
      <td width="8%" align="center" nowrap="nowrap">操作</td>
    </tr>
    <c:forEach items="${entityList}" var="cur" varStatus="vs">
      <tr>
        <td nowrap="nowrap" align="center">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
        <td nowrap="nowrap" align="left">
        	<a style="color:#00F;text-decoration:underline;" href="AgentsList.do?method=view&partner_id=${cur.partner_id}&mod_id=${af.map.mod_id}">${cur.partner_name}</a>
        </td>
        <td nowrap="nowrap" align="left">${cur.partner_sn}</td>
        <td nowrap="nowrap" align="center">
        	<c:if test="${empty cur.p_level }"><span style="color:#ccc;">未知</span></c:if>
        	<c:if test="${not empty cur.p_level }">${cur.p_level }级</c:if>
        </td>
        <td nowrap="nowrap" align="left">${cur.map.par_name}</td>
        <td nowrap="nowrap" align="left">${cur.map.par_id}</td>
        <td nowrap="nowrap" align="left">${cur.link_name}</td>
        <td nowrap="nowrap" align="left">${cur.link_mobile}</td>
        <td nowrap="nowrap">${cur.map.PROVINCE }</td>
        <td nowrap="nowrap">${cur.map.CITY }</td>
        <td nowrap="nowrap">${cur.map.COUNTRY }</td>
        <td nowrap="nowrap">${cur.map.TOWN }</td>
        <td nowrap="nowrap">${cur.partner_addr }</td>
        <td nowrap="nowrap" align="center">
        	<c:if test="${cur.is_del eq 0}"><span style="color:green;">启用</span></c:if>
          	<c:if test="${cur.is_del eq 1}"><span style="color:#f00;">停用</span></c:if>
        </td>
        <td nowrap="nowrap">
        	<c:choose>
				<c:when test="${cur.map.status eq '0' }">待确认</c:when>
				<c:when test="${cur.map.status eq '2' }">已确认</c:when>
				<c:otherwise>已确认</c:otherwise>
			</c:choose>
        </td>
        <td nowrap="nowrap" align="center">${empty cur.partner_c_id ? '无' : '有'}</td>
        <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd" /></td>
        <td align="center" nowrap="nowrap">
        	<c:if test="${cur.is_del eq 0}">
        		<span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'AgentsList.do', 'edit','&mod_id=${af.map.mod_id}&partner_id=${cur.partner_id}&r3_id=${af.map.r3_id}&'+$('#bottomPageForm').serialize())">修改</span>
				<c:if test="${cur.map.status eq '0' }"><span class="fblue" style="color:#ccc;">停用</span></c:if>
			  	<c:if test="${cur.map.status ne '0' }"><span class="fblue" style="cursor:pointer;" onclick="stopOrstart('${cur.partner_id}','stop');">停用</span></c:if>
			</c:if>
          	<c:if test="${cur.is_del eq 1}"> 
          		<span class="fblue" style="color:#ccc;">修改</span> 
          		<c:if test="${cur.map.status eq '0' }"><span class="fblue" style="color:#ccc;">启用</span></c:if>
              	<c:if test="${cur.map.status ne '0' }"><span class="fblue" style="cursor:pointer;" onclick="stopOrstart('${cur.partner_id}','start');">启用</span></c:if>
          	</c:if>
        </td>
      </tr>
    </c:forEach>
     <c:forEach begin="${fn:length(entityList)}" end="${af.map.pager.pageSize - 1}">
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
	   </tr>
    </c:forEach>
  </table>
  </div>
  <c:if test="${not empty entityList}">
    <div class="rtabcont3">
      <form id="bottomPageForm" name="bottomPageForm" method="post" action="AgentsList.do">
        <table width="100%" border="0" align="center" cellspacing="0" cellpadding="0">
          <tr>
            <td height="40" align="center"><script type="text/javascript">
		            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
		            pager.addHiddenInputs("method", "list");
		            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
		            pager.addHiddenInputs("partner_name_like", "${af.map.partner_name_like}");
		            pager.addHiddenInputs("partner_sn_like", "${af.map.partner_sn_like}");
		            pager.addHiddenInputs("p_level", "${af.map.p_level}");
		            pager.addHiddenInputs("is_del", "${af.map.is_del}");
		            pager.addHiddenInputs("par_name_like", "${af.map.par_name_like}");
		            pager.addHiddenInputs("link_name_like", "${af.map.link_name_like}");
		            pager.addHiddenInputs("link_mobile_like", "${af.map.link_mobile_like}");
		            document.write(pager.toString());
		     </script></td>
          </tr>
        </table>
      </form>
    </div>
  </c:if>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/scripts/lhgdialog/lhgdialog.min.js?skin=discuz"></script> 
<script type="text/javascript">
	function stopOrstart(id,flag){
		$.dialog({
			title:  "停用/启用申请",
			width:  570,
			height: 200,
	        lock:true ,
			content:"url:../AuditDialog/audit_Form.jsp?id="+id+"&flag="+flag+"&type=agent"
		});
	}
	$(document).ready(function(){
		//级别初始化
		$("#p_level").attr({"defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.p_level}"});
		$("#p_level").cs("${ctx}/customer/manager/CsAjax.do?method=getPartnerLevel", "p_level", "0", false);
		$("#p_level").change();
	});	
</script>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>