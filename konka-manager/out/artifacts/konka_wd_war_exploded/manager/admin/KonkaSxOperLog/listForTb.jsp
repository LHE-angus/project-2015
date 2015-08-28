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
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" alt="" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
 <div class="rtabcont1">
<html-el:form action="/admin/KonkaSxOperLog">
	<html-el:hidden property="method" styleId="method" value="listForTb" />
	<html-el:hidden property="mod_id" styleId="mod_id" />
	<table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="15"></td>
          	<td><strong class="fb">操作人：</strong>
				<html-el:text property="user_name" size="20" maxlength="20" styleId="user_name" /></td>
			 <td><strong class="fb">操作时间范围：</strong>
            <%-- <html-el:text property="oper_starttime"  size="10" maxlength="10" readonly="true" onclick="new Calendar(2011, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" /> --%>
            <input name="oper_starttime" id="oper_starttime" size="12" value="${af.map.oper_starttime}" title="点击选择日期" readonly="readonly" class="Wdate" type="text" onclick="WdatePicker({readOnly:true})" />
				至
			<%-- <html-el:text property="oper_endtime"  size="10" maxlength="10" readonly="true" onclick="new Calendar(2011, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" /></td> --%>	
			<input name="oper_endtime" id="oper_endtime" size="12" value="${af.map.oper_endtime}" title="点击选择日期" readonly="readonly" class="Wdate" type="text" onclick="WdatePicker({readOnly:true})" />
			<td>
				<strong class="fb">类型：</strong>
				<html-el:select property="link_id">
					<html-el:option value="">请选择</html-el:option>
					<html-el:option value="888881">同步R3客户</html-el:option>
					<html-el:option value="888882">同步产品库</html-el:option>
					<html-el:option value="888883">同步回款</html-el:option>
					<html-el:option value="888884">同步账期</html-el:option>
					<html-el:option value="888885">同步R3订单明细</html-el:option>
					<html-el:option value="888886">同步集采数据</html-el:option>
					<html-el:option value="888887">同步物流信息</html-el:option>
					<html-el:option value="888888">同步客户产品数据</html-el:option>
					<html-el:option value="888889">同步存销比与毛利分析接口数据</html-el:option>
					<html-el:option value="888890">同步业绩划拨</html-el:option>
					<html-el:option value="888891">同步R3客户分类</html-el:option>
					<html-el:option value="888892">发送邮件</html-el:option>
					<html-el:option value="888893">同步同一次顺丰物流运费</html-el:option>
					<html-el:option value="888894">同步订单在顺丰的状态</html-el:option>
					<html-el:option value="888895">同步分公司调拨计划评估</html-el:option>
					<html-el:option value="888897">业务汇总月统计数据</html-el:option>
					<html-el:option value="999998">月度库存查询</html-el:option>
					<html-el:option value="999999">客户库存汇总</html-el:option>
					<html-el:option value="999997">客户分仓库存汇总</html-el:option>
					<html-el:option value="888898">客户分类统计表-年度</html-el:option>
					<html-el:option value="888899">客户分类统计表-月度</html-el:option>
					<html-el:option value="888900">结转客户库存</html-el:option>
					<html-el:option value="888901">初始化统计基础数据-零售</html-el:option>
					<html-el:option value="888902">初始化统计分析区域数据</html-el:option>
					<html-el:option value="888903">初始化统计分析分公司数据</html-el:option>
					<html-el:option value="888904">初始化统计时间数据</html-el:option>
					<html-el:option value="888905">初始化统计门店数据</html-el:option>
					<html-el:option value="888906">初始化统计商品数据</html-el:option>
				</html-el:select>
			</td>
			<td><input class="but1" type="submit" name="Submit" value="搜索" /></td>
		</tr>
	</table>
</html-el:form>
<div class="rtabcont1">
  <%@ include file="/commons/pages/messages.jsp" %>
   <div class="pageContent">
		<form id="listForm" name="listForm" method="post" action="KonkaSxOperLog.do?method=listForTb">
		<input type="hidden" name="method" id="method" value="listForTb" />
		<input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
		 <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
			<tr class="tabtt1">
				<td align="center" width="35" nowrap="nowrap">序号</td>
				<td align="center" nowrap="nowrap" align="center">操作人</td>
				<td align="center" nowrap="nowrap" align="center">操作时间</td>
				<td  align="center" nowrap="nowrap" align="center">操作IP</td>
				<td  align="center" nowrap="nowrap" align="center">操作表</td>
				<td  align="center" nowrap="nowrap" align="center">类型</td>
				<td  align="center" nowrap="nowrap" align="center">明细</td>
				<td  align="center" nowrap="nowrap" align="center">备注</td>
			</tr>
			<tbody>
			<c:forEach var="cur" items="${entityList}" varStatus="vs">
			<tr>
				<td align="center" nowrap="nowrap">${vs.count}</td>
				<td align="left">${cur.oper_uname}</td>
				<td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.oper_time}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td align="left" nowrap="nowrap">${cur.oper_ip}</td>
				<td align="left" nowrap="nowrap">${cur.link_tab}</td>
				<td align="center" nowrap="nowrap">
					<c:if test="${cur.link_id eq '888881'}">同步R3客户</c:if>
					<c:if test="${cur.link_id eq '888882'}">同步产品库</c:if>
					<c:if test="${cur.link_id eq '888883'}">同步回款</c:if>
					<c:if test="${cur.link_id eq '888884'}">同步账期</c:if>
					<c:if test="${cur.link_id eq '888885'}">同步R3订单明细</c:if>
					<c:if test="${cur.link_id eq '888886'}">同步集采数据</c:if>
					<c:if test="${cur.link_id eq '888887'}">同步物流信息</c:if>
					<c:if test="${cur.link_id eq '888888'}">同步客户产品数据</c:if>
					<c:if test="${cur.link_id eq '888889'}">同步存销比与毛利分析接口数据</c:if>
					<c:if test="${cur.link_id eq '888890'}">同步业绩划拨</c:if>
					<c:if test="${cur.link_id eq '888891'}">同步R3客户分类</c:if>
					<c:if test="${cur.link_id eq '888892'}">发送邮件</c:if>
					<c:if test="${cur.link_id eq '888893'}">同步一次顺丰物流运费</c:if>
					<c:if test="${cur.link_id eq '888894'}">同步订单在顺丰的状态</c:if>
					<c:if test="${cur.link_id eq '888895'}">同步分公司调拨计划评估</c:if>
					<c:if test="${cur.link_id eq '888897'}">业务汇总月统计数据</c:if>
					<c:if test="${cur.link_id eq '999998'}">月度库存查询</c:if>
					<c:if test="${cur.link_id eq '999999'}">客户库存汇总</c:if>
					<c:if test="${cur.link_id eq '999997'}">客户分仓库存汇总</c:if>
					<c:if test="${cur.link_id eq '888898'}">客户分类统计表-年度</c:if>
					<c:if test="${cur.link_id eq '888899'}">客户分类统计表-月度</c:if>
					<c:if test="${cur.link_id eq '888900'}">结转客户库存</c:if>
					<c:if test="${cur.link_id eq '888901'}">初始化统计基础数据-零售</c:if>
					<c:if test="${cur.link_id eq '888902'}">初始化统计分析区域数据</c:if>
					<c:if test="${cur.link_id eq '888903'}">初始化统计分析分公司数据</c:if>
					<c:if test="${cur.link_id eq '888904'}">初始化统计时间数据</c:if>
					<c:if test="${cur.link_id eq '888905'}">初始化统计门店数据</c:if>
					<c:if test="${cur.link_id eq '888906'}">初始化统计商品数据</c:if>
				</td>
				<td align="left">${cur.ppdm_name}</td>
				<td align="left">${cur.oper_type}</td>
			</tr>
			<c:if test="${vs.last eq true}">
				<c:set var="i" value="${vs.count}" />
			</c:if>
			</c:forEach>
			<c:forEach begin="${i}" end="${af.map.pager.pageSize - 1}">
			<tr align="center">
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
	</form>
	<form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaSxOperLog.do">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td height="40">
					<div style="text-align:right; padding-right:5px;">
					<script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
					<script type="text/javascript">
						var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
						pager.addHiddenInputs("method", "listForTb");
						pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
						pager.addHiddenInputs("oper_starttime", "${af.map.oper_starttime}");
						pager.addHiddenInputs("oper_endtime", "${af.map.oper_endtime}");
						pager.addHiddenInputs("link_id", "${af.map.link_id}");
						pager.addHiddenInputs("user_name", "${af.map.user_name}");
						document.write(pager.toString());
					</script>
					</div>
				</td>
			</tr>
		</table>
	</form>
</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar/WdatePicker.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>