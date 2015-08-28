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
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
</head>
<body>
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
    <html-el:form action="/zmd/KonkaXxGoods">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="tree_param" value="${tree_param}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="15">&nbsp;</td>
          <td><strong class="fb">添加时间：</strong>
            <html-el:text property="add_date_start" styleId="add_date_start" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" />
            至
            <html-el:text property="add_date_end" styleId="add_date_end" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" /></td>
          <c:if test="${role_id_200_btw_300 or role_id_ge_30}">
            <td><strong class="fb">所属分公司：</strong>
              <html-el:select property="dept_id">
                <html-el:option value="">==请选择==</html-el:option>
                <c:forEach items="${konkaDeptList}" var="cur">
                  <c:if test="${af.map.dept_id eq cur.dept_id}">
                    <option value="${cur.dept_id}" selected="selected">${cur.dept_name}</option>
                  </c:if>
                  <c:if test="${af.map.dept_id ne cur.dept_id}">
                    <option value="${cur.dept_id}">${cur.dept_name}</option>
                  </c:if>
                </c:forEach>
              </html-el:select></td>
          </c:if>
          <td><input class="but1" type="submit" name="Submit" value="搜索" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
    <c:if test="${role_id_btw_300_400 or role_id_btw_30_40}">
      <!-- 不是分公司用户不让添加 -->
      <c:set var="can_edit" value="ebiz" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td><input name="button" type="button"  class="but2" value=" 新增 " onclick="location.href='KonkaXxGoods.do?method=add&mod_id=${af.map.mod_id}';" /></td>
        </tr>
      </table>
    </c:if>
  </div>
  <div class="rtabcont1">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2" id="t_1">
      <tr style="display:none;" id="t_2">
        <td colspan="13" style="font-weight:bold;font-size:18px;height:40px;text-align:center;border-bottom:none;">康佳专卖店资源管理数据表${af.map.now_date}</td>
      </tr>
      <tr class="tabtt1">
        <td width="5%" align="center" nowrap="nowrap"><font class="blue">序号</font></td>
        <td width="10%" align="center" nowrap="nowrap"><font class="blue">分公司</font></td>
        <td width="20%" align="center" nowrap="nowrap"><font class="blue">物料名称</font></td>
        <td width="17%" align="center" nowrap="nowrap"><font class="blue">仓位</font></td>
        <td width="10%" align="center" nowrap="nowrap"><font class="blue">数量</font></td>
        <td width="13%" align="center" nowrap="nowrap"><font class="blue">添加时间</font></td>
        <td width="15%" align="center" nowrap="nowrap"><font class="blue">添加人</font></td>
        <td width="10%" align="center" nowrap="nowrap"><font class="blue">操作</font></td>
      </tr>
      <c:forEach var="cur" items="${entityList}" varStatus="vs">
        <tr>
          <td align="center">${(af.map.pager.currentPage - 1)*af.map.pager.pageSize + vs.count}</td>
          <td align="left" nowrap="nowrap">${cur.map.dept_name}</td>
          <td align="left" nowrap="nowrap"><c:out value="${cur.goods_name}" /></td>
          <td align="left" nowrap="nowrap">${cur.map.store_name}</td>
          <td align="right">${cur.counts}</td>
          <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
          <td align="left" nowrap="nowrap">${cur.map.user_name}</td>
          <td align="center"><span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaXxGoods.do', 'view', 'goods_id=${cur.goods_id}&mod_id=${af.map.mod_id}&' + $('#bottomPageForm').serialize())">查看</span> |
            <c:if test="${(not empty can_edit) and (can_edit eq 'ebiz')}"> <span style="cursor:pointer;" class="fblue" onclick="confirmUpdate(null, 'KonkaXxGoods.do', 'goods_id=${cur.goods_id}&mod_id=${af.map.mod_id}&' + $('#bottomPageForm').serialize())">修改</span> </c:if>
            <c:if test="${empty can_edit}"> <span style="cursor:pointer;color:#ccc;">修改</span> </c:if></td>
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
        </tr>
      </c:forEach>
    </table>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaXxGoods.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td>&nbsp;</td>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
	            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
	            pager.addHiddenInputs("method", "list");
	            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
	            pager.addHiddenInputs("add_date_start", "${af.map.add_date_start}");
	            pager.addHiddenInputs("add_date_end", "${af.map.add_date_end}");
	            pager.addHiddenInputs("dept_id", "${af.map.dept_id}");
	            document.write(pager.toString());
	      </script></td>
        </tr>
      </table>
    </form>
  </div>
  <div class="clear"></div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	// 日期控件
	$("#add_date_start").datepicker();
	$("#add_date_end").datepicker();
	
	$("#span_help").click(function(){
        $("#cvtooltipClose").cvtooltip({
            panel: "#body_oarcont",
            direction: 1,                
            width: 420,
            left: 320,
            top: 5,
            speed: 500,
            delay: 10000
        });
    });
});
//]]></script>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>