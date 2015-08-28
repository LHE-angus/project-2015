<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>
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
<c:set var="type_id" value="" />
<c:set var="type_name" value="资讯" />
<c:forEach var="cur" items="${peArticleTypeList}">
	<c:if test="${cur.type_name eq fnx:substringAfterLast_ss(naviString, ' ')}">
		<c:set var="type_id" value="${cur.id}" />
		<c:set var="type_name" value="${cur.type_name}" />
	</c:if>
</c:forEach>
<div class="oarcont">
  <div class="oartop">
    <table width="500" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
    <html-el:form action="/admin/KonkaPeArticleInfo" enctype="multipart/form-data">
      <html-el:hidden property="id" styleId="id" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="receive_user_type" styleId="receive_user_type" />
      <html-el:hidden property="public_target" styleId="public_target" />
      <html-el:hidden property="areas_ids" styleId="areas_ids" />
      <html-el:hidden property="areas_names" styleId="areas_names" />
      <html-el:hidden property="user_type" styleId="user_type" />
      <html-el:hidden property="method" styleId="method" value="save" />
      <html-el:hidden property="queryString" styleId="queryString" />
      <c:if test="${not empty type_id}"><input type="hidden" name="article_type_id" value="${type_id}" /></c:if>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
        <tr>
          <td colspan="2" class="item_class" align="right" ><strong class="fb">基本信息</strong></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item">${type_name}标题：</td>
          <td><html-el:text property="title" styleId="title" style="width:380px;" styleClass="webinput" maxlength="60" />
            &nbsp;<span style="color:red">*</span></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item">关键字：</td>
          <td><html-el:text property="key_words" styleId="key_words" style="width:380px;" styleClass="webinput" maxlength="30" />
            <span class="note"> 字段用“，”分隔</span>&nbsp;<span style="color:red">*</span></td>
        </tr>
        <c:if test="${empty type_id}">
	        <tr>
	          <td nowrap="nowrap" class="title_item">${type_name}类别：</td>
	          <td><html-el:select property="article_type_id" styleId="article_type_id">
		              <html-el:option value="">请选择...</html-el:option>
		              <html-el:optionsCollection name="peArticleTypeList" label="type_name" value="id" />
		            </html-el:select>
		            &nbsp;<span style="color:red">*</span>
		     </td>
	        </tr>
         </c:if>
        <tr>
          <td nowrap="nowrap" class="title_item">作者：</td>
          <td><html-el:text property="author" styleId="author" style="width:380px;" styleClass="webinput" maxlength="30" /></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item">来源：</td>
          <td><html-el:text property="source" styleId="source" style="width:380px;" styleClass="webinput" maxlength="60" /></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item">摘要：</td>
          <td><html-el:textarea property="summary" styleId="summary" cols="50" rows="3" style="resize:none" />
            <span class="note"> 最多可输入250个字</span></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item">内容：</td>
          <td><FCK:editor instanceName="content">
              <jsp:attribute name="value">${af.map.content}</jsp:attribute>
            </FCK:editor>
            <div class="note">1、此处上传的图片不会自动缩放，请用相关做图软件缩放成您想要的大小；</div>
            <div class="note">2、点击最后一排倒数第三个按钮可实现全屏编辑。</div></td>
        </tr>
        <tr id="trFile">
          <td nowrap="nowrap" class="title_item">上传附件：</td>
          <td><div id="divFileHidden" style="display: none;">
              <input name="file_hidden" type="file" id="file_hidden" style="width: 500px;" />
              <img src="${ctx}/images/x.gif" style="vertical-align:middle; cursor: pointer;" id="imgDelTr" title="删除"/></div>
            <div id="divFile">
              <input name="file_show" type="file" id="file_show" style="width: 500px;" />
              <img src="${ctx }/images/+.gif" style="vertical-align:middle; cursor: pointer;" id="imgAddTr" title="再添加一个" /></div></td>
        </tr>
        <c:if test="${not empty attachmentList}">
          <tr>
            <td height="28" class="title_item">已上传的附件：</td>
            <td><ol>
                <c:forEach var="cur" items="${attachmentList}" varStatus="vs">
                  <li><a href="${ctx}/manager/admin/Download.do?method=downloadFile&save_name=${cur.save_name}">${cur.file_name}</a>&nbsp;&nbsp;&nbsp;<a href="#" id="att_del_${cur.id}">删除</a></li>
                </c:forEach>
              </ol></td>
          </tr>
        </c:if>
        <tr>
          <td colspan="2" align="right" ><strong class="fb">主图设置</strong></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item">上传主图：</td>
          <td width="88%"><c:if test="${not empty af.map.img_path}"><a href="${ctx}/${af.map.img_path}" target="_blank"><img src="${ctx}/${fn:substringBefore(af.map.img_path,'.')}_240.jpg" style="width:150px;height:100px;border:1px solid #ccc;" /></a>
              <input type="checkbox" name="checkbox" id="checkbox_1" value="checkbox" onclick="hideImagePath('img_path');" />
             <label for="checkbox_1"> 重新上传图片</label><br/>
              <html-el:file property="img_path" style="display:none;width:500px;" styleId="img_path" />
            </c:if>
            <c:if test="${empty af.map.img_path}">
              <html-el:file property="img_path" style="width:300px;" />
            </c:if>
          </td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item">主图说明：</td>
          <td><html-el:text property="img_desc" styleId="img_desc" style="width:380px;" styleClass="webinput" maxlength="100" /></td>
        </tr>
        <tr>
          <td  colspan="2" align="right"><strong class="fb">信息设置</strong></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item">是否置顶：</td>
          <td><label for="is_top1" style="width:80px;">
            <html-el:radio property="is_top" styleId="is_top1" value="0">否</html-el:radio>
            </label>
            <label for="is_top2" style="width:80px;">
            <html-el:radio property="is_top" styleId="is_top2" value="1">是</html-el:radio>
            </label>
          </td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item">是否显示在首页：</td>
          <td><label for="is_display_index1" style="width:80px;">
            <html-el:radio property="is_display_index" styleId="is_display_index1" value="0">否</html-el:radio>
            </label>
            <label for="is_display_index2" style="width:80px;">
            <html-el:radio property="is_display_index" styleId="is_display_index2" value="1">是</html-el:radio>
            </label>
          </td>
        </tr>
        <tr>
          <td  class="title_item">是否启用外链URL：</td>
          <td><label for="is_link_out1" style="width:80px;">
            <html-el:radio property="is_link_out" styleClass="is_link_out" styleId="is_link_out1" value="0">否</html-el:radio>
            </label>
            <label for="is_link_out2" style="width:80px;">
            <html-el:radio property="is_link_out" styleClass="is_link_out" styleId="is_link_out2" value="1">是</html-el:radio>
            </label>
            <div id="urlddiv" style="display:${af.map.is_link_out eq 1 ? 'block' : 'none'};">
              <html-el:text property="link_out_addr" size="60" maxlength="255" styleId="link_out_addr" />
            </div></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item">信息状态：</td>
          <td><label for="is_required7" style="width:80px;">
            <html-el:radio property="states" styleId="is_required7" value="0">未发布</html-el:radio>
            </label>
            <label for="is_required8" style="width:80px;">&nbsp;
            <html-el:radio property="states" styleId="is_required8" value="1">已发布</html-el:radio>
            </label>
          </td>
        </tr>
        <c:if var="training" test="${af.map.mod_id eq 860201 or af.map.mod_id eq 860202 
        or af.map.mod_id eq 860203 or af.map.mod_id eq 860204 or af.map.mod_id eq 860205 or af.map.mod_id eq 860206}"></c:if>
        <c:if test="${not training}">
        <!-- 
        <tr>
          <td nowrap="nowrap" class="title_item">投放位置：</td>
          <td><label for="public_place1" style="width:80px;">
            <html-el:radio property="public_place" styleId="public_place1" value="-1">全部</html-el:radio>
            </label>
 			<label for="public_place3" style="width:80px;">
            <html-el:radio property="public_place" styleId="public_place3" value="0">康佳专版</html-el:radio>
            </label> 
            <label for="public_place2" style="width:80px;">
            <html-el:radio property="public_place" styleId="public_place2" value="1">买卖商通</html-el:radio>
            </label>
          </td>
        </tr>
         -->
        <tr>
          <td nowrap="nowrap" class="title_item" align="left">发布范围：</td>
          <td><c:if test="${af.map.user_type gt 0}">
              <c:set var="disabled" value="true"></c:set>
            </c:if>
            <c:if test="${af.map.user_type eq 0}">
              <c:set var="disabled" value="false"></c:set>
            </c:if>
            <html-el:select property="public_type" styleId="public_type" disabled="${disabled}" >
              <html-el:option value="1">选择对象发布</html-el:option>
              <html-el:option value="0">对所有网点</html-el:option>
              <html-el:option value="4">选择分公司</html-el:option>
              <html-el:option value="5">选择人员角色</html-el:option>
            </html-el:select>
            &nbsp;<span style="color:red">*</span> </td>
        </tr>        
       <tr id="select_1" <c:if test="${af.map.user_type gt 0 or af.map.public_type eq 0}">style="display: none;"</c:if>>
        <td nowrap="nowrap" class="title_item" align="left">选择发布地区：</td>
          <td align="left"><html-el:select property="province" styleId="province">
              <html-el:option value="">-请选择省/直辖市/自治区-</html-el:option>
              <html-el:optionsCollection label="p_name" value="p_index"  name="baseProvince1List" />
            </html-el:select>
            <br/>
               <%@ include file="/commons/pages/areamovediv.jsp"%>
            <br/>
          </td>
        </tr>
        <tr id="select_2" <c:if test="${af.map.public_type eq 0}">style="display: none;"</c:if>>
        <td nowrap="nowrap" class="title_item" align="left">选择发布网点：</td>
          <td>
          <div style="padding:5px;">
          	<html-el:radio property="select_type" styleId="select_type0" value="0" /> <label for="select_type0">按具体网点发布</label>&nbsp;&nbsp;
          	<html-el:radio property="select_type" styleId="select_type1" value="1" /> <label for="select_type1">按网点类别发布</label>
          </div>
          <div id="type0">
            <html-el:text property="shop_name" styleId="shop_name" style="width:250px;"  maxlength="100" readonly="true" />
            &nbsp;
            <input id="gsBTN" type='button' class="but6" value="" onclick="getShopInfo();"/>
            <html-el:hidden property="shop_id" styleId="shop_id" />
            <c:if test="${af.map.user_type eq 0}">
            &nbsp;注:请先选择区域(省或地区),再选择网点，如不选网点则默认为所选区域下所有网点. </c:if>
          </div>
          <div id="type1">
            <html-el:text property="peShopCategoryName" styleId="peShopCategoryName" style="width:250px;"  maxlength="100" readonly="true" />
            &nbsp;
            <input type='button' value='' class="but6" onclick="getPeShopCategoryInfo();"/>
            <html-el:hidden property="peShopCategoryId" styleId="peShopCategoryId" />
          </div>
          </td>
        </tr>
        </c:if>
        <tr id="select_4">
         	<td nowrap="nowrap" class="title_item" align="left">选择分公司：</td>
         	<td>
	         	<html-el:text property="dept_names" styleId="dept_names" style="width:250px;"  maxlength="100" readonly="true" />
	         	&nbsp;
	            <input id="dept_btn" type="button" class="but6" value="" onclick="getDeptInfo();" />
	            <html-el:hidden property="dept_ids" styleId="dept_ids" />
            </td>
        </tr>
        <tr id="select_5">
         	<td nowrap="nowrap" class="title_item" align="left">选择角色：</td>
         	<td>
	         	<html-el:text property="role_names" styleId="role_names" style="width:250px;"  maxlength="100" readonly="true" />
	         	&nbsp;
	            <input id="role_btn" type="button" class="but6" value="" onclick="getRoleInfo();" />
	            <html-el:hidden property="role_ids" styleId="role_ids" />
            </td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item">是否公开：</td>
          <td><label for="msg_info_type1" style="width:80px;">
            <html-el:radio property="msg_info_type" styleId="msg_info_type1" value="0">公开</html-el:radio>
            </label>
            <label for="msg_info_type2" style="width:80px;">
            <html-el:radio property="msg_info_type" styleId="msg_info_type2" value="1">非公开</html-el:radio>
            </label>
          </td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item">是否推送：</td>
          <td><label for="is_sent1" style="width:80px;">
            <html-el:radio property="is_sent" styleId="is_sent1" value="0">是</html-el:radio>
            </label>
            <label for="is_sent2" style="width:80px;">
            <html-el:radio property="is_sent" styleId="is_sent2" value="1">否</html-el:radio>
            </label>
          </td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item">发布时间：</td>
          <td><fmt:formatDate value="${af.map.pub_date}" pattern="yyyy-MM-dd" var="_pub_date"/>
            <html-el:text property="pub_date" value="${_pub_date}" size="10" maxlength="20" readonly="true" onclick="new Calendar(2000, 2030, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" /></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item">排序值：</td>
          <td><html-el:text property="order_value" styleId="order_value" style="width:40px;" maxlength="4" size="4" styleClass="webinput" />
           取值范围：0 - 9999， 值越大，显示越靠前。&nbsp;&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>
          <c:if test="${training}">
          	<input type="hidden" value="-1" name="public_place" />
          	<input type="hidden" value="0" name="select_type" />
          	<input type="hidden" value="0" name="public_type" />
          	<input type="hidden" value="0" name="is_sent" />
          	<input type="hidden" value="0" name="msg_info_type" />
          </c:if>
            <input class="but4" type="button" name="Submit4" value="提交" id="btn_submit" />
            <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();" />
          </td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="clear"></div>
</div>
<%@ include file="/commons/pages/areamove.jsp"%>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
  var acceptUploadFileExts = "7z, aiff, asf, avi, bmp, csv, doc, fla, flv, gif, gz, gzip, jpeg, jpg, mid, mov, mp3, mp4, mpc, mpeg, mpg, ods, odt, pdf, png, ppt, pxd, qt, ram, rar, rm, rmi, rmvb, rtf, sdc, sitd, swf, sxc, sxw, tar, tgz, tif, tiff, txt, vsd, wav, wma, wmv, xls, xml, zip, docx, xlsx, pptx";

  $("#title").attr("dataType", "Require").attr("msg", "请填写${type_name}标题！");
  $("#key_words").attr("dataType", "Require").attr("msg", "请填写关键字！");
  $("#image_path" ).attr("dataType", "Filter" ).attr("msg", "图片的格式必须是(bmp,gif,jpeg,jpg)！").attr("require", "false").attr("accept", "bmp, gif, jpeg, jpg");
  $("#order_value" ).attr("focus",setOnlyNum);
  $("#file_show" ).attr("dataType", "Filter" ).attr("msg", "您上传的文件格式不受支持！").attr("require", "false").attr("accept", acceptUploadFileExts);
  $("#file_hidden" ).attr("dataType", "Filter" ).attr("msg", "您上传的文件格式不受支持！").attr("require", "false").attr("accept", acceptUploadFileExts);
  $("#shop_name"	 ).attr("dataType", "Require").attr("msg", "请选择发布的具体网点！");
  $("#peShopCategoryName").attr("dataType", "Require").attr("msg", "请选择发布的网点类别！");
  $("#article_type_id").attr("dataType", "Require").attr("msg", "请选择${type_name}类别！");
  $("#summary" ).attr({"dataType":"Limit","max":"250","msg":"摘要内容的字数应少于250个字！"});
  
  $("#province").change(function(){
		var province = $("#province").val();
		$.ajax({
				type: "POST",
				url: "CsAjax.do",
				data: "method=" + "getBaseProvinceList" + "&p_index=" + $("#province").val()+"&many_p_index=${af.map.many_p_index}",
				dataType: "json",
				error: function(request, settings) {},
				success: function(Datas) {
					createSelectareas(Datas,"0");
				}
		});
	});
	$("#province").change();
	
	if($("#dept_ids").val().length == 0){
		$("#select_4").hide();
	}
	if($("#role_ids").val().length == 0){
		$("#select_5").hide();
	}
	<c:if test="${not training}">
	$("#public_type").change(function(){
		if(this.value == 1){
			$("#select_1, #select_2").show();
			$("#select_4, #select_5").hide();
		} else if(this.value == 4) {
			$("#select_4").show();
			$("#select_1, #select_2,#select_5").hide();
			$("#select_type").val(0);
		} else if(this.value == 5) {
			$("#select_5").show();
			$("#select_1, #select_2,#select_4").hide();
			$("#select_type").val(0);
		} else if(this.value == 0){
			$("#select_0, #select_1,#select_4, #select_5").hide();
		}
	});
	
	$("#select_type0").click(function(){$("#type0").show();$("#type1").hide();});
	$("#select_type1").click(function(){$("#type1").show();$("#type0").hide();});
	
	$("#${af.map.select_type_id}").click();
	</c:if>
	

	// 提交
	$("#btn_submit").click(function(){
		<c:if test="${not training}">
		var area = document.getElementById("areaList1");
	    var ids_names = area.getElementsByTagName("input");
       <c:if test="${af.map.user_type eq 0}">
		$("#shop_name,#peShopCategoryName").removeAttr("dataType");
		if($("#public_type").val() == 1 && $("#areaList1 tr").length == 0){
			alert('请选择区域');
			return false;
		}
		
		if($("#public_type").val()=='1' &&$('#province').val()=='' &&ids_names.length==0 && $('#shop_id').val()==''){ //非所有网点情况下，省、地区、网点 必选其一
			alert('请选择发布区域(省、地区或网点).');
			return false;
		}
		
      </c:if>

    	if($("#public_type").val() == 1){
			if(document.getElementById("select_type0").checked) {
				$("#peShopCategoryName").removeAttr("dataType");
				$("#shop_name").attr("dataType", "Require");
			} else {
				$("#shop_name").removeAttr("dataType");
				$("#peShopCategoryName").attr("dataType", "Require");
			}
    	}
       </c:if>
       
       //添加外链地址的表单验证
       var uri = document.getElementById("is_link_out2");
       if(uri.checked) {
		$("#link_out_addr").attr("dataType", "Url").attr("msg", "请按有效格式填写URL，如http://www.baidu.com").attr("require", "true");
        }
       
       //添加内容验证
       this.form.content.value = FCKeditorAPI.GetInstance("content").GetXHTML();
       if (this.form.content.value == '') {
	       $("#content").attr("dataType", "Require").attr("msg", "${type_name}内容必须填写！");
	   }
       
	   if(Validator.Validate(this.form, 2)){
			$("#btn_submit").attr("value", "提交").attr("disabled", "true");
			$("#btn_back").attr("disabled", "true");
			this.form.submit();
	   }
	});
	
	$(".is_link_out").click(function(){
		
		var is_link_out = $(this).val();
		
		switch(is_link_out){
		case '1' :
			$("#urlddiv").show();
			// $("pswddiv").show();
			break;
		case '0' :
			$("#urlddiv").hide();
			break;
		}
	});

 $("a[id^='att_del_']").click(function() {
  var a = this;
   if(!confirm('确实要删除此附件？')) return;
    $.post("KonkaPeArticleInfo.do", { method : "deleteFile", id : $(this).attr("id").replace(/att_del_/g, '')}, function(success) {
   if (success){alert("恭喜您，删除附件成功!");$(a).parent().remove();} else alert("很抱歉，删除附件出错!"); 
  });
 });

$("#imgAddTr").click(function (){
	$("#divFileHidden").clone().find("#file_hidden").attr("name", "file_" + new Date().getTime()).end().appendTo($("#divFile")).show();
    $("img[id='imgDelTr']").each(function(){
		$(this).click(function (){
			$(this).parent().remove();
		});
	});
});

});

////////////////////////区域间移动-----start/////////////////////
var areaMove = new AreaMove("areaMove","areaList0","areaList1");
areaMove.show_key = ["p_name"];
areaMove.input_id_key = ["p_index"];
areaMove.input_name_key = ["id_name","id_name"];
areaMove.input_value_key = ["p_index","p_name"];
areaMove.setup();
// JSON 方式添加
function createSelectareas(Datas,flg){       	            
    if (Datas != null && Datas.length > 0) {
   	    areaMove.removeAreaElements(flg);
   	    for(var i = 0; i < Datas.length; i++) {
   			var jsonData = {p_index:Datas[i][1],p_name:Datas[i][0]};
   			areaMove.createAreaElement(jsonData,0);  
   	    }
    }
}
// ${type_name}修改时的发布区域初始化
var areas_ids = '${af.map.areas_ids}';
var areas_names = '${af.map.areas_names}';
if(areas_ids != "" && areas_names != ""){
	var arr_ids = areas_ids.split(",");
	var arr_names = areas_names.split(",");	
	for(var i = 0;i< arr_ids.length;i++){
		var jsonData = {p_index:arr_ids[i],p_name:arr_names[i]};
		areaMove.createAreaElement(jsonData,1);
	}
}
////////////////////////区域间移动-----end/////////////////////

function getPeShopCategoryInfo(){
	var returnValue = window.showModalDialog("PeShopCategory.do?method=listForLevel&selects=" + $("#peShopCategoryId").val() +  "&azaz=" + Math.random(),window,"dialogWidth:600px;status:no;dialogHeight:500px");
	if (returnValue != null) {
		$("#peShopCategoryId").val(returnValue.ids);
		$("#peShopCategoryName").val(returnValue.names);
	};	
}

//选网点
function getShopInfo() {
	var area = document.getElementById("areaList1");
    var ids_names =  area.getElementsByTagName("input");
    var ids = new Array();
    for(var i = 0;i< ids_names.length;i++){
       var arr = ids_names[i].value.split(",");
       ids[ids.length] = arr[0];
    }
	var areas_ids = ids.join(",");
	<c:if test="${af.map.user_type eq 0}">
	if(areas_ids == ''){
		alert('请先选择区域');
		return false;
	}
	</c:if>
	
	var returnValue = window.showModalDialog("SelectEntyShopByArea.do?method=toWindowFramePage&receive_user_type=" + $("#receive_user_type").val() + "&selectype=mutil&selects=" + $("#shop_id").val() + "&province=" + $("#province").val()+ "&areas_ids=" + areas_ids + "&azaz=" + Math.random(),window,"dialogWidth:610px;status:no;dialogHeight:438px");

	if (returnValue != null) {
		$("#shop_id").val(returnValue.ids);
		$("#shop_name").val(returnValue.names);
	};
};

//选分公司
function getDeptInfo(){
	var returnValue = window.showModalDialog("KonkaDept.do?method=listForDept&selects=" + $("#dept_ids").val()+ "&azaz=" + Math.random(),window,"dialogWidth:600px;status:no;dialogHeight:500px");
	if (returnValue != null) {
		$("#dept_ids").val(returnValue.ids);
		$("#dept_names").val(returnValue.names);
	};	
}

//选角色
function getRoleInfo(){
	var returnValue = window.showModalDialog("PeRoleInfo.do?method=listForRole&selects=" + $("#role_ids").val() +  "&azaz=" + Math.random(),window,"dialogWidth:600px;status:no;dialogHeight:500px");
	if (returnValue != null) {
		$("#role_ids").val(returnValue.ids);
		$("#role_names").val(returnValue.names);
	};	
}

function setOnlyNum() {
	$(this).css("ime-mode", "disabled");
	$(this).attr("t_value", "");
	$(this).attr("o_value", "");
	$(this).bind("dragenter",function(){
		return false;
	});
	$(this).keypress(function (){
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).keyup(function (){
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).blur(function (){
		if(!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value;}
		if(this.value.length == 0) this.value = "0";
	});
	//this.text.selected;
}

function setLength(){
	$(this).keypress(function (){
		if(this.value.length > this.len){
			this.value = this.value.substring(0, this.len);
		}
		$("#count").text(this.len - this.value.length);
	}).keyup(function (){
		if(this.value.length > this.len){
			this.value = this.value.substring(0, this.len);
		}
		$("#count").text(this.len - this.value.length);
	}).blur(function (){
		if(this.value.length > this.len){
			this.value = this.value.substring(0, this.len);
		}
		$("#count").text(this.len - this.value.length);
	});
}

function hideImagePath(id){
	var image_path = document.getElementById(id);
	if(image_path.style.display == "none") {
  		image_path.style.display = "";
		image_path.setAttribute("dataType", "Require");
		image_path.setAttribute("msg", "请选择需要上传的图片");
		tip.style.display = "";
	} else {
		image_path.style.display = "none";
		image_path.removeAttribute("dataType", "Require");
		image_path.removeAttribute("msg", "请选择需要上传的图片");		
		tip.style.display = "none";
	}
} 
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
