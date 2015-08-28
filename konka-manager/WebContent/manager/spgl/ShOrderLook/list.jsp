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
    <html-el:form action="/spgl/ShOrderLook">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
           <td width="100%" nowrap="nowrap">&nbsp;&nbsp;<strong class="fb">交易流水号：</strong>&nbsp;
            <html-el:text property="trade_index_like" styleId="trade_index_like" style="width:100px;" />&nbsp;
           	<strong class="fb">下单人姓名：</strong>&nbsp;
            <html-el:text property="order_user_name_like" styleId="order_user_name_like" style="width:100px;" maxlength="20"/>&nbsp;
            <strong class="fb">订单状态：</strong>
            <html-el:select property="state" styleId="state" onchange="this.form.submit();">
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
            </html-el:select>&nbsp;
            &nbsp;<strong class="fb">二次配送：</strong>
            <html-el:select property="is_ps" styleId="is_ps" onchange="this.form.submit();">
              <html-el:option value="">请选择...</html-el:option>
              <html-el:option value="0">否</html-el:option>
           	  <html-el:option value="1">是</html-el:option>
            </html-el:select>
            </td>
       </tr>
       <tr> 
           <td width="100%" nowrap="nowrap">&nbsp;
            <strong class="fb">购买人姓名：</strong>&nbsp;
            <html-el:text property="buyer_name_like" styleId="buyer_name_like" style="width:100px;" maxlength="20"/>
            &nbsp; <strong class="fb">购买人手机：</strong>
            <html-el:text property="buyer_mp_like" styleId="buyer_mp_like" style="width:100px;" maxlength="20"/>
            &nbsp;<strong class="fb">下单日期：</strong>
        		<html-el:text property="add_time_start" styleId="add_time_start"  size="9" maxlength="10" readonly="true" onclick="new Calendar(2010, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
				至
				<html-el:text property="add_time_end" styleId="add_time_end"  size="9" maxlength="10" readonly="true" onclick="new Calendar(2010, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
            </td>
        </tr>
        <tr><td width="100%" nowrap="nowrap">
        	&nbsp;&nbsp;<strong class="fb">当前处理的部门：</strong>
            <html-el:select property="dept_id" styleId="dept_id" style="width:120px;" onchange="this.form.submit();">
              <html-el:option value="">请选择...</html-el:option>
              <c:forEach var="cur" items="${sybDeptInfoList}">
                <html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
              </c:forEach>
               <c:if test="${is_admin eq 0}">
               <html-el:option value="2108">电商大宗部</html-el:option>
               <html-el:option value="122">小家电事业部</html-el:option>
               <html-el:option value="2137">配件部</html-el:option>
               <html-el:option value="744">白电事业部</html-el:option>
               <html-el:option value="745">白电空调事业部</html-el:option>
               <html-el:option value="2108">电商大宗部</html-el:option>
               </c:if>
            </html-el:select>&nbsp;
            &nbsp;<strong class="fb">订单类型：</strong>
            <html-el:select property="order_from" styleId="order_from" style="width:120px;" onchange="this.form.submit();">
              <html-el:option value="">请选择...</html-el:option>
              <html-el:option value="1">工卡</html-el:option>
           	  <html-el:option value="2">触网</html-el:option>
           	  <html-el:option value="3">会员</html-el:option>
            </html-el:select> &nbsp;
              <input class="but1" type="submit" id="t1" name="Submit" value="搜索" />
        	  <input type="button" value="Excel" id="export_excel" class="but_excel" style="margin-left: 10px;" />
        </td></tr>
      </table>
    </html-el:form>
  </div>
  
  <div class="rtabcont1" style="overflow-x:auto;">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tbody>
          <tr class="tabtt1">
            <td width="5%" nowrap="nowrap" align="center">序号</td>
            <td nowrap="nowrap" align="center">交易流水号</td>
            <td nowrap="nowrap" width="10%" >&nbsp;&nbsp;订单状态&nbsp;&nbsp;</td>
            <td nowrap="nowrap" width="7%">当前处理部门</td> 
            <td nowrap="nowrap" width="7%">订单类型</td>
            <td width="10%" nowrap="nowrap" align="center">下单人姓名</td>
            <td width="10%" nowrap="nowrap" align="center">购买人姓名</td>
            <td width="160" nowrap="nowrap" align="center">购买人地区</td> 
            <td width="10%" nowrap="nowrap" align="center">购买人手机</td> 
            <td width="10%" nowrap="nowrap" align="center">运单号</td> 
            <td width="10%" nowrap="nowrap" align="center">数量</td> 
            <td width="10%" nowrap="nowrap" align="center"> &nbsp;下单时间&nbsp; </td>
            <td width="10%" nowrap="nowrap" align="center">二次配送</td>
            <td width="10%" nowrap="nowrap" align="center">上门调试</td>
            <td width="10%" nowrap="nowrap" align="center">订单商品</td> 
          </tr>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr>
	         <td height="28"  align="center">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
	         <td align="right"><c:out value="${cur.trade_index}" /></td>
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
	          <td align="left" valign="middle"> <c:out value="${cur.map.dept_name}" /></td> 
	          <td align="left">
	          <c:if test="${cur.order_from eq 1 }">工卡</c:if>
	          <c:if test="${cur.order_from eq 2 and cur.map.is_sf ne true}">触网</c:if>
	          <c:if test="${cur.order_from eq 2 and cur.map.is_sf eq true }">顺丰</c:if>
	          </td>
              <td align="left"><c:out value="${cur.order_user_name}" /></td>
              <td align="left"><c:out value="${cur.buyer_name}" /></td>
              <td align="left"><c:out value="${cur.map.full_name}" /></td> 
              <td align="right" nowrap="nowrap"><c:out value="${cur.buyer_mp}" /></td> 
              <td align="left"><font color="red"><c:out value="${cur.map.log_sn}" /></font></td> 
              <td align="right" nowrap="nowrap"><c:out value="${cur.map.total_num}" /></td>             
              <td align="left" ><fmt:formatDate value="${cur.add_date}" pattern="yy-MM-dd HH:mm:ss" /></td>
              <td align="left" nowrap="nowrap"> <c:if test="${cur.is_ps eq 0}">否</c:if><c:if test="${cur.is_ps eq 1}">是</c:if></td>
              <td align="left" nowrap="nowrap"> <c:if test="${cur.deliver_way ne 1}">否</c:if>
              <c:if test="${cur.deliver_way eq 1}">是</c:if>
              </td>
              <td align="center" nowrap="nowrap"><c:forEach items="${cur.pshowOrdeDetailsList}" var="cur2">
                  ${cur2.map.pd_sn}*${cur2.num}<br/></c:forEach>
              </td>  
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
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="ShOrderLook.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
	            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
	            pager.addHiddenInputs("method", "list");
	            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
	            pager.addHiddenInputs("trade_index_like", "${af.map.trade_index_like}");
	            pager.addHiddenInputs("order_user_name_like", "${af.map.order_user_name_like}");
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
this.form.action="${ctx}/manager/spgl/ShOrderLook.do?method=excel";
this.form.submit();
this.form.action="${ctx}/manager/spgl/ShOrderLook.do";
this.value="导出Excel";
this.disabled=false;
});
	
});
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
