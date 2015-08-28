<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>销售明细(有交货明细信息)</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
    <div class="rtabcont1">
      <html-el:form action="/admin/KonkaR3OrderInfoSearch.do" >
        <html-el:hidden property="method" value="list" />
        <html-el:hidden property="mod_id" styleId="mod_id" />
        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableTop">
          <tr>
            <td>
             &nbsp;<c:if test="${empty is_fgs}">
             	销售组织:
              <html-el:text property="v_vkorg" styleId="v_vkorg" />
            	&nbsp;&nbsp;
            	</c:if>
            	分销渠道:
              <html-el:text property="v_vtweg" styleId="v_vtweg" />
            	&nbsp;&nbsp;产品组 :
              <html-el:text property="v_spart" styleId="v_spart" />
               &nbsp;&nbsp;客户编码:
              <html-el:text property="v_kunnr" styleId="v_kunnr" /></td>
           </tr>
           <tr>
            <td>
              &nbsp;订单开始日期:
              <html-el:text property="v_audat_begin" styleId="v_audat_begin" readonly="true" onclick="new Calendar(1990, 2021, 0).show(this);" />
           	  &nbsp;&nbsp;订单结束日期:
              <html-el:text property="v_audat_end" styleId="v_audat_end" readonly="true" onclick="new Calendar(1990, 2021, 0).show(this);" />
           &nbsp;&nbsp;<input name="button" type="button" class="bgSearch" id="s_button" value="搜 索" /></td>
          </tr>
        </table>
      </html-el:form>
    </div>
    <div class="rtabcont1" style="overflow-x:auto;">
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
        <tr>
          <th width="30" align="center" nowrap="nowrap">序号</th>
          <th align="center" nowrap="nowrap">销售组织</th>
          <th align="center" nowrap="nowrap">售达方</th>
          <th align="center" nowrap="nowrap">名称(售)</th>
          <th align="center" nowrap="nowrap">创建日期</th>
          <th align="center" nowrap="nowrap">凭证日期</th>
          <th align="center" nowrap="nowrap">订单号</th>
          <th align="center" nowrap="nowrap">订单类型</th>
          <th align="center" nowrap="nowrap">销售凭证项目号</th>
          <th align="center" nowrap="nowrap">机型</th>
          <th align="center" nowrap="nowrap">订单数量</th>
          <th align="center" nowrap="nowrap">实际已交货量</th>
          <th align="center" nowrap="nowrap">单价（含税）</th>
          <th align="center" nowrap="nowrap">总金额（含税）</th>
          <th align="center" nowrap="nowrap">K007（含税）</th>
          <th align="center" nowrap="nowrap">总净值金额(含税)</th>
          <th align="center" nowrap="nowrap">KF交货单</th>
          <th align="center" nowrap="nowrap">交货日期</th>
          <th align="center" nowrap="nowrap">物流交货单</th>
          <!-- 			        <th align="center" nowrap="nowrap">交货单行项目</th> -->
          <!-- 			        <th align="center" nowrap="nowrap">交货单项目类别</th> -->
          <!-- 			        <th align="center" nowrap="nowrap">工厂</th> -->
          <!-- 			        <th align="center" nowrap="nowrap">出货仓位</th> -->
          <th align="center" nowrap="nowrap">采购订单编号</th>
          <th align="center" nowrap="nowrap">采购订单日期</th>
          <th align="center" nowrap="nowrap">发货工厂</th>
          <th align="center" nowrap="nowrap">发货地点</th>
          <th align="center" nowrap="nowrap">办事处</th>
        </tr>
        <c:forEach var="cur" items="${entityList}" varStatus="vs" >
          <tr>
            <td align="center" nowrap="nowrap" height="20">${vs.count}</td>
            <td align="center" nowrap="nowrap" >${cur.VKORG}</td>
            <td align="center" nowrap="nowrap" >${cur.KUNNR}</td>
            <td align="center" nowrap="nowrap" ></td>
            <td align="center" nowrap="nowrap" >${cur.ERDAT}</td>
            <td align="center" nowrap="nowrap" >${cur.AUDAT}</td>
            <td align="center" nowrap="nowrap" >${cur.VBELN}</td>
            <td align="center" nowrap="nowrap" >${cur.AUART}</td>
            <td align="center" nowrap="nowrap" >${cur.POSNR}</td>
            <td align="center" nowrap="nowrap" >${cur.MATNR}</td>
            <td align="center" nowrap="nowrap" >${cur.KWMENG}</td>
            <td align="center" nowrap="nowrap" >${cur.LFIMG_L}</td>
            <td align="center" nowrap="nowrap" >${cur.CMPRE0}</td>
            <td align="center" nowrap="nowrap" >${cur.KZWI6}</td>
            <td align="center" nowrap="nowrap" ><fmt:formatNumber value="${cur.KZWI1-cur.KZWI6}" pattern="#0.00"/></td>
            <td align="center" nowrap="nowrap" ><fmt:formatNumber value="${cur.KZWI1}" pattern="#0.00"/></td>
            <td align="center" nowrap="nowrap" >${cur.VBELN_L}</td>
            <td align="center" nowrap="nowrap" >${cur.WADAT_IST}</td>
            <td align="center" nowrap="nowrap" >${cur.VBELN_LES}</td>
            <%-- 				      <td align="center" nowrap="nowrap" >${cur.POSNR_L}</td> --%>
            <%-- 				      <td align="center" nowrap="nowrap" >${cur.PSTYV_L}</td> --%>
            <%-- 				      <td align="center" nowrap="nowrap" >${cur.WERKS}</td> --%>
            <%-- 				      <td align="center" nowrap="nowrap" >${cur.LGORT}</td> --%>
            <td align="center" nowrap="nowrap" >${cur.BSTNK}</td>
            <td align="center" nowrap="nowrap" >${cur.BSTDK}</td>
            <td align="center" nowrap="nowrap" >${cur.WERKS_L}</td>
            <td align="center" nowrap="nowrap" >${cur.LGORT_L}</td>
            <td align="center" nowrap="nowrap" >${cur.VKBUR}</td>
          </tr>
        </c:forEach>
      </table>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" >
$(document).ready(function(){
	var is_fgs = '${is_fgs}';
	if(is_fgs != 1){
		$("#v_vkorg" ).attr("dataType" , "Require").attr("msg" , "销售组织不能为空！");
	}
	
	$("#v_vtweg" ).attr("dataType" , "Require").attr("msg" , "分销渠道不能为空！");
	$("#v_spart" ).attr("dataType" , "Require").attr("msg" , "产品组不能为空！");
	//$("#v_audat_begin").attr("dataType" , "Require").attr("msg" , "请选择定单开始日期！");
	//$("#v_audat_end").attr("dataType" , "Require").attr("msg" , "请选择定单结束日期！");
	
	$("#s_button").click(function(){
		//日期验证 start
		var v_audat_end = $("#v_audat_end").val();
		var v_audat_begin = $("#v_audat_begin").val();

		if(v_audat_begin.length == 0){
			alert("请选择定单开始日期！");
			return false;
		}
		
		if(v_audat_end.length == 0){
			alert("请选择定单结束日期！");
			return false;
		}
		var date_e = new Date(v_audat_end).valueOf();
		var last_month_date = date_e - 30 * 1000 * 60 * 60 * 24; //过去30天
		var date_s = new Date(v_audat_begin).valueOf();

		if(date_e < date_s){
			alert("订单的开始日期不得大于结束日期！");
			return false;
		}

		if(last_month_date > date_s){
			alert("订单日期的跨度不能超过30天！");
			return false;
		}
		//end
		var isSubmit = Validator.Validate(this.form, 2);
		if (isSubmit) {
			$(":button").attr("disabled", "true");
			$(":reset").attr("disabled", "true");
			this.form.submit();
		}
		
	});
}) ;
</script>
</body>
</html>