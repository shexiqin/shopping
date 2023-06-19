<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2023/4/7
  Time: 11:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<%
    String path = request.getContextPath();//
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <title>Title</title>
    <base href="<%=basePath%>">
    <script type="text/javascript" src="js/jquery-3.6.4.min.js"></script>
    <link href="styles/style.css" rel="stylesheet" type="text/css" />
</head>
<script>
    $(function () {
        showList('firPage');
    })
</script>
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
                        <p>[ 商品列表 ] [ <a href="toGoodsManageAdd.jsp">添加商品信息</a>]</p>


                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <th scope="col" width="30%"> 商品名称 </th>
                                <th scope="col" width="15%"> 价格 </th>
                                <th scope="col" width="20%"> 发布日期 </th>
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
    function update(obj) {
        var name=$(obj).attr("value");
        console.log(name);
        location.href="GoodsController?doWhat=toModifyGoods&goodsName="+name;
    }
    function deleteGoods(obj) {
        var name=$(obj).attr("value");
        console.log(name);
        if (confirm("确定要删除吗？")){
            location.href="GoodsController?doWhat=deleteGoods&goodsName="+name;
        }
    }
  function showList(pageFlg) {
      var currentPage=document.getElementById("currentPage").innerText;
      var totalPage=document.getElementById("totalPage").innerText;

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
          url: "GoodsController",// 提交地址
          data: "doWhat=goodsList&currentPage="+currentPage,// 提交时传递的参数
          dataType:"json",// 返回的类型 默认text
          async:true,// 默认异步 true   同步false
          cache:false,// 缓存
          success: function(data){// data即json格式的数据
              // 动态的打印表格的行
              var currentPage = data.currentPage;
              var totalPage = data.totalPage;
              var goodsList = data.goodsList;
              $("#currentPage").html(currentPage);
              $("#totalPage").html(totalPage);

              // 动态构建tr
              // 删除之前页内容
              $("table tr:gt(0)").remove();
              // tr构建
              $.each(goodsList,function (i,goods) {
                  var goodsName=goods.goodsname;
                  var update= "<td><img src='images/34.gif' onclick='update(this)' style='border:0; cursor:hand;' value="+goodsName+"></td>";
                  var deleteGoods= "<td><img src='images/35.gif' onclick='deleteGoods(this)' style='border:0; cursor:hand;' value="+goodsName+"></td>";

                  var tr ="<tr>"+
                      "<td><a href='GoodsController?doWhat=showGoodsDetail&goodsId="+goods.id+"'>"+goods.goodsname+"</a></td>"+
                      "<td>" + goods.price  +"</td>"+
                      "<td>" + goods.credate  +"</td>"+update+deleteGoods+
                      "</tr>";
                  $("table").append($(tr));
              });

          }

      });

  }
</script>
</html>
