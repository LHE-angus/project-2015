<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><c:if test="${ecUser.user_type eq 1 }">康佳EPP内部优惠购机平台</c:if><c:if test="${ecUser.user_type eq 2 }">康佳网上直销商城</c:if></title>
<link rel=stylesheet type=text/css  href="${ctx}/mobile/css/common.css" />
<style type="text/css">
.xt_fenye { font-weight: normal; height: 58px; line-height: 58px; text-align: right; padding-right: 30px; padding-top: 20px; }

</style>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
</head>
<body>
<div class="maincont">
  <!--right-->
  <div class="member_right padbot45">
    <div class="membertab3">
      <p style="margin-top:15px;font-size:14px;"> 您当前的积分:<span style="color:#FF2200;font-weight:bold;">${totalScore }</span></p>
      <p style="margin-top:15px;font-size:14px;">积分消费记录</p>
      <html-el:form action="/EcUserScoreRec">
        <html-el:hidden property="method" styleId="method" value="list" />
        <html-el:hidden property="ts" />
        <html-el:hidden property="user_id" value="${af.map.user_id}"/>
        <html-el:hidden property="userpass" value="${af.map.userpass}"/>
      </html-el:form>

      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtab1">
        <tr class="tabtt1">
          <th width="30%" nowrap="nowrap" align="center">积分类型</th>
          <th width="20%" nowrap="nowrap" align="center">积分</th>
        </tr>
        <tbody>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr>
              <td align="left" nowrap="nowrap"><c:out value="${cur.note }"/>&nbsp;&nbsp;<fmt:formatDate value="${cur.opr_date}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
              <td align="left" nowrap="nowrap">
              <c:if test="${cur.opr_type eq 0}"> ${cur.score }</c:if>
              <c:if test="${cur.opr_type eq 1}"> -${cur.score }</c:if>
              </td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    <c:if test="${af.map.pageCount >1}">  
    <div class="xt_fenye"> 
    <a onclick="goback(${af.map.currentPage},null,null,'${af.map.ts}');"><img src="${ctx}/mobile/images/xt_shagnyiye.jpg" width="66" height="24" /></a> <a onclick="goforward(${af.map.currentPage},null,null,'${af.map.ts}');"><img src="${ctx}/mobile/images/xt_xiayye.jpg" width="66" height="24" /></a> 
    </div>
    </c:if>
      
      <p style="margin-top:15px;font-size:14px;">积分获取记录</p>
       <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtab1">
        <tr class="tabtt1">
          <th width="60%" nowrap="nowrap" align="center">商品名称</th> 
          <th width="20%" nowrap="nowrap" align="center">积分</th>
        </tr>
        <tbody>
          <c:forEach var="cur" items="${orderDetailList}" varStatus="vs">
           <tr>
              <td align="left"  class="leixi">${cur.map.pd_sn }&nbsp;&nbsp; <fmt:formatDate value="${cur.map.add_date}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
              <td align="left"  class="leixi"> <fmt:formatNumber value="${cur.map.pay_price+cur.integral}" pattern="#,##0" /></td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
    </div>
  </div>
  <div class="clear"></div>
</div>
</body>
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

function goback(page,username,userpass,ts) {
	if(goPage(null,page,-1,5)){
		window.location.href="EcUserScoreRec.do?"+"user_id="+userid1+"&userpass="+userpass1+"&page="+page+"&forward=-1&ts="+ts;
	}
}

function goforward(page,username,userpass,ts) {
	if(goPage(null,page,1,5)){
		window.location.href="EcUserScoreRec.do?"+"user_id="+userid1+"&userpass="+userpass1+"&page="+page+"&forward=1&ts="+ts;
	}
}


 
//]]></script>
</html>
