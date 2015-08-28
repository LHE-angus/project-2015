<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><c:if test="${ecUser.user_type eq 1 }">康佳EPP内部优惠购机平台</c:if><c:if test="${ecUser.user_type eq 2 }">康佳网上直销商城</c:if></title>
<link rel=stylesheet type=text/css  href="${ctx}/styles/member/css/global1.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/member/css/epp.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/member/css/member.css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
</head>
<body>
<!-- head start -->
<div class="topbox">
<jsp:include page="/member/__inc/top.jsp" flush="true" />
<jsp:include page="/member/__inc/search.jsp" flush="true" />
</div> 
<jsp:include page="/member/__inc/nav.jsp" flush="true" />
<!-- second start -->
<div class="maincont">
  <jsp:include page="/member/__inc/_mleft.jsp" flush="true" />
  <!--right-->
  <div class="member_right padbot45">
    <div class="position"><a href="${ctx }/member/Index.do">首页</a> &gt; <a href="${ctx }/member/center/Index.do">会员中心</a> &gt; 我的团购</div>      
    <div class="membertab3">
      <p style="margin-top:15px;font-size:14px;">我的团购</p>
      <html-el:form action="/center/EcGroupBuy">
        <html-el:hidden property="method" styleId="method" value="list" />
        <table width="100%" border="0" cellspacing="0" cellpadding="0"  class="member_form_table0">
          <td width="40%"><html-el:text styleClass="input_txt" property="start_date" size="10" maxlength="10" readonly="true" onclick="new Calendar(2011, 2021, 0).show(this);" style="width:120px;cursor:pointer;text-align:center;" title="点击选择日期" styleId="start_date" ></html-el:text>
              至
              <html-el:text styleClass="input_txt" property="end_date" size="10" maxlength="10" readonly="true" onclick="new Calendar(2011, 2021, 0).show(this);" style="width:120px;cursor:pointer;text-align:center;" title="点击选择日期" styleId="end_date" ></html-el:text>
            </td>
            <td width="80%"><input class="inputbtn" type="submit" name="Submit" value="查询" />
            </td>
          </tr>
        </table>
      </html-el:form>
      <div style="overflow: auto;">   
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="member_form_table2">
        <tr class="tr1">
         <td width="5%" nowrap="nowrap" align="center">序号</td>
          <td width="15%" nowrap="nowrap" align="center">流水号</td>
          <td width="10%" nowrap="nowrap" align="center">商品名称</td>
          <td width="10%" nowrap="nowrap" align="center">商家名称</td>
          <td width="10%" nowrap="nowrap" align="center">商品地址</td>
          <td width="10%" nowrap="nowrap" align="center">商品电话</td>
          <td width="10%" nowrap="nowrap" align="center">金额</td> 
          <td width="7%" nowrap="nowrap" align="center">数量</td>
          <td width="7%" nowrap="nowrap" align="center">验证码</td>  
          <td width="10%" nowrap="nowrap" align="center">购买时间</td>
          <td width="10%" nowrap="nowrap" align="center">使用状态</td>
        </tr>
        <tbody>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr height="60">
              <td align="center" style="line-height:22px;">${vs.count }</td>
              <td align="center">${cur.trade_index }</td>
              <td align="left" nowrap="nowrap">
              <c:if test="${cur.map.is_djq eq 1}">
              <a href="${ctx }/member/EcGroupBuy.do?method=view&group_id=${cur.vouch_id}" style="color: blue;"><c:out value="${cur.map.title }"/></a>
              </c:if>
              <c:if test="${cur.map.is_djq eq 0}">
              <a href="${ctx }/member/PdShowForTuan.do?method=list&goods_id=100018651" style="color: blue;"><c:out value="${cur.map.title }"/></a>
              </c:if>  
              </td>
               <td align="center" nowrap="nowrap">${cur.map.shop_name}</td>
               <td align="center" nowrap="nowrap">${cur.map.shop_addr}</td>
               <td align="center" nowrap="nowrap">${cur.map.shop_tel}</td> 
              <td align="center" nowrap="nowrap">${cur.price}</td> 
              <td align="center" nowrap="nowrap">1</td>
               <td align="center" nowrap="nowrap">${cur.code}</td>    
              <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd hh:mm:ss" /></td>
              <td width="15%" nowrap="nowrap" align="center"> 
		           	<c:if test="${cur.is_del eq 1}"><a href="${ctx}/member/center/EcGroupBuy.do?method=view&id=${cur.id}" style="color: blue;">已作废</a></c:if>
		           	<c:if test="${cur.is_del eq 0}">
		           			<c:if test="${cur.is_userd eq 0}">
		           				未使用
		           			</c:if>
		           			<c:if test="${cur.is_userd eq 1}"> 
		           				<a href="${ctx}/member/center/EcGroupBuy.do?method=view&id=${cur.id}" style="color: blue;"><font color="#0022ff">已使用</font></a>
		           			</c:if>
		           	</c:if>
		           	
           	  </td>
            </tr>
          </c:forEach>
          <c:if test="${empty entityList }"> 
            <tr height="60">
              <td align="center" nowrap="nowrap" colspan="10"> 暂无记录 </td>
            </tr>
          </c:if>
        </tbody>
      </table>
      </div>
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
<jsp:include page="/member/__inc/footer.jsp" flush="true" />
<!-- six footer -->
</body>
</html>
