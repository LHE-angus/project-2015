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
    <html-el:form action="/admin/JStocksStoreSummary">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="type" value="${af.map.type}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="10%" class="fb" align="right">部门：</td>
          <td>
          <html-el:select property="dept_sn" styleId="dept_sn" onchange="changeFgsSn();">
              <html-el:option value="">-请选择-</html-el:option>
              <c:forEach items="${konkaDeptList}" var="cur">
               <html-el:option value="${cur.dept_sn}">${cur.dept_name}</html-el:option>
              </c:forEach>
           </html-el:select>
          <html-el:select property="handle_name_like_1" styleId="handle_name_like_1" >
            <html-el:option value="">-请选择-</html-el:option>
          </html-el:select>
          </td>
          
          <td width="10%" class="fb" align="right">客户R3编码：</td>
          <td> <html-el:text property="r3_code_like" size="15" maxlength="20" styleId="r3_code_like" /></td>
          
          <td width="10%" class="fb" align="right">客户名称：</td>
          <td><html-el:text property="customer_name_like" size="15" maxlength="20" styleId="customer_name_like" /></td>
        </tr>
        
        <tr>
        
          <td width="10%" class="fb" align="right">客户类型：</td>
          <td>
          	<html-el:select property="v_customer_type1" styleId="v_customer_type1" style="width:80px;">
            	<html-el:option value="">-请选择-</html-el:option>
            </html-el:select> 
            <html-el:select property="v_customer_type2" styleId="v_customer_type2" style="width:130px;">
                <html-el:option value="">-请选择-</html-el:option>
            </html-el:select>
          </td>
          
          <td width="10%" class="fb" align="right">产品型号：</td>
          <td><html-el:text property="goods_name_like" size="15" maxlength="20" styleId="goods_name_like" /></td>
          
          
          <td width="10%" class="fb" align="right">剩余库存：</td>
          <td>
         	 <html-el:text property="cur_min_num" size="10" maxlength="10" styleId="cur_min_num" onclick="setOnlyNum" />到
             <html-el:text property="cur_max_num" size="10" maxlength="10" styleId="cur_max_num" onclick="setOnlyNum"/>台
          </td>
          
        </tr>
        
        <tr>
        
          <td width="10%" class="fb" align="right">仓库名称：</td>
          <td>
          	<html-el:text property="store_name_like" styleClass="webinput" styleId="store_name_like" maxlength="40"/>
          </td>
          
          <td width="10%" class="fb" align="right">仓库编码：</td>
          <td>
			<html-el:text property="store_sn_like" styleClass="webinput" styleId="store_sn_like" maxlength="40"/>
		  </td>
		  
          <td width="10%" class="fb" align="right">仓库送达方编码：</td>
          <td>
         	 <html-el:text property="sale_r3_code_like" styleClass="webinput" styleId="sale_r3_code_like" maxlength="20"/>
          </td>
          
        </tr>
        
        <tr>
            <td colspan="5"></td>
        	<td>
            <html-el:submit styleClass="but1"  value="搜索" />
            
            <c:if test="${not empty is_allow_back}">
													
												<button name="btn_back" id="btn_back" type="button"
													class="btn btn-info">返回</button>
													</c:if>
            </td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div>
  <input type="button" value="导出" id="export_22" class="but_excel" style="margin-left: 10px;" />
  &nbsp;&nbsp;&nbsp;本次更新时间：<font color="red"><b><fmt:formatDate value="${add_date }" pattern="yyyy-MM-dd HH:mm:ss"/></b></font>
   &nbsp;&nbsp;库存合计：<font color="red"> <b>${af.map.totlePageNum}</b></font>
  </div>
  <div class="rtabcont1" style="overflow-x: auto;">
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
        <tr class="tabtt1">
          <td width="5%" align="center">序号</td>
          <td nowrap="nowrap" align="center" width="8%">分公司</td>
          <td nowrap="nowrap" align="center" width="8%">R3编码</td>
          <td nowrap="nowrap" align="center">客户名称</td>
          <td nowrap="nowrap" align="center">客户类型</td>
          <td nowrap="nowrap" align="center">细分类型</td>
          <td nowrap="nowrap" align="center">经办名称</td>
          <td nowrap="nowrap" align="center" width="7%">仓库名称</td>
          <td nowrap="nowrap" align="center" width="6%">仓库编码</td>
          <td nowrap="nowrap" align="center" width="6%">仓库送达方编码</td>
          
          <td nowrap="nowrap" align="center" width="7%">产品型号</td>
          <td nowrap="nowrap" align="center" width="3%">周转天数</td>
          <td nowrap="nowrap" align="center" width="6%">初始库存</td>
          <td nowrap="nowrap" align="center" width="7%">期初金额</td>
          <td nowrap="nowrap" align="center" width="6%">进货数量</td>
          <td nowrap="nowrap" align="center" width="7%">进货金额</td>
          <td nowrap="nowrap" align="center" width="6%">销售数量</td>
          <td nowrap="nowrap" align="center" width="7%">销售成本</td>
          <td nowrap="nowrap" align="center" width="7%">销售金额</td>
          <td nowrap="nowrap" align="center" width="6%">其他数量</td>
          <td nowrap="nowrap" align="center" width="7%">其他金额</td>
          <td nowrap="nowrap" align="center" width="6%">剩余库存量</td>
          <td nowrap="nowrap" align="center" width="7%">剩余库存金额</td>
          <td nowrap="nowrap" align="center" width="12%">盘存时间</td>
        </tr>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
            <td align="left" nowrap="nowrap">${fn:escapeXml(cur.map.dept_name)}</td>
            <td align="left" nowrap="nowrap">${fn:escapeXml(cur.r3_code)}</td>
            <td align="left" nowrap="nowrap">${fn:escapeXml(cur.customer_name)}</td>
            <td align="left" nowrap="nowrap">${cur.map.par_cust_type_name}</td>
            <td align="left" nowrap="nowrap">${cur.map.cust_type_name}</td>
            <td align="left" nowrap="nowrap">${fn:escapeXml(cur.map.handle_name)}</td>
            
            <td align="left" nowrap="nowrap">${cur.map.store_name}</td>
            <td align="left" nowrap="nowrap">${cur.map.store_sn}</td>
            <td align="left" nowrap="nowrap">${cur.map.sale_r3_code}</td>
            
            <td align="left" nowrap="nowrap">${fn:escapeXml(cur.goods_name)}</td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.map.zzts}" pattern="0.00" /></td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.init_num}" pattern="0" /></td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.init_money}" pattern="0.00" /></td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.come_num}" pattern="0" /></td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.come_money}" pattern="0.00" /></td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.out_num}" pattern="0" /></td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.sale_cost}" pattern="0.00" /></td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.out_money}" pattern="0.00" /></td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.other_num}" pattern="0" /></td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.other_money}" pattern="0.00" /></td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.init_num + cur.come_num - cur.out_num+cur.other_num}" pattern="0" /></td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.init_money + cur.come_money - cur.sale_cost+cur.other_money}" pattern="0" /></td>
            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.opr_date}" pattern="yyyy-MM-dd HH:mm" /></td>
          </tr>
        </c:forEach>
        <c:forEach begin="${fn:length(entityList)}" end="${af.map.pager.pageSize - 1}">
        <tr>
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
        </tr>
      </c:forEach>
      </table>
    <br />
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="JStocksStoreSummary.do">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
            pager.addHiddenInputs("r3_code_like", "${fn:escapeXml(af.map.r3_code_like)}");	
			pager.addHiddenInputs("dept_sn", "${fn:escapeXml(af.map.dept_sn)}");	
			pager.addHiddenInputs("customer_name_like", "${fn:escapeXml(af.map.customer_name_like)}");	
			pager.addHiddenInputs("customer_name_like", "${fn:escapeXml(af.map.store_name_like)}");	
			pager.addHiddenInputs("customer_name_like", "${fn:escapeXml(af.map.store_sn_like)}");	
			pager.addHiddenInputs("customer_name_like", "${fn:escapeXml(af.map.sale_r3_code_like)}");
			pager.addHiddenInputs("goods_name_like", "${fn:escapeXml(af.map.goods_name_like)}");	
			pager.addHiddenInputs("type", "${fn:escapeXml(af.map.type)}");	
			pager.addHiddenInputs("year", "${fn:escapeXml(af.map.year)}");	
			pager.addHiddenInputs("month", "${fn:escapeXml(af.map.month)}");
			pager.addHiddenInputs("v_customer_type1", "${af.map.v_customer_type1}");
            pager.addHiddenInputs("v_customer_type2", "${af.map.v_customer_type2}");
            pager.addHiddenInputs("cur_min_num", "${af.map.cur_min_num}");
            pager.addHiddenInputs("cur_max_num", "${af.map.cur_max_num}");		
            pager.addHiddenInputs("handle_name_like_1", "${af.map.handle_name_like_1}");		
            document.write(pager.toString());
            </script>
            </td>
        </tr>
      </table>
    </form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){                                         
	//客户类型初始化
	$("#v_customer_type1").attr({"subElement": "v_customer_type2", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.v_customer_type1}"});
	$("#v_customer_type2").attr({"defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.v_customer_type2}"});
	$("#cur_min_num").attr("focus",setOnlyNum);
	$("#cur_max_num").attr("focus",setOnlyNum);
	$("#v_customer_type1").cs("${ctx}/manager/admin/CsAjax.do?method=getKonkaShopType", "par_id", "0", false);
	$("#v_customer_type1").change();
	
	
	if('${af.map.handle_name_like_1}'!=""||'${af.map.handle_name_like_1}'!=null){
		changeFgsSn();  
	} 
	
});


function changeFgsSn(){ 
	//取得分公司
	var vdeptsn = $("#dept_sn").val();
	if(vdeptsn !=""){
		$.ajax({
			type: "post",
			url: "${ctx}/manager/admin/JStocksStoreSummary.do?method=getJBDataBydeptCode",
			data: {"dept_sn" : vdeptsn},
			dataType: "json",
			error: function(request, settings) {
				var html = "<option value=''>请选择</option>";
				$("#handle_name_like_1").empty();
				$("#handle_name_like_1").html(html);
				}, 
			success: function(result) {
				var html = "<option value=''>请选择</option>";
				for(var i = 0 ;i<result.length ;i++){
					if(result[i] == "${af.map.handle_name_like_1}"){
						html += "<option selected='selected' value='"+result[i]+"'>"+result[i]+"</option>";
						}else{
							html += "<option value='"+result[i]+"'>"+result[i]+"</option>";
							}
				}
				$("#handle_name_like_1").empty();
				$("#handle_name_like_1").html(html);
			}
		});
	}else{
		var html = "<option value=''>请选择</option>";
		$("#handle_name_like_1").empty();
		$("#handle_name_like_1").html(html);
	}
	
}
//返回
$("#btn_back").click(function() {
	history.back(-1);
});

//导出
$("#export_22").click(function(){
	if(confirm("提示，您确认导出数据？")){
		//CNZZ统计代码
		$("#bottomPageForm").append("<input type='hidden' name='excel_to_all' value='1' />");
		$("#bottomPageForm").submit();	
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
		if(!this.value.match(/^(?:[\+]?\d+(?:\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^$/))this.value=0;this.o_value=this.value};
		if(this.value.length == 0) this.value = "0";
	});
	//this.text.selected;
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>