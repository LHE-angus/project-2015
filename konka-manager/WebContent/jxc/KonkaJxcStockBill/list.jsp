<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<title>进货记录</title>
<script type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script>
</head>
<body>
<div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;" /> 当前位置：${naviString}</div>
<div class="rtabcont1">
  <html-el:form action="/KonkaJxcStockBill.do">
    <html-el:hidden property="method" value="list" />
    <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableTop">
      <tr>
        <td align="left" nowrap="nowrap"><input type="hidden" name="keySeq" id="keySeq" value="${af.map.keySeq}" />
          
          <!--
          
          <strong class="fb">&nbsp;&nbsp;产品类别：</strong>
            <html-el:select property="pd_type_id">
              <html-el:option value="">请选择</html-el:option>
              <c:forEach items="${basePdClassList}" var="cur" varStatus="vs">
                <html-el:option value="${cur.cls_id}">${fn:escapeXml(cur.tree_name)}</html-el:option>
              </c:forEach>
            </html-el:select>--> 
          <strong class="fb">进货编号：</strong>
          <html-el:text property="sn_like" styleClass="webinput" styleId="sn_like" />
          &nbsp; <strong class="fb">起始日期：</strong>
          <input type="text" id="add_date_gt" name="add_date_gt" class="webinput" value="${add_date_gt}" readonly="readonly" onclick="new Calendar(1990, 2020, 0).show(this);" style="cursor:pointer;text-align:center;width:80px;" />
          &nbsp;至
          <input type="text" id="add_date_lt" name="add_date_lt" class="webinput" value="${add_date_lt}" readonly="readonly" onclick="new Calendar(1990, 2020, 0).show(this);" style="cursor:pointer;text-align:center;width:80px;"/>
          &nbsp;
          <input name="button" type="submit" class="bgSearch" id="button" value="搜 索" /></td>
      </tr>
    </table>
  </html-el:form>
</div>
<div class="rtabcont1">
  <%@ include file="/commons/pages/messages.jsp" %>
  <input name="input" type="button" id="gotoAdd" class="bgButtonAdd" value="新 增" />
</div>
<div class="rtabcont1">
  <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
    <tr>
      <th width="5%">行号</th>
      <th>进货编号</th>
      <th width="12%">进货日期</th>
      <th width="10%">进货总数</th>
      <th width="10%">应付金额</th>
      <th width="10%">实付金额</th>
      <c:if test="${roleId eq 30}">
        <th width="15%">上级部门</th>
      </c:if>
      <th width="10%">添加人</th>
      <th width="8%">操作</th>
    </tr>
    <c:forEach items="${konkaJxcStockBilllist}" var="cur" varStatus="status">
      <tr>
        <td align="center">${status.count}</td>
        <td align="center">${cur.sn}</td>
        <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.jh_date}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
        <td align="center"><fmt:formatNumber value="${cur.jh_sum_count}" pattern="0" /></td>
        <td align="center"><fmt:formatNumber value="${cur.money_must}" pattern="0.00" /></td>
        <td align="center"><fmt:formatNumber value="${cur.money_result}" pattern="0.00" /></td>
        <c:if test="${roleId eq 30}">
          <td align="center">${fn:escapeXml(cur.map.dept_name)}</td>
        </c:if>
        <td align="center">${cur.add_user_name}</td>
        <td align="center"><span style="cursor:pointer;"  class="fblue"  onclick="doNeedMethod(null, 'KonkaJxcStockBill.do', 'view','&id=${cur.id}&'+$('#bottomPageForm').serialize())">查看</span></td>
      </tr>
    </c:forEach>
  </table>
  <div class="rtabcont3">
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaJxcStockBill.do">
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript">
		       var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
		       pager.addHiddenInputs("method", "list");
		       pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
		       pager.addHiddenInputs("pd_type_id", "${af.map.pd_type_id}");
		       pager.addHiddenInputs("sn_like", "${fn:escapeXml(af.map.sn_like)}");
		       pager.addHiddenInputs("add_date_gt", "${add_date_gt}");
		       pager.addHiddenInputs("add_date_lt", "${add_date_lt}");
		       document.write(pager.toString());
		       </script></td>
        </tr>
      </table>
    </form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#gotoAdd").click(function(){
		window.location.href = "${ctx}/jxc/KonkaJxcStockBill.do?method=add&mod_id=${af.map.mod_id}";
	});

	$(".bgSearch").click(function(){
    	var s_time = $("#add_date_gt").val();
		var e_time = $("#add_date_lt").val();
		if ("" != s_time && "" != e_time && s_time > e_time) {
			alert("开始日期不能大于结束日期！");
			return false;
		}
    });
});
	
		
//]]></script>
<jsp:include page="../public_page.jsp" flush="true"/>
<jsp:include page="/__analytics.jsp" />
</body>
</html>