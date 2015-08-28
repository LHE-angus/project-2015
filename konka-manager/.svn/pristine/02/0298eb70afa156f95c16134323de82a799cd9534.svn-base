<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer/jquery-ui.custom.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/tip/jquery.qtip.css" rel="stylesheet" type="text/css" />
<style type="text/css">
select{font-family:Microsoft YAHEI;font-size:12px;}
input{font-family:Microsoft YAHEI;font-size:12px;}
</style>
<title>订单记录</title>
<script type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script>
</head>
<body style="font-family:Microsoft Yahei;">
	<div class="oartop">
	    <table width="400" border="0" cellpadding="0" cellspacing="0">
	      <tr>
	        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
	        <td>当前位置：${naviString}</td>
	      </tr>
	    </table>
  	</div>
	<html-el:form action="/manager/JxcKonkaOrderRegister.do">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="cust_id" styleId="cust_id" />
	  <div class="rtabcont1">
	    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableTop">
	      <tr>
	        <td height="36" align="left" style="padding-left:5px;">
	          &nbsp;<strong class="fb">订单日期：</strong>
	          <input type="text" name="add_date_gt" id="add_date_gt" class="webinput" value="${af.map.add_date_gt}" readonly="readonly" onclick="new Calendar(1990, 2020, 0).show(this);" style="cursor:pointer;text-align:center;width:80px;" />
	          &nbsp;至
	          <input type="text" name="add_date_lt" id="add_date_lt" class="webinput" value="${af.map.add_date_lt}" readonly="readonly" onclick="new Calendar(1990, 2020, 0).show(this);" style="cursor:pointer;text-align:center;width:80px;"/>
	          &nbsp;<strong class="fb">交易流水号：</strong>
	          <html-el:text property="trade_index_like" styleClass="webinput" styleId="trade_index_like" maxlength="40"/>
	          &nbsp;
	          <input name="button" type="submit" class="bgSearch" id="button" value="搜 索" />
	          <!-- &nbsp;<span id="searchTip" class="jxcTip">默认不显示数据，点击搜索显示数据</span> -->
	        </td>
	      </tr>
	    </table>
	  </div>
	</html-el:form>
	<div class="rtabcont1">
	  <%@ include file="/commons/pages/messages.jsp" %>
	  <input name="input" type="button" id="gotoAdd" class="bgButtonAdd" value="新 增" onclick="location.href='${ctx}/manager/admin/JxcKonkaOrderRegister.do?method=add&mod_id=${af.map.mod_id}&cust_id=${af.map.cust_id}'" />
	</div>
	<div class="rtabcont1">
	  <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
	    <tr>
	      <th width="5%">行号</th>
	      <th width="10%">下单日期</th>
	      <th>交易流水号</th>
	      <th width="10%">订单数量</th>
	      <th width="13%">订单金额(元)</th>
	      <th width="13%">折扣金额(元)</th>
	      <th width="8%">支付方式</th>
	      <th width="8%">配送方式</th>
	      <th width="8%">订单审核状态</th>
	      <!--<th width="20%">审核角色</th>-->
	      <!-- <th width="20%">说明</th> -->
	      <th width="10%">操作</th>
	    </tr>
	    <c:forEach items="${konkaOrderInfoList}" var="cur" varStatus="vs">
	      <tr>
	        <td align="center" bgcolor="#fff2dc">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
	        <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.order_date}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
	        <td align="center">${cur.trade_index}</td>
	        <td align="center"><fmt:formatNumber value="${cur.order_num}" pattern="0" /></td>
	        <td align="center"><fmt:formatNumber value="${cur.money}" pattern="0.00" /></td>
	        <td align="center"><fmt:formatNumber value="${cur.good_discount_price}" pattern="0.00" /></td>
	        <td align="center">
	        	<c:if test="${cur.pay_type eq 4}">现款</c:if>
	        	<c:if test="${cur.pay_type eq 5}">担保</c:if>
	        </td>
	        <td align="center">
	        	<c:if test="${cur.send_type eq 1}">自提</c:if>
	        	<c:if test="${cur.send_type eq 2}">配送</c:if>
	        </td>
	        <td align="center">
	        	<c:if test="${cur.audit_state eq 0}"><span>未审核</span></c:if>
	          	<c:if test="${cur.audit_state eq 1}"><span style="color:#00f;">审核中 </span></c:if>
	          	<c:if test="${cur.audit_state eq 2}"><span style="color:#f00;">审核未通过</span></c:if>
	          	<c:if test="${cur.audit_state eq 3}"><span style="color:#060;">审核通过 </span></c:if>
	        </td>
	        <!--<td align="center">
	        	<c:if test="${(cur.audit_state eq 0) or (cur.audit_state eq 1)}"> 当前审核角色： </c:if>
	          	<c:if test="${(cur.audit_state eq 2) or (cur.audit_state eq 3)}"> 最后审核角色： </c:if>
	          	${fn:escapeXml(cur.map.audit_role_name)} 
	        </td>-->
	        <!-- <td align="center"><span title="${fn:escapeXml(cur.remark)}">${fn:escapeXml(fnx:abbreviate(cur.remark, 2 * 10, '...'))}</span></td> -->
	        <td align="center" nowrap="nowrap">
	        	<span style="cursor:pointer;"  class="fblue"  onclick="doNeedMethod(null, 'JxcKonkaOrderRegister.do', 'view','&mod_id=${af.map.mod_id}&order_id=${cur.id}&'+$('#bottomPageForm').serialize())">查看明细</span>
	         	<c:if test="${cur.is_submit eq 1}">
	        		<span style="color:#ccc;"title="订单已经提交，不能修改！">修改</span>
	        	</c:if>
	        	<c:if test="${cur.is_submit eq 0}">
	        		<span style="cursor:pointer;"  class="fblue"  onclick="doNeedMethod(null, 'JxcKonkaOrderRegister.do', 'edit','&mod_id=${af.map.mod_id}&order_id=${cur.id}&cust_id=${cur.cust_id}&'+$('#bottomPageForm').serialize())">修改</span>
	        	</c:if>
	        </td>
	      </tr>
	      <c:if test="${vs.last}">
              <c:forEach begin="1" end="${9 - vs.index}">
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
                </tr>
              </c:forEach>
           	</c:if>
	    </c:forEach>
	    <!--<tr>
	      <td align="center" bgcolor="#fff2dc"><font class="redbig" style="color: red;">合计</font></td>
	      <td colspan="3" bgcolor="#ededed"><font class="red" style="color: red;"></font></td>
	      <td bgcolor="#ededed"><font  style="color: red;">总计：
	        <fmt:formatNumber value="${countMoney}" pattern="0.00" />
	        </font></td>
	      <td bgcolor="#ededed"><font  style="color: red;">总计：
	        <fmt:formatNumber value="${countMoney}" pattern="0.00" />
	        </font></td>
	      <td bgcolor="#ededed"></td>
	      <td bgcolor="#ededed"></td>
	      <td bgcolor="#ededed"></td>
	    </tr>
	    -->
	    <c:if test="${empty konkaOrderInfoList}">
        	<c:forEach begin="0" end="9">
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
              </tr>
        	</c:forEach>
        </c:if>
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
		            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
		            pager.addHiddenInputs("cust_id", "${af.map.cust_id}");
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
<script type="text/javascript" src="${ctx}/customer/script/rowEffect.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script> 
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
<jsp:include page="/__analytics.jsp" />
</body>
</html>