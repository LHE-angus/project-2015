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
<link href="${ctx}/styles/customer/jNotify/jNotify.jquery.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.rtable1 td {
	padding:2px 5px;
}

span.sel-tab {
	height: 30px;
	display: block;
	float : right;
	padding: 0px 10px;
	margin-top: 2px;
	margin-right: 10px;
	border-radius:3px 3px 0px 0px;
}
span.sel-tab-active {
	border-bottom : 0px;
	border-top: 1px solid #ccc;
	border-left: 1px solid #ccc;
	border-right: 1px solid #ccc;
	background-color: #FFF;
}
</style>
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align: middle;" /></td>
        <td>当前位置：${naviString}</td>
        <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align: middle;" /> <span id="span_help" style="cursor: pointer;">帮助</span></td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
			<html-el:form action="/admin/KonkaYjglPlanFpTj">
				<html-el:hidden property="method" value="list" />
				<html-el:hidden property="mod_id" styleId="mod_id" />
				<table width="100%" border="0" cellspacing="1" cellpadding="5"
					class="rtable1">
					<tr>
						<td width="20%" nowrap="nowrap" ><strong
							class="fb">部门：</strong><html-el:select property="dept_id" styleId="dept_id" style="width:120px;" onchange="this.form.submit();">
	          				<html-el:option value="">请选择</html-el:option>
	          				<c:forEach items="${deptList}" var="cur">
	          					<html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
	          				</c:forEach>
	          			</html-el:select>
						</td>
						<td width="30%"><strong class="fb">门店：</strong>
							<html-el:text property="store_name_like" styleId="store_name_like"  maxlength="30"/>
						</td>
						<td width="20%"><strong class="fb">是否出样：</strong>
							<html-el:select property="is_cy" styleId="is_cy" style="width:120px;" onchange="this.form.submit();">
	          					<html-el:option value="2">全部</html-el:option>
	          					<html-el:option value="0">是</html-el:option>
	          					<html-el:option value="1">否</html-el:option>
	          			</html-el:select>  
						</td>
						<td width="30%"><strong class="fb">是否拖期：</strong>
							<html-el:select property="is_tq" styleId="is_tq" style="width:120px;" onchange="this.form.submit();">
	          					<html-el:option value="全部">全部</html-el:option>
	          					<html-el:option value="是">是</html-el:option>
	          					<html-el:option value="否">否</html-el:option>
	          			</html-el:select>  
						</td>
						
					</tr>
					<tr>
					<td width="20%"><strong
							class="fb">型号：</strong>
        		<html-el:text property="pd_name_like" styleId="pd_name_like"  maxlength="30"/>
				</td>
				<td width="30%"><strong
							class="fb">计划出样时间：</strong>
        		<html-el:text property="add_time_start" styleId="add_time_start"  size="9" maxlength="10" readonly="true" onclick="new Calendar(2010, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
				至
				<html-el:text property="add_time_end" styleId="add_time_end"  size="9" maxlength="10" readonly="true" onclick="new Calendar(2010, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
				</td>
					<td width="30%"><strong
							class="fb">直销员：</strong>  
        		<html-el:text property="cxy_name_like" styleId="cxy_name_like" maxlength="30" />
				</td>
					<td width="20%"><html-el:submit
								styleId="btn_submit" styleClass="but1">搜索</html-el:submit></td>
					</tr>
				</table>
			</html-el:form>  
		</div>
  <div class="rtabcont1" style="overflow-x: auto;">
    <table width="100%" border="0" cellspacing="0" cellpadding="0"
	class="rtable2">
      <tr > 
        <td width="5%" align="center" rowspan="2" nowrap="nowrap">序号</td>
        <td width="4%" align="center" rowspan="2" nowrap="nowrap">事业部</td>
        <td width="4%" align="center" rowspan="2" nowrap="nowrap">分公司</td>
        <td width="6%" align="center" rowspan="2" nowrap="nowrap">门店</td>
        <td align="center" width="6%" rowspan="2" nowrap="nowrap">型号</td>
        <td align="center" width="6%" rowspan="2" nowrap="nowrap">是否出样</td>
        <td width="3%" nowrap="nowrap" rowspan="2" align="center">是否拖期出样</td>
        <td width="3%" nowrap="nowrap" rowspan="2" align="center">出样计划时间</td>
        <td width="3%" nowrap="nowrap" rowspan="2" align="center">实际出样时间</td>
        <td width="3%" nowrap="nowrap" rowspan="2" align="center">出样差异数量</td>
        <td nowrap="nowrap" width="6%" rowspan="2" align="center">出样计划数量</td>
        <td nowrap="nowrap" width="6%" rowspan="2" align="center">实际出样数量</td>
         <td nowrap="nowrap" width="6%" rowspan="2" align="center">下样数量</td>
        <td nowrap="nowrap" width="6%" colspan="3" align="center">出样照片</td>
        <td nowrap="nowrap" width="6%" colspan="4" align="center">出样提报人</td>
      </tr>
      <tr >
         <td nowrap="nowrap" width="6%"  align="center">正面照</td>
         <td nowrap="nowrap" width="6%"  align="center">背面照</td>
         <td nowrap="nowrap" width="6%"  align="center">侧面照</td>
         <td nowrap="nowrap" width="6%"  align="center">直销员</td>
         <td nowrap="nowrap" width="6%"  align="center">直销员手机</td>
         <td nowrap="nowrap" width="6%"  align="center">业务员</td>
         <td nowrap="nowrap" width="6%"  align="center">业务员手机</td>
      </tr>
      <c:forEach var="cur" items="${entityList}" varStatus="vs">
        <tr class="list-tr">
          <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
          <td align="left" nowrap="nowrap">${cur.map.bra_name}</td>
          <td align="left" nowrap="nowrap">${cur.map.dept_name}</td>
          <td align="left" nowrap="nowrap">
            ${cur.map.store_name}
          </td>
          <td align="left" nowrap="nowrap">
           ${cur.map.pd_name} 
          </td>
          <td align="left" nowrap="nowrap">
           <c:if test="${empty cur.map.up_date}">否</c:if>
           <c:if test="${not empty cur.map.up_date}">是</c:if>
          </td>
           <td align="left" nowrap="nowrap">
          	${cur.map.is_tq}
          </td>
          <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.map.sy_date}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
          <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.map.up_date}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
          <td align="left" nowrap="nowrap">
          	 ${cur.map.wc_num}
          </td>
          <td align="left" nowrap="nowrap">
          	 ${cur.map.num}
          </td>
           <td align="left" nowrap="nowrap">
          	 ${cur.map.sy_num}
          </td>
           <td align="left" nowrap="nowrap">
          	 ${cur.map.xy_num}
          </td>
          <td align="left" nowrap="nowrap">
          <c:if test="${not empty cur.map.zm}">
          <a href="${ctx}/${cur.map.zm}" target="_blank">&nbsp;正面&nbsp;</a>
          </c:if>
          </td>
           <td align="left" nowrap="nowrap">
           <c:if test="${not empty cur.map.bm}">
          <a href="${ctx}/${cur.map.bm}" target="_blank">&nbsp;背面&nbsp;</a>
          </c:if>
          </td>
           <td align="left" nowrap="nowrap">
          <c:if test="${not empty cur.map.cm}">
          <a href="${ctx}/${cur.map.cm}" target="_blank">&nbsp;侧面&nbsp;</a>
          </c:if>
          </td>
           <td align="left" nowrap="nowrap">
          	 ${cur.map.cxy_name}
          </td>
           <td align="left" nowrap="nowrap">
          	 ${cur.map.cxy_phone}
          </td>
           <td align="left" nowrap="nowrap">
          	 ${cur.map.ywy_name}
          </td>
           <td align="left" nowrap="nowrap">
          	 ${cur.map.ywy_phone}
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
          <td>&nbsp;</td>  
          <td>&nbsp;</td>  
          <td>&nbsp;</td>       
        </tr>
      </c:forEach>
    </table>
  </div>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaYjglPlanFpTj.do">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
            <script type="text/javascript"> 
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
			pager.addHiddenInputs("dept_id", "${af.map.dept_id}");							
			pager.addHiddenInputs("is_cy", "${af.map.is_cy}");
			pager.addHiddenInputs("is_tq", "${af.map.is_tq}");
			pager.addHiddenInputs("add_time_end", "${af.map.add_time_end}");
			pager.addHiddenInputs("add_time_start", "${af.map.add_time_start}");
			pager.addHiddenInputs("cxy_name_like", "${af.map.cxy_name_like}");
			pager.addHiddenInputs("store_name_like", "${af.map.store_name_like}");							
			pager.addHiddenInputs("pd_name_like", "${af.map.pd_name_like}");	
            document.write(pager.toString());
            </script></td>
        </tr>
      </table>
    </form>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/imgpreview.0.22.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/jNotify/jNotify.jquery.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	
	//$(".list-tr td").each(function(){
		//var text = $(this).html();
		//if($.trim(text).length == 0) {
			//$(this).html("<span style='color:#CCC;'>未填写</span>");
		//}
	//});

	
	
});


//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
