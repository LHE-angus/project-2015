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
    <html-el:form action="/admin/ChannelBestSellingModelsAnalysis">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="mod_code" value="${af.map.mod_code}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="15"></td>
          <td><ul>
              <li> <strong class="fb">类别：</strong>
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
                  分大区</label>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span id="span_1"><strong class="fb">单客户：</strong>
                <html-el:text property="key_word" size="20" maxlength="20" styleId="key_word"  />
                </span>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span id="span_1"><strong class="fb">机型：</strong>
                <html-el:text property="key_word" size="20" maxlength="20" styleId="key_word" value="点击请选择"  />
                </span></li>
              <li style="padding-top:3px;"> <strong class="fb">时间：</strong>
                <label>
                  <input name="search_date" id="search_date1" type="radio" value="1" checked="checked" />
                  每天</label>
                <label>
                  <input name="search_date" id="search_date2" type="radio" value="2" />
                  每月</label>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <strong class="fb">范围：</strong>
                <html-el:text property="key_word" size="10" maxlength="20" styleId="key_word"  />
                至
                <html-el:text property="key_word" size="10" maxlength="20" styleId="key_word"  />
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <html-el:submit styleClass="but1" value="搜索" />
                &nbsp;<html-el:submit styleClass="but1" value="导出" />
              </li>
            </ul></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
  </div>
  <div class="rtabcont1">
    <div id="div_1">
      <html-el:form action="/admin/ChannelBestSellingModelsAnalysis" method="post">
        <html-el:hidden property="method" value="edit" />
        <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
        <html-el:hidden property="tree_param" value="${tree_param}" />
        <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
          <tr class="tabtt1">
            <td width="40" align="center">排名</td>
            <td width="40" nowrap="nowrap">产品编号</td>
            <td align="center">产品型号</td>
            <td width="60" align="center"><img src="${ctx}/images/chart-bar-up.png" border="0" width="20" alt="点击查看图表" />销量</td>
            <td width="60" align="center"><img src="${ctx}/images/chart-bar-up.png" border="0" width="20" alt="点击查看图表" />占比</td>
            <td width="60" align="center"><img src="${ctx}/images/chart-bar-up.png" border="0" width="20" alt="点击查看图表" />销额</td>
            <td width="60" align="center"><img src="${ctx}/images/chart-bar-up.png" border="0" width="20" alt="点击查看图表" />占比</td>
            <td width="60" align="center"><img src="${ctx}/images/chart-bar-up.png" border="0" width="20" alt="点击查看图表" />均价</td>
            <td width="60" align="center"><img src="${ctx}/images/chart-bar-up.png" border="0" width="20" alt="点击查看图表" />利润</td>
            <td width="60" align="center"><img src="${ctx}/images/chart-bar-up.png" border="0" width="20" alt="点击查看图表" />贡献率</td>
          </tr>
          <c:set var="tr_1_1" value="123456" />
          <c:set var="tr_2_1" value="112456" />
          <c:set var="tr_3_1" value="110056" />
          <c:set var="tr_4_1" value="90056" />
          <c:set var="tr_5_1" value="88056" />
          <c:set var="tr_6_1" value="67056" />
          <c:set var="tr_7_1" value="55056" />
          <c:set var="tr_8_1" value="52056" />
          <c:set var="tr_9_1" value="50056" />
          <c:set var="tr_10_1" value="48056" />
          
          <c:set var="tr_1_2" value="${tr_1_1 * 1342}" />
          <c:set var="tr_2_2" value="${tr_2_1 * 1642}" />
          <c:set var="tr_3_2" value="${tr_3_1 * 1442}" />
          <c:set var="tr_4_2" value="${tr_4_1 * 2342}" />
          <c:set var="tr_5_2" value="${tr_5_1 * 3342}" />
          <c:set var="tr_6_2" value="${tr_6_1 * 1352}" />
          <c:set var="tr_7_2" value="${tr_7_1 * 1342}" />
          <c:set var="tr_8_2" value="${tr_8_1 * 1782}" />
          <c:set var="tr_9_2" value="${tr_9_1 * 4322}" />
          <c:set var="tr_10_2" value="${tr_10_1 * 2312}" />
          
          <c:set var="tr_1_3" value="${tr_1_1 * 134}" />
          <c:set var="tr_2_3" value="${tr_2_1 * 64}" />
          <c:set var="tr_3_3" value="${tr_3_1 * 44}" />
          <c:set var="tr_4_3" value="${tr_4_1 * 340}" />
          <c:set var="tr_5_3" value="${tr_5_1 * 234}" />
          <c:set var="tr_6_3" value="${tr_6_1 * 350}" />
          <c:set var="tr_7_3" value="${tr_7_1 * 134}" />
          <c:set var="tr_8_3" value="${tr_8_1 * 178}" />
          <c:set var="tr_9_3" value="${tr_9_1 * 432}" />
          <c:set var="tr_10_3" value="${tr_10_1 * 231}" />
          
          <tr>
            <td align="center" nowrap="nowrap">1</td>
            <td height="28">2000516428</td>
            <td valign="middle"> 康佳 LED46MS92DC</td>
            <td align="right" nowrap="nowrap">${tr_1_1}</td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${tr_1_1/(tr_1_1 +tr_2_1 +tr_3_1 +tr_4_1 +tr_5_1 +tr_6_1 +tr_7_1 +tr_8_1 +tr_9_1 +tr_10_1) * 100}" pattern="0.00" />%</td>
            <td align="right" nowrap="nowrap">${tr_1_2}</td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${tr_1_2/(tr_1_2 +tr_2_2 +tr_3_2 +tr_4_2 +tr_5_2 +tr_6_2 +tr_7_2 +tr_8_2 +tr_9_2 +tr_20_2)* 100}" pattern="0.00" />%</td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${tr_1_2/tr_1_1}" pattern="0.00" /></td>
            <td align="right" nowrap="nowrap">${tr_1_3}</td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${tr_1_3/(tr_1_3 +tr_2_3 +tr_3_3 +tr_4_3 +tr_5_3 +tr_6_3 +tr_7_3 +tr_8_3 +tr_9_3 +tr_20_3)* 100}" pattern="0.00" />%</td>
          </tr>
          <tr>
            <td align="center" nowrap="nowrap">2</td>
            <td height="28">2000516426</td>
            <td valign="middle"> 康佳 LED46IS97N</td>
            <td align="right" nowrap="nowrap"> ${tr_2_1}</td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${tr_2_1/(tr_1_1 +tr_2_1 +tr_3_1 +tr_4_1 +tr_5_1 +tr_6_1 +tr_7_1 +tr_8_1 +tr_9_1 +tr_10_1) * 100}" pattern="0.00" />%</td>
            <td align="right" nowrap="nowrap">${tr_2_2}</td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${tr_2_2/(tr_1_2 +tr_2_2 +tr_3_2 +tr_4_2 +tr_5_2 +tr_6_2 +tr_7_2 +tr_8_2 +tr_9_2 +tr_20_2)* 100}" pattern="0.00" />%</td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${tr_2_2/tr_2_1}" pattern="0.00" /></td>
            <td align="right" nowrap="nowrap">${tr_2_3}</td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${tr_2_3/(tr_1_3 +tr_2_3 +tr_3_3 +tr_4_3 +tr_5_3 +tr_6_3 +tr_7_3 +tr_8_3 +tr_9_3 +tr_20_3)* 100}" pattern="0.00" />%</td>
          </tr>
          <tr>
            <td align="center" nowrap="nowrap">3</td>
            <td height="28">2000516425</td>
            <td valign="middle"> 康佳 LED46IS95D</td>
            <td align="right" nowrap="nowrap"> ${tr_3_1}</td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${tr_3_1/(tr_1_1 +tr_2_1 +tr_3_1 +tr_4_1 +tr_5_1 +tr_6_1 +tr_7_1 +tr_8_1 +tr_9_1 +tr_10_1) * 100}" pattern="0.00" />%</td>
            <td align="right" nowrap="nowrap">${tr_3_2}</td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${tr_3_2/(tr_1_2 +tr_2_2 +tr_3_2 +tr_4_2 +tr_5_2 +tr_6_2 +tr_7_2 +tr_8_2 +tr_9_2 +tr_20_2)* 100}" pattern="0.00" />%</td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${tr_3_2/tr_3_1}" pattern="0.00" /></td>
            <td align="right" nowrap="nowrap">${tr_3_3}</td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${tr_3_3/(tr_1_3 +tr_2_3 +tr_3_3 +tr_4_3 +tr_5_3 +tr_6_3 +tr_7_3 +tr_8_3 +tr_9_3 +tr_20_3)* 100}" pattern="0.00" />%</td>
          </tr>
          <tr>
            <td align="center" nowrap="nowrap">4</td>
            <td height="28">2000516424</td>
            <td valign="middle"> 康佳 LED42IS95D</td>
            <td align="right" nowrap="nowrap"> ${tr_4_1}</td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${tr_4_1/(tr_1_1 +tr_2_1 +tr_3_1 +tr_4_1 +tr_5_1 +tr_6_1 +tr_7_1 +tr_8_1 +tr_9_1 +tr_10_1) * 100}" pattern="0.00" />%</td>
            <td align="right" nowrap="nowrap">${tr_4_2}</td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${tr_4_2/(tr_1_2 +tr_2_2 +tr_3_2 +tr_4_2 +tr_5_2 +tr_6_2 +tr_7_2 +tr_8_2 +tr_9_2 +tr_20_2)* 100}" pattern="0.00" />%</td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${tr_4_2/tr_4_1}" pattern="0.00" /></td>
            <td align="right" nowrap="nowrap">${tr_4_3}</td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${tr_4_3/(tr_1_3 +tr_2_3 +tr_3_3 +tr_4_3 +tr_5_3 +tr_6_3 +tr_7_3 +tr_8_3 +tr_9_3 +tr_20_3)* 100}" pattern="0.00" />%</td>
          </tr>
          <tr>
            <td align="center" nowrap="nowrap">5</td>
            <td height="28">2000516421</td>
            <td valign="middle"> 康佳 LED42IS97N</td>
            <td align="right" nowrap="nowrap"> ${tr_5_1}</td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${tr_5_1/(tr_1_1 +tr_2_1 +tr_3_1 +tr_4_1 +tr_5_1 +tr_6_1 +tr_7_1 +tr_8_1 +tr_9_1 +tr_10_1) * 100}" pattern="0.00" />%</td>
            <td align="right" nowrap="nowrap">${tr_5_2}</td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${tr_5_2/(tr_1_2 +tr_2_2 +tr_3_2 +tr_4_2 +tr_5_2 +tr_6_2 +tr_7_2 +tr_8_2 +tr_9_2 +tr_20_2)* 100}" pattern="0.00" />%</td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${tr_5_2/tr_5_1}" pattern="0.00" /></td>
            <td align="right" nowrap="nowrap">${tr_5_3}</td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${tr_5_3/(tr_1_3 +tr_2_3 +tr_3_3 +tr_4_3 +tr_5_3 +tr_6_3 +tr_7_3 +tr_8_3 +tr_9_3 +tr_20_3)* 100}" pattern="0.00" />%</td>
          </tr>
          <tr>
            <td align="center" nowrap="nowrap">6</td>
            <td height="28">2000516416</td>
            <td valign="middle"> 康佳 LED37IS95N</td>
            <td align="right" nowrap="nowrap">${tr_6_1}</td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${tr_6_1/(tr_1_1 +tr_2_1 +tr_3_1 +tr_4_1 +tr_5_1 +tr_6_1 +tr_7_1 +tr_8_1 +tr_9_1 +tr_10_1) * 100}" pattern="0.00" />%</td>
            <td align="right" nowrap="nowrap">${tr_6_2}</td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${tr_6_2/(tr_1_2 +tr_2_2 +tr_3_2 +tr_4_2 +tr_5_2 +tr_6_2 +tr_7_2 +tr_8_2 +tr_9_2 +tr_20_2)* 100}" pattern="0.00" />%</td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${tr_6_2/tr_6_1}" pattern="0.00" /></td>
            <td align="right" nowrap="nowrap">${tr_6_3}</td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${tr_6_3/(tr_1_3 +tr_2_3 +tr_3_3 +tr_4_3 +tr_5_3 +tr_6_3 +tr_7_3 +tr_8_3 +tr_9_3 +tr_20_3)* 100}" pattern="0.00" />%</td>
          </tr>
          <tr>
            <td align="center" nowrap="nowrap">7</td>
            <td height="28">2000516414</td>
            <td valign="middle"> 康佳 LED40IS97N</td>
            <td align="right" nowrap="nowrap"> ${tr_7_1}</td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${tr_7_1/(tr_1_1 +tr_2_1 +tr_3_1 +tr_4_1 +tr_5_1 +tr_6_1 +tr_7_1 +tr_8_1 +tr_9_1 +tr_10_1) * 100}" pattern="0.00" />%</td>
            <td align="right" nowrap="nowrap">${tr_7_2}</td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${tr_7_2/(tr_1_2 +tr_2_2 +tr_3_2 +tr_4_2 +tr_5_2 +tr_6_2 +tr_7_2 +tr_8_2 +tr_9_2 +tr_20_2)* 100}" pattern="0.00" />%</td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${tr_7_2/tr_7_1}" pattern="0.00" /></td>
            <td align="right" nowrap="nowrap">${tr_7_3}</td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${tr_7_3/(tr_1_3 +tr_2_3 +tr_3_3 +tr_4_3 +tr_5_3 +tr_6_3 +tr_7_3 +tr_8_3 +tr_9_3 +tr_20_3)* 100}" pattern="0.00" />%</td>
          </tr>
          <tr>
            <td align="center" nowrap="nowrap">8</td>
            <td height="28">2000516412</td>
            <td valign="middle"> 康佳 LC46TS86DC</td>
            <td align="right" nowrap="nowrap"> ${tr_8_1}</td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${tr_8_1/(tr_1_1 +tr_2_1 +tr_3_1 +tr_4_1 +tr_5_1 +tr_6_1 +tr_7_1 +tr_8_1 +tr_9_1 +tr_10_1) * 100}" pattern="0.00" />%</td>
            <td align="right" nowrap="nowrap">${tr_8_2}</td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${tr_8_2/(tr_1_2 +tr_2_2 +tr_3_2 +tr_4_2 +tr_5_2 +tr_6_2 +tr_7_2 +tr_8_2 +tr_9_2 +tr_20_2)* 100}" pattern="0.00" />%</td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${tr_8_2/tr_8_1}" pattern="0.00" /></td>
            <td align="right" nowrap="nowrap">${tr_8_3}</td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${tr_8_3/(tr_1_3 +tr_2_3 +tr_3_3 +tr_4_3 +tr_5_3 +tr_6_3 +tr_7_3 +tr_8_3 +tr_9_3 +tr_20_3)* 100}" pattern="0.00" />%</td>
          </tr>
          <tr>
            <td align="center" nowrap="nowrap">9</td>
            <td height="28">2000516410</td>
            <td valign="middle"> 康佳 LC42MS96PD</td>
            <td align="right" nowrap="nowrap"> ${tr_9_1}</td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${tr_9_1/(tr_1_1 +tr_2_1 +tr_3_1 +tr_4_1 +tr_5_1 +tr_6_1 +tr_7_1 +tr_8_1 +tr_9_1 +tr_10_1) * 100}" pattern="0.00" />%</td>
            <td align="right" nowrap="nowrap">${tr_9_2}</td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${tr_9_2/(tr_1_2 +tr_2_2 +tr_3_2 +tr_4_2 +tr_5_2 +tr_6_2 +tr_7_2 +tr_8_2 +tr_9_2 +tr_20_2)* 100}" pattern="0.00" />%</td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${tr_9_2/tr_9_1}" pattern="0.00" /></td>
            <td align="right" nowrap="nowrap">${tr_9_3}</td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${tr_9_3/(tr_1_3 +tr_2_3 +tr_3_3 +tr_4_3 +tr_5_3 +tr_6_3 +tr_7_3 +tr_8_3 +tr_9_3 +tr_20_3)* 100}" pattern="0.00" />%</td>
          </tr>
          <tr>
            <td align="center" nowrap="nowrap">10</td>
            <td height="28">2000516409</td>
            <td valign="middle"> 康佳 LED42MS91DC</td>
            <td align="right" nowrap="nowrap"> ${tr_10_1}</td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${tr_10_1/(tr_1_1 +tr_2_1 +tr_3_1 +tr_4_1 +tr_5_1 +tr_6_1 +tr_7_1 +tr_8_1 +tr_9_1 +tr_10_1) * 100}" pattern="0.00" />%</td>
            <td align="right" nowrap="nowrap">${tr_10_2}</td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${tr_10_2/(tr_1_2 +tr_2_2 +tr_3_2 +tr_4_2 +tr_5_2 +tr_6_2 +tr_7_2 +tr_8_2 +tr_9_2 +tr_20_2)* 100}" pattern="0.00" />%</td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${tr_10_2/tr_10_1}" pattern="0.00" /></td>
            <td align="right" nowrap="nowrap">${tr_10_3}</td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${tr_10_3/(tr_1_3 +tr_2_3 +tr_3_3 +tr_4_3 +tr_5_3 +tr_6_3 +tr_7_3 +tr_8_3 +tr_9_3 +tr_20_3)* 100}" pattern="0.00" />%</td>
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
          </tr>
        </table>
      </html-el:form>
      <br />
      <form id="bottomPageForm" name="bottomPageForm" method="post" action="ChannelBestSellingModelsAnalysis.do">
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
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>