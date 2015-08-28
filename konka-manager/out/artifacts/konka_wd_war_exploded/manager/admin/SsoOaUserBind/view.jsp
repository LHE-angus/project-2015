<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<c:if test="${!empty is_add}">
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
</c:if>
</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="500" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" alt="" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
       <tr>
          <td class="title_item" align="right">活动编号：</td>
          <td width="50%">${af.map.act_sn}</td>
          <td class="title_item" align="right">活动标题：</td>
           <td width="50%">${af.map.title}</td>
        </tr>
        <tr>
          <td class="title_item" align="right">分公司：</td>
           <td width="50%" >${af.map.dept_name}</td>
           <td class="title_item" align="right">上报人：</td>
          <td width="50%" >${af.map.add_user_name}</td>
        </tr>
        <tr>
         <td class="title_item" nowrap="nowrap" align="right">活动开始时间：</td>
          <td width="50%">
            <fmt:formatDate value="${af.map.start_date}" pattern="yyyy-MM-dd" />
            </td>
           <td class="title_item" nowrap="nowrap" align="right">活动结束时间：</td>
          <td width="50%">
          	<fmt:formatDate value="${af.map.end_date}" pattern="yyyy-MM-dd" />
           </td>
        </tr>
        <tr>
          <td height="28" nowrap="nowrap" class="title_item" align="right">客户R3编码：</td>
          <td>${af.map.r3_code}</td>
          <td nowrap="nowrap" class="title_item" align="right">客户名称：</td>
          <td>${af.map.c_name}</td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right">客户电话：</td>
          <td width="50%"> ${af.map.c_link_name} </td>
          <td class="title_item" nowrap="nowrap" align="right">活动类型：</td>
          <td width="50%">
              <c:if test="${af.map.type_id eq 30010}">县级</c:if>
              <c:if test="${af.map.type_id eq 30020}">乡镇联合</c:if>
          </td>
        </tr>
         <tr>
          <td class="title_item" nowrap="nowrap" align="right">活动目标（万元）：</td>
          <td width="50%">${af.map.target_money}</td>
          <td class="title_item" nowrap="nowrap" align="right">活动达成（万元）：</td>
          <td width="50%"> ${af.map.done_money}</td>
        </tr>
         <tr>
          <td class="title_item" nowrap="nowrap" align="right">备注：</td>
          <td colspan="3">${af.map.memo}</td>
         </tr>
         <tr>
            <td height="28" class="title_item" align="right">已上传的附件：</td>
            <td><ol>
                <c:forEach var="cur" items="${attachmentList}" varStatus="vs">
                  <li><a href="${ctx}/manager/admin/Download.do?method=downloadFile1&save_name=${cur.save_name}" target="_blank">${cur.file_name}</a></li>
                </c:forEach>
              </ol></td>
          </tr>
          <tr>
            <td height="28" class="title_item" align="right">零售数据：</td>
            <td><table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
               <tr class="tabtt1">
		           <td width="5%" nowrap="nowrap" align="center">零售机型</td>
		           <td width="5%" nowrap="nowrap" align="center">数量</td>
		           <td width="8%" nowrap="nowrap" align="center">单价</td>
		           <td width="5%" nowrap="nowrap" align="center">总金额</td>
        	   </tr>
        	  <c:forEach var="cur1" items="${entityList}" varStatus="vs">
              <tr>
	              <td align="left" nowrap="nowrap" >${cur1.model_name}</td>
	              <td align="left" nowrap="nowrap">${cur1.num}</td>
	              <td align="left" nowrap="nowrap">${cur1.single_price}</td>
	              <td align="left" nowrap="nowrap">${cur1.all_price}</td>
              </tr>
             </c:forEach> 
       		 </table>
            </td>
          </tr>
          
        <tr>
          <td>&nbsp;</td>
          <td align="center" colspan="3" ><label>
            <input class="but5" type="button"  id="btn_back" value="返回" onclick="history.back();return false;" />
            </label></td>
        </tr>
      </table>
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/pinyin.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript">//<![CDATA[


//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
