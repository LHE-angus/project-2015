<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<title>${naviString}</title>
<base target="_self" />
<style type="text/css">
	.rtable1 td{
		padding: 4px 0 4px 0;
	}
</style>
</head>
<body style="font-family:Microsoft Yahei;">
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
  <html-el:form action="/manager/JBill.do">
    <html-el:hidden property="method" value="list" />
    <html-el:hidden property="mod_id" styleId="mod_id" />
    <html-el:hidden property="to_default_page" value="${af.map.to_default_page}" />
    <input type="hidden" name="view_type" class="view_type" value="${af.map.view_type}" />
    <div class="rtabcont1">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable1">
        <tr>
        	<td nowrap="nowrap" align="right"><strong class="fb">创建日期：</strong></td>
        	<td>
          		<html-el:text property="opr_date_gt" size="10" maxlength="10" readonly="true" title="点击选择日期" styleClass="Wdate" style="cursor:pointer;" onclick="WdatePicker({readOnly:true})" />
            	-
            	<html-el:text property="opr_date_lt" size="10" maxlength="10" readonly="true" title="点击选择日期" styleClass="Wdate" style="cursor:pointer;" onclick="WdatePicker({readOnly:true})" />
            </td>
        	<td nowrap="nowrap" align="right"><strong class="fb">销售单号：</strong></td>
        	<td><html-el:text property="bill_sn_like" styleClass="webinput" styleId="bill_sn_like" maxlength="30" size="20"/></td>
        	<td nowrap="nowrap" align="right"><strong class="fb">单据类型：</strong></td>
        	<td>
        		<html-el:select property="bill_type" styleId="bill_type" style="width:100px;">
	              <html-el:option value="">请选择</html-el:option>
	              <html-el:option value="20">零售</html-el:option>
	              <html-el:option value="21">零售退货</html-el:option>
	            </html-el:select>
        	</td>
        </tr>
        <tr>
        	<td nowrap="nowrap" align="right"><strong class="fb">销售单位：</strong></td>
        	<td><html-el:text property="p_name_like" styleClass="webinput" styleId="p_name_like" maxlength="30" size="20"/></td>
        	<td nowrap="nowrap" align="right"><strong class="fb">销售单位编码：</strong></td>
        	<td><html-el:text property="p_id_like" styleClass="webinput" styleId="p_id_like" maxlength="30" size="20"/></td>
        	<td nowrap="nowrap" align="right"><strong class="fb">财务确认：</strong></td>
        	<td>
        		<html-el:select property="bill_state" styleId="bill_state" style="width:100px;">
	              <html-el:option value="">请选择</html-el:option>
	              <html-el:option value="0">待确认</html-el:option>
	              <html-el:option value="1">已确认</html-el:option>
	            </html-el:select>
        	</td>
        </tr>
        <tr>
        	<td nowrap="nowrap" align="right"><strong class="fb">顾客姓名：</strong></td>
        	<td><html-el:text property="customer_name_like" styleClass="webinput" styleId="customer_name_like" maxlength="30" size="20"/></td>
        	<td nowrap="nowrap" align="right"><strong class="fb">顾客电话：</strong></td>
        	<td><html-el:text property="mobile_like" styleClass="webinput" styleId="mobile_like" maxlength="30" size="20"/></td>
        	<td nowrap="nowrap" colspan="2" align="center">
        		<input name="button" type="submit" class="bgSearch" id="button" value="搜 索" style="height: 23px"/>&nbsp;
        		<input type="button" class="but_excel" onclick="if(confirm('提示，您确认导出数据？')){doNeedMethod(null, 'JBill.do', 'list', 'export=true&' + $('#bottomPageForm').serialize())}" value="导出" />
        	</td>
        </tr>
      </table>
    </div>
  </html-el:form>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
  	<div style="overflow-x:auto;">
        <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
                <tr class="tabtt1">
                  <td nowrap="nowrap" align="center" width="3%">序号</td>
                  <td nowrap="nowrap" align="center" width="5%">创建日期</td>
                  <td nowrap="nowrap" align="center" width="5%">销售单号</td>
	              <td nowrap="nowrap" align="center" width="5%">单据类型</td>
	              <td nowrap="nowrap" align="center" width="5%">销售单位名称</td>
                  <td nowrap="nowrap" align="center" width="5%">销售单位编码</td>
	              <td nowrap="nowrap" align="center" width="5%">顾客姓名</td>
	              <td nowrap="nowrap" align="center" width="5%">顾客电话</td>
	              <td nowrap="nowrap" align="center" width="5%">数量</td>
                  <td nowrap="nowrap" align="center" width="5%">销售金额（元）</td>
                  <td nowrap="nowrap" align="center" width="5%">财务确认</td>
                  <td nowrap="nowrap" align="center" width="5%">财务确认金额</td>
                  <td nowrap="nowrap" align="center" width="5%">关联销售单号</td>
                  <td nowrap="nowrap" align="center" width="5%">创建人</td>
                  <td nowrap="nowrap" align="center" width="5%">操作</td>
                </tr>
                <c:forEach items="${entityList}" var="cur" varStatus="vs">
                  <tr>
                    <td nowrap="nowrap" align="center">${vs.count}</td>
                    <td nowrap="nowrap" align="center"><fmt:formatDate value="${cur.ADD_DATE}" pattern="yyyy-MM-dd" /></td>
                    <td nowrap="nowrap" align="center">
                    	<span title="点击可查看" style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, '${ctx}/customer/manager/JBill.do', 'view','bill_id=${cur.BILL_ID}&'+$('#bottomPageForm').serialize())"><font class="blue12px">${cur.BILL_SN}</font></span>
                    </td>
                    <td nowrap="nowrap" align="center">
                    	<c:if test="${cur.BILL_TYPE eq 20 }">零售</c:if>
                    	<c:if test="${cur.BILL_TYPE eq 21 }">零售退货</c:if>
                    </td>
                    <td nowrap="nowrap" align="left">${cur.P_NAME }</td>
                    <td nowrap="nowrap" align="left">${cur.P_ID }</td>
                    <td nowrap="nowrap" align="center">${cur.PARTNER_NAME }</td>
                    <td nowrap="nowrap" align="center">${cur.LINK_MOBILE }</td>
                    <td nowrap="nowrap" align="right">
                    	<c:if test="${cur.TOTAL_NUM gt 0 }">${cur.TOTAL_NUM }</c:if>
                    	<c:if test="${cur.TOTAL_NUM lt 0 }">${-cur.TOTAL_NUM }</c:if>
                    </td>
                    <td nowrap="nowrap" align="right">${cur.REC_MONEY}</td>
                    <td nowrap="nowrap" align="center">
                    	<c:if test="${cur.BILL_STATE eq 0 }">待确认</c:if>
                    	<c:if test="${cur.BILL_STATE eq 1 }">已确认</c:if>
                    </td>
                    <td nowrap="nowrap" align="right">
                    	<c:if test="${cur.BILL_STATE eq 0 }"></c:if>
                    	<c:if test="${cur.BILL_STATE eq 1 }">${cur.MONEY }</c:if>
                    </td>
                    <td nowrap="nowrap" align="center">
                    	<span title="点击可查看" style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, '${ctx}/customer/manager/JBill.do', 'view','bill_sn=${cur.R_BILL_SN}&'+$('#bottomPageForm').serialize())"><font class="blue12px">${cur.R_BILL_SN }</font></span>
                    </td>
                    <td nowrap="nowrap" align="center">${cur.ADD_USER_NAME }</td>
                    <td nowrap="nowrap" align="center">
                    	<c:choose>
                    		<c:when test="${cur.BILL_STATE eq 1}">
                    			<span class="fblue" style="color:#ccc;cursor:pointer;">修改</span>&nbsp;
                    			<span class="fblue" style="color:#ccc;cursor:pointer;">删除</span>&nbsp;
                    			<span class="fblue" style="color:#ccc;cursor:pointer;">财务确认</span>&nbsp;
                    		</c:when>
                    		<c:otherwise>
                   				<span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, '${ctx}/customer/manager/JBill.do', 'edit','bill_id=${cur.BILL_ID}&bill_type=${cur.BILL_TYPE }&'+$('#bottomPageForm').serialize())">修改</span>&nbsp;
                   				<span style="cursor:pointer;" class="fblue" onclick="deleteBill('${cur.BILL_ID}','1');">删除</span>&nbsp;
                    			<span style="cursor:pointer;" class="fblue" onclick="confirmJBill('${cur.BILL_ID}')">财务确认</span>&nbsp;	
                    		</c:otherwise>
                    	</c:choose>
                   		<span style="cursor:pointer;" class="fblue" onclick="printJBill('${cur.BILL_ID}')">打印</span>
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
		            <td>&nbsp;</td>
		            <td>&nbsp;</td>
		            <td>&nbsp;</td>
		            <td>&nbsp;</td>
		          </tr>
        </c:forEach>
        </table>
    <br />
      <form id="bottomPageForm" name="bottomPageForm" method="post" action="JBill.do">
        <table width="100%" border="0" align="center" cellspacing="0" cellpadding="0">
          <tr>
            <td height="40" align="right">
            <script type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script>
            <script type="text/javascript">
		            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
		            pager.addHiddenInputs("method", "list");
		            pager.addHiddenInputs("opr_date_gt", "${af.map.opr_date_gt}");
		            pager.addHiddenInputs("opr_date_lt", "${af.map.opr_date_lt}");
		            pager.addHiddenInputs("bill_sn_like", "${af.map.bill_sn_like}");
		            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
		            pager.addHiddenInputs("bill_type", "${af.map.bill_type}");
		            pager.addHiddenInputs("p_name_like", "${af.map.p_name_like}");
		            pager.addHiddenInputs("p_id_like", "${af.map.p_id_like}");
		            pager.addHiddenInputs("bill_state", "${af.map.bill_state}");
		            pager.addHiddenInputs("customer_name_like", "${af.map.customer_name_like}");
		            pager.addHiddenInputs("mobile_like", "${af.map.mobile_like}");
		            document.write(pager.toString());
		            </script></td>
          </tr>
        </table>
      </form>
    </div>
  </div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/lhgdialog/lhgdialog.min.js?skin=discuz"></script>
<script type="text/javascript">//<![CDATA[
	//删除订单                                         
	function deleteBill(bill_id,flag){
		if(confirm("是否删除该订单？")){
			$.post('${ctx}/customer/manager/JBill.do?method=delete&bill_id='+bill_id+'&is_del='+flag,function(result){
				$("#bottomPageForm").submit();
			},'json');
		}
	}

	//打印订单
	function printJBill(bill_id){
		$.dialog({
			title:  "单据打印",
			width:  900,
			height: 580,
	        lock:true ,
			content:"url:JBill.do?method=print&bill_id="+bill_id
		});
	}
	
	//财务确认
	function confirmJBill(bill_id){
		$.dialog({
			title:  "财务确认",
			width:  900,
			height: 580,
	        lock:true ,
			content:"url:JBill.do?method=confirm&bill_id="+bill_id
		});
	}
			
$(document).ready(function(){
	var f=document.forms[0];
	 $(".bgSearch").click(function(){
	    	var s_time = $("#opr_date_gt").val();
			var e_time = $("#opr_date_lt").val();
			if ("" != s_time && "" != e_time && s_time > e_time) {
				alert("开始日期不能大于结束日期！");
				return false;
			}
			if(!Validator.Validate(f, 1)){
				return false;
			}
	    });
});
//]]></script>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>