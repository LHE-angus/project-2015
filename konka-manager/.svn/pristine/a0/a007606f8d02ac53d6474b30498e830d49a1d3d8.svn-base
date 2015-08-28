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
      <html-el:form action="/admin/CrmCustomerGroup">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="deptid" value="${af.map.deptid}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
         <tr>
	           <td width="15%" valign="middle" nowrap="nowrap" class="title_item">群组编码：</td>
	           <td width= "35%">
	           	<html-el:text property="groupcode" styleId="groupcode"></html-el:text>
	           </td>
	           <td width="15%" valign="middle" nowrap="nowrap" class="title_item">群组名称：</td>
	           <td width= "35%">
	           	<html-el:text property="groupname" styleId="groupname"></html-el:text>
	           </td>
         </tr>
         
         <tr>
         	   <td width="15%" valign="middle" nowrap="nowrap" class="title_item">分公司：</td>
	           <td width= "35%">
		           <html-el:select property="dept_id" styleId="dept_id" style="width:151px;">
					<html-el:option value="">-请选择-</html-el:option>
					<c:forEach var="cur" items="${deptList}" varStatus="vs">
						<html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
					</c:forEach>
				   </html-el:select> 
			   </td>
	           <td width="15%" valign="middle" nowrap="nowrap" class="title_item">状态：</td>
	           <td width= "35%">
		           	<html-el:select property="isdel" styleId="isdel" style="width:151px;">
					<html-el:option value="">-请选择-</html-el:option>
					<html-el:option value="1">无效</html-el:option>
					<html-el:option value="0">有效</html-el:option>
				   </html-el:select>
	           </td>
         </tr>
         <tr>
               <td width="15%" valign="middle" nowrap="nowrap" class="title_item">客户名称：</td>
	           <td width= "35%">
	           	<html-el:text property="customername" styleId="customername"></html-el:text>
	           </td>
               <td><input class="but1" type="submit" name="Submit" value="搜索" /></td>
               <td></td>
         </tr> 
    </html-el:form>
  </div>
    
    <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td>
		    <input class="but2" type="button" name="Submit2" value="新增" onclick="location.href='CrmCustomerGroup.do?method=add&deptid=${af.map.deptid }&mod_id=${af.map.mod_id}';" />
		</td>
	 </tr>
	</table>
  </div>
    
  <div class="rtabcont1">
    	
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
        <tr class="tabtt1">
          <td align="center" nowrap="nowrap" width="10%">操作</td>
          <td align="center" nowrap="nowrap" width="10%">序号</td>
          <td align="center" nowrap="nowrap" width="10%">群组编码</td>
          <td align="center" nowrap="nowrap" width="10%">群组名称</td>	
          <td align="center" nowrap="nowrap" width="10%" style="display:none">分公司id</td>	
          <td align="center" nowrap="nowrap" width="10%" >所属部门</td>	
          <td align="center" nowrap="nowrap" width="10%">客户数</td>	
          <td align="center" nowrap="nowrap" width="10%">创建时间</td>	
          <td align="center" nowrap="nowrap" width="10%">最后修改时间</td>	
          <td align="center" nowrap="nowrap" width="10%">状态</td>	
        </tr>
		<c:forEach var="cur" items="${entityList}" varStatus="vs">
		        <tr bgcolor="${cur.isdel eq 0 ? '#FFFFFF':'#FFCC33' }">
		        	<td  align="center" nowrap="nowrap" >
		            <c:if test="${cur.isdel eq 0}">
		              <span style="cursor:pointer; color:red"  onclick="doNeedMethod('是否禁用？', 'CrmCustomerGroup.do', 'delete','mod_id=${af.map.mod_id}&id=${cur.id}&deptid=${af.map.deptid }&isdel=1')" title="禁用">禁用</span>
		            </c:if> 
		            <c:if test="${cur.isdel eq 1}">
		              <span style="cursor:pointer; color:red" onclick="doNeedMethod('是否启用？', 'CrmCustomerGroup.do', 'delete','mod_id=${af.map.mod_id}&id=${cur.id}&deptid=${af.map.deptid }&isdel=0')" title="启用">启用</span>
		            </c:if>
		              <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'CrmCustomerGroup.do', 'edit','mod_id=${af.map.mod_id}&id=${cur.id}&deptid=${af.map.deptid }')">修改</span>
		            </td>
		        	<td height="28"  align="center">${vs.count}</td>
		            <td align="center" nowrap="nowrap"><a href="${ctx }/manager/admin/CrmCustomerGroup.do?method=view&id=${cur.id}&mod=${af.map.mod_id}" style="color:blue;text-decoration:underline;">${cur.groupcode }</a></td> 
		            <td align="center" nowrap="nowrap">${cur.groupname }</td>
		            <td align="center" nowrap="nowrap" style="display:none">${cur.deptid }</td>
		            <td align="center" nowrap="nowrap">${cur.map.dept_name }</td>
		            <td align="center" nowrap="nowrap">${cur.map.howmany }</td>
		            <td align="center" nowrap="nowrap"><fmt:formatDate pattern="yyyy-MM-dd" value="${cur.created_date }"></fmt:formatDate></td>
		            <td align="center" nowrap="nowrap"><fmt:formatDate pattern="yyyy-MM-dd" value="${cur.last_updated_date }"></fmt:formatDate></td>
		            <td align="center" nowrap="nowrap">
		            <c:if test="${cur.isdel eq 0}">
		            	有效
		            </c:if>
		             <c:if test="${cur.isdel eq 1}">
		            	无效
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
					  pager.addHiddenInputs("groupcode", "${af.map.groupcode}");
					  pager.addHiddenInputs("groupname", "${af.map.groupname}");
					  pager.addHiddenInputs("customername", "${af.map.customername}");
					  pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
					  pager.addHiddenInputs("dept_id", "${af.map.dept_id}");
					  pager.addHiddenInputs("isdel", "${af.map.isdel}");
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