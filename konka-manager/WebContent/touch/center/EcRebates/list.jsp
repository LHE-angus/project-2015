<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><c:if test="${ecUser.user_type eq 1 }">康佳EPP内部优惠购机平台</c:if><c:if test="${ecUser.user_type eq 2 }">康佳网上直销商城</c:if></title>
<link rel=stylesheet type=text/css  href="${ctx}/styles/touch/css/global1.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/touch/css/epp.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/touch/css/touch.css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
</head>
<body>
<!-- head start -->
<div class="topbox">
<jsp:include page="/touch/__inc/top.jsp" flush="true" />
<jsp:include page="/touch/__inc/search.jsp" flush="true" />
</div> 
<jsp:include page="/touch/__inc/nav.jsp" flush="true" />
<!-- second start -->
<div class="maincont">
  <jsp:include page="/touch/__inc/_mleft.jsp" flush="true" />
  <!--right-->
  <div class="touch_right padbot45">
    <div class="position"><a href="${ctx }/touch/Index.do">首页</a> &gt; <a href="${ctx }/touch/center/Index.do">会员中心</a> &gt;我的佣金</div>
    <div class="touchtab3">
      <p style="margin-top:15px;font-size:14px;">您获得的佣金总额:<span style="color:#FF2200;font-weight:bold;"><fmt:formatNumber value="${fn:escapeXml(rebates)}" pattern="0.00" /> </span></p>      
      <html-el:form action="/center/EcRebates">
        <html-el:hidden property="method" styleId="method" value="list" />
        <table width="100%" border="0" cellspacing="0" cellpadding="0"  class="touch_form_table0">
          <td width="60%"><html-el:text styleClass="input_txt" property="start_date" size="10" maxlength="10" readonly="true" onclick="new Calendar(2011, 2021, 0).show(this);" style="width:120px;cursor:pointer;text-align:center;" title="点击选择日期" styleId="start_date" ></html-el:text>
              至
   <html-el:text styleClass="input_txt" property="end_date" size="10" maxlength="10" readonly="true" onclick="new Calendar(2011, 2021, 0).show(this);" style="width:120px;cursor:pointer;text-align:center;" title="点击选择日期" styleId="end_date" ></html-el:text>
           
      &nbsp;      
          佣金状态：  <html-el:select property="rebates_status" style="width:105px;height:25px; margin-left:-1px; margin-top:-1px;">
         <html-el:option value="">==全部==</html-el:option>
         <html-el:option value="0">需确认提现、兑换积分</html-el:option>
         <html-el:option value="1">提现 已发放</html-el:option>
         <html-el:option value="2">提现 等待发放</html-el:option>
         <html-el:option value="3">已兑换积分</html-el:option>
         </html-el:select>  </td> <td width="40%"><input class="inputbtn" type="submit" name="Submit" value="查询" />
            </td>
          </tr>
        </table>
      </html-el:form>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="touch_form_table2">
        <tr class="tr1">
          <td width="5%" nowrap="nowrap" align="center">序号</td>
          <td width="15%" nowrap="nowrap" align="center">商品名称</td>
          <td width="10%" nowrap="nowrap" align="center">单价数量</td>          
          <td width="10%" nowrap="nowrap" align="left">获得佣金</td>   
          <td width="10%" nowrap="nowrap" align="center">时间</td>
          <td width="10%" nowrap="nowrap" align="center">提现兑换状态</td> 
        </tr>
        <tbody>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr height="60">
              <td align="center" nowrap="nowrap">${vs.count }</td>
              <td align="center" nowrap="nowrap"><c:out value="${cur.pd_name }"/></td>
              <td align="center" nowrap="nowrap"><fmt:formatNumber value="${fn:escapeXml(cur.price)}" pattern="￥0.00" /> * ${cur.num }</span></td>
              <td align="left" nowrap="nowrap"><c:if test="${cur.rebates_status eq 1 }"><font color="green">
              <fmt:formatNumber value="${fn:escapeXml(cur.rebates)}" pattern="￥0.00" /></font></c:if><c:if test="${cur.rebates_status ne 1 }"><font color="red">
              <fmt:formatNumber value="${fn:escapeXml(cur.rebates)}" pattern="￥0.00" /></font></c:if></td>
              <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.map.add_date}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
              <td align="center" nowrap="nowrap">
              	<c:if test="${cur.rebates_status eq 1 }">提现 已发放</c:if>
              	<c:if test="${cur.rebates_status eq 0 }">[<a href="?method=save&type=2&id=${cur.bill_item_id }">提现</a>] [<a href="?method=save&type=3&id=${cur.bill_item_id }">兑换积分</a>]</c:if>
              	<c:if test="${cur.rebates_status eq 2 }">提现 等待发放</c:if>
              	<c:if test="${cur.rebates_status eq 3 }">已兑换积分</c:if>
              </td>  
             </tr>
          </c:forEach>
          <c:if test="${empty entityList }">
            <tr height="60">
              <td align="center" nowrap="nowrap" colspan="6"> 暂无记录 </td>
            </tr>
          </c:if>
        </tbody>
      </table>
      <c:if test="${af.map.pager.pageCount>1}">
        <form id="bottomPageForm" name="bottomPageForm" method="post" action="?">
          <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr>
              <td height="40"><div style="text-align: right; padding-right: 5px;">
                  <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
                  <script type="text/javascript">
			                     var pager = new Pager(document.bottomPageForm, parseInt('${af.map.pager.recordCount}'), parseInt('${af.map.pager.pageSize}'), parseInt('${af.map.pager.currentPage}'));
			                     pager.addHiddenInputs("method", "list");
			                     pager.addHiddenInputs("start_date", "<c:out value='${af.map.start_date}'/>"); 	
			                     pager.addHiddenInputs("end_date", "<c:out value='${af.map.end_date}'/>"); 	
			                     pager.addHiddenInputs("rebates_status", "<c:out value='${af.map.rebates_status}'/>"); 	
			                     document.write(pager.toString());
			                 </script>
                </div></td>
            </tr>
          </table>
        </form>
      </c:if>
    </div>
  </div>
  <div class="clear"></div>
</div>
<jsp:include page="/touch/__inc/footer.jsp" flush="true" />
<!-- six footer -->
</body>
</html>
