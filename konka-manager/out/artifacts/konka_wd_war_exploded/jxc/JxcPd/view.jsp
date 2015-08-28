<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>增加产品</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer/jquery-ui.custom.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/tip/jquery.qtip.css" rel="stylesheet" type="text/css" />
<style type="text/css"></style>
</head><body>
<div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;" /> 当前位置：库存管理 &gt; 产品管理</div>
<div class="rtabcont1">
  <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
     <tr>
        <th colspan="2" height="56">产品信息
         </th>
      </tr>
    <tr>
      <td width="15%" align="right" nowrap="nowrap"  class="title_item">所属系统：</td>
      <td width="85%" align="left">
        <c:if test="${ af.map.own_sys eq 0}">非家电下乡</c:if>
        <c:if test="${ af.map.own_sys eq 1}">家电下乡</c:if>
       </td>
    </tr>
    <tr>
      <td width="15%" align="right"  nowrap="nowrap"  class="title_item">产品类型：</td>
      <td width="85%" align="left">${af.map.pd_type_name }</td>
    </tr>
    <tr>
      <td align="right"  class="title_item">产品品牌：</td>
      <td align="left">${af.map.brand_name}</td>
    </tr>
    <tr id="jdxx">
      <td align="right" class="title_item">产品型号：</td>
      <td align="left">${af.map.name }</td>
    </tr>
    <tr>
      <td align="right"  class="title_item">期初库存：</td>
      <td align="left">${af.map.init_count }</td>
    </tr>
    <tr>
      <td align="right"  class="title_item">当前库存：</td>
      <td align="left">${af.map.count }</td>
    </tr>
    <tr>
      <td align="right" class="title_item">计量单位：</td>
      <td align="left">${af.map.unit }</td>
    </tr>
    <tr>
      <td align="right"  class="title_item">参考进货价：</td>
      <td align="left">${af.map.ref_price }</td>
    </tr>
    <tr>
      <td align="right" class="title_item">零售价：</td>
      <td align="left">${af.map.price }</td>
    </tr>
    <tr>
      <td align="right"  class="title_item">批发价：</td>
      <td align="left">${af.map.pf_price }</td>
    </tr>
    <tr>
      <td align="right" class="title_item">备注说明：</td>
      <td align="left">${af.map.remarks }</td>
    </tr>
    <tr>
      <td colspan="2" align="center"><div>
          <input type="button" name="return" id="btn_back" class="bgButtonBack" value=" 返 回 " onclick="history.back();" />
        </div></td>
    </tr>
  </table>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript">//<![CDATA[
//]]></script>
<jsp:include page="../public_page.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>