<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${naviString}</title>
<style>
.oarcont {float:left;width:100%;height:100%;background:#fff;font-size: 12px;}
.rtabcont1 {margin:8px 7px;clear:both;}

.rtable6 {border-top:1px #ccc solid;border-left:1px #ccc solid;}
.rtable6 td {border-right:1px #A1A1A1 solid;border-bottom:1px #A1A1A1 solid;padding:5px 5px 0px 5px;}
.rtable6 .tabtt6 {height:23px;background:#ED7676;}
.rtable6 .tabtt6 td {border-right:1px #e3e3e3 solid;border-bottom:1px #C00 solid;padding:5px 5px 0px 5px;;font:bold 12px "宋体";color:#FFFFFF;}
</style>
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <div class="rtabcont1" 	>
    <table class="rtable6" width="100%" cellspacing="1" cellpadding="0" border="0">
      <tr class="tabtt6">
        <td width="7%" nowrap="nowrap" align="center">铺底P仓</td>
        <td width="8%" align="center">周转90仓</td>
        <td width="7%" nowrap="nowrap" align="center">商务仓</td>
        <td width="8%" nowrap="nowrap" align="center">经营部F仓</td>
        
        <td width="7%" nowrap="nowrap" align="center">样机Y仓</td>
        <td width="7%" nowrap="nowrap" align="center">质检Q仓</td>
        <td width="8%" nowrap="nowrap" align="center">电子商务仓</td>
        <td width="7%" nowrap="nowrap" align="center">在途</td>
        <td width="7%" nowrap="nowrap" align="center">未发</td>
        
        <td width="8%" nowrap="nowrap" align="center">总量</td>
        <td width="8%" nowrap="nowrap" align="center">上月销量</td>
        <td width="8%" nowrap="nowrap" align="center">本月销量</td>
        <td nowrap="nowrap" align="center">周转率</td>
      </tr>
      <tr>
      	<td align="right">${p_count}</td>
      	<td align="right">${z_90_count}</td>
      	<td align="right">${sw_count}</td>
      	<td align="right">${f_count}</td>
      	
      	<td align="right">${y_count}</td>
      	<td align="right">${q_count}</td>
      	<td align="right">${dzsw_count}</td>
      	<td align="right">${zt_count}</td>
      	<td align="right">${wf_count}</td>
      	<td align="right">${zl_count}</td>
      	<td align="right">${sy_count}</td>
      	<td align="right">${by_count}</td>
      	<td align="right">${zzl}</td>
      </tr>
    </table>
  </div>
</div>
<script type="text/javascript">//<![CDATA[ 	                                    
//]]></script>
</body>
</html>