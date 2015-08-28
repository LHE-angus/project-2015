<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>进货登记</title>
<base target="_self" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/customer/jNotify/jNotify.jquery.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/customer/fancybox/fancybox.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/tip/jquery.qtip.css" rel="stylesheet" type="text/css" />
<style type="text/css">
select{font-family:Microsoft YAHEI;font-size:12px;}
input{font-family:Microsoft YAHEI;font-size:12px;}
label {cursor:pointer;}
</style>
</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="400" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
    <%@ include file="/commons/pages/messages.jsp" %>
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
        <tr>
          <td colspan="2"  bgcolor="#CCCCCC" style="font-weight:bold;">商品信息查看</td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">仓库：</td>
          <td align="left">${af.map.map.store_name }
          </td>
        </tr>
        <tr>
          <td width="17%" nowrap="nowrap" class="title_item" align="right">型号：</td>
          <td width="83%" align="left">${af.map.map.goods_name }</td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">初始化库存（单位：台）：</td>
          <td align="left">${af.map.init_count}</td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">初始化进货单价（单位：元）：</td>
          <td align="left">${af.map.buy_price}</td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">说明：</td>
          <td align="left">${af.map.init_desc}</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>
            <html-el:button property="" value="返 回" styleClass="but5" styleId="btn_back" onclick="history.back();" />
          </td>
        </tr>
      </table>
  </div>
</div>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>
