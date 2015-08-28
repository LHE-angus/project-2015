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
  <html-el:form action="/manager/JSubSellRec.do">
    <html-el:hidden property="method" value="listForPrint" />
    <html-el:hidden property="mod_id" styleId="mod_id" />
    <div class="rtabcont1">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable1">
        <tr>
        	<td nowrap="nowrap" align="right"><strong class="fb">创建日期：</strong></td>
        	<td>
          		<html-el:text property="opr_date_gt" size="10" maxlength="10" readonly="true" title="点击选择日期" styleClass="Wdate" style="cursor:pointer;" onclick="WdatePicker({readOnly:true})" />
            	-
            	<html-el:text property="opr_date_lt" size="10" maxlength="10" readonly="true" title="点击选择日期" styleClass="Wdate" style="cursor:pointer;" onclick="WdatePicker({readOnly:true})" />
            </td>
        	<td nowrap="nowrap" align="right"><strong class="fb">分销单号：</strong></td>
        	<td><html-el:text property="bill_sn_like" styleClass="webinput" styleId="bill_sn_like" maxlength="30" size="20"/></td>
        	<td nowrap="nowrap" align="right"><strong class="fb">单据类型：</strong></td>
        	<td>
        		<html-el:select property="bill_type" styleId="bill_type" style="width:100px;">
	              <html-el:option value="">请选择</html-el:option>
	              <html-el:option value="40">分销</html-el:option>
	              <html-el:option value="42">分销退货</html-el:option>
	            </html-el:select>
        	</td>
        </tr>
        <tr>
        	<td nowrap="nowrap" align="right"><strong class="fb">网点名称：</strong></td>
        	<td><html-el:text property="partner_name_like" styleClass="webinput" styleId="partner_name_like" maxlength="30" size="20"/></td>
        	<td nowrap="nowrap" align="right"><strong class="fb">网点编码：</strong></td>
        	<td><html-el:text property="partner_sn_like" styleClass="webinput" styleId="partner_sn_like" maxlength="30" size="20"/></td>
        	<td nowrap="nowrap" align="right"><strong class="fb">联系人：</strong></td>
        	<td><html-el:text property="link_name_like" styleClass="webinput" styleId="link_name_like" maxlength="30" size="20"/></td>
        </tr>
        <tr>
        	<td nowrap="nowrap" align="right"><strong class="fb">财务确认：</strong></td>
        	<td>
        		<html-el:select property="bill_state" styleId="bill_state" style="width:100px;">
	              <html-el:option value="">请选择</html-el:option>
	              <html-el:option value="0">待确认</html-el:option>
	              <html-el:option value="1">已确认</html-el:option>
	            </html-el:select>
        	</td>
        	<td nowrap="nowrap" align="right"><strong class="fb">网点确认：</strong></td>
        	<td>
        		<html-el:select property="wd_state" styleId="wd_state" style="width:100px;">
	              <html-el:option value="">请选择</html-el:option>
	              <html-el:option value="0">待确认</html-el:option>
	              <html-el:option value="1">已确认</html-el:option>
	              <html-el:option value="2">已退回</html-el:option>
	            </html-el:select>
        	</td>
        	<td nowrap="nowrap" colspan="2" align="center">
        		<input name="button" type="submit" class="bgSearch" id="button" value="搜 索" style="height: 23px"/>&nbsp;
        		<input type="button" class="but_excel" onclick="if(confirm('提示，您确认导出数据？')){doNeedMethod(null, 'JSubSellRec.do', 'listForPrint', 'export=true&' + $('#bottomPageForm').serialize())}" value="导出" />
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
                  <td nowrap="nowrap" align="center" width="5%">创建时间</td>
                  <td nowrap="nowrap" align="center" width="5%">分销单号</td>
	              <td nowrap="nowrap" align="center" width="5%">单据类型</td>
	              <td nowrap="nowrap" align="center" width="5%">网点名称</td>
                  <td nowrap="nowrap" align="center" width="5%">网点编码</td>
	              <td nowrap="nowrap" align="center" width="5%">联系人</td>
	              <td nowrap="nowrap" align="center" width="5%">数量</td>
                  <td nowrap="nowrap" align="center" width="5%">总金额（元）</td>
                  <td nowrap="nowrap" align="center" width="5%">折让金额</td>
                  <td nowrap="nowrap" align="center" width="5%">折后金额（元）</td>
                  <td nowrap="nowrap" align="center" width="5%">财务确认</td>
                  <td nowrap="nowrap" align="center" width="5%">财务确认金额</td>
                  <td nowrap="nowrap" align="center" width="5%">关联销售单号</td>
                  <td nowrap="nowrap" align="center" width="5%">网点确认</td>
                  <td nowrap="nowrap" align="center" width="5%">创建人</td>
                  <td nowrap="nowrap" align="center" width="5%">操作</td>
                </tr>
                <c:forEach items="${entityList}" var="cur" varStatus="vs">
                  <tr>
                    <td nowrap="nowrap" align="center">${vs.count}</td>
                    <td nowrap="nowrap" align="center"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd" /></td>
                    <td nowrap="nowrap" align="center">
                    	<span title="点击可查看" style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, '${ctx}/customer/manager/JSubSellRec.do', 'view','bill_id=${cur.bill_id}&'+$('#bottomPageForm').serialize())"><font class="blue12px">${cur.bill_sn}</font></span>
                    </td>
                    <td nowrap="nowrap" align="center">
                    	<c:if test="${cur.bill_type eq 40 }">分销</c:if>
                    	<c:if test="${cur.bill_type eq 42 }">分销退货</c:if>
                    </td>
                    <td nowrap="nowrap" align="left">${cur.map.partner_name }</td>
                    <td nowrap="nowrap" align="left">${cur.map.partner_sn }</td>
                    <td nowrap="nowrap" align="center">${cur.map.link_name }</td>
                    <td nowrap="nowrap" align="right">${cur.map.total_num }</td>
                    <td nowrap="nowrap" align="right"><fmt:formatNumber type="number" maxFractionDigits="2" value="${cur.sum_money}"></fmt:formatNumber></td>
                    <td nowrap="nowrap" align="right"><fmt:formatNumber type="number" maxFractionDigits="2" value="${cur.dis_money }"></fmt:formatNumber></td>
                    <td nowrap="nowrap" align="right"><fmt:formatNumber type="number" maxFractionDigits="2" value="${cur.rec_money }"></fmt:formatNumber></td>
                    <td nowrap="nowrap" align="center">
                    	<c:if test="${cur.bill_state eq 0 }">待确认</c:if>
                    	<c:if test="${cur.bill_state eq 1}">已确认</c:if>
                    </td>
                    <td nowrap="nowrap" align="right"><fmt:formatNumber type="number" maxFractionDigits="2" value="${cur.money }"></fmt:formatNumber></td>
                    <td nowrap="nowrap" align="center">
                    	<span title="点击可查看" style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, '${ctx}/customer/manager/JSubSellRec.do', 'view','r_bill_sn=${cur.r_bill_sn}&'+$('#bottomPageForm').serialize())"><font class="blue12px">${cur.r_bill_sn }</font></span>
                    </td>
                    <td nowrap="nowrap" align="center">
                    	<c:if test="${cur.map.status eq 0}">待确认</c:if>
                    	<c:if test="${cur.map.status eq 1}">已确认</c:if>
                    	<c:if test="${cur.map.status eq 2}">已退回</c:if>
                    </td>
                    <td nowrap="nowrap" align="center">${cur.add_user_name }</td>
                    <td nowrap="nowrap" align="center">
                    	<c:choose>
                    		<c:when test="${cur.map.status eq 1}">
                    			<span class="fblue" style="color:#ccc;cursor:pointer;">修改</span>&nbsp;
                    			<span class="fblue" style="color:#ccc;cursor:pointer;">删除</span>&nbsp;
                    			<c:if test="${cur.bill_state eq 0 }">
                    				<span style="cursor:pointer;" class="fblue" onclick="window.showModalDialog('JSubSellRec.do?method=confirm&' + encodeURI('bill_id=' + ${cur.bill_id} +'&random=' + Math.random()), window, 'dialogWidth:900px;status:no;dialogHeight:580px');location.reload();">财务确认</span>&nbsp;
                    			</c:if>
                    			<c:if test="${cur.bill_state eq 1 }">
                    				<span class="fblue" style="color:#ccc;cursor:pointer;">财务确认</span>&nbsp;
                    			</c:if>
                    		</c:when>
                    		<c:otherwise>
                   				<span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, '${ctx}/customer/manager/JSubSellRec.do', 'edit','bill_id=${cur.bill_id}&'+$('#bottomPageForm').serialize())">修改</span>&nbsp;
                   				<span style="cursor:pointer;" class="fblue" onclick="deleteBill('${cur.bill_id}','1');">删除</span>&nbsp;
                    			<c:if test="${cur.bill_type eq 40 }">
                    				<span class="fblue" style="color:#ccc;cursor:pointer;">财务确认</span>&nbsp;
                    			</c:if>
                    			<c:if test="${cur.bill_type eq 42 }">
	                    			<c:if test="${cur.bill_state eq 0 }">
	                    				<span style="cursor:pointer;" class="fblue" onclick="window.showModalDialog('JSubSellRec.do?method=confirm&' + encodeURI('bill_id=' + ${cur.bill_id} +'&random=' + Math.random()), window, 'dialogWidth:900px;status:no;dialogHeight:580px');location.reload();">财务确认</span>&nbsp;	
	                    			</c:if>
	                    			<c:if test="${cur.bill_state eq 1}">
	                    				<span class="fblue" style="color:#ccc;cursor:pointer;">财务确认</span>&nbsp;
	                    			</c:if>
                    			</c:if>
                    		</c:otherwise>
                    	</c:choose>
                   		<span style="cursor:pointer;" class="fblue" onclick="printJBill('${cur.bill_id}')">打印</span>
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
		            <td>&nbsp;</td>
		            <td>&nbsp;</td>
		          </tr>
        </c:forEach>
        </table>
    <br />
      <form id="bottomPageForm" name="bottomPageForm" method="post" action="JSubSellRec.do">
        <table width="100%" border="0" align="center" cellspacing="0" cellpadding="0">
          <tr>
            <td height="40" align="right">
            <script type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script>
            <script type="text/javascript">
		            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
		            pager.addHiddenInputs("method", "listForPrint");
		            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
		            pager.addHiddenInputs("opr_date_gt", "${af.map.opr_date_gt}");
		            pager.addHiddenInputs("opr_date_lt", "${af.map.opr_date_lt}");
		            pager.addHiddenInputs("bill_sn_like", "${af.map.bill_sn_like}");
		            pager.addHiddenInputs("bill_type", "${af.map.bill_type}");
		            pager.addHiddenInputs("partner_name_like", "${af.map.partner_name_like}");
		            pager.addHiddenInputs("partner_sn_like", "${af.map.partner_sn_like}");
		            pager.addHiddenInputs("link_name_like", "${af.map.link_name_like}");
		            pager.addHiddenInputs("bill_state", "${af.map.bill_state}");
		            pager.addHiddenInputs("wd_state", "${af.map.wd_state}");
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
			$.post('${ctx}/customer/manager/JSubSellRec.do?method=deleteBill&bill_id='+bill_id+'&is_del='+flag,function(result){
				$("#bottomPageForm").submit();
			},'json');
		}
	}

	//打印
	function printJBill(bill_id){
		$.dialog({
			title:  "单据打印",
			width:  900,
			height: 580,
	        lock:true ,
			content:"url:JSubSellRec.do?method=print&bill_id="+bill_id
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