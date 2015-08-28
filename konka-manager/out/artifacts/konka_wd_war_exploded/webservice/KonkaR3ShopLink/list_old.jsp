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
<link rel="stylesheet" type="text/css" href="${ctx}/mobile/css/common.css" />
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <div class="rtabcont2">
    <html-el:form action="/KonkaR3ShopLink">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="user_id" value="${user_id}" />
      <html-el:hidden property="userpass" value="${userpass}" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="queryString" styleId="queryString" />
	<table width="100%" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td><strong>所属客户：</strong> 
			<html-el:text property="customer_name_like" maxlength="20"></html-el:text></td>
			<td></td>
		</tr>
		<tr>
			<td><strong>分公司：</strong> 
			<html-el:text property="dept_name_like" maxlength="20"></html-el:text></td>
			<td></td>
		</tr>
		<tr>
		    <td><strong>姓名：</strong> 
			<html-el:text property="real_name_like" maxlength="20"></html-el:text></td>
			<td align="right"><input class="but1" type="submit" name="Submit" value="搜索" /></td>
		</tr>
	</table>
</html-el:form>
  </div>
  <div class="rtabcont1">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
      <td width="3%"></td>
        <td>
        	<input type="button" class="but2" name="add" value="新增" onclick="location.href='KonkaR3ShopLink.do?method=add&user_id=${user_id}&userpass=${userpass}';" />
        </td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtab1">    
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
        <tr style="margin-left:10px;">
        <td  align="left" style="cursor: pointer;"  onclick="goview('${cur.link_id}')">
         <span class="leixi">姓名：${cur.real_name}</span><br/>
         <span class="leixi">职务：
         <c:if test="${cur.position eq 1}">付款</c:if> 
         <c:if test="${cur.position eq 2}">对账 </c:if> 
         <c:if test="${cur.position eq 3}">业务</c:if> 
         <c:if test="${cur.position eq 4}">法人</c:if> 
         <c:if test="${cur.position eq 5}">售后</c:if> 
         <c:if test="${cur.position eq 6}">收货</c:if> 
         <c:if test="${cur.position eq 7}">送货</c:if> 
         <c:if test="${cur.position eq 8}">发票</c:if> 
         <c:if test="${cur.position eq 9 or empty cur.position}">其他</c:if>  
         </span> <br/>
         <span class="leixi">联系电话：${cur.tel}</span><br/>
         <span class="leixi">分公司：${cur.map.dept_name}</span><br/>
         <span class="leixi">客户名称：${cur.map.customer_name}</span><br/>
        </td>
        <td align="center"><a href="javascript:goedit('${cur.link_id}')" >修改</a></td>
        </tr>
        </c:forEach>
    </table>
  </div>
  <div style="text-align: center;">
  <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaR3ShopLink.do"> 
      <table width="98%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin:auto;">
        <tr>
          <td align="left">
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
  
  
  <div class="clear"></div>
</div>
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
<jsp:include page="/__analytics.jsp" />
</body>
</html>
