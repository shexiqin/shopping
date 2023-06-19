<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2023/4/7
  Time: 10:10
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

    <script type="text/javascript">
        function validate(input){
            var number=input.value;
            if(number.length==0){input.value="0";return;}
            else if(number.length>1&&number.indexOf("0")==0)
            {
                input.value=number.substring(1,number.length);
                return;
            }
            var patrn=/^\d+$/;
            if(!patrn.exec(input.value)){
                alert("请输入正整数");
                input.value=number.substring(0,number.length-1);return;
            }
        }
        function setSummary(index,price){
            try{
                var number = 0;
                number = document.getElementById('count'+index).value;
                var fPrice = parseFloat(price)*parseInt(number);
                document.getElementById('summary'+index).innerHTML=fPrice;
                setAllMoney();
            }catch(err){}
        }

        function setAllMoney(){
            try{
                var num = document.getElementsByName('num').length;
                var sum = 0.0;
                for(var i=1;i<num+1;i++){
                    var fPrice = document.getElementById('summary'+i).innerHTML;
                    sum = (sum*100+fPrice*100)/100;
                }
                document.getElementById('label_money').innerHTML=sum;
            }catch(err){}
        }

        window.onload = function(){
            try{
                setAllMoney();
            }catch(err){}
        }

    </script>
</head>
<body>
<div id="container">



    <script type="text/javascript">
        function checkLogin(){//判断是否是汉字、字母、数字组成
            var user = document.getElementById("hidden").value;
            if(user == null || user ==""){
                alert("您尚未登录，请先登录！");
            }
        }
    </script>
    <header>

        <div class="nav-bg">
            <div class="container">

                <ul class="nav-top">

                    <li><a href="gouwuche.html"><i class="iconfont" >&#xe623;</i>购物车0件</a></li>

                    <li id="gundong" >您  的 满 意 是 我 们 最 大 的 幸 福， 祝 你 购 物 愉 快!</li>


                    <li style="float: left;">有 问 题 请 您 及 时 拨 打 24 小 时 服 务 热 线 4 0 0 8 0 0 6 0 0</li>
                </ul>
                <b>  <a > 会员等级 <img src="images/baijin.png"/></a></b>
            </div>
        </div>
        <div class="container">

            <marquee width="500px" style="float: left;">
                <p style="font-size:30px;color:orange; margin-top: 22px;">欢迎您!caoqing</p>
            </marquee>

        </div>

    </header>

    <div class="nav">
        <div class="container">
            <a href="index.html" >首页</a> |
            <a href="huiyuan.html">会员资料修改</a> |
            <a href="gouwuche.html">购物车</a> |
            <a href="chankan.html">查看订单</a> |
            <a href="manage1.html">后台管理</a> |
            <a href="denglu.html">会员登录</a>


        </div>
    </div>


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
                                ￥
                                50.0
                            </td>
                            <td>
                                <input id="count1"
                                       name="count" size="8" value="1"
                                       onblur="setSummary('1','50.0')"
                                       class="input"  onchange="document.changeAllCountForm.submit();"  onpropertychange="validate(this);" />
                            </td>
                            <input type="hidden" name="hidden" value="1" id="hidden"/>
                            <td>
                                ￥
                                <span id="summary1">50.0 </span>
                            </td>
                            <td align="center">
                                <img src="images/35.gif"
                                     onclick="window.location.href='backCart.action?id=1'"
                                     style="border:0; float:inherit; cursor:hand" />
                            </td>
                        </tr>

                    </table>
                    <p class="ali_right">
                        合计总金额：￥
                        <span id="label_money"></span>
                    </p>

                    <p class="ali_center">
                        <a href="index.html">继续购物</a> |
                        <a href="jiezhang.html">收银结账</a> |
                        <a href="#">清空购物车</a> |
                        <a href="#"
                           onclick="document.changeAllCountForm.submit();">修改数量</a>
                    </p>
                </form>




            </div>
        </div>
    </div>
</div>

</body>
</html>
