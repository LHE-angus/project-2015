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
   <html-el:form  action="/demo/RetailSampleQuery">
     <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" styleId="mod_id"/>
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="15"></td>
		  <td><strong class="fb">分公司/办事处：</strong></td>
          <td colspan="2">
       			<html-el:select property="subcomp_id" styleId="subcomp_id" onchange="subcomp_id_chg();">
       				<html-el:option value="">-所属分公司-</html-el:option>
       				<html-el:optionsCollection label="dept_name" name="baseDeptList" value="dept_id"/>
       			</html-el:select>
		         &nbsp;
              <select name="office_id" id="office_id">
                <option value="">-所属办事处-</option>
              </select>
      	  </td>
      	  <td><strong class="fb">上报人：</strong></td>
  		<td> <html-el:text property="report_name_like" size="30" style="width:90px;" maxlength="40" styleId="report_name_like" styleClass="webinput" /></td>
  		<td><strong class="fb">样机名称：</strong>  
			<html-el:select property="fk_index" styleId="fk_index" style="width:130px;">
			<html-el:option value="">请选择...</html-el:option>
              <c:forEach var="cur" items="${categoryList}">
             <html-el:option value="${cur.c_index}">${cur.c_name}</html-el:option>
           </c:forEach>
          </html-el:select></td>
        <td><html-el:submit styleId="btn_submit" styleClass="but1">搜索</html-el:submit></td>
      </tr>
  </table>
      </html-el:form>
  </div>
  <div class="rtabcont1">
      <%@ include file="/commons/pages/messages.jsp" %>
  </div>
 <div class="rtabcont1">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tr class="tabtt1">
	          <td width="30" align="center">序号</td>
	          <td nowrap="nowrap" align="center" >样机名称</td>
	          <td width="70"  nowrap="nowrap" align="center" >数量</td>
	          <td nowrap="nowrap" align="center">分公司</td>
	          <td nowrap="nowrap" align="center">办事处</td>
	          <td nowrap="nowrap" align="center" >上报人</td>
	          <td width="130"  nowrap="nowrap" align="center" >上报时间</td> 
        </tr>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
	            <td align="center" nowrap="nowrap">${vs.count} </td>
	            <td align="left" nowrap="nowrap">${cur.map.c_name} </td>
	            <td align="right" nowrap="nowrap">${cur.thing_num }</td>
	            <td align="left" nowrap="nowrap">${cur.map.subcomp_name} </td>
	            <td align="left" nowrap="nowrap">${cur.map.office_name } </td>
	            <td align="center" nowrap="nowrap">${cur.map.user_name } </td>
	            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.reprot_time}" pattern="yyyy-MM-dd" /></td>
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
          </tr>
        </c:forEach>
      </table>
    <br />
      <form id="bottomPageForm" name="bottomPageForm" method="post" action="RetailSampleQuery.do">
        <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
              <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
            pager.addHiddenInputs("fk_index", "${af.map.fk_index}");
            pager.addHiddenInputs("subcomp_id", "${af.map.subcomp_id}");
            pager.addHiddenInputs("office_id", "${af.map.office_id}");
            pager.addHiddenInputs("report_name_like", "${af.map.report_name_like}");
            document.write(pager.toString());
            </script></td>
          </tr>
        </table>
      </form>
    </div>
  </div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script> 
<script type="text/javascript"  src="${ctx}/commons/scripts/imgpreview.0.22.js"></script> 
<script type="text/javascript" src="${ctx}/javascripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script> 
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
});

//分公司- 连动-办事处
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

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>