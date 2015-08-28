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
<style type="text/css">
<!--
.red{ color:#F00;}
.bla{ color:#000; font-size:12px; font-weight:bold;}
.note {color:#777;margin-left:10px;}
span.required {color:#FF0000;font-weight:normal;background-color:#F4FAFF;}
-->
</style>
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

  <div class="rtabcont2">
     <div align="left">
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="datagrid">
             <tr class="oartop">
                  <td colspan="2">${af.map.shop_name} 基本信息查看</td>
                </tr>
                <tr>
                  <td height="28" width="15%" nowrap="nowrap" class="title_item">商铺名称：</td>
                  <td><c:out value="${af.map.shop_name}" /></td>
                </tr>
                <tr>
                  <td height="28" nowrap="nowrap" class="title_item">所属区域：</td>
                  <td><c:out value="${af.map.map.p_index_name}" /></td>
                </tr>
                <tr>
                  <td height="28" nowrap="nowrap" class="title_item">详细地址：</td>
                  <td><c:out value="${af.map.street_addr}" /></td>
                </tr>
                <tr>
                  <td height="28" nowrap="nowrap" class="title_item">邮政编码：</td>
                  <td><c:out value="${af.map.post_code}" /></td>
                </tr>
                <tr>
                  <td height="28" nowrap="nowrap" class="title_item">联系人：</td>
                  <td><c:out value="${af.map.link_user}" /></td>
                </tr>
                <tr>
                  <td height="28" nowrap="nowrap" class="title_item">联系电话：</td>
                  <td><c:out value="${af.map.link_phone}" /></td>
                </tr>
                <tr>
                  <td height="28" nowrap="nowrap" class="title_item">产品大类：</td>
                  <td><c:out value="${af.map.map.cls_names}" /></td>
                </tr>
                <tr>
                  <td height="28" nowrap="nowrap" class="title_item">主营品牌：</td>
                  <td><c:out value="${af.map.map.brand_names}" /></td>
                </tr>
          <tr>
          <td colspan="6" height="40"  align="center">
                <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();return false;" />
          </td>
        </tr>
      </table>
    </div>
  </div>
  <div class="clear"></div>
</div>
<jsp:include page="/__analytics.jsp" />
</body>
</html>