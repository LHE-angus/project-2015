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
    <html-el:form action="/admin/JcfxKczzKh">
      <html-el:hidden property="method" value="add" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td align="right"><strong class="fb">R3 编 码：</strong></td>
          <td>
            <html-el:text property="code_like" size="10" maxlength="10" styleId="code_like" value="${af.map.code_like}"/>
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
          	<html-el:select property="v_customer_type1" styleId="v_customer_type1" style="width:80px;">
            	<html-el:option value="">-请选择-</html-el:option>
            </html-el:select>
            <html-el:select property="v_customer_type2" styleId="v_customer_type2" style="width:130px;">
                <html-el:option value="">-请选择-</html-el:option>
            </html-el:select>
          	<!--<html-el:select property="customer_type" styleId="customer_type" style="width:175px">
              <html-el:option value="">请选择...</html-el:option>
              <c:forEach var="cur" items="${konkaCategoryList}">
                <html-el:option value="${cur.c_index}">${cur.c_name}</html-el:option>
              </c:forEach>
            </html-el:select>-->
          </td>
          <td align="right"><strong class="fb">业务员：</strong></td>
          <td><html-el:text property="user_name_like" size="10" maxlength="10" styleId="user_name_like"  /></td>
        </tr>
        <tr>
          <td align="right"><strong class="fb">客户状态：</strong></td>
          <td>
          	  <html-el:select property="cust_stat" styleId="cust_stat" style="width:100px" >
          	  	<html-el:option value=''>请选择...</html-el:option>
          	  	<html-el:option value='1'>新客户</html-el:option>
          	  	<html-el:option value='2'>正式客户</html-el:option>
          	  	<html-el:option value='3'>静止客户</html-el:option>
          	  	<html-el:option value='4'>无效客户</html-el:option>
          	  	<html-el:option value='5'>门店网点</html-el:option>
          	  </html-el:select>
          </td>
          <td align="right"><strong class="fb">合并编码：</strong></td>
          <td>
          		<html-el:text property="merge_code" size="23" maxlength="10" styleId="merge_code" />
          </td>
          <td align="right"><strong class="fb">事业部：</strong></td>
          <td>
          	 <html-el:select property="branch_name" styleId="branch_name" style="width:100px" disabled="true">
	         	<html-el:option value="">请选择...</html-el:option>
	         	<html-el:option value="1">白电</html-el:option>
	         	<html-el:option value="2">多媒体</html-el:option>
	         </html-el:select>
          </td>
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
          </td>
          <td valign="middle" nowrap="nowrap" class="title_item" align="right">
          <html-el:submit styleClass="but1" value="搜索" />
          <html-el:button property="" value="返 回" styleClass="but5" styleId="btn_back" onclick="history.back();return false;" />
          </td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <%@ include file="/commons/pages/messages.jsp" %>
    <div class="rtabcont1" style="overflow-x:auto;">
      <form id="listForm" name="listForm" method="post" action="KonkaR3MmtMatch.do">
        <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
          <tr class="tabtt1">
            <td nowrap="nowrap" width="5%">分公司</td>
            <td nowrap="nowrap" width="5%">R3编码</td>
            <td nowrap="nowrap">客户名称</td>
            <td nowrap="nowrap">客户类型</td>
            <td nowrap="nowrap">客户状态</td>
			<td nowrap="nowrap" width="5%">区域信息</td>
            <td nowrap="nowrap" width="5%">合并编码</td>
            <td nowrap="nowrap" width="5%">业务员</td>
            <td nowrap="nowrap" width="5%">操作</td>
          </tr>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr>
              <td align="center" nowrap="nowrap">
              	${cur.branch_area_name}
              </td>
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
              <td align="left" nowrap="nowrap"><a href="javascript:void();" onclick="selectCustomer(${cur.id},'${cur.r3_code}','${cur.customer_name}')">选择</a></td>
            </tr>
          </c:forEach>
        </table>
      </form>
      <form id="bottomPageForm" name="bottomPageForm" method="post" action="JcfxKczzKh.do">
        <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td height="10" align="center"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script> 
              <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "add");
            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
			pager.addHiddenInputs("keyword", "${fn:escapeXml(af.map.keyword)}");	
			pager.addHiddenInputs("code_like", "${fn:escapeXml(af.map.code_like)}");	
			pager.addHiddenInputs("is_match", "${fn:escapeXml(af.map.is_match)}");
			pager.addHiddenInputs("is_sdf", "${fn:escapeXml(af.map.is_sdf)}");
			pager.addHiddenInputs("dept_id", "${fn:escapeXml(af.map.dept_id)}");
			pager.addHiddenInputs("customer_type", "${fn:escapeXml(af.map.customer_type)}");
			pager.addHiddenInputs("user_name_like", "${fn:escapeXml(af.map.user_name_like)}");	
			pager.addHiddenInputs("is_del", "${fn:escapeXml(af.map.is_del)}");
			pager.addHiddenInputs("add_date_start", "${fn:escapeXml(af.map.add_date_start)}");
			pager.addHiddenInputs("add_date_end", "${fn:escapeXml(af.map.add_date_end)}");
			pager.addHiddenInputs("cust_stat", "${fn:escapeXml(af.map.cust_stat)}");
			pager.addHiddenInputs("merge_code", "${fn:escapeXml(af.map.merge_code)}");
			pager.addHiddenInputs("branch_name", "${fn:escapeXml(af.map.branch_name)}");
			pager.addHiddenInputs("v_customer_type1", "${af.map.v_customer_type1}");
            pager.addHiddenInputs("v_customer_type2", "${af.map.v_customer_type2}");
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
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
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
	});
	
	var __export = "${af.map.export}";
	if (__export.length > 0) {
		toExcel('divExcel', '?method=toExcel');
	}
	
	//客户类型初始化
	$("#v_customer_type1").attr({"subElement": "v_customer_type2", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.v_customer_type1}"});
	$("#v_customer_type2").attr({"defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.v_customer_type2}"});

	$("#v_customer_type1").cs("${ctx}/manager/admin/CsAjax.do?method=getKonkaShopType", "par_id", "0", false);
	$("#v_customer_type1").change();
});

//选择客户
function selectCustomer(cust_id,r3_code,coustmer_name){
	var paras="method=save";
	if(cust_id){paras+="&cust_id="+cust_id;}
	if(r3_code){paras+="&r3_code="+r3_code;}
	if(coustmer_name){paras+="&coustmer_name="+coustmer_name;}
	$.ajax({
		   type: "POST",
		   url: "${ctx}/manager/admin/JcfxKczzKh.do",
		   data: paras,
		   success: function(result){
				    if(result.id){
		                 alert(result.res);
					 }
				     else{
				    	alert(result.res);
					}
		   }
	});
}
//]]>
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>