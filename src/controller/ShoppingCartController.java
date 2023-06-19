package controller;

import bean.TbCart;
import bean.TbGoods;
import bean.TbMember;
import bean.TbUsercar;
import com.alibaba.fastjson.JSONObject;
import service.GoodsService;
import service.MemberService;
import service.ShoppingCartService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartController extends HttpServlet {
    ShoppingCartService service = new ShoppingCartService();
    GoodsService goodsService=new GoodsService();
    MemberService memberService=new MemberService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取前台值
        String doWhat = request.getParameter("doWhat");

        //2.业务处理
        if ("showCartList".equals(doWhat)){
            showCartList(request,response);
        //删除购物车商品
        }else if("deleteCartGoods".equals(doWhat)){
            deleteCartGoods(request,response);
        //修改购物车商品
        }else if("updateCartGoods".equals(doWhat)){
            updateCartGoods(request,response);
        //清空购物车
        }else if("clearCart".equals(doWhat)){
            clearCart(request,response);
        //获取用户购物车数量
        }else if ("getShoppingCartCount".equals(doWhat)){
            getShoppingCartCount(request,response);
        }
    }


    /**
     * 清空购物车
     *
     * @param request 请求对象
     * @param response 响应对象
     * @throws ServletException servlet异常
     * @throws IOException IO异常
     */
    private void clearCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userid=request.getParameter("userid");
        service.clearCart(userid);

        request.getRequestDispatcher("/shoppingCart.jsp").forward(request,response);
    }


    /**
     * 修改购物车商品
     *
     * @param request 请求对象
     * @param response 响应对象
     * @throws ServletException servlet异常
     * @throws IOException IO异常
     */
    private void updateCartGoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id=request.getParameter("id");
        String count=request.getParameter("count");
        if ("0".equals(count)){
            service.deleteCartGoods(id);
            //request.getRequestDispatcher("/shoppingCart.jsp").forward(request,response);
            showCartList(request,response);
        }else {
            service.updateCartCount(id,count);
            showCartList(request,response);
            //request.getRequestDispatcher("/shoppingCart.jsp").forward(request,response);
        }
    }


    /**
     * 删除购物车商品
     *
     * @param request 请求对象
     * @param response 响应对象
     * @throws ServletException servlet异常
     * @throws IOException IO异常
     */
    private void deleteCartGoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id=request.getParameter("id");
        service.deleteCartGoods(id);

        request.getRequestDispatcher("/shoppingCart.jsp").forward(request,response);

    }


    /**
     * 获取用户购物车数量
     *
     * @param request 请求对象
     * @param response 响应对象
     */
    private void getShoppingCartCount(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userId = request.getParameter("memberId");
        int i=service.getShoppingCartCount(userId);
        response.getWriter().println(i);
        response.getWriter().close();;

    }


    /**
     * 展示购物车
     *
     * @param request 请求对象
     * @param response 响应对象
     * @throws IOException IO异常
     */
    private void showCartList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username=request.getParameter("username");

        TbMember tbMember=memberService.getMember(username);
        //获取该用户的所有商品 以及对应的数量
        List<TbUsercar> cart=service.getCart(tbMember.getId());
        List<TbCart> carts=new ArrayList<>();
        for (TbUsercar tbUsercar : cart) {
            TbGoods goods=goodsService.getGoodsById(tbUsercar.getGoodsid());
            String price;
            if ("限时折扣品".equals(goods.getClassname())){
                //获取该会员等级
                String zhekou;
                String vip=tbMember.getGrade();
                if ("铜牌会员".equals(vip)){
                    zhekou="0.8";
                }else{
                    zhekou="0.3";
                }
                 price=goods.getPrice()+"*"+zhekou;
            }else {
                 price=goods.getPrice();
            }
            String goodsName=goods.getGoodsname();
            String count=tbUsercar.getCount();
            String id=tbUsercar.getId();

            TbCart tbCart=new TbCart();
            tbCart.setGoodsName(goodsName);
            tbCart.setPrice(price);
            tbCart.setCartCount(count);
            tbCart.setCartId(id);
            carts.add(tbCart);
        }

        System.out.println(cart.size());

        JSONObject jsonObject=new JSONObject();
        jsonObject.put("cart",carts);
        jsonObject.put("cartTable",cart);
        PrintWriter out = response.getWriter();
        out.println(jsonObject);
        out.close();

    }











}
