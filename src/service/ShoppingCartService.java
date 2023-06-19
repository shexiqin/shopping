package service;

import bean.TbUsercar;
import dao.ShoppingCartDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShoppingCartService {
    ShoppingCartDao dao = new ShoppingCartDao();


    /**
     * 添加购物车
     *
     * @param goodId 商品ID
     * @param memberId 用户ID
     */
    public int addShoppingCart(String goodId,String memberId){
        return dao.addShoppingCart(goodId,memberId);
    }


    /**
     * 删除购物车商品
     *
     * @param id 购物车id
     */
    public int del(String id){
        return dao.del(id);
    }


    /**
     * 清空购物车
     *
     * @param memberId 会员id
     * @return 删除成功与否 >0成功，=0失败
     */
    public int emptyShoppingCart(String memberId){
        return dao.emptyShoppingCart(memberId);
    }


    /**
     * 获取用户购物车数量
     *
     * @param userId 用户id
     * @return 获取成功与否  >0获取成功，=0获取失败
     */
    public int getShoppingCartCount(String userId) {
        return dao.selShoppingCartCount(userId);
    }


    /**
     * 查看购物车
     *
     * @param goodid  商品id
     * @param memberid  会员id
     * @return 查看成功与否  >0成功，=0失败
     */
    public int checkCart(String goodid, String memberid) {
        return dao.countCart(goodid,memberid);
    }


    /**
     * 查询购物车商品记录条数
     *
     * @param goodid 商品id
     * @param memberid 会员id
     * @return 购物车商品记录条数
     */
    public String getCarCount(String goodid, String memberid) {
        return dao.queryCarCount(goodid,memberid);
    }


    /**
     * 添加购物车商品数量
     *
     * @param tbUsercar 用户购物车
     */
    public void addCartCount(TbUsercar tbUsercar) {
        dao.updateCarCount(tbUsercar);
    }


    /**
     * 根据用户id获取所有该用户商品
     *
     * @param id 用户id
     * @return 购物车列表
     */
    public List<TbUsercar> getCart(String id) {
        return dao.queryCart(id);
    }


    /**
     * 删除购物车列表
     *
     * @param id 用户id
     */
    public void deleteCartGoods(String id) {
        dao.deleteCart(id);
    }


    /**
     * 修改购物车商品
     *
     * @param id id
     * @param count 数量
     */
    public void updateCartCount(String id, String count) {
        dao.updateCarCountById(id,count);
    }


    /**
     * 清空购物车
     *
     * @param memberid 会员id
     */
    public void clearCart(String memberid) {
        dao.deletCart(memberid);
    }
}
