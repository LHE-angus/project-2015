<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.rtable1 td {
	padding:2px 5px;
}


.i {
color: #f60;
font-style: normal;
}

</style>
</head>
<body>
<div class="oarcont" id="body_oarcont">
	<table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="25%">
          	<strong class="fb">&nbsp;&nbsp;客户名称：</strong>${kh_name}
          </td>
          <td width="25%">
          	<strong class="fb">客户R3编码：</strong>${r3_code}
          </td>
        </tr>
    </table>
    <table width="100%">
    	<tr>
    		<td style="padding-left: 10px; font-weight:normal;color:#A8A8A8;" class="rtabcont1">注：此列表仅显示该客户已初始化库存的型号信息(已去除全部数据为0的记录)</td>
    		<td align="right" style="padding-right: 10px">单位：台、元</td>
    	</tr>
    </table>
  	<div class="rtabcont1" style="overflow-x: auto;" id="divExcel_all" title="销售明细">
  	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
  		<tr class="tabtt1">
  			<td width="5%" align="center" nowrap="nowrap">序号</td>

        	<td align="center" nowrap="nowrap">客户名称</td>
        	<td align="center" nowrap="nowrap">R3编码</td>        	
        	<td align="center" nowrap="nowrap">型号</td>        	
            <td width="3%" nowrap="nowrap" align="center">进货量</td>
        	<td width="6%" nowrap="nowrap" align="center">进货额</td>       	
        	<td width="3%" nowrap="nowrap" align="center">销售量</td>
        	<td width="6%" nowrap="nowrap" align="center">销售额</td>  
         	<td width="6%" nowrap="nowrap" align="center">库存量</td>
        	<td width="3%" nowrap="nowrap" align="center">库存额</td>    
        	<td width="6%" nowrap="nowrap" align="center">周转天数（量）</td>
        	<td width="3%" nowrap="nowrap" align="center">周转天数（额）</td>   
  		</tr>
  		<c:forEach var="cur" items="${entityList}" varStatus="vs">
  			<tr class="list-tr">
  				<td align="center" nowrap="nowrap">${vs.count}</td>
  				<td align="left" nowrap="nowrap">${kh_name}</td>
  				<td align="left" nowrap="nowrap">${r3_code}</td>  				
  				<td align="left" nowrap="nowrap">${cur.GOODS_NAME}</td>  				
  				<td align="right" nowrap="nowrap" >
  					<span title="点击可查看详情" class="fblue" style="cursor:pointer;" onclick="showModels('${r3_code}','${cur.GOODS_NAME}','${cur.GOODS_ID}','${date_begin}','${date_end}','in');">
  						<font class="blue12px">
  							<fmt:formatNumber value="${cur.IN_NUM}" pattern="#,###"/>
  						</font>
  					</span>
  				</td> 
   				<td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.IN_MONEY}" pattern="#,##0.00"/></td>
  				<td align="right" nowrap="nowrap" >
  					<span title="点击可查看详情" class="fblue" style="cursor:pointer;" onclick="showModels('${r3_code}','${cur.GOODS_NAME}','${cur.GOODS_ID}','${date_begin}','${date_end}','out');">
  						<font class="blue12px">
  							<fmt:formatNumber value="${cur.OUT_NUM}" pattern="#,###"/>
  						</font>
  					</span>
  				</td> 
  				<td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.OUT_MONEY}" pattern="#,##0.00"/></td>
  				<td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.STORE_NUM}" pattern="#,###"/></td>
  				<td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.STORE_MONEY}" pattern="#,##0.00"/></td>
  				<td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.ZZ_NUM}" pattern="#,##0.00"/></td>
  				<td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.ZZ_MONEY}" pattern="#,##0.00"/></td>
  			</tr>
  		</c:forEach>
  	</table>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/scripts/lhgdialog/lhgdialog.min.js?skin=discuz"></script>
<script type="text/javascript">
	//弹出客户型号数据列表
	function showModels(r3_code,model_name,goods_id,date_begin,date_end,flag){
		kh_name = encodeURI(encodeURI('${kh_name}'));
		$.dialog({
			title:  "记录详情列表",
			width:  800,
			height: 500,
	        lock:true ,
			content:"url:KonkaMobileSailDataSearch.do?method=listForModelDetails&r3_code="+
					r3_code+"&kh_name="+kh_name+"&r3_id=${r3_id}&goods_id="+goods_id+"&model_name="+
					model_name+"&date_begin="+date_begin+"&date_end="+date_end+"&flag="+flag,
			zIndex:1980
		});
		//$.dialog({title:'我是新标题'});
	}                                   
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>