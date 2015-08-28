<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>收货确认</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div style="width:100%">
  <div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;" /> 当前位置：${naviString}</div>
  <div class="rtabcont1">
    <html-el:form action="/KonkaJxcReceiveStockConfirm">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableTop">
        <tr>
          <td>
         <strong class="fb">发货时间：</strong>
         <input type="text" name="add_date_gt" id="add_date_gt" class="webinput" value="${af.map.add_date_gt}" readonly="readonly" onclick="new Calendar(1990, 2020, 0).show(this);" style="cursor:pointer;text-align:center;width:80px;" />
          &nbsp;至
          <input type="text" name="add_date_lt" id="add_date_lt" class="webinput" value="${af.map.add_date_lt}" readonly="readonly" onclick="new Calendar(1990, 2020, 0).show(this);" style="cursor:pointer;text-align:center;width:80px;"/>
          	&nbsp; <strong class="fb">是否确认：</strong>
            <html-el:select property="is_confirm" styleId="is_confirm" style="width:80px;">
              <html-el:option value="">全部</html-el:option>
              <html-el:option value="0">未确认</html-el:option>
              <html-el:option value="1">已确认</html-el:option>
            </html-el:select>&nbsp;&nbsp;
          	<html-el:submit value="搜 索" styleClass="bgSearch"/></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <form id="listForm" name="listForm" method="post" action="KonkaJxcReceiveStockConfirm.do?method=delete">
    <div class="rtabcont1">
      <%@ include file="/commons/pages/messages.jsp" %>
    </div>
    <div class="rtabcont1">
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
        <tr> 
          <th width="3%">序号</th>
          <th width="15%"nowrap="nowrap">发货单号</th>
          <th width="8%">发货数量</th>
          <th width="8%">应收金额</th>
          <th width="8%">实收金额</th>
          <th width="12%">发货日期</th>
          <th width="10%">发货人</th>
          <th>发货部门</th>
          <th width="8%">是否确认</th>
          <th width="10%">操作</th>
        </tr>
        <c:forEach items="${konkaJxcFhBillList}" var="cur" varStatus="vs">
          <tr> 
            <td align="center" nowrap="nowrap">${vs.count} </td>
            <td align="center" nowrap="nowrap">${cur.sn} </td>
            <td align="center" nowrap="nowrap">${cur.fh_sum_count}</td>
            <td align="center" nowrap="nowrap"><fmt:formatNumber value="${cur.money_must}" pattern="0.00" /></td>
            <td align="center" nowrap="nowrap"><fmt:formatNumber value="${cur.money_result}" pattern="0.00" /></td>
            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.fh_date}" pattern="yyyy-MM-dd" /></td>
            <td align="center" nowrap="nowrap">${cur.add_user_name} </td>
            <td align="center" nowrap="nowrap">${cur.add_dept_name} </td>
            <td align="center" nowrap="nowrap"><c:choose>
                  <c:when test="${cur.is_confirm eq 0}"> <span style="color:#F00;">未确认</span></c:when>
                  <c:when test="${cur.is_confirm eq 1}"> <span style="color:#060;">已确认</span></c:when>
                </c:choose></td>
            <td align="center" nowrap="nowrap">
            	<span style="cursor:pointer;"  class="fblue"  onclick="doNeedMethod(null, 'KonkaJxcReceiveStockConfirm.do', 'view','id=${cur.id}&keySeq=${af.map.keySeq}&'+$('#bottomPageForm').serialize())">查看</span> | 
            	<c:if test="${cur.is_confirm eq 0}">
            	<span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaJxcReceiveStockConfirm.do', 'confirm','id=${cur.id}&keySeq=${af.map.keySeq}&'+$('#bottomPageForm').serialize())">确认</span>
            	</c:if> 
            	<c:if test="${cur.is_confirm eq 1}">
            	<span style="color:#ccc;">确认</span>
            	</c:if> 
            </td>
          </tr>
        </c:forEach>
      </table>
    </div>
  </form>
  <div class="rtabcont3">
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaJxcReceiveStockConfirm.do">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
            <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
            pager.addHiddenInputs("is_confirm", "${af.map.is_confirm}");
            pager.addHiddenInputs("add_date_gt", "${fn:escapeXml(af.map.add_date_gt)}");
            pager.addHiddenInputs("add_date_lt", "${fn:escapeXml(af.map.add_date_lt)}");
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
	var f=document.forms[0];
	 $(".bgSearch").click(function(){
	    	var s_time = $("#add_date_gt").val();
			var e_time = $("#add_date_lt").val();
			if ("" != s_time && "" != e_time && s_time > e_time) {
				alert("开始日期不能大于结束日期！");
				return false;
			}
			if(!Validator.Validate(f, 1)){
				return false;
			}
	    });
	});
//]]></script>
<jsp:include page="../public_page.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>