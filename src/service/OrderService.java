package service;


import bean.TbOrder;
import bean.TbOrderDetail;
import bean.TbUsercar;
import dao.OrderDao;
import dao.ShoppingCartDao;

import java.util.List;

public class OrderService {
    OrderDao orderDao = new OrderDao();
    ShoppingCartDao shoppingCartDao = new ShoppingCartDao();


    /**
     * 查询全部订单的数量
     *
     * @return 数量
     */
    public int getOrderCount() {
        return orderDao.selectTotalCount();
    }


    /**
     * 通过ID查询订单
     *
     * @param memberId  ID
     * @return  查询成功与否   >0成功，=0失败
     */
    public int getOrderCount(String memberId) {
        return orderDao.selectTotalCount(memberId);
    }


    /**
     * 分页查询订单列表
     *
     * @param start  起始位置
     * @param end  结束位置
     * @return  List<TbOrder>订单列表
     */
    public List<TbOrder> getOrderList(int start, int end) {
        return orderDao.selectOrderList(start,end);
    }


    /**
     * 分页查询会员订单列表
     *
     * @param start  起始位置
     * @param end  结束位置
     * @return  List<TbOrder>订单列表
     */
    public List<TbOrder> getOrderListById(int start, int end, String memberId) {
        return orderDao.selectOrderListById(start,end,memberId);
    }


    /**
     * 查询订单详细信息
     *
     * @param id  ID
     * @return  TbOrder订单
     */
    public TbOrder getOrderById(String id) {
        return orderDao.selectOrderById(id);
    }


    /**
     * 查询订单的明细
     *
     * @param id  订单ID
     * @return  List<TbOrderDetail>订单明细列表
     */
    public List<TbOrderDetail> getOrderDetailById(String id) {
        return orderDao.selectOrderDetailById(id);
    }


    /**
     * 查询订单中商品的品种数
     *
     * @param memberId  会员ID
     * @return  品种数
     */
    public int getGoodsClassCount(int memberId) {

        return orderDao.selectGoodsClassCount(memberId);
    }


    /**
     * 生成订单
     *
     * @param tbOrder  tbOrder对象
     */
    public boolean setOrder(TbOrder tbOrder) {
        List<TbUsercar> usercarList = shoppingCartDao.selectGoodsByShoppingCart(Integer.parseInt(tbOrder.getMemberid()));
        return orderDao.insertOrder(tbOrder, usercarList);
    }


    /**
     * 修改备注
     *
     * @param id  ID
     * @param message  信息
     */
    public void modifyOrderEnforce(String id, String message) {
        orderDao.updateOrderBz(id,message);
    }


    /**
     * 删除订单
     *
     * @param id  ID
     */
    public void closeOrder(String id) {
        orderDao.delOrder(id);
        orderDao.delOrderDetail(id);
    }


    /**
     * 会员退货
     *
     * @param id  ID
     * @param returnMsg 退货信息
     */
    public void modifyOrderEnforceByMember(String id, String returnMsg) {
        orderDao.updateOrderBzByMember(id,returnMsg);
    }


    /**
     * 查询备注
     *
     * @param id  ID
     * @return  TbOrder订单
     */
    public TbOrder getOrderBz(String id) {
        return orderDao.selectOrderBz(id);
    }


    /**
     * 会员退款
     *
     * @param id  ID
     */
    public void modifyOrderEnforceReturnMoney(String id) {
        orderDao.updateOrderEnforceReturnMoney(id);
    }
}
