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
<style type="text/css">
<!--
.red {
	color:#F00;
}
.bla {
	color:#000;
	font-size:12px;
	font-weight:bold;
}
.note {
	color:#777;
	margin-left:10px;
}
span.required {
	color:#FF0000;
	font-weight:normal;
	background-color:#F4FAFF;
}
-->
</style>
</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td nowrap="nowrap">当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
  </div>
  <!--
  <div class="rtabcont1">
    <html-el:form action="/admin/KonkaShopVisit.do">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="tree_param" value="${tree_param}" />
      <html-el:hidden property="shop_name_like" value="${af.map.shop_name_like}" />
      <html-el:hidden property="shop_develop_status" value="${af.map.shop_develop_status}" />
      <html-el:hidden property="shop_visit_status" value="${af.map.shop_visit_status}" />
      <input class="but5" type="submit" name="Submit5" value="上一级" id="btn_back" />
    </html-el:form>
  </div>
  -->
  <div class="rtabcont1">
   <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();return false;" />
  </div>
  <div class="rtabcont1">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tbody>
          <tr class="tabtt1">
            <td width="30" nowrap="nowrap" ><font class="blue">序号</font></td>
            <td nowrap="nowrap" ><font class="blue">网点名</font></td>
            <td nowrap="nowrap" ><font class="blue">拜访日期</font></td>
            <td ><font class="blue">拜访状态</font></td>
            <td ><font class="blue">拜访计数</font></td>
            <td width="70" align="center"><font class="blue">操作</font></td>
          </tr>
          <c:forEach var="cur" items="${visitList}" varStatus="vs">
            <tr>
              <td height="28"  align="center">${(af.map.pager.currentPage - 1)*af.map.pager.pageSize + vs.count}</td>
              <td align="left" valign="middle">${cur.shop_name}</td>
              <td align="left"><fmt:formatDate value="${cur.visit_date }" pattern="yyyy-MM-dd"></fmt:formatDate></td>
              <td align="left">
                <c:choose>
                   <c:when test="${cur.visit_status eq 0}"> <span style="color: green;">未拜访</span> </c:when>
                   <c:when test="${cur.visit_status eq 1}"> <span style="color: green;">拜访中</span> </c:when>
                   <c:when test="${cur.visit_status eq 2}"> <span style="color: #F00;">拜访完成</span> </c:when>
                </c:choose>
              </td>
              <td align="left">第${cur.visit_count}次</td>
              <td align="center" nowrap="nowrap">
                  <span style="cursor:pointer;" class="fblue"  onclick="editVistRecord('${cur.id}')">修改</span> 
              </td>
            </tr>
            <c:if test="${vs.last eq true}">
              <c:set var="i" value="${vs.count}" />
            </c:if>
          </c:forEach>
          <c:forEach begin="${i}" end="${af.map.pager.pageSize - 1}">
            <tr align="center">
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaDLSVisit.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
            <script type="text/javascript">
	            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
	            pager.addHiddenInputs("method", "visit");
	            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
	            pager.addHiddenInputs("shop_id", "${af.map.shop_id}");
	            pager.addHiddenInputs("tree_param", "${tree_param}");
	            pager.addHiddenInputs("shop_name_like", "${af.map.shop_name_like}");
	            pager.addHiddenInputs("shop_develop_status", "${af.map.shop_develop_status}");
	            pager.addHiddenInputs("shop_visit_status", "${af.map.shop_visit_status}");
	            document.write(pager.toString());
	      </script></td>
        </tr>
      </table>
    </form>
  </div>
  <div class="rtabcont1">
	<span style="background-color:#ffc;cursor:pointer;" id="add_record" onclick="changeEdit(0);">添加拜访记录</span>
	<c:if test="${visit_count ne 0}">
	  <span style="cursor:pointer;" id="edit_record" onclick="changeEdit(1);">修改拜访记录</span>
	</c:if>
  </div>
  <div class="rtabcont2">
    <html-el:form action="/admin/KonkaDLSVisit.do">
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="tree_param" value="${tree_param}" />
      <html-el:hidden property="shop_id" value="${af.map.shop_id}" />
      <html-el:hidden property="save_visit_count" styleId="save_visit_count" value="${visit_count}" />
      <html-el:hidden property="visit_id" styleId="visit_id" value=""/>
      <html-el:hidden property="keyword" value="${af.map.keyword}" />
      <html-el:hidden property="ywy_name_like" value="${af.map.ywy_name_like}" />
      <html-el:hidden property="code_like" value="${af.map.code_like}" />
      
      <div align="left">
        <table width="100%" border="0" cellpadding="0" cellspacing="1" class="datagrid">
          <tr>
            <td nowrap="nowrap" height="28" class="title_item" width="100">网点名：</td>
            <td align="left">
            <html-el:text property="shop_name" styleId="shop_name" value="${shop_name}" size="40" maxlength="80" readonly="true"></html-el:text>            
            </td>
          </tr>
          <tr>
            <td nowrap="nowrap"  height="28" class="title_item" width="100">拜访计数：</td>
            <td align="left"><select name="visit_count" id="visit_count" class="bd">
                    <option value="${visit_count + 1}">第${visit_count + 1}次</option>
              </select>
            </td>
          </tr>
          <tr>
            <td nowrap="nowrap"  height="28" class="title_item" width="100">拜访状态：</td>
            <td align="left"><select name="visit_status" id="visit_status" class="bd">
                    <option value="">--请选择--</option>
                    <option value="1">拜访中</option>
                    <option value="2">拜访完了</option>
              </select>
            </td>
          </tr>
          <tr>
            <td nowrap="nowrap"  height="28" class="title_item" width="100">拜访记录：</td>
            <td align="left"><html-el:textarea property="visit_note" styleId="visit_note" cols="50" rows="5"/>
            <div style="display: none; font-size: 12px; color: #F00;">注：不可超过1000个中文字符！</div></td>
          </tr>

          <tr>
             <td nowrap="nowrap"  height="28" class="title_item" width="100">&nbsp;</td>
             <td height="40" ><input class="but4" type="button" name="Submit4" value=" 保存 " id="btn_submit" />
             </td>
          </tr>
        </table>
      </div>
    </html-el:form>
  </div>
  <div class="rtabcont3"></div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script> 
<script type="text/javascript">//<![CDATA[
	$(document).ready(function(){
		$("#visit_status").attr("dataType", "Require").attr("msg", "请选择拜访状态");
		$("#visit_note" ).attr("dataType", "Require").attr("msg", "请填写拜访记录");
		String.prototype.trim = function(){
	        return this.replace(/(^\s*)|(\s*$)/g, "");
	    }

		$("#btn_submit").click(function(){
			if(Validator.Validate(this.form, 3)){
	            $("#btn_submit").attr("value", "提交...").attr("disabled", "true");
				this.form.submit();
			}
		});
	});

	function ReplaceAll(str, sptr, sptr1)
	{
	   while (str.indexOf(sptr) >= 0)
	  {
	   str = str.replace(sptr, sptr1);
	  }
	  return str;
	}
		
   function editVistRecord(visit_id){
		  $.ajax({
				type: "POST",
				url: "KonkaDLSVisit.do",
				data: "method=" + "ajaxSelectVisitById" + "&visit_id=" +visit_id,
				dataType: "json",
				error: function(request, settings) {},
				success: function(jsonData) {
					if(jsonData && jsonData.ajax_status == "1"){
						$("#visit_status").val(jsonData.visit_status);
												
						var note = jsonData.visit_note;
						note = ReplaceAll(note,"#t#","\t");
                        note = ReplaceAll(note,"#r#","\r");
                        note = ReplaceAll(note,"#n#","\n");
                        
						$("#visit_note").val(note);

						$("#visit_id").val(jsonData.id);

					   	$("#visit_count").empty();
						var opt = new Option("第"+jsonData.visit_count+"次",jsonData.visit_count);
						$("#visit_count").get(0).options.add(opt);

			        	add_record.style.backgroundColor = "";
			    		edit_record.style.backgroundColor = "#ffc";

			    		op = 1;
					}
			    }
		 });
   }

   var add_record = document.getElementById("add_record");
   var edit_record = document.getElementById("edit_record");
   var op = 0;
   function changeEdit(flg){
	    
        if(flg == 0){
    	    add_record.style.backgroundColor = "#ffc";
    	    edit_record.style.backgroundColor = "";
    	    $("#visit_id").val("");
			$("#visit_status").val("");
			$("#visit_note").val("");

		   	$("#visit_count").empty();
			var opt = new Option("第"+(parseInt($("#save_visit_count").val())+1)+"次",parseInt($("#save_visit_count").val())+1);
			$("#visit_count").get(0).options.add(opt);
			op = 0;
        }else{
            if(op == 0){
               alert("请点击列表中网点的修改");
            }else{
            	add_record.style.backgroundColor = "";
        		edit_record.style.backgroundColor = "#ffc";
            }

        }
   }
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>