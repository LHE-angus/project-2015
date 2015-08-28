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
<link	href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css"	rel="stylesheet" type="text/css" />
</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
    <html-el:form action="/admin/KonkaSpActivityAddr" enctype="multipart/form-data">
      <html-el:hidden property="method" value="save" styleId="method" />
	<html-el:hidden property="mod_id" value="${af.map.mod_id}" />
	<html-el:hidden property="id" value="${af.map.id}" />
	<html-el:hidden property="queryString" styleId="queryString" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
       <tr>
       <td width="15%" class="title_item" align="right" nowrap="nowrap">
       <font color="red">*</font>预约点编码:</td>
       <td> ${af.map.addr_index}</td>
       </tr>
        <tr>
      	<td class="title_item" align="right" nowrap="nowrap">预约点地址: </td> 
      <td>${af.map.addr}</td>
       </tr>
       
       <tr>
			<td class="title_item" align="right" nowrap="nowrap"><font
				color="red">*</font>所属客户：</td>
			<td align="left" width="80%" colspan="3">
			${af.map.r3_code} &nbsp;&nbsp;${af.map.customer_name }
			</td>
		</tr>
     <tr>
			<td class="title_item" align="right" nowrap="nowrap"><font
				color="red">*</font>所属门店：</td>
			<td align="left" width="80%" colspan="3">
			${af.map.store_name}
			</td>
		</tr> 
      <tr>
      <td class="title_item" align="right" nowrap="nowrap">创建人: </td> 
        <td>${af.map.add_user_name}</td> 
       </tr>
       <tr>
      <td class="title_item" align="right" nowrap="nowrap">创建时间: </td> 
        <td><fmt:formatDate value="${af.map.add_date}" pattern="yyyy-mm-dd"></fmt:formatDate></td> 
       </tr>
       <tr>
      	<td class="title_item" align="right" nowrap="nowrap">预约点负责人: </td> 
        <td>${af.map.addr_header }</td> 
       </tr>
     
        
       
       <tr>
      	<td class="title_item" align="right" nowrap="nowrap">预约点描述: </td> 
        <td><html-el:textarea property="addr_memo" styleId="addr_memo"  rows="3" style="width:50%;">
        </html-el:textarea> </td> 
       </tr>
       
       <tr>
      	<td class="title_item" align="right" nowrap="nowrap">预约点状态: </td> 
        <td>&nbsp;&nbsp;&nbsp;&nbsp;<html-el:radio property="state" value="0" />开启
            &nbsp;&nbsp;&nbsp;&nbsp;<html-el:radio property="state" value="1" />关闭
         </td> 
       </tr> 
       
			          <c:if test="${not empty attachmentList}">
			          <tr>
			            <td height="28" class="title_item" align="right">已上传的附件：</td>
			            <td colspan="3"><ol>
			                <c:forEach var="cur" items="${attachmentList}" varStatus="vs">
			                  <li><a href="${ctx}/manager/admin/Download.do?method=downloadFile1&save_name=${cur.save_name}" target="_blank">${cur.file_name}</a>&nbsp;&nbsp;&nbsp;<a href="#" id="att_del_${cur.id}">删除</a></li>
			                </c:forEach>
			              </ol></td>
			            </tr>
			           </c:if>
			          
        <tr>
          <td>&nbsp;&nbsp;</td>
          <td>
            <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back()" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="clear"></div>
</div>
<!-- ****** Main Frame End ****** -->
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/commons.plugin.jxc.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.jgrowl.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/jquery-ui/external/bgiframe/jquery.bgiframe.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/lhgdialog/lhgdialog.min.js?skin=idialog"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript">//<![CDATA[

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
