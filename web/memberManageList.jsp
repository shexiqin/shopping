<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2023/4/7
  Time: 10:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();//
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>Title</title>
    <link href="styles/style.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="js/jquery-3.6.4.min.js"></script>
    <script>
        $(function () {
            splite('firPage');
        });
    </script>
    <c:if test="${msg!=null}">
        ${msg}
    </c:if>
</head>
<body>
<div class="main">
    <jsp:include page="managerTop.jsp"></jsp:include>
    <div class="adm_con">
        <div class="bg_3">
            <div class="bg_1">
                <div class="bg_2">
                    <h3><img src="images/25.gif" class="flo_right"/><img src="images/23.gif" class="flo_left"/><span>会员管理</span>
                    </h3>
                    <div class="padd">
                        <p> [会员列表]</p>
                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <th scope="col" width="10%"> 用户名</th>
                                <th scope="col" width="13%"> 真实姓名</th>
                                <th scope="col" width="13%"> 用户等级</th>
                                <th scope="col" width="15%"> 城 市</th>
                                <th scope="col" width="15%"> 电 话</th>
                                <th scope="col" width="17%"> E-mail</th>
                                <th scope="col" width="7%">修改</th>
                                <th scope="col" width="10%"> 冻结/解冻</th>
                            </tr>
                        </table>
                        <p class="ali_center">
                            当前页:<a id="currentPage"></a>/<a id="totalPage"></a>&nbsp;
                            <a onclick="splite('firPage')" style="border:0; cursor:hand;">首页</a>&nbsp;
                            <a onclick="splite('prePage')" style="border:0; cursor:hand;">上一页</a>&nbsp;
                            <a onclick="splite('nextPage')" style="border:0; cursor:hand;">下一页</a>&nbsp;
                            <a onclick="splite('endPage')" style="border:0; cursor:hand;">尾页</a>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    function splite(pageFlg) {
        var currentPage = $("#currentPage").html();
        var totalPage = $("#totalPage").html();
        currentPage = parseInt(currentPage);
        totalPage = parseInt(totalPage);
        if (pageFlg == 'firPage') {
            currentPage = 1;
        } else if (pageFlg == 'prePage') {
            if (currentPage != 1) {
                currentPage = currentPage - 1;
            }
        } else if (pageFlg == 'nextPage') {
            if (currentPage != totalPage) {
                currentPage = currentPage + 1;
            }
        } else {
            currentPage = totalPage;
        }
        $("#currentPage").html(currentPage);
        $.ajax({
            type: 'POST',
            url: 'MemberController',
            data: 'doWhat=memberList&currentPage=' + currentPage,
            dataType: 'json',
            success: function (data) {
                let totalPage = data.totalPage;
                $("#totalPage").html(totalPage);
                let memberList = data.memberList;
                $("table tr:gt(0)").remove();
                $.each(memberList, function (i, member) {
                    if (member.freeze == 0) {
                        var path = "images/31.gif";
                    } else {
                        var path = "images/32.gif";
                    }
                    let tr = "<tr>" +
                        "<td><a href='MemberController?doWhat=memberView&username=" + member.username + "'>" + member.username + "</a></td>" +
                        "<td>" + member.truename + "</td>" +
                        "<td>" + member.grade + "</td>" +
                        "<td>" + member.city + "</td>" +
                        "<td>" + member.tel + "</td>" +
                        "<td>" + member.email + "</td>" +
                        "<td>" +
                        "<a href='MemberController?doWhat=updateMember&username=" + member.username + "'>" +
                        "<img src='images/34.gif' style='border:0; cursor:hand;'/>" +
                        "</a>" +
                        "</td>" +
                        "<td>" +
                        "<a href='MemberController?doWhat=updateFreeze&username=" + member.username + "&freeze=" + member.freeze + "'>" +
                        "<img src='" + path + "' style='border:0; cursor:hand;'/>" +
                        "</a>" +
                        "</td>" +
                        "</tr>";
                    $(tr).appendTo("table");
                })
            }
        });
    }


    function updataFreeze(username, freeze) {
        window.location.href = "MemberController?doWhat=updateFreeze&username=" + username + "&freeze=" + freeze;
    }
</script>
</body>
</html>
