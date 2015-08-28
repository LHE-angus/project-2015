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
.filed_border{
	border-left: 1px solid #ccc;;
	border-right: 1px solid #ccc;;
	border-bottom:1px solid #ccc;;
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
    <html-el:form action="/admin/KonkaMobileSailDataBill">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="is_del" styleId="is_del" />
      <table width="100%" border="0" cellspacing="1" cellpadding="5" class="rtable1">
      	<tr>
        	<td width="10%" nowrap="nowrap" title="客户业务员所在部门" align="right">
          		<strong class="fb">部门：</strong>
          	</td>
          	<td width="28%">
	          	<c:if test="${empty current_dept}">
		            <html-el:select property="l3_dept_id" styleId="l3_dept_id" disabled="${disabled}">
		          		<html-el:option value="">-分公司/经营部-</html-el:option>
		          	</html-el:select>
		          	<html-el:select property="l4_dept_id" styleId="l4_dept_id">
		          		<html-el:option value="">-请选择-</html-el:option>
		          	</html-el:select>
		          	<html-el:select property="l5_dept_id" styleId="l5_dept_id">
		          		<html-el:option value="">-请选择-</html-el:option>
		          	</html-el:select>
	          	</c:if>
	          	<c:if test="${not empty current_dept}">
	          		${fn:replace(current_dept.full_name, ',', ' &gt; ')}
	          	</c:if>
            </td>
	        <td width="7%" align="right">
	        	<strong class="fb">客户名称：</strong>
	        </td>
	        <td width="18%">
	        	<html-el:text property="customer_name_like" size="30" style="width:170px;" maxlength="40" styleId="customer_name_like" styleClass="webinput" />
	        </td>
	        <td align="right" width="10%">
          		<strong class="fb">上报门店：</strong>
          	</td>
          	<td width="20%">
          		<html-el:text property="dept_name_like" size="30" maxlength="40" styleId="dept_name_like"  />
          	</td>
        </tr>
        <tr>
          <td align="right">
          	<strong class="fb">上报人 ：</strong>
          </td>
          <td>
          	<html-el:text property="report_name_like" size="30" style="width:170px;" maxlength="40" styleId="report_name_like" styleClass="webinput" />
          </td>
          <td align="right">
          	<strong class="fb">时间范围：</strong>
          </td>
          <td>
          	<html-el:text property="date_begin" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" onclick="new Calendar(2005, 2030, 0).show(this);" />
            -
            <html-el:text property="date_end" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" onclick="new Calendar(2005, 2030, 0).show(this);" />
          </td>
          <td align="right">
          	<strong class="fb">尺寸/型号：</strong>
          </td>
          <td>
          	<html-el:select property="measure_id" styleId="measure_id" onchange="category_id_chg();">
              <html-el:option value="">-选择尺寸-</html-el:option>
              <html-el:optionsCollection label="name" name="sizeList" value="name" />
            </html-el:select>
            <select name="model_id" id="model_id">
              <option value="">-产品型号-</option>
            </select>
          </td>
        </tr>
        <tr>
          <td align="right">
          	<strong class="fb">客户分类：</strong>
          </td>
          <td>
          	<html-el:select property="c_comm" styleId="c_comm" onchange="customer_cate_id_chg();">
              <html-el:option value="">-客户类型-</html-el:option>
              <c:forEach var="cur" items="${konkaCategoryList}">
              	 <c:if test="${not empty cur.map.c_comm}"><html-el:option value="${cur.map.par_index}">${cur.map.c_comm}</html-el:option></c:if>
              </c:forEach>
            </html-el:select>
            <select name="customer_cate_id" id="customer_cate_id">
              <option value="">-细分类型-</option>
            </select>
          </td>
          <td align="right">
          	<strong class="fb">单价范围：</strong>
          </td>
          <td>
          	<html-el:text property="single_price_begin" size="9" maxlength="10" styleId="single_price_begin"/>
            -
            <html-el:text property="single_price_end" size="9" maxlength="10" styleId="single_price_end"/>
           </td>
           <td align="right">
           	  <strong class="fb">显示0销量：</strong>
           </td>
           <td>
           	 <html-el:select property="isShow" styleId="isShow" style="width:50px;" >
           	 	<c:forEach items="${showList}" var="cur">
	            	<html-el:option value="${cur.val}">${cur.name}</html-el:option>
	            </c:forEach>
             </html-el:select>
           </td>
        </tr>
        <tr>
        	<td align="right">
          		<strong class="fb">R3编码：</strong>
          	</td>
          	<td>
          		<html-el:text property="r3_code" size="30" style="width:170px;" maxlength="40" styleId="r3_code" styleClass="webinput" />
          	</td>
       		<td align="right">
       			<strong class="fb">型&nbsp;&nbsp;&nbsp;&nbsp;号：</strong>
            </td>
            <td>
            	<html-el:text property="model_like" size="30" style="width:170px;" maxlength="40" styleId="model_like" styleClass="webinput" />
            </td>
           <td  align="right" nowrap="nowrap"><strong class="fb">审&nbsp;核&nbsp;状&nbsp;态&nbsp;：</strong></td>
             <td>
            	<html-el:select property="audit_state" styleId="audit_state">
            	<html-el:option value="">请选择</html-el:option>
            	<html-el:option value="0">上传中</html-el:option>
            	<html-el:option value="2">初审合格</html-el:option>
            	<html-el:option value="4">初审不合格</html-el:option>
            	<html-el:option value="6">初审通过并转单</html-el:option>
            	<html-el:option value="8">终审通过</html-el:option>
            	<html-el:option value="10">终审不通过</html-el:option>
            	</html-el:select>
            </td>
        </tr>
        <tr>
        <td  align="right" nowrap="nowrap"><strong class="fb">是否上报发票</strong></td>
             <td>
            	<html-el:select property="is_for_audit" styleId="is_for_audit">
            	<html-el:option value="">请选择</html-el:option>
            	<html-el:option value="1">否</html-el:option>
            	<html-el:option value="0">是</html-el:option>
            	</html-el:select>
            </td>
        	<td colspan="2">&nbsp;</td>
       		<td colspan="2" align="left" nowrap="nowrap">
	        	<html-el:submit styleId="btn_submit" styleClass="but1">搜索</html-el:submit>
	        &nbsp;&nbsp;&nbsp;&nbsp;
<!--            	<input type="button" value="导出" id="export_22" class="but_excel" style="margin-left: 10px;" title="包含上传附件列表，导出速度较慢" />-->
					<input type="button" value="导出" id="export_23" class="but_excel" style="margin-left: 10px;" title="不包含上传附件列表，导出速度较快" />
<!--            	不包含附件，速度较快-->
            </td>
        </tr>
      </table>
    </html-el:form>
  </div>
    <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
<!--    <table width="100%" border="0" cellspacing="0" cellpadding="0">-->
<!--      <tr>-->
<!--        <td>-->
<!--		    <logic-el:match name="popedom" value="+1+">-->
<!--		    <input class="but_excel" type="button" name="Submit3" value="导入" onclick="location.href='${ctx}/manager/admin/KonkaMobileSailData.do?method=imp&mod_id=${af.map.mod_id}'" />  -->
<!--		    </logic-el:match>-->
<!--		</td>-->
<!--	 </tr>-->
<!--	</table>-->
  </div>
  <div class="rtabcont1" style="font-weight:700;color:#F00;">
    <fmt:formatDate var="__now" value="${now}" pattern="yyyy年M月d日" />
    <fmt:formatDate var="__two_days_ago" value="${two_days_ago}" pattern="yyyy年M月d日" />
    <fmt:formatDate var="__yesterday" value="${yesterday}" pattern="yyyy年M月d日" />
    注：今天是${__now}，截止到${__two_days_ago}（含${__two_days_ago}）的数据已归档且不可修改，${__yesterday}至今的数据可能会被修改。 </div>
  <div class="rtabcont1" style="overflow-x: auto;">
    <table width="100%" border="0" cellspacing="0" cellpadding="0"
	class="rtable2">
      <tr class="tabtt1">
        <td width="5%" align="center" nowrap="nowrap">序号</td>
        <td width="5%" nowrap="nowrap" align="center">操作</td>
        <td align="center" nowrap="nowrap">日期</td>
        <td width="4%" align="center" nowrap="nowrap">分公司</td>
        <td align="center" nowrap="nowrap">客户名称</td>
        <td align="center" nowrap="nowrap">客户R3编码</td>
        <td width="6%" align="center" nowrap="nowrap">客户类型</td>
        <td width="6%" align="center" nowrap="nowrap">细分类型</td>
        <td width="4%" nowrap="nowrap" align="center">上报人</td>
        <td align="center" nowrap="nowrap">上报门店</td>
        <td width="4%" align="center" nowrap="nowrap">门店所属经办</td>
        
        
        
        <td width="4%" align="center" nowrap="nowrap">送达方</td>
        <td width="4%" align="center" nowrap="nowrap">出货仓</td>
        <td width="3%" nowrap="nowrap" align="center">尺寸</td>
        <td width="8%" nowrap="nowrap" align="center">产品型号</td>
        <td width="3%" nowrap="nowrap" align="center">数量</td>
        <td width="6%" nowrap="nowrap" align="center">单价</td>
        <td width="6%" nowrap="nowrap" align="center">金额</td>
        <td width="6%" nowrap="nowrap" align="center">参考单价</td>
        <td width="6%" nowrap="nowrap" align="center">参考金额</td>
        
        <td width="5%" nowrap="nowrap" align="center">发票单号</td>
        <td width="5%" nowrap="nowrap" align="center">申请提成金额</td>
        <td width="5%" nowrap="nowrap" align="center">初审提成金额</td>
        <td width="5%" nowrap="nowrap" align="center">终审金额</td>
        
        <td width="6%" nowrap="nowrap" align="center">消费者姓名</td>
        <td width="6%" nowrap="nowrap" align="center">电话</td>
        <td nowrap="nowrap" align="center">身份证</td>
        <td width="12%" nowrap="nowrap" align="center">地址</td>
        <td width="12%" nowrap="nowrap" align="center">备注</td>
        <td width="5%" nowrap="nowrap" align="center">数据来源</td>
        <td width="5%" nowrap="nowrap" align="center">附件</td>
      </tr>
      <c:forEach var="cur" items="${entityList}" varStatus="vs">
      <c:if test="${empty cur.map.bill_nos}">
        <tr class="list-tr" style="color:#668800">
        </c:if>
         <c:if test="${not empty cur.map.bill_nos}">
        <tr class="list-tr" >
        </c:if>
          <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
        <td><span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null,'KonkaMobileSailDataBill.do', 'view','id=${cur.id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">查看</span></td>
          <td align="left" nowrap="nowrap"><fmt:formatDate value="${cur.report_date}" pattern="yyyy-MM-dd" /></td>
          <td align="left" nowrap="nowrap">${cur.subcomp_name}</td>
          
<!--           <td align="left" nowrap="nowrap">${cur.cust_office_name}</td>-->
          <td align="left" nowrap="nowrap">${cur.customer_name}</td>
          <td align="left" nowrap="nowrap">${cur.customer_r3_code}</td>
          <td align="left" nowrap="nowrap">${cur.map.c_comm}</td>
          <td align="left" nowrap="nowrap">${cur.map.c_name}</td>
          <td align="left" nowrap="nowrap">${cur.report_name}</td>
          <td align="left" nowrap="nowrap">${cur.dept_name}</td>
         <td align="left" nowrap="nowrap">${cur.office_name}</td>
          
          <td align="left" nowrap="nowrap">${cur.map.r3_sdf_sn}</td>
          <td align="left" nowrap="nowrap">${cur.map.ck_name}</td>
          <td align="center" nowrap="nowrap">${cur.measure_name}</td>
          <td align="left" nowrap="nowrap">${cur.model_name}</td>
          <td align="right" nowrap="nowrap">${cur.num}</td>
          <td align="right" nowrap="nowrap"><span class="kz-price-12">
            <fmt:formatNumber value="${cur.num ne 0 ? (cur.all_price / cur.num) : 0 }" type="currency" />
            </span></td>
          <td align="right" nowrap="nowrap"><span class="kz-price-12">
            <fmt:formatNumber value="${cur.all_price}" type="currency" />
            </span></td>
          <td align="right" nowrap="nowrap"><span class="kz-price-12">
            <c:if test="${empty cur.price_ref}">-</c:if>
            <c:if test="${not empty cur.price_ref}">
              <fmt:formatNumber value="${cur.price_ref}" type="currency" />
            </c:if>
            </span></td>
          <td align="right" nowrap="nowrap"><span class="kz-price-12">
            <fmt:formatNumber value="${cur.num ne 0 ? cur.price_ref * cur.num : 0}" type="currency" />
            </span></td>
            
            <td align="left" nowrap="nowrap">${cur.map.bill_nos}</td>
             <td align="right" nowrap="nowrap"><span class="kz-price-12">
            <fmt:formatNumber value="${cur.map.dec_money}" type="currency" />
            </span></td>
             <td align="right" nowrap="nowrap"><span class="kz-price-12">
            <fmt:formatNumber value="${cur.map.audit_money}" type="currency" />
            </span></td> <td align="right" nowrap="nowrap"><span class="kz-price-12">
            <fmt:formatNumber value="${cur.map.final_audit_money}" type="currency" />
            </span></td>
          <td align="left" nowrap="nowrap">${cur.realname}</td>
          <td align="left" nowrap="nowrap">${cur.phonenum}</td>
          <td align="left" nowrap="nowrap">${cur.mastercode}</td>
          <td align="left" nowrap="nowrap">${cur.addresss}</td>
          <td align="left" nowrap="nowrap">${cur.memo}</td>
          <td align="center" nowrap="nowrap">${fn:split('手机端,WEB端,IOS手机端,外部导入',',')[cur.data_source]}</td>
          <td align="left" nowrap="nowrap">
          <c:if test="${not empty cur.map.fapiaos}">
           <c:set var="fapiao" value="${fn:split(cur.map.fapiaos,',')}" />
          <c:forEach items="${fapiao}" var="tt" varStatus="vs1">
          <c:set var="num" value="${fn:length(tt)}" />
          	<a href="${ctx}/manager/admin/Download.do?method=downloadFile1&save_name=${fn:substring(tt, 24, num)}" target="_blank">&nbsp;附件${vs1.count}&nbsp;</a>
          </c:forEach>
          </c:if>
          </td>
          
        </tr>
        <c:if test="${vs.last eq true}">
          <c:set var="i" value="${vs.count}" />
        </c:if>
      </c:forEach>
    </table>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaMobileSailDataBill.do">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="60" align="center"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
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
			pager.addHiddenInputs("model_id", "${af.map.model_id}");							
			pager.addHiddenInputs("report_name_like", "${af.map.report_name_like}");
			pager.addHiddenInputs("yw_name", "${af.map.yw_name}");
			pager.addHiddenInputs("customer_name_like", "${af.map.customer_name_like}");
			pager.addHiddenInputs("is_del", "${af.map.is_del}");
			pager.addHiddenInputs("measure_id", "${af.map.measure_id}");
			pager.addHiddenInputs("dept_name_like", "${af.map.dept_name_like}");									
			pager.addHiddenInputs("c_index", "${af.map.c_index}");									
			pager.addHiddenInputs("customer_cate_id", "${af.map.customer_cate_id}");
			pager.addHiddenInputs("c_comm", "${af.map.c_comm}");	
			pager.addHiddenInputs("single_price_begin", "${af.map.single_price_begin}");	
			pager.addHiddenInputs("single_price_end", "${af.map.single_price_end}");
			pager.addHiddenInputs("isShow", "${af.map.isShow}");
			pager.addHiddenInputs("r3_code", "${af.map.r3_code}");
			pager.addHiddenInputs("model_like", "${af.map.model_like}");
			pager.addHiddenInputs("audit_state", "${af.map.audit_state}");
			pager.addHiddenInputs("ext_audit_state", "${af.map.ext_audit_state}");
			pager.addHiddenInputs("is_for_audit", "${af.map.is_for_audit}");
            document.write(pager.toString());
            </script></td>
        </tr>
      </table>
    </form>
  </div>
  <div class="rtabcont1" style="overflow-x: auto; display: none;" id="divExcel_all" title="销售明细">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
      <tr class="tabtt1">
        <td width="5%" align="center" nowrap="nowrap">序号</td>
        <td align="center" nowrap="nowrap">日期</td>
        <td width="4%" align="center" nowrap="nowrap">分公司</td>
        <td width="6%" align="center" nowrap="nowrap">客户所在经办</td>
        <td align="center" nowrap="nowrap">客户名称</td>
        <td align="center" nowrap="nowrap">客户R3编码</td>
        <td width="6%" align="center" nowrap="nowrap">客户类型</td>
        <td width="6%" align="center" nowrap="nowrap">细分类型</td>
        <td width="4%" nowrap="nowrap" align="center">上报人</td>
        <td align="center" nowrap="nowrap">上报门店</td>
        <td width="4%" align="center" nowrap="nowrap">门店ID</td>
        <td width="4%" align="center" nowrap="nowrap">门店所属经办</td>
        <td width="4%" align="center" nowrap="nowrap">送达方</td>
        <td width="3%" nowrap="nowrap" align="center">尺寸</td>
        <td width="8%" nowrap="nowrap" align="center">产品型号</td>
        <td width="3%" nowrap="nowrap" align="center">数量</td>
        <td width="6%" nowrap="nowrap" align="center">单价</td>
        <td width="6%" nowrap="nowrap" align="center">金额</td>
        <td width="6%" nowrap="nowrap" align="center">参考单价</td>
        <td width="6%" nowrap="nowrap" align="center">参考金额</td>
        <td width="6%" nowrap="nowrap" align="center">消费者姓名</td>
        <td width="6%" nowrap="nowrap" align="center">电话</td>
        <td nowrap="nowrap" align="center">身份证</td>
        <td width="12%" nowrap="nowrap" align="center">地址</td>
        <td width="12%" nowrap="nowrap" align="center">备注</td>
        <td width="5%" nowrap="nowrap" align="center">数据来源</td>
      </tr>
      <c:forEach var="cur" items="${allList}" varStatus="vs">
        <tr class="list-tr">
          <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
          <td align="left" nowrap="nowrap"><fmt:formatDate value="${cur.report_date}" pattern="yyyy/MM/dd" /></td>
         <td align="left" nowrap="nowrap">${cur.subcomp_name}</td>
          <td align="left" nowrap="nowrap">${cur.cust_office_name}</td>
<!--          <td align="left" nowrap="nowrap">${cur.office_name}</td>-->
          <td align="left" nowrap="nowrap">${cur.customer_name}</td>
          <td align="left" nowrap="nowrap">${cur.customer_r3_code}</td>
          <td align="left" nowrap="nowrap">${cur.map.c_comm}</td>
          <td align="left" nowrap="nowrap">${cur.map.c_name}</td>
          <td align="left" nowrap="nowrap">${cur.report_name}</td>
          <td align="left" nowrap="nowrap">${cur.dept_name}</td>
          <td align="left" nowrap="nowrap">${cur.dept_id}</td>
          <td align="left" nowrap="nowrap">${cur.office_name}</td>
          <td align="left" nowrap="nowrap">${cur.map.r3_sdf_sn}</td>
          <td align="center" nowrap="nowrap">${cur.measure_name}</td>
          <td align="left" nowrap="nowrap">${cur.model_name}</td>
          <td align="right" nowrap="nowrap">${cur.num }</td>
          <td align="right" nowrap="nowrap"><span class="kz-price-12">
            <fmt:formatNumber value="${cur.num ne 0 ? (cur.all_price / cur.num) : 0 }" type="currency" />
            </span></td>
          <td align="right" nowrap="nowrap"><span class="kz-price-12">
            <fmt:formatNumber value="${cur.all_price}" type="currency" />
            </span></td>
          <td align="right" nowrap="nowrap"><span class="kz-price-12">
            <fmt:formatNumber value="${cur.num ne 0 ? (cur.all_price / cur.num) : 0 }" type="currency" />
            </span></td>
          <td align="right" nowrap="nowrap"><span class="kz-price-12">
            <fmt:formatNumber value="${cur.all_price}" type="currency" />
            </span></td>
          <td align="left" nowrap="nowrap">${cur.realname}</td>
          <td align="left" nowrap="nowrap">${cur.phonenum}</td>
          <td align="left" nowrap="nowrap">${cur.mastercode}</td>
          <td align="left" nowrap="nowrap">${cur.addresss}</td>
          <td align="left" nowrap="nowrap">${cur.memo}</td>
          <td align="center" nowrap="nowrap">${fn:split('手机端,WEB端,IOS手机端', ',')[cur.data_source]}</td>
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

	var current_dept = '${empty current_dept}';
	if(current_dept != 'false'){
		$("#l3_dept_id").attr({"subElement": "l4_dept_id", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l3_dept_id}"});
		$("#l4_dept_id").attr({"subElement": "l5_dept_id", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l4_dept_id}"});
		$("#l5_dept_id").attr({"defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l5_dept_id}"});
	
		$("#l3_dept_id").cs("${ctx}/manager/admin/CsAjax.do?method=getKonkaDeptForParId", "par_id", "${empty cs_par_id ? 0 : cs_par_id}");
		$("#l3_dept_id").change();
	}
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


	//导出
	$("#export_22").click(function(){
		if(confirm("提示，您确认导出数据？")){
			$("#bottomPageForm").append("<input type='hidden' name='excel_to_all' value='1' id='excel_to_all' />");
    		$("#bottomPageForm").submit();
    		$("#excel_to_all").val("");	
		}
	});
	//导出
	$("#export_23").click(function(){
		if(confirm("提示，您确认导出数据？")){
			$("#bottomPageForm").append("<input type='hidden' name='excel_to_all' value='2' id='excel_to_all' />");
    		$("#bottomPageForm").submit();
    		$("#excel_to_all").val("");	
		}
	});
	$("#l_toggle").click(function(){
		$("#ol_hide").toggle();
		$("#fs_hide").toggleClass("filed_border");
		window.parent.resizeFrameHeight('mainFrame', 3);
	});
	
	
	$("#single_price_begin").keyup(function(){//邮编必须为数字
		var _reg = /^[\+\-]?\d*?\.?\d*?$/;
		var single_price_begin =(this.value);
   		if (!_reg.test(single_price_begin)) {
   			$("#single_price_begin").val(0);
   		}
	});
	
	$("#single_price_end").keyup(function(){//邮编必须为数字
		var _reg = /^[\+\-]?\d*?\.?\d*?$/;
		var single_price_end =(this.value);
   		if (!_reg.test(single_price_end)) {
   			$("#single_price_end").val(0);
   		}
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

//类别-连动-型号
function category_id_chg(){
	$("#model_id").empty();
	$("<option value=''>-产品型号-</option>").appendTo($("#model_id"));
	var url = "${ctx}/manager/admin/KonkaMobileSailData.do?method=getModel&size_id="+$('#measure_id').val();
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
	var url = "${ctx}/manager/admin/KonkaMobileSailData.do?method=getCType&c_comm="+$('#c_comm').val();
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
