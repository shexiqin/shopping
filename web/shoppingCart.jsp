<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2023/4/7
  Time: 10:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();//
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<link href="styles/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-3.6.4.min.js"></script>
<html>
<script>
    $(function () {
        showCartList();

    })

</script>
<head>
    <base href="<%=basePath%>">
    <title>电子商城</title>
    <link href="styles/style.css" rel="stylesheet" type="text/css" />

    <script type="text/javascript">
        function validate(input) {
            var number = input.value;
            if (number.length == 0) {
                input.value = "0";
                return;
            } else if (number.length > 1 && number.indexOf("0") == 0) {
                input.value = number.substring(1, number.length);
                return;
            }
            var patrn = /^\d+$/;
            if (!patrn.exec(input.value)) {
                alert("请输入正整数");
                input.value = number.substring(0, number.length - 1);
                return;
            }
        }

        function setSummary(index, price) {
            try {
                var number = 0;
                number = document.getElementById('count' + index).value;
                var fPrice = parseFloat(price) * parseInt(number);
                document.getElementById('summary' + index).innerHTML = fPrice;
                setAllMoney();
            } catch (err) {}
        }

        function setAllMoney() {
            try {
                var num = document.getElementsByName('num').length;
                var sum = 0.0;
                for (var i = 1; i < num + 1; i++) {
                    var fPrice = document.getElementById('summary' + i).innerHTML;
                    sum = (sum * 100 + fPrice * 100) / 100;
                }
                document.getElementById('label_money').innerHTML = sum;
            } catch (err) {}
        }

        window.onload = function() {
            try {
                setAllMoney();
            } catch (err) {}
        }
    </script>

</head>
<body>
当前用户:<span id="member">${member.username}</span><br>
<jsp:include page="top.jsp"></jsp:include>
<div id="container">
    <div id="content">
        <div class="cartDet">
            <div class="bg">
                <h4>
                    购物车
                </h4>

                <form id="changeAllCount_action" name="changeAllCountForm" onsubmit="return true;" action="/shop/changeAllCount.action" method="post">
                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                            <th scope="col" width="15%">
                                编号
                            </th>
                            <th scope="col" width="30%">
                                商品名称
                            </th>
                            <th scope="col" width="15%">
                                单价
                            </th>
                            <th scope="col" width="15%">
                                数量
                            </th>
                            <th scope="col" width="15%">
                                金额
                            </th>
                            <th scope="col" width="10%">
                                删除
                            </th>
                        </tr>

                        <tr id="num">
                            <td>
                                1
                            </td>
                            <td>
                                杯子
                            </td>
                            <td>
                                ￥ 50.0
                            </td>
                            <td>
                                <input id="count1" name="count" size="8" value="1" onblur="setSummary('1','50.0')" class="input" onchange="document.changeAllCountForm.submit();" onpropertychange="validate(this);" />
                            </td>
                            <input type="hidden" name="hidden" value="1" id="hidden" />
                            <td>
                                ￥
                                <span id="summary1">50.0 </span>
                            </td>
                            <td align="center">
                                <img src="images/35.gif" onclick="window.location.href='backCart.action?id=1'" style="border:0; float:inherit; cursor:hand" />
                            </td>
                        </tr>



                    </table>
                    <p class="ali_right">
                        <span id="label_money"></span>
                    </p>

                    <p class="ali_center">
                        <a href="index.jsp">继续购物</a> |
                        <a href="checkOut.jsp">收银结账</a> |
                        <a href="ShoppingCartController?doWhat=clearCart&userid=${member.id}">清空购物车</a>
                </form>

            </div>
        </div>
    </div>
</div>

</body>

<script type="text/javascript">
    function updateCount(obj) {
        var username=$("#member").text();
        var id = $(obj).prop('name');
        var count = $(obj).val();
        if (count == '') {
            count = 0;
        }
        if (!isNaN(count)) {
            //location.href="ShoppingCartController?doWhat=updateCartGoods&id="+id+"&count="+count;
            $("table tr:gt(0)").remove();
            $.ajax({

                type: "post",
                url: "ShoppingCartController",
                data: "doWhat=updateCartGoods&id=" + id + "&count=" + count+"&username="+username,
                dataType: "json",
                success(data) {
                    var cart = data.cart;
                    var cartTable = data.cartTable;
                    var count11 = 1;
                    var total = 0;
                    $.each(cart, function (i, goods) {
                        var path = "<img src=\"images/35.gif\" onclick='deleteGoods(this)' value='" + goods.cartId + "'style=\"border:0; float:inherit; cursor:hand\" />";
                        var num = eval(goods.price+"*"+goods.cartCount);
                        total += num;
                        var tr = "<tr>" +
                            "<td>" + count11 + "</td>" +
                            "<td>" + goods.goodsName + "</td>" +
                            "<td>" + goods.price + "</td>" +
                            "<td><input type='text' value='" + goods.cartCount + "' ONCHANGE='updateCount(this)' name='" + goods.cartId + "'></td>" +
                            "<td >" + num + "</td>" +
                            "<td align=\"center\">" + path + "</td>" +
                            "</tr>";
                        $("table").append($(tr));
                        count11++;
                    })
                    var str = "合计总金额：￥";
                    $(".ali_right").text(str + total);


                }
            });

        } else {
            alert("请输入正确的格式");
        }


    }
    function deleteGoods(obj){
        var id=$(obj).attr("value");
        if(confirm("亲，确定要删除吗")){
            location.href="ShoppingCartController?doWhat=deleteCartGoods&id="+id;
        }

    }

    function showCartList() {
        var username=$("#member").text();
        $("table tr:gt(0)").remove();
        $.ajax({

            type:"post",
            url:"ShoppingCartController",
            data:"doWhat=showCartList&username="+username,
            dataType: "json",
            success(data){
                var cart=data.cart;
                var cartTable=data.cartTable;
                var count11=1;
                var total=0;
                $.each(cart,function (i,goods) {
                    var path="<img src=\"images/35.gif\" onclick='deleteGoods(this)' value='"+goods.cartId +"'style=\"border:0; float:inherit; cursor:hand\" />";
                    var num=eval(goods.price+"*"+goods.cartCount);
                    total+=num;
                    var tr="<tr>" +
                        "<td>" + count11 + "</td>" +
                        "<td>" + goods.goodsName+ "</td>" +
                        "<td>" + goods.price + "</td>" +
                        "<td><input type='text' value='"+goods.cartCount+"' ONCHANGE='updateCount(this)' name='"+goods.cartId+"'></td>" +
                        "<td >" + num + "</td>" +
                        "<td align=\"center\">" +path + "</td>"+
                        "</tr>";
                    $("table").append($(tr));
                    count11++;
                })
                var str="合计总金额：￥";
                $(".ali_right").text(str+total);


            }
        });

    }


    function gundong(){
        var div=document.getElementById("gundong")
        var txt=div.innerHTML;

        var arr=txt.split("");//把字符串变成数组

       // console.log(arr)

        var first=arr.shift(); //删除数组里
        //console.log(first)

        arr.push(first);
       // console.log(arr)
        txt=arr.join("")

        div.innerHTML=txt
       // console.log(txt);
    }

    setInterval(gundong,500)
</script>
</html>
