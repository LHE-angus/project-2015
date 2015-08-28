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
    <html-el:form action="/spgl/EcOrderExchange">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
           <td width="70%" nowrap="nowrap">&nbsp;&nbsp;<strong class="fb">交易流水号：</strong>&nbsp;
           <html-el:text property="trade_index_like" styleId="trade_index_like" maxlength="30" style="width:100px;" />
            &nbsp;<strong class="fb">退换货时间：</strong>
        		<html-el:text property="oper_date_start" styleId="oper_date_start"  size="9" maxlength="10" readonly="true" onclick="new Calendar(2012, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
				至
				<html-el:text property="oper_date_end" styleId="oper_date_end"  size="9" maxlength="10" readonly="true" onclick="new Calendar(2012, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
       &nbsp;           
           <strong class="fb">	退换货  ：</strong>&nbsp;
           <html-el:select property="is_exchange" onchange="this.form.submit();">
				<html-el:option value="">请选择</html-el:option>
				<html-el:option value="1">退货</html-el:option>
				<html-el:option value="2">换货</html-el:option> 
			</html-el:select>
			 &nbsp;<strong class="fb">退机类型  ：</strong>&nbsp;
			 <html-el:select property="exchange_info" onchange="this.form.submit();">
				           	<html-el:option value="">请选择</html-el:option>
				           	<html-el:option value="1">质量机退货</html-el:option>
				           	<html-el:option value="2">拒收</html-el:option>
				           	<html-el:option value="3">好机退货</html-el:option>
			           	</html-el:select>		                      
           </td>
           <td >
              <input class="but1" type="submit" id="t1" name="Submit" value="搜索" />
        	  <input type="button" value="Excel" id="export_excel" class="but_excel" style="margin-left: 10px;" />
        </td></tr>
      </table>
    </html-el:form>
  </div>
  
<%@ include file="/commons/pages/messages.jsp"%>
  <div class="rtabcont1" style="overflow-x:auto;">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tbody>
          <tr class="tabtt1">
	           <td width="5%" nowrap="nowrap" align="center" rowspan="2">序号</td> 
	           <td width="5%" nowrap="nowrap" align="center" rowspan="2">发货分公司</td> 
	           <td width="5%" nowrap="nowrap" align="center" rowspan="2">交易流水号</td> 
	           <td width="5%" nowrap="nowrap" align="center" rowspan="2">订单来源</td> 
	           <td width="5%" nowrap="nowrap" align="center" rowspan="2">退换货时间</td>  
	           <td width="5%" nowrap="nowrap" align="center" rowspan="2">型号</td> 
	           <td width="5%" nowrap="nowrap" align="center" rowspan="2">数量</td>
	           <td width="5%" nowrap="nowrap" align="center" rowspan="2">金额</td>
	           <td width="5%" nowrap="nowrap" align="center" rowspan="2">收货人</td>
	           <td width="25%" nowrap="nowrap" align="center" colspan="4">客服跟踪</td>
	           <td width="20%" nowrap="nowrap" align="center" colspan="4">财务跟踪</td> 
	           <td width="160" nowrap="nowrap" align="center" rowspan="2">备注</td>
	           <td width="5%" nowrap="nowrap" align="center" rowspan="2">操作</td>
          </tr>
          <tr class="tabtt1">
	          <!--td width="5%" nowrap="nowrap" align="center">机器状态</td-->
	          <td width="5%" nowrap="nowrap" align="center">退机类型</td>
	          <td width="5%" nowrap="nowrap" align="center">是否换货</td>
	          <td width="5%" nowrap="nowrap" align="center">报险方式</td>
	          <td width="5%" nowrap="nowrap" align="center">报险金额</td>
	          <td width="5%" nowrap="nowrap" align="center">机器入库</td>
	          <td width="5%" nowrap="nowrap" align="center">财务核销</td>
	          <td width="5%" nowrap="nowrap" align="center">理赔是否到账</td>
	          <td width="5%" nowrap="nowrap" align="center">退款金额</td>
          </tr>          
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr>
	         <td height="28"  align="center">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
	         <td align="left"><c:out value="${cur.oldPshowOrder.map.dept_name}" /></td>
	         <td align="left"><c:out value="${cur.oldPshowOrder.trade_index}" /></td>
	         <td align="left">
	          <c:if test="${cur.oldPshowOrder.order_from eq 1 }">工卡</c:if>
	          <c:if test="${cur.oldPshowOrder.order_from eq 2}">触网(${cur.oldPshowOrder.ecUser.map.dept_name })</c:if>	         
	         </td>
	         <td align="left"><fmt:formatDate value="${cur.oper_date}" pattern="yy-MM-dd HH:mm:ss" /></td>
	         <td align="left"><c:out value="${cur.pshowOrdeDetails.map.pd_sn}" /></td>
	         <td align="center"><c:out value="${cur.pshowOrdeDetails.num}" /></td>
	         <td align="left"><c:out value="${cur.pshowOrdeDetails.total_price}" /></td>
	         <td align="left"><c:out value="${cur.oldPshowOrder.buyer_name}" /></td>
	         <!-- >td align="left">
	         <c:if test="${cur.pd_step eq 1 }">客户家里</c:if>  <c:if test="${cur.pd_step eq 2 }">拉回在途</c:if><c:if test="${cur.pd_step eq 3 }">分公司</c:if>
	         </td --> 
	         <td align="left">
	         <c:if test="${cur.exchange_info eq 1 }">质量机</c:if>  <c:if test="${cur.exchange_info eq 2 }">碎屏</c:if><c:if test="${cur.exchange_info eq 3 }">好机</c:if><c:if test="${cur.exchange_info eq 4 }">其它</c:if>
	         </td>
	         <td align="left"><c:if test="${cur.is_exchange eq 1 }">退货</c:if>  <c:if test="${cur.is_exchange eq 2 }">换货</c:if></td>
	       	 <td align="left"><c:if test="${cur.insurance_way eq 1 }">顺丰理赔</c:if>  <c:if test="${cur.insurance_way eq 2 }">保险+顺丰理赔</c:if><c:if test="${cur.insurance_way eq 3 }">保险理赔</c:if></td>
	       	 <td align="left"><c:out value="${cur.insurance_price}" /></td>
	       	 <td align="left"><c:if test="${cur.pd_store eq 1 }">未入库</c:if>  <c:if test="${cur.pd_store eq 2 }">入库</c:if></td>
	       	 <td align="left"><c:if test="${cur.exchange_state eq 1 }">未退换货</c:if>  <c:if test="${cur.exchange_state eq 2 }">已退换货</c:if></td>
	       	 <td align="left"><c:if test="${cur.insurance_state eq 1 }">未到账</c:if>  <c:if test="${cur.insurance_state eq 2 }">已到账</c:if></td>
	       	 <td align="left"><c:out value="${cur.exchange_price}" /></td>
	       	  <td align="left"><c:out value="${cur.remark}" /></td>
	       	  <td align="left"><span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'EcOrderExchange.do','view' ,'id=${cur.id}&' + $('#bottomPageForm').serialize())">查看</span> <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'EcOrderExchange.do','edit' ,'id=${cur.id}&' + $('#bottomPageForm').serialize())">编辑</span></td>
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
              <td>&nbsp;</td> 
              <td>&nbsp;</td> 
              <td colspan="4">&nbsp;</td> 
              <td colspan="4">&nbsp;</td>
              <td>&nbsp;</td>   
              <td>&nbsp;</td> 
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="EcOrderExchange.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
	            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
	            pager.addHiddenInputs("method", "list");
	            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
	            pager.addHiddenInputs("oper_date_start", "${af.map.oper_date_start}");
	            pager.addHiddenInputs("oper_date_end", "${af.map.oper_date_end}");
	            pager.addHiddenInputs("is_exchange", "${af.map.is_exchange}");
	            pager.addHiddenInputs("exchange_info", "${af.map.exchange_info}");
	            pager.addHiddenInputs("trade_index_like", "${af.map.trade_index_like}"); 
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
 
	// 导出excel
$("#export_excel").click(function(){
this.value="正在提交...";
this.disabled=true;
this.form.action="${ctx}/manager/spgl/EcOrderExchange.do?method=sheet";
this.form.submit();
this.form.action="${ctx}/manager/spgl/EcOrderExchange.do";
this.value="Excel";
this.disabled=false;
});
	
});
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
