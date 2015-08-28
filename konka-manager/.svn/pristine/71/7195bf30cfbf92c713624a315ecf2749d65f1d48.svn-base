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
  <c:if test="${!empty not_zmd_dept}">
    <div class="rtabcont1">
      <html-el:form action="/zmd/KonkaXxZmdHdZjSb">
        <html-el:hidden property="method" value="list" />
        <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
        <html-el:hidden property="tree_param" value="${tree_param}" />
        <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
          <tr>
            <td width="15"></td>
            <td><strong class="fb">分公司：</strong>
              <html-el:select property="dept_id" styleId="dept_id">
                <html-el:option value="">-请选择-</html-el:option>
                <c:forEach items="${konkaDeptList}" var="cur">
                  <html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
                </c:forEach>
              </html-el:select>
              <input class="but1" type="submit" name="Submit" value="搜索" /></td>
          </tr>
        </table>
      </html-el:form>
    </div>
  </c:if>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp"%>
  </div>
  <c:if test="${!empty dept_id}">
    <div class="rtabcont1">
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td><input class="but2" type="submit" name="Submit2" value="新增" onclick="location.href='KonkaXxZmdHdZjSb.do?method=add&mod_id=${af.map.mod_id}&tree_param=${tree_param}&dept_id=${dept_id}';" /></td>
        </tr>
        <tr>
        <td align="right"><span style="color: red;">温馨提示:实际投入费用和实际产出的单位是万元!</span></td>
        </tr>
      </table>
    </div>
  </c:if>
  <div class="rtabcont1">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
      <tr class="tabtt1">
        <td nowrap="nowrap" align="center" width="5%">序号</td>
        <td nowrap="nowrap" align="center" width="10%">活动名称</td>
        <td nowrap="nowrap" align="center" width="10%">分公司</td>
        <td nowrap="nowrap" align="center" width="8%">专卖店</td>
        <td nowrap="nowrap"  align="center" width="14%">活动实际开展时间</td>
        <td nowrap="nowrap" align="center" >活动地点</td>
        <td nowrap="nowrap"  align="center" width="8%">实际投入费用</td>
        <td nowrap="nowrap"  align="center" width="8%">实际产出</td>
        <td nowrap="nowrap"  align="center" width="8%">负责人</td>
        <td nowrap="nowrap"  align="center" width="10%">负责人电话</td>
        <td nowrap="nowrap"  align="center" width="8%">操作</td>
      </tr>
      <tbody>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
            <td align="left">${fn:escapeXml(cur.hd_name)}</td>
            <td align="left" nowrap="nowrap">${fn:escapeXml(cur.map.dept_name)}</td>
            <td align="left" nowrap="nowrap">${fn:escapeXml(cur.map.zmd_sn)}</td>
            <td align="center"><fmt:formatDate value="${cur.real_start_date}" pattern="yyyy-MM-dd"/>
              至
              <fmt:formatDate value="${cur.real_end_date}" pattern="yyyy-MM-dd"/></td>
            <td align="left">${cur.hd_addr}</td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.real_money}" pattern="#0.0000"/></td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.real_outputs_money}" pattern="#0.0000"/></td>
            <td align="left">${cur.plan_user_name}</td>
            <td align="left">${cur.plan_user_tel}</td>
            <td align="center" nowrap="nowrap"><span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaXxZmdHdZjSb.do', 'edit','sp_hd_id=${cur.sp_hd_id}&mod_id=${af.map.mod_id}&dept_id=${cur.dept_id}&'+$('#bottomPageForm').serialize())">修改</span></td>
          </tr>
        </c:forEach>
        <c:forEach begin="${fn:length(entityList)}" end="${af.map.pager.pageSize - 1}" step="1">
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
          </tr>
        </c:forEach>
      </tbody>
    </table>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaXxZmdHdZjSb.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40"><div style="text-align: right; padding-right: 5px;">
              <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
              <script type="text/javascript">
				var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
				pager.addHiddenInputs("method", "list");
				pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
				pager.addHiddenInputs("tree_param", "${tree_param}");
				pager.addHiddenInputs("dept_id", "${af.map.dept_id}");
				document.write(pager.toString());
			  </script>
            </div></td>
        </tr>
      </table>
    </form>
  </div>
  <div class="clear"></div>
</div>
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
