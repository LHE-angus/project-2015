<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1"/>
<meta http-equiv="Expires" content="0" />
<meta http-equiv="Cache-Control" content="no-cache" />
<meta http-equiv="Pragma" content="no-cache" />
<title>预约点管理</title>
<link rel="stylesheet" type="text/css"
	href="${ctx}/mobile/css/common.css" />
</head>
<body>
<div class="oarcont">
  <div class="rtabcont2">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtab2">
       <tr>
       <td class="title_item" align="right" nowrap="nowrap"> 客户名称:</td>
       <td> <c:out value="${af.map.map.customer_name}" /></td>
       </tr>
        <tr>
      	<td class="title_item" align="right" nowrap="nowrap">分公司: </td> 
        <td><c:out value="${af.map.map.dept_name}" /></td>
       </tr> 
       <tr>
			<td class="title_item" align="right" nowrap="nowrap">职务：</td>
			<td >
			<c:if test="${af.map.position eq 1}">
        				付款
	            	</c:if>
	            	<c:if test="${af.map.position eq 2}">
	            		对账
	            	</c:if>
	            	<c:if test="${af.map.position eq 3}">
	            		业务
	            	</c:if>
	            	<c:if test="${af.map.position eq 4}">
	            		法人
	            	</c:if>
	            	<c:if test="${af.map.position eq 5}">
	            		售后
	            	</c:if>
	            	<c:if test="${af.map.position eq 6}">
	            		收货
	            	</c:if>
	            	<c:if test="${af.map.position eq 7}">
	            		送货
	            	</c:if>
	            	<c:if test="${af.map.position eq 8}">
	            		发票
	            	</c:if>
	            	<c:if test="${af.map.position eq 9 or empty af.map.position}">
	            		其他
	            	</c:if>
			</td>
		</tr>
       <tr>
		   <td class="title_item" align="right" nowrap="nowrap">性别：</td>
			<td >
			<c:if test="${af.map.sex eq 0}">男</c:if>
			<c:if test="${af.map.sex eq 1}">女</c:if> 
			<c:if test="${af.map.sex eq 2}">未知</c:if>
			</td>
		</tr> 
      <tr>
      <td class="title_item" align="right" nowrap="nowrap">移动电话: </td> 
        <td><c:out value="${af.map.tel}" /></td> 
       </tr>
       <tr>
      <td class="title_item" align="right" nowrap="nowrap">电子邮箱: </td> 
        <td><c:out value="${af.map.email}" /></td> 
       </tr>
       <tr>
      	<td class="title_item" align="right" nowrap="nowrap">QQ号: </td> 
        <td><c:out value="${af.map.qq}" /></td> 
       </tr>
       <tr>
      	<td class="title_item" align="right" nowrap="nowrap">是否默认: </td> 
        <td><c:if test="${af.map.is_default eq 0}">
				<c:out value="是" />
			</c:if> <c:if test="${af.map.is_default eq 1}">
				<c:out value="否" />
			</c:if></td> 
       </tr>
       <tr>
      	<td class="title_item" align="right" nowrap="nowrap">维护人: </td> 
        <td><c:out value="${af.map.map.user_name}" /></td> 
       </tr>
        <tr>
      	<td class="title_item" align="right" nowrap="nowrap">客户喜好: </td> 
        <td><c:out value="${af.map.customer_preferences}" /></td> 
       </tr>
        <tr>
      	<td class="title_item" align="right" nowrap="nowrap">R3编码: </td> 
        <td><c:out value="${af.map.map.r3_code}" /></td> 
       </tr>
        <tr>
      	<td class="title_item" align="right" nowrap="nowrap">姓名: </td> 
        <td><c:out value="${af.map.real_name}" /></td> 
       </tr>
        <tr>
      	<td class="title_item" align="right" nowrap="nowrap">岗位: </td> 
        <td><c:out value="${af.map.job}" /></td> 
       </tr>
         <tr>
      	<td class="title_item" align="right" nowrap="nowrap">生日: </td> 
        <td><fmt:formatDate value="${af.map.birthday}"
				pattern="yyyy-MM-dd" /></td> 
       </tr>
         <tr>
      	<td class="title_item" align="right" nowrap="nowrap">固定电话: </td> 
        <td><c:out value="${af.map.telephone}" /></td> 
       </tr>
        <tr>
      	<td class="title_item" align="right" nowrap="nowrap">传真: </td> 
        <td><c:out value="${af.map.fax}" /></td> 
       </tr>
        <tr>
      	<td class="title_item" align="right" nowrap="nowrap">微信号: </td> 
        <td><c:out value="${af.map.weixin}" /></td> 
       </tr>
       <tr>
      	<td class="title_item" align="right" nowrap="nowrap">是否有效: </td> 
        <td><c:if test="${af.map.is_valid eq 0}">
				<c:out value="是" />
			</c:if> <c:if test="${af.map.is_valid eq 1}">
				<c:out value="否" />
			</c:if> </td> 
       </tr>
       <tr>
      	<td class="title_item" align="right" nowrap="nowrap">维护时间: </td> 
        <td><fmt:formatDate value="${af.map.add_date}"
				pattern="yyyy-MM-dd" /></td> 
       </tr>
        <tr>
          <td>&nbsp;&nbsp;</td>
          <td>
            <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back()" /></td>
        </tr>
      </table>

  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript">//<![CDATA[

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
