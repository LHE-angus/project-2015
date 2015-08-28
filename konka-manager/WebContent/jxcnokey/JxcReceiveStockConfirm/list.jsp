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
  <div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;" /> 当前位置：进货管理 &gt; 收货确认</div>
  <div class="rtabcont1">
    <html-el:form action="/JxcReceiveStockConfirm">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" />
      <html-el:hidden property="keySeq" styleId="keySeq" value="${af.map.keySeq}"/>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableTop">
        <tr>
          <td>
         <strong class="fb">发货时间：</strong>
         <input type="text" name="add_date_gt" id="add_date_gt" class="webinput" value="${af.map.add_date_gt}" readonly="readonly" onclick="new Calendar(1990, 2020, 0).show(this);" style="cursor:pointer;text-align:center;width:80px;" />
          &nbsp;至
          <input type="text" name="add_date_lt" id="add_date_lt" class="webinput" value="${af.map.add_date_lt}" readonly="readonly" onclick="new Calendar(1990, 2020, 0).show(this);" style="cursor:pointer;text-align:center;width:80px;"/>
          	<!--<select name="supplier_id"  class="bdfont">
	            <option value="">选择供应商</option>
	            <c:forEach items="${listJxcSupplier}" var="cur">
	              <c:if test="${cur.id == supplier_id}">
	                <option selected="selected" value="${cur.id }">${cur.name }</option>
	              </c:if>
	              <c:if test="${cur.id != supplier_id}">
	                <option value="${cur.id }">${cur.name }</option>
	              </c:if>
	            </c:forEach>
	          </select>
          	-->
          	&nbsp; <strong class="fb">数据来源：</strong>
            <html-el:select property="data_src" styleId="data_src" style="width:80px;">
              <html-el:option value="">全部</html-el:option>
              <html-el:option value="1">系统录入</html-el:option>
              <html-el:option value="2">R3销售导入</html-el:option>
            </html-el:select>&nbsp;
          	&nbsp; <strong class="fb">是否确认：</strong>
            <html-el:select property="is_confirm" styleId="is_confirm" style="width:80px;">
              <html-el:option value="">全部</html-el:option>
              <html-el:option value="0">未确认</html-el:option>
              <html-el:option value="1">已确认</html-el:option>
            </html-el:select>&nbsp;
          	<html-el:submit value="搜 索" styleClass="bgSearch"/></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <form id="listForm" name="listForm" method="post" action="JxcReceiveStockConfirm.do?method=delete">
    <div class="rtabcont1">
      <%@ include file="/commons/pages/messages.jsp" %>
    </div>
    <div class="rtabcont1">
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
        <tr> 
          <th width="5%" nowrap="nowrap">序号</th>
          <th width="15%"nowrap="nowrap">发货单号</th>
          <th width="8%" nowrap="nowrap">发货数量</th>
          <th width="8%" nowrap="nowrap">应收金额</th>
          <th width="8%" nowrap="nowrap">实收金额</th>
          <th width="12%" nowrap="nowrap">发货日期</th>
          <th width="10%" nowrap="nowrap">发货人</th>
          <th nowrap="nowrap">供应商</th>
          <th width="8%" nowrap="nowrap">数据来源</th>
          <th width="8%" nowrap="nowrap">是否确认</th>
          <th width="8%" nowrap="nowrap">操作</th>
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
                  <c:when test="${cur.data_src eq 1}">系统录入</c:when>
                  <c:when test="${cur.data_src eq 2}">R3销售导入</c:when>
                </c:choose></td>
            <td align="center" nowrap="nowrap"><c:if test="${cur.data_src eq 1}">
	           		 <c:choose>
	                  <c:when test="${cur.is_confirm eq 0}"> <span style="color:#F00;">未确认</span></c:when>
	                  <c:when test="${cur.is_confirm eq 1}"> <span style="color:#060;">已确认</span></c:when>
	                </c:choose>
                </c:if> 
	            <c:if test="${cur.data_src eq 2}">
					<span style="color:#00f;">导入数据，自动确认</span>
                </c:if></td>
            <td align="center" nowrap="nowrap">
            	<span style="cursor:pointer;"  class="fblue"  onclick="doNeedMethod(null, 'JxcReceiveStockConfirm.do', 'view','id=${cur.id}&keySeq=${af.map.keySeq}&'+$('#bottomPageForm').serialize())">查看明细</span>
            	<c:if test="${cur.data_src eq 1}">
	            	| 
	            	<c:if test="${cur.is_confirm eq 0}">
	            	<span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'JxcReceiveStockConfirm.do', 'confirm','id=${cur.id}&keySeq=${af.map.keySeq}&'+$('#bottomPageForm').serialize())">确认</span>
	            	</c:if> 
	            	<c:if test="${cur.is_confirm eq 1}">
	            	<span style="color:#ccc;" title="已确认，不能再次确认">确认</span>
	            	</c:if> 
            	</c:if> 
            </td>
          </tr>
        </c:forEach>
      </table>
    </div>
  </form>
  <div class="rtabcont3">
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="JxcReceiveStockConfirm.do">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="center"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
            <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
            pager.addHiddenInputs("keySeq", "${af.map.keySeq}");
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