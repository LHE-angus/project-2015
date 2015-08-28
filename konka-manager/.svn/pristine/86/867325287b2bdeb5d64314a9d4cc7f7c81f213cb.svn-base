<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><c:if test="${ecUser.user_type eq 1 }">康佳EPP内部优惠购机平台</c:if><c:if test="${ecUser.user_type eq 2 }">康佳网上直销商城</c:if></title>
<link rel=stylesheet type=text/css  href="${ctx}/styles/member/css/global1.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/member/css/epp.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/member/css/member.css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
</head>
<body>
<!-- head start -->
<div class="topbox">
<jsp:include page="/member/__inc/top.jsp" flush="true" />
<jsp:include page="/member/__inc/search.jsp" flush="true" />
</div> 
<jsp:include page="/member/__inc/nav.jsp" flush="true" />
<!-- second start -->
<div class="maincont">
  <jsp:include page="/member/__inc/_mleft.jsp" flush="true" />
  <!--right-->
  <div class="member_right padbot45">
    <div class="position"><a href="${ctx }/member/Index.do">首页</a> &gt; <a href="${ctx }/member/center/Index.do">会员中心</a> &gt; 地址簿</div>
    <div class="membertab3">
      <p style="margin-top:10px;color:#fc7200;font-weight:bold;">新增收货地址</p>
      <html-el:form action="/center/EcUserAddrs">
        <html-el:hidden property="method" styleId="method" value="save" />
        <html-el:hidden property="id"/>
        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="member_form_table1">
          <tr>
            <td align="right">收货人姓名：</td>
            <td><html-el:text styleClass="input_txt" property="rel_name" styleId="rel_name" maxlength="20" style="width:160px;"></html-el:text>
            </td>
          </tr>
          <tr>
            <td align="right">所属地区：</td>
            <td align="left"><select name="province" id="province"  class="input_txt" style="width:160px;" >
                <option value="">-请选择省/直辖市/自治区-</option>
              </select>
              <select name="city" id="city"  class="input_txt" style="width:100px;">
                <option value="">-请选择市-</option>
              </select>
              <select name="country" id="country"  class="input_txt" style="width:100px;">
                <option value="">-请选择县-</option>
              </select>
              <select name="town" id="town"  class="input_txt" style="width:100px;">
                <option value="">-请选择乡/镇-</option>
              </select>
            </td>
          </tr>
          <tr>
            <td align="right">街道地址：</td>
            <td><html-el:text styleClass="input_txt" property="rel_addr" styleId="rel_addr" maxlength="200" style="width:300px;"></html-el:text>
            </td>
          </tr>
          <tr>
            <td align="right">邮政编码：</td>
            <td><html-el:text styleClass="input_txt" property="rel_zip" styleId="rel_zip" maxlength="10" style="width:160px;"></html-el:text>
            </td>
          </tr>
          <tr>
            <td align="right">固定电话：</td>
            <td><html-el:text styleClass="input_txt" property="rel_tel" styleId="rel_tel" maxlength="20" style="width:160px;"></html-el:text>
              区号-电话号码</td>
          </tr>
          <tr>
            <td align="right">手机号码：</td>
            <td><html-el:text styleClass="input_txt" property="rel_phone" styleId="rel_phone" maxlength="20" style="width:160px;"></html-el:text></td>
          </tr>
          <tr>
            <td align="right">设为默认：</td>
            <td><html-el:checkbox property="default_addr" value="1" ></html-el:checkbox>
            </td>
          </tr>
          <tr>
            <td></td>
            <td><input class="inputbtn" type="button" name="Submit4" id="btn_submit" value="保存" /></td>
          </tr>
        </table>
      </html-el:form>
      <p style="margin-top:10px;color:#fc7200;font-weight:bold;">已保存的有效地址</p>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="member_form_table1">
        <tr class="tabtt1">
          <td width="5%" nowrap="nowrap" align="center">序号</td>
          <td width="6%" nowrap="nowrap" align="center">收货人</td>
          <td width="6%" nowrap="nowrap" align="center">所在地区</td>
          <td width="15%" nowrap="nowrap" align="center">街道地址</td>
          <td width="8%" nowrap="nowrap" align="center">邮编</td>
          <td width="8%" nowrap="nowrap" align="center">电话/手机</td>
          <td width="8%" nowrap="nowrap" align="center"></td>
          <td width="10%" nowrap="nowrap" align="center">操作</td>
        </tr>
        <tbody>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr onmouseover="shows('s${vs.count }');" onmouseout="hides('s${vs.count }');">
              <td align="center" nowrap="nowrap">${vs.count }</td>
              <td align="center" nowrap="nowrap"><c:out value="${cur.rel_name }"/></td>
              <td align="center" nowrap="nowrap">${cur.map.full_name }</td>
              <td align="left" nowrap="nowrap"><c:out value="${cur.rel_addr }"/></td>
              <td align="center" nowrap="nowrap"><c:out value="${cur.rel_zip }"/></td>
              <td align="center" nowrap="nowrap"><c:out value="${cur.rel_tel }"/>
                /
                <c:out value="${cur.rel_phone}"/></td>
              <td align="center" nowrap="nowrap"><c:if test="${cur.default_addr eq 1 }" ><font color="blue">默认地址</font></c:if>
                <c:if test="${cur.default_addr ne 1 }" ><a id="s${vs.count }" style="display: none;" href="EcUserAddrs.do?method=defaultaddr&id=${cur.id }"><font color="red">设为默认</font></a></span></c:if>
              </td>
              <td align="center" nowrap="nowrap"><a href="?id=${cur.id }">修改</a>|<a href="#" id="t${cur.id }" onclick="deleteInfo(this);">删除</a></td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>
  </div>
  <div class="clear"></div>
</div>
<jsp:include page="/member/__inc/footer.jsp" flush="true" />
<!-- six footer -->
</body>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs_ext.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
   
   		// 区域
		$("#province").attr({"subElement": "city", "defaultText": "-请选择省/直辖市/自治区-", "defaultValue": "", "selectedValue": "${province}","dataType":"Require","msg":"请选择省/直辖市/自治区！"});
		$("#city").attr({"subElement": "country", "defaultText": "-请选择市-", "defaultValue": "", "selectedValue": "${city}"});
		$("#country").attr({"subElement": "town","defaultText": "-请选择县-", "defaultValue": "", "selectedValue": "${country}"});
		$("#town").attr({"defaultText": "-请选择乡/镇-", "defaultValue": "", "selectedValue": "${town}"});
		
		$("#province").cs_ext("${ctx}/CsAjax.do?method=getBaseProvinceFourList", "p_index", "0", false,"p_index_join","");
		$("#province").change();
		
		$("#rel_name"	).attr("dataType", "Require").attr("msg", "收货人姓名必须填写！"); 
		$("select[name='province']").attr("dataType", "Require").attr("msg", "请选择省/直辖市/自治区！"); 
		$("select[name='city']").attr("dataType", "Require").attr("msg", "请选择市！"); 
		$("select[name='country']").attr("dataType", "Require").attr("msg", "请选择县！"); 
		$("#rel_addr"	).attr("dataType", "Require").attr("msg", "详细街道地址必须填写！"); 
		$("#rel_tel"	).attr("dataType", "Phone").attr("Require", "false").attr("msg", "电话格式不正确！"); 
		$("#rel_phone"	).attr("dataType", "Mobile").attr("Require", "false").attr("msg", "手机格式不正确！"); 
		
		$("#btn_submit").click(function(){
			if($("#rel_tel").val()=="" && $("#rel_phone").val()==""){
				$("#rel_phone"	).attr("dataType", "Mobile").attr("Require", "true").attr("msg", "电话和手机至少填写一项！"); 
			}else{
				$("#rel_tel"	).attr("dataType", "Phone").attr("Require", "false").attr("msg", "电话格式不正确！"); 
				$("#rel_phone"	).attr("dataType", "Mobile").attr("Require", "false").attr("msg", "手机格式不正确！"); 
			}
			var isSubmit = Validator.Validate(this.form,3);
			if (isSubmit) {
				 $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
				 this.form.submit();
			}
		});
		
});


function deleteInfo(obj){
	var	id ="&id="+ obj.id.replace("t",""); 
	if(confirm("您确定删除吗？")){
		location.href="${ctx}/member/center/EcUserAddrs.do?method=delete"+id;
	}
}

function shows(objId){
	$("#"+objId).show();
}		
function hides(objId){
	$("#"+objId).hide();
}	
//]]></script>
</html>
