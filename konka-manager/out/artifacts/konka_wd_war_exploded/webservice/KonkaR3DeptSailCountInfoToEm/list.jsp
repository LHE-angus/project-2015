<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title> 分公司调拨计划评估</title>
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
	<div class="oarcont">
		<div class="rtabcont1">
			<table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable6">
				<tr class="tabtt6">
			        <td width="30" align="center" nowrap="nowrap">序号</td>
			        <td align="center" nowrap="nowrap">片区代码</td>
			        <td align="center" nowrap="nowrap">分公司</td>
			        <td align="center" nowrap="nowrap">经营部</td>
			        
			        <td align="center" nowrap="nowrap">物料</td>
			        <td align="center" nowrap="nowrap">上月销量</td>
			        <td align="center" nowrap="nowrap">本月销量</td>
			        <td align="center" nowrap="nowrap">总量</td>
			        
			        <td align="center" nowrap="nowrap">在途数量</td>
			        <td align="center" nowrap="nowrap">未发数量</td>
			        <td align="center" nowrap="nowrap">铺底60仓</td>
			        <td align="center" nowrap="nowrap">周转90仓</td>
			        
			        <td align="center" nowrap="nowrap">经营部F仓</td>
			        <td align="center" nowrap="nowrap">样机Y仓</td>
			        <td align="center" nowrap="nowrap">质检Q仓</td>
			        <td align="center" nowrap="nowrap">退机T仓</td>
			        
			        <td align="center" nowrap="nowrap">铺底P仓</td>
			        <td align="center" nowrap="nowrap">周转Z仓</td>
			        <td align="center" nowrap="nowrap">商务B仓</td>
			        <td align="center" nowrap="nowrap">电子商务仓</td>
					<td align="center" nowrap="nowrap">周转率</td>
				</tr>
				  <c:forEach var="cur" items="${list}" varStatus="vs" >
				    <tr>
				      <td>${vs.count}</td><td>${cur.BZIRK}</td><td>${cur.CLASS}</td><td>${cur.CLASS2}</td><td>${cur.MATNR}</td><td>${cur.LFIMG}</td><td>${cur.LFIMG1}</td><td>${cur.SUM}</td><td>${cur.MZT}</td><td>${cur.MWF}</td><td>${cur.PDLABST}</td><td>${cur.ZZLABST}</td><td>${cur.JYLABST}</td><td>${cur.YJLABST}</td><td>${cur.CLLABST }</td><td>${cur.TJLABST}</td><td>${cur.PCLABST}</td><td>${cur.ZCLABST}</td><td>${cur.BCLABST}</td><td>${cur.DZLABST}</td><td>${cur.map.zzl}</td>
				    </tr>
				  </c:forEach>
			</table>
		</div>
</div>
</body>
</html>