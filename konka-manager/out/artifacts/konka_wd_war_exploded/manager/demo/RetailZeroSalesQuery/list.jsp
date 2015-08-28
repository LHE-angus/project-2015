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
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align: middle;" /></td>
        <td>当前位置：${naviString}</td>
        <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align:middle;" /> <span id="span_help" style="cursor:pointer;">帮助</span>
   	   </td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/demo/RetailZeroSalesQuery">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="tree_param" value="${tree_param}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="15"></td>
          <td><strong class="fb">销售时间：</strong>
          <html-el:text property="report_date_start"  size="10" maxlength="10" readonly="true" onclick="new Calendar(2000, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
          	 至
      	<html-el:text property="report_date_end"  size="10" maxlength="10" readonly="true" onclick="new Calendar(2000, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" /></td>
           <td><strong class="fb">所属部门：</strong>
       		<html-el:select property="subcomp_id" styleId="subcomp_id" onchange="subcomp_id_chg();">
    			<html-el:option value="">-所属分公司-</html-el:option>
    			<html-el:optionsCollection label="dept_name" name="DeptList" value="dept_id"/>
    		</html-el:select>
           &nbsp;
           <select name="office_id" id="office_id" >
            	<option value="">-所属办事处-</option>
           </select></td>
             <td><input class="but1" type="submit" name="Submit" value="搜索" />
        </td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <form id="listForm" name="listForm" method="post" action="RetailZeroSalesQuery.do?method=delete">
      <input type="hidden" name="method" id="method" value="delete" />
      <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tr class="tabtt1">
          <td nowrap="nowrap" align="center">序号</td> 
          <td nowrap="nowrap" align="center">产品编号</td>
          <td nowrap="nowrap" align="center">产品型号</td>
          <td nowrap="nowrap" align="center">产品类别</td>
          <!-- <td nowrap="nowrap" align="center">销售状态</td> -->
          <!--  <td nowrap="nowrap" align="center">参考价格</td>-->
          <!--<td nowrap="nowrap" align="center">排序值</td>-->
          <!-- <td nowrap="nowrap" align="center">审核状态</td> -->
          <!--  <td nowrap="nowrap" align="center">操作</td>-->
        </tr>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr>
              <td align="center" nowrap="nowrap">${vs.count }</td>
              <td height="28" align="center">${cur.pd_id}</td>
              <td align="left" valign="middle"> ${fn:escapeXml(cur.md_name)}</td>
              <td align="left">${fn:escapeXml(cur.map.full_name)}</td>
             <!--  <td align="right"><fmt:formatNumber value="${cur.price_ref}" pattern="￥0" /></td>-->
              <!-- <td align="right">${cur.order_value}</td>-->
             <!-- <td align="center" nowrap="nowrap"> </td>-->
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
          </tr>
        </c:forEach>
      </table>
    </form>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="RetailZeroSalesQuery.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40"><div style="text-align: right; padding-right: 5px;"> 
              <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
              <script type="text/javascript">
				var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
				pager.addHiddenInputs("method", "list");
				pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
				pager.addHiddenInputs("report_date_start", "${af.map.report_date_start}");
				pager.addHiddenInputs("report_date_end", "${af.map.report_date_end}");
				pager.addHiddenInputs("subcomp_id", "${af.map.subcomp_id}");							
				pager.addHiddenInputs("office_id", "${af.map.office_id}");	
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
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>  
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/imgpreview.0.22.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	subcomp_id_chg();
	
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

	//I8右宽度不能自动适应加载，IE7,FireFox都可以的
	if(document.body.scrollLeft > 0 || document.body.scrollWidth > document.body.offsetWidth){
		$(".frame_right").width($(window).width() - 163);
	}else{
		$(".frame_right").width($(window).width() - 150);
	}
	
	//$("#price_ref_ge" ).attr("focus",setOnlyNum);
	//$("#price_ref_le" ).attr("focus",setOnlyNum);
	
	//$('.preview').imgPreview({
	//	containerID: 'imgPreviewWithStyles',
	//	srcAttr: 'id',
	//	imgCSS: {
			// Limit preview size:
			//height: 240,
			//width: 240
	//	},
		// When container is shown:
	//	onShow: function(link){
	//		$('<span>' + link.title.replace("<", "&lt;").replace(">", "&gt;") + '</span>').appendTo(this);
	//	},
		// When container hides: 
	//	onHide: function(link){
	//		$('span', this).remove();
	//	}
	//});
	
	$('.cls_name').click(function(){
		$("#cls_id option[value='"+ this.id + "']").attr("selected", true);
		document.forms[1].submit();
	});
	
	$('.jdxx_pd').click(function(){
		$("#is_jdxx_pd").val("1");
		document.forms[1].submit();
	});
});
function subcomp_id_chg(){
	$("#office_id").empty();
	$("<option value=''>-所属办事处-</option>").appendTo($("#office_id"));
	var url = "${ctx}/manager/admin/KonkaMobileSailData.do?method=getDept&subcomp_id="+$('#subcomp_id').val();
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


	//$(this).keypress(function (){
		//if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value
	//}).keyup(function (){
		//if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value
	//}).blur(function (){
		//if(!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}
		//if(this.value.length == 0) this.value = "0";
	//});
	//this.text.selected;
	//}
//]]>
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
