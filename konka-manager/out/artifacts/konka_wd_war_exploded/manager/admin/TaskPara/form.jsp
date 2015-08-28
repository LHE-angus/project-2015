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
</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="500" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" alt="" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
    <html-el:form action="/admin/TaskPara" enctype="multipart/form-data">
      <html-el:hidden property="id" styleId="id" />
      <html-el:hidden property="task_id" styleId="task_id" />
      <html-el:hidden property="year" styleId="year" />
      <html-el:hidden property="type" styleId="type" />
      <html-el:hidden property="task_name" styleId="task_name" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="method" styleId="method" value="save" />
      <html-el:hidden property="queryString" styleId="queryString" />
      <table width="100%" border="0"  cellpadding="0" cellspacing="0" class="rtable3">
		<tr>
          <td width="20%" nowrap="nowrap" class="title_item" align="right" >年度：</td>
          <td align="left"> <c:out value="${af.map.year}" /></td>
        </tr>
		<tr>
          <td width="20%" nowrap="nowrap" class="title_item" align="right" >名称：</td>
          <td align="left"> <c:out value="${af.map.task_name}" /></td>
        </tr>
        <tr>
          <td width="20%" nowrap="nowrap" class="title_item" align="right" >1：</td>
          <td align="left"><html-el:text property="column_1" styleId="column_1" style="width:380px;" styleClass="webinput" maxlength="6" /></td>
        </tr>
        <tr>
          <td width="20%" nowrap="nowrap" class="title_item" align="right" >2：</td>
          <td align="left"><html-el:text property="column_2" styleId="column_2" style="width:380px;" styleClass="webinput" maxlength="6" /></td>
        </tr>
        <tr>
          <td width="20%" nowrap="nowrap" class="title_item" align="right" >3：</td>
          <td align="left"><html-el:text property="column_3" styleId="column_3" style="width:380px;" styleClass="webinput" maxlength="6" /></td>
        </tr>
        <tr>
          <td width="20%" nowrap="nowrap" class="title_item" align="right" >4：</td>
          <td align="left"><html-el:text property="column_4" styleId="column_4" style="width:380px;" styleClass="webinput" maxlength="6"  /></td>
        </tr>
        <tr>
          <td width="20%" nowrap="nowrap" class="title_item" align="right" >5：</td>
          <td align="left"><html-el:text property="column_5" styleId="column_5" style="width:380px;" styleClass="webinput" maxlength="6"  /></td>
        </tr>
        <tr>
          <td width="20%" nowrap="nowrap" class="title_item" align="right" >6：</td>
          <td align="left"><html-el:text property="column_6" styleId="column_6" style="width:380px;" styleClass="webinput" maxlength="6"  /></td>
        </tr>
        <tr>
          <td width="20%" nowrap="nowrap" class="title_item" align="right" >7：</td>
          <td align="left"><html-el:text property="column_7" styleId="column_7" style="width:380px;" styleClass="webinput" maxlength="6" /></td>
        </tr>
        <tr>
          <td width="20%" nowrap="nowrap" class="title_item" align="right" >8：</td>
          <td align="left"><html-el:text property="column_8" styleId="column_8" style="width:380px;" styleClass="webinput" maxlength="6"  /></td>
        </tr>
        <tr>
          <td width="20%" nowrap="nowrap" class="title_item" align="right" >9：</td>
          <td align="left"><html-el:text property="column_9" styleId="column_9" style="width:380px;" styleClass="webinput" maxlength="6"  /></td>
        </tr>
        <tr>
          <td width="20%" nowrap="nowrap" class="title_item" align="right" >10：</td>
          <td align="left"><html-el:text property="column_10" styleId="column_10" style="width:380px;" styleClass="webinput" maxlength="6"  /></td>
        </tr>
        <tr>
          <td width="20%" nowrap="nowrap" class="title_item" align="right" >11：</td>
          <td align="left"><html-el:text property="column_11" styleId="column_11" style="width:380px;" styleClass="webinput" maxlength="6"  /></td>
        </tr>
        <tr>
          <td width="20%" nowrap="nowrap" class="title_item" align="right" >12：</td>
          <td align="left"><html-el:text property="column_12" styleId="column_12" style="width:380px;" styleClass="webinput" maxlength="6"  /></td>
        </tr>
         <tr>
          <td width="20%" nowrap="nowrap" class="title_item" align="right" >年度任务：</td>
          <td align="left"><html-el:text property="year_task" styleId="year_task" style="width:380px;" styleClass="webinput" maxlength="6"  /></td>
        </tr>
         <tr>
          <td width="20%" nowrap="nowrap" class="title_item" align="right" >年度系数：</td>
          <td align="left"><html-el:text property="year_para" styleId="year_para" style="width:380px;" styleClass="webinput" maxlength="6"  /></td>
        </tr>
        <tr>
          <td width="20%" nowrap="nowrap" class="title_item" align="right" >&nbsp;</td>
          <td><label>
            <input class="but4" type="button" name="Submit4" value="提交" id="send"/>
            <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();return false;" />
            </label>
          </td>
        </tr>
      </table>
    </html-el:form>
	</div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#column_1,#column_2,#column_3,#column_4,#column_5,#column_6,#column_7,#column_8,#column_9,#column_10,#column_11,#column_12,#year_task,#year_para").focus(setOnlyNum);
	
	 $("#send").click(function(){
			var isSubmit = Validator.Validate(this.form, 3);
			if (isSubmit) {
				  
				$(":button").attr("disabled", "true");
				$(":reset").attr("disabled", "true");
				this.form.submit();
			}
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
		//if(this.value.length == 0) this.value = "0";
	});
	//this.text.selected;
}

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>