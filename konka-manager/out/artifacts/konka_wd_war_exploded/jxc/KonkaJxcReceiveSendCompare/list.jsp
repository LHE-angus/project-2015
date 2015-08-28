<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${naviString}</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer/jquery-ui.custom.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
-->
</style>
</head>
<body>
<div style="width:100%">
  <div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;" /> 当前位置：${naviString}</div>
  <div class="rtabcont1">
    <html-el:form action="/KonkaJxcReceiveSendCompare">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" />
      <input type="hidden" name="search_flag" value="search_flag" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableTop">
        <tr>
          <td  class="fb" ><span style="color:red">*</span>网点：</td>
          <td colspan=2 ><html-el:hidden property="shop_id_temp" styleId="shop_id_temp"/>
            <html-el:select property="branch_or_wd_id" styleId="branch_or_wd_id" style="width:160px;">
              <html-el:option value="">请选择网点...</html-el:option>
              <c:forEach items="${konkaR3ShopList}" var="cur">
                <html-el:option value="${cur.id}">${cur.customer_name}</html-el:option>
              </c:forEach>
            </html-el:select></td>
          <td  class="fb" >产品型号：</td>
          <td ><html-el:select property="pd_id" styleId="pd_id" style="width:160px;">
              <html-el:option value="">请选择产品型号...</html-el:option>
              <c:forEach items="${pePdModelList}" var="cur">
                <html-el:option value="${cur.pd_id}">${cur.md_name}</html-el:option>
              </c:forEach>
            </html-el:select></td>
        </tr>
        <tr>
          <td ><strong class="fb">销售日期：</strong></td>
          <td><input type="text" name="start_date" id="start_date" value="${af.map.start_date}" readonly="readonly" onclick="new Calendar(1990, 2020, 0).show(this);" class="webinput" style="cursor:pointer;width:80px;"/>
            &nbsp;至
            <input type="text" name="end_date" id="end_date" value="${af.map.end_date}" readonly="readonly" onclick="new Calendar(1990, 2020, 0).show(this);" class="webinput" style="cursor:pointer;width:80px;"/></td>
          <td ><strong class="fb">确认状态：</strong></td>
          <td><html-el:select property="is_confirm" styleId="is_confirm" style="width:80px;">
              <html-el:option value="">全部</html-el:option>
              <html-el:option value="0">未确认</html-el:option>
              <html-el:option value="1">已确认</html-el:option>
            </html-el:select></td>
          <td><input class="bgSearch" type="button" name="buttonSearch" id="buttonSearch" value="搜 索" />
            <span class="jxcTip">默认不显示数据，选择条件点搜索显示数据</span></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <form id="listForm" name="listForm" method="post" action="">
    <input type="hidden" name="start_date" value="${af.map.start_date}" />
    <input type="hidden" name="end_date" value="${af.map.end_date}" />
    <input type="hidden" name="is_confirm_temp" value="${af.map.is_confirm}" />
    <div class="rtabcont1">
      <%@ include file="/commons/pages/messages.jsp" %>
    </div>
    <div class="rtabcont1">
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
        <c:if test="${ not empty af.map.search_flag }">
          <tr>
            <th colspan=7><b>${shopName}&nbsp;&nbsp;${af.map.start_date}至${af.map.end_date}收发货记录对比表</b></th>
          </tr>
        </c:if>
        <tr>
          <th width="5%" nowrap="nowrap">序号</th>
          <th nowrap="nowrap">产品型号</th>
          <th width="12%" nowrap="nowrap">产品大类</th>
          <th width="12%" nowrap="nowrap">产品品牌</th>
          <th width="10%" nowrap="nowrap">分公司发货总数</th>
          <th width="10%" nowrap="nowrap">网点进货总数</th>
          <th width="12%" nowrap="nowrap">操作</th>
        </tr>
        <c:forEach items="${entityList}" var="cur" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">${vs.count} </td>
            <td align="center" nowrap="nowrap">${fn:escapeXml(cur.pd_name)}</td>
            <td align="center" nowrap="nowrap">${fn:escapeXml(cur.pd_type_name)}</td>
            <td align="center" nowrap="nowrap">${fn:escapeXml(cur.brand_name)}</td>
            <td align="center" nowrap="nowrap">${cur.count}</td>
            <td align="center" nowrap="nowrap">${cur.map.jh_count}</td>
            <td align="center" nowrap="nowrap"><span style="cursor:pointer;"  class="fblue"  onclick="doNeedMethod(null, 'KonkaJxcReceiveSendCompare.do', 'viewJh','pd_id=${cur.pd_id}&branch_or_wd_id=${cur.branch_or_wd_id}&pd_fh_num=${cur.count}&mod_id=${af.map.mod_id}&'+$('#listForm').serialize())">进货来源查看</span> <span style="cursor:pointer;"  class="fblue"  onclick="doNeedMethod(null, 'KonkaJxcReceiveSendCompare.do', 'viewFh','pd_id=${cur.pd_id}&branch_or_wd_id=${cur.branch_or_wd_id}&pd_fh_num=${cur.count}&pd_jh_num=${cur.map.jh_count}&mod_id=${af.map.mod_id}&'+$('#listForm').serialize())">发货来源查看</span></td>
          </tr>
        </c:forEach>
      </table>
    </div>
    <div class="rtabcont1" style="display:none;" id="divExcel" title="${shopName}${start_date}至${end_date}发货来源详细表.xls" >
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
        <c:if test="${ not empty af.map.search_flag }">
          <tr>
            <th colspan=7><b>${shopName}&nbsp;&nbsp;${af.map.start_date}至${af.map.end_date}收发货记录对比表</b></th>
          </tr>
        </c:if>
        <tr>
          <th width="5%" nowrap="nowrap">序号</th>
          <th nowrap="nowrap">产品型号</th>
          <th width="12%" nowrap="nowrap">产品大类</th>
          <th width="12%" nowrap="nowrap">产品品牌</th>
          <th width="10%" nowrap="nowrap">分公司发货总数</th>
          <th width="10%" nowrap="nowrap">网点进货总数</th>
        </tr>
        <c:forEach items="${entityList}" var="cur" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">${vs.count} </td>
            <td align="center" nowrap="nowrap">${fn:escapeXml(cur.pd_name)}</td>
            <td align="center" nowrap="nowrap">${fn:escapeXml(cur.pd_type_name)}</td>
            <td align="center" nowrap="nowrap">${fn:escapeXml(cur.brand_name)}</td>
            <td align="center" nowrap="nowrap">${cur.count}</td>
            <td align="center" nowrap="nowrap">${cur.map.jh_count}</td>
          </tr>
        </c:forEach>
      </table>
    </div>
  </form>
  <c:if test="${not empty entityList}">
    <div class="rtabcont3">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" style="padding-left:5px;padding-top:10px">
        <tr>
          <td height="26" align="center"><div class="left">
              <input name="button" type="button" class="bgButtonExport" id="toExcel" value="导出" onclick="toExcel('divExcel', '?method=toExcel');"/>
              &nbsp;
              <input name="button" type="button" class="bgButtonBack" id="button" value="返回" onclick="history.back();"/>
            </div></td>
        </tr>
      </table>
    </div>
  </c:if>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/print.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#branch_or_wd_id").multiselect({
			selectedList: 1,
			multiple: false,
			minWidth:320
			
		}).multiselectfilter({width:230});                                         
		 $("#pd_id").multiselect({
			selectedList:1,
			multiple: false,
			minWidth:320
		}).multiselectfilter({width:230});                                         
		
			var f=document.forms[0];
			 $(".bgSearch").click(function(){
			    	var s_time = $("#start_date").val();
					var e_time = $("#end_date").val();
					if ("" != s_time && "" != e_time && s_time > e_time) {
						alert("开始日期不能大于结束日期！");
						return false;
					}
					var id=$("#branch_or_wd_id").val();
					if(id != ''){
						$("#shop_id_temp").val(id);
					}
					if($("#shop_id_temp").val()==''){
						//$("#shop_id_temp").attr("dataType", "Require").attr("msg", "请选择网点");
						alert("请选择网点!");
						return false;
					}
					$("#shop_id_temp").val("");//清空隐藏域临时变量
					//$("#branch_or_wd_id").attr("dataType", "Require").attr("msg", "请选择网点");
					if(!Validator.Validate(f, 1)){
						return false;
					}else{
						f.submit();
					}
			    });  
	});                              
	
            
 
//]]></script>
<jsp:include page="../public_page.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>