<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer/jquery-ui.custom.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/tip/jquery.qtip.css" rel="stylesheet" type="text/css" />
<style type="text/css">
select {
	font-family:Microsoft YAHEI;
	font-size:12px;
}

input#btn_submit {
  background: url("../../images/manager/but_serv1.jpg") no-repeat;
  height: 30px;
  width: 60px;
}
input.bgButtonBack {
  background: url("../../images/manager/but_return1.jpg") no-repeat;
  height: 30px;
  width: 60px;
}
input {
	font-family:Microsoft YAHEI;
	font-size:12px;
}
.title_item
{
	font-size:12px;
	color:#202020;
	width: 20%;
	height: 40px;
	background-color: #fff;

}
.rtabcont1{border:solid #ccc 1px;}
#new_password{ border:solid #ccc 1px;height: 20px;}
#old_password{ border:solid #ccc 1px;height: 20px;}
#repeat{ border:solid #ccc 1px;height: 20px;}
.tableClass{border-collapse:collapse;border:none;}
</style>
</head>
<body style="font-family:Microsoft Yahei;">
<div class="oartop">
  <table width="400" border="0" cellpadding="0" cellspacing="0">
    <tr>
      <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
      <td>当前位置：${naviString}</td>
    </tr>
  </table>
</div>
<%@ include file="/commons/pages/messages.jsp" %>
<div class="rtabcont1">
  <html-el:form action="/manager/JxcPasswardUpdate.do">
    <html-el:hidden property="method" value="save" />
    <html-el:hidden property="mod_id" styleId="mod_id" />
    <html-el:hidden property="queryString" styleId="queryString" />
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
    <tr><td style="height: 30px" colspan="2"></td></tr>
      <tr>
        <td width="12%" nowrap="nowrap" class="title_item" style="background-color: #FFF">原密码：</td>
        <td width="88%"><html-el:password property="old_password" styleId="old_password" maxlength="30" style="width:200px" /></td>
      </tr>
      <tr>
        <td nowrap="nowrap" class="title_item" style="background-color: #FFF">新密码：</td>
        <td><input name="new_password" id="new_password" maxlength="30" type="password" onkeyup="pwStrength(this.value);" onBlur="pwStrength(this.value);" style="width:200px" />
          <div id="passwdBar" style="width:360px;height:10px;display:none;">
            <ul>
              <li id="posBar" style="width:0px;height:10px;background:#f00;display:inline-block;"></li>
              <li id="negBar" style="width:350px;height:10px;background:#ff0;display:inline-block;"></li>
            </ul>
          </div>
          <div id="passwdRating"></div></td>
      </tr>
      <tr>
        <td nowrap="nowrap" class="title_item" style="background-color: #FFF">重复新密码：</td>
        <td><html-el:password property="repeat" styleId="repeat" maxlength="30" style="width:200px" /></td>
      </tr>
        <tr>
        	<td style="color: #666666 ;font-size: 12px;height: 40px;"></td>
	         <td style="color: #666666 ;font-size: 12px;" >
	         <ul>
	         	<li style="color: red;font-weight:bold;">提示:</li>
	         	<li>密码要求6-15位,而且要包括字母和数字.</li>
	         	<li>新密码要求与重复新密码相同.</li>  
	         </ul>
	         </td>
        </tr>
        
      <tr>
        <td>&nbsp;</td>
        <td><input class="bgButtonSave" type="button" name="Submit4" id="btn_submit" value="" />&nbsp;&nbsp;
          <input class="bgButtonBack" type="button" name="Submit5" value="" onclick="history.back();return false;" /></td>
      </tr>
      <tr><td style="height: 30px;" colspan="2"></td></tr>
      
    </table>
  </html-el:form>
</div>
<div class="clear"></div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#old_password").attr("dataType", "Require").attr("msg", "原密码不能为空");
	$("#new_password").attr("dataType", "Require").attr("msg", "新密码不能为空");
	$("#repeat").attr("datatype", "Repeat" ).attr("to", "new_password").attr("msg", "两次输入的密码不一致");
	$("#btn_submit").click(function(){
		
		if(Validator.Validate(this.form, 3)){
            $("#btn_submit").attr("value", "...").attr("disabled", "true");
            $("#btn_back").attr("disabled", "true");
			this.form.submit();
		}
	});
});
//]]></script>
<script type="text/javascript">
//以下是密码强度校验
	ratingMsgs = new Array(5);
	ratingMsgColors = new Array(5);
	barColors = new Array(5);
	ratingMsgs[0] = "太短";
	ratingMsgs[1] = "弱";
	ratingMsgs[2] = "一般";
	ratingMsgs[3] = "很好";
	ratingMsgs[4] = "极佳";
	ratingMsgColors[0] = "#676767";
	ratingMsgColors[1] = "#aa0033";
	ratingMsgColors[2] = "#f5ac00";
	ratingMsgColors[3] = "#6699cc";
	ratingMsgColors[4] = "#008000";
	barColors[0] = "#dddddd";
	barColors[1] = "#aa0033";
	barColors[2] = "#ffcc33";
	barColors[3] = "#6699cc";
	barColors[4] = "#008000";
	function passwordGrade(pwd) {
		var score = 0;
		var regexArr = [ '[0-9]', '[a-z]', '[A-Z]', '[\\W_]' ];
		var repeatCount = 0;
		var prevChar = '';
		//长度一个加一分，大于18按18算 
		var len = pwd.length;
		score += len > 18 ? 18 : len;
		//字符类型多一个加4分 
		for ( var i = 0, num = regexArr.length; i < num; i++) {
			if (eval('/' + regexArr[i] + '/').test(pwd)) {
				score += 4;
			}
		}
		for ( var i = 0, num = regexArr.length; i < num; i++) {
			if (pwd.match(eval('/' + regexArr[i] + '/g'))
					&& pwd.match(eval('/' + regexArr[i] + '/g')).length >= 2) {
				score += 2;
			}
			if (pwd.match(eval('/' + regexArr[i] + '/g'))
					&& pwd.match(eval('/' + regexArr[i] + '/g')).length >= 5) {
				score += 2;
			}
		}
		//重复一次减一分
		for ( var i = 0, num = pwd.length; i < num; i++) {
			if (pwd.charAt(i) == prevChar) {
				repeatCount++;
			} else {
				prevChar = pwd.charAt(i);
			}
		}
		score -= repeatCount * 1;
		return score;
	}
	//pwStrength函数 
	//当用户放开键盘或密码输入框失去焦点时,根据不同的级别显示不同的颜色
	function pwStrength(pwd) {
		if (pwd == null || pwd == '') {
			resetBar();
		} else {
			//if (pwd.length <= 4) {
				//DrawBar(0);
			//	return;
			//}
			var S_level;
			mark = passwordGrade(pwd);
			//弱
			if (mark <= 10) {
				S_level = 1;
			}
			//一般
			if (mark > 10 && mark <= 20) {
				S_level = 2;
			}
			//很好
			if (mark > 20 && mark <= 30) {
				S_level = 3;
			}
			//极佳
			if (mark > 30) {
				S_level = 4;
			}
			DrawBar(S_level);
		}
		return;
	}
	function DrawBar(rating) {
		var posbar = getElement('posBar');
		var negbar = getElement('negBar');
		var passwdRating = getElement('passwdRating');
		var barLength = 360;
		if (rating >= 0 && rating <= 4) {
			posbar.style.width = barLength / 4 * rating;
			negbar.style.width = barLength / 4 * (4 - rating);
		} else {
			posbar.style.width = 0;
			negbar.style.width = barLength;
			rating = 5;
		}
		posbar.style.background = barColors[rating];
		passwdRating.innerHTML = "<font color='" + ratingMsgColors[rating]
				+ "'>" + ratingMsgs[rating] + "</font>";
	}
	function resetBar() {
		var posbar = getElement('posBar');
		var negbar = getElement('negBar');
		var passwdRating = getElement('passwdRating');
		var barLength = getElement('passwdBar').width;
		posbar.style.width = "0px";
		negbar.style.width = barLength + "px";
		passwdRating.innerHTML = "";
	}
	function getElement(name) {
		if (document.all) {
			return document.all(name);
		}
		return document.getElementById(name);
	}
</script>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>