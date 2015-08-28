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
      <html-el:form action="/admin/CrmPriceHeader">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
<%--       <html-el:hidden property="dept_id" value="${af.map.dept_id}" /> --%>
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
         <tr>
	           <td width="15%" valign="middle" nowrap="nowrap" class="title_item">价格编码：</td>
	           <td width="35%" >
	           	<html-el:text property="price_code"></html-el:text>
	           </td>
	           <td width="15%" valign="middle" nowrap="nowrap" class="title_item">价格名称：</td>
	           <td width="35%" >
	           	<html-el:text property="price_name"></html-el:text>
	           </td>
         </tr>
         
         <tr>
           	<td width="15%" valign="middle" nowrap="nowrap" class="title_item">部门：</td>
	           <td width="35%" >
		           <html-el:select property="dept_id" styleId="dept_id" style="width:172px;" >
					<html-el:option value="">请选择</html-el:option>
					<c:forEach var="cur" items="${deptList}" varStatus="vs">
						<html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
					</c:forEach>
				   </html-el:select> 
				</td>
	           <td width="15%" valign="middle" nowrap="nowrap" class="title_item">状态：</td>
	           <td width="35%" >
		           	<html-el:select property="isdel" styleId="isdel" style="width:172px;">
					<html-el:option value="">请选择</html-el:option>
					<html-el:option value="1">无效</html-el:option>
					<html-el:option value="0">有效</html-el:option>
				   </html-el:select>
	           </td>
         </tr>  
         
         <tr>
               <td width="15%" valign="middle" nowrap="nowrap" class="title_item">客户群组编码：</td>
	           <td width="35%" >
	           	<html-el:text property="groupcode"></html-el:text>
	           </td>
	           <td width="15%" valign="middle" nowrap="nowrap" class="title_item">机型编码：</td>
	           <td width="35%" >
	           	<html-el:text property="modelcode_line"></html-el:text>
	           </td>
         </tr>
         <tr>
         	<td></td>
         	<td colspan="3"><input class="but1" type="submit" name="Submit" value="搜索" /></td>
         </tr>
    </html-el:form>
  </div>
    
    <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td>
<!-- 		    <input class="but2" type="button" name="Submit2" value="order" onclick="location.href='KonkaOrderSearch.do?method=toSimpleExcel';" /> -->
		    <input class="but2" type="button" name="Submit2" value="新增" onclick="location.href='CrmPriceHeader.do?method=add&dept_id=${af.map.dept_id }&mod_id=${af.map.mod_id}';" />
		</td>
	 </tr>
	</table>
  </div>
    
  <div class="rtabcont1">
    <div style="overflow-x: auto;">
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
        <tr class="tabtt1">
          <td align="center" nowrap="nowrap" width="10%">操作</td>
          <td align="center" nowrap="nowrap" width="5%">序号</td>
          <td align="center" nowrap="nowrap" width="10%" style="display:none">部门id</td>	
          <td align="center" nowrap="nowrap" width="10%">部门</td>	
          <td align="center" nowrap="nowrap" width="10%">价格类型</td>	
          <td align="center" nowrap="nowrap" width="10%">客户群组</td>	
          <td align="center" nowrap="nowrap" width="10%">价格编码</td>
          <td align="center" nowrap="nowrap" width="10%">价格名称</td>	
          <td align="center" nowrap="nowrap" width="5%">机型数量</td>	
          <td align="center" nowrap="nowrap" width="10%">开始时间</td>	
          <td align="center" nowrap="nowrap" width="10%">结束时间</td>	
          <td align="center" nowrap="nowrap" width="10%">状态</td>	
          <td align="center" nowrap="nowrap" width="10%">创建时间</td>	
        </tr>
		<c:forEach var="cur" items="${entityList}" varStatus="vs">
		        <tr bgcolor="${cur.isdel eq 0 ? '#FFFFFF':'#FFCC33' }">
		        	<td  align="center" nowrap="nowrap" >
		            <c:choose>
			            <c:when test="${cur.isdel eq 0}">
			            	<span style="cursor:pointer;color:red"  onclick="doNeedMethod('确认操作吗', 'CrmPriceHeader.do', 'delete','mod_id=${af.map.mod_id}&id=${cur.id}&dept_id=${af.map.dept_id }&isdel=1')" title="禁用">禁用</span>
			            </c:when> 
			             <c:otherwise>
			            	<span style="cursor:pointer;color:red"  onclick="doNeedMethod('确认操作', 'CrmPriceHeader.do', 'delete','mod_id=${af.map.mod_id}&id=${cur.id}&dept_id=${af.map.dept_id }&isdel=0')" title="启用">启用</span>
			            </c:otherwise>
					</c:choose>
		            <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'CrmPriceHeader.do', 'edit','mod_id=${af.map.mod_id}&id=${cur.id}&dept_id=${af.map.dept_id }')">修改</span>
		            </td>
		        	<td height="28"  align="center">${vs.count}</td>
		            <td align="center" nowrap="nowrap" style="display:none">${cur.dept_id }</td> 
		            <td align="center" nowrap="nowrap">${cur.map.dept_name }</td> 
		            <td align="center" nowrap="nowrap">${cur.price_type }</td> 
		            <td align="center" nowrap="nowrap">${cur.groupcode }</td> 
		            <td align="left" nowrap="nowrap">
		            <a href="${ctx }/manager/admin/CrmPriceHeader.do?method=view&id=${cur.id}&mod=${af.map.mod_id}" style="color:blue;text-decoration:underline;">${cur.price_code }</a>
		            </td>
		            <td align="left" nowrap="nowrap">${cur.price_name }</td>
		            <td align="right" nowrap="nowrap">${cur.map.howmany }</td>
		            <td align="center" nowrap="nowrap"><fmt:formatDate pattern="yyyy-MM-dd" value="${cur.begin_date }"></fmt:formatDate></td>
		            <td align="center" nowrap="nowrap"><fmt:formatDate pattern="yyyy-MM-dd" value="${cur.end_date }"></fmt:formatDate></td>
		            <td align="center" nowrap="nowrap">
		             <c:choose>
			            <c:when test="${cur.isdel eq 0}">
			            	有效
			            </c:when> 
			             <c:otherwise>
			            	无效
			            </c:otherwise>
					</c:choose>
		            </td>
		            <td align="center" nowrap="nowrap"><fmt:formatDate pattern="yyyy-MM-dd" value="${cur.created_date }"></fmt:formatDate></td>
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
					  pager.addHiddenInputs("price_code", "${af.map.price_code}");
					  pager.addHiddenInputs("price_name", "${af.map.price_name}");
					  pager.addHiddenInputs("isdel", "${af.map.isdel}");
					  pager.addHiddenInputs("groupcode", "${af.map.groupcode}");
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