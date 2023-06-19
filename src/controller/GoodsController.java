package controller;

import bean.TbClasses;
import bean.TbGoods;
import bean.TbMember;
import bean.TbUsercar;
import com.alibaba.fastjson.JSONObject;
import com.jspsmart.upload.Request;
import com.sun.org.apache.regexp.internal.RE;
import javafx.application.Application;
import service.GoodsService;
import service.MemberService;
import service.ShoppingCartService;
import util.FileUploadUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Member;
import java.security.interfaces.RSAKey;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class GoodsController extends HttpServlet {
    GoodsService goodsService = new GoodsService();
    MemberService memberService=new MemberService();
    ShoppingCartService shoppingCartService=new ShoppingCartService();
    FileUploadUtil fileUploadUtil=new FileUploadUtil();


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String doWhat=request.getParameter("doWhat");

        //业务划分
        //展示商品列表
        if ("goodsList".equals(doWhat)){
            showGoodsList(request,response);
        //添加商品
        }else if("addGoods".equals(doWhat)){
            addGoods(request,response);
        //展示商品类别
        }else if("showClass".equals(doWhat)){
            showGoodsClass(request,response);
        //去修改商品信息
        }else if("toModifyGoods".equals(doWhat)){
            toModifyGoods(request,response);
        //修改商品信息
        }else if("modifyGoods".equals(doWhat)){
            modifyGoods(request,response);
        //删除商品
        }else if("deleteGoods".equals(doWhat)){
            delGoods(request,response);
        //查询商品详细信息
        }else if("showGoodsDetail".equals(doWhat)){
            showGoodsDetail(request,response);
        //根据商品类别查询商品
        }else if("showProcess".equals(doWhat)){
            showProcess(request,response);
        //前台商品详细信息
        }else if("details".equals(doWhat)){
            details(request,response);
        //获取所有商品
        }else if("showAllProcess".equals(doWhat)){
            showAllProcess(request,response);
        //订购商品
        }else if ("booking".equals(doWhat)){
            bookingGoods(request, response);
        }
    }


    /**
     * 订购商品
     *
     * @param request 请求对象
     * @param response 响应对象
     * @throws ServletException servlet异常
     * @throws IOException IO异常
     */
    private void bookingGoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String goodid=request.getParameter("goodId");
        String username=request.getParameter("username");

        TbGoods tbGoods=goodsService.getGoodsById(goodid);
         TbMember tbMember=memberService.getMember(username);

        int flg= shoppingCartService.checkCart(tbGoods.getId(),tbMember.getId());
        if (flg>0){
            //说明该用户的该产品id已经存在 只需商品数量加1即可
            String count=shoppingCartService.getCarCount(tbGoods.getId(),tbMember.getId());
            int c=Integer.valueOf(count)+1;
            String newCount=String.valueOf(c);

            TbUsercar tbUsercar=new TbUsercar();

            tbUsercar.setGoodsid(tbGoods.getId());
            tbUsercar.setMemberid(tbMember.getId());
            tbUsercar.setCount(newCount);

            //将新的数量放到购物车表中
            shoppingCartService.addCartCount(tbUsercar);
            request.getRequestDispatcher("/shoppingCart.jsp").forward(request,response);
        }else {
            //说明该用户的该件商品并不存在 则直接添加
            shoppingCartService.addShoppingCart(tbGoods.getId(),tbMember.getId());
            request.getRequestDispatcher("/shoppingCart.jsp").forward(request,response);
        }




    }


    /**
     * 获取所有商品
     *
     * @param request 请求对象
     * @param response 响应对象
     */
    private void showAllProcess(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取当前页数
        String currentPageStr=request.getParameter("currentPage");
        int currentPage=1;
        if(currentPageStr!=null){
            currentPage=Integer.parseInt(currentPageStr);
        }
        //获取当前页大小
        String pageSizeStr = request.getParameter("pageSize");
        int pageSzie = 8;
        if (pageSizeStr != null){
            pageSzie = Integer.parseInt(pageSizeStr);
        }

        //获取名字
        String name = request.getParameter("goodName");
        //获取底价
        String lowPrice = request.getParameter("price1");
        //获取顶价
        String upPrice = request.getParameter("price2");
        //获取排序规则
        String sort = request.getParameter("sort");


        //总条数
        int totalCount=goodsService.getGoodsCountBy(name,lowPrice,upPrice);

        //总页数
        int totalPage=totalCount%pageSzie==0?totalCount/pageSzie:totalCount/pageSzie+1;

        //当前页面的第一条
        int start=(currentPage-1)*pageSzie+1;
        //当前页面最后一条
        int end=start+pageSzie-1;

        List<TbGoods> goodsList=goodsService.getGoodsListBy(start,end,name,lowPrice,upPrice,sort);

        JSONObject jsonObject=new JSONObject();
        jsonObject.put("goodsList",goodsList);
        jsonObject.put("totalPage",totalPage);
        jsonObject.put("currentPage",currentPage);

        PrintWriter out = response.getWriter();
        out.println(jsonObject);
        out.close();
    }


    /**
     * 前台商品详细信息
     *
     * @param request 请求对象
     * @param response 响应对象
     */
    private void details(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String goodId = request.getParameter("goodId");
        TbGoods goods=goodsService.getGoodsById(goodId);
        request.setAttribute("goods",goods);
        request.getRequestDispatcher("/details.jsp").forward(request,response);
    }


    /**
     * 根据商品类别查询商品
     *
     * @param request 请求对象
     * @param response 响应对象
     */
    private void showProcess(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String className = request.getParameter("classname");
        List tbGoods=goodsService.getGoodsByClass(className);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("goodList",tbGoods);
        response.getWriter().println(jsonObject);
        response.getWriter().close();
    }


    /**
     * 查询商品详细信息
     *
     * @param request  请求对象
     * @param response  响应对象
     * @throws ServletException  Servlet异常
     * @throws IOException  IO异常
     */
    private void showGoodsDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String goodsId = request.getParameter("goodsId");
        TbGoods goods = goodsService.getGoodsDetail(Integer.parseInt(goodsId));
        request.setAttribute("goods",goods);
        request.getRequestDispatcher("/goodsMessageView.jsp").forward(request,response);
    }


    /**
     * 删除商品
     *
     * @param request  请求对象
     * @param response  响应对象
     * @throws ServletException  Servlet异常
     * @throws IOException  IO异常
     */
    private void delGoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String goodsName=request.getParameter("goodsName");

        goodsService.delGoods(goodsName);

        request.getRequestDispatcher("/goodsManageList.jsp").forward(request,response);

    }


    /**
     * 修改商品信息
     *
     * @param request  请求对象
     * @param response  响应对象
     * @throws ServletException  Servlet异常
     * @throws IOException  IO异常
     */
    private void modifyGoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //二进制表单都需要调取这个方法
        HashMap<String,Object> map=fileUploadUtil.uploadFile(getServletConfig(),request,response);
        Request req=(Request)map.get("req");
        String path=(String)map.get("path");



        String goodsname=req.getParameter("goodsname");
        String price=req.getParameter("price");
        String introduce=req.getParameter("introduce");
        String id=req.getParameter("id");
        TbGoods tbGoods=new TbGoods();
        tbGoods.setGoodsname(goodsname);
        tbGoods.setPrice(price);
        tbGoods.setIntroduce(introduce);
        tbGoods.setId(id);
        //用户并没有修改图片 上传原来图片
        if(path==null){
            TbGoods oldGoods=goodsService.getGoodsById(id);
            path=oldGoods.getPicture();
            tbGoods.setPicture(path);

        //否则上传新修改的图片
        }else if (path!=null){
            tbGoods.setPicture(path);
        }
        int flg=goodsService.modifyGoods(tbGoods);
        if (flg>0){
            request.getRequestDispatcher("/goodsManageList.jsp").forward(request,response);
        }else {
            request.setAttribute("msg","<script>alert('修改失败，请重新设置')</script>");
            request.getRequestDispatcher("toGoodsManageEdit.jsp").forward(request,response);

        }


    }


    /**
     * 跳转物品修改页面
     *
     * @param request  请求对象
     * @param response  响应对象
     * @throws ServletException  Servlet异常
     * @throws IOException  IO异常
     */
    private void toModifyGoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String goodsName=request.getParameter("goodsName");
        TbGoods tbGoods=goodsService.getGoods(goodsName);

        request.setAttribute("tbGoods",tbGoods);
        request.getRequestDispatcher("/toGoodsManageEdit.jsp").forward(request,response);

    }


    /**
     * 异步查询类别
     *
     * @param request  请求对象
     * @param response  响应对象
     * @throws IOException  IO异常
     */
    private void showGoodsClass(HttpServletRequest request, HttpServletResponse response) throws IOException {

        List<TbClasses> tbClassesList=goodsService.getGoodsClass();
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("tbClassesList",tbClassesList);
        PrintWriter out = response.getWriter();
        out.println(jsonObject);
        out.close();
    }


    /**
     * 添加商品
     *
     * @param request 请求对象
     * @param response 响应对象
     * @throws ServletException servlet异常
     * @throws IOException IO异常
     */
    private void addGoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HashMap<String, Object> map = fileUploadUtil.uploadFile(getServletConfig(), request, response);
        String path = (String) map.get("path");
        Request req = (Request) map.get("req");
        //获取信息
        String goodsName=req.getParameter("goodsname");
        String price=req.getParameter("price");
        String introduce=req.getParameter("introduce");
        String classify=req.getParameter("classify");

        TbGoods tbGoods=new TbGoods();
        tbGoods.setGoodsname(goodsName);
        tbGoods.setPrice(price);
        tbGoods.setPicture(path);
        tbGoods.setCredate(getTime());
        tbGoods.setIntroduce(introduce);
        tbGoods.setClassname(classify);

        //插入数据
        int flg = goodsService.addGoods(tbGoods);
        if(flg>0){
            /*request.setAttribute("msg","<script>alert('商品添加成功')</script>");*/
            /*request.getRequestDispatcher("goodsManageList.jsp").forward(request,response);*/
            response.sendRedirect("goodsManageList.jsp");
        }else {
            request.setAttribute("msg","<script>alert('商品添加失败')</script>");
            request.getRequestDispatcher("toGoodsManageAdd.jsp").forward(request,response);
        }
        
    }


    /**
     * 获取格式化后的日期
     *
     * @return String字符串
     */
    public String getTime(){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar=Calendar.getInstance();
        Date date=calendar.getTime();
        return simpleDateFormat.format(date);
    }


    /**
     * 后台异步刷新商品列表
     *
     * @param request  请求对象
     * @param response  响应对象
     * @throws IOException  IO异常
     */
    private void showGoodsList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int eachPageCount=5;

        //获取当前页数
        String currentPageStr=request.getParameter("currentPage");
        int currentPage=1;
        if(currentPageStr!=null){
            currentPage=Integer.parseInt(currentPageStr);
        }
        //总条数
        int totalCount=goodsService.getGoodsCount();
        //总页数
        int totalPage=totalCount%eachPageCount==0?totalCount/eachPageCount:totalCount/eachPageCount+1;

        //当前页面的第一条
        int start=(currentPage-1)*eachPageCount+1;
        //当前页面最后一条
        int end=start+eachPageCount-1;
        List<TbGoods> goodsList=goodsService.getGoodsListBySplit(start,end);

        JSONObject jsonObject=new JSONObject();
        jsonObject.put("goodsList",goodsList);
        jsonObject.put("totalPage",totalPage);
        jsonObject.put("currentPage",currentPage);

        PrintWriter out = response.getWriter();
        out.println(jsonObject);
        out.close();
    }


}
