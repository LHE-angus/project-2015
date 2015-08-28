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
<link href="${ctx}/commons/styles/message/message.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/customer/multiselect/jquery.multiselect2side.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="oarcont">
  <div class="oartop">
  </div>
  <div class="rtabcont2">
    <%@ include file="/commons/pages/messages.jsp" %>
     <input class="but5" type="button" value="返回" onclick="history.go(-1);" />
  </div>
  <div class="rtabcont2">
    <html-el:form action="admin/CrmCustomerGroupForVip" method="post">
      <html-el:hidden property="method" value="save" styleId="method"  />
      <html-el:hidden property="id" styleId="id" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" styleId="mod_id" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
      	<tr>
          <td nowrap="nowrap" height="28" class="title_item" align="right" width="15%"><span style="color: red;">*</span>群组编码：</td>
          <td >${af.map.groupcode }</td>
        </tr>
        <tr>
           <td nowrap="nowrap" height="28" class="title_item" align="right" width="15%"><span style="color: red;">*</span>群组名称：</td>
            <td >${af.map.groupname }</td>
        </tr>
         <tr>
           <td nowrap="nowrap" height="28" class="title_item" align="right" width="15%"><span style="color: red;">*</span>所属部门：</td>
           <td width="85%">
	          ${af.map.map.dept_name }
           </td>
         </tr>
       	<tr>
       	<td nowrap="nowrap" height="28" class="title_item" align="right">备注:</td>
       	<TD >
       		${af.map.remark }
       	</TD>
       	</tr>

       	<tr>
       		<td colspan="2">
	       		<div class="rtabcont1">
				    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2" id="ctmtab">
				        <tr class="tabtt1">
 							<td align="center" nowrap="nowrap" width="10%">SN</td>
				          <td align="center" nowrap="nowrap" width="10%" style="display:none">客户ID</td>
				          <td align="center" nowrap="nowrap" width="10%">客户编码</td>
				          <td align="center" nowrap="nowrap" width="10%">客户名称</td>
				          <td align="center" nowrap="nowrap" width="10%">客户所在部门编码</td>
				          <td align="center" nowrap="nowrap" width="10%">客户所在部门名称</td>
				        </tr>
						<c:forEach var="cur" items="${ctmList}" varStatus="vs">
						        <tr>
						            <td align="center" nowrap="nowrap" >${vs.count }</td>
						            <td align="center" nowrap="nowrap" style="display:none">${cur.id }</td>
						            <td align="center" nowrap="nowrap">${cur.map.customercode }</td>
						            <td align="center" nowrap="nowrap">${cur.customername }</td>
						            <td align="center" nowrap="nowrap">${cur.map.deptcode }</td>
						            <td align="center" nowrap="nowrap">${cur.map.deptname }</td>
						        </tr>
						</c:forEach>
				      </table>
				</div>
       		</td>
       	</tr>
      </table>
    </html-el:form>
    <div>
		<form id="bottomPageForm" name="bottomPageForm" method="post" action="?">
	     	<html-el:hidden property="method" value="view" />
		     <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		       <tr>
		         <td height="40" align="center"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
		           <script type="text/javascript">
					  var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
						  pager.addHiddenInputs("id", "${af.map.id}");
						  pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
						  pager.addHiddenInputs("deptid", "${af.map.deptid}");
						  document.write(pager.toString());
					</script>
				</td>
		       </tr>
		     </table> 
      </form>
    </div>
  </div>
  <div class="clear"></div>
</div>
<!-- ****** Main Frame End ****** -->
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar/WdatePicker.js"></script>
<script type="text/javascript">//<![CDATA[
    $(document).ready(function(){
	});

//]]></script>
</body>
</html>
