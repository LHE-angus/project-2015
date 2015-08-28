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
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
        <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align:middle;" /> <span id="span_help" style="cursor:pointer;">帮助</span></td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/admin/KonkaR3MmtMatch">
      <html-el:hidden property="method" value="list" />
       <html-el:hidden property="is_kf" value="1" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td align="right"><strong class="fb">R3 编 码：</strong></td>
          <td>
            <html-el:text property="code_like" size="10" maxlength="10" styleId="code_like" />
          </td>
          <td align="right"><strong class="fb">客户搜索：</strong></td>
          <td>
            <html-el:text property="keyword" size="23" maxlength="10" styleId="keyword" title="请输入客户名称或所属地区或分公司所在地"/>
          </td>
          <td align="right"><strong class="fb">R3分类：</strong></td>
          <td>
            <html-el:select property="is_sdf" styleId="is_sdf" style="width:100px" onchange="this.form.submit();">
              <html-el:option value="0">售达方</html-el:option>
              <html-el:option value="1">送达方</html-el:option>
            </html-el:select>
          </td>
        </tr>
        <tr>
          <td align="right"><strong class="fb">分 公 司：</strong></td>
          <td>
          	<html-el:select property="dept_id" styleId="dept_id" style="width:100px">
              <html-el:option value="">请选择...</html-el:option>
              <c:forEach var="cur" items="${sybDeptInfoList}">
                <html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
              </c:forEach>
            </html-el:select>
          </td>
          <td align="right"><strong class="fb">客户类型：</strong></td>
          <td>
          	<html-el:select property="c_index" styleId="c_index" style="width:175px">
              <html-el:option value="">请选择...</html-el:option>
              <c:forEach var="cur" items="${konkaCategoryList}">
                <html-el:option value="${cur.c_index}">${cur.c_name}</html-el:option>
              </c:forEach>
            </html-el:select>
          </td>
          <td align="right"><strong class="fb">业务员：</strong></td>
          <td><html-el:text property="user_name_like" size="10" maxlength="10" styleId="user_name_like"  /></td>
        </tr>
        <tr>
        
          <td align="right"><strong class="fb">是否停用：</strong></td>
          <td>
          	<html-el:select property="is_del" styleId="is_del" style="width:100px" onchange="this.form.submit();">
              <html-el:option value="0">未停用</html-el:option>
              <html-el:option value="1">已停用</html-el:option>
            </html-el:select>
          </td>
          <td align="right"><strong class="fb">加盟时间：</strong></td>
          <td>  
            <html-el:text property="add_date_start" styleId="add_date_start" size="7" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" onclick="new Calendar(2005, 2030, 0).show(this);" />
            	至
            <html-el:text property="add_date_end" styleId="add_date_end" size="7" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" onclick="new Calendar(2005, 2030, 0).show(this);" />
            &nbsp;&nbsp;&nbsp;&nbsp;<html-el:submit styleClass="but1" value="搜索" />
          </td>
          <td align="left"></td>
          <td></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <%@ include file="/commons/pages/messages.jsp" %>

    <div class="rtabcont1" style="overflow-x:auto;">
      <form id="listForm" name="listForm" method="post" action="KonkaR3MmtMatch.do">
        <input type="hidden" name="method" id="method" value="download" />
        <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
        <html-el:hidden property="dept_sn" value="${af.map.dept_sn}" />
        <logic-el:match name="popedom" value="+2+">
          <input type="button" name="button" id="plbutton" class="bgButtonAdd" value="分配" onClick="confirmDispatchAll(this.form);"/>
        </logic-el:match>
      	&nbsp;
        <logic-el:match name="popedom" value="+2+">
          <input type="button" name="button" id="plfpbutton" class="btn_link" value="分类" onClick="confirmPlSdfAll(this.form);" />
        </logic-el:match>  
        	&nbsp;
        <input type="button" class="btn_sync" id="syncBtn" value="同步" />
        	&nbsp;
        <logic-el:match name="popedom" value="+1+">
      <input type="button" class="but2" onclick="doNeedMethod(null, 'KonkaR3Shop.do','toR3Add' ,$('#bottomPageForm').serialize())" value="创建" />
          <input type="button" class="but_excel" onClick="doNeedMethod(null, 'KonkaR3MmtMatch.do', 'list', 'export=true&' + $('#bottomPageForm').serialize())" value="导出" />
          <span style="color:#F00;">注：请先搜索再导出，因结算、回款、帐期统计数据会导致系统反应缓慢或宕机，因此这些数据将不作导出。</span> 
        </logic-el:match>
        
        
        <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
          <tr class="tabtt1">
            <td width="3%" align="center" nowrap="nowrap"><input name="chkAll" type="checkbox" id="chkAll" value="-1" onClick="checkAll(this);" /></td>
            <td nowrap="nowrap" width="5%">分公司</td>
            <c:if test="${af.map.match eq 1}">
              <td width="3%" align="center" nowrap="nowrap"><input name="chkAll" type="checkbox" id="chkAll" value="-1" onClick="checkAll(this);" /></td>
            </c:if>
            <td nowrap="nowrap" width="5%">R3编码</td>
            <td nowrap="nowrap">客户名称</td>
            <td nowrap="nowrap">客户类型</td>
            <td nowrap="nowrap">客户状态</td>
			<td nowrap="nowrap" width="5%">区域信息</td>
            <td nowrap="nowrap" width="5%">合并编码</td>
            <td nowrap="nowrap" width="5%">业务员</td>
            <td nowrap="nowrap" width="5%">当月结算</td>
            <td nowrap="nowrap" width="5%">去年同期</td>
            <td nowrap="nowrap" width="5%">当月回款</td>
            <td nowrap="nowrap" width="5%">去年同期</td>
            <td nowrap="nowrap" width="5%">余额(应收)</td>
            <td nowrap="nowrap" align="center" width="8%">加盟时间</td>
            <td nowrap="nowrap" align="center" width="8%">更新时间</td>
            <td nowrap="nowrap" align="center" width="15%">操作</td>
          </tr>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr>
              <td align="center" nowrap="nowrap">
              	<input name="pks" type="checkbox" id="pks" value="${cur.id}" />
              </td>
              <td align="center" nowrap="nowrap">
              	${cur.branch_area_name}
              </td>
              <c:if test="${af.map.match eq 1}">
                <td align="center" nowrap="nowrap">
                	<input name="pks" type="checkbox" id="pks" value="${cur.id}" />
                </td>
              </c:if>
              <td align="left">${fn:escapeXml(cur.r3_code)}</td>
              <td align="left" nowrap="nowrap">
              	<a style="cursor:pointer;color:blue;" href="KonkaR3MmtMatch.do?method=detail&id=${cur.id}&mod_id=${af.map.mod_id}&key=1">${cur.customer_name}</a></td>
              <td align="center" nowrap="nowrap">${fn:escapeXml(cur.map.c_name)}
                <c:if test="${empty cur.map.c_name}"><span style="color:#ccc;">未指定</span></c:if>
              </td>
              <td align="left">
              	<c:if test="${cur.shop_status eq 1}">
              		新客户
              	</c:if>
              	<c:if test="${cur.shop_status eq 2}">
              		正式客户
              	</c:if>
              	<c:if test="${cur.shop_status eq 3}">
              		静止客户
              	</c:if>
              	<c:if test="${cur.shop_status eq 4}">
              		无效客户
              	</c:if>
              	<c:if test="${cur.shop_status eq 5}">
              		门店网点
              	</c:if>
              	<c:if test="${empty cur.shop_status}"><span style="color:#ccc;">未指定</span></c:if>
              </td>  
              <td align="left" nowrap="nowrap">
              	${fn:escapeXml(cur.area_name)}-${fn:escapeXml(cur.branch_area_name)}</td>
              <!-- 
            <td align="left" >${fn:escapeXml(cur.handle_name)}</td>
             -->
              <td align="left" nowrap="nowrap">${fn:escapeXml(cur.customer_code)}</td>
              <td align="left" nowrap="nowrap">${fn:escapeXml(cur.map.user_name)}</td>
              <td align="right" nowrap="nowrap" class="kz-price-12"><fmt:formatNumber value="${cur.map.stat.map.cur_cls_money}" pattern="#,##0.00"/></td>
              <td align="right" nowrap="nowrap" class="kz-price-12"><fmt:formatNumber value="${cur.map.stat.map.lastyear_cls_money}" pattern="#,##0.00"/></td>
              <td align="right" nowrap="nowrap" class="kz-price-12"><fmt:formatNumber value="${cur.map.stat.map.cur_back_money}" pattern="#,##0.00"/></td>
              <td align="right" nowrap="nowrap" class="kz-price-12">
              	<fmt:formatNumber value="${cur.map.stat.map.lastyear_back_money}" pattern="#,##0.00"/>
              </td>
              	<!--<fmt:formatDate value="${cur.map.stat.map.credit_time}" var="credit_time" />-->
              <td align="right" title="${credit_time}">
              	${fn:escapeXml(cur.map.stat.map.credit_balance)}
              </td>
              <td align="center" nowrap="nowrap">
              	<fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd" />
              </td>
              <td align="center" nowrap="nowrap">
              	<fmt:formatDate value="${cur.create_date}" pattern="yyyy-MM-dd" />
              </td>
              <td align="left" style="padding:0px 5px;" nowrap="nowrap"><span style="cursor:pointer;" class="fblue" onClick="doNeedMethod(null, 'R3UserAssign.do','edit','&pks=${cur.id }&' + $('#bottomPageForm').serialize())">分配</span>
                <c:if test="${cur.is_del eq 0}"> <a href="${ctx}/manager/admin/KonkaR3Store.do?mod_id=106001&r3_code=${cur.r3_code}" class="fblue">门店</a>
                  <logic-el:match name="popedom" value="+2+"> <span style="cursor:pointer;" class="fblue" onClick="doNeedMethod(null, 'KonkaR3MmtMatch.do','toModify' ,'id=${cur.id}&' + $('#bottomPageForm').serialize())">修改</span> </logic-el:match>
                  <logic-el:notMatch name="popedom" value="+2+"> <span class="fblue" style="color:#ccc">匹配</span> <span class="fblue" style="color:#ccc">解除</span> <span style="color:#CCC;">修改</span> </logic-el:notMatch>
                </c:if>
                <logic-el:match name="popedom" value="+4+">
                  <c:if test="${cur.is_del eq 0}"> <span style="cursor:pointer;" class="fblue" onClick="confirmDelete('确定停用吗?', 'KonkaR3MmtMatch.do', 'id=${cur.id}&r3_code=${cur.r3_code}&' + $('#bottomPageForm').serialize())">停用</span> </c:if>
                  <c:if test="${cur.is_del eq 1}"> <span style="color:#CCC;">门店&nbsp;</span><span style="color:#CCC;">修改</span> <span style="cursor:pointer;" class="fblue" onClick="doNeedMethod('确认启用吗？', 'KonkaR3MmtMatch.do','reStart' ,'id=${cur.id}&' + $('#bottomPageForm').serialize())">启用</span> </c:if>
                </logic-el:match>
                <logic-el:notMatch name="popedom" value="+4+"> <span style="color:#CCC;">停用</span>&nbsp;&nbsp;<span class="fblue" style="color:#CCC;">启用</span> </logic-el:notMatch>
                <logic-el:match name="popedom" value="+4+">
                  <c:if test="${cur.is_del eq 0}"> <span style="cursor:pointer;" class="fblue" onClick="location.href='${ctx}/manager/admin/CustomerUsers.do?method=list&mod_id=${af.map.mod_id}&cust_id=${cur.id}';">账户</span> </c:if>
                  <c:if test="${cur.is_del eq 1}"> <span style="color:#CCC;" title="该账户已被停用">账户</span> </c:if>
                </logic-el:match>
                <logic-el:notMatch name="popedom" value="+4+"><span style="color:#CCC;" title="该账户已被停用">账户</span></logic-el:notMatch>
                <br />
                <c:if test="${empty cur.b_lng}"><span style="color:#CCC;" title="该客户还未进行位置标注，无法获取其地理位置！">位置</span>&nbsp;</c:if>
                <c:if test="${not empty cur.b_lng}"><span style="cursor:pointer;" class="fblue" onClick="location.href='${ctx}/manager/admin/KonkaR3MmtMatch.do?method=locatInMap&mod_id=${af.map.mod_id}&id=${cur.id}';">位置</span>&nbsp;</c:if>
                <a class="fblue" href="${ctx}/manager/admin/KonkaR3Backmoney.do?mod_id=${af.map.mod_id}&r3_code=${cur.r3_code}&is_kh=1">回款</a> <a class="fblue" href="${ctx}/manager/admin/ChannelDataImport.do?mod_id=${af.map.mod_id}&keyword=${cur.r3_code}&is_kh=1">订单</a> <a class="fblue" href="${ctx}/manager/admin/QueryCustomerAccount.do?mod_id=${af.map.mod_id}&r3_code=${cur.r3_code}">帐期</a></td>
            </tr>
          </c:forEach>
        </table>
      </form>
      <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaR3MmtMatch.do">
        <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td height="10" align="center"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script> 
              <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
            pager.addHiddenInputs("is_kf", "1");
			pager.addHiddenInputs("keyword", "${fn:escapeXml(af.map.keyword)}");	
			pager.addHiddenInputs("code_like", "${fn:escapeXml(af.map.code_like)}");	
			pager.addHiddenInputs("is_match", "${fn:escapeXml(af.map.is_match)}");
			pager.addHiddenInputs("is_sdf", "${fn:escapeXml(af.map.is_sdf)}");
			pager.addHiddenInputs("dept_id", "${fn:escapeXml(af.map.dept_id)}");
			pager.addHiddenInputs("c_index", "${fn:escapeXml(af.map.c_index)}");
			pager.addHiddenInputs("user_name_like", "${fn:escapeXml(af.map.user_name_like)}");	
			pager.addHiddenInputs("is_del", "${fn:escapeXml(af.map.is_del)}");
			pager.addHiddenInputs("add_date_start", "${fn:escapeXml(af.map.add_date_start)}");
			pager.addHiddenInputs("add_date_end", "${fn:escapeXml(af.map.add_date_end)}");
            document.write(pager.toString());
            </script></td>
          </tr>
        </table>
      </form>
    </div>
</div>
  <div id="divExcel" title="客户表" class="rtabcont1" style="overflow-x: auto; display: none;">
    <table width="100%" border="1" cellpadding="5" cellspacing="0" style="border-collapse:collapse;">
      <thead>
        <tr>
          <td width="3%" align="center" nowrap="nowrap">序号</td>
          <td nowrap="nowrap" width="5%">分公司</td>
          <td nowrap="nowrap" width="5%">R3编码</td>
          <td nowrap="nowrap">客户名称</td>
          <td nowrap="nowrap" width="5%">客户类型</td>
          <td nowrap="nowrap" width="5%">区域信息</td>
          <!--  <td nowrap="nowrap" width="10%">经办名称</td> -->
          <td nowrap="nowrap" width="5%">合并编码</td>
          <td nowrap="nowrap" width="5%">业务员</td>
          <td nowrap="nowrap" width="5%">当月结算</td>
          <td nowrap="nowrap" width="5%">去年同期</td>
          <td nowrap="nowrap" width="5%">当月回款</td>
          <td nowrap="nowrap" width="5%">去年同期</td>
          <td nowrap="nowrap" width="5%">帐期</td>
          <td nowrap="nowrap" align="center" width="8%">加盟时间</td>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="cur" items="${entityList1}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">${vs.count}</td>
            <td align="center" nowrap="nowrap">${cur.branch_area_name}</td>
            <td align="left">${fn:escapeXml(cur.r3_code)}</td>
            <td align="left" nowrap="nowrap">${cur.customer_name}</td>
            <td align="center" nowrap="nowrap">${fn:escapeXml(cur.map.c_name)}
              <c:if test="${empty cur.map.c_name}"><span style="color:#ccc;">未指定</span></c:if></td>
            <td align="left" nowrap="nowrap">${fn:escapeXml(cur.area_name)}-${fn:escapeXml(cur.branch_area_name)}</td>
            <td align="left" nowrap="nowrap">${fn:escapeXml(cur.customer_code)}</td>
            <td align="left" nowrap="nowrap">${fn:escapeXml(cur.map.user_name)}</td>
            <td align="right" nowrap="nowrap" class="kz-price-12"><fmt:formatNumber value="${cur.map.stat.map.cur_cls_money}" pattern="#,##0.00"/></td>
            <td align="right" nowrap="nowrap" class="kz-price-12"><fmt:formatNumber value="${cur.map.stat.map.lastyear_cls_money}" pattern="#,##0.00"/></td>
            <td align="right" nowrap="nowrap" class="kz-price-12"><fmt:formatNumber value="${cur.map.stat.map.cur_back_money}" pattern="#,##0.00"/></td>
            <td align="right" nowrap="nowrap" class="kz-price-12"><fmt:formatNumber value="${cur.map.stat.map.lastyear_back_money}" pattern="#,##0.00"/></td>
            <fmt:formatDate value="${cur.map.stat.map.credit_time}" var="credit_time" />
            <td align="right" title="${credit_time}">${fn:escapeXml(cur.map.stat.map.credit_balance)}</td>
            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd" /></td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
  </div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
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
		doNeedMethod(null, 'KonkaR3MmtMatch.do','showR3ShopDataPage' ,$('#bottomPageForm').serialize());
		// window.showModalDialog("${ctx}/manager/admin/KonkaR3MmtMatch.do?method=showR3ShopDataPage", window, "dialogWidth:810px;status:no;dialogHeight:415px;scroll:no");
	});
	
	var __export = "${af.map.export}";
	if (__export.length > 0) {
		toExcel('divExcel', '?method=toExcel');
	}
});
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
	form.action = 'KonkaR3MmtMatch.do';
	form.method.value = 'plSdfEdit';
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
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>