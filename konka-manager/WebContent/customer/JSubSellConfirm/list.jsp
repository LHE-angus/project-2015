<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<style type="text/css">
select{font-family:Microsoft YAHEI;font-size:12px;}
input{font-family:Microsoft YAHEI;font-size:12px;}
</style>
<title>${naviString}</title>
<base target="_self" />
<script type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script>
</head>
<body style="font-family:Microsoft Yahei;">
<div class="oarcont">
	<div class="oartop">
	    <table width="400" border="0" cellpadding="0" cellspacing="0">
	      <tr>
	        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
	        <td>当前位置：${naviString}</td>
	      </tr>
	    </table>
  	</div>
	<div class="rtabcont1">
	  <%@ include file="/commons/pages/messages.jsp" %>
	</div>
	<html-el:form action="/manager/JSubSellConfirm.do">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <div class="rtabcont1">
      	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable1">
	    	<tr>
	        	<td nowrap="nowrap" height="36" align="right" style="padding-left:5px;"><strong class="fb">状&nbsp;&nbsp;&nbsp;&nbsp;态：</strong></td>
     	  		<td>
     	  			<html-el:select property="status" styleId="status" style="width:100px;" onchange="this.form.submit();">
    					<html-el:option value="">请选择</html-el:option>
		    			<html-el:option value="0">未确认</html-el:option>
		    			<html-el:option value="1">已确认</html-el:option>
		    			<html-el:option value="2">已退回</html-el:option>
		    		</html-el:select>
		    	</td>
		    	<td nowrap="nowrap" align="right"><strong class="fb">分销日期：</strong></td>
		    	<td>
		    		<html-el:text property="opr_date_gt" size="13" maxlength="10" readonly="true" title="点击选择日期" styleClass="Wdate" style="cursor:pointer;" onclick="WdatePicker({readOnly:true})" />
		          	-
		            <html-el:text property="opr_date_lt" size="13" maxlength="10" readonly="true" title="点击选择日期" styleClass="Wdate" style="cursor:pointer;" onclick="WdatePicker({readOnly:true})" />
		    	</td>
		    	<td nowrap="nowrap" align="right"><strong class="fb">分销商名称：</strong></td>
		    	<td><html-el:text property="partner_name_like" styleClass="webinput" styleId="partner_name_like" maxlength="30" size="20"/></td>
		    </tr>
		    <tr>
		    	<td nowrap="nowrap" align="right"><strong class="fb">单据类型：</strong></td>
		    	<td>
		    		<html-el:select property="bill_type" styleId="bill_type" style="width:100px;">
		              <html-el:option value="">请选择</html-el:option>
		              <html-el:option value="40">分销</html-el:option>
		              <html-el:option value="42">分销退货</html-el:option>
		            </html-el:select>
		    	</td>
		    	<td nowrap="nowrap" align="right"><strong class="fb">分销单号：</strong></td>
		    	<td><html-el:text property="bill_sn_like" styleClass="webinput" styleId="bill_sn_like" maxlength="30" size="20"/></td>
		    	<td>&nbsp;</td>
		    	<td nowrap="nowrap"><input name="button" type="submit" class="bgSearch" id="button" value="搜 索" /></td>
		    </tr>
		</table>
     </div>
	</html-el:form>
	<div class="rtabcont1">
	  	<table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
	  		<tr>
	  		  <th width="5%" nowrap="nowrap" align="center">序号</th>
		      <th width="15%" nowrap="nowrap" align="center">分销商名称</th>
		      <th width="15%" nowrap="nowrap" align="center">分销商编码</th>
		      <th width="5%" nowrap="nowrap" align="center">单据类型</th>
		      <th width="15%" nowrap="nowrap" align="center">分销单号</th>
		      <th width="8%" nowrap="nowrap" align="center">数量</th>
		      <th width="12%" nowrap="nowrap" align="center">折后金额</th>
		      <th width="12%" nowrap="nowrap" align="center">分销时间</th>
		      <th width="5%" nowrap="nowrap" align="center">状态</th>
		      <th width="5%" nowrap="nowrap" align="center">操作</th>
	    	</tr>
	    	<c:forEach items="${entityList}" var="cur" varStatus="vs">
	    		<tr>
	    		  <td align="center">${vs.count + af.map.pager.pageSize * (af.map.pager.currentPage - 1)}</td>
			      <td nowrap="nowrap" align="center">${cur.map.part_name}</td>
			      <td nowrap="nowrap" align="center">${cur.map.part_sn}</td>
			      <td nowrap="nowrap" align="center">
			      	<c:if test="${cur.map.bill_type eq 40 }">分销</c:if>
            		<c:if test="${cur.map.bill_type eq 42 }">分销退货</c:if>
			      </td>
			      <td nowrap="nowrap" align="center">
			      	<span title="点击可查看" style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, '${ctx}/customer/manager/JSubSellRec.do', 'view','bill_confirm=0&bill_sn=${cur.sell_bill_sn}&'+$('#bottomPageForm').serialize())"><font class="blue12px">${cur.sell_bill_sn}</font></span>
			      </td>
			      <td nowrap="nowrap" align="right" style="padding-right: 5px">${cur.map.num }</td>
			      <td align="right" style="padding-right: 5px"><fmt:formatNumber type="currency" value="${cur.map.rec_money}"></fmt:formatNumber></td>
			      <td nowrap="nowrap" align="center"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			      <td nowrap="nowrap" align="center">
			      	<c:if test="${cur.status eq 0 }">待确认</c:if>
			      	<c:if test="${cur.status eq 1 }">已确认</c:if>
			      	<c:if test="${cur.status eq 2 }">已退回</c:if>
			      </td>
			      <td nowrap="nowrap" align="center">
			      	<c:if test="${cur.status eq 0 }">
			      		<html-el:button property="btn_sure" onclick="doNeedMethod(null, '${ctx}/customer/manager/JSubSellRec.do', 'view','bill_confirm=1&bill_sn=${cur.sell_bill_sn}&sub_sell_id=${cur.id }&'+$('#bottomPageForm').serialize())" value="&nbsp;确&nbsp;认&nbsp;"></html-el:button>
			      	</c:if>
			      	<c:if test="${cur.status eq 1 or cur.status eq 2 }">
			      		<html-el:button property="" disabled="true" value="&nbsp;确&nbsp;认&nbsp;"></html-el:button>
			      	</c:if>
			      </td>
		    	</tr>
	    	</c:forEach>
	  	</table>
	  	<div class="rtabcont3">
	      <form id="bottomPageForm" name="bottomPageForm" method="post" action="JSubSellConfirm.do">
	        <table width="100%" border="0" align="center" cellspacing="0" cellpadding="0">
	          <tr>
	            <td height="40" align="center"><script type="text/javascript">
		            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
		            pager.addHiddenInputs("method", "list");
		            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
		            pager.addHiddenInputs("status", "${af.map.status}");
		            document.write(pager.toString());
		            </script></td>
	          </tr>
	        </table>
	      </form>
	    </div>
	</div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/customer/script/rowEffect.js"></script>
<script type="text/javascript">//<![CDATA[
//]]></script>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>