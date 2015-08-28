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
    <html-el:hidden property="method" value="listForConfirm" />
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
          <td nowrap="nowrap" align="right"><strong class="fb">商品类型：</strong></td>
          <td>
          	<html-el:select property="type_id" styleClass="type_id" styleId="type_id" onchange="selectGoodType(this)" style="width:100px;">
	       	<html-el:option value="">请选择</html-el:option>
         		<c:forEach var="cur" items="${jBaseTypes}" varStatus="vs">
         			<html-el:option value="${cur.type_id}">${cur.type_name}</html-el:option>
         		</c:forEach>
	        </html-el:select>
          </td>
          <td nowrap="nowrap" align="right"><strong class="fb">机型：</strong></td>
          <td>
          	<html-el:select property="goods_id" styleId="goods_id" styleClass="goods_id">
            	<html-el:option value="">请选择</html-el:option>
            		<c:forEach var="cur" items="${jBaseGoodsList}" varStatus="vs">
				    	<html-el:option value="${cur.goods_id}">${cur.goods_name}</html-el:option>
				    </c:forEach>
			</html-el:select>
          </td>
          <td nowrap="nowrap" align="right"><strong class="fb">单据状态：</strong></td>
          <td>
          	<html-el:select property="status" styleId="status" style="width:100px;">
              <html-el:option value="">请选择</html-el:option>
              <html-el:option value="0">未确认</html-el:option>
              <html-el:option value="1">已确认</html-el:option>
            </html-el:select>
          </td>
        </tr>
        <tr>
          <td nowrap="nowrap" align="right"><strong class="fb">网点确认：</strong></td>
          <td>
          	<html-el:select property="wd_state" styleId="wd_state" style="width:100px;">
              <html-el:option value="">请选择</html-el:option>
              <html-el:option value="0">待确认</html-el:option>
              <html-el:option value="1">已确认</html-el:option>
              <html-el:option value="2">已退回</html-el:option>
            </html-el:select>
          </td>
          <td nowrap="nowrap" align="right"><strong class="fb">入库仓库：</strong></td>
          <td>
          	<html-el:select property="in_store_id" styleId="in_store_id" style="width:100px;">
              <html-el:option value="">请选择</html-el:option>
              <c:forEach var="cur" items="${storeList}" varStatus="vs">
		    	<html-el:option value="${cur.store_id}">${cur.store_name}</html-el:option>
		      </c:forEach>
            </html-el:select>
          </td>
          
          <td colspan="3"></td>
          <td><input name="button" type="submit" class="bgSearch" id="button" value="搜 索" style="height: 23px"/>
            &nbsp;<input type="button" class="but_excel" onclick="doNeedMethod(null, 'JSubSellRec.do', 'listForConfirm', 'export=true&' + $('#bottomPageForm').serialize())" value="导出" /></td>
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
                  <td nowrap="nowrap" align="center" width="5%">分销单号</td>
	              <td nowrap="nowrap" align="center" width="5%">单据类型</td>
	              <td nowrap="nowrap" align="center" width="5%">分销商名称</td>
	              <td nowrap="nowrap" align="center" width="5%">分销商编码</td>
                  <td nowrap="nowrap" align="center" width="5%">商品类型</td>
                  <td nowrap="nowrap" align="center" width="5%">机型</td>
                  <td nowrap="nowrap" align="center" width="5%">入库仓库</td>
                  <td nowrap="nowrap" align="center" width="5%">数量</td>
                  <td nowrap="nowrap" align="center" width="5%">销售单价</td>
                  <td nowrap="nowrap" align="center" width="5%">总金额（元）</td>
                  <td nowrap="nowrap" align="center" width="5%">折让金额（元）</td>
                  <td nowrap="nowrap" align="center" width="5%">折后金额（元）</td>
                  <td nowrap="nowrap" align="center" width="5%">关联销售单号</td>
                  <td nowrap="nowrap" align="center" width="5%">网点确认</td>
                </tr>
                <c:forEach items="${entityList}" var="cur" varStatus="vs">
                  <tr>
                    <td nowrap="nowrap" align="center">${vs.count}</td>
                    <td nowrap="nowrap" align="center"><fmt:formatDate value="${cur.OPR_DATE}" pattern="yyyy-MM-dd" /></td>
                    <td nowrap="nowrap" align="center">
                    	<span title="点击可查看" style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, '${ctx}/customer/manager/JSubSellRec.do', 'view','bill_confirm=2&bill_id=${cur.BILL_ID}&'+$('#bottomPageForm').serialize())"><font class="blue12px">${cur.BILL_SN}</font></span>
                    </td>
                    <td nowrap="nowrap" align="center">
                    	<c:if test="${cur.BILL_TYPE eq 40 }">分销</c:if>
                    	<c:if test="${cur.BILL_TYPE eq 42 }">分销退货</c:if>
                    </td>
                    <td nowrap="nowrap" align="left">${cur.CUSTOMER_NAME }</td>
                    <td nowrap="nowrap" align="left">${cur.R3_CODE }</td>
                    <td nowrap="nowrap" align="center">${cur.TYPE_NAME}</td>
                    <td nowrap="nowrap" align="center">${cur.GOODS_NAME }</td>
                    <td nowrap="nowrap" align="center">${cur.STORE_NAME }</td>
                    <td nowrap="nowrap" align="center">${cur.NUM }</td>
                    <td nowrap="nowrap" align="right">${cur.PRICE }</td>
                    <td nowrap="nowrap" align="right">${cur.MONEY }</td>
                    <td nowrap="nowrap" align="right">${cur.DIS_MONEY }</td>
                    <td nowrap="nowrap" align="right">${cur.REC_MONEY }</td>
                    <td nowrap="nowrap" align="right">
                    	<span title="点击可查看" style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, '${ctx}/customer/manager/JSubSellRec.do', 'view','bill_confirm=2&r_bill_id=${cur.R_BILL_ID}&'+$('#bottomPageForm').serialize())"><font class="blue12px">${cur.R_BILL_SN}</font></span>
                    </td>
                    <td nowrap="nowrap" align="center">
                    	<c:if test="${cur.STATUS eq 0}">待确认</c:if>
                    	<c:if test="${cur.STATUS eq 1}">已确认</c:if>
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
		            pager.addHiddenInputs("method", "list");
		            pager.addHiddenInputs("opr_date_gt", "${af.map.opr_date_gt}");
		            pager.addHiddenInputs("opr_date_lt", "${af.map.opr_date_lt}");
		            pager.addHiddenInputs("bill_sn_like", "${af.map.bill_sn_like}");
		            pager.addHiddenInputs("bill_type", "${af.map.bill_type}");
		            pager.addHiddenInputs("partner_name_like", "${af.map.partner_name_like}");
		            pager.addHiddenInputs("partner_id_like", "${af.map.partner_id_like}");
		            pager.addHiddenInputs("link_name_like", "${af.map.link_name_like}");
		            pager.addHiddenInputs("type_id", "${af.map.type_id}");
		            pager.addHiddenInputs("goods_id", "${af.map.goods_id}");
		            pager.addHiddenInputs("status", "${af.map.status}");
		            pager.addHiddenInputs("wd_state", "${af.map.wd_state}");
		            pager.addHiddenInputs("in_store_id", "${af.map.in_store_id}");
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
<script type="text/javascript">//<![CDATA[
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

function selectGoodType(obj){
	var $this = $(obj);
	var goods_id = document.getElementById("goods_id");
	var type_id = $this.val();
	$.ajax({
		type: "POST",
		url: "JSubSellRec.do",
		data: {method : "ajaxGetGoodsList", "type_id": type_id},
		dataType: "json",
		cache:false,
		error: function(){alert("数据加载请求失败！");},
		success: function(ret){
			if(ret){
				goods_id.options.length=0; ;
				goods_id.options.add(new Option("请选择",""));;
				for(var i=0; i<ret.list.length; i++){			
					goods_id.options.add(new Option(ret.list[i].goods_name,ret.list[i].goods_id));;
				}
			}
		}
   });
}
//]]></script>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>