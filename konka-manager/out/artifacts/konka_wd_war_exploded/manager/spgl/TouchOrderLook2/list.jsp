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
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td nowrap="nowrap">当前位置：${naviString}</td>
        <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align:middle;" /> <span id="span_help" style="cursor:pointer;">帮助</span></td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/spgl/TouchOrderLook2">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
           <td width="100%" nowrap="nowrap">
           &nbsp;&nbsp;<strong class="fb">下单分公司：</strong>
            <html-el:select property="dept_id" styleId="dept_id" style="width:120px;">
              <html-el:option value="">请选择...</html-el:option>
              <c:forEach var="cur" items="${sybDeptInfoList}">
                <html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
              </c:forEach>
            </html-el:select>&nbsp;&nbsp;
           <strong class="fb">下单客户R3编码：</strong>&nbsp;
            <html-el:text property="r3_code" styleId="r3_code" style="width:100px;"  maxlength="30"/> &nbsp; &nbsp;
            </td>
       </tr>
       <tr> 
           <td width="100%" nowrap="nowrap">&nbsp;
           <strong class="fb">交易流水号：</strong>&nbsp;
            <html-el:text property="trade_index_like" styleId="trade_index_like" style="width:100px;" maxlength="30"/>&nbsp;
            &nbsp;<strong class="fb">下单日期：</strong>
        		<html-el:text property="add_time_start" styleId="add_time_start"  size="9" maxlength="10" readonly="true" onclick="new Calendar(2010, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
				至
				<html-el:text property="add_time_end" styleId="add_time_end"  size="9" maxlength="10" readonly="true" onclick="new Calendar(2010, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
            &nbsp;<strong class="fb">订单状态：</strong>
            <html-el:select property="state" styleId="state">
           	 <html-el:option value="">请选择</html-el:option>
           	  <html-el:option value="-30">已退货</html-el:option>
           	  <html-el:option value="-20">审核未通过</html-el:option>
           	  <html-el:option value="-10">已关闭</html-el:option>
           	  <html-el:option value="0">已预订</html-el:option>
           	  <html-el:option value="5">待确认</html-el:option>
           	  <html-el:option value="10">已确认</html-el:option>
              <html-el:option value="20">审核通过</html-el:option>
              <html-el:option value="30">下发处理</html-el:option>
              <html-el:option value="40">商家发货</html-el:option>
              <html-el:option value="50">客户已换货</html-el:option>
              <html-el:option value="60">确认收货</html-el:option>    
            </html-el:select>
            &nbsp;<strong class="fb">支付方式：</strong>
            <html-el:select property="pay_way" styleId="pay_way" style="width:120px;">
              <html-el:option value="">请选择...</html-el:option>
              <html-el:option value="0">货到付款</html-el:option>
           	  <html-el:option value="1">银行汇款</html-el:option>
           	  <html-el:option value="2">支付宝</html-el:option>
           	  <html-el:option value="3">银联</html-el:option>
           	  <html-el:option value="4">财付通</html-el:option>
           	  <html-el:option value="5">民生银行</html-el:option>
           	  <html-el:option value="8">顺丰代收货款</html-el:option>
           	  <html-el:option value="9">线下处理</html-el:option>
            </html-el:select>
            </td>
        </tr>
        <tr><td width="100%" nowrap="nowrap">&nbsp;
            <strong class="fb">购买人姓名：</strong>&nbsp;
            <html-el:text property="buyer_name_like" styleId="buyer_name_like" style="width:100px;" maxlength="30"/>
            &nbsp; <strong class="fb">购买人手机：</strong>
            <html-el:text property="buyer_mp_like" styleId="buyer_mp_like" style="width:100px;" maxlength="30"/>
             &nbsp;&nbsp;
             <strong class="fb">二次配送：</strong>
            <html-el:select property="is_ps" styleId="is_ps" >
              <html-el:option value="">请选择...</html-el:option>
              <html-el:option value="0">否</html-el:option>
           	  <html-el:option value="1">是</html-el:option>
            </html-el:select>
             &nbsp;&nbsp;<strong class="fb">订单来源：</strong>
            <html-el:select property="touch_type" styleId="touch_type" style="width:120px;">
              <html-el:option value="">请选择...</html-el:option>
              <html-el:option value="1">触网1</html-el:option>
           	  <html-el:option value="2">触网2</html-el:option>
            </html-el:select>
            
            
            <input class="but1" type="submit" id="t1" name="Submit" value="搜索" style="margin-left: 10px;" />
        	<input type="button" value="Excel" id="export_excel" class="but_excel" />
        	
        </td></tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont2" id="r3moneyTb">
   <%@ include file="/commons/pages/messages.jsp"%> 
  </div>
  <div class="rtabcont1" style="overflow-x:auto;">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tbody>
          <tr class="tabtt1">
            <td width="5%" nowrap="nowrap" align="center">序号</td>
            <td nowrap="nowrap" align="center">交易流水号</td>
            <td width="10%" nowrap="nowrap" >&nbsp;&nbsp;订单状态&nbsp;&nbsp;</td>
            <td width="7%" nowrap="nowrap" > 触网类型</td> 
            <td width="7%" nowrap="nowrap" >下单分公司</td> 
            <td width="10%" nowrap="nowrap" align="center">下单人姓名</td>
            <td width="10%" nowrap="nowrap" align="center">客户R3编码</td>
            <td width="10%" nowrap="nowrap" align="center">购买人姓名</td>
            <td width="160" nowrap="nowrap" align="center">购买人地区</td>
            <td width="10%" nowrap="nowrap" align="center">购买人手机</td>
            <td width="10%" nowrap="nowrap" align="center">支付方式</td>
            <td width="10%" nowrap="nowrap" align="center"> &nbsp;下单时间&nbsp; </td>
            <td width="7%" nowrap="nowrap" align="center">二次配送</td>
            <td width="15%" nowrap="nowrap" align="center" >操作</td>
          </tr>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr>
	         <td height="28"  align="center">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
	         <td align="right"><a style="color: blue;" href="?method=view&id=<c:out value="${cur.id}" />"><c:out value="${cur.trade_index}" /></a></td>
	         <td align="center">
		         <c:if test="${cur.state eq -30 }">已退货</c:if>
		         <c:if test="${cur.state eq -20 }">审核未通过</c:if>
		         <c:if test="${cur.state eq -10 }">已关闭</c:if>
		         <c:if test="${cur.state eq 0 }">已预订</c:if>
		         <c:if test="${cur.state eq 5 }">待确认</c:if>
		         <c:if test="${cur.state eq 10 }">已确认</c:if>
		         <c:if test="${cur.state eq 20 }">审核通过</c:if>
		         <c:if test="${cur.state eq 30 }">下发处理</c:if>
		         <c:if test="${cur.state eq 40 }">商家发货</c:if>
		         <c:if test="${cur.state eq 50 }">客户已换货</c:if>
		         <c:if test="${cur.state eq 60 }">确认收货</c:if>
		         <c:if test="${cur.pay_way eq 9}"><br/>（线下处理）</c:if>
	         </td>
	         <td align="left" ><c:if test="${cur.pay_way eq 9}">触网2</c:if>
	         					<c:if test="${cur.pay_way ne 9}">触网1</c:if>
	         </td>
	         <td align="left" valign="middle">
	         <c:if test="${not empty cur.map.old_dept_name}"><c:out value="${cur.ecUser.map.dept_name}" /></c:if>
	         <c:if test="${empty cur.map.old_dept_name}"><c:out value="${cur.map.dept_name_new}" /></c:if>
	         </td> 
              <td align="left"><c:out value="${cur.order_user_name}" /></td>
              <td align="left"><c:out value="${cur.ecUser.map.r3_code}" /></td>
              <td align="left"><c:out value="${cur.buyer_name}" /></td>
              <td align="left"><c:out value="${cur.map.full_name}" /> &nbsp;<c:out value="${cur.buyer_addr}" /></td>
              <td align="right" nowrap="nowrap"><c:out value="${cur.buyer_mp}" /></td>
              <td align="center">
	              <c:if test="${cur.pay_way eq 0}">货到付款</c:if>
	              <c:if test="${cur.pay_way eq 1}">银行汇款</c:if>
	              <c:if test="${cur.pay_way eq 2}">支付宝</c:if>
	              <c:if test="${cur.pay_way eq 3}">银联</c:if>
	              <c:if test="${cur.pay_way eq 4}">财付通</c:if>
	              <c:if test="${cur.pay_way eq 5}">民生银行</c:if>
	               <c:if test="${cur.pay_way eq 8}">顺丰代收货款</c:if>
	              <c:if test="${cur.pay_way eq 9}">线下处理</c:if>
              </td>
              <td align="left" ><fmt:formatDate value="${cur.add_date}" pattern="yy-MM-dd HH:mm:ss" /></td>
              <td align="left" nowrap="nowrap"> <c:if test="${cur.is_ps eq 0}">否</c:if> <c:if test="${cur.is_ps eq 1}">是</c:if> </td>
              <td align="center" nowrap="nowrap">
              	<span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'TouchOrderLook2.do','view' ,'id=${cur.id}&' + $('#bottomPageForm').serialize())">查看</span>
              </td>
            </tr>
            <c:if test="${vs.last eq true}"><c:set var="i" value="${vs.count}" /></c:if>
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
    </div>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="TouchOrderLook2.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
	            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
	            pager.addHiddenInputs("method", "list");
	            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
	            pager.addHiddenInputs("trade_index_like", "${af.map.trade_index_like}");
	            pager.addHiddenInputs("state", "${af.map.state}");
	            pager.addHiddenInputs("buyer_name_like", "${af.map.buyer_name_like}");
	            pager.addHiddenInputs("buyer_mp_like", "${af.map.buyer_mp_like}");
	            pager.addHiddenInputs("dept_id", "${af.map.dept_id}");
	            pager.addHiddenInputs("trade_no_like", "${af.map.trade_no_like}");
	            pager.addHiddenInputs("order_from", "${af.map.order_from}");
	            pager.addHiddenInputs("pay_way", "${af.map.pay_way}");
	            pager.addHiddenInputs("add_time_start", "${af.map.add_time_start}");
				pager.addHiddenInputs("add_time_end", "${af.map.add_time_end}");
				pager.addHiddenInputs("is_ps", "${af.map.is_ps}");
				pager.addHiddenInputs("r3_code", "${af.map.r3_code}");
				pager.addHiddenInputs("touch_type", "${af.map.touch_type}");
	            document.write(pager.toString());
	      </script></td>
        </tr>
      </table>
    </form>
 
  <div class="clear"></div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#syncBtn").click(function(){
		$("#syncBtn").attr("value", "正在更新...请稍后").attr("disabled", "true");
		$.ajax({
			type: "POST",
			url: "TouchOrderLook2.do",
			data: "method=orderFy",
			dataType: "text",
			error: function(request, settings) {
				
			},
			success: function(oper) {
				alert(oper);
				$("#syncBtn").removeAttr("disabled").attr("value","物流费用更新");
			}
		});

	});

	$("#syncBtn2").click(function(){
		$("#syncBtn2").attr("value", "正在更新...请稍后").attr("disabled", "true");
		$.ajax({
			type: "POST",
			url: "TouchOrderLook2.do",
			data: "method=updateorderState",
			dataType: "text",
			error: function(request, settings) {
				
			},
			success: function(oper) {
				alert(oper);
				$("#syncBtn2").removeAttr("disabled").attr("value","订单状态更新");
			}
		});

	});

	// 导出excel
    $("#export_excel").click(function(){
    	 this.value="正在提交...";
    	 this.disabled=true;
    	 this.form.action="${ctx}/manager/spgl/TouchOrderLook2.do?method=excel";
    	 this.form.submit();
    	 this.form.action="${ctx}/manager/spgl/TouchOrderLook2.do";
    	 this.value="导出Excel";
    	 this.disabled=false;
    });

	
});
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
