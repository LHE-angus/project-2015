<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>附件信息</title>
<link href="http://qdgl.konka.com/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="http://qdgl.konka.com/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<link href="http://qdgl.konka.com/styles/customer/jNotify/jNotify.jquery.css" rel="stylesheet" type="text/css" />
<link href="http://qdgl.konka.com/scripts/tip/jquery.qtip.css" rel="stylesheet" type="text/css" />
<link href="http://qdgl.konka.com/styles/customer/css/tab.css" rel="stylesheet" type="text/css" />
</head>
<body>
<!-- 附件 -->			    
  <div id="tab3"  style="width:1200px">
  <div align="left"><input class="bgButtonBack" type="reset" name="reset" value="返回" id="btn_back" onclick="history.back();"/></div> 
  	<c:if test="${not empty attachmentList}">
  	<table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
  		<tr>
       	<td class="title_item" style="text-align:center;font-size:15px;font-weight:bold;color:#74685F">附件</td>
       </tr>
       <tr>
       	<td align="center">
       		<div style="width:100%;padding:5px 5px 0 5px;">
       			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="navClass" style="margin:10px 0 10px 0;">
					<thead id="nav" style="width:99%;background:#abd589;">
			  			<tr>
				  			<th width="3%" nowrap="nowrap">序号</th>
				  			<th width="40%" nowrap="nowrap">附件</th>
				  			<th nowrap="nowrap">备注</th>
				  		</tr>
			  		</thead>
			  		<tbody id="fileTbody">
			  			<c:forEach items="${attachmentList}" var="cur" varStatus="vs">
			  				<tr id="picModelTr_${vs.count}">
								<td align="center">${vs.count}</td>
								<td align="left" nowrap="nowrap">
									<a href="qdgl.konka.com/${cur.save_path}">${cur.file_name}</a>
<!--										<html-el:hidden property="id_" value="${cur.id}" />-->
<!--										<html-el:hidden property="link_id_" value="${cur.link_id}" />-->
<!--										<html-el:hidden property="link_tab_" value="${cur.link_tab}" />-->
<!--										<html-el:hidden property="file_name_" value="${cur.file_name}" />-->
<!--										<html-el:hidden property="file_type_" value="${cur.file_type}" />-->
<!--										<html-el:hidden property="file_size_" value="${cur.file_size}" />-->
<!--										<html-el:hidden property="save_path_" value="${cur.save_path}" />-->
<!--										<html-el:hidden property="save_name_" value="${cur.save_name}" />-->
<!--										<html-el:hidden property="del_mark_" value="${cur.del_mark}" />-->
								</td>
								<td align="left">${empty cur.file_desc?'未填写':cur.file_desc}</td>
						   </tr>
			  			</c:forEach>
			  		</tbody>
			  	</table>
       		</div>
       	</td>
       </tr>
  	</table>
 	</c:if>
 	<c:if test="${empty attachmentList}">
 		<div style="color:#F00;text-align:center;">没有附件</div>
 	</c:if>
  </div>
</body>
</html>