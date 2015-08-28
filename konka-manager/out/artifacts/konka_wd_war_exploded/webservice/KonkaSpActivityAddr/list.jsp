<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<META charset="UTF-8">
<META name="viewport" content="width=device-width, initial-scale=1">
<META http-equiv=Expires content=0>
<META http-equiv=Cache-Control content=no-cache>
<META http-equiv=Pragma content=no-cache>
<title>预约点管理</title>
<link rel="stylesheet" type="text/css"
	href="${ctx}/mobile/css/common.css" />
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <div class="rtabcont2">
    <html-el:form action="/KonkaSpActivityAddr">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="user_id" value="${user_id}" />
      <html-el:hidden property="userpass" value="${userpass}" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="queryString" styleId="queryString" />
	<table width="100%" border="0" cellspacing="5" cellpadding="0">
		<tr>

			<td><strong>预约点编码：</strong> <html-el:text
				property="addr_index_like"></html-el:text></td>
		</tr>
		<tr>
			<td><strong>门店名称：</strong> <html-el:text
				property="store_name_like"></html-el:text></td>
		</tr>
		<tr>

			<td><strong>时&nbsp;&nbsp;&nbsp;&nbsp;间：</strong> <html-el:text
				property="add_date_start" styleId="add_date_start" size="7"
				maxlength="10" readonly="true" styleClass="webinput"
				style="cursor:pointer;"
				onclick="new Calendar(2005, 2030, 0).show(this);" /> 至 <html-el:text
				property="add_date_end" styleId="add_date_end" size="7"
				maxlength="10" readonly="true" styleClass="webinput"
				style="cursor:pointer;"
				onclick="new Calendar(2005, 2030, 0).show(this);" /></td>
		</tr>
		<tr>
			<td align="right"><input class="but1" type="submit"
				name="Submit" value="搜索" /></td>
		</tr>
	</table>
</html-el:form>
  </div>
  <div class="rtabcont1">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
      <td width="3%"></td>
        <td>
        	<input type="button" class="but2" name="add" value="新增" onclick="location.href='KonkaSpActivityAddr.do?method=add&user_id=${user_id}&userpass=${userpass}';" />
        </td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtab1">    
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
        <tr style="margin-left:10px;">
        <td  align="left" style="cursor: pointer;"  onclick="goview('${cur.id}')">
        <span class="leixi">门店名称：${cur.store_name}</span>
        <br/>
          <span class="leixi">预约点编码：${cur.addr_index}</span>
          <br/>
          <span class="leixi">预约点地址：${cur.addr}</span>
          <br/>
         <span class="leixi">预约点状态：
          <c:choose>
          <c:when test="${cur.state eq 0}"><font color="green">开启</font></c:when>
             <c:when test="${cur.state eq 1}"><font color="grey">关闭</font></c:when>
             <c:otherwise>未指定</c:otherwise>
             </c:choose>
             </span>
        <br/>
        </td>
        <td align="center"><a href="javascript:goedit('${cur.id}')" data-role="button" data-icon="plus" data-theme="b" data-inline="true">修改</a></td>
        </tr>
        </c:forEach>
      
    </table>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaSpActivityAddr.do">
      <table width="98%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin:20px 0px;">
        <tr>
          <td align="left">
          <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
                var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
	            pager.addHiddenInputs("method", "list");
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
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}
function goedit(id){
	window.location.href= "${ctx}/webservice/KonkaSpActivityAddr.do?method=edit&id="+ id +"&user_id=${user_id}&userpass=${userpass}";
}
function goview(id){
	window.location.href= "${ctx}/webservice/KonkaSpActivityAddr.do?method=view&id="+ id +"&user_id=${user_id}&userpass=${userpass}";
}
//]]>
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
