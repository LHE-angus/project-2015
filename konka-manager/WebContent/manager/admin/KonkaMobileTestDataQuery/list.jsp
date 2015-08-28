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
    <html-el:form action="/admin/KonkaMobileTestDataQuery">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="is_del" styleId="is_del" />
      <table width="100%" border="0" cellspacing="1" cellpadding="5" class="rtable1">
        <tr>
          <td align="right" width="10%" nowrap="nowrap" title="客户业务员所在部门">
          	<strong class="fb">部门：</strong></td>
          <td>
          <c:if test="${empty current_dept}">
            <html-el:select property="l3_dept_id" styleId="l3_dept_id" disabled="${disabled}">
          		<html-el:option value="">-分公司/经营部-</html-el:option>
          	</html-el:select>
          	<html-el:select property="l4_dept_id" styleId="l4_dept_id">
          		<html-el:option value="">-请选择-</html-el:option>
          	</html-el:select>
          	<html-el:select property="l5_dept_id" styleId="l5_dept_id">
          		<html-el:option value="">-请选择-</html-el:option>
          	</html-el:select></c:if><c:if test="${not empty current_dept}">${fn:replace(current_dept.full_name, ',', ' &gt; ')}</c:if>
            </td>
          <td align="right"><strong class="fb">客户名称：</strong></td>
          <td>
          	<html-el:text property="customer_name_like" size="26" maxlength="40" styleId="customer_name_like" styleClass="webinput" />
          </td>
          <td align="right"><strong class="fb">上报人 ：</strong></td>
          <td><html-el:text property="report_name_like" size="26" maxlength="40" styleId="report_name_like" styleClass="webinput" /></td>
        </tr>
        <tr>
          <td align="right"><strong class="fb">上报门店：</strong></td>
          <td><html-el:text property="dept_name_like" size="26" maxlength="40" styleId="dept_name_like"  /></td>
          <td align="right"><strong class="fb">时间范围：</strong></td>
          <td><html-el:text property="date_begin" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" onclick="new Calendar(2005, 2030, 0).show(this);" />
            -
            <html-el:text property="date_end" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" onclick="new Calendar(2005, 2030, 0).show(this);" />
          </td>
          <td align="right"><strong class="fb">型号-模糊查找：</strong></td>
          <td>
          	<html-el:text property="model_name_like" size="26" maxlength="40" styleId="model_name_like"  />
          	<!--<html-el:select property="measure_id" styleId="measure_id" onchange="category_id_chg();">
              <html-el:option value="">-选择尺寸-</html-el:option>
              <html-el:optionsCollection label="name" name="sizeList" value="name" />
            </html-el:select>
            &nbsp;
            <select name="model_id" id="model_id">
              <option value="">-产品型号-</option>
            </select>-->
          </td>
        </tr>
        <tr>
           <td align="right"><strong class="fb">上样时间：</strong></td>
          <td>
          	<html-el:text property="up_date_begin" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" onclick="new Calendar(2005, 2030, 0).show(this);" />
            -
            <html-el:text property="up_date_end" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" onclick="new Calendar(2005, 2030, 0).show(this);" />
          </td>
          <td align="right"><strong class="fb">下样时间：</strong></td>
          <td>
          	<html-el:text property="down_date_begin" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" onclick="new Calendar(2005, 2030, 0).show(this);" />
            -
            <html-el:text property="down_date_end" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" onclick="new Calendar(2005, 2030, 0).show(this);" />
           </td>
           <td align="right"><strong class="fb">客户分类：</strong></td>
          <td>
          	<html-el:select property="c_comm" styleId="c_comm" onchange="customer_cate_id_chg();">
              <html-el:option value="">-客户类型-</html-el:option>
              <c:forEach var="cur" items="${konkaCategoryList}">
              	 <c:if test="${not empty cur.map.c_comm}">
              	 	<html-el:option value="${cur.map.par_index}">${cur.map.c_comm}</html-el:option>
              	 </c:if>
              </c:forEach>
            </html-el:select>&nbsp;
            <select name="customer_cate_id" id="customer_cate_id">
              <option value="">-细分类型-</option>
            </select>
           </td>
        </tr>
        <tr>
        	<td align="right"><strong class="fb">上样状态：</strong></td>
          	<td>
          		<html-el:select property="test_statu" styleId="test_statu" onchange="this.form.submit();">
              		<html-el:option value="0">上样中</html-el:option>
              		<html-el:option value="1">已下样</html-el:option>
            	</html-el:select>
           </td>
        	<td colspan="4" align="left" nowrap="nowrap" style="padding-left:7%">
          	<html-el:submit styleId="btn_submit" styleClass="but1">搜索</html-el:submit>
            <input type="button" value="Excel" id="export_excel" class="but_excel" style="margin-left: 10px;" />
          </td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp"%>
  </div>
  <div class="rtabcont1" style="overflow-x: auto;">
    <table width="100%" border="0" cellspacing="0" cellpadding="0"
	class="rtable2">
      <tr class="tabtt1">
        <td width="5%" align="center" nowrap="nowrap">序号</td>
        <td align="center" nowrap="nowrap">日期</td>
        <td width="4%" align="center" nowrap="nowrap">分公司</td>
        <td width="6%" align="center" nowrap="nowrap">经办</td>
        <td align="center" nowrap="nowrap">客户名称</td>
        <td align="center" nowrap="nowrap">客户R3编码</td>
        <td align="center" nowrap="nowrap">门店R3编码</td>
        <td width="6%" align="center" nowrap="nowrap">客户类型</td>
        <td width="6%" align="center" nowrap="nowrap">细分类型</td>
        <td width="4%" nowrap="nowrap" align="center">上报人</td>
        <td align="center" nowrap="nowrap">上报门店</td>
        <td width="3%" nowrap="nowrap" align="center">尺寸</td>
        <td width="8%" nowrap="nowrap" align="center">产品型号</td>
        <td width="8%" nowrap="nowrap" align="center">串码</td>
        <td width="3%" nowrap="nowrap" align="center">数量</td>
        <td width="6%" nowrap="nowrap" align="center">挂牌价</td>
        <td width="6%" nowrap="nowrap" align="center">上样时间</td>
        <td width="6%" nowrap="nowrap" align="center">下架时间</td>
        <td width="6%" nowrap="nowrap" align="center">状态</td>
        <td width="6%" nowrap="nowrap" align="center">总销售量</td>
        <td width="6%" nowrap="nowrap" align="center">总销售额</td>
        <td width="6%" nowrap="nowrap" align="center">正面</td>
        <td width="6%" nowrap="nowrap" align="center">背面</td>
        <td width="6%" nowrap="nowrap" align="center">侧面</td>
        <td width="12%" nowrap="nowrap" align="center">备注</td>
        <logic-el:match name="popedom" value="+2+">
        <td width="5%" nowrap="nowrap" align="center">操作</td>
        </logic-el:match>
      </tr>
      <c:set var="now">
      <fmt:formatDate value="${today}" pattern="yyyy-MM-dd" />
    </c:set>
      <c:forEach var="cur" items="${entityList}" varStatus="vs">
        <tr class="list-tr">
          <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
          <td align="left" nowrap="nowrap"><fmt:formatDate value="${cur.report_date}" pattern="yyyy/MM/dd" /></td>
          <td align="left" nowrap="nowrap">${cur.subcomp_name}</td>
          <td align="left" nowrap="nowrap">${cur.office_name}</td>
          <td align="left" nowrap="nowrap">${cur.channel_a_name}</td>
          <td align="left" nowrap="nowrap">${cur.channel_b_name}</td>
          <td align="left" nowrap="nowrap">${cur.map.store_r3_sn}</td>
          <td align="left" nowrap="nowrap">${cur.map.c_comm}</td>
          <td align="left" nowrap="nowrap">${cur.map.c_name}</td>
          <td align="left" nowrap="nowrap">${cur.report_name}</td>
          <td align="left" nowrap="nowrap">${cur.dept_name}</td>
          <td align="center" nowrap="nowrap">${cur.measure_name}</td>
          <td align="left" nowrap="nowrap">${cur.model_name}</td>
           <td align="left" nowrap="nowrap">${cur.code}</td>
          <td align="right" nowrap="nowrap">${cur.num}</td>
          <td align="right" nowrap="nowrap"><span class="kz-price-12">
            <fmt:formatNumber value="${cur.price}" type="currency" />
            </span></td>
          <td align="right" nowrap="nowrap"><fmt:formatDate value="${cur.up_date}"/></td>
          <td align="right" nowrap="nowrap"><fmt:formatDate value="${cur.down_date}"/></td>
          <c:set var="downDate">
            <fmt:formatDate value="${cur.down_date}" pattern="yyyy-MM-dd" />
          </c:set>
          <td align="right" nowrap="nowrap"> 
         		<c:if test="${not empty downDate}">
          		 <c:if test="${not empty cur.map.up_self}"><span style="color:#009900;">上样中</span></c:if>
	             <c:if test="${empty cur.map.up_self}"><span style="color:#CD0000;">已下样</span></c:if>
          		</c:if>
          		<c:if test="${empty downDate}"> 
          			<c:if test="${not empty cur.map.up_self}">
          			<span style="color:#009900;">上样中</span>
          			</c:if>
          			<c:if test="${empty cur.map.up_self}"><span style="color:#CD0000;">已下样</span></c:if>   
          		</c:if>
          </td>
          <td align="right" nowrap="nowrap">
          ${cur.map.all_num}
          </td>
          <td align="right" nowrap="nowrap">
          <span class="kz-price-12"><fmt:formatNumber value="${cur.map.all_price_1}" type="currency" /> </span>
          </td> 
          <td align="center" nowrap="nowrap">
          <c:if test="${not empty cur.map.zm}">
          <a href="${ctx}/${cur.map.zm}" target="_blank">&nbsp;正面&nbsp;</a>
          </c:if>
          </td>
          <td align="center" nowrap="nowrap">
          <c:if test="${not empty cur.map.bm}">
          <a href="${ctx}/${cur.map.bm}" target="_blank">&nbsp;背面&nbsp;</a>
          </c:if>
          </td> 
          <td align="center" nowrap="nowrap">
           <c:if test="${not empty cur.map.cm}">
          <a href="${ctx}/${cur.map.cm}" target="_blank">&nbsp;侧面&nbsp;</a>
          </c:if>
          </td>  
          <td align="left" nowrap="nowrap">
           <c:set var="ss" value="${cur.memo}" />
           <c:choose>
		     <c:when test="${fn:length(ss) > 15}">
		        ${fnx:abbreviate(ss,15,'...')}
		     </c:when>
		     <c:otherwise>
		        <c:out value="${ss}" />
		     </c:otherwise>
		   </c:choose>
          </td>
          <logic-el:match name="popedom" value="+2+">
            <td align="center" nowrap="nowrap">
            <c:if test="${cur.status eq 0}"> 
            <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod('是否设置为无效！', 'KonkaMobileTestDataQuery.do', 'delete','id=${cur.id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">无效</span> 
            </c:if>
            <c:if test="${cur.status eq 1}"> 
            <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod('是否启用？', 'KonkaMobileTestDataQuery.do', 'reStrat','id=${cur.id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">启用</span> 
            </c:if>
            </td>
          </logic-el:match>
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
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <logic-el:match name="popedom" value="+2+">
          	<td>&nbsp;</td>
          </logic-el:match>
        </tr>
      </c:forEach>
    </table>
  </div>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaMobileTestDataQuery.do">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="center"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
            <script type="text/javascript"> 
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
			pager.addHiddenInputs("date_begin", "${fn:escapeXml(af.map.date_begin)}");							
			pager.addHiddenInputs("date_end", "${fn:escapeXml(af.map.date_end)}");							
			pager.addHiddenInputs("l3_dept_id", "${af.map.l3_dept_id}");							
			pager.addHiddenInputs("l4_dept_id", "${af.map.l4_dept_id}");							
			pager.addHiddenInputs("l5_dept_id", "${af.map.l5_dept_id}");							
			pager.addHiddenInputs("office_id", "${af.map.office_id}");							
			pager.addHiddenInputs("category_id", "${af.map.category_id}");							
			pager.addHiddenInputs("report_name_like", "${af.map.report_name_like}");
			pager.addHiddenInputs("yw_name", "${af.map.yw_name}");
			pager.addHiddenInputs("customer_name_like", "${af.map.customer_name_like}");
			pager.addHiddenInputs("is_del", "${af.map.is_del}");
			pager.addHiddenInputs("dept_name_like", "${af.map.dept_name_like}");									
			pager.addHiddenInputs("c_index", "${af.map.c_index}");									
			pager.addHiddenInputs("customer_cate_id", "${af.map.customer_cate_id}");
			pager.addHiddenInputs("c_comm", "${af.map.c_comm}");										
			//pager.addHiddenInputs("measure_id", "${af.map.measure_id}");	
			pager.addHiddenInputs("model_name_like", "${af.map.model_name_like}");
			pager.addHiddenInputs("up_date_begin", "${af.map.up_date_begin}");
			pager.addHiddenInputs("up_date_end", "${af.map.up_date_end}");
			pager.addHiddenInputs("down_date_begin", "${af.map.down_date_begin}");
			pager.addHiddenInputs("down_date_end", "${af.map.down_date_end}");
			pager.addHiddenInputs("test_statu", "${af.map.test_statu}");
            document.write(pager.toString());
            </script></td>
        </tr>
      </table>
    </form>
  <div class="rtabcont1" style="overflow-x: auto; display: none;" id="divExcel_all" title="销售明细">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
      <tr class="tabtt1">
        <td width="5%" align="center" nowrap="nowrap">序号</td>
        <td align="center" nowrap="nowrap">日期</td>
        <td width="4%" align="center" nowrap="nowrap">分公司</td>
        <td width="6%" align="center" nowrap="nowrap">经办</td>
        <td align="center" nowrap="nowrap">客户名称</td>
        <td align="center" nowrap="nowrap">客户R3编码</td>
        <td width="6%" align="center" nowrap="nowrap">客户类型</td>
        <td width="6%" align="center" nowrap="nowrap">细分类型</td>
        <td width="4%" nowrap="nowrap" align="center">上报人</td>
        <td align="center" nowrap="nowrap">上报门店</td>
        <td width="3%" nowrap="nowrap" align="center">尺寸</td>
        <td width="8%" nowrap="nowrap" align="center">产品型号</td>
        <td width="3%" nowrap="nowrap" align="center">数量</td>
        <td width="6%" nowrap="nowrap" align="center">挂牌价</td>
        <td width="12%" nowrap="nowrap" align="center">备注</td>
      </tr>
      <c:forEach var="cur" items="${allList}" varStatus="vs">
        <tr class="list-tr">
          <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
          <td align="left" nowrap="nowrap"><fmt:formatDate value="${cur.report_date}" pattern="yyyy/MM/dd" /></td>
          <td align="left" nowrap="nowrap">${cur.subcomp_name}</td>
          <td align="left" nowrap="nowrap">${cur.office_name}</td>
          <td align="left" nowrap="nowrap">${cur.channel_a_name}</td>
          <td align="left" nowrap="nowrap">${cur.channel_b_name}</td>
          <td align="left" nowrap="nowrap">${cur.map.c_comm}</td>
          <td align="left" nowrap="nowrap">${cur.map.c_name}</td>
          <td align="left" nowrap="nowrap">${cur.report_name}</td>
          <td align="left" nowrap="nowrap">${cur.dept_name}</td>
          <td align="center" nowrap="nowrap">${cur.measure_name}</td>
          <td align="left" nowrap="nowrap">${cur.model_name}</td>
          <td align="right" nowrap="nowrap">${cur.num}</td>
          <td align="right" nowrap="nowrap"><span class="kz-price-12">
            <fmt:formatNumber value="${cur.price}" type="currency" />
            </span></td>
          <td align="left" nowrap="nowrap">${cur.memo}</td>
        </tr>
      </c:forEach>
    </table>
  </div>
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

	 <c:if test="${empty current_dept}">
	 
		$("#l3_dept_id").attr({"subElement": "l4_dept_id", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l3_dept_id}"});
		$("#l4_dept_id").attr({"subElement": "l5_dept_id", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l4_dept_id}"});
		$("#l5_dept_id").attr({"defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l5_dept_id}"});
	
		$("#l3_dept_id").cs("${ctx}/manager/admin/CsAjax.do?method=getKonkaDeptForParId", "par_id", "${empty cs_par_id ? 0 : cs_par_id}");
		$("#l3_dept_id").change();

	</c:if>
	
	
	//subcomp_id_chg();
	category_id_chg();
	customer_cate_id_chg();
	
	$(".list-tr td").each(function(){
		var text = $(this).html();
		if($.trim(text).length == 0) {
			$(this).html("<span style='color:#CCC;'>未填写</span>");
		}
	});

	var queryForm = document.forms[0];
	$("#span_help").click(function(){
        $("#cvtooltipClose").cvtooltip({
            panel: "#body_oarcont",
            direction: 1,                
            width: 420,
            left: 320,
            top: 5,
            speed: 500,
            delay: 10000
        });
    });

    // 导出excel
    $("#export_excel").click(function(){
    	if(confirm("提示，您确认导出数据？")){
    		loading();
    		$("#bottomPageForm").append("<input type='hidden' name='excel_all' value='1' />");
    		$("#bottomPageForm").submit();
    		//$.jNotify._close();  
    	}
    });

    var excel_all = "${af.map.excel_all}";
	if("1" == excel_all){
		toExcel('divExcel_all', '${ctx}/manager/admin/KonkaMobileTestDataQuery.do?method=toExcel');
	}
	
});

//分公司- 连动-办事处
function subcomp_id_chg(){
	$("#office_id").empty();
	$("<option value=''>-所属办事处-</option>").appendTo($("#office_id"));
	var url = "${ctx}/manager/admin/KonkaMobileTestDataQuery.do?method=getDept&subcomp_id="+$('#subcomp_id').val();
	$.getJSON(url, function(data) {
		if(data != null){
			$.each(data, function(i, item) {
				$("<option></option>").val(item[1]).text(item[0]).appendTo($("#office_id"));
			});
		}
		if('${af.map.office_id }' != null || '${af.map.office_id }' != '' ){
			$("#office_id").val('${af.map.office_id }');
		}
	});
}

//类别-连动-型号
function category_id_chg(){
	$("#model_id").empty();
	$("<option value=''>-产品型号-</option>").appendTo($("#model_id"));
	var url = "${ctx}/manager/admin/KonkaMobileTestDataQuery.do?method=getModel&size_id="+$('#measure_id').val();
	$.getJSON(url, function(data) {
		if(data != null){
			$.each(data, function(i, item) {
				var option = $("<option></option>").val(item[1]).text(item[0]);
				option.appendTo($("#model_id"));
			});
			if('${af.map.model_id }' != null || '${af.map.model_id }' != '' ){
				$("#model_id").val('${af.map.model_id }');
			}
		}
	});
}

//类别-连动-客户类别
function customer_cate_id_chg(){
	$("#customer_cate_id").empty();
	$("<option value=''>-细分类型-</option>").appendTo($("#customer_cate_id"));
	var url = "${ctx}/manager/admin/KonkaMobileTestDataQuery.do?method=getCType&c_comm="+$('#c_comm').val();
	$.getJSON(url, function(data) {
		if(data != null){
			$.each(data, function(i, item) {
				var option = $("<option></option>").val(item[1]).text(item[0]);
				option.appendTo($("#customer_cate_id"));
			});
			if('${af.map.customer_cate_id }' != null || '${af.map.customer_cate_id}' != '' ){
				$("#customer_cate_id").val('${af.map.customer_cate_id }');
			}
		}
	});
}

function loading(){
	jLoading("&nbsp;&nbsp;正在加载数据...", {autoHide:false, HorizontalPosition:"center", VerticalPosition:"center", MinWidth:150});
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
