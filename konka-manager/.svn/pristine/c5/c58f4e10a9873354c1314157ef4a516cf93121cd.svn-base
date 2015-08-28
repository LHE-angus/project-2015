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
<link href="${ctx}/styles/customer/XYTips/style.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/manager/admin/KonkaCompetProdManage/tips/style.css" rel="stylesheet" type="text/css" />
<style type="text/css">
input,textarea,select,file,button{font-family:Microsoft Yahei;font-size:12px;}
</style>
</head>
<body style="font-family:Microsoft Yahei;">
<div class="oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
	  <div class="rtabcont1">
		<%@ include file="/commons/pages/messages.jsp"%>
	  </div>
  	<html-el:form action="/admin/SalaryManage" enctype="multipart/form-data" method="post" >
  		<html-el:hidden property="method" value="save" />
	    <html-el:hidden property="id" />
	    <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
	    <html-el:hidden property="queryString" />
	    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
	    	<tr>
	  	  		<td align="center" colspan="2" style="font-weight:900;">员工工资明细表导入</td>
	  	  	</tr>
	  	  	<tr>
	  	  		<td width="12%" class="title_item" nowrap="nowrap">年份：</td>
	  	  		<td align="left">
	  	  			<html-el:select property="year" styleId="year">
	  	  				<html-el:option value="">=请选择=</html-el:option>
	  	  				<html-el:option value="${year - 2}">${year - 2}年</html-el:option>
	  	  				<html-el:option value="${year - 1}">${year - 1}年</html-el:option>
	  	  				<html-el:option value="${year}">${year}年</html-el:option>
	  	  			</html-el:select>
	  	  		</td>
	  	  	</tr>
	  	  	<tr>
	  	  		<td class="title_item" nowrap="nowrap">月份：</td>
	  	  		<td align="left">
	  	  			<html-el:select property="month" styleId="month">
	  	  				<html-el:option value="">=请选择=</html-el:option>
	  	  				<html-el:option value="1">1月</html-el:option>
	  	  				<html-el:option value="2">2月</html-el:option>
	  	  				<html-el:option value="3">3月</html-el:option>
	  	  				<html-el:option value="4">4月</html-el:option>
	  	  				<html-el:option value="5">5月</html-el:option>
	  	  				<html-el:option value="6">6月</html-el:option>
	  	  				<html-el:option value="7">7月</html-el:option>
	  	  				<html-el:option value="8">8月</html-el:option>
	  	  				<html-el:option value="9">9月</html-el:option>
	  	  				<html-el:option value="10">10月</html-el:option>
	  	  				<html-el:option value="11">11月</html-el:option>
	  	  				<html-el:option value="12">12月</html-el:option>
	  	  			</html-el:select>
	  	  		</td>
	  	  	</tr>
	    	<tr>
			  	<td class="title_item" nowrap="nowrap">模板：</td>
			  	<td style="text-align:left;padding-left:5px;">
			  		<a href="${ctx}/manager/admin/SalaryManage/template/0f848d1e-eb33-4cbe-b1fa-d73c3ef121e3_060.xls">模板下载</a>
			  	</td>
		  	</tr>
	  	  	<tr>
			  	<td nowrap="nowrap" class="title_item">文件上传：</td>
			  	<td style="text-align:left;padding-left:5px;">
			  		<input type="file" name="up_load_file" id="up_load_file" style="width:250px" />
			  		<c:if test="${not empty entityList}">
			  			&nbsp;&nbsp;<a href="javascript:viewErrorInfoDiv();">查看错误明细</a>
			  		</c:if>
			  	</td>
		  	</tr>
		  	<tr>
			  	<td align="center" colspan="2">
			  		<html-el:button property="btn_submit" styleId="btn_submit" styleClass="but_excel" style="font-family:Microsoft Yahei;font-size:12px;" value="导入  " />
			  	</td>
		  	</tr>
	    </table>
  	</html-el:form>
  </div>
</div>

<!-- 导出错误明细 -->
<c:if test="${not empty entityList}">
	<div id="errorInfo" style="width:95%">
		<table>
		  	<tr>
		  		<td width="15%" height="70"><img src="${ctx}/images/manager/cry1.gif" alt="" style="vertical-align:middle;" /></td>
				<td nowrap="nowrap">
					<span style="font-family:'Microsoft yahei','黑体','宋体';font-size:18px;vertical-align:middle;color:#FF0000;line-height:26px;">抱歉，导入数据失败！</span><br />
		 			<span style="font-family:'Microsoft yahei','黑体','宋体';font-size:14px;vertical-align:middle;color:#666;line-height:26px;">请将鼠标移至下表中的标记处查看详细错误信息</span>
				</td>
			</tr>
		</table>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
  	  		<tr class="tabtt1">
  	  			<td width="3%" nowrap="nowrap" align="center">序号</td>
  	  			<td nowrap="nowrap" align="center">年份</td>
  	  			<td nowrap="nowrap" align="center">月份</td>
  	  			<td nowrap="nowrap" align="center">身份证号码</td>
  	  			<td nowrap="nowrap" align="center">入职日期</td>
  	  			<td nowrap="nowrap" align="center">入职年限</td>
  	  			<td nowrap="nowrap" align="center">员工姓名</td>
  	  			<td nowrap="nowrap" align="center">月度排名</td>
  	  			<td nowrap="nowrap" align="center">标准工资</td>
  	  			<td nowrap="nowrap" align="center">基本工资</td>
  	  			<td nowrap="nowrap" align="center">工龄工资</td>
  	  			<td nowrap="nowrap" align="center">浮动工资</td>
  	  			<td nowrap="nowrap" align="center">补助津贴</td>
  	  			<td nowrap="nowrap" align="center">提成奖金</td>
  	  			<td nowrap="nowrap" align="center">加班费</td>
  	  			<td nowrap="nowrap" align="center">奖励金额</td>
  	  			<td nowrap="nowrap" align="center">扣款金额</td>
  	  			<td nowrap="nowrap" align="center">销售额</td>
  	  			<td nowrap="nowrap" align="center">销售量</td>
  	  			<td nowrap="nowrap" align="center">负毛利</td>
  	  			<td nowrap="nowrap" align="center">社保</td>
  	  			<td nowrap="nowrap" align="center">公积金</td>
  	  			<td nowrap="nowrap" align="center">税前工资</td>
  	  			<td nowrap="nowrap" align="center">税金</td>
  	  			<td nowrap="nowrap" align="center">税后工资</td>
  	  			<td nowrap="nowrap" align="center">其他</td>
  	  			<td nowrap="nowrap" align="center">实发工资</td>
  	  		</tr>
  	  		<c:forEach items="${entityList}" var="cur" varStatus="vs">
  	  			<tr>
          			<td align="center">${vs.count}</td>
          			<!-- 年份 -->
          			<c:choose>
	          			<c:when test="${empty cur.map.msg1}">
			  				<td nowrap="nowrap" style="text-align:center;">${fn:replace(cur.y, " ", "&nbsp;")}</td>
			  			</c:when>
			  			<c:otherwise>
			  				<td nowrap="nowrap" style="text-align:center;color:#CD0000;background:#FFFF00;cursor:pointer;" onmouseover="tooltip.show('<strong>${cur.map.msg1}</strong>');" onmouseout="tooltip.hide();">${fn:replace(cur.map.con1, " ", "&nbsp;")}</td>
			  			</c:otherwise>
	       			</c:choose>
          			
          			<!-- 月份 -->
          			<c:choose>
	          			<c:when test="${empty cur.map.msg2}">
			  				<td nowrap="nowrap" style="text-align:center;">${fn:replace(cur.m, " ", "&nbsp;")}</td>
			  			</c:when>
			  			<c:otherwise>
			  				<td nowrap="nowrap" style="text-align:center;color:#CD0000;background:#FFFF00;cursor:pointer;" onmouseover="tooltip.show('<strong>${cur.map.msg2}</strong>');" onmouseout="tooltip.hide();">${fn:replace(cur.map.con2, " ", "&nbsp;")}</td>
			  			</c:otherwise>
	       			</c:choose>
          			
          			<!-- 身份证号码 -->
          			<c:choose>
	          			<c:when test="${empty cur.map.msg3}">
			  				<td nowrap="nowrap" style="text-align:left;">${fn:replace(cur.id_card_no, " ", "&nbsp;")}</td>
			  			</c:when>
			  			<c:otherwise>
			  				<td nowrap="nowrap" style="text-align:left;color:#CD0000;background:#FFFF00;cursor:pointer;" onmouseover="tooltip.show('<strong>${cur.map.msg3}</strong>');" onmouseout="tooltip.hide();">${fn:replace(cur.map.con3, " ", "&nbsp;")}</td>
			  			</c:otherwise>
	       			</c:choose>
	       			
          			<!-- 入职日期 -->
          			<c:choose>
	          			<c:when test="${empty cur.map.msg4}">
			  				<td nowrap="nowrap" style="text-align:left;">${fn:replace(cur.employ_date, " ", "&nbsp;")}</td>
			  			</c:when>
			  			<c:otherwise>
			  				<td nowrap="nowrap" style="text-align:left;color:#CD0000;background:#FFFF00;cursor:pointer;" onmouseover="tooltip.show('<strong>${cur.map.msg4}</strong>');" onmouseout="tooltip.hide();">${fn:replace(cur.map.con4, " ", "&nbsp;")}</td>
			  			</c:otherwise>
	       			</c:choose>
	       			
          			<!-- 入职年限 -->
          			<c:choose>
	          			<c:when test="${empty cur.map.msg5}">
			  				<td nowrap="nowrap" style="text-align:left;">${fn:replace(cur.employ_years, " ", "&nbsp;")}</td>
			  			</c:when>
			  			<c:otherwise>
			  				<td nowrap="nowrap" style="text-align:left;color:#CD0000;background:#FFFF00;cursor:pointer;" onmouseover="tooltip.show('<strong>${cur.map.msg5}</strong>');" onmouseout="tooltip.hide();">${fn:replace(cur.map.con5, " ", "&nbsp;")}</td>
			  			</c:otherwise>
	       			</c:choose>
          			
          			<!-- 员工姓名 -->
          			<c:choose>
	          			<c:when test="${empty cur.map.msg6}">
			  				<td nowrap="nowrap" style="text-align:left;">${fn:replace(cur.real_name, " ", "&nbsp;")}</td>
			  			</c:when>
			  			<c:otherwise>
			  				<td nowrap="nowrap" style="text-align:left;color:#CD0000;background:#FFFF00;cursor:pointer;" onmouseover="tooltip.show('<strong>${cur.map.msg6}</strong>');" onmouseout="tooltip.hide();">${fn:replace(cur.map.con6, " ", "&nbsp;")}</td>
			  			</c:otherwise>
	       			</c:choose>
          			
          			<!-- 月度排名 -->
          			<c:choose>
	          			<c:when test="${empty cur.map.msg7}">
			  				<td nowrap="nowrap" style="text-align:left;">${fn:replace(cur.month_rank, " ", "&nbsp;")}</td>
			  			</c:when>
			  			<c:otherwise>
			  				<td nowrap="nowrap" style="text-align:left;color:#CD0000;background:#FFFF00;cursor:pointer;" onmouseover="tooltip.show('<strong>${cur.map.msg7}</strong>');" onmouseout="tooltip.hide();">${fn:replace(cur.map.con7, " ", "&nbsp;")}</td>
			  			</c:otherwise>
	       			</c:choose>
          			
          			<!-- 标准工资 -->
          			<c:choose>
	          			<c:when test="${empty cur.map.msg8}">
			  				<td nowrap="nowrap" style="text-align:right;">${fn:replace(cur.w_std, " ", "&nbsp;")}</td>
			  			</c:when>
			  			<c:otherwise>
			  				<td nowrap="nowrap" style="text-align:right;color:#CD0000;background:#FFFF00;cursor:pointer;" onmouseover="tooltip.show('<strong>${cur.map.msg8}</strong>');" onmouseout="tooltip.hide();">${fn:replace(cur.map.con8, " ", "&nbsp;")}</td>
			  			</c:otherwise>
	       			</c:choose>
          			
          			<!-- 基本工资 -->
          			<c:choose>
	          			<c:when test="${empty cur.map.msg9}">
			  				<td nowrap="nowrap" style="text-align:right;">${fn:replace(cur.w_base, " ", "&nbsp;")}</td>
			  			</c:when>
			  			<c:otherwise>
			  				<td nowrap="nowrap" style="text-align:right;color:#CD0000;background:#FFFF00;cursor:pointer;" onmouseover="tooltip.show('<strong>${cur.map.msg9}</strong>');" onmouseout="tooltip.hide();">${fn:replace(cur.map.con9, " ", "&nbsp;")}</td>
			  			</c:otherwise>
	       			</c:choose>
          			
          			<!-- 工龄工资 -->
          			<c:choose>
	          			<c:when test="${empty cur.map.msg10}">
			  				<td nowrap="nowrap" style="text-align:right;">${fn:replace(cur.employ_year_w, " ", "&nbsp;")}</td>
			  			</c:when>
			  			<c:otherwise>
			  				<td nowrap="nowrap" style="text-align:right;color:#CD0000;background:#FFFF00;cursor:pointer;" onmouseover="tooltip.show('<strong>${cur.map.msg10}</strong>');" onmouseout="tooltip.hide();">${fn:replace(cur.map.con10, " ", "&nbsp;")}</td>
			  			</c:otherwise>
	       			</c:choose>
          			
          			<!-- 浮动工资 -->
          			<c:choose>
	          			<c:when test="${empty cur.map.msg11}">
			  				<td nowrap="nowrap" style="text-align:right;">${fn:replace(cur.w_float, " ", "&nbsp;")}</td>
			  			</c:when>
			  			<c:otherwise>
			  				<td nowrap="nowrap" style="text-align:right;color:#CD0000;background:#FFFF00;cursor:pointer;" onmouseover="tooltip.show('<strong>${cur.map.msg11}</strong>');" onmouseout="tooltip.hide();">${fn:replace(cur.map.con11, " ", "&nbsp;")}</td>
			  			</c:otherwise>
	       			</c:choose>
          			
          			<!-- 补助津贴 -->
          			<c:choose>
	          			<c:when test="${empty cur.map.msg12}">
			  				<td nowrap="nowrap" style="text-align:right;">${fn:replace(cur.w_allowance, " ", "&nbsp;")}</td>
			  			</c:when>
			  			<c:otherwise>
			  				<td nowrap="nowrap" style="text-align:right;color:#CD0000;background:#FFFF00;cursor:pointer;" onmouseover="tooltip.show('<strong>${cur.map.msg12}</strong>');" onmouseout="tooltip.hide();">${fn:replace(cur.map.con12, " ", "&nbsp;")}</td>
			  			</c:otherwise>
	       			</c:choose>
          			
          			<!-- 提成奖金 -->
          			<c:choose>
	          			<c:when test="${empty cur.map.msg13}">
			  				<td nowrap="nowrap" style="text-align:right;">${fn:replace(cur.w_rewards, " ", "&nbsp;")}</td>
			  			</c:when>
			  			<c:otherwise>
			  				<td nowrap="nowrap" style="text-align:right;color:#CD0000;background:#FFFF00;cursor:pointer;" onmouseover="tooltip.show('<strong>${cur.map.msg13}</strong>');" onmouseout="tooltip.hide();">${fn:replace(cur.map.con13, " ", "&nbsp;")}</td>
			  			</c:otherwise>
	       			</c:choose>
          			
          			<!-- 加班费 -->
          			<c:choose>
	          			<c:when test="${empty cur.map.msg14}">
			  				<td nowrap="nowrap" style="text-align:right;">${fn:replace(cur.w_overtime, " ", "&nbsp;")}</td>
			  			</c:when>
			  			<c:otherwise>
			  				<td nowrap="nowrap" style="text-align:right;color:#CD0000;background:#FFFF00;cursor:pointer;" onmouseover="tooltip.show('<strong>${cur.map.msg14}</strong>');" onmouseout="tooltip.hide();">${fn:replace(cur.map.con14, " ", "&nbsp;")}</td>
			  			</c:otherwise>
	       			</c:choose>
          			
          			<!-- 奖励金额 -->
          			<c:choose>
	          			<c:when test="${empty cur.map.msg15}">
			  				<td nowrap="nowrap" style="text-align:right;">${fn:replace(cur.w_encourage, " ", "&nbsp;")}</td>
			  			</c:when>
			  			<c:otherwise>
			  				<td nowrap="nowrap" style="text-align:right;color:#CD0000;background:#FFFF00;cursor:pointer;" onmouseover="tooltip.show('<strong>${cur.map.msg15}</strong>');" onmouseout="tooltip.hide();">${fn:replace(cur.map.con15, " ", "&nbsp;")}</td>
			  			</c:otherwise>
	       			</c:choose>
          			
          			<!-- 扣款金额 -->
          			<c:choose>
	          			<c:when test="${empty cur.map.msg16}">
			  				<td nowrap="nowrap" style="text-align:right;">${fn:replace(cur.w_deduct, " ", "&nbsp;")}</td>
			  			</c:when>
			  			<c:otherwise>
			  				<td nowrap="nowrap" style="text-align:right;color:#CD0000;background:#FFFF00;cursor:pointer;" onmouseover="tooltip.show('<strong>${cur.map.msg16}</strong>');" onmouseout="tooltip.hide();">${fn:replace(cur.map.con16, " ", "&nbsp;")}</td>
			  			</c:otherwise>
	       			</c:choose>
          			
          			<!-- 销售额 -->
          			<c:choose>
	          			<c:when test="${empty cur.map.msg17}">
			  				<td nowrap="nowrap" style="text-align:right;">${fn:replace(cur.sale_money, " ", "&nbsp;")}</td>
			  			</c:when>
			  			<c:otherwise>
			  				<td nowrap="nowrap" style="text-align:right;color:#CD0000;background:#FFFF00;cursor:pointer;" onmouseover="tooltip.show('<strong>${cur.map.msg17}</strong>');" onmouseout="tooltip.hide();">${fn:replace(cur.map.con17, " ", "&nbsp;")}</td>
			  			</c:otherwise>
	       			</c:choose>
          			
          			<!-- 销售量-->
          			<c:choose>
	          			<c:when test="${empty cur.map.msg18}">
			  				<td nowrap="nowrap" style="text-align:right;">${fn:replace(cur.sale_num, " ", "&nbsp;")}</td>
			  			</c:when>
			  			<c:otherwise>
			  				<td nowrap="nowrap" style="text-align:right;color:#CD0000;background:#FFFF00;cursor:pointer;" onmouseover="tooltip.show('<strong>${cur.map.msg18}</strong>');" onmouseout="tooltip.hide();">${fn:replace(cur.map.con18, " ", "&nbsp;")}</td>
			  			</c:otherwise>
	       			</c:choose>
          			
          			<!-- 负毛利 -->
          			<c:choose>
	          			<c:when test="${empty cur.map.msg19}">
			  				<td nowrap="nowrap" style="text-align:right;">${fn:replace(cur.loss_profit, " ", "&nbsp;")}</td>
			  			</c:when>
			  			<c:otherwise>
			  				<td nowrap="nowrap" style="text-align:right;color:#CD0000;background:#FFFF00;cursor:pointer;" onmouseover="tooltip.show('<strong>${cur.map.msg19}</strong>');" onmouseout="tooltip.hide();">${fn:replace(cur.map.con19, " ", "&nbsp;")}</td>
			  			</c:otherwise>
	       			</c:choose>
          			
          			<!-- 社保 -->
          			<c:choose>
	          			<c:when test="${empty cur.map.msg20}">
			  				<td nowrap="nowrap" style="text-align:right;">${fn:replace(cur.w_ss, " ", "&nbsp;")}</td>
			  			</c:when>
			  			<c:otherwise>
			  				<td nowrap="nowrap" style="text-align:right;color:#CD0000;background:#FFFF00;cursor:pointer;" onmouseover="tooltip.show('<strong>${cur.map.msg20}</strong>');" onmouseout="tooltip.hide();">${fn:replace(cur.map.con20, " ", "&nbsp;")}</td>
			  			</c:otherwise>
	       			</c:choose>
          			
          			<!-- 公积金 -->
          			<c:choose>
	          			<c:when test="${empty cur.map.msg21}">
			  				<td nowrap="nowrap" style="text-align:right;">${fn:replace(cur.w_cpf, " ", "&nbsp;")}</td>
			  			</c:when>
			  			<c:otherwise>
			  				<td nowrap="nowrap" style="text-align:right;color:#CD0000;background:#FFFF00;cursor:pointer;" onmouseover="tooltip.show('<strong>${cur.map.msg21}</strong>');" onmouseout="tooltip.hide();">${fn:replace(cur.map.con21, " ", "&nbsp;")}</td>
			  			</c:otherwise>
	       			</c:choose>
          			
          			<!-- 税前工资 -->
          			<c:choose>
	          			<c:when test="${empty cur.map.msg22}">
			  				<td nowrap="nowrap" style="text-align:right;">${fn:replace(cur.w_before_tax, " ", "&nbsp;")}</td>
			  			</c:when>
			  			<c:otherwise>
			  				<td nowrap="nowrap" style="text-align:right;color:#CD0000;background:#FFFF00;cursor:pointer;" onmouseover="tooltip.show('<strong>${cur.map.msg22}</strong>');" onmouseout="tooltip.hide();">${fn:replace(cur.map.con22, " ", "&nbsp;")}</td>
			  			</c:otherwise>
	       			</c:choose>
          			
          			<!-- 税金 -->
          			<c:choose>
	          			<c:when test="${empty cur.map.msg23}">
			  				<td nowrap="nowrap" style="text-align:right;">${fn:replace(cur.w_money_of_tax, " ", "&nbsp;")}</td>
			  			</c:when>
			  			<c:otherwise>
			  				<td nowrap="nowrap" style="text-align:right;color:#CD0000;background:#FFFF00;cursor:pointer;" onmouseover="tooltip.show('<strong>${cur.map.msg23}</strong>');" onmouseout="tooltip.hide();">${fn:replace(cur.map.con23, " ", "&nbsp;")}</td>
			  			</c:otherwise>
	       			</c:choose>
          			
          			<!-- 税后工资 -->
          			<c:choose>
	          			<c:when test="${empty cur.map.msg24}">
			  				<td nowrap="nowrap" style="text-align:right;">${fn:replace(cur.w_after_tax, " ", "&nbsp;")}</td>
			  			</c:when>
			  			<c:otherwise>
			  				<td nowrap="nowrap" style="text-align:right;color:#CD0000;background:#FFFF00;cursor:pointer;" onmouseover="tooltip.show('<strong>${cur.map.msg24}</strong>');" onmouseout="tooltip.hide();">${fn:replace(cur.map.con24, " ", "&nbsp;")}</td>
			  			</c:otherwise>
	       			</c:choose>
          			
          			<!-- 其他 -->
          			<c:choose>
	          			<c:when test="${empty cur.map.msg25}">
			  				<td nowrap="nowrap" style="text-align:right;">${fn:replace(cur.w_other, " ", "&nbsp;")}</td>
			  			</c:when>
			  			<c:otherwise>
			  				<td nowrap="nowrap" style="text-align:right;color:#CD0000;background:#FFFF00;cursor:pointer;" onmouseover="tooltip.show('<strong>${cur.map.msg25}</strong>');" onmouseout="tooltip.hide();">${fn:replace(cur.map.con25, " ", "&nbsp;")}</td>
			  			</c:otherwise>
	       			</c:choose>
          			
          			<!-- 实发工资 -->
          			<c:choose>
	          			<c:when test="${empty cur.map.msg26}">
			  				<td nowrap="nowrap" style="text-align:right;">${fn:replace(cur.w_fact, " ", "&nbsp;")}</td>
			  			</c:when>
			  			<c:otherwise>
			  				<td nowrap="nowrap" style="text-align:right;color:#CD0000;background:#FFFF00;cursor:pointer;" onmouseover="tooltip.show('<strong>${cur.map.msg26}</strong>');" onmouseout="tooltip.hide();">${fn:replace(cur.map.con26, " ", "&nbsp;")}</td>
			  			</c:otherwise>
	       			</c:choose>
          		</tr>
  	  		</c:forEach>
  	  	</table>
	</div>
</c:if>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/manager/admin/KonkaCompetProdManage/tips/script.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/XYTips/jquery.XYTipsWindow.2.8.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#year").attr("dataType", "Require").attr("msg", "请选择导入工资年份！");
	$("#month").attr("dataType", "Require").attr("msg", "请选择导入工资月份！");
	$("#up_load_file").attr("dataType", "Require").attr("msg", "请导入员工工资Excel文件！");

	$("#btn_submit").click(function(){
		if(Validator.Validate(document.forms[0], 3)){
			//验证选择的年月是否重复提交，2013-07-26，Bengin
			$.ajax({
				type: "POST" , 
				url: "${ctx}/manager/admin/SalaryManage.do", 
				data:"method=verifyRepeat&year=" + $("#year").val() + "&month=" + $("#month").val() + "&n=" + Math.random(),
				dataType: "json" , 
		        async: true, 
		    		// error: function (request, settings) {alert("数据加载请求失败，请稍后重新！ ");return false;}, 
		        success: function (result) {
					if (result.state == -1) {
						alert("当前登录用户信息丢失！");
						return false;
					} else if (result.state == -2) {
						alert("未查询到分公司信息！");
						return false;
					} else if (result.state == 0) {
						alert("参数丢失！");
						return false;
					} else if (result.state == 1) {
						if(confirm("确定提交表单？")){
				            $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
				            document.forms[0].submit();
						} else {
							return false;
						}
					} else if (result.state == 2) {
						if (confirm("经系统查询，" + $("#year").val() + "年" + $("#month").val() + "月份《" + result.fgs_name + "分公司员工工资明细表》已上传，点击确定将覆盖已上传内容，是否继续？")) {
							$("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
							document.forms[0].submit();
						} else {
							return false;
						}
					}

		        }
			});
			//验证选择的年月是否重复提交，2013-07-26，End
		}
	});
	
	//显示上传文件错误详细信息
	viewErrorInfoDiv();
});

function viewErrorInfoDiv(){
	if ("" != "${entityList}") {
		$.XYTipsWindow({
			___title:"导入错误明细",
			___content:"id:errorInfo",
			___offsets:"middle-top",//居中置顶
			___width:"900",
			___height:"400",
			___boxBdColor:"#E9F3FD",
			___boxBdOpacity:"1",
			___boxWrapBdColor:"#A6C9E1",
			___windowBgColor:"#000000", //遮罩层背景颜色(默认值:#000000),
			___windowBgOpacity:"0.2", //遮罩层背景透明度(默认值:0.5)
			___showbg:true,
			___drag:"___boxTitle",
			___showBoxbg:true,
			___fns:function(){ //弹出窗口后执行函数
				//window.parent.resizeFrameHeight('mainFrame', 3);
			},
			___button:["关闭"],
			___closeback:function(){//点击右上角关闭按钮回调函数
				//alert("close");
			}
			/*
			___button:["确定","取消"],
			___callback:function(val){
				if ("确定" == val) {
					alert("1111");
				} else if ("取消" == val) {
					alert("2222");
				}
			}*/
		});
	}
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>