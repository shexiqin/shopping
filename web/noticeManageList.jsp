<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2023/4/9
  Time: 13:34
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
    <script type="text/javascript" src="js/jquery-3.6.4.min.js"></script>
</head>
<body>
<c:if test="${msg != null}">
    ${msg}
</c:if>
<div class="main">
    <jsp:include page="managerTop.jsp"></jsp:include>
    <div class="adm_con">
        <div class="bg_3">
            <div class="bg_1">
                <div class="bg_2">
                    <h3><img src="images/25.gif" class="flo_right" /><img src="images/23.gif" class="flo_left" /><span>商品列表</span></h3>
                    <div class="padd">
                        <p>[ 公告列表 ] [ <a href="toNoticeManageAdd.jsp">添加公告</a>]</p>

                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <th scope="col" width="30%"> 公告标题 </th>
                                <th scope="col" width="15%"> 发布时间 </th>
                                <th scope="col" width="20%"> 截止时间 </th>
                                <th scope="col" width="10%"> 修改 </th>
                                <th scope="col" width="10%"> 删除 </th>
                            </tr>
                        </table>

                        <div align="center">
                            当前页：<span id="currentPage"></span>/<span id="totalPage"></span>&nbsp;
                            <a href="javascript:showList('firPage')">首页</a>&nbsp;
                            <a href="javascript:showList('prePage')">上一页</a>&nbsp;
                            <a href="javascript:showList('nextPage')">下一页</a>&nbsp;
                            <a href="javascript:showList('endPage')">尾页</a>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    $(function () {
        splite('firPage');
    });

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
            url: 'NoticeController',
            data: 'doWhat=noticeList&currentPage='+currentPage,
            dataType: 'json',
            success: function (data) {
                let totalPage = data.totalPage;
                $("#totalPage").html(totalPage);
                let noticeList = data.noticeList;
                $("table tr:gt(0)").remove();
                $.each(noticeList, function (i, notice) {
                    let tr = "<tr>" +
                        "<td><a href='NoticeController?doWhat=noticeView&id="+notice.id+"'>" +notice.title +"</a></td>" +
                        "<td>" +notice.creatdate +"</td>" +
                        "<td>" +notice.enddate +"</td>" +
                        "<td>" +
                        "<a href='NoticeController?doWhat=toUpdateNotice&id="+notice.id+"'>" +
                        "<img src='images/34.gif' style='border:0; cursor:hand;'/>" +
                        "</a>" +
                        "</td>" +
                        "<td>" +
                        "<a href='NoticeController?doWhat=deleteNotice&id="+notice.id+"'>" +
                        "<img src='images/35.gif' style='border:0; cursor:hand;'/>" +
                        "</a>" +
                        "</td>" +
                        "</tr>";
                    $(tr).appendTo("table");
                })
            }
        });
    }
</script>
</html>
