<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<style type="text/css">
select {
	font-family:Microsoft YAHEI;
	font-size:12px;
}
input {
	font-family:Microsoft YAHEI;
	font-size:12px;
}
.bill-list {
	border-top: 2px solid #aaa;
	border-bottom: 2px solid #aaa;
	margin-bottom:1em;
}
.bill-list td, .bill-list th {
	padding:3px;
}
.bill-list th {
	background-color: #eee;
	border-bottom: 1px solid #aaa;
	line-height:25px;
	font-weight:800;
}
.bill-bottom {
	line-height:25px;
}
.bill-bottom td {
	border-top: 1px solid #aaa;
}
.bill-list-inner {
	border:0px;
	border-collapse:collapse;
	background-color:#ccc;
}
.bill-list-inner th {
	font-weight:normal;
	background-color:#eee;
	padding:0px 5px;
}
.bill-list-inner td {
	background-color:#fff;
	padding:2px 5px;
}
</style>
<title>订单记录</title>
<script type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script>
</head>
<body style="font-family:Microsoft Yahei;">
<div class="oarcont">
  <div class="oartop">
    <table width="400" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;" /></td>
        <td>当前位置：订单管理 &gt; 订单记录</td>
      </tr>
    </table>
  </div>
  <html-el:form action="/KonkaJxcOrderInfoView.do">
    <html-el:hidden property="mod_id" />
    <div class="rtabcont1">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableTop">
        <tr>
          <td height="36" align="left" style="padding-left:5px;"><input type="hidden" name="method" value="list" />
            <strong class="fb">订单日期：</strong>
            <input type="text" name="start_date" id="start_date" class="webinput" value="${af.map.start_date}" readonly="readonly" onclick="new Calendar(1990, 2020, 0).show(this);" style="cursor:pointer;text-align:center;width:80px;" />
            &nbsp;至
            <input type="text" name="end_date" id="end_date" class="webinput" value="${af.map.end_date}" readonly="readonly" onclick="new Calendar(1990, 2020, 0).show(this);" style="cursor:pointer;text-align:center;width:80px;"/>
            &nbsp;<strong class="fb">交易流水号：</strong>
            <html-el:text property="trade_index_like" styleClass="webinput" styleId="trade_index_like" maxlength="40" size="30" />
            &nbsp;<strong class="fb">客户名称模糊搜索：</strong>
            <html-el:text property="customer_name_like" size="20" maxlength="20" styleId="customer_name_like" title="请输入客户名称或所属地区或分公司所在地"/></td>
        </tr>
        <tr>
          <td><strong class="fb"> 分公司：</strong>
            <html-el:select property="fgs_dept_id" styleId="fgs_dept_id">
              <html-el:option value="">请选择...</html-el:option>
              <c:forEach var="cur" items="${sybDeptInfoList}">
                <html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
              </c:forEach>
            </html-el:select>
            &nbsp;<strong class="fb"> 经营部：</strong>
            <html-el:select property="jyb_dept_id" styleId="jyb_dept_id">
              <html-el:option value="">请选择...</html-el:option>
            </html-el:select>
            &nbsp; <strong class="fb">办事处：</strong>
            <html-el:select property="bsc_dept_id" styleId="bsc_dept_id">
              <html-el:option value="">请选择...</html-el:option>
            </html-el:select>
            &nbsp;
            <input name="button" type="submit" class="bgSearch" id="button" value="搜 索" /></td>
        </tr>
      </table>
    </div>
  </html-el:form>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
  </div>
  <div class="rtabcont1">
    <c:forEach items="${konkaOrderInfoList}" var="cur" varStatus="status">
      <div class="bill-list">
        <table width="100%" border="0" cellpadding="5" cellspacing="0">
          <tr>
            <th width="15%">交易流水号：${cur.trade_index}</th>
            <th width="15%" style="text-align:right;">下单日期：
              <fmt:formatDate value="${cur.order_date }" pattern="yyyy-MM-dd"></fmt:formatDate></th>
            <th width="15%" style="text-align:right;">订单数量：
              <fmt:formatNumber value="${cur.order_num}" pattern="0" /></th>
            <th width="15%" style="text-align:right;">订单金额（元）：
              <fmt:formatNumber value="${cur.money}" pattern="0.00" /></th>
            <th width="15%" style="text-align:right;">折扣金额（元）：
              <fmt:formatNumber value="${cur.good_discount_price}" pattern="0.00" /></th>
          </tr>
          <tr>
            <td colspan="5" style="padding:0px 0px 0px 30px;"><table width="100%" border="0" cellpadding="5" cellspacing="1" class="bill-list-inner">
                <tr>
                  <th width="10%" style="text-align:left;">序号</th>
                  <th width="10%" style="text-align:left;">产品型号</th>
                  <th width="10%" style="text-align:left;">数量</th>
                  <th width="10%" style="text-align:left;">金额</th>
                </tr>
                <c:forEach items="${cur.konkaOrderInfoDetailsList}" var="cur1" varStatus="status1">
                  <tr>
                    <td align="left">${status1.count}</td>
                    <td align="left">${fn:escapeXml(cur1.pd_name)}</td>
                    <td align="left">${fn:escapeXml(cur1.good_count)}</td>
                    <td align="left"><fmt:formatNumber value="${fn:escapeXml(cur1.good_sum_price)}" pattern="0.00" />
                      元</td>
                  </tr>
                </c:forEach>
              </table></td>
          </tr>
          <tr class="bill-bottom">
            <td align="center">订单类型：
              <c:choose>
                <c:when test="${cur.process_state eq 1}"> <span style="color:#060;">普通订单</span></c:when>
                <c:when test="${cur.process_state eq 2}"> <span style="color:#f00;">特殊订单</span></c:when>
              </c:choose></td>
            <td align="center">订单审核状态：
              <c:if test="${cur.audit_state eq 0}"> <span>未审核</span> </c:if>
              <c:if test="${cur.audit_state eq 1}"> <span style="color:#00f;">审核中 </span> </c:if>
              <c:if test="${cur.audit_state eq 3}"> <span style="color:#060;">审核通过 </span> </c:if>
              <c:if test="${cur.audit_state eq 2}"> <span style="color:#f00;">审核未通过</span> </c:if></td>
            <td align="center"><c:if test="${(cur.audit_state eq 0) or (cur.audit_state eq 1)}"> 当前审核角色： </c:if>
              <c:if test="${(cur.audit_state eq 2) or (cur.audit_state eq 3)}"> 最后审核角色： </c:if>
              ${fn:escapeXml(cur.map.audit_role_name)} </td>
            <td align="right" colspan="2">操作： <span style="cursor:pointer;"  class="fblue"  onclick="doNeedMethod(null, 'KonkaJxcOrderInfoView.do', 'view','&order_id=${cur.id}&'+$('#bottomPageForm').serialize())">查看明细</span>
              <c:if test="${cur.is_del eq 0}"> <span style="cursor:pointer;"  class="fblue"  onclick="doNeedMethod(null, 'KonkaJxcOrderInfoView.do', 'toDelOrEnd','&order_id=${cur.id}&type_operate=1&'+$('#bottomPageForm').serialize())">废除</span> </c:if>
              <c:if test="${cur.is_end eq 0}"> <span style="cursor:pointer;"  class="fblue"  onclick="doNeedMethod(null, 'KonkaJxcOrderInfoView.do', 'toDelOrEnd','&order_id=${cur.id}&type_operate=2&'+$('#bottomPageForm').serialize())">完结</span> </c:if>
              <c:if test="${cur.is_del eq 1}"> <span  class="fblue" title="该订单已废除，不能再次废除!">废除</span> </c:if>
              <c:if test="${cur.is_end eq 1}"> <span  class="fblue"  title="该订单已完结，不能再次完结!" >完结</span> </c:if></td>
          </tr>
        </table>
      </div>
    </c:forEach>
    <c:if test="${not empty konkaOrderInfoList}">
      <div class="rtabcont3">
        <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaJxcOrderInfoView.do?method=list">
          <table width="100%" border="0" align="center" cellspacing="0" cellpadding="0">
            <tr>
              <td height="40" align="center"><script type="text/javascript">
	            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
	            pager.addHiddenInputs("method", "list");
	            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
	            pager.addHiddenInputs("start_date", "${af.map.start_date}");
	            pager.addHiddenInputs("end_date", "${af.map.end_date}");
	            pager.addHiddenInputs("trade_index_like", "${af.map.trade_index_like}");
	            pager.addHiddenInputs("customer_name_like", "${af.map.customer_name_like}");
	            pager.addHiddenInputs("fgs_dept_id", "${af.map.fgs_dept_id}");
	            pager.addHiddenInputs("jyb_dept_id", "${af.map.jyb_dept_id}");
	            pager.addHiddenInputs("bsc_dept_id", "${af.map.bsc_dept_id}");
	            document.write(pager.toString());
	            </script></td>
            </tr>
          </table>
        </form>
      </div>
    </c:if>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script> 
<script type="text/javascript">//<![CDATA[
	$(document).ready(function(){
		$("#fgs_dept_id").change( function() {
			var dept_id = $("#fgs_dept_id").val();
			$("#jyb_dept_id").empty();
			$("#bsc_dept_id").empty();

			if(""==dept_id){
		   		var opt1 = new Option( "请选择...",""); 
		   		var opt2 = new Option( "请选择...",""); 
				$("#jyb_dept_id").get(0).options.add(opt1);
				$("#bsc_dept_id").get(0).options.add(opt2);
			   	}
		   	if(dept_id!=""){
			   	$.ajax({
					type: "POST",
					cache: false,
					url: "CsAjax.do",
					data: "method=getJybDeptListByFgsId&fgs_dept_id=" + $("#fgs_dept_id").val(),
					dataType: "json",
					error: function(request, settings){},
					success: function(data) {
						if (data.length >= 1) {
							var opt1 = new Option( "请选择...",""); 
							$("#jyb_dept_id").get(0).options.add(opt1);

							var opt2 = new Option( "请选择...",""); 
							$("#bsc_dept_id").get(0).options.add(opt2);
							
							for(var i = 0; i < data.length - 1; i++) {
								var opt = new Option( data[i].name,data[i].id); 
								$("#jyb_dept_id").get(0).options.add(opt);
							}
							<c:if test="${not empty af.map.jyb_dept_id }">$("#jyb_dept_id").val("${af.map.jyb_dept_id}");$("#jyb_dept_id").change();</c:if>
						}
					}
				});
		   	}
		   	
		});
		$("#jyb_dept_id").change( function() {
			var dept_id = $("#jyb_dept_id").val();
			$("#bsc_dept_id").empty();

		   	if(""==dept_id){
		   		var fgs_dept_id = $("#fgs_dept_id").val();
				   var opt = new Option( "请选择...",""); 
					$("#bsc_dept_id").get(0).options.add(opt);
				}
		   	else if(dept_id!=""){
			   	$.ajax({
					type: "POST",
					cache: false,
					url: "CsAjax.do",
					data: "method=getBscDeptListByJybId&jyb_dept_id=" + $("#jyb_dept_id").val(),
					dataType: "json",
					error: function(request, settings){},
					success: function(data) {
						if (data.length >= 1) {
							var opt = new Option( "请选择...",""); 
							$("#bsc_dept_id").get(0).options.add(opt);
							
							for(var i = 0; i < data.length - 1; i++) {
								var opt = new Option( data[i].name,data[i].id); 
								$("#bsc_dept_id").get(0).options.add(opt);
							}
								<c:if test="${not empty af.map.bsc_dept_id }">$("#bsc_dept_id").val("${af.map.bsc_dept_id}");</c:if>
						}
					}
				});
		   	}
		   	
		});

		<c:if test="${not empty af.map.fgs_dept_id }">
		$("#fgs_dept_id").val("${af.map.fgs_dept_id}");
		$("#fgs_dept_id").change();
	</c:if>
		var f=document.forms[0];
		 $(".bgSearch").click(function(){
		    	var s_time = $("#start_date").val();
				var e_time = $("#end_date").val();
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
<jsp:include page="../public_page.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>