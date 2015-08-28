<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<style>
.bg_g{
	background-color: #97FFFF;
}
.bg_c{
	background-color: #EE9A49;
}
.bg_y{
	background-color: #FFEC8B;
}
</style>
</head>
<body>
<div id="body_oarcont">
  <div>
    <table width="100%" cellspacing="1" cellpadding="0" border="1">
      <tr >
      	<td height="20" width="7%" nowrap="nowrap" align="center" class="bg_g">片区代码</td>
        <td width="7%" nowrap="nowrap" align="center" class="bg_g">分公司</td>
        <td nowrap="nowrap" align="center" class="bg_g">机型</td>
        <td width="7%" nowrap="nowrap" align="center" class="bg_g">铺底60</td>
        <td width="7%" nowrap="nowrap" align="center" class="bg_g">铺底P仓</td>
        <td width="7%" nowrap="nowrap" align="center" class="bg_c">周转90仓</td>
        <td width="7%" nowrap="nowrap" align="center" class="bg_g">商务仓</td>
        <td width="7%" nowrap="nowrap" align="center" class="bg_g">经营部F仓</td>
        
        <td width="7%" nowrap="nowrap" align="center" class="bg_g">样机Y仓</td>
        <td width="7%" nowrap="nowrap" align="center" class="bg_g">质检Q仓</td>
        <td width="7%" nowrap="nowrap" align="center" class="bg_g">退机T仓</td>
        <td width="7%" nowrap="nowrap" align="center" class="bg_g">周转Z仓</td>
        
        <td width="7%" nowrap="nowrap" align="center" class="bg_g">电子商务仓</td>
        <td width="7%" nowrap="nowrap" align="center" class="bg_g">在途</td>
        <td width="7%" nowrap="nowrap" align="center" class="bg_g">未发</td>
        <td width="7%" nowrap="nowrap" align="center" class="bg_g">总量</td>
        
        <td width="7%" nowrap="nowrap" align="center" class="bg_g">上月销量</td>
        <td width="7%" nowrap="nowrap" align="center" class="bg_c">本月销量</td>
        <td width="7%" nowrap="nowrap" align="center" class="bg_y">周转率%</td>
      </tr>
      <c:forEach var="cur" items="${entityList1}" varStatus="vs">
        <tr>
          <td>${cur.bzirk}</td><td>${cur.class1}</td><td>${cur.matnr}</td><td><fmt:formatNumber value="${cur.pdlabst}" pattern="###,##0" /></td><td><fmt:formatNumber value="${cur.pclabst}" pattern="###,##0" /></td><td><fmt:formatNumber value="${cur.zzlabst}" pattern="###,##0" /></td><td><fmt:formatNumber value="${cur.bclabst}" pattern="###,##0" /></td><td><fmt:formatNumber value="${cur.jylabst}" pattern="###,##0" /></td><td><fmt:formatNumber value="${cur.yjlabst}" pattern="###,##0" /></td><td><fmt:formatNumber value="${cur.cllabst}" pattern="###,##0" /></td><td><fmt:formatNumber value="${cur.tjlabst}" pattern="###,##0" /></td><td><fmt:formatNumber value="${cur.zclabst}" pattern="###,##0" /></td><td><fmt:formatNumber value="${cur.dzlabst}" pattern="###,##0" /></td><td><fmt:formatNumber value="${cur.mzt}" pattern="###,##0" /></td><td><fmt:formatNumber value="${cur.mwf}" pattern="###,##0" /></td><td><fmt:formatNumber value="${cur.sum_}" pattern="###,##0" /></td><td><fmt:formatNumber value="${cur.lfimg1}" pattern="###,##0" /></td><td><fmt:formatNumber value="${cur.lfimg}" pattern="###,##0" /></td><td class="bg_y"><fmt:formatNumber value="${cur.map.zzl}" pattern="###,##0.00" /></td>
        </tr>
      </c:forEach>
        <tr>
          <td nowrap="nowrap" colspan="3" align="left">合计</td><td><fmt:formatNumber value="${v_pdlabst_1}" pattern="###,##0" /></td><td><fmt:formatNumber value="${v_pclabst_1}" pattern="###,##0" /></td><td><fmt:formatNumber value="${v_zzlabst_1}" pattern="###,##0" /></td><td><fmt:formatNumber value="${v_bclabst_1}" pattern="###,##0" /></td><td><fmt:formatNumber value="${v_jylabst_1}" pattern="###,##0" /></td><td><fmt:formatNumber value="${v_yjlabst_1}" pattern="###,##0" /></td><td><fmt:formatNumber value="${v_cllabst_1}" pattern="###,##0" /></td><td><fmt:formatNumber value="${v_tjlabst_1}" pattern="###,##0" /></td><td><fmt:formatNumber value="${v_zclabst_1}" pattern="###,##0" /></td><td><fmt:formatNumber value="${v_dzlabst_1}" pattern="###,##0" /></td><td><fmt:formatNumber value="${v_mzt_1}" pattern="###,##0" /></td><td><fmt:formatNumber value="${v_mwf_1}" pattern="###,##0" /></td><td><fmt:formatNumber value="${v_sum_1}" pattern="###,##0" /></td><td><fmt:formatNumber value="${v_lfimg1_1}" pattern="###,##0" /></td><td><fmt:formatNumber value="${v_lfimg_1}" pattern="###,##0" /></td><td class="bg_y"><fmt:formatNumber value="${v_zzl_1}" pattern="###,##0.00" /></td>
        </tr>
    </table>
  </div>
</div>
</body>
</html>