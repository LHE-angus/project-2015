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
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/admin/KonkaR3DeptStockInfo">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="tree_param" value="${tree_param}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="15"></td>
          <td>
		    &nbsp;<strong class="fb">年度：</strong>
	        <html-el:select property="year" styleId="year">
	          <c:forEach items="${yearList}" var="cur">
	            <html-el:option value="${cur}">${cur}年</html-el:option>
	          </c:forEach>
	        </html-el:select>
	        <html-el:select property="month" styleId="month">
              <html-el:option value="01">1月</html-el:option>
              <html-el:option value="02">2月</html-el:option>
              <html-el:option value="03">3月</html-el:option>
              <html-el:option value="04">4月</html-el:option>
              <html-el:option value="05">5月</html-el:option>
              <html-el:option value="06">6月</html-el:option>
              <html-el:option value="07">7月</html-el:option>
              <html-el:option value="08">8月</html-el:option>
              <html-el:option value="09">9月</html-el:option>
              <html-el:option value="10">10月</html-el:option>
              <html-el:option value="11">11月</html-el:option>
              <html-el:option value="12">12月</html-el:option>
            </html-el:select>	          
           	&nbsp;<strong class="fb">片区：</strong>
            <html-el:text property="v_bzirk" styleId="v_bzirk" size="16" maxlength="20"></html-el:text>
            &nbsp;<strong class="fb">分公司：</strong>
            <html-el:text property="v_class_like" styleId="v_class_like" size="16" maxlength="20"></html-el:text>
            &nbsp;
          	<strong class="fb">物料：</strong>
            <html-el:text property="v_matnr_like" styleId="v_matnr_like" size="16" maxlength="20"></html-el:text>
            &nbsp;&nbsp;
            <input class="but1" type="submit" name="Submit" value="搜索" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1" style="overflow-x: auto;">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
      <tr class="tabtt1">
        <td nowrap="nowrap" align="center" width="5%">序号</td>
        <td nowrap="nowrap" align="center" width="8%">片区代码</td>
        <td nowrap="nowrap">分公司</td>
        <td nowrap="nowrap" align="center" width="8%">经营部</td>
        <td nowrap="nowrap" align="center" width="8%">物料</td>
        
        <td nowrap="nowrap" align="center" width="8%">上月销量</td>
        <td nowrap="nowrap" align="center" width="8%">本月销量</td>
        <td nowrap="nowrap" align="center" width="8%">总量</td>
        
        <td nowrap="nowrap" align="center" width="8%">在途数量</td>
        <td nowrap="nowrap" align="center" width="8%">未发数量</td>
        <td nowrap="nowrap" align="center" width="8%">铺底60仓</td>
        <td nowrap="nowrap" align="center" width="8%">周转90仓</td>
        
        <td nowrap="nowrap" align="center" width="8%">经营部F仓</td>
        <td nowrap="nowrap" align="center" width="8%">样机Y仓</td>
        <td nowrap="nowrap" align="center" width="8%">质检Q仓</td>
        <td align="center" nowrap="nowrap" width="8%">退机T仓</td>
        
        <td align="center" nowrap="nowrap" width="8%">铺底P仓</td>
        <td align="center" nowrap="nowrap" width="8%">周转Z仓</td>
        <td align="center" nowrap="nowrap" width="8%">商务B仓</td>
        <td align="center" nowrap="nowrap" width="8%">电子商务仓</td>
	  </tr>
      <tbody>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
             <td align="center" nowrap="nowrap" >${cur.bzirk}</td>
	         <td align="center" nowrap="nowrap" >${cur.class1}</td>
	         <td align="center" nowrap="nowrap" >${cur.class2}</td>
	         <td align="center" nowrap="nowrap" >${cur.matnr}</td>
	         
	         <td align="right" nowrap="nowrap" ><fmt:formatNumber value="${cur.lfimg}" pattern="###,##0" /></td>
	         <td align="right" nowrap="nowrap" ><fmt:formatNumber value="${cur.lfimg1}" pattern="###,##0" /></td>
	         <td align="right" nowrap="nowrap" ><fmt:formatNumber value="${cur.sum_}" pattern="###,##0" /></td>
	      
	         <td align="right" nowrap="nowrap" ><fmt:formatNumber value="${cur.mzt}" pattern="###,##0" /></td>
	         <td align="right" nowrap="nowrap" ><fmt:formatNumber value="${cur.mwf}" pattern="###,##0" /></td>
	         <td align="right" nowrap="nowrap" ><fmt:formatNumber value="${cur.pdlabst}" pattern="###,##0" /></td>
	         <td align="right" nowrap="nowrap" ><fmt:formatNumber value="${cur.zzlabst}" pattern="###,##0" /></td>
	      
	         <td align="right" nowrap="nowrap" ><fmt:formatNumber value="${cur.jylabst}" pattern="###,##0" /></td>
	         <td align="right" nowrap="nowrap" ><fmt:formatNumber value="${cur.yjlabst}" pattern="###,##0" /></td>
	         <td align="right" nowrap="nowrap" ><fmt:formatNumber value="${cur.cllabst}" pattern="###,##0" /></td>
	         <td align="right" nowrap="nowrap" ><fmt:formatNumber value="${cur.tjlabst}" pattern="###,##0" /></td>
	      
	         <td align="right" nowrap="nowrap" ><fmt:formatNumber value="${cur.pclabst}" pattern="###,##0" /></td>
	         <td align="right" nowrap="nowrap" ><fmt:formatNumber value="${cur.zclabst}" pattern="###,##0" /></td>
	         <td align="right" nowrap="nowrap" ><fmt:formatNumber value="${cur.bclabst}" pattern="###,##0" /></td>
	         <td align="right" nowrap="nowrap" ><fmt:formatNumber value="${cur.dzlabst}" pattern="###,##0" /></td>
          </tr>
        </c:forEach>
        <c:forEach begin="${fn:length(entityList)}" end="${af.map.pager.pageSize - 1}" step="1">
          <tr>
           	<td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaR3DeptStockInfo.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40"><div style="text-align: right; padding-right: 5px;">
              <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
              <script type="text/javascript">
				var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
				pager.addHiddenInputs("method", "list");
				pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
				pager.addHiddenInputs("tree_param", "${tree_param}");
				pager.addHiddenInputs("v_bzirk", "${af.map.v_bzirk}");
				pager.addHiddenInputs("v_class_like", "${af.map.v_class_like}");
				pager.addHiddenInputs("v_matnr_like", "${af.map.v_matnr_like}");
				pager.addHiddenInputs("v_matkl", "${af.map.v_matkl}");
				pager.addHiddenInputs("year", "${af.map.year}");
				pager.addHiddenInputs("month", "${af.map.month}");
				document.write(pager.toString());
			  </script>
            </div></td>
        </tr>
      </table>
    </form>
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript">//<![CDATA[
//]]>
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
