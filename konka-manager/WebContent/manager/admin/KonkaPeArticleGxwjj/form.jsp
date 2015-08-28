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
    <html-el:form action="/admin/KonkaPeArticleGxwjj" enctype="multipart/form-data">
      <html-el:hidden property="id" styleId="id" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="queryString" styleId="queryString" />
      <c:if test="${not empty type_id}">
        <input type="hidden" name="article_type_id" value="${type_id}" />
      </c:if>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
        <tr>
          <td nowrap="nowrap" class="title_item">文件标题：</td>
          <td><html-el:text property="title" styleId="title" style="width:380px;" styleClass="webinput" maxlength="60" />
            &nbsp;<span style="color:red">*</span></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item">文件描述内容：</td>
          <td><FCK:editor instanceName="content">
              <jsp:attribute name="value">${af.map.content}</jsp:attribute>
            </FCK:editor>
            <div class="note">1、此处上传的图片不会自动缩放，请用相关做图软件缩放成您想要的大小；</div>
            <div class="note">2、点击最后一排倒数第三个按钮可实现全屏编辑。</div></td>
        </tr>
        <tr id="trFile">
          <td nowrap="nowrap" class="title_item">上传文件：</td>
          <td><div id="divFileHidden" style="display: none;">
              <input name="file_hidden" type="file" id="file_hidden" style="width: 500px;" />
              <img src="${ctx}/images/x.gif" style="vertical-align:middle; cursor: pointer;" id="imgDelTr" title="删除"/></div>
            <div id="divFile">
              <input name="file_show" type="file" id="file_show" style="width: 500px;" />
              <img src="${ctx }/images/+.gif" style="vertical-align:middle; cursor: pointer;" id="imgAddTr" title="再添加一个" /></div></td>
        </tr>
        <c:if test="${not empty attachmentList}">
          <tr>
            <td height="28" class="title_item">已上传的文件：</td>
            <td><ol>
                <c:forEach var="cur" items="${attachmentList}" varStatus="vs">
                  <li><a href="${ctx}/manager/admin/Download.do?method=downloadFile&save_name=${cur.save_name}">${cur.file_name}</a>&nbsp;&nbsp;&nbsp;<a href="#" id="att_del_${cur.id}">删除</a></li>
                </c:forEach>
              </ol></td>
          </tr>
        </c:if>
        <tr>
          <td>&nbsp;</td>
          <td><input class="but4" type="button" name="Submit4" value="提交" id="btn_submit" />
            <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
  var acceptUploadFileExts = "7z, aiff, asf, avi, bmp, csv, doc, fla, flv, gif, gz, gzip, jpeg, jpg, mid, mov, mp3, mp4, mpc, mpeg, mpg, ods, odt, pdf, png, ppt, pxd, qt, ram, rar, rm, rmi, rmvb, rtf, sdc, sitd, swf, sxc, sxw, tar, tgz, tif, tiff, txt, vsd, wav, wma, wmv, xls, xml, zip, docx, xlsx, pptx";

  $("#title").attr("dataType", "Require").attr("msg", "请填写文件标题！");
  $("#image_path" ).attr("dataType", "Filter" ).attr("msg", "图片的格式必须是(bmp,gif,jpeg,jpg)！").attr("require", "false").attr("accept", "bmp, gif, jpeg, jpg");
  $("#file_show" ).attr("dataType", "Filter" ).attr("msg", "您上传的文件格式不受支持！").attr("require", "false").attr("accept", acceptUploadFileExts);
  $("#file_hidden" ).attr("dataType", "Filter" ).attr("msg", "您上传的文件格式不受支持！").attr("require", "false").attr("accept", acceptUploadFileExts);
  
	// 提交
	$("#btn_submit").click(function(){
       
       //添加内容验证
       this.form.content.value = FCKeditorAPI.GetInstance("content").GetXHTML();
     
       if((this.form.content.value).length == 0){
    	   $("#content").attr("dataType", "Require").attr("msg", "文件描述内容必须填写！");
        }
       
	   if(Validator.Validate(this.form, 2)){
			$("#btn_submit").attr("value", "提交").attr("disabled", "true");
			$("#btn_back").attr("disabled", "true");
			this.form.submit();
	   }
	});
	
 $("a[id^='att_del_']").click(function() {
  var a = this;
   if(!confirm('确实要删除此附件？')) return;
    $.post("KonkaGroupPeArticleInfo.do", { method : "deleteFile", id : $(this).attr("id").replace(/att_del_/g, '')}, function(success) {
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

function resizeFrameHeight(offset, min_height) {
	$("#mainFrame", window.parent.document).height(Math.max((min_height || 0), $(document).find("body").height(), $(document).children().height()) + (offset || 0));
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
