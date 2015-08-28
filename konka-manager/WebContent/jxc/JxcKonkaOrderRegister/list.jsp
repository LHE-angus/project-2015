<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<title>订单记录</title>
<script type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script>
</head>
<body>
<div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;" /> 当前位置：订单管理 &gt; 订单记录</div>
<html-el:form action="/JxcKonkaOrderRegister.do">
  <div class="rtabcont1">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableTop">
      <tr>
        <td height="36" align="left" style="padding-left:5px;"><input type="hidden" name="method" value="list" />
          <input type="hidden" name="keySeq" id="keySeq" value="${af.map.keySeq}" />
          <strong class="fb"> 订单日期：</strong>
          <input type="text" name="add_date_gt" id="add_date_gt" class="webinput" value="${af.map.add_date_gt}" readonly="readonly" onclick="new Calendar(1990, 2020, 0).show(this);" style="cursor:pointer;text-align:center;width:80px;" />
          &nbsp;至
          <input type="text" name="add_date_lt" id="add_date_lt" class="webinput" value="${af.map.add_date_lt}" readonly="readonly" onclick="new Calendar(1990, 2020, 0).show(this);" style="cursor:pointer;text-align:center;width:80px;"/>
          &nbsp;<strong class="fb">交易流水号：</strong>
          <html-el:text property="trade_index_like" styleClass="webinput" styleId="trade_index_like" maxlength="40"/>
          &nbsp;
          <input name="button" type="submit" class="bgSearch" id="button" value="搜 索" />
          
          <!-- &nbsp;<span id="searchTip" class="jxcTip">默认不显示数据，点击搜索显示数据</span> --></td>
      </tr>
    </table>
  </div>
</html-el:form>
<div class="rtabcont1">
  <%@ include file="/commons/pages/messages.jsp" %>
  <input name="input" type="button" id="gotoAdd" class="bgButtonAdd" value="新 增" />
</div>
<div class="rtabcont1">
  <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
    <tr>
      <th width="5%">行号</th>
      <th width="10%">下单日期</th>
      <th width="12%">交易流水号</th>
      <th width="10%">订单数量</th>
      <th width="13%">订单金额(元)</th>
      <th width="13%">折扣金额(元)</th>
      <th width="8%">订单审核状态</th>
      <th width="20%">审核角色</th>
      <!-- <th width="20%">说明</th> -->
      <th width="12%">操作</th>
    </tr>
    <c:forEach items="${konkaOrderInfoList}" var="cur" varStatus="status">
      <tr>
        <td align="center" bgcolor="#fff2dc">${status.count }</td>
        <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.order_date }" pattern="yyyy-MM-dd"></fmt:formatDate></td>
        <td align="center">${cur.trade_index}</td>
        <td align="center"><fmt:formatNumber value="${cur.order_num}" pattern="0" /></td>
        <td align="center"><fmt:formatNumber value="${cur.money}" pattern="0.00" /></td>
        <td align="center"><fmt:formatNumber value="${cur.good_discount_price}" pattern="0.00" /></td>
        <td align="center"><c:if test="${cur.audit_state eq 0}"> <span>未审核</span> </c:if>
          <c:if test="${cur.audit_state eq 1}"> <span style="color:#00f;">审核中 </span> </c:if>
          <c:if test="${cur.audit_state eq 2}"> <span style="color:#f00;">审核未通过</span> </c:if>
          <c:if test="${cur.audit_state eq 3}"> <span style="color:#060;">审核通过 </span> </c:if></td>
        <td align="center"><c:if test="${(cur.audit_state eq 0) or (cur.audit_state eq 1)}"> 当前审核角色： </c:if>
          <c:if test="${(cur.audit_state eq 2) or (cur.audit_state eq 3)}"> 最后审核角色： </c:if>
          ${fn:escapeXml(cur.map.audit_role_name)} </td>
        <!-- <td align="center"><span title="${fn:escapeXml(cur.remark)}">${fn:escapeXml(fnx:abbreviate(cur.remark, 2 * 10, '...'))}</span></td> -->
        <td align="center">
        <span style="cursor:pointer;"  class="fblue"  onclick="doNeedMethod(null, 'JxcKonkaOrderRegister.do', 'view','&keySeq=${af.map.keySeq}&order_id=${cur.id}&'+$('#bottomPageForm').serialize())">查看明细</span>
        <c:if test="${cur.is_submit eq 1}">
        <span style="color:#ccc;"title="订单已经提交，不能修改！">修改</span>
        </c:if>
        <c:if test="${cur.is_submit eq 0}">
        <span style="cursor:pointer;"  class="fblue"  onclick="doNeedMethod(null, 'JxcKonkaOrderRegister.do', 'edit','&keySeq=${af.map.keySeq}&order_id=${cur.id}&'+$('#bottomPageForm').serialize())">修改</span>
        </c:if>
        </td>
      </tr>
    </c:forEach>
  </table>
  <c:if test="${not empty konkaOrderInfoList}">
    <div class="rtabcont3">
      <form id="bottomPageForm" name="bottomPageForm" method="post" action="JxcKonkaOrderRegister.do?method=list">
        <table width="100%" border="0" align="center" cellspacing="0" cellpadding="0">
          <tr>
            <td height="40" align="center"><script type="text/javascript">
	            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
	            pager.addHiddenInputs("method", "list");
	            pager.addHiddenInputs("add_date_gt", "${af.map.add_date_gt}");
	            pager.addHiddenInputs("add_date_lt", "${af.map.add_date_lt}");
	            pager.addHiddenInputs("trade_index_like", "${af.map.trade_index_like}");
	            pager.addHiddenInputs("keySeq", "${af.map.keySeq}");
	            document.write(pager.toString());
	            </script></td>
          </tr>
        </table>
      </form>
    </div>
  </c:if>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script> 
<script type="text/javascript">//<![CDATA[
	$(document).ready(function(){
		$("#gotoAdd").click(function(){
			window.location.href = "${ctx}/jxc/JxcKonkaOrderRegister.do?method=add&keySeq=${af.map.keySeq}";
		})
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