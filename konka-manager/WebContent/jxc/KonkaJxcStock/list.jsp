<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>实时库存</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div style="width:100%;">
<div class="oartop" ><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;" /> 当前位置 ： ${naviString}</div>
<div class="rtabcont1" >
  <html-el:form action="/KonkaJxcStock.do">
    <html-el:hidden property="method" value="list"/>
    <html-el:hidden property="mod_id" />
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableTop">
      <tr>
        <td nowrap="nowrap"><strong class="fb">类型</strong>：
          <html-el:select property="pd_type_id" styleId="pd_type_id" value="${af.map.pd_type_id}">
              <html-el:option value="">请选择...</html-el:option>
              <c:forEach items="${basePdClassList}" var="cur" varStatus="vs">
                <html-el:option value="${cur.cls_id}">${fn:escapeXml(cur.tree_name)}</html-el:option>
              </c:forEach>
            </html-el:select>
          &nbsp; <strong class="fb">型号</strong>：
          <html-el:text styleClass="webinput" property="name_like" style="width:75px" />
          &nbsp;<strong class="fb">时间</strong>：
          <html-el:text styleClass="webinput" property="add_date_st" styleId="add_date_st" value="${af.map.add_date_st}" readonly="true" onclick="new Calendar(1990, 2020, 0).show(this);" style="cursor:pointer;text-align:left;width:74px;" />
         	至
          <html-el:text styleClass="webinput" property="add_date_en" styleId="add_date_en" value="${af.map.add_date_en}" readonly="true" onclick="new Calendar(1990, 2020, 0).show(this);" style="cursor:pointer;text-align:left;width:74px;" />
          &nbsp;
          <html-el:checkbox property="show_by_store_id" styleId="show_by_store_id" />
          <label for="show_by_store_id" title="点击，按仓库显示产品">全部仓库</label>
          <html-el:checkbox property="is_show_0_store" styleId="is_show_0_store" />
          <label for="is_show_0_store" title="点击，显示零库存产品">零库存</label>
          <input name="button" type="submit" class="bgSearch" id="button" value="搜 索" /></td>
      </tr>
    </table>
  </html-el:form>
</div>
<div class="rtabcont1" >
  <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
    <tr class="title_top">
      <td rowspan="2" align="center" >序号</td>
      <c:if test="${not empty af.map.show_by_store_id}">
        <td rowspan="2" align="center" >仓库</td>
      </c:if>
      <td rowspan="2" align="center" >产品型号</td>
      <td rowspan="2" align="center" >产品类型</td>
      <td colspan="3" align="center">期初结存</td>
      <td colspan="3" align="center">日常进货</td>
      <td colspan="3" align="center">日常出货<br/>
        （成本）</td>
      <td colspan="3" align="center" nowrap="nowrap">盘盈/(-)盘亏</td>
      <td colspan="3" align="center">期末结存</td>
      <td rowspan="2" width="10%" align="center">操作</td>
    </tr>
    <tr class="title_top">
      <td align="center">数量</td>
      <td align="center">单价</td>
      <td align="center">金额</td>
      <td align="center">数量</td>
      <td align="center">单价</td>
      <td align="center">金额</td>
      <td align="center">数量</td>
      <td align="center">单价</td>
      <td align="center">金额</td>
      <td align="center">数量</td>
      <td align="center">单价</td>
      <td align="center">金额</td>
      <td align="center">数量</td>
      <td align="center">单价</td>
      <td align="center">金额</td>
    </tr>
    <tr>
      <td colspan="${empty af.map.show_by_store_id ? 3:4 }" align="center" ><font class="redbig" style="color: red;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;合计</font></td>
      <td align="right"><fmt:formatNumber value="${af.map.qcjc_count_total}"  pattern="0"/></td>
      <td align="right">&nbsp;</td>
      <td align="right"><fmt:formatNumber value="${af.map.qcjc_money_total}" type="currency" /></td>
       <td align="right"><fmt:formatNumber value="${af.map.rcjh_count_total}"  pattern="0" /></td>
      <td align="right">&nbsp;</td>
      <td align="right"><fmt:formatNumber value="${af.map.rcjh_money_total }" type="currency" /></td>
      <td align="right"><fmt:formatNumber value="${af.map.rcch_count_total}" pattern="0" /></td>
      <td align="right">&nbsp;</td>
      <td align="right"><fmt:formatNumber value="${af.map.rcch_money_total}" type="currency" /></td>
      <td align="right"><fmt:formatNumber value="${af.map.pc_count_total}" pattern="0" /></td>
      <td align="right">&nbsp;</td>
      <td align="right"><fmt:formatNumber value="${af.map.pc_money_total}" type="currency" /></td>
      <td align="right"><fmt:formatNumber value="${af.map.qmjc_count_total}" pattern="0" /></td>
      <td align="right"></td>
      <td align="right"><fmt:formatNumber value="${af.map.qmjc_money_total}" type="currency" /></td>
      <td ></td>
    </tr>
    <c:forEach items="${entityList}" var="cur" varStatus="vs">
      <tr>
        <td align="center">${vs.index+1}</td>
        <c:if test="${not empty af.map.show_by_store_id}">
          <td align="left">${cur.store_name}</td>
        </c:if>
        <td align="left">${cur.pd_name}</td>
        <td align="left">${cur.pd_type_name}</td>
        <td align="right">${empty cur.map.qcjc_count?0:cur.map.qcjc_count}</td>
        <td align="right"><fmt:formatNumber value="${cur.map.qcjc_price}" var="qcjc_price" type="currency" />
          ${empty cur.map.qcjc_price?0.00:qcjc_price}</td>
        <td align="right"><fmt:formatNumber value="${cur.map.qcjc_money}" var="qcjc_money" type="currency" />
          ${empty cur.map.qcjc_money?'0.00':qcjc_money}</td>
        <td align="right">${empty cur.map.jh_count?0:cur.map.jh_count + cur.map.thzp_count}</td>
        <td align="right"><fmt:formatNumber value="${cur.map.rcjh_price}" var="rcjh_price" type="currency" />
          ${empty cur.map.rcjh_price?0.00:rcjh_price}</td>
        <td align="right"><fmt:formatNumber value="${cur.map.jh_sum_money + cur.map.thzp_sum_money}" var="jh_sum_money" type="currency" />
          ${empty cur.map.jh_sum_money?'0.00':jh_sum_money}</td>
        <td align="right">${empty cur.map.ch_count?0:cur.map.ch_count}</td>
        <td align="right"><fmt:formatNumber value="${cur.map.rcch_price}" var="rcch_price" type="currency" />
          ${empty cur.map.rcch_price?0.00:rcch_price}</td>
        <td align="right"><fmt:formatNumber value="${cur.map.ch_sum_money}" var="ch_sum_money" type="currency" />
          ${empty cur.map.ch_sum_money?'0.00':ch_sum_money}</td>
        <td align="right">${empty cur.map.pc_count?0:cur.map.pc_count}</td>
        <td align="right"><fmt:formatNumber value="${cur.map.pc_price}" var="pc_price" type="currency" />
          ${empty cur.map.pc_price?0.00:pc_price}</td>
        <td align="right"><fmt:formatNumber value="${cur.map.pc_sum_money}" var="pc_sum_money" type="currency" />
          ${empty cur.map.pc_sum_money?'0.00':pc_sum_money}</td>
        <td align="right"><fmt:formatNumber value="${cur.map.qmjc_count}" pattern="0" /></td>
        <td align="right"><fmt:formatNumber value="${cur.map.qmjc_price}" var="qmjc_price" type="currency"  />
          ${empty cur.map.qmjc_price?0.00:qmjc_price}</td>
        <td align="right"><fmt:formatNumber value="${cur.map.qmjc_money}" type="currency" /></td>
        <td align="center"><c:if test="${not empty af.map.show_by_store_id}"><span style="cursor:pointer;"  class="fblue"  onclick="openDetailsForStore('${af.map.mod_id }','${cur.pd_id}','${af.map.dept_id}','${cur.pd_type_id}','${cur.store_id}','${af.map.add_date_st}','${af.map.add_date_en}','true')">明细账</span></c:if>
          <c:if test="${empty af.map.show_by_store_id}"><span style="cursor:pointer;"  class="fblue"  onclick="openDetails('${af.map.mod_id }','${cur.pd_id}','${af.map.dept_id}','${cur.pd_type_id}','${af.map.add_date_st}','${af.map.add_date_en}')">明细账</span></c:if></td>
      </tr>
    </c:forEach>
  </table>
</div>
<div class="rtabcont1" ><span class="jxcTip"> 备注：默认不显示数据，点击搜索显示数据</span></div>
<!--<form id="bottomPageForm" name="bottomPageForm" method="post" action="JxcStock.do">
  <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td height="40" align="center"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
        <script type="text/javascript">
          var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
          pager.addHiddenInputs("method", "list");
          pager.addHiddenInputs("pd_type", "${af.map.pd_type}");
          pager.addHiddenInputs("name_like", "${af.map.name_like}");
          pager.addHiddenInputs("add_date_st", "${af.map.add_date_st}");
          pager.addHiddenInputs("add_date_en", "${af.map.add_date_en}");
          document.write(pager.toString());
          </script></td>
    </tr>
  </table>
</form>-->
<c:url value="" var="expPara">
  <c:param name="method" value="toExcelForList"/>
  <c:param name="type" value="sskclist"/>
  <c:param name="add_date_lt" value="${af.map.add_date_st}"/>
  <c:param name="add_date_gt" value="${af.map.add_date_en}"/>
  <c:param name="pd_type" value="${af.map.pd_type}"/>
  <c:param name="name_like" value="${af.map.name_like}"/>
</c:url>
<!--<div class="rtabcont1">
<html-el:form action="/JxcStock.do${expPara}">
<table width="100%" border="0" cellspacing="0" cellpadding="0" style="padding-left:5px;padding-top:10px">
  <tr>
    <td height="26">
      <div class="left">
        <input name="button" type="button" class="bgButton" id="button" value="打 印" onclick="openChild()" />
        <input name="button" type="button" class="bgButton" id="toExcel" value="导 出" />
      </div>
    </td>
  </tr>
</table>
</html-el:form>
</div>-->
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
//	getCountTotal(); //纵向

	var b_d = $("#add_date_st");
	var e_d = $("#add_date_en");

	b_d.attr({"dataType":"Require","msg":"请选择开始日期！"});
	e_d.attr({"dataType":"Require","msg":"请选择结束日期！"});
	$("form").submit(function(){
	   var isSubmit = Validator.Validate(this, 1);	
	   if (isSubmit) {
		   if(e_d.val() < b_d.val()) {
				alert("开始日期必须小于等于结束日期!");
				return false;
		   }
		   $("#submit_loading").show();
	   }
	   return isSubmit;
	});
});

function openDetailsForStore(mod_id,pd_id,dept_id,pd_type_id,store_id,add_date_st,add_date_en,show_by_store_id){
	window.location.href = "KonkaJxcStock.do?method=view&add_date_st=${af.map.add_date_st}&add_date_en=${af.map.add_date_en}&pd_id=" + pd_id+ "&pd_type_id="+ pd_type_id +"&dept_id=" + dept_id +"&store_id=" + store_id+"&add_date_st="+add_date_st+"&add_date_en="+add_date_en +"&show_by_store_id=" + show_by_store_id+"&mod_id="+ mod_id;
}

function openDetails(mod_id,pd_id,dept_id,pd_type_id,add_date_st,add_date_en){
	window.location.href = "KonkaJxcStock.do?method=view&add_date_st=${af.map.add_date_st}&add_date_en=${af.map.add_date_en}&pd_id=" + pd_id+ "&pd_type_id="+ pd_type_id +"&dept_id=" + dept_id+"&add_date_st="+add_date_st+"&add_date_en="+add_date_en+"&mod_id="+ mod_id;
}

var type = "sskclist";
var add_date_st = "${af.map.add_date_st}";
var add_date_en = "${af.map.add_date_en}";
var pd_type = "${af.map.pd_type}";
var brand_name_like = "${af.map.brand_name_like}";
var param = "type=" + type + "&add_date_lt=" + add_date_st + "&add_date_gt=" + add_date_en + "&pd_type=" + pd_type+ "&name_like=" + brand_name_like;
function openChild(){
	window.showModalDialog("?method=print&" + encodeURI(param) + "&date=" + new Date(), window, "dialogWidth:900px;status:no;dialogHeight:580px");
}
$("#toExcel").click(function(){
	this.form.submit();
	//window.open("?method=toExcelForList&" + param, "下载页面", "height=100,width=100");
});
//]]></script>
<jsp:include page="../public_page.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>