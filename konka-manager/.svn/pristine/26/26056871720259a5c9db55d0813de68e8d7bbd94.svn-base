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
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
</head>
<body>
<div class="oarcont">
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp"%>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td>
             <c:if test="${is_admin eq 1 }"> 
        	<input type="button" class="but2" value="新 增" onclick="location.href='EcStocksNew.do?method=add2&goods_id=${af.map.goods_id}&mod_id=${af.map.mod_id}';" />
        	</c:if>
            <c:if test="${is_fgs eq 1 and af.map.lock_state ne 1}">    
        	<input type="button" class="but2" value="新 增" onclick="location.href='EcStocksNew.do?method=add2&goods_id=${af.map.goods_id}&mod_id=${af.map.mod_id}';" />
        	</c:if>
        </td>
      </tr>
    </table>
  </div>
   <div style="background-color: yellow;font-size: 15px;">商品型号：${af.map.pd_sn}</div>
  <div class="rtabcont1" >
    <table class="rtable2" width="100%" border="0" cellspacing="1" cellpadding="0">
      <tr class="tabtt1">
        <td width="5%"  align="center">序号</td>
        <td width="10%"  align="center">仓库名称</td>
        <td width="10%"  align="center" >仓库类型</td>
        <td width="10%"  align="center" >仓库所属</td>
        <td width="10%"  align="center" >库存</td>
        <td  width="10%" align="center">操作</td> 
      </tr>
      <c:forEach var="cur" items="${entityList}" varStatus="vs">
        <tr>
          <td align="center"  >${vs.count}</td>
          <td align="left" width="10%"  >${cur.map.store_name}</td>
          <td align="left" width="10%"  >
          	<c:if test="${cur.map.store_type eq 0}">全国仓</c:if>
          	<c:if test="${cur.map.store_type eq 1}">区域仓</c:if>
          	<c:if test="${cur.map.store_type eq 2}">分公司仓</c:if>
          </td>
          <td align="left" width="10%" ><c:if test="${cur.plat_sys eq 0}">总部</c:if><c:if test="${cur.plat_sys eq 1}">分公司</c:if></td>
          <td align="right" width="10%"  >${cur.stocks}</td>
          <td nowrap="nowrap" align="center"> 
          <c:if test="${not empty cur.store_id }">
          <c:if test="${is_admin eq 1}">
         	 <input type="button" class="but2" value="修改" onclick=" location.href='EcStocksNew.do?method=edit2&store_id=${cur.store_id}&goods_id=${cur.goods_id}&store_type=${cur.map.store_type}&mod_id=${af.map.mod_id}&own_sys=${af.map.own_sys}';" />
             <input type="button" class="but2" value="删除" onclick=" if(confirm('确定删除?')) location.href='EcStocksNew.do?method=delete2&store_id=${cur.store_id}&goods_id=${cur.goods_id}&mod_id=${af.map.mod_id}&own_sys=${af.map.own_sys}';" />
          </c:if>
           <c:if test="${is_fgs eq 1 and af.map.lock_state ne 1  and cur.plat_sys eq 1 and  (fn:contains(af.map.group_id,cur.map.dept_id) and cur.map.dept_id ne 0 )}">
          	 <input type="button" class="but2" value="修改" onclick=" location.href='EcStocksNew.do?method=edit2&store_id=${cur.store_id}&goods_id=${cur.goods_id}&store_type=${cur.map.store_type}&mod_id=${af.map.mod_id}&own_sys=${af.map.own_sys}';" />
             <input type="button" class="but2" value="删除" onclick=" if(confirm('确定删除?')) location.href='EcStocksNew.do?method=delete2&store_id=${cur.store_id}&goods_id=${cur.goods_id}&mod_id=${af.map.mod_id}&own_sys=${af.map.own_sys}';" />
           </c:if>
           </c:if>
          </td>
        </tr>
      </c:forEach>
    </table>
    <c:if test="${not vs.last}">
      <div style="height:40px;"></div>
    </c:if>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="EcStocksNew.do">
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
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript">//<![CDATA[  
$(document).ready(function(){
	 
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
