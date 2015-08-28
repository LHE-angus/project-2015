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
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable4">
      <tr>
        <td width="15%" nowrap="nowrap" class="title_item" align="right">属性名称：</td>
        <td width="85%"><c:out value="${entity.prop_name}" /></td>
      </tr>
      <tr>
        <td height="28" width="15%" nowrap="nowrap" class="title_item" align="right">产品类别：</td>
        <td width="85%"><c:out value="${cls_name}" /></td>
      </tr>
      <tr>
        <td height="28" width="15%" nowrap="nowrap" class="title_item" align="right">属性类别：</td>
        <td width="85%"><c:out value="${category_name}" /></td>
      </tr>
      <tr>
        <td height="28" width="15%" nowrap="nowrap" class="title_item" align="right">单位：</td>
        <td width="85%"><c:out value="${entity.prop_unit}" /></td>
      </tr>
      <tr>
        <td height="28" width="15%" nowrap="nowrap" class="title_item" align="right">是否必填：</td>
        <td width="85%"><c:choose>
            <c:when test="${entity.is_required eq 1}">是</c:when>
            <c:when test="${entity.is_required eq 0}">否</c:when>
          </c:choose></td>
      </tr>
      <tr>
        <td height="28" width="15%" nowrap="nowrap" class="title_item" align="right">属性类型：</td>
        <td width="85%"><c:choose>
            <c:when test="${entity.prop_type eq 0}">输入</c:when>
            <c:when test="${entity.prop_type eq 1}">单选</c:when>
            <c:when test="${entity.prop_type eq 2}">多选</c:when>
          </c:choose></td>
      </tr>
      <tr>
        <td height="28" width="15%" nowrap="nowrap" class="title_item" align="right">属性值类型：</td>
        <td width="85%"><c:choose>
            <c:when test="${entity.prop_val_type eq 0}">数字</c:when>
            <c:when test="${entity.prop_val_type eq 1}">文本</c:when>
            <c:when test="${entity.prop_val_type eq 2}">日期</c:when>
          </c:choose></td>
      </tr>
      <tr>
        <td height="28" width="15%" nowrap="nowrap" class="title_item" align="right">属性值范围最小值：</td>
        <td width="85%"><c:out value="${entity.prop_val_min}" /></td>
      </tr>
      <tr>
        <td height="28" width="15%" nowrap="nowrap" class="title_item" align="right">属性值范围最大值：</td>
        <td width="85%"><c:out value="${entity.prop_val_max}" /></td>
      </tr>
      <tr>
        <td height="28" width="15%" nowrap="nowrap" class="title_item" align="right">备注：</td>
        <td width="85%"><c:out value="${entity.memo}" /></td>
      </tr>
      <tr>
        <td height="28" width="15%" nowrap="nowrap" class="title_item" align="right">添加时间：</td>
        <td width="85%"><fmt:formatDate value="${entity.add_date}" pattern="yyyy-MM-dd" /></td>
      </tr>
      <tr>
        <td height="28" width="15%" nowrap="nowrap" class="title_item" align="right">排序值：</td>
        <td width="85%"><c:out value="${entity.order_value}" /></td>
      </tr>
      <tr>
        <td width="15%">&nbsp;&nbsp;</td>
        <td width="85%"><input class="but5" type="button" name="Submit5" value="返回" onclick="history.back()" /></td>
      </tr>
    </table>
  </div>
</div>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>
