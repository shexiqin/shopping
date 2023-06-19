<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2023/4/7
  Time: 10:43
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
    <title>电子商城</title>
    <link href="styles/style.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="iconfont/iconfont.css"/>
    <script type="text/javascript" src="js/jquery-3.6.4.min.js"></script>
    <c:if test="${msg!=null}">
        ${msg}
    </c:if>
    <c:if test="${member.grade=='铜牌会员'}">
        <a id="youhui" hidden>0.8</a>
    </c:if>
    <c:if test="${member.grade=='银牌会员'}">
        <a id="youhui" hidden>0.7</a>
    </c:if>
    <c:if test="${member.grade=='黄金会员'}">
        <a id="youhui" hidden>0.6</a>
    </c:if>
    <c:if test="${member.grade=='白金会员'}">
        <a id="youhui" hidden>0.5</a>
    </c:if>
    <c:if test="${member.grade=='钻石会员'}">
        <a id="youhui" hidden>0.3</a>
    </c:if>
    <c:if test="${member.grade==null}">
        <a id="youhui" hidden>1</a>
    </c:if>

    <script>
        var count = 0;
        $(function () {
            fenlei();
            shangpinList('firPage');

            $("#pageSzie").on("change",function () {
                shangpinList('firPage');
            })
        });

        //查找按钮
        function chaxun() {
            shangpinList('firPage');
        }


        //分类
        function fenlei() {
            $.ajax({
                type: "post",
                url: "GoodsController",
                data: "doWhat=showClass",
                dataType: "json",
                success: function (data) {
                    var count = '0';
                    $.each(data.tbClassesList, function (i, tbclass) {
                        count++;
                        var leibiedaohang = '#leibie';
                        leibiedaohang = leibiedaohang + count;
                        var fl =
                            "<li><a href='" + leibiedaohang + "'>" + tbclass.name + "</a></li>";
                        $("#shangpinfenlei").append($(fl));
                        $(leibiedaohang).html(tbclass.name);
                        if (tbclass.name == '限时折扣品') {
                            zhekou(tbclass.name)
                        }
                    })
                }
            });
        }


        //分页查询
        function shangpinList(pageFlg) {
            let pageSize = $("#pageSzie").val();
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
            } else if(pageFlg == 'endPage'){
                currentPage = totalPage;
            }else {
                currentPage =currentPage;
            }
            let price1 = $(":input[name='price1']").val();
            let price2 = $(":input[name='price2']").val();
            let goodName = $(":input[name='goodName']").val();
            let sort = $("#sort").html();
            $.ajax({
                type: "post",
                url: "GoodsController",
                data: "doWhat=showAllProcess&currentPage=" + currentPage + "&pageSize=" + pageSize+"&price1="+price1+"&price2="+price2+"&goodName="+goodName+"&sort="+sort,
                dataType: "json",
                success: function (data) {
                    $("#currentPage").html(data.currentPage);
                    $("#totalPage").html(data.totalPage);
                    $("#processList").empty();
                    $.each(data.goodsList, function (i, good) {
                        let li = "<li>" +
                            "<img src='" + good.picture + "' width='100px' height='100px'/>" +
                            "<span>" + good.goodsname + "</span>" +
                            "<span>单价：" + good.price + "</span>" +
                            "<span>" +
                            "<a href='GoodsController?doWhat=details&goodId=" + good.id + "'>" +
                            "<img src='images/08.gif' alt='详细信息' style='border:0; cursor:hand;'/>" +
                            "</a>&nbsp;" +
                            "<a href='javascript:checkBooking("+ good.id+")'>" +
                            "<img src='images/33.gif' alt='订购' style='border:0; cursor:hand;'/>" +
                            "</a>&nbsp;" +
                            "</span>" +
                            "</li>";
                        $("#processList").append($(li));
                    })
                }
            });
        }

        //折扣
        function zhekou(className) {
            $.ajax({
                type: "post",
                url: "GoodsController",
                data: "doWhat=showProcess&classname=" + className,
                dataType: "json",
                success: function (data) {
                    let youhui=$("#youhui").html();
                    $.each(data.goodList, function (i, good) {
                        var price=parseInt(good.price)*youhui;
                        if (i <= 2) {
                            var li = "<li>" +
                                "<img src='" + good.picture + "' width='100px' height='88px'/><br>" +
                                "<span>" + good.goodsname + "</span><br>" +
                                "<span >原价：" + good.price + "</span><br>" +
                                "<span style='color: red'>现价：" + price + "</span>" +
                                "<span>" +
                                "<br/>" +
                                "<a href='GoodsController?doWhat=details&goodId=" + good.id + "'>" +
                                "<img src='images/08.gif'  alt='详细信息' style='border:0; cursor:hand;'/>" +
                                "</a>" +
                                "<a href='javascript:checkBooking("+ good.id+")'>" +
                                "<img src='images/33.gif' alt='订购' style='border:0; cursor:hand;'/>" +
                                "</a>" +
                                "</span>" +
                                "</li>";
                            $("#xianshi").append($(li));
                        }
                    })
                }
            });
        }


        function checkBooking(goodsid) {
            var username=$("#member").text();
            if (username==''){
                if (confirm("您还没有登录，请前往登录界面")){
                    location.href="login.jsp";
                }
            }else {
                location.href="GoodsController?doWhat=booking&goodId="+goodsid+"&username="+username;

            }
        }

        //排序
        function srotByMoney(sort) {
            $("#sort").html(sort);
            shangpinList('currentPage');
        }

    </script>
</head>
<body>
<span id="member" hidden>${member.username}</span>
<jsp:include page="top.jsp"></jsp:include>
<!--轮播-->
<div class="banner">
    <div id="gal-wrap">
        <ul id="gallery">
            <li>
                <a><img src="images/lunbo.jpg"/></a>
            </li>
            <li>
                <a><img src="images/lunbo.jpg"/></a>
            </li>
            <li>
                <a><img src="images/lunbo.jpg"/></a>
            </li>
        </ul>
    </div>
    <div class="fenlei">
        <ul id="shangpinfenlei" class="ul-nav">
        </ul>

        <ul class="xianshi" id="xianshi">
            <b>限时折扣</b>
        </ul>
    </div>
</div>

<div class="content">
    <h3 style="background: lightpink;">全部商品</h3>
    <div class="tiaojian">
        价格：
        <input type="text" name="price1">
        ~&nbsp;&nbsp;
        <input type="text" name="price2">
        &nbsp;&nbsp;
        名称：
        <input type="text" name="goodName" style="width:200px;">
        <input type="button" onclick="chaxun()" value="查找">
        <hr/>
        <div class="orderBy" onclick="srotByMoney('desc')">价格由高到低</div>
        <div class="orderBy" onclick="srotByMoney('asc')">价格由低到高</div>
        <div class="orderBy" style="float: right;margin-right: 250px;">
            <a id="sort" hidden></a>
            当前第<span id="currentPage"></span>/<span id="totalPage"></span>页
            <a onclick="shangpinList('firPage')" style="border:0; cursor:hand;">首页</a>
            <a onclick="shangpinList('prePage')" style="border:0; cursor:hand;">上一页</a>
            <select id="pageSzie">
                <option value="8">8</option>
                <option value="12">12</option>
                <option value="16">16</option>
            </select>
            <a onclick="shangpinList('nextPage')" style="border:0; cursor:hand;">下一页</a>
            <a onclick="shangpinList('endPage')" style="border:0; cursor:hand;">尾页</a>
        </div>
        <div style="clear: both;"></div>
    </div>
    <ul class="ul_list" id="processList">
    </ul>
</div>
</body>


<script type="text/javascript">
    setInterval(changer, 3000)
    var index = 0;

    function changer() {
        var gallery = document.getElementById("gallery");
        var li = gallery.getElementsByTagName("li");
        var start = index;

        index -= 1000;
        var total = 1000 * li.length;
        // var a =  3 >2 ? "true" :"false"
        index = index == -total ? 0 : index;

        var end = index;

        //gallery.style.left = end + "px";

        var timeIndex = setInterval(slider, 10)

        //0,-500,-1000,
        function slider() {
            if (end == 0) {
                start += 40;
                if (start <= end) {
                    gallery.style.left = start + "px";
                } else {
                    clearInterval(timeIndex);
                }
            } else {
                start -= 20;
                if (start >= end) {
                    gallery.style.left = start + "px";
                } else {
                    clearInterval(timeIndex);
                }
            }
        }

    }
</script>
</html>
