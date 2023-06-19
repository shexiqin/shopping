<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2023/4/7
  Time: 17:08
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();//
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="styles/style.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" href="iconfont/iconfont.css" />
    <script  type="text/javascript" src="js/jquery-3.6.4.min.js"></script>
    <base href="<%=basePath%>">
    <title>会员注册</title>

    <script>
        $(function () {
            cityList();
        });
    </script>
    <c:if test="${msg!=null}">
        ${msg}
    </c:if>
</head>


<body>
<div id="container">
    <jsp:include page="top.jsp"></jsp:include>
    <div id="content">
        <div class="userinfo">
            <form id="memberModify_action" name="memberRegisterForm" onsubmit="return true;" action="MemberController?doWhat=regist" method="post">
                <input type="hidden" name="member.id" value="3" id="memberModify_action_member_id" />
                <ul>
                    <li><span>用户名：</span>
                        <input type="text" name="member.username" value="" id="memberModify_action_member_username" onblur="checkUserName()"/>
                        <font class="red"> *</font>
                        <a id="username"></a>
                    </li>
                    <li><span>真实姓名：</span><input type="text" name="member.realname" value="" id="memberModify_action_member_realname" />
                        <font class="red"> *</font>
                        <a id="realname"></a>
                    </li>
                    <li><span>密码：</span><input type="password" name="password" id="memberModify_action_password" />
                        <font class="red">*</font>
                        <a id="password"></a>
                    </li>
                    <li><span>确认密码：</span><input type="password" name="rePassword" id="memberModify_action_repassword" />
                        <font class="red"> *</font>
                        <a id="repassword"></a>
                    </li>
                    <li><span>所在城市：</span>
                        <select id="city" name="city" style="width: 151px"></select>
                    </li>
                    <li><span>联系地址：</span><input type="text" name="member.address" value="" id="memberModify_action_member_address" />
                        <font class="red"> *</font>
                        <a id="address"></a>
                    </li>
                    <li><span>邮政编码：</span><input type="mail" name="member.postcode" value="" id="memberModify_action_member_postcode" />
                        <font class="red"> *</font>
                        <a id="postcode"></a>
                    </li>
                    <li><span>证件号码：</span><input type="text" name="member.cardno" value="" id="memberModify_action_member_cardno" />
                        <a id="cardno"></a>
                    </li>
                    <li><span>证件类别：</span>
                        <select name="member.cardtype" style="width: 151px">
                            <option value="身份证">身份证</option>
                            <option value="军官证">军官证</option>
                            <option value="学生证">学生证</option>
                        </select>
                    </li>
                    <li><span>联系电话：</span><input type="text" name="member.tel" value="" id="memberModify_action_member_tel"/>
                        <font class="red"> *</font>
                        <a id="tel"></a>
                    </li>
                    <li><span>Email:</span><input type="email" name="member.email" value="" id="memberModify_action_member_email" />
                        <a id="email"></a>
                    </li>
                    <li class="button_img">
                        <input id="zhuce" type="button" onclick="checkNUll()" value="确认注册">
                        <input type="reset"  value="重新填写">
                    </li>
                </ul>
            </form>
        </div>
    </div>
</div>


<script>


    function cityList() {
        $.ajax({
            type:"POST",
            url:"MemberController",
            data:"doWhat=getCity",
            dataType:"json",
            success:function (data) {
                var cityList = data.cityList;
                $.each(cityList,function (i,city) {
                    let option = $("<option></option>");
                    $(option).val(city.name);
                    $(option).html(city.name);
                    $("#city").append($(option));
                })
            }
        });
    }



    function checkUserName() {
        var userName = $("#memberModify_action_member_username").val();
        $.ajax({
            type: 'POST',
            url: 'MemberController',
            data: "doWhat=checkUserName&userName=" + userName,
            success: function (data) {
                if (userName==null || userName==""){
                    $("#memberModify_action_member_username").css("background", "pink");
                    $("#username").html("<font color='red'>用户名不能为空</font>");
                }
                else if (data == 0) {
                    $("#memberModify_action_member_username").css("background", "");
                    $("#username").html("<font color='green'>此用户名可用</font>");
                } else {
                    $("#memberModify_action_member_username").css("background", "pink");
                    $("#username").html("<font color='red'>此用户名已存在</font>");
                }
            }
        });
    }



    function checkNUll() {
        var username = $("#memberModify_action_member_username");
        var realname =$("#memberModify_action_member_realname");
        var password =$("#memberModify_action_password");
        var repassword = $("#memberModify_action_repassword");
        var postcode = $("#memberModify_action_member_postcode");
        var address = $("#memberModify_action_member_address");
        var tel = $("#memberModify_action_member_tel");
        var cardno = $("#memberModify_action_member_cardno");
        if (username.val() == null || username.val() == "" ) {
            username.css("background", "pink");
            $("#username").html("<font color='red'>用户名不能为空</font>");
        }else if (realname.val() == null || realname.val() == "" ){
            realname.css("background", "pink");
            $("#realname").html("<font color='red'>真实姓名不能为空</font>");
        }else if (password.val() == null || password.val() == "" ){
            password.css("background", "pink");
            $("#password").html("<font color='red'>密码不能为空</font>");
        }else if (repassword.val() == null || repassword.val() == "" ){
            repassword.css("background", "pink");
            $("#repassword").html("<font color='red'>确认密码不能为空</font>");
        }else if (repassword.val() != password.val()){
            password.css("background", "pink");
            $("#password").html("<font color='red'>两次密码内容不一致</font>");
            repassword.css("background", "pink");
            $("#repassword").html("<font color='red'>两次密码内容不一致</font>");
        }else if (address.val() == null || address.val() == ""){
            repassword.css("background", "pink");
            $("#repassword").html("<font color='red'>确认密码不能为空</font>");
        }else if (postcode.val() == null || postcode.val() == ""){
            postcode.css("background", "pink");
            $("#postcode").html("<font color='red'>邮政编码不能为空</font>");
        }else if (tel.val() == null || tel.val() == ""){
            tel.css("background", "pink");
            $("#tel").html("<font color='red'>手机号不能为空</font>");
        }else if (password.val().length<8){
            password.css("background", "pink");
            $("#password").html("<font color='red'>密码长度最低为8位</font>");
        }else if (cardno.val().length!=16){
            cardno.css("background", "pink");
            $("#cardno").html("<font color='red'>证件号码为18位</font>");
        }else if (!(/^1[34578]\\d{9}$/.test(tel.val()))){
            tel.css("background", "pink");
            $("#tel").html("<font color='red'>手机号格式错误</font>");
        }else {
           $("#memberModify_action").submit();
        }
    }

</script>

</body>
</html>
