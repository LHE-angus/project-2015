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
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div>
  <h3 align="center" ><strong class="fb">存品详情</strong></h3>
  </div>
    <div class="rtabcont2">
    <html-el:form action="/admin/KonkaMobileCollectionForJB" enctype="multipart/form-data">
     <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
        <tr>
          <td nowrap="nowrap" class="title_item" align="right" width="147">存品型号：</td>
          <td>${af.map.coll_name}</td>
        </tr>
        
        <tr>
          <td nowrap="nowrap" class="title_item" align="right" width="147">所在仓库：</td>
          <td>${af.map.map.storage_name}</td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right" width="147">所在仓位：</td>
          <td>${af.map.map.storage_area_name}</td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right" width="147">初始库存：</td>
          <td>${af.map.base_num}</td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right" width="147">业务数：</td>
          <td>${af.map.busi_num}</td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right" width="147">备注：</td>
          <td>${af.map.remark}</td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right" width="147">是否锁定：</td>
          <td><c:if test="${af.map.status eq 0}">正常</c:if>
              <c:if test="${af.map.status eq 1}">锁定</c:if></td>
        </tr>
        </table>
    </html-el:form>
    <div>
     <br />
        <label >
            <input class="but5" type="button"  value="返回" onclick="history.back();return false;" />
          </label>
  </div>
  </div>
<div class="rtabcont3"></div>
<div class="clear"></div>
</div>
<script type="text/javascript">//<![CDATA[
	$(document).ready(function(){
		//
	});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
