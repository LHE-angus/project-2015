<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>康佳网上直销商城</title>
<link rel=stylesheet type=text/css  href="${ctx}/styles/zxmall/css/global1.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/zxmall/css/epp.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/zxmall/css/zxmall.css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<style type="text/css">
.login_div{position: absolute;	border: solid 1px #d1e3f5;top:35%;text-align: center;border:1px solid #E03425;background: #f5f4f4;left:40%;width:350px;padding-bottom: 0px;padding-top: 0px;display: none;z-index:10002;} 
fieldset {text-align:left;padding:10px;margin-top:5px; background:#fff;}
fieldset legend {color:#1E7ACE;font-weight:bold;padding:3px 20px 3px 20px;border:1px solid #E03425;background:#fff;}
fieldset label {float:left;width:100px;text-align:right;padding:4px;margin:1px;}
fieldset div {clear:left;margin-top:15px;margin-bottom:18px;}
fieldset .buttom {width:44px; padding:1px 10px; margin-left:10px;font-size:12px;border:1px #1E7ACE solid;background:#D0F0FF;}
#login_bg_div {display: none; position: absolute; top: 0%; left: 0%; width: 100%; height: 100%; background-color: black; z-index:10000; -moz-opacity: 0.7; opacity:.70; filter: alpha(opacity=60);}
</style> 
</head>
<body>
<!-- head start -->
<div class="topbox">
<jsp:include page="/zxmall/__inc/top.jsp" flush="true" />
<jsp:include page="/zxmall/__inc/search.jsp" flush="true" />
</div> 
<jsp:include page="/zxmall/__inc/nav.jsp" flush="true" />
<div class="login_div" id="login_div" style="display:none;z-index:10000;position:absolute;">
<form action="" method="post" name="apLogin" id="apLogin">
	<fieldset>		
		<h3>银行卡信息维护<span id="x_close" title="关闭" style="float:right;font-size:18px;color:red;cursor:pointer;">×</span></h3>
		<font id="login_msg" color="#E03425"></font>
		<div><label for="bank_name">开户银行</label><input type="text" name="bank_name" id="bank_name" value="${af.map.bank_name}" size="18" maxlength="30" /><font color="red" id="u_msg"></font><br/></div>
		<div><label for="bank_account_name">开户姓名</label><input type="text" name="bank_account_name" id="bank_account_name" value="${af.map.bank_account_name}" size="18" maxlength="30" /><font color="red" id="p_msg"></font><br/> </div>
		<div><label for="bank_account_name">银行卡号</label><input type="text" name="bank_account" id="bank_account" value="${af.map.bank_account}" size="18" maxlength="30" /><font color="red" id="a_msg"></font><br/> </div>
		<div class="cookiechk">
			<input name="login791" id="sbt_login" type="button" class="buttom" value="提交" style="margin-left:130px;" onclick="sub();"/>	<input name="cancel" type="button" id="login_close" class="buttom" value="取消" />
		</div>
	</fieldset>
</form>
</div>
<!-- second start -->
<div class="maincont">
  <jsp:include page="/zxmall/__inc/_mleft.jsp" flush="true" />
  <!--right-->
  <div class="zxmall_right padbot45">
    <div class="position"><a href="${ctx }/zxmall/Index.do">首页</a> &gt; <a href="${ctx }/zxmall/center/Index.do">会员中心</a> &gt;我的佣金</div>
    <div class="zxmalltab3">
      <p style="margin-top:15px;font-size:14px;">您获得的佣金总额:<span style="color:#FF2200;font-weight:bold;"><fmt:formatNumber value="${fn:escapeXml(rebates)}" pattern="0.00" /> </span></p>      
      <html-el:form action="/center/EcRebates">
        <html-el:hidden property="method" styleId="method" value="list" />
        <table width="100%" border="0" cellspacing="0" cellpadding="0"  class="zxmall_form_table0">
          <td width="60%"><html-el:text styleClass="input_txt" property="start_date" size="10" maxlength="10" readonly="true" onclick="new Calendar(2011, 2021, 0).show(this);" style="width:120px;cursor:pointer;text-align:center;" title="点击选择日期" styleId="start_date" ></html-el:text>
              至
   <html-el:text styleClass="input_txt" property="end_date" size="10" maxlength="10" readonly="true" onclick="new Calendar(2011, 2021, 0).show(this);" style="width:120px;cursor:pointer;text-align:center;" title="点击选择日期" styleId="end_date" ></html-el:text>
           
      &nbsp;      
          佣金状态：  <html-el:select property="rebates_status" style="width:105px;height:25px; margin-left:-1px; margin-top:-1px;">
         <html-el:option value="">==全部==</html-el:option>
         <html-el:option value="0">需确认提现、兑换积分</html-el:option>
         <html-el:option value="1">提现 已发放</html-el:option>
         <html-el:option value="2">提现 等待发放</html-el:option>
         <html-el:option value="3">已兑换积分</html-el:option>
         </html-el:select>  </td> <td width="40%"><input class="inputbtn" type="submit" name="Submit" value="查询" />
            </td>
          </tr>
        </table>
      </html-el:form>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="zxmall_form_table2">
        <tr class="tr1">
          <td width="5%" nowrap="nowrap" align="center">序号</td>
          <td width="15%" nowrap="nowrap" align="center">商品名称</td>
          <td width="10%" nowrap="nowrap" align="center">单价数量</td>          
          <td width="10%" nowrap="nowrap" align="left">获得佣金</td>   
          <td width="10%" nowrap="nowrap" align="center">时间</td>
          <td width="10%" nowrap="nowrap" align="center">提现兑换状态</td> 
        </tr>
        <tbody>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr height="60">
              <td align="center" nowrap="nowrap">${vs.count }</td>
              <td align="center" nowrap="nowrap"><c:out value="${cur.pd_name }"/></td>
              <td align="center" nowrap="nowrap"><fmt:formatNumber value="${fn:escapeXml(cur.price)}" pattern="￥0.00" /> * ${cur.num }</span></td>
              <td align="left" nowrap="nowrap"><c:if test="${cur.rebates_status eq 1 }"><font color="green">
              <fmt:formatNumber value="${fn:escapeXml(cur.rebates)}" pattern="￥0.00" /></font></c:if><c:if test="${cur.rebates_status ne 1 }"><font color="red">
              <fmt:formatNumber value="${fn:escapeXml(cur.rebates)}" pattern="￥0.00" /></font></c:if></td>
              <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.map.add_date}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
              <td align="center" nowrap="nowrap">
              	<c:if test="${cur.rebates_status eq 1 }">提现 已发放</c:if>
              	<c:if test="${cur.rebates_status eq 0 }"><c:if test="${not empty cur.rebates and cur.rebates>0}">[<a href="#" onclick="encash('2','${cur.bill_item_id}')" >提现</a>] [<a href="#" onclick="encash('3','${cur.bill_item_id}')">兑换积分</a>]</c:if></c:if>
              	<c:if test="${cur.rebates_status eq 2 }">提现 等待发放</c:if>
              	<c:if test="${cur.rebates_status eq 3 }">已兑换积分</c:if>
              	<c:if test="${cur.rebates_status eq 4 }">抵扣货款</c:if>
              </td>  
             </tr>
          </c:forEach>
          <c:if test="${empty entityList }">
            <tr height="60">
              <td align="center" nowrap="nowrap" colspan="6"> 暂无记录 </td>
            </tr>
          </c:if>
        </tbody>
      </table>
      <c:if test="${af.map.pager.pageCount>1}">
        <form id="bottomPageForm" name="bottomPageForm" method="post" action="?">
          <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr>
              <td height="40"><div style="text-align: right; padding-right: 5px;">
                  <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
                  <script type="text/javascript">
			                     var pager = new Pager(document.bottomPageForm, parseInt('${af.map.pager.recordCount}'), parseInt('${af.map.pager.pageSize}'), parseInt('${af.map.pager.currentPage}'));
			                     pager.addHiddenInputs("method", "list");
			                     pager.addHiddenInputs("start_date", "<c:out value='${af.map.start_date}'/>"); 	
			                     pager.addHiddenInputs("end_date", "<c:out value='${af.map.end_date}'/>"); 	
			                     pager.addHiddenInputs("rebates_status", "<c:out value='${af.map.rebates_status}'/>"); 	
			                     document.write(pager.toString());
			                 </script>
                </div></td>
            </tr>
          </table>
        </form>
      </c:if>
    </div>
  </div>
  <div class="clear"></div>
</div>
<jsp:include page="/zxmall/__inc/footer.jsp" flush="true" />
<!-- six footer -->
<script type="text/javascript">//<![CDATA[

$(document).ready(function(){
	
});
function encash(type,id){
	var is_ws = "${af.map.is_ws}";
	var user_id = "${af.map.user_id}";     
	if(is_ws == "0"){
		if(type == "2"){
		$("#login_div").fadeIn(500);   
		$("#login_close").click(function(){$("#login_div").fadeOut(500);});  
		$("#x_close").click(function(){$("#login_div").fadeOut(500);});
		var flag = false;    
		$("#sbt_login").click(function(){ 
			var bank_name=$('#bank_name').val();
			var bank_account_name=$('#bank_account_name').val();  
			var bank_account=$('#bank_account').val();
			$('#u_msg').html('');
			$('#p_msg').html('');
			$('#a_msg').html('');  
			if(bank_name!=null){bank_name=bank_name.replace(/(^\s*)|(\s*$)/g, "");}
			if(bank_account_name!=null){bank_account_name=bank_account_name.replace(/(^\s*)|(\s*$)/g, "");}
			if(bank_account!=null){bank_account=bank_account.replace(/(^\s*)|(\s*$)/g, "");}
			
			if(bank_name==''){$('#u_msg').html('请输入开户行名称');return false;	}
			if(bank_account_name==''){$('#p_msg').html('请输入开户人姓名');	return false;}
			if(bank_account==''){$('#a_msg').html('请输入银行卡号');	return false;} 
			this.disabled=true;
			this.value="正在验证。。。";
			$.ajax({
				type: "POST",
				url:"${ctx}/zxmall/center/EcRebates.do",
				data: {"method":"ajaxSave","user_id":user_id,"bank_name":bank_name,"bank_account_name":bank_account_name,"bank_account":bank_account,"timestamp":new Date().getTime() },
				dataType: "text",sync: false,
				error: function (xhr, ajaxOptions, thrownError) {alert('网络异常，请稍后再试。。。');document.getElementById("sbt_login").value="提交";document.getElementById("sbt_login").disabled=false; 
				},success: function(result){
					if(result=='1'){ 
						$("#login_div").hide();
						alert('银行卡信息维护成功');
						flag = true;
						location.href="${ctx}/zxmall/center/EcRebates.do?method=save&type="+type+"&id="+id;  
					}else{ 
						document.getElementById("sbt_login").value="提交";document.getElementById("sbt_login").disabled=false; 
					}
				}
			});
		});	
		} else if(type == "3"){
			location.href="${ctx}/zxmall/center/EcRebates.do?method=save&type="+type+"&id="+id; 
		}
	}else if(is_ws == "1"){
		location.href="${ctx}/zxmall/center/EcRebates.do?method=save&type="+type+"&id="+id;
	}
	
	
}

</script>

</body>
</html>
