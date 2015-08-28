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
<style type="text/css">
	.button {width:82px;height:24px;font:normal 12px/20px "宋体";text-align:center;cursor:pointer;}
</style>
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
    <html-el:form action="/paragon/KonkaParagonShowshopDetail">
  <div class="rtabcont1">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
    <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
      	<tr>
          <td nowrap="nowrap" align="right" width="80"><strong class="fb">门店代码：</strong></td>
          <td nowrap="nowrap" align="left" width="130"><html-el:text property="show_shop_code" size="20" style="width:90px;" maxlength="10" styleId="show_shop_code" styleClass="webinput" /></td>
          <td nowrap="nowrap" align="right" width="80"><strong class="fb">门店名称：</strong></td>
          <td nowrap="nowrap" align="left" width="130"><html-el:text property="shop_name_like" size="15" maxlength="20" styleId="shop_name_like"  /></td>
          <td nowrap="nowrap"  align="right" width="80">&nbsp;<strong class="fb">数据期号：</strong></td>
          <td nowrap="nowrap" align="left" width="130">
          	<html-el:text property="year" styleId="year" size="8" maxlength="10" readonly="true" onclick="WdatePicker({dateFmt:'yyyyMM'})" style="cursor:pointer;text-align:center;" title="点击选择日期" />
          </td>
          </tr>
          <tr>
          <td nowrap="nowrap" align="right" width="80">&nbsp;<strong class="fb">客户类别：</strong></td>
          <td nowrap="nowrap" align="left" width="130"> 
          	<html-el:select property="custom_type">
          		<html-el:option value="">请选择...</html-el:option>
             	<html-el:option value="1">连锁</html-el:option>
             	<html-el:option value="2">超市</html-el:option>
             	<html-el:option value="3">县乡客户群</html-el:option>
             	<html-el:option value="4">城市客户群</html-el:option>
             </html-el:select>
           </td>
          <td nowrap="nowrap" align="right" width="80">&nbsp;<strong class="fb">客户：</strong></td>
          <td nowrap="nowrap" align="left" width="130"><html-el:text property="custom_name_like" size="20" style="width:90px;" maxlength="10" styleId="custom_name_like" styleClass="webinput" /></td>
          <td nowrap="nowrap" align="right" width="80">&nbsp;<strong class="fb">经办：</strong></td>
          <td nowrap="nowrap" align="left" width="130">
          	<html-el:text property="channel_name" size="18"/>
          </td>
        </tr>
        <tr>
          <td nowrap="nowrap" align="right" width="80">&nbsp;<strong class="fb">区域：</strong></td>
          <td nowrap="nowrap" align="left" width="130">
          		 <html-el:select property="area_id">
             	<html-el:option value="">请选择...</html-el:option>
             	<html-el:option value="10">华东</html-el:option>
                <html-el:option value="20">山东</html-el:option>
                <html-el:option value="30">东北</html-el:option>
                <html-el:option value="40">华北</html-el:option>
             	<html-el:option value="50">华南</html-el:option>
                <html-el:option value="60">西南</html-el:option>
                <html-el:option value="70">华中</html-el:option>
                <html-el:option value="80">西北</html-el:option>
             </html-el:select>
          </td>
          <td nowrap="nowrap" align="right" width="80"> &nbsp;<strong class="fb">展台面积：</strong></td>
          <td nowrap="nowrap" align="left" width="130">
			<html-el:text property="spare_start" size="8" maxlength="20"/>
			-
			<html-el:text property="spare_end" size="8" maxlength="20"/>
          </td>
          <td nowrap="nowrap" align="right" width="80">&nbsp;<strong class="fb">制作时间：</strong></td>
          <td nowrap="nowrap" align="left"  colspan="3">
   			<html-el:text property="date_start" size="8" maxlength="10" readonly="true" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="cursor:pointer;text-align:center;" title="点击选择日期" />
           -
			<html-el:text property="date_end" size="8" maxlength="10" readonly="true" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" style="cursor:pointer;text-align:center;" title="点击选择日期" />
          </td>
          <td nowrap="nowrap" align="right" width="80">&nbsp;</td>
          <td nowrap="nowrap" align="left" width="130">&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
         <tr>
          <td nowrap="nowrap" align="right" width="80">&nbsp;<strong class="fb">网线接口：</strong></td>
          <td nowrap="nowrap" align="left" width="130">
          		<html-el:select property="have_interface">
	             	<html-el:option value="">请选择...</html-el:option>
	             	<html-el:option value="0">无</html-el:option>
	             	<html-el:option value="1">有</html-el:option>
	             </html-el:select>
          </td>
          <td nowrap="nowrap" align="right" width="80"><strong class="fb">展台延米数：</strong></td>
          <td nowrap="nowrap" align="left">
			<html-el:text property="mile_start" size="8" maxlength="20"/>
			-
			<html-el:text property="mile_end" size="8" maxlength="20"/>
          </td>
          <td nowrap="nowrap" align="right" width="80"><strong class="fb">制作费：</strong></td>
          <td nowrap="nowrap" align="left">
			<html-el:text property="cash_start" size="8" maxlength="20"/>
			-
			<html-el:text property="cash_end" size="8" maxlength="20"/></td>
          <td align="left">&nbsp;</td>
		</tr>
         <tr>
          <td nowrap="nowrap" align="right" width="80"> &nbsp;<strong class="fb">分公司${userInfo.role_id}：</strong></td>
          <td nowrap="nowrap" align="left" width="130">
          		<html-el:select property="part_company_id" styleId="part_company_id">
          			<c:if test="${roleInfo.role_id eq 1000}">
		        		<html-el:option value="">请选择...</html-el:option>
          			</c:if>
		        	<html-el:optionsCollection name="deptInfoList" label="dept_name" value="dept_id" />
	        	</html-el:select>
          </td>
          <td nowrap="nowrap" align="right" width="80"><strong class="fb">销售额(万)：</strong></td>
          <td nowrap="nowrap" align="left">
          			<html-el:text property="sales_start" size="8" maxlength="20"/>
	                 -
	      			<html-el:text property="sales_end" size="8" maxlength="20"/>
          </td>
          <td nowrap="nowrap" align="right" width="80"><strong class="fb">进场费(万)：</strong></td>
          <td nowrap="nowrap" align="left">
          			<html-el:text property="et_start" size="8" maxlength="20"/>
	                 -
	      			<html-el:text property="et_end" size="8" maxlength="20"/></td>
          <td align="left">&nbsp;<input type="button" name="" value="搜索" id="btn_submit" class="but1" /></td>
        </tr>
      </table>
  </div>
   <div class="rtabcont1">
	 <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
      	<td width="10%"><input type="button" name="toExcel" id="toExcel" value="导出" class="but4" /></td>
      	<td>提示：本页面查询所有可选参数中，<font color='red'>数据期号</font>（即数据发生月份）为必填项。</td>
      </tr>
	</table>
  </div>
    </html-el:form>
  <div id="divExcel" title="综合查询"  class="rtabcont1" style="overflow-x:auto;">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tr class="tabtt1">
          <td nowrap="nowrap" width="35" align="center" >序号</td>
          <td nowrap="nowrap" align="center" >门店代码</td>
          <td width="90" align="center" nowrap="nowrap">门店名称</td>
          <td width="30" nowrap="nowrap" align="center" >区域</td>
          <td width="50" nowrap="nowrap" align="center" >分公司</td>
          <td width="50" nowrap="nowrap" align="center" >经办</td>
          <td width="60" align="center" >客户类别</td>
          <td width="90" nowrap="nowrap" align="center" >客户</td>
          <td width="80" align="center" >本期零售额<br />(万元)</td>
          <td width="80" align="center" >本期进场费<br />(万元)</td>
          <td width="50" align="center" >直销员人数</td>
          <td width="70" align="center" >展位面积<br />(m2)</td>
          <td width="70" align="center" >展位类型</td>
          <td width="70" align="center" >展位延米(m)</td>
          <td width="80" align="center" >制作时间</td>
          <td width="70" align="center" >制作费用<br />(元)</td>
          <td width="50" align="center" >网线接口</td>
          <td width="70" align="center" >46寸及以上样机数</td>
        </tr>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr onmouseover="this.bgColor='#97CBFF';this.style.cursor='pointer';" onmouseout="this.bgColor=''" onclick="doNeedMethod(null, 'KonkaParagonSub.do', 'view','fixdate='+ $('#year').val()+'&scode=${cur.show_shop_code}&mod_id=${af.map.mod_id}')" title="查看详细">
            <td align="center" nowrap="nowrap">${vs.count}</td>
            <td align="center" nowrap="nowrap">${cur.show_shop_code}</td>
             <td align="left" >${cur.show_shop_name}</td>
            <td align="left" nowrap="nowrap">
            	<c:choose>
					<c:when test="${cur.area_id eq 10}">华东</c:when>
					<c:when test="${cur.area_id eq 20}">山东</c:when>
					<c:when test="${cur.area_id eq 30}">东北</c:when>
					<c:when test="${cur.area_id eq 40}">华北</c:when>
					<c:when test="${cur.area_id eq 50}">华南</c:when>
					<c:when test="${cur.area_id eq 60}">西南</c:when>
					<c:when test="${cur.area_id eq 70}">华中</c:when>
					<c:when test="${cur.area_id eq 80}">西北</c:when>
				</c:choose>
            </td>
            <td align="left" >${cur.map.part_company_name}</td>
            <td align="left" >${cur.channel_name}</td>
            <td align="left" >
            		<c:choose>
						<c:when test="${cur.custom_type eq 1}">连锁</c:when>
						<c:when test="${cur.custom_type eq 2}">超市</c:when>
						<c:when test="${cur.custom_type eq 3}">县乡客户群</c:when>
						<c:when test="${cur.custom_type eq 4}">城市客户群</c:when>
						<c:otherwise>城市专卖店</c:otherwise>
					</c:choose>
            </td>
            <td align="left" >${cur.custom_name}</td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.sales}" type="currency" pattern="#,##0.00" /></td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.etcash}" type="currency" pattern="#,##0.00" /></td>
            <td align="center" nowrap="nowrap">${cur.seller_num}</td>
            <td align="center" >${cur.showt_area}</td>
            <td align="center" nowrap="nowrap">${cur.showt_type}</td>
            <td align="center" nowrap="nowrap">${cur.showt_mile}</td>
            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.showt_time}" pattern="yyyy-MM-dd" /></td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.showt_cash}" type="currency" pattern="#,##0.00" /></td>
            <td align="center" >
            	<c:choose>
            		<c:when test="${cur.have_interface > 0}">有</c:when>
            		<c:when test="${empty cur.have_interface}">无</c:when>
            	</c:choose>
            </td>
            <td align="center" >${cur.macnum} </td>
          </tr>
          <c:if test="${vs.last eq true}">
            <c:set var="i" value="${vs.count}" />
          </c:if>
        </c:forEach>
        <c:forEach begin="${i}" end="${af.map.pager.pageSize - 1}">
          <tr align="center">
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
      </table>
    <br />
  </div>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaParagonShowshopDetail.do">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
			pager.addHiddenInputs("show_shop_code", "${fn:escapeXml(af.map.show_shop_code)}");							
            pager.addHiddenInputs("year", "${af.map.year}");
            pager.addHiddenInputs("custom_type", "${af.map.custom_type}");
            pager.addHiddenInputs("custom_name_like", "${af.map.custom_name_like}");
            pager.addHiddenInputs("area_id", "${af.map.area_id}");
            pager.addHiddenInputs("part_company_id", "${af.map.part_company_id}");
            pager.addHiddenInputs("channel_name", "${af.map.channel_name}");
            pager.addHiddenInputs("have_interface", "${af.map.have_interface}");
            pager.addHiddenInputs("date_start", "${af.map.date_start}");
            pager.addHiddenInputs("date_end", "${af.map.date_end}");
            pager.addHiddenInputs("shop_name_like", "${fn:escapeXml(af.map.shop_name_like)}");
            
            pager.addHiddenInputs("mile_start", "${af.map.mile_start}");
            pager.addHiddenInputs("mile_end", "${af.map.mile_end}");
            pager.addHiddenInputs("spare_start", "${af.map.spare_start}");
            pager.addHiddenInputs("spare_end", "${af.map.spare_end}");
            pager.addHiddenInputs("cash_start", "${af.map.cash_start}");
            pager.addHiddenInputs("cash_end", "${af.map.cash_end}");
            pager.addHiddenInputs("sales_start", "${af.map.sales_start}");
            pager.addHiddenInputs("sales_end", "${af.map.sales_end}");
            pager.addHiddenInputs("et_start", "${af.map.et_start}");
            pager.addHiddenInputs("et_end", "${af.map.et_end}");
            
            document.write(pager.toString());
            </script></td>
        </tr>
      </table>
    </form>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/scripts/print.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	
	$("#btn_submit").click(function(){
		if($("#year").val() != ""){
			this.form.method.value = 'list';
			this.form.submit();
		}
		else
		{
			alert("请选择数据期号");
		}
	});
	
	$("#toExcel").click(function(){
		if($("#year").val() != ""){
			this.form.method.value = 'toExcel';
			this.form.submit();
		}
		else
		{
			alert("请选择数据期号");
		}
	});
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
