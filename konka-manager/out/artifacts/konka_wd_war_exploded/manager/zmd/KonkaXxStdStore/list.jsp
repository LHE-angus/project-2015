<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align: middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/zmd/KonkaXxStdStore">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="tree_param" value="${tree_param}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="15"></td>
          <td><strong class="fb">工厂：</strong>
            <html-el:text property="factory_id_like" styleId="factory_id_like" size="16" maxlength="20"></html-el:text>
            &nbsp;&nbsp; <strong class="fb">库位：</strong>
            <html-el:text property="store_id_like" styleId="store_id_like" size="16" maxlength="20"></html-el:text>
            &nbsp;&nbsp;
            <c:if test="${empty dept_name}"> <strong class="fb">分公司：</strong>
              <html-el:select property="dept_name" styleId="dept_name">
                <html-el:option value="">==请选择==</html-el:option>
                <c:forEach var="cur" items="${konkaDeptList}">
                  <html-el:option value="${cur.dept_name}">${fn:escapeXml(cur.dept_name)}</html-el:option>
                </c:forEach>
              </html-el:select>
            </c:if>
            &nbsp;&nbsp;
            <input class="but1" type="submit" name="Submit" value="搜索" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp"%>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td><input class="but2" type="submit" name="Submit2" value="新增" onclick="location.href='KonkaXxStdStore.do?method=add&mod_id=${af.map.mod_id}&tree_param=${tree_param}';" /></td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
      <tr class="tabtt1">
        <td nowrap="nowrap" align="center" width="5%">序号</td>
        <td nowrap="nowrap" align="center" width="10%">工厂</td>
        <td nowrap="nowrap"  align="center" width="10%">库位</td>
        <td nowrap="nowrap"  align="center" >仓储地点描述</td>
        <td nowrap="nowrap"  align="center" width="10%">分公司</td>
        <!-- 
        <td nowrap="nowrap"  align="center" width="10%">操作</td>
        -->
      </tr>
      <tbody>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
            <td align="left">${fn:escapeXml(cur.factory_id)}</td>
            <td align="left">${fn:escapeXml(cur.store_id)}</td>
            <td align="left">${fn:escapeXml(cur.store_desc)}</td>
            <td align="left">${fn:escapeXml(cur.dept_name)}</td>
            <!--
            <td align="center"><span style="cursor: pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaXxStdStore.do', 'view','factory_id=${cur.factory_id}&store_id=${cur.store_id}&'+$('#bottomPageForm').serialize())">查看</span></td>
          	-->
          </tr>
        </c:forEach>
        <c:forEach begin="${fn:length(entityList)}" end="${af.map.pager.pageSize - 1}" step="1">
          <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaXxStdStore.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40"><div style="text-align: right; padding-right: 5px;">
              <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
              <script type="text/javascript">
				var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
				pager.addHiddenInputs("method", "list");
				pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
				pager.addHiddenInputs("tree_param", "${tree_param}");
				pager.addHiddenInputs("factory_id_like", "${af.map.factory_id_like}");
				pager.addHiddenInputs("store_id_like", "${af.map.store_id_like}");
				pager.addHiddenInputs("dept_name", "${af.map.dept_name}");
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
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	//I8右宽度不能自动适应加载，IE7,FireFox都可以的
	if(document.body.scrollLeft > 0 || document.body.scrollWidth > document.body.offsetWidth){
		$(".frame_right").width($(window).width() - 163);
	}else{
		$(".frame_right").width($(window).width() - 150);
	}
});
//]]>
</script>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>
