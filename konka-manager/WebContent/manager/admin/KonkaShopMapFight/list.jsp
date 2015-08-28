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
        <td nowrap="nowrap">当前位置：${naviString} &nbsp;全国</td>
        <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align:middle;" /> <span id="span_help" style="cursor:pointer;">帮助</span></td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <table width="720" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:3px;" id="flash_map">
      <tr>
        <td align="center" style="border:1px solid #D6D6D6;"><img src="${ctx}/images/province/00.jpg" border="0" usemap="#Map"></td>
      </tr>
    </table>
  </div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<map name="Map" id="Map">
  <area shape="rect" coords="644,89,688,117" href="${ctx}/manager/admin/KonkaShopMapFight.do?mod_id=105030&amp;method=province&amp;p_index=230000" alt="黑龙江" />
  <area shape="rect" coords="651,145,693,172" href="${ctx}/manager/admin/KonkaShopMapFight.do?mod_id=105030&method=province&p_index=220000" alt="吉林" />
  <area shape="rect" coords="607,172,642,202" href="${ctx}/manager/admin/KonkaShopMapFight.do?mod_id=105030&method=province&p_index=210000" alt="辽宁" />
  <area shape="rect" coords="159,178,204,206" href="${ctx}/manager/admin/KonkaShopMapFight.do?mod_id=105030&method=province&p_index=990000" alt="新疆" />650000
  <area shape="rect" coords="260,259,314,294" href="${ctx}/manager/admin/KonkaShopMapFight.do?mod_id=105030&method=province&p_index=630000" alt="青海" />
  <area shape="rect" coords="193,329,244,357" href="${ctx}/manager/admin/KonkaShopMapFight.do?mod_id=105030&method=province&p_index=540000" alt="西藏" />
  <area shape="rect" coords="434,199,486,231" href="${ctx}/manager/admin/KonkaShopMapFight.do?mod_id=105030&method=province&p_index=150000" alt="内蒙古" />
  <area shape="rect" coords="360,224,403,261" href="${ctx}/manager/admin/KonkaShopMapFight.do?mod_id=105030&method=province&p_index=620000" alt="甘肃" />
  <area shape="rect" coords="409,245,452,273" href="${ctx}/manager/admin/KonkaShopMapFight.do?mod_id=105030&method=province&p_index=640000" alt="宁夏" />
  <area shape="rect" coords="476,255,514,281" href="${ctx}/manager/admin/KonkaShopMapFight.do?mod_id=105030&method=province&p_index=140000" alt="山西" />
  <area shape="rect" coords="529,251,582,279" href="${ctx}/manager/admin/KonkaShopMapFight.do?mod_id=105030&method=province&p_index=370000" alt="山东" />
  <area shape="rect" coords="522,228,549,245" href="${ctx}/manager/admin/KonkaShopMapFight.do?mod_id=105030&method=province&p_index=130000" alt="河北" />
  <area shape="rect" coords="550,217,578,234" href="${ctx}/manager/admin/KonkaShopMapFight.do?mod_id=105030&method=province&p_index=120000" alt="天津" />
  <area shape="rect" coords="526,203,556,217" href="${ctx}/manager/admin/KonkaShopMapFight.do?mod_id=105030&method=province&p_index=110000" alt="北京" />
  <area shape="rect" coords="561,291,600,318" href="${ctx}/manager/admin/KonkaShopMapFight.do?mod_id=105030&method=province&p_index=320000" alt="江苏" />
  <area shape="rect" coords="426,293,467,314" href="${ctx}/manager/admin/KonkaShopMapFight.do?mod_id=105030&method=province&p_index=610000" alt="陕西" />
  <area shape="rect" coords="494,298,528,325" href="${ctx}/manager/admin/KonkaShopMapFight.do?mod_id=105030&method=province&p_index=410000" alt="河南" />
  <area shape="rect" coords="540,317,574,336" href="${ctx}/manager/admin/KonkaShopMapFight.do?mod_id=105030&method=province&p_index=340000" alt="安徽" />
  <area shape="rect" coords="594,336,637,365" href="${ctx}/manager/admin/KonkaShopMapFight.do?mod_id=105030&method=province&p_index=310000" alt="上海" />
  <area shape="rect" coords="344,347,392,380" href="${ctx}/manager/admin/KonkaShopMapFight.do?mod_id=105030&method=province&p_index=510000" alt="四川" />
  <area shape="rect" coords="469,336,515,364" href="${ctx}/manager/admin/KonkaShopMapFight.do?mod_id=105030&method=province&p_index=420000" alt="湖北" />
  <area shape="rect" coords="414,362,461,391" href="${ctx}/manager/admin/KonkaShopMapFight.do?mod_id=105030&method=province&p_index=500000" alt="重庆" />
  <area shape="rect" coords="473,390,513,429" href="${ctx}/manager/admin/KonkaShopMapFight.do?mod_id=105030&method=province&p_index=430000" alt="湖南" />
  <area shape="rect" coords="303,427,362,464" href="${ctx}/manager/admin/KonkaShopMapFight.do?mod_id=105030&method=province&p_index=530000" alt="云南" />
  <area shape="rect" coords="405,408,446,440" href="${ctx}/manager/admin/KonkaShopMapFight.do?mod_id=105030&method=province&p_index=520000" alt="贵州" />
  <area shape="rect" coords="415,449,464,478" href="${ctx}/manager/admin/KonkaShopMapFight.do?mod_id=105030&method=province&p_index=450000" alt="广西" />
  <area shape="rect" coords="482,446,528,479" href="${ctx}/manager/admin/KonkaShopMapFight.do?mod_id=105030&method=province&p_index=440000" alt="广东" />
  <area shape="rect" coords="437,510,480,545" href="${ctx}/manager/admin/KonkaShopMapFight.do?mod_id=105030&method=province&p_index=460000" alt="海南" />
  <area shape="rect" coords="530,388,569,419" href="${ctx}/manager/admin/KonkaShopMapFight.do?mod_id=105030&method=province&p_index=360000" alt="江西" />
  <area shape="rect" coords="579,371,623,395" href="${ctx}/manager/admin/KonkaShopMapFight.do?mod_id=105030&method=province&p_index=330000" alt="浙江" />
  <area shape="rect" coords="571,416,611,440" href="${ctx}/manager/admin/KonkaShopMapFight.do?mod_id=105030&method=province&p_index=350000" alt="福建" />
</map>
<script type="text/javascript">//<![CDATA[
$(document).ready(function() {
	$("#nation_iframe").width($("#flash_map").width()+80);
	$("#nation_iframe").height(600);

});

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>