<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<META charset="UTF-8">
<META name="viewport" content="width=device-width, initial-scale=1">
<META http-equiv=Expires content=0>
<META http-equiv=Cache-Control content=no-cache>
<META http-equiv=Pragma content=no-cache>
<title>预约点管理</title>
<link rel="stylesheet" type="text/css"
	href="${ctx}/mobile/css/common.css" />
</head>
<body>
<div class="oarcont">
  <div class="rtabcont2">
    <html-el:form action="/KonkaSpActivityAddr" enctype="multipart/form-data">
      <html-el:hidden property="method" value="save" styleId="method" />
	<html-el:hidden property="id" value="${af.map.id}" />
	<html-el:hidden property="user_id" value="${user_id}" />
      <html-el:hidden property="userpass" value="${userpass}" />
	<html-el:hidden property="queryString" styleId="queryString" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtab2">
       <tr>
          <th colspan="2" width="90%" align="left" class="bno" id="filetitle">预约点基本信息</th>
        </tr>
       <tr>
       <td width="10%" class="title_item" align="right" nowrap="nowrap">
       <font color="red">*</font>预约点编码: </td>
       <td>
     <html-el:text property="addr_index" styleId="addr_index"  readonly="true" style="width:170px;color:red;" ></html-el:text>
       </td>
       </tr>
       <tr>
      	<td class="title_item" align="right" nowrap="nowrap">预约点标题: </td> 
        <td><html-el:text property="addr" styleId="addr" style="width:70%;">
        </html-el:text> </td> 
       </tr>
       <tr>
			<td class="title_item" align="right" nowrap="nowrap"><font
				color="red">*</font>所属门店：</td>
			<td align="left" width="80%" colspan="3"><html-el:select
				property="store_id" styleId="store_id" value="${af.map.store_id}">
				<html-el:option value="">--请选择--</html-el:option>
				<c:forEach items="${storeList}" var="cur" varStatus="vs">
					<html-el:option value="${cur.store_id}">${cur.store_name}</html-el:option>
				</c:forEach>
			</html-el:select>
			<html-el:hidden property="store_name"
				styleId="store_name" />
			</td>
		</tr>
  <%--    <tr> 
			<td class="title_item" align="right" nowrap="nowrap"><font
				color="red">*</font>所属客户：</td>
			<td align="left" width="80%" colspan="3"><html-el:select  
			property="r3_code" styleId="r3_code" value="${af.map.r3_code}">
				<html-el:option value="">--请选择--</html-el:option>
				<c:forEach items="${customerList}" var="cur" varStatus="vs">
					<html-el:option value="${cur.map.customer_code}">${cur.map.customer_name}</html-el:option>
				</c:forEach>
			</html-el:select>
			<html-el:hidden property="customer_name"
				styleId="customer_name" />
			</td>
		</tr>
       --%>	
       <tr>
      <td class="title_item" align="right" nowrap="nowrap">创建人: </td> 
        <td><html-el:text property="add_user_name" styleId="add_user_name" readonly="true"></html-el:text> </td> 
       </tr>
      
       <tr>
      	<td class="title_item" align="right" nowrap="nowrap">预约点负责人: </td> 
        <td><html-el:text property="addr_header" styleId="addr_header" >
        </html-el:text> </td> 
       </tr>
      
      
        
       
       <tr>
      	<td class="title_item" align="right" nowrap="nowrap">预约点描述: </td> 
        <td><html-el:textarea property="addr_memo" styleId="addr_memo"  rows="3" style="width:50%;">
        </html-el:textarea> </td> 
       </tr>
       
       <tr>
      	<td class="title_item" align="right" nowrap="nowrap">预约点状态: </td> 
        <td>&nbsp;&nbsp;&nbsp;&nbsp;<html-el:radio property="state" value="0" />开启
            &nbsp;&nbsp;&nbsp;&nbsp;<html-el:radio property="state" value="1" />关闭
         </td> 
       </tr> 
        <tr>
				            <td nowrap="nowrap" class="title_item" align="right">上传附件：</td>
				            <td colspan="3"><div id="divFileHidden" style="display: none;">
				                <input name="file_hidden" type="file" id="file_hidden" style="width: 180px;" />
				                <img src="../images/x.gif" style="vertical-align:middle; cursor: pointer;" id="imgDelTr" title="删除"/></div>
				              <div id="divFile">
				                <input name="file_show" type="file" id="file_show" style="width: 180px;" />
				                <img src="../images/+.gif" style="vertical-align:middle; cursor: pointer;" id="imgAddTr" title="再添加一个" /></div>
				            </td>
			          	</tr>
			          <c:if test="${not empty attachmentList}">
			          <tr>
			            <td height="28" class="title_item" align="right">已上传的附件：</td>
			            <td colspan="3"><ol>
			                <c:forEach var="cur" items="${attachmentList}" varStatus="vs">
			                  <li><a href="${ctx}/${cur.save_path}" target="_blank">${cur.file_name}</a>&nbsp;&nbsp;&nbsp;<a href="#" id="att_del_${cur.id}">删除</a></li>
			                </c:forEach>
			              </ol></td>
			            </tr>
			           </c:if>
			          
        <tr>
          <td>&nbsp;&nbsp;</td>
          <td><input class="but4" type="submit" name="Submit4" value="提交" />
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
	
		$("#store_id").change( function(){
			$("#store_name").val($(this).find("option:selected").text());
		});

		 $("#imgAddTr").click(function (){
		        $("#divFileHidden").clone().find("#file_hidden").attr("name", "file_" + new Date().getTime()).end().appendTo($("#divFile")).show();
		        resizeFrameHeight();
		        $("img[id='imgDelTr']").each(function(){
		            $(this).click(function (){
		                $(this).parent().remove();
		                resizeFrameHeight();
		            });
		        });
	     });


		 $("a[id^='att_del_']").click(function() {
		  	  var a = this;
		  	   if(!confirm('确实要删除此附件？')) return;
		  	    $.post("KonkaSpActivityAddr.do", { method : "deleteFile", id : $(this).attr("id").replace(/att_del_/g, '')}, function(success) {
		  	   if (success){alert("恭喜您，删除附件成功!");$(a).parent().remove();} else alert(" 很抱歉，删除附件出错!"); 
		  	  });
		   }); 
		
});
function resizeFrameHeight(offset, min_height) {
	// frame id can be found in page /konka-wd/WebContent/manager/admin/Frames2/indexFrame.jsp, and search 'iframe' to get.
	$("#mainFrame", window.parent.document).height(Math.max((min_height || 0), $(document).find("body").height(), $(document).children().height()) + (offset || 0));
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
