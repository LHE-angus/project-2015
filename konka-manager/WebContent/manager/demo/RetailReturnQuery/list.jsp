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
<style>
body {background-color:white;overflow-x:auto;}
</style>
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
    <%@ include file="../inc_retail_search.jsp" %>
  </div>
  <div class="rtabcont1">
    <div id="div_1">
      <html-el:form action="/admin/RetailReturnQuery" method="post">
        <html-el:hidden property="method" value="edit" />
        <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
        <html-el:hidden property="tree_param" value="${tree_param}" />
        <table width="1500" border="0" cellpadding="0" cellspacing="1" class="rtable2">
          <tr class="tabtt1">
            <td width="30" align="center">排名</td>
            <td width="40" nowrap="nowrap">分公司</td>
            <td align="center">网点</td>
            <td width="80" align="center">渠道</td>
            <td width="80" align="center">R3</td>
            <td width="60" align="center">产品类别</td>
            <td width="60" align="center">产品尺寸</td>
            <td width="180" align="center">产品型号</td>
            <td width="60" align="center">价格</td>
            <td width="60" align="center">数量</td>
            <td width="60" align="center">赠品</td>
            <td width="60" align="center">数量</td>
            <td width="80" align="center">业务员</td>
            <td width="80" align="center">直销员</td>
            <td width="80" align="center">上报时间</td>
            <td width="40" align="center">操作</td>
          </tr>
          <tr>
            <td align="center" nowrap="nowrap">1</td>
            <td align="center">长沙</td>
            <td valign="middle"> 凉山州众望电器有限责任公司</td>
            <td nowrap="nowrap">县乡客户</td>
            <td nowrap="nowrap">F105GCJZH</td>
            <td nowrap="nowrap">LED</td>
            <td nowrap="nowrap">14</td>
            <td nowrap="nowrap">康佳 LED46IS95D</td>
            <td align="right" nowrap="nowrap">1234</td>
            <td align="right" nowrap="nowrap">10</td>
            <td align="right" nowrap="nowrap">&nbsp;</td>
            <td align="right" nowrap="nowrap">&nbsp;</td>
            <td nowrap="nowrap">业务员A</td>
            <td nowrap="nowrap">直销员C</td>
            <td align="right" nowrap="nowrap">20111103 10:10</td>
            <td align="center" nowrap="nowrap">修改</td>
          </tr>
          <tr>
            <td align="center" nowrap="nowrap">2</td>
            <td height="28" align="center">长沙</td>
            <td valign="middle"> 凉山州众望电器有限责任公司</td>
            <td nowrap="nowrap">县乡客户</td>
            <td nowrap="nowrap">F105GCJZH</td>
            <td nowrap="nowrap">LED</td>
            <td nowrap="nowrap">17</td>
            <td nowrap="nowrap">康佳 LED46IS95D</td>
            <td align="right" nowrap="nowrap">1234</td>
            <td align="right" nowrap="nowrap">10</td>
            <td align="right" nowrap="nowrap">&nbsp;</td>
            <td align="right" nowrap="nowrap">&nbsp;</td>
            <td nowrap="nowrap">业务员A</td>
            <td nowrap="nowrap">直销员C</td>
            <td align="right" nowrap="nowrap">20111103 10:10</td>
            <td align="center" nowrap="nowrap">&nbsp;</td>
          </tr>
          <tr>
            <td align="center" nowrap="nowrap">3</td>
            <td align="center">长沙</td>
            <td valign="middle"> 凉山州众望电器有限责任公司</td>
            <td nowrap="nowrap">县乡客户</td>
            <td nowrap="nowrap">F105GCJZH</td>
            <td nowrap="nowrap">LED</td>
            <td nowrap="nowrap">25</td>
            <td nowrap="nowrap">康佳 LED46IS95D</td>
            <td align="right" nowrap="nowrap">1234</td>
            <td align="right" nowrap="nowrap">10</td>
            <td align="right" nowrap="nowrap">&nbsp;</td>
            <td align="right" nowrap="nowrap">&nbsp;</td>
            <td nowrap="nowrap">业务员A</td>
            <td nowrap="nowrap">直销员C</td>
            <td align="right" nowrap="nowrap">20111103 10:10</td>
            <td align="center" nowrap="nowrap">&nbsp;</td>
          </tr>
          <tr>
            <td align="center" nowrap="nowrap">4</td>
            <td align="center">长沙</td>
            <td valign="middle"> 凉山州众望电器有限责任公司</td>
            <td nowrap="nowrap">县乡客户</td>
            <td nowrap="nowrap">F105GCJZH</td>
            <td nowrap="nowrap">LED</td>
            <td nowrap="nowrap">14</td>
            <td nowrap="nowrap">康佳 LED46IS95D</td>
            <td align="right" nowrap="nowrap">1234</td>
            <td align="right" nowrap="nowrap">10</td>
            <td align="right" nowrap="nowrap">&nbsp;</td>
            <td align="right" nowrap="nowrap">&nbsp;</td>
            <td nowrap="nowrap">业务员A</td>
            <td nowrap="nowrap">直销员C</td>
            <td align="right" nowrap="nowrap">20111103 10:10</td>
            <td align="center" nowrap="nowrap">&nbsp;</td>
          </tr>
          <tr>
            <td align="center" nowrap="nowrap">5</td>
            <td align="center">长沙</td>
            <td valign="middle"> 凉山州众望电器有限责任公司</td>
            <td nowrap="nowrap">县乡客户</td>
            <td nowrap="nowrap">F105GCJZH</td>
            <td nowrap="nowrap">CRT</td>
            <td nowrap="nowrap">17</td>
            <td nowrap="nowrap">康佳 LED46IS95D</td>
            <td align="right" nowrap="nowrap">1234</td>
            <td align="right" nowrap="nowrap">10</td>
            <td align="right" nowrap="nowrap">&nbsp;</td>
            <td align="right" nowrap="nowrap">&nbsp;</td>
            <td nowrap="nowrap">业务员A</td>
            <td nowrap="nowrap">直销员C</td>
            <td align="right" nowrap="nowrap">20111103 10:10</td>
            <td align="center" nowrap="nowrap">&nbsp;</td>
          </tr>
          <tr>
            <td align="center" nowrap="nowrap">6</td>
            <td align="center">长沙</td>
            <td valign="middle"> 凉山州众望电器有限责任公司</td>
            <td nowrap="nowrap">县乡客户</td>
            <td nowrap="nowrap">F105GCJZH</td>
            <td nowrap="nowrap">CRT</td>
            <td nowrap="nowrap">25</td>
            <td nowrap="nowrap">康佳 LED46IS95D</td>
            <td align="right" nowrap="nowrap">1234</td>
            <td align="right" nowrap="nowrap">10</td>
            <td align="right" nowrap="nowrap">&nbsp;</td>
            <td align="right" nowrap="nowrap">&nbsp;</td>
            <td nowrap="nowrap">业务员A</td>
            <td nowrap="nowrap">直销员C</td>
            <td align="right" nowrap="nowrap">20111103 10:10</td>
            <td align="center" nowrap="nowrap">&nbsp;</td>
          </tr>
          <tr>
            <td align="center" nowrap="nowrap">7</td>
            <td align="center">长沙</td>
            <td valign="middle"> 凉山州众望电器有限责任公司</td>
            <td nowrap="nowrap">县乡客户</td>
            <td nowrap="nowrap">F105GCJZH</td>
            <td nowrap="nowrap">CRT</td>
            <td nowrap="nowrap">14</td>
            <td nowrap="nowrap">康佳 LED46IS95D</td>
            <td align="right" nowrap="nowrap">1234</td>
            <td align="right" nowrap="nowrap">10</td>
            <td align="right" nowrap="nowrap">&nbsp;</td>
            <td align="right" nowrap="nowrap">&nbsp;</td>
            <td nowrap="nowrap">业务员A</td>
            <td nowrap="nowrap">直销员C</td>
            <td align="right" nowrap="nowrap">20111103 10:10</td>
            <td align="center" nowrap="nowrap">&nbsp;</td>
          </tr>
          <tr>
            <td align="center" nowrap="nowrap">8</td>
            <td align="center">长沙</td>
            <td valign="middle"> 凉山州众望电器有限责任公司</td>
            <td nowrap="nowrap">县乡客户</td>
            <td nowrap="nowrap">F105GCJZH</td>
            <td nowrap="nowrap">CRT</td>
            <td nowrap="nowrap">14</td>
            <td nowrap="nowrap">康佳 LED46IS95D</td>
            <td align="right" nowrap="nowrap">1234</td>
            <td align="right" nowrap="nowrap">10</td>
            <td align="right" nowrap="nowrap">&nbsp;</td>
            <td align="right" nowrap="nowrap">&nbsp;</td>
            <td nowrap="nowrap">业务员A</td>
            <td nowrap="nowrap">直销员C</td>
            <td align="right" nowrap="nowrap">20111103 10:10</td>
            <td align="center" nowrap="nowrap">&nbsp;</td>
          </tr>
          <tr>
            <td align="center" nowrap="nowrap">9</td>
            <td align="center">长沙</td>
            <td valign="middle"> 凉山州众望电器有限责任公司</td>
            <td nowrap="nowrap">县乡客户</td>
            <td nowrap="nowrap">F105GCJZH</td>
            <td nowrap="nowrap">CRT</td>
            <td nowrap="nowrap">17</td>
            <td nowrap="nowrap">康佳 LED46IS95D</td>
            <td align="right" nowrap="nowrap">1234</td>
            <td align="right" nowrap="nowrap">10</td>
            <td align="right" nowrap="nowrap">&nbsp;</td>
            <td align="right" nowrap="nowrap">&nbsp;</td>
            <td nowrap="nowrap">业务员A</td>
            <td nowrap="nowrap">直销员C</td>
            <td align="right" nowrap="nowrap">20111103 10:10</td>
            <td align="center" nowrap="nowrap">&nbsp;</td>
          </tr>
          <tr>
            <td align="center" nowrap="nowrap">10</td>
            <td align="center">长沙</td>
            <td valign="middle"> 凉山州众望电器有限责任公司</td>
            <td nowrap="nowrap">县乡客户</td>
            <td nowrap="nowrap">F105GCJZH</td>
            <td nowrap="nowrap">CRT</td>
            <td nowrap="nowrap">25</td>
            <td nowrap="nowrap">康佳 LED46IS95D</td>
            <td align="right" nowrap="nowrap">1234</td>
            <td align="right" nowrap="nowrap">10</td>
            <td align="right" nowrap="nowrap">&nbsp;</td>
            <td align="right" nowrap="nowrap">&nbsp;</td>
            <td nowrap="nowrap">业务员A</td>
            <td nowrap="nowrap">直销员C</td>
            <td align="right" nowrap="nowrap">20111103 10:10</td>
            <td align="center" nowrap="nowrap">&nbsp;</td>
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
            <td nowrap="nowrap"></td>
            <td align="center" nowrap="nowrap"></td>
          </tr>
        </table>
      </html-el:form>
      <br />
      <form id="bottomPageForm" name="bottomPageForm" method="post" action="RetailReturnQuery.do">
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