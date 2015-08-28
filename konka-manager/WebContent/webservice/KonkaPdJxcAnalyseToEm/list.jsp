<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>产品存销分析</title>
<style>
.oarcont {float:left;width:100%;height:100%;background:#fff;font-size: 12px;}
.rtabcont1 {margin:8px 7px;clear:both;}

.rtable6 {border-top:1px #ccc solid;border-left:1px #ccc solid;}
.rtable6 td {border-right:1px #A1A1A1 solid;border-bottom:1px #A1A1A1 solid;padding:5px 5px 0px 5px;}
.rtable6 .tabtt6 {height:23px;background:#ED7676;}
.rtable6 .tabtt6 td {border-right:1px #e3e3e3 solid;border-bottom:1px #C00 solid;padding:5px 5px 0px 5px;;font:bold 12px "宋体";color:#FFFFFF;}
</style>
</head>
<body>
	<div class="oarcont">
		<div class="rtabcont1">
			<table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable6">
			  <tr class="rtable6">
		        <td nowrap="nowrap">序号</td>
		        <td nowrap="nowrap">分销渠道</td>
		        <td nowrap="nowrap">物料组</td>
		        <td nowrap="nowrap">物料号</td>
		        <td nowrap="nowrap">数量</td>
		        <td nowrap="nowrap">发票额</td>
		        <td nowrap="nowrap">净价 </td>
		        <td nowrap="nowrap">交货额</td>
		        <td nowrap="nowrap">单位成本</td>
		        <td nowrap="nowrap">单位毛利</td>
		        <td nowrap="nowrap">销售毛利</td>
		        <td nowrap="nowrap">毛利率</td>
		        <td nowrap="nowrap">销售构成比</td>
		        <td nowrap="nowrap">贡献毛利率</td>
		        <td nowrap="nowrap">当前库存数量</td>
		        <td nowrap="nowrap">分康库存</td>
		        <td nowrap="nowrap">分公司中央仓库存</td>
		        <td nowrap="nowrap">分公司P仓</td>
		        <td nowrap="nowrap">样机仓数量</td>
		        <td nowrap="nowrap">分公司BQDZ仓</td>
		        <td nowrap="nowrap">分公司E仓</td>
		        <td nowrap="nowrap">分公司ET仓</td>
		        <td nowrap="nowrap">基地库存</td>
		        <td nowrap="nowrap">Q仓库存</td>
		        <td nowrap="nowrap">分公司非限库存</td>
		        <td nowrap="nowrap">分公司在途库存</td>
		        <td nowrap="nowrap">分公司D仓库存</td>
		        <td nowrap="nowrap">售前待修机总计（分公司售前机+分康售前机）</td>
		        <td nowrap="nowrap">售前待发机</td>
		        <td nowrap="nowrap">大客户仓库存</td>
		        <td nowrap="nowrap">报废库存</td>
		        <td nowrap="nowrap">区域调拨中心</td>
		        <td nowrap="nowrap">分公司有效库存</td>
		        <td nowrap="nowrap">Z仓库存</td>
		        <td nowrap="nowrap">移动平均价格/周期单价</td>
		        <td nowrap="nowrap">当前库存价值</td>
		        <td nowrap="nowrap">当前库存构成比</td>
		        <td nowrap="nowrap">当前毛利</td>
		        <td nowrap="nowrap">当前毛利率</td>
		        <td nowrap="nowrap">当前的存销比</td>
		      </tr>
		      <c:forEach var="cur" items="${entityList}" varStatus="vs" >
		        <tr><td>${vs.count}</td><td>${cur.VTWEG}</td><td>${cur.MATKL}</td><td>${cur.MATNR}</td><td>${cur.MENGE}</td><td>${cur.SAAMT}</td><td>${cur.NETPR}</td><td>${cur.COAMT}</td><td>${cur.UNIT_CO}</td><td>${cur.UNIT_ML}</td><td>${cur.ML}</td><td>${cur.MLV}</td><td>${cur.GCB}</td><td>${cur.GXMLV}</td><td>${cur.LBKUM}</td><td>${cur.LABSFK}</td><td>${cur.LABS2}</td><td>${cur.LABS3}</td><td>${cur.LABS16}</td><td>${cur.LABS6}</td><td>${cur.LABS11}</td><td>${cur.LABS12}</td><td>${cur.LABS13}</td><td>${cur.LABS14}</td><td>${cur.LABS15}</td><td>${cur.LABS17}</td><td>${cur.LABS18}</td><td>${cur.LABS10}</td><td>${cur.LABSDF}</td><td>${cur.LABS5}</td><td>${cur.LABS4}</td><td>${cur.LABS7}</td><td>${cur.LABSF}</td><td>${cur.LABSZ}</td><td>${cur.VERPR}</td><td>${cur.SALK3}</td><td>${cur.STOCK_GCB}</td><td>${cur.STOCK_ML}</td><td>${cur.STOCK_MLV}</td><td>${cur.STOCK_CXB}</td>
		        </tr>
		      </c:forEach>
			</table>
		</div>
</div>
</body>
</html>