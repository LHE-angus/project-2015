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
    <html-el:form action="/zmd/KonkaXxZmdPeArticleInfo" enctype="multipart/form-data">
      <html-el:hidden property="id" styleId="id" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="queryString" styleId="queryString" />
      <c:if test="${not empty type_id}">
        <input type="hidden" name="article_type_id" value="${type_id}" />
      </c:if>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
        <tr>
          <td colspan="2" class="item_class" align="right" ><strong class="fb">基本信息</strong></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item">标题：</td>
          <td><html-el:text property="title" styleId="title" style="width:380px;" styleClass="webinput" maxlength="60" />
            &nbsp;<span style="color:red">*</span></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item">来源：</td>
          <td><html-el:text property="source" styleId="source" style="width:380px;" styleClass="webinput" maxlength="60" /></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item">发文人：</td>
          <td><html-el:text property="author" styleId="author" style="width:380px;" styleClass="webinput" maxlength="60" /></td>
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
          <td nowrap="nowrap" class="title_item">上传主图：</td>
          <td width="88%"><c:if test="${not empty af.map.img_path}"><a href="${ctx}/${af.map.img_path}" target="_blank"><img src="${ctx}/${fn:substringBefore(af.map.img_path,'.')}_240.jpg" style="width:150px;height:100px;border:1px solid #ccc;" /></a>
              <input type="checkbox" name="checkbox" id="checkbox_1" value="checkbox" onclick="hideImagePath('img_path');" />
              <label for="checkbox_1"> 重新上传图片</label>
              <br/>
              <html-el:file property="img_path" style="display:none;width:500px;" styleId="img_path" />
            </c:if>
            <c:if test="${empty af.map.img_path}">
              <html-el:file property="img_path" style="width:300px;" />
            </c:if></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item">主图说明：</td>
          <td><html-el:text property="img_desc" styleId="img_desc" style="width:380px;" styleClass="webinput" maxlength="100" /></td>
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
          <td nowrap="nowrap" class="title_item">发布时间：</td>
          <td><fmt:formatDate value="${af.map.pub_date}" pattern="yyyy-MM-dd" var="_pub_date"/>
            <html-el:text property="pub_date" value="${_pub_date}" size="10" maxlength="20" readonly="true" onclick="new Calendar(2000, 2030, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" /></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item">信息状态：</td>
          <td><label for="is_required7" style="width:80px;">
              <html-el:radio property="states" styleId="is_required7" value="0">未发布</html-el:radio>
            </label>
            <label for="is_required8" style="width:80px;">&nbsp;
              <html-el:radio property="states" styleId="is_required8" value="1">已发布</html-el:radio>
            </label></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item">排序值：</td>
          <td><html-el:text property="order_value" styleId="order_value" style="width:40px;" maxlength="4" size="4" styleClass="webinput" />
            取值范围：0 - 9999， 值越大，显示越靠前。&nbsp;&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td><input class="but4" type="button" name="Submit4" value="提交" id="btn_submit" />
            <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="clear" style="margin-bottom: 100px;"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
  var acceptUploadFileExts = "7z, aiff, asf, avi, bmp, csv, doc, fla, flv, gif, gz, gzip, jpeg, jpg, mid, mov, mp3, mp4, mpc, mpeg, mpg, ods, odt, pdf, png, ppt, pxd, qt, ram, rar, rm, rmi, rmvb, rtf, sdc, sitd, swf, sxc, sxw, tar, tgz, tif, tiff, txt, vsd, wav, wma, wmv, xls, xml, zip, docx, xlsx, pptx";

  $("#title").attr("dataType", "Require").attr("msg", "请填写资讯标题！");
  $("#image_path" ).attr("dataType", "Filter" ).attr("msg", "图片的格式必须是(bmp,gif,jpeg,jpg)！").attr("require", "false").attr("accept", "bmp, gif, jpeg, jpg");
  $("#order_value" ).attr("focus",setOnlyNum);
  $("#file_show" ).attr("dataType", "Filter" ).attr("msg", "您上传的文件格式不受支持！").attr("require", "false").attr("accept", acceptUploadFileExts);
  $("#file_hidden" ).attr("dataType", "Filter" ).attr("msg", "您上传的文件格式不受支持！").attr("require", "false").attr("accept", acceptUploadFileExts);
  $("#summary" ).attr({"dataType":"Limit","max":"250","msg":"摘要内容的字数应少于250个字！"});
  
	// 提交
	$("#btn_submit").click(function(){
	
       //添加内容验证
       this.form.content.value = FCKeditorAPI.GetInstance("content").GetXHTML();
     
       if((this.form.content.value).length == 0){
    	   $("#content").attr("dataType", "Require").attr("msg", "资讯内容必须填写！");
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
			$("#link_out_addr").attr("dataType", "Url").attr("msg", "请按有效格式填写URL，如http://www.baidu.com").attr("require", "true");
			break;
		case '0' :
			$("#urlddiv").hide();
			$("#link_out_addr").removeAttr("dataType");
			break;
		}
		  resizeFrameHeight();
	});

 $("a[id^='att_del_']").click(function() {
  var a = this;
   if(!confirm('确实要删除此附件？')) return;
    $.post("KonkaXxZmdPeArticleInfo.do", { method : "deleteFile", id : $(this).attr("id").replace(/att_del_/g, '')}, function(success) {
   if (success){alert("恭喜您，删除附件成功!");$(a).parent().remove();} else alert("很抱歉，删除附件出错!"); 
  });

    resizeFrameHeight();
 });

$("#imgAddTr").click(function (){
	$("#divFileHidden").clone().find("#file_hidden").attr("name", "file_" + new Date().getTime()).end().appendTo($("#divFile")).show();
    $("img[id='imgDelTr']").each(function(){
		$(this).click(function (){
			$(this).parent().remove();
		});
	});

    resizeFrameHeight();
});

});

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

function resizeFrameHeight(offset, min_height) {
	// frame id can be found in page /konka-wd/WebContent/manager/admin/Frames2/indexFrame.jsp, and search 'iframe' to get.
	$("#mainFrame", window.parent.document).height(Math.max((min_height || 0), $(document).find("body").height(), $(document).children().height()) + (offset || 0));
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
