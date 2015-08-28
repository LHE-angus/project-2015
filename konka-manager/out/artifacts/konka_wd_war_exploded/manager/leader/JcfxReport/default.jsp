<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
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
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align: middle;" /></td>
        <td>当前位置：${naviString}决策分析</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    
  </div>
  <div class="rtabcont1"> 
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
      <tr>
        <td><a href="${ctx}/manager/admin/JcfxReportLswc.do">多媒体零售完成</a></td>
      </tr>
      <tr>
        <td><a href="${ctx}/manager/admin/JcfxReportLswcFgs.do">分公司零售完成</a></td>
      </tr>
      <tr>
        <td><a href="${ctx}/manager/admin/JcfxReportXswc.do">多媒体销售完成</a></td>
      </tr>
      <tr>
        <td><a href="${ctx}/manager/admin/JcfxReportXswcFgs.do">分公司销售完成 </a></td>
      </tr>
      <tr>
        <td><a href="${ctx}/manager/admin/JcfxReportXsqs.do ">销售趋势 </a> </td>
      </tr>
      <tr>
        <td><a href="${ctx}/manager/admin/jcfxReportLscp.do ">零售产品结构分析</a> </td>
      </tr>
      <tr>
        <td><a href="${ctx}/manager/admin/JcfxKczzKh.do?method=jcfxCwbkczzlList">分渠道汇总连锁渠道周转情况</a> </td>
      </tr>
      <tr>
        <td><a href="${ctx}/manager/admin/JcfxKczzKh.do?method=jcfxQglsqdzzqkfgspmList">全国连锁渠道周转情况分公司排名汇总表</a> </td>
      </tr>
      <tr>
        <td><a href="${ctx}/manager/admin/JcfxReportKczzfx.do">库存周转分析</a> </td>
      </tr>
      
    </table>
  </div> 
  <div class="clear"></div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div> 
<jsp:include page="/__analytics.jsp" />
</body>
</html>
