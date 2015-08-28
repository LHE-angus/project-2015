<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="500" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" alt="" style="vertical-align:middle;" /></td>
        <td>当前位置：基础信息管理&nbsp; &gt;&nbsp; 组织架构管理&nbsp; &gt;&nbsp;组织架构&nbsp; &gt;&nbsp;部门客户</td>
      </tr>
    </table>
  </div>
   <div>
  <h3 align="center" ><strong class="fb">客户信息查看</strong></h3>
  </div>
    <div class="rtabcont2">
     <table width="80%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
     <tr>
          <td height="28" nowrap="nowrap" class="title_item" align="right" width="20%">R3客户名称：</td>
          <td><c:out value="${entity.customer_name}"/></td>
        </tr>
		<tr>
          <td height="28" nowrap="nowrap" class="title_item" align="right">所在区域名称：</td>
          <td><c:out value="${entity.area_name}"/></td>
        </tr>	
		<tr>
          <td height="28" nowrap="nowrap" class="title_item" align="right">分公司所在地名称：</td>
          <td><c:out value="${entity.branch_area_name}"/></td>
        </tr>	
		<tr>
          <td height="28" nowrap="nowrap" class="title_item" align="right">客户类型：</td>
          <td>${entity.customer_type}
          </td>
        </tr>	
		<tr>
          <td height="28" nowrap="nowrap" class="title_item" align="right">交易状态：</td>
          <td> <c:if test="${entity.status eq 1}">
                 <c:out value="有交易"/>
              </c:if>
              <c:if test="${entity.status eq 2}">
                 <c:out value="无交易"/>
              </c:if>
           </td>
        </tr>	
		<tr>
          <td height="28" nowrap="nowrap" class="title_item" align="right">R3编码：</td>
          <td><c:out value="${entity.r3_code}"/></td>
        </tr>	
		<tr>
          <td height="28" nowrap="nowrap" class="title_item" align="right">经办名称：</td>
          <td><c:out value="${entity.handle_name}"/></td>
        </tr>	
		<tr>
          <td height="28" nowrap="nowrap" class="title_item" align="right">合并客户编码：</td>
          <td><c:out value="${entity.customer_code}"/></td>
        </tr>	
		<tr>
          <td height="28" nowrap="nowrap" class="title_item" align="right">备注：</td>
          <td><c:out value="${entity.r3_desc}"/></td>
        </tr>	
		<tr>
          <td nowrap="nowrap" class="title_item" align="right">2010合并编码：</td>
          <td><c:out value="${entity.merge_code_2010}"/></td>
        </tr>	
		<tr>
          <td height="28" nowrap="nowrap" class="title_item" align="right">导入日期：</td>
          <td><fmt:formatDate value="${entity.import_date}" pattern="yyyy-mm-dd" /></td>
        </tr>	
		<tr>
          <td height="28" nowrap="nowrap" class="title_item" align="right">导入人姓名：</td>
          <td><c:out value="${entity.map.import_user_name}"/></td>
        </tr>		
    </table>
    <div align="center" > 
     <br />
        <label >
            <input class="but5" type="button"  value="返回" onclick="javascript:window.close();" />
          </label>
  </div>
  </div>
<div class="clear"></div>
</div>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
