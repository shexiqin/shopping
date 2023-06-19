package dao;

import bean.TbUsercar;
import util.DbUtil;

import java.lang.reflect.Parameter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartDao {
    DbUtil dbUtil = DbUtil.getInstance();


    /**
     * 添加购物车
     *
     * @param goodsId 商品ID
     * @param memberId 用户ID
     */
    public int addShoppingCart(String goodsId,String memberId){
        String sql = "insert into TB_USERCAR (ID,GOODSID,MEMBERID) values (SEQ_USERCAR.nextval,?,?)";
        ArrayList<Object> params = new ArrayList<>();
        params.add(goodsId);
        params.add(memberId);
        return dbUtil.execute(sql,params);
    }


    /**
     * 删除购物车商品
     *
     * @param id 购物车id
     */
    public int del(String id){
        String sql = "delete from TB_USERCAR where ID = ?";
        ArrayList<Object> params = new ArrayList<>();
        params.add(id);
        return dbUtil.execute(sql,params);
    }


    /**
     * 查询购物车信息
     *
     * @return  List<TbUsercar>用户购物车列表
     */
    public List<TbUsercar> selectGoodsByShoppingCart(int memberId) {
        String sql = "select c.GOODSID,g.GOODSNAME,g.PRICE,c.COUNT from TB_USERCAR c,TB_GOODS g where c.GOODSID = g.ID and MEMBERID = ?";
        ArrayList<Object> params = new ArrayList<>();
        params.add(memberId);
        return dbUtil.queryListObject(sql,params,TbUsercar.class);
    }


    /**
     * 清空购物车
     *
     * @param memberId 会员id
     * @return 删除成功与否 >0成功，=0失败
     */
    public int emptyShoppingCart(String memberId){
        String sql = "delete from TB_USERCAR where MEMBERID = ?";
        ArrayList<Object> params = new ArrayList<>();
        params.add(memberId);
        return dbUtil.execute(sql,params);
    }


    /**
     * 获取用户购物车数量
     *
     * @param userId 用户id
     * @return 获取成功与否  >0获取成功，=0获取失败
     */
    public int selShoppingCartCount(String userId) {
        String sql = "select count(1) from TB_USERCAR where MEMBERID=?";
        ArrayList<Object> list = new ArrayList<>();
        list.add(userId);
        return dbUtil.count(sql,list);
    }


    /**
     * 查看购物车
     *
     * @param goodid  商品id
     * @param memberid  会员id
     * @return 查看成功与否  >0成功，=0失败
     */
    public int countCart(String goodid, String memberid) {
        String sql="select count(1) from tb_usercar where goodsid=? and memberid=?";
        ArrayList<Object> params=new ArrayList<>();
        params.add(goodid);
        params.add(memberid);
        return dbUtil.count(sql,params);
    }


    /**
     * 查询购物车商品记录条数
     *
     * @param goodid 商品id
     * @param memberid 会员id
     * @return 购物车商品记录条数
     */
    public String queryCarCount(String goodid, String memberid) {
        String sql="select count from tb_usercar where goodsid=? and memberid=?";
        ArrayList<Object> params=new ArrayList<>();
        params.add(goodid);
        params.add(memberid);

        TbUsercar tbUsercar=dbUtil.queryObject(sql,params,TbUsercar.class);
        return tbUsercar.getCount();
    }


    /**
     * 添加购物车商品数量
     *
     * @param tbUsercar 用户购物车
     */
    public void updateCarCount(TbUsercar tbUsercar) {
        String sql="update tb_usercar set count=? where goodsid=? and memberid=?";
        ArrayList<Object> params=new ArrayList<>();
        params.add(tbUsercar.getCount());
        params.add(tbUsercar.getGoodsid());
        params.add(tbUsercar.getMemberid());

        dbUtil.execute(sql,params);
    }


    /**
     * 根据用户id获取所有该用户商品
     *
     * @param id 用户id
     * @return 购物车列表
     */
    public List<TbUsercar> queryCart(String id) {
        String sql="select id,goodsid,count from tb_usercar where memberid=?";
        ArrayList<Object> parmas=new ArrayList<>();
        parmas.add(id);

        return dbUtil.queryListObject(sql,parmas,TbUsercar.class);
    }


    /**
     * 删除购物车列表
     *
     * @param id 用户id
     */
    public void deleteCart(String id) {
        String sql="delete tb_usercar where id=?";
        ArrayList<Object> params=new ArrayList<>();
        params.add(id);
        dbUtil.execute(sql,params);
    }


    /**
     * 修改购物车商品
     *
     * @param id id
     * @param count 数量
     */
    public void updateCarCountById(String id, String count) {
        String sql="update tb_usercar set count=? where id=?";
        ArrayList<Object> params=new ArrayList<>();
        params.add(count);
        params.add(id);
        int flg= dbUtil.execute(sql,params);
    }


    /**
     * 清空购物车
     *
     * @param memberid 会员id
     */
    public void deletCart(String memberid) {
        String sql="delete tb_usercar where memberid=?";
        ArrayList<Object> params=new ArrayList<>();
        params.add(memberid);
        int flg=dbUtil.execute(sql,params);
    }

}
