<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/commons/styles/EntpShopSearch_style.css" rel="stylesheet" type="text/css" />

</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td nowrap="nowrap">当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
      <html-el:form action="/admin/KonkaSapInterfaceSetting">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
         <tr>
	           <td width="15%" valign="right" nowrap="nowrap" class="title_item">接口名称</td>
	           <td width="35%">
	           	<html-el:text property="serviceDesc" styleId="serviceDesc"></html-el:text>
	           </td>
	       	   <td><input class="but1" type="submit" name="Submit" value="搜索" /></td>
	           <td></td>
       	</tr>
         </tr> 
    </html-el:form>
  </div>
    
    <div class="rtabcont1" style="margin:10px">
    <%@ include file="/commons/pages/messages.jsp" %>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td>
		    <input class="but2" type="button" name="Submit2" value="新增" onclick="location.href='KonkaSapInterfaceSetting.do?method=add&mod_id=${af.map.mod_id}';" />
		</td>
	 </tr>
	</table>
  </div>
    
  <div class="rtabcont1">
    	
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
        <tr class="tabtt1">
          <td align="center" nowrap="nowrap" width="10%">操作</td>
          <td align="center" nowrap="nowrap" width="30%">接口名称</td>
          <td align="center" nowrap="nowrap" width="30%">时间</td>	
          <td align="center" nowrap="nowrap" width="30%">状态</td>	
        </tr>
		<c:forEach var="cur" items="${entityList}" varStatus="vs">
		        <tr>
		        	<td align="center">
						<span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaSapInterfaceSetting.do', 'edit','id=${cur.id}&mod_id=${af.map.mod_id}')">修改</span>
		            </td>
		            <td align="center" nowrap="nowrap">${cur.serviceDesc }</td>
		            <td align="center" nowrap="nowrap">${cur.update_time }</td>
		            <td align="center" nowrap="nowrap">
		            <c:if test="${cur.status eq 0}">
						<div style="background-color: #00CC00"><font color="white ">启用</font></div>
		            </c:if>
		             <c:if test="${cur.status eq -1}">
		            	<div style="background-color: red"><font color="white ">挂起</font></div>
		            </c:if>
		            </td>
		        </tr>
		</c:forEach>
      </table>
                  
     <form id="bottomPageForm" name="bottomPageForm" method="post" action="?">
	     <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
	       <tr>
	         <td height="40" align="center"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
	           <script type="text/javascript">
				  var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
					  pager.addHiddenInputs("method", "list");
					  pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
					  document.write(pager.toString());
				</script>
			</td>
	       </tr> 
	     </table>
     </form> 
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript">//<![CDATA[
	$(document).ready(function(){
// 		var s = ${af.map.deptid}; 
//     	alert(s);
	}); 
//]]>
</script>
</body>
</html>