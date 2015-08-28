<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="UTF-8" ></meta>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta http-equiv=Expires content=0 />
<meta http-equiv=Cache-Control content=no-cache />
<meta http-equiv=Pragma content=no-cache />
<title>客户联系人</title>
<link rel="stylesheet" type="text/css" href="${ctx}/mobile/wap/common.css" />
</head>
<body>
<div class="conlist">
  <ul>
  <li>
  <html-el:form action="/KonkaR3ShopLink">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="user_id" value="${user_id}" />
      <html-el:hidden property="userpass" value="${userpass}" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="queryString" styleId="queryString" />  
  	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  <tr>
	    <td width="6%" align="right">客&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;户：</td>
	    <td >
	    <html-el:text property="customer_name_like" styleClass="input-txt" maxlength="20" />
	    </td>
	  </tr>
	  <tr>
	    <td align="right">分&nbsp;&nbsp;公&nbsp;&nbsp;司：</td>
	    <td >
	    <html-el:text property="dept_name_like" styleClass="input-txt" maxlength="20"></html-el:text>
	    </td>
	  </tr>
	  <tr>
	    <td align="right">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</td>
	    <td>
	    <html-el:text property="real_name_like" styleClass="input-txt" maxlength="20"></html-el:text>
	    </td>
	    </tr>
	     <tr>
	    <td align="right">&nbsp;</td>
	    <td><input class="websub" value="查 询" type="submit" /></td>
	    </tr>
	</table>
   </html-el:form>
</li>
<c:forEach var="cur" items="${entityList}" varStatus="vs">    
	<li>
	<p ><strong>姓名：</strong><a style="color: blue;cursor: pointer;" onclick="goview('${cur.link_id}')">${cur.real_name}</a></p>
	<p onclick="goview('${cur.link_id}')"><strong>职务：</strong><c:if test="${cur.position eq 1}">付款</c:if> 
	         <c:if test="${cur.position eq 2}">对账 </c:if> 
	         <c:if test="${cur.position eq 3}">业务</c:if> 
	         <c:if test="${cur.position eq 4}">法人</c:if> 
	         <c:if test="${cur.position eq 5}">售后</c:if> 
	         <c:if test="${cur.position eq 6}">收货</c:if> 
	         <c:if test="${cur.position eq 7}">送货</c:if> 
	         <c:if test="${cur.position eq 8}">发票</c:if> 
	         <c:if test="${cur.position eq 9 or empty cur.position}">其他</c:if></p>
	<span class="r"><span class="webbut" onclick="goedit('${cur.link_id}')" >修改</span></span>             
	<p onclick="goview('${cur.link_id}')"><strong>联系电话：</strong>${cur.tel}</p>
	<p onclick="goview('${cur.link_id}')"><strong>分公司：</strong>${cur.map.dept_name}</p>
	<p onclick="goview('${cur.link_id}')"><strong>客户名称：</strong>${cur.map.customer_name}</p>
	</li>
</c:forEach>         
  <li>
  <div style="margin：0 auto;text-align: center;">
  <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaR3ShopLink.do"> 
      <table width="98%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td align="center">
          <script type="text/javascript" src="${ctx}/commons/scripts/pager4.js">;</script>
            <script type="text/javascript">
                var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
	            pager.addHiddenInputs("method", "list");
	            pager.addHiddenInputs("customer_name_like", "${af.map.customer_name_like}");
	            pager.addHiddenInputs("dept_name_like", "${af.map.dept_name_like}");
	            pager.addHiddenInputs("real_name_like", "${af.map.real_name_like}");
	            pager.addHiddenInputs("user_id", "${user_id}");
	            pager.addHiddenInputs("userpass", "${userpass}");
	            document.write(pager.toString());
            </script></td>
        </tr>
      </table>
    </form>
    </div>
  </li>
  </ul>  
</div>
 <div class="clear"></div>

<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	//I8右宽度不能自动适应加载，IE7,FireFox都可以的
	if(document.body.scrollLeft > 0 || document.body.scrollWidth > document.body.offsetWidth){
		$(".frame_right").width($(window).width() - 163);
	}else{
		$(".frame_right").width($(window).width() - 150);
	}
});

function goedit(id){
	window.location.href= "${ctx}/webservice/KonkaR3ShopLink.do?method=edit&link_id="+ id +"&user_id=${user_id}&userpass=${userpass}";
}
function goview(id){
	window.location.href= "${ctx}/webservice/KonkaR3ShopLink.do?method=view&link_id="+ id +"&user_id=${user_id}&userpass=${userpass}";
}
//]]>
</script>
</body>
</html>
