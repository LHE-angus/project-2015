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
<script type="text/javascript" src="${ctx}/commons/scripts/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/javascripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>  
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
		<html-el:form action="/paragon/KonkaParagonShowt">
		<html-el:hidden property="method" styleId="method" value="list" />
		<html-el:hidden property="mod_id" styleId="mod_id" />
		<table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
	        <tr>
	        <td width="15"></td>
	        <td><strong class="fb">门店代码：</strong><html-el:text property="shop_order_like" size="15" maxlength="20" styleId="shop_order_like"  /></td>
	         <td><strong class="fb">门店名称：</strong>
            	<html-el:text property="shop_name_like" size="15" maxlength="20" styleId="shop_name_like"  />
            </td>
            <td><strong class="fb">数据期号：</strong>
            <html-el:text property="fixdate" styleId="fixdate" size="8" maxlength="10" readonly="true" onclick="WdatePicker({dateFmt:'yyyyMM'})" style="cursor:pointer;text-align:center;" title="点击选择日期" />
            </td>
          </tr>
           <tr>
	        <td width="15"></td>
	        <td nowrap="nowrap">
	        	<strong class="fb">区域　：</strong>
        		<html-el:select property="area_id" styleId="area_id">
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
	        <td><strong class="fb">分公司　：</strong>
	        	<html-el:select property="part_company_id" styleId="part_company_id">
		        	<html-el:option value="">请选择...</html-el:option>
		        	<html-el:optionsCollection name="deptInfoList" label="dept_name" value="dept_id" />
	        	</html-el:select></td>
	        <td>
	        	<strong class="fb">经办　　：</strong>
	        	<html-el:text property="channel_name_like" size="15" maxlength="20" styleId="channel_name_like"  />
          	</td>
	        <td><html-el:submit styleClass="but1" value="搜索" styleId="btn_submit" /></td>
           </tr>
       </table>
		</html-el:form>
	</div>
	<div class="rtabcont1">
<%@ include file="/commons/pages/messages.jsp" %>
 <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td>
		    <logic-el:match name="popedom" value="+1+">  
		    <input class="but2" type="button" name="Submit2" value="新增" onclick="location.href='KonkaParagonShowt.do?method=add&mod_id=${af.map.mod_id}';" style="display:none;"/>
		    </logic-el:match>
		    <logic-el:match name="popedom" value="+4+">
		    <input class="but3" type="button" name="Submit3" value="回收站" onclick="confirmDeleteAll(document.getElementById('listForm'));" />
		    </logic-el:match>
		</td>
	 </tr>
	</table>
  </div>
  <div class="rtabcont1">
	<form id="listForm" name="listForm" method="post" action="KonkaParagonShowt.do?method=delete">
		<input type="hidden" name="method" id="method" value="delete" />
		<input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
       	    <tr class="tabtt1">
				<td width="25" nowrap="nowrap" align="center"><input name="chkAll" type="checkbox" id="chkAll" value="-1" onclick="checkAll(this);" /></td>
				<td nowrap="nowrap" align="center">门店代码</td>
				<td nowrap="nowrap" align="center">门店名称</td>
				<td nowrap="nowrap" align="center">形象版本名称</td>
				<td nowrap="nowrap" align="center">展位面积</td>
				<td nowrap="nowrap" align="center">展位类型</td>
				<td nowrap="nowrap" align="center">展位延米</td>
				<td nowrap="nowrap" align="center">制作费用</td>
				<td nowrap="nowrap" align="center">数据期号</td>
				<td nowrap="nowrap" align="center">制作时间</td>
				<td width="120" nowrap="nowrap" align="center">操作</td>
			</tr>
			<c:forEach var="cur" items="${entityList}" varStatus="vs">
			<tr>
				<td align="center" nowrap="nowrap"><input name="pks" type="checkbox" id="pks" value="${cur.showt_id}" /></td>
				<td align="center" nowrap="nowrap"><a href="KonkaParagonShowt.do?method=viewShop&mod_id=502000&show_shop_code=${cur.show_shop_code}" title="点击查看门店详细信息">
					${cur.show_shop_code}</a></td>
				<td align="left">${cur.map.show_shop_name}</td>
				<td align="left">${fn:escapeXml(cur.map.version_name)}</td>
				<td align="right" nowrap="nowrap">${cur.showt_area}㎡</td>
				<td align="center" nowrap="nowrap">${cur.showt_type}</td>
				<td align="right" nowrap="nowrap">${cur.showt_mile}m</td>
				<td align="right" nowrap="nowrap">
				￥<fmt:formatNumber value="${cur.showt_cash}" pattern="#,###.00" type="currency"/>元</td>
				<td align="center" nowrap="nowrap">${fn:escapeXml(cur.fixdate)}</td>
				<td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.showt_time}" pattern="yyyy-MM-dd"/></td>
				<td align="center">
				<span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaParagonShowt.do', 'view','mod_id=${af.map.mod_id}&showt_id=${cur.showt_id}&'+$('#bottomPageForm').serialize())">查看</span>
				|
				<logic-el:match name="popedom" value="+2+">
					<span style="cursor:pointer;" class="fblue" onclick="confirmUpdate(null, 'KonkaParagonShowt.do', 'mod_id=${af.map.mod_id}&showt_id=${cur.showt_id}&' + $('#bottomPageForm').serialize())">修改</span>
				</logic-el:match>
				<logic-el:notMatch name="popedom" value="+2+">
					<span style="color:#CCC;">修改</span>
				</logic-el:notMatch>
				|
				<logic-el:match name="popedom" value="+4+">
					<span style="cursor:pointer;" class="fblue" onclick="confirmDelete('确定删除吗?', 'KonkaParagonShowt.do', 'mod_id=${af.map.mod_id}&showt_id=${cur.showt_id}&' + $('#bottomPageForm').serialize())">删除</span>
				</logic-el:match>
				<logic-el:notMatch name="popedom" value="+4+">
					<span style="color:#CCC;">删除</span></logic-el:notMatch>
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
	</form>
	<form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaParagonShowt.do">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td height="40" align="center">
					<script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
					<script type="text/javascript">
						var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
						pager.addHiddenInputs("method", "list");
						pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
						pager.addHiddenInputs("shop_order_like", "${af.map.shop_order_like}");
						pager.addHiddenInputs("shop_name_like", "${fn:escapeXml(af.map.shop_name_like)}");	
						pager.addHiddenInputs("fixdate", "${af.map.fixdate}");
						pager.addHiddenInputs("channel_name_like", "${af.map.channel_name_like}");
						pager.addHiddenInputs("part_company_id", "${af.map.part_company_id}");
						pager.addHiddenInputs("area_id", "${af.map.area_id}");
						document.write(pager.toString());
					</script>
					</td>
				</tr>
			</table>
		</form>
	</div>
 <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#btn_submit").click(function(){
		this.form.submit();
	});
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>