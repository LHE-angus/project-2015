<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${naviString}</title>
<style>
.oarcont {float:left;width:100%;height:100%;background:#fff;font-size: 12px;}
.rtabcont1 {margin:8px 7px;clear:both;}

.rtable6 {border-top:1px #ccc solid;border-left:1px #ccc solid;}
.rtable6 td {border-right:1px #A1A1A1 solid;border-bottom:1px #A1A1A1 solid;padding:5px 5px 0px 5px;}
.rtable6 .tabtt6 {height:23px;background:#ED7676;}
.rtable6 .tabtt6 td {border-right:1px #e3e3e3 solid;border-bottom:1px #C00 solid;padding:5px 5px 0px 5px;;font:bold 12px "宋体";color:#FFFFFF;}
</style>
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <h1 align="center" style="padding-top: 20px;font-size: 22px;">${af.map.year}年${mm}月
    <c:choose>
      <c:when test="${jb_type eq 1}">A</c:when>
      <c:when test="${jb_type eq 2}">B</c:when>
      <c:when test="${jb_type eq 3}">C</c:when>
      <c:otherwise>A、B、C</c:otherwise>
    </c:choose>
    类经办回款业绩通报（不含业绩划拨）</h1>
  <div><span style="color: gray;padding-left: 10px;">提示：“--”表示为无数据！&nbsp;数据最新同步时间：${new_date}</span><span style="color: red;float:right; padding-right: 10px;" >单位:万元</span></div>
  <div class="rtabcont1" 	>
    <%@ include file="/commons/pages/messages.jsp" %>
    <table class="rtable6" width="100%" cellspacing="1" cellpadding="0" border="0">
      <tr bgcolor="#7EC0EE">
        <td width="7%" nowrap="nowrap" align="center">分公司</td>
        <td nowrap="nowrap" align="center">经办名称</td>
        <td width="7%" nowrap="nowrap" align="center">经办类型</td>
        <td width="7%" nowrap="nowrap" align="center">经办经理</td>
        <td width="7%" nowrap="nowrap" align="center">任务系数</td>
        <td width="7%" nowrap="nowrap" align="center">当月任务</td>
        <td width="7%" nowrap="nowrap" align="center">当月回款</td>
        <td width="7%" nowrap="nowrap" align="center">去年同期</td>
        <td width="7%" nowrap="nowrap" align="center">回款完成率<br />%</td>
        <td width="7%" nowrap="nowrap" align="center">同比增长率<br />%</td>
        <td width="7%" align="center">回款完成<br />率排名</td>
        <td width="7%" align="center">同比增长<br />率排名</td>
        <td width="7%" nowrap="nowrap" align="center">回款排名<br />模拟奖金</td>
        <td width="7%" nowrap="nowrap" align="center">同比增长<br />模拟奖金</td>
      </tr>
      <c:forEach var="cur" items="${entityList}" varStatus="vs">
       <c:choose>
        <c:when test="${vs.count le 10}">
	        <tr style="background-color: #FFEBCD;">
	          <td nowrap="nowrap" align="left">${cur.map.dept_name}</td>
	          <td nowrap="nowrap" align="left">${cur.map.jb_name}</td>
	          <td nowrap="nowrap" align="left">
	          	<c:choose>
	          		<c:when test="${cur.map.jb_type eq 1}">A类</c:when>
	          		<c:when test="${cur.map.jb_type eq 2}">B类</c:when>
	          		<c:when test="${cur.map.jb_type eq 3}">C类</c:when>
	          	</c:choose>
	          </td>
	          <td nowrap="nowrap" align="left">${cur.map.jb_jl}<c:if test="${empty cur.map.jb_jl}">--</c:if></td>
	          <td nowrap="nowrap" align="right">${cur.map.task}
	            <c:if test="${empty cur.map.task}"><span title="未设置任务系数">--</span></c:if></td>
	          <td nowrap="nowrap" align="right" ><fmt:formatNumber value="${cur.map.task_money}" pattern="###,##0" />
              <c:if test="${empty cur.map.task_money}"><span title="未设置当月任务">--</span></c:if></td>
              <td nowrap="nowrap" align="right" ><fmt:formatNumber value="${cur.map.hk_money}" pattern="###,##0" />
              <c:if test="${empty cur.map.hk_money}">0</c:if></td>
              <td nowrap="nowrap" align="right" ><fmt:formatNumber value="${cur.map.l_hk_money}" pattern="###,##0" />
              <c:if test="${empty cur.map.l_hk_money}">0</c:if></td>
	          <td nowrap="nowrap" align="right" style="color: red;">${cur.map.hk_wcl}</td>
	          <td nowrap="nowrap" align="right" style="color: ${(cur.map.hk_zzl lt 0) ? 'green' : 'red'};">${cur.map.hk_zzl}</td>
	          <td nowrap="nowrap" align="center">${cur.map.hk_wc_pm}</td>
	          <td nowrap="nowrap" align="center">${cur.map.hk_zz_pm}</td>
	          <c:choose>
	          <c:when test="${cur.map.jj_hk_wcl gt 0}">
	          <td nowrap="nowrap" align="center" style="color: red;">${cur.map.jj_hk_wcl}</td>
	          </c:when>
	          <c:otherwise>
	          <td nowrap="nowrap" align="center" >&nbsp;</td>
	          </c:otherwise>
	          </c:choose>
	          <c:choose>
	          <c:when test="${cur.map.jj_hk_zzl gt 0}">
	          <td nowrap="nowrap" align="center" style="color: red;">${cur.map.jj_hk_zzl}</td>
	          </c:when>
	          <c:otherwise>
	          <td nowrap="nowrap" align="center" >&nbsp;</td>
	          </c:otherwise>
	          </c:choose>
	        </tr>
        </c:when>
        <c:when test="${(vs.count gt (size -10)) && (vs.count le size)}">
	        <tr style="background-color:#EDEDED;">
	          <td nowrap="nowrap" align="left">${cur.map.dept_name}</td>
	          <td nowrap="nowrap" align="left">${cur.map.jb_name}</td>
	          <td nowrap="nowrap" align="left">
	          	<c:choose>
	          		<c:when test="${cur.map.jb_type eq 1}">A类</c:when>
	          		<c:when test="${cur.map.jb_type eq 2}">B类</c:when>
	          		<c:when test="${cur.map.jb_type eq 3}">C类</c:when>
	          	</c:choose>
	          </td>
	          <td nowrap="nowrap" align="left">${cur.map.jb_jl}<c:if test="${empty cur.map.jb_jl}">--</c:if></td>
	          <td nowrap="nowrap" align="right">${cur.map.task}
	            <c:if test="${empty cur.map.task}"><span title="未设置任务系数">--</span></c:if></td>
	          <td nowrap="nowrap" align="right" ><fmt:formatNumber value="${cur.map.task_money}" pattern="###,##0" />
              <c:if test="${empty cur.map.task_money}"><span title="未设置当月任务">--</span></c:if></td>
              <td nowrap="nowrap" align="right" ><fmt:formatNumber value="${cur.map.hk_money}" pattern="###,##0" />
              <c:if test="${empty cur.map.hk_money}">0</c:if></td>
              <td nowrap="nowrap" align="right" ><fmt:formatNumber value="${cur.map.l_hk_money}" pattern="###,##0" />
              <c:if test="${empty cur.map.l_hk_money}">0</c:if></td>
	          <td nowrap="nowrap" align="right" style="color: red;">${cur.map.hk_wcl}</td>
	          <td nowrap="nowrap" align="right" style="color: ${(cur.map.hk_zzl le 0) ? 'green' : 'red'}">${cur.map.hk_zzl}</td>
	          <td nowrap="nowrap" align="center">${cur.map.hk_wc_pm}</td>
	          <td nowrap="nowrap" align="center">${cur.map.hk_zz_pm}</td>
	          <c:choose>
          	  <c:when test="${cur.map.jj_hk_wcl gt 0}">
          	  <td nowrap="nowrap" align="center" style="color: red;">${cur.map.jj_hk_wcl}</td>
          	  </c:when>
         	  <c:otherwise>
	          <td nowrap="nowrap" align="center" >&nbsp;</td>
	          </c:otherwise>
	          </c:choose>
	          <c:choose>
	          <c:when test="${cur.map.jj_hk_zzl gt 0}">
	          <td nowrap="nowrap" align="center" style="color: red;">${cur.map.jj_hk_zzl}</td>
	          </c:when>
	          <c:otherwise>
	          <td nowrap="nowrap" align="center" >&nbsp;</td>
	          </c:otherwise>
	          </c:choose>
	        </tr>
        </c:when>
         <c:otherwise>
         	<tr>
	          <td nowrap="nowrap" align="left">${cur.map.dept_name}</td>
	          <td nowrap="nowrap" align="left">${cur.map.jb_name}</td>
	          <td nowrap="nowrap" align="left">
	          	<c:choose>
	          		<c:when test="${cur.map.jb_type eq 1}">A类</c:when>
	          		<c:when test="${cur.map.jb_type eq 2}">B类</c:when>
	          		<c:when test="${cur.map.jb_type eq 3}">C类</c:when>
	          	</c:choose>
	          </td>
	          <td nowrap="nowrap" align="left">${cur.map.jb_jl}<c:if test="${empty cur.map.jb_jl}">--</c:if></td>
	          <td nowrap="nowrap" align="right">${cur.map.task}
	            <c:if test="${empty cur.map.task}"><span title="未设置任务系数">--</span></c:if></td>
	          <td nowrap="nowrap" align="right" ><fmt:formatNumber value="${cur.map.task_money}"  maxFractionDigits="0" />
              <c:if test="${empty cur.map.task_money}"><span title="未设置当月任务">--</span></c:if></td>
              <td nowrap="nowrap" align="right" ><fmt:formatNumber value="${cur.map.hk_money}"  maxFractionDigits="0" />
              <c:if test="${empty cur.map.hk_money}">0</c:if></td>
              <td nowrap="nowrap" align="right" ><fmt:formatNumber value="${cur.map.l_hk_money}"  maxFractionDigits="0" />
              <c:if test="${empty cur.map.l_hk_money}">0</c:if></td>
	          <td nowrap="nowrap" align="right" style="color: red;">${cur.map.hk_wcl}</td>
	          <td nowrap="nowrap" align="right" style="color: ${(cur.map.hk_zzl le 0) ? 'green' : 'red'}">${cur.map.hk_zzl}</td>
	          <td nowrap="nowrap" align="center">${cur.map.hk_wc_pm}</td>
	          <td nowrap="nowrap" align="center">${cur.map.hk_zz_pm}</td>
	          <c:choose>
	          <c:when test="${cur.map.jj_hk_wcl gt 0}">
	          <td nowrap="nowrap" align="center" style="color: red;">${cur.map.jj_hk_wcl}</td>
	          </c:when>
	          <c:otherwise>
	          <td nowrap="nowrap" align="center" >&nbsp;</td>
	          </c:otherwise>
	          </c:choose>
	          <c:choose>
	          <c:when test="${cur.map.jj_hk_zzl gt 0}">
	          <td nowrap="nowrap" align="center" style="color: red;">${cur.map.jj_hk_zzl}</td>
	          </c:when>
	          <c:otherwise>
	          <td nowrap="nowrap" align="center" >&nbsp;</td>
	          </c:otherwise>
	          </c:choose>
	        </tr>
         </c:otherwise>
        </c:choose>
      </c:forEach>
      <tr>
      	<td nowrap="nowrap" align="left" style="font-weight:600;">合计</td>
      	<td nowrap="nowrap" align="left" style="font-weight:600;">---</td>
      	<td nowrap="nowrap" align="left" style="font-weight:600;">---</td>
      	<td nowrap="nowrap" align="left" style="font-weight:600;">---</td>
      	<td nowrap="nowrap" align="right" style="font-weight:600;">${task}
         <c:if test="${empty task}">--</c:if></td>
         <td nowrap="nowrap" align="right" style="font-weight:600;">${task_money}
         <c:if test="${empty task_money}">--</c:if></td>
         <td nowrap="nowrap" align="right" style="font-weight:600;">${hk_money}
         <c:if test="${empty hk_money}">--</c:if></td>
         <td nowrap="nowrap" align="right" style="font-weight:600;">${l_hk_money}
         <c:if test="${empty l_hk_money}">--</c:if></td>
         <td nowrap="nowrap" align="right" style="font-weight:600;">${t_hk_wcl}
         <c:if test="${empty t_hk_wcl}">--</c:if></td>
         <td nowrap="nowrap" align="right" style="font-weight:600;">${t_hk_zzl}
         <c:if test="${empty t_hk_zzl}">--</c:if></td>
      	<td nowrap="nowrap" align="center" style="font-weight:600;">---</td>
      	<td nowrap="nowrap" align="center" style="font-weight:600;">---</td>
      	<td nowrap="nowrap" align="center" style="font-weight:600;">${t_jj_hk_wcl}</td>
      	<td nowrap="nowrap" align="center" style="font-weight:600;">${t_jj_hk_zzl}</td>
      </tr>
    </table>
  </div>
  <div>&nbsp;&nbsp;备注：数据采集于R3系统，采集时间段为&nbsp;
  ${firstDay}--${yestDay}
</div>
</div>
<script type="text/javascript">//<![CDATA[ 	                                    
//]]></script>
</body>
</html>