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
        <td>当前位置${naviString}</td>
        <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align:middle;" /> <span id="span_help" style="cursor:pointer;">帮助</span></td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/admin/RetailSalesQuery">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="mod_code" value="${af.map.mod_code}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="15"></td>
          <td><ul>
              <li> <strong class="fb">选择日期</strong>
                <html-el:text property="key_word" size="10" maxlength="20" styleId="key_word"  />
                至
                <html-el:text property="key_word" size="10" maxlength="20" styleId="key_word"  />
                &nbsp;&nbsp;&nbsp;<strong class="fb">留言类别</strong>
                <html-el:select property="fgs_dept_id" styleId="fgs_dept_id">
                  <html-el:option value="">请选择...</html-el:option>
                  <html-el:option value="10">问题咨询</html-el:option>
                  <html-el:option value="">工作建议</html-el:option>
                  <html-el:option value="">其它</html-el:option>
                </html-el:select>
                &nbsp;&nbsp;&nbsp;<strong class="fb">留言编号</strong>
                <html-el:text property="customer_name_like" maxlength="20" styleId="customer_name_like" style="width:90px;"  />
              </li>
              <li style="padding-top:3px;"> <strong class="fb">所属部门</strong>
                <select name="fgs_dept_id" id="fgs_dept_id" style="width:80px;">
                  <option value="">请选择分公司</option>
                  <option value="2">北京</option>
                  <option value="42">重庆</option>
                  <option value="4">长沙</option>
                  <option value="5">成都</option>
                  <option value="6">东莞</option>
                  <option value="7">福州</option>
                  <option value="8">广州</option>
                  <option value="9">贵阳</option>
                  <option value="10">哈尔滨</option>
                  <option value="11">海口</option>
                  <option value="12">杭州</option>
                  <option value="13">合肥</option>
                  <option value="14">衡阳</option>
                  <option value="15">呼和浩特</option>
                  <option value="16">济南</option>
                  <option value="17">锦州</option>
                  <option value="18">荆州</option>
                  <option value="19">昆明</option>
                  <option value="20">兰州</option>
                  <option value="21">内江</option>
                  <option value="22">南昌</option>
                  <option value="23">南京</option>
                  <option value="24">南宁</option>
                  <option value="25">南通</option>
                  <option value="26">南阳</option>
                  <option value="27">上海</option>
                  <option value="28">深圳</option>
                  <option value="29">沈阳</option>
                  <option value="30">石家庄</option>
                  <option value="31">苏州</option>
                  <option value="32">太原</option>
                  <option value="33">天津</option>
                  <option value="34">温州</option>
                  <option value="35">乌鲁木齐</option>
                  <option value="36">芜湖</option>
                  <option value="37">武汉</option>
                  <option value="38">西安</option>
                  <option value="39">厦门</option>
                  <option value="40">徐州</option>
                  <option value="41">郑州</option>
                  <option value="3">长春</option>
                </select>
                <select name="jyb_dept_id" id="jyb_dept_id" style="width:120px;">
                  <option value="">请选择经营部或办事处</option>
                </select>
                <select name="ywy_user_id" id="ywy_user_id" style="width:130px;">
                  <option value="">请选择业务员</option>
                </select>
                &nbsp;&nbsp;&nbsp;<strong class="fb">关键词</strong>
                <html-el:text property="customer_name_like" maxlength="20" styleId="customer_name_like" style="width:120px;"  />
                &nbsp;&nbsp;&nbsp;
                <html-el:submit styleClass="but1" value="搜索" />
              </li>
            </ul></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <div id="div_1">
      <html-el:form action="/admin/RetailSampleQuery" method="post">
        <html-el:hidden property="method" value="edit" />
        <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
        <html-el:hidden property="tree_param" value="${tree_param}" />
        <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
          <tr class="tabtt1">
            <td width="60" align="center">留言编号</td>
            <td align="center">留言内容</td>
            <td align="center">操作</td>
          </tr>
          <tr>
            <td align="center" nowrap="nowrap">17205</td>
            <td><p>留言分类：[问题咨询] 留言时间：2011-11-02 16:26:40 <br /> 
              问：请问十一期间工作计划具体是什么？<br />
              姓名：A11A11　部门：西安分公司-某经营部　联系方式：13312345678              <br />
                <br />
            答：近日十一促销计划会下发。<br />
            姓名：A11A11　部门：西安分公司-某经营部　联系方式：13312345678            <br />
            </p></td>
            <td align="center">回复 删除</td>
          </tr>
          <tr>
            <td height="28" align="center" nowrap="nowrap">&nbsp;</td>
            <td valign="middle">&nbsp;</td>
            <td align="center" valign="middle">&nbsp;</td>
          </tr>
          <tr>
            <td align="center" nowrap="nowrap">&nbsp;</td>
            <td valign="middle">&nbsp;</td>
            <td width="80" align="center" nowrap="nowrap">&nbsp;</td>
          </tr>
          <tr>
            <td align="center" nowrap="nowrap">&nbsp;</td>
            <td valign="middle">&nbsp;</td>
            <td align="center" nowrap="nowrap">&nbsp;</td>
          </tr>
          <tr>
            <td align="center" nowrap="nowrap">&nbsp;</td>
            <td valign="middle">&nbsp;</td>
            <td align="center" nowrap="nowrap">&nbsp;</td>
          </tr>
          <tr>
            <td align="center" nowrap="nowrap">&nbsp;</td>
            <td valign="middle">&nbsp;</td>
            <td align="center" nowrap="nowrap">&nbsp;</td>
          </tr>
          <tr>
            <td align="center" nowrap="nowrap">&nbsp;</td>
            <td valign="middle">&nbsp;</td>
            <td align="center" nowrap="nowrap">&nbsp;</td>
          </tr>
          <tr>
            <td align="center" nowrap="nowrap">&nbsp;</td>
            <td valign="middle">&nbsp;</td>
            <td align="center" nowrap="nowrap">&nbsp;</td>
          </tr>
          <tr>
            <td align="center" nowrap="nowrap">&nbsp;</td>
            <td valign="middle">&nbsp;</td>
            <td align="center" nowrap="nowrap">&nbsp;</td>
          </tr>
          <tr>
            <td align="center" nowrap="nowrap">&nbsp;</td>
            <td valign="middle">&nbsp;</td>
            <td align="center" nowrap="nowrap">&nbsp;</td>
          </tr>
          <tr>
            <td align="center" nowrap="nowrap">&nbsp;</td>
            <td nowrap="nowrap"></td>
            <td align="center" nowrap="nowrap"></td>
          </tr>
        </table>
      </html-el:form>
      <br />
      <form id="bottomPageForm" name="bottomPageForm" method="post" action="RetailSampleQuery.do">
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