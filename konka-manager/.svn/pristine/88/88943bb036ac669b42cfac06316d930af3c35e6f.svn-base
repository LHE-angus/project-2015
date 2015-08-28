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
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script> 
<script type="text/javascript"  src="${ctx}/commons/scripts/imgpreview.0.22.js"></script> 
<script type="text/javascript" src="${ctx}/javascripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script> 
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
       <c:if test="${102020 eq af.map.mod_id }"> <td>当前位置：客户管理&nbsp;&gt;&nbsp;代理商网点&nbsp;&gt;&nbsp;网点经营分析</td></c:if>
       <c:if test="${102010 eq af.map.mod_id }"> <td>当前位置：客户管理&nbsp;&gt;&nbsp;代理商网点&nbsp;&gt;&nbsp;网点列表&nbsp;&gt;&nbsp;代理商下的网点</td></c:if>
        <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align:middle;" /> <span id="span_help" style="cursor:pointer;">帮助</span></td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/admin/AgentsList">
      <html-el:hidden property="method" value="jxslist" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="mod_code" value="${af.map.mod_code}" />
      <html-el:hidden property="r3_id" value="${af.map.r3_id}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="15"></td>        
          <td><strong class="fb">网点名称：</strong>
          <html-el:text property="jxs_name_like" size="20" maxlength="20" styleId="jxs_name_like"  />
          &nbsp;&nbsp;<html-el:submit styleClass="but1" value="搜索" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
    <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back()" />
  </div>
  <div class="rtabcont1">
    <html-el:form action="/admin/AgentsList" method="post">
      <html-el:hidden property="method" value="shopDispach" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="tree_param" value="${tree_param}" />
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
        <tr class="tabtt1">
          <td width="5%" align="center" nowrap="nowrap">序号</td>
          <td nowrap="nowrap">经销商名称</td>
          <!-- <td nowrap="nowrap" align="center" width="100">操作</td> -->
        </tr>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
            <td align="left"><a style="cursor:pointer;color:blue;" href="KonkaR3MmtMatch.do?method=detail&id=${cur.map.dls_shops.id}&mod_id=${af.map.mod_id}&key=1">${cur.map.dls_shops.customer_name}</a></td>
            <!-- <td align="center" nowrap="nowrap"><span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'EntpShopSellAnalysis.do', 'list','back_type=1&shop_id=${cur.mmt_shop_id}&page_type=0&type=1');">经营情况</span> -->    
           <!--  <c:if test="${not empty cur.mmt_shop_id }">
             | <span style="cursor:pointer;" class="fblue" id="map" onclick="doNeedMethod(null, 'R3UserOperateAnalysis.do', 'edit','shop_id=${cur.mmt_shop_id}&mod_code=${af.map.dls_shops.mod_code }&'+$('#bottomPageForm').serialize())">修改网点</span>
             </c:if>
             <c:if test="${empty cur.mmt_shop_id }">
               | <span style="color: #ccc" class="fblue" title="未成功匹配MMT网点的网点不可以修改">修改网点</span>
             </c:if> -->
           </td>
          </tr>
          <c:if test="${vs.last eq true}">
            <c:set var="i" value="${vs.count}" />
          </c:if>
        </c:forEach>
        <c:forEach begin="${i}" end="${af.map.pager.pageSize - 1}">
          <tr align="center">
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
        </c:forEach>
      </table>
    </html-el:form>
    <br />
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="AgentsList.do">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
            <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "jxslist");
            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");	
			pager.addHiddenInputs("jxs_name_like", "${af.map.jxs_name_like}");	
	        pager.addHiddenInputs("r3_id", "${af.map.r3_id}");
            document.write(pager.toString());
            </script></td>
        </tr>
      </table>
    </form>
  </div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>

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