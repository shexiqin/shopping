package controller;

import bean.TbMember;
import bean.TbOrder;
import bean.TbOrderDetail;
import com.alibaba.fastjson.JSONObject;
import service.OrderService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class OrderController extends HttpServlet {

    OrderService orderService = new OrderService();



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String doWhat=request.getParameter("doWhat");
        //业务划分
        //分页查询订单列表
        if("orderList".equals(doWhat)){
            orderList(request,response);
        //订单详情
        }else if("orderDetail".equals(doWhat)){
            orderDetail(request,response);
        //添加订单
        }else if("addOrder".equals(doWhat)){
            addOrder(request,response);
        //跳转到处理退货
        }else if ("toReturnGoods".equals(doWhat)){
            toReturnGoods(request,response);
        //跳转退款页面
        }else if ("toReturnMoney".equals(doWhat)){
            toReturnMoney(request,response);
        //处理退货
        }else if("returnGoods".equals(doWhat)){
            returnGoods(request,response);
        //处理退款
        }else if("returnMoney".equals(doWhat)){
            returnMoney(request,response);
        //关闭订单
        }else if ("closeOrder".equals(doWhat)){
            closeOrder(request,response);
        //会员订单查询
        }else if ("orderListById".equals(doWhat)){
            orderListById(request,response);
        //会员退货
        }else if("memberReturnGoods".equals(doWhat)){
            memberReturnGoods(request,response);
        //会员退款
        }else if ("memberReturnMoney".equals(doWhat)){
            memberReturnMoney(request,response);
        //会员订单查询
        }else if("memberOrderDetail".equals(doWhat)){
            memberOrderDetail(request,response);
        }
    }


    /**
     * 会员订单查询
     *
     * @param request 请求对象
     * @param response 响应对象
     * @throws ServletException Servlet异常
     * @throws IOException IO异常
     */
    private void memberOrderDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        TbOrder order = orderService.getOrderById(id);
        List<TbOrderDetail> orderDetailList = orderService.getOrderDetailById(id);
        request.setAttribute("order",order);
        request.setAttribute("orderDetailList",orderDetailList);
        request.getRequestDispatcher("orderInformation.jsp").forward(request,response);

    }


    /**
     * 会员退款
     *
     * @param request 请求对象
     * @param response 响应对象
     * @throws IOException IO异常
     */
    private void memberReturnMoney(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        orderService.modifyOrderEnforceReturnMoney(id);
        response.sendRedirect("checkOrders.jsp");
    }


    /**
     * 会员退货
     *
     * @param request 请求对象
     * @param response 响应对象
     * @throws IOException IO异常
     */
    private void memberReturnGoods(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        String returnMsg = request.getParameter("returnMsg");
        orderService.modifyOrderEnforceByMember(id,returnMsg);
        response.sendRedirect("checkOrders.jsp");
    }


    /**
     * 后台取消订单
     *
     * @param request 请求对象
     * @param response 响应对象
     * @throws IOException IO异常
     */
    private void closeOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        orderService.closeOrder(id);
        response.sendRedirect("orderManageList.jsp");
    }


    /**
     * 处理退款
     *
     * @param request 请求对象
     * @param response 响应对象
     * @throws IOException IO异常
     */
    private void returnMoney(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        String money = request.getParameter("returnMoney");
        String returnMsg = request.getParameter("returnMsg");
        String message = money+","+returnMsg;
        orderService.modifyOrderEnforce(id,message);
        response.sendRedirect("orderManageList.jsp");
    }


    /**
     * 处理退货
     *
     * @param request 请求对象
     * @param response 响应对象
     * @throws IOException IO异常
     */
    private void returnGoods(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        String isAccept = "处理结果:"+request.getParameter("isAccept");
        String address = "退货地址"+request.getParameter("address");
        String returnMsg = "退货理由"+request.getParameter("returnMsg");
        String message = isAccept+","+address+","+returnMsg;
        orderService.modifyOrderEnforce(id,message);
        response.sendRedirect("orderManageList.jsp");
    }


    /**
     * 跳转退款页面
     *
     * @param request 请求对象
     * @param response 响应对象
     * @throws ServletException Servlet异常
     * @throws IOException IO异常
     */
    private void toReturnMoney(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        List<TbOrderDetail> orderDetailList = orderService.getOrderDetailById(id);
        double returnMoney = 0;
        for (TbOrderDetail tbOrderDetail : orderDetailList) {
            double price = Double.parseDouble(tbOrderDetail.getPrice());
            int numbers = Integer.parseInt(tbOrderDetail.getNumbers());
            returnMoney = returnMoney+price*numbers;
        }
        request.setAttribute("id",id);
        request.setAttribute("returnMoney",returnMoney);
        request.getRequestDispatcher("refund.jsp").forward(request,response);
    }


    /**
     * 跳转到退货页面
     *
     * @param request 请求对象
     * @param response 响应对象
     * @throws ServletException Servlet异常
     * @throws IOException IO异常
     */
    private void toReturnGoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        request.setAttribute("id",id);
        TbOrder tbOrder = orderService.getOrderBz(id);
        String bz = tbOrder.getBz();
        request.setAttribute("bz",bz);
        request.getRequestDispatcher("processReturn.jsp").forward(request,response);
    }


    /**
     * 添加订单
     *
     * @param request 请求对象
     * @param response 响应对象
     * @throws IOException IO异常
     * @throws ServletException Servlet异常
     */
    private void addOrder(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        TbMember member = (TbMember)request.getSession().getAttribute("member");
        if(member != null){

            Date date = new Date();
            //用户ID
            String memberId = member.getId();
            //订单编号
            String orderCode = getOrderCode(date);
            //品种数
            int count = orderService.getGoodsClassCount(Integer.parseInt(memberId));
            //收件人姓名
            String username = request.getParameter("shopOrder.username");
            //邮寄地址
            String address = request.getParameter("shopOrder.address");
            //邮政编码
            String postcode = request.getParameter("shopOrder.postcode");
            //电话
            String tel = request.getParameter("shopOrder.tel");
            //支付方式
            String pay = request.getParameter("shopOrder.pay");
            //运送方式
            String carry = request.getParameter("shopOrder.carry");
            //订单日期
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateStr = simpleDateFormat.format(date);
            //备注
            String bz = request.getParameter("shopOrder.bz");
            TbOrder tbOrder = new TbOrder(null,memberId,orderCode,String.valueOf(count),username,address,postcode,tel,pay,carry,dateStr,null,bz);
            if(orderService.setOrder(tbOrder)){
                //将订单编号返回页面
                request.setAttribute("orderCode",orderCode);
                request.getRequestDispatcher("orderNumber.jsp").forward(request,response);
            }else {
                request.setAttribute("msg","<script>alert('结算失败');</script>");
                response.sendRedirect("checkOut.jsp");
            }
        }else {
            response.sendRedirect("login.jsp");
        }

    }


    /**
     * 订单详情
     *
     * @param request 请求对象
     * @param response 响应对象
     * @throws ServletException Servlet异常
     * @throws IOException IO异常
     */
    private void orderDetail(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter("id");
        TbOrder order = orderService.getOrderById(id);
        List<TbOrderDetail> orderDetailList = orderService.getOrderDetailById(id);
        request.setAttribute("order",order);
        request.setAttribute("orderDetailList",orderDetailList);
        request.getRequestDispatcher("orderManageView.jsp").forward(request,response);
    }


    /**
     * 异步查询订单列表
     *
     * @param request 请求对象
     * @param response 响应对象
     * @throws IOException IO异常
     */
    private void orderList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int eachPageCount = 5;
        //获取当前页
        String currentPageStr=request.getParameter("currentPage");
        int currentPage=1;
        if(currentPageStr!=null){
            currentPage=Integer.parseInt(currentPageStr);
        }
        //总条数
        int totalCount=orderService.getOrderCount();
        //总页数
        int totalPage=totalCount%eachPageCount==0?totalCount/eachPageCount:totalCount/eachPageCount+1;

        //当前页面的第一条
        int start=(currentPage-1)*eachPageCount+1;
        //当前页面最后一条
        int end=start+eachPageCount-1;
        List<TbOrder> orderList = orderService.getOrderList(start,end);

        JSONObject jsonObject=new JSONObject();
        jsonObject.put("orderList",orderList);
        jsonObject.put("totalPage",totalPage);
        jsonObject.put("currentPage",currentPage);

        PrintWriter out = response.getWriter();
        out.println(jsonObject);
        out.close();
    }


    /**
     * 异步查询订单会员列表
     *
     * @param request 请求对象
     * @param response 响应对象
     * @throws IOException IO异常
     */
    private void orderListById(HttpServletRequest request, HttpServletResponse response) throws IOException {
        TbMember member = (TbMember)request.getSession().getAttribute("member");
        String memberId = member.getId();
        int eachPageCount = 5;
        //获取当前页
        String currentPageStr=request.getParameter("currentPage");
        int currentPage=1;
        if(currentPageStr!=null){
            currentPage=Integer.parseInt(currentPageStr);
        }
        //总条数
        int totalCount=orderService.getOrderCount(memberId);
        //总页数
        int totalPage=totalCount%eachPageCount==0?totalCount/eachPageCount:totalCount/eachPageCount+1;

        //当前页面的第一条
        int start=(currentPage-1)*eachPageCount+1;
        //当前页面最后一条
        int end=start+eachPageCount-1;
        List<TbOrder> orderList = orderService.getOrderListById(start,end,memberId);

        JSONObject jsonObject=new JSONObject();
        jsonObject.put("orderList",orderList);
        jsonObject.put("totalPage",totalPage);
        jsonObject.put("currentPage",currentPage);

        PrintWriter out = response.getWriter();
        out.println(jsonObject);
        out.close();
    }


    /**
     * 生成订单编号
     *
     * @return String订单编号
     */
    public String  getOrderCode(Date date){
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int num = random.nextInt(10);
            stringBuilder.append(num);
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateStr = simpleDateFormat.format(date);
        return dateStr+stringBuilder.toString();
    }


}
