package dao;

import bean.TbClasses;
import bean.TbGoods;
import util.DbUtil;

import java.util.ArrayList;
import java.util.List;

public class GoodsDao {
    DbUtil dbUtil=DbUtil.getInstance();


    /**
     * 查询商品总数
     *
     * @return  数量
     */
    public int selectTotalCount() {
        String sql="select count(1) from tb_goods";
        return dbUtil.count(sql,null);
    }


    /**
     * 分页查询商品
     *
     * @param start  起始位置
     * @param end  结束位置
     * @return  List<TbGoods>商品列表
     */
    public List<TbGoods> queryGoodsListBySplit(int start, int end) {
        String sql="select ID,GOODSNAME,price,credate from(select ID,GOODSNAME,price,credate,ROWNUM r from TB_GOODS) where r>=? and r<=?";
        ArrayList<Object> params=new ArrayList<>();
        params.add(start);
        params.add(end);
        return dbUtil.queryListObject(sql,params,TbGoods.class);
    }


    /**
     * 查询所有分类
     *
     * @return  List<TbClasses>类别列表
     */
    public List<TbClasses> queryGoodsClass() {
        String sql="select id,name from tb_classes";
        return dbUtil.queryListObject(sql,null,TbClasses.class);
    }


    /**
     * 插入商品类别
     *
     * @param goodsClass  类别
     * @return 插入成功与否   1 成功   0 失败
     */
    public int insertGoodsClass(String goodsClass){
        String sql ="insert into TB_CLASSES (ID,NAME) values (SEQ_CLASSES.nextval,?)";
        ArrayList<Object> params = new ArrayList<>();
        params.add(goodsClass);
        return dbUtil.execute(sql,params);
    }


    /**
     * 通过类别名找类别ID
     *
     * @param options  类别名
     * @return  ID
     */
    public String queryGoodsClass(String options) {
        String sql="select id from TB_CLASSES where name=?";
        ArrayList<Object> params=new ArrayList<>();
        params.add(options);
        TbClasses classes=dbUtil.queryObject(sql,params,TbClasses.class);
        return classes.getId();
    }


    /**
     * 添加商品
     *
     * @param tbGoods  tbGoods对象
     * @return  插入成功与否   1 插入成功    0 插入失败
     */
    public int insertGoods(TbGoods tbGoods) {
        String sql2="insert into tb_goods(id,goodsname,price,picture,credate,introduce,classname) values(SEQ_GOODS.nextval,?,?,?,to_date(?,'yyyy-MM-dd hh24:mi:ss'),?,?)";
        ArrayList<Object> params=new ArrayList<>();
        params.add(tbGoods.getGoodsname());
        params.add(tbGoods.getPrice());
        params.add(tbGoods.getPicture());
        params.add(tbGoods.getCredate());
        params.add(tbGoods.getIntroduce());
        params.add(tbGoods.getClassname());
        return dbUtil.execute(sql2,params);
    }


    /**
     * 得到商品
     *
     * @param goodsName 商品名
     * @return 商品
     */
    public TbGoods getGoods(String goodsName) {
        String sql="select id,goodsname,price,picture,introduce from tb_goods where goodsname=?";
        ArrayList<Object> params=new ArrayList<>();
        params.add(goodsName);

        return dbUtil.queryObject(sql,params,TbGoods.class);
    }


    /**
     * 修改商品
     *
     * @param tbGoods 商品
     * @return 修改成功与否  >0修改成功， =0修改失败
     */
    public int updateGoods(TbGoods tbGoods) {

        String sql="update tb_goods set goodsname=?,price=?,picture=?,introduce=? where id=?";
        ArrayList<Object> params=new ArrayList<>();
        params.add(tbGoods.getGoodsname());
        params.add(tbGoods.getPrice());
        params.add(tbGoods.getPicture());
        params.add(tbGoods.getIntroduce());
        params.add(tbGoods.getId());

        int count =dbUtil.execute(sql,params);
        return count;
    }


    /**
     * 删除商品
     *
     * @param goodsName 商品名
     */
    public void deleteGoods(String goodsName) {
        String sql="delete tb_goods where goodsname=?";
        ArrayList<Object> parmas=new ArrayList<>();
        parmas.add(goodsName);
        int count= dbUtil.execute(sql,parmas);
    }


    /**
     * 查询商品详细信息
     *
     * @param goodsId  商品ID
     * @return  TbGoods对象
     */
    public TbGoods selectGoodsDetail(int goodsId) {
        String sql = "select ID, GOODSNAME, PRICE, PICTURE, CREDATE, INTRODUCE, CLASSNAME, UN_LINE from TB_GOODS where ID=?";
        ArrayList<Object> params = new ArrayList<>();
        params.add(goodsId);
        return dbUtil.queryObject(sql,params,TbGoods.class);
    }


    /**
     * 通过类别名查询所有的商品
     *
     * @param className 类别名
     * @return 商品实体对象
     */
    public List<TbGoods> selGoodsByClass(String className) {
        String sql = "select ID, GOODSNAME, PRICE, PICTURE, CREDATE, INTRODUCE, CLASSNAME, UN_LINE from TB_GOODS where classname=?";
        ArrayList<Object> params = new ArrayList<>();
        params.add(className);
        return dbUtil.queryListObject(sql,params,TbGoods.class);
    }


    /**
     * 类别名查询商品
     *
     * @param goodId 商品id
     * @return 商品
     */
    public TbGoods selGoodsById(Object goodId) {
        String sql="select ID, GOODSNAME, PRICE, PICTURE, CREDATE, INTRODUCE, CLASSNAME, UN_LINE from tb_goods where id=?";
        ArrayList<Object> params=new ArrayList<>();
        params.add(goodId);
        TbGoods tbGoods=dbUtil.queryObject(sql,params,TbGoods.class);
        System.out.println(tbGoods.getPicture());
        return tbGoods;
    }


    /**
     * 获取所有商品
     *
     * @return 商品列表
     */
    public List selAllGoods() {
        String sql = "select ID, GOODSNAME, PRICE, PICTURE, CREDATE, INTRODUCE, CLASSNAME, UN_LINE from TB_GOODS";
        return dbUtil.queryListObject(sql,null,TbGoods.class);
    }


    /**
     * 根据条件查询个数
     *
     * @param name 名字
     * @param lowPrice 价格底线
     * @param upPrice 价格上限
     * @return 个数
     */
    public int selectGoodCountBy(String name, String lowPrice, String upPrice) {
        String  sql= "select count(1) from TB_GOODS where 1=1";
        ArrayList<Object> params = new ArrayList<>();
        if (!"".equals(name)){
            params.add("%"+name+"%");
            sql = sql+ " and GOODSNAME like ?";
        }
        if(!"".equals(lowPrice)){
            params.add(lowPrice);
            sql = sql+ " and PRICE>=? ";
        }
        if(!"".equals(upPrice)){
            params.add(upPrice);
            sql = sql+ " and PRICE<=? ";
        }
        return dbUtil.count(sql,params);
    }


    /**
     * 条件查询
     *
     * @param start  起始
     * @param end  结束
     * @param name  商品名
     * @param lowPrice  底价
     * @param upPrice  顶价
     * @param sortBy  排序
     * @return  List<TbGoods>商品列表
     */
    public List<TbGoods> selectGoodsBy(int start, int end, String name, String lowPrice, String upPrice, String sortBy) {

        String sql2 = "select ID,GOODSNAME,PICTURE,PRICE,CREDATE,ROWNUM R from TB_GOODS where 1=1";
        ArrayList<Object> params = new ArrayList<>();
       if (!"".equals(name)){
            params.add("%"+name+"%");
            sql2 = sql2+ " and GOODSNAME like ?";
        }
        if(!"".equals(lowPrice)){
            params.add(lowPrice);
            sql2 = sql2+ " and PRICE>=?";
        }
        if(!"".equals(upPrice)){
            params.add(upPrice);
            sql2 = sql2+ " and PRICE<=?";
        }
        if (!"".equals(sortBy)){
            if("asc".equals(sortBy)){
                sql2 = sql2+ " order by PRICE asc";
            }else {
                sql2 = sql2+ " order by PRICE desc";
            }
        }
        String sql1 ="select ID,GOODSNAME,PICTURE,PRICE,CREDATE from("+sql2+") where R>=? and R<=?";
        params.add(start);
        params.add(end);
        return dbUtil.queryListObject(sql1,params,TbGoods.class);
    }

}
