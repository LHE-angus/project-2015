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
<link href="${ctx}/styles/customer/jNotify/jNotify.jquery.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.webinput {
	background:#f5f4f4;
	padding-left: 5px;
	height: 19px;
	line-height: 19px;
	/*font-family: Arial, Helvetica, sans-serif;*/
	border: #ccc solid 1px;
}
</style>
</head>
<body style="font-family:Microsoft Yahei;">
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td nowrap="nowrap">当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/admin/KonkaInterfaceAccessLog">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td height="36" align="left" style="padding-left:5px;">
          			&nbsp;&nbsp;授权码  ： <html-el:text property="licenses_sn_like" styleId="licenses_sn_like" styleClass="webinput" />
					&nbsp;&nbsp;接口名称： <html-el:text property="req_mod_name_like" styleId="req_mod_name_like" styleClass="webinput" />&nbsp;&nbsp;
					&nbsp;访问日期：
        		<html-el:text property="add_time_start" styleId="add_time_start"  size="9" maxlength="10" readonly="true" onclick="new Calendar(2010, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
				至
				<html-el:text property="add_time_end" styleId="add_time_end"  size="9" maxlength="10" readonly="true" onclick="new Calendar(2010, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
            &nbsp;
            <input name="button" type="submit" class="bgSearch" id="button" value="搜 索" style="font-family:Microsoft YAHEI;" />
          </td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
      <tr class="tabtt1">
        <td width="5%" nowrap="nowrap" align="center">序号</td>
        <td nowrap="nowrap" align="center" >授权码</td> 
        <td nowrap="nowrap" width="15%" align="center">访问用户</td>
        <td width="15%" nowrap="nowrap" align="center">用户授权码</td>
        <td width="15%" nowrap="nowrap" align="center">访问ip</td>
        <td width="15%" nowrap="nowrap" align="center">接口名称</td>
        <td width="15%" nowrap="nowrap" align="center">返回状态</td>
        <td width="20%" nowrap="nowrap" align="center">添加时间</td>
         <td width="6%" nowrap="nowrap" align="center">操作</td>
      </tr>
      <c:forEach items="${entityList}" var="cur" varStatus="vs">
        <tr>
          <td align="center" nowrap="nowrap">${vs.count}</td>
          <td align="center" nowrap="nowrap">${cur.licenses_sn}</td>  
          <td align="center" nowrap="nowrap"><span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaInterfaceAccessLog.do','view' ,'id=${cur.id}&' + $('#bottomPageForm').serialize())">${cur.map.user_name}</span></td>
          <td align="center" nowrap="nowrap">${cur.user_key} </td>
          <td align="center" nowrap="nowrap">${cur.ip} </td>
          <td align="center" nowrap="nowrap">${cur.req_mod_name}</td>
          <td align="center" nowrap="nowrap">
           <c:if test="${cur.req_state eq 0}">成功</c:if>
           <c:if test="${cur.req_state eq 1}">失败</c:if>   
           </td>
          <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.add_date}"  pattern="yyyy-MM-dd"></fmt:formatDate></td>
          <td align="center" nowrap="nowrap"> 
          	<span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaInterfaceAccessLog.do','view' ,'id=${cur.id}&' + $('#bottomPageForm').serialize())">查看</span>
          </td>
        </tr>
      </c:forEach>
    </table>
    <c:if test="${not vs.last}">
      <div style="height:40px;"></div>
    </c:if>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaInterfaceAccessLog.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40"><div style="text-align: right; padding-right: 5px;">
              <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
              <script type="text/javascript">
								var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
								pager.addHiddenInputs("method", "list");
								pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
								pager.addHiddenInputs("licenses_sn_like", "${af.map.licenses_sn_like}");
								pager.addHiddenInputs("req_mod_name_like", "${af.map.req_mod_name_like}");
								pager.addHiddenInputs("add_time_start", "${af.map.add_time_start}");
								pager.addHiddenInputs("add_time_end", "${af.map.add_time_end}");
								document.write(pager.toString());
			  </script>
            </div></td>
        </tr>
      </table>
    </form>
  </div>
  <div class="clear"></div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/imgpreview.0.22.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/jNotify/jNotify.jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

	
});

//]]>
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
