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
<link	href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css"	rel="stylesheet" type="text/css" />
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
    <html-el:form action="/admin/KonkaFightActivityManager" enctype="multipart/form-data">
      <html-el:hidden property="method" value="save" styleId="method" />
	<html-el:hidden property="mod_id" value="${af.map.mod_id}" />
	<html-el:hidden property="id" value="${af.map.id}" />
	<html-el:hidden property="queryString" styleId="queryString" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
       <tr>
			<td class="title_item" align="right" nowrap="nowrap"><font
				color="red">*</font>所属门店：</td>
			<td align="left" width="80%" colspan="3">
			  <html-el:select styleId="store_id" property="store_id" styleClass="text012" >
              <html-el:option value="">-请选择-</html-el:option>
              <c:forEach items="${storeList}" var="cur">
              <html-el:option value="${cur.store_id}">${cur.store_name}</html-el:option>
               </c:forEach>
         </html-el:select>
			</td>
		</tr>
		<tr>
      	<td class="title_item" align="right" nowrap="nowrap"><font
				color="red">*</font>活动名称: </td> 
        <td><html-el:text property="activity_name" styleId="activity_name" style="width:20%;">
        </html-el:text> </td> 
       </tr>
		<tr>
      	<td class="title_item" align="right" nowrap="nowrap"><font
				color="red">*</font>活动开始时间: </td> 
        <td width="88%" align="left">
             <fmt:formatDate value="${af.map.begin_date}" var="_s_date" pattern="yyyy-MM-dd" />
			<html-el:text property="begin_date" styleId="begin_date" size="10" maxlength="10" value="${_s_date}" readonly="true" onclick="new Calendar(1900, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
          </td> 
       </tr>
		<tr>
      	<td class="title_item" align="right" nowrap="nowrap"><font
				color="red">*</font>活动结束时间 </td> 
         <td width="88%" align="left">
             <fmt:formatDate value="${af.map.end_date}" var="_e_date" pattern="yyyy-MM-dd" />
			<html-el:text property="end_date" styleId="end_date" size="10" maxlength="10" value="${_e_date}" readonly="true" onclick="new Calendar(1900, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
          </td> 
       </tr>
  <tr>
    <td class="title_item" align="right" nowrap="nowrap"><font
				color="red">*</font>竞品品牌 </td>
    <td align="left" nowrap="nowrap">
    	  <html-el:select styleId="brand_id" property="brand_id" styleClass="text012" >
              <html-el:option value="">-请选择-</html-el:option>
              <c:forEach items="${pdList}" var="cur">
              <html-el:option value="${cur.brand_id}">${cur.brand_name}</html-el:option>
               </c:forEach>
         </html-el:select><br />
    </td>
  </tr>
  <tr>
  <td class="title_item" align="right" nowrap="nowrap">主推型号 </td>
    <td align="left" nowrap="nowrap">
    	  <html-el:text property="model" styleId="model"></html-el:text>
    </td>
  </tr>
  <tr>
   <td class="title_item" align="right" nowrap="nowrap">实际销售量 </td>
    <td align="left" nowrap="nowrap">
    	  <html-el:text property="sale_num" styleId="sale_num"></html-el:text>
    </td>
  </tr>
  <tr>
   <td class="title_item" align="right" nowrap="nowrap">实际销售额 </td>
    <td align="left" nowrap="nowrap">
    	  <html-el:text property="sale_money" styleId="sale_money"></html-el:text>
    </td>
  </tr>  
       <tr>
      	<td class="title_item" align="right" nowrap="nowrap">其他说明: </td> 
        <td><html-el:textarea property="memo" styleId="memo"  rows="3" style="width:50%;">
        </html-el:textarea> </td> 
       </tr>
     <%--  
       <tr>
      	<td class="title_item" align="right" nowrap="nowrap">预约点状态: </td> 
        <td>&nbsp;&nbsp;&nbsp;&nbsp;<html-el:radio property="state" value="0" />开启
            &nbsp;&nbsp;&nbsp;&nbsp;<html-el:radio property="state" value="1" />关闭
         </td> 
       </tr> 
        <tr>
				            <td nowrap="nowrap" class="title_item" align="right">上传附件：</td>
				            <td colspan="3"><div id="divFileHidden" style="display: none;">
				                <input name="file_hidden" type="file" id="file_hidden" style="width: 250px;" />
				                <img src="../../images/x.gif" style="vertical-align:middle; cursor: pointer;" id="imgDelTr" title="删除"/></div>
				              <div id="divFile">
				                <input name="file_show" type="file" id="file_show" style="width: 250px;" />
				                <img src="../../images/+.gif" style="vertical-align:middle; cursor: pointer;" id="imgAddTr" title="再添加一个" /></div>
				            </td>
			          	</tr>
			          <c:if test="${not empty attachmentList}">
			          <tr>
			            <td height="28" class="title_item" align="right">已上传的附件：</td>
			            <td colspan="3"><ol>
			                <c:forEach var="cur" items="${attachmentList}" varStatus="vs">
			                  <li><a href="${ctx}/manager/admin/Download.do?method=downloadFile1&save_name=${cur.save_name}" target="_blank">${cur.file_name}</a>&nbsp;&nbsp;&nbsp;<a href="#" id="att_del_${cur.id}">删除</a></li>
			                </c:forEach>
			              </ol></td>
			            </tr>
			           </c:if>
			          
         --%>
        <tr>
          <td>&nbsp;&nbsp;</td>
             <td height="40" ><input class="but4" type="button" name="Submit4" value=" 保存 " id="btn_submit" />
            <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back()" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="clear"></div>
</div>
<!-- ****** Main Frame End ****** -->
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/commons.plugin.jxc.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.jgrowl.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/jquery-ui/external/bgiframe/jquery.bgiframe.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/lhgdialog/lhgdialog.min.js?skin=idialog"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){	
	$("#store_id").attr("dataType", "Require").attr("msg", "请选择门店！");
	$("#activity_name").attr("dataType", "Require").attr("msg", "请填写活动名称！");
	$("#brand_id").attr("dataType", "Require").attr("msg", "请选择品牌！");
	$("#begin_date").attr("dataType", "Require").attr("msg", "请选择开始时间！");
	$("#end_date").attr("dataType", "Require").attr("msg", "请选择结束时间！");
//	$("#sale_num").attr("dataType", "Integer").attr("msg", "请填写实际销售量！");
//	$("#sale_num").attr("dataType", "Number").attr("msg", "请填写实际销售额！");


	/**	$("#r3_code").multiselect( {
			noneSelectedText : '==请选择==',
			selectedList : 1,
			multiple : false,
			minWidth : 150,
			click : function(event, ui) {
				if (ui.value != "") {
					$(this).val(ui.value);
					$("#customer_name").val(
							$(this).find("option:selected").text());
					
				}
			}
		}).multiselectfilter();	
		*/
// 		$("#store_id").multiselect( {
// 			noneSelectedText : '==请选择==',
// 			selectedList : 1,
// 			multiple : false,
// 			minWidth : 150,
// 			click : function(event, ui) {
// 				if (ui.value != "") {
// 					$(this).val(ui.value);
// 					$("#store_name").val(
// 							$(this).find("option:selected").text());
					
// 				}
// 			}
// 		}).multiselectfilter();	
//		brand_pd_module_init();
		
$("#btn_submit").click(function(){
	if(Validator.Validate(this.form, 3)){
        $("#btn_submit").attr("value", "提交...").attr("disabled", "true");
		this.form.submit();
	}
});
});
function resizeFrameHeight(offset, min_height) {
	// frame id can be found in page /konka-wd/WebContent/manager/admin/Frames2/indexFrame.jsp, and search 'iframe' to get.
	$("#mainFrame", window.parent.document).height(Math.max((min_height || 0), $(document).find("body").height(), $(document).children().height()) + (offset || 0));
}

//根据用户名和密码初始化型号信息
function brand_pd_module_init(){
	// 正在加载数据层
	//var load_data = $.dialog({content: '数据加载中...', max: false, min: false, icon: 'loading.gif', title: '提示！'});
	
	$.ajax({
		type: "POST",
		url:  "${ctx}/MobileList.do?method=GetListJP&from_html=1",
		data: { "timestamp":new Date().getTime() },
		dataType: "jsonp",
		jsonp: "jsonpcallback",
		error: function (xhr, ajaxOptions, thrownError) { alert("数据加载请求失败【" + xhr.statusText + "," + xhr.responseText + "," + xhr.status + "," + thrownError +"】！"); },
		success: function(result) {
		  // 关闭-正在加载数据层
	//  	  load_data.close();	
			
		  if (result.status == "-1"){
		        $.dialog({
				    max: false, min: false, fixed: true,
				    content: result.msg,
				    init: function () {
				        var that = this, i = 8;
				        var fn = function () {
				            that.title(i + '秒后关闭');
				            !i && that.close();
				            i--;
				        };
				        timer = setInterval(fn, 1000);
				        fn();
				    },
				    close: function () {
				        clearInterval(timer);
				    }
				});
	      } else {
	    	
	    	  brand_pd_module_data = result;
	    	  
	    	  $("#brand_id").empty();
	  		  $("#brand_id").append("<option value=''>-请选择-</option>");
	  		  var arr = new Array();
	  		  for(var i = 0; i < result.length; i++){
		  		  var falg = true;
	  		  	  for(var j = 0; j < arr.length; j++){
		  		  	  if(arr[j] == result[i].addon2)
		  		  		falg = false;
	  		  	  }
	  		  	  if(falg){
	  		  		arr[arr.length] = result[i].addon2;
		  			$("#brand_id").append("<option value='" + result[i].addon1 + "'>" + result[i].addon2 + "</option>");
		  			$("#brand_id").val('${af.map.brand_id}');
		  		  }
	  		  }
	  		  
		  }
		}
	});
}

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
