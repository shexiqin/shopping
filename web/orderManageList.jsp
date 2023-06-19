<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2023/4/7
  Time: 10:46
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
    <script src="js/jquery-3.6.4.min.js"></script>
</head>
<body>
<script>
    $(function () {
        showList('firPage');
    })
</script>

<div class="main">


    <jsp:include page="managerTop.jsp"></jsp:include>


    <div class="adm_con">
        <div class="bg_3">
            <div class="bg_1">
                <div class="bg_2">
                    <h3><img src="images/25.gif" class="flo_right" /><img src="images/23.gif" class="flo_left" /><span>订单管理</span></h3>
                    <div class="padd">


                        <table width="100%" border="0" cellspacing="0" cellpadding="0" id="order-tab">
                            <tr>
                                <td scope="col" width="25%"> 订单号 </td>
                                <td scope="col" width="10%"> 品种数 </td>
                                <td scope="col" width="15%"> 收件人 </td>
                                <td scope="col" width="20%"> 订货日期 </td>
                                <td scope="col" width="15%"> 执行</td>
                                <td scope="col" width="15%"> 操作</td>
                            </tr>
                        </table>


                        <p class="ali_center">
                            当前页：<span id="currentPage"></span>/<span id="totalPage"></span>&nbsp;
                            <a href="javascript:showList('firPage')">首页</a>&nbsp;
                            <a href="javascript:showList('prePage')">上一页</a>&nbsp;
                            <a href="javascript:showList('nextPage')">下一页</a>&nbsp;
                            <a href="javascript:showList('endPage')">尾页</a>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    function showList(pageFlg) {
        let currentPage = $("#currentPage").html();
        let totalPage = $("#totalPage").html();

        currentPage=parseInt(currentPage);
        totalPage=parseInt(totalPage);

        if(pageFlg=='firPage'){
            currentPage=1;
        }else if (pageFlg=='prePage'){
            if(currentPage!=1){
                currentPage=currentPage-1;
            }
        }else if (pageFlg=='nextPage'){
            if (currentPage!=totalPage){
                currentPage=currentPage+1;
            }
        }else {
            currentPage=totalPage;
        }
        $.ajax({
            type: "POST",// 提交类型
            url: "OrderController",// 提交地址
            data: "doWhat=orderList&currentPage="+currentPage,// 提交时传递的参数
            dataType:"json",// 返回的类型 默认text
            success: function(data){// data即json格式的数据
                // 动态的打印表格的行
                let currentPage = data.currentPage;
                let totalPage = data.totalPage;
                let orderList = data.orderList;
                $("#currentPage").html(currentPage);
                $("#totalPage").html(totalPage);
                // 删除之前页内容
                $("table tr:gt(0)").remove();
                // tr构建
                $.each(orderList,function (i,order) {
                    let img = "";
                    let returnTp = "";
                    if(order.enforce == '1'){
                        img =  "<img src=\"images/30.gif\" style=\"border:0;\"/>";// onclick="window.location.href="  cursor:hand;
                        returnTp = "<a href=\"OrderController?doWhat=toReturnGoods&id="+order.id+"\">退货</a>";
                    }else if(order.enforce == '2') {
                        img =  "<img src=\"images/30.gif\" style=\"border:0;\"/>";
                        returnTp ="<a href=\"OrderController?doWhat=toReturnMoney&id="+order.id+"\">退款</a>";
                    }else if (order.enforce == '0'){
                        img = "正常";
                    } else {
                        img = "已执行";
                    }
                    let tr = "<tr>\n" +
                        "<td><a href=\"OrderController?doWhat=orderDetail&id="+order.id+"\">"+order.ordercode+"</a></td>" +
                        "<td>"+order.bnumber+"</td>" +
                        "<td>"+order.username+"</td>" +
                        "<td>"+order.orderdate+"</td>" +
                        "<td>"+img+"</td>" +
                        "<td>" +returnTp+
                        "<a href=\"orderClose.jsp?id="+order.id+"\">关闭</a></td>" +
                        "</tr>";
                    $("#order-tab").append($(tr));
                });

            }

        });

    }
</script>
</html>
