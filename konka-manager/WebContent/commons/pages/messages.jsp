<%@ include file="/commons/pages/taglibs.jsp" %>
<logic-el:messagesPresent>
<link href="${ctx}/commons/styles/message/message.css" rel="stylesheet" type="text/css" />
<table cellpadding="5" width="100%" cellspacing="8px" class="noteMacro" border="0" align="center">
  <tr>
    <td width="40" align="center" valign="middle" height="25"><img src="${ctx}/commons/styles/message/images/warning.gif" width="16" height="16" style="vertical-align:middle;" alt="" border="0" /></td>
    <td><p>
        <html-el:messages id="error"> ${error}<br />
        </html-el:messages>
      </p></td>
  </tr>
</table>
</logic-el:messagesPresent>
<logic-el:messagesPresent message="true">
  <link href="${ctx}/commons/styles/message/message.css" rel="stylesheet" type="text/css" />
  <table cellpadding="5" width="100%" cellspacing="8px" class="tipMacro" border="0" align="center">
    <tr>
      <td width="40" align="center" valign="middle" height="25"><img src="${ctx}/commons/styles/message/images/check.gif" width="16" height="16" style="vertical-align:middle;" alt="" border="0" /></td>
      <td><p>
          <html-el:messages id="message" message="true"> ${message}<br />
          </html-el:messages>
        </p></td>
    </tr>
  </table>
</logic-el:messagesPresent>
