<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2023/4/7
  Time: 10:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();//
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>电子商城</title>
    <link href="inc/style.css" rel="stylesheet" type="text/css" />
    <link href="styles/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="tuihuo">
    <%
        String id =request.getParameter("id");
    %>

    <form id="memberModify_action" name="memberRegisterForm" onsubmit="return true;" action="OrderController?doWhat=closeOrder" method="post">

        <table width="60%" border="0" cellspacing="0" cellpadding="0" align = "center">
            <tr>
                <td>关闭理由
                </td>
                <td>
                    <input type="hidden" name="id" value="<%=id %>">
                    <textarea name="member.address" rows="4" cols="30" id="memberModify_action_member_address"/></textarea>
                </td>
            </tr>
            <tr>
                <td><img   src="images/12.gif"   onclick="document.memberRegisterForm.submit();" style= "CURSOR:hand"/>
                </td>
                <td><img   src="images/13.gif"   onclick="document.memberRegisterForm.reset();" style= "CURSOR:hand"/></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
