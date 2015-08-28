<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>产品存销分析</title>
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
        <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align:middle;" /> <span id="span_help" style="cursor:pointer;">帮助</span></td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/admin/KonkaPdJxcAnalyse.do" >
      <html-el:hidden property="method" value="list" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
        	<td>
         	&nbsp;&nbsp;<strong class="fb"><span style="color: red">*</span>销售订单开始日期:</strong>
            <html-el:text property="v_bstdk_begin" styleId="v_bstdk_begin"  readonly="true"   styleClass="webinput"  onclick="new Calendar(1990, 2021, 0).show(this);" />
          &nbsp;&nbsp;<strong class="fb">销售订单结束日期:</strong>
            <html-el:text property="v_bstdk_end" styleId="v_bstdk_end"  readonly="true" styleClass="webinput"  onclick="new Calendar(1990, 2021, 0).show(this);" />
          &nbsp;&nbsp;<strong class="fb"><span style="color: red">*</span>分销渠道:</strong>
            <html-el:text property="v_vtweg" styleId="v_vtweg" value="10" />
            	</td>
          </tr>
          <tr>
          <td>
          &nbsp;&nbsp;<strong class="fb"><span style="color: red">*</span>产品组:</strong>
            <html-el:text property="v_spart" styleId="v_spart" value="10" />
		  &nbsp;&nbsp;<strong class="fb">机型(开始):</strong>
            <html-el:text property="v_matnr_begin" styleId="v_matnr_begin" />
          &nbsp;&nbsp;<strong class="fb">机型(结束):</strong>
            <html-el:text property="v_matnr_end" styleId="v_matnr_end" />
            	</td>
          </tr>
          <tr>
          <td>
          &nbsp;&nbsp;<strong class="fb">存销比（开始）:</strong>
            <html-el:text property="v_cxb_begin" styleId="v_cxb_begin" />
          &nbsp;&nbsp;<strong class="fb">存销比（结束）:</strong>
            <html-el:text property="v_cxb_end" styleId="v_cxb_end" />
          &nbsp;&nbsp;<input name="button" type="submit" class="bgSearch" id="s_button" value="搜 索"  /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1" style="overflow-x: auto;">
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
      <tr class="tabtt1">
        <td width="5%" nowrap="nowrap">序号</td>
        <td width="8%" nowrap="nowrap">分销渠道</td>
        <td width="8%" nowrap="nowrap">物料组</td>
        <td width="8%" nowrap="nowrap">物料号</td>
        <td width="8%" nowrap="nowrap">数量</td>
        <td width="8%" nowrap="nowrap">发票额</td>
        <td width="8%" nowrap="nowrap">净价 </td>
        <td width="8%" nowrap="nowrap">交货额</td>
        <td width="8%" nowrap="nowrap">单位成本</td>
        <td width="8%" nowrap="nowrap">单位毛利</td>
        <td width="8%" nowrap="nowrap">销售毛利</td>
        <td width="8%" nowrap="nowrap">毛利率</td>
        <td width="8%" nowrap="nowrap">销售构成比</td>
        <td width="8%" nowrap="nowrap">贡献毛利率</td>
        <td width="8%" nowrap="nowrap">当前库存数量</td>
        <td width="8%" nowrap="nowrap">分康库存</td>
        <td width="8%" nowrap="nowrap">分公司中央仓库存</td>
        <td width="8%" nowrap="nowrap">分公司P仓</td>
        <td width="8%" nowrap="nowrap">样机仓数量</td>
        <td width="8%" nowrap="nowrap">分公司BQDZ仓</td>
        <td width="8%" nowrap="nowrap">分公司E仓</td>
        <td width="8%" nowrap="nowrap">分公司ET仓</td>
        <td width="8%" nowrap="nowrap">基地库存</td>
        <td width="8%" nowrap="nowrap">Q仓库存</td>
        <td width="8%" nowrap="nowrap">分公司非限库存</td>
        <td width="8%" nowrap="nowrap">分公司在途库存</td>
        <td width="8%" nowrap="nowrap">分公司D仓库存</td>
        <td width="8%" nowrap="nowrap">售前待修机总计（分公司售前机+分康售前机）</td>
        <td width="8%" nowrap="nowrap">售前待发机</td>
        <td width="8%" nowrap="nowrap">大客户仓库存</td>
        <td width="8%" nowrap="nowrap">报废库存</td>
        <td width="8%" nowrap="nowrap">区域调拨中心</td>
        <td width="8%" nowrap="nowrap">分公司有效库存</td>
        <td width="8%" nowrap="nowrap">Z仓库存</td>
        <td width="8%" nowrap="nowrap">移动平均价格/周期单价</td>
        <td width="8%" nowrap="nowrap">当前库存价值</td>
        <td width="8%" nowrap="nowrap">当前库存构成比</td>
        <td width="8%" nowrap="nowrap">当前毛利</td>
        <td width="8%" nowrap="nowrap">当前毛利率</td>
        <td width="8%" nowrap="nowrap">当前的存销比</td>
      </tr>
      <c:forEach var="cur" items="${entityList}" varStatus="vs" >
        <tr>
          <td align="center" nowrap="nowrap" height="20">${vs.count}</td>
          <td align="center" nowrap="nowrap" >${cur.VTWEG}</td>
          <td align="center" nowrap="nowrap" >${cur.MATKL}</td>
          <td align="center" nowrap="nowrap" >${cur.MATNR}</td>
          <td align="center" nowrap="nowrap" >${cur.MENGE}</td>
          <td align="center" nowrap="nowrap" >${cur.SAAMT}</td>
          <td align="center" nowrap="nowrap" >${cur.NETPR}</td>
          <td align="center" nowrap="nowrap" >${cur.COAMT}</td>
          <td align="center" nowrap="nowrap" >${cur.UNIT_CO}</td>
          <td align="center" nowrap="nowrap" >${cur.UNIT_ML}</td>
          <td align="center" nowrap="nowrap" >${cur.ML}</td>
          <td align="center" nowrap="nowrap" >${cur.MLV}</td>
          <td align="center" nowrap="nowrap" >${cur.GCB}</td>
          <td align="center" nowrap="nowrap" >${cur.GXMLV}</td>
          <td align="center" nowrap="nowrap" >${cur.LBKUM}</td>
          <td align="center" nowrap="nowrap" >${cur.LABSFK}</td>
          <td align="center" nowrap="nowrap" >${cur.LABS2}</td>
          <td align="center" nowrap="nowrap" >${cur.LABS3}</td>
          <td align="center" nowrap="nowrap" >${cur.LABS16}</td>
          <td align="center" nowrap="nowrap" >${cur.LABS6}</td>
          <td align="center" nowrap="nowrap" >${cur.LABS11}</td>
          <td align="center" nowrap="nowrap" >${cur.LABS12}</td>
          <td align="center" nowrap="nowrap" >${cur.LABS13}</td>
          <td align="center" nowrap="nowrap" >${cur.LABS14}</td>
          <td align="center" nowrap="nowrap" >${cur.LABS15}</td>
          <td align="center" nowrap="nowrap" >${cur.LABS17}</td>
          <td align="center" nowrap="nowrap" >${cur.LABS18}</td>
          <td align="center" nowrap="nowrap" >${cur.LABS10}</td>
          <td align="center" nowrap="nowrap" >${cur.LABSDF}</td>
          <td align="center" nowrap="nowrap" >${cur.LABS5}</td>
          <td align="center" nowrap="nowrap" >${cur.LABS4}</td>
          <td align="center" nowrap="nowrap" >${cur.LABS7}</td>
          <td align="center" nowrap="nowrap" >${cur.LABSF}</td>
          <td align="center" nowrap="nowrap" >${cur.LABSZ}</td>
          <td align="center" nowrap="nowrap" >${cur.VERPR}</td>
          <td align="center" nowrap="nowrap" >${cur.SALK3}</td>
          <td align="center" nowrap="nowrap" >${cur.STOCK_GCB}</td>
          <td align="center" nowrap="nowrap" >${cur.STOCK_ML}</td>
          <td align="center" nowrap="nowrap" >${cur.STOCK_MLV}</td>
          <td align="center" nowrap="nowrap" >${cur.STOCK_CXB}</td>
        </tr>
      </c:forEach>
    </table>
  </div>
  <div class="rtabcont3"> <span></span> </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" ><!--
$(document).ready(function(){
	$("#s_button").click(function(){
	
		if($("#v_bstdk_begin").val()==null || $("#v_bstdk_begin").val()==""){
			alert("销售订单开始日期不能为空");
			return false ;
		}
		if($("#v_vtweg").val()==null ||$("#v_vtweg").val()==""){
			alert("分销渠道不能为空");
			return false ;
		}
		if($("#v_spart").val()==null ||$("#v_spart").val()==""){
			alert("产品组不能为空");
			return false ;
		}
		return true;
	});
}) ;
</script>
</body>
</html>