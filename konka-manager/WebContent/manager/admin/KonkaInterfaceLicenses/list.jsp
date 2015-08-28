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
    <html-el:form action="/admin/KonkaInterfaceLicenses">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td height="36" align="left" style="padding-left:5px;">
          			&nbsp;&nbsp;授权码  ： <html-el:text property="licenses_sn_like" styleId="licenses_sn_like" styleClass="webinput" />
					&nbsp;&nbsp;授权单位 ： <html-el:text property="org_name_like" styleId="org_name_like" styleClass="webinput" />&nbsp;&nbsp;
					是否删除：<html-el:select property="is_del" styleId="is_del">
					<html-el:option value="0">正常</html-el:option>
					<html-el:option value="1">已删除</html-el:option>
					</html-el:select>&nbsp;&nbsp; 
            <input name="button" type="submit" class="bgSearch" id="button" value="搜 索" style="font-family:Microsoft YAHEI;" />
          </td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
		<%@ include file="/commons/pages/messages.jsp"%>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td>
					&nbsp;&nbsp;<input type="button" class="but2" value="新 增" onclick="location.href='KonkaInterfaceLicenses.do?method=add&mod_id=${af.map.mod_id}';" />
				</td>
			</tr>
		</table>		
	</div>
  <div class="rtabcont1">
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
      <tr class="tabtt1">
        <td width="5%" nowrap="nowrap" align="center">序号</td>
        <td nowrap="nowrap" align="center" >授权码</td>
        <td nowrap="nowrap" width="25%" align="center">单位名称</td>
        <td width="15%" nowrap="nowrap" align="center">是否注销</td>
        <td width="15%" nowrap="nowrap" align="center">是否删除</td>
        <td width="20%" nowrap="nowrap" align="center">添加时间</td>
         <td width="6%" nowrap="nowrap" align="center">操作</td>
      </tr>
      <c:forEach items="${entityList}" var="cur" varStatus="vs">
        <tr>
          <td align="center" nowrap="nowrap">${vs.count}</td>
          <td align="center" nowrap="nowrap"><span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaInterfaceLicenses.do','view' ,'id=${cur.id}&' + $('#bottomPageForm').serialize())">${cur.licenses_sn}</span></td>
          <td align="center" nowrap="nowrap">${cur.org_name}</td>
           <td align="center" nowrap="nowrap">
           <c:if test="${cur.info_state eq 0}">正常</c:if>
           <c:if test="${cur.info_state eq 1}">已注销</c:if>
           </td>
           <td align="center" nowrap="nowrap">
           <c:if test="${cur.is_del eq 0}">正常</c:if>
           <c:if test="${cur.is_del eq 1}">已删除</c:if>   
           </td>
          <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.add_date}"  pattern="yyyy-MM-dd"></fmt:formatDate></td>
          <td align="center" nowrap="nowrap"> 
          	<span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaInterfaceLicenses.do','view' ,'id=${cur.id}&' + $('#bottomPageForm').serialize())">查看</span>
         	 <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaInterfaceLicenses.do','edit' ,'id=${cur.id}&' + $('#bottomPageForm').serialize())">修改</span> 
          </td>
        </tr>
      </c:forEach>
    </table>
    <c:if test="${not vs.last}">
      <div style="height:40px;"></div>
    </c:if>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaInterfaceLicenses.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40"><div style="text-align: right; padding-right: 5px;">
              <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
              <script type="text/javascript">
								var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
								pager.addHiddenInputs("method", "list");
								pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
								pager.addHiddenInputs("licenses_sn_like", "${af.map.licenses_sn_like}");
								pager.addHiddenInputs("org_name_like", "${af.map.org_name_like}");
								pager.addHiddenInputs("is_del", "${af.map.is_del}");
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
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

	
});

//]]>
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
