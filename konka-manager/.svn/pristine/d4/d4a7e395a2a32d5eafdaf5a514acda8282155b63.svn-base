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
<link href="${ctx}/commons/styles/message/message.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/customer/multiselect/jquery.multiselect2side.css" rel="stylesheet" type="text/css" />

<style>
</style>
  
  
</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
    <%@ include file="/commons/pages/messages.jsp" %>
  </div>
  
  <div class="rtabcont2">
    <html-el:form action="admin/CrmPriceHeader" method="post">
      <html-el:hidden property="method" value="save" styleId="method"  />
      <html-el:hidden property="id" styleId="id" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" styleId="mod_id" />
      
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
        <tr>
        <!-- zfor,zfgc,zfcr,zfre -->
          <td class="title_item" width="10%">价格类型：</td>
          <td width="40%">
	          <html-el:select property="price_type" styleId="price_type" style="width:150px">
		          	<html-el:option value="ZFOR">常规价格</html-el:option>
		          	<html-el:option value="ZFGC">工程机价格</html-el:option>
		          	<html-el:option value="ZFCR">返利补差价格</html-el:option>
		          	<html-el:option value="ZFRE">退货价格</html-el:option>
		      </html-el:select>
          </td>
          <td ></td>
          <td ></td>
        </tr>
        <tr>
          <td class="title_item" width="10%">价格编号：</td>
          <td width="40%"><html-el:text property="price_code" styleId="price_code" readonly="true"/></td>
          <td class="title_item" width="10%">价格名称：</td>
          <td width="40%"><html-el:text property="price_name" styleId="price_name"/> <span style="font-style: normal; color: gray">建议:群组+价格类型+时间(北京苏宁常规机型3月份)</span></td>
        </tr>
		<tr>
          <td class="title_item" width="10%">部门：</td>
          <td width="40%">
	           <html-el:select property="dept_id" styleId="dept_id" style="width:150px" onchange="getGroupsByDeptId()">
	           		<html-el:option value="">请选择</html-el:option>
	          		<c:forEach items="${deptList}" var="cur">
	          			<html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
	          		</c:forEach>
	           </html-el:select>
           </td>
          <td class="title_item" width="10%">客户群组：</td>
          <td width="40%">
          	 <html-el:select property="groupcode" styleId="groupcode" style="width:150px">
	           		<html-el:option value="">请选择</html-el:option>
	          		<c:forEach items="${groupList}" var="v">
	          			<html-el:option value="${v.groupcode}">${v.groupname}</html-el:option>
	          		</c:forEach>
	           </html-el:select>
        </tr>
        <tr>
          <td class="title_item" width="10%">开始时间：</td>
          <td width="40%">
          <fmt:formatDate value="${af.map.begin_date}" pattern="yyyy-MM-dd" var="_begindate" />
		       <input name="begin_date" id="begin_date" size="12" value="${_begindate}" style="width:150px" class="Wdate" type="text" onclick="WdatePicker({readOnly:true,maxDate:'#F{$dp.$D(\'end_date\')}'})" />
          </td>
          <td class="title_item" width="10%">结束时间：</td>
          <td width="40%">
           <fmt:formatDate value="${af.map.end_date}" pattern="yyyy-MM-dd" var="_enddate" />
		        <input name="end_date" id="end_date" size="12" value="${_enddate}" style="width:150px" class="Wdate" type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,minDate:'#F{$dp.$D(\'begin_date\')||\'%y-%M-{%d}\'}'})" />
		  </td>
        </tr>
	       	<tr>
	       		<td nowrap="nowrap" class="title_item" colspan="2">
		       		&nbsp;&nbsp;<input class="but2" type="button" title="添加机型" value="添加" onclick="showModelPage();" ></input>
		       		&nbsp;&nbsp;<input class="but4" type="button" value="保存" id="btn_save" />
		       		&nbsp;&nbsp;<input class="but5" type="button" value="返回" onclick="history.go(-1);" id="btn_back" />
	    			&nbsp;&nbsp;<input class="but_excel" type="button" name="Submit3" value="导入" onclick="gotoPage()" />
	       		</td>
	       		<td colspan="2"></td>
        	</tr>
       	<tr>
       		<td colspan="4">
	       		<div class="rtabcont1">
				    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2" id="ctmtab">
				        <tr class="tabtt1">
				          <td align="center" nowrap="nowrap" >操作</td>
				        
				          <td align="center" nowrap="nowrap"  style="display:none">行ID</td>
				          <td align="center" nowrap="nowrap"  style="display:none">头ID</td>
				          <td align="center" nowrap="nowrap" >机型编码</td>
				          <td align="center" nowrap="nowrap" >机型名称</td>	
				          <td align="center" nowrap="nowrap" >供价</td>	
				          <td align="center" nowrap="nowrap" >市场价</td>	
				          <td align="center" nowrap="nowrap" >最低价</td>	
				          <td align="center" nowrap="nowrap" >提成</td>	
				          <td align="center" nowrap="nowrap" >返利</td>
				          <td align="center" nowrap="nowrap" >折扣</td>
				          <td align="center" nowrap="nowrap" >功能</td>	 
				          <td align="center" nowrap="nowrap" >推广政策</td>	
<!-- 				          <td align="center" nowrap="nowrap" >备注</td> -->
				        </tr>
						<c:forEach var="cur" items="${lineList}" varStatus="vs">
						        <tr>
						       	 	<td align="center">
						            <span style="cursor:pointer;" class="fblue" onclick="showModelPage2(${cur.id},${cur.headid })" id="btn_update_line">修改</span>
						            |&nbsp;<span style="cursor:pointer;" class="fblue" onclick="doNeedMethod('确定删除吗？', 'CrmPriceHeader.do', 'deleteL','line_id=${cur.id}&mod_id=${af.map.mod_id}&id=${cur.headid}&')">删除</span>
						            </td>
						            <td align="center" nowrap="nowrap" style="display:none">${cur.id }</td> 
						            <td align="center" nowrap="nowrap" style="display:none">${cur.headid }</td> 
						            <td align="left" nowrap="nowrap">${cur.modelcode }</td> 
						            <td align="left" nowrap="nowrap">${cur.modelname }</td> 
						            <td align="right" nowrap="nowrap">${cur.forprice }</td> 
						            <td align="right" nowrap="nowrap">${cur.marketprice }</td>
						            <td align="right" nowrap="nowrap">${cur.lowestprice }</td> 
						            <td align="right" nowrap="nowrap">${cur.tc }</td> 
						            <td align="right" nowrap="nowrap">${cur.fl }</td>
						            <td align="right" nowrap="nowrap">${cur.discount }</td>
						            <td align="left" nowrap="nowrap">${cur.func }</td> 
						            <td align="left" nowrap="nowrap">${cur.policy }</td>
<%-- 						            <td align="center" nowrap="nowrap">${cur.remark }</td> --%>
						            
						        </tr> 
						</c:forEach>
				      </table>
				</div> 
       		</td>
       	</tr>
      </table>
    </html-el:form>
    <div>
		<form id="bottomPageForm" name="bottomPageForm" method="post" action="?">
	     	<html-el:hidden property="method" value="edit" />
		     <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		       <tr>
		         <td height="40" align="center"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
		           <script type="text/javascript">
					  var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
						  pager.addHiddenInputs("id", "${af.map.id}"); 
						  pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
						  pager.addHiddenInputs("dept_id", "${af.map.dept_id}");
						  document.write(pager.toString()); 
					</script>
				</td>
		       </tr>
		     </table>
      </form>
    </div>
  </div>
  <div class="clear"></div>
</div>
<!-- ****** Main Frame End ****** -->
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar/WdatePicker.js"></script> 
<script type="text/javascript" src="${ctx}/styles/customer/multiselect/jquery.multiselect2sideYwy.js"></script>
<script type="text/javascript">//<![CDATA[
    $(document).ready(function(){
    	/**较验规则**/
    	$("#dept_id").attr("dataType", "Require").attr("msg", "部门不能为空！");
    	$("#groupcode").attr("dataType", "Require").attr("msg", "客户群组不能为空！");
    	$("#price_code").attr("dataType", "Require").attr("msg", "价格编号不能为空！");
    	$("#price_name").attr("dataType", "Require").attr("msg", "价格名称不能为空！");
    	$("#begin_date").attr("dataType", "Require").attr("msg", "开始时间不能为空！");
        $("#btn_save").click(function(){
        	if(Validator.Validate(this.form, 3)){
    			 $("#btn_submit").attr("disabled", "true");
    			 this.form.submit();
    		}
        });
    	
        // 根据部门ID,获取部门下面的客户群组
        getGroupsByDeptId();
        
	}); 
    
    // 根据部门Id获取部门所有客户群组 
    function getGroupsByDeptId(){
    	$("#groupcode").empty();
    	var url = "${ctx}/manager/admin/CrmPriceHeader.do?method=getGroupsByDeptId&dept_id="+$('#dept_id').val();
		$.getJSON(url, function(data) {
			if(data != null){
				$.each(data, function(i, item) {
					// 返回值以数组返回 
					$("<option></option>").val(item[0]).text(item[1]).appendTo($("#groupcode"));
				});
			}
			if('${af.map.groupcode }' != null || '${af.map.groupcode }' != '' ){
				$("#groupcode").val('${af.map.groupcode }');
			}
		});
    }
    
    
    // 弹出机型 挑选页面,保存操作在弹出页面执行 
    function showModelPage(){
    	var id = $("#id").val();
    	var tt = Math.random();
    	
    	if(!id){
    		alert("先执行保存再添加机型 ! ");
    		return ;
    	}
    	var returnValue =  window.showModalDialog("${ctx}/manager/admin/CrmPriceHeader.do?method=showGetModelPage&dept_id="+"${af.map.dept_id}"+"&headid="+id+"&mod_id="+"${af.map.mod_id}&tt="+tt+"",window,"Height=550,Width=880,resizable=no,toolbar=no,menubar=no,scrollbars=no,addressbars=no, status=no,alwaysRaised=yes");
	    window.location.href=window.location.href;
   }
    // 弹出机型 挑选页面,修改操作在弹出页面执行 
    function showModelPage2(lineid,headid){
    	var tt = Math.random();
    	if(!lineid){
    		alert("lineid is null");
    		return ; 
    	} 
    	if(!headid){
    		alert("headid is null");
    		return ;
    	} 
    	window.showModalDialog("${ctx}/manager/admin/CrmPriceHeader.do?method=showGetModelPage2&dept_id="+"${af.map.dept_id}"+"&headid="+headid+"&id="+lineid+"&mod_id="+"${af.map.mod_id}&tt="+tt+"",window,"Height=550,Width=880,resizable=no,toolbar=no,menubar=no,scrollbars=no,addressbars=no, status=no,alwaysRaised=yes");
	    window.location.href=window.location.href;
   } 
    
    function gotoPage(){
    	var id = $("#id").val();
    	var tt = Math.random();
    	if(!id){
    		alert("先执行保存再导入机型 ! ");
    		return ;
    	}
    	 doNeedMethod(null, 'CrmPriceHeader.do', 'add_excel','mod_id=${af.map.mod_id}&id='+id+'&dept_id=${af.map.dept_id }&tt='+tt);
    }
    
//]]></script>
</body>
</html>
