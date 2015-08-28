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
      <html-el:form action="/oa/OaUserGroup">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
         <tr>
	           <td  class="title_item">群组：</td>
	           <td width="35%" >
	           	<html-el:text property="group_name"></html-el:text>
	           </td>
	           
	           <td class="title_item">部门：</td>
	           <td width="35%" >
		           <html-el:select property="dept_id" styleId="dept_id" style="width:172px;" >
					<html-el:option value="">请选择</html-el:option>
					<c:forEach var="cur" items="${deptList}" varStatus="vs">
						<html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
					</c:forEach>
				   </html-el:select> 
				</td>
         </tr>
         <tr>
         	<td colspan="3"></td>
         	<td><input class="but1" type="submit" name="Submit" value="搜索" /></td>
         </tr>
    </html-el:form>
  </div>
    
    <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td>
		    <input class="but2" type="button" name="Submit2" value="添加" onclick="location.href='OaUserGroup.do?method=add&dept_id=${af.map.dept_id }&mod_id=${af.map.mod_id}';" />
		</td>
	 </tr>
	</table>
  </div>
    
  <div class="rtabcont1">
    <div style="overflow-x: auto;">
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
        <tr class="tabtt1">
          <td align="center" nowrap="nowrap" width="5%">序号</td>
          <td align="center" nowrap="nowrap" width="10%" style="display:none">部门id</td>	
          <td align="center" nowrap="nowrap" width="10%">部门</td>	
          <td align="center" nowrap="nowrap" width="10%">客户群组</td>	
          <td align="center" nowrap="nowrap" width="10%">状态</td>	
          <td align="center" nowrap="nowrap" width="10%">操作</td>	
        </tr>
		<c:forEach var="cur" items="${OaUserGroupHList}" varStatus="vs">
		        <tr bgcolor="${cur.is_del eq 0 ? '#FFFFFF':'#FFCC33' }">
		        	
		        	<td height="28"  align="center">${vs.count}</td>
		            <td align="center" nowrap="nowrap" style="display:none">${cur.dept_id }</td> 
		            <td align="left" nowrap="nowrap">${cur.dept_name }</td> 
		            <td align="center" nowrap="nowrap">${cur.group_name }</td> 
		            <td align="center" nowrap="nowrap">
		             <c:choose>
			            <c:when test="${cur.is_del eq 0}">
			            	有效
			            </c:when> 
			             <c:otherwise>
			            	无效
			            </c:otherwise>
					</c:choose>
		            </td>
		            <td  align="center" nowrap="nowrap" >
		            <c:choose>
			            <c:when test="${cur.is_del eq 0}">
			            	<span style="cursor:pointer;color:red"  onclick="doNeedMethod('确认操作吗', 'OaUserGroup.do', 'delete','mod_id=${af.map.mod_id}&id=${cur.id}&dept_id=${af.map.dept_id }&is_del=1')" title="禁用">禁用</span>
			            </c:when> 
			             <c:otherwise>
			            	<span style="cursor:pointer;color:red"  onclick="doNeedMethod('确认操作', 'OaUserGroup.do', 'delete','mod_id=${af.map.mod_id}&id=${cur.id}&dept_id=${af.map.dept_id }&is_del=0')" title="启用">启用</span>
			            </c:otherwise>
					</c:choose>
		            <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'OaUserGroup.do', 'edit','mod_id=${af.map.mod_id}&id=${cur.id}&dept_id=${af.map.dept_id }')">修改</span>
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
					  pager.addHiddenInputs("dept_id", "${af.map.dept_id}");
					  pager.addHiddenInputs("group_name", "${af.map.group_name}");
					  document.write(pager.toString());
				</script>
			</td>
	       </tr> 
	     </table>
     </form>
     </div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript">//<![CDATA[
	$(document).ready(function(){
		
	}); 
//]]>
</script>
</body>
</html>