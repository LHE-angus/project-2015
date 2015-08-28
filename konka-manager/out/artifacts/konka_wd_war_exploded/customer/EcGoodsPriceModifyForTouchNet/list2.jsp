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
<link href="${ctx}/styles/customer/jNotify/jNotify.jquery.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.webinput {
	background:#f5f4f4;
	padding-left: 5px;
	height: 19px;
	line-height: 19px;
	/*font-family: Arial, Helvetica, sans-serif;*/
	border: #ccc solid 1px;
}
ul.ckUl{list-style-type:none;display:inline;}
ul.ckUl li{float:left;margin:auto 5px auto 0px;/*padding:2px 5px;*/}
input,textarea,select{font-family:Microsoft Yahei;font-size:12px;}
.ck-li{position:relative;}
.ck-li .hidden-accessible{position:absolute !important;clip:rect(1px 1px 1px 1px);}
.ck-li .ck-default{font-family:Microsoft yahei,verdana,Geneva,Tahoma,Georgia;font-size:12px;display:inline-block;padding:1px 8px;height:16px;line-height:16px;border:1px solid #CCC;background: #F6F6F6;font-weight: bold;color:#C4C4C4;cursor:pointer;}
.ck-li .ck-hover{font-family:Microsoft yahei,verdana,Geneva,Tahoma,Georgia;font-size:12px;display:inline-block;padding:1px 8px;height:16px;line-height:16px;border:1px solid #FBCB09; background:#FDF5CE;font-weight: bold;color:#C77405;cursor:pointer;}
.ck-li .ck-visited{font-family:Microsoft yahei,verdana,Geneva,Tahoma,Georgia;font-size:12px;display:inline-block;padding:1px 8px;height:16px;line-height:16px;border:2px solid #EF0F28/*#FF4800/*FBD850*/; background:white url("${ctx}/styles/customer/images/ck-visited.gif") right bottom no-repeat;font-weight:bold;color:#EF0F28/*#FF4800/*#EB8F00*/;cursor:pointer;}
</style>
</head>
<body style="font-family:Microsoft Yahei;">
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td nowrap="nowrap">当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
		<%@ include file="/commons/pages/messages.jsp"%>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td>
					<c:if test="${empty af.map.is_pingbi}">
						<input type="button"  value="屏蔽商品" onclick="location.href='EcGoodsPriceModifyForTouchNet.do?method=pingbi&mod_id=${af.map.mod_id}&goods_id_1=${af.map.goods_id}&own_sys=${af.map.own_sys}';" />	
					</c:if>
					<c:if test="${not empty af.map.is_pingbi}">
						<input type="button"  value="恢复屏蔽" onclick="location.href='EcGoodsPriceModifyForTouchNet.do?method=huifu&mod_id=${af.map.mod_id}&goods_id_1=${af.map.goods_id}&own_sys=${af.map.own_sys}';" />	
					</c:if>
				</td>
			</tr>
		</table>		
	</div>
	<c:if test="${not empty eptList}">
  <div class="rtabcont1">
  	<table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
  		<tr class="tabtt1">
  		<td width="5%" nowrap="nowrap" align="center">序号</td>
        <td nowrap="nowrap" align="center">商品名称</td>
        <td nowrap="nowrap" width="10%"  align="center" >部门ID</td>
        <td nowrap="nowrap" width="10%"  align="center" >类型</td>
        <td nowrap="nowrap" width="10%"  align="center" >价格</td>
        <td nowrap="nowrap" width="10%"  align="center" >销售价格</td>
		</tr>
        <c:forEach items="${eptList}" var="cur" varStatus="vs">
		<tr>
		<td align="center" nowrap="nowrap">${vs.count}</td>
		<td align="center" nowrap="nowrap">${cur.map.pd_name}</td>
		<td align="center" nowrap="nowrap">${cur.dept_id}</td>
		<td align="center" nowrap="nowrap">
			<c:if test="${cur.type eq 0}">价格修改中</c:if>
			<c:if test="${cur.type eq 1}">屏蔽商品中</c:if>
		</td>
		<td align="center" nowrap="nowrap">${cur.price}</td>
		<td align="center" nowrap="nowrap">${cur.original_price}</td>
		</tr>
		</c:forEach>
  	</table>
  </div>
  </c:if>
  <div class="rtabcont1">
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
      <tr class="tabtt1">
        <td width="5%" nowrap="nowrap" align="center">序号</td>
        <td nowrap="nowrap" align="center">商品名称</td>
        <td nowrap="nowrap" width="10%"  align="center" >商品所属</td>
        <td nowrap="nowrap" width="10%"  align="center" >价格类型</td>
        <td nowrap="nowrap" width="10%"  align="center" >分公司</td>
        <td nowrap="nowrap" width="20%"  align="center" >管辖区域</td>
        <td nowrap="nowrap" width="10%"  align="center">原价格</td>
        <td nowrap="nowrap" width="10%"  align="center">现销售价格</td>
        <td nowrap="nowrap" width="10%"  align="center">操作</td>
      </tr>
      <c:forEach items="${entityList}" var="cur" varStatus="vs">
        <tr>
          <td align="center" nowrap="nowrap">${vs.count}</td>
          <td align="left"  nowrap="nowrap">${cur.map.pd_name}</td>
          <td  align="left" nowrap="nowrap" >
          <c:if test="${af.map.pd_dept eq 0}">总部产品</c:if>
           <c:if test="${af.map.pd_dept ne 0}">分公司产品</c:if>
          </td>
          <td align="center" nowrap="nowrap">
          	<c:if test="${cur.price_type eq 0}">全国价格</c:if>
          	<c:if test="${cur.price_type eq 2}">分公司价格</c:if>
          	<c:if test="${cur.price_type eq 3}">县市价格</c:if>
          </td>
          <td align="center" nowrap="nowrap">${cur.map.dept_name}</td>
          <td align="center" >${cur.map.areaList}</td>
          <td align="center" nowrap="nowrap">${cur.original_price}</td>
          <td align="center" nowrap="nowrap">${cur.price}</td>
          <td align="center" nowrap="nowrap">
          
          <c:if test="${is_admin eq 1}">
          <input type="button" value="删除" onclick=" if(confirm('确定删除?')) location.href='EcGoodsPriceModifyForTouchNet.do?method=delete2&id=${cur.id}&goods_id=${cur.goods_id}&own_sys=${af.map.own_sys}&mod_id=${af.map.mod_id}';" />
          </c:if>
          <c:if test="${is_fgs eq 1 && af.map.pd_dept eq af.map.fgs_id}">
	          <input type="button" value="删除" onclick=" if(confirm('确定删除?')) location.href='EcGoodsPriceModifyForTouchNet.do?method=delete2&id=${cur.id}&goods_id=${cur.goods_id}&own_sys=${af.map.own_sys}&mod_id=${af.map.mod_id}';" />
          </c:if>
          </td>
        </tr>
      </c:forEach>
      <tr>
        <td colspan="9" align="center"><html-el:button property="" value="返 回" styleClass="but5" styleId="btn_back" onclick="location.href = '${ctx}/customer/manager/PdShow.do?method=list&own_sys=${af.map.own_sys}&mod_id=${af.map.mod_id}' " /></td>
      </tr>
    </table>
    <c:if test="${not vs.last}">
      <div style="height:40px;"></div>
    </c:if>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="EcGoodsPriceModifyForTouchNet.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40"><div style="text-align: right; padding-right: 5px;">
              <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
              <script type="text/javascript">
								var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
								pager.addHiddenInputs("method", "list2");
								pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
								pager.addHiddenInputs("goods_id", "${af.map.goods_id}");
								document.write(pager.toString());
							</script>
            </div></td>
        </tr>
      </table>
    </form>
  </div>
  <div class="clear"></div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/imgpreview.0.22.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/jNotify/jNotify.jquery.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

	
});

//]]>
</script>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>
