<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>康佳渠道管理系统 - 家电行业电子商务平台</title>
<meta name="Keywords" content="网点地图,商铺地图,维修网点地图,销售网点地图,买卖提家电网,电视,空调,热水器,小家电,冰箱,洗衣机,电脑,手机,新闻,数据报告,评测,导购,售后,投诉,家电维修" />
<meta name="Description" content="网点地图,商铺地图,维修网点地图,销售网点地图" />
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td nowrap="nowrap">当前位置：${naviString} &nbsp;${p_name}</td>
        <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align:middle;" /> <span id="span_help" style="cursor:pointer;">帮助</span></td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();return false;" />
  </div>
  <div class="rtabcont1">
    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-bottom: 18px;">
      <tr>
        <td colspan="2" height="44" align="center" class="f142" style="border: 1px solid #D6D6D6; background: #F5F5F5;"> 所有黑电经销网点
          <fmt:formatNumber value="${map_all.count_all}" pattern="0" />
          个，
          其中R3网点
          <fmt:formatNumber value="${map_all.count_r3}" pattern="0" />
          个，代理商
          <fmt:formatNumber value="${map_all.count_dls}" pattern="0" />
          个，经销商
          <fmt:formatNumber value="${map_all.count_jxs}" pattern="0" />
          个<br/>
    </td>
      </tr>
      <tr>
        <td style="border: 1px solid #D6D6D6; "><c:set var="a" value="province/_${af.map.p_index}.jsp" />
          <jsp:include page="${a}" flush="true">
			<jsp:param name="action_url" value="KonkaShopMapFight.do" />
			<jsp:param name="params" value="method=city&mod_id=${af.map.mod_id}" />
			<jsp:param name="width" value="600" />
			<jsp:param name="height" value="864" />
		  </jsp:include></td>
        <td width="238" style="border: 1px solid #D6D6D6; "><img src="${ctx}/flash/images/fenxi.png"></img></td>
      </tr>
    </table>
  </div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>