<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2023/4/7
  Time: 10:05
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
    <link href="styles/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="tuihuo">

    <form id="memberModify_action" name="memberRegisterForm" action="OrderController?doWhat=returnGoods" method="post">

        <table width="60%" border="0" cellspacing="0" cellpadding="0" align = "center">
            <tr>
                <td>处理结果:
                </td>
                <td>
                    <input name="id" type="hidden" value="${id}">
                    <select name="isAccept"><option value="同意退货">同意退货</option><option value="拒绝退货">拒绝退货</option></select>
                </td>
            </tr>
            <tr>
                <td>退货地址:
                </td>
                <td><input type="text" name="address" value="河南省林州市" id="memberModify_action_member_cardno"/></td>
            </tr>
            <tr>
                <td>退货理由:
                </td>
                <td>
                    <textarea name="returnMsg" rows="4" cols="30" id="memberModify_action_member_address"/>
                    ${bz}
                    </textarea>
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
