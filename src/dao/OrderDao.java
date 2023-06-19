package dao;

import bean.TbOrder;
import bean.TbOrderDetail;
import bean.TbUsercar;
import util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {
    DbUtil dbUtil = DbUtil.getInstance();


    /**
     * 查询订单的数量
     *
     * @return  数量
     */
    public int selectTotalCount() {
        String sql = "select count(1) from TB_ORDER";
        return dbUtil.count(sql,null);
    }


    /**
     * 通过ID查询订单
     *
     * @param memberId  ID
     * @return  查询成功与否  >0成功，=0失败
     */
    public int selectTotalCount(String memberId) {
        String sql = "select count(1) from TB_ORDER where MEMBERID=?";
        ArrayList<Object> params = new ArrayList<>();
        params.add(memberId);
        return dbUtil.count(sql,params);
    }


    /**
     * 分页查询订单列表
     *
     * @param start  起始位置
     * @param end  结束位置
     * @return  List<TbOrder>订单列表
     */
    public List<TbOrder> selectOrderList(int start, int end) {
        String sql = "select ID, ORDERCODE, BNUMBER, USERNAME, ORDERDATE, ENFORCE from(select t.*,ROWNUM r from TB_ORDER t where ROWNUM<=?) where r>=?";
        ArrayList<Object> params = new ArrayList<>();
        params.add(end);
        params.add(start);
        return dbUtil.queryListObject(sql,params,TbOrder.class);
    }


    /**
     * 分页查询会员订单列表
     *
     * @param start  起始位置
     * @param end  结束位置
     * @return  List<TbOrder>订单列表
     */
    public List<TbOrder> selectOrderListById(int start, int end, String memberId) {
        String sql = "select ID, ORDERCODE, BNUMBER, USERNAME, ORDERDATE, ENFORCE from(select t.*,ROWNUM r from TB_ORDER t where MEMBERID=? and ROWNUM<=?) where r>=?";
        ArrayList<Object> params = new ArrayList<>();
        params.add(memberId);
        params.add(end);
        params.add(start);
        return dbUtil.queryListObject(sql,params,TbOrder.class);
    }


    /**
     * 查询订单详细信息
     *
     * @param id  订单ID
     * @return  TbOrder订单
     */
    public TbOrder selectOrderById(String id) {
        String sql = "select o.ID, m.USERNAME NAME, ORDERCODE, o.USERNAME, o.ADDRESS, o.POSTCODE, o.TEL, PAY, CARRY, ORDERDATE, BZ from TB_ORDER o,TB_MEMBER m where o.MEMBERID = m.ID AND o.ID=?";
        ArrayList<Object> params = new ArrayList<>();
        params.add(id);
        return dbUtil.queryObject(sql,params,TbOrder.class);
    }


    /**
     * 查询订单的明细
     *
     * @param id  订单ID
     * @return  List<TbOrderDetail>订单明细列表
     */
    public List<TbOrderDetail> selectOrderDetailById(String id) {
        String sql = "select d.ID,g.GOODSNAME,d.PRICE,d.NUMBERS from TB_ORDER_DETAIL d,TB_GOODS g where  d.GOODSID = g.ID and ORDERID =?";
        ArrayList<Object> params = new ArrayList<>();
        params.add(id);
        return dbUtil.queryListObject(sql,params,TbOrderDetail.class);
    }


    /**
     * 查询订单中商品的品种数
     *
     * @param memberId  会员ID
     * @return  品种数
     */
    public int selectGoodsClassCount(int memberId) {
        String sql = "select count(CLASSNAME) from TB_GOODS where ID in (select GOODSID from TB_USERCAR where MEMBERID = ?) order by CLASSNAME";
        ArrayList<Object> params = new ArrayList<>();
        params.add(memberId);
        return dbUtil.count(sql,params);
    }


    /**
     * 生成订单
     *
     * @param tbOrder   tbOrder对象
     * @param usercarList  usercarList购物车集合
     */
    public boolean insertOrder(TbOrder tbOrder, List<TbUsercar> usercarList) {
        boolean a = false;
        Connection connection = dbUtil.getConnection();
        PreparedStatement pstmt = null;
        String sql1 = "insert into TB_ORDER (ID, MEMBERID, ORDERCODE, BNUMBER, USERNAME, ADDRESS, POSTCODE, TEL, PAY, CARRY, ORDERDATE, BZ) VALUES (SEQ_ORDER.nextval,?,?,?,?,?,?,?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?)";
        String sql2 = "insert into TB_ORDER_DETAIL (ID, ORDERID, GOODSID, PRICE, NUMBERS) VALUES (SEQ_ORDER_DETAIL.nextval,?,?,?,?)";
        String sql3 = "select ID from TB_ORDER where ORDERCODE=?";
        String sql4 = "delete from TB_USERCAR where MEMBERID = ?";
        try {
            connection.setAutoCommit(false);
            //插入订单
            ArrayList<Object> params = new ArrayList<>();
            params.add(tbOrder.getMemberid());
            params.add(tbOrder.getOrdercode());
            params.add(tbOrder.getBnumber());
            params.add(tbOrder.getUsername());
            params.add(tbOrder.getAddress());
            params.add(tbOrder.getPostcode());
            params.add(tbOrder.getTel());
            params.add(tbOrder.getPay());
            params.add(tbOrder.getCarry());
            params.add(tbOrder.getOrderdate());
            params.add(tbOrder.getBz());
            dbUtil.execute(connection,sql1,params);

            //查询订单ID
            pstmt = connection.prepareStatement(sql3);
            pstmt.setString(1,tbOrder.getOrdercode());
            ResultSet resultSet = pstmt.executeQuery();
            resultSet.next();
            int orderID = resultSet.getInt(1);

            //批量插入订单明细表
            for (TbUsercar tbUsercar : usercarList) {
                pstmt = connection.prepareStatement(sql2);
                pstmt.setInt(1,orderID);
                pstmt.setInt(2,Integer.parseInt(tbUsercar.getGoodsid()));
                pstmt.setDouble(3,Double.parseDouble(tbUsercar.getPrice()));
                pstmt.setInt(4,Integer.parseInt(tbUsercar.getCount()));
                pstmt.addBatch();
                pstmt.executeBatch();
            }

            //删除购物车表
            pstmt = connection.prepareStatement(sql4);
            pstmt.setInt(1,Integer.parseInt(tbOrder.getMemberid()));
            pstmt.executeUpdate();

            //提交事务
            connection.commit();
            a = true;
        } catch (Exception throwables) {
            throwables.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }finally {
            dbUtil.freeStatement(pstmt);
            dbUtil.freeConnection(connection);
            return a;
        }

    }


    /**
     * 处理退货，退款
     *
     * @param id  ID
     * @param message  信息
     */
    public void updateOrderBz(String id, String message) {
        String sql = "update TB_ORDER set BZ =?,ENFORCE = 3 where ID = ?";
        ArrayList<Object> params = new ArrayList<>();
        params.add(message);
        params.add(id);
        dbUtil.execute(sql,params);
    }


    /**
     * 删除订单
     *
     * @param id  ID
     */
    public void delOrder(String id) {
        String sql = "delete from TB_ORDER where ID=?";
        ArrayList<Object> params = new ArrayList<>();
        params.add(id);
        dbUtil.execute(sql,params);
    }


    /**
     * 会员退货
     *
     * @param id  ID
     * @param returnMsg  退货信息
     */
    public void updateOrderBzByMember(String id, String returnMsg) {
        String sql = "update TB_ORDER set BZ =?,ENFORCE=1 where ID = ?";
        ArrayList<Object> params = new ArrayList<>();
        params.add(returnMsg);
        params.add(id);
        dbUtil.execute(sql,params);
    }


    /**
     * 查询备注
     *
     * @param id  ID
     * @return  TbOrder订单
     */
    public TbOrder selectOrderBz(String id) {
        String sql = "select BZ from TB_ORDER where ID=?";
        ArrayList<Object> params = new ArrayList<>();
        params.add(id);
        return dbUtil.queryObject(sql,params,TbOrder.class);
    }


    /**
     * 会员退款
     *
     * @param id  ID
     */
    public void updateOrderEnforceReturnMoney(String id) {
        String sql = "update TB_ORDER set ENFORCE=2 where ID = ?";
        ArrayList<Object> params = new ArrayList<>();
        params.add(id);
        dbUtil.execute(sql,params);
    }


    /**
     * 删除订单明细
     *
     * @param id  订单ID
     */
    public void delOrderDetail(String id) {
        String sql = "delete from TB_ORDER_DETAIL where ORDERID=?";
        ArrayList<Object> params = new ArrayList<>();
        params.add(id);
        dbUtil.execute(sql,params);
    }

}
