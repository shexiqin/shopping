<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2023/4/7
  Time: 7:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <base href="<%=basePath %>">
    <title>电子商城</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
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
        var count=0;
        $(function () {
            fenlei();
        })

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
                        shangpinList(tbclass.name)
                        if (tbclass.name=='限时折扣品'){
                            zhekou(tbclass.name)
                        }
                    })
                }
            });
        }

        function shangpinList(className) {
            count++;
            var str=count.toString();
            $.ajax({
                type: "post",
                url: "GoodsController",
                data: "doWhat=showProcess&classname=" + className,
                dataType: "json",
                success: function (data) {
                    $.each(data.goodList, function (i, good) {
                        if (i <= 7) {
                            var processList = '#processList';
                            processList = processList + str;
                            var li = "<li>" +
                                "<img src='" + good.picture + "' width='100px' height='100px'/>" +
                                "<span>" + good.goodsname + "</span>" +
                                "<span>单价：" + good.price + "</span>" +
                                "<span>" +
                                "<a href='GoodsController?doWhat=details&goodId=" + good.id + "'>" +
                                "<img src='images/08.gif' alt='详细信息' style='border:0; cursor:hand;'/>" +
                                "</a>&nbsp;" +
                                "<a href='javascript:checkBooking("+ good.id+")'>" +
                                "<img src='images/33.gif' alt='订购'  style='border:0; cursor:hand;'/>" +
                                "</a>&nbsp;" +
                                "</span>" +
                                "</li>";
                            $(processList).append($(li));
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
                                "<span style='color: red'>现价："+ price + "</span>" +
                                "<span>" +
                                "<br/>" +
                                "<a href='GoodsController?doWhat=details&goodId=" + good.id + "'>" +
                                "<img src='images/08.gif'  alt='详细信息' style='border:0; cursor:hand;'/>" +
                                "</a>" +
                                "<a href='javascript:checkBooking("+ good.id+")'>" +
                                "<img src='images/33.gif' alt='订购'  style='border:0; cursor:hand;'/>" +
                                "</a>" +
                                "</span>" +
                                "</li>";
                            $("#xianshi").append($(li));
                        }
                    })
                }
            });
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
        </ul>
    </div>


    <div class="fenlei">
        <ul id="shangpinfenlei" class="ul-nav">
        </ul>

        <ul class="xianshi" id="xianshi" >
            <b>限时折扣</b>
        </ul>
    </div>
</div>


<div class="content">
    <h3 style="background: lightpink;" id="leibie1"></h3>
    <ul class="ul_list" id="processList1">


    </ul>
</div>


<div class="content">
    <h3 style="background: #867bc5;" id="leibie2"></h3>
    <ul class="ul_list" id="processList2">

    </ul>
</div>


<div class="content">
    <h3 style="background:#53c7ff;" id="leibie3"></h3>
    <ul class="ul_list" id="processList3">

    </ul>
</div>

</body>


<%--轮播图--%>
<script type="text/javascript">
    //公告展示
    $(function () {
        $.ajax({
            type: "post",
            url: "NoticeController",
            data: "doWhat=showNotice",
            dataType: "json",
            success: function (data) {
                $.each(data.noticeList, function (i, notice) {
                    let li = "<li><a href='NoticeController?doWhat=noticeDetail&id="+notice.id+"'><img src='"+notice.addfile+"'></a></li>";
                    $("#gallery").append($(li));
                });
            }
        });
    });
    setInterval(changer, 3000);
    var index = 0;


    function changer() {
        let gallery = $("#gallery");
        var li = $("#gallery li");
        var start = index;
        index -= 1000;
        var total = 1000 * li.length;
        gallery.css("width",total+"px");
        index = index == -total ? 0 : index;
        var end = index;
        var timeIndex = setInterval(slider, 10);

//0,-500,-1000,
        function slider() {
            if (end == 0) {
                start += 40;
                if (start <= end) {
                    gallery.css("left",start + "px");
                } else {
                    clearInterval(timeIndex);
                }
            } else {
                start -= 20;
                if (start >= end) {
                    gallery.css("left",start + "px");
                } else {
                    clearInterval(timeIndex);
                }
            }
        }
    }


</script>
</html>