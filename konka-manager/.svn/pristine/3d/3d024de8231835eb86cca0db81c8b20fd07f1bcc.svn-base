<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>康佳产品展示平台</title>
<link rel=stylesheet type=text/css  href="${ctx}/styles/customer/pshow/css/global.css">
<link rel=stylesheet type=text/css  href="${ctx}/styles/customer/pshow/css/sale.css">
<link rel=stylesheet type=text/css  href="${ctx}/styles/customer/pshow/css/myorder.css">
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
</head>
<body>
<!-- head start -->
<div id="topbox">
  <jsp:include page="/customer/PShow/_inc/_top2.jsp" flush="true" />
  <jsp:include page="/customer/PShow/_inc/_head2.jsp" flush="true" />
</div>
<!-- head end --> 
<!-- topnav start -->
<jsp:include page="/customer/PShow/_inc/_head_nav22.jsp" flush="true" />
<!-- topnav end --> 

<!-- third start -->
<div class="maincont">
<div class="user_top" style="margin-left:1px;">尊敬的用户：<font class="orange">${customerUserInfo.real_name}</font> 欢迎您来到康佳电商平台！   您上一次的登录时间是
  <fmt:formatDate value="${customerUserInfo.last_login_time}" pattern="yyyy-MM-dd HH:mm"/>
</div>
<div class="userbox">
<!-- 左侧导航开始  -->
<jsp:include page="_inc/_left.jsp" flush="true"></jsp:include>
<!-- 左侧导航结束  -->
<div class="usecenter">
  <div class="usernav">·您当前的位置：用户中心  &gt; 交易管理  &gt;&nbsp;<font class="orange">我的订单</font></div>
  <div class="userp">
    <div class="userpp">
      <ul>
        <li class="pr">我的订单</li>
      </ul>
    </div>
  </div>
  <%@ include file="/commons/pages/messages.jsp" %>
  <form action="${ctx}/MyOrder.do" class="form_cust" id="form_id_search" method="post">
    <input type="hidden" name="method" value="list" />
    <table width="788" border="0" cellspacing="0" cellpadding="0" style="margin-top:6px; border:1px #c2c2c2 solid;">
      <tr>
        <td width="80" height="32" align="right" style="background:url(${ctx}/styles/customer/pshow/images/user_search.jpg) repeat-x;">订单状态：</td>
        <td width="168" align="left" style="background:url(${ctx}/styles/customer/pshow/images/user_search.jpg) repeat-x;"><select name="state" style="width:100px;" onchange="this.form.submit();" id="state">
            <option value="">--请选择--</option>
            <option value="-30">已退货</option>
            <option value="-20">审核未通过</option>
            <option value="-10">已关闭</option>
            <option value="0">未支付</option>
            <option value="10">已支付</option>
            <option value="20_30">订单处理中</option>
            <option value="40">已发货</option>
            <option value="50">已换货</option>
            <option value="60">已确认收货</option>
          </select></td>
        <td width="469" align="left" style="background:url(${ctx}/styles/customer/pshow/images/user_search.jpg) repeat-x;"><img src="${ctx}/styles/customer/pshow/images/user_search_1.jpg" style="cursor: pointer;" width="43" height="23" id="but_search"/></td>
      </tr>
    </table>
  </form>
    <c:forEach var="cur" items="${entityList}" varStatus="vs">
      <c:url value="/Payment.do?method=view&trade_index=${cur.trade_index}" var="view_url" />
      <table width="790" border="0" align="center" cellpadding="0" cellspacing="0" style="border:1px #eaeaea solid; margin-top:8px;">
        <tr>
          <td colspan="6" align="left" height="30"><font class="orange14px" style="margin-left:5px;">订单号:</font><a href="${view_url}" target="_blank">${cur.trade_index}</a>&nbsp;&nbsp;<font class="orange14px">下单时间:</font>
            <fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd HH:mm"/></td>
        </tr>
        <tr>
          <td width="106" height="23" align="center" style="background:url(${ctx}/styles/customer/pshow/images/user_tab.jpg) repeat-x;"><strong>商品图片</strong></td>
          <td width="260" align="center" style="background:url(${ctx}/styles/customer/pshow/images/user_tab.jpg) repeat-x;"><strong>型号</strong></td>
          <td width="97" align="center" style="background:url(${ctx}/styles/customer/pshow/images/user_tab.jpg) repeat-x;"><strong>价格（元）</strong></td>
          <td width="70" align="center" style="background:url(${ctx}/styles/customer/pshow/images/user_tab.jpg) repeat-x;"><strong>数量</strong></td>
          <td width="90" align="center" style="background:url(${ctx}/styles/customer/pshow/images/user_tab.jpg) repeat-x;"><strong>订单状态</strong></td>
          <td width="131" align="center" style="background:url(${ctx}/styles/customer/pshow/images/user_tab.jpg) repeat-x;"><strong>操作</strong></td>
        </tr>
        <tr>
          <td width="623" colspan="5" style="padding:0px;"><table border="0" width="100%" style="margin:0px;">
              <c:forEach var="cur1" items="${cur.pshowOrdeDetailsList}">
              	<c:set value="${fn:split(cur1.map.main_pic, ',')[0]}" var="main_pic_path" />
                <tr>
                  <td width="106" align="center" style="padding:3px 0px;"><a href="${view_url}" target="_blank"><c:if test="${empty cur1.map.main_pic}"><img src="${ctx}/styles/images/no_img.gif" width="86" height="86" /></c:if>
                    <c:if test="${not empty cur1.map.main_pic}"><img src="${ctx}/${fn:substringBefore(main_pic_path, '.')}_086.jpg" /></c:if></a></td>
                  <td width="260" align="left" style="padding-left:10px;"><a href="${view_url}" target="_blank"><span title="${cur1.pd_name}">${fnx:abbreviate(cur1.pd_name,2*25, '...')}</span></a></td>
                  <td width="97" align="center"><font class="red"><fmt:formatNumber value="${cur1.price}" pattern="￥0.00" /></font></td>
                  <td width="70" align="center">${cur1.num}</td>
                  <td width="90" align="center"><c:choose>
                      <c:when test="${cur.state eq -30}">已退货</c:when>
                      <c:when test="${cur.state eq -20}">审核未通过</c:when>
                      <c:when test="${cur.state eq -10}">已关闭</c:when>
                      <c:when test="${cur.state eq 0}">未支付</c:when>
                      <c:when test="${cur.state eq 10}">已支付</c:when>
                      <c:when test="${cur.state ge 20 and cur.state le 30}">订单处理中</c:when>
                      <c:when test="${cur.state eq 40}">已发货</c:when>
                      <c:when test="${cur.state gt 50}">已换货</c:when>
                      <c:when test="${cur.state gt 60}">已确认收货</c:when>
                    </c:choose></td>
                </tr>
              </c:forEach>
            </table></td>
          <td width="131" align="center"><c:if test="${cur.state eq 0}"><span style="cursor:pointer;" onclick="javascript:c_close('确定关闭订单吗？', '${ctx}/MyOrder.do?method=close&id=${cur.id}');"><font class="blue12px"><a>取消</a></font></span>&nbsp;<font class="blue12px"><span style="cursor:pointer;"><a href="${ctx}/Payment.do?method=list&trade_index=${cur.trade_index}" target="_blank">支付</a></span></font></c:if>
            <c:if test="${cur.state ne 0}"><span style="color:gray;">取消</span>&nbsp;<span style="color:gray;">支付</span></c:if></td>
        </tr>
      </table>
    </c:forEach>
  <form id="bottomPageForm" name="bottomPageForm" method="post" action="${ctx}/MyOrder.do">
    <table width="790" border="0" align="center" cellpadding="0" cellspacing="0" style="border:1px #eaeaea solid; margin-top:8px;">
      <tr>
        <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
          <script type="text/javascript">
	            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
	            pager.addHiddenInputs("method", "list");
	            pager.addHiddenInputs("state", "${af.map.state}");
	            document.write(pager.toString());
	      </script></td>
      </tr>
    </table>
  </form>
</div>
</div>
</div>
<!-- third end -->

<!-- six start -->
<div class="maincont">
  <!-- footer start-->
  <jsp:include page="/customer/PShow/_inc/_footer2.jsp" flush="true" />
  <!-- footer end-->
</div>
<!-- six end -->
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#state").val("${af.map.state}");
	$(document).delegate("#but_search", "click", function(){
		$("#form_id_search").submit();
	});
});
function c_close(msg, url){
	if(confirm(msg)){
		location.href = url;
	}
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
