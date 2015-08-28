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
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td nowrap="nowrap">当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/zmd/KonkaXxPdStatistics" styleClass="form_search">
      <html-el:hidden property="method" value="view" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="15">&nbsp;</td>
          <c:if test="${role_id_btw_200_300}">
            <td><strong class="fb">分公司：</strong>
              <html-el:select property="dept_id" style="width:154px;" styleId="dept_id" onchange="$('#message_tip').show();deptChange(0);">
                <html-el:option value="">==请选择==</html-el:option>
                <c:forEach var="cur" items="${konkaDeptList}">
                  <html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
                </c:forEach>
              </html-el:select></td>
            <td><strong class="fb">专卖店编号：</strong>
              <html-el:select property="zmd_id" style="width:154px;" styleId="zmd_id">
                <html-el:option value="">==请选择==</html-el:option>
              </html-el:select></td>
          </c:if>
          <c:if test="${role_id_btw_300_400}">
            <input type="hidden" name="dept_id" value="${userInfo.dept_id}" />
            <td><strong class="fb">专卖店编号：</strong>
              <html-el:select property="zmd_id" style="width:154px;" styleId="zmd_id">
                <html-el:option value="">==请选择==</html-el:option>
                <c:forEach var="cur" items="${zmdList}">
                  <html-el:option value="${cur.zmd_id}">${cur.zmd_sn}</html-el:option>
                </c:forEach>
              </html-el:select></td>
          </c:if>
          <td><strong class="fb">日期：</strong>
            <html-el:text property="date_start" styleId="date_start" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" />
            &nbsp;至&nbsp;
            <html-el:text property="date_end" styleId="date_end" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" /></td>
          <td><input class="but1" type="button" value="查看" id="btn_search" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
  </div>
  <div class="rtabcont1">
    <c:if test="${empty entityListForMoney or empty entityListForNum}">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tr>
          <td align="left" height="28"><strong class="fb" style="color:green;">请选择查询条件点击查看！</strong></td>
        </tr>
      </table>
    </c:if>
    <c:if test="${not empty entityListForMoney and not empty entityListForNum}">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2" style="margin-bottom:5px;">
      <tr>
        <td align="left" height="28"><strong class="fb" style="color:green;">友情提示：点击可以查看产品占比图！</strong></td>
      </tr>
    </table>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2" style="margin-bottom:40px;">
      <tr class="tabtt1">
        <td width="5%" nowrap="nowrap" align="center"><font class="blue">序号</font></td>
        <td width="20%" nowrap="nowrap" align="center"><font class="blue">产品型号</font></td>
        <td width="25%" nowrap="nowrap" align="center"><font class="blue">销售数（台）</font></td>
        <td width="40%" nowrap="nowrap" align="center"><font class="blue">销售额</font></td>
        <td width="10%" nowrap="nowrap" align="center"><font class="blue">点击查看</font></td>
      </tr>
      <c:forEach items="${entityListForNum}" var="cur1" varStatus="vs">
        <c:forEach items="${entityListForMoney}" var="cur2">
          <c:if test="${cur1.map.md_name eq cur2.map.md_name}">
            <tr>
              <td height="28" nowrap="nowrap"align="center">${vs.count}</td>
              <td height="28" nowrap="nowrap" class="title_item" align="right">${cur1.map.md_name}</td>
              <td style="padding-right:5px;" align="right"><c:if test="${cur1.map.sell_num_sum eq '0'}"><span style="color:#ccc;">0</span></c:if>
                <c:if test="${cur1.map.sell_num_sum ne '0'}">
                  <fmt:formatNumber value="${cur1.map.sell_num_sum}" pattern="#,##0.#" />
                </c:if></td>
              <td style="padding-right:5px;" align="right"><c:if test="${cur2.map.sell_money_sum eq '0'}"><span style="color:#ccc;">0</span></c:if>
                <c:if test="${cur2.map.sell_money_sum ne '0'}"><span class="kz-price-12">
                  <fmt:formatNumber value="${cur2.map.sell_money_sum}" type="currency" />
                  </span></c:if></td>
              <td onclick="pdJump('${cur1.map.md_name}');" align="center" style="cursor:pointer;"><form action="${ctx}/manager/zmd/KonkaXxPdStatistics.do" style="margin:0px;padding:0px;" id="form_${cur1.map.md_name}">
                  <input type="hidden" name="method" value="list" />
                  <input type="hidden" name="mod_id" value="${af.map.mod_id}" />
                  <input type="hidden" name="dept_id" value="${dept_id}" />
                  <input type="hidden" name="zmd_id" value="${zmd_id}" />
                  <input type="hidden" name="date_start" value="${af.map.date_start}" />
                  <input type="hidden" name="date_end" value="${af.map.date_end}" />
                  <input type="hidden" name="md_name" value="${cur1.map.md_name}" />
                  <input type="hidden" name="search_flag" value="1" />
                  <span style="color:green;">点击查看</span>
                </form></td>
            </tr>
          </c:if>
        </c:forEach>
      </c:forEach>
    </table>
  </div>
  </c:if>
  <div class="clear"></div>
</div>
<!-- Ajax 提交 覆盖层  -->
<div id="message_tip" style="display:none;z-index:888888888;">
  <div class="ui-overlay">
    <div class="ui-widget-overlay"></div>
    <div class="ui-widget-shadow ui-corner-all" style="width:302px;height:152px;position:absolute;left:35%;top:25%"></div>
  </div>
  <div style="position:absolute;width:280px;height:130px;left:35%;top:25%;padding:10px;" class="ui-widget ui-widget-content ui-corner-all"> <span><img id="loading" src="${ctx}/images/loading.gif" />正在查询，请稍等...</span> </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	// 日期控件
	$("#date_start").datepicker();
	$("#date_end").datepicker();
	
	<c:if test="${role_id_btw_200_300}">
	$("#dept_id").attr("dataType" , "Require").attr("msg" , "选择分公司！");
	</c:if>
	$("#date_start").attr("dataType" , "Require").attr("msg" , "请选择开始日期！");
	$("#date_end"  ).attr("dataType" , "Require").attr("msg" , "请选择结束日期！");
	
	$("#btn_search").click(function (){
		if(Validator.Validate(this.form, 1)){
			$(".form_search").submit();
		}
	});
	
	// 总部管理员回显使用
	<c:if test="${role_id_btw_200_300}">
		var dept_id = "${af.map.dept_id}";
		if("" != dept_id ){
			deptChange("${af.map.zmd_id}");
		}
	</c:if>
});

function deptChange(zmd_id){
    $("#zmd_id" ).empty();
    $("#zmd_id" ).append("<option value=''>==请选择==</option>");  
	$.ajax({
    	type: "POST" , 
        url: "KonkaXxPdStatistics.do" , 
        data: "method=ajaxGetZmdList&dept_id=" + $("#dept_id").val() + "&t=" + (new Date()).getTime(), 
        dataType: "json" , 
        async: true, // false 为同步，true为异步
        error: function (request, settings) {alert(" 数据加载请求失败！ "); $('#message_tip').hide();}, 
        success: function (data) {
        	$('#message_tip').hide();
        	
            if (data.result == "false") {
				alert("请求失败，请重新访问！");
				return ;
			} 
            if(data.zmd_list.length == 0){
				return ;
            }
            // 专卖店
			var zmd_list = data.zmd_list.split("#");
			for(var i = 0; i < zmd_list.length; i++){
				var option = zmd_list[i];
				 $("#zmd_id").append("<option value=\"" + option.split(",")[0] + "\">" + option.split(",")[1] + "</option>");  
			}
			
			// 回显使用
			if(0 != zmd_id){
				$("#zmd_id").val(zmd_id);
			}
        }
	}); 
}

function pdJump(md_name){
	$("#form_" + md_name).submit();
}
//]]></script>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>