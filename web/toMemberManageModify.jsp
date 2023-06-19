<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2023/4/9
  Time: 22:06
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
            cityList();
            select();
        });
    </script>
    <c:if test="${msg!=null}">
        ${msg}
    </c:if>
    <style>
        #userinfo {
            width: 759px;
            height: 448px;
            background: url(images/11.gif) no-repeat;
            margin: 0 auto;
            padding-top: 80px;
        }
    </style>
</head>
<body>
<div id="content" class="main">
    <jsp:include page="managerTop.jsp"></jsp:include>
    <div class="userinfo" id="userinfo">
        <form id="to_memberModify_action" name="memberRegisterForm" onsubmit="return true;"
              action="MemberController?doWhat=memberManageModify" method="post">
            <input type="hidden" name="userId" value="${member.id}">
            <ul>
                <input type="hidden" name="userId" value="${member.id}">
                <li><span>用户名：</span><input type="text" name="username" id="memberModify_action_member_username"
                                            value="${member.username}" onblur="checkUserName()"/>
                    <a id="username"></a>
                </li>
                <li><span>真实姓名：</span><input type="text" name="truename" id="memberModify_action_member_realname"
                                             value="${member.truename}"/>
                    <a id="realname"></a>
                </li>
                <li><span>密码：</span><input type="text" name="password" id="memberModify_action_password"
                                           value="${member.password}"/>
                    <a id="password"></a>
                    <font class="red">*</font>
                </li>
                <li><span>所在城市：</span>
                    <select id="city" name="city" style="width: 151px"></select>
                    <a id="memberCity" hidden>${member.city}</a>
                </li>
                <%--                        <input type="text" name="member.city" value="??" id="memberModify_action_member_city" />--%>
                <li><span>联系地址：</span><input type="text" name="member.address" value="${member.address}"
                    <a id="address"></a>
                    id="memberModify_action_member_address"/>
                    <font class="red"> *</font>
                </li>
                <li><span>邮政编码：</span><input type="text" name="member.postcode" value="${member.postcode}"
                                             id="memberModify_action_member_postcode"/>
                    <a id="postcode"></a>
                    <font class="red"> *</font>
                </li>
                <li><span>证件号码：</span><input type="text" name="member.cardno" value="${member.cardno}"
                                             id="memberModify_action_member_cardno"/>
                    <a id="cardno"></a>
                </li>

                <li><span>证件类别：</span>
                    <select id="cardtype" name="member.cardtype" style="width: 151px">
                        <option value="身份证">身份证</option>
                        <option value="军官证">军官证</option>
                        <option value="学生证">学生证</option>
                    </select>
                    <a id="memberCardType" hidden>${member.cardtype}</a>
                </li>
                <li><span>联系电话：</span><input type="text" name="member.tel" value="${member.tel}"
                                             id="memberModify_action_member_tel"/>
                    <a id="tel"></a>
                    <font class="red"> *</font>
                </li>
                <li><span>Email:</span><input type="text" name="member.email" value="${member.email}"
                                              id="memberModify_action_member_email"/>
                </li>
                <li><span>用户等级:</span>
                    <select id="grade" name="member.grade" style="width: 151px">
                        <option value="铜牌会员">铜牌会员</option>
                        <option value="银牌会员">银牌会员</option>
                        <option value="黄金会员">黄金会员</option>
                        <option value="白金会员">白金会员</option>
                        <option value="钻石会员">钻石会员</option>
                    </select>
                    <a id="memberGrade" hidden>${member.grade}</a>
                </li>
                <li class="button_img">
                    <input id="xiugai" type="button" onclick="checkLogin()" value="确认修改">
                    <input type="reset" value="重新填写">
                </li>
            </ul>
        </form>
    </div>
</div>

<script>



    function cityList() {
        var memberCity = $("#memberCity").html();
        var memberCardType = $("#memberCardType").html();
        var memberGrade = $("#memberGrade").html();
        $.ajax({
            type: "POST",
            url: "MemberController",
            data: "doWhat=getCity",
            dataType: "json",
            success: function (data) {
                var cityList = data.cityList;
                $.each(cityList, function (i, city) {
                    let option = $("<option></option>");
                    $(option).val(city.name);
                    $(option).html(city.name);
                    $("#city").append($(option));
                })
                $("#city").find("option[value='" + memberCity + "']").attr("selected", true);
                $("#cardtype").find("option[value='" + memberCardType + "']").attr("selected", true);
                $("#grade").find("option[value='" + memberGrade + "']").attr("selected", true);
            }
        });
    }


    function checkLogin() {
        var username = $("#memberModify_action_member_username");
        var realname =$("#memberModify_action_member_realname");
        var password =$("#memberModify_action_password");
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
        }else if (tel.val() == null || tel.val() == ""){
            tel.css("background", "pink");
            $("#tel").html("<font color='red'>手机号不能为空</font>");
        }else if (password.val().length<8){
            password.css("background", "pink");
            $("#password").html("<font color='red'>密码长度最低为8位</font>");
        }else if (cardno.val().length!=16){
            cardno.css("background", "pink");
            $("#cardno").html("<font color='red'>证件号码为16位</font>");
        }else if (!(/^1[3|4|5|7|8]\d{9}$/.test(tel.val()))){
            tel.css("background", "pink");
            $("#tel").html("<font color='red'>手机号格式错误</font>");
        }else {
            $("#to_memberModify_action").submit();
        }
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
</script>
</body>
</html>
