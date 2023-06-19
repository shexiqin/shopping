<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2023/4/7
  Time: 10:38
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
    <link href="styles/style.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="js/jquery-3.6.4.min.js"></script>
    <script>
        $(function () {
            cityList();
        });
    </script>
    <c:if test="${msg!=null}">
        ${msg}
    </c:if>
    <style>
        #userinfo {
            width:759px;
            height:448px;
            background:url(images/11.gif) no-repeat;
            margin: 0 auto;
            padding-top:80px;
        }
    </style>
</head>
<body>
    <jsp:include page="top.jsp"></jsp:include>
    <div id="content">
        <div class="userinfo" id="userinfo">
            <form id="to_memberModify_action" name="memberRegisterForm" onsubmit="return true;" action="MemberController?doWhat=memberModify" method="post">
                <ul>
                    <li><span>用户名：</span>
                        <input type="hidden" name="userId" value="${member.id}">
                        <a style="color: red" >${member.username}</a>
                    </li>
                    <li><span>真实姓名：</span>
                        <a style="color: red">${member.truename}</a>
                    </li>
                    <li><span>密码：</span><input type="text" name="password" id="memberModify_action_password" value="${member.password}"/>
                        <font class="red">*</font>
                        <a id="password"></a>
                    </li>
                    <li><span>所在城市：</span>
                        <select id="city" name="city" style="width: 151px"></select>
                        <a id="sessionCity" hidden>${member.city}</a>
                    </li>
                    <%--                        <input type="text" name="member.city" value="??" id="memberModify_action_member_city" />--%>
                    <li><span>联系地址：</span><input type="text" name="member.address" value="${member.address}" id="memberModify_action_member_address" />
                        <font class="red"> *</font>
                        <a id="address"></a>
                    </li>
                    <li><span>邮政编码：</span><input type="text" name="member.postcode" value="${member.postcode}" id="memberModify_action_member_postcode" />
                        <font class="red"> *</font>
                        <a id="postcode"></a>
                    </li>
                    <li><span>证件号码：</span><input type="text" name="member.cardno" value="${member.cardno}" id="memberModify_action_member_cardno" />
                        <a id="cardno"></a>
                    </li>
                    <li><span>证件类别：</span>
                        <select id="cardtype" name="member.cardtype" style="width: 151px">
                            <option value="身份证">身份证</option>
                            <option value="军官证">军官证</option>
                            <option value="学生证">学生证</option>
                        </select>
                        <a id="sessionCardType" hidden>${member.cardtype}</a>
                    </li>
                    <li><span>联系电话：</span><input type="text" name="member.tel" value="${member.tel}" id="memberModify_action_member_tel" />
                        <font class="red"> *</font>
                        <a id="tel"></a>

                    </li>
                    <li><span>Email:</span><input type="text" name="member.email" value="${member.email}" id="memberModify_action_member_email" />
                        <a id="email"></a>
                    </li>
                    <li class="button_img">
                        <input id="xiugai" type="button" onclick="checkLogin()" value="确认修改">
                        <input type="reset"  value="重新填写">
                    </li>
                </ul>
            </form>
        </div>
    </div>

<script>

    function cityList() {
        var sessionCity=$("#sessionCity").html();
        var sessionCardType=$("#sessionCardType").html();
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
                $("#city option[value='"+sessionCity+"']").attr("selected",true);
                $("#cardtype option[value='"+sessionCardType+"']").attr("selected",true);
            }
        });
    }




    function checkLogin() { //判断是否是汉字、字母、数字组成
        var password =$("#memberModify_action_password");
        var repassword = $("#memberModify_action_repassword");
        var postcode = $("#memberModify_action_member_postcode");
        var address = $("#memberModify_action_member_address");
        var tel = $("#memberModify_action_member_tel");
        var cardno = $("#memberModify_action_member_cardno");
        if (password.val() == null || password.val() == "" ){
            password.css("background", "pink");
            $("#password").html("<font color='red'>密码不能为空</font>");
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
            $("#cardno").html("<font color='red'>证件号码为16位</font>");
        }else if (!(/^1[3|4|5|7|8]\d{9}$/.test(tel.val()))){
            tel.css("background", "pink");
            $("#tel").html("<font color='red'>手机号格式错误</font>");
        }else {
            $("#to_memberModify_action").submit();
        }
    }
</script>
</body>
</html>
