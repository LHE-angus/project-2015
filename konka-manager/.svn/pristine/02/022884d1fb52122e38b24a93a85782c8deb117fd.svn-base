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
    <html-el:form action="/admin/KonkaOrderMeetingManager">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="queryString" styleId="queryString"/>
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td nowrap="nowrap">&nbsp;
          <strong class="fb">分 公 司：</strong>
            <html-el:select property="dept_id" styleId="dept_id">
              <html-el:option value="">请选择...</html-el:option>
              <c:forEach var="cur" items="${sybDeptInfoList}">
                <html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
              </c:forEach>
            </html-el:select>            
          &nbsp;&nbsp;
          <strong class="fb">客户名称：</strong>
            <html-el:text property="customer_like" size="20" maxlength="20" styleId="customer_like"/>
          &nbsp;&nbsp;<strong class="fb">R3编码：</strong>
            <html-el:text property="r3_code_like" size="20" maxlength="20" styleId="r3_code_like"/>
            &nbsp;&nbsp;<strong class="fb">单据状态：</strong>
            <html-el:select property="file_status" styleId="file_status" >
              <html-el:option value="3">有效</html-el:option>
              <html-el:option value="0">无效</html-el:option>
            </html-el:select>
            </td></tr>
           <tr>
          <td nowrap="nowrap">
            &nbsp;&nbsp;<strong class="fb">主&nbsp;&nbsp;&nbsp; 题：</strong>
            <html-el:text property="meeting_name_like" size="20" maxlength="80" styleId="meeting_name_like" />
          &nbsp;&nbsp;<strong class="fb">会议类型：</strong>
            <html-el:select property="meeting_id" styleId="meeting_id">
              <html-el:option value="">--请选择--</html-el:option>
              <c:forEach var="cur" items="${spActivityList}">
                <html-el:option value="${cur.id}">${cur.hd_type}</html-el:option>
              </c:forEach>
            </html-el:select>
          &nbsp;&nbsp;<strong class="fb">订货金额（万元）：</strong>
            <html-el:text property="order_money_min" maxlength="10" styleId="order_money_min" style="width:80px;" />
            至
            <html-el:text property="order_money_max" maxlength="10" styleId="order_money_max" style="width:80px;" />
             &nbsp;&nbsp;<strong class="fb">召开时间：</strong>
            <html-el:text property="open_date_start" styleId="open_date_start" size="10" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;width:80px;" onclick="new Calendar(2005, 2030, 0).show(this);" />
            至
            <html-el:text property="open_date_end" styleId="open_date_end" size="10" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;width:80px;" onclick="new Calendar(2005, 2030, 0).show(this);" />
         </td>
        </tr>
        <tr>
          <td nowrap="nowrap">
          &nbsp;&nbsp;<strong class="fb">创建日期：</strong>
            <html-el:text property="add_meeting_date_start" styleId="add_meeting_date_start" size="10" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;width:80px;" onclick="new Calendar(2005, 2030, 0).show(this);" />
            至
            <html-el:text property="add_meeting_date_end" styleId="add_meeting_date_end" size="10" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;width:80px;" onclick="new Calendar(2005, 2030, 0).show(this);" />
             &nbsp;&nbsp;<strong class="fb">上报人：</strong>
            <html-el:text property="report_user_name_like" size="20" maxlength="20" styleId="report_user_name_like" />
             &nbsp;&nbsp;<strong class="fb">负责人：</strong>
            <html-el:text property="charge_person_like" size="20" maxlength="20" styleId="charge_person_like" />
            &nbsp;&nbsp;
            <html-el:submit styleClass="but1" value="搜索" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <%@ include file="/commons/pages/messages.jsp" %>

    <div class="rtabcont1" style="overflow-x:auto;">
      <form id="listForm" name="listForm" method="post" action="KonkaOrderMeetingManager.do">
        <input type="hidden" name="method" id="method" value="delete" />
        <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
        <html-el:hidden property="dept_sn" value="${af.map.dept_sn}" />
        <logic-el:match name="popedom" value="+2+">
          <input class="but2" type="button" name="Submit2" value="新增" onclick="location.href='KonkaOrderMeetingManager.do?method=add&mod_id=${af.map.mod_id}';" />
        </logic-el:match>
        <logic-el:match name="popedom" value="+2+">
          <input class="but3" type="button" name=button id="button" style="width:67px;" value="删除" onClick="confirmDeleteAll(this.form);" />
        </logic-el:match>  
        <logic-el:match name="popedom" value="+1+">
          <input type="button" class="but_excel" onClick="doNeedMethod(null, 'KonkaOrderMeetingManager.do', 'list', 'export=true&' + $('#bottomPageForm').serialize())" value="导出" />
          <span style="color:#F00;">注：请先搜索后再作导出动作，考虑到导出结算、回款、帐期统计数据会导致系统反应缓慢或宕机，这些数据将不作导出以提高导出速度和最大可导出数据条数。</span> </logic-el:match>
        <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
          <tr class="tabtt1">
            <td width="3%" align="center" nowrap="nowrap">
            <input name="chkAll" type="checkbox" id="chkAll" value="-1" onClick="checkAll(this);" />
            </td>
            <td align="center" nowrap="nowrap" width="3%">序号</td>
            <td align="center" nowrap="nowrap" width="5%">分公司</td>
            <td align="center" nowrap="nowrap">主题</td>
            <td align="center" nowrap="nowrap" width="5%">单据编号</td>
            <td align="center" nowrap="nowrap" width="5%">会议类型</td>
            <!--  <td nowrap="nowrap" width="10%">经办名称</td> -->
            <td align="center" nowrap="nowrap" width="5%">召开时间</td>
            <td align="center" nowrap="nowrap" width="5%">会议状态</td>
            <td align="center" nowrap="nowrap" width="5%">订货金额</td>
            <td align="center" nowrap="nowrap" width="5%">订货数量</td>
            <td align="center" nowrap="nowrap" width="5%">负责人</td>
            <td align="center" nowrap="nowrap" width="5%">上报人</td>
            <td align="center" nowrap="nowrap" width="5%">单据状态</td>
            <td align="center" nowrap="nowrap" align="center" width="8%">创建时间</td>
            <td align="center" nowrap="nowrap" align="center" width="8%">附件</td>
            <td align="center" nowrap="nowrap" align="center" width="13%">操作</td>
          </tr>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr>
              <td align="center" nowrap="nowrap">
              <c:if test="${fn:escapeXml(cur.file_status) eq 1 and fn:escapeXml(cur.meeting_status) eq 0 and cur.map.allowUpdate}">
              <input name="pks" type="checkbox" id="pks" value="${cur.id}" />
              </c:if>
              </td>
              <td align="center" nowrap="nowrap">${vs.count}</td>
              <td align="center" nowrap="nowrap">${cur.dept_name}</td>
              <td align="left" nowrap="nowrap"><a style="cursor:pointer;color:blue;" href="KonkaOrderMeetingManager.do?method=view&id=${cur.id}&mod_id=${af.map.mod_id}">${cur.meeting_name}</a></td>
              <td align="center" nowrap="nowrap">${fn:escapeXml(cur.meeting_sn)}</td>
              <td align="left" nowrap="nowrap">${fn:escapeXml(cur.map.meeting_name)}</td>
              <td align="left" nowrap="nowrap"><fmt:formatDate value="${cur.open_date}" pattern="yyyy-MM-dd" /></td>
              <td align="right" nowrap="nowrap">
              	<c:choose>
              		<c:when test="${fn:escapeXml(cur.meeting_status) eq 0}">未上报</c:when>
              		<c:when test="${fn:escapeXml(cur.meeting_status) eq 30}">进行中</c:when>
              		<c:when test="${fn:escapeXml(cur.meeting_status) eq 50}">已结束</c:when>
              	</c:choose>
              </td>
              <td align="right" nowrap="nowrap">${fn:escapeXml(cur.order_money)}</td>
              <td align="right" nowrap="nowrap">${fn:escapeXml(cur.order_num)}</td>
              <td align="right" nowrap="nowrap">${fn:escapeXml(cur.charge_person)}</td>
              <td align="right" nowrap="nowrap">${fn:escapeXml(cur.report_user_name)}</td>
              <td align="center" nowrap="nowrap">
              		<c:if test="${fn:escapeXml(cur.file_status) eq 0}">无效</c:if>
              		<c:if test="${fn:escapeXml(cur.file_status) ne 0}">有效</c:if>
              </td>
              <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.add_meeting_date}" pattern="yyyy-MM-dd" /></td>
              <td align="left" nowrap="nowrap">
              	<c:if test="${not empty cur.map.files}">
		           <c:set var="file" value="${fn:split(cur.map.files,',')}" />
		          <c:forEach items="${file}" var="tt" varStatus="vs1">
		          <c:set var="num" value="${fn:length(tt)}" />
		          	<a href="${ctx}/manager/admin/Download.do?method=downloadFile&save_name=${fn:substring(tt, 24, num)}" target="_blank">&nbsp;附件${vs1.count}&nbsp;</a>
		          </c:forEach>
		          </c:if>
              </td>
              <td align="left" style="padding:0px 5px;" nowrap="nowrap">
              <c:choose>
              <c:when test="${fn:escapeXml(cur.file_status) eq 1 and fn:escapeXml(cur.meeting_status) eq 0 and  cur.map.allowUpdate}">              
              	<span style="cursor:pointer;" class="fblue" onClick="doNeedMethod(null, '${ctx}/manager/admin/KonkaOrderMeetingManager.do','edit','&id=${cur.id }&' + $('#bottomPageForm').serialize())">修改</span>
              </c:when>
              <c:otherwise>
              <span style="color:#CCC;">修改</span>
              </c:otherwise>
              </c:choose>
              <c:choose>
              <c:when test="${fn:escapeXml(cur.file_status) eq 1 and fn:escapeXml(cur.meeting_status) eq 0 and  cur.map.allowUpdate}"> 
                  <span style="cursor:pointer;" class="fblue" onClick="confirmDelete('确定删除吗?', '${ctx}/manager/admin/KonkaOrderMeetingManager.do', 'id=${cur.id}&' + $('#bottomPageForm').serialize())">删除</span>
                 </c:when>
              <c:otherwise>
              <span style="color:#CCC;">删除</span>
              </c:otherwise>
              </c:choose>
            <c:choose>
              <c:when test="${fn:escapeXml(cur.file_status) eq 1 and fn:escapeXml(cur.meeting_status) ne 0 and cur.map.allowUpdate}">   
                 <span style="cursor:pointer;" class="fblue" onClick="doNeedMethod(null, '${ctx}/manager/admin/KonkaOrderMeetingManager.do','edit' ,'id=${cur.id}&mt=process&' + $('#bottomPageForm').serialize())">过程</span>
            </c:when>
              <c:otherwise>
              <span style="color:#CCC;">过程</span>
              </c:otherwise>
              </c:choose>
             <c:choose>
             <c:when test="${fn:escapeXml(cur.file_status) eq 1 and fn:escapeXml(cur.meeting_status) ne 0 and cur.map.allowUpdate}"> 
                  <span style="cursor:pointer;" class="fblue" onClick="doNeedMethod(null, '${ctx}/manager/admin/KonkaOrderMeetingManager.do','edit' ,'id=${cur.id}&mt=outcome&' + $('#bottomPageForm').serialize())">结果</span>
             </c:when>
              <c:otherwise>
              <span style="color:#CCC;">结果</span>
              </c:otherwise>
              </c:choose>
             <c:choose>
              <c:when test="${fn:escapeXml(cur.file_status) ne 0 and cur.map.allowUpdate}"><!--  fn:escapeXml(cur.file_status) eq 0 and  -->
                <span style="cursor:pointer;" class="fblue" onClick="doNeedMethod('确认无效吗？', '${ctx}/manager/admin/KonkaOrderMeetingManager.do','enable' ,'id=${cur.id}&' + $('#bottomPageForm').serialize())">无效</span>
              </c:when>
              <c:otherwise>
              	<span style="color:#CCC;">无效</span>
              </c:otherwise>
              </c:choose>
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
            </tr>
          </c:forEach>
        </table>
      </form>
      <br />
      <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaOrderMeetingManager.do">
        <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
              <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
			pager.addHiddenInputs("dept_id", "${fn:escapeXml(af.map.dept_id)}");	
			pager.addHiddenInputs("file_status", "${fn:escapeXml(af.map.file_status)}");	
			pager.addHiddenInputs("meeting_id", "${fn:escapeXml(af.map.meeting_id)}");	
			pager.addHiddenInputs("customer_like", "${fn:escapeXml(af.map.customer_like)}");	
			pager.addHiddenInputs("r3_code_like", "${fn:escapeXml(af.map.r3_code_like)}");
			pager.addHiddenInputs("meeting_name_like", "${fn:escapeXml(af.map.meeting_name_like)}");
			pager.addHiddenInputs("order_money_min", "${fn:escapeXml(af.map.order_money_min)}");
			pager.addHiddenInputs("order_money_max", "${fn:escapeXml(af.map.order_money_max)}");	
			pager.addHiddenInputs("report_user_name_like", "${fn:escapeXml(af.map.report_user_name_like)}");
			pager.addHiddenInputs("charge_person_like", "${fn:escapeXml(af.map.charge_person_like)}");
			pager.addHiddenInputs("open_date_start", "${fn:escapeXml(af.map.open_date_start)}");
			pager.addHiddenInputs("open_date_end", "${fn:escapeXml(af.map.open_date_end)}");
			pager.addHiddenInputs("add_meeting_date_start", "${fn:escapeXml(af.map.add_meeting_date_start)}");
			pager.addHiddenInputs("add_meeting_date_end", "${fn:escapeXml(af.map.add_meeting_date_end)}");
            document.write(pager.toString());
            </script></td>
          </tr>
        </table>
      </form>
    </div>
</div>

<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#order_money_min").focus(setOnlyNum);
	$("#order_money_max").focus(setOnlyNum2);
	
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
	
	$("#syncBtn").click(function(){
		doNeedMethod(null, 'KonkaOrderMeetingManager.do','showR3ShopDataPage' ,$('#bottomPageForm').serialize());
		// window.showModalDialog("${ctx}/manager/admin/KonkaOrderMeetingManager.do?method=showR3ShopDataPage", window, "dialogWidth:810px;status:no;dialogHeight:415px;scroll:no");
	});
	
	var __export = "${af.map.export}";
	if (__export.length > 0) {
		toExcel('divExcel', '?method=toExcel');
	}
});

function setOnlyNum() {
	$(this).css("ime-mode", "disabled");
	$(this).attr("t_value", "");
	$(this).attr("o_value", "");
	$(this).bind("dragenter",function(){
		return false;
	});
	$(this).keypress(function (){
		if(!this.value.match(/^\d*?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).keyup(function (){
		if(!this.value.match(/^\d*?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).blur(function (){
		if(!this.value.match(/^(?:[\+]?\d+(?:\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=this.value;this.o_value=this.value};
	});
	//this.text.selected;
}
function setOnlyNum2() {
	$(this).css("ime-mode", "disabled");
	$(this).attr("t_value", "");
	$(this).attr("o_value", "");
	$(this).bind("dragenter",function(){
		return false;
	});
	$(this).keypress(function (){
		if(!this.value.match(/^\d*?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).keyup(function (){
		if(!this.value.match(/^\d*?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).blur(function (){
		if(!this.value.match(/^(?:[\+]?\d+(?:\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=this.value;this.o_value=this.value};
	});
	//this.text.selected;
}
function confirmDispatchAll(form) {
	form.action = 'R3UserAssign.do';
	form.method.value = 'edit';
	var checkedCount = 0;
	if (!form.pks) {
		return;
	}
	if(!form.pks.length) {
		if (form.pks.checked == true) {
			checkedCount = 1;
		}
	}
	for (var i = 0; i < form.pks.length; i++) {
		if (form.pks[i].checked == true) {
			checkedCount++;
		}
	}
	if (checkedCount == 0) {
		alert("请至少选择一个客户！");
	} else {
			form.submit();
	}
}

function confirmPlSdfAll(form){
	form.action = 'KonkaOrderMeetingManager.do';
	form.method.value = 'delete';
	var checkedCount = 0;
	if (!form.pks) {
		return;
	}
	if(!form.pks.length) {
		if (form.pks.checked == true) {
			checkedCount = 1;
		}
	}
	for (var i = 0; i < form.pks.length; i++) {
		if (form.pks[i].checked == true) {
			checkedCount++;
		}
	}
	if (checkedCount == 0) {
		alert("请至少选择一项！");
	} else {
			form.submit();
	}
}

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>