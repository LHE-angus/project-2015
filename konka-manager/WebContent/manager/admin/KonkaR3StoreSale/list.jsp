<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
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
<div class="oarcont" id="body_oarcont" style="position:relative;overflow:hidden;">
<div class="oartop">
  <table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
      <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
      <td>当前位置：${naviString}</td>
      <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align:middle;" /> <span id="span_help" style="cursor:pointer;">帮助</span></td>
    </tr>
  </table>
</div>
<div class="rtabcont1" style="width:100%;overflow-x:auto;"> 
  <html-el:form action="/admin/KonkaR3StoreSale">
    <html-el:hidden property="method" value="list" />
    <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
    <html-el:hidden property="current_fgs_code" styleId="current_fgs_code" value="${current_fgs_code}" />

    <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
      <tr>
      	<td nowrap="nowrap" align="right">
			<strong class="fb">分公司：</strong>
        </td>
        <td>
	       	<html-el:select property="dept_id" styleId="dept_id" style="width:90px">
              <html-el:option value="">-请选择-</html-el:option>
            </html-el:select>
        </td>
        <td align="right">
        	<strong class="fb">门店名称：</strong>
        </td>
        <td>
          <html-el:text property="store_name_like" maxlength="40" size="20" />
        </td>
        <td  align="right" >
          <strong class="fb">门店编号：</strong>
        </td>
        <td>
          <html-el:text property="store_id" maxlength="20" size="20" />
        </td>
        <td align="right" >
          <strong class="fb">业务员：</strong>
        </td>
        <td>
          <html-el:text property="ywy_name_like" maxlength="20" size="15" />
        </td>
     </tr>
     <tr>
     	
     	<td align="right">
          <strong class="fb"> 客户R3编码：</strong>
        </td>
        <td>
          <html-el:text property="r3_code_like" maxlength="40" size="20" value='${af.map.r3_code_like}'/>
        </td>
        <td valign="middle" nowrap="nowrap" class="title_item" align="right">客户类型：</td>
		<td >
       		<html-el:select property="v_customer_type1" styleId="v_customer_type1" style="width:80px;">
            	<html-el:option value="">-请选择-</html-el:option>
            </html-el:select>
            <html-el:select property="v_customer_type2" styleId="v_customer_type2" style="width:130px;">
                <html-el:option value="">-请选择-</html-el:option>
            </html-el:select>
		</td>
        <td align="right">
            <strong class="fb">是否停用：</strong>
        </td>
        <td>
             <html-el:select property="is_del" styleId="is_del" style="width:90px;" onchange="this.form.submit();">
              <html-el:option value="0">未停用</html-el:option>
               <html-el:option value="1">已停用</html-el:option>
             </html-el:select> 
        </td>
      </tr>
      <tr>
      <td align="right">
          <strong class="fb">时间范围：</strong>
        </td>
        <td colspan="3">
          <html-el:text property="cur_date_begin" styleId="cur_date_begin" size="7" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;width:120px;" onclick="new Calendar(2005, 2030, 0).show(this);" />
            	至
            <html-el:text property="cur_date_end" styleId="cur_date_end" size="7" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;width:120px;" onclick="new Calendar(2005, 2030, 0).show(this);" />
         
        </td>
      
      
      
      	<td align="right">
          <strong class="fb">客户名称：</strong>
        </td>
        <td>
          <html-el:text property="kh_name_like" maxlength="40" size="20" />
        </td>
      	
      	<td align="right">
          <input class="but1" type="button" name="Submit" id="butsubmit" value="搜索" />
        </td>
        <td></td>
      </tr>
    </table>
  </html-el:form>
  </div>
  
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
   
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
    
      <tr>
        <td>
		    <input type="button" id="export_22" value="导出" class="but_excel" />
		</td>
	 </tr>
	</table>

  </div>
  <div class="rtabcont1" style="overflow-x:auto;">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tr class="tabtt1">
          <td width="5%" align="center" nowrap="nowrap">序号</td>
          <td width="5%" align="center" nowrap="nowrap">分公司</td>
          <td align="center" nowrap="nowrap">门店名称</td>
          <td width="6%" align="center" nowrap="nowrap">门店编号</td>
          <td width="10%" align="center" nowrap="nowrap">客户名称</td>
          <td width="5%" align="center" nowrap="nowrap">客户R3编码</td>
          <td align="center" nowrap="nowrap">客户类型</td>
          <td align="center" nowrap="nowrap">细分类型</td>
          <td width="5%" align="center" nowrap="nowrap">业务员姓名</td>
          <td align="center" nowrap="nowrap">促销员姓名</td>

          <td align="center" nowrap="nowrap">上年同期量</td>
          <td align="center" nowrap="nowrap">上年同期额</td>
          <td align="center" nowrap="nowrap">本期零售量</td>
          <td align="center" nowrap="nowrap">本期零售额</td>
          <td align="center" nowrap="nowrap">易TV量</td>
          <td align="center" nowrap="nowrap">易TV额</td>
          <td align="center" nowrap="nowrap">4K量</td>
          <td align="center" nowrap="nowrap">4K额</td>
          <td align="center" nowrap="nowrap">大板量</td>
          <td align="center" nowrap="nowrap">大板额</td>
		  <td>照片</td>
        </tr>
        <tbody>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr>
              <td nowrap="nowrap" align="center">${(af.map.pager.currentPage - 1)*af.map.pager.pageSize + vs.count}</td>
              <td align="left" nowrap="nowrap"><font class="blue12px">${fn:escapeXml(cur.dept_name)}</font></td>
              <td align="left" nowrap="nowrap"><font class="blue12px">${cur.store_name}</font></td>
              <td align="right" nowrap="nowrap"><font class="blue12px">${cur.store_id}</font></td>
               <td align="left" nowrap="nowrap">${cur.map.ext_customer_name}</td>
              <td align="left" nowrap="nowrap"><a href="${ctx}/manager/admin/KonkaR3Store.do?mod_id=${af.map.mod_id}&r3_code=${cur.r3_code}" class="fblue" title="点击可查询该客户全部门店">${fn:escapeXml(cur.r3_code)}</a></td>
            
              <td align="left" nowrap="nowrap">${cur.map.ext_par_customer_type_name}</td>
              <td align="left" nowrap="nowrap">${cur.map.ext_customer_type_name}</td>

              <td align="left" nowrap="nowrap"><font class="blue12px">${fn:escapeXml(cur.map.ext_ywy_name)}</font></td>
             
              <td align="left" nowrap="nowrap">
              	<font class="blue12px">${cur.map.ext_cxy_name}</font>
              </td>
          <td align="center" nowrap="nowrap">${cur.map.last_all_num}</td>
          <td align="center" nowrap="nowrap">${cur.map.last_all_price}</td>
          <td align="center" nowrap="nowrap">${cur.map.cur_all_num}</td>
          <td align="center" nowrap="nowrap">${cur.map.cur_all_price}</td>
          <td align="center" nowrap="nowrap">${cur.map.ytv_num}</td>
          <td align="center" nowrap="nowrap">${cur.map.ytv_price}</td>
          <td align="center" nowrap="nowrap">${cur.map.k_num}</td>
          <td align="center" nowrap="nowrap">${cur.map.k_price}</td>
           <td align="center" nowrap="nowrap">${cur.map.db_num}</td>
          <td align="center" nowrap="nowrap">${cur.map.db_price}</td>  
              <td align="left" nowrap="nowrap">
             <c:set var="file_desc" value="${fn:split(cur.map.file_desc,',')}" />
          <c:forEach items="${file_desc}" var="tt" varStatus="vs1">
          <c:set var="num" value="${fn:length(tt)}" />
          <c:set var="step" value="${vs1.count-1}" />
          	<a href="${ctx}/${fn:split(cur.map.save_path,',')[step]}" target="_blank">&nbsp;<font color="blue">${tt}</font>&nbsp;</a>
          </c:forEach>
              </td>
            </tr>
          </c:forEach></tbody>
      </table>
	  <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaR3StoreSale.do">
	      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
	        <tr>
	          <td height="60" align="center"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
	            <script type="text/javascript">
			            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
			            pager.addHiddenInputs("method", "list");
			            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
			            pager.addHiddenInputs("tree_param", "${tree_param}");
			            pager.addHiddenInputs("r3_code_like", "${af.map.r3_code_like}");
			            pager.addHiddenInputs("dept_id", "${af.map.dept_id}");
			            pager.addHiddenInputs("store_name_like", "${af.map.store_name_like}");
			            pager.addHiddenInputs("ywy_name_like","${af.map.ywy_name_like}");
			            pager.addHiddenInputs("zxy_name_like","${af.map.zxy_name_like}");
			            pager.addHiddenInputs("is_del", "${af.map.is_del}");
			            pager.addHiddenInputs("r3_code", "${af.map.r3_code}");
			            pager.addHiddenInputs("store_id", "${af.map.store_id}");
			            pager.addHiddenInputs("v_customer_type1", "${af.map.v_customer_type1}");
			            pager.addHiddenInputs("v_customer_type2", "${af.map.v_customer_type2}");
			            pager.addHiddenInputs("jing_ban_like", "${af.map.jing_ban_like}");
			            pager.addHiddenInputs("kh_name_like", "${af.map.kh_name_like}");
			            pager.addHiddenInputs("havaman", "${af.map.havaman}");
			            document.write(pager.toString());
			   </script></td>
	        </tr>
	      </table>
	    </form>
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/common.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script> 
<script type="text/javascript" src="${ctx}/styles/customer/jNotify/jNotify.jquery.js"></script> 



<script type="text/javascript">//<![CDATA[
$(document).ready(function() {

	$("#l_toggle").click(function(){
		$("#ol_hide").toggle();
		$("#fs_hide").toggleClass("filed_border");
		window.parent.resizeFrameHeight('mainFrame', 3);
	});
	$("#butsubmit").click(function(){
		loading();
		var f = document.forms[0];
		f.submit();
	});
	//导出
	$("#export_22").click(function(){
		if(confirm("提示，您确认导出数据？")){
			//CNZZ统计代码
			$("#bottomPageForm").append("<input type='hidden' name='excel_to_all' value='1' id='excel_to_all' />");
    		$("#bottomPageForm").submit();
    		$("#excel_to_all").val("");	
		}
	});

	//客户类型初始化
	$("#v_customer_type1").attr({"subElement": "v_customer_type2", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.v_customer_type1}"});
	$("#v_customer_type2").attr({"defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.v_customer_type2}"});

	$("#v_customer_type1").cs("${ctx}/manager/admin/CsAjax.do?method=getKonkaShopType", "par_id", "0", false);
	$("#v_customer_type1").change();

	//分公司列表初始化
	$("#dept_id").attr({"defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.dept_id}"});
	$("#dept_id").cs("${ctx}/manager/admin/CsAjax.do?method=getKonkaDeptForParId", "par_id", "0", false);
	$("#dept_id").change();

	//默认当前分公司
	var fgs = $("#current_fgs_code").val();
	if(fgs!=""){
		$("#dept_id").val(fgs);
		$("#dept_id").change();
	}
});   
function loading(){
	jLoading("&nbsp;&nbsp;正在加载数据...", {autoHide:false, HorizontalPosition:"center", VerticalPosition:"center", MinWidth:150});
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
