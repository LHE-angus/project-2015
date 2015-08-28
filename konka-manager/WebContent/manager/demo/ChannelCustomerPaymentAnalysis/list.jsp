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
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
        <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align:middle;" /> <span id="span_help" style="cursor:pointer;">帮助</span></td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/admin/ChannelCustomerPaymentAnalysis">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="mod_code" value="${af.map.mod_code}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="15"></td>
          <td><strong class="fb">类别：</strong>
                <label>
                  <input name="search_type" id="search_type1" type="radio" value="1" checked="checked" />
                  单客户</label>
                <label>
                  <input name="search_type" id="search_type2" type="radio" value="2" />
                  客户群</label>
                <label>
                  <input name="search_type" id="search_type3" type="radio" value="3" />
                  分公司</label>
                <label>
                  <input name="search_type" id="search_type4" type="radio" value="4" />
                  分大区</label></td>
           <td><span id="span_1"><strong class="fb">单客户：</strong>
                <html-el:text property="key_word" size="20" maxlength="20" styleId="key_word"  />
                </span></td>
           <td width="15"></td>
           <td width="15"></td>
        </tr>
        <tr>
          <td width="15"></td>
          <td><strong class="fb">时间：</strong>
                <label>
                  <input name="search_date" id="search_date1" type="radio" value="1" checked="checked" />
                  每天</label>
                <label>
                  <input name="search_date" id="search_date2" type="radio" value="2" />
                  每月</label></td>
          <td><strong class="fb">范围　：</strong>
                <html-el:text property="key_word" size="10" maxlength="20" styleId="key_word"  />
                至
                <html-el:text property="key_word" size="10" maxlength="20" styleId="key_word"  /></td>
          <td><html-el:submit styleClass="but1" value="搜索" /></td>
           <td><html-el:submit styleClass="but1" value="导出" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
  </div>
  <div class="rtabcont1">
    <div id="div_1">
      <html-el:form action="/admin/ChannelCustomerPaymentAnalysis" method="post">
        <html-el:hidden property="method" value="edit" />
        <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
        <html-el:hidden property="tree_param" value="${tree_param}" />
        <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
          <tr class="tabtt1">
            <td width="40" align="center">序号</td>
            <td nowrap="nowrap">名称</td>
            <td width="60" align="center"><span style="cursor:pointer;" onclick="openChartDialog();" title="点击查看图表"><img src="${ctx}/images/chart-bar-up.png" border="0" width="20" />当月回款任务</span></td>
            <td width="60" align="center"><span style="cursor:pointer;" onclick="openChartDialog();" title="点击查看图表"><img src="${ctx}/images/chart-bar-up.png" border="0" width="20" />当月回款额</span></td>
            <td width="80" align="center"><span style="cursor:pointer;" onclick="openChartDialog();" title="点击查看图表"><img src="${ctx}/images/chart-bar-up.png" border="0" width="20" />当月回款完成率</span></td>
            <td align="center"><span style="cursor:pointer;" onclick="openChartDialog();" title="点击查看图表"><img src="${ctx}/images/chart-bar-up.png" border="0" width="20" />累计回款任务</span></td>
            <td align="center"><span style="cursor:pointer;" onclick="openChartDialog();" title="点击查看图表"><img src="${ctx}/images/chart-bar-up.png" border="0" width="20" />累计回款额</span></td>
            <td align="center"><span style="cursor:pointer;" onclick="openChartDialog();" title="点击查看图表"><img src="${ctx}/images/chart-bar-up.png" border="0" width="20" />累计回款完成率</span></td>
            <td align="center"><span style="cursor:pointer;" onclick="openChartDialog();" title="点击查看图表"><img src="${ctx}/images/chart-bar-up.png" border="0" width="20" />支年同期任务</span></td>
            <td align="center"><span style="cursor:pointer;" onclick="openChartDialog();" title="点击查看图表"><img src="${ctx}/images/chart-bar-up.png" border="0" width="20" />去年同期回款</span></td>
            <td align="center"><span style="cursor:pointer;" onclick="openChartDialog();" title="点击查看图表"><img src="${ctx}/images/chart-bar-up.png" border="0" width="20" />同比去年同期增长率</span></td>
            <td align="center"><span style="cursor:pointer;" onclick="openChartDialog();" title="点击查看图表"><img src="${ctx}/images/chart-bar-up.png" border="0" width="20" />去年同期任务累计</span></td>
            <td align="center"><span style="cursor:pointer;" onclick="openChartDialog();" title="点击查看图表"><img src="${ctx}/images/chart-bar-up.png" border="0" width="20" />去年同期累计回款</span></td>
            <td align="center"><span style="cursor:pointer;" onclick="openChartDialog();" title="点击查看图表"><img src="${ctx}/images/chart-bar-up.png" border="0" width="20" />同比去年累计增长率</span></td>
          </tr>
          <tr>
            <td align="center" nowrap="nowrap">1</td>
            <td nowrap="nowrap">扬州韩华家电有限公司</td>
            <td align="right" nowrap="nowrap">&nbsp;</td>
            <td align="right" nowrap="nowrap">&nbsp;</td>
            <td align="right" nowrap="nowrap">&nbsp;</td>
            <td align="right" nowrap="nowrap">&nbsp;</td>
            <td align="right" nowrap="nowrap">&nbsp;</td>
            <td align="right" nowrap="nowrap">&nbsp;</td>
            <td align="right" nowrap="nowrap">&nbsp;</td>
            <td align="right" nowrap="nowrap">&nbsp;</td>
            <td align="right" nowrap="nowrap">&nbsp;</td>
            <td align="right" nowrap="nowrap">&nbsp;</td>
            <td align="right" nowrap="nowrap">&nbsp;</td>
            <td align="right" nowrap="nowrap">&nbsp;</td>
          </tr>
          <tr>
            <td align="center" nowrap="nowrap">2</td>
            <td nowrap="nowrap">扬州佳恩电器有限公司</td>
            <td align="right" nowrap="nowrap">&nbsp;</td>
            <td align="right" nowrap="nowrap">&nbsp;</td>
            <td align="right" nowrap="nowrap">&nbsp;</td>
            <td align="right" nowrap="nowrap">&nbsp;</td>
            <td align="right" nowrap="nowrap">&nbsp;</td>
            <td align="right" nowrap="nowrap">&nbsp;</td>
            <td align="right" nowrap="nowrap">&nbsp;</td>
            <td align="right" nowrap="nowrap">&nbsp;</td>
            <td align="right" nowrap="nowrap">&nbsp;</td>
            <td align="right" nowrap="nowrap">&nbsp;</td>
            <td align="right" nowrap="nowrap">&nbsp;</td>
            <td align="right" nowrap="nowrap">&nbsp;</td>
          </tr>
          <tr>
            <td align="center" nowrap="nowrap">3</td>
            <td nowrap="nowrap">扬州市名品家电有限公司</td>
            <td align="right" nowrap="nowrap">&nbsp;</td>
            <td align="right" nowrap="nowrap">&nbsp;</td>
            <td align="right" nowrap="nowrap">&nbsp;</td>
            <td align="right" nowrap="nowrap">&nbsp;</td>
            <td align="right" nowrap="nowrap">&nbsp;</td>
            <td align="right" nowrap="nowrap">&nbsp;</td>
            <td align="right" nowrap="nowrap">&nbsp;</td>
            <td align="right" nowrap="nowrap">&nbsp;</td>
            <td align="right" nowrap="nowrap">&nbsp;</td>
            <td align="right" nowrap="nowrap">&nbsp;</td>
            <td align="right" nowrap="nowrap">&nbsp;</td>
            <td align="right" nowrap="nowrap">&nbsp;</td>
          </tr>
          <tr>
            <td align="center" nowrap="nowrap">&nbsp;</td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
          </tr>
          <tr>
            <td align="center" nowrap="nowrap">&nbsp;</td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
          </tr>
          <tr>
            <td align="center" nowrap="nowrap">&nbsp;</td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
          </tr>
          <tr>
            <td align="center" nowrap="nowrap">&nbsp;</td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
          </tr>
          <tr>
            <td align="center" nowrap="nowrap">&nbsp;</td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
          </tr>
          <tr>
            <td align="center" nowrap="nowrap">&nbsp;</td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
          </tr>
          <tr>
            <td align="center" nowrap="nowrap">&nbsp;</td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
          </tr>
          <tr>
            <td align="center" nowrap="nowrap">&nbsp;</td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
            <td nowrap="nowrap"></td>
          </tr>
        </table>
      </html-el:form>
      <br />
      <form id="bottomPageForm" name="bottomPageForm" method="post" action="ChannelCustomerPaymentAnalysis.do">
        <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
              <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
            document.write(pager.toString());
            </script></td>
          </tr>
        </table>
      </form>
    </div>
    <div id="div_2">
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
        <tr class="tabtt1">
          <td width="40" align="center">序号</td>
          <td nowrap="nowrap">名称</td>
          <td width="60" align="center"><span style="cursor:pointer;" onclick="openChartDialog();" title="点击查看图表"><img src="${ctx}/images/chart-bar-up.png" border="0" width="20" />当月回款任务</span></td>
          <td width="60" align="center"><span style="cursor:pointer;" onclick="openChartDialog();" title="点击查看图表"><img src="${ctx}/images/chart-bar-up.png" border="0" width="20" />当月回款额</span></td>
          <td width="80" align="center"><span style="cursor:pointer;" onclick="openChartDialog();" title="点击查看图表"><img src="${ctx}/images/chart-bar-up.png" border="0" width="20" />当月回款完成率</span></td>
          <td align="center"><span style="cursor:pointer;" onclick="openChartDialog();" title="点击查看图表"><img src="${ctx}/images/chart-bar-up.png" border="0" width="20" />累计回款任务</span></td>
          <td align="center"><span style="cursor:pointer;" onclick="openChartDialog();" title="点击查看图表"><img src="${ctx}/images/chart-bar-up.png" border="0" width="20" />累计回款额</span></td>
          <td align="center"><span style="cursor:pointer;" onclick="openChartDialog();" title="点击查看图表"><img src="${ctx}/images/chart-bar-up.png" border="0" width="20" />累计回款完成率</span></td>
          <td align="center"><span style="cursor:pointer;" onclick="openChartDialog();" title="点击查看图表"><img src="${ctx}/images/chart-bar-up.png" border="0" width="20" />支年同期任务</span></td>
          <td align="center"><span style="cursor:pointer;" onclick="openChartDialog();" title="点击查看图表"><img src="${ctx}/images/chart-bar-up.png" border="0" width="20" />去年同期回款</span></td>
          <td align="center"><span style="cursor:pointer;" onclick="openChartDialog();" title="点击查看图表"><img src="${ctx}/images/chart-bar-up.png" border="0" width="20" />去年同期增长率</span></td>
          <td align="center"><span style="cursor:pointer;" onclick="openChartDialog();" title="点击查看图表"><img src="${ctx}/images/chart-bar-up.png" border="0" width="20" />去年同期任务累计</span></td>
          <td align="center"><span style="cursor:pointer;" onclick="openChartDialog();" title="点击查看图表"><img src="${ctx}/images/chart-bar-up.png" border="0" width="20" />去年同期回款累计</span></td>
          <td align="center"><span style="cursor:pointer;" onclick="openChartDialog();" title="点击查看图表"><img src="${ctx}/images/chart-bar-up.png" border="0" width="20" />去年同期回款累计增长率</span></td>
        </tr>
        <tr>
          <td align="center" nowrap="nowrap">1</td>
          <td nowrap="nowrap">乡镇客户</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
        </tr>
        <tr>
          <td align="center" nowrap="nowrap">2</td>
          <td nowrap="nowrap">县级客户</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
        </tr>
        <tr>
          <td align="center" nowrap="nowrap">3</td>
          <td nowrap="nowrap">核心500</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
        </tr>
        <tr>
          <td align="center" nowrap="nowrap">4</td>
          <td nowrap="nowrap">白金100</td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
        </tr>
        <tr>
          <td align="center" nowrap="nowrap">&nbsp;</td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
        </tr>
        <tr>
          <td align="center" nowrap="nowrap">&nbsp;</td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
        </tr>
        <tr>
          <td align="center" nowrap="nowrap">&nbsp;</td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
        </tr>
        <tr>
          <td align="center" nowrap="nowrap">&nbsp;</td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
        </tr>
        <tr>
          <td align="center" nowrap="nowrap">&nbsp;</td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
        </tr>
        <tr>
          <td align="center" nowrap="nowrap">&nbsp;</td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
        </tr>
        <tr>
          <td align="center" nowrap="nowrap">&nbsp;</td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap"></td>
        </tr>
      </table>
    </div>
    <div id="div_3">
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
        <tr class="tabtt1">
          <td width="40" align="center">序号</td>
          <td nowrap="nowrap">名称</td>
          <td align="center"><span style="cursor:pointer;" onclick="openChartDialog();" title="点击查看图表"><img src="${ctx}/images/chart-bar-up.png" border="0" width="20" />当月回款任务</span></td>
          <td align="center"><span style="cursor:pointer;" onclick="openChartDialog();" title="点击查看图表"><img src="${ctx}/images/chart-bar-up.png" border="0" width="20" />当月回款额</span></td>
          <td align="center"><span style="cursor:pointer;" onclick="openChartDialog();" title="点击查看图表"><img src="${ctx}/images/chart-bar-up.png" border="0" width="20" />当月回款完成率</span></td>
          <td align="center"><span style="cursor:pointer;" onclick="openChartDialog();" title="点击查看图表"><img src="${ctx}/images/chart-bar-up.png" border="0" width="20" />累计回款任务</span></td>
          <td align="center"><span style="cursor:pointer;" onclick="openChartDialog();" title="点击查看图表"><img src="${ctx}/images/chart-bar-up.png" border="0" width="20" />累计回款额</span></td>
          <td align="center"><span style="cursor:pointer;" onclick="openChartDialog();" title="点击查看图表"><img src="${ctx}/images/chart-bar-up.png" border="0" width="20" />累计回款完成率</span></td>
          <td align="center"><span style="cursor:pointer;" onclick="openChartDialog();" title="点击查看图表"><img src="${ctx}/images/chart-bar-up.png" border="0" width="20" />支年同期任务</span></td>
          <td align="center"><span style="cursor:pointer;" onclick="openChartDialog();" title="点击查看图表"><img src="${ctx}/images/chart-bar-up.png" border="0" width="20" />去年同期回款</span></td>
          <td align="center"><span style="cursor:pointer;" onclick="openChartDialog();" title="点击查看图表"><img src="${ctx}/images/chart-bar-up.png" border="0" width="20" />去年同期增长率</span></td>
          <td align="center"><span style="cursor:pointer;" onclick="openChartDialog();" title="点击查看图表"><img src="${ctx}/images/chart-bar-up.png" border="0" width="20" />去年同期任务累计</span></td>
          <td align="center"><span style="cursor:pointer;" onclick="openChartDialog();" title="点击查看图表"><img src="${ctx}/images/chart-bar-up.png" border="0" width="20" />去年同期回款累计</span></td>
          <td align="center"><span style="cursor:pointer;" onclick="openChartDialog();" title="点击查看图表"><img src="${ctx}/images/chart-bar-up.png" border="0" width="20" />去年同期回款累计增长率</span></td>
        </tr>
        <tr>
          <td>1</td>
          <td>北京</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
        </tr>
        <tr>
          <td>2</td>
          <td>郑州</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
        </tr>
        <tr>
          <td>3</td>
          <td>成都</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
        </tr>
        <tr>
          <td>4</td>
          <td>东莞</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
        </tr>
        <tr>
          <td>5</td>
          <td>福州</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
        </tr>
        <tr>
          <td>6</td>
          <td>广州</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
        </tr>
        <tr>
          <td>7</td>
          <td>贵阳</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
        </tr>
        <tr>
          <td>8</td>
          <td>哈尔滨</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
        </tr>
        <tr>
          <td>9</td>
          <td>海口</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
        </tr>
        <tr>
          <td>10</td>
          <td>杭州</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
        </tr>
        <tr>
          <td>11</td>
          <td>合肥</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
        </tr>
        <tr>
          <td>12</td>
          <td>衡阳</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
        </tr>
        <tr>
          <td>13</td>
          <td>呼和浩</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
        </tr>
        <tr>
          <td>14</td>
          <td>济南</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
        </tr>
        <tr>
          <td>15</td>
          <td>锦州</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
        </tr>
        <tr>
          <td>16</td>
          <td>荆州</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
        </tr>
        <tr>
          <td>17</td>
          <td>昆明</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
        </tr>
        <tr>
          <td>18</td>
          <td>兰州</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
        </tr>
        <tr>
          <td>19</td>
          <td>内江</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
        </tr>
        <tr>
          <td>20</td>
          <td>南昌</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
        </tr>
        <tr>
          <td>21</td>
          <td>南京</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
        </tr>
        <tr>
          <td>22</td>
          <td>南宁</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
        </tr>
        <tr>
          <td>23</td>
          <td>南通</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
        </tr>
        <tr>
          <td>24</td>
          <td>南阳</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
        </tr>
        <tr>
          <td>25</td>
          <td>上海</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
        </tr>
        <tr>
          <td>26</td>
          <td>深圳</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
        </tr>
        <tr>
          <td>27</td>
          <td>沈阳</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
        </tr>
        <tr>
          <td>28</td>
          <td>石家庄</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
        </tr>
        <tr>
          <td>29</td>
          <td>苏州</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
        </tr>
        <tr>
          <td>30</td>
          <td>太原</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
        </tr>
        <tr>
          <td>31</td>
          <td>天津</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
        </tr>
        <tr>
          <td>32</td>
          <td>温州</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
        </tr>
        <tr>
          <td>33</td>
          <td>乌鲁木</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
        </tr>
        <tr>
          <td>34</td>
          <td>芜湖</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
        </tr>
        <tr>
          <td>35</td>
          <td>武汉</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
        </tr>
        <tr>
          <td>36</td>
          <td>西安</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
        </tr>
        <tr>
          <td>37</td>
          <td>厦门</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
        </tr>
        <tr>
          <td>38</td>
          <td>徐州</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
        </tr>
        <tr>
          <td>39</td>
          <td>重庆</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
        </tr>
        <tr>
          <td>40</td>
          <td>长沙</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
        </tr>
        <tr>
          <td>41</td>
          <td>长春</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
        </tr>
      </table>
    </div>
    <div id="div_4">
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
        <tr class="tabtt1">
          <td width="40" align="center">序号</td>
          <td nowrap="nowrap">名称</td>
          <td align="center"><span style="cursor:pointer;" onclick="openChartDialog();" title="点击查看图表"><img src="${ctx}/images/chart-bar-up.png" border="0" width="20" />当月回款任务</span></td>
          <td align="center"><span style="cursor:pointer;" onclick="openChartDialog();" title="点击查看图表"><img src="${ctx}/images/chart-bar-up.png" border="0" width="20" />当月回款额</span></td>
          <td align="center"><span style="cursor:pointer;" onclick="openChartDialog();" title="点击查看图表"><img src="${ctx}/images/chart-bar-up.png" border="0" width="20" />当月回款完成率</span></td>
          <td align="center"><span style="cursor:pointer;" onclick="openChartDialog();" title="点击查看图表"><img src="${ctx}/images/chart-bar-up.png" border="0" width="20" />累计回款任务</span></td>
          <td align="center"><span style="cursor:pointer;" onclick="openChartDialog();" title="点击查看图表"><img src="${ctx}/images/chart-bar-up.png" border="0" width="20" />累计回款额</span></td>
          <td align="center"><span style="cursor:pointer;" onclick="openChartDialog();" title="点击查看图表"><img src="${ctx}/images/chart-bar-up.png" border="0" width="20" />累计回款完成率</span></td>
          <td align="center"><span style="cursor:pointer;" onclick="openChartDialog();" title="点击查看图表"><img src="${ctx}/images/chart-bar-up.png" border="0" width="20" />支年同期任务</span></td>
          <td align="center"><span style="cursor:pointer;" onclick="openChartDialog();" title="点击查看图表"><img src="${ctx}/images/chart-bar-up.png" border="0" width="20" />去年同期回款</span></td>
          <td align="center"><span style="cursor:pointer;" onclick="openChartDialog();" title="点击查看图表"><img src="${ctx}/images/chart-bar-up.png" border="0" width="20" />去年同期增长率</span></td>
          <td align="center"><span style="cursor:pointer;" onclick="openChartDialog();" title="点击查看图表"><img src="${ctx}/images/chart-bar-up.png" border="0" width="20" />去年同期任务累计</span></td>
          <td align="center"><span style="cursor:pointer;" onclick="openChartDialog();" title="点击查看图表"><img src="${ctx}/images/chart-bar-up.png" border="0" width="20" />去年同期回款累计</span></td>
          <td align="center"><span style="cursor:pointer;" onclick="openChartDialog();" title="点击查看图表"><img src="${ctx}/images/chart-bar-up.png" border="0" width="20" />去年同期回款累计增长率</span></td>
        </tr>
        <tr>
          <td align="center" nowrap="nowrap">1</td>
          <td width="72">西南区域</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
        </tr>
        <tr>
          <td align="center" nowrap="nowrap">2</td>
          <td>华北区域</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
        </tr>
        <tr>
          <td align="center" nowrap="nowrap">3</td>
          <td>华南区域</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
        </tr>
        <tr>
          <td align="center" nowrap="nowrap">4</td>
          <td>东北区域</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
        </tr>
        <tr>
          <td align="center" nowrap="nowrap">5</td>
          <td>西北区域</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
        </tr>
        <tr>
          <td align="center" nowrap="nowrap">6</td>
          <td>华中区域</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
        </tr>
        <tr>
          <td align="center" nowrap="nowrap">7</td>
          <td>华东区域</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
        </tr>
        <tr>
          <td align="center" nowrap="nowrap">8</td>
          <td>山东大区</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td align="right" nowrap="nowrap">&nbsp;</td>
        </tr>
      </table>
    </div>
  </div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script> 
<script type="text/javascript"  src="${ctx}/commons/scripts/imgpreview.0.22.js"></script> 
<script type="text/javascript" src="${ctx}/javascripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
    $("#div_2").hide();
    $("#div_3").hide();
    $("#div_4").hide();
	
	$("#search_type1").click(function(){
        $("#span_1").show();
        $("#div_1").show();
        $("#div_2").hide();
        $("#div_3").hide();
        $("#div_4").hide();
    });
	$("#search_type2").click(function(){
        $("#span_1").hide();
        $("#div_1").hide();
        $("#div_2").show();
        $("#div_3").hide();
        $("#div_4").hide();
    });
	$("#search_type3").click(function(){
		 $("#span_1").hide();
        $("#div_1").hide();
        $("#div_2").hide();
        $("#div_3").show();
        $("#div_4").hide();
    });
	$("#search_type4").click(function(){
		 $("#span_1").hide();
        $("#div_1").hide();
        $("#div_2").hide();
        $("#div_3").hide();
        $("#div_4").show();
    });
	
	
	
	$("#span_help").click(function(){
        $("#cvtooltipClose").cvtooltip({
            panel: "#body_oarcont",
            direction: 1,                
            width: 420,
            left: 320,
            top: 5,
            speed: 500,
            delay: 10000
        });
    });
});

function openChartDialog(){
    window.showModalDialog("Dialog.do?method=list&mod_id=${af.map.mod_id}&time=" + new Date().getTime(), window, "dialogWidth:800px;status:no;dialogHeight:600px");
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>