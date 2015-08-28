<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<title>网点经营情况</title>
<script type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script>
</head>
<body>
<div style="width:100%">
  <div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;" /> 当前位置：${naviString}</div>
  <div class="rtabcont1">
    <html-el:form action="/KonkaJxcShopSellStatistics">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableTop">
        <tr>
          <td width="5%" nowrap="nowrap"><strong class="fb">管辖区域:</strong></td>
          <td width="5%"><html-el:select property="name_1" styleId="name_1" style="width:80px;">
              <html-el:option value="">请选择...</html-el:option>
            </html-el:select></td>
          <td width="6%" nowrap="nowrap"><strong class="fb">网点名称：</strong></td>
          <td width="15%"><html-el:text property="name_2" styleClass="webinput" styleId="name_2" /></td>
          <td width="6%" nowrap="nowrap"><strong class="fb">R3编码：</strong></td>
          <td width="15%"><html-el:text property="name_3" styleClass="webinput" styleId="name_3" /></td>
         <td width="4%" nowrap="nowrap"><strong class="fb">时间</strong>：</td>
          <td width="6%" nowrap="nowrap"><html-el:text styleClass="webinput" property="add_date_st" styleId="add_date_st" value="${af.map.add_date_st}" readonly="readonly" onclick="new Calendar(1990, 2020, 0).show(this);" style="cursor:pointer;text-align:left;width:80px;" />
          &nbsp;至
          <html-el:text styleClass="webinput" property="add_date_en" styleId="add_date_en" value="${af.map.add_date_en}" readonly="readonly" onclick="new Calendar(1990, 2020, 0).show(this);" style="cursor:pointer;text-align:left;width:80px;" />
          </td>
          <td><html-el:submit value="搜 索" styleClass="bgSearch"/></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
  </div>
  <div class="rtabcont1">
   <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
  <tr class="title_top">
  	<td width="5%" align="center">序号</td>
    <td width="32%"  align="center">网点名称</td>
    <td width="15%" align="center">R3编码</td>
    <td width="12%" align="center">联系人</td>
    <td width="12%" align="center">电话</td>
    <td width="24%" align="center">统计报表</td>
  </tr>
  <tr>
  	<td align="center">1</td>
    <td align="center">扬州韩华家电有限公司</td>
    <td align="center">f1669yzhh</td>
    <td align="center">李闯王</td>
    <td align="center">15856945621</td>
    <td align="center"><span style="cursor:pointer;"  class="fblue"  onclick="doNeedMethod(null,'KonkaJxcShopAnalysisOfRegional.do', 'view','id=${cur.id}&'+$('#bottomPageForm').serialize())">报表</span> | <a href="#">分销情况（代理商）</a></td>
  </tr>
  <tr>
  	<td align="center">2</td>
    <td align="center">镇江八佰伴商贸有限公司</td>
    <td align="center">F166ZJXX</td>
    <td align="center">张仲傅</td>
    <td align="center">13625845862</td>
    <td align="center"><span style="cursor:pointer;"  class="fblue"  onclick="doNeedMethod(null,'KonkaJxcShopAnalysisOfRegional.do', 'view','id=${cur.id}&'+$('#bottomPageForm').serialize())">报表</span> | <a href="#">分销情况（代理商）</a></td>
  </tr>
  <tr>
  	<td align="center">3</td>
    <td align="center">海安新时代</td>
    <td align="center">F160HMHAT</td>
    <td align="center">杜月苼</td>
    <td align="center">18754689245</td>
    <td align="center"><span style="cursor:pointer;"  class="fblue"  onclick="doNeedMethod(null,'KonkaJxcShopAnalysisOfRegional.do', 'view','id=${cur.id}&'+$('#bottomPageForm').serialize())">报表</span> | <a href="#">分销情况（代理商）</a></td>
  </tr>
  <tr>
  	<td align="center">4</td>
    <td align="center">南通市尊贵家电有限公司</td>
    <td align="center">F160NTXM</td>
    <td align="center">吴刚</td>
    <td align="center">13854612548</td>
    <td align="center"><span style="cursor:pointer;"  class="fblue"  onclick="doNeedMethod(null,'KonkaJxcShopAnalysisOfRegional.do', 'view','id=${cur.id}&'+$('#bottomPageForm').serialize())">报表</span> | <a href="#">分销情况（代理商）</a></td>
  </tr>
</table>
  </div>
<div class="rtabcont1" ><span class="jxcTip"> 备注：此数据为示例数据，仅供参考！</span></div>
  <div class="rtabcont1">
<table width="600" border="0" cellpadding="0" cellspacing="0" >
  <tr>
    <td align="center"><img src="${ctx}/images/manager/wait.bmp" alt="" style="vertical-align:middle;" /></td>
  </tr>
</table>
</div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<jsp:include page="../public_page.jsp" flush="true"/>
<jsp:include page="/__analytics.jsp" />
</body>
</html>