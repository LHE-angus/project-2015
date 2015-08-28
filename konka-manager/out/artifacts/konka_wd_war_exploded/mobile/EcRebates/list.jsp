<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><c:if test="${ecUser.user_type eq 1 }">康佳EPP内部优惠购机平台</c:if><c:if test="${ecUser.user_type eq 2 }">康佳网上直销商城</c:if></title>
<link rel=stylesheet type=text/css  href="${ctx}/mobile/css/common.css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
</head>
<body>
<div class="maincont">
  <!--right-->
  <div class="member_right padbot45">
    <div class="membertab3">
      <p style="margin-top:15px;font-size:14px;">您获得的佣金总额:<span style="color:#FF2200;font-weight:bold;"><fmt:formatNumber value="${fn:escapeXml(rebates)}" pattern="0.00" /> </span></p>      
      <html-el:form action="/EcRebates">
        <html-el:hidden property="method" styleId="method" value="list" />
        <table width="100%" border="0" cellspacing="0" cellpadding="0" >
         <tr height="40">
          <td width="20%">起始时间：</td>
          <td nowrap="nowrap">
          <html-el:text styleClass="input_txt" property="start_date" size="10" maxlength="10" readonly="true" onclick="new Calendar(2011, 2021, 0).show(this);" style="width:80px;cursor:pointer;text-align:center;" title="点击选择日期" styleId="start_date" ></html-el:text>
              至
   <html-el:text styleClass="input_txt" property="end_date" size="10" maxlength="10" readonly="true" onclick="new Calendar(2011, 2021, 0).show(this);" style="width:80px;cursor:pointer;text-align:center;" title="点击选择日期" styleId="end_date" ></html-el:text>
         </td>
        </tr>
         <tr height="40"> 
         <td width="20%"> 佣金状态：</td> 
         <td> 
           <html-el:select property="rebates_status" style="width:105px;height:25px; margin-left:-1px; margin-top:-1px;">
         <html-el:option value="">==全部==</html-el:option>
         <html-el:option value="0">需确认提现、兑换积分</html-el:option>
         <html-el:option value="1">提现 已发放</html-el:option>
         <html-el:option value="2">提现 等待发放</html-el:option>
         <html-el:option value="3">已兑换积分</html-el:option>
         </html-el:select>  
         </td>
         </tr>
         <tr height="40"> 
          <td width="30%">  </td> 
         <td width="30%"><input class="inputbtn" type="submit" name="Submit" value="查询" />
            </td>
          </tr>
        </table>
      </html-el:form>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtab1">
        <tr class="tabtt1">
          <td width="15%" nowrap="nowrap" align="center">商品信息</td>
          <td width="10%" nowrap="nowrap" align="center">提现兑换状态</td> 
        </tr>
        <tbody>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr height="60">
              <td align="left" nowrap="nowrap">
              <c:out value="${cur.map.pd_sn}"/>&nbsp;&nbsp;<fmt:formatNumber value="${fn:escapeXml(cur.price)}" pattern="￥0.00" />&nbsp;&nbsp;
              <c:if test="${cur.rebates_status eq 1 }"><font color="green">
              <fmt:formatNumber value="${fn:escapeXml(cur.rebates)}" pattern="￥0.00" /></font></c:if><c:if test="${cur.rebates_status ne 1 }"><font color="red">
              <fmt:formatNumber value="${fn:escapeXml(cur.rebates)}" pattern="￥0.00" /><c:if test="${empty cur.rebates}">￥0.00</c:if></font></c:if>&nbsp;&nbsp;
              <fmt:formatDate value="${cur.map.add_date}" pattern="yyyy-MM-dd HH:mm:ss" />
              </td>
              <td align="left" nowrap="nowrap">
              	<c:if test="${cur.rebates_status eq 1 }">提现 已发放</c:if>
              	<c:if test="${cur.rebates_status eq 0 }">[<a href="?method=save&type=2&id=${cur.bill_item_id}&username=${af.map.username}&userpass=${af.map.userpass}&user_id=${af.map.user_id}">提现</a>] [<a href="?method=save&type=3&id=${cur.bill_item_id}&username=${af.map.username}&userpass=${af.map.userpass}&user_id=${af.map.user_id}">兑换积分</a>]</c:if>
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
      <c:if test="${af.map.pageCount >1}">  
    <div class="xt_fenye" style="float: right;"> 
    <a onclick="goback(${af.map.currentPage},null,null,'${af.map.rebates_status}');"><img src="${ctx}/mobile/images/xt_shagnyiye.jpg" width="66" height="24" /></a> <a onclick="goforward(${af.map.currentPage},null,null,'${af.map.rebates_status}');"><img src="${ctx}/mobile/images/xt_xiayye.jpg" width="66" height="24" /></a> 
    </div>
    </c:if>
    </div>
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
		
});

var userid1='${af.map.user_id}';
var userpass1='${af.map.userpass}';
var recordNum = '${af.map.recordCount}';

function goview(url){
	var href_value = "${ctx}/" + url;
	window.location.href = href_value ;
}


function goPage(method,page,forward,pagelimit){
	pagelimit = Math.ceil(recordNum / pagelimit);
	if(page == 1 && forward == -1){
		alert("已到首页！");
		return false;
	}
	else if(page == pagelimit && forward == 1){
		alert("已到尾页！");
		return false;
	}
	else if(pagelimit == 0){
		alert("无翻页信息！");
		return false;
	}
	else
		return true;
}

function goback(page,username,userpass,rebates_status) {
	if(goPage(null,page,-1,5)){
		window.location.href="EcRebates.do?"+"user_id="+userid1+"&userpass="+userpass1+"&page="+page+"&forward=-1&rebates_status"+rebates_status;
	}
}

function goforward(page,username,userpass,rebates_status) {
	if(goPage(null,page,1,5)){
		window.location.href="EcRebates.do?"+"user_id="+userid1+"&userpass="+userpass1+"&page="+page+"&forward=1&rebates_status"+rebates_status;
	}
}


 
//]]></script>
</body>
</html>
